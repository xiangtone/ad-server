
-- ----------------------------
-- Procedure structure for cp_data
-- ----------------------------
DROP PROCEDURE IF EXISTS `cp_data`;
DELIMITER ;;
CREATE  PROCEDURE `cp_data`()
BEGIN
DECLARE i INT; 
SET i=0;
WHILE i<160 DO 
REPLACE INTO t_user_save (`uuid`,`imei`,`tel_num`,`tel_model`,`net_env`,`area_code`,`operator`,`os`,`brand`,`screen_width`,`screen_heigh`,`score`,`create_time`,`mac`,`pda_type`,`app_id`,`imsi`, `area_province`, `openudid`, `idfa`, `jailbroken`, `idfv`)
SELECT t.uuid,t.imei,t.tel_num,t.tel_model,t.net_env,t.area_code,t.operator,t.os,t.brand,t.screen_width,t.screen_heigh,t.score,t.create_time,t.mac,t.pda_type,t.app_id,t.imsi,t.area_province,t.openudid,t.idfa,t.jailbroken,t.idfv FROM t_user t  WHERE t.id>=i*1000000 AND t.id<(i+1)*1000000;
SET i=i+1; 
SELECT SLEEP(2);
END WHILE; 
END;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for ESCORE_FINACE_BYDAY
-- ----------------------------
DROP PROCEDURE IF EXISTS `ESCORE_FINACE_BYDAY`;
DELIMITER ;;
CREATE  PROCEDURE `ESCORE_FINACE_BYDAY`(IN strdate VARCHAR(10), IN strhour VARCHAR(2))
BEGIN
  delete from t_static_orignal_byhour_sum
   where 1 = 1
     and STATIC_DATE = strdate
     and STATIC_HOUR = strhour;
  
  insert into t_static_orignal_byhour_sum
    (static_date, static_hour, app_id, channel, uuid, area, ip, init)
    select static_date, static_hour, app_id, channel, uuid, area, ip, init
      from t_static_tmp_user_init
     where 1 = 1
       and STATIC_DATE = strdate
       and STATIC_HOUR = strhour;
  
  insert into t_static_orignal_byhour_sum
    (static_date,
     static_hour,
     ad_id,
     pay_type,
     app_id,
     type_id,
     channel,
     uuid,
     area,
     ip,
     pv,
     syspv,
     price,
     cost)
    select static_date,
           static_hour,
           ad_id,
           pay_type,
           app_id,
           type_id,
           channel,
           uuid,
           area,
           ip,
           1 as epv,
           pv,
           price,
           (case
             when app_id = '247' then 
              0
             else
              price*ifnull(a.scale, 1) * 1 / 1000
           end) as cost
           
      from t_static_tmp_ad_pv t left join t_application a on t.app_id=a.id
     where 1 = 1
       and STATIC_DATE = strdate
       and STATIC_HOUR = strhour;
  
  insert into t_static_orignal_byhour_sum
    (static_date,
     static_hour,
     app_id,
     type_id,
     channel,
     uuid,
     area,
     ip,
     pospv)
    select static_date,
           static_hour,
           app_id,
           type_id,
           channel,
           uuid,
           area,
           ip,
           pospv
      from t_static_tmp_pos_pv
     where 1 = 1
       and STATIC_DATE = strdate
       and STATIC_HOUR = strhour;
  
  insert into t_static_orignal_byhour_sum
    (static_date,
     static_hour,
     ad_id,
     pay_type,
     app_id,
     type_id,
     channel,
     uuid,
     area,
     ip,
     click,
     sysclick,
     price,
     cost)
    select static_date,
           static_hour,
           ad_id,
           pay_type,
           app_id,
           type_id,
           channel,
           uuid,
           area,
           ip,
           1 as eclick,
           click,
           price,
           (case
             when app_id = '247' then 
              0 
             else
              price*ifnull(a.scale, 1) * 1
           end) as cost
      from t_static_tmp_ad_click t left join t_application a on t.app_id=a.id
     where 1 = 1
       and STATIC_DATE = strdate
       and STATIC_HOUR = strhour;
       
 
  insert into t_static_orignal_byhour_sum
    (static_date,
     static_hour,
     ad_id,
     pay_type,
     app_id,
     type_id,
     channel,
     uuid,
     area,
     ip,
     clickd,
     sysclickd,
     price,
     cost)
    select static_date,
           static_hour,
           ad_id,
           pay_type,
           app_id,
           type_id,
           channel,
           uuid,
           area,
           ip,
           1 as eclickd,
           clickd,
           price,
           (case
             when app_id = '247' then 
              0
             when (type_id='5') then
              0
             else 
              price*ifnull(a.scale, 1) * 1
           end) as cost
      from t_static_tmp_ad_clickd t left join t_application a on t.app_id=a.id
     where 1 = 1
       and STATIC_DATE = strdate
       and STATIC_HOUR = strhour;
  
  insert into t_static_orignal_byhour_sum
    (static_date,
     static_hour,
     ad_id,
     pay_type,
     app_id,
     type_id,
     channel,
     uuid,
     area,
     ip,
     download,
     sysdownload,
     price,
     cost)
    select static_date,
           static_hour,
           ad_id,
           pay_type,
           app_id,
           type_id,
           channel,
           uuid,
           area,
           ip,
           1 as edownload,
           download,
           price,
           (case
             when app_id = '247' then 
              1 
             else
              price *ifnull(a.scale, 1)* 1
           end) as cost
      from t_static_tmp_ad_download t left join t_application a on t.app_id=a.id
     where 1 = 1
       and STATIC_DATE = strdate
       and STATIC_HOUR = strhour;
  
  insert into t_static_orignal_byhour_sum
    (static_date,
     static_hour,
     ad_id,
     pay_type,
     app_id,
     type_id,
     channel,
     uuid,
     area,
     ip,
     activate,
     sysactivate,
     price,
     cost)
    select static_date,
           static_hour,
           ad_id,
           pay_type,
           app_id,
           type_id,
           channel,
           uuid,
           area,
           ip,
           1 as eactivate,
           activate,
           price,
           (case
             when app_id = '247' then 
              0
             else
              price *ifnull(a.scale, 1) *  1
           end) as cost
      from t_static_tmp_ad_activate  t left join t_application a on t.app_id=a.id
     where 1 = 1
       and STATIC_DATE = strdate
       and STATIC_HOUR = strhour;
