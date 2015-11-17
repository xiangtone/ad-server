package cn.adwalker.ad.admin.finance.vo;

import java.util.Date;

import cn.adwalker.core.util.lang.ObjectUtils;

/**
 * <p>
 * Title: IncomeAuditVo
 * </p>
 * <p>
 * Description:开发者收入vo  
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-6-17
 */
public class IncomeAuditVo {

	/** 主键 */
	private Long id;
	/** 统计日期 */
	private Date effect_time;
	/** 效果发生时间 */
	private Date create_time;
	/** 开发者id */
	private Long dev_id;
	/** 开发者邮箱 */
	private String dev_email;
	/** 应用id */
	private Long app_id;
	/** 应用名称 */
	private String app_name;

	/** 活动id */
	private Long campaign_id;
	/** 活动名称 */
	private String campaign_name;
	/** 管理员id */
	private Long managerId;
	/** 状态（0：未结算，1：已结算） */
	private Integer status;
	/** 确认收入 */
	private Double confirmMoney;
	/** 状态 */
	private String statusString;
	/** 操作人 */
	private String finance_name;

	/**
	 * 业绩提交人(平台运营名称)
	 */
	private String manager_name;

	/**
	 * 提交时间
	 */
	private String manager_time;

	/**
	 * 
	 */
	private String finance_time;

 

	/** 开发者账号状态 */
	private Integer dev_status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getEffect_time() {
		return effect_time;
	}

	public void setEffect_time(Date effect_time) {
		this.effect_time = effect_time;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Long getDev_id() {
		return dev_id;
	}

	public void setDev_id(Long dev_id) {
		this.dev_id = dev_id;
	}

	public String getDev_email() {
		return dev_email;
	}

	public void setDev_email(String dev_email) {
		this.dev_email = dev_email;
	}

	public Long getApp_id() {
		return app_id;
	}

	public void setApp_id(Long app_id) {
		this.app_id = app_id;
	}

	public String getApp_name() {
		return app_name;
	}

	public void setApp_name(String app_name) {
		this.app_name = app_name;
	}

	public Long getCampaign_id() {
		return campaign_id;
	}

	public void setCampaign_id(Long campaign_id) {
		this.campaign_id = campaign_id;
	}

	public String getCampaign_name() {
		return campaign_name;
	}

	public void setCampaign_name(String campaign_name) {
		this.campaign_name = campaign_name;
	}

	public Long getManagerId() {
		return managerId;
	}

	public void setManagerId(Long managerId) {
		this.managerId = managerId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Double getConfirmMoney() {
		return confirmMoney;
	}

	public void setConfirmMoney(Double confirmMoney) {
		this.confirmMoney = confirmMoney;
	}

	public String getFinance_name() {
		return finance_name;
	}

	public void setFinance_name(String finance_name) {
		this.finance_name = finance_name;
	}

	public Integer getDev_status() {
		return dev_status;
	}

	public void setDev_status(Integer dev_status) {
		this.dev_status = dev_status;
	}

	public String getManager_name() {
		return manager_name;
	}

	public void setManager_name(String manager_name) {
		this.manager_name = manager_name;
	}

	public String getManager_time() {
		return manager_time;
	}

	public void setManager_time(String manager_time) {
		this.manager_time = manager_time;
	}

	public String getFinance_time() {
		return finance_time;
	}

	public void setFinance_time(String finance_time) {
		this.finance_time = finance_time;
	}

	public String getStatusString() {
		if(ObjectUtils.isNotEmpty(status)){
			if(status==2){
				statusString="已审核";
			}else{
				statusString="审核不通过";
			}
		}
		return statusString;
	}

	public void setStatusString(String statusString) {
		this.statusString = statusString;
	}
	
}
