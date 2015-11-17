CREATE OR REPLACE PROCEDURE "ESCORE_STATIC_ADCOST"(strdate IN VARCHAR2,
                                                   strhour IN VARCHAR2) AS

BEGIN
  update t_ad set cost_day = '0.00';
MERGE INTO T_AD t
      USING (select ad_id,
                    round(sum(adcost.pv) / 1000, 0) as pv,
                    sum(adcost.click) as click,
                    sum(adcost.download) as download,
                    sum(adcost.activate) as activate
               from t_static_ad_byhour adcost
              where adcost.static_date = strdate
              group by adcost.ad_id) adcost_day
      ON (t.id = adcost_day.ad_id)
      WHEN MATCHED THEN
        update
           set t.cost_day =
               (case
                 when BUDGET_TYPE = 'M' then
                  adcost_day.pv
                 when BUDGET_TYPE = 'C' then
                  adcost_day.click
                 when BUDGET_TYPE = 'D' then
                  adcost_day.download
                 when BUDGET_TYPE = 'A' then
                  adcost_day.activate
                 else
                  0
               end)
         where t.budget_day is not null
           and t.budget_day > '0';


END ESCORE_STATIC_ADCOST;
