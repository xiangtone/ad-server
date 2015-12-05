/**
 * <p>Title: HistoryAdQuery.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author kingdom
 * @date 2013-9-7
 * @version 1.0
 */
package cn.adwalker.ad.admin.app.bean;

import java.io.Serializable;

/**
 * <p>
 * Title: HistoryAdQuery
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-9-7
 */
public class HistoryAdQuery implements Serializable {

	/** @Fields serialVersionUID : TODO */
	private static final long serialVersionUID = 8345898215075243346L;

	private Long app_id;

	public Long getApp_id() {
		return app_id;
	}

	public void setApp_id(Long app_id) {
		this.app_id = app_id;
	}

}
