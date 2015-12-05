package cn.adwalker.ad.admin.report.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.adwalker.ad.admin.report.bean.AdByDayQuery;
import cn.adwalker.ad.admin.report.bean.ResByDayQuery;
import cn.adwalker.ad.admin.report.service.IReportResByDayService;
import cn.adwalker.ad.admin.report.vo.ReportAdByDay;
import cn.adwalker.ad.admin.report.vo.ReportResByDay;
import cn.adwalker.core.log.Log;
import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.repository.impl.BaseServiceImpl;
import cn.adwalker.core.util.lang.ObjectUtils;
import cn.adwalker.model.report.dao.IReportDao;

/**
* <p>Title: ReportResByDayServiceImpl</p>
* <p>Description:Res数据统计O</p>
* <p>Company: adwalker</p> 
* @author    lichuang
* @date       2014年10月17日
 */
@Service
public class ReportResByDayServiceImpl extends BaseServiceImpl implements IReportResByDayService {

	@Resource
	private IReportDao reportDao;

	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.NEVER)
	@Override
	@Log
	public List<ReportResByDay> findByPage(ResByDayQuery bean,
			IPageInfo pageInfo)throws Exception {
		List<ReportResByDay> resultList = null;
		String temp_table = "";
		StringBuilder sb = new StringBuilder(
				" where d.os='ios' and  static_date BETWEEN ? and ? ");
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

			if (ObjectUtils.isNotEmpty(bean.getRes_type())) {
				sb.append(" and res_type = ?");
				param.add(bean.getRes_type());
			}
			
			if (bean.getType_id() != null) {
				sb.append(" and a.type_id=?");
				param.add(bean.getType_id());
			}
		}
		resultList = (List<ReportResByDay>) reportDao
				.findByPage(
						"a.static_date,d.res_type,a.ad_id as id,a.ad_name as placement_name,a.type_name as fname,IFNULL(a.pv, 0) as adpv,IFNULL(a.click, 0) as click,IFNULL(a.download, 0) as download,IFNULL(a.activate, 0) as activate,IFNULL(round(a.cost,4), 0) as cost,IFNULL(round(a.click*100 / a.pv,4), 0) as ctrc,IFNULL(round(a.download*100 / a.click,4), 0) as ctrd,IFNULL(round(a.activate*100 / a.download,4), 0) as ctra",
						"t_static_ad_byday a left join t_ad d on a.ad_id=d.id " + temp_table + sb.toString(),
						param.toArray(), "order by static_date",
						ReportResByDay.class, pageInfo);

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
	public List<Object> findAll(ResByDayQuery bean) throws Exception {
		List<Object> resultList = null;
		String temp_table = "";
		StringBuilder sb = new StringBuilder(
				" where d.os='ios' and static_date BETWEEN ? and ? ");
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

			if (ObjectUtils.isNotEmpty(bean.getRes_type())) {
				sb.append(" and res_type = ?");
				param.add(bean.getRes_type());
			}
			if (bean.getType_id() != null) {
				sb.append(" and a.type_id=?");
				param.add(bean.getType_id());
			}
			
		}

		String table = "t_static_ad_byday a left join t_ad d on a.ad_id=d.id " + temp_table;

		resultList = (List<Object>) reportDao
				.findAll(
						"a.static_date,d.res_type,a.ad_id as id,a.ad_name as placement_name,a.type_name as fname,IFNULL(a.pv, 0) as adpv,IFNULL(a.click, 0) as click,IFNULL(a.download, 0) as download,IFNULL(a.activate, 0) as activate,IFNULL(round(a.cost,4), 0) as cost,IFNULL(round(a.click*100 / a.pv,4), 0) as ctrc,IFNULL(round(a.download*100 / a.click,4), 0) as ctrd,IFNULL(round(a.activate*100 / a.download,4), 0) as ctra",
						table + sb.toString(), param.toArray(),
						" order by static_date", ReportResByDay.class);

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
	public ReportResByDay findTotal(ResByDayQuery bean) throws Exception {
		ReportResByDay total = null;
		String temp_table = "";
		StringBuilder sb = new StringBuilder(
				" where d.os='ios' and  static_date BETWEEN ? and ? ");
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


			if (ObjectUtils.isNotEmpty(bean.getRes_type())) {
				sb.append(" and res_type = ?");
				param.add(bean.getRes_type());
			}
			if (bean.getType_id() != null) {
				sb.append(" and a.type_id=?");
				param.add(bean.getType_id());
			}
		}
		List<ReportResByDay> list = reportDao
				.findAll(
						"IFNULL(sum(a.pv), 0) as adpv,IFNULL(sum(a.click), 0) as click,IFNULL(sum(a.download), 0) as download,IFNULL(sum(a.activate), 0) as activate,IFNULL(round(sum(a.cost),4), 0) as cost,IFNULL(round(sum(a.click)*100 / sum(a.pv),4), 0) as ctrc,IFNULL(round(sum(a.download)*100 / sum(a.click),4), 0) as ctrd,IFNULL(round(sum(a.activate)*100 / sum(a.download),4), 0) as ctra",
						"t_static_ad_byday a left join t_ad d on a.ad_id=d.id" + temp_table + sb.toString(),
						param.toArray(), " order by static_date",
						ReportResByDay.class);
		if (list != null && list.size() > 0) {
			total = list.get(0);

		}
		return total;
	}
}