DELIMITER $$

USE `ad_platform`$$

DROP TRIGGER /*!50032 IF EXISTS */ `trigger_ad_before_update`$$

CREATE
    /*!50017 DEFINER = 'root'@'localhost' */
    TRIGGER `trigger_ad_before_update` BEFORE UPDATE ON `t_ad` 
    FOR EACH ROW BEGIN
		DECLARE new_ad_status INT(11);
		DECLARE old_ad_status INT(5);
		DECLARE ad_click INT(20);
		DECLARE ad_pv INT(20);
		DECLARE ad_download INT(20);
		DECLARE ad_activate INT(20);
		DECLARE v_count INT(11);
		DECLARE v_off_line_type INT(11);
		SET new_ad_status = NEW.`status`;
		SET old_ad_status = OLD.`status`;
		IF (old_ad_status=1 AND (new_ad_status=-30 OR new_ad_status=-1)) THEN
			SELECT COUNT(1) INTO v_count FROM T_AD_ACTUAL_DAY WHERE ad_id= OLD.id AND static_date=DATE_FORMAT(NOW(), '%Y-%m-%d');
			IF v_count=0 THEN
				SET ad_click=0;
				SET ad_pv=0;
				SET ad_download=0;
				SET ad_activate=0;
			ELSE
				SELECT IMPRESSIONS_AMOUNT,CLICK_AMOUNT,DOWNLOAD_AMOUNT,ACTION_AMOUNT INTO ad_pv,ad_click,ad_download,ad_activate FROM T_AD_ACTUAL_DAY WHERE ad_id=OLD.id AND static_date=DATE_FORMAT(NOW(), '%Y-%m-%d');
			END IF;
			CASE WHEN new_ad_status=-30 THEN SET v_off_line_type=0;
			WHEN new_ad_status=-1 THEN SET v_off_line_type=1;
			END CASE;
			INSERT INTO T_LOG_AD_BUDGET(BUDGET_DAY,CREATE_TIME,OFFLINE_TIME,ONLINE_TIME,BUDGET_TYPE,STATIC_DATE,AD_ID,CLICK,DOWNLOAD,PV,ACTIVATE,OFFLINE_TYPE,STATUS) VALUES (OLD.BUDGET_DAY,NOW(),NOW(),OLD.online_time,OLD.BUDGET_TYPE,DATE_FORMAT(NOW(), '%Y-%m-%d'),OLD.id,ad_click,ad_download,ad_pv,ad_activate,v_off_line_type,0);
		END IF;
	END;
$$

DELIMITER ;