CREATE OR REPLACE VIEW VIEW_USER AS
SELECT email,password, 1 AS type,status FROM T_ADVERTISER
UNION ALL
SELECT email,password, 3 AS type,status FROM T_CHANNEL
UNION ALL
SELECT email,password, 2 AS type,status FROM T_DEVELOPER;
