package cn.adwalker.ad.bean;

/**
 * <p>增加积分流水bean</p>
 * @author jief
 *
 */
public class UserAddScore {
	private Long id;			//主键
	private String uuid;		//用户标示
	private Long ad_id;		//广告id
	private Long app_id;		//媒体id
	private Integer score;		//本次增加的积分
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
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
	public Long getAd_id() {
		return ad_id;
	}
	public void setAd_id(Long ad_id) {
		this.ad_id = ad_id;
	}
	public Long getApp_id() {
		return app_id;
	}
	public void setApp_id(Long app_id) {
		this.app_id = app_id;
	}
  
}
