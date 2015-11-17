CREATE OR REPLACE VIEW V_FINACE_DEV_INCOM_CONFIRM AS
SELECT app.name as app_name,
       app.dev_email,
       app.dev_id,
       app.dev_STATUS,
       c.CAMPAIGN_NAME,
       c.CAMPAIGN_ID,
       u.real_name as submit_name,
       f."ID",
       f."APP_ID",
       f."MANAGER_ID",
       f."STATUS",
       f."CREATE_TIME",
       f."FINANCE_ID",
       f.dev_cost as confirmMoney,
       f."EFFECT_TIME"
  FROM T_FINACE_DEV_INCOM_CONFIRM F
  LEFT JOIN V_APP_DEV APP
    ON F.APP_ID = APP.ID
  LEFT JOIN  v_ad_campaign c
    ON F.AD_ID = c.ID
  LEFT JOIN T_SYS_USER u
    on f.finance_id = u.id;
