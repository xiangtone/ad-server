package cn.adwalker.ad.admin.channel.service;

import java.util.List;

import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.ad.admin.channel.bean.ChannelRatingBean;
import cn.adwalker.ad.admin.channel.vo.ChannelRatingVo;
/**
 * 
 * @author zouguibao
 * @version 1.0
 * @since 2013-5-14
 */
public interface IChannelRatingService {
	/**
	* <p>Title: findByPage</p>
	* <p>Description:渠道评级List</p>
	* @param bean
	* @param pageInfor
	* @return
	* @throws Exception
	* @author cuidd
	* @date 2013-5-14
	* @return List<MediaRatingVo>
	* @version 1.0
	* @throws
	*/
	public List<ChannelRatingVo> findByPage(ChannelRatingBean bean,
			IPageInfo pageInfor) throws Exception;
	/**
	* <p>Title: updateChannelRating</p>
	* <p>Description:渠道评级修改</p>
	* @param channelId
	* @param score
	* @author lichuang
	* @date 2013-7-9
	* @return void
	* @version 1.0
	 */
	public void updateChannelRating(Long channelId,Double score);
}
