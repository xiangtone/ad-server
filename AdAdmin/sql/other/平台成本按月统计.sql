insert into t_finace_dev_incom_confirm_bymonth(app_id,ad_id,dev_cost,month,year) SELECT  app_id,ad_id,SUM(dev_cost),DATE_FORMAT(effect_time, '%Y-%m'),DATE_FORMAT(effect_time, '%Y-%m')  FROM  t_finace_dev_incom_confirm where status=2 GROUP BY app_id,ad_id,DATE_FORMAT(effect_time, '%Y-%m')











UPDATE  t_finace_dev_incom_confirm_bymonth t SET t.ad_name=(SELECT  placement_name   FROM v_ad_campaign WHERE id=t.ad_id) WHERE t.ad_name IS NULL


UPDATE  t_finace_dev_incom_confirm_bymonth t SET t.dev_id=(SELECT  dev_id  FROM t_application WHERE id=t.app_id) WHERE t.dev_id IS NULL 


UPDATE  t_finace_dev_incom_confirm_bymonth t SET t.app_name=(SELECT  NAME  FROM t_application WHERE id=t.app_id) WHERE t.app_name IS NULL 



UPDATE  t_finace_dev_incom_confirm_bymonth t SET t.dev_email=(SELECT  email   FROM t_developer WHERE id=t.dev_id) WHERE t.dev_email IS NULL 



UPDATE  t_finace_dev_incom_confirm_bymonth t SET t.campaign_id=(SELECT  campaign_id   FROM v_ad_campaign WHERE id=t.ad_id) WHERE t.campaign_id IS NULL






UPDATE  t_finace_dev_incom_confirm_bymonth t SET t.os=(SELECT  os  FROM t_application WHERE id=t.app_id) WHERE t.os IS NULL 
