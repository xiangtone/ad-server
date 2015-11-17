package cn.adwalker.ad.param;

import cn.adwalker.ad.picker.vo.ParamVo;

/**
 * 
* <p>Title: AddScoreParam</p>
* <p>Description:增加积分接口参数</p>
* <p>Company: adwalker</p> 
* @author    cuidd
* @date       2014年11月19日
 */
public class AddScoreParam extends ParamVo {
	private Long appId;
	private Long adId;
	private String uuid;
	private String deveiceId;
	private String channel;//渠道
	private String pageType;
	private String page_type;//广告的分类(区分 墙 插屏  banner)
	private String idfa;
	private String id;
	private String imsi;
	private String devUserId;
	private String os;
	
	public String getPageType() {
		return pageType;
	}
	
	public Long getAdId() {
		return adId;
	}
	
	public String getImsi() {
		return imsi;
	}

	public String getIdfa() {
		return idfa;
	}
	
	public String getId() {
		return id;
	}
	
	public String getDevUserId() {
		return devUserId;
	}

	/***/
	public Long getAppId() {
		return appId;
	}
	public void setAppId(Long appId) {
		this.appId = appId;
	}
	public String getUuid() {
		return uuid;
	}

	public String getChannel() {
		return channel;
	}
	
	public String getPage_type() {
		return page_type;
	}

	public String getDeveiceId() {
		return deveiceId;
	}
	public void setDeveiceId(String deveiceId) {
		this.deveiceId = deveiceId;
	}

	
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}

	public void setidfa(String yjfIdfa) {
		idfa = yjfIdfa;
	}
	
	public void setPageType(String pageType) {
		this.pageType = pageType;
	}
	
	
	public void setAdId(Long adId) {
		this.adId = adId;
	}
	
	public void setImsi(String imsi) {
		this.imsi = imsi;
	}
	
	public void setVersion(String version) {
		this.version = version;
	}
	
	public void setIdfa(String idfa) {
		this.idfa = idfa;
	}
	
	public void setDevUserId(String devUserId) {
		this.devUserId = devUserId;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	public void setPage_type(String pageType) {
		page_type = pageType;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
}
