select 
	a.static_date,
	a.adv_id,
	a.ad_id as id,
	a.ad_name as placement_name,
	a.type_name as fname,a.os,IFNULL(a.pv, 0) as adpv,
	
	IFNULL(a.click, 0) as click,IFNULL(a.download, 0) as download,
	IFNULL(a.activate, 0) as activate,
	IFNULL(round(a.cost,4), 0) as cost,
	IFNULL(round(a.click*100 / a.pv,4), 0) as ctrc,
	IFNULL(round(a.download*100 / a.click,4), 0) as ctrd,
	IFNULL(round(a.activate*100 / a.download,4), 0) as ctra from t_report_ad_byday a  
where static_date BETWEEN '2014-01-22'  and '2014-01-22'  order by static_date limit 0,20