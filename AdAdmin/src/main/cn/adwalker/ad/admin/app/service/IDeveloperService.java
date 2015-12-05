package cn.adwalker.ad.admin.app.service;

import java.util.List;

import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.model.app.domain.Developer;
import cn.adwalker.ad.admin.app.bean.DevQuery;
import cn.adwalker.ad.admin.app.vo.DeveloperVo;

/**
 * @author guoyongxiang
 * 
 * 
 */
public interface IDeveloperService {


	/**
	 * 根据email查询开发者信息
	 * 
	 * @param email
	 * @return
	 * @throws Exception
	 */
	public Developer exists(String email) throws Exception;

	/**
	 * 查询所有开发者列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Developer> findAll() throws Exception;

	/**
	 * 根据ID查询用户信息
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Developer getById(Long id) throws Exception;

	/**
	 * 根据ID查询用户信息
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Developer getByIdAndName(Long id, String name) throws Exception;

	/**
	 * 根据ID查询用户信息
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<Developer> getByEmail(String name) throws Exception;

	/**
	 * 修改用户信息
	 * 
	 * @param userDeveloper
	 * @return
	 * @throws Exception
	 */
	public Developer update(Developer userDeveloper) throws Exception;

	/**
	 * 修改用户信息
	 * 
	 * @param long1
	 * 
	 * @param userDeveloper
	 * @return
	 * @throws Exception
	 */
	public Developer updateStatus(Long dev_id, Integer status, Long long1)
			throws Exception;

	/**
	 * 修改用户密码
	 * 
	 * @param userDeveloper
	 * @throws Exception
	 */
	public void updatePass(Developer userDeveloper) throws Exception;

	/**
	 * 扣税金额
	 * 
	 * @return
	 * @throws Exception
	 */
	public Double getTax(Double preTax) throws Exception;

	 

	/**
	 * @throws Exception
	 *             <p>
	 *             Title: findByPage
	 *             </p>
	 *             <p>
	 *             Description:TODO
	 *             </p>
	 * @param bean
	 * @param pageInfo
	 * @return
	 * @author cuidd
	 * @date 2013-1-25
	 * @return List<Developer>
	 * @version 1.0
	 * @throws
	 */

	public List<Developer> findByPage(DevQuery bean, IPageInfo pageInfo)
			throws Exception;

	/**
	* <p>Title: getAppCount</p>
	* <p>Description:TODO</p>
	* @return
	* @throws Exception
	* @author cuidd
	* @date 2013-1-26
	* @return List<DeveloperVo>
	* @version 1.0
	* @throws
	*/
	
	List<DeveloperVo> getAppCount() throws Exception;

	/**
	* <p>Title: updateFreeze</p>
	* <p>Description:TODO</p>
	* @param developerId
	* @author cuidd
	* @date 2013-5-15
	* @return void
	* @version 1.0
	 * @throws Exception 
	*/
	
	public void updateFreeze(Long developerId) throws Exception;
	/**
	* <p>Title: findByDev</p>
	* <p>Description:TODO</p>
	* @param bean
	* @return
	* @throws Exception
	* @author lichuang
	* @date 2013-5-20
	* @return List<Developer>
	* @version 1.0
	 */
	public List<Developer> findByDev(DevQuery bean)throws Exception;
}