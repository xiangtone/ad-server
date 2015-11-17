/**
 * 
 */
package cn.adwalker.ad.admin.sys.service;

import java.util.List;

import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.model.sys.domain.SysRole;
import cn.adwalker.model.sys.domain.SysUser;
import cn.adwalker.ad.admin.common.vo.SysUserVo;
import cn.adwalker.ad.admin.sys.bean.PermissionSearchBean;
import cn.adwalker.ad.admin.sys.form.RoleAddForm;
import cn.adwalker.ad.admin.sys.vo.PermissionByRoleVo;
import cn.adwalker.ad.admin.sys.vo.RoleVo;

/**
 * @author wjp 角色服务接口
 */
public interface ISysRoleService {

	/**
	 * 
	 * <p>
	 * Title: insert
	 * </p>
	 * <p>
	 * Description:添加角色
	 * </p>
	 * 
	 * @param form
	 * @param manageUser
	 * @return
	 * @throws Exception
	 * @author cuidd
	 * @date 2013-7-11
	 * @return Long
	 * @version 1.0
	 */
	public Long insert(RoleAddForm form, SysUserVo manageUser) throws Exception;

	/**
	 * 根据id删除
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int deleteById(Long id) throws Exception;

	/**
	 * 查询所有带分页
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<SysRole> findAll() throws Exception;

	/**
	 * 查询带分页
	 * 
	 * @param start
	 * @param limit
	 * @param orderBy
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public List<RoleVo> findByPage(IPageInfo pageInfo) throws Exception;

	/**
	 * 
	* <p>Title: getEntity</p>
	* <p>Description:根据id查询</p>
	* @param roleId
	* @return
	* @throws Exception
	* @author cuidd
	* @date 2013-7-11
	* @return SysRole
	* @version 1.0
	 */
	public SysRole getEntity(Long roleId) throws Exception;

	/**
	 * <p>
	 * Title: findPermissionByRole
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param id
	 * @author cuidd
	 * @date 2013-7-11
	 * @return void
	 * @version 1.0
	 * @param pageInfo 
	 * @throws Exception
	 */
	public List<PermissionByRoleVo> findPermissionByRole(Long id)
			throws Exception;

	/**
	 * <p>
	 * Title: getManageUserByRoleId
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param roleId
	 * @return
	 * @author cuidd
	 * @date 2013-7-11
	 * @return List<SysUser>
	 * @version 1.0
	 * @throws Exception
	 */
	public List<SysUser> getManageUserByRoleId(Long roleId) throws Exception;

	/**
	* <p>Title: update</p>
	* <p>Description:TODO</p>
	* @param form
	* @param currentUser
	* @author cuidd
	* @date 2013-7-11
	* @return void
	* @version 1.0
	 * @param id 
	 * @throws Exception 
	*/
	public void update(Long id, RoleAddForm form, SysUserVo currentUser) throws Exception;

	/**
	* <p>Title: findPermissionForRoleSel</p>
	* <p>Description:TODO</p>
	* @param id
	* @param pageInfo
	* @return
	* @author cuidd
	* @date 2013-7-31
	* @return List<PermissionByRoleVo>
	* @version 1.0
	 * @throws Exception 
	*/
	
	public List<PermissionByRoleVo> findPermissionForRoleSel(Long id,PermissionSearchBean bean,
			IPageInfo pageInfo) throws Exception;

	/**
	* <p>Title: roleAddPermission</p>
	* <p>Description:TODO</p>
	* @param id
	* @param ids
	* @author cuidd
	* @date 2013-7-31
	* @return void
	* @version 1.0
	*/
	
	public void roleAddPermission(Long id, String ids);

	/**
	* <p>Title: delRolePermission</p>
	* <p>Description:TODO</p>
	* @param id
	* @return
	* @author cuidd
	* @date 2013-8-7
	* @return List<PermissionByRoleVo>
	* @version 1.0
	 * @throws Exception 
	*/
	
	public void delRolePermission(Long id) throws Exception;

}
