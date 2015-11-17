/**
 * 
 */
package cn.adwalker.ad.model.log.domain;

import java.util.Date;

/**
 * @author wjp
 * 管理员行为日志记录
 */
public class EScoreManagerLog {

	/** 主键编号 */
	private Long id;
	
	/** 操作者id */
	private Long userId;
	
	/** 操作这角色Id */
	private Long roleId;
	
	/** 目标用户类型 1：广告主，2：开发者*/
	private Integer target;
	
	/** 被操作用户id */
	private Long toUserId;
	
	/** 被操作用户应用id */
	private Long toUserAppId;
	
	/** 方法名称 （*.do）*/
	private String method;
	
	/** 参数列表 */
	private String params;
	
	/** 级别 */
	private Integer logLevel;
	
	/** 备注信息 */
	private String msg;
	
	/** 操作结果 1：成功，-1：失败 */
	private Integer flag;
	
	/** 原来的钱 */
	private Double formerMoney;
	
	/** 修改的钱 */
	private Double modifyMoney;
	
	/** 原来的积分 */
	private Double formerScore;
	
	/** 修改的积分 */
	private Double modifyScore;
	
	/** 原来对象的json */
	private String formerJson;
	
	/** 修改的json */
	private String modifyJson;
	
	/** 创建时间 */
	private Date createTime;

	/** 菜单 */
	private String menu; 
	
	private String role;// 角色
	
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * @return the roleId
	 */
	public Long getRoleId() {
		return roleId;
	}

	/**
	 * @param roleId the roleId to set
	 */
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	/**
	 * @return the target
	 */
	public Integer getTarget() {
		return target;
	}

	/**
	 * @param target the target to set
	 */
	public void setTarget(Integer target) {
		this.target = target;
	}

	/**
	 * @return the toUserId
	 */
	public Long getToUserId() {
		return toUserId;
	}

	/**
	 * @param toUserId the toUserId to set
	 */
	public void setToUserId(Long toUserId) {
		this.toUserId = toUserId;
	}

	/**
	 * @return the toUserAppId
	 */
	public Long getToUserAppId() {
		return toUserAppId;
	}

	/**
	 * @param toUserAppId the toUserAppId to set
	 */
	public void setToUserAppId(Long toUserAppId) {
		this.toUserAppId = toUserAppId;
	}

	/**
	 * @return the method
	 */
	public String getMethod() {
		return method;
	}

	/**
	 * @param method the method to set
	 */
	public void setMethod(String method) {
		this.method = method;
	}

	/**
	 * @return the params
	 */
	public String getParams() {
		return params;
	}

	/**
	 * @param params the params to set
	 */
	public void setParams(String params) {
		this.params = params;
	}


	/**
	 * @return the logLevel
	 */
	public Integer getLogLevel() {
		return logLevel;
	}

	/**
	 * @param logLevel the logLevel to set
	 */
	public void setLogLevel(Integer logLevel) {
		this.logLevel = logLevel;
	}

	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * @param msg the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * @return the flag
	 */
	public Integer getFlag() {
		return flag;
	}

	/**
	 * @param flag the flag to set
	 */
	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	/**
	 * @return the formerMoney
	 */
	public Double getFormerMoney() {
		return formerMoney;
	}

	/**
	 * @param formerMoney the formerMoney to set
	 */
	public void setFormerMoney(Double formerMoney) {
		this.formerMoney = formerMoney;
	}

	/**
	 * @return the modifyMoney
	 */
	public Double getModifyMoney() {
		return modifyMoney;
	}

	/**
	 * @param modifyMoney the modifyMoney to set
	 */
	public void setModifyMoney(Double modifyMoney) {
		this.modifyMoney = modifyMoney;
	}

	/**
	 * @return the formerScore
	 */
	public Double getFormerScore() {
		return formerScore;
	}

	/**
	 * @param formerScore the formerScore to set
	 */
	public void setFormerScore(Double formerScore) {
		this.formerScore = formerScore;
	}

	/**
	 * @return the modifyScore
	 */
	public Double getModifyScore() {
		return modifyScore;
	}

	/**
	 * @param modifyScore the modifyScore to set
	 */
	public void setModifyScore(Double modifyScore) {
		this.modifyScore = modifyScore;
	}

	/**
	 * @return the formerJson
	 */
	public String getFormerJson() {
		return formerJson;
	}

	/**
	 * @param formerJson the formerJson to set
	 */
	public void setFormerJson(String formerJson) {
		this.formerJson = formerJson;
	}

	/**
	 * @return the modifyJson
	 */
	public String getModifyJson() {
		return modifyJson;
	}

	/**
	 * @param modifyJson the modifyJson to set
	 */
	public void setModifyJson(String modifyJson) {
		this.modifyJson = modifyJson;
	}

	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the menu
	 */
	public String getMenu() {
		return menu;
	}

	/**
	 * @param menu the menu to set
	 */
	public void setMenu(String menu) {
		this.menu = menu;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
}
