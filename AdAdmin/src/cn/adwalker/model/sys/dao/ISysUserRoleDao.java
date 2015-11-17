/**
 * 
 */
package cn.adwalker.model.sys.dao;

import java.util.List;

import cn.adwalker.core.repository.IBaseDao;
import cn.adwalker.model.sys.domain.SysUserRole;

/**
 * @author wjp
 * 管理员、角色映射
 */
public interface ISysUserRoleDao extends IBaseDao<SysUserRole> {

	/**
	 * 添加
	 * @param sysUserRole
	 * @return
	 * @throws Exception
	 */
	public int insert(SysUserRole sysUserRole) throws Exception;
	
	/**
	 * 根据id删除
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int deleteById(Long id) throws Exception;
	
	/**
	 * 根据用户id删除关系
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public int deleteByUserId(Long userId) throws Exception;
	
	/**
	 * 根据id修改
	 * @return
	 * @throws Exception
	 */
	public int updataById(SysUserRole sysUserRole) throws Exception;
	
	/**
	 * 根据用户id修改角色
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public int updateRoleByUserId(Long userId,String[] checkbox) throws Exception;
	
	/**
	 * 查询所有
	 * @return
	 * @throws Exception
	 */
	public List<SysUserRole> findAll() throws Exception;
	
	/**
	 * 获取用户数量根据角色id
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int getUserCountByRoleId(Long roleId) throws Exception;
	/**
	* <p>Title: deleteByRoleId</p>
	* <p>Description:TODO</p>
	* @param userId
	* @throws Exception
	* @author lichuang
	* @date 2013-5-27
	* @return void
	* @version 1.0
	 */
	public int deleteByRoleId(Long userId)throws Exception;
	
}
