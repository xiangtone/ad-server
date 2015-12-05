package cn.adwalker.ad.admin.finance.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.util.lang.ObjectUtils;
import cn.adwalker.model.finance.dao.IFinanceRevenueCostDao;
import cn.adwalker.model.finance.domain.RevenueCostConsume;
import cn.adwalker.ad.admin.finance.bean.FinanceRevenueCostYJFbean;
import cn.adwalker.ad.admin.finance.bean.FinanceRevenueCostbean;
import cn.adwalker.ad.admin.finance.service.IFinanceRevenueCostService;
import cn.adwalker.ad.admin.finance.vo.FinanceRevenueCostVo;
import cn.adwalker.ad.admin.finance.vo.FinanceRevenueCostYJFVo;
import cn.adwalker.ad.admin.finance.vo.RevenueCostVo;
import cn.adwalker.ad.admin.finance.vo.RevenueCostYJFVo;

/**
 * <p>
 * Title: FinanceRevenueCostServiceImpl
 * </p>
 * <p>
 * Description:确认收入成本Service实现类
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-6-7
 */
@Service("financeRevenueCost")
public class FinanceRevenueCostServiceImpl implements
		IFinanceRevenueCostService {
	@Resource
	private IFinanceRevenueCostDao financeRevenueCostDao;

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findByCost
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param bean
	 * @param pageInfo
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.finance.service.IFinanceRevenueCostService#findByCost(cn.adwalker.ad.admin.finance.bean.FinanceRevenueCostbean,
	 *      cn.adwalker.core.page.IPageInfo)
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.NEVER)
	@Override
	public List<FinanceRevenueCostVo> findByCost(FinanceRevenueCostbean bean,
			IPageInfo pageInfo) throws Exception {
		List<FinanceRevenueCostVo> list = null;
		StringBuffer sb = new StringBuffer(
				" T_CAMPAIGN_CONFIRM a left join v_campaign t  on a.campaign_id = t.id  where 1=1 and a.STATUS=1");
		if (bean != null) {
			// 活动id
			if (ObjectUtils.isNotEmpty(bean.getCampaign_id())) {
				sb.append(" and a.campaign_id like '%");
				sb.append(bean.getCampaign_id());
				sb.append("%'");
			}
			// 活动名称
			if (ObjectUtils.isNotEmpty(bean.getCampaign_name())) {
				sb.append(" and upper(t.campaign_name) like '%");
				sb.append(bean.getCampaign_name().trim().toUpperCase());
				sb.append("%'");
			}
			// 广告主id
			if (ObjectUtils.isNotEmpty(bean.getAdv_Id())) {
				sb.append(" and t.adv_id like '%");
				sb.append(bean.getAdv_Id());
				sb.append("%'");
			}
			// 广告主
			if (ObjectUtils.isNotEmpty(bean.getAdv_email())) {
				sb.append(" and upper(t.company_name) like '%");
				sb.append(bean.getAdv_email().trim().toUpperCase());
				sb.append("%'");
			}
			// 平台类型
			if (ObjectUtils.isNotEmpty(bean.getOs())) {
				sb.append(" and t.os like'%");
				sb.append(bean.getOs());
				sb.append("%'");
			}
			if (ObjectUtils.isNotEmpty(bean.getMonth_stat_date())) {
				sb.append(" and a.month_stat_date>= '");
				sb.append(bean.getMonth_stat_date());
				sb.append("'");
			}
			if (ObjectUtils.isNotEmpty(bean.getMonth_end_date())) {
				sb.append(" and a.month_end_date<= '");
				sb.append(bean.getMonth_end_date());
				sb.append("'");
			}
			sb.append(" ");
		}
		list = (List<FinanceRevenueCostVo>) financeRevenueCostDao
				.findByPage(
						"a.*, t.adv_id, t.company_name, t.CAMPAIGN_NAME, t.os",
						sb.toString(), null, null, FinanceRevenueCostVo.class,
						pageInfo);
		return list;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findByList
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param bean
	 * @param pageInfo
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.finance.service.IFinanceRevenueCostService#findByList(cn.adwalker.ad.admin.finance.bean.FinanceRevenueCostYJFbean,
	 *      cn.adwalker.core.page.IPageInfo)
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.NEVER)
	@Override
	public List<FinanceRevenueCostYJFVo> findByList(
			FinanceRevenueCostYJFbean bean, IPageInfo pageInfo)
			throws Exception {
		List<Object> param = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer(
				"V_INCOME_OUTCOME v   LEFT JOIN  T_ADVERTISER adv ON v.adv_id=adv.id LEFT JOIN  T_TYPE tp  on v.type_id=tp.id where 1=1");
		// 广告主ID
		if (!StringUtils.isEmpty(bean.getAdv())) {
			sb.append(" and (v.adv_id =? or upper(adv.company_name) like ?)");
			param.add(bean.getAdv_Id());
			param.add("%" + bean.getAdv_email().trim().toUpperCase() + "%");
		}

		// 活动名称
		if (!ObjectUtils.isEmpty(bean.getCampaign())) {
			sb.append(" and (upper(v.campaign_name) like ? or v.campaign_id=?)");
			param.add("%" + bean.getCampaign_name().trim().toUpperCase() + "%");
			param.add(bean.getCampaign_id());
		}

		// 平台类型
		if (!ObjectUtils.isEmpty(bean.getOs())) {
			sb.append(" and v.os ='");
			sb.append(bean.getOs());
			sb.append("'");
		}
		// 广告类型
		if (!ObjectUtils.isEmpty(bean.getType_id())) {
			sb.append(" and v.type_id =?");
			param.add(bean.getType_id());
		}
		// 媒体类型
		if (!ObjectUtils.isEmpty(bean.getMedia_type())) {
			sb.append(" and v.type like ?");
			param.add("%" + bean.getMedia_type() + "%");
		}
		// 效果发生时间
		if (ObjectUtils.isNotEmpty(bean.getStatDateStartTime())) {
			sb.append(" and v.static_date >='" + bean.getStatDateStartTime()
					+ "'");
		}
		if (ObjectUtils.isNotEmpty(bean.getStatDateEndTime())) {
			sb.append(" and v.static_date <='" + bean.getStatDateEndTime()
					+ "'");
		}
		return (List<FinanceRevenueCostYJFVo>) financeRevenueCostDao
				.findByPage("v.*, adv.company_name,tp.name type_name",
						sb.toString(), param.toArray(), " order by v.static_date",
						FinanceRevenueCostYJFVo.class, pageInfo);//增加默认排序 mark by cdd

	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: getRevenueCostSum
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.finance.service.IFinanceRevenueCostService#getRevenueCostSum(cn.adwalker.ad.admin.finance.bean.FinanceRevenueCostbean)
	 */
	@Transactional(propagation = Propagation.NEVER)
	@Override
	public RevenueCostVo getRevenueCostSum(FinanceRevenueCostbean bean)
			throws Exception {
		List<RevenueCostConsume> list;
		list = this.getRevenueCostSumDetailList(bean);
		Double sum_gross_profit = 0d;// 百分比
		Double sum_income_money = 0d;// 确认收入
		Double sum_forecast_money = 0d;// 确认成本
		if (list != null && list.size() > 0) {
			sum_gross_profit = list.get(0).getSum_gross_profit();
			sum_income_money = list.get(0).getSum_income_money();
			sum_forecast_money = list.get(0).getSum_forecast_money();
		}
		return new RevenueCostVo(sum_gross_profit, sum_income_money,
				sum_forecast_money);

	}

	/**
	 * 
	 * <p>
	 * Title: getRevenueCostSumDetailList
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param bean
	 * @return
	 * @author lichuang
	 * @date 2013-8-27
	 * @return List<RevenueCostConsume>
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	private List<RevenueCostConsume> getRevenueCostSumDetailList(
			FinanceRevenueCostbean bean) {
		List<RevenueCostConsume> list = null;
		List<Object> param = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer(
				"select sum(a.income_money) as sum_income_money,sum(a.exprice_money) as sum_forecast_money, (sum(a.income_money)-sum(a.exprice_money))/sum(a.income_money)*100 as sum_gross_profit from T_CAMPAIGN_CONFIRM a left join v_campaign t  on a.campaign_id = t.id  where 1=1 and a.STATUS=1");
		if (bean != null) {
			// 活动id
			if (ObjectUtils.isNotEmpty(bean.getCampaign_id())) {
				sb.append(" and a.campaign_id like '%");
				sb.append(bean.getCampaign_id());
				sb.append("%'");
			}
			// 活动名称
			if (ObjectUtils.isNotEmpty(bean.getCampaign_name())) {
				sb.append(" and upper(t.campaign_name) like '%");
				sb.append(bean.getCampaign_name().trim().toUpperCase());
				sb.append("%'");
			}
			// 广告主id
			if (ObjectUtils.isNotEmpty(bean.getAdv_Id())) {
				sb.append(" and t.adv_id like '%");
				sb.append(bean.getAdv_Id());
				sb.append("%'");
			}
			// 广告主
			if (ObjectUtils.isNotEmpty(bean.getAdv_email())) {
				sb.append(" and upper(t.company_name) like '%");
				sb.append(bean.getAdv_email().trim().toUpperCase());
				sb.append("%'");
			}
			// 平台类型
			if (ObjectUtils.isNotEmpty(bean.getOs())) {
				sb.append(" and t.os like'%");
				sb.append(bean.getOs());
				sb.append("%'");
			}
			if (ObjectUtils.isNotEmpty(bean.getMonth_stat_date())) {
				sb.append(" and a.month_stat_date>= '");
				sb.append(bean.getMonth_stat_date());
				sb.append("'");
			}
			if (ObjectUtils.isNotEmpty(bean.getMonth_end_date())) {
				sb.append(" and a.month_end_date<= '");
				sb.append(bean.getMonth_end_date());
				sb.append("'");
			}
			sb.append(" ");
		}
		try {
			list = this.financeRevenueCostDao.findAll(sb.toString(),
					param.toArray(), RevenueCostConsume.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: getRevenueCostYJFSum
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.finance.service.IFinanceRevenueCostService#getRevenueCostYJFSum(cn.adwalker.ad.admin.finance.bean.FinanceRevenueCostYJFbean)
	 */
	@Override
	public RevenueCostYJFVo getRevenueCostYJFSum(FinanceRevenueCostYJFbean bean)
			throws Exception {

		List<RevenueCostConsume> list;
		list = this.getRevenueCostYJFSumDetailList(bean);
		Double sum_sys_cost= 0d;//预计成本
		Double sum_forecast_income= 0d;//预计收入
		Double sum_forecast_confirm_cost= 0d;//预确认成本
		Double sum_forecast_confirm_income= 0d;//预确认收入
		if (list != null && list.size() > 0) {
			sum_sys_cost = list.get(0).getSum_sys_cost();
			sum_forecast_income = list.get(0).getSum_forecast_income();
			sum_forecast_confirm_cost = list.get(0).getSum_forecast_confirm_cost();
			sum_forecast_confirm_income = list.get(0).getSum_forecast_confirm_income();
		}
		return new RevenueCostYJFVo(sum_sys_cost,sum_forecast_income,sum_forecast_confirm_cost,sum_forecast_confirm_income);
	}

	/**
	 * 
	 * <p>
	 * Title: getRevenueCostYJFSumDetailList
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param bean
	 * @return
	 * @author lichuang
	 * @date 2013-8-27
	 * @return List<RevenueCostConsume>
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	private List<RevenueCostConsume> getRevenueCostYJFSumDetailList(
			FinanceRevenueCostYJFbean bean) {
		List<RevenueCostConsume> list = null;
		List<Object> param = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer(
				"select sum(v.sys_cost) as sum_sys_cost, sum(v.sys_cost*(1+v.profit_rate)) as sum_forecast_income, sum(v.sys_cost) as sum_forecast_confirm_cost,sum(v.IN_PRICE*v.CONFIRM_NUM) as sum_forecast_confirm_income from V_INCOME_OUTCOME v   LEFT JOIN  T_ADVERTISER adv ON v.adv_id=adv.id LEFT JOIN  T_TYPE tp  on v.type_id=tp.id where 1=1");
		// 广告主ID
		if (!StringUtils.isEmpty(bean.getAdv())) {
			sb.append(" and (v.adv_id =? or upper(adv.company_name) like ?)");
			param.add(bean.getAdv_Id());
			param.add("%" + bean.getAdv_email().trim().toUpperCase() + "%");
		}

		// 活动名称
		if (!ObjectUtils.isEmpty(bean.getCampaign())) {
			sb.append(" and (upper(v.campaign_name) like ? or v.campaign_id=?)");
			param.add("%" + bean.getCampaign_name().trim().toUpperCase() + "%");
			param.add(bean.getCampaign_id());
		}

		// 平台类型
		if (!ObjectUtils.isEmpty(bean.getOs())) {
			sb.append(" and v.os ='");
			sb.append(bean.getOs());
			sb.append("'");
		}
		// 广告类型
		if (!ObjectUtils.isEmpty(bean.getType_id())) {
			sb.append(" and v.type_id =?");
			param.add(bean.getType_id());
		}
		// 媒体类型
		if (!ObjectUtils.isEmpty(bean.getMedia_type())) {
			sb.append(" and v.type like ?");
			param.add("%" + bean.getMedia_type() + "%");
		}
		// 效果发生时间
		if (ObjectUtils.isNotEmpty(bean.getStatDateStartTime())) {
			sb.append(" and v.static_date >='" + bean.getStatDateStartTime()
					+ "'");
		}
		if (ObjectUtils.isNotEmpty(bean.getStatDateEndTime())) {
			sb.append(" and v.static_date <='" + bean.getStatDateEndTime()
					+ "'");
		}
		try {
			list = this.financeRevenueCostDao.findAll(sb.toString(),
					param.toArray(), RevenueCostConsume.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
