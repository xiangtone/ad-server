package cn.adwalker.ad.admin.channel.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.adwalker.ad.admin.channel.bean.SearchChannelBean;
import cn.adwalker.ad.admin.channel.form.ChannelAddForm;
import cn.adwalker.ad.admin.channel.form.ChannelBankInfo;
import cn.adwalker.ad.admin.channel.form.UpdateChannel;
import cn.adwalker.ad.admin.channel.service.IChannelService;
import cn.adwalker.ad.admin.channel.vo.ChannelAndBankinfoVo;
import cn.adwalker.ad.admin.channel.vo.ChannelBankInfoVo;
import cn.adwalker.ad.admin.channel.vo.ChannelListVo;
import cn.adwalker.ad.admin.channel.vo.ChannelVo;
import cn.adwalker.ad.admin.common.vo.SysUserVo;
import cn.adwalker.ad.config.AppConstant;
import cn.adwalker.ad.config.StatusConstant;
import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.util.MD5;
import cn.adwalker.core.util.lang.ObjectUtils;
import cn.adwalker.model.channel.dao.IChannelDao;
import cn.adwalker.model.channel.domain.BankInfoChannel;
import cn.adwalker.model.channel.domain.Channel;

/**
 * <p>
 * Title: ChannelServiceImpl
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-4-11
 */
@Service("registChannelService")
public class ChannelServiceImpl implements IChannelService {

