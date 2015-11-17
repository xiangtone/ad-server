package cn.adwalker.ad.logger;

import java.io.Serializable;


/***
 * 
* <p>Title: 积分消耗</p>
* <p>Description:TODO</p>
* <p>Company: adwalker</p> 
* @author    caiqiang
* @date       2013-1-7
 */
public class ScoreConsume implements Serializable {

	private static final long serialVersionUID = 3728723686805308529L;
	
	private String uuid;
	private Long developerId;
	private Long appId;
	private Integer score;
	private String channel;// 渠道
	
	private String areaCode;// 地域编号
	private String ip;// 地域编号
	
	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public Long getDeveloperId() {
		return developerId;
	}
	public void setDeveloperId(Long developerId) {
		this.developerId = developerId;
	}
	public Long getAppId() {
		return appId;
	}
	public void setAppId(Long appId) {
		this.appId = appId;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public void logClick() {
		String s[] = {uuid,String.valueOf(developerId), String.valueOf(appId), String.valueOf(score), channel,areaCode,ip};
		if (uuid != null && !"".equals(uuid)) {
			ScoreConsumeLogger logger = new ScoreConsumeLogger();
			logger.log(s);
		} 
	}


}
