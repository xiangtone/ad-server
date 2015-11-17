#渠道
INSERT INTO tmp_ios_effect_detail_channel(plancement_id,ad_key,channel_id,sys_num,confirm_num,static_date)
SELECT
 	p.id,
 	t.ad_id,
	cof.channel_id,
	t.sys_num,
	t.confirm_num,
	t.static_date
 FROM (SELECT 
	DATE_FORMAT(log.create_time,'%Y-%m-%d') static_date,
	log.ad_id,
	log.channel,
	COUNT(1) sys_num,
	SUM(activite_status) confirm_num
	FROM T_IOS_ACTION_LOG LOG
	WHERE   log.CREATE_TIME >='2014-01-01 00:00:00' AND log.CREATE_TIME <'2014-04-30 23:59:59' GROUP BY DATE_FORMAT(log.create_time, '%Y-%m-%d'),log.ad_id,channel) t
	LEFT JOIN t_placement p ON p.config_id=t.ad_id
	LEFT JOIN t_channel_config cof ON cof.channel=t.channel
	
	
#平台

INSERT INTO tmp_ios_effect_detail_platform(ad_id,sys_num,confirm_num,static_date,ad_name,in_price,charge_type,blance_mode,out_price,income,type_id)
SELECT
 	t.ad_id,
	t.sys_num,
	t.confirm_num,
	t.static_date,
	ad.placement_name,
	ad.price,
	ad.charge_type,
	ad.blance_mode,
	ad.blance_price,
	(t.sys_num*ad.price)income,
	ad.type_id
	
 FROM (SELECT 
	DATE_FORMAT(log.create_time,'%Y-%m-%d') static_date,
	COUNT(1) sys_num,
	SUM(activite_status) confirm_num,
	ad_key as ad_id
	FROM T_IOS_ACTION_LOG LOG
	WHERE  log.channel='adwalker' and log.CREATE_TIME >='2014-01-01 00:00:00' AND log.CREATE_TIME <'2014-04-30 23:59:59' GROUP BY DATE_FORMAT(log.create_time, '%Y-%m-%d'),log.ad_key) t
	left join v_ad_campaign ad on t.ad_id=ad.id

