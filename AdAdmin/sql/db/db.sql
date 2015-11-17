-- ----------------------------
-- View structure for v_ad 
-- ----------------------------
DROP VIEW IF EXISTS `v_ad`;
CREATE  VIEW `v_ad` AS (select `ad`.`id` AS `ID`,`ad`.`placement_id` AS `PLACEMENT_ID`,`ad`.`budget_day` AS `BUDGET_DAY`,`ad`.`cost_day` AS `COST_DAY`,`ad`.`budget_type` AS `BUDGET_TYPE`,`ad`.`blance_price` AS `BLANCE_PRICE`,`ad`.`blance_mode` AS `BLANCE_MODE`,`ad`.`type_id` AS `TYPE_ID`,`ad`.`media_id` AS `MEDIA_ID`,`ad`.`create_time` AS `CREATE_TIME`,`ad`.`package_id` AS `PACKAGE_ID`,`ad`.`status` AS `STATUS`,`ad`.`start_time` AS `START_TIME`,`ad`.`end_time` AS `END_TIME`,`ad`.`create_user` AS `CREATE_USER`,`ad`.`update_time` AS `UPDATE_TIME`,`ad`.`ad_type` AS `AD_TYPE`,`ad`.`online_time` AS `ONLINE_TIME`,`ad`.`offline_time` AS `OFFLINE_TIME`,`channel`.`id` AS `midia_id`,`channel`.`name` AS `media_name`,NULL AS `os` from (`t_ad` `ad` left join `t_channel` `channel` on((`ad`.`media_id` = `channel`.`id`))) where (`ad`.`ad_type` = 1)) union all (select `ad`.`id` AS `ID`,`ad`.`placement_id` AS `PLACEMENT_ID`,`ad`.`budget_day` AS `BUDGET_DAY`,`ad`.`cost_day` AS `COST_DAY`,`ad`.`budget_type` AS `BUDGET_TYPE`,`ad`.`blance_price` AS `BLANCE_PRICE`,`ad`.`blance_mode` AS `BLANCE_MODE`,`ad`.`type_id` AS `TYPE_ID`,`ad`.`media_id` AS `MEDIA_ID`,`ad`.`create_time` AS `CREATE_TIME`,`ad`.`package_id` AS `PACKAGE_ID`,`ad`.`status` AS `STATUS`,`ad`.`start_time` AS `START_TIME`,`ad`.`end_time` AS `END_TIME`,`ad`.`create_user` AS `CREATE_USER`,`ad`.`update_time` AS `UPDATE_TIME`,`ad`.`ad_type` AS `AD_TYPE`,`ad`.`online_time` AS `ONLINE_TIME`,`ad`.`offline_time` AS `OFFLINE_TIME`,`app`.`id` AS `midia_id`,`app`.`name` AS `media_name`,`app`.`os` AS `os` from (`t_ad` `ad` left join `t_application` `app` on((`ad`.`media_id` = `app`.`id`))) where (`ad`.`ad_type` = 0));

-- ----------------------------
-- View structure for v_ad_cam
-- ----------------------------
DROP VIEW IF EXISTS `v_ad_cam`;
CREATE  VIEW `v_ad_cam` AS (select `rel`.`campaign_id` AS `CAMPAIGN_ID`,`ad`.`ad_type` AS `AD_TYPE`,`ad`.`id` AS `ID` from (`t_ad` `ad` left join `t_campaign_placement_rel` `rel` on((`ad`.`placement_id` = `rel`.`placement_id`))));

-- ----------------------------
-- View structure for v_ad_campaign
-- ----------------------------
DROP VIEW IF EXISTS `v_ad_campaign`;
CREATE  VIEW `v_ad_campaign` AS select `adv`.`id` AS `adv_id`,`adv`.`email` AS `adv_email`,`adv`.`company_name` AS `adv_name`,`c`.`id` AS `CAMPAIGN_ID`,`c`.`campaign_name` AS `CAMPAIGN_NAME`,`c`.`price` AS `price`,`p`.`name` AS `placement_name`,`p`.`os` AS `os`,`ad`.`ID` AS `ID`,`ad`.`PLACEMENT_ID` AS `PLACEMENT_ID`,`ad`.`BUDGET_DAY` AS `BUDGET_DAY`,`ad`.`BLANCE_PRICE` AS `BLANCE_PRICE`,`ad`.`BLANCE_MODE` AS `BLANCE_MODE`,`ad`.`TYPE_ID` AS `TYPE_ID`,`ad`.`MEDIA_ID` AS `MEDIA_ID`,`ad`.`CREATE_TIME` AS `CREATE_TIME`,`ad`.`PACKAGE_ID` AS `PACKAGE_ID`,`ad`.`STATUS` AS `STATUS`,`ad`.`START_TIME` AS `START_TIME`,`ad`.`END_TIME` AS `END_TIME`,`ad`.`CREATE_USER` AS `CREATE_USER`,`ad`.`UPDATE_TIME` AS `UPDATE_TIME`,`ad`.`AD_TYPE` AS `AD_TYPE`,`ad`.`ONLINE_TIME` AS `ONLINE_TIME`,`ad`.`OFFLINE_TIME` AS `OFFLINE_TIME`,`ad`.`midia_id` AS `MIDIA_ID`,`ad`.`media_name` AS `MEDIA_NAME`,`ad`.`COST_DAY` AS `COST_DAY`,`ad`.`BUDGET_TYPE` AS `BUDGET_TYPE`,greatest(`ad`.`CREATE_TIME`,ifnull(`ad`.`UPDATE_TIME`,'2000-05-07 13:23:44')) AS `release_time` from ((((`v_ad` `ad` left join `t_placement` `p` on((`ad`.`PLACEMENT_ID` = `p`.`id`))) left join `t_campaign_placement_rel` `rel` on((`rel`.`placement_id` = `p`.`id`))) left join `t_campaign` `c` on((`rel`.`campaign_id` = `c`.`id`))) left join `t_advertiser` `adv` on((`c`.`adv_id` = `adv`.`id`)));

