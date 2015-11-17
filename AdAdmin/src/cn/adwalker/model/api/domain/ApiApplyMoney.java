package cn.adwalker.model.api.domain;

import java.util.Date;

import cn.adwalker.core.repository.Entity;

/**
 * 
 * <p>
 * Title: ApiLog
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-8-1
 */
public class ApiApplyMoney implements Entity {

	/** @Fields serialVersionUID : TODO */
	private static final long serialVersionUID = 522063683725734194L;
	private String apply_date;
	private Long dev_id;// 开发者id",
	private String applay_money;// :
	private Date create_time;// :

	public String getApply_date() {
		return apply_date;
	}

	public void setApply_date(String apply_date) {
		this.apply_date = apply_date;
	}

	public Long getDev_id() {
		return dev_id;
	}

	public void setDev_id(Long dev_id) {
		this.dev_id = dev_id;
	}

	public String getApplay_money() {
		return applay_money;
	}

	public void setApplay_money(String applay_money) {
		this.applay_money = applay_money;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
}
