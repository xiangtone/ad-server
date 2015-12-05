/*
 * ReportDevAdDayStatDaoImpl.java
 *
 * Copyright 2011 1000CHI Software Technology Co.,Ltd
 * All rights reserved.
 * 
 * Created on 20-Dec-2011
 */
package cn.adwalker.model.operation.dao.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.repository.impl.BaseDaoImpl;
import cn.adwalker.core.util.lang.ObjectUtils;
import cn.adwalker.model.operation.dao.IOperationDevPresentDayDao;
import cn.adwalker.ad.admin.operation.bean.OperDevPresentbean;

/**
 * 功能概述：<br>
 * 开发者广告报表
 * 
 * @author zhaozengbin,guoyongxiang
 */
@Repository("operationDevPresentDayDao")
public class OperationDevPresentDayDaoImpl extends BaseDaoImpl implements IOperationDevPresentDayDao {

	@Override
	public double findSumReward(OperDevPresentbean bean, IPageInfo pageInfo) throws Exception {
		double value = 0d;
		StringBuffer sql = new StringBuffer();
		sql.append("select sum(MONEY) as reward_money from V_FINANCE_DEV_AWARD_PUNISH where 1=1 and TYPE=0 ");
		if (ObjectUtils.isNotEmpty((bean.getDev_Id()))) {
			sql.append(" and DEV_ID ");
			sql.append(" = ");
			sql.append(bean.getDev_Id());
		}
		if (ObjectUtils.isNotEmpty((bean.getDev_Name()))) {
			sql.append(" and DEV_EMAIL ");
			sql.append(" like '%");
			sql.append(bean.getDev_Name());
			sql.append("%'");
		}
		if (ObjectUtils.isNotEmpty(bean.getStat_date_begin())) {
			sql.append(" and CREATE_TIME>='" + bean.getStat_date_begin() + " 00:00:00'");
		}
		if (ObjectUtils.isNotEmpty(bean.getStat_date_end())) {
			sql.append(" and CREATE_TIME<='" + bean.getStat_date_end() + " 23:59:59'");
		}
		List<Map<String, Object>> resultLst = (List<Map<String, Object>>) jdbcTemplate.queryForList(sql.toString());
		if (resultLst != null && resultLst.size() > 0) {
			BigDecimal bb = (BigDecimal) resultLst.get(0).get("reward_money");
			if (bb != null) {
				value = bb.doubleValue();
			}
		}
		return value;
	}

}
