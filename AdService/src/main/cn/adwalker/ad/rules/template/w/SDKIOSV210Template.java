/**
* <p>Title: SDKIOSV120Template.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2013-5-21
* @version 1.0
*/
package cn.adwalker.ad.rules.template.w;


import cn.adwalker.ad.rules.bean.ScoreConfigBean;
import cn.adwalker.ad.rules.template.SDKTemplate;

/**
 * <p>Title: SDKIOSV120Template</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2013-5-21
 */
public class SDKIOSV210Template extends SDKTemplate {
	
	public SDKIOSV210Template(String sv){
		setSdkVersion(sv);
	}
	@Override
	public int getAdScore(ScoreConfigBean bean) {
		int result =0;
		Double score = bean.getPrice()* bean.getExchange_rate_rmb()* bean.getScale();	
		result = score.intValue();
		return result;
	}
}
