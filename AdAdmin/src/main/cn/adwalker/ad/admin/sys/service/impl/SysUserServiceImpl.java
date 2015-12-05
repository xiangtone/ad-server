/**
 * 
 */
package cn.adwalker.ad.admin.sys.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.util.BeanUtils;
import cn.adwalker.core.util.MD5;
import cn.adwalker.model.common.domain.SysAccout;
import cn.adwalker.model.sys.dao.ISysRoleDao;
import cn.adwalker.model.sys.dao.ISysUserDao;
import cn.adwalker.model.sys.dao.ISysUserRoleDao;
import cn.adwalker.model.sys.domain.SysPurview;
import cn.adwalker.model.sys.domain.SysRole;
import cn.adwalker.model.sys.domain.SysUser;
import cn.adwalker.model.sys.domain.SysUserRole;
import cn.adwalker.model.sys.domain.SysUserRoleView;
import cn.adwalker.ad.admin.common.vo.SysUserAccoutVo;
import cn.adwalker.ad.admin.common.vo.SysUserVo;
import cn.adwalker.ad.admin.sys.bean.UserQueryBean;
import cn.adwalker.ad.admin.sys.form.UserAddForm;
import cn.adwalker.ad.admin.sys.form.UserEditForm;
import cn.adwalker.ad.admin.sys.service.ISysUserService;

/**
 * @author wjp
 * 
 */
@SuppressWarnings("deprecation")
@Service(value = "manageUserService")
@Transactional
public class SysUserServiceImpl implements ISysUserService {

	@Resource
	private ISysUserDao sysUserDao;

	@Resource
	private ISysRoleDao sysRoleDao;

