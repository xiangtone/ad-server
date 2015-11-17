CREATE OR REPLACE PROCEDURE "ESCORE_PACKAGE_ACTIVATE"(strdate IN VARCHAR2) AS
  my_cursor    package_page.cursor_page;--自定义游标
  temp_id      t_package_activate.id%type;--id
  PACKAGE_ID   t_package_activate.package_id%type;--包id
  SYS_ACTIVATE t_package_activate.sys_activate%type;--系统激活数
BEGIN
  --分别抓取平台渠道的广告投放信息放入临时表中
  insert into TEMP_PACKAGE_ACTIVATE
    (id,
     PACKAGE_ID,
     STATIC_DATE,
     MEDIA_ID,
     TYPE_ID,
     SYS_ACTIVATE,
     sys_cost,
     out_price,
     media_type,
     BLANCE_MODE,
     in_price)
    select seq_package_activate.NEXTVAL, t.*, v.PRICE
      from ((select PACKAGE_ID,
                    strdate,
                    MEDIA_ID,
                    TYPE_ID,
                    sum(stat.activate),
                    sum(cost) cost,
                    null as out_price,
                    0 as media_type,
                    null as BLANCE_MODE
               from T_LOG_AD_PLACEMENT p
              INNER JOIN (SELECT ad_id,
                                sum(ACTIVATE) ACTIVATE,
                                sum(cost) cost
                           FROM T_STATIC_AD_BYDAY
                          where static_date = strdate
                          group by ad_id
                         having(sum(cost) > 0 OR sum(ACTIVATE) > 0)) stat
                 on p.ad_id = stat.ad_id
              where static_date = strdate and p.os='android'
              group by PACKAGE_ID, MEDIA_ID, TYPE_ID) UNION all
            (select PACKAGE_ID,
                    strdate,
                    MEDIA_ID,
                    TYPE_ID,
                    0           as activate,
                    0           as cost,
                    ad_price    as out_price,
                    1           as media_type,
                    BLANCE_MODE as BLANCE_MODE
             
               from T_LOG_AD_PLACEMENT
              where ad_type = 1
                and static_date = strdate
                and os='android'
              and status!=-5
             )) t
      left Join T_PLACEMENT_PACKAGE pac
        on t.package_id = pac.id
      left join v_campaign v
        on pac.placement_id = v.PLACEMENT_id;

        --开启游标生成主表---并维护临时表中的数据跟主表之间的关系
  open my_cursor for 'select  PACKAGE_ID,sum(SYS_ACTIVATE) SYS_ACTIVATE from TEMP_PACKAGE_ACTIVATE group by PACKAGE_ID';
  LOOP
    FETCH my_cursor
      INTO PACKAGE_ID, SYS_ACTIVATE;
    EXIT WHEN my_cursor%NOTFOUND;
    DBMS_OUTPUT.PUT_LINE(PACKAGE_ID);
    select seq_package_activate.NEXTVAL into temp_id FROM DUAL;
    DBMS_OUTPUT.PUT_LINE(temp_id);--调试语句
    EXECUTE IMMEDIATE 'insert into T_PACKAGE_ACTIVATE(id,CREATE_TIME,PACKAGE_ID,STATIC_DATE,SYS_ACTIVATE,status)VALUES(:id,sysdate,:PACKAGE_ID,:STATIC_DATE,:SYS_ACTIVATE,0)'
      USING temp_id, package_id, strdate, SYS_ACTIVATE;
    EXECUTE IMMEDIATE 'UPDATE T_PACKAGE_ACTIVATE_TEMP SET PARENT_ID=:parent_id where package_id=:package_id'
      USING temp_id, package_id;
    --创建每个表对应的投放信息
  
  END LOOP;
  CLOSE my_cursor;

  --取出临时表中的数据放入物理表里边
  insert into T_PACKAGE_ACTIVATE_DETAIL
    (id,
     PACKAGE_ID,
     STATIC_DATE,
     MEDIA_ID,
     TYPE_ID,
     SYS_ACTIVATE,
     parent_id,
     sys_cost,
     in_price,
     out_price,
     media_type,
     BLANCE_MODE,status)
    select id,
           PACKAGE_ID,
           STATIC_DATE,
           MEDIA_ID,
           TYPE_ID,
           SYS_ACTIVATE,
           parent_id,
           sys_cost,
           in_price,
           out_price,
           media_type,
           BLANCE_MODE,0 as status
      from TEMP_PACKAGE_ACTIVATE;
END ESCORE_PACKAGE_ACTIVATE;