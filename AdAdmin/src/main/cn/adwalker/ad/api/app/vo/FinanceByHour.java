/**
 * <p>Title: App.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author kingdom
 * @date 2013-7-23
 * @version 1.0
 */
package cn.adwalker.ad.api.app.vo;

import java.io.Serializable;
import java.util.Date;

import cn.adwalker.ad.api.app.util.Signature;
import cn.adwalker.core.util.ConfigUtil;
import cn.adwalker.core.util.DateUtil;

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
public class FinanceByHour implements Serializable {

	/** @Fields serialVersionUID : TODO */
	private static final long serialVersionUID = 7638338243317145525L;

	/**
	 * 
	 * <p>
	 * Title:
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 */
	public FinanceByHour() {
		super();
	}

	/**
	 * 
	 * <p>
	 * Title:
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param date
	 */
	public FinanceByHour(String date) {
		time = DateUtil.formatDate(new Date());
		url = ConfigUtil.getString("api.data.download.url_hour") + "/" + date;
		sig = Signature.signature();

	}

	private String url;

	private String filename;

	private String hour;

	private String time;

	private String sig;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getSig() {
		return sig;
	}

	public void setSig(String sig) {
		this.sig = sig;
	}
}
