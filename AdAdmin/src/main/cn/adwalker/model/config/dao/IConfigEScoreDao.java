/**
 * 
 */
package cn.adwalker.model.config.dao;

import cn.adwalker.core.repository.IBaseDao;
import cn.adwalker.model.config.domain.ConfigEScore;
import cn.adwalker.model.config.domain.ConfigFinance;
import cn.adwalker.model.config.domain.ConfigPushDelay;
import cn.adwalker.model.config.domain.ConfigQuickLyTaskFinance;

/**
 * @author wjp积分设置
 */
public interface IConfigEScoreDao extends IBaseDao<ConfigEScore> {

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
	 * 添加
	 * 
	 * @param configEScore
	 * @return
	 * @throws Exception
	 */
	public long insert(ConfigEScore configEScore) throws Exception;

	/**
	 * 修改
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int updateById(ConfigEScore configEScore) throws Exception;

	/**
	 * 修改财务设置信息
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int updateById(ConfigFinance configFinance) throws Exception;

	/**
	 * 根据id删除
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int deleteById(Long id) throws Exception;

	public ConfigPushDelay getPushDelayConfigTax() throws Exception;

	public int updatePushDelayTax(ConfigPushDelay configPushDelays);
	/**
	 * 
	* <p>Title: getQuickLyTask</p>
	* <p>Description:快速任务货币配置查询</p>
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
	* <p>Description:快速任务货币配置修改</p>
	* @param qt
	* @return
	* @throws Exception
	* @author cuidd
	* @date 2014年10月31日
	* @return Object
	* @version 1.0
	 */
	public int updateQuickLyTask(ConfigQuickLyTaskFinance qt)throws Exception;

}
