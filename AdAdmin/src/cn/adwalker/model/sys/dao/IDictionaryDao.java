package cn.adwalker.model.sys.dao;

import cn.adwalker.ad.admin.sys.bean.SysDictionaryBean;
import cn.adwalker.ad.admin.sys.vo.SysDictionaryVo;
import cn.adwalker.core.repository.IBaseDao;

/**
 * 功能概述： <br>
 * 开发者管理接口
 * 
 * @author zhaozengbin
 */
@SuppressWarnings("rawtypes")
public interface IDictionaryDao extends IBaseDao {
	
	/**
	 * 查询一条字典内容
	 * <p>Title: findByDictionaryId</p>
     * <p>Description:TODO</p>
	 * @param id
	 */
	public SysDictionaryVo findByDictionaryId(Long id);
	
	
	/**
	 * 修改数据字典
	 * <p>Title: updateDictionary</p>
     * <p>Description:TODO</p>
	 * @param id
	 */
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
