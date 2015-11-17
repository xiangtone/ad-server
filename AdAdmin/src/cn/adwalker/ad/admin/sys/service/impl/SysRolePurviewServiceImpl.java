/**
 * 
 */
package cn.adwalker.ad.admin.sys.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.adwalker.model.sys.dao.ISysRolePermissionDao;
import cn.adwalker.model.sys.domain.SysRolePurview;
import cn.adwalker.ad.admin.sys.service.ISysRolePurviewService;

/**
 * @author wjp
 * 
 */
@Service(value="manageRolePurviewService")
@Transactional
public class SysRolePurviewServiceImpl implements ISysRolePurviewService{

	@Resource
	private ISysRolePermissionDao sysRolePermissionDao;

	/**
	 * @param sysRolePermissionDao the sysRolePermissionDao to set
	 */
	public void setManageRolePurviewDao(ISysRolePermissionDao sysRolePermissionDao) {
		this.sysRolePermissionDao = sysRolePermissionDao;
	}

	@Override
	public List<SysRolePurview> findPurviewIdByRoleId(Long roleId)
			throws Exception {
		return this.sysRolePermissionDao.findPurviewByRoleId(roleId);
	}

	@Override
	public void addRolePurviewMapping(Long roleId, String[] checkbox)
			throws Exception {
		SysRolePurview bean ;
		for(String purviewId : checkbox){
			bean = new SysRolePurview();
			bean.setResId(Long.valueOf(purviewId));
			bean.setRoleId(roleId);
			bean.setCreateTime(new Date());
			this.sysRolePermissionDao.insert(bean);
		}
		sysRolePermissionDao.insertPurview(roleId);
	}

	@Override
	public int deleteRolePurviewMapping(Long roleId) throws Exception {
		return this.sysRolePermissionDao.deleteByRoleId(roleId);
	}
	
}
