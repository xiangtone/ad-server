package cn.adwalker.ad.rules.template.w;

import cn.adwalker.ad.rules.bean.ScoreConfigBean;
import cn.adwalker.ad.rules.template.SDKTemplate;

public class SDKWeixinTemplate extends SDKTemplate {
	
	@Override
	public int getAdScore(ScoreConfigBean bean) {
		int result =0;
		Double score=null;
		score = bean.getPrice()* bean.getExchange_rate_rmb()* bean.getScale();
		result = score.intValue();
		return result;
	}
}
