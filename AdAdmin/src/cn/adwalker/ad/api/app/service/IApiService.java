package cn.adwalker.ad.api.app.service;

import java.io.IOException;
import java.util.Date;

import cn.adwalker.ad.api.app.form.App;
import cn.adwalker.ad.api.app.form.Dev;
import cn.adwalker.ad.api.app.form.DevAccount;

/**
 * 
 * <p>
 * Title: IApiService
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-4-9
 */
public interface IApiService {

	/**
	 * <p>
	 * Title: saveOrUpdate
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param dev
	 * @author cuidd
	 * @date 2013-7-30
	 * @return void
	 * @version 1.0
	 * @throws Exception
	 */

	void saveOrUpdateDev(Dev dev) throws Exception;

	/**
	 * <p>
	 * Title: saveOrUpdateApp
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param app
	 * @author cuidd
	 * @date 2013-8-1
	 * @return void
	 * @version 1.0
	 * @throws Exception
	 * @throws NumberFormatException
	 */
	void saveOrUpdateApp(App app) throws NumberFormatException, Exception;

	/**
	 * 
	 * <p>
	 * Title: exportCsv
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @throws NumberFormatException
	 * @throws Exception
	 * @author cuidd
	 * @date 2013-8-2
	 * @return void
	 * @version 1.0
	 */
	void exportCsv() throws NumberFormatException, Exception;

	/**
	 * <p>
	 * Title: exportCsvByDay
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @author cuidd
	 * @date 2013-8-2
	 * @return void
	 * @version 1.0
	 * @throws IOException
	 * @throws Exception
	 */
	void exportCsvByDay(Date date) throws IOException, Exception;
	
	/**
	 * <p>
	 * Title: sendApp
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @author cuidd
	 * @date 2013-8-12
	 * @return void
	 * @version 1.0
	 * @throws IOException
	 */
	void sendApp(Long app_id, String aduit, Integer status, Double scale)
			throws IOException;

	/**
	 * <p>
	 * Title: saveApplyMoney
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param applyMoney
	 * @author cuidd
	 * @date 2013-9-5
	 * @return void
	 * @version 1.0
	 * @throws Exception
	 */
	void saveApplyMoney(DevAccount applyMoney) throws Exception;
}
