/**
 * <p>Title: AdByHourQuery.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author kingdom
 * @date 2013-8-15
 * @version 1.0
 */
package cn.adwalker.ad.admin.report.bean;

/**
 * <p>
 * Title: AdByHourQuery
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-8-15
 */
public class AdByHourQuery {
	
	/*
	 * 开始时间
	 */
	private String beginTime;
	
	/*
	 * 结束时间
	 */
	private String endTime;
	/*
	 * 统计小时
	 */
	private String static_hour;
	/*
	 * 广告主
	 */
	private String adv_id;
	/*
	 * 广告形式
	 */
	private Long type_id;
	/*
	 * 投放名称
	 */
	private String placement_name;
	/*
	 * 主键
	 */
	private String id;

	/*
	 * 系统类型
	 */
	private String os;

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getStatic_hour() {
		return static_hour;
	}

	public void setStatic_hour(String static_hour) {
		this.static_hour = static_hour;
	}

	public String getAdv_id() {
		return adv_id;
	}

	public void setAdv_id(String adv_id) {
		this.adv_id = adv_id;
	}

	public String getPlacement_name() {
		return placement_name;
	}

	public void setPlacement_name(String placement_name) {
		this.placement_name = placement_name;
	}

	public Long getType_id() {
		return type_id;
	}

	public void setType_id(Long type_id) {
		this.type_id = type_id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}
}
