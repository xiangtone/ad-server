package cn.adwalker.ad.admin.channel.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.model.channel.dao.IChannelDao;
import cn.adwalker.ad.admin.channel.bean.ChannelRatingBean;
import cn.adwalker.ad.admin.channel.service.IChannelRatingService;
import cn.adwalker.ad.admin.channel.vo.ChannelRatingVo;

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
@Service("channelRatingService")
public class ChannelRatingServiceImpl implements IChannelRatingService {

	@Resource
	private IChannelDao channelDao;

	@SuppressWarnings("unchecked")
	@Override
	public List<ChannelRatingVo> findByPage(ChannelRatingBean bean,
			IPageInfo pageInfor) throws Exception {
		List<ChannelRatingVo> list = null;
		String columns = "a.ID channelId,a.NAME channelName,a.CHANNE_MODE os,a.SCALE scale";
		String tableAndWhere = "T_CHANNEL a where 1=1";
		if (bean != null) {
			if (bean.getChannelID() != null) {
				tableAndWhere += " and a.ID=" + bean.getChannelID();
			}
			if (!StringUtils.isEmpty(bean.getChannelName())) {
				tableAndWhere += " and upper(a.name) like'%"
						+ bean.getChannelName().trim().toUpperCase() + "%'";
			}

			if (!StringUtils.isEmpty(bean.getChannelRating())) {
				if (bean.getChannelRating().equals("A")) {
					tableAndWhere += " and a.SCALE between " + 1.31 + " and "
							+ 1.5;
				} else if (bean.getChannelRating().equals("B")) {
					tableAndWhere += " and a.SCALE between " + 1.01 + " and "
							+ 1.3;
				} else if (bean.getChannelRating().equals("C")) {
					tableAndWhere += " and a.SCALE between " + 0.71 + " and "
							+ 1.00;
				} else if (bean.getChannelRating().equals("D")) {
					tableAndWhere += " and a.SCALE between " + 0.41 + " and "
							+ 0.7;
				} else if (bean.getChannelRating().equals("E")) {
					tableAndWhere += " and a.SCALE between " + 0.00 + " and "
							+ 0.40;
				}
			}
			if (bean.getOs() != null && !bean.getOs().equals("")) {
				if (bean.getOs().equals("agent")) {
					tableAndWhere += " and a.CHANNE_MODE=2";
				} else if (bean.getOs().equals("sdk")) {
					tableAndWhere += " and a.CHANNE_MODE=0";
				} else if (bean.getOs().equals("api")) {
					tableAndWhere += " and a.CHANNE_MODE=1";
				}
			}
		}
		list = (List<ChannelRatingVo>) channelDao.findByPage(columns,
				tableAndWhere, ChannelRatingVo.class, pageInfor);

		return list;
	}

	@Override
	public void updateChannelRating(Long channelId, Double score) {
		channelDao.updateChannelRating(channelId, score);
	}
}
