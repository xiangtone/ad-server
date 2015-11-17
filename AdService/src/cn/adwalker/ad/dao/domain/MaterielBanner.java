/**
* <p>Title: MaterielBanner.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2013-4-16
* @version 1.0
*/
package cn.adwalker.ad.dao.domain;

import cn.adwalker.ad.cache.element.Wall;

/**
 * <p>Title: MaterielBanner</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2013-4-16
 */
public class MaterielBanner extends Wall {

	/** @Fields serialVersionUID : TODO*/
	private static final long serialVersionUID = 6124924291358086035L;
    private String img_url_first;
    private String img_url_second;
    private String img_url_third;
    private String redirect_url;
    private Integer time_directional_str;
	
	public String getImg_url_first() {
		return img_url_first;
	}
	public void setImg_url_first(String img_url_first) {
		this.img_url_first = img_url_first;
	}
	public String getImg_url_second() {
		return img_url_second;
	}
	public void setImg_url_second(String img_url_second) {
		this.img_url_second = img_url_second;
	}
	public String getImg_url_third() {
		return img_url_third;
	}
	public void setImg_url_third(String img_url_third) {
		this.img_url_third = img_url_third;
	}
	public String getRedirect_url() {
		return redirect_url;
	}
	public void setRedirect_url(String redirect_url) {
		this.redirect_url = redirect_url;
	}
	public Integer getTime_directional_str() {
		return time_directional_str;
	}
	public void setTime_directional_str(Integer time_directional_str) {
		this.time_directional_str = time_directional_str;
	}
}
