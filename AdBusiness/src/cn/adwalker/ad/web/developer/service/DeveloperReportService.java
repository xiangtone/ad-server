package cn.adwalker.ad.web.developer.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import cn.adwalker.ad.core.page.IPageInfo;
import cn.adwalker.ad.model.developer.dao.DeveloperReportDao;
import cn.adwalker.ad.util.DateUtil;
import cn.adwalker.ad.util.ObjectUtils;
import cn.adwalker.ad.web.developer.bean.DevImcome;
import cn.adwalker.ad.web.developer.bean.DevImcomeQueryBean;
import cn.adwalker.ad.web.developer.bean.DevReportbean;
import cn.adwalker.ad.web.developer.vo.DevActualTimeReportVo;
import cn.adwalker.ad.web.developer.vo.DevIncome;
import cn.adwalker.ad.web.developer.vo.DevIncomeListVo;
import cn.adwalker.ad.web.developer.vo.DevIncomeReport;
import cn.adwalker.ad.web.developer.vo.DevIncomeReportTemp;

@Service("developerReportService")
public class DeveloperReportService {

	@Resource
	private DeveloperReportDao developerReportDao;

	@SuppressWarnings("unchecked")
	public List<DevIncomeReport> findDevIncome(Long id, DevImcome bean, IPageInfo pageInfo) throws Exception {
		List<DevIncomeReport> list = null;
		StringBuilder sb = new StringBuilder("where app.dev_id=? ");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(id);
		if (bean != null) {
			if (bean.getType_id() != null) {
				sb.append(" and stat.type_id=?");
				paramList.add(bean.getType_id());
			}
			if (!StringUtils.isEmpty(bean.getStart_date())) {
				sb.append(" and stat.STATIC_DATE>=?");
				paramList.add(bean.getStart_date());
			}
			if (!StringUtils.isEmpty(bean.getEnd_date())) {
				sb.append(" and stat.STATIC_DATE<=?");
				paramList.add(bean.getEnd_date());
			}
			if (bean.getApp_id() != null) {
				sb.append(" and stat.app_id=?");
				paramList.add(bean.getApp_id());
			}
			if (!StringUtils.isEmpty(bean.getOs())) {
				sb.append(" and app.os=?");
				paramList.add(bean.getOs());
			}
		}
		List<DevIncomeReportTemp> tempList = (List<DevIncomeReportTemp>) developerReportDao.findByPage(" app.name as app_name,app.os as app_os,rep.app_id,rep.STATIC_DATE,IFNULL(rep.pospv,0),rep.click,rep.cost,rep.effect,type.name type_name", "(select " + "stat.app_id," + "stat.type_id," + "stat.static_date," + "sum(click) click," + "sum(cost)cost," + "sum(pospv)pospv," + "sum(" + "case when app.os = 'ios' then (case stat.type_id when 1 then click when 0 then ACTIVATE when 4 then click when 5 then  pv end)" + "when app.os = 'android' then(case stat.type_id when 0 then ACTIVATE when 1 then download when 2 then download when 4 then click when 5 then pv end)end)effect " + "from T_STATIC_APP_BYDAY stat left join t_application app on stat.app_id = app.id " + sb.toString()
				+ " group by stat.app_id, app.os, stat.type_id, stat.static_date)rep " + "left join t_application app  on app_id = app.id  left join T_DEVELOPER dev on app.dev_id=dev.id " + "left join t_type type on rep.type_id=type.id ", paramList.toArray(), "order by rep.STATIC_DATE asc", DevIncomeReportTemp.class, pageInfo);
		list = getDevIncomes(tempList);
		return list;
	}

