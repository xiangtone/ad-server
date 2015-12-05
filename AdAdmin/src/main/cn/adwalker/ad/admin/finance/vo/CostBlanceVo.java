package cn.adwalker.ad.admin.finance.vo;

import java.util.Date;

/**
 * 
 * <p>
 * Title: ChannelEffectVo
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-7-30
 */
public class CostBlanceVo {

	// 主键
	private Long id;

	private String start_date;

	private String end_date;

	private Long campaign_id;

	// 活动名称
	private String campaign_name;

	// 状态
	private Integer status;
	
	private Date create_time;	
	
	/**
	 * 对账时间
	 */
	private Date submit_time;
	
	/**
	 * 结算时间
	 */
	private Date operator_time;
	
	private Long operator_id;
	/**
	 * 结算人
	 */
	private String operator_name;

	/**
	 * 媒体名称
	 */
	private String channel_name;
	
	
	/**
	 * 渠道账号
	 */
	private String channel_account;
	
	
	private Long channel_id;

	/**
	 * 类型名称
	 */
	private String type_name;
	
	/**
	 * 
	 */
	private String create_user_name;
	
	private String status_str;

	// 类型名称
	private String os;

	// 效果发生时间
	private String static_date;
	// 广告主录入效果数
	private Integer confirm_num;
	// 确认数=（confirm_num*渠道指数）
	private Integer confirm_amount;
	// 广告接入单价
	private Double price;
	
	private Double cost;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCampaign_name() {
		return campaign_name;
	}

	public void setCampaign_name(String campaign_name) {
		this.campaign_name = campaign_name;
	}

	public String getStatic_date() {
		return static_date;
	}

	public void setStatic_date(String static_date) {
		this.static_date = static_date;
	}

	public Integer getConfirm_num() {
		return confirm_num;
	}

	public void setConfirm_num(Integer confirm_num) {
		this.confirm_num = confirm_num;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	
	public String getChannel_name() {
		return channel_name;
	}

	public void setChannel_name(String channel_name) {
		this.channel_name = channel_name;
	}

	public Long getChannel_id() {
		return channel_id;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public void setChannel_id(Long channel_id) {
		this.channel_id = channel_id;
	}

	public String getType_name() {
		return type_name;
	}

	public void setType_name(String type_name) {
		this.type_name = type_name;
	}

	public Integer getConfirm_amount() {
		if (confirm_amount == null) {
			confirm_amount = 0;
		}
		return confirm_amount;
	}

	public void setConfirm_amount(Integer confirm_amount) {
		this.confirm_amount = confirm_amount;
	}

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	public Long getCampaign_id() {
		return campaign_id;
	}

	public void setCampaign_id(Long campaign_id) {
		this.campaign_id = campaign_id;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getCreate_user_name() {
		return create_user_name;
	}

	public void setCreate_user_name(String create_user_name) {
		this.create_user_name = create_user_name;
	}

	public String getChannel_account() {
		return channel_account;
	}

	public void setChannel_account(String channel_account) {
		this.channel_account = channel_account;
	}

	public Date getOperator_time() {
		return operator_time;
	}

	public void setOperator_time(Date operator_time) {
		this.operator_time = operator_time;
	}

	public Long getOperator_id() {
		return operator_id;
	}

	public void setOperator_id(Long operator_id) {
		this.operator_id = operator_id;
	}

	public String getOperator_name() {
		return operator_name;
	}

	public void setOperator_name(String operator_name) {
		this.operator_name = operator_name;
	}

	public Date getSubmit_time() {
		return submit_time;
	}

	public void setSubmit_time(Date submit_time) {
		this.submit_time = submit_time;
	}

	public String getStatus_str() {
		if(status==null){
			status_str= "未知";
		}else if(status==0){
			status_str= "未提交";
		}else if(status==1){
			status_str= "已提交";
		}else if(status==2){
			status_str= "已对账";
		}else if(status==5){
			status_str= "结算";
		}else if(status==6){
			status_str= "不结算";
		}
		return status_str;
	}
}
