/**
 * 
 */
package cn.adwalker.model.sys.dao;

import java.util.List;

import cn.adwalker.core.repository.IBaseDao;
import cn.adwalker.model.sys.domain.SysRolePermissionRel;
import cn.adwalker.model.sys.domain.SysRolePurview;

/**
 * @author wjp 权限、角色映射关系
 */
public interface ISysRolePermissionDao extends IBaseDao<SysRolePermissionRel> {

	/**
	 * 根据角色id删除
	 * 
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	public int deleteByRoleId(Long roleId) throws Exception;

	/**
	 * 根据id修改
	 * 
	 * @return
	 * @throws Exception
	 */
	public int updateById(SysRolePurview sysRolePurview) throws Exception;

	/**
	 * 查询
	 * 
	 * @return
	 */
	public List<SysRolePurview> findAll();

	/**
	 * 根据角色id查询权限
	 * 
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	public List<SysRolePurview> findPurviewByRoleId(Long roleId)
			throws Exception;

	/**
	 * <p>
	 * Title: insertPurview
	 * </p>
	 * <p>
	 * Description:插入角色资源对应的父id
	 * </p>
	 * 
	 * @param roleId
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-4-17
	 * @return void
	 * @version 1.0
	 * @throws
	 */
	public void insertPurview(Long roleId) throws Exception;

}
