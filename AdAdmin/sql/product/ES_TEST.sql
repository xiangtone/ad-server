CREATE OR REPLACE PROCEDURE "ES_TEST"(strdate IN VARCHAR2) AS
  my_cursor    package_page.cursor_page;
  PACKAGE_ID   t_package_activate.package_id%type;
  SYS_ACTIVATE t_package_activate.sys_activate%type;
BEGIN
  --平台的


  open my_cursor for 'SELECT  PACKAGE_ID,sum(SYS_ACTIVATE) SYS_ACTIVATE  FROM  T_PACKAGE_ACTIVATE_DETAIL where static_date=:static_date group by PACKAGE_ID'
    using strdate;
  LOOP
    FETCH my_cursor
      INTO PACKAGE_ID, SYS_ACTIVATE;
  EXIT WHEN my_cursor%NOTFOUND;
    DBMS_OUTPUT.PUT_LINE(PACKAGE_ID);
  
  END LOOP;
  CLOSE my_cursor;
END ES_TEST;
