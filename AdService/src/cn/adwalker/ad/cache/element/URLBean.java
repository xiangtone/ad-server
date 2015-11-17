/**
 * <p>Title: URLBean.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author www.adwalker.cn
 * @date 2013-7-18
 * @version 1.0
 */
package cn.adwalker.ad.cache.element;

import cn.adwalker.ad.bean.Data;

/**
 * <p>
 * Title: URLBean
 * </p>
 * <p>
 * Description:TODO 
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author www.adwalker.cn
 * @date 2013-7-18
 */
public class URLBean extends Data {

	/** @Fields serialVersionUID : TODO */
	private static final long serialVersionUID = 5763655963384367122L;
	private String apiURL;

	public String getApiURL() {
		return apiURL;
	}

	public void setApiURL(String apiURL) {
		this.apiURL = apiURL;
	}

}
