package cn.adwalker.ad.api.app.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.adwalker.ad.api.app.service.IApiLogService;
import cn.adwalker.model.api.dao.IApiLogDao;
import cn.adwalker.model.api.domain.ApiLog;

/**
 * 
 * <p>
 * Title: RegistAdvServiceImpl
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-3-19
 */
@Service
public class ApiLogServiceImpl implements IApiLogService {

	@Resource
	private IApiLogDao dao;

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: log
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param res_data
	 * @throws Exception 
	 * @see cn.adwalker.ad.api.app.service.IApiLogService#log(java.lang.String)
	 */
	@Override
	public void log(String res_data) throws Exception {
		ApiLog entity = new ApiLog(res_data, 0);
		dao.insert(entity);

	}

}
