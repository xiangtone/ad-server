create index idx_idfa_adid_as on t_ios_activite_log (IDFA,AD_ID,ACTIVITE_STATUS);


create index idx__package_activate_detail_1 on t_package_activate_detail (status,static_date);




##广告按天报表
create index idx_report_ad_byday_static_date on t_report_ad_byday(static_date);


#结算数据录入，按日期，状态增加索引
create index idx_package_activate on t_package_activate(static_date,status);





#结算数据，平台成本关联关系
create index idx_balance_platform_rel_detail_id on t_package_activate(static_date,status);







#开发者收入审核2013
create index idx_t_finace_dev_incom_confirm_2013_status on t_finace_dev_incom_confirm_2013(status);



create index idx_t_finace_dev_incom_confirm_2013_status_effect_time on t_finace_dev_incom_confirm_2013(status,effect_time);


create index idx_t_finace_dev_incom_confirm_effect_time on t_finace_dev_incom_confirm_2013(effect_time);












