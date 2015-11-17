package cn.adwalker.IOSChannel.picker.bean;

public class AdAreaRate implements  java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer adId;
	private String area_code;
	private Integer rate;
	public AdAreaRate(){}
	public AdAreaRate(Integer id, Integer adId, String areaCode, Integer rate) {
		super();
		this.id = id;
		this.adId = adId;
		area_code = areaCode;
		this.rate = rate;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getAdId() {
		return adId;
	}
	public void setAdId(Integer adId) {
		this.adId = adId;
	}
	public String getArea_code() {
		return area_code;
	}
	public void setArea_code(String areaCode) {
		area_code = areaCode;
	}
	public Integer getRate() {
		return rate;
	}
	public void setRate(Integer rate) {
		this.rate = rate;
	}
	
	
	
	
   
}
