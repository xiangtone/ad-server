package cn.adwalker.ad.admin.sys.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.model.sys.dao.IPermissionDao;
import cn.adwalker.model.sys.dao.IPermissionResRelDao;
import cn.adwalker.model.sys.dao.ISysResourceDao;
import cn.adwalker.model.sys.domain.Permission;
import cn.adwalker.model.sys.domain.PermissionResRel;
import cn.adwalker.model.sys.domain.SysPurview;
import cn.adwalker.model.sys.domain.SysResource;
import cn.adwalker.ad.admin.common.vo.SysUserVo;
import cn.adwalker.ad.admin.sys.bean.ResourceSearchBean;
import cn.adwalker.ad.admin.sys.form.ResourceForm;
import cn.adwalker.ad.admin.sys.service.IResourceService;
import cn.adwalker.ad.admin.sys.vo.ResourceListVo;
import cn.adwalker.ad.admin.sys.vo.SysPurviewVo;
import cn.adwalker.ad.config.AppConstant;

/**
 * @author wjp
 * 
 */
@Service(value = "resourceService")
@Transactional
public class ResourceServiceImpl implements IResourceService {

	/** 日志记录器 */
	Logger logger = Logger.getLogger(ResourceServiceImpl.class);
	/** 資源管理 */
	@Resource
	private ISysResourceDao sysResourceDao;

	@Resource
	private IPermissionDao permissionDao;

