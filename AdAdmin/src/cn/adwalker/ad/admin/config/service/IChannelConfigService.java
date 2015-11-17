package cn.adwalker.ad.admin.config.service;

import java.util.List;

import cn.adwalker.ad.admin.config.form.ChannelConfigSearchForm;
import cn.adwalker.ad.admin.config.vo.ChannelConfigVo;
import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.model.channel.domain.Channel;
import cn.adwalker.model.config.domain.ChannelConfig;
/**
 * 渠道配置服务
 * @author jief
 *
 */
public interface IChannelConfigService {
	/**
	 * 分页查询
	 * @param bean
	 * @param pageInfo
	 * @return
	 * @throws Exception
	 */
	public List<ChannelConfigVo> getList(ChannelConfigSearchForm bean, IPageInfo pageInfo) throws Exception;
	/**
	 * 保存或更新
	 * @param config
	 */
	public  String saveOrUpdate(ChannelConfig config);
	
	/**
	 * 根据channel删除渠道配置
	 * @param channel
	 */
	public String deleteByChannel(String channel);
	/**
	 * 
	 * @param channel
	 * @return
	 */
	public ChannelConfig getConfigByChannel(String channel);
	
	/**
	 * 
	* <p>Title: querySysUser</p>
	* <p>Description:TODO</p>
	* @return
	* @author cuidd
	* @date 2014-9-12
	* @return List<SysUserVo>
	* @version 1.0
	 * @param channel 
	 * @throws Exception 
	 */
	public List<Channel> queryChannelForSel(String channel) throws Exception;

}
