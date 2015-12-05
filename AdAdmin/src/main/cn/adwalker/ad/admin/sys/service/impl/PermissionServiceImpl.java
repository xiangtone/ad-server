package cn.adwalker.ad.admin.sys.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.model.sys.dao.IPermissionDao;
import cn.adwalker.model.sys.domain.Permission;
import cn.adwalker.ad.admin.common.vo.SysUserVo;
import cn.adwalker.ad.admin.sys.bean.PermissionSearchBean;
import cn.adwalker.ad.admin.sys.form.PermissionForm;
import cn.adwalker.ad.admin.sys.service.IPermissionService;
import cn.adwalker.ad.admin.sys.vo.PermissionListVo;
import cn.adwalker.ad.config.AppConstant;

/**
 * @author wjp
 * 
 */
@Service(value = "permissionService")
@Transactional
public class PermissionServiceImpl implements IPermissionService {

	/** 日志记录器 */
	Logger logger = Logger.getLogger(PermissionServiceImpl.class);
	/** 資源管理 */
	@Resource
	private IPermissionDao permissionDao;

	/**
	 * (non-Javadoc)
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
	 * @see cn.adwalker.admin.sys.service.IResourceService.common.service.IResourceManageListService#findByPage(cn.adwalker.admin.sys.bean.ResourceForm.common.bean.ResourceSearchBean,
	 *      cn.adwalker.core.page.IPageInfo)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PermissionListVo> findByPage(PermissionSearchBean bean,
			IPageInfo pageInfor) throws Exception {
		List<Object> list = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer(
				"T_SYS_PERMISSION where 1=1 and status= 0 ");
		if (StringUtils.isNotEmpty((bean.getName()))) {
			sb.append(" and NAME ");
			sb.append(" like '%");
			sb.append(bean.getName().trim());
			sb.append("%'");
		}
		if (StringUtils.isNotEmpty(bean.getCode())) {
			sb.append(" and code ");
			sb.append(" like '%");
			sb.append(bean.getCode().trim());
			sb.append("%'");
		}
		return (List<PermissionListVo>) permissionDao.findByPage("*",
				sb.toString(), list.toArray(), " order by create_time desc ",
				PermissionListVo.class, pageInfor);
	}

	/**
	 * (non-Javadoc)
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
	 * @see cn.adwalker.admin.sys.service.IResourceService.common.service.IResourceManageListService#updateStatus(java.lang.Long,
	 *      java.lang.Integer)
	 */
	@Override
	public PermissionListVo updateStatus(Long id, Integer status)
			throws Exception {
		permissionDao.updateStatus(id, status);
		return null;
	}

	/**
	 * (non-Javadoc)
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
	 * @see cn.adwalker.admin.sys.service.IResourceService.common.service.IResourceManageListService#insertResource(cn.adwalker.admin.sys.bean.ResourceForm.common.bean.ResourceSearchBean)
	 */
	@Override
	public void insert(PermissionForm form, SysUserVo manageUser)
			throws Exception {
		Permission entity = new Permission();
		entity.setStatus(AppConstant.RESOURCE_ADD);
		entity.setCode(form.getCode());
		entity.setCreate_time(new Date());
		entity.setCreate_user(manageUser.getId());
		entity.setName(form.getName());
		entity.setNote(form.getNote());
		entity.setOrder_num(form.getOrder_num());
		permissionDao.insert(entity);
	}

	/**
	 * (non-Javadoc)
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
	 * @see cn.adwalker.admin.sys.service.IResourceService.common.service.IResourceManageListService#updateResource(cn.adwalker.admin.sys.bean.ResourceForm.common.bean.ResourceSearchBean)
	 */

	@Override
	public void update(Long id, PermissionForm form, SysUserVo manageUser)
			throws Exception {
		if (id != null && form != null && manageUser != null) {
			Permission entity = permissionDao.get(id, Permission.class);
			if (entity != null) {
				entity.setCode(form.getCode());
				entity.setName(form.getName());
				entity.setNote(form.getNote());
				entity.setOrder_num(form.getOrder_num());
				entity.setType(form.getType());
				entity.setUpdate_time(new Date());
				entity.setUpdate_user(manageUser.getId());
				permissionDao.update(entity);
			}

		}
	}

	/**
	 * (non-Javadoc)
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
	 * @see cn.adwalker.admin.sys.service.IResourceService.common.service.IResourceManageListService#getById(java.lang.Long)
	 */
	@Override
	public Permission getById(Long id) throws Exception {
		return permissionDao.get(id, Permission.class);
	}
}