	@Resource
	private IPermissionResRelDao permissionResRelDao;

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
	 * @see cn.adwalker.admin.sys.service.IResourceService.common.service.IResourceManageListService#findByPage(cn.adwalker.ad.admin.sys.bean.ResourceEditForm.common.bean.ResourceSearchBean,
	 *      cn.adwalker.core.page.IPageInfo)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ResourceListVo> findByPage(ResourceSearchBean bean,
			IPageInfo pageInfor) throws Exception {
		List<Object> list = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer(
				"T_SYS_RESOURCE where 1=1 and status= 0 ");
		if (StringUtils.isNotEmpty((bean.getRes_name()))) {
			sb.append(" and NAME ");
			sb.append(" like '%");
			sb.append(bean.getRes_name().trim());
			sb.append("%'");
		}
		if (StringUtils.isNotEmpty(bean.getRes_url())) {
			sb.append(" and URL ");
			sb.append(" like '%");
			sb.append(bean.getRes_url().trim());
			sb.append("%'");
		}

		return (List<ResourceListVo>) sysResourceDao.findByPage("*",
				sb.toString(), list.toArray(), " order by create_time desc ",
				ResourceListVo.class, pageInfor);
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
	public ResourceListVo updateStatus(Long id, Integer status)
			throws Exception {
		sysResourceDao.updateStatus(id, status);
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
	 * @see cn.adwalker.admin.sys.service.IResourceService.common.service.IResourceManageListService#insertResource(cn.adwalker.ad.admin.sys.bean.ResourceEditForm.common.bean.ResourceSearchBean)
	 */
	@Override
	@Transactional(rollbackFor = { Exception.class })
	public void insertResource(ResourceForm form, SysUserVo manageUser)
			throws Exception {
		SysResource entity = new SysResource();
		entity.setCreate_time(new Date());
		entity.setName(form.getName());
		entity.setParent_id(form.getParent_id());
		entity.setOrder_num(form.getOrder_num());
		entity.setUrl(form.getUrl());
		entity.setStat_report_id(form.getStat_report_id());
		entity.setNote(form.getNote());
		entity.setType(form.getType());
		entity.setStatus(AppConstant.RESOURCE_ADD);
		entity.setCreate_user(manageUser.getId());
		Long res_id = sysResourceDao.insert(entity);
		// 添加资源成功后在权限菜单中加入数据
		Permission permission = new Permission();
		permission.setName(form.getName());
		permission.setCreate_user(manageUser.getId());
		permission.setStatus(0);
		Long permission_id = permissionDao.insert(permission);
		PermissionResRel rel = new PermissionResRel();
		rel.setPermission_id(permission_id);
		rel.setResource_id(res_id);
		permissionResRelDao.insert(rel);

		// 权限资源中间表中加入数据
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
	 * @see cn.adwalker.admin.sys.service.IResourceService.common.service.IResourceManageListService#updateResource(cn.adwalker.ad.admin.sys.bean.ResourceEditForm.common.bean.ResourceSearchBean)
	 */

	@Override
	public void updateResource(Long id, ResourceForm form, SysUserVo manageUser)
			throws Exception {
		SysResource entity = (SysResource) sysResourceDao.get(id,
				SysResource.class);
		if (entity != null) {
			entity.setParent_id(form.getParent_id());
			entity.setName(form.getName());
			entity.setUrl(form.getUrl());
			entity.setOrder_num(form.getOrder_num());
			entity.setNote(form.getNote());
			entity.setType(form.getType());
			entity.setStat_report_id(form.getStat_report_id());
			entity.setUpdate_time(new Date());
			entity.setUpdate_user(manageUser.getId());
			sysResourceDao.update(entity);
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
	public SysResource getById(Long id) throws Exception {
		SysResource vo = sysResourceDao.get(id, SysResource.class);
		return vo;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SysPurview> findAll() throws Exception {
		String sql = " select * from T_SYS_RESOURCE  where  status>=0 start with parent_id = 0 connect by prior id = parent_id ";
		return (List<SysPurview>) sysResourceDao.findAll(sql, SysPurview.class);
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: getCurrentUserPurview
	 * </p>
	 * <p>
	 * Description:获取当前用户权限下的菜单集合
	 * </p>
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.admin.sys.service.IPermissionService.common.service.IManagePurviewService#getCurrentUserPurview(java.lang.Long)
	 */
	@Override
	public TreeMap<SysPurview, List<SysPurview>> getCurrentUserPurview(
			Long userId) throws Exception {
		TreeMap<SysPurview, List<SysPurview>> map = new TreeMap<SysPurview, List<SysPurview>>();// 存放权限信息的集合
		List<SysPurview> rootList = this.findRootPurviewListByUserId(userId);
		if (rootList != null && rootList.size() > 0) {
			List<SysPurview> childList;// 权限子菜单列表
			for (SysPurview bean : rootList) {
				childList = new ArrayList<SysPurview>();
				childList = this.findSubPurviewListByParentId(bean.getId(),
						userId);
				if (childList != null && childList.size() > 0) {
					map.put(bean, childList);
				} else {
					map.put(bean, null);
				}
			}
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	private List<SysPurview> findRootPurviewListByUserId(Long userId)
			throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select p.* from T_SYS_ROLE_PERMISSION role_rel left join V_RESOURCE_PERMISSION p "
				+ "on role_rel.permission_id=p.id,T_SYS_USER_ROLE role "
				+ "where p.status>=0 and role_rel.role_id=role.role_id  and role.user_id=? and p.parent_id=0 order by p.ORDER_NUM DESC");
		return (List<SysPurview>) sysResourceDao.findAll(sb.toString(),
				new Object[] { userId }, SysPurview.class);
	}

	@SuppressWarnings("unchecked")
	private List<SysPurview> findSubPurviewListByParentId(Long parentId,
			Long userId) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from (select * from V_RESOURCE_PERMISSION p where p.permission_id in (select rp.permission_id from T_SYS_ROLE_PERMISSION rp where rp.role_id =  ");
		sql.append(" (select role_id from T_SYS_USER_ROLE where user_id = "
				+ userId + "))) mp ");
		sql.append(" where mp.parent_id = " + parentId
				+ " and mp.status>=0 order by mp.ORDER_NUM ");
		return (List<SysPurview>) sysResourceDao.findAll(sql.toString(),
				SysPurview.class);
	}

	@Override
	public List<SysPurview> findSub() throws Exception {
		return this.sysResourceDao.findSub();
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: getUserPurview
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.sys.service.IPermissionService#getUserPurview(java.lang.Long)
	 */
	@Override
	public SysPurviewVo getUserPurview(Long id) throws Exception {
		SysPurviewVo vo = new SysPurviewVo();// 存放权限信息的集合
		List<SysPurviewVo> rootList = this.findRootPurviewListByUser(id);
		if (rootList != null) {
			vo.setItem(rootList);
		}
		for (SysPurviewVo bean : rootList) {
			this.findSubPurviewList(bean, id);
		}
		return vo;
	}

	@SuppressWarnings("unchecked")
	private List<SysPurviewVo> findRootPurviewListByUser(Long userId)
			throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select p.* from T_SYS_ROLE_PURVIEW role_rel left join T_SYS_RESOURCE p "
				+ "on role_rel.res_id=p.id,T_SYS_USER_ROLE role "
				+ "where p.status>=0 and role_rel.role_id=role.role_id  and role.user_id=? and p.parent_id=0 order by p.sort");
		return (List<SysPurviewVo>) sysResourceDao.findAll(sb.toString(),
				new Object[] { userId }, SysPurviewVo.class);
	}

	@SuppressWarnings("unchecked")
	private void findSubPurviewList(SysPurviewVo vo, Long userId)
			throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from (select * from T_SYS_RESOURCE p where p.id in (select rp.res_id from T_SYS_ROLE_PURVIEW rp where rp.role_id =  ");
		sql.append(" (select role_id from T_SYS_USER_ROLE where user_id = "
				+ userId + "))) mp ");
		sql.append(" where mp.parent_id = " + vo.getId()
				+ " and mp.status>=0 order by mp.sort ");

		List<SysPurviewVo> list = (List<SysPurviewVo>) sysResourceDao.findAll(
				sql.toString(), SysPurviewVo.class);
		if (list != null) {
			vo.setItem(list);
			for (SysPurviewVo bean : list) {
				this.findSubPurviewList(bean, userId);
			}
		}
	}
}
