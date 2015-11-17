package cn.adwalker.model.config.dao;

import cn.adwalker.core.repository.IBaseDao;
import cn.adwalker.model.config.domain.ChannelConfig;


public interface IChannelConfigDao  extends IBaseDao<ChannelConfig>{
	/**
	 * 保存
	 * @param config
	 */
	public void save(ChannelConfig config);
	/**
	 * 更新
	 * @param config
	 */
	public void update(ChannelConfig config);
	/**
	 * <p>删除</>
	 * @param id
	 */
	public void delById(String id);
	/**
	 * <p>根据id获取渠道配置</p>
	 * @param id
	 * @return
	 */
	public ChannelConfig getConfigByid(String id);
}
