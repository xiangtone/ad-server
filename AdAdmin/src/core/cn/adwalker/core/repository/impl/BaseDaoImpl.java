/**
 * 
 */
package cn.adwalker.core.repository.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.repository.Entity;
import cn.adwalker.core.repository.IBaseDao;
import cn.adwalker.core.util.BeanUtils;
import cn.adwalker.core.util.EQueryFunction;
import cn.adwalker.core.util.lang.ObjectUtils;

/**
 * 功能概述：<br>
 * 
 * @author zhaozengbin,wjp
 */
public class BaseDaoImpl<E extends Entity> implements IBaseDao<E> {

	private static Logger logger = Logger.getLogger(BaseDaoImpl.class);
	protected final  String ID = "id";
	// 表名
	protected String tableName;

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	@Resource
	protected JdbcTemplate jdbcTemplate;

	@Resource
	protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	/**
	 * 
	 * @see cn.adwalker.core.repository.IBaseDao#findReportData(java.util.Map,
	 *      java.lang.String, java.lang.String, java.lang.String[])
	 */
	@Override
	public String findReportData(Map<String, String[]> functions,
			String fromCondition, List<String> whereCondition,
			List<String> groupCondition, List<String> orderCondition)
			throws Exception {
		List<String> orderConditionTrue = new ArrayList<String>();
		StringBuffer sql = new StringBuffer();
		sql.append("select ");
		for (EQueryFunction eqf : EQueryFunction.values()) {
			if (ObjectUtils.isNotEmpty(functions.get(eqf.getFunction()))) {
				for (String condition : functions.get(eqf.getFunction())) {
					if (!condition.contains("|")) {
						sql.append(eqf.getFunction() + "(" + condition + ") "
								+ condition + ",");
					} else {
						String conditionSplit[] = condition.split("\\|");
						sql.append(eqf.getFunction() + "(" + conditionSplit[0]
								+ ") " + conditionSplit[1] + ",");
					}
					for (String order : orderCondition) {
						if ((!condition.contains("|") && condition
								.equals((order.split(" ")[0])))
								|| (condition.contains("|") && condition
										.split("\\|")[1].equals(order
										.split(" ")[0]))) {
							orderConditionTrue.add(order.split(" ")[0]);
						}
					}
				}
			}
		}

		if (ObjectUtils.isNotEmpty(groupCondition)) {
			for (String condition : groupCondition) {
				sql.append(condition + ",");
			}
		}
		sql.append("max(ID) ID");
		sql.append(" from ");
		sql.append(fromCondition);
		sql.append(" where ");
		sql.append("1 = 1");
		if (ObjectUtils.isNotEmpty(whereCondition)) {
			for (String condition : whereCondition) {
				sql.append(" and " + condition);
			}
		}
		if (ObjectUtils.isNotEmpty(groupCondition)) {
			sql.append(" group by ");
			for (String condition : groupCondition) {
				sql.append(condition + ",");
			}
		} else {
			sql.append(",");
		}
		String sqlStr = sql.substring(0, sql.lastIndexOf(","));
		sql = new StringBuffer();
		sql.append(sqlStr);

		if (ObjectUtils.isNotEmpty(orderCondition)) {
			for (String condition : orderCondition) {
				String conditions[] = condition.split(" ");
				if (groupCondition.contains(conditions[0])
						|| orderConditionTrue.contains(conditions[0])) {
					if (sql.indexOf(" order by ") != 0) {
						sql.append(" order by ");
					}
					if ("desc".equals(conditions[1])
							|| "DESC".equals(conditions[1])) {
						sql.append(condition + " nulls last,");
					} else {
						sql.append(condition + " nulls first,");
					}

				} else {
					sql.append(",");
				}
			}
		} else {
			sql.append(",");
		}
		sqlStr = sql.substring(0, sql.lastIndexOf(","));
		return sqlStr;
	}

