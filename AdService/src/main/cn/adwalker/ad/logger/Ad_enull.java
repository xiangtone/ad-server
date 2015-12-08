/**
* <p>Title: Ad.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2013-1-4
* @version 1.0
*/
package cn.adwalker.ad.logger;

import java.io.Serializable;


/**
 * <p>Title: Ad</p>
 * <p>Description:广告表</p>
 * <p>Company: adwalker</p> 
 * 
 * @author    www.adwalker.cn
 * @date       2013-1-4
 */
public class Ad_enull implements Serializable {
	/** @Fields serialVersionUID : TODO*/
	private static final long serialVersionUID = -2280102996708930165L;
	private String title;// 标题

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public void softAd_enull() {
		String s[] = {title};
		Ad_enullLogger logger = new Ad_enullLogger();
			logger.log(s);			
	}	
}
