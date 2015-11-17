select date_format(log.create_time,'%Y-%m-%d') static_date,
							 NOW(),
							 (case when log.channel ='zijiren' then 0  when log.channel!='zijiren' then 1  end)MEDIA_TYPE,
							 log.ad_id,
							 log.application_key,
							 log.channel,
							 log.page_type,
							 count(1),
							 sum(log.activite_status)
							 ,log.OS_VERSION 
							 from T_IOS_ACTION_LOG log, t_campaign_config cof 
							 where log.ad_id=cof.id and cof.url is not null and log.CREATE_TIME>=? and log.CREATE_TIME<=? group by date_format(log.create_time, '%Y-%m-%d'),ad_id,channel,application_key,page_type,os_version