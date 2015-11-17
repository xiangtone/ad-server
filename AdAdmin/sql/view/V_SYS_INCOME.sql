select 
	`v`.`ID` AS `ID`,
	`v`.`os` AS `系统`,
	`v`.`CAMPAIGN_name` AS `活动名称`,
	`v`.`type` AS `平台类型`,
	`v`.`meia_name` AS `媒体名称`,
	str_to_date(`v`.`STATIC_DATE`,'%Y-%m-%d') AS `日期`,
	`v`.`SYS_ACTIVATE` AS `系统激活数`,
	(case when (`v`.`TYPE_ID` = 0) then '积分墙' when (`v`.`TYPE_ID` = 1) then '推荐墙' when (`v`.`TYPE_ID` = 4) then 'BANNER' when (`v`.`TYPE_ID` = 5) then '插屏' end) AS `广告形式`,
	`v`.`PACKAGE_ID` AS `包ID`,
	`v`.`CONFIRM_NUM` AS `广告主确认数`,
	`v`.`IN_PRICE` AS `接入单价`,
	`v`.`SYS_COST` AS `系统成本`,
	`v`.`CONFIRM_AMOUNT` AS `广告主确认数2`,
	`v`.`out_price` AS `投放单价`,
	`v`.`CAMPAIGN_ID` AS `活动ID`,
	`v`.`profit_rate` AS `加价率`,
	v.blance_mode,
	`t`.`remarks` AS `渠道包号`,
	`p`.`name` AS `投放名称`,
	(`v`.`IN_PRICE` * `v`.`CONFIRM_NUM`) AS `广告收入`,
	`adv`.`company_name` AS `公司名称`,
	`adv`.`seal_man_name` AS `销售`,
	`cp`.`charge_type` AS `考核方式`,
	`s`.`name` AS `分类` 
	from `v_income_outcome` `v` 
	left join `t_placement_package` `t` on `v`.`PACKAGE_ID` = `t`.`id` 
	left join `t_placement` `p` on `t`.`placement_id` = `p`.`id` 
	left join `t_campaign` `cp` on `v`.`CAMPAIGN_ID` = `cp`.`id` 
	left join `v_adv` `adv` on `cp`.`adv_id` = `adv`.`id` 
	left join `t_escore_sort` `s` on `p`.`category_id` = `s`.`id`