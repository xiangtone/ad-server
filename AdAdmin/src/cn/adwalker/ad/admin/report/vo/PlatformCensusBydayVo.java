package cn.adwalker.ad.admin.report.vo;

import java.math.BigDecimal;

/**
* <p>Title: PlatformCensusGeneralViewVo</p>
* <p>Description:平台总数据表</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-5-18
 */
public class PlatformCensusBydayVo {
	/** 主键ID */
	private Long id;
	/** 时间 */
	private String stat_date;
	/** PV */
	private  BigDecimal pv_amount;
	/** 点击 */
	private  BigDecimal click_amount;
	/** 下载 */
	private  BigDecimal down_amount;
	/** 平台确认数 */
	private  BigDecimal sum_platform_amount;
		
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getStat_date() {
		return stat_date;
	}
	public void setStat_date(String stat_date) {
		this.stat_date = stat_date;
	}
	
	public BigDecimal getPv_amount() {
		return pv_amount;
	}
	public void setPv_amount(BigDecimal pv_amount) {
		this.pv_amount = pv_amount;
	}
	public BigDecimal getClick_amount() {
		return click_amount;
	}
	public void setClick_amount(BigDecimal click_amount) {
		this.click_amount = click_amount;
	}
	public BigDecimal getDown_amount() {
		return down_amount;
	}
	public void setDown_amount(BigDecimal down_amount) {
		this.down_amount = down_amount;
	}
	public BigDecimal getSum_platform_amount() {
		return sum_platform_amount;
	}
	public void setSum_platform_amount(BigDecimal sum_platform_amount) {
		this.sum_platform_amount = sum_platform_amount;
	}	
}
