/**
* <p>Title: ComparatorAdScore.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2013-5-14
* @version 1.0
*/
package cn.adwalker.ad.util;

import java.util.Comparator;

import cn.adwalker.ad.cache.element.Advertise;

/**
 * <p>Title: ComparatorAdScore</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2013-5-14
 */
public class ComparatorAdScore implements Comparator<Advertise> {

	/** 
	* <p>Title: compare</p>
	* <p>Description:
        	排序依次显示未下载、未签到、已完成签到
      </p>
	* @param o1
	* @param o2
	* @return
	* @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	*/
	@Override
	public int compare(Advertise o1, Advertise o2) {
		int isSownloadFirst = o1.getIsDownload();
		int isSownloadSecond = o2.getIsDownload();
		
		return isSownloadFirst-isSownloadSecond;
		
	}



}
