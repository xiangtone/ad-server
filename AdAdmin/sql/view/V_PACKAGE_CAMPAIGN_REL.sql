CREATE OR REPLACE VIEW V_PACKAGE_CAMPAIGN_REL AS
SELECT  PAK.ID,PAK. PLACEMENT_ID,REL.CAMPAIGN_ID  FROM   T_PLACEMENT_PACKAGE pak LEFT JOIN  T_CAMPAIGN_PLACEMENT_REL rel  on PAK. PLACEMENT_ID=REL.PLACEMENT_ID;
