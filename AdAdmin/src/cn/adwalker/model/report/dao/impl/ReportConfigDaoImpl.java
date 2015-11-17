package cn.adwalker.model.report.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import cn.adwalker.core.repository.impl.BaseDaoImpl;
import cn.adwalker.model.report.dao.IReportConfigDao;
import cn.adwalker.model.report.domain.TStatReportItem;
import cn.adwalker.model.report.domain.TStatReportTable;

@Repository("reportConfigDao")
public class ReportConfigDaoImpl extends BaseDaoImpl implements IReportConfigDao {
	
	/**
	 * 日志工具
	 */
	private Logger logger = Logger.getLogger(ReportConfigDaoImpl.class);

	@Override
	public void insertTable(TStatReportTable data) {
		StringBuffer sql = new StringBuffer();
		sql.append("insert into t_stat_report_table (JSP_NAME,MENU_NAME,MENU_REQUEST,ORIGIN_SQL,COUNT_SQL,PAGE_SIZE,STATUS,ROLE,NOTE,CREATETIME,C_OPERATOR,UPDATETIME,U_OPERATOR,TOTAL_SQL,DEFAULT_START,DEFAULT_END)");
		sql.append(" values(:jspName,:menuName,:menuRequest,:originSql,:countSql,:pageSize,:status,:role,:note,now(),:COperator,:updatetime,:UOperator,:totalSql,:defaultStart,:defaultEnd)");
		super.update(sql.toString(), data);
	}

