select 
	date_format(log.create_time,'%Y-%m-%d') static_date,
	NOW(),
	(case   when log.channel ='zijiren' then 0  when log.channel!='zijiren' then 1  end)MEDIA_TYPE,log.ad_id,log.application_key,log.channel,
	log.page_type,
	count(1),
	sum(case when activite_status = 0 then 0 when activite_status = 1 then 1 end),
	OS_VERSION 
	from T_IOS_ACTION_LOG log, t_campaign_config cof 
	where log.ad_id=cof.id and cof.url is not null and log.CREATE_TIME>='2014-01-15' and log.CREATE_TIME<='2014-01-22' group by date_format(log.create_time, '%Y-%m-%d'),ad_id,channel,application_key,page_type,os_version) t
							
							
							

	
	
	select 
	date_format(log.create_time,'%Y-%m-%d') static_date,
	NOW(),
	(case   when log.channel ='zijiren' then 0  when log.channel!='zijiren' then 1  end)MEDIA_TYPE,log.ad_id,log.application_key,log.channel,
	log.page_type,
	count(1),
	sum(case when act.activite_status = 0 then 0 when act.activite_status = 1 then 1 end),
	log.OS_VERSION 
	from T_IOS_ACTION_LOG log left join t_ios_activite_log act on act.action_id=log.id
	where   log.CREATE_TIME>='2014-01-15' and log.CREATE_TIME<='2014-01-22' group by date_format(log.create_time, '%Y-%m-%d'),ad_id,channel,application_key,page_type,os_version
	
	
	
	
	
	
	
	
	
	
	 select count(1) from  T_PACKAGE_ACTIVATE a left join  V_PLACEMENT_PACKAGE t  on a.package_id=t.ID left join T_FINANCE_BALANCE_INCOME_REL rel on rel.detail_id=a.id where 1=1 and a.status!=-3  and a.static_date>=? and a.static_date<=?
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	find /yijifenlog/192.168.113.*/adactivate/activate.log.2014-01-21-*|xargs cat|awk -F '\t' '{print$2,$13,substr($1,0,10),$3}'|sort -n|uniq -c|awk '{print$4,"\t",$2,"\t",$5,"\t",$3,"\t",$1}'
	>/home/yijifen/EScore_Service/v$formatday.txt
	
	
	
	
	
	
	
	
	
	
	select  t.* ,ad.id,la.activate,cost from (SELECT  id,package_id from  t_package_activate_detail where static_date='2014-01-24' and type_id=0 GROUP  by package_id )t  left join  t_ad ad  on  t.package_id=ad.package_id left join (SELECT  ad_id,activate,cost   from  t_report_ad_byday where static_date='2014-01-24') la on ad.id=la.ad_id
where t.package_id=2635
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

