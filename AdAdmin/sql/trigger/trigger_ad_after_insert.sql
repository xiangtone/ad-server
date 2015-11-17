DELIMITER $$

USE `ad_platform`$$

DROP TRIGGER /*!50032 IF EXISTS */ `trigger_ad_after_insert`$$

CREATE
    /*!50017 DEFINER = 'root'@'localhost' */
    TRIGGER `trigger_ad_after_insert` AFTER INSERT ON `t_ad` 
    FOR EACH ROW BEGIN
		DECLARE os VARCHAR(10);
		DECLARE temp_ad_type INT(11);
		SET temp_ad_type = NEW.ad_type;
		SELECT os INTO os FROM T_PLACEMENT p WHERE p.id = NEW.PLACEMENT_id;
		INSERT INTO T_MEDIA_SCALE(ID, PLACEMENT_ID, MEDIA_ID, PACKAGE_ID,SCALE, OS) VALUES(NEW.ID,NEW.PLACEMENT_ID, NEW.MEDIA_ID, NEW.PACKAGE_ID, 1, OS);
	END;
$$

DELIMITER ;