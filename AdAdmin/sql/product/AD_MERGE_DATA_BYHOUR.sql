CREATE OR REPLACE PROCEDURE "ESCORE_MERGE_DATA_BYHOUR"(strdate IN VARCHAR2,
                                                       strhour IN VARCHAR2) AS
BEGIN
  delete from t_static_orignal_byhour_sum
   where 1 = 1
     and STATIC_DATE = strdate
     and STATIC_HOUR = strhour;

  --插入小时应用init数据
  insert into t_static_orignal_byhour_sum
    (static_date, static_hour, app_id, channel, uuid, area, ip, init)
    select static_date, static_hour, app_id, channel, uuid, area, ip, init
      from t_static_tmp_user_init
     where 1 = 1
       and STATIC_DATE = strdate
       and STATIC_HOUR = strhour;

  --插入小时广告pv数据
  insert into t_static_orignal_byhour_sum
    (static_date,
     static_hour,
     ad_id,
     pay_type,
     app_id,
     type_id,
     channel,
     uuid,
     area,
     ip,
     pv,
     syspv,
     price,
     cost)
    select static_date,
           static_hour,
           ad_id,
           pay_type,
           app_id,
           type_id,
           channel,
           uuid,
           area,
           ip,
           1 as epv,
           pv,
           price,
           (case
             when app_id = '247' then --特殊处理墨迹天气
              0
             else
              price*nvl(a.scale, 1) * 1 / 1000
           end) as cost
           
      from t_static_tmp_ad_pv t left join t_application a on t.app_id=a.id
     where 1 = 1
       and STATIC_DATE = strdate
       and STATIC_HOUR = strhour;

  --插入小时位置pv数据
  insert into t_static_orignal_byhour_sum
    (static_date,
     static_hour,
     app_id,
     type_id,
     channel,
     uuid,
     area,
     ip,
     pospv)
    select static_date,
           static_hour,
           app_id,
           type_id,
           channel,
           uuid,
           area,
           ip,
           pospv
      from t_static_tmp_pos_pv
     where 1 = 1
       and STATIC_DATE = strdate
       and STATIC_HOUR = strhour;

  --插入小时广告click数据
  insert into t_static_orignal_byhour_sum
    (static_date,
     static_hour,
     ad_id,
     pay_type,
     app_id,
     type_id,
     channel,
     uuid,
     area,
     ip,
     click,
     sysclick,
     price,
     cost)
    select static_date,
           static_hour,
           ad_id,
           pay_type,
           app_id,
           type_id,
           channel,
           uuid,
           area,
           ip,
           1 as eclick,
           click,
           price,
           (case
             when app_id = '247' then --特殊处理墨迹天气
              0 
             else
              price*nvl(a.scale, 1) * 1
           end) as cost
      from t_static_tmp_ad_click t left join t_application a on t.app_id=a.id
     where 1 = 1
       and STATIC_DATE = strdate
       and STATIC_HOUR = strhour;
       
 --插入小时广告clickd数据
  insert into t_static_orignal_byhour_sum
    (static_date,
     static_hour,
     ad_id,
     pay_type,
     app_id,
     type_id,
     channel,
     uuid,
     area,
     ip,
     clickd,
     sysclickd,
     price,
     cost)
    select static_date,
           static_hour,
           ad_id,
           pay_type,
           app_id,
           type_id,
           channel,
           uuid,
           area,
           ip,
           1 as eclickd,
           clickd,
           price,
           (case
             when app_id = '247' then --特殊处理墨迹天气
              0
             when (type_id='5') then
              0
             else 
              price*nvl(a.scale, 1) * 1
           end) as cost
      from t_static_tmp_ad_clickd t left join t_application a on t.app_id=a.id
     where 1 = 1
       and STATIC_DATE = strdate
       and STATIC_HOUR = strhour;

  --插入小时广告download数据
  insert into t_static_orignal_byhour_sum
    (static_date,
     static_hour,
     ad_id,
     pay_type,
     app_id,
     type_id,
     channel,
     uuid,
     area,
     ip,
     download,
     sysdownload,
     price,
     cost)
    select static_date,
           static_hour,
           ad_id,
           pay_type,
           app_id,
           type_id,
           channel,
           uuid,
           area,
           ip,
           1 as edownload,
           download,
           price,
           (case
             when app_id = '247' then --特殊处理墨迹天气
              1 
             else
              price *nvl(a.scale, 1)* 1
           end) as cost
      from t_static_tmp_ad_download t left join t_application a on t.app_id=a.id
     where 1 = 1
       and STATIC_DATE = strdate
       and STATIC_HOUR = strhour;

  --插入小时广告activate数据
  insert into t_static_orignal_byhour_sum
    (static_date,
     static_hour,
     ad_id,
     pay_type,
     app_id,
     type_id,
     channel,
     uuid,
     area,
     ip,
     activate,
     sysactivate,
     price,
     cost)
    select static_date,
           static_hour,
           ad_id,
           pay_type,
           app_id,
           type_id,
           channel,
           uuid,
           area,
           ip,
           1 as eactivate,
           activate,
           price,
           (case
             when app_id = '247' then --特殊处理墨迹天气
              0
             else
              price *nvl(a.scale, 1) *  1
           end) as cost
      from t_static_tmp_ad_activate  t left join t_application a on t.app_id=a.id
     where 1 = 1
       and STATIC_DATE = strdate
       and STATIC_HOUR = strhour;

END ESCORE_MERGE_DATA_BYHOUR;