	private List<DevIncomeReport> getDevIncomes(List<DevIncomeReportTemp> temps) {
		List<DevIncomeReport> list = null;
		if (temps != null) {
			Map<String, DevIncomeReport> map = new TreeMap<String, DevIncomeReport>();
			for (DevIncomeReportTemp temp : temps) {
				DevIncomeReport income = map.get(temp.getKey());
				if (income == null) {
					income = new DevIncomeReport();
				}
				if (income.getList() == null) {
					income.setList((new ArrayList<DevIncomeReportTemp>()));
				}
				if (income.getMap() == null) {
					income.setMap((new HashMap<String, Integer>()));
				}
				if (income.getMap().get(temp.getStatic_date()) == null) {
					income.getMap().put(temp.getStatic_date(), 0);
					temp.setIndex(0);
				} else {
					temp.setIndex(1);
				}
				income.getMap().put(temp.getStatic_date(), income.getMap().get(temp.getStatic_date()) + 1);
				income.getList().add(temp);
				map.put(temp.getKey(), income);
			}
			if (map != null && !map.isEmpty()) {
				list = new ArrayList<DevIncomeReport>();
				for (String key : map.keySet()) {
					list.add(map.get(key));
				}
			}
		}
		return list;
	}

	public DevIncomeReportTemp getSumDevIncomeReportTemp(Long id, DevImcome bean) {
		StringBuilder sb = new StringBuilder(" where app.dev_id=");
		sb.append(id);
		if (bean != null) {
			if (bean.getType_id() != null) {
				sb.append(" and stat.type_id=").append(bean.getType_id());
			}
			if (!StringUtils.isEmpty(bean.getStart_date())) {
				sb.append(" and stat.STATIC_DATE>='").append(bean.getStart_date()).append("'");
			}
			if (!StringUtils.isEmpty(bean.getEnd_date())) {
				sb.append(" and stat.STATIC_DATE<='").append(bean.getEnd_date()).append("'");
			}
			if (bean.getApp_id() != null) {
				sb.append(" and stat.app_id=").append(bean.getApp_id());
			}
			if (!StringUtils.isEmpty(bean.getOs())) {
				sb.append(" and app.os='").append(bean.getOs()).append("'");
			}
		}
		return developerReportDao.getSumDevIncomeReportTemp(sb.toString());
	}

	@SuppressWarnings("unchecked")
	public DevIncome findDevIncome(Long id, Date date) throws Exception {
		DevIncome income = null;
		StringBuilder sql = new StringBuilder("(select app_id,sum(CASE type_id WHEN 0 THEN effect ELSE null END) as score_wall_num,sum(CASE type_id WHEN 0 THEN cost ELSE null END) as score_wall_cost,sum(CASE type_id WHEN 1 THEN effect ELSE null END) as page_wall_num,sum(CASE type_id WHEN 1 THEN cost ELSE null END) as page_wall_cost,");
		sql.append("sum(CASE type_id WHEN 4 THEN effect ELSE null END) as banner_num,sum(CASE type_id WHEN 4 THEN cost ELSE null END) as banner_cost,sum(CASE type_id WHEN 5 THEN effect ELSE null END) as chaping_num,sum(CASE type_id WHEN 5 THEN cost ELSE null END) as chaping_cost from ");
		sql.append("(select app_id,type_id, sum(case when app.os = 'ios' then (case stat.type_id  when 1 then click when 2 then click when 4 then click when 5 then pv end) when app.os = 'android' then (case stat.type_id when 0 then ACTIVATE when 1 then download when 2 then download when 4 then click when 5 then  pv  end) end) effect, sum(cost) cost ");
		sql.append("from (select APP_ID,STATIC_DATE,TYPE_ID,sum(CLICK) CLICK,sum(POSPV) POSPV,sum(PV) PV,sum(DOWNLOAD) DOWNLOAD,sum(ACTIVATE) ACTIVATE,sum(COST) COST from T_STATIC_APP_BYHOUR s left join T_APPLICATION a on s.app_id = a.id  where a.dev_id=? and static_date=? group by app_id, static_date, type_id) stat,T_APPLICATION app ");
		sql.append("where  stat.app_id=app.id group by app_id, type_id) t1 where effect > 0  group by app_id) t2");
		List<DevIncome> list = (List<DevIncome>) developerReportDao.findAll("IFNULL(sum(score_wall_num),0) as score_wall_num,IFNULL(sum(score_wall_cost),0) as score_wall_cost,IFNULL(sum(page_wall_num),0) as page_wall_num,IFNULL(sum(page_wall_cost),0) as page_wall_cost,IFNULL(sum(banner_num),0) as banner_num,IFNULL(sum(banner_cost),0) as banner_cost,IFNULL(sum(chaping_num),0) as CHARTBOOST_num,IFNULL(sum(chaping_cost),0) as CHARTBOOST_cost", 
				sql.toString(), new Object[] { id, DateUtil.format(date) }, DevIncome.class);
		if (list != null && list.size() > 0) {
			income = list.get(0);
		}
		return income;
	}

