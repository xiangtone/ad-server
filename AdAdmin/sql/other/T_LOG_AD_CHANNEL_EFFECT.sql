merge into T_LOG_AD_CHANNEL_EFFECT c
using (SELECT temp.*, rel.CAMPAIGN_ID
         FROM ((SELECT PACKAGE_ID,
                       media_id,
                       -1 as type_id,
                       id as ad_id,
                       0 as type,
                       'android' as os
                  FROM T_AD ad
                 where ad_type = 1) UNION all
               (SELECT PACKAGE_ID,
                       media_id,
                       type_id,
                       -1 as ad_id,
                       1 as type,
                       'android' as os
                  FROM T_AD
                 GROUP BY PACKAGE_ID, media_id, type_id)) temp
         LEFT JOIN T_PLACEMENT_PACKAGE p
           on temp. PACKAGE_ID = p.id
         LEFT JOIN T_CAMPAIGN_PLACEMENT_REL rel
           on p.Placement_id = rel.Placement_id) t
on (c.PACKAGE_ID = t.PACKAGE_ID and c.CHANNEL_ID = t.media_id and c.type_id = c.type_id and c.STATIC_DATE = '2013-7-12')
when not matched then
  insert
    (id,
     CREATE_TIME,
     PACKAGE_ID,
     CHANNEL_ID,
     type_id,
     ad_id,
     STATIC_DATE,
     type,
     os,
     CAMPAIGN_ID)
  values
    (SE_AD_RECODER.NEXTVAL,
     TO_DATE('2013-07-12 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
     t.PACKAGE_ID,
     t.media_id,
     t.type_id,
     t.ad_id,
     '2013-7-12',
     t.type,
     t.os,
    t. CAMPAIGN_ID)