-- ----------------------------
-- View structure for v_ad_effect
-- ----------------------------
DROP VIEW IF EXISTS `v_ad_effect`;
CREATE  VIEW `v_ad_effect` AS select `e`.`id` AS `ID`,`e`.`static_date` AS `STATIC_DATE`,`e`.`sys_activate` AS `SYS_ACTIVATE`,`e`.`media_id` AS `MEDIA_ID`,`e`.`type_id` AS `TYPE_ID`,`e`.`package_id` AS `PACKAGE_ID`,`e`.`create_time` AS `CREATE_TIME`,`e`.`parent_id` AS `PARENT_ID`,`e`.`confirm_num` AS `CONFIRM_NUM`,`e`.`in_price` AS `IN_PRICE`,`e`.`sys_cost` AS `SYS_COST`,`e`.`confirm_amount` AS `CONFIRM_AMOUNT`,`e`.`status` AS `STATUS`,`e`.`out_price` AS `OUT_PRICE`,`e`.`media_type` AS `MEDIA_TYPE`,`e`.`blance_mode` AS `BLANCE_MODE`,`e`.`submit_time` AS `SUBMIT_TIME`,`v`.`ID` AS `campaign_id`,`e`.`invoice_status` AS `INVOICE_STATUS`,`e`.`send_status` AS `SEND_STATUS` from ((`t_package_activate_detail` `e` left join `t_placement_package` `t` on((`e`.`package_id` = `t`.`id`))) left join `v_campaign` `v` on((`t`.`placement_id` = `v`.`PLACEMENT_id`))) where (`e`.`status` = 1);

-- ----------------------------
-- View structure for v_app_category
-- ----------------------------
DROP VIEW IF EXISTS `v_app_category`;
CREATE  VIEW `v_app_category` AS select `t`.`id` AS `ID`,`t`.`appkey` AS `APPKEY`,`t`.`os` AS `OS`,`t`.`dev_id` AS `DEV_ID`,`t`.`name` AS `NAME`,`t`.`keyword` AS `KEYWORD`,`t`.`long_desc` AS `LONG_DESC`,`t`.`project_url` AS `PROJECT_URL`,`t`.`resource_size` AS `RESOURCE_SIZE`,`t`.`package_name` AS `PACKAGE_NAME`,`t`.`version_name` AS `VERSION_NAME`,`t`.`version_code` AS `VERSION_CODE`,`t`.`status` AS `STATUS`,`t`.`manager_id` AS `MANAGER_ID`,`t`.`check_time` AS `CHECK_TIME`,`t`.`check_msg` AS `CHECK_MSG`,`t`.`del` AS `DEL`,`t`.`release_time` AS `RELEASE_TIME`,`t`.`update_time` AS `UPDATE_TIME`,`t`.`last_update_man` AS `LAST_UPDATE_MAN`,`t`.`create_time` AS `CREATE_TIME`,`t`.`category_id` AS `CATEGORY_ID`,`t`.`orderby` AS `ORDERBY`,`t`.`down_time` AS `DOWN_TIME`,`t`.`market_url` AS `MARKET_URL`,`c1`.`name` AS `cname`,`c2`.`name` AS `fname` from ((`t_application` `t` left join `t_escore_category` `c1` on((`t`.`category_id` = `c1`.`id`))) left join `t_escore_category` `c2` on((`c1`.`parent_id` = `c2`.`id`)));

-- ----------------------------
-- View structure for v_app_dev
-- ----------------------------
DROP VIEW IF EXISTS `v_app_dev`;
CREATE  VIEW `v_app_dev` AS select `dev`.`real_name` AS `dev_name`,`dev`.`email` AS `dev_email`,`dev`.`status` AS `dev_STATUS`,`app`.`id` AS `ID`,`app`.`appkey` AS `APPKEY`,`app`.`os` AS `OS`,`app`.`dev_id` AS `DEV_ID`,`app`.`category_id` AS `CATEGORY_ID`,`app`.`name` AS `NAME`,`app`.`keyword` AS `KEYWORD`,`app`.`long_desc` AS `LONG_DESC`,`app`.`project_url` AS `PROJECT_URL`,`app`.`resource_size` AS `RESOURCE_SIZE`,`app`.`package_name` AS `PACKAGE_NAME`,`app`.`version_name` AS `VERSION_NAME`,`app`.`version_code` AS `VERSION_CODE`,`app`.`status` AS `STATUS`,`app`.`manager_id` AS `MANAGER_ID`,`app`.`check_time` AS `CHECK_TIME`,`app`.`check_msg` AS `CHECK_MSG`,`app`.`del` AS `DEL`,`app`.`release_time` AS `RELEASE_TIME`,`app`.`update_time` AS `UPDATE_TIME`,`app`.`last_update_man` AS `LAST_UPDATE_MAN`,`app`.`create_time` AS `CREATE_TIME`,`app`.`app_res` AS `app_res`,`app`.`app_manager` AS `app_manager`,`app`.`orderby` AS `ORDERBY`,`app`.`down_time` AS `DOWN_TIME`,`app`.`scale` AS `scale` from (`t_application` `app` left join `t_developer` `dev` on((`app`.`dev_id` = `dev`.`id`)));

-- ----------------------------
-- View structure for v_app_page
-- ----------------------------
DROP VIEW IF EXISTS `v_app_page`;
CREATE  VIEW `v_app_page` AS select `page`.`id` AS `id`,`page`.`status` AS `status`,`page`.`rate` AS `rate`,`v`.`scale` AS `scale`,`v`.`DEV_ID` AS `dev_id`,`v`.`dev_name` AS `dev_name`,`v`.`dev_email` AS `dev_email`,`v`.`ID` AS `app_id`,`v`.`NAME` AS `app_name`,`v`.`OS` AS `os`,`type`.`name` AS `type_name`,`type`.`id` AS `type_id` from ((`t_page` `page` left join `v_app_dev` `v` on((`page`.`app_id` = `v`.`ID`))) left join `t_type` `type` on((`page`.`type_id` = `type`.`id`)));

-- ----------------------------
-- View structure for v_app_sort
-- ----------------------------
DROP VIEW IF EXISTS `v_app_sort`;
CREATE  VIEW `v_app_sort` AS select `app`.`id` AS `id`,`app`.`name` AS `name`,`app`.`category_id` AS `category_id`,`s`.`name` AS `sort_name` from (`t_application` `app` left join `t_escore_sort` `s` on((`app`.`category_id` = `s`.`id`)));

