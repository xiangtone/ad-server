CREATE OR REPLACE VIEW V_PLACEMENT_PACKAGE AS
SELECT c.CAMPAIGN_NAME,
rel.CAMPAIGN_ID ,
       
       p."ID",
       p."RES_URL",
       p."CREATE_USER",
       p."CODE",
       p."FILE_NAME",
       p."PLACEMENT_ID",
       p."PACKAGE_NAME",
       p."VERSION_NAME",
       p."VERSION_CODE",
       p."CREATE_TIME",
       p."STATUS",
       p."FILE_SIZE",
       PLACEMENT."OS",
       p.remarks
  FROM T_PLACEMENT_PACKAGE p
  LEFT JOIN T_CAMPAIGN_PLACEMENT_REL rel
    on p.PLACEMENT_ID = rel.PLACEMENT_ID
  LEFT JOIN T_CAMPAIGN c
    ON rel.CAMPAIGN_ID = c.id
     left join   T_PLACEMENT PLACEMENT on p.placement_id=PLACEMENT.id;
