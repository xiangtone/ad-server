package cn.adwalker.model.finance.domain;

import java.util.Date;

public class DevConsumeDetailChange {

	private int id;
	private String statDate;//业绩发生时间
	private Long adv_Id;//广告主ID
	private Long ad_Id;//广告ID
	private Integer valid_Amount_Old;//有效激活数(旧)
	private Integer valid_Amount_New;//有效激活数(新)
	private Long yunYing_Id;//数据导入人
	private Date createTime;//数据导入时间
	
	
	
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getStatDate() {
		return statDate;
	}



	public void setStatDate(String statDate) {
		this.statDate = statDate;
	}



	public Long getAdv_Id() {
		return adv_Id;
	}



	public void setAdv_Id(Long adv_Id) {
		this.adv_Id = adv_Id;
	}



	public Long getAd_Id() {
		return ad_Id;
	}



	public void setAd_Id(Long ad_Id) {
		this.ad_Id = ad_Id;
	}



	



	public Integer getValid_Amount_Old() {
		return valid_Amount_Old;
	}



	public void setValid_Amount_Old(Integer valid_Amount_Old) {
		this.valid_Amount_Old = valid_Amount_Old;
	}



	public Integer getValid_Amount_New() {
		return valid_Amount_New;
	}



	public void setValid_Amount_New(Integer valid_Amount_New) {
		this.valid_Amount_New = valid_Amount_New;
	}



	public Long getYunYing_Id() {
		return yunYing_Id;
	}



	public void setYunYing_Id(Long yunYing_Id) {
		this.yunYing_Id = yunYing_Id;
	}



	public Date getCreateTime() {
		return createTime;
	}



	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}



	public DevConsumeDetailChange() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
