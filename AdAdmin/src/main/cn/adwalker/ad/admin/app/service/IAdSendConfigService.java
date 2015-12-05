package cn.adwalker.ad.admin.app.service;

import java.util.List;

import cn.adwalker.ad.admin.app.bean.AdSendBean;
import cn.adwalker.ad.admin.app.vo.AdSendConfigVo;
import cn.adwalker.core.page.IPageInfo;

public interface IAdSendConfigService {
	public List<AdSendConfigVo> findByPage(AdSendBean bean,IPageInfo pageInfor) throws Exception;
	public int saveOrUpdate(AdSendBean entity) throws Exception;
	public int delAdSendConfig(Long id) throws Exception;
	
}
