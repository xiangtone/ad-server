create or replace view v_app_dev as
select 
	`dev`.`real_name` as `dev_name`,
	`dev`.`email` as `dev_email`,
	`dev`.`status` as `dev_status`,
	`app`.`id` as `id`,
	`app`.`appkey` as `appkey`,
	`app`.`os` as `os`,
	`app`.`dev_id` as `dev_id`,
	`app`.`category_id` as `category_id`,
	`app`.`name` as `name`,
	`app`.`keyword` as `keyword`,
	`app`.`long_desc` as `long_desc`,
	`app`.`project_url` as `project_url`,
	`app`.`resource_size` as `resource_size`,
	`app`.`package_name` as `package_name`,
	`app`.`version_name` as `version_name`,
	`app`.`version_code` as `version_code`,
	`app`.`status` as `status`,
	`app`.`manager_id` as `manager_id`,
	`app`.`check_time` as `check_time`,
	`app`.`check_msg` as `check_msg`,
	`app`.`del` as `del`,
	`app`.`release_time` as `release_time`,
	`app`.`update_time` as `update_time`,
	`app`.`last_update_man` as `last_update_man`,
	`app`.`create_time` as `create_time`,
	`app`.`app_res` as `app_res`,
	`app`.`app_manager_id` as `app_manager_id`,
	`app`.`orderby` as `orderby`,
	`app`.`down_time` as `down_time`,
	`app`.`scale` as `scale` 
	from `t_application` `app` left join `t_developer` `dev` on `app`.`dev_id` = `dev`.`id`