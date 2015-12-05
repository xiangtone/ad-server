package cn.adwalker.ad.admin.channel.service;

import java.util.List;

import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.ad.admin.channel.bean.ChannelEffectQueryBean;
import cn.adwalker.ad.admin.channel.vo.ChannelEffectSumVo;
import cn.adwalker.ad.admin.channel.vo.ChannelEffectVo;

/**
 * <p>
 * Title: IChannelEffectQueryService
 * </p>
 * <p>
 * Description:TODO 
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-5-23
 */
public interface IChannelEffectQueryService {

	/**
	 * <p>
	 * Title: findDetailByPList
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param bean
	 * @param pageInfo
	 * @return
	 * @author cuidd
	 * @date 2013-7-16
	 * @return List<ChannelEffectVo>
	 * @version 1.0
	 * @throws Exception
	 */

	public List<ChannelEffectVo> findByPage(
			ChannelEffectQueryBean bean, IPageInfo pageInfo) throws Exception;

	/**
	 * <p>
	 * Title: findAll
	 * </p>
	 * <p>
	 * Description:导出报表
	 * </p>
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-7-22
	 * @return List<ChannelEffectVo>
	 * @version 1.0
	 */
	public List<ChannelEffectVo> findAll(ChannelEffectQueryBean bean)
			throws Exception;

	/**
	 * <p>
	 * Title: getNumberSum
	 * </p>
	 * <p>
	 * Description:求和
	 * </p>
	 * 
	 * @param bean
	 * @return
	 * @author lichuang
	 * @date 2013-8-5
	 * @return ChannelEffectSumVo
	 * @version 1.0
	 */
	public ChannelEffectSumVo getNumberSum(ChannelEffectQueryBean bean)
			throws Exception;
}
