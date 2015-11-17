package cn.adwalker.ad.admin.report.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.adwalker.core.log.Log;
import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.repository.impl.BaseServiceImpl;
import cn.adwalker.model.report.dao.IReportDao;
import cn.adwalker.ad.admin.report.bean.AdByDayQuery;
import cn.adwalker.ad.admin.report.service.IReportAdByDayService;
import cn.adwalker.ad.admin.report.vo.ReportAdByDay;

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
public class ReportAdByDayServiceImpl extends BaseServiceImpl implements
		IReportAdByDayService {

	@Resource
	private IReportDao reportDao;

	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.NEVER)
	@Override
	@Log
	public List<ReportAdByDay> findByPage(IPageInfo pageInfo, AdByDayQuery bean)
			throws Exception {
		List<ReportAdByDay> resultList = null;
		String temp_table = "";
		StringBuilder sb = new StringBuilder(
				" where static_date BETWEEN ? and ? ");
		List<Object> param = new ArrayList<Object>();
		if (bean != null) {
			param.add(bean.getBeginTime());
			param.add(bean.getEndTime());
			if (!StringUtils.isEmpty(bean.getId())) {
				sb.append(" and  a.ad_id=?");
				param.add(bean.getId());
			}
			if (!StringUtils.isEmpty(bean.getPlacement_name())) {
				sb.append(" and upper(a.ad_name) like ?  ");
				param.add("%" + bean.getPlacement_name().trim().toUpperCase()
						+ "%");
			}

			if (!StringUtils.isEmpty(bean.getAdv_id())) {
				sb.append(" and a.adv_id = ?");
				param.add(bean.getAdv_id().trim());
			}
			if (bean.getType_id() != null) {
				sb.append(" and a.type_id=?");
				param.add(bean.getType_id());
			}
			if (bean.getArea_type() != null && bean.getArea_type() != 0) {
				temp_table = "left join V_CAMPAIGN_SALESMAN sel on a.CAMPAIGN_id=sel.CAMPAIGN_id ";
				sb.append(" and sel.area_type=?");
				param.add(bean.getArea_type());
			}
			if (!StringUtils.isEmpty(bean.getOs())) {
				sb.append(" and os=?");
				param.add(bean.getOs());
			}
		}
		resultList = (List<ReportAdByDay>) reportDao
				.findByPage(
						"a.static_date,a.adv_id,a.ad_id as id,a.ad_name as placement_name,a.type_name as fname,a.os,IFNULL(a.pv, 0) as adpv,IFNULL(a.click, 0) as click,IFNULL(a.download, 0) as download,IFNULL(a.activate, 0) as activate,IFNULL(round(a.cost,4), 0) as cost,IFNULL(round(a.click*100 / a.pv,4), 0) as ctrc,IFNULL(round(a.download*100 / a.click,4), 0) as ctrd,IFNULL(round(a.activate*100 / a.download,4), 0) as ctra",
						"t_report_ad_byday a " + temp_table + sb.toString(),
						param.toArray(), "order by static_date",
						ReportAdByDay.class, pageInfo);

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
	 * @see cn.adwalker.ad.admin.report.service.IReportAdByHourService#findAll(cn.adwalker.admin.report.bean.AdByHourQuery)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> findAll(AdByDayQuery bean) throws Exception {
		List<Object> resultList = null;
		String temp_table = "";
		StringBuilder sb = new StringBuilder(
				" where static_date BETWEEN ? and ? ");
		List<Object> param = new ArrayList<Object>();
		if (bean != null) {
			param.add(bean.getBeginTime());
			param.add(bean.getEndTime());
			if (!StringUtils.isEmpty(bean.getId())) {
				sb.append(" and  ad_id=?");
				param.add(bean.getId());
			}
			if (!StringUtils.isEmpty(bean.getPlacement_name())) {
				sb.append(" and upper(a.ad_name) like ?  ");
				param.add("%" + bean.getPlacement_name().trim().toUpperCase()
						+ "%");
			}

			if (!StringUtils.isEmpty(bean.getAdv_id())) {
				sb.append(" and a.adv_id = ?");
				param.add(bean.getAdv_id().trim());
			}
			if (bean.getType_id() != null) {
				sb.append(" and a.type_id=?");
				param.add(bean.getType_id());
			}
			if (bean.getArea_type() != null && bean.getArea_type() != 0) {
				temp_table = "left join V_CAMPAIGN_SALESMAN sel on a.CAMPAIGN_id=sel.CAMPAIGN_id ";
				sb.append(" and sel.area_type=?");
				param.add(bean.getArea_type());
			}
			if (!StringUtils.isEmpty(bean.getOs())) {
				sb.append(" and os=?");
				param.add(bean.getOs());
			}
		}

		String table = "t_report_ad_byday a " + temp_table;

		resultList = (List<Object>) reportDao
				.findAll(
						"a.static_date,a.adv_id,a.ad_id as id,a.ad_name as placement_name,a.type_name as fname,a.os,IFNULL(a.pv, 0) as adpv,IFNULL(a.click, 0) as click,IFNULL(a.download, 0) as download,IFNULL(a.activate, 0) as activate,IFNULL(round(a.cost,4), 0) as cost,IFNULL(round(a.click*100 / a.pv,4), 0) as ctrc,IFNULL(round(a.download*100 / a.click,4), 0) as ctrd,IFNULL(round(a.activate*100 / a.download,4), 0) as ctra",
						table + sb.toString(), param.toArray(),
						" order by static_date", ReportAdByDay.class);

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
	 * @see cn.adwalker.ad.admin.report.service.IReportAdByHourService#findTotal(cn.adwalker.admin.report.bean.AdByHourQuery)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ReportAdByDay findTotal(AdByDayQuery bean) throws Exception {
		ReportAdByDay total = null;
		String temp_table = "";
		StringBuilder sb = new StringBuilder(
				" where static_date BETWEEN ? and ? ");
		List<Object> param = new ArrayList<Object>();
		if (bean != null) {
			param.add(bean.getBeginTime());
			param.add(bean.getEndTime());
			if (!StringUtils.isEmpty(bean.getId())) {
				sb.append(" and  ad_id=?");
				param.add(bean.getId());
			}
			if (!StringUtils.isEmpty(bean.getPlacement_name())) {
				sb.append(" and upper( a.ad_name) like ?  ");
				param.add("%" + bean.getPlacement_name().trim().toUpperCase()
						+ "%");
			}

			if (!StringUtils.isEmpty(bean.getAdv_id())) {
				sb.append(" and a.adv_id = ?");
				param.add(bean.getAdv_id().trim());
			}
			if (bean.getType_id() != null) {
				sb.append(" and a.type_id=?");
				param.add(bean.getType_id());
			}
			if (bean.getArea_type() != null && bean.getArea_type() != -0) {
				temp_table = "left join V_CAMPAIGN_SALESMAN sel on a.CAMPAIGN_id=sel.CAMPAIGN_id ";
				sb.append(" and sel.area_type=?");
				param.add(bean.getArea_type());
			}
			if (!StringUtils.isEmpty(bean.getOs())) {
				sb.append(" and os=?");
				param.add(bean.getOs());
			}
		}
		List<ReportAdByDay> list = reportDao
				.findAll(
						"IFNULL(sum(a.pv), 0) as adpv,IFNULL(sum(a.click), 0) as click,IFNULL(sum(a.download), 0) as download,IFNULL(sum(a.activate), 0) as activate,IFNULL(round(sum(a.cost),4), 0) as cost,IFNULL(round(sum(a.click)*100 / sum(a.pv),4), 0) as ctrc,IFNULL(round(sum(a.download)*100 / sum(a.click),4), 0) as ctrd,IFNULL(round(sum(a.activate)*100 / sum(a.download),4), 0) as ctra",
						"t_report_ad_byday a " + temp_table + sb.toString(),
						param.toArray(), " order by static_date",
						ReportAdByDay.class);
		if (list != null && list.size() > 0) {
			total = list.get(0);

		}
		return total;
	}
}