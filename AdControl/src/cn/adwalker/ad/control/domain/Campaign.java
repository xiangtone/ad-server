package cn.adwalker.ad.control.domain;

import java.util.Date;

public class Campaign {	
		private Long id;// 主键
		private String campaign_name;// 活动名称
		private Integer campaign_type;// 活动类型
		private Integer category_id;// 活动类别(对应app的应用分类)
		private Long adv_id;// 广告主id
		private String charge_type;// 计费方式
		private Double price;// 接入单价
		private Double budget;// 限制每日预算
		private Long salesman_id;
		private String campaign_required;// 限量要求
		private Date create_time;// 数据入库时间
		private Long create_user;// 操作人id
		private Integer area_directional;// 区域定向
		private Integer app_type;// 应用类型
		private Integer sdk_version;// sdk版本定向
		private Integer terminal_type;// 终端类型	
		private Integer time_directional;// 时段定向
		private Integer operat_agencies;
		private Integer phone_brand;
		private Integer balance_cycle;//结算周期		
		private Date last_balance_date;//上次结算日期
		private Integer max_package_code;
		private Integer confirm_mode;// 确认方式（0线下、1线上）

		/********************** getter\setter方法 ****************************/

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Integer getCampaign_type() {
			return campaign_type;
		}

		public void setCampaign_type(Integer campaign_type) {
			this.campaign_type = campaign_type;
		}

		public Integer getTime_directional() {
			return time_directional;
		}

		public void setTime_directional(Integer time_directional) {
			this.time_directional = time_directional;
		}

		public Integer getTerminal_type() {
			return terminal_type;
		}

		public Integer getOperat_agencies() {
			return operat_agencies;
		}

		public void setOperat_agencies(Integer operat_agencies) {
			this.operat_agencies = operat_agencies;
		}

		public Integer getPhone_brand() {
			return phone_brand;
		}

		public void setPhone_brand(Integer phone_brand) {
			this.phone_brand = phone_brand;
		}

		public void setTerminal_type(Integer terminal_type) {
			this.terminal_type = terminal_type;
		}

		public String getCharge_type() {
			return charge_type;
		}

		public void setCharge_type(String charge_type) {
			this.charge_type = charge_type;
		}

		public Double getBudget() {
			return budget;
		}

		public void setBudget(Double budget) {
			this.budget = budget;
		}

		public String getCampaign_required() {
			return campaign_required;
		}

		public void setCampaign_required(String campaign_required) {
			this.campaign_required = campaign_required;
		}

		public Integer getApp_type() {
			return app_type;
		}

		public void setApp_type(Integer app_type) {
			this.app_type = app_type;
		}

		public Double getPrice() {
			return price;
		}

		public void setPrice(Double price) {
			this.price = price;
		}

		public Integer getArea_directional() {
			return area_directional;
		}

		public void setArea_directional(Integer area_directional) {
			this.area_directional = area_directional;
		}

		public String getCampaign_name() {
			return campaign_name;
		}

		public void setCampaign_name(String campaign_name) {
			this.campaign_name = campaign_name;
		}

		public Long getAdv_id() {
			return adv_id;
		}

		public void setAdv_id(Long adv_id) {
			this.adv_id = adv_id;
		}

		public Integer getCategory_id() {
			return category_id;
		}

		public void setCategory_id(Integer category_id) {
			this.category_id = category_id;
		}

		public Long getCreate_user() {
			return create_user;
		}

		public void setCreate_user(Long create_user) {
			this.create_user = create_user;
		}

		public Date getCreate_time() {
			return create_time;
		}

		public void setCreate_time(Date create_time) {
			this.create_time = create_time;
		}

		public Integer getMax_package_code() {
			return max_package_code;
		}

		public void setMax_package_code(Integer max_package_code) {
			this.max_package_code = max_package_code;
		}

		public Integer getConfirm_mode() {
			return confirm_mode;
		}

		public void setConfirm_mode(Integer confirm_mode) {
			this.confirm_mode = confirm_mode;
		}

		public Integer getSdk_version() {
			return sdk_version;
		}

		public void setSdk_version(Integer sdk_version) {
			this.sdk_version = sdk_version;
		}

		/**
		 * @return salesman_id
		 */
		public Long getSalesman_id() {
			return salesman_id;
		}

		/**
		 * @param salesman_id the salesman_id to set
		 */
		
		public void setSalesman_id(Long salesman_id) {
			this.salesman_id = salesman_id;
		}

		/**
		 * @return balance_cycle
		 */
		public Integer getBalance_cycle() {
			return balance_cycle;
		}

		/**
		 * @param balance_cycle the balance_cycle to set
		 */
		
		public void setBalance_cycle(Integer balance_cycle) {
			this.balance_cycle = balance_cycle;
		}

		/**
		 * @return last_balance_date
		 */
		public Date getLast_balance_date() {
			return last_balance_date;
		}

		/**
		 * @param last_balance_date the last_balance_date to set
		 */
		
		public void setLast_balance_date(Date last_balance_date) {
			this.last_balance_date = last_balance_date;
		}
		
	}

