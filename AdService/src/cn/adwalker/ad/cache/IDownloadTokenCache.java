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


/**
 * <p>Title: IAdDetailCache</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2013-4-11
 */
public interface IDownloadTokenCache {
	
	 /**
	 * <p>Title: getAdDetail</p>
	 * <p>Description:TODO</p>
	 * @param id
	 * @return
	 * @return AdDetail
	  */
	 public String getCache(String id);
	 
	 public String addCache(String id,String token);
	 
	 
	 public void clear(String id);
	 
	 
}
