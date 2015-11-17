package cn.adwalker.ad.admin.report.vo;

import java.math.BigDecimal;

/**
 * <p>
 * Title: CensusGeneralViewAdvVo
 * </p>
 * <p>
 * Description:统计概览app
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-5-18
 */
public class CensusGeneralViewAppVo {
	/** 主键ID */
	private Long id;
	/** 广告主 */
	private String app_name;
	/** cpc */
	private BigDecimal cpc_cost;
	/** cpm */
	private BigDecimal cpm_cost;
	/** cpd */
	private BigDecimal cpd_cost;
	/** cpa */
	private BigDecimal cpa_cost;
	/** 总收入 */
	private double cost;
	/** 收入sum */
	private double sum_cost;
	/** 占比 */
	private double proportion;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getApp_name() {
		return app_name;
	}

	public void setApp_name(String app_name) {
		this.app_name = app_name;
	}

	public BigDecimal getCpc_cost() {
		return cpc_cost;
	}

	public void setCpc_cost(BigDecimal cpc_cost) {
		this.cpc_cost = cpc_cost;
	}

	public BigDecimal getCpm_cost() {
		return cpm_cost;
	}

	public void setCpm_cost(BigDecimal cpm_cost) {
		this.cpm_cost = cpm_cost;
	}

	public BigDecimal getCpd_cost() {
		return cpd_cost;
	}

	public void setCpd_cost(BigDecimal cpd_cost) {
		this.cpd_cost = cpd_cost;
	}

	public BigDecimal getCpa_cost() {
		return cpa_cost;
	}

	public void setCpa_cost(BigDecimal cpa_cost) {
		this.cpa_cost = cpa_cost;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public double getSum_cost() {
		return sum_cost;
	}

	public void setSum_cost(double sum_cost) {
		this.sum_cost = sum_cost;
	}

	public double getProportion() {
		if(sum_cost!=0){
			proportion = (cost / sum_cost)*100;
		}
		return proportion;
	}

	public void setProportion(double proportion) {
		this.proportion = proportion;
	}

}
