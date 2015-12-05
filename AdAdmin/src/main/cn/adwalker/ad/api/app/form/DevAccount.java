/**
 * <p>Title: App.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author kingdom
 * @date 2013-7-23
 * @version 1.0
 */
package cn.adwalker.ad.api.app.form;

import java.io.Serializable;

/**
 * <p>
 * Title: App
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-7-23
 */
public class DevAccount implements Serializable {

	/** @Fields serialVersionUID : TODO */
	private static final long serialVersionUID = 7638338243317145525L;

	private String date;
	private Long dev_id;// 开发者id",
	private String applay_money;// :

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Long getDev_id() {
		return dev_id;
	}

	public void setDev_id(Long dev_id) {
		this.dev_id = dev_id;
	}

	public String getApplay_money() {
		return applay_money;
	}

	public void setApplay_money(String applay_money) {
		this.applay_money = applay_money;
	}
}
