/*
Target Server Type    : ORACLE
Target Server Version : 11
File Encoding         : utf-8
name:P_PACKAGE_ACTIVATE_JOB
Author  :cuidd
Date: 2013-08-22
*/
CREATE OR REPLACE PROCEDURE "P_DATAMONITOR"(strdate IN VARCHAR2,strhour IN VARCHAR2,last_strdate in varchar2,lasthour in varchar2) is
      data_count number(30);
      last_date_count number(30);
       ID T_LOG_MONITOR.Id%type;
      -- STATUS T_LOG_MONITOR.Status%type;
      -- TIMING_TASK_TYPE T_LOG_MONITOR.Timing_Task_Type%type;		
     --  DISPATCH_STATUS T_LOG_MONITOR.Dispatch_Status%type;
       CREATE_TIME T_LOG_MONITOR.Create_Time%type;
       HOUR T_LOG_MONITOR.Hour%type;
       STATIC_DATE T_LOG_MONITOR.Static_Date%type;

begin
    --计算昨天的日期执行存储过程
    --1获取当前小时的数据总激活数
       SELECT  sum(CLICK)   into data_count  FROM T_STATIC_APP_BYHOUR where static_date=strdate and static_hour =strhour ;
       SELECT  sum(click)   into last_date_count  FROM T_STATIC_APP_BYHOUR where static_date=last_strdate and static_hour =lasthour;
        dbms_output.put_line('-----'||last_date_count);
       INSERT INTO T_LOG_MONITOR_DETAIL( ID,CREATE_TIME,HOUR,STATIC_DATE,DATE_COUNT,LAST_DATE_COUNT,TABLE_NAME,COLUMN_NAME)VALUES( SEQ_LOG_MONITOR_DETAIL.NEXTVAL,SYSDATE,strhour,strdate,data_count,LAST_DATE_COUNT,'CLICK','T_STATIC_APP_BYHOUR');
end P_DATAMONITOR;
