package cn.adwalker.ad.admin.operation.bean;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

/**
 * 
 * <p>
 * Title: ConfirmNumberbean
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-7-16
 */
public class ConfirmNumberbean {
	// 主键
	private Long id;
	// 渠道id
	private String package_id;

	// 渠道包号
	private String package_code;
	// 活动名称
	private String campaign;

	// 效果发生时间--开始
	private String start_time;
	
	
	//效果发生时间--结束
	private String end_time;

	/**
	 * 文件名
	 */
	private String file_name;

	/**
	 * 
	 */
	private Integer status;
	
	/**
	 * 结算状态
	 */
	private Integer	balance_status;

	public Long getCampaign_id() {
		Long l = 0l;
		if (!StringUtils.isEmpty(campaign) && NumberUtils.isNumber(campaign)) {
			l = Long.valueOf(campaign);
		}
		return l;
	}

	public String getCampaign_name() {
		String s = null;
		if (!StringUtils.isEmpty(campaign)) {
			s = campaign;
		}
		return s;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPackage_id() {
		return package_id;
	}

	public void setPackage_id(String package_id) {
		this.package_id = package_id;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getPackage_code() {
		return package_code;
	}

	public String getCampaign() {
		return campaign;
	}

	public void setCampaign(String campaign) {
		this.campaign = campaign;
	}

	public void setPackage_code(String package_code) {
		this.package_code = package_code;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public Integer getBalance_status() {
		return balance_status;
	}

	public void setBalance_status(Integer balance_status) {
		this.balance_status = balance_status;
	}
}
