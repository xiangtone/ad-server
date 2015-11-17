/**
* <p>Title: Ad.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2013-1-4
* @version 1.0
*/
package cn.adwalker.ad.vo;

import cn.adwalker.ad.bean.Data;



/**
 * <p>Title: Ad</p>
 * <p>Description:广告表</p>
 * <p>Company: adwalker</p> 
 * 
 * @author  www.adwalker.cn
 * @date       2013-1-4
 */
public class NoticeInfo extends Data {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer integer;
	private Integer todayInteger;
	private String rate;
	
	
	
	public NoticeInfo(Integer integer, Integer todayInteger, String rate) {
		super();
		this.integer = integer;
		this.todayInteger = todayInteger;
		this.rate = rate;
	}
	public Integer getInteger() {
		return integer;
	}
	public void setInteger(Integer integer) {
		this.integer = integer;
	}
	public Integer getTodayInteger() {
		return todayInteger;
	}
	public void setTodayInteger(Integer todayInteger) {
		this.todayInteger = todayInteger;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	
	
	
	
}
