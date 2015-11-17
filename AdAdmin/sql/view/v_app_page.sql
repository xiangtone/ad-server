create or replace view v_app_page as
select
	page.id id,
	page.status status,
	page.rate,
	v.scale scale,
	v.dev_id,
	v.dev_name,
	v.dev_email,
	v.id app_id,
	v.NAME app_name,
	v.app_res,
	v.app_manager_id,
	v.os,
	type.name type_name,
	type.id type_id
	from T_PAGE page
		left join V_APP_DEV v on page.app_id = v.id
		left join T_TYPE type on page.type_id = type.id;