	@Override
	public TStatReportTable getReportTableForEdit(Long id) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from t_stat_report_table where id=" + id);
		List<TStatReportTable> list = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<TStatReportTable>(TStatReportTable.class));
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public void updateTable(TStatReportTable table) {
		StringBuffer sql = new StringBuffer();
		sql.append(" update t_stat_report_table set");
		sql.append(" JSP_NAME=?");
		sql.append(",MENU_NAME=?");
		sql.append(",MENU_REQUEST=?");
		sql.append(",ORIGIN_SQL=?");
		sql.append(",COUNT_SQL=?");
		sql.append(",PAGE_SIZE=?");
		sql.append(",STATUS=?");
		sql.append(",ROLE=?");
		sql.append(",NOTE=?");
		// sql.append(" and CREATETIME='"+table.getCreatetime()+"'");
		sql.append(",C_OPERATOR=?");
		sql.append(",UPDATETIME=NOW()");
		sql.append(",U_OPERATOR=?");
		sql.append(",TOTAL_SQL=?");
		sql.append(",DEFAULT_START=?");
		sql.append(",DEFAULT_END=?");
		sql.append(" where id =" + table.getId());
		logger.info("更新===" + sql);
		jdbcTemplate.update(sql.toString(), table.getJspName(), table.getMenuName(), table.getMenuRequest(), table.getOriginSql(), table.getCountSql(), table.getPageSize(), table.getStatus(),
				table.getRole(), table.getNote(), table.getCOperator(), table.getUOperator(), table.getTotalSql(), table.getDefaultStart(), table.getDefaultEnd());
	}

	@Override
	public List<TStatReportItem> getReportItemsForList(Long id) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select id,TABLE_ID as tableId,SQL_NAME as sqlName,JSP_NAME as jspName,FORMAT,STATUS,SEQUENCES as SEQUENCE,ISSEARCH,JSP_TYPE as jspType,SQL_TYPE as sqlType, ");
		sql.append(" ORIGIN_SQL as originSql, ISLINK ,LINKS as link, CREATETIME,C_OPERATOR as COperator ,UPDATETIME , U_OPEARTOR as UOpeartor from t_stat_report_item where table_id=" + id);
		logger.info("字段查询:" + sql);
		List<TStatReportItem> list = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<TStatReportItem>(TStatReportItem.class));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	@Override
	public void insertItem(TStatReportItem table) {
		StringBuffer sql = new StringBuffer();
		sql.append("insert into T_STAT_REPORT_ITEM (TABLE_ID,SQL_NAME,JSP_NAME,FORMAT,STATUS,SEQUENCES,ISSEARCH,JSP_TYPE,SQL_TYPE,ORIGIN_SQL,ISLINK,LINKS,CREATETIME,C_OPERATOR,UPDATETIME,U_OPEARTOR)");
		sql.append(" values(:tableId,:sqlName,:jspName,:format,:status,:sequence,:isSearch,:jspType,:sqlType,:originSql,:isLink,:link,:createtime,:COperator,:updatetime,:UOpeartor)");
		logger.info("字段的sql=" + sql);
		super.update(sql.toString(), table);

	}

	@Override
	public TStatReportItem getReportItemForEdit(Long id) {
		StringBuffer sql = new StringBuffer();
		sql.append("select id,TABLE_ID as tableId,SQL_NAME as sqlName,JSP_NAME as jspName,FORMAT,STATUS,SEQUENCES as SEQUENCE,ISSEARCH,JSP_TYPE as jspType,SQL_TYPE as sqlType, ");
		sql.append(" ORIGIN_SQL as originSql, ISLINK ,LINKS as link, CREATETIME,C_OPERATOR as COperator ,UPDATETIME , U_OPEARTOR as UOpeartor from t_stat_report_item where id=" + id);
		logger.info("单个字段查询:" + sql);
		List<TStatReportItem> list = this.jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper(TStatReportItem.class));
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public void updateItem(TStatReportItem table) {
		StringBuffer sql = new StringBuffer();
		sql.append(" update t_stat_report_item set ");
		sql.append(" SQL_NAME='" + table.getSqlName() + "'");
		sql.append(",JSP_NAME='" + table.getJspName() + "'");
		sql.append(",FORMAT='" + table.getFormat() + "'");
		sql.append(",STATUS='" + table.getStatus() + "'");
		sql.append(",SEQUENCES=" + table.getSequence());
		sql.append(",ISSEARCH='" + table.getIsSearch() + "'");
		sql.append(",JSP_TYPE='" + table.getJspType() + "'");
		sql.append(",SQL_TYPE='" + table.getSqlType() + "'");
		sql.append(",ORIGIN_SQL=q'[" + table.getOriginSql() + "]'");
		sql.append(",ISLINK='" + table.getIsLink() + "'");
		sql.append(",LINKS='" + table.getLink() + "'");
		// sql.append(" and CREATETIME='"+table.getCreatetime()+"'");
		sql.append(",C_OPERATOR='" + table.getCOperator() + "'");
		sql.append(",UPDATETIME=NOW()");
		sql.append(",U_OPEARTOR='" + table.getUOpeartor() + "'");
		sql.append(" where id =" + table.getId());
		logger.info("字段更新===" + sql);
		jdbcTemplate.update(sql.toString());

	}

	@Override
	public void insertRolePurview(Long sysPurvieId) {
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into T_SYS_ROLE_PURVIEW (role_id,res_id,create_time)");
		sql.append(" values(1006,").append(sysPurvieId).append(",NOW())");
		logger.info("权限sql=" + sql);
		jdbcTemplate.update(sql.toString());

	}

	@Override
	public TStatReportTable findTStatReportTable() {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from t_stat_report_table order by id desc");
		List<TStatReportTable> list = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<TStatReportTable>(TStatReportTable.class));
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public TStatReportTable getReportTable() {
		return null;
	}

	@Override
	public TStatReportTable getReportTable(Long id) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from t_stat_report_table where status=1 and id=" + id + " ");
		List<TStatReportTable> list = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<TStatReportTable>(TStatReportTable.class));
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
		// 有可能id已经状态为9
		// String hql = "from t_stat_report_table where status=1 and id=" + id + " ";
		// List list = this.find(hql);
		// TStatReportTable t = new TStatReportTable();
		// if (list != null && list.size() == 1) {
		// t = (TStatReportTable) list.get(0);
		// return t;
		// }
	}

	@Override
	public List executeQuery(String sql) {
		ResultSet rs = null;
		List<Map<String, Object>> list = null;
		try {
			// sn = HibernateSessionFactory.getSession();
			// Connection conn = sn.connection();
			// Connection conn = null;
			// rs = conn.createStatement().executeQuery(sql);
			// list = getList(rs);
			list = jdbcTemplate.queryForList(sql.toString());
			if (list != null && list.size() > 0) {
				return list;
			}
			return null;
		} catch (Exception e) {
			logger.error("executeQuery :" + e.getMessage(), e);
		} finally {
			// HibernateSessionFactory.closeSession();
		}
		return list;

	}

	@Override
	public List executeQueryList(String sql) {
		ResultSet rs = null;
		List<Map<String, Object>> list = null;
		try {
			// sn = HibernateSessionFactory.getSession();
			// Connection conn = sn.connection();
			// Connection conn = null;
			// rs = conn.createStatement().executeQuery(sql);
			// list = getListList(rs);
			list = this.jdbcTemplate.queryForList(sql.toString());
			if (list != null && list.size() > 0) {
				return list;
			}
		} catch (Exception e) {
			logger.error("executeQuery :" + e.getMessage(), e);
		} finally {
			// HibernateSessionFactory.closeSession();
		}
		return list;

	}

	@Override
	public List getReportItems(Long id) {
		// String hql = "from TStatReportItem where status=1 and table_id=" + id
		// + " order by sequence,id desc";
		// List list = this.find(hql);
		// return list;
		StringBuffer sql = new StringBuffer();
		sql.append(" select id,TABLE_ID as tableId,SQL_NAME as sqlName,JSP_NAME as jspName,FORMAT,STATUS,SEQUENCES as SEQUENCE,ISSEARCH,JSP_TYPE as jspType,SQL_TYPE as sqlType, ");
		sql.append(" ORIGIN_SQL as originSql, ISLINK ,LINKS as link, CREATETIME,C_OPERATOR as COperator ,UPDATETIME , U_OPEARTOR as UOpeartor ");
		sql.append(" from T_STAT_REPORT_ITEM  where status=1 and table_id=" + id + " order by sequences,id desc");
		List<TStatReportItem> list = this.jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper(TStatReportItem.class));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	@Override
	public HashMap getPageResult_sql_List(String countSql, String sql, int currentPage, int pageSize) {
		Statement stmt = null;
		ResultSet rs = null;
		Connection conn = null;

		int total = 0; // 总共有多少条记录
		int totalPage = 0; // 共有多少页
		Integer list = null;
		try {
			// stmt = conn.createStatement();
			// rs = stmt.executeQuery(countSql);
			total = this.jdbcTemplate.queryForInt(countSql.toString());
			// if(list!=null&&list.size()>0){
			// total = (Integer) list.get(0);
			// }

			// if (rs.next())
			// total = rs.getInt(1);
		} catch (Exception e) {
			logger.error("执行分页getPageResultSet()在得到总页数时出错:" + countSql, e);
		}
		// 设置当前页数和总页数
		totalPage = (int) Math.ceil((double) total / pageSize); // 共有多少页
		if (currentPage < 1)
			currentPage = 1;
		// 如果当前页大于总页数,则显示最后一页
		if (currentPage > totalPage)
			currentPage = totalPage;
		// 如果当前页小于0,则显示第一页
		if (currentPage < 1)
			currentPage = 1;

		// 根据条件判断，取出所需记录
		sql = sql + " limit " + ((currentPage - 1) * pageSize) + "," + pageSize;
		List list1 = getResultSet_sql_List(sql);
		HashMap hashMap = new HashMap();
		hashMap.put("totalPage", Integer.valueOf(totalPage)); // 共有多少页
		hashMap.put("currentPage", Integer.valueOf(currentPage));
		hashMap.put("list", list1);
		return hashMap;
	}

	private List getResultSet_sql_List(String sql) {
		// Session sn = null;
		// Statement stmt = null;
		// ResultSet rs = null;

		// sn = HibernateSessionFactory.getSession();
		// Connection conn = sn.connection();
		// Connection conn = null;
		List<Map<String, Object>> list = null;
		try {
			// stmt = conn.createStatement();
			// rs = stmt.executeQuery(sql);
			// list = getListList(rs);
			list = this.jdbcTemplate.queryForList(sql);
			if (list != null && list.size() > 0) {
				return list;
			}
		} catch (Exception e) {
			logger.error("执行SQL出错:" + sql, e);
		} finally {
			// HibernateSessionFactory.closeSession();
		}
		return list;
	}
}
