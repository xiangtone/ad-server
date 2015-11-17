CREATE OR REPLACE PROCEDURE "ESCORE_STATIC_BYDAY"(strdate IN VARCHAR2,
                                                  strhour IN VARCHAR2) AS
BEGIN
  --将广告相关的小时数据合并到日数据
  delete from t_static_ad_byday
   where 1 = 1
     and static_date = strdate;

  insert into t_static_ad_byday
    (static_date,
     ad_id,
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
    select static_date,
           ad_id,
           app_id,
           type_id,
           channel,
           sum(syspv),
           sum(pv),
           sum(sysclick),
           sum(click),
           sum(sysclickd),
           sum(clickd),
           sum(sysdownload),
           sum(download),
           sum(sysactivate),
           sum(activate),
           sum(cost)
      from t_static_ad_byhour
     where 1 = 1
       and static_date = strdate
     group by static_date, ad_id, type_id, app_id, channel;
     delete from T_STATIC_AD_BYDAY t  where app_id in ('777','200','208');/*删除测试应用id产生的数据*/
     
  --将应用相关的小时数据合并到日数据
  
  delete from t_static_app_byday
   where 1 = 1
     and static_date = strdate;

  insert into t_static_app_byday
    (static_date,
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
    select static_date,
           app_id,
           type_id,
           sum(pospv),
           sum(syspv),
           sum(pv),
           sum(sysclick),
           sum(click),
           sum(sysclickd),
           sum(clickd),
           sum(sysdownload),
           sum(download),
           sum(sysactivate),
           sum(activate),
           sum(cost)
      from t_static_app_byhour
     where 1 = 1
       and static_date = strdate
     group by static_date, app_id, type_id;

delete from T_STATIC_APP_BYDAY t  where app_id in ('777','200','208');/*删除测试应用id产生的数据*/

 delete from t_static_channel_byday
   where 1 = 1
     and static_date = strdate;

insert into t_static_channel_byday
  (static_date,
   channel,
   app_id,
   type_id,
   pv,
   click,
   clickd,
   download,
   activate,
   cost)
  select static_date,
         channel,
         app_id,
         type_id,
         sum(pv),
         sum(click),
         sum(clickd),
         sum(download),
         sum(activate),
         sum(cost)
    from t_static_ad_byhour
   where 1 = 1
         and static_date = strdate
   group by static_date, channel,app_id, type_id;
   
   delete from T_STATIC_CHANNEL_BYDAY t  where app_id in ('777','200','208');/*删除测试应用id产生的数据*/


END ESCORE_STATIC_BYDAY;
