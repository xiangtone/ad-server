package cn.adwalker.ad.admin.operation.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.adwalker.ad.admin.operation.bean.AchievementReportbean;
import cn.adwalker.ad.admin.operation.form.AchievementPublishForm;
import cn.adwalker.ad.admin.operation.service.IOperationAchievementReportService;
import cn.adwalker.ad.admin.operation.vo.AchievementReportSum;
import cn.adwalker.ad.admin.operation.vo.AchievementReportSumVo;
import cn.adwalker.ad.admin.operation.vo.AchievementReportVo;
import cn.adwalker.ad.admin.operation.vo.ChannelDetailVo;
import cn.adwalker.ad.admin.operation.vo.ChannelPlatformSumVo;
import cn.adwalker.ad.admin.operation.vo.PlatformDetailVo;
import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.util.lang.ObjectUtils;
import cn.adwalker.model.ad.dao.IAreaDao;
import cn.adwalker.model.ad.domain.Area;
import cn.adwalker.model.operation.dao.ICampaignConfirmDao;
import cn.adwalker.model.operation.domain.ChannelPlatformSum;

/**
 * <p>
 * Title: OperationAchievementReportServiceImpl
 * </p>
 * <p>
 * Description:业绩报表service实现类
 * </p>
 * <p>
 * Company: emar
 * </p>
 * 
 * @author lichuang
 * @date 2013-10-24
 */
