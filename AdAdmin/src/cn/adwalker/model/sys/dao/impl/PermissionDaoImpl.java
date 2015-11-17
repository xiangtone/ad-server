package cn.adwalker.model.sys.dao.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import cn.adwalker.core.repository.impl.BaseDaoImpl;
import cn.adwalker.model.report.domain.TStatReportTable;
import cn.adwalker.model.sys.dao.IPermissionDao;
import cn.adwalker.model.sys.domain.Permission;
import cn.adwalker.model.sys.domain.SysPurview;

/**
 * 
 * <p>
 * Title: ResourceDaoImpl
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-3-15
 */
@Repository("permissionDao")
public class PermissionDaoImpl extends BaseDaoImpl<Permission> implements IPermissionDao {

	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger(PermissionDaoImpl.class);

	public PermissionDaoImpl() {
		setTableName("T_SYS_PERMISSION");

	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: updateStatus
	 * </p>
	 * 
	 * @param dev_id
	 * @param status
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.model.sys.dao.ISysResourceDao#updateStatus(java.lang.Long, java.lang.Integer)
	 */
	@Override
	public Integer updateStatus(Long id, Integer status) throws Exception {
		String sql = "update T_SYS_PERMISSION set STATUS=?,CREATE_TIME= ? where ID=?";
		return jdbcTemplate.update(sql, new Object[] { status, new Date(), id });

	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findAll
	 * </p>
	 * 
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.model.sys.dao.ISysResourceDao#findAll()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Permission> findAll() throws Exception {
		return (List<Permission>) findAll("select id,name from T_SYS_PERMISSION", Permission.class);
	}

	@Override
	public void addSysPurview(TStatReportTable table) {
		String sql = "insert into T_SYS_PERMISSION (res_name,res_url,STAT_REPORT_ID,parent_id,create_time) values(:menuName,:menuRequest,:id,3,NOW())";
		super.update(sql, table);

	}

	@Override
	public void updateSysPurview(TStatReportTable table) {
		StringBuffer sql = new StringBuffer();
		sql.append("update T_SYS_PERMISSION set RES_NAME='").append(table.getMenuName());
		sql.append("',RES_URL='").append(table.getMenuRequest());
		sql.append("' where STAT_REPORT_ID =" + table.getId());
		jdbcTemplate.update(sql.toString());
	}

	@SuppressWarnings("deprecation")
	@Override
	public Long findByTableId(Long id) {
		String sql = "select id from T_SYS_PERMISSION where STAT_REPORT_ID=" + id;
		return this.jdbcTemplate.queryForLong(sql);

	}

	@Override
	public int deleteById(Long id) throws Exception {
		String sql = "delete from  T_SYS_PERMISSION where id=?";
		return jdbcTemplate.update(sql, id);
	}

	@Override
	public int updateById(SysPurview sysPurview) throws Exception {
		String sql = "update T_SYS_PERMISSION set res_name=:resName,res_url=:resUrl whereid=:id";
		return super.update(sql, sysPurview);
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findRootPurviewListByUserId
	 * </p>
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.model.sys.dao.IPermissionDao#findRootPurviewListByUserId(java.lang.Long)
	 */
	@Override
	public List<SysPurview> findRootPurviewListByUserId(Long userId) throws Exception {
		String sb = "select p.* from T_SYS_ROLE_PURVIEW role_rel left join T_SYS_PERMISSION p " + "on role_rel.res_id=p.id,T_SYS_USER_ROLE role " + "where p.status>=0 and role_rel.role_id=role.role_id  and role.user_id=? and p.parent_id=0 order by p.sort";
		return this.jdbcTemplate.query(sb, new BeanPropertyRowMapper<SysPurview>(SysPurview.class), userId);
	}

	@Override
	public List<SysPurview> findSubPurviewListByParentId(Long parentId, Long userId) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from (select * from T_SYS_PERMISSION p where p.id in (select rp.res_id from T_SYS_ROLE_PURVIEW rp where rp.role_id=");
		sql.append("(select role_id from T_SYS_USER_ROLE where user_id=").append(userId).append("))) mp");
		sql.append(" where mp.parent_id=").append(parentId).append(" and mp.status>=0 order by mp.sort");
		return jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<SysPurview>(SysPurview.class));
	}

	@Override
	public List<SysPurview> findSub() throws Exception {
		String sql = " select * from T_SYS_PERMISSION where parent_id <> 0 ";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<SysPurview>(SysPurview.class));
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: updateCode
	 * </p>
	 * 
	 * @param id
	 * @param code
	 * @throws Exception
	 * @see cn.adwalker.model.sys.dao.IPermissionDao#updateCode(java.lang.Long, java.lang.String)
	 */
	@Override
	public void updateCode(Long id, String code) throws Exception {
		String sql = "update T_SYS_PERMISSION set code=? where id = ? ";
		jdbcTemplate.update(sql, new Object[] { code, id });
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: getButtonPermission
	 * </p>
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.model.sys.dao.IPermissionDao#getButtonPermission(java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Set<String> getButtonPermission(Long id) throws Exception {
		Set<String> result = null;
		String sql = " SELECT  p.*  FROM  T_SYS_ROLE_PERMISSION rel   LEFT  JOIN  T_SYS_ROLE r  on   rel.role_id=r.id  LEFT  JOIN  T_SYS_PERMISSION p on p.id=rel.PERMISSION_id where r.id in (SELECT  role_id from  T_SYS_USER_ROLE where user_id=?)";
		List<Permission> list = (List<Permission>) this.findAll(sql, new Object[] { id }, Permission.class);
		if (list != null && list.size() > 0) {
			result = new HashSet<String>();
			for (Permission p : list) {
				result.add(p.getCode());
			}
		}
		return result;
	}
}