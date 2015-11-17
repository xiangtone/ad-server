select 
	str_to_date(`a`.`static_date`,'%Y-%m-%d %T') AS `static_date`,
	`a`.`type_id` AS `type_id`,
	`a`.`confirm_num` AS `confirm_num`,
	`a`.`confirm_amount` AS `confirm_amount`,
	`a`.`blance_mode` AS `blance_mode`,
	`p`.`CAMPAIGN_ID` AS `campaign_id`,
	`p`.`CAMPAIGN_NAME` AS `CAMPAIGN_NAME`,
	`camp`.`placement_name` AS `placement_name`,
	`camp`.`CHARGE_TYPE` AS `charge_type`,
	`m`.`NAME` AS `media_name`,
	`e`.`name` AS `type_name`,
	`a`.`out_price` AS `out_price`,
	`a`.`sys_cost` AS `sys_cost`,
	`t`.`price` AS `price`,
	(`a`.`confirm_num` * `t`.`price`) AS `sys_income`,
	`adv`.`company_name` AS `公司名称`,
	salesman.name "销售"
	from `t_ios_activate_num_detail` `a` 
		left join `v_ios_activate` `p` on `a`.`parent_id` = `p`.`id` 
		left join `v_media` `m` on `a`.`media_id` = `m`.`ID`
		left join `t_type` `e` on `a`.`type_id` = `e`.`id` 
		left join `t_ios_activate_num` `t` on `a`.`parent_id` = `t`.`id` 
		left join `v_campaign` `camp` on `p`.`CAMPAIGN_ID` = `camp`.`ID` 
		left join `v_adv` `adv` on `camp`.`adv_id` = `adv`.`id` 
		left join t_campaign_salesman salesman  on cp.salesman_id=salesman.id
	where `p`.`status` = 9 and `a`.`status` = 9 order by `a`.`id` desc