package cn.adwalker.model.app.dao;

import cn.adwalker.core.repository.IBaseDao;
import cn.adwalker.model.app.domain.AppCurrency;

/**
 * 功能概述： <br>
 * 开发者SDK应用管理接口
 * 
 * @author guoyongxiang
 */
public interface IAppCurrencyDao extends IBaseDao<AppCurrency> {
	/**
	 * 插入虚拟货币
	 * @param tag :0 插入 1：更新
	 * @return
	 * @throws Exception
	 */
	public void saveOrUpdate(AppCurrency currency) throws Exception;
	/**
	 * 查询当前应用的货币汇率
	 * @return
	 * @throws Exception
	 */
	public AppCurrency findCurrencyByAppId(Long app_id) throws Exception;
	
	/**
	 * 根据分类id查询虚拟货币
	 * 
	 * @param id 分类ID
	 * @return
	 */
	public AppCurrency findCurrencyById(String id);
}