package cn.adwalker.model.app.dao;

import cn.adwalker.ad.admin.app.bean.PreventInfoBean;
import cn.adwalker.core.repository.IBaseDao;
import cn.adwalker.model.app.domain.PreventCheatSchemeInfo;

public interface IPreventCheatSchemeInfoDao extends IBaseDao<PreventCheatSchemeInfo> { 
	
	public int saveOrUpdate(PreventInfoBean entity) throws Exception;
}

