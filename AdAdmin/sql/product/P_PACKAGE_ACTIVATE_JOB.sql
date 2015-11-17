/*
Target Server Type    : ORACLE
Target Server Version : 11
File Encoding         : utf-8
name:P_PACKAGE_ACTIVATE_JOB
Author  :cuidd
Date: 2013-08-22
*/
CREATE OR REPLACE PROCEDURE "P_DATA_DATAMONITOR_JOB" is
      s_date varchar2(10):=to_char(sysdate-1,'yyyy-mm-dd');
begin
    --计算昨天的日期执行存储过程
   ESCORE_PACKAGE_ACTIVATE(s_date);
 
end P_DATA_DATAMONITOR_JOB;