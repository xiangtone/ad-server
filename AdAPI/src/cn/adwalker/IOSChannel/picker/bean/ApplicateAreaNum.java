package cn.adwalker.IOSChannel.picker.bean;

public class ApplicateAreaNum implements  java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer app_id;
	private String area_code;
	private Integer num;
	public ApplicateAreaNum(){}
	public ApplicateAreaNum(Integer id, Integer appId, String areaCode,
			Integer num) {
		super();
		this.id = id;
		app_id = appId;
		area_code = areaCode;
		this.num = num;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getApp_id() {
		return app_id;
	}
	public void setApp_id(Integer appId) {
		app_id = appId;
	}
	public String getArea_code() {
		return area_code;
	}
	public void setArea_code(String areaCode) {
		area_code = areaCode;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	
	
	
	
   
}
