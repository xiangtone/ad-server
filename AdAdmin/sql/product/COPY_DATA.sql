CREATE OR REPLACE PROCEDURE "COPY_DATA" IS
tmpVar NUMBER;
/******************************************************************************
   NAME:       copy_data
   PURPOSE:    

   REVISIONS:
   Ver        Date        Author           Description
   ---------  ----------  ---------------  ------------------------------------
   1.0        2013-4-28          1. Created this procedure.

   NOTES:

   Automatically available Auto Replace Keywords:
      Object Name:     copy_data
      Sysdate:         2013-4-28
      Date and Time:   2013-4-28, 下午 03:06:46, and 2013-4-28 下午 03:06:46
      Username:         (set in TOAD Options, Procedure Editor)
      Table Name:       (set in the "New PL/SQL Object" dialog)

******************************************************************************/

/*定义游标*/
cursor cur_result is 
    select click,static_date,app_id,type_id,pospv,pv,download,activate,cost,syspv,sysclick,sysdownload,sysactivate,clickd,sysclickd,syspospv 
    from escore.t_static_app_byday;

/*定义游标数据行*/
one_row cur_result%rowtype;  

BEGIN
    /*判断如果游标处于打开状态先关闭*/
     if cur_result%isopen then
       close cur_result;
     end if;
    /*游标遍历*/
    for one_row in cur_result loop
         insert into escore_alpha.t_static_app_byday
          (click,
           static_date,
           app_id,
           type_id,
           pospv,
           pv,
           download,
           activate,
           cost,
           syspv,
           sysclick,
           sysdownload,
           sysactivate,
           clickd,
           sysclickd,
           syspospv)
        values
          (one_row.click,
           one_row.static_date,
           one_row.app_id,
           one_row.type_id,
           one_row.pospv,
           one_row.pv,
           one_row.download,
           one_row.activate,
           one_row.cost,
           one_row.syspv,
           one_row.sysclick,
           one_row.sysdownload,
           one_row.sysactivate,
           one_row.clickd,
           one_row.sysclickd,
           one_row.syspospv);
    end loop;
    dbms_output.put_line('数据统计完成');
    commit;
exception 
    when others then 
    dbms_output.put_line(sqlcode||' '||sqlerrm); 
END copy_data; 
