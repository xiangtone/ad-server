
--1、创建物化视图表：
drop table app_visit_message_detail_mv2;
create table app_visit_message_detail_mv2
(
  app_id,
  uuid,
  create_time,
  wall_type,
  statnum
)
partition by range (create_time)
(
  partition p201206 values less than (to_date('2012-07-01', 'yyyy-mm-dd')) tablespace yijifen,
  partition p201207 values less than (to_date('2012-08-01', 'yyyy-mm-dd')) tablespace yijifen,
  partition p201208 values less than (to_date('2012-09-01', 'yyyy-mm-dd')) tablespace yijifen,
  partition p201209 values less than (to_date('2012-10-01', 'yyyy-mm-dd')) tablespace yijifen,
  partition p201210 values less than (to_date('2012-11-01', 'yyyy-mm-dd')) tablespace yijifen,
  partition p201211 values less than (to_date('2012-12-01', 'yyyy-mm-dd')) tablespace yijifen,
  partition p201212 values less than (to_date('2013-01-01', 'yyyy-mm-dd')) tablespace yijifen,
  partition p201301 values less than (to_date('2013-02-01', 'yyyy-mm-dd')) tablespace yijifen,
  partition p201302 values less than (to_date('2013-03-01', 'yyyy-mm-dd')) tablespace yijifen,
  partition p201303 values less than (to_date('2013-04-01', 'yyyy-mm-dd')) tablespace yijifen,
  partition p201304 values less than (to_date('2013-05-01', 'yyyy-mm-dd')) tablespace yijifen,
  partition p201305 values less than (to_date('2013-06-01', 'yyyy-mm-dd')) tablespace yijifen,
  partition p201306 values less than (to_date('2013-07-01', 'yyyy-mm-dd')) tablespace yijifen,
  partition p201307 values less than (to_date('2013-08-01', 'yyyy-mm-dd')) tablespace yijifen,
  partition p201308 values less than (to_date('2013-09-01', 'yyyy-mm-dd')) tablespace yijifen,
  partition p201309 values less than (to_date('2013-10-01', 'yyyy-mm-dd')) tablespace yijifen,
  partition p201310 values less than (to_date('2013-11-01', 'yyyy-mm-dd')) tablespace yijifen,
  partition p201311 values less than (to_date('2013-12-01', 'yyyy-mm-dd')) tablespace yijifen,
  partition p201312 values less than (to_date('2014-01-01', 'yyyy-mm-dd')) tablespace yijifen,
  partition p201401 values less than (to_date('2014-02-01', 'yyyy-mm-dd')) tablespace yijifen,
  partition pmax values less than (maxvalue) tablespace yijifen
)
nologging
as
select app_id,uuid,trunc(create_time) create_time,wall_type,count(1) statnum 
from  app_visit_message_detail_part  where rownum <1
and flag = 0
group by
 wall_type,trunc(create_time),app_id,uuid;

 --2,、创建索引
 create unique index  ind_avmd_mv2
 on app_visit_message_detail_mv2(  create_time,app_id, uuid, wall_type, statnum)
 local
  tablespace yijifen_ind nologging;  

--3、创建物化视图
drop materialized view app_visit_message_detail_mv2;

 create materialized view APP_VISIT_MESSAGE_DETAIL_MV2
 on prebuilt table
  as 
 select app_id,uuid,trunc(create_time) create_time,wall_type,count(1) statnum 
from  app_visit_message_detail_part  where 
 flag = 0
group by
 wall_type,trunc(create_time),app_id,uuid;
 
 
 
--4.物化视图的刷新是使用
begin dbms_mview.refresh('APP_VISIT_MESSAGE_DETAIL_MV2','c'); end;

-----------------------------------------------------------------------------------


--1、创建物化视图表:
drop table app_visit_message_detail_mv;
create table app_visit_message_detail_mv
(
  app_id,
  uuid,
  ip,
  create_time,
  wall_type
)
partition by range (create_time)
(
  partition p201206 values less than (to_date('2012-07-01', 'yyyy-mm-dd')) tablespace yijifen,
  partition p201207 values less than (to_date('2012-08-01', 'yyyy-mm-dd')) tablespace yijifen,
  partition p201208 values less than (to_date('2012-09-01', 'yyyy-mm-dd')) tablespace yijifen,
  partition p201209 values less than (to_date('2012-10-01', 'yyyy-mm-dd')) tablespace yijifen,
  partition p201210 values less than (to_date('2012-11-01', 'yyyy-mm-dd')) tablespace yijifen,
  partition p201211 values less than (to_date('2012-12-01', 'yyyy-mm-dd')) tablespace yijifen,
  partition p201212 values less than (to_date('2013-01-01', 'yyyy-mm-dd')) tablespace yijifen,
  partition p201301 values less than (to_date('2013-02-01', 'yyyy-mm-dd')) tablespace yijifen,
  partition p201302 values less than (to_date('2013-03-01', 'yyyy-mm-dd')) tablespace yijifen,
  partition p201303 values less than (to_date('2013-04-01', 'yyyy-mm-dd')) tablespace yijifen,
  partition p201304 values less than (to_date('2013-05-01', 'yyyy-mm-dd')) tablespace yijifen,
  partition p201305 values less than (to_date('2013-06-01', 'yyyy-mm-dd')) tablespace yijifen,
  partition p201306 values less than (to_date('2013-07-01', 'yyyy-mm-dd')) tablespace yijifen,
  partition p201307 values less than (to_date('2013-08-01', 'yyyy-mm-dd')) tablespace yijifen,
  partition p201308 values less than (to_date('2013-09-01', 'yyyy-mm-dd')) tablespace yijifen,
  partition p201309 values less than (to_date('2013-10-01', 'yyyy-mm-dd')) tablespace yijifen,
  partition p201310 values less than (to_date('2013-11-01', 'yyyy-mm-dd')) tablespace yijifen,
  partition p201311 values less than (to_date('2013-12-01', 'yyyy-mm-dd')) tablespace yijifen,
  partition p201312 values less than (to_date('2014-01-01', 'yyyy-mm-dd')) tablespace yijifen,
  partition p201401 values less than (to_date('2014-02-01', 'yyyy-mm-dd')) tablespace yijifen,
  partition pmax values less than (maxvalue) tablespace yijifen
)
nologging
as
select app_id,uuid,ip,trunc(create_time),wall_type
from  app_visit_message_detail_part  where rownum <1
group by
 wall_type,trunc(create_time),app_id,ip,uuid;

--2、创建索引
 create unique index  ind_ios_action_log_create_time on T_IOS_ACTION_LOG(create_time)local tablespace escore nologging;  

--3、创建物化视图：
drop materialized view app_visit_message_detail_mv;

 create materialized view APP_VISIT_MESSAGE_DETAIL_MV
 on prebuilt table
  as select wall_type,trunc(create_time) create_time,app_id,ip,uuid
from  app_visit_message_detail_part 
group by
 wall_type,trunc(create_time) ,app_id,ip,uuid;
 
 
 --4、刷新
 begin dbms_mview.refresh('APP_VISIT_MESSAGE_DETAIL_MV','c'); end;
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 

 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 


