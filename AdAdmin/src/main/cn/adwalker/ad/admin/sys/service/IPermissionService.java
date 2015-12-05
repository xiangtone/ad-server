package cn.adwalker.ad.admin.sys.service;

import java.util.List;

import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.model.sys.domain.Permission;
import cn.adwalker.ad.admin.common.vo.SysUserVo;
import cn.adwalker.ad.admin.sys.bean.PermissionSearchBean;
import cn.adwalker.ad.admin.sys.form.PermissionForm;
import cn.adwalker.ad.admin.sys.vo.PermissionListVo;

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
public interface IPermissionService {
	
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
	List<PermissionListVo> findByPage(PermissionSearchBean bean,
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
	public PermissionListVo updateStatus(Long id, Integer status)
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
	public void insert(PermissionForm form, SysUserVo manageUser)
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
	public Permission getById(Long id) throws Exception;

	/**
	 * @param id 
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
	public void update(Long id, PermissionForm form, SysUserVo manageUser)
			throws Exception;

}
