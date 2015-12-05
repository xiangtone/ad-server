package cn.adwalker.ad.admin.operation.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.adwalker.ad.admin.common.vo.SysUserVo;
import cn.adwalker.ad.admin.operation.bean.ConfirmIosNumberbean;
import cn.adwalker.ad.admin.operation.bean.IosNumDetailbean;
import cn.adwalker.ad.admin.operation.service.IConfirmationIosNumberService;
import cn.adwalker.ad.admin.operation.vo.AdvNumberSum;
import cn.adwalker.ad.admin.operation.vo.AdvNumberSumVo;
import cn.adwalker.ad.admin.operation.vo.ConfirmationIosNumberVo;
import cn.adwalker.ad.admin.operation.vo.FractionIosNumberVo;
import cn.adwalker.ad.admin.operation.vo.FractionNumberVo;
import cn.adwalker.ad.admin.operation.vo.IosNumDetailVo;
import cn.adwalker.ad.config.StatusConstant;
import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.util.lang.ObjectUtils;
import cn.adwalker.model.operation.dao.IActivateIosDao;
import cn.adwalker.model.operation.dao.IActivateIosDetailDao;
import cn.adwalker.model.operation.dao.IIosAndroidDayChannelNumDao;
import cn.adwalker.model.operation.dao.IIosAndroidDayNumDao;
import cn.adwalker.model.operation.domain.ActivateNumDetailIos;
import cn.adwalker.model.operation.domain.ActivateNumDetailIosSel;
import cn.adwalker.model.operation.domain.ActivateNumIos;
import cn.adwalker.model.operation.domain.IosAndroidDayChannelNum;
import cn.adwalker.model.operation.domain.IosAndroidDayNum;

/**
 * <p>
 * Title: ChannelEffectQueryServiceImpl
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-5-23
 */
