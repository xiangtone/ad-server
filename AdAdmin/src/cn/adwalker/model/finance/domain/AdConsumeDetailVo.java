package cn.adwalker.model.finance.domain;

import java.util.Date;
/**
 * 
* <p>Title: AdConsumeDetailVo</p>
* <p>Description:TODO</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-1-16
 */
public class AdConsumeDetailVo {

	private int id;
	private String statDate;//效果发生时间
	private Long adv_Id;//广告主ID
	private Long ad_Id;//广告ID
	private Integer valid_Amount;//有效激活数
	private String effect_Type;//计费类型
	private Double price;//单价
	private Long yunYing_DR_Id;//数据导入人
	private Long yunYing_SH_Id;//运营审核人
	private Date achievement_Time;//业绩提交时间：运营审核时间
	private Integer yunYing_Status;//运营审核状态
	private Date createTime;//创建时间
	
	private String email;//广告主名称
	private String ad_Name;//广告名称
	private String achievement_Name;//业绩提交人
	
	private String createTime_String;//常见时间 String类型
	private double total_valid_Amount;//总的有效激活数 
	private Double one_SumMoney;//一条的总金额
	
	
	
	public Date getAchievement_Time() {
		return achievement_Time;
	}
	public void setAchievement_Time(Date achievement_Time) {
		this.achievement_Time = achievement_Time;
	}
	public String getAchievement_Name() {
		return achievement_Name;
	}
	public void setAchievement_Name(String achievement_Name) {
		this.achievement_Name = achievement_Name;
	}
	public Double getOne_SumMoney() {
		return one_SumMoney;
	}
	public void setOne_SumMoney(Double one_SumMoney) {
		this.one_SumMoney = one_SumMoney;
	}
	public String getCreateTime_String() {
		return createTime_String;
	}
	public void setCreateTime_String(String createTime_String) {
		this.createTime_String = createTime_String;
	}
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAd_Name() {
		return ad_Name;
	}
	public void setAd_Name(String ad_Name) {
		this.ad_Name = ad_Name;
	}
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
	
	public Integer getValid_Amount() {
		return valid_Amount;
	}
	public void setValid_Amount(Integer valid_Amount) {
		this.valid_Amount = valid_Amount;
	}
	public String getEffect_Type() {
		return effect_Type;
	}
	public void setEffect_Type(String effect_Type) {
		this.effect_Type = effect_Type;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Long getYunYing_DR_Id() {
		return yunYing_DR_Id;
	}
	public void setYunYing_DR_Id(Long yunYing_DR_Id) {
		this.yunYing_DR_Id = yunYing_DR_Id;
	}
	public Long getYunYing_SH_Id() {
		return yunYing_SH_Id;
	}
	public void setYunYing_SH_Id(Long yunYing_SH_Id) {
		this.yunYing_SH_Id = yunYing_SH_Id;
	}

	public Integer getYunYing_Status() {
		return yunYing_Status;
	}
	public void setYunYing_Status(Integer yunYing_Status) {
		this.yunYing_Status = yunYing_Status;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public double getTotal_valid_Amount() {
		return total_valid_Amount;
	}
	public void setTotal_valid_Amount(double total_valid_Amount) {
		this.total_valid_Amount = total_valid_Amount;
	}
	public AdConsumeDetailVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
