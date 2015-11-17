/*应用数据按天统计数据源sql*/
select a.static_date as staticdate,
       t.dev_id as devId,
       p.email as devName,
       t.id as appId,
       t.name as appName,
       t.os as os,
       f.name as typename,
       nvl(a.pospv, 0) as pospv,
       nvl(a.adpv, 0) as adpv,
       nvl(a.click, 0) as click,
       nvl(a.download, 0) as download,
       nvl(a.downloaded, 0) as downloaded,
       nvl(a.activate, 0) as activate,
       nvl(round(a.cost,3), 0) as cost,
       nvl(round(a.click*100 / a.pospv,3), 0)||'%' as ctrc,
       nvl(round(a.downloaded*100 / a.click,3), 0)||'%' as ctrd,
       nvl(round(a.activate*100 / a.downloaded,3), 0)||'%' as ctra
  from (select static_date,
               app_id,
               type_id,
               sum(pospv) as pospv,
               sum(pv) as adpv,
               sum(click) as click,
               sum(download) as download,
               sum(downloaded) as downloaded,
               sum(activate) as activate,
               sum(cost) as cost
          from t_static_app_byday
         where static_date BETWEEN 'beginTime' and 'endTime'
         group by app_id,type_id, static_date)  a
           left join t_application  t on t.id = a.app_id
	   left join T_ADVERTISER  p on t.dev_id = p.id
           left join t_type  f on f.id = a.type_id
 where t.id IS NOT NULL t.ID t.NAME t.OS a.TYPE_ID t.DEV_ID
 -------------------------------------
 t.ID t.NAME t.OS a.TYPE_ID t.DEV_ID这几个字段是用来作为查询条件用的，并不是
 查询语句本身必须的。以下同上，且在查询时多表之间使用左连接进行多表查询，且对查询出的字段
 一定要明确名称，最好为其取别名
 -------------------------------------
 /*应用数据按天统计总和sql*/
 select '总计','-','*','**','***'，'****',nvl(sum(a.pospv), 0) as pospv,nvl(sum(a.adpv), 0) as adpv,
       nvl(sum(a.click), 0) as click,
       nvl(sum(a.download), 0) as download,
			 nvl(sum(a.downloaded), 0) as downloaded,
       nvl(sum(a.activate), 0) as activate,
       nvl(sum(a.cost), 0) as cost,
       nvl(round(sum(a.click)*100 / sum(a.pospv),3), 0)||'%' as ctrc,
       nvl(round(sum(a.downloaded)*100 / sum(a.click),3), 0)||'%' as ctrd,
       nvl(round(sum(a.activate)*100 / sum(a.downloaded),3), 0)||'%' as ctra
  from (select static_date,
               app_id,
               type_id,
               sum(pospv) as pospv,
               sum(pv) as adpv,
               sum(click) as click,
               sum(download) as download,
							 sum(downloaded) as downloaded,
               sum(activate) as activate,
               sum(cost)  as cost
          from t_static_app_byday
         where static_date BETWEEN 'beginTime' and 'endTime'
         group by app_id,type_id,static_date)  a
           left join t_application  t on t.id = a.app_id
	   left join T_ADVERTISER  p on t.dev_id = p.id
           left join t_type  f on f.id = a.type_id
 where t.id IS NOT NULL t.ID t.NAME t.OS a.TYPE_ID t.DEV_ID
 
 /*应用数据实时统计数据源sql*/
 
 select a.static_date,
       a.static_hour,
       t.id,
       t.name,
       f.name as fname,
       t.os,
       nvl(a.pospv, 0) as pospv,
       nvl(a.adpv, 0) as adpv,
       nvl(a.click, 0) as click,
       nvl(a.download, 0) as download,
       nvl(a.downloaded,0) as downloaded,
       nvl(a.activate, 0) as activate,
       nvl(round(a.cost,3), 0) as cost,
       nvl(round(a.click*100 / a.pospv,3), 0)||'%' as ctrc,
       nvl(round(a.downloaded*100 / a.click,3), 0)||'%' as ctrd,
       nvl(round(a.activate*100 / a.downloaded,3), 0)||'%' as ctra
  from (select static_date,
               static_hour,
               app_id,
               type_id,
               sum(pospv) as pospv,
               sum(pv) as adpv,
               sum(click) as click,
               sum(download) as download,
               sum(downloaded) as downloaded,
               sum(activate) as activate,
               sum(cost) as cost
          from t_static_app_byhour
         where static_date BETWEEN 'beginTime' and 'endTime'
         group by app_id, type_id,static_date,static_hour)  a
           left join t_application  t on t.id = a.app_id
           left join t_type  f on f.id = a.type_id
 where t.id IS NOT NULL t.ID t.NAME t.OS a.STATIC_HOUR a.TYPE_ID
 
 /*应用数据实时统计总和sql*/
 select '总计','*','*','**','***','****','*****',nvl(sum(a.pospv), 0) as pospv,nvl(sum(a.adpv), 0) as adpv,
       nvl(sum(a.click), 0) as click,
       nvl(sum(a.download), 0) as download,
       nvl(sum(a.downloaded),0) as downloaded,
       nvl(sum(a.activate), 0) as activate,
       nvl(sum(a.cost), 0) as cost,
       nvl(round(sum(a.click)*100 / sum(a.pospv),3), 0)||'%' as ctrc,
       nvl(round(sum(a.downloaded)*100 / sum(a.click),3), 0)||'%' as ctrd,
       nvl(round(sum(a.activate)*100 / sum(a.downloaded),3), 0)||'%' as ctra
  from (select static_date,
               static_hour,
               app_id,
               type_id,
               sum(pospv) as pospv,
               sum(pv) as adpv,
               sum(click) as click,
               sum(download) as download,
               sum(downloaded) as downloaded,
               sum(activate) as activate,
               sum(cost)  as cost
          from t_static_app_byhour
         where static_date BETWEEN 'beginTime' and 'endTime'
         group by app_id, type_id,static_date,static_hour)  a
           left join t_application  t on t.id = a.app_id
           left join t_type  f on f.id = a.type_id
 where t.id IS NOT NULL t.ID t.NAME t.OS a.STATIC_HOUR a.TYPE_ID
 order by static_date desc, a.static_hour desc,pospv desc
 
 /*综合统计按天数据源sql*/
 select ad.id as adid,
       cate.name as catename,
       app.id as appid,
       app.name as appname,
       type.name as typename,
       nvl(sta.adpv, 0) as adpv,
       nvl(sta.click, 0) as click,
       nvl(sta.download, 0) as download,
       nvl(sta.downloaded, 0) as downloaded,
       nvl(sta.activate, 0) as activate,
       nvl(round(sta.cost,3), 0) as cost,
         sta.static_date as datedd,
       nvl(round(sta.click*100 / sta.adpv,3), 0)||'%' as ctrc,
       nvl(round(sta.downloaded*100 / sta.click,3), 0)||'%' as ctrd,
       nvl(round(sta.activate*100 / sta.downloaded,3), 0)||'%' as ctra
  from (select static_date,
               ad_id,
               app_id,
               type_id,
               sum(pv) as adpv,
               sum(click) as click,
               sum(download) as download,
               sum(downloaded) as downloaded,
               sum(activate) as activate,
               sum(cost) as cost
          from t_static_ad_byday
         where static_date BETWEEN 'beginTime' and 'endTime'
         group by ad_id,app_id,type_id,static_date) sta
           left join t_ad  ad on ad.id = sta.ad_id
           left join t_application  app on app.id = sta.app_id
           left join t_type  type on type.id = sta.type_id
	   left join t_placement place on place.id=ad.placement_id 
           left join t_escore_category cate on cate.id = place.category_id
 where ad.id IS NOT NULL ad.ID ad.AD_NAME ad.OS cate.ID app.NAME app.ID type.ID
 /*综合统计按天总和sql*/
   select '总计','*','**','***','****',nvl(sum(sta.adpv), 0) as adpv,
       nvl(sum(sta.click), 0) as click,
       nvl(sum(sta.download), 0) as download,
       nvl(sum(sta.downloaded), 0) as downloaded,
       nvl(sum(sta.activate), 0) as activate,
       nvl(round(sum(sta.cost),3), 0) as cost,         
       nvl(round(sum(sta.click)*100 / sum(sta.adpv),3), 0)||'%' as ctrc,
       nvl(round(sum(sta.downloaded)*100 / sum(sta.click),3), 0)||'%' as ctrd,
       nvl(round(sum(sta.activate)*100 / sum(sta.downloaded),3), 0)||'%' as ctra
 from (select static_date,
               ad_id,
               app_id,
               type_id,
               sum(pv) as adpv,
               sum(click) as click,
               sum(download) as download,
               sum(downloaded) as downloaded,
               sum(activate) as activate,
               sum(cost) as cost
          from t_static_ad_byday
         where static_date BETWEEN 'beginTime' and 'endTime'
         group by ad_id,app_id,type_id,static_date) sta
           left join t_ad  ad on ad.id = sta.ad_id
           left join t_application  app on app.id = sta.app_id
           left join t_type  type on type.id = sta.type_id
	   left join t_placement place on place.id=ad.placement_id 
           left join t_escore_category cate on cate.id = place.category_id
 where ad.id IS NOT NULL ad.ID ad.AD_NAME ad.OS cate.ID app.NAME app.ID type.ID
 
 /*广告统计按天数据源sql*/
 select a.static_date,
       cam.adv_id,
       t.id,
       cam.campaign_name,
       app.os,
       nvl(a.adpv, 0) as adpv,
       nvl(a.click, 0) as click,
       nvl(a.download, 0) as download,
       nvl(a.downloaded, 0) as downloaded,
       nvl(a.activate, 0) as activate,
       nvl(round(a.cost,3), 0) as cost,
       nvl(round(a.click*100 / a.adpv,3), 0)||'%' as ctrc,
       nvl(round(a.downloaded*100 / a.click,3), 0)||'%' as ctrd,
       nvl(round(a.activate*100 / a.downloaded,3), 0)||'%' as ctra
  from (select static_date,
               ad_id,
               sum(pv) as adpv,
               sum(click) as click,
               sum(download) as download,
               sum(downloaded) as downloaded,
               sum(activate) as activate,
               sum(cost) as cost
          from t_static_ad_byday
         where static_date BETWEEN 'beginTime' and 'endTime'
         group by ad_id, static_date)  a
           left join t_ad  t on t.id = a.ad_id
	   left join t_application app on t.app_id = app.id
	   left join t_campaign cam on cam.id = t.placement_id
 where t.id IS NOT NULL t.ADV_ID t.ID t.AD_NAME t.OS
 /*广告统计按天总和sql*/
 select '总计','*','**','***','****',nvl(sum(a.adpv), 0) as adpv,
       nvl(sum(a.click), 0) as click,
       nvl(sum(a.download), 0) as download,
       nvl(sum(a.downloaded), 0) as downloaded,
       nvl(sum(a.activate), 0) as activate,
       nvl(round(sum(a.cost),3), 0) as cost,         
       nvl(round(sum(a.click)*100 / sum(a.adpv),3), 0)||'%' as ctrc,
       nvl(round(sum(a.downloaded)*100 / sum(a.click),3), 0)||'%' as ctrd,
       nvl(round(sum(a.activate)*100 / sum(a.downloaded),3), 0)||'%' as ctra
  from (select static_date,
               ad_id,
               sum(pv) as adpv,
               sum(click) as click,
               sum(download) as download,
               sum(downloaded) as downloaded,
               sum(activate) as activate,
               sum(cost) as cost
          from t_static_ad_byday
         where static_date BETWEEN 'beginTime' and 'endTime'
         group by ad_id, static_date)  a
          left join t_ad  t on t.id = a.ad_id
	  left join t_application app on t.app_id = app.id
	  left join t_campaign cam on cam.id = t.placement_id
 where t.id IS NOT NULL t.ADV_ID t.ID t.AD_NAME t.OS
  /*广告统计实时数据源sql*/
   select a.static_date,
       a.static_hour,
       cam.adv_id,
       t.id,
       cam.campaign_name,
       app.os,
       f.name as fname,
       nvl(a.adpv, 0) as adpv,
       nvl(a.click, 0) as click,
       nvl(a.download, 0) as download,
       nvl(a.activate, 0) as activate,
       nvl(round(a.cost,3), 0) as cost,
       nvl(round(a.click*100 / a.adpv,3), 0)||'%' as ctrc,
       nvl(round(a.download*100 / a.click,3), 0)||'%' as ctrd,
       nvl(round(a.activate*100 / a.download,3), 0)||'%' as ctra
  from (select static_date,
               static_hour,
               ad_id,
	       app_id,
               type_id,
               sum(pv) as adpv,
               sum(click) as click,
               sum(download) as download,
               sum(activate) as activate,
               sum(cost) as cost
          from t_static_ad_byhour
         where static_date BETWEEN 'beginTime' and 'endTime'
         group by ad_id, type_id,static_date,static_hour,app_id)  a
           left join t_ad  t on t.id = a.ad_id
           left join t_type f on f.id=a.type_id
	   left join t_application app on t.app_id = app.id
	   left join t_campaign cam on cam.id = t.placement_id
 where t.id IS NOT NULL t.ADV_ID t.ID t.AD_NAME a.STATIC_HOUR f.ID
  /*广告统计实时总和ql*/
  select '总计','*','**','***','****','*****','******',nvl(sum(a.adpv), 0) as adpv,
       nvl(sum(a.click), 0) as click,
       nvl(sum(a.download), 0) as download,
       nvl(sum(a.activate), 0) as activate,
       nvl(round(sum(a.cost),3), 0) as cost,         
       nvl(round(sum(a.click)*100 / sum(a.adpv),3), 0)||'%' as ctrc,
       nvl(round(sum(a.download)*100 / sum(a.click),3), 0)||'%' as ctrd,
       nvl(round(sum(a.activate)*100 / sum(a.download),3), 0)||'%' as ctra
  from (select static_date,
               static_hour,
               ad_id,
	       app_id,
               type_id,
               sum(pv) as adpv,
               sum(click) as click,
               sum(download) as download,
               sum(activate) as activate,
               sum(cost) as cost
          from t_static_ad_byhour
         where static_date BETWEEN 'beginTime' and 'endTime'
         group by ad_id,type_id, static_date,static_hour,app_id)  a
           left join t_ad  t on t.id = a.ad_id
           left join t_type f on f.id=a.type_id
	   left join t_application app on t.app_id = app.id
	   left join t_campaign cam on cam.id = t.placement_id
 where t.id IS NOT NULL t.ADV_ID t.ID t.AD_NAME a.STATIC_HOUR f.ID