package cn.adwalker.ad.admin.operation.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import jxl.Sheet;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.adwalker.ad.admin.common.vo.SysUserVo;
import cn.adwalker.ad.admin.operation.bean.IncomeRevenueBean;
import cn.adwalker.ad.admin.operation.form.AdIosEntering;
import cn.adwalker.ad.admin.operation.service.IOperationConfirmIncome;
import cn.adwalker.ad.admin.operation.vo.IncomeConfirmVo;
import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.util.DateUtil;
import cn.adwalker.core.util.lang.ObjectUtils;
import cn.adwalker.model.operation.dao.IAdEffectDataDao;
import cn.adwalker.model.operation.dao.IOperationConfirmIncomeDao;
import cn.adwalker.model.operation.domain.FinanceAdEffectData;
import cn.adwalker.model.operation.domain.IncomeConfirm;

/**
 * <p>
 * Title: OperationAdEffectDataServiceImpl
 * </p>
 * <p>
 * Description:收入确认service
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-5-15
 */
@Service(value = "operationConfirmIncome")
@Transactional
@JsonIgnoreProperties(value = { "hibernateLazyInitializer" })
public class OperationConfirmIncomeImpl implements IOperationConfirmIncome {

	/** 日志记录器 */
	Logger logger = Logger.getLogger(OperationConfirmIncomeImpl.class);

	/** 广告主广告效果录入 */
	@Resource
	private IAdEffectDataDao adEffectDataDao;

	/** 确认收入 */
	@Resource
	private IOperationConfirmIncomeDao confirmIncomeDao;

	@Override
	public int insert(FinanceAdEffectData financeAdEffectData) throws Exception {
		return this.adEffectDataDao.insert(financeAdEffectData);
	}

