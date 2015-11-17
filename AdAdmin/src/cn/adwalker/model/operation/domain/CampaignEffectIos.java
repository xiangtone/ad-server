/**
 * <p>Title: CampaignEffectIos.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author kingdom
 * @date 2013-9-12
 * @version 1.0
 */
package cn.adwalker.model.operation.domain;

import java.util.Date;

import cn.adwalker.core.repository.Entity;

/**
 * <p>
 * Title: CampaignEffectIos
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-9-12
 */
public class CampaignEffectIos implements Entity {
	/** @Fields serialVersionUID : TODO */
	private static final long serialVersionUID = 2268498544950173697L;
	// 主键
	private Long id;
	private String campaign_id;
	private String start_time;
	private String end_time;
	private Double confirm_total;
	private Date create_time;
	private Integer status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCampaign_id() {
		return campaign_id;
	}

	public void setCampaign_id(String campaign_id) {
		this.campaign_id = campaign_id;
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

	public Double getConfirm_total() {
		return confirm_total;
	}

	public void setConfirm_total(Double confirm_total) {
		this.confirm_total = confirm_total;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