-- ----------------------------
-- View structure for v_campaign
-- ----------------------------
DROP VIEW IF EXISTS `v_campaign`;
CREATE  VIEW `v_campaign` AS select `c`.`id` AS `ID`,`c`.`campaign_name` AS `CAMPAIGN_NAME`,`c`.`price` AS `PRICE`,`c`.`operater_id` AS `OPERATER_ID`,`c`.`create_time` AS `CREATE_TIME`,`c`.`adv_id` AS `ADV_ID`,`c`.`terminal_type` AS `TERMINAL_TYPE`,`c`.`operat_agencies` AS `OPERAT_AGENCIES`,`c`.`phone_brand` AS `PHONE_BRAND`,`c`.`app_type` AS `APP_TYPE`,`c`.`time_directional` AS `TIME_DIRECTIONAL`,`c`.`area_directional` AS `AREA_DIRECTIONAL`,`c`.`charge_type` AS `CHARGE_TYPE`,`c`.`category_id` AS `CATEGORY_ID`,`c`.`create_user` AS `CREATE_USER`,`c`.`campaign_type` AS `CAMPAIGN_TYPE`,`c`.`budget` AS `BUDGET`,`c`.`profit_rate` AS `profit_rate`,`c`.`campaign_required` AS `CAMPAIGN_REQUIRED`,`c`.`confirm_mode` AS `confirm_mode`,`rel`.`status` AS `status`,`p`.`os` AS `os`,`p`.`id` AS `PLACEMENT_id`,`p`.`plan_start` AS `plan_start`,`p`.`plan_end` AS `plan_end`,`p`.`name` AS `placement_name`,`p`.`category_id` AS `placement_CATEGORY_ID`,`p`.`star_level` AS `STAR_LEVEL`,`u`.`real_name` AS `CREATE_USER_NAME`,`adv`.`company_name` AS `company_name`,`adv`.`email` AS `adv_email` from ((((`t_campaign_placement_rel` `rel` left join `t_campaign` `c` on((`rel`.`campaign_id` = `c`.`id`))) left join `t_placement` `p` on((`rel`.`placement_id` = `p`.`id`))) left join `t_sys_user` `u` on((`c`.`create_user` = `u`.`id`))) left join `t_advertiser` `adv` on((`c`.`adv_id` = `adv`.`id`)));

-- ----------------------------
-- View structure for v_campaign_category
-- ----------------------------
DROP VIEW IF EXISTS `v_campaign_category`;
CREATE  VIEW `v_campaign_category` AS select `t`.`id` AS `ID`,`t`.`campaign_id` AS `CAMPAIGN_ID`,`t`.`category_id` AS `CATEGORY_ID`,`c`.`type` AS `TYPE`,`t`.`create_time` AS `CREATE_TIME`,`c`.`content_value` AS `content_value`,`c`.`sort` AS `sort`,`c`.`name` AS `name` from (`t_campaign_category_rel` `t` left join `t_escore_sort` `c` on((`t`.`category_id` = `c`.`id`)));

-- ----------------------------
-- View structure for v_dev_apply_money
-- ----------------------------
DROP VIEW IF EXISTS `v_dev_apply_money`;
CREATE  VIEW `v_dev_apply_money` AS select `dev`.`email` AS `dev_email`,`dev`.`finance_income` AS `dev_balance`,`u`.`real_name` AS `manager_name`,`t`.`account_hoder` AS `kaihu_name`,`u2`.`real_name` AS `finance_name`,`t`.`id` AS `ID`,`t`.`dev_id` AS `DEV_ID`,`t`.`pay_type` AS `PAY_TYPE`,`t`.`manager_id` AS `MANAGER_ID`,`t`.`manager_money` AS `MANAGER_MONEY`,`t`.`manager_time` AS `MANAGER_TIME`,`t`.`apply_money` AS `APPLY_MONEY`,`t`.`status` AS `STATUS`,`t`.`manager_desc` AS `MANAGER_DESC`,`t`.`finance_id` AS `FINANCE_ID`,`t`.`finance_money` AS `FINANCE_MONEY`,`t`.`balance_money` AS `BALANCE_MONEY`,`t`.`finance_tax` AS `FINANCE_TAX`,`t`.`finance_dues` AS `FINANCE_DUES`,`t`.`finance_time` AS `FINANCE_TIME`,`t`.`create_time` AS `CREATE_TIME`,`t`.`tax_status` AS `TAX_STATUS`,`t`.`bank_name` AS `BANK_NAME`,`t`.`bank_subbranch` AS `BANK_SUBBRANCH`,`t`.`bank_account` AS `BANK_ACCOUNT`,`t`.`invoice` AS `invoice`,`t`.`bank_city` AS `BANK_CITY` from (((`t_dev_apply_money` `t` left join `t_developer` `dev` on((`t`.`dev_id` = `dev`.`id`))) left join `t_sys_user` `u` on((`t`.`manager_id` = `u`.`id`))) left join `t_sys_user` `u2` on((`t`.`finance_id` = `u2`.`id`)));

-- ----------------------------
-- View structure for v_dev_email
-- ----------------------------
DROP VIEW IF EXISTS `v_dev_email`;
CREATE  VIEW `v_dev_email` AS (select `escore`.`t_developer`.`email` AS `email` from `t_developer` where (`escore`.`t_developer`.`resource_code` <> 'KUAIYOU')) union all (select `escore`.`t_dev_email_old`.`email` AS `email` from `t_dev_email_old`);

-- ----------------------------
-- View structure for v_dev_income
-- ----------------------------
DROP VIEW IF EXISTS `v_dev_income`;
CREATE  VIEW `v_dev_income` AS select `app`.`dev_id` AS `dev_id`,`s`.`click` AS `CLICK`,`s`.`static_date` AS `STATIC_DATE`,`s`.`app_id` AS `APP_ID`,`s`.`type_id` AS `TYPE_ID`,`s`.`pospv` AS `POSPV`,`s`.`pv` AS `PV`,`s`.`download` AS `DOWNLOAD`,`s`.`activate` AS `ACTIVATE`,`s`.`cost` AS `COST`,`s`.`syspv` AS `SYSPV`,`s`.`sysclick` AS `SYSCLICK`,`s`.`sysdownload` AS `SYSDOWNLOAD`,`s`.`sysactivate` AS `SYSACTIVATE`,`s`.`clickd` AS `CLICKD` from (`t_static_app_byday` `s` left join `t_application` `app` on((`s`.`app_id` = `app`.`id`)));

-- ----------------------------
-- View structure for v_escore_category
-- ----------------------------
DROP VIEW IF EXISTS `v_escore_category`;
CREATE  VIEW `v_escore_category` AS select `t1`.`id` AS `ID`,`t1`.`parent_id` AS `PARENT_ID`,`t1`.`name` AS `NAME`,`t1`.`sort` AS `SORT`,`t1`.`create_time` AS `CREATE_TIME`,`t2`.`name` AS `parent_name` from (`t_escore_category` `t1` left join `t_escore_category` `t2` on((`t1`.`parent_id` = `t2`.`id`)));

