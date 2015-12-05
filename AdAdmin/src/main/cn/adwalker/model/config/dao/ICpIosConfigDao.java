package cn.adwalker.model.config.dao;

import cn.adwalker.core.repository.IBaseDao;
import cn.adwalker.model.config.domain.CampaignConfig;


/**
 * <p>广告主接口配置Dao</p>
 * @author jief
 * 
 */
@SuppressWarnings("rawtypes")
public interface ICpIosConfigDao extends IBaseDao{
	/**
	 * 保存
	 * @param config
	 */
	public void saveOrUpdate(CampaignConfig config);
	
	
	public void save(CampaignConfig config);
	
	/**
	 * <p>删除</>
	 * @param id
	 */
	public void delById(String id);
	/**
	 * <p>根据id获取广告配置</p>
	 * @param id
	 * @return
	 */
	public CampaignConfig getConfigById(Long id);
	
	
	/**
	* <p>Title: updatePrice</p>
	* <p>Description:修改单价</p>
	* @param config_id
	* @param price
	* @author cuidd
	* @date 2014年6月23日
	* @return void
	* @version 1.0
	 */
	public void updatePrice(String config_id,Double price);
}
