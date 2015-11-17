CREATE OR REPLACE PROCEDURE "P_DATAMONITOR_JOB" is
        tmp_date date:=sysdate-1/24;
         s_date varchar2(10):=to_char(tmp_date,'yyyy-mm-dd');
        s_hour varchar2(2):=to_char(tmp_date,'hh24');
         tem_date_last date:=sysdate-2/24;
         s_date_last varchar2(10):=to_char(tem_date_last,'yyyy-mm-dd');
         s_hour_last varchar2(2):=to_char(tem_date_last,'hh24');
    
      
begin
    --测试计算日期及时间
     dbms_output.put_line(s_date||'------'||s_hour);
     dbms_output.put_line(s_date_last||'------'||s_hour_last);
     P_DATAMONITOR(s_date,s_hour,s_date_last,s_hour_last);
     
end P_DATAMONITOR_JOB;