	@Resource
	private ISysUserRoleDao sysUserRoleDao;

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: addManageUser
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param form
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.sys.service.ISysUserService#addManageUser(cn.adwalker.ad.admin.sys.form.UserAddForm)
	 */
	@Override
	public int addManageUser(UserAddForm form) throws Exception {
		SysUser user = new SysUser();
		String passWord = new MD5().getMD5ofStr(form.getPassWord());// 加密
		user.setCreateTime(new Date());
		user.setDel(0);
		user.setType(0);
		user.setPassWord(passWord);
		user.setArea_type(form.getArea_type());
		user.setEmail(form.getEmail());
		user.setRealName(form.getRealName());
		user.setTelNum(form.getTelNum());
		user.setUserName(form.getUserName());
		return this.sysUserDao.insert(user);
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: deleteById
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.sys.service.ISysUserService#deleteById(java.lang.Long)
	 */
	@Override
	public int deleteById(Long id) throws Exception {
		return this.sysUserDao.deleteById(id);
	}

	@Override
	public List<SysUser> getAll() throws Exception {
		return this.sysUserDao.findAll();
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: getManageUserByName
	 * </p>
	 * <p>
	 * Description:根据名称查找用户
	 * </p>
	 * 
	 * @param userName
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.sys.service.manager.common.service.ISysUserService#getManageUserByName(java.lang.String)
	 */
	@Override
	public SysUser getUserByName(String userName) throws Exception {
		if (StringUtils.isEmpty(userName)) {
			return null;
		}
		return this.sysUserDao.findOneByName(userName);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SysUserVo> findByPage(UserQueryBean bean, IPageInfo pageInfor)
			throws Exception {
		StringBuilder sb = new StringBuilder("T_SYS_USER where del = 0");
		List<Object> list = new ArrayList<Object>();
		if (bean != null) {
			if (!StringUtils.isEmpty(bean.getUser_name())) {
				sb.append(" and user_name like ?");
				list.add("%" + bean.getUser_name() + "%");
			}
		}
		return this.manageUserToVo((List<SysUserVo>) sysUserDao.findByPage(
				" * ", sb.toString(), list.toArray(),
				"order by create_time desc", SysUserVo.class, pageInfor));
	}

	/**
	 * 
	 * <p>
	 * Title: manageUserToVo
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param manageUserList
	 * @return
	 * @throws Exception
	 * @author cuidd
	 * @date 2013-7-11
	 * @return List<SysUserVo>
	 * @version 1.0
	 */
	private List<SysUserVo> manageUserToVo(List<SysUserVo> list)
			throws Exception {
		if (list != null && list.size() > 0) {
			for (SysUserVo vo : list) {
				SysRole sysRole = this.sysRoleDao.getRoleByUserId(vo.getId());
				if (sysRole != null) {
					vo.setRoleName(sysRole.getName());
				}
			}
		}
		return list;
	}

	@Override
	public SysUserVo manageUserToVo(SysUser manageUser) throws Exception {
		SysUserVo bean = new SysUserVo();
		BeanUtils.copyProperties(bean, manageUser);
		SysRole sysRole = this.sysRoleDao.getRoleByUserId(manageUser.getId());
		if (sysRole != null) {
			bean.setRoleName(sysRole.getName());
			bean.setRoleId(sysRole.getId());
			bean.setRoleCode(sysRole.getCode());
		}
		return bean;
	}

	public Set<GrantedAuthority> listRoleToCollection(Set<String> set) {
		Set<GrantedAuthority> list = new HashSet<GrantedAuthority>();
		for (String role : set) {
			list.add(new GrantedAuthorityImpl(role));
		}
		return list;
	}

	@Override
	public SysUser getManageUserById(Long id) throws Exception {
		return this.sysUserDao.findOneById(id);
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: updatePassWord
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param id
	 * @param passWord
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.sys.service.ISysUserService#updatePassWord(java.lang.Long,
	 *      java.lang.String)
	 */
	@Override
	public int updatePassWord(Long id, String passWord) throws Exception {
		return this.sysUserDao.updatePassWord(id, passWord);
	}

	@Override
	public int deleteUserRole(Long userId) throws Exception {
		return this.sysUserRoleDao.deleteByUserId(userId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SysPurview> findPurview(Long roleId) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select p.url as resUrl from V_RESOURCE_PERMISSION  p , T_SYS_ROLE_PERMISSION rp ,t_sys_role r where p.permission_id=rp.permission_id and rp.role_id=r.id ");
		sql.append(" and r.id=").append(roleId);
		return (List<SysPurview>) this.sysUserDao.findAll(sql.toString(),
				SysPurview.class);
	}

	@Override
	public SysUserAccoutVo getSysUser(Long id) throws Exception {
		SysUserAccoutVo vo = this.sysUserDao.getSysUser(id);
		SysRole role = sysRoleDao.getRoleByUserId(id);
		if (role != null) {
			vo.setRole_id(role.getId());
		}
		return vo;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: updateAcount
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param sysUserAccout
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.sys.service.ISysUserService#updateAcount(cn.adwalker.admin.common.form.SysUserAccout)
	 */
	@Override
	public int updateAcount(UserEditForm form) throws Exception {
		SysAccout accout = null;
		if (form != null) {
			accout = new SysAccout();
			accout.setId(form.getId());
			accout.setEmail(form.getEmail());
			accout.setRealName(form.getRealName());
			accout.setUserName(form.getUserName());
			accout.setArea_type(form.getArea_type());
			sysUserRoleDao.deleteByUserId(form.getId());

			SysUserRole sysUserRole = new SysUserRole();
			sysUserRole.setRoleId(form.getRole_id());
			sysUserRole.setUserId(form.getId());
			sysUserRoleDao.insert(sysUserRole);

		}
		return this.sysUserDao.updateAcount(accout);
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findRoleByUser
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.sys.service.ISysUserService#findRoleByUser(java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<SysUserRoleView> findRoleByUser(Long userId) throws Exception {
		String sql = "select r.name,r.code,rel.* from T_SYS_USER_ROLE rel LEFT JOIN  T_SYS_ROLE r on rel.role_id=r.id where rel.USER_ID = ? ";
		return (List<SysUserRoleView>) this.sysUserDao.findAll(sql,
				new Object[] { userId }, SysUserRoleView.class);

	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: deleteByRoleId
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param userId
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.sys.service.ISysUserService#deleteByRoleId(java.lang.Long)
	 */
	@Override
	public void deleteByRoleId(Long userId) throws Exception {
		this.sysUserRoleDao.deleteByRoleId(userId);
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: updateRoleByUserId
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param userId
	 * @param checkbox
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.sys.service.ISysUserService#updateRoleByUserId(java.lang.Long,
	 *      java.lang.String[])
	 */
	@Override
	public int updateRoleByUserId(Long userId, String[] checkbox)
			throws Exception {
		return this.sysUserRoleDao.updateRoleByUserId(userId, checkbox);

	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findAllRole
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.sys.service.ISysUserService#findAllRole()
	 */
	@Override
	public List<SysRole> findAllRole() throws Exception {
		return sysRoleDao.findAll();
	}
}
