CREATE OR REPLACE PROCEDURE "ESCORE_STATIC_BYHOUR_TMP"(strdate IN VARCHAR2,
                                                   strhour IN VARCHAR2) AS

BEGIN

  delete from t_static_app_byhour_tmp
   where 1 = 1
     and STATIC_DATE = strdate
     and STATIC_HOUR = strhour;

  insert into t_static_app_byhour_tmp
    (static_date,
     static_hour,
     app_id,
     type_id,
     pospv,
     syspv,
     pv,
     sysclick,
     click,
     sysdownload,
     download,
     sysactivate,
     activate,
     cost)
    select static_date,
           static_hour,
           app_id,
           type_id,
           sum(pospv),
           sum(syspv),
           sum(pv),
           sum(sysclick),
           sum(click),
           sum(sysdownload),
           sum(download),
           sum(sysactivate),
           sum(activate),
           sum(cost)
      from t_static_orignal_byhour_tmp
     where 1 = 1
       and STATIC_DATE = strdate
       and STATIC_HOUR = strhour
       and type_id is not null
     group by static_date, static_hour, app_id, type_id;

  delete from t_static_ad_byhour_tmp
   where 1 = 1
     and STATIC_DATE = strdate
     and STATIC_HOUR = strhour;

  insert into t_static_ad_byhour_tmp
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
     sysdownload,
     download,
     sysactivate,
     activate,
     cost)
    select static_date,
           static_hour,
           ad_id,
           pay_type,
           app_id,
           type_id,
           channel,
           sum(syspv),
           sum(pv),
           sum(sysclick),
           sum(click),
           sum(sysdownload),
           sum(download),
           sum(sysactivate),
           sum(activate),
           sum(cost)
      from t_static_orignal_byhour_tmp
     where 1 = 1
       and STATIC_DATE = strdate
       and STATIC_HOUR = strhour
       and ad_id is not null
     group by static_date,
              static_hour,
              ad_id,
              pay_type,
              app_id,
              type_id,
              channel;

END ESCORE_STATIC_BYHOUR_TMP;