-- ----------------------------
-- View structure for v_finace_dev_incom_confirm
-- ----------------------------
DROP VIEW IF EXISTS `v_finace_dev_incom_confirm`;
CREATE  VIEW `v_finace_dev_incom_confirm` AS select `app`.`NAME` AS `app_name`,`app`.`dev_email` AS `dev_email`,`app`.`DEV_ID` AS `dev_id`,`app`.`dev_STATUS` AS `dev_STATUS`,`c`.`CAMPAIGN_NAME` AS `CAMPAIGN_NAME`,`c`.`CAMPAIGN_ID` AS `CAMPAIGN_ID`,`ou`.`real_name` AS `manager_name`,`f`.`manager_time` AS `manager_time`,`u`.`real_name` AS `finance_name`,`f`.`id` AS `ID`,`f`.`app_id` AS `APP_ID`,`f`.`manager_id` AS `MANAGER_ID`,`f`.`status` AS `STATUS`,`f`.`create_time` AS `CREATE_TIME`,`f`.`finance_id` AS `FINANCE_ID`,`f`.`finance_time` AS `FINANCE_TIME`,`f`.`finance_money` AS `finance_money`,`f`.`dev_cost` AS `confirmMoney`,`f`.`effect_num` AS `effect_num`,`f`.`effect_time` AS `EFFECT_TIME` from ((((`t_finace_dev_incom_confirm` `f` left join `v_app_dev` `app` on((`f`.`app_id` = `app`.`ID`))) left join `v_ad_campaign` `c` on((`f`.`ad_id` = `c`.`ID`))) left join `t_sys_user` `u` on((`f`.`finance_id` = `u`.`id`))) left join `t_sys_user` `ou` on((`f`.`manager_id` = `ou`.`id`)));

-- ----------------------------
-- View structure for v_finance_dev_award_punish
-- ----------------------------
DROP VIEW IF EXISTS `v_finance_dev_award_punish`;
CREATE  VIEW `v_finance_dev_award_punish` AS select `dev`.`email` AS `DEV_EMAIL`,`u`.`real_name` AS `FINANCE_NAME`,`f`.`id` AS `ID`,`f`.`dev_id` AS `DEV_ID`,`f`.`note` AS `NOTE`,`f`.`activity_begin` AS `ACTIVITY_BEGIN`,`f`.`activity_end` AS `ACTIVITY_END`,`f`.`finance_id` AS `FINANCE_ID`,`f`.`finance_type` AS `FINANCE_TYPE`,`f`.`create_time` AS `CREATE_TIME`,`f`.`money` AS `MONEY`,`f`.`type` AS `TYPE` from ((`t_finance_dev_award_punish` `f` left join `t_sys_user` `u` on((`f`.`finance_id` = `u`.`id`))) left join `t_developer` `dev` on((`f`.`dev_id` = `dev`.`id`)));

-- ----------------------------
-- View structure for v_income_outcome
-- ----------------------------
DROP VIEW IF EXISTS `v_income_outcome`;
CREATE  VIEW `v_income_outcome` AS select `t1`.`id` AS `ID`,'android' AS `os`,`cmp`.`campaign_name` AS `CAMPAIGN_name`,(case when (`v`.`TYPE` = 0) then '平台' when (`v`.`TYPE` = 1) then '渠道' end) AS `type`,`v`.`NAME` AS `meia_name`,`t1`.`static_date` AS `STATIC_DATE`,`t1`.`sys_activate` AS `SYS_ACTIVATE`,`t1`.`media_id` AS `MEDIA_ID`,`t1`.`type_id` AS `TYPE_ID`,`t1`.`package_id` AS `PACKAGE_ID`,`t1`.`confirm_num` AS `CONFIRM_NUM`,`t1`.`in_price` AS `IN_PRICE`,`t1`.`sys_cost` AS `SYS_COST`,`t1`.`confirm_amount` AS `CONFIRM_AMOUNT`,`t1`.`out_price` AS `out_price`,`rel`.`CAMPAIGN_ID` AS `CAMPAIGN_ID`,`cmp`.`profit_rate` AS `profit_rate`,`cmp`.`adv_id` AS `adv_id` from ((((`t_package_activate_detail` `t1` left join `t_package_activate` `a` on((`t1`.`parent_id` = `a`.`id`))) left join `v_media` `v` on((`t1`.`media_id` = `v`.`ID`))) left join `v_package_campaign_rel` `rel` on((`t1`.`package_id` = `rel`.`ID`))) left join `t_campaign` `cmp` on((`rel`.`CAMPAIGN_ID` = `cmp`.`id`))) where ((`a`.`status` = 1) and (`t1`.`status` = 1));

-- ----------------------------
-- View structure for v_income_outcome_sales
-- ----------------------------
DROP VIEW IF EXISTS `v_income_outcome_sales`;
CREATE  VIEW `v_income_outcome_sales` AS select `v`.`ID` AS `ID`,`v`.`os` AS `OS`,`v`.`CAMPAIGN_name` AS `CAMPAIGN_NAME`,`v`.`type` AS `TYPE`,`v`.`meia_name` AS `MEIA_NAME`,`v`.`STATIC_DATE` AS `STATIC_DATE`,`v`.`SYS_ACTIVATE` AS `SYS_ACTIVATE`,`v`.`MEDIA_ID` AS `MEDIA_ID`,`v`.`TYPE_ID` AS `TYPE_ID`,`v`.`PACKAGE_ID` AS `PACKAGE_ID`,`v`.`CONFIRM_NUM` AS `CONFIRM_NUM`,`v`.`IN_PRICE` AS `IN_PRICE`,`v`.`SYS_COST` AS `SYS_COST`,`v`.`CONFIRM_AMOUNT` AS `CONFIRM_AMOUNT`,`v`.`out_price` AS `OUT_PRICE`,`v`.`CAMPAIGN_ID` AS `CAMPAIGN_ID`,`v`.`profit_rate` AS `PROFIT_RATE`,`v`.`adv_id` AS `ADV_ID`,`sel`.`NAME` AS `NAME` from (`v_income_outcome` `v` left join `v_campaign_salesman` `sel` on((`v`.`CAMPAIGN_ID` = `sel`.`ID`)));

-- ----------------------------
-- View structure for v_ios_activate
-- ----------------------------
DROP VIEW IF EXISTS `v_ios_activate`;
CREATE  VIEW `v_ios_activate` AS select `rel`.`campaign_name` AS `CAMPAIGN_NAME`,`p`.`campaign_id` AS `CAMPAIGN_ID`,`p`.`id` AS `id`,`p`.`status` AS `status` from (`t_ios_activate_num` `p` left join `t_campaign` `rel` on((`p`.`campaign_id` = `rel`.`id`)));