	/** 根据sql获取该sql查询到的记录数 */
	@SuppressWarnings("deprecation")
	@Override
	public int getCount(String sql) {
		sql = "select count(*) from (" + sql + ")";
		return this.jdbcTemplate.queryForInt(sql);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<?> findAlll(int pageIndex, int pageRecord, String sql,
			Object object) throws Exception {
		StringBuffer sql_page = new StringBuffer();
		sql_page.append(sql);
		return this.jdbcTemplate.query(sql_page.toString(),
				new BeanPropertyRowMapper(object.getClass()));
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findByPage
	 * </p>
	 * 
	 * @param columns
	 * @param table
	 * @param condition
	 * @param object
	 * @param pageInfor
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.core.repository.IBaseDao#findByPage(java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.Object[],
	 *      cn.adwalker.core.page.IPageInfo)
	 */
	@SuppressWarnings({ "unchecked", "rawtypes", "deprecation" })
	@Override
	public List<?> findByPage(String columns, String tableAndWhere,
			Object[] object, String orderBy, Class<?> clazz, IPageInfo pageInfo) {
		String sql = " select count(1) from " + tableAndWhere;// 防止哪个孩子直接丢gourp
																// by进来
		logger.debug(sql);
		Integer totalRow = jdbcTemplate.queryForInt(sql, object);
		pageInfo.setTotalRow(totalRow);
		// 限定要显示的页数大于等于1 小于等于总页数
		int page = pageInfo.getCurrentPage();
		if (page > pageInfo.getTotalPage()) {
			page = pageInfo.getTotalPage();
		}
		if (page < 1) {
			page = 1;
		}
		pageInfo.setCurrentPage(page);// 更新计算后的当前页数
		if (totalRow > 0) {
			int pageSize = pageInfo.getPageSize();
			StringBuilder sqlString = new StringBuilder("select ");
			sqlString.append(columns).append(" from ").append(tableAndWhere);
			if (!StringUtils.isEmpty(pageInfo.getOrder())) {
				sqlString.append(" ").append(pageInfo.getOrder());
			} else if (!StringUtils.isEmpty(orderBy)) {
				sqlString.append(" ").append(orderBy);
			}
			sqlString.append(" limit ").append((page - 1) * pageSize)
					.append(",").append(pageSize);
			logger.debug(sqlString);
			return jdbcTemplate.query(sqlString.toString(),
					new BeanPropertyRowMapper(clazz), object);

		}
		return null;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findByPage
	 * </p>
	 * 
	 * @param columns
	 * @param table
	 * @param orderBy
	 * @param clazz
	 * @param pageInfor
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.core.repository.IBaseDao#findByPage(java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.Class,
	 *      cn.adwalker.core.page.IPageInfo)
	 */
	@Override
	public List<?> findByPage(String columns, String table, String orderBy,
			Class<?> clazz, IPageInfo pageInfo) {
		return findByPage(columns, table, null, orderBy, clazz, pageInfo);
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findByPage
	 * </p>
	 * 
	 * @param columns
	 * @param tableAndWhere
	 * @param object
	 * @param clazz
	 * @param pageInfor
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.core.repository.IBaseDao#findByPage(java.lang.String,
	 *      java.lang.String, java.lang.Object[], java.lang.Class,
	 *      cn.adwalker.core.page.IPageInfo)
	 */
	@Override
	public List<?> findByPage(String columns, String tableAndWhere,
			Object[] object, Class<?> clazz, IPageInfo pageInfo)
			throws Exception {
		return findByPage(columns, tableAndWhere, object, null, clazz, pageInfo);
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findByPage
	 * </p>
	 * 
	 * @param columns
	 * @param tableAndWhere
	 * @param clazz
	 * @param pageInfor
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.core.repository.IBaseDao#findByPage(java.lang.String,
	 *      java.lang.String, java.lang.Class,
	 *      cn.adwalker.core.page.IPageInfo)
	 */
	@Override
	public List<?> findByPage(String columns, String tableAndWhere,
			Class<?> clazz, IPageInfo pageInfo) throws Exception {
		return findByPage(columns, tableAndWhere, null, null, clazz, pageInfo);
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findAll
	 * </p>
	 * 
	 * @param columns
	 * @param tableAndWhere
	 * @param object
	 * @param orderBy
	 * @param clazz
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.core.repository.IBaseDao#findAll(java.lang.String,
	 *      java.lang.String, java.lang.Object[], java.lang.String,
	 *      java.lang.Class)
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<?> findAll(String columns, String tableAndWhere,
			Object[] object, String orderBy, Class<?> clazz) throws Exception {
		String sqlString = "select " + columns + " from " + tableAndWhere;
		if (!StringUtils.isEmpty(orderBy)) {
			sqlString = sqlString + orderBy;
		}
		return jdbcTemplate.query(sqlString, new BeanPropertyRowMapper(clazz),
				object);
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: insert
	 * </p>
	 * 
	 * @param obj
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.core.repository.IBaseDao#insert(java.lang.Object)
	 */
	@Override
	public Long insert(Object obj) throws Exception {
		Field[] fields = obj.getClass().getDeclaredFields();
		List<String> arr = new ArrayList<String>();
		for (int i = 0, len = fields.length; i < len; i++) {
			if (!fields[i].getName().toUpperCase().equals("SERIALVERSIONUID")) {
				if (!fields[i].getName().equals(this.ID)) {
					arr.add(fields[i].getName());
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		sb.append(" insert into " + tableName + "(");
		for (String s : arr) {
			sb.append(s + ",");
		}
		KeyHolder keyHolder = new GeneratedKeyHolder();
		sb = sb.deleteCharAt(sb.length() - 1);
		sb.append(")");
		sb.append(" values (");
		for (String s : arr) {
			if (!s.equals(this.ID)) {
				sb.append(":" + s + ",");
			}
		}
		sb = sb.deleteCharAt(sb.length() - 1);
		sb.append(")");
		namedParameterJdbcTemplate.update(sb.toString(),
				new BeanPropertySqlParameterSource(obj), keyHolder);
		return keyHolder.getKey().longValue();
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: update
	 * </p>
	 * 
	 * @param obj
	 * @throws Exception
	 * @see cn.adwalker.core.repository.IBaseDao#update(java.lang.Object)
	 */
	@Override
	public void update(Object obj) throws Exception {
		Field[] fields = obj.getClass().getDeclaredFields();
		List<String> arr = new ArrayList<String>();
		for (int i = 0, len = fields.length; i < len; i++) {
			if (!fields[i].getName().toUpperCase().equals("SERIALVERSIONUID")
					&& (!fields[i].getName().equals(this.ID))) {
				arr.add(fields[i].getName());
			}
		}
		StringBuilder sb = new StringBuilder();
		sb.append(" UPDATE " + tableName + " SET ");
		for (String s : arr) {
			sb.append(s.toUpperCase() + "= :" + s + ",");
		}
		sb = sb.deleteCharAt(sb.length() - 1);
		sb.append(" WHERE ID=:id");
		namedParameterJdbcTemplate.update(sb.toString(),
				new BeanPropertySqlParameterSource(obj));

	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: updateBatch
	 * </p>
	 * 
	 * @param obj
	 * @throws Exception
	 * @see cn.adwalker.core.repository.IBaseDao#updateBatch(java.lang.Object)
	 */
	@Override
	public void updateBatch(List<?> obj, Class<?> clazz) throws Exception {
		Field[] fields = clazz.getDeclaredFields();
		List<String> arr = new ArrayList<String>();
		for (int i = 0, len = fields.length; i < len; i++) {
			if (!fields[i].getName().toUpperCase().equals("SERIALVERSIONUID")
					&& !fields[i].getName().toUpperCase().equals("ID")) {
				arr.add(fields[i].getName());
			}
		}
		StringBuilder sb = new StringBuilder();
		sb.append(" UPDATE " + tableName + " SET ");
		for (String fild : arr) {
			sb.append(fild.toUpperCase() + "= :" + fild + ",");
		}
		sb = sb.deleteCharAt(sb.length() - 1);
		sb.append(" WHERE ID=:id");
		List<Object[]> parameters = new ArrayList<Object[]>();
		Object param[] = null;
		for (Object o : obj) {
			param = new Object[arr.size() + 1];
			int i = 0;
			for (String fild : arr) {
				param[i] = BeanUtils.getProperty(o, fild.toLowerCase());
				i++;
			}
			param[arr.size()] = BeanUtils.getProperty(o, "id".toLowerCase());
			parameters.add(param);
		}
		jdbcTemplate.batchUpdate(sb.toString(), parameters);

	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findAll
	 * </p>
	 * 
	 * @param sql
	 * @param object
	 * @param clazz
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.core.repository.IBaseDao#findAll(java.lang.String,
	 *      java.lang.Object[], java.lang.Class)
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<?> findAll(String sql, Object[] object, Class<?> clazz)
			throws Exception {
		if (StringUtils.isEmpty(sql)) {
			return null;
		}
		return jdbcTemplate
				.query(sql, new BeanPropertyRowMapper(clazz), object);
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findAll
	 * </p>
	 * 
	 * @param sql
	 * @param clazz
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.core.repository.IBaseDao#findAll(java.lang.String,
	 *      java.lang.Class)
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<?> findAll(String sql, Class<?> clazz) throws Exception {
		if (StringUtils.isEmpty(sql)) {
			return null;
		}
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper(clazz));
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: get
	 * </p>
	 * 
	 * @param id
	 * @param clazz
	 * @throws Exception
	 * @see cn.adwalker.core.repository.IBaseDao#get(java.lang.Long,
	 *      java.lang.Class)
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public <T extends Entity> T get(Long id, Class<?> clazz) throws Exception {
		Object obj = null;
		if (id != null) {
			StringBuilder sb = new StringBuilder("select ");
			String arr[] = BeanUtils.getFileds(clazz);
			if (arr != null && arr.length > 0) {
				for (int i = 0; i < arr.length; i++) {
					if (!arr[i].toUpperCase().equals("SERIALVERSIONUID")) {
						sb.append(arr[i] + ",");
					}
				}
				sb.deleteCharAt(sb.length() - 1);

			}
			sb.append("  from " + tableName + " where id= ?");
			List<?> list = (List<?>) jdbcTemplate.query(sb.toString(),
					new BeanPropertyRowMapper(clazz), id);
			if (list != null && list.size() > 0) {
				obj = list.get(0);
			}
		}
		return (T) obj;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findAll
	 * </p>
	 * 
	 * @param columns
	 * @param tableAndWhere
	 * @param object
	 * @param clazz
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.core.repository.IBaseDao#findAll(java.lang.String,
	 *      java.lang.String, java.lang.Object[], java.lang.Class)
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<?> findAll(String columns, String tableAndWhere,
			Object[] object, Class<?> clazz) throws Exception {
		String sqlString = "select " + columns + " from " + tableAndWhere;
		return jdbcTemplate.query(sqlString, new BeanPropertyRowMapper(clazz),
				object);
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: delete
	 * </p>
	 * 
	 * @param id
	 * @throws Exception
	 * @see cn.adwalker.core.repository.IBaseDao#delete(java.lang.Long)
	 */
	@Override
	public void delete(Long id) throws Exception {
		String str = "delete from " + tableName + " where id=?";
		jdbcTemplate.update(str, new Object[] { id });

	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: update
	 * </p>
	 * 
	 * @param sql
	 * @param objects
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.core.repository.IBaseDao#update(java.lang.String,
	 *      java.lang.Object[])
	 */
	@Override
	public void update(String sql, Object[] objects) throws Exception {
		jdbcTemplate.update(sql, objects);
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findTop
	 * </p>
	 * 
	 * @param columns
	 * @param tableAndWhere
	 * @param object
	 * @param orderBy
	 * @param clazz
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.core.repository.IBaseDao#findTop(java.lang.String,
	 *      java.lang.String, java.lang.Object[], java.lang.String,
	 *      java.lang.Class)
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<?> findTop(String columns, String tableAndWhere,
			Object[] object, String orderBy, Class<?> clazz, int top)
			throws Exception {
		StringBuffer sqlString = new StringBuffer("select ");
		sqlString.append(columns).append(" from ").append(tableAndWhere);
		if (!StringUtils.isEmpty(orderBy)) {
			sqlString.append(sqlString).append(" ").append(orderBy);
		}
		sqlString.append(" limit 0,").append(top);
		return jdbcTemplate.query(sqlString.toString(),
				new BeanPropertyRowMapper(clazz), object);
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: batchUpdate
	 * </p>
	 * 
	 * @param string
	 * @param ads
	 * @see cn.adwalker.model.ad.dao.IAdDao#batchUpdate(java.lang.String,
	 *      java.util.List)
	 */
	@Override
	public void batchUpdate(String string, List<Object[]> ads) {
		jdbcTemplate.batchUpdate(string, ads);
	}

	/**
	 * <p>
	 * Title: update
	 * </p>
	 * 
	 * @param string
	 * @param beanPropertySqlParameterSource
	 * @author cuidd
	 * @date 2013-10-25
	 * @return void
	 * @version 1.0
	 */
	@Override
	public int update(String sql, Object object) {
		return namedParameterJdbcTemplate.update(sql,
				new BeanPropertySqlParameterSource(object));
	}

	/**
	 * <p>
	 * Title: update
	 * </p>
	 * 
	 * @param string
	 * @param beanPropertySqlParameterSource
	 * @author cuidd
	 * @date 2013-10-25
	 * @return void
	 * @version 1.0
	 */
	@Override
	public long insert(String sql, Object object) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		namedParameterJdbcTemplate.update(sql,
				new BeanPropertySqlParameterSource(object), keyHolder);
		return keyHolder.getKey().longValue();
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: countQuery
	 * </p>
	 * 
	 * @param sql
	 * @param objects
	 * @return
	 * @see cn.adwalker.core.repository.IBaseDao#countQuery(java.lang.String,
	 *      java.lang.Object[])
	 */
	@SuppressWarnings("deprecation")
	@Override
	public int countQuery(String sql, Object[] objects) {
		Integer totalRow = jdbcTemplate.queryForInt(sql, objects);
		return totalRow;
	}


	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: batchInsert
	 * </p>
	 * <p>
	 * Description:批量插入
	 * </p>
	 * 
	 * @param str
	 * @param objects
	 * @see cn.adwalker.core.repository.IBaseDao#batchInsert(java.lang.String,
	 *      java.util.List)
	 */
	@Override
	public void batchInsert(String str, List<Object[]> objects) {
		jdbcTemplate.batchUpdate(str, objects);

	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findTop
	 * </p>
	 * <p>
	 * Description:查询前xx条
	 * </p>
	 * 
	 * @param sql
	 * @param object
	 * @param orderBy
	 * @param clazz
	 * @param top
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.core.repository.IBaseDao#findTop(java.lang.String,
	 *      java.lang.Object[], java.lang.String, java.lang.Class, int)
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<?> findTop(String sql, Object[] object, Class<?> clazz, int top)
			throws Exception {
		StringBuffer sqlString = new StringBuffer();
		sqlString.append(sql);
		sqlString.append(" limit 0,").append(top);
		return jdbcTemplate.query(sqlString.toString(),
				new BeanPropertyRowMapper(clazz), object);
	}
}
