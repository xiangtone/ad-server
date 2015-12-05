package cn.adwalker.ad.admin.finance.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.adwalker.ad.admin.common.vo.SysUserVo;
import cn.adwalker.ad.admin.finance.bean.AchievementReportbean;
import cn.adwalker.ad.admin.finance.form.CampaignConfirmFrom;
import cn.adwalker.ad.admin.finance.service.IAchievementReportService;
import cn.adwalker.ad.admin.finance.vo.AchievementReportSumVo;
import cn.adwalker.ad.admin.finance.vo.CampaignConfirmVo;
import cn.adwalker.ad.admin.finance.vo.ChannelDetailVo;
import cn.adwalker.ad.admin.finance.vo.ChannelPlatformSumVo;
import cn.adwalker.ad.admin.finance.vo.IncomeDetailVo;
import cn.adwalker.ad.admin.finance.vo.PlatformDetailVo;
import cn.adwalker.ad.admin.operation.vo.AchievementReportSum;
import cn.adwalker.ad.config.Constant;
import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.util.lang.ObjectUtils;
import cn.adwalker.model.ad.dao.IAreaDao;
import cn.adwalker.model.ad.dao.ICampaignSalesmanDao;
import cn.adwalker.model.ad.domain.Area;
import cn.adwalker.model.finance.domain.IncomeMonthCampaign;
import cn.adwalker.model.operation.dao.ICampaignConfirmDao;
import cn.adwalker.model.operation.dao.IIosAndroidDayNumDao;
import cn.adwalker.model.operation.domain.ChannelPlatformSum;

/**
 * <p>
 * Title: AchievementReportServiceImpl
 * </p>
 * <p>
 * Description:业绩报表service实现类
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-10-24
 */
@Service("achievementReportService")
public class AchievementReportServiceImpl implements IAchievementReportService {

	@Resource
	private ICampaignConfirmDao campaignConfirmDao;

	@Resource
	private IAreaDao areaDao;
	
