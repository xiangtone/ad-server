/**
 * 
 */
package cn.adwalker.ad.admin.sys.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.model.sys.dao.ISysRoleDao;
import cn.adwalker.model.sys.dao.ISysRolePermissionDao;
import cn.adwalker.model.sys.dao.ISysUserDao;
import cn.adwalker.model.sys.dao.ISysUserRoleDao;
import cn.adwalker.model.sys.domain.SysRole;
import cn.adwalker.model.sys.domain.SysUser;
import cn.adwalker.ad.admin.common.vo.SysUserVo;
import cn.adwalker.ad.admin.sys.bean.PermissionSearchBean;
import cn.adwalker.ad.admin.sys.form.RoleAddForm;
import cn.adwalker.ad.admin.sys.service.ISysRoleService;
import cn.adwalker.ad.admin.sys.vo.PermissionByRoleVo;
import cn.adwalker.ad.admin.sys.vo.RoleVo;

/**
 * <p>
 * Title: SysRoleServiceImpl
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-5-27
 */
@Service(value = "manageRoleService")
@Transactional
public class SysRoleServiceImpl implements ISysRoleService {

	@Resource
	private ISysRoleDao sysRoleDao;

	@Resource
	private ISysUserDao sysUserDao;

	@Resource
	private ISysUserRoleDao sysUserRoleDao;

	@Resource
	private ISysRolePermissionDao sysRolePermissionDao;