-- ----------------------------
-- View structure for v_ios_income_outcome
-- ----------------------------
DROP VIEW IF EXISTS `v_ios_income_outcome`;
CREATE  VIEW `v_ios_income_outcome` AS select str_to_date(`a`.`static_date`,'%Y-%m-%d %T') AS `static_date`,`a`.`type_id` AS `type_id`,`a`.`confirm_num` AS `confirm_num`,`a`.`confirm_amount` AS `confirm_amount`,`a`.`blance_mode` AS `blance_mode`,`p`.`CAMPAIGN_ID` AS `campaign_id`,`p`.`CAMPAIGN_NAME` AS `CAMPAIGN_NAME`,`camp`.`placement_name` AS `placement_name`,`camp`.`CHARGE_TYPE` AS `charge_type`,`m`.`NAME` AS `media_name`,`e`.`name` AS `type_name`,`a`.`out_price` AS `out_price`,`a`.`sys_cost` AS `sys_cost`,`t`.`price` AS `price`,(`a`.`confirm_num` * `t`.`price`) AS `sys_income`,`adv`.`company_name` AS `公司名称`,`adv`.`seal_man_name` AS `销售` from ((((((`t_ios_activate_num_detail` `a` left join `v_ios_activate` `p` on((`a`.`parent_id` = `p`.`id`))) left join `v_media` `m` on((`a`.`media_id` = `m`.`ID`))) left join `t_type` `e` on((`a`.`type_id` = `e`.`id`))) left join `t_ios_activate_num` `t` on((`a`.`parent_id` = `t`.`id`))) left join `v_campaign` `camp` on((`p`.`CAMPAIGN_ID` = `camp`.`ID`))) left join `v_adv` `adv` on((`camp`.`ADV_ID` = `adv`.`id`))) where ((`p`.`status` = 9) and (`a`.`status` = 9)) order by `a`.`id` desc;

-- ----------------------------
-- View structure for v_media
-- ----------------------------
DROP VIEW IF EXISTS `v_media`;
CREATE  VIEW `v_media` AS (select `t_application`.`id` AS `ID`,`t_application`.`name` AS `NAME`,0 AS `TYPE` from `t_application` where (`t_application`.`id` = `t_application`.`placement`)) union (select `t_channel`.`id` AS `ID`,`t_channel`.`name` AS `NAME`,1 AS `TYPE` from `t_channel`) union all (select 13159 AS `id`,'学生赚' AS `name`,0 AS `type`);

-- ----------------------------
-- View structure for v_media_name
-- ----------------------------
DROP VIEW IF EXISTS `v_media_name`;
CREATE  VIEW `v_media_name` AS select `c`.`id` AS `id`,`c`.`name` AS `media_name` from `t_channel` `c` where (`c`.`status` = 1) union all select `d`.`id` AS `id`,`d`.`name` AS `media_name` from `t_application` `d`;

-- ----------------------------
-- View structure for v_package_campaign_rel
-- ----------------------------
DROP VIEW IF EXISTS `v_package_campaign_rel`;
CREATE  VIEW `v_package_campaign_rel` AS select `pak`.`id` AS `ID`,`pak`.`placement_id` AS `PLACEMENT_ID`,`rel`.`campaign_id` AS `CAMPAIGN_ID` from (`t_placement_package` `pak` left join `t_campaign_placement_rel` `rel` on((`pak`.`placement_id` = `rel`.`placement_id`)));

-- ----------------------------
-- View structure for v_placement_package
-- ----------------------------
DROP VIEW IF EXISTS `v_placement_package`;
CREATE  VIEW `v_placement_package` AS select `c`.`campaign_name` AS `CAMPAIGN_NAME`,`rel`.`campaign_id` AS `CAMPAIGN_ID`,`p`.`id` AS `ID`,`p`.`res_url` AS `RES_URL`,`p`.`create_user` AS `CREATE_USER`,`p`.`code` AS `CODE`,`p`.`file_name` AS `FILE_NAME`,`p`.`placement_id` AS `PLACEMENT_ID`,`p`.`package_name` AS `PACKAGE_NAME`,`p`.`version_name` AS `VERSION_NAME`,`p`.`version_code` AS `VERSION_CODE`,`p`.`create_time` AS `CREATE_TIME`,`p`.`status` AS `STATUS`,`p`.`file_size` AS `FILE_SIZE`,`placement`.`os` AS `OS`,`p`.`remarks` AS `remarks` from (((`t_placement_package` `p` left join `t_campaign_placement_rel` `rel` on((`p`.`placement_id` = `rel`.`placement_id`))) left join `t_campaign` `c` on((`rel`.`campaign_id` = `c`.`id`))) left join `t_placement` `placement` on((`p`.`placement_id` = `placement`.`id`)));

-- ----------------------------
-- View structure for v_resource_permission
-- ----------------------------
DROP VIEW IF EXISTS `v_resource_permission`;
CREATE  VIEW `v_resource_permission` AS select `res`.`id` AS `ID`,`res`.`name` AS `NAME`,`res`.`url` AS `URL`,`res`.`parent_id` AS `PARENT_ID`,`res`.`note` AS `NOTE`,`res`.`order_num` AS `ORDER_NUM`,`res`.`create_time` AS `CREATE_TIME`,`res`.`stat_report_id` AS `STAT_REPORT_ID`,`res`.`status` AS `STATUS`,`res`.`type` AS `TYPE`,`res`.`create_user` AS `CREATE_USER`,`res`.`update_time` AS `UPDATE_TIME`,`res`.`update_user` AS `UPDATE_USER`,`rel`.`permission_id` AS `permission_id` from (`t_sys_resource` `res` left join `t_sys_permission_res_rel` `rel` on((`res`.`id` = `rel`.`resource_id`)));

