INSERT INTO 
	`t_ios_action_log_cc`
	(
		id,ad_id,mac,stat_date,activite_date,channel,status,create_time,activite_status,stat_enddate,application_key,page_type,ad_key,sdkversion,udid,client_ip,
openudid,idfa,idfv,os_version,callback_url,activate_status,click_time,ssid,bssid,phoneName,latitude,longitude,in_price
	)
	
SELECT  
	t.id,t.ad_id,t.mac,t.stat_date,t.activite_date,t.channel,t.status,t.create_time,t.activite_status,t.stat_enddate,t.application_key,t.page_type,t.ad_key,t.sdkversion,t.udid,t.client_ip,t.
openudid,t.idfa,t.idfv,t.os_version,t.callback_url,t.activate_status,t.click_time,t.ssid,t.bssid,t.phoneName,t.latitude,t.longitude,t.in_price
	FROM 	`t_ios_action_log` t where t.create_time<'2014-04-01'



