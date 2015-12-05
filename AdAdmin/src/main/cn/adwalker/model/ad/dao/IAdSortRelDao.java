package cn.adwalker.model.ad.dao;

import java.util.List;

import cn.adwalker.core.repository.IBaseDao;
import cn.adwalker.model.ad.domain.AdCategoryRel;
import cn.adwalker.model.common.domain.Sort;

/**
 * 
* <p>Title: IEscoreSortDao</p>
* <p>Description:TODO</p>
* <p>Company: adwalker</p> 
* @author    cuidd
* @date       2013-6-4
 */
public interface IAdSortRelDao extends IBaseDao<AdCategoryRel> {


	/**
	* <p>Title: findCampaignCategory</p>
	* <p>Description:应用类型</p>
	* @param campaign_id
	* @param type
	* @return
	* @throws Exception
	* @author lichuang
	* @date 2013-8-21
	* @return List<Sort>
	* @version 1.0
	 */
	
	public List<Sort> findCampaignCategory(Long campaign_id,Integer type) throws Exception;

	/**
	* <p>Title: updateCategory</p>
	* <p>Description:TODO</p>
	* @param id
	* @param category
	* @author cuidd
	* @date 2013-5-17
	* @return void
	* @version 1.0
	*/
	
	public void updateCategory(Long id, Long[] category);
	
	/**
	* <p>Title: findCampaignCity</p>
	* <p>Description:城市</p>
	* @param id
	* @param i
	* @return
	* @throws Exception
	* @author lichuang
	* @date 2013-6-3
	* @return List<Sort>
	* @version 1.0
	 */
	public List<Sort> findCampaignCity(Long campaign_id,Integer type)throws Exception;
	

	/**
	* <p>Title: delCategory</p>
	* <p>Description:TODO</p>
	* @param id
	* @author cuidd
	* @date 2013-6-28
	* @return void
	* @version 1.0
	*/
	
	public void delCategory(Long id);
}
