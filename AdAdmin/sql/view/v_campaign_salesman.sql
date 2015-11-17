create or replace view v_campaign_salesman as
select 
	`c`.`id` AS `CAMPAIGN_id`,
	`s`.`id` AS `ID`,
	`s`.`name` AS `NAME`,
	`s`.`mobile` AS `MOBILE`,
	`s`.`qq` AS `QQ`,
	`s`.`create_time` AS `CREATE_TIME`,
	`s`.`status` AS `STATUS`,
	`c`.`adv_id` AS `ADV_ID`,
	`s`.`area_type` AS `AREA_TYPE`,
	`s`.`create_user` AS `CREATE_USER` 
	from `t_campaign` `c` 
		left join `t_campaign_salesman` `s` on `c`.`salesman_id` = `s`.`id`