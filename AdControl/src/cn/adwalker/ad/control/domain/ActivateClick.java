package cn.adwalker.ad.control.domain;

import java.io.Serializable;

public class ActivateClick implements Serializable {

	private static final long serialVersionUID = 5320508541573211721L;

	private Integer activate;

	private Integer click;
	
	private Double price;
	

	public Integer getActivate() {
		return activate;
	}

	public void setActivate(Integer activate) {
		this.activate = activate;
	}

	public Integer getClick() {
		return click;
	}

	public void setClick(Integer click) {
		this.click = click;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
}

