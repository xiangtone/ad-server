/**
 * 
 */
package cn.adwalker.model.sys.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import cn.adwalker.core.repository.impl.BaseDaoImpl;
import cn.adwalker.model.sys.dao.ISysRoleDao;
import cn.adwalker.model.sys.domain.SysRole;

/**
 * <p>
 * Title: SysRoleDaoImpl
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-5-27
 */
@Repository("manageRoleDao")
public class SysRoleDaoImpl extends BaseDaoImpl<SysRole> implements ISysRoleDao {

	public SysRoleDaoImpl() {
		setTableName("T_SYS_ROLE");
	}

	@Override
	public int deleteById(Long id) throws Exception {
		String sql = " delete from T_SYS_ROLE where id = ? ";
		return jdbcTemplate.update(sql, id);
	}

	@Override
	public int updateById(SysRole sysRole) throws Exception {
		String sql = " update T_SYS_ROLE set role_name = :roleName where id = :id ";
		return super.update(sql.toString(), sysRole);
	}

	@Override
	public SysRole getRoleByUserId(Long userId) throws Exception {
		String sql = " select * from T_SYS_ROLE t where id in (select m.role_id from T_SYS_USER_ROLE m where m.user_id = ?) ";
		List<SysRole> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<SysRole>(SysRole.class), userId);
		if (list.size() != 0) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findAll
	 * </p>
	 * 
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.model.sys.dao.ISysRoleDao#findAll()
	 */
	@Override
	public List<SysRole> findAll() throws Exception {
		String sql = "select * from t_sys_role ";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<SysRole>(SysRole.class));
	}

}