END;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for ESCORE_MERGE_DATA_BYHOUR
-- ----------------------------
DROP PROCEDURE IF EXISTS `ESCORE_MERGE_DATA_BYHOUR`;
DELIMITER ;;
CREATE  PROCEDURE `ESCORE_MERGE_DATA_BYHOUR`(IN strdate VARCHAR(10), IN strhour VARCHAR(2))
BEGIN
  delete from t_static_orignal_byhour_sum
   where 1 = 1
     and STATIC_DATE = strdate
     and STATIC_HOUR = strhour;
  
  insert into t_static_orignal_byhour_sum
    (static_date, static_hour, app_id, channel, uuid, area, ip, init)
    select static_date, static_hour, app_id, channel, uuid, area, ip, init
      from t_static_tmp_user_init
     where 1 = 1
       and STATIC_DATE = strdate
       and STATIC_HOUR = strhour;
  
  insert into t_static_orignal_byhour_sum
    (static_date,
     static_hour,
     ad_id,
     pay_type,
     app_id,
     type_id,
     channel,
     uuid,
     area,
     ip,
     pv,
     syspv,
     price,
     cost)
    select static_date,
           static_hour,
           ad_id,
           pay_type,
           app_id,
           type_id,
           channel,
           uuid,
           area,
           ip,
           1 as epv,
           pv,
           price,
           (case
             when app_id = '247' then
              0
             when (app_id = '14278' and type_id = 5) then
              0.03
             else
              price * ifnull(a.scale, 1) * 1 / 1000
           end) as cost
      from t_static_tmp_ad_pv t left join t_application a on t.app_id=a.id
     where 1 = 1
       and STATIC_DATE = strdate
       and STATIC_HOUR = strhour;
  
  insert into t_static_orignal_byhour_sum
    (static_date,
     static_hour,
     app_id,
     type_id,
     channel,
     uuid,
     area,
     ip,
     pospv)
    select static_date,
           static_hour,
           app_id,
           type_id,
           channel,
           uuid,
           area,
           ip,
           pospv
      from t_static_tmp_pos_pv
     where 1 = 1
       and STATIC_DATE = strdate
       and STATIC_HOUR = strhour;
  
  insert into t_static_orignal_byhour_sum
    (static_date,
     static_hour,
     ad_id,
     pay_type,
     app_id,
     type_id,
     channel,
     uuid,
     area,
     ip,
     click,
     sysclick,
     price,
     cost)
    select static_date,
           static_hour,
           ad_id,
           pay_type,
           app_id,
           type_id,
           channel,
           uuid,
           area,
           ip,
           1 as eclick,
           click,
           price,
           (case
             when app_id = '247' then
              0
             when (app_id = '14278' and type_id = 5) then
              0
             else
              price * IFNULL(a.scale, 1) * 1
           end) as cost
      from t_static_tmp_ad_click t left join t_application a on t.app_id=a.id
     where 1 = 1
       and STATIC_DATE = strdate
       and STATIC_HOUR = strhour;
       
 
  insert into t_static_orignal_byhour_sum
    (static_date,
     static_hour,
     ad_id,
     pay_type,
     app_id,
     type_id,
     channel,
     uuid,
     area,
     ip,
     clickd,
     sysclickd,
     price,
     cost)
    select static_date,
           static_hour,
           ad_id,
           pay_type,
           app_id,
           type_id,
           channel,
           uuid,
           area,
           ip,
           1 as eclickd,
           clickd,
           price,
           (case
             when app_id = '247' then
              0
             when (type_id = '5') then
              0
             else
              price * IFNULL(a.scale, 1) * 1
           end) as cost
      from t_static_tmp_ad_clickd t left join t_application a on t.app_id=a.id
     where 1 = 1
       and STATIC_DATE = strdate
       and STATIC_HOUR = strhour;
  
  insert into t_static_orignal_byhour_sum
    (static_date,
     static_hour,
     ad_id,
     pay_type,
     app_id,
     type_id,
     channel,
     uuid,
     area,
     ip,
     download,
     sysdownload,
     price,
     cost)
    select static_date,
           static_hour,
           ad_id,
           pay_type,
           app_id,
           type_id,
           channel,
           uuid,
           area,
           ip,
           1 as edownload,
           download,
           price,
          (case
             when app_id = '247' then
              1.1
             when (app_id = '14278' and type_id = 5) then
              0
             else
              price * IFNULL(a.scale, 1) * 1
           end) as cost
      from t_static_tmp_ad_download t left join t_application a on t.app_id=a.id
     where 1 = 1
       and STATIC_DATE = strdate
       and STATIC_HOUR = strhour;
  
  insert into t_static_orignal_byhour_sum
    (static_date,
     static_hour,
     ad_id,
     pay_type,
     app_id,
     type_id,
     channel,
     uuid,
     area,
     ip,
     activate,
     sysactivate,
     price,
     cost)
    select static_date,
           static_hour,
           ad_id,
           pay_type,
           app_id,
           type_id,
           channel,
           uuid,
           area,
           ip,
           1 as eactivate,
           activate,
           price,
           (case
             when app_id = '247' then
              0
             when (app_id = '14278' and type_id = 5) then
              0
             else
              price * IFNULL(a.scale, 1) * 1
           end) as cost
      from t_static_tmp_ad_activate  t left join t_application a on t.app_id=a.id
     where 1 = 1
       and STATIC_DATE = strdate
       and STATIC_HOUR = strhour;
