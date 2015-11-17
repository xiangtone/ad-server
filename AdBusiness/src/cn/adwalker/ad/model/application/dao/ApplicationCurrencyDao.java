package cn.adwalker.ad.model.application.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import cn.adwalker.ad.model.application.domain.AppCurrency;

/**
 * 功能概述：<br>
 * 开发者SDK应用实现类
 */
@Repository("applicationCurrencyDao")
public class ApplicationCurrencyDao {

	@Resource
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public void saveOrUpdate(AppCurrency currency) throws Exception {
		if (currency != null && currency.getApp_id() != null) {
			AppCurrency entity = getByAppId(currency.getApp_id());
			boolean flag = (entity != null) ? true : false;
			StringBuffer sql = new StringBuffer();
			if (!flag) {
				entity = new AppCurrency();
				entity.setStatus(1);
			}
			entity.setExchange_rate_rmb(currency.getExchange_rate_rmb());
			entity.setVirtual_currency_name(currency.getVirtual_currency_name());
			entity.setApp_id(currency.getApp_id());
			if (flag) {
				sql.append(" update T_DEV_CURRENCY set ");
				sql.append("VIRTUAL_CURRENCY_NAME=:virtual_currency_name,");
				sql.append("EXCHANGE_RATE_RMB=:exchange_rate_rmb");
				sql.append(" where APP_ID=:app_id");
			} else {
				sql.append(" insert into T_DEV_CURRENCY(");
				sql.append("APP_ID,");
				sql.append("VIRTUAL_CURRENCY_NAME,");
				sql.append("EXCHANGE_RATE_RMB,");
				sql.append("STATUS)");
				sql.append(" values (");
				sql.append(":app_id,");
				sql.append(":virtual_currency_name,");
				sql.append(":exchange_rate_rmb,");
				sql.append(":status)");
			}
			namedParameterJdbcTemplate.update(sql.toString(), new BeanPropertySqlParameterSource(currency));
		}
	}

	private AppCurrency getByAppId(Long app_id) throws Exception {
		AppCurrency entity = null;
		if (app_id == null) {
			throw new Exception();
		}
		StringBuilder sb = new StringBuilder();
		sb.append("select * from T_DEV_CURRENCY t where t.app_id = ?");
		List<AppCurrency> objects = namedParameterJdbcTemplate.getJdbcOperations().query(sb.toString(), new BeanPropertyRowMapper<AppCurrency>(AppCurrency.class), app_id);
		if (objects != null && objects.size() > 0) {
			entity = objects.get(0);
		}
		return entity;
	}

	public AppCurrency findCurrencyByAppId(Long app_id) throws Exception {
		return getByAppId(app_id);
	}

	public AppCurrency findCurrencyById(String id) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from T_DEV_CURRENCY t where t.app_id = ?");
		List<AppCurrency> objects = namedParameterJdbcTemplate.getJdbcOperations().query(sql.toString(), new BeanPropertyRowMapper<AppCurrency>(AppCurrency.class), Integer.valueOf(id));
		AppCurrency currency = null;
		if (objects != null && objects.size() > 0) {
			currency = (AppCurrency) objects.get(0);
			return currency;
		}
		return null;
	}

}
