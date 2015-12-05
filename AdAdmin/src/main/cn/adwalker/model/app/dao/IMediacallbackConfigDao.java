package cn.adwalker.model.app.dao;

import cn.adwalker.ad.admin.app.bean.AppMediaBean;
import cn.adwalker.core.repository.IBaseDao;
import cn.adwalker.model.app.domain.MediacallbackConfigEntity;

public interface IMediacallbackConfigDao extends IBaseDao<MediacallbackConfigEntity> { 
	
	public int saveOrUpdate(AppMediaBean entity) throws Exception;
	public int delMediacallbackConfig(Long id) throws Exception;
}

