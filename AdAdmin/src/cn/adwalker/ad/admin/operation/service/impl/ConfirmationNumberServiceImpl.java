package cn.adwalker.ad.admin.operation.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.adwalker.ad.admin.operation.bean.ConfirmNumDetailbean;
import cn.adwalker.ad.admin.operation.bean.ConfirmNumberbean;
import cn.adwalker.ad.admin.operation.service.IConfirmationNumberService;
import cn.adwalker.ad.admin.operation.vo.AdvNumberSum;
import cn.adwalker.ad.admin.operation.vo.AdvNumberSumVo;
import cn.adwalker.ad.admin.operation.vo.ConfirmationNumberDetailVo;
import cn.adwalker.ad.admin.operation.vo.ConfirmationNumberVo;
import cn.adwalker.ad.admin.operation.vo.FractionNumberVo;
import cn.adwalker.ad.config.StatusConstant;
import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.util.lang.ObjectUtils;
import cn.adwalker.model.operation.dao.IIosAndroidDayNumDao;
import cn.adwalker.model.operation.dao.IPackageActivateDao;
import cn.adwalker.model.operation.dao.IPackageActivateDetailDao;
import cn.adwalker.model.operation.domain.IosAndroidDayNum;
import cn.adwalker.model.operation.domain.PackageActivateAndroid;

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
@Service("confirmationNumberService")
public class ConfirmationNumberServiceImpl implements
		IConfirmationNumberService {

	@Resource
	private IPackageActivateDetailDao packageActivateDetailDao;

	@Resource
	private IPackageActivateDao packageActivateDao;
	
	@Resource
	private  IIosAndroidDayNumDao iosAndroidDayNumDao;

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
	public List<ConfirmationNumberVo> findList(ConfirmNumberbean bean,
			IPageInfo pageInfo) throws Exception {
		List<ConfirmationNumberVo> resultList = null;
		List<Object> param = new ArrayList<Object>();
		StringBuilder sb = new StringBuilder(
				" T_PACKAGE_ACTIVATE a "
						+ "left join  V_PLACEMENT_PACKAGE t  on a.package_id=t.ID "
						+ "left join T_FINANCE_BALANCE_INCOME_REL rel on rel.detail_id=a.id "
						+ "where 1=1 and a.status!=-3 ");
		if (bean != null) {
			if (StringUtils.isNotEmpty(bean.getStart_time())) {
				sb.append(" and a.static_date>=?");
				param.add(bean.getStart_time());
			}
			if (StringUtils.isNotEmpty(bean.getEnd_time())) {
				sb.append(" and a.static_date<=?");
				param.add(bean.getEnd_time());
			}
			if (!StringUtils.isEmpty(bean.getPackage_id())) {
				sb.append(" and a.PACKAGE_ID like '%");
				sb.append(bean.getPackage_id().trim());
				sb.append("%'");
			}
			if (!StringUtils.isEmpty(bean.getPackage_code())) {
				sb.append(" and t.remarks like ?");
				param.add("%" + bean.getPackage_code().trim() + "%");

			}
			if (!StringUtils.isEmpty(bean.getCampaign())) {
				sb.append(" and (upper(t.CAMPAIGN_NAME) like ? or t.CAMPAIGN_ID=? )");
				param.add("%" + bean.getCampaign_name().trim().toUpperCase()
						+ "%");
				param.add(bean.getCampaign_id());
			}
			if (bean.getStatus() != null) {
				sb.append(" and a.status=? ");
				param.add(bean.getStatus());
			}
			if (!StringUtils.isEmpty(bean.getFile_name())) {
				sb.append(" and t.file_name like ? ");
				param.add("%" + bean.getFile_name().trim() + "%");
			}
			if (bean.getBalance_status() != null) {
				if (bean.getBalance_status() == 1) {
					sb.append(" and  (rel.balance_id is not null) ");
				} else if (bean.getBalance_status() == 0) {
					sb.append(" and  (rel.balance_id is null) ");
				}
			}
		}

		resultList = (List<ConfirmationNumberVo>) packageActivateDetailDao
				.findByPage(
						"a.id,a.create_time,a.package_id,a.static_date,a.sys_activate,a.confirm_amount,a.status,t.CAMPAIGN_NAME,t.CAMPAIGN_ID,t.code,t.file_name,t.remarks,rel.BALANCE_ID",
						sb.toString(), param.toArray(), " order by a.id desc ",
						ConfirmationNumberVo.class, pageInfo);
		if (resultList != null && resultList.size() > 0) {
			StringBuilder stringBuilder = null;
			for (ConfirmationNumberVo vo : resultList) {
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
		List<FractionNumberVo> list = (List<FractionNumberVo>) packageActivateDetailDao
				.findAll(
						"SELECT l.*,c.name media_name,t.name type_name FROM  T_PACKAGE_ACTIVATE_DETAIL l left join V_MEDIA c on l.MEDIA_ID = c.id left join t_type t on t.id=l.type_id where l.parent_id=? ",
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
	public void confirmationNumber(Long id, Integer number) throws Exception {
		List<FractionNumberVo> list = this.getMediaNum(id);
		boolean b = false;
		// 单包投放才进行以下操作
		if (list != null && list.size() == 1) {
			FractionNumberVo vo = list.get(0);
			packageActivateDetailDao.saveConfirm(vo.getId(), number);
			b = true;

		}
		packageActivateDao.confirmationNumber(id, number, b ? 2 : -1);

	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findFractionList
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param package_id
	 * @param create_time
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.operation.service.IConfirmationNumberService#findFractionList(java.lang.String,
	 *      java.util.Date)
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.NEVER)
	@Override
	public List<FractionNumberVo> findFractionList(Long id) throws Exception {
		List<FractionNumberVo> list = null;
		if (id != null) {
			StringBuilder sb = new StringBuilder(
					" T_PACKAGE_ACTIVATE_DETAIL l left join V_MEDIA c on l.MEDIA_ID = c.id,t_sys_config_escore e where 1=1 and l.parent_id=? ");
			list = (List<FractionNumberVo>) packageActivateDetailDao
					.findAll(
							"l.id,l.package_id,l.static_date,l.type_id,l.media_id,l.confirm_num,l.sys_activate,c.name as media_name",
							sb.toString(), new Object[] { id },
							FractionNumberVo.class);
		}
		return list;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: saveConfirm
	 * </p>
	 * 
	 * @param id
	 * @param confirm_num
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.operation.service.IConfirmationNumberService#saveConfirm(java.lang.Long[],
	 *      java.lang.Integer[])
	 */
	@Override
	public void saveConfirm(Long[] id, Integer[] confirm_num, Long fraction_id)
			throws Exception {
		packageActivateDetailDao.saveConfirm(id, confirm_num);
		packageActivateDao.updateStatus(fraction_id,
				StatusConstant.FRACTION_STATUS_F);
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: submitConfirmNumber
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param ids
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.operation.service.IConfirmationNumberService#submitConfirmNumber(java.lang.String)
	 */
	@Override
	public void submitConfirmNumber(String ids) throws Exception {
		// 系统数据入库
		packageActivateDao.submitConfirmNumber(ids);
		packageActivateDao.updateStatu(ids, 9);// 更新相应效果数据状态(已提交)
		//读取提交android数据]
		String arr[]=ids.split(",");
		for (String id : arr) {
		PackageActivateAndroid plist=packageActivateDao.getById(Long.valueOf(id));
		IosAndroidDayNum dU =new IosAndroidDayNum();
			if(plist!=null){
				dU.setCampaign_id(plist.getCampaign_id());
				dU.setCharge_type(plist.getCharge_type());
				dU.setConfirm_amount(plist.getConfirm_amount());
				dU.setCreate_time(new Date());
				dU.setPackage_id(plist.getPackage_id());
				dU.setPrice(plist.getIn_price());
				dU.setStatus(1);
				dU.setStatic_date(plist.getStatic_date());
				iosAndroidDayNumDao.insert(dU);
			}
		}
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findDetailByPList
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param bean
	 * @param pageInfo
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.operation.service.IConfirmationNumberService#findDetailByPList(cn.adwalker.ad.admin.operation.bean.ConfirmNumberbean,
	 *      cn.adwalker.core.page.IPageInfo)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ConfirmationNumberDetailVo> findDetailByPage(
			ConfirmNumDetailbean bean, IPageInfo pageInfo) throws Exception {
		List<ConfirmationNumberDetailVo> resultList = null;
		List<Object> list = new ArrayList<Object>();
		StringBuilder sb = new StringBuilder(
				" T_PACKAGE_ACTIVATE_DETAIL a "
						+ "left join V_PLACEMENT_PACKAGE t on a.package_id=t.ID "
						+ "LEFT  JOIN  V_MEDIA  m ON  a.media_id=m.id  "
						+ "left join   t_type sys_type on a.type_id=sys_type.id "
						+ "left join T_FINANCE_COST_CHANNEL_REL rel on rel.detail_id=a.id ,T_PACKAGE_ACTIVATE p "
						+ "where 1=1 and a.parent_id=p.id and p.status=9 and a.status=1");
		if (bean != null) {
			if (ObjectUtils.isNotEmpty(bean.getStatic_start_date())) {
				sb.append(" and a.static_date >=?");
				list.add(bean.getStatic_start_date());
			}

			if (ObjectUtils.isNotEmpty(bean.getStatic_end_date())) {
				sb.append(" and a.static_date<=?");
				list.add(bean.getStatic_end_date());
			}
			if (!StringUtils.isEmpty(bean.getPackage_id())) {
				sb.append(" and a.PACKAGE_ID like '%");
				sb.append(bean.getPackage_id().trim());
				sb.append("%'");
			}
			if (!StringUtils.isEmpty(bean.getCampaign_name())) {
				sb.append(" and upper(t.CAMPAIGN_NAME) like '%");
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
			if (bean.getBalance_status() != null) {
				if (bean.getBalance_status() == 1) {
					sb.append(" and  (rel.balance_id is not null) ");
				} else if (bean.getBalance_status() == 0) {
					sb.append(" and  (rel.balance_id is null) ");
				}
			}
		}

		resultList = (List<ConfirmationNumberDetailVo>) packageActivateDetailDao
				.findByPage(
						" a.*,t.CAMPAIGN_NAME,t.code,t.file_name,t.remarks,m.name media_name,sys_type.name type_name,rel.balance_id",
						sb.toString(), list.toArray(), " order by a.id desc ",
						ConfirmationNumberDetailVo.class, pageInfo);
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
	public List<ConfirmationNumberDetailVo> findAll(ConfirmNumDetailbean bean)
			throws Exception {
		List<ConfirmationNumberDetailVo> resultList = null;
		List<Object> list = new ArrayList<Object>();
		StringBuilder sb = new StringBuilder(
				" T_PACKAGE_ACTIVATE_DETAIL a left join V_PLACEMENT_PACKAGE t on a.package_id=t.ID LEFT  JOIN  V_MEDIA  m ON  a.media_id=m.id  left join   t_type sys_type on a.type_id=sys_type.id,T_PACKAGE_ACTIVATE p where 1=1 and a.parent_id=p.id and p.status=9 and a.status=1");
		if (bean != null) {
			if (ObjectUtils.isNotEmpty(bean.getStatic_start_date())) {
				sb.append(" and a.static_date >=?");
				list.add(bean.getStatic_start_date());
			}

			if (ObjectUtils.isNotEmpty(bean.getStatic_end_date())) {
				sb.append(" and a.static_date<=?");
				list.add(bean.getStatic_end_date());
			}
			if (!StringUtils.isEmpty(bean.getPackage_id())) {
				sb.append(" and a.PACKAGE_ID like '%");
				sb.append(bean.getPackage_id().trim());
				sb.append("%'");
			}
			if (!StringUtils.isEmpty(bean.getCampaign_name())) {
				sb.append(" and upper(t.CAMPAIGN_NAME) like '%");
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
			if (bean.getBalance_status() != null) {
				sb.append(" and a.invoice_status=? ");
				list.add(bean.getBalance_status());
			}
		}

		resultList = (List<ConfirmationNumberDetailVo>) packageActivateDetailDao
				.findAll(
						" a.*,t.CAMPAIGN_NAME,t.code,t.file_name,t.remarks,m.name media_name,sys_type.name type_name",
						sb.toString(), list.toArray(), " order by a.id desc ",
						ConfirmationNumberDetailVo.class);
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
	 * @see cn.adwalker.ad.admin.operation.service.IConfirmationNumberService#getNumberSum(cn.adwalker.ad.admin.operation.bean.ConfirmNumDetailbean)
	 */
	@Override
	public AdvNumberSumVo getNumberSum(ConfirmNumDetailbean bean)
			throws Exception {
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
	 * Description:TODO
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
	private List<AdvNumberSum> getAdvNumberSumList(ConfirmNumDetailbean bean) {
		List<AdvNumberSum> listsum = null;
		List<Object> list = new ArrayList<Object>();
		// 默认给值0 mark by cdd
		StringBuilder sb = new StringBuilder(
				"select  IFNULL(sum(a.confirm_num),0) as sum_amount,IFNULL(sum(a.confirm_amount),0) as sum_platform_amount from T_PACKAGE_ACTIVATE_DETAIL a left join V_PLACEMENT_PACKAGE t on a.package_id=t.ID LEFT  JOIN  V_MEDIA  m ON  a.media_id=m.id  left join   t_type sys_type on a.type_id=sys_type.id,T_PACKAGE_ACTIVATE p where 1=1 and a.parent_id=p.id and p.status=9 and a.status=1");
		if (bean != null) {
			if (ObjectUtils.isNotEmpty(bean.getStatic_start_date())) {
				sb.append(" and a.static_date >=?");
				list.add(bean.getStatic_start_date());
			}

			if (ObjectUtils.isNotEmpty(bean.getStatic_end_date())) {
				sb.append(" and a.static_date<=?");
				list.add(bean.getStatic_end_date());
			}
			if (!StringUtils.isEmpty(bean.getPackage_id())) {
				sb.append(" and a.PACKAGE_ID like '%");
				sb.append(bean.getPackage_id().trim());
				sb.append("%'");
			}
			if (!StringUtils.isEmpty(bean.getCampaign_name())) {
				sb.append(" and upper(t.CAMPAIGN_NAME) like '%");
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
			if (bean.getBalance_status() != null) {
				sb.append(" and a.invoice_status=? ");
				list.add(bean.getBalance_status());
			}
		}
		try {
			// sum(XXX)有空值，转出来的时候会报错
			listsum = (List<AdvNumberSum>) this.packageActivateDetailDao
					.findAll(sb.toString(), list.toArray(), AdvNumberSum.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listsum;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: restConfirmationNumber
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param id
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.operation.service.IConfirmationNumberService#restConfirmationNumber(java.lang.Long)
	 */
	@Override
	public void restConfirmationNumber(Long id) throws Exception {
		List<FractionNumberVo> list = this.getMediaNum(id);
		// 更新父表状态为-1
		packageActivateDao.confirmationNumber(id, null, 0);
		if (list != null && list.size() > 0) {
			packageActivateDetailDao
					.update("update T_PACKAGE_ACTIVATE_DETAIL set CONFIRM_NUM=?,CONFIRM_AMOUNT=?,status=? where parent_id=?",
							new Object[] { null, null, 0, id });
		}
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: delConfirmationNumber
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param id
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.operation.service.IConfirmationNumberService#delConfirmationNumber(java.lang.Long)
	 */
	@Override
	public void delConfirmationNumber(Long id) throws Exception {
		List<FractionNumberVo> list = this.getMediaNum(id);
		// 更新父表状态为-1
		packageActivateDao.updateStatus(id, -3);
		if (list != null && list.size() > 0) {
			packageActivateDetailDao
					.update("update T_PACKAGE_ACTIVATE_DETAIL set status=? where parent_id=?",
							new Object[] { -3, id });
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> findAll(ConfirmNumberbean bean) throws Exception {
		List<Object> resultList = null;
		List<Object> param = new ArrayList<Object>();
		StringBuilder sb = new StringBuilder(
				" T_PACKAGE_ACTIVATE a "
						+ "left join  V_PLACEMENT_PACKAGE t  on a.package_id=t.ID "
						+ "left join T_FINANCE_BALANCE_INCOME_REL rel on rel.detail_id=a.id "
						+ "where 1=1 and a.status!=-3 ");
		if (bean != null) {
			if (StringUtils.isNotEmpty(bean.getStart_time())) {
				sb.append(" and a.static_date>=?");
				param.add(bean.getStart_time());
			}
			if (StringUtils.isNotEmpty(bean.getEnd_time())) {
				sb.append(" and a.static_date<=?");
				param.add(bean.getEnd_time());
			}
			if (!StringUtils.isEmpty(bean.getPackage_id())) {
				sb.append(" and a.PACKAGE_ID like '%");
				sb.append(bean.getPackage_id().trim());
				sb.append("%'");
			}
			if (!StringUtils.isEmpty(bean.getPackage_code())) {
				sb.append(" and t.remarks like ?");
				param.add("%" + bean.getPackage_code().trim() + "%");

			}
			if (!StringUtils.isEmpty(bean.getCampaign())) {
				sb.append(" and (upper(t.CAMPAIGN_NAME) like ? or t.CAMPAIGN_ID=? )");
				param.add("%" + bean.getCampaign_name().trim().toUpperCase()
						+ "%");
				param.add(bean.getCampaign_id());
			}
			if (bean.getStatus() != null) {
				sb.append(" and a.status=? ");
				param.add(bean.getStatus());
			}
			if (!StringUtils.isEmpty(bean.getFile_name())) {
				sb.append(" and t.file_name like ? ");
				param.add("%" + bean.getFile_name().trim() + "%");
			}
			if (bean.getBalance_status() != null) {
				if (bean.getBalance_status() == 1) {
					sb.append(" and  (rel.balance_id is not null) ");
				} else if (bean.getBalance_status() == 0) {
					sb.append(" and  (rel.balance_id is null) ");
				}
			}
		}

		resultList = (List<Object>) packageActivateDetailDao
				.findAll(
						"a.id,a.create_time,a.package_id,a.static_date,a.sys_activate,a.confirm_amount,a.status,t.campaign_name,t.campaign_id,t.code,t.file_name,t.remarks,rel.balance_id",
						sb.toString(), param.toArray(), " order by a.id desc ",
						ConfirmationNumberVo.class);
		return resultList;
	}
}