	@Resource
	private IChannelDao channelDao;

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: registService
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param registChannelVo
	 * @param manageUser
	 * @return
	 * @throws Exception
	 * @see cn.cn.adwalker.ad.admin.report.service.ICensusGeneralViewService.channel.service.IChannelService#registService(cn.adwalker.model.channel.domain.RegistChannelVo,
	 *      cn.adwalker.ad.admin.common.vo.SysUserVo.common.vo.ManageUserVo)
	 */
	@Override
	@Transactional(rollbackFor = { Exception.class })
	public Long channelAddForm(ChannelAddForm registChannel, SysUserVo manageUser) throws Exception {
		registChannel.setPassword(new MD5().getMD5ofStr(registChannel
				.getPassword()));
		registChannel.setStatus(StatusConstant.CHANNEL_STATUS_F);
		registChannel.setScale(1d);
		registChannel.setCreate_time(new Date());
		registChannel.setCreate_user(manageUser.getId());
		Long id = null;
		if (registChannel != null) {
			id = channelDao.insert(registChannel);
		}
		return id;

	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findChannelList
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param bean
	 * @param pageInfo
	 * @return
	 * @throws Exception
	 * @see cn.cn.adwalker.ad.admin.report.service.ICensusGeneralViewService.channel.service.IChannelService#findChannelList(cn.adwalker.ad.admin.channel.bean.CensusGeneralViewBean.channel.bean.SearchChannelBean,
	 *      cn.adwalker.core.page.IPageInfo)
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.NEVER)
	@Override
	public List<ChannelListVo> findByPage(SearchChannelBean bean,
			IPageInfo pageInfo) throws Exception {
		List<Object> list = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer("T_CHANNEL where 1=1 and status>=-2");
		if (ObjectUtils.isNotEmpty(bean.getChannel_emails())) {
			sb.append(" and upper(EMAIL) ");
			sb.append(" like '%");
			sb.append(bean.getChannel_emails());
			sb.append("%'");
		}
		if (ObjectUtils.isNotEmpty(bean.getChanne_managers())) {
			sb.append(" and CHANNE_MANAGER ");
			sb.append(" like '%");
			sb.append(bean.getChanne_managers());
			sb.append("%'");
		}
		if (ObjectUtils.isNotEmpty(bean.getChannel_names())) {
			sb.append(" and upper(NAME) ");
			sb.append(" like '%");
			sb.append(bean.getChannel_names());
			sb.append("%'");
		}
		if (ObjectUtils.isNotEmpty(bean.getOs())) {
			sb.append(" and OS ='");
			sb.append(bean.getOs());
			sb.append("'");
		}
		if (ObjectUtils.isNotEmpty(bean.getReal_names())) {
			sb.append(" and REAL_NAME ");
			sb.append(" like '%");
			sb.append(bean.getReal_names());
			sb.append("%'");
		}
		if (ObjectUtils.isNotEmpty(bean.getChanne_modes())) {
			if (bean.getChanne_modes() != -1) {
				sb.append(" and CHANNE_MODE ='");
				sb.append(bean.getChanne_modes());
				sb.append("'");
			}
		}

		return (List<ChannelListVo>) channelDao.findByPage("*", sb.toString(),
				list.toArray(), " order by create_time desc ",
				ChannelListVo.class, pageInfo);
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: exists
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param checkParament
	 * @return
	 * @throws Exception
	 * @see cn.cn.adwalker.ad.admin.report.service.ICensusGeneralViewService.channel.service.IChannelService#exists(java.lang.String)
	 */
	@Override
	public boolean exists(String checkParament) throws Exception {
		if (checkParament != null) {
			if (channelDao.exists(checkParament)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: channelSealStatus
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param id
	 * @param manageUser
	 * @throws Exception
	 * @see cn.cn.adwalker.ad.admin.report.service.ICensusGeneralViewService.channel.service.IChannelService#channelSealStatus(java.lang.Long,
	 *      cn.adwalker.ad.admin.common.vo.SysUserVo.common.vo.ManageUserVo)
	 */
	public void channelSealStatus(Long id, SysUserVo manageUser)
			throws Exception {
		if (id != null) {
			channelDao.updateStatus(id, manageUser,
					StatusConstant.CHANNEL_STATUS_DEL);
		}
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: channelActivationStatus
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param id
	 * @param manageUser
	 * @throws Exception
	 * @see cn.cn.adwalker.ad.admin.report.service.ICensusGeneralViewService.channel.service.IChannelService#channelActivationStatus(java.lang.Long,
	 *      cn.adwalker.ad.admin.common.vo.SysUserVo.common.vo.ManageUserVo)
	 */
	public void channelActivationStatus(Long id, SysUserVo manageUser)
			throws Exception {
		if (id != null) {
			channelDao.updateStatus(id, manageUser,
					StatusConstant.CHANNEL_STATUS_F);
		}
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: getService
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.channel.service.IChannelService#getService(java.lang.Long)
	 */
	@Override
	public ChannelVo getChannel(Long id) throws Exception {
		ChannelVo channelVo = channelDao.getChannel(id);
		return channelVo;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: getBankInfoService
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.channel.service.IChannelService#getBankInfoService(java.lang.Long)
	 */
	@Override
	public ChannelBankInfoVo getBankInfoService(Long id) throws Exception {
		ChannelBankInfoVo channelBankInfoVo = channelDao.getBankInfoService(id);
		return channelBankInfoVo;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: setPasswoid
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param id
	 * @param manageUser
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.channel.service.IChannelService#setPasswoid(java.lang.Long,
	 *      cn.adwalker.ad.admin.common.vo.SysUserVo)
	 */
	@Override
	public void setPasswoid(Long id, SysUserVo manageUser) throws Exception {
		String md5ofStr = new MD5().getMD5ofStr(AppConstant.RESET_CHANNEL);
		if (id != null) {
			channelDao.setPasswoid(id, manageUser, md5ofStr);
		}
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: updateChannel
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param updateChannel
	 * @param manageUser
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.channel.service.IChannelService#updateChannel(cn.adwalker.ad.admin.channel.form.UpdateChannel,
	 *      cn.adwalker.ad.admin.common.vo.SysUserVo)
	 */
	@Override
	public void updateChannel(UpdateChannel updateChannel,
			ChannelBankInfo bankForm, SysUserVo manageUser) throws Exception {
		updateChannel.setUpdate_time(new Date());
		channelDao.update(updateChannel);
		if (bankForm != null
				&& !StringUtils.isEmpty(bankForm.getAccount_hoder())
				&& !StringUtils.isEmpty(bankForm.getBank_account())
				&& !StringUtils.isEmpty(bankForm.getBank_subbranch())) {
			BankInfoChannel bankInfoChannel = new BankInfoChannel();
			bankInfoChannel.setUser_id(updateChannel.getId());
			bankInfoChannel.setUpdate_time(new Date());
			bankInfoChannel.setAccount_hoder(bankForm.getAccount_hoder());
			bankInfoChannel.setBank_account(bankForm.getBank_account());
			bankInfoChannel.setBank_subbranch(bankForm.getBank_subbranch());
			bankInfoChannel.setInvoice_require(bankForm.getInvoice_require());
			bankInfoChannel.setInvoice_require_others(bankForm
					.getInvoice_require_others());
			channelDao.updateServicebankInfo(bankInfoChannel,
					updateChannel.getId());
		}

	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findChanInfo
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param chaId
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.channel.service.IChannelService#findChanInfo(java.lang.Long)
	 */
	@Override
	public ChannelAndBankinfoVo findChanInfo(Long chaId) throws Exception {

		Channel chanInfo = channelDao.findChanInfo(chaId);
		ChannelBankInfo chaBankInfo = channelDao.findchanBankInfo(chaId);

		ChannelAndBankinfoVo result = new ChannelAndBankinfoVo(chanInfo,
				chaBankInfo);

		return result;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: delChannel
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param ids
	 * @see cn.adwalker.ad.admin.channel.service.IChannelService#delChannel(java.lang.String)
	 */
	@Override
	public void delChannel(String ids) {
		if (!StringUtils.isEmpty(ids)) {
			List<Long> list = new ArrayList<Long>();
			String arr[] = ids.split(",");
			if (arr != null && arr.length > 0) {
				for (int i = 0; i < arr.length; i++) {
					if (NumberUtils.isNumber(arr[i])) {
						list.add(Long.valueOf(arr[i]));
					}
				}
				if (list.size() > 0) {
					channelDao.updateStatus(list,
							StatusConstant.CHANNEL_STATUS_T);
				}

			}
		}
	}
}