	@Override
	public List<SysRole> findAll() throws Exception {
		return this.sysRoleDao.findAll();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RoleVo> findByPage(IPageInfo pageInfo) throws Exception {
		List<RoleVo> list = (List<RoleVo>) sysRoleDao.findByPage("*",
				"T_SYS_ROLE", "order by create_time desc", RoleVo.class,
				pageInfo);
		return this.manageRoleToVo(list);

	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: insert
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param form
	 * @param manageUser
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.sys.service.ISysRoleService#insert(cn.adwalker.ad.admin.sys.form.RoleAddForm,
	 *      cn.adwalker.ad.admin.common.vo.SysUserVo)
	 */
	@Override
	public Long insert(RoleAddForm form, SysUserVo manageUser) throws Exception {
		SysRole entity = new SysRole();
		entity.setName(form.getName());
		entity.setCode(form.getCode());
		entity.setNote(form.getNote());
		entity.setCreate_time(new Date());
		entity.setOrder_num(0);
		entity.setStatus(0);
		entity.setCreate_user(manageUser.getId());
		return this.sysRoleDao.insert(entity);
	}

	@Override
	public int deleteById(Long id) throws Exception {
		this.sysRolePermissionDao.deleteByRoleId(id);// 删除关系
		return this.sysRoleDao.deleteById(id);
	}

	/**
	 * 
	 * <p>
	 * Title: manageRoleToVo
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param manageRoleList
	 * @return
	 * @throws Exception
	 * @author cuidd
	 * @date 2013-7-11
	 * @return List<RoleVo>
	 * @version 1.0
	 */
	private List<RoleVo> manageRoleToVo(List<RoleVo> manageRoleList)
			throws Exception {
		for (RoleVo vo : manageRoleList) {
			int userCount = this.sysUserRoleDao
					.getUserCountByRoleId(vo.getId());
			vo.setUserCount(userCount);
		}
		return manageRoleList;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: getEntity
	 * </p>
	 * <p>
	 * Description:根据id查询
	 * </p>
	 * 
	 * @param roleId
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.sys.service.ISysRoleService#getEntity(java.lang.Long)
	 */
	@Override
	public SysRole getEntity(Long roleId) throws Exception {
		return this.sysRoleDao.get(roleId, SysRole.class);
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findPermissionByRole
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param id
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.sys.service.ISysRoleService#findPermissionByRole(java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PermissionByRoleVo> findPermissionByRole(Long id)
			throws Exception {
		List<PermissionByRoleVo> list = null;
		if (id != null) {
			list = (List<PermissionByRoleVo>) sysUserRoleDao
					.findAll(
							"select * from  T_SYS_PERMISSION p,T_SYS_ROLE_PERMISSION rel  where rel.permission_id=p.id and role_id=?",
							new Object[] { id }, PermissionByRoleVo.class);
		}
		return list;

	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: getManageUserByRoleId
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param roleId
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.sys.service.ISysRoleService#getManageUserByRoleId(java.lang.Long)
	 */
	@Override
	public List<SysUser> getManageUserByRoleId(Long roleId) throws Exception {
		return this.sysUserDao.findManageUserByRoleId(roleId);
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: update
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param form
	 * @param currentUser
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.sys.service.ISysRoleService#update(cn.adwalker.ad.admin.sys.form.RoleAddForm,
	 *      cn.adwalker.ad.admin.common.vo.SysUserVo)
	 */
	@Override
	public void update(Long id, RoleAddForm form, SysUserVo currentUser)
			throws Exception {
		SysRole entity = sysRoleDao.get(id, SysRole.class);
		if (entity != null) {
			entity.setName(form.getName());
			entity.setCode(form.getCode());
			entity.setNote(form.getNote());
			entity.setOrder_num(0);
			entity.setUpdate_time(new Date());
			entity.setUpdate_user(currentUser.getId());
		}

		this.sysRoleDao.update(entity);

	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findPermissionForRoleSel
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param id
	 * @param pageInfo
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.sys.service.ISysRoleService#findPermissionForRoleSel(java.lang.Long,
	 *      cn.adwalker.core.page.IPageInfo)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PermissionByRoleVo> findPermissionForRoleSel(Long id,
			PermissionSearchBean bean, IPageInfo pageInfo) throws Exception {
		List<PermissionByRoleVo> list = null;
		if (id != null) {
			List<Object> param = new ArrayList<Object>();
			param.add(id);
			StringBuilder sb = new StringBuilder(
					"T_SYS_PERMISSION P where not exists(SELECT 1  FROM  T_SYS_ROLE_PERMISSION REL where REL.role_id =? and  REL.PERMISSION_ID=p.id)");
			if (bean != null) {
				if (!StringUtils.isEmpty(bean.getName())) {
					sb.append(" and p.name like ? ");
					param.add("%" + bean.getName() + "%");
				}
				if (!StringUtils.isEmpty(bean.getCode())) {
					sb.append(" and p.code like ? ");
					param.add("%" + bean.getCode() + "%");
				}

			}
			list = (List<PermissionByRoleVo>) sysUserRoleDao.findByPage(" p.*",
					sb.toString(), param.toArray(), PermissionByRoleVo.class,
					pageInfo);
		}
		return list;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: roleAddPermission
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param id
	 * @param ids
	 * @see cn.adwalker.ad.admin.sys.service.ISysRoleService#roleAddPermission(java.lang.Long,
	 *      java.lang.String)
	 */
	@Override
	public void roleAddPermission(Long id, String ids) {
		List<Object[]> objects = new ArrayList<Object[]>();
		if (!StringUtils.isEmpty(ids)) {
			String arr[] = ids.split(",");
			StringBuilder sb = new StringBuilder();
			sb.append(" insert into T_SYS_ROLE_PERMISSION(ROLE_ID,PERMISSION_ID,CREATE_TIME)");
			sb.append(" values (?,?,?)");
			for (int i = 0; i < arr.length; i++) {
				objects.add(new Object[] { id, arr[i], new Date() });
			}
			sysRolePermissionDao.batchUpdate(sb.toString(), objects);
		}

	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: delRolePermission
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.sys.service.ISysRoleService#delRolePermission(java.lang.Long)
	 */
	@Override
	public void delRolePermission(Long id) throws Exception {
		sysRolePermissionDao.delete(id);
	}
}
