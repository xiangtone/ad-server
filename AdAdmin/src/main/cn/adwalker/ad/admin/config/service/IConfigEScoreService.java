/**
 * 
 */
package cn.adwalker.ad.admin.config.service;

import cn.adwalker.model.config.domain.ConfigEScore;
import cn.adwalker.model.config.domain.ConfigFinance;
import cn.adwalker.model.config.domain.ConfigPushDelay;
import cn.adwalker.model.config.domain.ConfigQuickLyTaskFinance;

/**
 * @author wjp
 * 管理员服务接口
 */
public interface IConfigEScoreService {
	
	/**
	 * 获取平台参数
	 * 
	 * @return
	 */
	public ConfigEScore getPlatformConfig();

	/**
	 * 获取财务参数
	 * 
	 * @param status
	 *            1：线上的 2：线下的
	 * @return
	 */
	public ConfigFinance getFinanceConfig(int status);

	
	/**
	 * 修改，根据id
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int updateById(ConfigEScore configEscore) throws Exception;
	
	/**
	 * 添加
	 * @param configEscore
	 * @return
	 * @throws Exception
	 */
	public long insert(ConfigEScore configEscore) throws Exception;
	
	/**
	 * 修改财务设置信息，根据id
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int updateById(ConfigFinance configFinance) throws Exception;

	public ConfigPushDelay getPushDelayConfigTax()throws Exception;
	
	public int updatePushDelayTax(ConfigPushDelay configPushDelay)throws Exception;
	/**
	* <p>Title: getQuickLyTask</p>
	* <p>Description:TODO</p>
	* @param status
	* @return
	* @author cuidd
	* @date 2014年10月31日
	* @return ConfigQuickLyTaskFinance
	* @version 1.0
	 */
	public ConfigQuickLyTaskFinance getQuickLyTask(int status);
	/**
	* <p>Title: updateQuickLyTask</p>
	* <p>Description:修改快速任务汇率</p>
	* @param qt
	* @throws Exception
	* @author cuidd
	* @date 2014年10月31日
	* @return void
	* @version 1.0
	 */
	public int updateQuickLyTask(ConfigQuickLyTaskFinance qt)throws Exception;
}
