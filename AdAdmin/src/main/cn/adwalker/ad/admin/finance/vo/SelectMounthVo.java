/**
 * <p>Title: SelectMounthVo.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author hadoop
 * @date 2013-10-31
 * @version 1.0
 */
package cn.adwalker.ad.admin.finance.vo;

import java.io.Serializable;

/**
 * <p>
 * Title: SelectMounthVo
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-10-31
 */
public class SelectMounthVo implements Serializable {

	/** @Fields serialVersionUID : TODO */
	private static final long serialVersionUID = -8224580346728333688L;
	private String month;
	private String month_start_date;

	public SelectMounthVo() {
		super();
	}

	/**
	 * <p>
	 * Title:
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param month
	 * @param month_start_date
	 * @param month_end_date
	 */
	public SelectMounthVo(String month, String month_start_date,
			String month_end_date) {
		super();
		this.month = month;
		this.month_start_date = month_start_date;
		this.month_end_date = month_end_date;
	}

	private String month_end_date;

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getMonth_start_date() {
		return month_start_date;
	}

	public void setMonth_start_date(String month_start_date) {
		this.month_start_date = month_start_date;
	}

	public String getMonth_end_date() {
		return month_end_date;
	}

	public void setMonth_end_date(String month_end_date) {
		this.month_end_date = month_end_date;
	}

}
