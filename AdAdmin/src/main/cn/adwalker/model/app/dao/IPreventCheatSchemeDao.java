package cn.adwalker.model.app.dao;

import java.util.List;

import cn.adwalker.ad.admin.app.bean.PreventBean;
import cn.adwalker.core.repository.IBaseDao;
import cn.adwalker.model.app.domain.PreventCheatScheme;

public interface IPreventCheatSchemeDao extends IBaseDao<PreventCheatScheme> { 
	
	public int saveOrUpdate(PreventBean entity) throws Exception;
	public List<PreventCheatScheme> getPreventCheatSchemeList(Integer type) throws Exception;
	public PreventCheatScheme getIsDefaultPreventCheatScheme(Integer isDefault,Integer adormedia);
	public PreventCheatScheme getPreventCheatSchemeById(Long id);
}

