create or replace view v_campaign_category as
select  t."ID",t."CAMPAIGN_ID",t."CATEGORY_ID",c."TYPE",t."CREATE_TIME",
c.content_value,c.sort,c.name
 from T_CAMPAIGN_CATEGORY_REL t left join T_ESCORE_SORT c  on  t.category_id=c.id;
