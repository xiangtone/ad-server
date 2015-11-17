package cn.adwalker.ad.admin.report.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.adwalker.model.report.dao.ICensusGeneralViewDao;
import cn.adwalker.ad.admin.report.bean.CensusGeneralViewBean;
import cn.adwalker.ad.admin.report.service.ICensusGeneralViewService;
import cn.adwalker.ad.admin.report.vo.AllCensusGeneralViewVo;
import cn.adwalker.ad.admin.report.vo.CensusGeneralViewAdvVo;
import cn.adwalker.ad.admin.report.vo.CensusGeneralViewAppVo;
import cn.adwalker.ad.admin.report.vo.ChannelCensusGeneralViewVo;
import cn.adwalker.ad.admin.report.vo.PlatformCensusBydayVo;
import cn.adwalker.ad.admin.report.vo.PlatformCensusGeneralViewVo;
import cn.adwalker.ad.admin.report.vo.PlatformCensusOutcomeVo;
import cn.adwalker.ad.admin.report.vo.ReportAndroidIosAllOutincomeVo;
import cn.adwalker.ad.admin.report.vo.ReportAndroidIosAllVo;
import cn.adwalker.ad.admin.report.vo.ReportIncomeExpensesVo;
import cn.adwalker.core.util.lang.ObjectUtils;

/**
 * <p>
 * Title: CensusGeneralViewServiceImpl
 * </p>
 * <p>
 * Description:统计概览
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-5-18
 */
@Service("censusGeneralView")
public class CensusGeneralViewServiceImpl implements ICensusGeneralViewService {

	@Resource
	private JdbcTemplate jdbcTemplate;

