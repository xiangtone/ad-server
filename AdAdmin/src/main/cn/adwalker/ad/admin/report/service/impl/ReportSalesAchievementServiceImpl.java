package cn.adwalker.ad.admin.report.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.adwalker.core.log.Log;
import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.repository.impl.BaseServiceImpl;
import cn.adwalker.core.util.lang.ObjectUtils;
import cn.adwalker.model.report.dao.IReportDao;
import cn.adwalker.model.report.domain.SumSalesAchievement;
import cn.adwalker.ad.admin.report.bean.SalesAchievementBean;
import cn.adwalker.ad.admin.report.service.IReportSalesAchievementService;
import cn.adwalker.ad.admin.report.vo.ReportSalesAchievementVo;
import cn.adwalker.ad.admin.report.vo.SumSalesAchievementVo;

/**
 * <p>
 * Title: ReportSalesAchievementServiceImpl
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-8-19
 */
@Service
public class ReportSalesAchievementServiceImpl extends BaseServiceImpl
		implements IReportSalesAchievementService {

	@Resource
	private IReportDao reportDao;

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findByPage
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param pageInfo
	 * @param bean
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.report.service.IReportSalesAchievementService#findByPage(cn.adwalker.core.page.IPageInfo,
	 *      cn.adwalker.ad.admin.report.bean.SalesAchievementBean)
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.NEVER)
	@Override
	@Log
	public List<ReportSalesAchievementVo> findByPage(IPageInfo pageInfo,
			SalesAchievementBean bean) throws Exception {
		List<ReportSalesAchievementVo> list = null;
		StringBuffer sb = new StringBuffer(
				" T_CAMPAIGN_CONFIRM a left join v_campaign t  on a.campaign_id = t.id  left join V_CAMPAIGN_SALESMAN sel  on a.campaign_id=sel.CAMPAIGN_id where 1=1 and a.STATUS=1");
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
			if (ObjectUtils.isNotEmpty(bean.getAdv_id())) {
				sb.append(" and t.adv_id like '%");
				sb.append(bean.getAdv_id());
				sb.append("%'");
			}
			// 平台类型
			if (ObjectUtils.isNotEmpty(bean.getOs())) {
				sb.append(" and t.os like'%");
				sb.append(bean.getOs());
				sb.append("%'");
			}
			// 销售人
			if (ObjectUtils.isNotEmpty(bean.getSales_name())) {
				sb.append(" and  upper(sel.NAME) like'%");
				sb.append(bean.getSales_name());
				sb.append("%'");
			}
			if (ObjectUtils.isNotEmpty(bean.getBeginTime())) {
				sb.append(" and a.month_stat_date>= '");
				sb.append(bean.getBeginTime());
				sb.append("'");
			}
			if (ObjectUtils.isNotEmpty(bean.getEndTime())) {
				sb.append(" and a.month_end_date<= '");
				sb.append(bean.getEndTime());
				sb.append("'");
			}
			
			if (bean.getSales_id()!=null) {
				sb.append(" and t.salesman_id=");
				sb.append(bean.getSales_id());
				sb.append("");
			}
			/*1、销售总监可以看到全部数据
			 *2、销售经理可以看到本区内的数据
			 *3、销售专员只能看到自己的数据
			 */
			if (bean.getArea_type_ad() != null) {
				sb.append(" and sel.area_type=");
				sb.append(bean.getArea_type_ad());
			}
			sb.append(" ");
		}
		list = (List<ReportSalesAchievementVo>) reportDao
				.findByPage(
						" sel.NAME as sales_name,sel.AREA_TYPE ,a.*, t.adv_id, t.adv_email, t.CAMPAIGN_NAME, t.os",
						sb.toString(), null, null,
						ReportSalesAchievementVo.class, pageInfo);
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
	 * @see cn.adwalker.ad.admin.report.service.IReportSalesAchievementService#findAll(cn.adwalker.admin.report.bean.AdByHourQuery)
	 */
	@Override
	public List<ReportSalesAchievementVo> findAll(SalesAchievementBean bean)
			throws Exception {
		List<ReportSalesAchievementVo> resultList = null;
		StringBuffer sb = new StringBuffer(
				" T_CAMPAIGN_CONFIRM a left join v_campaign t  on a.campaign_id = t.id  left join V_CAMPAIGN_SALESMAN sel  on a.campaign_id=sel.CAMPAIGN_id where 1=1 and a.STATUS=1");
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
			// 销售人
			if (ObjectUtils.isNotEmpty(bean.getSales_name())) {
				sb.append(" and  upper(sel.NAME) like'%");
				sb.append(bean.getSales_name());
				sb.append("%'");
			}
			// 广告主id
			if (ObjectUtils.isNotEmpty(bean.getAdv_id())) {
				sb.append(" and t.adv_id like '%");
				sb.append(bean.getAdv_id());
				sb.append("%'");
			}
			// 平台类型
			if (ObjectUtils.isNotEmpty(bean.getOs())) {
				sb.append(" and t.os like'%");
				sb.append(bean.getOs());
				sb.append("%'");
			}
			if (ObjectUtils.isNotEmpty(bean.getBeginTime())) {
				sb.append(" and a.month_stat_date>= '");
				sb.append(bean.getBeginTime());
				sb.append("'");
			}
			if (ObjectUtils.isNotEmpty(bean.getEndTime())) {
				sb.append(" and a.month_end_date<= '");
				sb.append(bean.getEndTime());
				sb.append("'");
			}
			if (bean.getArea_type_ad() != null) {
				sb.append(" and sel.area_type=");
				sb.append(bean.getArea_type_ad());
			}
			sb.append(" ");
		}
		resultList = (List<ReportSalesAchievementVo>) reportDao
				.findAll(
						" sel.NAME as sales_name,sel.AREA_TYPE , a.*, t.adv_id, t.adv_email, t.CAMPAIGN_NAME, t.os",
						sb.toString(), null, null,
						ReportSalesAchievementVo.class);

		return resultList;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: getSumSales
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.report.service.IReportSalesAchievementService#getSumSales(cn.adwalker.ad.admin.report.bean.SalesAchievementBean)
	 */
	@Override
	public SumSalesAchievementVo getSumSales(SalesAchievementBean bean)
			throws Exception {
		List<SumSalesAchievement> list;
		list = this.geSumList(bean);
		Integer sum_platform_amount = 0;// 统计 总确认数
		Double sum_income_money = 0d;// 确认 总金额
		if (list != null && list.size() > 0) {
			sum_platform_amount = list.get(0).getSum_platform_amount();
			sum_income_money = list.get(0).getSum_income_money();
		}
		return new SumSalesAchievementVo(sum_platform_amount, sum_income_money);
	}

	/**
	 * 
	 * <p>
	 * Title: getAdvConsumeDetailList
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param bean
	 * @return
	 * @author lichuang
	 * @date 2013-8-20
	 * @return List<SumSalesAchievement>
	 * @version 1.0
	 */
	private List<SumSalesAchievement> geSumList(SalesAchievementBean bean) {
		List<SumSalesAchievement> list = null;
		List<Object> param = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer(
				"select sum(a.income_money) as sum_income_money from T_CAMPAIGN_CONFIRM a left join v_campaign t  on a.campaign_id = t.id  left join V_CAMPAIGN_SALESMAN sel  on a.campaign_id=sel.CAMPAIGN_id where 1=1 and a.STATUS=1");
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
			// 销售人
			if (ObjectUtils.isNotEmpty(bean.getSales_name())) {
				sb.append(" and  upper(sel.NAME) like'%");
				sb.append(bean.getSales_name());
				sb.append("%'");
			}
			// 广告主id
			if (ObjectUtils.isNotEmpty(bean.getAdv_id())) {
				sb.append(" and t.adv_id like '%");
				sb.append(bean.getAdv_id());
				sb.append("%'");
			}
			// 平台类型
			if (ObjectUtils.isNotEmpty(bean.getOs())) {
				sb.append(" and t.os like'%");
				sb.append(bean.getOs());
				sb.append("%'");
			}
			if (ObjectUtils.isNotEmpty(bean.getBeginTime())) {
				sb.append(" and a.month_stat_date>= '");
				sb.append(bean.getBeginTime());
				sb.append("'");
			}
			if (ObjectUtils.isNotEmpty(bean.getEndTime())) {
				sb.append(" and a.month_end_date<= '");
				sb.append(bean.getEndTime());
				sb.append("'");
			}
			if (bean.getArea_type_ad() != null) {
				sb.append(" and sel.area_type=");
				sb.append(bean.getArea_type_ad());
			}
			sb.append(" ");
		}
		try {
			list = this.reportDao.findAll(sb.toString(), param.toArray(),
					SumSalesAchievement.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}