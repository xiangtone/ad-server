create or replace view v_finance_dev_award_punish as
select 

dev.email AS  DEV_EMAIL,u.REAL_NAME AS  FINANCE_NAME,

f."ID",f."DEV_ID",f."NOTE",f."ACTIVITY_BEGIN",f."ACTIVITY_END",f."FINANCE_ID",f."FINANCE_TYPE",f."CREATE_TIME",f."MONEY",f."TYPE" from T_FINANCE_DEV_AWARD_PUNISH f  left join T_SYS_USER u on f.FINANCE_ID=u.id left join T_DEVELOPER dev on  f.dev_id=dev.id;