	@Resource
	private ICensusGeneralViewDao censusGeneralViewDao;

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findChannelList
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param bean
	 * @param pageInfo
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.report.service.ICensusGeneralViewService#findChannelList(cn.adwalker.admin.channel.bean.SearchChannelBean,
	 *      cn.adwalker.core.page.IPageInfo)
	 */
	@Transactional(propagation = Propagation.NEVER)
	@Override
	public List<CensusGeneralViewAdvVo> findGeneralViewAdvList(
			CensusGeneralViewBean bean) throws Exception {
		List<CensusGeneralViewAdvVo> advList = censusGeneralViewDao
				.findGeneralViewAdvList(bean);
		double adv_cost = censusGeneralViewDao.findAdvsumCost(bean);
		for (int i = 0; i < advList.size(); i++) {
			CensusGeneralViewAdvVo vo = advList.get(i);
			vo.setSum_cost(adv_cost);
		}
		return advList;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findChannelList
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param bean
	 * @param pageInfo
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.report.service.ICensusGeneralViewService#findChannelList(cn.adwalker.admin.channel.bean.SearchChannelBean,
	 *      cn.adwalker.core.page.IPageInfo)
	 */
	@Transactional(propagation = Propagation.NEVER)
	@Override
	public List<CensusGeneralViewAppVo> findGeneralViewAppList(
			CensusGeneralViewBean bean) throws Exception {
		List<CensusGeneralViewAppVo> appList = censusGeneralViewDao
				.findGeneralViewAppList(bean);
		double sum_cost = censusGeneralViewDao.findGeneralsumCost(bean);
		for (int i = 0; i < appList.size(); i++) {
			CensusGeneralViewAppVo vo = appList.get(i);
			vo.setSum_cost(sum_cost);
		}
		return appList;

	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findGeneralViewPlatformList
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.report.service.ICensusGeneralViewService#findGeneralViewPlatformList(cn.adwalker.ad.admin.report.bean.CensusGeneralViewBean)
	 */
	@Transactional(propagation = Propagation.NEVER)
	@Override
	public PlatformCensusGeneralViewVo findGeneralViewPlatformList(
			CensusGeneralViewBean bean) throws Exception {
		PlatformCensusGeneralViewVo vo = new PlatformCensusGeneralViewVo();
		PlatformCensusBydayVo byday = null;
		PlatformCensusOutcomeVo outcome = null;
		byday = this.findGeneralViewPlatform(bean);
		outcome = this.findGeneralViewOutcome(bean);
		if (byday != null && outcome != null) {
			vo.setDown_amount(byday.getDown_amount());
			vo.setClick_amount(byday.getClick_amount());
			vo.setPv_amount(byday.getPv_amount());
			vo.setSum_platform_amount(byday.getSum_platform_amount());
			vo.setSum_cost_price(outcome.getSum_cost_price());
			vo.setSum_income_amount(outcome.getSum_income_amount());
			vo.setSum_income_money(outcome.getSum_income_money());
		}

		return vo;
	}

	/**
	 * <p>
	 * Title: findGeneralViewOutcome
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-8-8
	 * @return List<PlatformCensusOutcomeVo>
	 * @version 1.0
	 */
	private PlatformCensusOutcomeVo findGeneralViewOutcome(
			CensusGeneralViewBean bean) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select sum(v.SYS_COST) as sum_cost_price,sum(v.CONFIRM_NUM) as sum_income_amount  ,sum(v.CONFIRM_NUM*v.IN_PRICE) as sum_income_money from  V_INCOME_OUTCOME v  LEFT JOIN V_MEDIA t on v.MEDIA_ID = t.ID where 1 = 1 and t.TYPE = 0");
		// 日
		if (ObjectUtils.isNotEmpty(bean.getStart_stat_date())) {
			sb.append(" and v.static_date >='");
			sb.append(bean.getStart_stat_date());
			sb.append("'");
		}
		if (ObjectUtils.isNotEmpty(bean.getEnd_stat_date())) {
			sb.append("and v.static_date <='");
			sb.append(bean.getEnd_stat_date());
			sb.append("'");
		}
		// 周
		if (ObjectUtils.isNotEmpty(bean.getStart_date())) {
			sb.append(" and v.static_date >='");
			sb.append(bean.getStart_date());
			sb.append("'");
		}
		if (ObjectUtils.isNotEmpty(bean.getEnd_date())) {
			sb.append("and v.static_date <='");
			sb.append(bean.getEnd_date());
			sb.append("'");
		}
		// 月
		if (ObjectUtils.isNotEmpty(bean.getMonth_stat_date())) {
			sb.append("and v.static_date like '%");
			sb.append(bean.getMonth_stat_date());
			sb.append("%'");
		}
		List<PlatformCensusOutcomeVo> listOut = jdbcTemplate.query(sb
				.toString(), new BeanPropertyRowMapper(
				PlatformCensusOutcomeVo.class));
		if (listOut != null && listOut.size() > 0) {
			PlatformCensusOutcomeVo conf = listOut.get(0);
			return conf;
		} else {
			return null;
		}
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findGeneralViewChannelList
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.report.service.ICensusGeneralViewService#findGeneralViewChannelList(cn.adwalker.ad.admin.report.bean.CensusGeneralViewBean)
	 */
	@Transactional(propagation = Propagation.NEVER)
	@Override
	public List<ChannelCensusGeneralViewVo> findGeneralViewChannelList(
			CensusGeneralViewBean bean) throws Exception {

		return this.findGeneralViewChannel(bean);
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findAllCensusGeneralViewList
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.report.service.ICensusGeneralViewService#findAllCensusGeneralViewList(cn.adwalker.ad.admin.report.bean.CensusGeneralViewBean)
	 */
	@Transactional(propagation = Propagation.NEVER)
	@Override
	public AllCensusGeneralViewVo findAllCensusGeneralViewList(
			CensusGeneralViewBean bean) throws Exception {
		AllCensusGeneralViewVo vo = new AllCensusGeneralViewVo();
		PlatformCensusBydayVo byday = null;
		PlatformCensusOutcomeVo outcome = null;
		byday = this.findGeneralViewPlatform(bean);
		outcome = this.findGeneralViewAll(bean);
		if (byday != null && outcome != null) {
			vo.setDown_amount(byday.getDown_amount());
			vo.setClick_amount(byday.getClick_amount());
			vo.setPv_amount(byday.getPv_amount());
			vo.setSum_platform_amount(byday.getSum_platform_amount());
			vo.setSum_cost_price(outcome.getSum_cost_price());
			vo.setSum_income_amount(outcome.getSum_income_amount());
			vo.setSum_income_money(outcome.getSum_income_money());
		}
		return vo;
	}

	/**
	 * <p>
	 * Title: findGeneralViewChannelList
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-5-28
	 * @return List<ChannelCensusGeneralViewVo>
	 * @version 1.0
	 */
	@Transactional(propagation = Propagation.NEVER)
	private List<ChannelCensusGeneralViewVo> findGeneralViewChannel(
			CensusGeneralViewBean bean) throws Exception {
		List<Object> list = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer();
		sb.append(" V_INCOME_OUTCOME v  LEFT JOIN V_MEDIA t on v.MEDIA_ID = t.ID where 1 = 1 and t.TYPE = 1 ");
		// 日
		if (ObjectUtils.isNotEmpty(bean.getStart_stat_date())) {
			sb.append(" and v.static_date >='");
			sb.append(bean.getStart_stat_date());
			sb.append("'");
		}
		if (ObjectUtils.isNotEmpty(bean.getEnd_stat_date())) {
			sb.append("and v.static_date <='");
			sb.append(bean.getEnd_stat_date());
			sb.append("'");
		}
		sb.append(" group by v.STATIC_DATE");
		return (List<ChannelCensusGeneralViewVo>) censusGeneralViewDao
				.findAll(
						" v.STATIC_DATE,sum(v.SYS_COST) as sum_cost_price,sum(v.CONFIRM_NUM) as sum_income_amount ,sum(v.CONFIRM_NUM*v.IN_PRICE) as sum_income_money ",
						sb.toString(), list.toArray(),
						ChannelCensusGeneralViewVo.class);
	}

	/**
	 * <p>
	 * Title: findGeneralViewPlatform
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-5-28
	 * @return List<PlatformCensusGeneralViewVo>
	 * @version 1.0
	 */
	@Transactional(propagation = Propagation.NEVER)
	private PlatformCensusBydayVo findGeneralViewPlatform(
			CensusGeneralViewBean bean) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select sum(s.pv) as pv_amount, sum(s.click) as click_amount, sum(s.download) as down_amount, sum(s.activate) as sum_platform_amount,sum(s.cost) as sum_cost_price from T_STATIC_APP_BYDAY s  where 1 = 1");
		// ri
		if (ObjectUtils.isNotEmpty(bean.getStart_stat_date())) {
			sb.append(" and s.static_date>='");
			sb.append(bean.getStart_stat_date());
			sb.append("'");
		}
		if (ObjectUtils.isNotEmpty(bean.getEnd_stat_date())) {
			sb.append("and s.static_date<='");
			sb.append(bean.getEnd_stat_date());
			sb.append("'");
		}
		// zhou
		if (ObjectUtils.isNotEmpty(bean.getStart_date())) {
			sb.append(" and s.static_date>='");
			sb.append(bean.getStart_date());
			sb.append("'");
		}
		if (ObjectUtils.isNotEmpty(bean.getEnd_date())) {
			sb.append("and s.static_date<='");
			sb.append(bean.getEnd_date());
			sb.append("'");
		}
		// yue
		if (ObjectUtils.isNotEmpty(bean.getMonth_stat_date())) {
			sb.append("and s.static_date like '%");
			sb.append(bean.getMonth_stat_date());
			sb.append("%'");
		}
		List<PlatformCensusBydayVo> listbyday = jdbcTemplate.query(sb
				.toString(), new BeanPropertyRowMapper(
				PlatformCensusBydayVo.class));
		if (listbyday != null && listbyday.size() > 0) {
			PlatformCensusBydayVo confirm = listbyday.get(0);
			return confirm;
		} else {
			return null;
		}
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: flowlist
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.report.service.ICensusGeneralViewService#flowlist(cn.adwalker.ad.admin.report.bean.CensusGeneralViewBean)
	 */
	@Transactional(propagation = Propagation.NEVER)
	@Override
	public List<ReportAndroidIosAllVo> flowlist(CensusGeneralViewBean bean)
			throws Exception {
		List<ReportAndroidIosAllVo> list = null;
		list = censusGeneralViewDao.flowlist(bean);
		return list;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: IncomeExpenseslist
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.report.service.ICensusGeneralViewService#IncomeExpenseslist(cn.adwalker.ad.admin.report.bean.CensusGeneralViewBean)
	 */
	@Transactional(propagation = Propagation.NEVER)
	@Override
	public List<ReportIncomeExpensesVo> IncomeExpenseslist(
			CensusGeneralViewBean bean) throws Exception {
		List<ReportIncomeExpensesVo> list = null;
		list = censusGeneralViewDao.findAllList(bean);
		return list;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findChannelList
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.model.report.dao.ICensusGeneralViewDao#findChannelList(cn.adwalker.ad.admin.report.bean.CensusGeneralViewBean)
	 */
	private List<ReportIncomeExpensesVo> findChannelList(
			CensusGeneralViewBean bean) throws Exception {
		List<Object> list = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer(
				" T_LOG_AD_CHANNEL_EFFECT l left join T_AD a on l.ad_id=a.id where 1 = 1 and  l.type=1");
		if (ObjectUtils.isNotEmpty(bean.getAndroid_ios_type())) {
			sb.append("and l.os like '%");
			sb.append(bean.getAndroid_ios_type());
			sb.append("%'");
		}
		if (ObjectUtils.isNotEmpty(bean.getStart_stat_date())) {
			sb.append(" and l.static_date>='");
			sb.append(bean.getStart_stat_date());
			sb.append("'");
		}
		if (ObjectUtils.isNotEmpty(bean.getEnd_stat_date())) {
			sb.append("and l.static_date<='");
			sb.append(bean.getEnd_stat_date());
			sb.append("'");
		}
		if (ObjectUtils.isNotEmpty(bean.getStart_stat_date())) {
			sb.append(" and l.static_date >='");
			sb.append(bean.getStart_stat_date());
			sb.append("'");
		}
		if (ObjectUtils.isNotEmpty(bean.getEnd_stat_date())) {
			sb.append("and l.static_date <='");
			sb.append(bean.getEnd_stat_date());
			sb.append("'");
		}
		if (ObjectUtils.isNotEmpty(bean.getMonth_stat_date())) {
			sb.append("and l.static_date like '%");
			sb.append(bean.getMonth_stat_date());
			sb.append("%'");
		}
		if (ObjectUtils.isNotEmpty(bean.getMonth_stat_date())) {
			sb.append("and l.static_date like '%");
			sb.append(bean.getMonth_stat_date());
			sb.append("%'");
		}
		sb.append(" group by l.static_date");
		return (List<ReportIncomeExpensesVo>) censusGeneralViewDao
				.findAll(
						" l.static_date, (sum(l.confirm_num)*sum(a.blance_price)) as cost ,sum(l.income_money) as income_money",
						sb.toString(), list.toArray(),
						" order by l.static_date desc ",
						ReportIncomeExpensesVo.class);
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findPlatformList
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.model.report.dao.ICensusGeneralViewDao#findPlatformList(cn.adwalker.ad.admin.report.bean.CensusGeneralViewBean)
	 */
	private List<ReportIncomeExpensesVo> findPlatformList(
			CensusGeneralViewBean bean) throws Exception {
		List<Object> list = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer(
				"t_static_app_byday t left join T_APPLICATION a on t.app_id = a.id left join T_LOG_AD_CHANNEL_EFFECT l on t.static_date = l.static_date where 1 = 1 and l.type=0");

		if (ObjectUtils.isNotEmpty(bean.getAndroid_ios_type())) {
			sb.append("and a.os like '%");
			sb.append(bean.getAndroid_ios_type());
			sb.append("%'");
		}
		if (ObjectUtils.isNotEmpty(bean.getAndroid_ios_type())) {
			sb.append("and l.os like '%");
			sb.append(bean.getAndroid_ios_type());
			sb.append("%'");
		}

		if (ObjectUtils.isNotEmpty(bean.getStart_stat_date())) {
			sb.append(" and t.static_date>='");
			sb.append(bean.getStart_stat_date());
			sb.append("'");
		}
		if (ObjectUtils.isNotEmpty(bean.getEnd_stat_date())) {
			sb.append("and t.static_date<='");
			sb.append(bean.getEnd_stat_date());
			sb.append("'");
		}
		if (ObjectUtils.isNotEmpty(bean.getStart_stat_date())) {
			sb.append(" and t.static_date >='");
			sb.append(bean.getStart_stat_date());
			sb.append("'");
		}
		if (ObjectUtils.isNotEmpty(bean.getEnd_stat_date())) {
			sb.append("and t.static_date <='");
			sb.append(bean.getEnd_stat_date());
			sb.append("'");
		}
		if (ObjectUtils.isNotEmpty(bean.getMonth_stat_date())) {
			sb.append("and t.static_date like '%");
			sb.append(bean.getMonth_stat_date());
			sb.append("%'");
		}
		if (ObjectUtils.isNotEmpty(bean.getMonth_stat_date())) {
			sb.append("and t.static_date like '%");
			sb.append(bean.getMonth_stat_date());
			sb.append("%'");
		}

		sb.append("group by t.static_date,l.static_date");
		return (List<ReportIncomeExpensesVo>) censusGeneralViewDao
				.findAll(
						" t.static_date, sum(t.cost) as cost,sum(l.income_money) as income_money",
						sb.toString(), list.toArray(),
						" order by a.static_date desc ",
						ReportIncomeExpensesVo.class);
	}

	/**
	 * <p>
	 * Title: findGeneralViewAll
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-8-7
	 * @return List<AllCensusGeneralViewVo>
	 * @version 1.0
	 */
	@Transactional(propagation = Propagation.NEVER)
	private PlatformCensusOutcomeVo findGeneralViewAll(
			CensusGeneralViewBean bean) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select sum(v.SYS_COST) as sum_cost_price,sum(v.CONFIRM_NUM) as sum_income_amount,sum(v.CONFIRM_NUM*v.IN_PRICE) as sum_income_money  from  V_INCOME_OUTCOME v  LEFT JOIN V_MEDIA t on v.MEDIA_ID = t.ID where 1 = 1");
		// 日
		if (ObjectUtils.isNotEmpty(bean.getStart_stat_date())) {
			sb.append(" and v.static_date >='");
			sb.append(bean.getStart_stat_date());
			sb.append("'");
		}
		if (ObjectUtils.isNotEmpty(bean.getEnd_stat_date())) {
			sb.append("and v.static_date <='");
			sb.append(bean.getEnd_stat_date());
			sb.append("'");
		}
		// 周
		if (ObjectUtils.isNotEmpty(bean.getStart_date())) {
			sb.append(" and v.static_date >='");
			sb.append(bean.getStart_date());
			sb.append("'");
		}
		if (ObjectUtils.isNotEmpty(bean.getEnd_date())) {
			sb.append("and v.static_date <='");
			sb.append(bean.getEnd_date());
			sb.append("'");
		}
		// 月
		if (ObjectUtils.isNotEmpty(bean.getMonth_stat_date())) {
			sb.append("and v.static_date like '%");
			sb.append(bean.getMonth_stat_date());
			sb.append("%'");
		}
		List<PlatformCensusOutcomeVo> listOut = jdbcTemplate.query(sb
				.toString(), new BeanPropertyRowMapper(
				PlatformCensusOutcomeVo.class));
		if (listOut != null && listOut.size() > 0) {
			PlatformCensusOutcomeVo conf = listOut.get(0);
			return conf;
		} else {
			return null;
		}
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: flowlistOuticome
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.report.service.ICensusGeneralViewService#flowlistOuticome(cn.adwalker.ad.admin.report.bean.CensusGeneralViewBean)
	 */
	@Override
	public List<ReportAndroidIosAllOutincomeVo> flowlistOuticome(
			CensusGeneralViewBean bean) throws Exception {
		List<ReportAndroidIosAllOutincomeVo> list = null;
		list = censusGeneralViewDao.flowlistOuticome(bean);
		return list;
	}
}