-- ----------------------------
-- View structure for v_sys_income
-- ----------------------------
DROP VIEW IF EXISTS `v_sys_income`;
CREATE  VIEW `v_sys_income` AS select `v`.`ID` AS `ID`,`v`.`os` AS `系统`,`v`.`CAMPAIGN_name` AS `活动名称`,`v`.`type` AS `平台类型`,`v`.`meia_name` AS `媒体名称`,str_to_date(`v`.`STATIC_DATE`,'%Y-%m-%d') AS `日期`,`v`.`SYS_ACTIVATE` AS `系统激活数`,(case when (`v`.`TYPE_ID` = 0) then '积分墙' when (`v`.`TYPE_ID` = 1) then '推荐墙' when (`v`.`TYPE_ID` = 4) then 'BANNER' when (`v`.`TYPE_ID` = 5) then '插屏' end) AS `广告形式`,`v`.`PACKAGE_ID` AS `包ID`,`v`.`CONFIRM_NUM` AS `广告主确认数`,`v`.`IN_PRICE` AS `接入单价`,`v`.`SYS_COST` AS `系统成本`,`v`.`CONFIRM_AMOUNT` AS `广告主确认数2`,`v`.`out_price` AS `投放单价`,`v`.`CAMPAIGN_ID` AS `活动ID`,`v`.`profit_rate` AS `加价率`,`t`.`remarks` AS `渠道包号`,`p`.`name` AS `投放名称`,(`v`.`IN_PRICE` * `v`.`CONFIRM_NUM`) AS `广告收入`,`adv`.`company_name` AS `公司名称`,`adv`.`seal_man_name` AS `销售`,`cp`.`charge_type` AS `考核方式`,`s`.`name` AS `分类` from (((((`v_income_outcome` `v` left join `t_placement_package` `t` on((`v`.`PACKAGE_ID` = `t`.`id`))) left join `t_placement` `p` on((`t`.`placement_id` = `p`.`id`))) left join `t_campaign` `cp` on((`v`.`CAMPAIGN_ID` = `cp`.`id`))) left join `v_adv` `adv` on((`cp`.`adv_id` = `adv`.`id`))) left join `t_escore_sort` `s` on((`p`.`category_id` = `s`.`id`)));

-- ----------------------------
-- View structure for view_user
-- ----------------------------
DROP VIEW IF EXISTS `view_user`;
CREATE  VIEW `view_user` AS select `t_advertiser`.`email` AS `email`,`t_advertiser`.`password` AS `password`,1 AS `type`,`t_advertiser`.`status` AS `status` from `t_advertiser` union all select `t_channel`.`email` AS `email`,`t_channel`.`password` AS `password`,3 AS `type`,`t_channel`.`status` AS `status` from `t_channel` union all select `t_developer`.`email` AS `email`,`t_developer`.`password` AS `password`,2 AS `type`,`t_developer`.`status` AS `status` from `t_developer`;

-- ----------------------------
-- Procedure structure for ESCORE_FINACE_BYDAY
-- ----------------------------
DROP PROCEDURE IF EXISTS `ESCORE_FINACE_BYDAY`;
DELIMITER ;;
CREATE  PROCEDURE `ESCORE_FINACE_BYDAY`(IN strdate VARCHAR(10), IN strhour VARCHAR(2))
BEGIN
  delete from t_static_orignal_byhour_sum
   where 1 = 1
     and STATIC_DATE = strdate
     and STATIC_HOUR = strhour;
  
  insert into t_static_orignal_byhour_sum
    (static_date, static_hour, app_id, channel, uuid, area, ip, init)
    select static_date, static_hour, app_id, channel, uuid, area, ip, init
      from t_static_tmp_user_init
     where 1 = 1
       and STATIC_DATE = strdate
       and STATIC_HOUR = strhour;
  
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
             when app_id = '247' then 
              0
             else
              price*ifnull(a.scale, 1) * 1 / 1000
           end) as cost
           
      from t_static_tmp_ad_pv t left join t_application a on t.app_id=a.id
     where 1 = 1
       and STATIC_DATE = strdate
       and STATIC_HOUR = strhour;
  
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
             when app_id = '247' then 
              0 
             else
              price*ifnull(a.scale, 1) * 1
           end) as cost
      from t_static_tmp_ad_click t left join t_application a on t.app_id=a.id
     where 1 = 1
       and STATIC_DATE = strdate
       and STATIC_HOUR = strhour;
       
 
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
             when app_id = '247' then 
              0
             when (type_id='5') then
              0
             else 
              price*ifnull(a.scale, 1) * 1
           end) as cost
      from t_static_tmp_ad_clickd t left join t_application a on t.app_id=a.id
     where 1 = 1
       and STATIC_DATE = strdate
       and STATIC_HOUR = strhour;
  
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
             when app_id = '247' then 
              1 
             else
              price *ifnull(a.scale, 1)* 1
           end) as cost
      from t_static_tmp_ad_download t left join t_application a on t.app_id=a.id
     where 1 = 1
       and STATIC_DATE = strdate
       and STATIC_HOUR = strhour;
  
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
             when app_id = '247' then 
              0
             else
              price *ifnull(a.scale, 1) *  1
           end) as cost
      from t_static_tmp_ad_activate  t left join t_application a on t.app_id=a.id
     where 1 = 1
       and STATIC_DATE = strdate
       and STATIC_HOUR = strhour;
END;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for ESCORE_MERGE_DATA_BYHOUR
-- ----------------------------
DROP PROCEDURE IF EXISTS `ESCORE_MERGE_DATA_BYHOUR`;
DELIMITER ;;
CREATE  PROCEDURE `ESCORE_MERGE_DATA_BYHOUR`(IN strdate VARCHAR(10), IN strhour VARCHAR(2))
BEGIN
  delete from t_static_orignal_byhour_sum
   where 1 = 1
     and STATIC_DATE = strdate
     and STATIC_HOUR = strhour;
  
  insert into t_static_orignal_byhour_sum
    (static_date, static_hour, app_id, channel, uuid, area, ip, init)
    select static_date, static_hour, app_id, channel, uuid, area, ip, init
      from t_static_tmp_user_init
     where 1 = 1
       and STATIC_DATE = strdate
       and STATIC_HOUR = strhour;
  
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
             when app_id = '247' then
              0
             when (app_id = '14278' and type_id = 5) then
              0.03
             else
              price * ifnull(a.scale, 1) * 1 / 1000
           end) as cost
      from t_static_tmp_ad_pv t left join t_application a on t.app_id=a.id
     where 1 = 1
       and STATIC_DATE = strdate
       and STATIC_HOUR = strhour;
  
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
             when app_id = '247' then
              0
             when (app_id = '14278' and type_id = 5) then
              0
             else
              price * IFNULL(a.scale, 1) * 1
           end) as cost
      from t_static_tmp_ad_click t left join t_application a on t.app_id=a.id
     where 1 = 1
       and STATIC_DATE = strdate
       and STATIC_HOUR = strhour;
       
 
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
             when app_id = '247' then
              0
             when (type_id = '5') then
              0
             else
              price * IFNULL(a.scale, 1) * 1
           end) as cost
      from t_static_tmp_ad_clickd t left join t_application a on t.app_id=a.id
     where 1 = 1
       and STATIC_DATE = strdate
       and STATIC_HOUR = strhour;
  
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
             when app_id = '247' then
              1.1
             when (app_id = '14278' and type_id = 5) then
              0
             else
              price * IFNULL(a.scale, 1) * 1
           end) as cost
      from t_static_tmp_ad_download t left join t_application a on t.app_id=a.id
     where 1 = 1
       and STATIC_DATE = strdate
       and STATIC_HOUR = strhour;
  
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
             when app_id = '247' then
              0
             when (app_id = '14278' and type_id = 5) then
              0
             else
              price * IFNULL(a.scale, 1) * 1
           end) as cost
      from t_static_tmp_ad_activate  t left join t_application a on t.app_id=a.id
     where 1 = 1
       and STATIC_DATE = strdate
       and STATIC_HOUR = strhour;
