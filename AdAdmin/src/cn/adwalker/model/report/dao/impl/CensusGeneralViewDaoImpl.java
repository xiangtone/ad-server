package cn.adwalker.model.report.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import cn.adwalker.model.report.dao.ICensusGeneralViewDao;
import cn.adwalker.ad.admin.report.bean.CensusGeneralViewBean;
import cn.adwalker.ad.admin.report.vo.CensusGeneralViewAdvVo;
import cn.adwalker.ad.admin.report.vo.CensusGeneralViewAppVo;
import cn.adwalker.ad.admin.report.vo.ReportAndroidIosAllOutincomeVo;
import cn.adwalker.ad.admin.report.vo.ReportAndroidIosAllVo;
import cn.adwalker.ad.admin.report.vo.ReportIncomeExpensesVo;
import cn.adwalker.core.repository.impl.BaseDaoImpl;
import cn.adwalker.core.util.lang.ObjectUtils;

/**
 * <p>
 * Title: CensusGeneralViewDaoImpl
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-5-18
 */
@Repository("censusGeneralViewDao")
public class CensusGeneralViewDaoImpl extends BaseDaoImpl implements ICensusGeneralViewDao {
	
	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findGeneralViewAppList
	 * </p>
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.model.report.dao.ICensusGeneralViewDao#findGeneralViewAppList(cn.adwalker.ad.admin.report.bean.CensusGeneralViewBean)
	 */
	@Override
	public List<CensusGeneralViewAppVo> findGeneralViewAppList(CensusGeneralViewBean bean) throws Exception {
		List<Object> list = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer(" (select t.app_id, IFNULL(sum(t.cost),0) as cost,sum(t.pv) as cpm_cost,sum(t.download) as cpd_cost,sum(t.activate) as cpa_cost ,sum(t.pospv) as cpc_cost from t_static_app_byday t where 1=1");
		// 日
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
		// 周
		if (ObjectUtils.isNotEmpty(bean.getStart_date())) {
			sb.append(" and t.STATIC_DATE >='");
			sb.append(bean.getStart_date());
			sb.append("'");
		}
		if (ObjectUtils.isNotEmpty(bean.getEnd_date())) {
			sb.append("and t.static_date <='");
			sb.append(bean.getEnd_date());
			sb.append("'");
		}
		// 月
		if (ObjectUtils.isNotEmpty(bean.getMonth_stat_date())) {
			sb.append("and t.static_date like '%");
			sb.append(bean.getMonth_stat_date());
			sb.append("%'");
		}
		sb.append("group by t.app_id ) t left join t_application a on t.app_id=a.id");

		return (List<CensusGeneralViewAppVo>) this.findTop(" t.*,a.name as app_name", sb.toString(), list.toArray(), " order by t.cost desc ", CensusGeneralViewAppVo.class, 10);
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findGeneralsumCost
	 * </p>
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.model.report.dao.ICensusGeneralViewDao#findGeneralsumCost(cn.adwalker.ad.admin.report.bean.CensusGeneralViewBean)
	 */
	@Override
	public double findGeneralsumCost(CensusGeneralViewBean bean) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select sum(t.cost) as sum_cost from t_static_app_byday t where 1=1");
		// 日
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
		// 周
		if (ObjectUtils.isNotEmpty(bean.getStart_date())) {
			sb.append(" and t.STATIC_DATE >='");
			sb.append(bean.getStart_date());
			sb.append("'");
		}
		if (ObjectUtils.isNotEmpty(bean.getEnd_date())) {
			sb.append("and t.static_date <='");
			sb.append(bean.getEnd_date());
			sb.append("'");
		}
		// 月
		if (ObjectUtils.isNotEmpty(bean.getMonth_stat_date())) {
			sb.append("and t.static_date like '%");
			sb.append(bean.getMonth_stat_date());
			sb.append("%'");
		}
		return this.jdbcTemplate.queryForLong(sb.toString());
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findGeneralViewAdvList
	 * </p>
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.model.report.dao.ICensusGeneralViewDao#findGeneralViewAdvList(cn.adwalker.ad.admin.report.bean.CensusGeneralViewBean)
	 */
	@Override
	public List<CensusGeneralViewAdvVo> findGeneralViewAdvList(CensusGeneralViewBean bean) throws Exception {
		List<Object> list = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer(" V_INCOME_OUTCOME v  LEFT JOIN T_ADVERTISER adv ON v.adv_id = adv.id LEFT JOIN T_TYPE tp on v.type_id = tp.id where 1 = 1 ");
		// 日
		if (ObjectUtils.isNotEmpty(bean.getStart_stat_date())) {
			sb.append(" and v.STATIC_DATE >='");
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
			sb.append(" and v.STATIC_DATE >='");
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
		sb.append("group by  adv.company_name");
		return (List<CensusGeneralViewAdvVo>) findTop("  sum(v.CONFIRM_NUM) as income_amount,sum(v.CONFIRM_NUM * v.IN_PRICE) as income_money, adv.company_name", sb.toString(), list.toArray(), "order by sum(v.CONFIRM_NUM) desc ", CensusGeneralViewAdvVo.class, 10);
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findAdvsumCost
	 * </p>
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.model.report.dao.ICensusGeneralViewDao#findAdvsumCost(cn.adwalker.ad.admin.report.bean.CensusGeneralViewBean)
	 */
	@Override
	public double findAdvsumCost(CensusGeneralViewBean bean) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select sum(v.CONFIRM_NUM*v.IN_PRICE) from V_INCOME_OUTCOME v where 1=1");
		if (ObjectUtils.isNotEmpty(bean.getStart_stat_date())) {
			sb.append(" and v.STATIC_DATE >='");
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
			sb.append(" and v.STATIC_DATE >='");
			sb.append(bean.getStart_date());
			sb.append("'");
		}
		if (ObjectUtils.isNotEmpty(bean.getEnd_date())) {
			sb.append("and v.static_date <='");
			sb.append(bean.getEnd_date());
			sb.append("'");
		}
		if (ObjectUtils.isNotEmpty(bean.getMonth_stat_date())) {
			sb.append("and v.static_date like '%");
			sb.append(bean.getMonth_stat_date());
			sb.append("%'");
		}
		return this.jdbcTemplate.queryForLong(sb.toString());
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: flowlist
	 * </p>
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.model.report.dao.ICensusGeneralViewDao#flowlist(cn.adwalker.ad.admin.report.bean.CensusGeneralViewBean)
	 */
	@Override
	public List<ReportAndroidIosAllVo> flowlist(CensusGeneralViewBean bean) throws Exception {
		List<Object> list = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer("t_static_app_byday t left join T_APPLICATION l on t.app_id = l.id where 1 = 1");
		if (ObjectUtils.isNotEmpty(bean.getOs())) {
			sb.append("and  l.os like '%");
			sb.append(bean.getOs());
			sb.append("%'");
		}
		// 日
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
		// 周
		if (ObjectUtils.isNotEmpty(bean.getStart_date())) {
			sb.append(" and t.static_date>='");
			sb.append(bean.getStart_date());
			sb.append("'");
		}
		if (ObjectUtils.isNotEmpty(bean.getEnd_date())) {
			sb.append("and t.static_date<='");
			sb.append(bean.getEnd_date());
			sb.append("'");
		}
		// 月
		if (ObjectUtils.isNotEmpty(bean.getMonth_stat_date())) {
			sb.append("and t.static_date like '%");
			sb.append(bean.getMonth_stat_date());
			sb.append("%'");
		}
		sb.append("group by t.static_date");
		return (List<ReportAndroidIosAllVo>) this.findAll("t.static_date, sum(t.pv) as  pv, sum( t.click) as click ", sb.toString(), list.toArray(), " order by t.static_date desc ", ReportAndroidIosAllVo.class);
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findAllList
	 * </p>
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.model.report.dao.ICensusGeneralViewDao#findAllList(cn.adwalker.ad.admin.report.bean.CensusGeneralViewBean)
	 */

	@Override
	public List<ReportIncomeExpensesVo> findAllList(CensusGeneralViewBean bean) throws Exception {
		List<Object> list = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer("V_INCOME_OUTCOME v  LEFT JOIN v_media m on v.MEDIA_ID =m.ID where 1=1");
		if (ObjectUtils.isNotEmpty(bean.getAndroid_ios_type())) {
			sb.append("and v.os like '%");
			sb.append(bean.getAndroid_ios_type());
			sb.append("%'");
		}

		if (ObjectUtils.isNotEmpty(bean.getPlatform_channel_type())) {
			sb.append(" and m.TYPE=");
			sb.append(bean.getPlatform_channel_type());
		}
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
		sb.append(" group by v.STATIC_DATE");
		return (List<ReportIncomeExpensesVo>) findAll("v.static_date,sum(v.SYS_COST) as cost ,sum(v.IN_PRICE*v.CONFIRM_NUM) as income_money ", sb.toString(), list.toArray(), " order by v.static_date desc ", ReportIncomeExpensesVo.class);
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: flowlistOuticome
	 * </p>
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.model.report.dao.ICensusGeneralViewDao#flowlistOuticome(cn.adwalker.ad.admin.report.bean.CensusGeneralViewBean)
	 */
	@Override
	public List<ReportAndroidIosAllOutincomeVo> flowlistOuticome(CensusGeneralViewBean bean) throws Exception {
		List<Object> list = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer("V_INCOME_OUTCOME v where 1 = 1");
		if (ObjectUtils.isNotEmpty(bean.getOs())) {
			sb.append("and v.os like '%");
			sb.append(bean.getOs());
			sb.append("%'");
		}
		// 日
		if (ObjectUtils.isNotEmpty(bean.getStart_stat_date())) {
			sb.append(" and v.static_date>='");
			sb.append(bean.getStart_stat_date());
			sb.append("'");
		}
		if (ObjectUtils.isNotEmpty(bean.getEnd_stat_date())) {
			sb.append("and v.static_date<='");
			sb.append(bean.getEnd_stat_date());
			sb.append("'");
		}
		// 周
		if (ObjectUtils.isNotEmpty(bean.getStart_date())) {
			sb.append(" and v.static_date>='");
			sb.append(bean.getStart_date());
			sb.append("'");
		}
		if (ObjectUtils.isNotEmpty(bean.getEnd_date())) {
			sb.append("and v.static_date<='");
			sb.append(bean.getEnd_date());
			sb.append("'");
		}
		// 月
		if (ObjectUtils.isNotEmpty(bean.getMonth_stat_date())) {
			sb.append("and v.static_date like '%");
			sb.append(bean.getMonth_stat_date());
			sb.append("%'");
		}
		sb.append("group by v.static_date");
		return (List<ReportAndroidIosAllOutincomeVo>) this.findAll(" v.static_date, sum(v.CONFIRM_NUM) as sum_income_amount", sb.toString(), list.toArray(), " order by v.static_date desc ", ReportAndroidIosAllOutincomeVo.class);
	}

}