END;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for ESCORE_MERGE_DATA_BYHOUR_TMP
-- ----------------------------
DROP PROCEDURE IF EXISTS `ESCORE_MERGE_DATA_BYHOUR_TMP`;
DELIMITER ;;
CREATE  PROCEDURE `ESCORE_MERGE_DATA_BYHOUR_TMP`(IN strdate VARCHAR(10), IN strhour VARCHAR(2))
BEGIN
  delete from t_static_orignal_byhour_tmp
   where 1 = 1
     and STATIC_DATE = strdate
     and STATIC_HOUR = strhour;
  
  insert into t_static_orignal_byhour_tmp
    (static_date, static_hour, app_id, channel, uuid, area, ip, init)
    select static_date, static_hour, app_id, channel, uuid, area, ip, init
      from t_static_tmp_user_init
     where 1 = 1
       and STATIC_DATE = strdate
       and STATIC_HOUR = strhour;
  
  insert into t_static_orignal_byhour_tmp
    (static_date,
     static_hour,
     ad_id,
     pay_type,
     app_id,
     type_id,
     channel,
     uuid,
     area,
     ip,
     pv,
     price)
    select static_date,
           static_hour,
           ad_id,
           pay_type,
           app_id,
           type_id,
           channel,
           uuid,
           area,
           ip,
           pv,
           price
      from t_static_tmp_ad_pv
     where 1 = 1
       and STATIC_DATE = strdate
       and STATIC_HOUR = strhour;
  
  insert into t_static_orignal_byhour_tmp
    (static_date,
     static_hour,
     app_id,
     type_id,
     channel,
     uuid,
     area,
     ip,
     pospv)
    select static_date,
           static_hour,
           app_id,
           type_id,
           channel,
           uuid,
           area,
           ip,
           pospv
      from t_static_tmp_pos_pv
     where 1 = 1
       and STATIC_DATE = strdate
       and STATIC_HOUR = strhour;
  
  insert into t_static_orignal_byhour_tmp
    (static_date,
     static_hour,
     ad_id,
     pay_type,
     app_id,
     type_id,
     channel,
     uuid,
     area,
     ip,
     click,
     price)
    select static_date,
           static_hour,
           ad_id,
           pay_type,
           app_id,
           type_id,
           channel,
           uuid,
           area,
           ip,
           click,
           price
      from t_static_tmp_ad_click
     where 1 = 1
       and STATIC_DATE = strdate
       and STATIC_HOUR = strhour;
  
  insert into t_static_orignal_byhour_tmp
    (static_date,
     static_hour,
     ad_id,
     pay_type,
     app_id,
     type_id,
     channel,
     uuid,
     area,
     ip,
     download,
     price)
    select static_date,
           static_hour,
           ad_id,
           pay_type,
           app_id,
           type_id,
           channel,
           uuid,
           area,
           ip,
           download,
           price
      from t_static_tmp_ad_download
     where 1 = 1
       and STATIC_DATE = strdate
       and STATIC_HOUR = strhour;
  
  insert into t_static_orignal_byhour_tmp
    (static_date,
     static_hour,
     ad_id,
     pay_type,
     app_id,
     type_id,
     channel,
     uuid,
     area,
     ip,
     activate,
     price)
    select static_date,
           static_hour,
           ad_id,
           pay_type,
           app_id,
           type_id,
           channel,
           uuid,
           area,
           ip,
           activate,
           price
      from t_static_tmp_ad_activate
     where 1 = 1
       and STATIC_DATE = strdate
       and STATIC_HOUR = strhour;
