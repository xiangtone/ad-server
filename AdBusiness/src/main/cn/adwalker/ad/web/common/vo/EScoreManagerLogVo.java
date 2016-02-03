/**
 * 
 */
package cn.adwalker.ad.web.common.vo;

import java.util.Date;

/**
 * @author wjp
 * 管理员行为日志记录
 */
public class EScoreManagerLogVo {

	/** 主键编号 */
	private Long id;
	
	/** 操作者id */
	private Long userId;
	
	/** 操作这角色Id */
	private Long roleId;
	
	/** 目标用户类型 1：广告主，2：开发者*/
	private int target;
	
	/** 被操作用户id */
	private Long toUserId;
	
	/** 被操作用户应用id */
	private Long toUserAppId;
	
	/** 方法名称 （*.do）*/
	private String method;
	
	/** 参数列表 */
	private String params;
	
	/** 级别 */
	private int logLevel;
	
	/** 备注信息 */
	private String msg;
	
	/** 操作结果 1：成功，-1：失败 */
	private int flag;
	
	/** 原来的钱 */
	private double formerMoney;
	
	/** 修改的钱 */
	private double modifyMoney;
	
	/** 原来的积分 */
	private double formerScore;
	
	/** 修改的积分 */
	private double modifyScore;
	
	/** 原来对象的json */
	private String formerJson;
	
	/** 修改的json */
	private String modifyJson;
	
	/** 创建时间 */
	private Date createTime;

	/** 用户名 */
	private String userName;
	
	/** 角色名 */
	private String roleName;
	
	/** 菜单 */
	private String menu; 
	
	
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
	public int getTarget() {
		return target;
	}

	/**
	 * @param target the target to set
	 */
	public void setTarget(int target) {
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
	public int getLogLevel() {
		return logLevel;
	}

	/**
	 * @param logLevel the logLevel to set
	 */
	public void setLogLevel(int logLevel) {
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
	public int getFlag() {
		return flag;
	}

	/**
	 * @param flag the flag to set
	 */
	public void setFlag(int flag) {
		this.flag = flag;
	}

	/**
	 * @return the formerMoney
	 */
	public double getFormerMoney() {
		return formerMoney;
	}

	/**
	 * @param formerMoney the formerMoney to set
	 */
	public void setFormerMoney(double formerMoney) {
		this.formerMoney = formerMoney;
	}

	/**
	 * @return the modifyMoney
	 */
	public double getModifyMoney() {
		return modifyMoney;
	}

	/**
	 * @param modifyMoney the modifyMoney to set
	 */
	public void setModifyMoney(double modifyMoney) {
		this.modifyMoney = modifyMoney;
	}

	/**
	 * @return the formerScore
	 */
	public double getFormerScore() {
		return formerScore;
	}

	/**
	 * @param formerScore the formerScore to set
	 */
	public void setFormerScore(double formerScore) {
		this.formerScore = formerScore;
	}

	/**
	 * @return the modifyScore
	 */
	public double getModifyScore() {
		return modifyScore;
	}

	/**
	 * @param modifyScore the modifyScore to set
	 */
	public void setModifyScore(double modifyScore) {
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}
	
}
