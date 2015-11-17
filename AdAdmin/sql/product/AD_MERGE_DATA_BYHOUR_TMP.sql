CREATE OR REPLACE PROCEDURE "ESCORE_MERGE_DATA_BYHOUR_TMP"(strdate IN VARCHAR2,
                                                           strhour IN VARCHAR2) AS
BEGIN
  delete from t_static_orignal_byhour_tmp
   where 1 = 1
     and STATIC_DATE = strdate
     and STATIC_HOUR = strhour;

  --插入小时应用init数据
  insert into t_static_orignal_byhour_tmp
    (static_date, static_hour, app_id, channel, uuid, area, ip, init)
    select static_date, static_hour, app_id, channel, uuid, area, ip, init
      from t_static_tmp_user_init
     where 1 = 1
       and STATIC_DATE = strdate
       and STATIC_HOUR = strhour;

  --插入小时广告pv数据
  insert into t_static_orignal_byhour_tmp
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
     price)
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
           pv,
           price
      from t_static_tmp_ad_pv
     where 1 = 1
       and STATIC_DATE = strdate
       and STATIC_HOUR = strhour;

  --插入小时位置pv数据
  insert into t_static_orignal_byhour_tmp
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
  insert into t_static_orignal_byhour_tmp
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
     price)
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
           click,
           price
      from t_static_tmp_ad_click
     where 1 = 1
       and STATIC_DATE = strdate
       and STATIC_HOUR = strhour;

  --插入小时广告download数据
  insert into t_static_orignal_byhour_tmp
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
     price)
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
           download,
           price
      from t_static_tmp_ad_download
     where 1 = 1
       and STATIC_DATE = strdate
       and STATIC_HOUR = strhour;

  --插入小时广告activate数据
  insert into t_static_orignal_byhour_tmp
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
     price)
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
           activate,
           price
      from t_static_tmp_ad_activate
     where 1 = 1
       and STATIC_DATE = strdate
       and STATIC_HOUR = strhour;

END ESCORE_MERGE_DATA_BYHOUR_TMP;