	@SuppressWarnings("unchecked")
	public List<DevIncomeListVo> findDevIncome(Long id, DevImcomeQueryBean bean, IPageInfo pageInfo) throws Exception {
		StringBuilder sb = new StringBuilder();
		if (!StringUtils.isEmpty(bean.getAppIdOrName())) {
			sb.append(" and (");
			if (bean.getApp_id() != null) {
				sb.append(" s.app_id=" + bean.getApp_id() + " ");
			}
			if (!StringUtils.isEmpty(bean.getName())) {
				sb.append(" or app.name like'%" + bean.getName() + "%' ");
			}
			sb.append(")");
		}
		StringBuilder sql = new StringBuilder("(select app_id,sum(CASE type_id WHEN 0 THEN effect ELSE null END) as score_wall_num,sum(CASE type_id WHEN 0 THEN cost ELSE null END) as score_wall_cost,sum(CASE type_id WHEN 1 THEN effect ELSE null END) as page_wall_num,sum(CASE type_id WHEN 1 THEN cost ELSE null END) as page_wall_cost,");
		sql.append("sum(CASE type_id WHEN 4 THEN effect ELSE null END) as banner_num,sum(CASE type_id WHEN 4 THEN cost ELSE null END) as banner_cost,sum(CASE type_id WHEN 5 THEN effect ELSE null END) as chaping_num,sum(CASE type_id WHEN 5 THEN cost ELSE null END) as chaping_cost from (");
		sql.append("select app_id,type_id,sum(case when app.os = 'ios' then (case stat.type_id  when 1 then click when 0 then ACTIVATE when 4 then click when 5 then pv end) when app.os = 'android' then (case stat.type_id when 0 then ACTIVATE when 1 then download when 2 then download when 4 then click when 5 then  pv  end) end) effect, sum(cost) cost ");
		sql.append("from (select APP_ID,STATIC_DATE,TYPE_ID,sum(CLICK) CLICK,sum(POSPV) POSPV,sum(PV) PV,sum(DOWNLOAD) DOWNLOAD,sum(ACTIVATE) ACTIVATE,sum(COST) COST from T_STATIC_APP_BYHOUR s,T_APPLICATION app where dev_id=? ");
		sql.append(sb);
		sql.append(" and s.static_date=? and s.app_id=app.id  group by app_id, static_date, type_id) stat, T_APPLICATION app  where  stat.app_id = app.id group by app_id, type_id) t  where effect>0 group by app_id) t2 left join T_APPLICATION t on t2.app_id=t.id");
		
		List<DevIncomeListVo> list = (List<DevIncomeListVo>) developerReportDao.findByPage("t.id,t.name,IFNULL(score_wall_num,0) as score_wall_num,IFNULL(score_wall_cost,0) as score_wall_cost,IFNULL(page_wall_num,0) as page_wall_num,IFNULL(page_wall_cost,0) as page_wall_cost,IFNULL(banner_num,0) as banner_num,IFNULL(banner_cost,0) as banner_cost,IFNULL(chaping_num,0) as CHARTBOOST_num,IFNULL(chaping_cost,0) as CHARTBOOST_cost",
				sql.toString(), new Object[] { id, DateUtil.format(bean.getDate()) }, DevIncomeListVo.class, pageInfo);
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<DevActualTimeReportVo> findDevActualTime(DevReportbean bean, IPageInfo pageInfo) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append(" T_STATIC_APP_BYHOUR t left join t_application p  on t.app_id = p.id where 1=1  and p.DEV_ID = " + bean.getDev_id());
		if (ObjectUtils.isNotEmpty(bean.getEnd_date())) {
			sb.append(" and t.static_date='");
			sb.append(bean.getEnd_date());
			sb.append("'");
		}
		if (ObjectUtils.isNotEmpty(bean.getType_id())) {
			sb.append(" and t.type_id= ");
			sb.append(bean.getType_id());
		}
		if (ObjectUtils.isNotEmpty(bean.getApp_id())) {
			sb.append(" and t.app_id= ");
			sb.append(bean.getApp_id());
		}
		sb.append(" group by t.static_hour order by t.static_hour desc");
		return (List<DevActualTimeReportVo>) developerReportDao.findByPage("sum(t.pv) as pv, sum(t.click) as click, sum(t.activate) as activate, sum(t.cost) as cost, t.static_hour", sb.toString(), DevActualTimeReportVo.class, pageInfo);
	}

