create or replace view v_escore_category as
select t1."ID",t1."PARENT_ID",t1."NAME",t1."SORT",t1."CREATE_TIME",t2.name parent_name from T_ESCORE_CATEGORY t1  LEFT join  T_ESCORE_CATEGORY t2 ON t1.parent_id=t2.id;
