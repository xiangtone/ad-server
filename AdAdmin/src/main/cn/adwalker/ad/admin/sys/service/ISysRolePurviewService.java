/**
 * 
 */
package cn.adwalker.ad.admin.sys.service;

import java.util.List;

import cn.adwalker.model.sys.domain.SysRolePurview;

/**
 * @author wjp
 * 角色权限映射服务接口
 */
public interface ISysRolePurviewService {

	/**
	 * 根据角色id查询权限
	 * @return
	 * @throws Exception
	 */
	public List<SysRolePurview> findPurviewIdByRoleId(Long roleId) throws Exception;
	
	/**
	 * 添加角色权限关系映射
	 * @param roleId
	 * @param checkbox
	 * @return
	 * @throws Exception
	 */
	public void addRolePurviewMapping(Long roleId,String[] checkbox) throws Exception;
	
	/**
	 * 根据角色id删除映射关系
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	public int deleteRolePurviewMapping(Long roleId) throws Exception;
	
	
}
