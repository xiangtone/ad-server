package cn.adwalker.ad.model.page.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import cn.adwalker.ad.core.page.IPageInfo;
import cn.adwalker.ad.web.developer.vo.DevIncome;

public class BaseDao {

	@Resource
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@SuppressWarnings("unchecked")
	public List<?> findAll(int pageIndex, int pageRecord, String sql, Object object, int tag) throws Exception {
		int start = (pageIndex - 1) * pageRecord + 1;
		int end = pageIndex * pageRecord;
		StringBuffer sql_page = new StringBuffer();
		sql_page.append("select * from (select row_.*,rownum rownum_ from ( ");
		sql_page.append(sql);
		if (tag != 2) {
			sql_page.append(" ) row_ where rownum <= ");
			sql_page.append(end);
			sql_page.append(" ) where rownum_ >= ");
			sql_page.append(start);
		} else {
			sql_page.append(") row_ ) ");
		}
		return this.namedParameterJdbcTemplate.query(sql_page.toString(), new BeanPropertyRowMapper(object.getClass()));
	}

	@SuppressWarnings("unchecked")
	public List<?> findAll(int pageIndex, int pageRecord, String sql, Object object) throws Exception {
		int start = (pageIndex - 1) * pageRecord + 1;
		int end = pageIndex * pageRecord;

		StringBuffer sql_page = new StringBuffer();
		sql_page.append("select * from (select row_.*,rownum rownum_ from ( ");
		sql_page.append(sql);
		sql_page.append(" ) row_ where rownum <= ");
		sql_page.append(end);
		sql_page.append(" ) where rownum_ >= ");
		sql_page.append(start);
		return this.namedParameterJdbcTemplate.query(sql_page.toString(), new BeanPropertyRowMapper(object.getClass()));
	}

	@SuppressWarnings("unchecked")
	public List<?> findAllByStartCountAndShowCount(int startCount, int showCount, String sql, Object object) throws Exception {

		StringBuffer sql_page = new StringBuffer();
		sql_page.append("select * from (select row_.*,rownum rownum_ from ( ");
		sql_page.append(sql);
		sql_page.append(" ) row_ where rownum <= ");
		sql_page.append(showCount);
		sql_page.append(" ) where rownum_ >= ");
		sql_page.append(startCount);
		return this.namedParameterJdbcTemplate.query(sql_page.toString(), new BeanPropertyRowMapper(object.getClass()));
	}

	/** 根据sql获取该sql查询到的记录数 */
	public int getCount(String sql) {
		sql = "select count(*) from (" + sql + ") t1";
		return this.namedParameterJdbcTemplate.getJdbcOperations().queryForInt(sql);
	}

	@SuppressWarnings("unchecked")
	public List<?> findAlll(int pageIndex, int pageRecord, String sql, Object object) throws Exception {
		StringBuffer sql_page = new StringBuffer();
		sql_page.append(sql);
		return this.namedParameterJdbcTemplate.query(sql_page.toString(), new BeanPropertyRowMapper(object.getClass()));
	}

	@SuppressWarnings("unchecked")
	public List<?> findByPage(String columns, String tableAndWhere, Object[] object, String orderBy, Class<?> clazz, IPageInfo pageInfo) {
		String sql = " select count(1) from (select 1 from " + tableAndWhere + ") t1";// 防止哪个孩子直接丢gourp by进来
		Integer totalRow = this.namedParameterJdbcTemplate.getJdbcOperations().queryForInt(sql, object);
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
			sqlString.append(" limit ").append((page - 1) * pageSize).append(",").append(pageSize);
			return this.namedParameterJdbcTemplate.getJdbcOperations().query(sqlString.toString(), new BeanPropertyRowMapper(clazz), object);
		}
		return null;
	}

	public List<?> findByPage(String columns, String table, String orderBy, Class<?> clazz, IPageInfo pageInfo) {
		return findByPage(columns, table, null, orderBy, clazz, pageInfo);
	}

	public List<?> findByPage(String columns, String tableAndWhere, Object[] object, Class<?> clazz, IPageInfo pageInfo) throws Exception {
		return findByPage(columns, tableAndWhere, object, null, clazz, pageInfo);
	}

	public List<?> findByPage(String columns, String tableAndWhere, Class<?> clazz, IPageInfo pageInfo) throws Exception {
		return findByPage(columns, tableAndWhere, null, null, clazz, pageInfo);
	}

	public List<?> findBySqlPage(String columns, String tableAndWhere, Class<?> clazz, IPageInfo pageInfor) throws Exception {
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<?> findAll(String columns, String tableAndWhere, Object[] object, String orderBy, Class<?> clazz) throws Exception {
		String sqlString = "select " + columns + " from " + tableAndWhere;
		if (!StringUtils.isEmpty(orderBy)) {
			sqlString = sqlString + orderBy;
		}
		return this.namedParameterJdbcTemplate.getJdbcOperations().query(sqlString, new BeanPropertyRowMapper(clazz), object);
	}

	public List<?> findIncome(String sql) throws Exception {
		return null;
	}

	public List<?> findAll(String columns, String tableAndWhere, Object[] objects, Class<DevIncome> clazz) throws Exception {
		return findAll(columns, tableAndWhere, objects, null, clazz);
	}

	public List<?> findAll(String sql, Class<?> clazz) throws Exception {
		if (StringUtils.isEmpty(sql)) {
			return null;
		}
		return namedParameterJdbcTemplate.query(sql, new BeanPropertyRowMapper(clazz));
	}

	public long insert(String sql, Object object) throws Exception {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(object), keyHolder);
		return keyHolder.getKey().longValue();
	}
}
