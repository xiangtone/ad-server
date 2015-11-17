package cn.adwalker.ad.vo;

import cn.adwalker.ad.bean.Data;
import cn.adwalker.ad.cache.element.AdDetailVo;

/**
 * 
* <p>Title: AdDetail</p>
* <p>Description:广告详情缓存</p>
* <p>Company: adwalker</p> 
* @author    www.adwalker.cn
* @date       2013-1-29
 */
public class AdDetailJson extends Data {
	
	/** @Fields serialVersionUID : TODO*/
	private static final long serialVersionUID = 1821736728938364085L;
	private AdDetailVo AdDetail;

	public AdDetailVo getAdDetail() {
		return AdDetail;
	}

	public void setAdDetail(AdDetailVo AdDetail) {
		this.AdDetail = AdDetail;
	}
}
