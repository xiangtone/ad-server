CREATE OR REPLACE VIEW v_campaign AS
select 
	`c`.`online_time` AS `online_time`,
	`c`.`res_type` AS `res_type`,
	`p`.`config_id` AS `config_id`,
	`c`.`salesman_id` AS `salesman_id`,
	`c`.`id` AS `ID`,
	`c`.`campaign_name` AS `campaign_name`,
	`c`.`price` AS `PRICE`,
	`c`.`operater_id` AS `operater_id`,
	`c`.`create_time` AS `create_time`,
	`c`.`adv_id` AS `ADV_ID`,
	`c`.`terminal_type` AS `terminal_type`,
	`c`.`operat_agencies` AS `operat_agencies`,
	`c`.`phone_brand` AS `phone_brand`,
	`c`.`app_type` AS `app_type`,
	`c`.`time_directional` AS `time_directional`,
	`c`.`area_directional` AS `area_directional`,
	`c`.`charge_type` AS `charge_type`,
	`c`.`category_id` AS `category_id`,
	`c`.`create_user` AS `create_user`,
	`c`.`campaign_type` AS `campaign_type`,
	`c`.`budget` AS `budget`,
	`c`.`profit_rate` AS `profit_rate`,
	`c`.`campaign_required` AS `campaign_required`,
	`c`.`confirm_mode` AS `confirm_mode`,
	`rel`.`status` AS `status`,
	`p`.`os` AS `os`,
	`p`.`id` AS `placement_id`,
	`p`.`plan_start` AS `plan_start`,
	`p`.`plan_end` AS `plan_end`,
	`p`.`name` AS `placement_name`,
	`p`.`category_id` AS `placement_category_id`,
	`p`.`star_level` AS `star_level`,
	`u`.`real_name` AS `create_user_name`,
	`adv`.`company_name` AS `company_name`,
	`adv`.`email` AS `adv_email`
	from `t_campaign_placement_rel` `rel` 
		left join `t_campaign` `c` on `rel`.`campaign_id` = `c`.`id`
		left join `t_placement` `p` on `rel`.`placement_id` = `p`.`id` 
		left join `t_sys_user` `u` on `c`.`create_user` = `u`.`id` 
		left join `t_advertiser` `adv` on `c`.`adv_id` = `adv`.`id`