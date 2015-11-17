/**
 * 
 */
package cn.adwalker.ad.admin.sys.service;

import java.util.List;

import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.model.sys.domain.SysPurview;
import cn.adwalker.model.sys.domain.SysRole;
import cn.adwalker.model.sys.domain.SysUser;
import cn.adwalker.model.sys.domain.SysUserRoleView;
import cn.adwalker.ad.admin.common.vo.SysUserAccoutVo;
import cn.adwalker.ad.admin.common.vo.SysUserVo;
import cn.adwalker.ad.admin.sys.bean.UserQueryBean;
import cn.adwalker.ad.admin.sys.form.UserAddForm;
import cn.adwalker.ad.admin.sys.form.UserEditForm;

/**
 * @author wjp 管理员服务接口
 */
public interface ISysUserService {

	/**
	 * 根据用户名取一条记录
	 * 
	 * @return
	 * @throws Exception
	 */
	public SysUser getUserByName(String userName) throws Exception;

	/**
	 * 根据id获取一条记录
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public SysUser getManageUserById(Long id) throws Exception;

	/**
	 * 分页查询
	 * 
	 * @param pageIndex
	 * @param pageRecord
	 * @return
	 * @throws Exception
	 */
	public List<SysUserVo> findByPage(UserQueryBean bean, IPageInfo pageInfor)
			throws Exception;

	/**
	 * 取所有
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<SysUser> getAll() throws Exception;

	/**
	 * <p>
	 * Title: addManageUser
	 * </p>
	 * <p>
	 * Description:添加管理员用户
	 * </p>
	 * 
	 * @param form
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-10-23
	 * @return int
	 * @version 1.0
	 */
	public int addManageUser(UserAddForm form) throws Exception;

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
	 * 转换vo ，增加角色名称信息
	 * 
	 * @param manageUser
	 * @return
	 * @throws Exception
	 */
	public SysUserVo manageUserToVo(SysUser manageUser) throws Exception;

	/**
	 * <p>
	 * Title: updatePassWord
	 * </p>
	 * <p>
	 * Description:重置后台登陆人员密码
	 * </p>
	 * 
	 * @param id
	 * @param passWord
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-5-30
	 * @return int
	 * @version 1.0
	 */
	public int updatePassWord(Long id, String passWord) throws Exception;

	/**
	 * 删除用户的角色
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public int deleteUserRole(Long userId) throws Exception;

	/**
	 * 
	 * <p>
	 * Title: findPurview
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param roleId
	 * @return
	 * @throws Exception
	 * @author cuidd
	 * @date 2013-8-19
	 * @return List<SysPurview>
	 * @version 1.0
	 */
	public List<SysPurview> findPurview(Long roleId) throws Exception;

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
	 * @throws
	 */
	public SysUserAccoutVo getSysUser(Long id) throws Exception;

	/**
	 * <p>
	 * Title: findRoleByUser
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param userId
	 * @return
	 * @author cuidd
	 * @date 2013-7-11
	 * @return List<SysRole>
	 * @version 1.0
	 * @throws Exception
	 */

	public List<SysUserRoleView> findRoleByUser(Long userId) throws Exception;

	/**
	 * <p>
	 * Title: deleteByRoleId
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param userId
	 * @author cuidd
	 * @date 2013-8-7
	 * @return void
	 * @version 1.0
	 * @throws Exception
	 */
	public void deleteByRoleId(Long userId) throws Exception;

	/**
	 * <p>
	 * Title: updateRoleByUserId
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param userId
	 * @param checkbox
	 * @author cuidd
	 * @date 2013-8-7
	 * @return void
	 * @version 1.0
	 * @throws Exception
	 */

	public int updateRoleByUserId(Long userId, String[] checkbox)
			throws Exception;

	/**
	 * <p>
	 * Title: findAllRole
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @return
	 * @author cuidd
	 * @date 2013-8-19
	 * @return List<SysRoleVo>
	 * @version 1.0
	 * @throws Exception
	 */

	public List<SysRole> findAllRole() throws Exception;

	/**
	 * <p>
	 * Title: updateAcount
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param form
	 * @return
	 * @author cuidd
	 * @date 2013-8-19
	 * @return int
	 * @version 1.0
	 * @throws Exception
	 */
	public int updateAcount(UserEditForm form) throws Exception;
}
