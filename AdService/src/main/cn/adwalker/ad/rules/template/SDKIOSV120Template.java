/**
* <p>Title: SDKIOSV120Template.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2013-5-21
* @version 1.0
*/
package cn.adwalker.ad.rules.template;
import cn.adwalker.ad.picker.util.StringUtil;
import cn.adwalker.ad.rules.bean.ScoreConfigBean;

/**
 * <p>Title: SDKIOSV120Template</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2013-5-21
 */
public class SDKIOSV120Template extends SDKTemplate {
	
	public SDKIOSV120Template(String sv){
		setSdkVersion(sv);
	}
	@Override
	public int getAdScore(ScoreConfigBean bean) {
		int result =0;
		Double score=null;
		if (!StringUtil.isEmpty(bean.getQuickly_task())&&bean.getQuickly_task().equals("1")) {
			 score = bean.getPrice()* bean.getQuickly_task_rate()* bean.getScale();
		}else {
			 score = bean.getPrice()* bean.getExchange_rate_rmb()* bean.getScale();
		}
		
		result = score.intValue();
		return result;
	}
}
