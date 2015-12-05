package cn.adwalker;

import cn.adwalker.model.report.dao.IReportConfigDao;

public class TestReportManageDao extends TestBase {

	public void testtable() throws Exception {
		IReportConfigDao placementDao = (IReportConfigDao) context
				.getBean("iReportManageDao");

		placementDao
				.executeQueryList("select '总计','*','*','**','***','****','*****',IFNULL(sum(a.pospv), 0) as pospv,IFNULL(sum(a.adpv), 0) as adpv,"
						+ " IFNULL(sum(a.click), 0) as click,IFNULL(sum(a.download), 0) as download,IFNULL(sum(a.activate), 0) as activate,IFNULL(sum(a.cost), 0) as cost,"
						+ "IFNULL(round(sum(a.click)*100 / sum(a.pospv),3), 0)||'%' as ctrc,IFNULL(round(sum(a.download)*100 / sum(a.click),3), 0)||'%' as ctrd,"
						+ "IFNULL(round(sum(a.activate)*100 / sum(a.download),3), 0)||'%' as ctra  from (select static_date,static_hour,app_id,"
						+ "type_id,sum(pospv) as pospv,sum(pv) as adpv,sum(click) as click,sum(download) as download,sum(activate) as activate,sum(cost)  as cost from t_static_app_byhour  where static_date BETWEEN 'beginTime' and 'endTime' "
						+ "group by app_id, type_id,static_date,static_hour)  a  left join t_application  t on t.id = a.app_id left join t_type  f on f.id = a.type_id where t.id IS NOT NULL HAHA.ID HAHA.NAME HAHA.OS HAHA.STATIC_HOUR HAHA.TYPE_ID t.HAHA  order by static_date desc, a.static_hour desc,pospv desc");

	}

}
