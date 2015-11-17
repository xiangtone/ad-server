/**
 * <p>Title: AppBlack.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author www.adwalker.cn
 * @date 2013-6-14
 * @version 1.0
 */
package cn.adwalker.ad.bean;

import java.io.Serializable;

/**
 * <p>
 * Title: AppBlack
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author www.adwalker.cn
 * @date 2013-6-14
 */
public class AppBlack implements Serializable {

	/** @Fields serialVersionUID : TODO */
	private static final long serialVersionUID = -3774448743633738446L;
	private Long app_id;
	private String black;

	public Long getApp_id() {
		return app_id;
	}

	public void setApp_id(Long app_id) {
		this.app_id = app_id;
	}

	public String getBlack() {
		return black;
	}

	public void setBlack(String black) {
		this.black = black;
	}
}
