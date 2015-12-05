package cn.adwalker.model.config.dao;

import cn.adwalker.core.repository.IBaseDao;
import cn.adwalker.model.config.domain.CooperationConfig;
/**
 * <p>开发者接口对接配置DAO</p>
 * @author jief
 *
 */
public interface ICooperationConfigDao extends IBaseDao<CooperationConfig>{
	/**
	 * 查询
	 * @param id
	 * @return
	 */
	public CooperationConfig getConfigByAppId(Long id);
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	public boolean deleteByAppId(int id);
	/**
	 * 保存
	 * @param config
	 * @return
	 */
	public boolean saveConfig(CooperationConfig config);
	/**
	 * 更新
	 * @param config
	 * @return
	 */
	public boolean updateConfig(CooperationConfig config);
	
	
}
