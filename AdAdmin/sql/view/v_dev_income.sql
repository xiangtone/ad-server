create or replace view v_dev_income as
select app.dev_id,s."CLICK",s."STATIC_DATE",s."APP_ID",s."TYPE_ID",s."POSPV",s."PV",s."DOWNLOAD",s."ACTIVATE",s."COST",s."SYSPV",s."SYSCLICK",s."SYSDOWNLOAD",s."SYSACTIVATE",s."CLICKD" from T_STATIC_APP_BYDAY s left join T_APPLICATION app on s.app_id=app.id;
