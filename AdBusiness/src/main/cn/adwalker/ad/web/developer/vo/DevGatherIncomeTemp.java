package cn.adwalker.ad.web.developer.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class DevGatherIncomeTemp implements Serializable {

	private static final long serialVersionUID = 8465237714859413876L;
	private String app_id;

	private String appName;

	/**
	 * 广告形式
	 */
	private String typeName;

	private String app_os;

	private String pay_type;

	private Integer pv;

	private Integer click;

	private Integer effect;

	private int pospv;

	public double getPrice() {
		double d = 0d;
		if (effect != null && cost != null) {
			d = cost.doubleValue() / effect;
		}
		return d;
	}

	/**
	 * 
	 * <p>
	 * Title: getClickRate
	 * </p>
	 * <p>
	 * Description:点击率
	 * </p>
	 * 
	 * @return
	 * @author cuidd
	 * @date 2013-2-7
	 * @return Double
	 * @version 1.0
	 * @throws
	 */
	public Double getClickRate() {
		double d = 0d;
		if (pv != null && click != null) {
			d = Double.valueOf(click) / pv;
		}
		d = d * 100;
		return d;
	}

	private BigDecimal cost;

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getPay_type() {
		return pay_type;
	}

	public void setPay_type(String pay_type) {
		this.pay_type = pay_type;
	}

	public String getApp_os() {
		return app_os;
	}

	public int getPospv() {
		return pospv;
	}

	public void setPospv(int pospv) {
		this.pospv = pospv;
	}

	public Integer getEffect() {
		return effect!=null?effect:0;
	}

	public void setEffect(Integer effect) {
		this.effect = effect;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public void setApp_os(String app_os) {
		this.app_os = app_os;
	}

	public Integer getPv() {
		return pv;
	}

	public void setPv(Integer pv) {
		this.pv = pv;
	}

	public Integer getClick() {
		return click != null ? click : 0;
	}

	public String getApp_id() {
		return app_id;
	}

	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}

	public void setClick(Integer click) {
		this.click = click;
	}

	public BigDecimal getCost() {
		return cost != null ? cost : BigDecimal.valueOf(0);
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}
}