END;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for ESCORE_STATIC_ADCOST
-- ----------------------------
DROP PROCEDURE IF EXISTS `ESCORE_STATIC_ADCOST`;
DELIMITER ;;
CREATE  PROCEDURE `ESCORE_STATIC_ADCOST`(IN strdate VARCHAR(10),IN strhour VARCHAR(10))
BEGIN
		update t_ad set cost_day=0;
		update t_ad t set cost_day=(SELECT CASE t.BUDGET_TYPE WHEN 'M' THEN ROUND(SUM(adcost.pv) / 1000, 0) 
			WHEN 'C' THEN SUM(adcost.click) WHEN 'D' THEN SUM(adcost.download) WHEN 'A' THEN SUM(adcost.activate) end
			from t_static_ad_byhour adcost where adcost.static_date=strdate and t.id=adcost.ad_id GROUP BY adcost.ad_id)
		where t.budget_day is not null and t.budget_day>0;
	END;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for ESCORE_STATIC_BYDAY
-- ----------------------------
DROP PROCEDURE IF EXISTS `ESCORE_STATIC_BYDAY`;
DELIMITER ;;
CREATE  PROCEDURE `ESCORE_STATIC_BYDAY`(IN strdate VARCHAR(10),IN strhour VARCHAR(10))
BEGIN
	DELETE FROM t_static_ad_byday WHERE static_date = strdate;
	INSERT INTO t_static_ad_byday (static_date,ad_id,app_id,type_id,channel,syspv,pv,sysclick,click,sysclickd,clickd,sysdownload,download,sysactivate,activate,cost)
	SELECT static_date,ad_id,app_id,type_id,channel,SUM(syspv),SUM(pv),SUM(sysclick),SUM(click),SUM(sysclickd),SUM(clickd),SUM(sysdownload),SUM(download),SUM(sysactivate),SUM(activate),SUM(cost)
	FROM t_static_ad_byhour WHERE static_date = strdate GROUP BY static_date,ad_id,type_id,app_id,channel;
	DELETE FROM t_static_ad_byday WHERE app_id IN ('777','200','208');
	
	DELETE FROM t_static_app_byday WHERE static_date=strdate;
	INSERT INTO t_static_app_byday (static_date,app_id,type_id,pospv,syspv,pv,sysclick,click,sysclickd,clickd,sysdownload,download,sysactivate,activate,cost)
	SELECT static_date,app_id,type_id,SUM(pospv),SUM(syspv),SUM(pv),SUM(sysclick),SUM(click),SUM(sysclickd),SUM(clickd),SUM(sysdownload),SUM(download),SUM(sysactivate),SUM(activate),SUM(cost)
	FROM t_static_app_byhour WHERE static_date=strdate GROUP BY static_date,app_id,type_id;
	DELETE FROM t_static_app_byday WHERE app_id IN ('777','200','208');
	
	DELETE FROM t_static_channel_byday WHERE static_date = strdate;
	INSERT INTO t_static_channel_byday (static_date,channel,app_id,type_id,pv,click,clickd,download,activate,cost)
	SELECT static_date,channel,app_id,type_id,SUM(pv),SUM(click),SUM(clickd),SUM(download),SUM(activate),SUM(cost)
	FROM t_static_ad_byhour WHERE static_date = strdate GROUP BY static_date,channel,app_id,type_id;
	DELETE FROM t_static_channel_byday WHERE app_id IN ('777','200','208');
