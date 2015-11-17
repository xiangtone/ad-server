UPDATE T_IOS_ACTION_LOG LOG  SET activate_status=(SELECT CASE  WHEN COUNT(1)>0 THEN 1 ELSE 0 END FROM  t_ios_activite_log act WHERE act.action_id=log.id) WHERE  
log.CREATE_TIME >='2014-01-01 00:00:00'

             AND log.CREATE_TIME <'2014-01-31 23:59:59';


INSERT INTO T_IOS_EFFECT_MONTH(ad_name,channel_name,sys_num,confirm_num)
SELECT
 	camp.ad_name,
	cof.channel_name,
	t.sys_num,
	t.confirm_num
 FROM (SELECT 
	log.ad_id,
	log.channel,
	COUNT(1) sys_num,
	SUM(activate_status) confirm_num
	FROM T_IOS_ACTION_LOG LOG
	WHERE   log.CREATE_TIME >='2014-01-01 00:00:00' AND log.CREATE_TIME <'2014-01-31 23:59:59' GROUP BY log.ad_id,channel) t
	LEFT JOIN t_campaign_config camp ON camp.id=t.ad_id
	LEFT JOIN t_channel_config cof ON cof.channel=t.channel
	
	
	
	
	
	
	
	
#按天出数据	
INSERT INTO t_ios_effect_detail_tmp_channel(ad_name,channel_name,sys_num,confirm_num,static_date)
SELECT
 	camp.ad_name,
	cof.channel_name,
	t.sys_num,
	t.confirm_num,
	t.static_date
	
 FROM (SELECT 
	DATE_FORMAT(log.create_time,'%Y-%m-%d') static_date,
	log.ad_id,
	log.channel,
	COUNT(1) sys_num,
	SUM(activate_status) confirm_num
	FROM T_IOS_ACTION_LOG LOG
	WHERE   log.CREATE_TIME >='2014-01-01 00:00:00' AND log.CREATE_TIME <'2014-01-31 23:59:59' GROUP BY DATE_FORMAT(log.create_time, '%Y-%m-%d'),log.ad_id,channel) t
	LEFT JOIN t_campaign_config camp ON camp.id=t.ad_id
	LEFT JOIN t_channel_config cof ON cof.channel=t.channel
	
	
	





SELECT SUM(confirm_num) FROM T_IOS_EFFECT_MONTH





             
             
             
             
              