	@Resource
	private ICampaignSalesmanDao campaignSalesmanDao;
	
	
	
	
	
	
	@Resource
	private  IIosAndroidDayNumDao iosAndroidDayNumDao;
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
	public List<IncomeDetailVo> findByPage(AchievementReportbean bean,SysUserVo manageUser,
			IPageInfo pageInfo) throws Exception {
		List<Object> params = new ArrayList<Object>();
		StringBuilder sb = new StringBuilder(
				" t_ios_android_day_num n LEFT JOIN  v_campaign t  ON n.campaign_id= t.ID LEFT JOIN t_campaign_salesman s ON t.salesman_id = s.id  where 1=1");
		if (bean != null) {
			if (ObjectUtils.isNotEmpty(bean.getMonth_stat_date())) {
				sb.append(" and n.static_date>= '");
				sb.append(bean.getMonth_stat_date());
				sb.append("'");
			}
			if (ObjectUtils.isNotEmpty(bean.getMonth_end_date())) {
				sb.append(" and n.static_date<= '");
				sb.append(bean.getMonth_end_date());
				sb.append("'");
			}
			
			if (ObjectUtils.isNotEmpty(bean.getAdv())) {
				sb.append(" and (upper(t.company_name)  like ? or t.adv_id =? )");
				params.add("%" + bean.getCompany_name().toUpperCase() + "%");
				params.add(bean.getAdv_id());
			}
			if (ObjectUtils.isNotEmpty(bean.getCampaign_name())) {
				sb.append(" and (upper(t.CAMPAIGN_NAME)  like ? or n.campaign_id =? )");
				params.add("%" + bean.getCampaign_name().toUpperCase() + "%");
				params.add(bean.getCampaign_id());
			}
			if (ObjectUtils.isNotEmpty(bean.getStatus())) {
				sb.append(" and n.status =?");
				params.add(bean.getStatus());
			}
			if (ObjectUtils.isNotEmpty(bean.getCharge_type())) {
				sb.append(" and n.charge_type =?");
				params.add(bean.getCharge_type());
			}
			if (ObjectUtils.isNotEmpty(bean.getOs())) {
				sb.append(" and t.os =?");
				params.add(bean.getOs());
			}
			if (ObjectUtils.isNotEmpty(bean.getSales())) {
				sb.append(" and s.name like ?");
				params.add("%" + bean.getSales()+ "%");
			}
			
			if (Constant.isSeal(manageUser.getRoleCode())) {
				Long l = campaignSalesmanDao
						.getSalesmanIdBySysUser(manageUser.getId());
				sb.append(" and s.id=?");
				params.add(l);
			
			}
		
		}
		return (List<IncomeDetailVo>) campaignConfirmDao
				.findByPage(
						" n.* ,t.company_name,t.campaign_name,t.os,s.name",
						sb.toString(), params.toArray(),
						" order by n.create_time desc ",
						IncomeDetailVo.class, pageInfo);
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
				"SELECT sum(n.price*n.confirm_amount) sum_income_cha_money, sum(n.confirm_amount) sum_forecast_amount FROM t_ios_android_day_num n LEFT JOIN  v_campaign t  ON n.campaign_id= t.ID LEFT JOIN t_campaign_salesman s ON t.salesman_id = s.id  where 1=1");
		if (bean != null) {
			if (ObjectUtils.isNotEmpty(bean.getMonth_stat_date())) {
				sb.append(" and n.static_date>= '");
				sb.append(bean.getMonth_stat_date());
				sb.append("'");
			}
			if (ObjectUtils.isNotEmpty(bean.getMonth_end_date())) {
				sb.append(" and n.static_date<= '");
				sb.append(bean.getMonth_end_date());
				sb.append("'");
			}
			
			if (ObjectUtils.isNotEmpty(bean.getAdv())) {
				sb.append(" and (upper(t.company_name)  like ? or t.adv_id =? )");
				params.add("%" + bean.getCompany_name().toUpperCase() + "%");
				params.add(bean.getAdv_id());
			}
			if (ObjectUtils.isNotEmpty(bean.getCampaign_name())) {
				sb.append(" and (upper(t.CAMPAIGN_NAME)  like ? or n.campaign_id =? )");
				params.add("%" + bean.getCampaign_name().toUpperCase() + "%");
				params.add(bean.getCampaign_id());
			}
			if (ObjectUtils.isNotEmpty(bean.getStatus())) {
				sb.append(" and n.status =?");
				params.add(bean.getStatus());
			}
			if (ObjectUtils.isNotEmpty(bean.getCharge_type())) {
				sb.append(" and n.charge_type =?");
				params.add(bean.getCharge_type());
			}
			if (ObjectUtils.isNotEmpty(bean.getOs())) {
				sb.append(" and t.os =?");
				params.add(bean.getOs());
			}
			if (ObjectUtils.isNotEmpty(bean.getSales())) {
				sb.append(" and s.name like ?");
				params.add("%" + bean.getSales()+ "%");
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
	public List<IncomeDetailVo> findAll(AchievementReportbean bean)
			throws Exception {

		List<Object> list = new ArrayList<Object>();
		StringBuilder sb = new StringBuilder(
				" T_CAMPAIGN_CONFIRM b left join V_CAMPAIGN t on b.campaign_id = t.ID left join t_campaign_salesman s "
						+ "on t.salesman_id = s.id left join t_sys_user u on b.Manager_id = u.id  where 1=1 and b.status in(3,8,9)");
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
			if (ObjectUtils.isNotEmpty(bean.getStatus())) {
				sb.append(" and b.status =?");
				list.add(bean.getStatus());
			}
		

		}

		return (List<IncomeDetailVo>) campaignConfirmDao
				.findAll(
						"b.*,t.campaign_name,t.charge_type, t.os,t.adv_id, t.company_name,u.real_name as create_user_name, s.name,s.area_type",
						sb.toString(), list.toArray(),
						" order by b.create_time desc ",
						IncomeDetailVo.class);
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
						"b.cost as income_money,b.amount as income_amount,b.type_id,t.company_name,t.id as campaign_id,t.CAMPAIGN_NAME, "
								+ "t.charge_type,t.os,t.adv_id,p.id as channel_id, p.dev_id,p.name as channel_name",
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
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private List<ChannelPlatformSum> getChaSumList(Long id) throws Exception {
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
	public List<PlatformDetailVo> getPlaInfo(Long confirm_id, IPageInfo pageInfo)
			throws Exception {
		List<Object> list = new ArrayList<Object>();
		StringBuilder sb = new StringBuilder(
				"T_FINANCE_COST_PLATFORM b left join V_CAMPAIGN t on b.CAMPAIGN_ID = t.ID left join t_application p on b.app_id=p.id where 1=1 and b.status=1");

		if (ObjectUtils.isNotEmpty(confirm_id)) {
			sb.append(" and b.balance_id =");
			sb.append(confirm_id);
		}
		return (List<PlatformDetailVo>) campaignConfirmDao
				.findByPage(
						"b.cost as income_money, b.amount as income_amount, t.company_name, t.id as campaign_id, t.CAMPAIGN_NAME,t.charge_type,"
								+ "t.os,t.adv_id, p.id as APP_ID,p.name as app_name  ",
						sb.toString(), list.toArray(),
						" order by b.create_time desc ",
						PlatformDetailVo.class, pageInfo);
	}

	@Override
	public ChannelPlatformSumVo findPlaSum(Long confirm_id) throws Exception {
		List<ChannelPlatformSum> list;
		list = this.getPlaSumList(confirm_id);
		Double sum_pha_money = 0d;// 确认 总金额
		Double sum_cha_money = 0d;// 确认 总金额
		if (list != null && list.size() > 0) {
			sum_pha_money = list.get(0).getSum_pha_money();
		}
		return new ChannelPlatformSumVo(sum_pha_money, sum_cha_money);
	}

	@SuppressWarnings("unchecked")
	private List<ChannelPlatformSum> getPlaSumList(Long confirm_id) {
		List<ChannelPlatformSum> list = null;
		StringBuilder sb = new StringBuilder(
				"select sum(b.cost) as sum_pha_money FROM  T_FINANCE_COST_PLATFORM b left join V_CAMPAIGN t on b.CAMPAIGN_ID = t.ID left join t_application p on b.app_id=p.id where 1=1 and b.status=1");
		if (ObjectUtils.isNotEmpty(confirm_id)) {
			sb.append(" and b.balance_id =");
			sb.append(confirm_id);
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
	* <p>Title: findByArea</p>
	* <p>Description:TODO</p>
	* @return
	* @throws Exception
	* @see cn.adwalker.ad.admin.finance.service.emar.escore.platform.finance.service.IAchievementReportService#findByArea()
	 */
	@Override
	public List<Area> findByArea() throws Exception {
		return this.areaDao.findByArea();
	}
	/**
	 * (non-Javadoc)
	* <p>Title: submitIncomeNum</p>
	* <p>Description:TODO</p>
	* @param ids
	* @throws Exception
	* @see cn.adwalker.ad.admin.finance.service.IAchievementReportService#submitIncomeNum(java.lang.String)
	 */
	@Override
	public Long submitIncomeNum(String ids,SysUserVo manageUser) throws Exception {
		Long income_id=0l;
		String arr[]=ids.split(",");
		String ars=null;
		if (arr != null && arr.length > 0) {
			StringBuilder sb = new StringBuilder();
			for (String d : arr) {
				sb.append(d).append(",");
			}
			sb.deleteCharAt(sb.length() - 1);
			ars = sb.toString();
		} else {
			ars = null;
		}
		IncomeMonthCampaign plist =new IncomeMonthCampaign();
				plist=iosAndroidDayNumDao.getById(ars);
		if(plist!=null){
			plist.setCreate_time(new Date());
			plist.setIncome_money(plist.getForecast_money());
			plist.setStatus(1);
			plist.setInvoice_status(0);
			plist.setPayment_status(0);
			plist.setManager_id(manageUser.getId());
			income_id=campaignConfirmDao.insert(plist);
		}
		iosAndroidDayNumDao.updateById(ars,income_id);
		
		return income_id;
	}
	/**
	 * (non-Javadoc)
	* <p>Title: getById</p>
	* <p>Description:TODO</p>
	* @param income_id
	* @return
	* @throws Exception
	* @see cn.adwalker.ad.admin.finance.service.IAchievementReportService#getById(java.lang.Long)
	 */
	@Override
	public CampaignConfirmVo getById(Long income_id) throws Exception {
		CampaignConfirmVo plist =new CampaignConfirmVo();
			plist=campaignConfirmDao.getgetById(income_id);
		return plist;
	}
	/**
	 * (non-Javadoc)
	* <p>Title: updateStatus</p>
	* <p>Description:TODO</p>
	* @param id
	* @throws Exception
	* @see cn.adwalker.ad.admin.finance.service.IAchievementReportService#updateStatus(java.lang.Long)
	 */
	@Override
	public void updateStatus(CampaignConfirmFrom from) throws Exception {
		IncomeMonthCampaign plist =new IncomeMonthCampaign();
		if(from!=null){
			plist.setId(from.getId());
			plist.setIncome_money(from.getIncome_money());
			plist.setIncome_remark(from.getIncome_remark());
			campaignConfirmDao.updateStatus(plist);
			
		}
	}
	/**
	 * (non-Javadoc)
	* <p>Title: deleteStatus</p>
	* <p>Description:TODO</p>
	* @param id
	* @throws Exception
	* @see cn.adwalker.ad.admin.finance.service.IAchievementReportService#deleteStatus(java.lang.Long)
	 */
	@Override
	public void deleteStatus(Long id) throws Exception {
		campaignConfirmDao.deleteStatus(id);
		iosAndroidDayNumDao.updateStatus(id, 1);
	}
	
}
