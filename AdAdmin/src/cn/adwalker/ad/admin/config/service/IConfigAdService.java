package cn.adwalker.ad.admin.config.service;

import java.util.List;
import java.util.Map;

import cn.adwalker.model.config.domain.ConfigPushDelay;

/**
 * 
 * <p>
 * Title: IConfigAdService
 * </p>
 * <p>
 * Description:预警设置服务接口
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2012-9-20
 */
public interface IConfigAdService {


	/**
	 * 
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
	* @return
	* @throws Exception
	* @return Map<String,ConfigPushDelay>
	* @throws
	 */
	public Map<String, ConfigPushDelay> getAlarmConfig()
			throws Exception;

}
