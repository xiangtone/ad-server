/**
 * <p>Title: AppQuery.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author kingdom
 * @date 2013-1-25
 * @version 1.0
 */
package cn.adwalker.ad.admin.operation.bean;


/**
 * <p>
 * Title: AppQuery
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-1-25
 */
public class AppDeductionBean {

	private Long devID;
	private String devName;
	private Long mediaID;
	private String mediaName;
	private String os;
	private Long type_id;
	private String mediaRating;
	
	private Integer app_res;
	private Long app_manager_id;
	
	private String devMail;

	public Long getDevID() {
		return devID;
	}

	public void setDevID(Long devID) {
		this.devID = devID;
	}

	public String getDevName() {
		return devName;
	}

	public void setDevName(String devName) {
		this.devName = devName;
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

	public String getDevMail() {
		return devMail;
	}

	public void setDevMail(String devMail) {
		this.devMail = devMail;
	}

	public String getMediaRating() {
		return mediaRating;
	}

	public void setMediaRating(String mediaRating) {
		this.mediaRating = mediaRating;
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
