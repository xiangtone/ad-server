
CREATE OR REPLACE PROCEDURE "ESCORE_FINACE_BYDAY"(strdate IN VARCHAR2,
                                                       strhour IN VARCHAR2) AS
BEGIN
  delete from t_static_orignal_byhour_sum
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
           1 as pv,
           price,
           price * pv / 1000 as cost
      from t_static_tmp_ad_pv
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
           1 as click,
           price,
           price * click as cost
      from t_static_tmp_ad_click
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
           1 as download,
           price,
           price * download as cost
      from t_static_tmp_ad_download
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
           1 as activate,
           price,
           activate * price as cost
      from t_static_tmp_ad_activate
     where 1 = 1
       and STATIC_DATE = strdate
       and STATIC_HOUR = strhour;

END ESCORE_FINACE_BYDAY;

