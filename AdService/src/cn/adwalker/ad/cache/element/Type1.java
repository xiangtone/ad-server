/**
* <p>Title: Type1.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2013-4-14
* @version 1.0
*/
package cn.adwalker.ad.cache.element;

import java.util.List;

import cn.adwalker.ad.bean.Data;

/**
 * <p>Title: Type1</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2013-4-14
 */
public class Type1 extends Data {

	/** @Fields serialVersionUID : TODO*/
	private static final long serialVersionUID = 1L;	
	private int id;
	private List<Advertise> advertiselist;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<Advertise> getAdvertiselist() {
		return advertiselist;
	}
	public void setAdvertiselist(List<Advertise> advertiselist) {
		this.advertiselist = advertiselist;
	}
}
