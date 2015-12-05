/**
 * 
 */
package cn.adwalker.model.sys.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import cn.adwalker.model.common.domain.SysAccout;
import cn.adwalker.model.sys.dao.ISysUserDao;
import cn.adwalker.model.sys.dao.ISysUserRoleDao;
import cn.adwalker.model.sys.domain.SysUser;
import cn.adwalker.ad.admin.common.vo.SysUserAccoutVo;
import cn.adwalker.ad.admin.common.vo.ViewUserVo;
import cn.adwalker.core.repository.impl.BaseDaoImpl;

/**
 * @author wjp
 * 
 */
@Repository("manageUserDao")
public class SysUserDaoImpl extends BaseDaoImpl<SysUser> implements ISysUserDao {

	@Resource
	private ISysUserRoleDao sysUserRoleDao;

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: deleteById
	 * </p>
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.model.sys.dao.ISysUserDao#deleteById(java.lang.Long)
	 */
	@Override
	public int deleteById(Long id) throws Exception {
		sysUserRoleDao.deleteByUserId(id);// 删除映射关系
		String sql = " update T_SYS_USER set del = 1 where id = ? ";
		return this.jdbcTemplate.update(sql, id);
	}

	@Override
	public List<SysUser> findAll() throws Exception {
		String sql = " select * from T_SYS_USER ";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<SysUser>(SysUser.class));
	}

	@Override
	public int insert(SysUser manageUser) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into T_SYS_USER (user_Name,password,real_name,tel_num,type,del,email,area_type,create_time) ");
		sql.append(" values (:userName,:passWord,:realName,:telNum,:type,:del,:email,:area_type,:createTime) ");
		return super.update(sql.toString(), manageUser);
	}

	@Override
	public int updateById(SysUser manageUser) throws Exception {
		return 0;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: 根据名称获取一条记录
	 * </p>
	 * 
	 * @param userName
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.model.sys.dao.ISysUserDao#findOneByName(java.lang.String)
	 */
	@Override
	public SysUser findOneByName(String userName) throws Exception {
		SysUser user = null;
		String sql = " select * from T_SYS_USER where user_name = ? and del = 0";
		List<SysUser> manageUserList = this.jdbcTemplate.query(sql, new BeanPropertyRowMapper<SysUser>(SysUser.class), userName);
		if (manageUserList.size() != 0) {
			user = manageUserList.get(0);
		}
		return user;
	}

	@Override
	public SysUser findOneById(Long id) throws Exception {
		String sql = " select * from T_SYS_USER where id = ? ";
		List<SysUser> manageUserList = this.jdbcTemplate.query(sql, new BeanPropertyRowMapper<SysUser>(SysUser.class), id);
		if (manageUserList.size() != 0) {
			return manageUserList.get(0);
		}
		return null;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: updatePassWord
	 * </p>
	 * 
	 * @param id
	 * @param passWord
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.model.sys.dao.ISysUserDao#updatePassWord(java.lang.Long, java.lang.String)
	 */
	@Override
	public int updatePassWord(Long id, String passWord) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" update T_SYS_USER set password = ");
		sql.append("'" + passWord + "'");
		sql.append(" where id = ? ");
		return this.jdbcTemplate.update(sql.toString(), id);
	}

	@Override
	public List<SysUser> findManageUserByRoleId(Long roleId) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from T_SYS_USER where id in ");
		sql.append(" ( select user_id from T_SYS_USER_ROLE where role_id = ? ) ");
		return this.jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<SysUser>(SysUser.class), roleId);
	}

	@Override
	public SysUserAccoutVo getSysUser(Long id) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from T_SYS_USER where id =" + id);
		List<SysUserAccoutVo> list = (List<SysUserAccoutVo>) jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<SysUserAccoutVo>(SysUserAccoutVo.class));
		SysUserAccoutVo sysVo = new SysUserAccoutVo();
		if (list != null && list.size() > 0) {
			sysVo = list.get(0);
		}
		return sysVo;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: updateAcount
	 * </p>
	 * 
	 * @param sysAccout
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.model.sys.dao.ISysUserDao#updateAcount(cn.adwalker.model.common.domain.SysAccout)
	 */
	@Override
	public int updateAcount(SysAccout sysAccout) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append(" update T_SYS_USER set REAL_NAME = ");
		sb.append("'" + sysAccout.getRealName() + "'");
		sb.append(",EMAIL=");
		sb.append("'" + sysAccout.getEmail() + "'");
		sb.append(",USER_NAME=");
		sb.append("'" + sysAccout.getUserName() + "'");
		sb.append(", AREA_TYPE=");
		sb.append("'" + sysAccout.getArea_type() + "'");
		sb.append(" where id = ");
		sb.append("'" + sysAccout.getId() + "'");
		return jdbcTemplate.update(sb.toString());
	}
	
	
	@SuppressWarnings("deprecation")
	@Override
	public Integer findOperator(String operator) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT id from T_SYS_USER WHERE real_name = ?");
		Integer id;
		try {
			id = jdbcTemplate.queryForInt(sql.toString(), operator);
		} catch (Exception e) {
			id = -1;
		}
		return id;
	}
	
	/**
	 * 
	 * @see cn.adwalker.model.sys.dao.ISysMailDao#login(java.lang.String, java.lang.String)
	 */
	@Override
	public ViewUserVo login(String email, String password) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from view_user where EMAIL = '");
		sql.append(email.trim());
		sql.append("' and PASSWORD ='");
		sql.append(password);
		sql.append("'");
		ViewUserVo vo = null;
		vo = jdbcTemplate.queryForObject(sql.toString(), new BeanPropertyRowMapper<ViewUserVo>(ViewUserVo.class));
		return vo;
	}
	
	
	/**
	 * @see cn.adwalker.model.sys.dao.ISysMailDao#existEmail(java.lang.String)
	 */
	@SuppressWarnings("deprecation")
	@Override
	public int existEmail(String email) throws Exception {
		String sql = "select type from view_user where EMAIL=?";
		return jdbcTemplate.queryForInt(sql, email);
	}

	
	
	
}
