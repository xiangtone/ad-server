/**
* <p>Title: MaterielList.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2013-5-30
* @version 1.0
*/
package cn.adwalker.ad.dao.domain;

import cn.adwalker.ad.cache.element.Wall;

/**
 * <p>Title: MaterielList</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2013-5-30
 */
public class MaterielList extends Wall  {
	
	/** @Fields serialVersionUID : TODO*/
	private static final long serialVersionUID = 2857996942090922990L;
	private long id;
	private String banner_url;// banner地址
    private String redirect_url;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public String getBanner_url() {
		return banner_url;
	}
	public void setBanner_url(String banner_url) {
		this.banner_url = banner_url;
	}
	public String getRedirect_url() {
		return redirect_url;
	}
	public void setRedirect_url(String redirect_url) {
		this.redirect_url = redirect_url;
	}
}
