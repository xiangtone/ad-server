package cn.adwalker.ad.admin.operation.vo;

/**
 * 应用调量
* <p>Title: AppDeductionUp</p>
* <p>Description:TODO</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-3-20
 */
public class AppDeductionVo {

	/** 主键 */
	private Long id;
	private Long devID;
	/** 开发者名字 */
	private String devName;
	/** 开发者邮箱 */
	private String devMail;	
	/** 百分比 */
	private Double rate;
	/** 应用ID */
	private Long appId;

	/** 应用名称 */
	private String appName;
	/** 平台 */
	private String os;
	
	private String adForm;
	private String status;
	private Double  scale;
	
	
	public String getLevel() {
		String str = null;
		Integer i=getScale_int();
		if (i != null) {
			if (i > 130 && i <= 150) {
				str = "A";
			} else if (i > 100 && i <= 130) {
				str = "B";
			} else if (i > 70 && i <= 100) {
				str = "C";
			} else if (i > 40 && i <= 70) {
				str = "D";
			} else if (i >= 0 && i <= 40) {
				str = "E";
			}

		}
		return str;

	}
	
	
	public Integer getScale_int() {
		Double d = null;
		if (scale != null) {
			d = (Double) scale * 100;
		}
		return d != null ? d.intValue() : null;

	}
	
	

	public Double getScale() {
		return scale;
	}


	public void setScale(Double scale) {
		this.scale = scale;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDevName() {
		return devName;
	}

	public void setDevName(String devName) {
		this.devName = devName;
	}

	

	public String getDevMail() {
		return devMail;
	}

	public void setDevMail(String devMail) {
		this.devMail = devMail;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public Long getAppId() {
		return appId;
	}

	public void setAppId(Long appId) {
		this.appId = appId;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	

	public String getAdForm() {
		return adForm;
	}

	public void setAdForm(String adForm) {
		this.adForm = adForm;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public Long getDevID() {
		return devID;
	}

	public void setDevID(Long devID) {
		this.devID = devID;
	}
	
}
