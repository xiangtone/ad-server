package cn.adwalker.ad.admin.channel.service;

import java.util.List;

import cn.adwalker.ad.admin.channel.bean.SearchChannelBean;
import cn.adwalker.ad.admin.channel.form.ChannelAddForm;
import cn.adwalker.ad.admin.channel.form.ChannelBankInfo;
import cn.adwalker.ad.admin.channel.form.UpdateChannel;
import cn.adwalker.ad.admin.channel.vo.ChannelAndBankinfoVo;
import cn.adwalker.ad.admin.channel.vo.ChannelBankInfoVo;
import cn.adwalker.ad.admin.channel.vo.ChannelListVo;
import cn.adwalker.ad.admin.channel.vo.ChannelVo;
import cn.adwalker.ad.admin.common.vo.SysUserVo;
import cn.adwalker.core.page.IPageInfo;

/**
 * <p>
 * Title: IChannelService
 * </p>
 * <p>
 * Description:渠道service接口
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-4-11
 */
public interface IChannelService {

	/**
	 * <p>
	 * Title: registService
	 * </p>
	 * <p>
	 * Description:渠道注册
	 * </p>
	 * 
	 * @param registChannel
	 * @param manageUser
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-5-17
	 * @return Long
	 * @version 1.0
	 */
	public Long channelAddForm(ChannelAddForm registChannel, SysUserVo manageUser)
			throws Exception;

	/**
	 * <p>
	 * Title: exists
	 * </p>
	 * <p>
	 * Description:检查用户是否存在
	 * </p>
	 * 
	 * @param checkParament
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-7-9
	 * @return boolean
	 * @version 1.0
	 */
	public boolean exists(String checkParament) throws Exception;

	/**
	 * <p>
	 * Title: findChannelList
	 * </p>
	 * <p>
	 * Description:查看渠道list
	 * </p>
	 * 
	 * @param bean
	 * @param pageInfo
	 * @return
	 * @author lichuang
	 * @date 2013-4-11
	 * @return List<ChannelListVo>
	 * @version 1.0
	 */
	public List<ChannelListVo> findByPage(SearchChannelBean bean,
			IPageInfo pageInfo) throws Exception;

	/**
	 * 
	 * <p>
	 * Title: channelSealStatus
	 * </p>
	 * <p>
	 * Description:渠道封号、激活
	 * </p>
	 * 
	 * @param id
	 * @param manageUser
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-4-11
	 * @return void
	 * @version 1.0
	 * @throws
	 */
	public void channelSealStatus(Long id, SysUserVo manageUser)
			throws Exception;

	/**
	 * 
	 * <p>
	 * Title: channelActivationStatus
	 * </p>
	 * <p>
	 * Description:渠道
	 * </p>
	 * 
	 * @param id
	 * @param manageUser
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-4-11
	 * @return void
	 * @version 1.0
	 * @throws
	 */
	public void channelActivationStatus(Long id, SysUserVo manageUser)
			throws Exception;

	/**
	 * <p>
	 * Title: getService
	 * </p>
	 * <p>
	 * Description:渠道修改查看
	 * </p>
	 * 
	 * @param id
	 * @return
	 * @author lichuang
	 * @date 2013-4-16
	 * @return ChannelVo
	 * @version 1.0
	 * @throws Exception
	 */
	public ChannelVo getChannel(Long id) throws Exception;

	/**
	 * <p>
	 * Title: getBankInfoService
	 * </p>
	 * <p>
	 * Description:渠道修改财务查看
	 * </p>
	 * 
	 * @param id
	 * @return
	 * @author lichuang
	 * @date 2013-4-16
	 * @return ChannelBankInfoVo
	 * @version 1.0
	 */
	public ChannelBankInfoVo getBankInfoService(Long id) throws Exception;

	/**
	 * <p>
	 * Title: setPasswoid
	 * </p>
	 * <p>
	 * Description:渠道重置密码
	 * </p>
	 * 
	 * @param id
	 * @param manageUser
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-4-16
	 * @return void
	 * @version 1.0
	 */
	public void setPasswoid(Long id, SysUserVo manageUser) throws Exception;

	/**
	 * <p>
	 * Title: updateChannel
	 * </p>
	 * <p>
	 * Description:修改渠道
	 * </p>
	 * 
	 * @param updateChannel
	 * @param manageUser
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-4-16
	 * @return void
	 * @version 1.0
	 * @param bankInfo
	 */
	public void updateChannel(UpdateChannel updateChannel,
			ChannelBankInfo bankInfo, SysUserVo manageUser) throws Exception;

	/**
	 * <p>
	 * Title: findChanInfo
	 * </p>
	 * <p>
	 * Description:渠道基本资料查看
	 * </p>
	 * 
	 * @param chaId
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-5-14
	 * @return ChannelAndBankinfoVo
	 * @version 1.0
	 */
	public ChannelAndBankinfoVo findChanInfo(Long chaId) throws Exception;

	/**
	 * <p>
	 * Title: delChannel
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param ids
	 * @author cuidd
	 * @date 2013-7-9
	 * @return void
	 * @version 1.0
	 */

	public void delChannel(String ids);

}
