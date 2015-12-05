package cn.adwalker.ad.admin.report.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.adwalker.ad.admin.report.bean.AdByHourQuery;
import cn.adwalker.ad.admin.report.service.IReportAdByHourService;
import cn.adwalker.ad.admin.report.vo.ReportAdByHour;
import cn.adwalker.core.log.Log;
import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.repository.impl.BaseServiceImpl;
import cn.adwalker.model.report.dao.IReportDao;

/**
 * 
 * <p>
 * Title: ReportAdByHourServiceImpl
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-8-15
 */
@Service
public class ReportAdByHourServiceImpl extends BaseServiceImpl implements
		IReportAdByHourService {

	@Resource
	private IReportDao reportDao;

	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.NEVER)
	@Override
	@Log
	public List<ReportAdByHour> findByPage(AdByHourQuery bean,
			IPageInfo pageInfo) throws Exception {
		List<ReportAdByHour> resultList = null;
		String temp_table = "";
		StringBuilder sb = new StringBuilder(" where t.id IS NOT NULL");
		List<Object> param = new ArrayList<Object>();
		if (bean != null) {
			param.add(bean.getBeginTime());
			param.add(bean.getEndTime());
			if (!StringUtils.isEmpty(bean.getId())) {
				sb.append(" and  t.id=?");
				param.add(bean.getId());
			}
			if (!StringUtils.isEmpty(bean.getPlacement_name())) {
				sb.append(" and upper(t.ad_name) like ?  ");
				param.add("%" + bean.getPlacement_name().trim().toUpperCase()
						+ "%");
			}

			if (!StringUtils.isEmpty(bean.getAdv_id())) {
				sb.append(" and t.adv_id = ?");
				param.add(bean.getAdv_id().trim());
			}
			if (!StringUtils.isEmpty(bean.getStatic_hour())) {
				sb.append(" and a.static_hour=?");
				param.add(bean.getStatic_hour());
			}
			if (bean.getType_id() != null) {
				sb.append(" and a.type_id=?");
				param.add(bean.getType_id());
			}
			if (!StringUtils.isEmpty(bean.getOs())) {
				sb.append(" and t.os=?");
				param.add(bean.getOs());
			}
		}

		String table = "(select static_date,static_hour,ad_id,type_id,sum(pv) as adpv,sum(click) as click,sum(download) as download,sum(activate) as activate,sum(cost) as cost from t_static_ad_byhour where static_date BETWEEN ? and ? group by ad_id, type_id,static_date,static_hour)a left join t_ad  t on t.id = a.ad_id left join t_type f on f.id=a.type_id "
				+ temp_table;
		resultList = (List<ReportAdByHour>) reportDao
				.findByPage(
						"a.static_date,a.static_hour,0 as adv_id,t.id,t.ad_name,f.name as fname,t.os,IFNULL(a.adpv, 0) as adpv,IFNULL(a.click, 0) as click,IFNULL(a.download, 0) as download,IFNULL(a.activate, 0) as activate,IFNULL(round(a.cost,4), 0) as cost,IFNULL(round(a.click*100 / a.adpv,4), 0) as ctrc,IFNULL(round(a.download*100 / a.click,4), 0) as ctrd,IFNULL(round(a.activate*100 / a.download,4), 0) as ctra",
						table + sb.toString(), param.toArray(),
						"order by static_hour", ReportAdByHour.class, pageInfo);

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
	 * @see cn.adwalker.ad.admin.report.service.IReportAdByHourService#findAll(cn.adwalker.ad.admin.report.bean.AdByHourQuery)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> findAll(AdByHourQuery bean) throws Exception {
		List<Object> resultList = null;
		String temp_table = "";
		StringBuilder sb = new StringBuilder(" where t.id IS NOT NULL");
		List<Object> param = new ArrayList<Object>();
		if (bean != null) {
			param.add(bean.getBeginTime());
			param.add(bean.getEndTime());
			if (!StringUtils.isEmpty(bean.getId())) {
				sb.append(" and  a.ad_id=?");
				param.add(bean.getId());
			}
			if (!StringUtils.isEmpty(bean.getPlacement_name())) {
				sb.append(" and upper(t.ad_name) like ?  ");
				param.add("%" + bean.getPlacement_name().trim().toUpperCase()
						+ "%");
			}

			if (!StringUtils.isEmpty(bean.getAdv_id())) {
				sb.append(" and t.adv_id = ?");
				param.add(bean.getAdv_id().trim());
			}
			if (!StringUtils.isEmpty(bean.getStatic_hour())) {
				sb.append(" and a.static_hour=?");
				param.add(bean.getStatic_hour());
			}
			if (bean.getType_id() != null) {
				sb.append(" and a.type_id=?");
				param.add(bean.getType_id());
			}
			 
			if (!StringUtils.isEmpty(bean.getOs())) {
				sb.append(" and os=?");
				param.add(bean.getOs());
			}
		}

		String table = "(select static_date,static_hour,ad_id,type_id,sum(pv) as adpv,sum(click) as click,sum(download) as download,sum(activate) as activate,sum(cost) as cost from t_static_ad_byhour where static_date BETWEEN ? and ? group by ad_id, type_id,static_date,static_hour)a left join t_ad  t on t.id = a.ad_id left join t_type f on f.id=a.type_id "
				+ temp_table;

		resultList = (List<Object>) reportDao
				.findAll(
						"a.static_date,a.static_hour,0 as adv_id,t.id,t.ad_name,f.name as fname,t.os,IFNULL(a.adpv, 0) as adpv,IFNULL(a.click, 0) as click,IFNULL(a.download, 0) as download,IFNULL(a.activate, 0) as activate,IFNULL(round(a.cost,4), 0) as cost,IFNULL(round(a.click*100 / a.adpv,4), 0) as ctrc,IFNULL(round(a.download*100 / a.click,4), 0) as ctrd,IFNULL(round(a.activate*100 / a.download,4), 0) as ctra",
						table + sb.toString(), param.toArray(),
						" order by static_hour", ReportAdByHour.class);

		return resultList;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findTotal
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.report.service.IReportAdByHourService#findTotal(cn.adwalker.ad.admin.report.bean.AdByHourQuery)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ReportAdByHour findTotal(AdByHourQuery bean) throws Exception {
		ReportAdByHour total = null;
		String temp_table = "";
		StringBuilder sb = new StringBuilder(" where t.id IS NOT NULL");
		List<Object> param = new ArrayList<Object>();
		if (bean != null) {
			param.add(bean.getBeginTime());
			param.add(bean.getEndTime());
			if (!StringUtils.isEmpty(bean.getId())) {
				sb.append(" and  a.ad_id=?");
				param.add(bean.getId());
			}
			if (!StringUtils.isEmpty(bean.getPlacement_name())) {
				sb.append(" and upper(t.ad_name) like ?  ");
				param.add("%" + bean.getPlacement_name().trim().toUpperCase()
						+ "%");
			}

			if (!StringUtils.isEmpty(bean.getAdv_id())) {
				sb.append(" and t.adv_id = ?");
				param.add(bean.getAdv_id().trim());
			}
			if (!StringUtils.isEmpty(bean.getStatic_hour())) {
				sb.append(" and a.static_hour=?");
				param.add(bean.getStatic_hour());
			}

			if (bean.getType_id() != null) {
				sb.append(" and a.type_id=?");
				param.add(bean.getType_id());
			}
			/*
			 * if (bean.getArea_type() != null && bean.getArea_type() != -0) {
			 * temp_table =
			 * "left join V_CAMPAIGN_SALESMAN sel on t.CAMPAIGN_id=sel.CAMPAIGN_id "
			 * ; sb.append(" and sel.area_type=?");
			 * param.add(bean.getArea_type()); }
			 */
			if (!StringUtils.isEmpty(bean.getOs())) {
				sb.append(" and os=?");
				param.add(bean.getOs());
			}
		}

		String table = "(select static_date,static_hour,ad_id,type_id,sum(pv) as adpv,sum(click) as click,sum(download) as download,sum(activate) as activate,sum(cost) as cost from t_static_ad_byhour where static_date BETWEEN ? and ? group by ad_id, type_id,static_date,static_hour)a left join t_ad  t on t.id = a.ad_id left join t_type f on f.id=a.type_id "
				+ temp_table;

		List<ReportAdByHour> list = reportDao
				.findAll(
						"IFNULL(sum(a.adpv), 0) as adpv,IFNULL(sum(a.click), 0) as click,IFNULL(sum(a.download), 0) as download,IFNULL(sum(a.activate), 0) as activate,IFNULL(round(sum(a.cost),4), 0) as cost,IFNULL(round(sum(a.click)*100 / sum(a.adpv),4), 0) as ctrc,IFNULL(round(sum(a.download)*100 / sum(a.click),4), 0) as ctrd,IFNULL(round(sum(a.activate)*100 / sum(a.download),4), 0) as ctra",
						table + sb.toString(), param.toArray(),
						" order by static_hour", ReportAdByHour.class);
		if (list != null && list.size() > 0) {
			total = list.get(0);

		}
		return total;
	}
}