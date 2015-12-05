package cn.adwalker.model.app.dao;

import cn.adwalker.ad.admin.app.bean.AdSendBean;
import cn.adwalker.core.repository.IBaseDao;
import cn.adwalker.model.app.domain.AdSendConfigEntity;

public interface IAdSendConfigDao extends IBaseDao<AdSendConfigEntity> { 
	
	public int saveOrUpdate(AdSendBean entity) throws Exception;
	public int delAdSendConfig(Long id) throws Exception;
}

