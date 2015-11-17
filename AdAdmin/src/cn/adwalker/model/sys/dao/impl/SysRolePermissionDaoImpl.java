/**
 * 
 */
package cn.adwalker.model.sys.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import cn.adwalker.core.repository.impl.BaseDaoImpl;
import cn.adwalker.model.sys.dao.ISysRolePermissionDao;
import cn.adwalker.model.sys.domain.SysRolePermissionRel;
import cn.adwalker.model.sys.domain.SysRolePurview;

/**
 * @author wjp
 * 
 */
@Repository
public class SysRolePermissionDaoImpl extends BaseDaoImpl<SysRolePermissionRel> implements ISysRolePermissionDao {

	public SysRolePermissionDaoImpl() {
		setTableName("T_SYS_ROLE_PERMISSION");
	}

	@Override
	public List<SysRolePurview> findAll() {
		String sql = " select * from T_SYS_ROLE_PURVIEW ";
		return this.jdbcTemplate.query(sql, new BeanPropertyRowMapper<SysRolePurview>(SysRolePurview.class));
	}

	@Override
	public int updateById(SysRolePurview sysRolePurview) throws Exception {
		String sql = "update T_SYS_ROLE_PURVIEW set role_id = :roleId , res_id = :resId where id = :id ";
		return super.update(sql, sysRolePurview);
	}

	@Override
	public List<SysRolePurview> findPurviewByRoleId(Long roleId) throws Exception {
		String sql = "select * from T_SYS_ROLE_PERMISSION where role_id = ? ";
		return this.jdbcTemplate.query(sql, new BeanPropertyRowMapper<SysRolePurview>(SysRolePurview.class), roleId);
	}

	@Override
	public int deleteByRoleId(Long roleId) throws Exception {
		String sql = " delete from T_SYS_ROLE_PERMISSION where role_id = ? ";
		return this.jdbcTemplate.update(sql, roleId);
	}

	private List<Long> getResourceParent(Long roleId) {
		List<Long> result = null;
		StringBuffer sql = new StringBuffer();
		sql.append("select p.parent_id value from T_SYS_ROLE_PURVIEW c left join T_SYS_RESOURCE p on c.res_id=p.id  where 1=1 and  p.parent_id is not null and p.parent_id!=0  and ROLE_ID= ? group by p.parent_id");
		List<Map<String, Object>> list = this.jdbcTemplate.queryForList(sql.toString(), new Object[] { roleId });
		if (list != null && list.size() > 0) {
			result = new ArrayList<Long>();
			for (Map<String, Object> map : list) {
				result.add(((BigDecimal) map.get("value")).longValue());
			}
		}
		return result;
	}

	@Override
	public void insertPurview(Long roleId) throws Exception {
		List<Long> list = getResourceParent(roleId);
		if (list != null && list.size() > 0) {
			String sb = "insert into T_SYS_ROLE_PURVIEW (role_id,res_id,create_time) values(?,?,NOW())";
			List<Object[]> parameters = new ArrayList<Object[]>();
			for (Long s : list) {
				Object[] objects = { roleId, Long.valueOf(s) };
				parameters.add(objects);
			}
			jdbcTemplate.batchUpdate(sb, parameters);
		}
	}

}
