package cn.adwalker.ad.control.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import cn.adwalker.ad.control.exception.DatabaseException;
import cn.adwalker.ad.control.exception.ObjectNotFoundException;

public abstract class BaseDao<T> {

	private Log log = LogFactory.getLog(getClass());
	
	@Resource(name="namedParameterJdbcTemplate")
	protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	protected Class<T> clazz;
	
	protected void setClazz(Class<T> clazz) {
		this.clazz = clazz;
	}
	
	/**
	 * 根据ID查询对象
	 * @param sql sql
	 * @param id 对象ID
	 * @return
	 * @throws DatabaseException
	 * @throws ObjectNotFoundException 
	 */
	protected T getById(String sql, Object id) throws DatabaseException {
		try {
			return namedParameterJdbcTemplate.getJdbcOperations().queryForObject(sql, new BeanPropertyRowMapper<T>(clazz), id);
		} catch (EmptyResultDataAccessException e) {
			log.info("没有数据");
			return null;
		} catch (Exception e) {
			log.error("exception :", e);
			throw new DatabaseException(e.getMessage());
		}
	}

	/**
	 * 获取对象
	 * @param sql sql
	 * @param params 参数
	 * @return
	 * @throws DatabaseException
	 */
	protected T getObjByParams(String sql, Object... params) throws DatabaseException {
		try {
			return namedParameterJdbcTemplate.getJdbcOperations().queryForObject(sql, new BeanPropertyRowMapper<T>(clazz), params);
		} catch (EmptyResultDataAccessException e) {
			log.info("没有数据");
			return null;
		} catch (Exception e) {
			log.error("exception :", e);
			throw new DatabaseException(e.getMessage());
		}
	}
	
	/**
	 * 查询总数,分页
	 * @param sql sql
	 * @param params 参数
	 * @return
	 * @throws DatabaseException
	 */
	@SuppressWarnings("deprecation")
	protected Integer getCount(String sql, Object ... params) throws DatabaseException {
		try {
			return namedParameterJdbcTemplate.getJdbcOperations().queryForInt(sql, params);
		} catch (EmptyResultDataAccessException e) {
			log.error("exception :", e);
			throw new DatabaseException(e.getMessage());
		}
	}

	/**
	 * 查询总数,分页
	 * @param sql sql
	 * @return
	 * @throws DatabaseException
	 */
	@SuppressWarnings("deprecation")
	protected Integer getCount(String sql) throws DatabaseException {
		try {
			return namedParameterJdbcTemplate.getJdbcOperations().queryForInt(sql);
		} catch (EmptyResultDataAccessException e) {
			log.error("exception :", e);
			throw new DatabaseException(e.getMessage());
		}
	}
	
	/**
	 * 分页查询
	 * @param sql sql
	 * @param offset 开始记录数
	 * @param pageSize 每页记录数
	 * @param orderBy 排序
	 * @param params 参数
	 * @return
	 * @throws DatabaseException
	 */
	protected List<T> getList(String sql, int offset, int pageSize, String orderBy, Object ... params) throws DatabaseException {
		if(orderBy != null && !"".equals(orderBy)) {
			sql += " ORDER BY " + orderBy;
		}
		if(offset != 0 || pageSize != 0) {
			sql +=" limit " + offset + "," + pageSize;
		}
		try {
			return namedParameterJdbcTemplate.getJdbcOperations().query(sql.toString(),  new BeanPropertyRowMapper<T>(clazz), params);
		} catch (Exception e) {
			log.error("exception :", e);
			throw new DatabaseException(e.getMessage());
		}
	}

	/**
	 * 分页查询
	 * @param sql sql
	 * @param offset 开始记录数
	 * @param pageSize 每页记录数
	 * @param orderBy 排序
	 * @return
	 * @throws DatabaseException
	 */
	protected List<T> getList(String sql, int offset, int pageSize, String orderBy) throws DatabaseException {
		if(orderBy != null && !"".equals(orderBy)) {
			sql += " ORDER BY " + orderBy;
		}
		if(offset != 0 || pageSize != 0) {
			sql +=" limit " + offset + "," + pageSize;
		}
		try {
			return namedParameterJdbcTemplate.getJdbcOperations().query(sql.toString(),  new BeanPropertyRowMapper<T>(clazz));
		} catch (Exception e) {
			log.error("exception :", e);
			throw new DatabaseException(e.getMessage());
		}
	}
	
	/**
	 * 保存
	 * @param sql sql
	 * @param obj 对象
	 * @return
	 * @throws DatabaseException
	 */
	protected Long save(String sql, T obj) throws DatabaseException {
		try {
			KeyHolder keyHolder = new GeneratedKeyHolder();
			namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(obj), keyHolder);
			return keyHolder.getKey().longValue();
		} catch (Exception e) {
			log.error("exception :", e);
			throw new DatabaseException(e.getMessage());
		}
	}
	
	/**
	 * 修改
	 * @param sql sql
	 * @param obj 对象
	 * @return
	 * @throws DatabaseException
	 */
	protected Integer update(String sql, T obj) throws DatabaseException {
		try {
			return namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(obj));
		} catch (Exception e) {
			log.error("exception :", e);
			throw new DatabaseException(e.getMessage());
		}
	}

	/**
	 * 修改
	 * @param sql sql
	 * @param params 参数
	 * @return
	 * @throws DatabaseException
	 */
	protected Integer update(String sql, Object... params) throws DatabaseException {
		try {
			return namedParameterJdbcTemplate.getJdbcOperations().update(sql, params);
		} catch (Exception e) {
			log.error("exception :", e);
			throw new DatabaseException(e.getMessage());
		}
	}
	
	/**
	 * 删除
	 * @param sql sql
	 * @param id 对象ID
	 * @return
	 * @throws DatabaseException
	 */
	protected Integer delete(String sql, Long id) throws DatabaseException {
		try {
			return namedParameterJdbcTemplate.getJdbcOperations().update(sql, id);
		} catch (Exception e) {
			log.error("exception :", e);
			throw new DatabaseException(e.getMessage());
		}
	}
	
	/**
	 * 获取所有对象
	 * @param sql
	 * @return
	 * @throws DatabaseException
	 */
	protected List<T> getAll(String sql) throws DatabaseException {
		try {
			return namedParameterJdbcTemplate.getJdbcOperations().query(sql, new BeanPropertyRowMapper<T>(clazz));
		} catch (Exception e) {
			log.error("exception :", e);
			throw new DatabaseException(e.getMessage());
		}
	}
	
	/**
	 * 批量更新,非包装对象
	 * @param sq sql
	 * @param objList 参数列表
	 * @return
	 * @throws DatabaseException
	 */
	protected int[] batchUpdate(String sql, List<Object[]> paramsList) throws DatabaseException {
		try {
			return namedParameterJdbcTemplate.getJdbcOperations().batchUpdate(sql, paramsList);
		} catch (Exception e) {
			log.error("exception :", e);
			throw new DatabaseException(e.getMessage());
		}
	}
	
	/**
	 * 批量更新对象
	 * @param sq sql
	 * @param objList 对象列表
	 * @param batchPreparedStatementSetter 封装的setter
	 * @return
	 * @throws DatabaseException
	 */
	protected int[] batchUpdateObj(String sql, List<T> objList, BatchPreparedStatementSetter batchPreparedStatementSetter) throws DatabaseException {
		try {
			return namedParameterJdbcTemplate.getJdbcOperations().batchUpdate(sql, batchPreparedStatementSetter);
		} catch (Exception e) {
			log.error("exception :", e);
			throw new DatabaseException(e.getMessage());
		}
	}
	
}
