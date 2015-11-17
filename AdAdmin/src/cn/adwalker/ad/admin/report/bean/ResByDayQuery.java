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
* <p>Title: ResByDayQuery</p>
* <p>Description:Res查询条件bean</p>
* <p>Company: adwalker</p> 
* @author    lichuang
* @date       2014年10月17日
 */
public class ResByDayQuery {
	
	/*
	 * 开始时间
	 */
	private String beginTime;
	
	/*
	 * 结束时间
	 */
	private String endTime;

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
	 * 来源
	 */
	private int res_type;

	
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

	public int getRes_type() {
		return res_type;
	}

	public void setRes_type(int res_type) {
		this.res_type = res_type;
	}
	
	
}
