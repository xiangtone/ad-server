--定制JOB
--JOB删除
begin	
dbms_scheduler.drop_job(job_name => 'JOB_DATAMONITOR',force => TRUE);
end;

--JOB创建
begin
dbms_scheduler.create_job (
job_name => 'JOB_DATAMONITOR',--JOB名
job_type => 'STORED_PROCEDURE',--调度类型
job_action => 'P_DATAMONITOR_JOB', --存储过程名
start_date => sysdate,--现在开始执行
--repeat_interval => 'FREQ=SECONDLY; INTERVAL=1;' -- 按秒，每秒执行一次
repeat_interval => 'FREQ = HOURLY; BYMINUTE = 40; BYSECOND = 0' --按天，每天八点
);
end;

--JOB 启用
begin
dbms_scheduler.enable('JOB_DATAMONITOR');
end;

--JOB运行
begin
dbms_scheduler.run_job('JOB_DATAMONITOR',TRUE); -- true代表同步执行
end;

--暂停
BEGIN dbms_scheduler.stop_job(job_name =>'JOB_PACKAGE_ACTIVATE',force => TRUE);END;

--停止
BEGIN dbms_scheduler.disable('JOB_PACKAGE_ACTIVATE');END;

