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
import cn.adwalker.ad.admin.operation.bean.ConfirmNumChannelbean;
import cn.adwalker.ad.admin.operation.bean.ConfirmNumCpdbean;
import cn.adwalker.ad.admin.operation.bean.ConfirmNumDetailbean;
import cn.adwalker.ad.admin.operation.service.IConfirmNumberChannelService;
import cn.adwalker.ad.admin.operation.vo.ConfirmCpdSum;
import cn.adwalker.ad.admin.operation.vo.ConfirmCpdSumVo;
import cn.adwalker.ad.admin.operation.vo.ConfirmNumberChannelVo;
import cn.adwalker.ad.admin.operation.vo.ConfirmNumberCpdVo;
import cn.adwalker.ad.admin.operation.vo.ConfirmationNumberDetailVo;
import cn.adwalker.ad.admin.operation.vo.FractionNumberVo;
import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.util.lang.ObjectUtils;
import cn.adwalker.model.channel.dao.IMediaScaleDao;
import cn.adwalker.model.operation.dao.IIosAndroidDayChannelNumDao;
import cn.adwalker.model.operation.dao.IPackageActivateDao;
import cn.adwalker.model.operation.dao.IPackageActivateDetailDao;
import cn.adwalker.model.operation.domain.IosAndroidDayChannelNum;
import cn.adwalker.model.operation.domain.PackageActivateDetail;

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
@Service
public class ConfirmNumberChannelServiceImpl implements
		IConfirmNumberChannelService {

	@Resource
	private IPackageActivateDetailDao confirmationNumberDao;

	@Resource
	private IPackageActivateDao packageActivateDao;

	@Resource
	private IMediaScaleDao mediaScaleDao;
	

	@Resource
	private IIosAndroidDayChannelNumDao iosAndroidDayChannelNumDao;



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
		List<FractionNumberVo> list = (List<FractionNumberVo>) confirmationNumberDao
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
		if (list != null && list.size() >= 1) {
			FractionNumberVo vo = list.get(0);
			confirmationNumberDao.saveConfirm(vo.getId(), number);
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
					" T_PACKAGE_ACTIVATE_DETAIL l left join V_MEDIA c on l.MEDIA_ID = c.id where 1=1 and l.parent_id=? ");
			list = (List<FractionNumberVo>) confirmationNumberDao
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
		packageActivateDao.updateStatu(ids, 1);// 更新相应效果数据状态(已提交)
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
	 * @see cn.adwalker.ad.admin.operation.service.IConfirmationNumberService#findDetailByPList(cn.adwalker.admin.operation.bean.ConfirmNumberbean,
	 *      cn.adwalker.core.page.IPageInfo)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ConfirmNumberChannelVo> findDetailByPage(
			ConfirmNumChannelbean bean, IPageInfo pageInfo) throws Exception {
		List<ConfirmNumberChannelVo> resultList = null;
		List<Object> list = new ArrayList<Object>();
		StringBuilder sb = new StringBuilder(
				" T_PACKAGE_ACTIVATE_DETAIL a  left join V_PLACEMENT_PACKAGE t on a.package_id=t.ID LEFT  JOIN  V_MEDIA  m ON  a.media_id=m.id  left join   t_type sys_type on a.type_id=sys_type.id,T_PACKAGE_ACTIVATE p where 1=1 and a.parent_id=p.id and p.status=9 and a.status!=-3 and a.media_type=1  and a.blance_mode!='CPD'");
		if (bean != null) {
			if (ObjectUtils.isNotEmpty(bean.getCreate_time())) {
				sb.append(" and a.static_date=?");
				list.add(bean.getCreate_time());
			}
			if (!StringUtils.isEmpty(bean.getPackage_id())) {
				sb.append(" and a.PACKAGE_ID like '%");
				sb.append(bean.getPackage_id().trim());
				sb.append("%'");
			}

			if (!StringUtils.isEmpty(bean.getPackage_code())) {
				sb.append(" and t.remarks like ? ");
				list.add("%" + bean.getPackage_code().trim() + "%");
			}

			if (!StringUtils.isEmpty(bean.getCampaign_name())) {
				sb.append(" and upper(t.CAMPAIGN_NAME) like '%");
				sb.append(bean.getCampaign_name().trim().toUpperCase());
				sb.append("%'");
			}
			if (!StringUtils.isEmpty(bean.getMedia_name())) {
				sb.append(" and upper(m.name) like '%");
				sb.append(bean.getMedia_name().trim().toUpperCase());
				sb.append("%'");
			}
			if (bean.getPage_status() != null) {
				sb.append(" and a.status = '");
				sb.append(bean.getPage_status());
				sb.append("'");
			}
		}

		resultList = (List<ConfirmNumberChannelVo>) confirmationNumberDao
				.findByPage(
						" a.*,t.CAMPAIGN_NAME,t.code,t.CAMPAIGN_id,t.file_name,t.remarks,m.name media_name,sys_type.name type_name",
						sb.toString(), list.toArray(), " order by a.id desc ",
						ConfirmNumberChannelVo.class, pageInfo);
		if (resultList != null && resultList.size() > 0) {
			for (ConfirmNumberChannelVo vo : resultList) {
				if (vo.getStatus() != 1) {
					vo.setScale(mediaScaleDao.getMediaScale(vo.getMedia_id(),
							vo.getCampaign_id()));
				}
			}

		}
		return resultList;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: confirmationAmount
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param id
	 * @param number
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.operation.service.IConfirmationNumberService#confirmationAmount(java.lang.Long,
	 *      java.lang.Integer)
	 */
	@Override
	public void confirmationAmount(Long id, Integer amount, Integer media_type,Long campaign_id,SysUserVo managerUser)
			throws Exception {
		IosAndroidDayChannelNum iadcm=new IosAndroidDayChannelNum();
		if (media_type != null) {
			if (media_type == 1) {
				Double out_price = confirmationNumberDao.getPrice(id);
				if (out_price != null) {
					confirmationNumberDao.confirmationAmount(id, amount,
							out_price);
				}
				PackageActivateDetail pAd=confirmationNumberDao.get(id, PackageActivateDetail.class);
				if(pAd!=null){
					iadcm.setCampaign_id(campaign_id);
					iadcm.setChannel_id(pAd.getMedia_id());
					iadcm.setCharge_type(pAd.getBlance_mode());
					iadcm.setParent_id(pAd.getParent_id());
					iadcm.setPrice(pAd.getOut_price());
					iadcm.setCreate_time(new Date());
					iadcm.setStatic_date(pAd.getStatic_date());
					iadcm.setStatus(1);
					iadcm.setOperater_id(managerUser.getId());
					iadcm.setConfirm_amount(Integer.valueOf(pAd.getConfirm_amount()));
					iosAndroidDayChannelNumDao.insert(iadcm);
				}
			} else {
				confirmationNumberDao.confirmationAmount(id, amount);

			}
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
	 * @see cn.adwalker.ad.admin.operation.service.IConfirmationNumberService#findAll(cn.adwalker.admin.finance.bean.FinanceConsumebean)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ConfirmationNumberDetailVo> findAll(ConfirmNumDetailbean bean)
			throws Exception {
		List<ConfirmationNumberDetailVo> resultList = null;
		List<Object> list = new ArrayList<Object>();
		StringBuilder sb = new StringBuilder(
				" T_PACKAGE_ACTIVATE_DETAIL a  left join V_PLACEMENT_PACKAGE t on a.package_id=t.ID LEFT  JOIN  V_MEDIA  m ON  a.media_id=m.id  left join   t_type sys_type on a.type_id=sys_type.id,T_PACKAGE_ACTIVATE p where 1=1 and a.parent_id=p.id and p.status=1 ");
		if (bean != null) {
			if (ObjectUtils.isNotEmpty(bean.getCreate_time())) {
				sb.append(" and a.static_date=?");
				list.add(bean.getCreate_time());
			}
			if (!StringUtils.isEmpty(bean.getPackage_id())) {
				sb.append(" and a.PACKAGE_ID like '%");
				sb.append(bean.getPackage_id().trim());
				sb.append("%'");
			}
			if (!StringUtils.isEmpty(bean.getCampaign_name())) {
				sb.append(" and upper(t.CAMPAIGN_NAME) like '%");
				sb.append(bean.getCampaign_name().toUpperCase());
				sb.append("%'");
			}
			if (!StringUtils.isEmpty(bean.getMedia_name())) {
				sb.append(" and upper(m.name) like '%");
				sb.append(bean.getMedia_name().trim().toUpperCase());
				sb.append("%'");
			}
			if (bean.getMedia_type() != null) {
				sb.append(" and m.type=? ");
				list.add(bean.getMedia_type());
			}
		}

		resultList = (List<ConfirmationNumberDetailVo>) confirmationNumberDao
				.findAll(
						" a.*,t.CAMPAIGN_NAME,t.code,t.file_name,t.remarks,m.name media_name,sys_type.name type_name,l.scale,m.type media_type",
						sb.toString(), list.toArray(),
						" order by a.create_time desc ",
						ConfirmationNumberDetailVo.class);
		return resultList;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findByCpdList
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param bean
	 * @param pageInfo
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.operation.service.IConfirmNumberChannelService#findByCpdList(cn.adwalker.ad.admin.operation.bean.ConfirmNumCpdbean,
	 *      cn.adwalker.core.page.IPageInfo)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ConfirmNumberCpdVo> findByCpdList(ConfirmNumCpdbean bean,
			IPageInfo pageInfo) throws Exception {
		List<ConfirmNumberCpdVo> resultList = null;
		List<Object> list = new ArrayList<Object>();
		StringBuilder sb = new StringBuilder(
				" T_PACKAGE_ACTIVATE_DETAIL a left join V_PLACEMENT_PACKAGE t on a.package_id = t.ID LEFT JOIN V_MEDIA m ON a.media_id = m.id left join t_type sys_type"
						+ " on a.type_id = sys_type.id, T_PACKAGE_ACTIVATE p where 1 = 1 and a.parent_id = p.id and p.status = 1 and a.status != -3 and a.media_type = 1 and a.blance_mode='CPD'");
		if (bean != null) {
			if (ObjectUtils.isNotEmpty(bean.getCreate_time_sart())) {
				sb.append(" and a.static_date>=?");
				list.add(bean.getCreate_time_sart());
			}
			if (ObjectUtils.isNotEmpty(bean.getCreate_time_end())) {
				sb.append(" and a.static_date<=?");
				list.add(bean.getCreate_time_end());
			}
			if (ObjectUtils.isNotEmpty(bean.getMedia_id())) {
				sb.append(" and a.media_id=?");
				list.add(bean.getMedia_id());
			}
			if (ObjectUtils.isNotEmpty(bean.getCampaign_id())) {
				sb.append(" and t.CAMPAIGN_ID=?");
				list.add(bean.getCampaign_id());
			}

			if (!StringUtils.isEmpty(bean.getCampaign_name())) {
				sb.append(" and upper(t.CAMPAIGN_NAME) like '%");
				sb.append(bean.getCampaign_name().trim().toUpperCase());
				sb.append("%'");
			}
			if (!StringUtils.isEmpty(bean.getMedia_name())) {
				sb.append(" and upper(m.name) like '%");
				sb.append(bean.getMedia_name().trim().toUpperCase());
				sb.append("%'");
			}

			if (bean.getStatus() != null) {
				sb.append(" and a.status=? ");
				list.add(bean.getStatus());
			}
		}

		resultList = (List<ConfirmNumberCpdVo>) confirmationNumberDao
				.findByPage(
						"a.id, a.static_date,a.sys_cost as confirm_money,a.status,a.media_id,a.confirm_num,a.in_price as price,t.CAMPAIGN_NAME,t.CAMPAIGN_ID,m.name as media_name,a.confirm_amount as channel_effect",
						sb.toString(), list.toArray(), " order by a.id desc ",
						ConfirmNumberCpdVo.class, pageInfo);
		return resultList;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: confirmationCpd
	 * </p>
	 * <p>
	 * Description:更新cpd渠道成本
	 * </p>
	 * 
	 * @param id
	 * @param amount
	 * @param media
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.operation.service.IConfirmNumberChannelService#confirmationCpd(java.lang.Long,
	 *      java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public void confirmationCpd(Long id) throws Exception {
		confirmationNumberDao.submitData(id, 1);
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findSum
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.operation.service.IConfirmNumberChannelService#findSum(cn.adwalker.ad.admin.operation.bean.ConfirmNumCpdbean)
	 */
	@Override
	public ConfirmCpdSumVo findSum(ConfirmNumCpdbean bean) throws Exception {
		List<ConfirmCpdSum> list;
		list = this.getSumList(bean);
		Double sum_forecast_money = 0d;// 预确认 总金额
		Double sum_income_money = 0d;// 确认 总金额
		Integer sum_channel_num = 0;// 确认 渠道数
		if (list != null && list.size() > 0) {
			sum_forecast_money = list.get(0).getSum_forecast_money();
			sum_income_money = list.get(0).getSum_income_money();
			sum_channel_num = list.get(0).getSum_channel_num();
		}
		return new ConfirmCpdSumVo(sum_income_money, sum_forecast_money,sum_channel_num);
	}

	/**
	 * <p>
	 * Title: getSumList
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param bean
	 * @return
	 * @author lichuang
	 * @date 2013-10-12
	 * @return List<ConfirmCpdSum>
	 * @version 1.0
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private List<ConfirmCpdSum> getSumList(ConfirmNumCpdbean bean)
			throws Exception {
		List<ConfirmCpdSum> list = null;
		List<Object> param = new ArrayList<Object>();
		StringBuilder sb = new StringBuilder(
				"select sum(a.confirm_num*a.in_price) as sum_forecast_money,sum(a.sys_cost)as sum_income_money,sum(a.confirm_amount) as sum_channel_num from T_PACKAGE_ACTIVATE_DETAIL a left join V_PLACEMENT_PACKAGE t on a.package_id = t.ID LEFT JOIN V_MEDIA m ON a.media_id = m.id left join t_type sys_type"
						+ " on a.type_id = sys_type.id, T_PACKAGE_ACTIVATE p where 1 = 1 and a.parent_id = p.id and p.status = 1 and a.status != -3 and a.media_type = 1 and a.blance_mode='CPD'");
		if (bean != null) {
			if (ObjectUtils.isNotEmpty(bean.getCreate_time_sart())) {
				sb.append(" and a.static_date>=?");
				param.add(bean.getCreate_time_sart());
			}
			if (ObjectUtils.isNotEmpty(bean.getCreate_time_end())) {
				sb.append(" and a.static_date<=?");
				param.add(bean.getCreate_time_end());
			}
			if (ObjectUtils.isNotEmpty(bean.getMedia_id())) {
				sb.append(" and a.media_id=?");
				param.add(bean.getMedia_id());
			}
			if (ObjectUtils.isNotEmpty(bean.getCampaign_id())) {
				sb.append(" and t.CAMPAIGN_ID=?");
				param.add(bean.getCampaign_id());
			}

			if (!StringUtils.isEmpty(bean.getCampaign_name())) {
				sb.append(" and upper(t.CAMPAIGN_NAME) like '%");
				sb.append(bean.getCampaign_name().trim().toUpperCase());
				sb.append("%'");
			}
			if (!StringUtils.isEmpty(bean.getMedia_name())) {
				sb.append(" and upper(m.name) like '%");
				sb.append(bean.getMedia_name().trim().toUpperCase());
				sb.append("%'");
			}

			if (bean.getStatus() != null) {
				sb.append(" and a.status=? ");
				param.add(bean.getStatus());

			}
		}
		list = (List<ConfirmCpdSum>) this.confirmationNumberDao.findAll(
				sb.toString(), param.toArray(), ConfirmCpdSum.class);

		return list;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: updateCpdData
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param id
	 * @param amount
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.operation.service.IConfirmNumberChannelService#updateCpdData(java.lang.Long,
	 *      java.lang.Double)
	 */
	@Override
	public void updateCpdData(Long id, Double amount,Integer num) throws Exception {
		confirmationNumberDao.updateChannelCost(id, amount,num);
	}
}
