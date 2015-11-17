package cn.adwalker.ad.control.service;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import cn.adwalker.ad.control.dao.OperationConfirmIncomeDao;
import cn.adwalker.ad.control.util.ConfigUtil;
import cn.adwalker.ad.control.util.HttpClientUtils;

@Repository("adIosClickService")
public class AdIosClickService {
	private static final Logger log = LoggerFactory.getLogger(AdIosClickService.class);
	@Resource
	private OperationConfirmIncomeDao confirmIncomeDao;

	/**
	* <p>Title: tranceDataIosClick</p>
	* <p>Description:TODO</p>
	* @throws Exception
	* @author cuidd
	* @date 2014年6月18日
	* @return void
	* @version 1.0
	 */
	public void tranceDataIosClick() throws Exception {
		log.info("开始----tranceDataIosClick");
		int num = confirmIncomeDao.countQuery(new Object[] { 0 });
		if (num > 0) {
			confirmIncomeDao.updateIos(new Object[] { 0 });
			String url = ConfigUtil.getString("ios.below.date");
			HttpClientUtils.httpRequestPost(url, "status=2");
		}
		log.info("结束----tranceDataIosClick");
	}

	

}
