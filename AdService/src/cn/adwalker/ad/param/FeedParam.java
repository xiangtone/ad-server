package cn.adwalker.ad.param;

import cn.adwalker.ad.picker.vo.ParamVo;


public class FeedParam extends ParamVo {
	private Long appId;
	private String page_type;//广告的分类(区分 墙 插屏  banner)

	public String getPage_type() {
		return page_type;
	}
	public void setPage_type(String pageType) {
		page_type = pageType;
	}
	public Long getAppId() {
		return appId;
	}
	public void setAppId(Long appId) {
		this.appId = appId;
	}
}
