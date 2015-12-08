/**
* <p>Title: IAdWallService.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2013-5-27
* @version 1.0
*/
package cn.adwalker.ad.service;

import cn.adwalker.ad.param.FeedParam;
import cn.adwalker.ad.vo.AdFeedBackNameJson;


/**
 * 
* <p>Title: IAdFeedbackService</p>
* <p>Description:广告反馈？？</p>
* <p>Company: adwalker</p> 
* @author    cuidd
* @date       2014年10月8日
 */
public interface IAdFeedbackService {

	
	public AdFeedBackNameJson adBackFeedPicker(FeedParam vo,String os);

	
}
