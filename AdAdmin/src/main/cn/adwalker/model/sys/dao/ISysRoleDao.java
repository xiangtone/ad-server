/**
 * 
 */
package cn.adwalker.model.sys.dao;

import java.util.List;

import cn.adwalker.core.repository.IBaseDao;
import cn.adwalker.model.sys.domain.SysRole;

/**
 * @author wjp
 * 角色管理
 */
public interface ISysRoleDao extends IBaseDao<SysRole> {
	/**
	 * 删除
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int deleteById(Long id) throws Exception;
	
	/**
	 * 修改
	 * @param sysRole
	 * @return
	 * @throws Exception
	 */
	public int updateById(SysRole sysRole) throws Exception;
	
	/**
	 * 查询所有
	 * @return
	 * @throws Exception
	 */
	public List<SysRole> findAll() throws Exception;
	
	/**
	 * 根据用户id获取角色名称
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public SysRole getRoleByUserId(Long userId) throws Exception;
}