	@Override
	public int update(FinanceAdEffectData financeAdEffectData) throws Exception {
		return this.adEffectDataDao.update(financeAdEffectData);
	}
	

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findByPage
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param bean
	 * @param pageInfor
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.operation.service.IOperationConfirmIncome#findByPage(cn.adwalker.ad.admin.operation.bean.IncomeRevenueBean, cn.adwalker.core.page.IPageInfo)
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.NEVER)
	@Override
	public List<IncomeConfirmVo> findByPage(IncomeRevenueBean bean, IPageInfo pageInfor) throws Exception {
		List<Object> list = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer(" T_IOS_INCOME_NUMBER t left join T_CAMPAIGN_CONFIG a on t.ad_id = a.id left join t_placement p on a.placement_id=p.id where 1 = 1");
		// 广告id/名称
		if (ObjectUtils.isNotEmpty(bean.getCampaign())) {
			sb.append(" and (upper(p.name)  like ? or a.placement_id =? )");
			list.add("%" + bean.getCampaign_name().toUpperCase() + "%");
			list.add(bean.getCampaign_id());
		}
		// mac
		if (StringUtils.isNotEmpty((bean.getIncome_mac()))) {
			sb.append(" and t.income_mac like ?");
			list.add("%" + bean.getIncome_mac().trim().toUpperCase() + "%");
		}
		// 效果发生时间
		if (ObjectUtils.isNotEmpty(bean.getStatStartTime())) {
			sb.append(" and date_format(t.create_time,'%Y-%m-%d') >='" + bean.getStatStartTime() + "'");
		}
		if (ObjectUtils.isNotEmpty(bean.getStatEndTime())) {
			sb.append(" and date_format(t.create_time,'%Y-%m-%d') <='" + bean.getStatEndTime() + "'");
		}

		// 状态
		if (ObjectUtils.isNotEmpty((bean.getStatus()))) {
			sb.append(" and t.status ='");
			sb.append(bean.getStatus());
			sb.append("'");
		}

		return (List<IncomeConfirmVo>) confirmIncomeDao.findByPage("t.*, p.name as ad_name,a.placement_id", sb.toString(), list.toArray(), " order by t.id desc ", IncomeConfirmVo.class, pageInfor);
	}



	/**
	 * \ (non-Javadoc)
	 * <p>
	 * Title: tranAudit
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param ids
	 * @param status
	 * @param request
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.operation.service.IOperationConfirmIncome#tranAudit(java.lang.Long[], int, javax.servlet.http.HttpServletRequest)
	 */
	@Override
	@Transactional(rollbackFor = { Exception.class })
	public void tranAudit(String[] s_date, Long[] ad_id, Integer[] ch_plf_type, SysUserVo curManageUser) throws Exception {
		Long manageId = 0L;
		List<IncomeConfirm> list = null;
		if (curManageUser != null) {
			manageId = curManageUser.getId();
		}
		try {
			this.confirmIncomeDao.batchDealStatus(s_date, ad_id, manageId, ch_plf_type, "");// 批量操作状态
			list = this.confirmIncomeDao.findIosDate(s_date, ad_id, manageId, ch_plf_type);// 批量操作状态
			this.confirmIncomeDao.insertIosDate(list);// 批量操作状态
			// 日志记录
		} catch (Exception e) {
			e.printStackTrace();
			// 日志记录
		}
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: insertAdEffectIosData
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param list
	 * @param currUser
	 * @see cn.adwalker.ad.admin.operation.service.IOperationConfirmIncome#insertAdEffectIosData(java.util.List, cn.adwalker.ad.admin.common.vo.SysUserVo)
	 */
	@Override
	public void insertAdEffectIosData(List<AdIosEntering> list) throws Exception {

		this.confirmIncomeDao.insertAdEffectIosData(list);
	}

	/**
	 * de(non-Javadoc)
	 * <p>
	 * Title: checkDataIos
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param sheet
	 * @param currUser
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.operation.service.IOperationConfirmIncome#checkDataIos(jxl.Sheet, cn.adwalker.ad.admin.common.vo.SysUserVo)
	 */
	public HashMap<String, Object> checkDataIos(Sheet sheet, SysUserVo currUser) throws Exception {
		HashMap<String, Object> resMap = new HashMap<String, Object>();
		// 封装返回值
		List<AdIosEntering> list = new ArrayList<AdIosEntering>();
		String msg = "";
		for (int j = 1; j < sheet.getRows(); j++) {
			try {
				String adkey = sheet.getCell(0, j).getContents().trim();// 第一行
				if ("".equals(adkey)) {// 广告id为空
					msg = "adkey不能为空";
					break;
				}
				AdIosEntering enter = new AdIosEntering();
				enter.setStat_date(DateUtil.formatDate(new Date(), "yyyy-MM-dd"));
				if (ObjectUtils.isNotEmpty(adkey)) {
					enter.setAd_id(sheet.getCell(0, j).getContents().trim());
				}
				if (ObjectUtils.isNotEmpty(sheet.getCell(1, j).getContents().trim())) {
					enter.setIncome_mac(sheet.getCell(1, j).getContents().trim());
				}
				if (ObjectUtils.isNotEmpty(sheet.getCell(2, j).getContents().trim())) {
					enter.setOpenudid(sheet.getCell(2, j).getContents().trim());
				}
				if (ObjectUtils.isNotEmpty(sheet.getCell(3, j).getContents().trim())) {
					enter.setIdfa(sheet.getCell(3, j).getContents().trim());
				}
				enter.setCreate_time(new Date());
				enter.setStatus(0);
				enter.setManager_id(currUser.getId());
				list.add(enter);
			} catch (Exception e) {
				e.printStackTrace();
				msg = "数据异常！";
				break;
			}
		}
		if (!msg.equals("")) {
			resMap.put("error", msg);
		} else {
			resMap.put("list", list);
		}
		return resMap;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: tranAudit
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param ids
	 * @param manageUser
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.operation.service.IOperationConfirmIncome#tranAudit(java.lang.Long[], cn.adwalker.ad.admin.common.vo.SysUserVo)
	 */
	@Override
	public void tranAudit(Long[] ids, SysUserVo manageUser) throws Exception {
		// 设置激活状态
		List<Long> mapAd = new ArrayList<Long>();
		for (int i = 0; i < ids.length; i++) {
			mapAd.add(ids[i]);
		}
		// 批量修改状态
		Long manageId = 0L; // 当前登录管理员id
		if (manageUser != null) {
			manageId = manageUser.getId();
		}
		try {
			this.confirmIncomeDao.batchDealAdStatus(mapAd, 2, manageId);// 2：ios线下给积分

		} catch (Exception e) {
			e.printStackTrace();
			// 记录日志
		}
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findAll
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.operation.service.IOperationConfirmIncome#findAll(cn.adwalker.ad.admin.operation.bean.IncomeRevenueBean)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<IncomeConfirmVo> findAll(IncomeRevenueBean bean) throws Exception {

		List<IncomeConfirmVo> list = null;
		List<Object> param = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer(" T_IOS_INCOME_NUMBER t left join T_CAMPAIGN_CONFIG a on t.ad_id = a.id left join t_placement p on a.placement_id=p.id where 1 = 1");
		// 广告id/名称
		if (StringUtils.isNotEmpty(bean.getCampaign())) {
			sb.append(" and (a.placement_id)=? or  upper(p.name) like ? )");
			param.add(bean.getCampaign_id());
			param.add("%" + bean.getCampaign_name().trim().toUpperCase() + "%");
		}
		// mac
		if (StringUtils.isNotEmpty((bean.getIncome_mac()))) {
			sb.append(" and t.income_mac like ?");
			param.add("%" + bean.getIncome_mac().trim().toUpperCase() + "%");
		}
		// 效果发生时间
		if (ObjectUtils.isNotEmpty(bean.getStatStartTime())) {
			sb.append(" and date_format(t.create_time,'%Y-%m-%d') >='" + bean.getStatStartTime() + "'");
		}
		if (ObjectUtils.isNotEmpty(bean.getStatEndTime())) {
			sb.append(" and date_format(t.create_time,'%Y-%m-%d') <='" + bean.getStatEndTime() + "'");
		}

		// 状态
		if (ObjectUtils.isNotEmpty((bean.getStatus()))) {
			sb.append(" and t.status ='");
			sb.append(bean.getStatus());
			sb.append("'");
		}

		list = (List<IncomeConfirmVo>) confirmIncomeDao.findAll("t.*, p.name as ad_name,a.placement_id", sb.toString(), param.toArray(), " order by t.id desc ", IncomeConfirmVo.class);
		return list;

	}
}
