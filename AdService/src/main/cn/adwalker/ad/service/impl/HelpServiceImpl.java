/**
* <p>Title: HelpServiceImpl.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2013-5-15
* @version 1.0
*/
package cn.adwalker.ad.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.adwalker.ad.dao.IUserFeedbackDao;
import cn.adwalker.ad.dao.domain.UserFeedback;
import cn.adwalker.ad.picker.util.StringUtil;
import cn.adwalker.ad.picker.vo.UserFeedbackVo;
import cn.adwalker.ad.service.IHelpService;


@Service("helpService")
public class HelpServiceImpl implements IHelpService {

	@Resource
	private IUserFeedbackDao userFeedbackDao;
	
	/**  
	* <p>Title: addUserFeedback</p>
	* <p>Description:TODO</p>
	* @param appId
	* @param uuid
	* @param version
	* @param userFeedback
	* @param emailAddr
	* @see cn.adwalker.ad.service.IHelpService#addUserFeedback(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	*/
	@Override
	public int addUserFeedback(UserFeedbackVo fvo) {
		boolean isNull =StringUtil.isEmpty(fvo.type);
	    if (!isNull) {
	    	UserFeedback bean = new UserFeedback(fvo.getAdId()!=null?fvo.getAdId():0,fvo.getOpenudid(),fvo.getVersion(),fvo.getDesc(),fvo.getEmail(),fvo.getType());
			return userFeedbackDao.addData(bean);
	    } else {
	    	return 0;
	    }
		
	}

}
