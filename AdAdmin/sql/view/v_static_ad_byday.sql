create or replace view v_static_ad_byday as
select 
to_date( a."STATIC_DATE" ,'yyyy-mm-dd')  "统计日期",
       t.adv_id "广告主id",
       t.id "广告id",
       t.placement_name "广告名称",
       f.name as  "广告形式",
       t.os "系统类型",
       nvl(a.adpv, 0) as "展示",
       nvl(a.click, 0) as "点击",
       nvl(a.download, 0) as "下载" ,
       nvl(a.activate, 0) as  "激活",
       nvl(round(a.cost, 4), 0) as "收益",
       nvl(round(a.click * 100 / a.adpv, 4), 0)  "点击转化率",
       nvl(round(a.download * 100 / a.click, 4), 0) "下载转化率",
       nvl(round(a.activate * 100 / a.download, 4), 0) "激活转化率"
  from (select static_date,
               ad_id,
               type_id,
               sum(pv) as adpv,
               sum(click) as click,
               sum(download) as download,
               sum(activate) as activate,
               sum(cost) as cost
          from t_static_ad_byday
         group by ad_id, type_id, static_date) a
  left join v_ad_campaign t
    on t.id = a.ad_id
  left join t_type f
    on f.id = a.type_id
 where t.id IS NOT NULL;
