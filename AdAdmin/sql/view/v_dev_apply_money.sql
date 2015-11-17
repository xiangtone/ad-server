create or replace view v_dev_apply_money as
select dev.email              dev_email,
       dev.FINANCE_INCOME     as dev_balance,
       u.real_name            manager_name,
       t."ACCOUNT_HODER" kaihu_name,
       u2.real_name           finance_name,
       t."ID",
       t."DEV_ID",
       t."PAY_TYPE",
       t."MANAGER_ID",
       t."MANAGER_MONEY",
       t."MANAGER_TIME",
       t."APPLY_MONEY",
       t."STATUS",
       t."MANAGER_DESC",
       t."FINANCE_ID",
       t."FINANCE_MONEY",
       t."BALANCE_MONEY",
       t."FINANCE_TAX",
       t."FINANCE_DUES",
       t."FINANCE_TIME",
       t."CREATE_TIME",
       t."TAX_STATUS",
       t."BANK_NAME",
       t."BANK_SUBBRANCH",
       t."BANK_ACCOUNT",
       t.invoice,
       t."BANK_CITY"
  from T_DEV_APPLY_MONEY t
  left join T_DEVELOPER dev
    on T.DEV_ID = dev.id
  left join T_SYS_USER u
    on t.MANAGER_ID = u.id
  left join T_SYS_USER u2
    on t.finance_ID = u2.id;
