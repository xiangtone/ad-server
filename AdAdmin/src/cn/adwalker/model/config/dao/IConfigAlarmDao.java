package cn.adwalker.model.config.dao;

import java.util.List;
import java.util.Map;

import cn.adwalker.model.config.domain.ConfigPushDelay;
import cn.adwalker.model.config.domain.ConfigSDK;

/**
 * 
* <p>Title: IConfigAlarmDao</p>
* <p>Description:TODO</p>   
* <p>Company: adwalker</p>    
* @author    cuidd
* @date       2012-9-20
 */
public interface IConfigAlarmDao {

	
	/**
	* <p>Title: updateAlarmConfig</p>
	* <p>Description:修改参数配置的值</p>
	* @param list
	* @return
	* @throws Exception
	* @return int
	* @throws
	 */
	public int updateAlarmConfig(List<ConfigPushDelay> list) throws Exception;

	/**
	 * 
	* <p>Title: getAlarmConfig</p>
	* <p>Description:获取参数现有的值</p>
	* @return map
	* @throws Exception
	* @return Map<String,ConfigPushDelay>
	* @throws
	 */
	public Map<String, ConfigPushDelay> getAlarmConfig() throws Exception;

	/**
	* <p>Title: getConfig</p>
	* <p>Description:TODO</p>
	* @return
	* @author cuidd
	* @date 2013-8-26
	* @return ConfigSDK
	* @version 1.0
	 * @throws Exception 
	*/
	public ConfigSDK getConfig() throws Exception;

}
