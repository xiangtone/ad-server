select 
	`v`.`ID` AS `ID`,
	`v`.`os` AS `OS`,
	`v`.`CAMPAIGN_name` AS `CAMPAIGN_NAME`,
	`v`.`type` AS `TYPE`,
	`v`.`meia_name` AS `MEIA_NAME`,
	`v`.`STATIC_DATE` AS `STATIC_DATE`,
	`v`.`SYS_ACTIVATE` AS `SYS_ACTIVATE`,
	`v`.`MEDIA_ID` AS `MEDIA_ID`,
	`v`.`TYPE_ID` AS `TYPE_ID`,
	`v`.`PACKAGE_ID` AS `PACKAGE_ID`,
	`v`.`CONFIRM_NUM` AS `CONFIRM_NUM`,
	`v`.`IN_PRICE` AS `IN_PRICE`,
	`v`.`SYS_COST` AS `SYS_COST`,
	`v`.`CONFIRM_AMOUNT` AS `CONFIRM_AMOUNT`,
	`v`.`out_price` AS `OUT_PRICE`,
	`v`.`CAMPAIGN_ID` AS `CAMPAIGN_ID`,
	`v`.`profit_rate` AS `PROFIT_RATE`,
	`v`.`adv_id` AS `ADV_ID`,
	`sel`.`NAME` AS `NAME` 
	from `v_income_outcome` `v` 
		left join `v_campaign_salesman` `sel` on`v`.`CAMPAIGN_ID` = `sel`.`ID`)))