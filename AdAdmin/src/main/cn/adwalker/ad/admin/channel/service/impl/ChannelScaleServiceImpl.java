package cn.adwalker.ad.admin.channel.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.model.channel.dao.IMediaScaleDao;
import cn.adwalker.ad.admin.channel.bean.ChannelScaleBean;
import cn.adwalker.ad.admin.channel.service.IChannelScaleService;
import cn.adwalker.ad.admin.channel.vo.ChannelScaleVo;

/**
 * 
 * <p>
 * Title: ChannelRatingServiceImpl
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-7-16
 */
@Service("channelScaleService")
public class ChannelScaleServiceImpl implements IChannelScaleService {

	@Resource
	private IMediaScaleDao mediaScaleDao;

	@SuppressWarnings("unchecked")
	@Override
	public List<ChannelScaleVo> findByPage(ChannelScaleBean bean,
			IPageInfo pageInfo) throws Exception {
		List<ChannelScaleVo> list = null;
		List<Object> param = new ArrayList<Object>();
		String columns = " t.id,t.os,t.media_id,t.SCALE,v.id campaign_id,v.campaign_name, media.name media_name ";
		StringBuilder sb = new StringBuilder(
				"T_MEDIA_SCALE  t  LEFT  JOIN V_CAMPAIGN v  on  t.placement_id=v.placement_id left join V_MEDIA  media on t.media_id=media.id  where 1=1");
		if (bean != null) {
			if (!StringUtils.isEmpty(bean.getChannel())) {
				sb.append(" and (t.media_id= ? or  upper(media.name) like ? )");
				param.add(bean.getChannel_id());
				param.add("%" + bean.getChannel_name().trim().toUpperCase() + "%");
			}

			if (!StringUtils.isEmpty(bean.getCampaign())) {
				sb.append(" and (v.id= ? or  upper(v.campaign_name) like ? )");
				param.add(bean.getCampaign_id());
				param.add("%" + bean.getCampaign_name() + "%");
			}
			if (!StringUtils.isEmpty(bean.getOs())) {
				sb.append(" and t.os=?");
				param.add(bean.getOs());

			}

		}
		list = (List<ChannelScaleVo>) mediaScaleDao.findByPage(columns,
				sb.toString(), param.toArray(), ChannelScaleVo.class, pageInfo);

		return list;
	}

	@Override
	public void updateChannelRating(Long channelId,Long campaign_id, Long media_id,  Double score) throws Exception {
		mediaScaleDao.updateChannelRating(channelId,campaign_id,media_id,  score);
	}
}
