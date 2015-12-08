/**
* <p>Title: IAdDetailCache.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2013-4-11
* @version 1.0
*/
package cn.adwalker.ad.cache;

import cn.adwalker.ad.cache.element.Advertise;
import cn.adwalker.ad.dao.domain.AdActivateNum;

/**
 * <p>Title: IAdDetailCache</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2013-4-11
 */
public interface IAdCache {
	
	 /**
	 * <p>Title: getAdDetail</p>
	 * <p>Description:TODO</p>
	 * @param id
	 * @return
	 * @return AdDetail
	  */
	 public Advertise getAdv(long id);
	 
	 
	 public Advertise replaceAdv(long id);
	 
	 
	 public AdActivateNum getAdActivateNum(String ad_id);
}
