package cn.adwalker.ad.admin.operation.bean;

public class MediaRatingBean {
	private Long devID;
	private String devMail;
	private Long mediaID;
	private String mediaName;
	private String mediaRating;
	private String os;
	private Integer orderColumn;
	private Integer orderCondition;
	private Long type_id;

	private Integer app_res;
	private Long app_manager_id;

	public Integer getOrderColumn() {
		return orderColumn;
	}

	public void setOrderColumn(Integer orderColumn) {
		this.orderColumn = orderColumn;
	}

	public Integer getOrderCondition() {
		return orderCondition;
	}

	public void setOrderCondition(Integer orderCondition) {
		this.orderCondition = orderCondition;
	}

	public Long getDevID() {
		return devID;
	}

	public void setDevID(Long devID) {
		this.devID = devID;
	}

	public String getDevMail() {
		return devMail;
	}

	public void setDevMail(String devMail) {
		this.devMail = devMail;
	}

	public Long getMediaID() {
		return mediaID;
	}

	public void setMediaID(Long mediaID) {
		this.mediaID = mediaID;
	}

	public String getMediaName() {
		return mediaName;
	}

	public void setMediaName(String mediaName) {
		this.mediaName = mediaName;
	}

	public String getMediaRating() {
		return mediaRating;
	}

	public void setMediaRating(String mediaRating) {
		this.mediaRating = mediaRating;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public Long getType_id() {
		return type_id;
	}

	public void setType_id(Long type_id) {
		this.type_id = type_id;
	}

	public Integer getApp_res() {
		return app_res;
	}

	public void setApp_res(Integer app_res) {
		this.app_res = app_res;
	}

	public Long getApp_manager_id() {
		return app_manager_id;
	}

	public void setApp_manager_id(Long app_manager_id) {
		this.app_manager_id = app_manager_id;
	}
}