@Service("operationAchievementReportService")
public class OperationAchievementReportServiceImpl implements
		IOperationAchievementReportService {

	@Resource
	private ICampaignConfirmDao campaignConfirmDao;
	
	@Resource
	private IAreaDao areaDao;
	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findCampaignList
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param bean
	 * @param pageInfo
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.operation.service.emar.escore.platform.operation.service.IOperationCampaignConfirmService#findCampaignList(com.emar.escore.platform.operation.bean.CampaignConfirmbean,
	 *      com.emar.escore.core.page.IPageInfo)
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.NEVER)
	@Override
	public List<AchievementReportVo> findByPage(AchievementReportbean bean,
			IPageInfo pageInfo) throws Exception {
		List<Object> params = new ArrayList<Object>();
		StringBuilder sb = new StringBuilder(
				" T_CAMPAIGN_CONFIRM b left join V_CAMPAIGN t on b.campaign_id = t.ID left join t_campaign_salesman s "
						+ "on t.salesman_id = s.id left join t_sys_user u on b.Manager_id = u.id  where 1=1 and b.status in(2,3,8,9)");
		if (bean != null) {
			if (ObjectUtils.isNotEmpty(bean.getMonth_stat_date())) {
				sb.append(" and b.month_stat_date>= '");
				sb.append(bean.getMonth_stat_date());
				sb.append("'");
			}
			if (ObjectUtils.isNotEmpty(bean.getMonth_end_date())) {
				sb.append(" and b.month_end_date<= '");
				sb.append(bean.getMonth_end_date());
				sb.append("'");
			}
			if (ObjectUtils.isNotEmpty(bean.getStatus())) {
				sb.append(" and b.STATUS =");
				sb.append(bean.getStatus());
			}
			if (ObjectUtils.isNotEmpty(bean.getAdv())) {
				sb.append(" and (upper(t.company_name)  like ? or t.adv_id =? )");
				params.add("%" + bean.getCompany_name().toUpperCase() + "%");
				params.add(bean.getAdv_id());
			}
			if (ObjectUtils.isNotEmpty(bean.getCampaign_name())) {
				sb.append(" and (upper(t.CAMPAIGN_NAME)  like ? or b.campaign_id =? )");
				params.add("%" + bean.getCampaign_name().toUpperCase() + "%");
				params.add(bean.getCampaign_id());
			}
			if (!StringUtils.isEmpty(bean.getMonth())) {
				sb.append(" and b.month =?");
				params.add(bean.getMonth());
			}

		}
		return (List<AchievementReportVo>) campaignConfirmDao
				.findByPage(
						"b.*,t.campaign_name,t.charge_type, t.os,t.adv_id, t.company_name,u.real_name as create_user_name, s.name,s.area_type",
						sb.toString(), params.toArray(),
						" order by b.create_time desc ",
						AchievementReportVo.class, pageInfo);
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
	 * @see cn.adwalker.ad.admin.operation.service.emar.escore.platform.operation.service.IOperationCampaignConfirmService#findSum(com.emar.escore.platform.operation.bean.CampaignConfirmbean)
	 */
	@Override
	public AchievementReportSumVo findSum(AchievementReportbean bean)
			throws Exception {
		List<AchievementReportSum> list;
		list = this.getSumList(bean);
		Double sum_income_money = 0d;// 确认 总金额
		Double sum_income_plm_money = 0d;// 平台 统计
		Double sum_income_cha_money = 0d;// 渠道收入 统计
		Double sum_cost_money = 0d;// 总成本 统计
		Double sum_cos_plm_money = 0d;// 平台成本 统计
		Double sum_cost_cha_money = 0d;// 渠道成本 统计
		Double sum_gross_profit = 0d;// 总毛利率 统计
		Double sum_plm_gross_profit = 0d;// 平台毛利率 统计
		Double sum_cha_gross_profit = 0d;// 渠道毛利率 统计
		Integer sum_income_amount = 0;// 统计 总确认数
		Integer sum_forecast_amount = 0;// 预确认数

		if (list != null && list.size() > 0) {
			sum_income_money = list.get(0).getSum_income_money();
			sum_income_plm_money = list.get(0).getSum_income_plm_money();
			sum_income_cha_money = list.get(0).getSum_income_cha_money();
			sum_cost_money = list.get(0).getSum_cost_money();
			sum_cos_plm_money = list.get(0).getSum_cost_plm_money();
			sum_cost_cha_money = list.get(0).getSum_cost_cha_money();
			sum_gross_profit = list.get(0).getSum_gross_profit();
			sum_plm_gross_profit = list.get(0).getSum_plm_gross_profit();
			sum_cha_gross_profit = list.get(0).getSum_cha_gross_profit();
			sum_income_amount = list.get(0).getSum_income_amount();
			sum_forecast_amount = list.get(0).getSum_forecast_amount();
		}
		return new AchievementReportSumVo(sum_income_money,
				sum_income_plm_money, sum_income_cha_money, sum_cost_money,
				sum_cos_plm_money, sum_cost_cha_money, sum_gross_profit,
				sum_plm_gross_profit, sum_cha_gross_profit, sum_income_amount,
				sum_forecast_amount);
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
	 * @date 2013-10-24
	 * @return List<CampaignConfirmSum>
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	private List<AchievementReportSum> getSumList(AchievementReportbean bean) {
		List<AchievementReportSum> list = null;
		List<Object> params = new ArrayList<Object>();
		StringBuilder sb = new StringBuilder(
				"select  sum(b.forecast_amount) as sum_forecast_amount, sum(b.income_amount) as sum_income_amount, sum(b.income_money) as sum_income_money,"
						+ "sum(b.platform_income_money) as sum_income_plm_money,sum(b.channel_income_money) as sum_income_cha_money,sum(b.channel_cost_money) as sum_cost_cha_money,sum(b.platform_cost_money) as sum_cost_plm_money "
						+ " from T_CAMPAIGN_CONFIRM b left join V_CAMPAIGN t on b.campaign_id = t.ID left join t_campaign_salesman s "
						+ "on t.salesman_id = s.id left join t_sys_user u on b.Manager_id = u.id where  1 = 1 and b.status in(2,3,8,9)");
		if (bean != null) {
			if (ObjectUtils.isNotEmpty(bean.getMonth_stat_date())) {
				sb.append(" and b.month_stat_date>= '");
				sb.append(bean.getMonth_stat_date());
				sb.append("'");
			}
			if (ObjectUtils.isNotEmpty(bean.getMonth_end_date())) {
				sb.append(" and b.month_end_date<= '");
				sb.append(bean.getMonth_end_date());
				sb.append("'");
			}
			if (ObjectUtils.isNotEmpty(bean.getStatus())) {
				sb.append(" and b.STATUS =");
				sb.append(bean.getStatus());
			}
			if (ObjectUtils.isNotEmpty(bean.getAdv())) {
				sb.append(" and (upper(t.company_name)  like ? or t.adv_id =? )");
				params.add("%" + bean.getCompany_name().toUpperCase() + "%");
				params.add(bean.getAdv_id());
			}
			if (ObjectUtils.isNotEmpty(bean.getCampaign_name())) {
				sb.append(" and (upper(t.CAMPAIGN_NAME)  like ? or b.campaign_id =? )");
				params.add("%" + bean.getCampaign_name().toUpperCase() + "%");
				params.add(bean.getCampaign_id());
			}
			if (!StringUtils.isEmpty(bean.getMonth())) {
				sb.append(" and b.month =?");
				params.add(bean.getMonth());
			}

		}
		try {
			list = (List<AchievementReportSum>) this.campaignConfirmDao
					.findAll(sb.toString(), params.toArray(),
							AchievementReportSum.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
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
	 * @see cn.adwalker.ad.admin.operation.service.emar.escore.platform.operation.service.IOperationAchievementReportService#findAll(cn.cn.adwalker.ad.admin.operation.bean.emar.escore.platform.operation.bean.AchievementReportbean)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<AchievementReportVo> findAll(AchievementReportbean bean)
			throws Exception {

		List<Object> list = new ArrayList<Object>();
		StringBuilder sb = new StringBuilder(
				" T_CAMPAIGN_CONFIRM b left join V_CAMPAIGN t on b.campaign_id = t.ID left join T_ADV_SALESMAN s "
						+ "on t.adv_id = s.adv_id left join t_sys_user u on b.Manager_id = u.id  where 1=1 and b.status in(2,3,8,9)");
		if (bean != null) {
			if (ObjectUtils.isNotEmpty(bean.getMonth_stat_date())) {
				sb.append(" and b.month_stat_date>= '");
				sb.append(bean.getMonth_stat_date());
				sb.append("'");
			}
			if (ObjectUtils.isNotEmpty(bean.getMonth_end_date())) {
				sb.append(" and b.month_end_date<= '");
				sb.append(bean.getMonth_end_date());
				sb.append("'");
			}
			if (ObjectUtils.isNotEmpty(bean.getStatus())) {
				sb.append(" and b.STATUS =");
				sb.append(bean.getStatus());
			}
			if (ObjectUtils.isNotEmpty(bean.getAdv())) {
				sb.append(" and (upper(t.company_name)  like ? or t.adv_id =? )");
				list.add("%" + bean.getCompany_name().toUpperCase() + "%");
				list.add(bean.getAdv_id());
			}
			if (ObjectUtils.isNotEmpty(bean.getCampaign_name())) {
				sb.append(" and (upper(t.CAMPAIGN_NAME)  like ? or b.campaign_id =? )");
				list.add("%" + bean.getCampaign_name().toUpperCase() + "%");
				list.add(bean.getCampaign_id());
			}

		}
		return (List<AchievementReportVo>) campaignConfirmDao
				.findAll(
						"b.*,t.campaign_name,t.charge_type, t.os,t.adv_id, t.company_name,u.real_name as create_user_name, s.name,s.area_type",
						sb.toString(), list.toArray(),
						" order by b.create_time desc ",
						AchievementReportVo.class);
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: updateStatus
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param id
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.operation.service.emar.escore.platform.operation.service.IOperationAchievementReportService#updateStatus(java.lang.Long)
	 */
	@Override
	public void publish(AchievementPublishForm form) throws Exception {
		campaignConfirmDao
				.update("update T_CAMPAIGN_CONFIRM set STATUS=3,month=?,note=?,publish_time=now() where ID=?",
						new Object[] { form.getMonth(), form.getNote(),
								form.getId() });
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: getChaInfo
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param id
	 * @param pageInfo
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.operation.service.emar.escore.platform.operation.service.IOperationAchievementReportService#getChaInfo(java.lang.Long,
	 *      com.emar.escore.core.page.IPageInfo)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ChannelDetailVo> getChaInfo(Long id, IPageInfo pageInfo)
			throws Exception {
		List<Object> list = new ArrayList<Object>();
		StringBuilder sb = new StringBuilder(
				" T_FINANCE_COST_CHANNEL b left join V_CAMPAIGN t on b.CAMPAIGN_ID = t.ID left join t_channel p on b.channel_id=p.id where 1=1 and b.status=1");
		if (ObjectUtils.isNotEmpty(id)) {
			sb.append(" and b.balance_id =");
			sb.append(id);
		}
		return (List<ChannelDetailVo>) campaignConfirmDao
				.findByPage(
						"b.cost as income_money,b.amount as income_amount,b.type_id, t.company_name,t.id as campaign_id,t.CAMPAIGN_NAME, "
								+ "t.charge_type,t.os,t.adv_id,p.id as channel_id,p.name as channel_name",
						sb.toString(), list.toArray(), " order by t.id desc ",
						ChannelDetailVo.class, pageInfo);
	}

	@Override
	public ChannelPlatformSumVo findChaSum(Long id) throws Exception {

		List<ChannelPlatformSum> list;
		list = this.getChaSumList(id);
		Double sum_pha_money = 0d;// 确认 总金额
		Double sum_cha_money = 0d;// 确认 总金额
		if (list != null && list.size() > 0) {
			sum_cha_money = list.get(0).getSum_cha_money();
		}
		return new ChannelPlatformSumVo(sum_pha_money, sum_cha_money);
	}

	/**
	 * <p>
	 * Title: getChaPlaSumList
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param id
	 * @return
	 * @author lichuang
	 * @date 2013-10-26
	 * @return List<ChannelPlatformSum>
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	private List<ChannelPlatformSum> getChaSumList(Long id) {
		List<ChannelPlatformSum> list = null;
		StringBuilder sb = new StringBuilder(
				"select sum(b.cost) as sum_cha_money FROM  T_FINANCE_COST_CHANNEL b left join V_CAMPAIGN t on b.CAMPAIGN_ID = t.ID left join t_channel p on b.channel_id=p.id where 1=1 and b.status=1");
		if (ObjectUtils.isNotEmpty(id)) {
			sb.append(" and b.balance_id =");
			sb.append(id);
		}
		try {
			list = (List<ChannelPlatformSum>) this.campaignConfirmDao.findAll(
					sb.toString(), null, ChannelPlatformSum.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PlatformDetailVo> getPlaInfo(AchievementReportbean bean,
			IPageInfo pageInfo) throws Exception {
		List<Object> list = new ArrayList<Object>();
		StringBuilder sb = new StringBuilder(
				"T_FINANCE_COST_PLATFORM b left join V_CAMPAIGN t on b.CAMPAIGN_ID = t.ID left join t_application p on b.app_id=p.id where 1=1 and b.status=1");
		if (bean != null) {

			if (ObjectUtils.isNotEmpty(bean.getConfirm_id())) {
				sb.append(" and b.balance_id =");
				sb.append(bean.getConfirm_id());
			}
		}

		return (List<PlatformDetailVo>) campaignConfirmDao
				.findByPage(
						"b.cost as income_money, b.amount as income_amount, t.company_name, t.id as campaign_id, t.CAMPAIGN_NAME,t.charge_type,"
								+ "t.os,t.adv_id, p.id as APP_ID, p.dev_id,p.name as app_name ",
						sb.toString(), list.toArray(),
						" order by b.create_time desc ",
						PlatformDetailVo.class, pageInfo);
	}

	@Override
	public ChannelPlatformSumVo findPlaSum(AchievementReportbean bean)
			throws Exception {
		List<ChannelPlatformSum> list;
		list = this.getPlaSumList(bean);
		Double sum_pha_money = 0d;// 确认 总金额
		Double sum_cha_money = 0d;// 确认 总金额
		if (list != null && list.size() > 0) {
			sum_pha_money = list.get(0).getSum_pha_money();
		}
		return new ChannelPlatformSumVo(sum_pha_money, sum_cha_money);
	}

	@SuppressWarnings("unchecked")
	private List<ChannelPlatformSum> getPlaSumList(AchievementReportbean bean) {
		List<ChannelPlatformSum> list = null;
		StringBuilder sb = new StringBuilder(
				"select sum(b.cost) as sum_pha_money FROM  T_FINANCE_COST_PLATFORM b left join V_CAMPAIGN t on b.CAMPAIGN_ID = t.ID left join t_application p on b.app_id=p.id where 1=1 and b.status=1");
		if (bean != null) {

			if (ObjectUtils.isNotEmpty(bean.getConfirm_id())) {
				sb.append(" and b.balance_id =");
				sb.append(bean.getConfirm_id());
			}
		}
		try {
			list = (List<ChannelPlatformSum>) this.campaignConfirmDao.findAll(
					sb.toString(), null, ChannelPlatformSum.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: updatereduceAchievement
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param id
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.operation.service.emar.escore.platform.operation.service.IOperationAchievementReportService#updatereduceAchievement(java.lang.Long)
	 */
	@Override
	public void updatereduceAchievement(Long id) throws Exception {
		campaignConfirmDao.updatereduceAchievement(id);
	}
	/**
	 * (non-Javadoc)
	* <p>Title: findByArea</p>
	* <p>Description:TODO</p>
	* @return
	* @throws Exception
	* @see cn.adwalker.ad.admin.operation.service.emar.escore.platform.operation.service.IOperationAchievementReportService#findByArea()
	 */
	@Override
	public List<Area> findByArea() throws Exception {
		return this.areaDao.findByArea();
	}
}