END;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for ESCORE_STATIC_BYHOUR
-- ----------------------------
DROP PROCEDURE IF EXISTS `ESCORE_STATIC_BYHOUR`;
DELIMITER ;;
CREATE  PROCEDURE `ESCORE_STATIC_BYHOUR`(IN strdate VARCHAR(10),IN strhour VARCHAR(10))
BEGIN
		DELETE FROM t_static_app_byhour WHERE STATIC_DATE = strdate AND STATIC_HOUR = strhour;
		INSERT INTO t_static_app_byhour (static_date,static_hour,app_id,type_id,pospv,syspv,pv,sysclick,click,sysclickd,clickd,sysdownload,download,sysactivate,activate,cost)
		SELECT t.static_date,t.static_hour,t.app_id,t.type_id,ROUND(SUM(t.pospv*p.rate),0),ROUND(SUM(t.syspv*p.rate),0),ROUND(SUM(t.pv*p.rate),0),ROUND(SUM(t.sysclick*p.rate),0),
		ROUND(SUM(t.click*p.rate),0),ROUND(SUM(t.sysclickd*p.rate),0),ROUND(SUM(t.clickd*p.rate),0),ROUND(SUM(t.sysdownload*p.rate),0),ROUND(SUM(t.download*p.rate),0),ROUND(SUM(t.sysactivate*p.rate),0),ROUND(SUM(t.activate*p.rate),0),ROUND(SUM(t.cost*p.rate),4)
		FROM t_static_orignal_byhour_sum t LEFT JOIN t_page p ON t.type_id=p.type_id AND t.app_id=p.app_id 
		WHERE t.STATIC_DATE=strdate AND t.STATIC_HOUR=strhour AND t.type_id IS NOT NULL GROUP BY t.static_date, t.static_hour, t.app_id, t.type_id
		UNION ALL
		SELECT t.static_date,t.static_hour,t.app_id,t.type_id,ROUND(SUM(t.pospv*p.rate),0),ROUND(SUM(t.syspv*p.rate),0),ROUND(SUM(t.pv*p.rate),0),ROUND(SUM(t.sysclick*p.rate),0),
		ROUND(SUM(t.click*p.rate),0),ROUND(SUM(t.sysclickd*p.rate),0),ROUND(SUM(t.clickd*p.rate),0),ROUND(SUM(t.sysdownload*p.rate),0),ROUND(SUM(t.download*p.rate),0),ROUND(SUM(t.sysactivate*p.rate),0),ROUND(SUM(t.activate*p.rate),0),ROUND(SUM(t.cost*p.rate),4)
		FROM t_static_orignal_byhour_sum t LEFT JOIN t_page p ON t.type_id=6 AND p.type_id=1 AND t.app_id=p.app_id 
		WHERE t.STATIC_DATE=strdate AND t.STATIC_HOUR=strhour AND t.type_id IS NOT NULL GROUP BY t.static_date, t.static_hour, t.app_id, t.type_id;
		DELETE FROM t_static_ad_byhour WHERE STATIC_DATE=strdate AND STATIC_HOUR = strhour;
		INSERT INTO t_static_ad_byhour (static_date,static_hour,ad_id,pay_type,app_id,type_id,channel,syspv,pv,sysclick,click,sysclickd,clickd,sysdownload,download,sysactivate,activate,cost)
		SELECT t.static_date,t.static_hour,t.ad_id,t.pay_type,t.app_id,t.type_id,t.channel,ROUND(SUM(t.syspv*p.rate),0),ROUND(SUM(t.pv*p.rate),0),ROUND(SUM(t.sysclick*p.rate),0),ROUND(SUM(t.click*p.rate),0),
		ROUND(SUM(t.sysclickd*p.rate),0),ROUND(SUM(t.clickd*p.rate),0),ROUND(SUM(t.sysdownload*p.rate),0),ROUND(SUM(t.download*p.rate),0),ROUND(SUM(t.sysactivate*p.rate),0),ROUND(SUM(t.activate*p.rate),0),ROUND(SUM(t.cost*p.rate),4)
		FROM t_static_orignal_byhour_sum t LEFT JOIN t_page p ON t.type_id=p.type_id AND t.app_id=p.app_id 
		WHERE t.STATIC_DATE = strdate AND t.STATIC_HOUR = strhour AND t.ad_id IS NOT NULL GROUP BY t.static_date,t.static_hour,t.ad_id,t.pay_type,t.app_id,t.type_id,t.channel
		UNION ALL
		SELECT t.static_date,t.static_hour,t.ad_id,t.pay_type,t.app_id,t.type_id,t.channel,ROUND(SUM(t.syspv*p.rate),0),ROUND(SUM(t.pv*p.rate),0),ROUND(SUM(t.sysclick*p.rate),0),ROUND(SUM(t.click*p.rate),0),
		ROUND(SUM(t.sysclickd*p.rate),0),ROUND(SUM(t.clickd*p.rate),0),ROUND(SUM(t.sysdownload*p.rate),0),ROUND(SUM(t.download*p.rate),0),ROUND(SUM(t.sysactivate*p.rate),0),ROUND(SUM(t.activate*p.rate),0),ROUND(SUM(t.cost*p.rate),4)
		FROM t_static_orignal_byhour_sum t LEFT JOIN t_page p ON t.type_id=6 AND p.type_id=1 AND t.app_id=p.app_id 
		WHERE t.STATIC_DATE = strdate AND t.STATIC_HOUR = strhour AND t.ad_id IS NOT NULL GROUP BY t.static_date,t.static_hour,t.ad_id,t.pay_type,t.app_id,t.type_id,t.channel;
		TRUNCATE TABLE t_static_orignal_byhour_sum;
	END;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for looptest
