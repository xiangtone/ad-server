package cn.adwalker.model.channel.domain;

public class Cooperation {
	private String cp_name; // 合作企业名称
	private String cp_id; // 合作企业标识
	private String cp_action_table; // 从合作企业产品中产生的操作表
	private String cp_effect_table; // 从合作企业产品中产生的效果审核表
	private String cp_info1_table; // 对应统计信息1的表
	private String cp_info2_table; // 对应统计信息2的表
	private Double assign; // 分配比例

	public String getCp_name() {
		return cp_name;
	}

	public void setCp_name(String cp_name) {
		this.cp_name = cp_name;
	}

	public String getCp_id() {
		return cp_id;
	}

	public void setCp_id(String cp_id) {
		this.cp_id = cp_id;
	}

	public String getCp_action_table() {
		return cp_action_table;
	}

	public void setCp_action_table(String cp_action_table) {
		this.cp_action_table = cp_action_table;
	}

	public String getCp_effect_table() {
		return cp_effect_table;
	}

	public void setCp_effect_table(String cp_effect_table) {
		this.cp_effect_table = cp_effect_table;
	}

	public String getCp_info1_table() {
		return cp_info1_table;
	}

	public void setCp_info1_table(String cp_info1_table) {
		this.cp_info1_table = cp_info1_table;
	}

	public String getCp_info2_table() {
		return cp_info2_table;
	}

	public void setCp_info2_table(String cp_info2_table) {
		this.cp_info2_table = cp_info2_table;
	}

	public Double getAssign() {
		return assign;
	}

	public void setAssign(Double assign) {
		this.assign = assign;
	}
}
