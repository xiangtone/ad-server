/**
* <p>Title: ComparatorAd.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2013-1-4
* @version 1.0
*/
package cn.adwalker.ad.util;

import java.util.Comparator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.adwalker.ad.cache.element.Advertise;

/**
 * <p>Title: ComparatorAd</p>
 * <p>Description:广告表排序</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2013-1-4
 */
public class ComparatorAd implements Comparator<Advertise>{
	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory
			.getLogger(ComparatorAd.class);

	/**  (non-Javadoc)
	* <p>Title: compare</p>
	* <p>Description:TODO</p>
	* @param o1
	* @param o2
	* @return
	* @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	*/
	@Override
	public int compare(Advertise ad0, Advertise ad1) {
		int temp1 = 0;
		temp1 = ad1.getWeight()-ad0.getWeight();  		
	    return temp1;
		
	}


}