END;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for ESCORE_MERGE_DATA_BYHOUR_TMP
-- ----------------------------
DROP PROCEDURE IF EXISTS `ESCORE_MERGE_DATA_BYHOUR_TMP`;
DELIMITER ;;
CREATE  PROCEDURE `ESCORE_MERGE_DATA_BYHOUR_TMP`(IN strdate VARCHAR(10), IN strhour VARCHAR(2))
BEGIN
  delete from t_static_orignal_byhour_tmp
   where 1 = 1
     and STATIC_DATE = strdate
     and STATIC_HOUR = strhour;
  
  insert into t_static_orignal_byhour_tmp
    (static_date, static_hour, app_id, channel, uuid, area, ip, init)
    select static_date, static_hour, app_id, channel, uuid, area, ip, init
      from t_static_tmp_user_init
     where 1 = 1
       and STATIC_DATE = strdate
       and STATIC_HOUR = strhour;
  
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
END;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for ESCORE_STATIC_ADCOST
-- ----------------------------
DROP PROCEDURE IF EXISTS `ESCORE_STATIC_ADCOST`;
DELIMITER ;;
CREATE  PROCEDURE `ESCORE_STATIC_ADCOST`(IN strdate VARCHAR(10),IN strhour VARCHAR(10))
BEGIN
		update t_ad set cost_day=0;
		update t_ad t set cost_day=(SELECT CASE t.BUDGET_TYPE WHEN 'M' THEN ROUND(SUM(adcost.pv) / 1000, 0) 
			WHEN 'C' THEN SUM(adcost.click) WHEN 'D' THEN SUM(adcost.download) WHEN 'A' THEN SUM(adcost.activate) end
			from t_static_ad_byhour adcost where adcost.static_date=strdate and t.id=adcost.ad_id GROUP BY adcost.ad_id)
		where t.budget_day is not null and t.budget_day>0;
	END;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for ESCORE_STATIC_BYDAY
-- ----------------------------
DROP PROCEDURE IF EXISTS `ESCORE_STATIC_BYDAY`;
DELIMITER ;;
CREATE  PROCEDURE `ESCORE_STATIC_BYDAY`(IN strdate VARCHAR(10),IN strhour VARCHAR(10))
BEGIN
	DELETE FROM t_static_ad_byday WHERE static_date = strdate;
	INSERT INTO t_static_ad_byday (static_date,ad_id,app_id,type_id,channel,syspv,pv,sysclick,click,sysclickd,clickd,sysdownload,download,sysactivate,activate,cost)
	SELECT static_date,ad_id,app_id,type_id,channel,SUM(syspv),SUM(pv),SUM(sysclick),SUM(click),SUM(sysclickd),SUM(clickd),SUM(sysdownload),SUM(download),SUM(sysactivate),SUM(activate),SUM(cost)
	FROM t_static_ad_byhour WHERE static_date = strdate GROUP BY static_date,ad_id,type_id,app_id,channel;
	DELETE FROM t_static_ad_byday WHERE app_id IN ('777','200','208');
	
	DELETE FROM t_static_app_byday WHERE static_date=strdate;
	INSERT INTO t_static_app_byday (static_date,app_id,type_id,pospv,syspv,pv,sysclick,click,sysclickd,clickd,sysdownload,download,sysactivate,activate,cost)
	SELECT static_date,app_id,type_id,SUM(pospv),SUM(syspv),SUM(pv),SUM(sysclick),SUM(click),SUM(sysclickd),SUM(clickd),SUM(sysdownload),SUM(download),SUM(sysactivate),SUM(activate),SUM(cost)
	FROM t_static_app_byhour WHERE static_date=strdate GROUP BY static_date,app_id,type_id;
	DELETE FROM t_static_app_byday WHERE app_id IN ('777','200','208');
	
	DELETE FROM t_static_channel_byday WHERE static_date = strdate;
	INSERT INTO t_static_channel_byday (static_date,channel,app_id,type_id,pv,click,clickd,download,activate,cost)
	SELECT static_date,channel,app_id,type_id,SUM(pv),SUM(click),SUM(clickd),SUM(download),SUM(activate),SUM(cost)
	FROM t_static_ad_byhour WHERE static_date = strdate GROUP BY static_date,channel,app_id,type_id;
	DELETE FROM t_static_channel_byday WHERE app_id IN ('777','200','208');
