/**
* <p>Title: IOSAdCostServiceImpl.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2013-5-15
* @version 1.0
*/
package cn.adwalker.ad.service.impl;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.adwalker.ad.cache.ISystemConfigCache;
import cn.adwalker.ad.cache.IUserScoreCache;
import cn.adwalker.ad.dao.IUserScoreDao;
import cn.adwalker.ad.service.IDelayActionService;



/**
 * <p>Title: IOSAdCostServiceImpl</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2013-5-15
 */
@Service("delayActionService")
public class DelayActionServiceImpl implements IDelayActionService {
	@Resource
	private IUserScoreDao userScoreDao;
	@Resource
	private IUserScoreCache userScoreCache;
	@Resource
	private ISystemConfigCache systemConfigCache;
	
	
	/**  
	* <p>Title: batchAddData</p>
	* <p>Description:TODO</p>
	* @param key
	* @see com.adwalker.escore.service.IIOSAdCostService#batchAddData(java.lang.String)
	*/
	@Override
	public void batchAddData(Long key) {
	}

}
