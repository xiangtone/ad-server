package cn.adwalker.ad.web.developer.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import cn.adwalker.ad.util.StringUtil;

public class DevIncomeReportTemp implements Serializable {

	private static final long serialVersionUID = 8465237714859413876L;

	private int index;

	private String app_id;

	private String static_date;
	
	public String getKey(){
		if (StringUtil.isEmpty(app_id) || StringUtil.isEmpty(static_date)) {
			return null;
		}
		return app_id + "_" + static_date;
	}

	private String typeName;

	private String appName;

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

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getStatic_date() {
		return static_date;
	}

	public void setStatic_date(String static_date) {
		this.static_date = static_date;
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

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public BigDecimal getCost() {
		return cost != null ? cost : BigDecimal.valueOf(0);
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}
}