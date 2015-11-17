/**
* <p>Title: Advertisement_IOS.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2012-12-21
* @version 1.0
*/
package cn.adwalker.IOSChannel.picker.bean;

import java.util.Date;
/**
 * <p>Title: Advertisement_IOS</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2012-12-21
 */
public class UserInfo  implements  java.io.Serializable{


	/** @Fields serialVersionUID : TODO*/
	private static final long serialVersionUID = 5942778177722469811L;
	private Long id;// 主键
	private String uuid;// 用户ID
	private String imei;// imei
	private String imsi;
	private String telNum;// 电话号码
	private String telModel;// 机型
	private String netEnv;// 网络环境
	private String areaCode;// 地域编号
	private String operator;// 运营商
	private String os;// 操作系统
	private String brand;// 厂商品牌编码
	private String screenWidth;// 屏幕宽
	private String screenHeigh;// 屏幕高
	private Integer pdaType;//终端类型：0、手机；1、pad   iphone  ipad
	private String area_province;// 省
	private String appIds;// 用户所下广告集合，例：123@456@	
	private Integer score;// 积分
	private Date createTime;// 创建时间
	private Long appId;//
	private String openUDID;
	private String idfa;
	private String jailbroken;
	private String idfv;
	public String dateTag;
	//1:终端类型(pad) 2:手机品牌 3:运营商 4:应用类型 5:黑名单 6:时间段 7:地区 
	//System.out.println("P:0,B:sunm,P:union,C:1002,H:15,A:北京".matches("P:.*,B:.*,P:.*,C:(1002|1003),H:(14|15),A:北京|福建"));
	public String matchInfo;
	
	public String getDateTag() {
		return dateTag;
	}

	public void setDateTag(String dateTag) {
		this.dateTag = dateTag;
	}


	public UserInfo() {
		this.score = 0;
		this.createTime = new Date();
	}

	public UserInfo(String uuid, String imei, String telNum, String telModel, String netEnv, String areaCode, String operator, String screenWidth,
			String screenHeigh, String os, String brand,String imsi,Long appId,int pdaType,String openUDID,String idfa,String jailbroken,String idfv) {
		this.uuid = uuid;
		this.imei = imei;
		this.telNum = telNum;
		this.telModel = telModel;
		this.netEnv = netEnv;
		this.areaCode = areaCode;
		this.operator = operator;
		this.os = os;
		this.brand = brand;
		this.screenWidth = screenWidth;
		this.screenHeigh = screenHeigh;
		this.score = 0;
		this.imsi = imsi;
		this.appId = appId;
		this.pdaType = pdaType;
		this.createTime = new Date();
		this.openUDID = openUDID;
		this.idfa = idfa;
		this.idfv = idfv;
		this.jailbroken = jailbroken;
	}

	
	public Integer getPdaType() {
		return pdaType;
	}

	public void setPdaType(Integer pdaType) {
		this.pdaType = pdaType;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getTelNum() {
		return telNum;
	}

	public void setTelNum(String telNum) {
		this.telNum = telNum;
	}

	public String getTelModel() {
		return telModel;
	}

	public void setTelModel(String telModel) {
		this.telModel = telModel;
	}

	public String getNetEnv() {
		return netEnv;
	}

	public void setNetEnv(String netEnv) {
		this.netEnv = netEnv;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	
	public String getScreenWidth() {
		return screenWidth;
	}

	public void setScreenWidth(String screenWidth) {
		this.screenWidth = screenWidth;
	}

	public String getScreenHeigh() {
		return screenHeigh;
	}

	public void setScreenHeigh(String screenHeigh) {
		this.screenHeigh = screenHeigh;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getAppIds() {
		return appIds;
	}

	public void setAppIds(String appIds) {
		this.appIds = appIds;
	}

	public String getImsi() {
		return imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}
	public Long getAppId() {
		return appId;
	}

	public void setAppId(Long appId) {
		this.appId = appId;
	}

	public String getArea_province() {
		return area_province;
	}

	public void setArea_province(String area_province) {
		this.area_province = area_province;
	}

	public String getOpenUDID() {
		return openUDID;
	}

	public void setOpenUDID(String openUDID) {
		this.openUDID = openUDID;
	}

	public String getIdfa() {
		return idfa;
	}

	public void setIdfa(String idfa) {
		this.idfa = idfa;
	}

	public String getJailbroken() {
		return jailbroken;
	}

	public void setJailbroken(String jailbroken) {
		this.jailbroken = jailbroken;
	}

	public String getIdfv() {
		return idfv;
	}

	public void setIdfv(String idfv) {
		this.idfv = idfv;
	}
	

}
