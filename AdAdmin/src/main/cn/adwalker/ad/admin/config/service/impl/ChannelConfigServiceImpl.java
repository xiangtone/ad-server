package cn.adwalker.ad.admin.config.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import cn.adwalker.ad.admin.config.form.ChannelConfigSearchForm;
import cn.adwalker.ad.admin.config.service.IChannelConfigService;
import cn.adwalker.ad.admin.config.vo.ChannelConfigVo;
import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.util.lang.ObjectUtils;
import cn.adwalker.model.channel.dao.IChannelDao;
import cn.adwalker.model.channel.domain.Channel;
import cn.adwalker.model.config.dao.IChannelConfigDao;
import cn.adwalker.model.config.domain.ChannelConfig;

@Service("channelConfigService")
public class ChannelConfigServiceImpl implements IChannelConfigService {
	@Resource
	IChannelConfigDao channelConfigDao;

	@Resource
	IChannelDao channelDao;

	@SuppressWarnings("unchecked")
	@Override
	public List<ChannelConfigVo> getList(ChannelConfigSearchForm bean,
			IPageInfo pageInfo) throws Exception {
		List<Object> list = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer(" t_channel_config cfg left join t_channel c on cfg.channel_id=c.id where 1=1 ");
		if (ObjectUtils.isNotEmpty(bean.getChannel())) {
			sb.append(" and cfg.channel='");
			sb.append(bean.getChannel());
			sb.append("'");
		}
		if (ObjectUtils.isNotEmpty(bean.getChannel_name())) {
			sb.append(" and cfg.channel_name like '%");
			sb.append(bean.getChannel_name());
			sb.append("%'");
		}
		return (List<ChannelConfigVo>) channelConfigDao.findByPage("cfg.*,c.email",
				sb.toString(), list.toArray(), " order by cfg.channel desc ",
				ChannelConfigVo.class, pageInfo);
	}

	@Override
	public String saveOrUpdate(ChannelConfig config) {
		String result = "{status:1}";
		if (config != null && StringUtils.isNotBlank(config.getChannel())) {
			if (config.getPass_rate() != null && config.getPass_rate() > 1.0f) {
				result = "{status:-1,error:'回调比率保存失败不能大于1.0'}";
				return result;
			}
			ChannelConfig c = this.channelConfigDao.getConfigByid(config
					.getChannel());
			if (c != null) {
				this.channelConfigDao.update(config);
			} else {
				this.channelConfigDao.save(config);
			}
		} else {
			result = "{status:-1,error:'保存失败'}";
		}
		return result;
	}

	@Override
	public String deleteByChannel(String channel) {
		String result = "{status:1}";
		this.channelConfigDao.delById(channel);
		return result;
	}

	@Override
	public ChannelConfig getConfigByChannel(String channel) {
		return this.channelConfigDao.getConfigByid(channel);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Channel> queryChannelForSel(String channel) throws Exception {
		List<Channel> list = null;
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT *   from  t_channel t where t.os='ios' and not  exists(select  1 from t_channel_config c where c.channel_id=t.id "
				+ (!StringUtils.isEmpty(channel) ? ("and channel!='" + channel + "'")
						: "") + ") and status=1");//
		list = (List<Channel>) channelDao.findAll(sb.toString(), Channel.class);

		return list;
	}

}