END;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for ESCORE_STATIC_BYHOUR
-- ----------------------------
DROP PROCEDURE IF EXISTS `ESCORE_STATIC_BYHOUR`;
DELIMITER ;;
CREATE  PROCEDURE `ESCORE_STATIC_BYHOUR`(IN strdate VARCHAR(10),IN strhour VARCHAR(10))
BEGIN
		DELETE FROM t_static_app_byhour WHERE STATIC_DATE = strdate AND STATIC_HOUR = strhour;
		INSERT INTO t_static_app_byhour (static_date,static_hour,app_id,type_id,pospv,syspv,pv,sysclick,click,sysclickd,clickd,sysdownload,download,sysactivate,activate,cost)
		SELECT t.static_date,t.static_hour,t.app_id,t.type_id,ROUND(SUM(t.pospv*p.rate),0),ROUND(SUM(t.syspv*p.rate),0),ROUND(SUM(t.pv*p.rate),0),ROUND(SUM(t.sysclick*p.rate),0),
		ROUND(SUM(t.click*p.rate),0),ROUND(SUM(t.sysclickd*p.rate),0),ROUND(SUM(t.clickd*p.rate),0),ROUND(SUM(t.sysdownload*p.rate),0),ROUND(SUM(t.download*p.rate),0),ROUND(SUM(t.sysactivate*p.rate),0),ROUND(SUM(t.activate*p.rate),0),ROUND(SUM(t.cost*p.rate),4)
		FROM t_static_orignal_byhour_sum t LEFT JOIN t_page p ON t.type_id=p.type_id AND t.app_id=p.app_id 
		WHERE t.STATIC_DATE=strdate AND t.STATIC_HOUR=strhour AND t.type_id IS NOT NULL GROUP BY t.static_date, t.static_hour, t.app_id, t.type_id
		UNION ALL
		SELECT t.static_date,t.static_hour,t.app_id,t.type_id,ROUND(SUM(t.pospv*p.rate),0),ROUND(SUM(t.syspv*p.rate),0),ROUND(SUM(t.pv*p.rate),0),ROUND(SUM(t.sysclick*p.rate),0),
		ROUND(SUM(t.click*p.rate),0),ROUND(SUM(t.sysclickd*p.rate),0),ROUND(SUM(t.clickd*p.rate),0),ROUND(SUM(t.sysdownload*p.rate),0),ROUND(SUM(t.download*p.rate),0),ROUND(SUM(t.sysactivate*p.rate),0),ROUND(SUM(t.activate*p.rate),0),ROUND(SUM(t.cost*p.rate),4)
		FROM t_static_orignal_byhour_sum t LEFT JOIN t_page p ON t.type_id=6 AND p.type_id=1 AND t.app_id=p.app_id 
		WHERE t.STATIC_DATE=strdate AND t.STATIC_HOUR=strhour AND t.type_id IS NOT NULL GROUP BY t.static_date, t.static_hour, t.app_id, t.type_id;
		DELETE FROM t_static_ad_byhour WHERE STATIC_DATE=strdate AND STATIC_HOUR = strhour;
		INSERT INTO t_static_ad_byhour (static_date,static_hour,ad_id,pay_type,app_id,type_id,channel,syspv,pv,sysclick,click,sysclickd,clickd,sysdownload,download,sysactivate,activate,cost)
		SELECT t.static_date,t.static_hour,t.ad_id,t.pay_type,t.app_id,t.type_id,t.channel,ROUND(SUM(t.syspv*p.rate),0),ROUND(SUM(t.pv*p.rate),0),ROUND(SUM(t.sysclick*p.rate),0),ROUND(SUM(t.click*p.rate),0),
		ROUND(SUM(t.sysclickd*p.rate),0),ROUND(SUM(t.clickd*p.rate),0),ROUND(SUM(t.sysdownload*p.rate),0),ROUND(SUM(t.download*p.rate),0),ROUND(SUM(t.sysactivate*p.rate),0),ROUND(SUM(t.activate*p.rate),0),ROUND(SUM(t.cost*p.rate),4)
		FROM t_static_orignal_byhour_sum t LEFT JOIN t_page p ON t.type_id=p.type_id AND t.app_id=p.app_id 
		WHERE t.STATIC_DATE = strdate AND t.STATIC_HOUR = strhour AND t.ad_id IS NOT NULL GROUP BY t.static_date,t.static_hour,t.ad_id,t.pay_type,t.app_id,t.type_id,t.channel
		UNION ALL
		SELECT t.static_date,t.static_hour,t.ad_id,t.pay_type,t.app_id,t.type_id,t.channel,ROUND(SUM(t.syspv*p.rate),0),ROUND(SUM(t.pv*p.rate),0),ROUND(SUM(t.sysclick*p.rate),0),ROUND(SUM(t.click*p.rate),0),
		ROUND(SUM(t.sysclickd*p.rate),0),ROUND(SUM(t.clickd*p.rate),0),ROUND(SUM(t.sysdownload*p.rate),0),ROUND(SUM(t.download*p.rate),0),ROUND(SUM(t.sysactivate*p.rate),0),ROUND(SUM(t.activate*p.rate),0),ROUND(SUM(t.cost*p.rate),4)
		FROM t_static_orignal_byhour_sum t LEFT JOIN t_page p ON t.type_id=6 AND p.type_id=1 AND t.app_id=p.app_id 
		WHERE t.STATIC_DATE = strdate AND t.STATIC_HOUR = strhour AND t.ad_id IS NOT NULL GROUP BY t.static_date,t.static_hour,t.ad_id,t.pay_type,t.app_id,t.type_id,t.channel;
		TRUNCATE TABLE t_static_orignal_byhour_sum;
	END;;
DELIMITER ;

-- ----------------------------
-- Trigger structure for trigger_ad_before_update
-- ----------------------------
DELIMITER ;
CREATE TRIGGER `trigger_ad_before_update` BEFORE UPDATE ON `t_ad` FOR EACH ROW BEGIN
		DECLARE new_ad_status INT(11);
		DECLARE old_ad_status INT(5);
		DECLARE ad_click INT(20);
		DECLARE ad_pv INT(20);
		DECLARE ad_download INT(20);
		DECLARE ad_activate INT(20);
		DECLARE v_count INT(11);
		DECLARE v_off_line_type INT(11);
		SET new_ad_status = NEW.`status`;
		SET old_ad_status = OLD.`status`;
		IF (old_ad_status=1 AND (new_ad_status=-30 OR new_ad_status=-1)) THEN
			SELECT COUNT(1) INTO v_count FROM T_AD_ACTUAL_DAY WHERE ad_id= OLD.id AND static_date=DATE_FORMAT(NOW(), '%Y-%m-%d');
			IF v_count=0 THEN
				SET ad_click=0;
				SET ad_pv=0;
				SET ad_download=0;
				SET ad_activate=0;
			ELSE
				SELECT IMPRESSIONS_AMOUNT,CLICK_AMOUNT,DOWNLOAD_AMOUNT,ACTION_AMOUNT INTO ad_pv,ad_click,ad_download,ad_activate FROM T_AD_ACTUAL_DAY WHERE ad_id=OLD.id AND static_date=DATE_FORMAT(NOW(), '%Y-%m-%d');
			END IF;
			CASE WHEN new_ad_status=-30 THEN SET v_off_line_type=0;
			WHEN new_ad_status=-1 THEN SET v_off_line_type=1;
			END CASE;
			INSERT INTO T_LOG_AD_BUDGET(BUDGET_DAY,CREATE_TIME,OFFLINE_TIME,ONLINE_TIME,BUDGET_TYPE,STATIC_DATE,AD_ID,CLICK,DOWNLOAD,PV,ACTIVATE,OFFLINE_TYPE,STATUS) VALUES (OLD.BUDGET_DAY,NOW(),NOW(),OLD.online_time,OLD.BUDGET_TYPE,DATE_FORMAT(NOW(), '%Y-%m-%d'),OLD.id,ad_click,ad_download,ad_pv,ad_activate,v_off_line_type,0);
		END IF;
	END;;
DELIMITER ;
