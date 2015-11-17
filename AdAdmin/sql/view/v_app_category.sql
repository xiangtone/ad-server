create or replace view v_app_category as
select t."ID",
       t."APPKEY",
       t."OS",
       t."DEV_ID",
       t."NAME",
       t."KEYWORD",
       t."LONG_DESC",
       t."PROJECT_URL",
       t."RESOURCE_SIZE",
       t."PACKAGE_NAME",
       t."VERSION_NAME",
       t."VERSION_CODE",
       t."STATUS",
       t."MANAGER_ID",
       t."CHECK_TIME",
       t."CHECK_MSG",
       t."DEL",
       t."RELEASE_TIME",
       t."UPDATE_TIME",
       t."LAST_UPDATE_MAN",
       t."CREATE_TIME",
       t."CATEGORY_ID",
       t."ORDERBY",
       t."DOWN_TIME",
       t."MARKET_URL",
       c1.name             as cname,
       c2.name             as fname
  from T_APPLICATION t
  left join t_escore_category c1
    on t.category_id = c1.id
  left join t_escore_category c2
    on c1.parent_id = c2.id;
