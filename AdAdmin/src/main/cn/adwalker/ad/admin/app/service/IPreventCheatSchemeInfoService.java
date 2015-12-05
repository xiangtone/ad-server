package cn.adwalker.ad.admin.app.service;

import java.util.List;

import cn.adwalker.ad.admin.app.bean.PreventInfoBean;
import cn.adwalker.ad.admin.app.vo.PreventCheatSchemeInfoVo;
import cn.adwalker.core.page.IPageInfo;

public interface IPreventCheatSchemeInfoService {
	public List<PreventCheatSchemeInfoVo> findByPage(PreventInfoBean bean,IPageInfo pageInfor) throws Exception;
	public int saveOrUpdate(PreventInfoBean entity) throws Exception;
	
}
