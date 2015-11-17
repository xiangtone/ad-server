/**
 * <p>Title: AdBudgetLog.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author kingdom
 * @date 2013-9-2
 * @version 1.0
 */
package cn.adwalker.ad.admin.mail.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * Title: AdBudgetLog
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-9-2
 */
public class AdBudgetMail implements Serializable {
	private static final long serialVersionUID = 8458353803636397050L;
	private Long id;
	private String name;
	private Integer budget_day;
	private Integer cost_day;
	private String budget_type;

	public Date getOff_line_time() {
		return new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getBudget_day() {
		return budget_day;
	}

	public void setBudget_day(Integer budget_day) {
		this.budget_day = budget_day;
	}

	public Integer getCost_day() {
		return cost_day;
	}

	public void setCost_day(Integer cost_day) {
		this.cost_day = cost_day;
	}

	public String getBudget_type() {
		return budget_type;
	}

	public void setBudget_type(String budget_type) {
		this.budget_type = budget_type;
	}
}