delete from  T_REPORT_APP_BYDAY;
insert into T_REPORT_APP_BYDAY(STATIC_DATE,DEV_ID,APP_ID,APP_NAME,TYPE_ID,TYPE_NAME,OS,POSPV,ADPV,CLICK,CLICKD,DOWNLOAD,ACTIVATE,COST,EFFECT) 
							 select 
							 str_to_date(a.STATIC_DATE, '%Y-%m-%d %T') static_date,
							 t.dev_id dev_id,
							 t.id app_id,
							 t.name app_name,
							 a.type_id,
							 f.name type_name,
							 t.os os,
							 IFNULL(a.pospv, 0) popv,
							 IFNULL(a.adpv, 0) as adpv,
							 IFNULL(a.click, 0) as click,
							 IFNULL(a.clickd, 0) as clickd,
							 IFNULL(a.download, 0) as download,
							 IFNULL(a.activate, 0) as activate,
							 IFNULL(round(a.cost, 4), 0) as cost,
							 IFNULL((case when os = 'ios' then(case a.type_id when 0 then activate when 1 then  click when 2 then click when 4 then click when 5 then adpv end) when os = 'android' then (case a.type_id when 0 then activate  when 1 then download when 2 then download when 4 then click when 5 then adpv end) end),0) effect 
							 from (select static_date,app_id,type_id,sum(pospv) as pospv,sum(pv) as adpv,sum(click) as click,sum(clickd) as clickd,sum(download) as download,sum(activate) as activate,sum(cost) as cost from t_static_app_byday group by app_id, type_id, static_date) a left join t_application t on t.id = a.app_id left join t_type f  on f.id = a.type_id where t.id IS NOT NULL
		