create or replace view v_static_app_byday as
select 
	to_date( a."STATIC_DATE" ,'yyyy-mm-dd')  "统计日期",
       t.dev_id "开发者id",
       t.id "应用id",
       t.name "应用名称",
       t.os "系统类型",
       f.name  "广告形式",
       nvl(a.pospv, 0) as  "墙展示",
       nvl(a.adpv, 0) as  "广告展示",
       nvl(a.click, 0) as  "点击",
       nvl(a.clickd, 0) as  "二次点击",
       nvl(a.download, 0) as  "下载",
       nvl(a.activate, 0) as  "激活",
       nvl(round(a.cost, 4), 0) as  "成本",
       nvl(round(a.click * 100 / a.adpv, 4), 0) "点击转化率",
       nvl(round(a.download * 100 / a.click, 4), 0) "下载转化率",
       nvl(round(a.activate * 100 / a.download, 4), 0) "激活转化率"
  from (select static_date,
               app_id,
               type_id,
               sum(pospv) as pospv,
               sum(pv) as adpv,
               sum(click) as click,
               sum(clickd) as clickd,
               sum(download) as download,
               sum(activate) as activate,
               sum(cost) as cost
          from t_static_app_byday
         group by app_id, type_id, static_date) a
  left join t_application t
    on t.id = a.app_id
  left join t_type f
    on f.id = a.type_id
 where t.id IS NOT NULL;
