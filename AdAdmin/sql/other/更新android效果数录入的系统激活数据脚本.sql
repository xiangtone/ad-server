select *  from  (SELECT  id,type_id,package_id,media_id,sys_activate,sys_cost  from  T_PACKAGE_ACTIVATE_DETAIL t  where t.static_date='2013-10-06'  and media_type=0)a
left join ( select  t.*
      from ((select PACKAGE_ID,
                    '2013-10-06',
                    MEDIA_ID,
                    TYPE_ID,
                    sum(stat.activate),
                    sum(cost) cost
               from T_LOG_AD_PLACEMENT p
              INNER JOIN (SELECT ad_id,
                                sum(ACTIVATE) ACTIVATE,
                                sum(cost) cost
                           FROM T_STATIC_AD_BYDAY
                          where static_date ='2013-10-06'
                          group by ad_id
                         having(sum(cost) > 0 OR sum(ACTIVATE) > 0)) stat
                 on p.ad_id = stat.ad_id
              where static_date = '2013-10-06' and p.os='android'
              group by PACKAGE_ID, MEDIA_ID, TYPE_ID))t
      left Join T_PLACEMENT_PACKAGE pac
        on t.package_id = pac.id) b on  
        (a.type_id=b.type_id and a.package_id=b.package_id and a.media_id=b.media_id)