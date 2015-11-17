CREATE OR REPLACE PROCEDURE "ESCORE_STATIC_BYHOUR"(strdate IN VARCHAR2,
                                                   strhour IN VARCHAR2) AS

BEGIN

  delete from t_static_app_byhour
   where 1 = 1
     and STATIC_DATE = strdate
     and STATIC_HOUR = strhour;

  insert into t_static_app_byhour
    (static_date,
     static_hour,
     app_id,
     type_id,
     pospv,
     syspv,
     pv,
     sysclick,
     click,
     sysclickd,
     clickd,
     sysdownload,
     download,
     sysactivate,
     activate,
     cost)
    select t.static_date,
           t.static_hour,
           t.app_id,
           t.type_id,
           round(sum(t.pospv*p.rate),0),
           round(sum(t.syspv*p.rate),0),
           round(sum(t.pv*p.rate),0),
           sum(t.sysclick),
           sum(t.click),
           sum(t.sysclickd),
           sum(t.clickd),
           sum(t.sysdownload),
           sum(t.download),
           sum(t.sysactivate),
           sum(t.activate),
           round(sum(t.cost*p.rate),4)
      from t_static_orignal_byhour_sum t
      left join t_page p on t.app_id=p.app_id and t.type_id=p.type_id 
     where 1 = 1
       and t.STATIC_DATE = strdate
       and t.STATIC_HOUR = strhour
       and t.type_id is not null
     group by t.static_date, t.static_hour, t.app_id, t.type_id;

  delete from t_static_ad_byhour
   where 1 = 1
     and STATIC_DATE = strdate
     and STATIC_HOUR = strhour;

  insert into t_static_ad_byhour
    (static_date,
     static_hour,
     ad_id,
     pay_type,
     app_id,
     type_id,
     channel,
     syspv,
     pv,
     sysclick,
     click,
     sysclickd,
     clickd,
     sysdownload,
     download,
     sysactivate,
     activate,
     cost)
    select t.static_date,
           t.static_hour,
           t.ad_id,
           t.pay_type,
           t.app_id,
           t.type_id,
           t.channel,
           round(sum(t.syspv*p.rate),0),
           round(sum(t.pv*p.rate),0),
           sum(t.sysclick),
           sum(t.click),
           sum(t.sysclickd),
           sum(t.clickd),
           sum(t.sysdownload),
           sum(t.download),
           sum(t.sysactivate),
           sum(t.activate),
           round(sum(t.cost*p.rate),4)
      from t_static_orignal_byhour_sum t
      left join t_page p on t.app_id=p.app_id and t.type_id=p.type_id 
     where 1 = 1
       and t.STATIC_DATE = strdate
       and t.STATIC_HOUR = strhour
       and t.ad_id is not null
     group by t.static_date,
              t.static_hour,
              t.ad_id,
              t.pay_type,
              t.app_id,
              t.type_id,
              t.channel;
    execute immediate 'truncate table t_static_orignal_byhour_sum';
END ESCORE_STATIC_BYHOUR;