	@SuppressWarnings("unchecked")
	public List<DevActualTimeReportVo> findDevActualAll(DevReportbean bean) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append("select sum(t.pv) as pv, sum(t.click) as click, sum(t.activate) as activate, sum(t.cost) as cost, t.static_hour from T_STATIC_APP_BYHOUR t left join t_application p  on t.app_id = p.id where 1=1  and p.DEV_ID = " + bean.getDev_id());
		if (ObjectUtils.isNotEmpty(bean.getEnd_date())) {
			sb.append(" and t.static_date='");
			sb.append(bean.getEnd_date());
			sb.append("'");
		}
		if (ObjectUtils.isNotEmpty(bean.getType_id())) {
			sb.append(" and t.type_id= ");
			sb.append(bean.getType_id());
		}
		if (ObjectUtils.isNotEmpty(bean.getApp_id())) {
			sb.append(" and t.app_id= ");
			sb.append(bean.getApp_id());
		}
		sb.append(" group by t.static_hour order by t.static_hour desc");
		return (List<DevActualTimeReportVo>) developerReportDao.findAll(sb.toString(), DevActualTimeReportVo.class);
	}

	@SuppressWarnings("unchecked")
	public List<DevActualTimeReportVo> findDevHistorical(DevReportbean bean, IPageInfo pageInfo) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append(" t_static_app_byday t left join t_application p  on t.app_id = p.id where 1=1  and p.DEV_ID = " + bean.getDev_id());
		if (ObjectUtils.isNotEmpty(bean.getStart_date())) {
			sb.append(" and t.static_date>='");
			sb.append(bean.getStart_date());
			sb.append("'");
		}
		if (ObjectUtils.isNotEmpty(bean.getEnd_date())) {
			sb.append(" and t.static_date<='");
			sb.append(bean.getEnd_date());
			sb.append("'");
		}
		if (ObjectUtils.isNotEmpty(bean.getType_id())) {
			sb.append(" and t.type_id= ");
			sb.append(bean.getType_id());
		}
		if (ObjectUtils.isNotEmpty(bean.getApp_id())) {
			sb.append(" and t.app_id= ");
			sb.append(bean.getApp_id());
		}
		sb.append(" group by t.static_date order by t.static_date desc");
		return (List<DevActualTimeReportVo>) developerReportDao.findByPage("sum(t.pv) as pv, sum(t.click) as click, sum(t.activate) as activate, sum(t.cost) as cost, t.static_date", sb.toString(), DevActualTimeReportVo.class, pageInfo);
	}

	@SuppressWarnings("unchecked")
	public List<DevActualTimeReportVo> findDevHistoricalAll(DevReportbean bean) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append("select sum(t.pv) as pv, sum(t.click) as click, sum(t.activate) as activate, sum(t.cost) as cost, t.static_date from t_static_app_byday t left join t_application p  on t.app_id = p.id where 1=1  and p.DEV_ID = " + bean.getDev_id());
		if (ObjectUtils.isNotEmpty(bean.getStart_date())) {
			sb.append(" and t.static_date>='");
			sb.append(bean.getStart_date());
			sb.append("'");
		}
		if (ObjectUtils.isNotEmpty(bean.getEnd_date())) {
			sb.append(" and t.static_date<='");
			sb.append(bean.getEnd_date());
			sb.append("'");
		}
		if (ObjectUtils.isNotEmpty(bean.getType_id())) {
			sb.append(" and t.type_id= ");
			sb.append(bean.getType_id());
		}
		if (ObjectUtils.isNotEmpty(bean.getApp_id())) {
			sb.append(" and t.app_id= ");
			sb.append(bean.getApp_id());
		}
		sb.append(" group by t.static_date order by t.static_date desc");
		return (List<DevActualTimeReportVo>) developerReportDao.findAll(sb.toString(), DevActualTimeReportVo.class);
	}

}