@Service("confirmationIosNumberService")
public class ConfirmationIosNumberServiceImpl implements
		IConfirmationIosNumberService {

	@Resource
	private IActivateIosDetailDao confirmationIosNumberDao;
	@Resource
	private IActivateIosDao activateIosDao;
	
	@Resource
	private  IIosAndroidDayNumDao iosAndroidDayNumDao;
	
	@Resource
	private  IIosAndroidDayChannelNumDao iosAndroidDayChannelNumDao;

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findList
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param bean
	 * @param pageInfo
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.operation.service.IConfirmationNumberService#findList(cn.adwalker.admin.operation.bean.AdPriceBean,
	 *      cn.adwalker.core.page.IPageInfo)
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.NEVER)
	@Override
	public List<ConfirmationIosNumberVo> findList(ConfirmIosNumberbean bean,
			IPageInfo pageInfo) throws Exception {
		List<ConfirmationIosNumberVo> resultList = null;
		List<Object> list = new ArrayList<Object>();
		StringBuilder sb = new StringBuilder(
				" T_IOS_ACTIVATE_NUM a left join  T_CAMPAIGN t  on a.CAMPAIGN_ID=t.ID where 1=1 and a.status!=-3 ");
		if (bean != null) {
			if (ObjectUtils.isNotEmpty(bean.getStat_date())) {
				sb.append(" and a.static_date>=?");
				list.add(bean.getStat_date());
			}
			if (ObjectUtils.isNotEmpty(bean.getEnd_date())) {
				sb.append(" and a.static_date<=?");
				list.add(bean.getEnd_date());
			}
			if (ObjectUtils.isNotEmpty(bean.getCampaign())) {
				sb.append(" and (upper(t.CAMPAIGN_NAME)  like ? or t.id =? )");
				list.add("%" + bean.getCampaign_name().toUpperCase() + "%");
				list.add(bean.getCampaign_id());
			}
			if (bean.getStatus() != null) {
				sb.append(" and a.status=? ");
				list.add(bean.getStatus());
			}
		}

		resultList = (List<ConfirmationIosNumberVo>) confirmationIosNumberDao
				.findByPage("a.*,t.CAMPAIGN_NAME", sb.toString(),
						list.toArray(), " order by a.id desc ",
						ConfirmationIosNumberVo.class, pageInfo);
		if (resultList != null && resultList.size() > 0) {
			StringBuilder stringBuilder = null;
			for (ConfirmationIosNumberVo vo : resultList) {
				stringBuilder = new StringBuilder();
				List<FractionNumberVo> detailList = getMediaNum(vo.getId());
				if (detailList != null && detailList.size() > 0) {
					vo.setMedia_num(detailList.size());
					for (FractionNumberVo f : detailList) {
						stringBuilder.append(f.getMedia_name()).append("(")
								.append(f.getType_name()).append("),");
					}
					stringBuilder.deleteCharAt(stringBuilder.length() - 1);
					vo.setMedias(stringBuilder.toString());
				} else {
					vo.setMedia_num(0);
				}

			}

		}
		return resultList;
	}

	/**
	 * 
	 * <p>
	 * Title: getMediaNum
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @return
	 * @author cuidd
	 * @date 2013-7-16
	 * @return Integer
	 * @version 1.0
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private List<FractionNumberVo> getMediaNum(Long id) throws Exception {
		List<FractionNumberVo> list = (List<FractionNumberVo>) confirmationIosNumberDao
				.findAll(
						"SELECT l.*,c.name media_name,t.name type_name FROM  t_ios_activate_num_detail l left join V_MEDIA c on l.MEDIA_ID = c.id left join t_type t on t.id=l.type_id where l.parent_id=? ",
						new Object[] { id }, FractionNumberVo.class);
		return list;

	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: confirmationNumber
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param id
	 * @param number
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.operation.service.IConfirmationNumberService#confirmationNumber(java.lang.Long,
	 *      java.lang.Integer)
	 */
	@Override
	@Transactional(rollbackFor = { Exception.class })
	public void confIosNum(Long id, Integer number) throws Exception {
		List<FractionNumberVo> list = this.getMediaNum(id);
		boolean b = false;
		// 单包投放才进行以下操作
		if (list != null && list.size() == 1) {
			FractionNumberVo vo = list.get(0);
			confirmationIosNumberDao.saveConfirm(vo.getId(), number);
			b = true;

		}
		activateIosDao.restConfIosNum(id, number, b ? 2 : -1);

	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: fractionIos
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.operation.service.IConfirmationIosNumberService#fractionIos(java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.NEVER)
	@Override
	public List<FractionIosNumberVo> fractionIos(Long id) throws Exception {
		List<FractionIosNumberVo> list = null;
		if (id != null) {
			StringBuilder sb = new StringBuilder(
					" T_IOS_ACTIVATE_NUM_DETAIL l left join V_MEDIA c on l.MEDIA_ID = c.id left join t_ios_activate_num n on l.parent_id=n.id where 1=1 and l.parent_id=? ");
			list = (List<FractionIosNumberVo>) confirmationIosNumberDao
					.findAll(
							"l.id,l.click, l.activate ,n.campaign_id,l.static_date,l.type_id,l.media_id,l.confirm_num,l.sys_activate,c.name as media_name,l.media_type",
							sb.toString(), new Object[] { id },
							FractionIosNumberVo.class);

		}

		return list;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: saveConfIos
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param id
	 * @param confirm_num
	 * @param fraction_id
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.operation.service.IConfirmationIosNumberService#saveConfIos(java.lang.Long[],
	 *      java.lang.Integer[], java.lang.Long)
	 */
	@Override
	@Transactional(rollbackFor = { Exception.class })
	public void saveConfIos(Long[] id, Integer[] confirm_num,
			Integer[] comfirm_cost, Long fraction_id) throws Exception {
		// 保存IOS日确认数分数
		confirmationIosNumberDao.saveConfIos(id, confirm_num,comfirm_cost);
		// IOS日确认数分数状态
		activateIosDao.updateStatus(fraction_id,
				StatusConstant.FRACTION_STATUS_F);
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: submitConfNumIos
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param ids
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.operation.service.IConfirmationIosNumberService#submitConfNumIos(java.lang.String)
	 */
	@Override
	@Transactional(rollbackFor = { Exception.class })
	public void submitConfNumIos(String ids,SysUserVo manageUser) throws Exception {
		List <ActivateNumDetailIosSel> aNdis=new ArrayList<ActivateNumDetailIosSel>();
		ActivateNumIos plist=null;
		// 提交IOS日明细确认数
		activateIosDao.submitConfNumIos(ids);
		// 提交IOS日确认数
		activateIosDao.updateStatu(ids, 9);
		// 提交IOS日确认数到t_ios_android_day_num
		String arr[]=ids.split(",");
			for (String id : arr) {
				plist=activateIosDao.getById(Long.valueOf(id));
			IosAndroidDayNum dU =new IosAndroidDayNum();
				if(plist!=null){
					dU.setCampaign_id(plist.getCampaign_id());
					dU.setCharge_type(plist.getBalance_model());
					dU.setConfirm_amount(plist.getConfirm_amount());
					dU.setCreate_time(new Date());
					dU.setPackage_id(null);
					dU.setPrice(plist.getPrice());
					dU.setStatus(1);
					dU.setStatic_date(plist.getStatic_date());
					iosAndroidDayNumDao.insert(dU);
				}
				//查询ios的明细数据
				String sql="SELECT t.*,l.campaign_id FROM t_ios_activate_num_detail t "
						+ "LEFT JOIN t_ios_activate_num l ON t.`parent_id`=l.`id`  WHERE 1=1 AND t.media_type=1  AND t.status=9 AND  t.parent_id="+Long.valueOf(id);
					List<ActivateNumDetailIosSel>	tmp=(List<ActivateNumDetailIosSel>) confirmationIosNumberDao.findAll(sql,ActivateNumDetailIosSel.class);
					aNdis.addAll(tmp);
			}
			//查出明细数据，插入渠道成本明细表
			for (ActivateNumDetailIosSel aNdi : aNdis) {
				IosAndroidDayChannelNum  iadcm=new  IosAndroidDayChannelNum();
				if(aNdi!=null && aNdi.getMedia_type()==1 && aNdi.getStatus()==9){
					iadcm.setCampaign_id(aNdi.getCampaign_id());
					iadcm.setChannel_id(aNdi.getMedia_id());
					iadcm.setCharge_type(aNdi.getBlance_mode());
					iadcm.setParent_id(aNdi.getParent_id());
					iadcm.setPrice(aNdi.getOut_price());
					iadcm.setCreate_time(new Date());
					iadcm.setStatic_date(aNdi.getStatic_date());
					iadcm.setStatus(1);
					iadcm.setOperater_id(manageUser.getId());
					iadcm.setConfirm_amount(Integer.valueOf(aNdi.getComfirm_cost()==null?0:aNdi.getComfirm_cost()));
					iosAndroidDayChannelNumDao.insert(iadcm);
				}
			}
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findDetailByPage
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param bean
	 * @param pageInfo
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.operation.service.IConfirmationIosNumberService#findDetailByPage(cn.adwalker.ad.admin.operation.bean.IosNumDetailbean,
	 *      cn.adwalker.core.page.IPageInfo)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<IosNumDetailVo> findDetailByPage(IosNumDetailbean bean,
			IPageInfo pageInfo) throws Exception {
		List<IosNumDetailVo> resultList = null;
		List<Object> list = new ArrayList<Object>();
		StringBuilder sb = new StringBuilder(
				"  t_ios_activate_num_detail a LEFT JOIN V_ios_activate p ON a.parent_id=p.id LEFT JOIN V_MEDIA m ON a.media_id = m.id left join t_type e on a.type_id = e.id where 1 = 1 and p.status = 9 and a.status = 9");
		if (bean != null) {
			if (ObjectUtils.isNotEmpty(bean.getStatic_start_date())) {
				sb.append(" and a.static_date >=?");
				list.add(bean.getStatic_start_date());
			}

			if (ObjectUtils.isNotEmpty(bean.getStatic_end_date())) {
				sb.append(" and a.static_date<=?");
				list.add(bean.getStatic_end_date());
			}

			if (!StringUtils.isEmpty(bean.getCampaign_name())) {
				sb.append(" and upper(p.CAMPAIGN_NAME) like '%");
				sb.append(bean.getCampaign_name().trim().toUpperCase());
				sb.append("%'");
			}
			if (!StringUtils.isEmpty(bean.getMedia_name())) {
				sb.append(" and m.name like '%");
				sb.append(bean.getMedia_name());
				sb.append("%'");
			}
			if (bean.getMedia_type() != null) {
				sb.append(" and m.type=? ");
				list.add(bean.getMedia_type());
			}

		}

		resultList = (List<IosNumDetailVo>) confirmationIosNumberDao
				.findByPage(
						" a.*, p.campaign_id,p.CAMPAIGN_NAME,m.name media_name,e.name type_name",
						sb.toString(), list.toArray(), " order by a.id desc ",
						IosNumDetailVo.class, pageInfo);
		return resultList;
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
	 * @see cn.adwalker.ad.admin.operation.service.IConfirmationNumberService#findAll(cn.adwalker.admin.finance.bean.FinanceConsumebean)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<IosNumDetailVo> findAll(IosNumDetailbean bean) throws Exception {
		List<IosNumDetailVo> resultList = null;
		List<Object> list = new ArrayList<Object>();
		StringBuilder sb = new StringBuilder(
				" t_ios_activate_num_detail a LEFT JOIN V_ios_activate p ON a.parent_id=p.id LEFT JOIN V_MEDIA m ON a.media_id = m.id left join t_type e on a.type_id = e.id where 1 = 1 and p.status = 9 and a.status = 9");
		if (bean != null) {
			if (ObjectUtils.isNotEmpty(bean.getStatic_start_date())) {
				sb.append(" and a.static_date >=?");
				list.add(bean.getStatic_start_date());
			}

			if (ObjectUtils.isNotEmpty(bean.getStatic_end_date())) {
				sb.append(" and a.static_date<=?");
				list.add(bean.getStatic_end_date());
			}

			if (!StringUtils.isEmpty(bean.getCampaign_name())) {
				sb.append(" and upper(p.CAMPAIGN_NAME) like '%");
				sb.append(bean.getCampaign_name().trim().toUpperCase());
				sb.append("%'");
			}
			if (!StringUtils.isEmpty(bean.getMedia_name())) {
				sb.append(" and m.name like '%");
				sb.append(bean.getMedia_name());
				sb.append("%'");
			}
			if (bean.getMedia_type() != null) {
				sb.append(" and m.type=? ");
				list.add(bean.getMedia_type());
			}

		}

		resultList = (List<IosNumDetailVo>) confirmationIosNumberDao
				.findAll(
						" a.*, p.campaign_id,p.CAMPAIGN_NAME,m.name media_name,e.name type_name",
						sb.toString(), list.toArray(), " order by a.id desc ",
						IosNumDetailVo.class);
		return resultList;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: getNumberSum
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.operation.service.IConfirmationIosNumberService#getNumberSum(cn.adwalker.ad.admin.operation.bean.IosNumDetailbean)
	 */
	@Override
	public AdvNumberSumVo getNumberSum(IosNumDetailbean bean) throws Exception {
		List<AdvNumberSum> list;
		list = this.getAdvNumberSumList(bean);
		int sum_platform_amount = 0;// 统计 总确认数
		int sum_amount = 0;// 总确认数

		if (list != null && list.size() > 0) {
			sum_platform_amount = list.get(0).getSum_platform_amount();
			sum_amount = list.get(0).getSum_amount();

		}
		return new AdvNumberSumVo(sum_amount, sum_platform_amount);
	}

	/**
	 * 
	 * <p>
	 * Title: getAdvNumberSumList
	 * </p>
	 * <p>
	 * Description:求和
	 * </p>
	 * 
	 * @param bean
	 * @return
	 * @author lichuang
	 * @date 2013-8-5
	 * @return List<AdvNumberSum>
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	private List<AdvNumberSum> getAdvNumberSumList(IosNumDetailbean bean) {
		List<AdvNumberSum> listsum = null;
		List<Object> list = new ArrayList<Object>();
		// 默认给值0 mark by cdd
		StringBuilder sb = new StringBuilder(
				"select  IFNULL(sum(a.confirm_num),0) as sum_amount,IFNULL(sum(a.confirm_amount),0) as sum_platform_amount from t_ios_activate_num_detail a LEFT JOIN V_ios_activate p ON a.parent_id=p.id LEFT JOIN V_MEDIA m ON a.media_id = m.id left join t_type e on a.type_id = e.id where 1 = 1 and p.status = 9 and a.status = 9");
		if (bean != null) {
			if (ObjectUtils.isNotEmpty(bean.getStatic_start_date())) {
				sb.append(" and a.static_date >=?");
				list.add(bean.getStatic_start_date());
			}

			if (ObjectUtils.isNotEmpty(bean.getStatic_end_date())) {
				sb.append(" and a.static_date<=?");
				list.add(bean.getStatic_end_date());
			}

			if (!StringUtils.isEmpty(bean.getCampaign_name())) {
				sb.append(" and upper(p.CAMPAIGN_NAME) like '%");
				sb.append(bean.getCampaign_name().trim().toUpperCase());
				sb.append("%'");
			}
			if (!StringUtils.isEmpty(bean.getMedia_name())) {
				sb.append(" and m.name like '%");
				sb.append(bean.getMedia_name());
				sb.append("%'");
			}
			if (bean.getMedia_type() != null) {
				sb.append(" and m.type=? ");
				list.add(bean.getMedia_type());
			}

		}
		try {
			// sum(XXX)有空值，转出来的时候会报错
			listsum = (List<AdvNumberSum>) this.confirmationIosNumberDao
					.findAll(sb.toString(), list.toArray(), AdvNumberSum.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listsum;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: restConfIosNum
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param id
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.operation.service.IConfirmationIosNumberService#restConfIosNum(java.lang.Long)
	 */
	@Override
	public void restConfIosNum(Long id) throws Exception {
		List<FractionNumberVo> list = this.getMediaNum(id);
		// 更新父表状态为-1
		activateIosDao.restConfIosNum(id, null, 0);
		if (list != null && list.size() > 0) {
			confirmationIosNumberDao
					.update("update T_IOS_ACTIVATE_NUM_DETAIL set CONFIRM_NUM=?,CONFIRM_AMOUNT=?,status=? where parent_id=?",
							new Object[] { null, null, 0, id });
		}
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: delConfIosNum
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param id
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.operation.service.IConfirmationIosNumberService#delConfIosNum(java.lang.Long)
	 */
	@Override
	public void delConfIosNum(Long id) throws Exception {
		List<FractionNumberVo> list = this.getMediaNum(id);
		// 更新父表状态为-1
		activateIosDao.updateStatus(id, -3);
		if (list != null && list.size() > 0) {
			confirmationIosNumberDao
					.update("update t_ios_activate_num_detail set status=? where parent_id=?",
							new Object[] { -3, id });
		}

	}
}
