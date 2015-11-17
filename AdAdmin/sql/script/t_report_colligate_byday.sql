 insert into t_report_colligate_byday 
    (ID,
      AD_ID,    
      AD_NAME,      
      APP_ID,   
      APP_NAME,     
      TYPE_NAME,    
      OS,  
      ADPV, 
      CLICK, 
      DOWNLOAD,     
      ACTIVATE,   
      COST,   
      DATEDD,
      CTRC,  		
      CTRD,			
      CTRA)
select SEQ_report_colligate_byday.NEXTVAL, ad.id as ad_id,
       ad.placement_name as ad_name,
       app.id as app_id,
       app.name as app_name,
       type.name as type_name,
       ad.os as os,
       nvl(sta.adpv, 0) as adpv,
       nvl(sta.click, 0) as click,
       nvl(sta.download, 0) as download,
       nvl(sta.activate, 0) as activate,
       nvl(round(sta.cost,4), 0) as cost,
         sta.static_date as datedd,
       nvl(round(sta.click*100 / sta.adpv,4), 0)||'%' as ctrc,
       nvl(round(sta.download*100 / sta.click,4), 0)||'%' as ctrd,
       nvl(round(sta.activate*100 / sta.download,4), 0)||'%' as ctra
  from (select static_date,
               ad_id,
               app_id,
               type_id,
               sum(pv) as adpv,
               sum(click) as click,
               sum(download) as download,
               sum(activate) as activate,
               sum(cost) as cost
          from t_static_ad_byday
--where static_date BETWEEN '2013-11-01' and '2013-11-13'
         group by ad_id,app_id,type_id,static_date) sta
           left join v_ad_campaign  ad on ad.id = sta.ad_id
           left join t_application  app on app.id = sta.app_id
           left join t_type  type on type.id = sta.type_id
 where ad.id IS NOT NULL
