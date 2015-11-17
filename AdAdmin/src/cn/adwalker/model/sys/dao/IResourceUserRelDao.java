package cn.adwalker.model.sys.dao;

import cn.adwalker.core.repository.IBaseDao;
import cn.adwalker.model.sys.domain.PermissionResRel;

/**
 * 功能概述： <br>
 * 开发者管理接口
 * 
 * @author zhaozengbin
 */
public interface IResourceUserRelDao extends IBaseDao<PermissionResRel> {
	public boolean needDataPermisstion(Long resource_id,Long user_id);


	

}
