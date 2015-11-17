select a.static_date,
       t.adv_id,
       t.id,
       t.placement_name as name,
       f.name as fname,
       t.os,
       nvl(a.adpv, 0) as adpv,
       nvl(a.click, 0) as click,
       nvl(a.download, 0) as download,
       nvl(a.activate, 0) as activate,
       nvl(round(a.cost,4), 0) as cost,
       nvl(round(a.click*100 / a.adpv,4), 0)||'%' as ctrc,
       nvl(round(a.download*100 / a.click,4), 0)||'%' as ctrd,
       nvl(round(a.activate*100 / a.download,4), 0)||'%' as ctra
  from (select static_date,
               ad_id,
               type_id,
               sum(pv) as adpv,
               sum(click) as click,
               sum(download) as download,
               sum(activate) as activate,
               sum(cost) as cost
          from t_static_ad_byday
         where static_date BETWEEN 'beginTime' and 'endTime'
         group by ad_id, type_id,static_date)  a
          left join v_ad_campaign  t on t.id = a.ad_id
          left join t_type f on f.id=a.type_id
 where t.id IS NOT NULL t.ADV_ID t.ID t.AD_NAME t.OS f.ID
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 select a.static_date,
       a.static_hour,
       t.adv_id,
       t.id,
       t.placement_name,
       f.name as fname,
       t.os,
       nvl(a.adpv, 0) as adpv,
       nvl(a.click, 0) as click,
       nvl(a.download, 0) as download,
       nvl(a.activate, 0) as activate,
       nvl(round(a.cost,4), 0) as cost,
       nvl(round(a.click*100 / a.adpv,4), 0)||'%' as ctrc,
       nvl(round(a.download*100 / a.click,4), 0)||'%' as ctrd,
       nvl(round(a.activate*100 / a.download,4), 0)||'%' as ctra
  from (select static_date,
               static_hour,
               ad_id,
               type_id,
               sum(pv) as adpv,
               sum(click) as click,
               sum(download) as download,
               sum(activate) as activate,
               sum(cost) as cost
          from t_static_ad_byhour
         where static_date BETWEEN 'beginTime' and 'endTime'
         group by ad_id, type_id,static_date,static_hour)  a
           left join v_ad_campaign  t on t.id = a.ad_id
           left join t_type f on f.id=a.type_id
 where t.id IS NOT NULL t.ADV_ID t.ID t.AD_NAME t.OS a.STATIC_HOUR f.ID
 order by static_date desc，a.static_hour desc,adpv desc
 
 
 
 
 
 
 
 
   CREATE TABLE "T_ESCORE_INVOICE"(	"ID" NUMBER(38,0) NOT NULL ENABLE, 
	"TYPE" VARCHAR2(255), 
	"INVOICE_TYPE" VARCHAR2(255), 
	"INVOICE_DATE" VARCHAR2(255), 
	"COMPANY_INCOME" VARCHAR2(255), 
	"TAXPAYER_NUMBER_INCOME" VARCHAR2(255), 
	"ADDRESS_INCOME" VARCHAR2(255), 
	"PHONE_INCOME" VARCHAR2(255), 
	"BANK_SUBBRANCH_INCOME" VARCHAR2(255) DEFAULT NULL, 
	"BANK_ACCOUNT_INCOME" VARCHAR2(255), 
	"COMPANY_OUTCOME" VARCHAR2(255), 
	"TAXPAYER_NUMBER_OUTCOME" VARCHAR2(255), 
	"ADDRESS_OUTCOME" VARCHAR2(255), 
	"PHONE_OUTCOME" VARCHAR2(255), 
	"BANK_SUBBRANCH_OUTCOME" VARCHAR2(255), 
	"BANK_ACCOUNT_OUTCOME" VARCHAR2(255), 
	"INVOICE_MONEY" NUMBER(10,2), 
	"INVOICE_URL" VARCHAR2(4000), 
	"CREATE_TIME" DATE DEFAULT sysdate, 
	"MANAGER_ID" NUMBER(38,0), 
	 CHECK ("ID" IS NOT NULL) ENABLE, 
	 CHECK ("ID" IS NOT NULL) ENABLE, 
	 CHECK ("ID" IS NOT NULL) ENABLE, 
	 CHECK ("ID" IS NOT NULL) ENABLE, 
	 PRIMARY KEY ("ID")
   ) 
   
   
   
   
   
    insert into T_IOS_EFFECT_BYDAY (ID,STATIC_DATE,CREATE_TIME,MEDIA_TYPE,AD_ID,STATUS,CHANNEL,APP,PAGE_TYPE,SYS_NUM,CONFIRM_NUM)
    select seq_IOS_EFFECT_BYDAY.NEXTVAL, t.* from (select 
to_char(create_time,'yyyy-MM-dd') static_date,
ad_id,
application_key,
channel,
page_type,
count(1),

sum(case when  activite_status=0  then 0
when activite_status=1 then 1 end)



from T_IOS_ACTION_LOG where   CREATE_TIME>=to_date('2013-03-01 18:36:11','yyyy-MM-dd hh24:mi:ss') and  CREATE_TIME<to_date('2013-04-01 00:00:00','yyyy-MM-dd hh24:mi:ss')
 group by to_char(create_time,'yyyy-MM-dd'),ad_id,application_key,channel,page_type
) t
     
        
        
        
        
        
        
        

        

 
 
 
 
 
 
 
 
 
 
 
 
 
 
 