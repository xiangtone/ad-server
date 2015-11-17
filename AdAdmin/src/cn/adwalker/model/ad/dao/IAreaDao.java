package cn.adwalker.model.ad.dao;

import java.util.List;

import cn.adwalker.core.repository.IBaseDao;
import cn.adwalker.model.ad.domain.Area;
import cn.adwalker.model.ad.domain.CampaignSalesman;

/**
* <p>Title: IAreaDao</p>
* <p>Description:区域查询接口</p>
* <p>Company: emar</p> 
* @author   lichuang
* @date       2014-3-12
 */
public interface IAreaDao extends IBaseDao<CampaignSalesman> {
	/**
	* <p>Title: findByArea</p>
	* <p>Description:区域查询</p>
	* @return
	* @throws Exception
	* @author lichuang
	* @date 2014-3-12
	* @return List<Area>
	* @version 1.0
	 */
	public List<Area> findByArea() throws Exception;

}