-- ----------------------------
DROP PROCEDURE IF EXISTS `looptest`;
DELIMITER ;;
CREATE DEFINER=`root`@`124.205.200.130` PROCEDURE `looptest`()
begin
   
   declare v_sscn bigint;
   declare v_escn bigint;
   declare v_mscn bigint;
   declare flag int;
   declare optype char(1);
   declare optab  char(1);
   declare v_id   bigint;
  select  ifnull(end_scn,start_scn) into v_sscn from scn_log;
-- 1
-- select v_sscn,v_escn;
  select  ifnull(max(scn),0) into v_mscn from t_user_log;
-- 2
-- select v_sscn,v_escn;
  set v_escn=ifnull(v_sscn,0) + 10000;
-- 3
-- select v_sscn,v_escn;
 
  if v_escn> v_mscn  then
    set v_escn = v_mscn + 1;
  end if;
 
   update scn_log set start_scn=v_sscn, end_scn=v_escn;  
-- 4
-- select v_sscn,v_escn;
 while v_sscn < v_escn do
   set flag=0;
   select count(scn),max(op_type),max(lower(right(uuid,1))),max(id) into flag,optype,optab,v_id from t_user_log where scn=v_sscn;
   
   if flag =1 then   
       if optab='0' then
           delete from t_user_0 where id=v_id;       
          if optype='I' or optype='U' then
              insert into t_user_0 
               select  id,uuid,imei,tel_num,tel_model,
                        net_env,area_code,operator,os,brand,
                        screen_width,screen_heigh,score,create_time,mac,
                        pda_type,app_id,imsi,area_province,openudid,
                        idfa,jailbroken,idfv
                   from t_user_log where scn=v_sscn;
          end if;
       elseif optab='1' then
          delete from t_user_1 where id=v_id;     
         if optype='I' or optype='U' then
             insert into t_user_1 
              select  id,uuid,imei,tel_num,tel_model,
                      net_env,area_code,operator,os,brand,
                      screen_width,screen_heigh,score,create_time,mac,
                      pda_type,app_id,imsi,area_province,openudid,
                      idfa,jailbroken,idfv
                 from t_user_log where scn=v_sscn;
         end if;
       elseif optab='2' then
          delete from t_user_2 where id=v_id;     
         if optype='I' or optype='U' then
             insert into t_user_2 
              select  id,uuid,imei,tel_num,tel_model,
                      net_env,area_code,operator,os,brand,
                      screen_width,screen_heigh,score,create_time,mac,
                      pda_type,app_id,imsi,area_province,openudid,
                      idfa,jailbroken,idfv
                 from t_user_log where scn=v_sscn;
         end if;
       elseif optab='3' then
          delete from t_user_3 where id=v_id;     
         if optype='I' or optype='U' then
             insert into t_user_3 
              select  id,uuid,imei,tel_num,tel_model,
                      net_env,area_code,operator,os,brand,
                      screen_width,screen_heigh,score,create_time,mac,
                      pda_type,app_id,imsi,area_province,openudid,
                      idfa,jailbroken,idfv
                 from t_user_log where scn=v_sscn;
         end if;
       elseif optab='4' then
          delete from t_user_4 where id=v_id;     
         if optype='I' or optype='U' then
             insert into t_user_4 
              select  id,uuid,imei,tel_num,tel_model,
                      net_env,area_code,operator,os,brand,
                      screen_width,screen_heigh,score,create_time,mac,
                      pda_type,app_id,imsi,area_province,openudid,
                      idfa,jailbroken,idfv
                 from t_user_log where scn=v_sscn;
         end if;
       elseif optab='5' then
          delete from t_user_5 where id=v_id;     
         if optype='I' or optype='U' then
             insert into t_user_5 
              select  id,uuid,imei,tel_num,tel_model,
                      net_env,area_code,operator,os,brand,
                      screen_width,screen_heigh,score,create_time,mac,
                      pda_type,app_id,imsi,area_province,openudid,
                      idfa,jailbroken,idfv
                 from t_user_log where scn=v_sscn;
         end if;
       elseif optab='6' then
          delete from t_user_6 where id=v_id;     
         if optype='I' or optype='U' then
             insert into t_user_6 
              select  id,uuid,imei,tel_num,tel_model,
                      net_env,area_code,operator,os,brand,
                      screen_width,screen_heigh,score,create_time,mac,
                      pda_type,app_id,imsi,area_province,openudid,
                      idfa,jailbroken,idfv
                 from t_user_log where scn=v_sscn;
         end if;
       elseif optab='7' then
          delete from t_user_7 where id=v_id;     
         if optype='I' or optype='U' then
             insert into t_user_7 
              select  id,uuid,imei,tel_num,tel_model,
                      net_env,area_code,operator,os,brand,
                      screen_width,screen_heigh,score,create_time,mac,
                      pda_type,app_id,imsi,area_province,openudid,
                      idfa,jailbroken,idfv
                 from t_user_log where scn=v_sscn;
         end if;
       elseif optab='8' then
          delete from t_user_8 where id=v_id;     
         if optype='I' or optype='U' then
             insert into t_user_8 
              select  id,uuid,imei,tel_num,tel_model,
                      net_env,area_code,operator,os,brand,
                      screen_width,screen_heigh,score,create_time,mac,
                      pda_type,app_id,imsi,area_province,openudid,
                      idfa,jailbroken,idfv
                 from t_user_log where scn=v_sscn;
         end if;
       elseif optab='9' then
          delete from t_user_9 where id=v_id;     
         if optype='I' or optype='U' then
             insert into t_user_9 
              select  id,uuid,imei,tel_num,tel_model,
                      net_env,area_code,operator,os,brand,
                      screen_width,screen_heigh,score,create_time,mac,
                      pda_type,app_id,imsi,area_province,openudid,
                      idfa,jailbroken,idfv
                 from t_user_log where scn=v_sscn;
         end if;
       elseif optab='a' then
          delete from t_user_a where id=v_id;     
         if optype='I' or optype='U' then
             insert into t_user_a 
              select  id,uuid,imei,tel_num,tel_model,
                      net_env,area_code,operator,os,brand,
                      screen_width,screen_heigh,score,create_time,mac,
                      pda_type,app_id,imsi,area_province,openudid,
                      idfa,jailbroken,idfv
                 from t_user_log where scn=v_sscn;
         end if;
       elseif optab='b' then
          delete from t_user_b where id=v_id;     
         if optype='I' or optype='U' then
             insert into t_user_b 
              select  id,uuid,imei,tel_num,tel_model,
                      net_env,area_code,operator,os,brand,
                      screen_width,screen_heigh,score,create_time,mac,
                      pda_type,app_id,imsi,area_province,openudid,
                      idfa,jailbroken,idfv
                 from t_user_log where scn=v_sscn;
         end if;
       elseif optab='c' then
          delete from t_user_c where id=v_id;     
         if optype='I' or optype='U' then
             insert into t_user_c 
              select  id,uuid,imei,tel_num,tel_model,
                      net_env,area_code,operator,os,brand,
                      screen_width,screen_heigh,score,create_time,mac,
                      pda_type,app_id,imsi,area_province,openudid,
                      idfa,jailbroken,idfv
                 from t_user_log where scn=v_sscn;
         end if;
       elseif optab='d' then
          delete from t_user_d where id=v_id;     
         if optype='I' or optype='U' then
             insert into t_user_d
              select  id,uuid,imei,tel_num,tel_model,
                      net_env,area_code,operator,os,brand,
                      screen_width,screen_heigh,score,create_time,mac,
                      pda_type,app_id,imsi,area_province,openudid,
                      idfa,jailbroken,idfv
                 from t_user_log where scn=v_sscn;
         end if;
       elseif optab='e' then
          delete from t_user_e where id=v_id;     
         if optype='I' or optype='U' then
             insert into t_user_e 
              select  id,uuid,imei,tel_num,tel_model,
                      net_env,area_code,operator,os,brand,
                      screen_width,screen_heigh,score,create_time,mac,
                      pda_type,app_id,imsi,area_province,openudid,
                      idfa,jailbroken,idfv
                 from t_user_log where scn=v_sscn;
         end if;
       elseif optab='f' then
          delete from t_user_f where id=v_id;     
         if optype='I' or optype='U' then
             insert into t_user_f 
              select  id,uuid,imei,tel_num,tel_model,
                      net_env,area_code,operator,os,brand,
                      screen_width,screen_heigh,score,create_time,mac,
                      pda_type,app_id,imsi,area_province,openudid,
                      idfa,jailbroken,idfv
                 from t_user_log where scn=v_sscn;
         end if;
       end if;
   end if;
  set v_sscn=v_sscn+1;
  end while;
 
  commit;
 end;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for while_loop
-- ----------------------------
DROP PROCEDURE IF EXISTS `while_loop`;
DELIMITER ;;
CREATE DEFINER=`root`@`124.205.200.130` PROCEDURE `while_loop`()
begin
 declare i int;
 set i=0;
  while i<30000 do
     call looptest;
    set i=i+1;
   select sleep(10);
  end while;
 end;;
DELIMITER ;

-- ----------------------------
-- Records 
-- ----------------------------

