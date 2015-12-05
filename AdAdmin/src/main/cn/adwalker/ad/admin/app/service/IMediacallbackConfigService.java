package cn.adwalker.ad.admin.app.service;

import java.util.List;

import cn.adwalker.ad.admin.app.bean.AppMediaBean;
import cn.adwalker.ad.admin.app.vo.MediacallbackConfigVo;
import cn.adwalker.core.page.IPageInfo;

public interface IMediacallbackConfigService {
	public List<MediacallbackConfigVo> findByPage(AppMediaBean bean,IPageInfo pageInfor) throws Exception;
	public int saveOrUpdate(AppMediaBean entity) throws Exception;
	public int delMediacallbackConfig(Long id) throws Exception;
}
