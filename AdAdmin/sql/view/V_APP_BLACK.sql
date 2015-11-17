CREATE OR REPLACE VIEW V_APP_BLACK AS
SELECT "ID",
       "APPKEY",
       "OS",
       "DEV_ID",
       "NAME",
       "KEYWORD",
       "LONG_DESC",
       "PROJECT_URL",
       "RESOURCE_SIZE",
       "PACKAGE_NAME",
       "VERSION_NAME",
       "VERSION_CODE",
       "STATUS",
       "MANAGER_ID",
       "CHECK_TIME",
       "CHECK_MSG",
       "DEL",
       "RELEASE_TIME",
       "UPDATE_TIME",
       "LAST_UPDATE_MAN",
       "CREATE_TIME",
       "ORDERBY",
       "DOWN_TIME",
       "MARKET_URL",
       "BLACKLIST",
       "SCALE",
       "PLACEMENT",
       "CATEGORY_ID",
       "APP_ID",
       "BLACK_NAME",
        "BLACK_ID"
       
  FROM T_APPLICATION app
  LEFT JOIN (
             
             SELECT app_id,
                     (select wmsys.wm_concat(name)
                        from table(split(black)) t1
                        LEFT JOIN T_PLACEMENT c
                          on t1.column_value = c.id
                       where name is not null) black_name
                       ,
                          (select wmsys.wm_concat(rel.CAMPAIGN_ID)
                        from table(split(black)) t1
                        LEFT JOIN T_CAMPAIGN_PLACEMENT_REL rel
                          on t1.column_value = rel.PLACEMENT_ID) black_id
             
               FROM T_APP_BLACK) black
    on app.id = black.app_id;
