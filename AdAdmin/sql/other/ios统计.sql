--按天
select 
	date_format(log.create_time,'%Y-%m-%d') static_date,
	NOW(),
	(case   when log.channel ='zijiren' then 0  when log.channel!='zijiren' then 1  end)MEDIA_TYPE,log.ad_id,log.application_key,log.channel,
	log.page_type,
	count(1),
	sum(case when act.activite_status = 0 then 0 when act.activite_status = 1 then 1 end),
	log.OS_VERSION 
	from T_IOS_ACTION_LOG log left join t_ios_activite_log act on act.action_id=log.id
	where   log.CREATE_TIME>='2014-01-01' and log.CREATE_TIME<='2014-01-31' group by date_format(log.create_time, '%Y-%m-%d'),ad_id,channel,application_key,page_type,os_version
	
	
	
	
--按月
select 
	date_format(log.create_time,'%Y-%m-%d') static_date,
	NOW(),
	(case   when log.channel ='zijiren' then 0  when log.channel!='zijiren' then 1  end)MEDIA_TYPE,log.ad_id,log.application_key,log.channel,
	log.page_type,
	count(1),
	sum(case when act.activite_status = 0 then 0 when act.activite_status = 1 then 1 end),
	log.OS_VERSION 
	from T_IOS_ACTION_LOG log left join t_ios_activite_log act on act.action_id=log.id
	where   log.CREATE_TIME>='2014-01-01' and log.CREATE_TIME<='2014-01-31' group by ad_id,channel,application_key
	