package cn.adwalker.ad.admin.sys.service;

import java.util.List;

import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.ad.admin.sys.bean.SysDictionaryBean;
import cn.adwalker.ad.admin.sys.vo.SysDictionaryVo;

public interface ISysDictionarySettingService {
	public List<SysDictionaryVo> findByPage(SysDictionaryBean bean,IPageInfo pageInfor) throws Exception;
	public SysDictionaryVo findById(Long id);
	public void updateDictionary(SysDictionaryBean form);
	/**
	* <p>Title: addDictionary</p>
	* <p>Description:添加数据字典</p>
	* @param form
	* @author lichuang
	* @date 2013-5-30
	* @return void
	* @version 1.0
	 */
	public void addDictionary(SysDictionaryBean form);
}
