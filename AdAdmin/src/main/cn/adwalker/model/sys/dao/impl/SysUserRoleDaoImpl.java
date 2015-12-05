/**
 * 
 */
package cn.adwalker.model.sys.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import cn.adwalker.core.repository.impl.BaseDaoImpl;
import cn.adwalker.model.sys.dao.ISysUserRoleDao;
import cn.adwalker.model.sys.domain.SysUserRole;

/**
 * @author wjp 管理员 、 角色映射
 */
@Repository("manageUserRoleDao")
public class SysUserRoleDaoImpl extends BaseDaoImpl<SysUserRole> implements ISysUserRoleDao {

	@Override
	public int deleteById(Long id) throws Exception {
		String sql = "delete from T_SYS_USER_ROLE where id=?";
		return this.jdbcTemplate.update(sql, id);
	}

	@Override
	public List<SysUserRole> findAll() throws Exception {
		String sql = "select * from T_SYS_USER";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<SysUserRole>(SysUserRole.class));
	}

	@Override
	public int insert(SysUserRole sysUserRole) throws Exception {
		String sql = "insert into T_SYS_USER_ROLE (user_id,role_id,create_time) values (:userId,:roleId,:createTime)";
		return super.update(sql, sysUserRole);
	}

	@SuppressWarnings("deprecation")
	@Override
	public int getUserCountByRoleId(Long roleId) throws Exception {
		// del = 0 :代表未删除
		String sql = " select count(*) from T_SYS_USER a where a.del = 0 and a.id in (select t.user_Id from T_SYS_USER_ROLE t where t.role_id = ?) ";
		return this.jdbcTemplate.queryForInt(sql, roleId);
	}

	@Override
	public int deleteByUserId(Long userId) throws Exception {
		String sql = " delete from T_SYS_USER_ROLE where user_id = ? ";
		return jdbcTemplate.update(sql, userId);
	}

	@Override
	public int updateRoleByUserId(Long userId, String[] checkbox) throws Exception {
		SysUserRole sysUserRole = new SysUserRole();
		for (int i = 0; i < checkbox.length; i++) {
			StringBuffer sql = new StringBuffer();
			sysUserRole.setCreateTime(new Date());
			sysUserRole.setRoleId(Long.valueOf(checkbox[i]));
			sysUserRole.setUserId(userId);
			sql.append(" insert into T_SYS_USER_ROLE (user_id,role_id,create_time) ");
			sql.append(" values (:userId,:roleId,:createTime) ");
			super.update(sql.toString(), sysUserRole);
		}
		return 0;
	}

	@Override
	public int updataById(SysUserRole sysUserRole) throws Exception {
		return 0;
	}

	@Override
	public int deleteByRoleId(Long userId) throws Exception {
		String sql = "delete from T_SYS_USER_ROLE where USER_ID=?";
		return jdbcTemplate.update(sql, userId);
	}

}
