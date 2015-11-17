package cn.adwalker.ad.admin.sys.service;

import java.util.List;
import java.util.TreeMap;

import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.model.sys.domain.SysPurview;
import cn.adwalker.model.sys.domain.SysResource;
import cn.adwalker.ad.admin.common.vo.SysUserVo;
import cn.adwalker.ad.admin.sys.bean.ResourceSearchBean;
import cn.adwalker.ad.admin.sys.form.ResourceForm;
import cn.adwalker.ad.admin.sys.vo.ResourceListVo;
import cn.adwalker.ad.admin.sys.vo.SysPurviewVo;

/**
 * 
 * <p>
 * Title: IResourceService
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-3-15
 */
public interface IResourceService {
	/**
	 * 资源管理查询
	 * <p>
	 * Title: findByPage
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param bean
	 * @param pageInfor
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-3-15
	 * @return List<ResourceListVo>
	 * @version 1.0
	 * @throws
	 */

	List<ResourceListVo> findByPage(ResourceSearchBean bean,
			IPageInfo pageInfor) throws Exception;

	/**
	 * 刪除資源
	 * <p>
	 * Title: updateStatus
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param id
	 * @param status
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-3-15
	 * @return ResourceListVo
	 * @version 1.0
	 * @throws
	 */
	public ResourceListVo updateStatus(Long id, Integer status)
			throws Exception;

	/**
	 * @param manageUser 
	 * 
	 * <p>
	 * Title: insertResource
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-3-15
	 * @return ResourceListVo
	 * @version 1.0
	 * @throws
	 */
	public void insertResource(ResourceForm bean, SysUserVo manageUser)
			throws Exception;

	/**
	 * 
	 * <p>
	 * Title: getById
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-3-15
	 * @return ResourceListVo
	 * @version 1.0
	 * @throws
	 */
	public SysResource getById(Long id) throws Exception;

	/**
	 * @param manageUser 
	 * 
	 * <p>
	 * Title: updateResource
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-3-17
	 * @return ResourceListVo
	 * @version 1.0
	 * @throws
	 */
	public void updateResource(Long id,ResourceForm form, SysUserVo manageUser)
			throws Exception;
	
	

	/**
	 * 查询所有
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<SysPurview> findAll() throws Exception;

	/**
	 * 查询子菜单列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<SysPurview> findSub() throws Exception;


	/**
	 * 获取当前用户的权限信息
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public TreeMap<SysPurview, List<SysPurview>> getCurrentUserPurview(
			Long userId) throws Exception;

	/**
	 * <p>
	 * Title: getUserPurview
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param id
	 * @return
	 * @author cuidd
	 * @date 2013-6-18
	 * @return SysPurviewVo
	 * @version 1.0
	 * @throws Exception
	 */

	public SysPurviewVo getUserPurview(Long id) throws Exception;

}
