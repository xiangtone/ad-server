package cn.adwalker.ad.admin.app.service;

import java.util.List;

import cn.adwalker.ad.admin.app.bean.PreventBean;
import cn.adwalker.ad.admin.app.vo.PreventCheatSchemeVo;
import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.model.app.domain.PreventCheatScheme;

public interface IPreventCheatSchemeService {
	public List<PreventCheatSchemeVo> findByPage(PreventBean bean,IPageInfo pageInfor) throws Exception;
	public int saveOrUpdate(PreventBean entity,PreventBean entity2) throws Exception;
	public List<PreventCheatScheme> getPreventCheatSchemeList(Integer type) throws Exception;
	public PreventCheatScheme getIsDefaultPreventCheatScheme(Integer isDefault,Integer adormedia);
	public PreventCheatScheme getPreventCheatSchemeById(Long id);
}
