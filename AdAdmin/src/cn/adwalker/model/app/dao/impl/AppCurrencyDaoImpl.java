/**
 * 
 */
package cn.adwalker.model.app.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import cn.adwalker.core.repository.impl.BaseDaoImpl;
import cn.adwalker.model.app.dao.IAppCurrencyDao;
import cn.adwalker.model.app.domain.AppCurrency;

/**
 * 功能概述：<br>
 * 开发者SDK应用实现类
 * 
 * @author guoyongxiang
 */
@Repository
public class AppCurrencyDaoImpl extends BaseDaoImpl<AppCurrency> implements IAppCurrencyDao {

	public AppCurrencyDaoImpl() {
		setTableName("T_DEV_CURRENCY");
	}

	@Override
	public void saveOrUpdate(AppCurrency vo) throws Exception {
		if (vo != null && vo.getApp_id() != null) {
			AppCurrency entity = getByAppId(vo.getApp_id());
			boolean flag = (entity != null) ? true : false;
			StringBuilder sql = new StringBuilder();
			if (!flag) {
				entity = new AppCurrency();
				entity.setStatus(1);
			}
			entity.setExchange_rate_rmb(vo.getExchange_rate_rmb());
			entity.setVirtual_currency_name(vo.getVirtual_currency_name());
			entity.setApp_id(vo.getApp_id());
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
				sql.append(" values (:app_id,:virtual_currency_name,:exchange_rate_rmb,:status)");
			}
			super.update(sql.toString(), entity);
		}
	}

	private AppCurrency getByAppId(Long app_id) throws Exception {
		AppCurrency entity = null;
		if (app_id == null) {
			throw new Exception();
		}
		StringBuilder sb = new StringBuilder();
		sb.append("select * from T_DEV_CURRENCY t where t.app_id = ?");
		List<AppCurrency> objects = jdbcTemplate.query(sb.toString(), new BeanPropertyRowMapper<AppCurrency>(AppCurrency.class), app_id);
		if (objects != null && objects.size() > 0) {
			entity = objects.get(0);
		}
		return entity;
	}

	@Override
	public AppCurrency findCurrencyByAppId(Long app_id) throws Exception {
		return getByAppId(app_id);
	}

	@Override
	public AppCurrency findCurrencyById(String id) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from T_DEV_CURRENCY t where t.app_id = ?");
		List<AppCurrency> objects = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<AppCurrency>(AppCurrency.class), Integer.valueOf(id));
		AppCurrency currency = null;
		if (objects != null && objects.size() > 0) {
			currency = (AppCurrency) objects.get(0);
			return currency;
		}
		return null;
	}

}
