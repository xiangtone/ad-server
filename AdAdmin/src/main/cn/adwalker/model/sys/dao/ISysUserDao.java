/**
 * 
 */
package cn.adwalker.model.sys.dao;

import java.util.List;

import cn.adwalker.model.common.domain.SysAccout;
import cn.adwalker.model.sys.domain.SysUser;
import cn.adwalker.ad.admin.common.vo.SysUserAccoutVo;
import cn.adwalker.ad.admin.common.vo.ViewUserVo;
import cn.adwalker.core.repository.IBaseDao;

/**
 * @author wjp 管理员用户
 */
public interface ISysUserDao extends IBaseDao<SysUser> {

	/**
	 * 添加管理员用户
	 * 
	 * @param manageUser
	 * @return
	 * @throws Exception
	 */
	public int insert(SysUser manageUser) throws Exception;

	/**
	 * <p>
	 * Title: deleteById
	 * </p>
	 * <p>
	 * Description:根据id删除用户，逻辑删除
	 * </p>
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-10-23
	 * @return int
	 * @version 1.0
	 */
	public int deleteById(Long id) throws Exception;

	/**
	 * 根据id修改
	 * 
	 * @param manageUser
	 * @return
	 * @throws Exception
	 */
	public int updateById(SysUser manageUser) throws Exception;

	/**
	 * 查询所有
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<SysUser> findAll() throws Exception;

	/**
	 * 根据名称获取一条记录
	 * 
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	public SysUser findOneByName(String userName) throws Exception;

	/**
	 * 根据id获取
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public SysUser findOneById(Long id) throws Exception;

	/**
	 * <p>
	 * Title: updatePassWord
	 * </p>
	 * <p>
	 * Description:重置后台登陆人员密码
	 * </p>
	 * 
	 * @param id
	 * @param passWOrd
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-5-30
	 * @return int
	 * @version 1.0
	 */
	public int updatePassWord(Long id, String passWOrd) throws Exception;

	/**
	 * 根据角色id查找管理员用户
	 * 
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	public List<SysUser> findManageUserByRoleId(Long roleId) throws Exception;

	/**
	 * <p>
	 * Title: getSysUser
	 * </p>
	 * <p>
	 * Description:跳转到账户设置修改
	 * </p>
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-4-18
	 * @return SysUserVo
	 * @version 1.0
	 */
	public SysUserAccoutVo getSysUser(Long id) throws Exception;

	/**
	 * <p>
	 * Title: updateAcount
	 * </p>
	 * <p>
	 * Description:账户设置修改
	 * </p>
	 * 
	 * @param sysAccout
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-4-19
	 * @return int
	 * @version 1.0
	 */
	public int updateAcount(SysAccout sysAccout) throws Exception;
	
	/**
	 * 
	* <p>Title: findOperator</p>
	* <p>Description:TODO</p>
	* @param operator
	* @return
	* @author cuidd
	* @date 2014-1-9
	* @return Integer
	* @version 1.0
	 */
	public Integer findOperator(String operator);
	
	

	/**
	 * 登录
	 * @param email
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public ViewUserVo login(String email,String password) throws Exception;
	
	
	/**
	 * 验证邮箱是否存在
	 * 
	 * @param email
	 * @return
	 * @throws Exception
	 */
	public int  existEmail(String email ) throws Exception;
	
}
