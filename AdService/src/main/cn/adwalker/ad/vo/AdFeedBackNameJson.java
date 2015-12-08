package cn.adwalker.ad.vo;
import java.util.List;

import cn.adwalker.ad.bean.AdFeedBackJson;
import cn.adwalker.ad.bean.Data;


public class AdFeedBackNameJson extends Data {
	
	private static final long serialVersionUID = 8148999916544312788L;
	private List<AdFeedBackJson> adList;
	public List<AdFeedBackJson> getAdList() {
		return adList;
	}
	public void setAdList(List<AdFeedBackJson> adList) {
		this.adList = adList;
	}
	
	
}
