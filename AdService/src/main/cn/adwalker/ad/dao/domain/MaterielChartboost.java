/**
 * <p>Title: MaterielChartboost.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author www.adwalker.cn
 * @date 2013-4-17
 * @version 1.0
 */
package cn.adwalker.ad.dao.domain;

import cn.adwalker.ad.cache.element.Wall;

/**
 * <p>
 * Title: MaterielChartboost
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author www.adwalker.cn
 * @date 2013-4-17
 */
public class MaterielChartboost extends Wall {

	/** @Fields serialVersionUID : TODO */
	private static final long serialVersionUID = -7156318401576228080L;

	private String img_horizontal;
	private String img_vertical;
	private String redirect_url;

	public String getImg_horizontal() {
		return img_horizontal;
	}

	public void setImg_horizontal(String img_horizontal) {
		this.img_horizontal = img_horizontal;
	}

	public String getImg_vertical() {
		return img_vertical;
	}

	public void setImg_vertical(String img_vertical) {
		this.img_vertical = img_vertical;
	}
	public String getRedirect_url() {
		return redirect_url;
	}

	public void setRedirect_url(String redirect_url) {
		this.redirect_url = redirect_url;
	}
}
