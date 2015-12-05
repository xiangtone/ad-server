/*
 * IUserAdvertiserDao.java
 *
 * Copyright 2011 1000CHI Software Technology Co.,Ltd
 * All rights reserved.
 * 
 * Created on 2011-11-30
 */
package cn.adwalker.model.channel.dao;

import java.util.List;
import java.util.Map;

import cn.adwalker.model.channel.domain.BankInfoChannel;
import cn.adwalker.model.channel.domain.Channel;
import cn.adwalker.model.channel.domain.ChannelMoney;
import cn.adwalker.ad.admin.channel.form.ChannelBankInfo;
import cn.adwalker.ad.admin.channel.vo.ChannelBankInfoVo;
import cn.adwalker.ad.admin.channel.vo.ChannelVo;
import cn.adwalker.ad.admin.common.vo.SysUserVo;
import cn.adwalker.core.repository.IBaseDao;

/**
 * 
 * <p>
 * Title: IUserAdvertiserDao
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-4-2
 */
public interface IChannelDao extends IBaseDao<Channel> {

	/**
	 * <p>
	 * Title: Exists
	 * </p>
	 * <p>
	 * Description:用户是否存在
	 * </p>
	 * 
	 * @param email
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-4-11
	 * @return UserChannels
	 * @version 1.0
	 */
	public Boolean exists(String email) throws Exception;

	/**
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
	 */
	public int updateStatus(Long id, SysUserVo manageUser, Integer value)
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
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-4-16
	 * @return ChannelVo
	 * @version 1.0
	 */
	public ChannelVo getChannel(Long id) throws Exception;

	/**
	 * <p>
	 * Title: findBankInfoService
	 * </p>
	 * <p>
	 * Description:渠道修改财务查看
	 * </p>
	 * 
	 * @param id
	 * @return
	 * @throws Exception
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
	 * @param md5ofStr
	 * @author lichuang
	 * @date 2013-4-16
	 * @return void
	 * @version 1.0
	 */
	public int setPasswoid(Long id, SysUserVo manageUser, String md5ofStr)
			throws Exception;

	/**
	 * <p>
	 * Title: updateServicebankInfo
	 * </p>
	 * <p>
	 * Description:渠道财务修改
	 * </p>
	 * 
	 * @param bankInfoChannel
	 * @param id
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-4-17
	 * @return void
	 * @version 1.0
	 */
	public int updateServicebankInfo(BankInfoChannel bankInfoChannel, Long id)
			throws Exception;

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
	 * @return Channel
	 * @version 1.0
	 */
	public Channel findChanInfo(Long chaId) throws Exception;

	/**
	 * <p>
	 * Title: findchanBankInfo
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
	 * @return ChannelBankInfo
	 * @version 1.0
	 */
	public ChannelBankInfo findchanBankInfo(Long chaId) throws Exception;

	/**
	 * 修改渠道评级
	 * <p>
	 * Title: updateChannelRating
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param channelId
	 * @param score
	 */
	public void updateChannelRating(Long channelId, Double score);

	/**
	 * <p>
	 * Title: updateStatus
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param list
	 * @param channelStatusDel
	 * @author cuidd
	 * @date 2013-7-9
	 * @return void
	 * @version 1.0
	 */
	public void updateStatus(List<Long> list, Integer channelStatusDel);
	/**
	* <p>Title: batchUpdateTotalMAndConfirmM</p>
	* <p>Description:TODO</p>
	* @param tempMap
	* @throws Exception
	* @author lichuang
	* @date 2014-2-17
	* @return void
	* @version 1.0
	 */
	public void batchUpdateTotalMAndConfirmM(Map<Long, ChannelMoney> tempMap)throws Exception;
	/**
	* <p>Title: updateFinanceIncome</p>
	* <p>Description:渠道收入录入</p>
	* @param channel_id
	* @param cost
	* @throws Exception
	* @author lichuang
	* @date 2014-2-17
	* @return void
	* @version 1.0
	 */
	public void updateFinanceIncome(Long channel_id, Double cost)throws Exception ;
}
