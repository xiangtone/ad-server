package cn.adwalker.ad.model.application.domain;

import java.util.Date;

import cn.adwalker.ad.util.StatusConfigUtil;

/**
 * 功能描述：<br>
 * 上传的SDK应用实体
 * 
 * @author guoyongxiang
 */
public class Page {

	/** 主键 */
	private Long id;
	
	/** 关键字 */
	private String theme;
	
	/** 状态（0：待审核，1：审核中，2：通过，3：未通过，4：上线，5：下线，6：终止） */
	private Integer status;

	private Integer type_id;
	
	private String type_name;
 
	/** 创建时间 */
	private Date createTime;
	
	/** 修改时间 */
	private Date updateTime;

	/** 密钥 */
	private String appkey;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	public Integer getType_id() {
		return type_id;
	}

	public void setType_id(Integer type_id) {
		this.type_id = type_id;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime
	 *            the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the appkey
	 */
	public String getAppkey() {
		return appkey;
	}

	/**
	 * @param appkey
	 *            the appkey to set
	 */
	public void setAppkey(String appkey) {
		this.appkey = appkey;
	}

	/**
	 * @return the updateTime
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getType_name() {
		return type_name;
	}

	public void setType_name(String type_name) {
		this.type_name = type_name;
	}

	/**
	 * @param updateTime
	 *            the updateTime to set
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * @return the appStatusName
	 */
	public String getAppStatusName() {
		return StatusConfigUtil.getString(this.status.toString()); 
	}
}
