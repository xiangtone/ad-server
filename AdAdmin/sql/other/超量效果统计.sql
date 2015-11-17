SELECT  *  from  (SELECT p.name,a.*,ad.budget_type,ad.budget_day,ad.type_id  from (SELECT  ad_id,max(static_hour) hour,sum(click),sum(pv),sum(download),sum(activate)  from T_STATIC_AD_BYHOUR  WHERE STATIC_date='2013-10-27' GROUP by ad_id) a LEFT  JOIN  T_AD ad on a.ad_id=ad.id
LEFT  JOIN  T_PLACEMENT p  on ad.PLACEMENT_id=p.id where p.os='android'
order by a.hour 
) 