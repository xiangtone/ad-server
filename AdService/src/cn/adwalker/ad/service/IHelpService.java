/**
* <p>Title: HelpService.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2013-5-15
* @version 1.0
*/
package cn.adwalker.ad.service;

import cn.adwalker.ad.picker.vo.UserFeedbackVo;


public interface IHelpService {

	/**
	* <p>Title: addUserFeedback</p>
	* <p>Description:TODO</p>
	* @param appId
	* @param uuid
	* @param version
	* @param userFeedback
	* @param emailAddr
	* @return void
	* @throws
	*/
	public int addUserFeedback(UserFeedbackVo userFeedbackVo);

}
