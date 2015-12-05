package cn.adwalker.model.common.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import cn.adwalker.ad.admin.channel.form.ChannelBankInfoform;
import cn.adwalker.ad.config.StatusConstant;
import cn.adwalker.core.repository.impl.BaseDaoImpl;
import cn.adwalker.core.util.lang.ObjectUtils;
import cn.adwalker.model.channel.domain.BankInfoChannel;
import cn.adwalker.model.common.dao.IBankInfoDao;
import cn.adwalker.model.common.domain.BankInfo;

/**
 * 功能概述：<br>
 * 银行信息管理接口实现
 * 
 * @author zhaozengbin,guoyongxiang
 */
@Repository("bankInfoDao")
public class BankInfoDaoImpl extends BaseDaoImpl<BankInfo> implements IBankInfoDao {

	public BankInfoDaoImpl() {
		setTableName("T_BANK_INFO");
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: deleteById
	 * </p>
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.model.common.dao.IBankInfoDao#deleteById(java.lang.Long)
	 */
	@Override
	public Integer deleteById(Long id) throws Exception {
		String sql = "delete T_BANK_INFO where ID = ?";
		return jdbcTemplate.update(sql, id);
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: deleteByUserId
	 * </p>
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.model.common.dao.IBankInfoDao#deleteByUserId(java.lang.Long)
	 */
	@Override
	public Integer deleteByUserId(Long userId) throws Exception {
		String sql = "delete T_BANK_INFO where USER_ID = ?";
		return jdbcTemplate.update(sql.toString(), userId);
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findById
	 * </p>
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.model.common.dao.IBankInfoDao#findById(java.lang.Long)
	 */
	@Override
	public BankInfo findById(Long id) throws Exception {
		String sql = "select * from T_BANK_INFO where ID = ?";
		return jdbcTemplate.queryForObject(sql.toString(), new BeanPropertyRowMapper<BankInfo>(BankInfo.class), id);
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findByUser
	 * </p>
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.model.common.dao.IBankInfoDao#findByUser(java.lang.Long)
	 */
	@Override
	public List<BankInfo> findByUser(Long userId) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from T_BANK_INFO where USER_ID = ?");
		List<BankInfo> bankInfoList = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<BankInfo>(BankInfo.class), userId);
		if (bankInfoList != null && bankInfoList.size() > 0) {
			return bankInfoList;
		}
		return null;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: getDevBlank
	 * </p>
	 * 
	 * @param userId
	 * @param role
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.model.common.dao.IBankInfoDao#getDevBlank(java.lang.Long, java.lang.Integer)
	 */
	@Override
	public BankInfo getDevBlank(Long userId, Integer role) throws Exception {
		String sql = "select * from T_BANK_INFO where USER_ID=? and ROLE=?";
		List<BankInfo> bankInfoList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<BankInfo>(BankInfo.class), new Object[] { userId, role });
		if (ObjectUtils.isNotEmpty(bankInfoList)) {
			return bankInfoList.get(0);
		}
		return null;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: insert
	 * </p>
	 * 
	 * @param bankInfo
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.model.common.dao.IBankInfoDao#insert(cn.adwalker.model.common.domain.BankInfo)
	 */
	@Override
	public long insert(BankInfo bankInfo) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("insert into T_BANK_INFO(");
		sql.append("USER_ID,");
		sql.append("ROLE,");
		sql.append("ACCOUNT_HODER,");
		sql.append("BANK_NAME,");
		sql.append("CITY_ID,");
		sql.append("BANK_SUBBRANCH,");
		sql.append("BANK_ACCOUNT,");
		sql.append("BANK_CITY,");
		sql.append("CARD_TYPE,");
		sql.append("CARD_CODE,");
		sql.append("CARD_URL,");
		sql.append("CREATE_TIME)");
		sql.append(" values(");
		sql.append(":userId,");
		sql.append(":role,");
		sql.append(":account_hoder,");
		sql.append(":bankName,");
		sql.append(":city_id,");
		sql.append(":bankSubbranch,");
		sql.append(":bankAccount,");
		sql.append(":bank_city,");
		sql.append(":cardType,");
		sql.append(":cardCode,");
		sql.append(":cardUrl,");
		sql.append(":createTime)");
		return insert(sql.toString(), bankInfo);
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: update
	 * </p>
	 * 
	 * @param bankInfo
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.model.common.dao.IBankInfoDao#update(cn.adwalker.model.common.domain.BankInfo)
	 */
	@Override
	public Integer update(BankInfo bankInfo) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("update T_BANK_INFO SET ");
		if (ObjectUtils.isNotEmpty(bankInfo.getAccount_hoder())) {
			sql.append("ACCOUNT_HODER =:account_hoder,");
		}
		if (ObjectUtils.isNotEmpty(bankInfo.getBankName())) {
			sql.append("BANK_NAME =:bankName,");
		}
		if (ObjectUtils.isNotEmpty(bankInfo.getBank_city())) {
			sql.append("BANK_CITY =:bank_city,");
		}
		if (ObjectUtils.isNotEmpty(bankInfo.getCity_id())) {
			sql.append("CITY_ID =:city_id,");
		}
		if (ObjectUtils.isNotEmpty(bankInfo.getBankSubbranch())) {
			sql.append("BANK_SUBBRANCH =:bankSubbranch,");
		}
		if (ObjectUtils.isNotEmpty(bankInfo.getBankAccount())) {
			sql.append("BANK_ACCOUNT =:bankAccount,");
		}
		if (ObjectUtils.isNotEmpty(bankInfo.getCardType())) {
			sql.append("CARD_TYPE =:cardType,");
		}
		if (ObjectUtils.isNotEmpty(bankInfo.getCardCode())) {
			sql.append("CARD_CODE =:cardCode,");
		}
		if (ObjectUtils.isNotEmpty(bankInfo.getCardUrl())) {
			sql.append("CARD_URL =:cardUrl,");
		}

		String sql1 = sql.substring(0, sql.length() - 1);
		return super.update(sql1, bankInfo);
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: insertbankInfo
	 * </p>
	 * 
	 * @param advBankInfoVo
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.model.ad.dao.IUserAdvertiserDao#insertbankInfo(cn.adwalker.manager.ad.vo.AdvBankInfoVo)
	 */
	@Override
	public long insertbankInfo(BankInfoChannel bankInfoChannel) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into T_BANK_INFO (USER_ID,ROLE,ACCOUNT_HODER,BANK_ACCOUNT,BANK_SUBBRANCH,INVOICE_REQUIRE,INVOICE_REQUIRE_OTHERS)");
		sql.append(" values (:channel_id,:userType,:account_hoder,:bank_account,:bank_subbranch,:invoice_require,:invoice_require_others)");
		return insert(sql.toString(), bankInfoChannel);
	}
	
	
	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: bankInfoService
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param channelBankInfoVo
	 * @throws Exception
	 * @see cn.cn.adwalker.ad.admin.report.service.ICensusGeneralViewService.channel.service.IChannelService#bankInfoService(cn.adwalker.BankInfoChannel.channel.vo.ChannelBankInfoVo)
	 */
	@SuppressWarnings("unused")
	private void bankInfoService(ChannelBankInfoform channelBankInfo,
			Long channel_id) throws Exception {
		BankInfoChannel bankInfoChannel = new BankInfoChannel();
		bankInfoChannel.setUserType(StatusConstant.CHANNEL_B);
		bankInfoChannel.setChannel_id(channel_id);
		bankInfoChannel.setAccount_hoder(channelBankInfo.getAccount_hoder());
		bankInfoChannel.setBank_account(channelBankInfo.getBank_account());
		bankInfoChannel.setBank_subbranch(channelBankInfo.getBank_subbranch());
		bankInfoChannel.setCreateTime(new Date());
		bankInfoChannel
				.setInvoice_require(channelBankInfo.getInvoice_require());
		bankInfoChannel.setInvoice_require_others(channelBankInfo
				.getInvoice_require_others());
		bankInfoChannel.setUserType(channelBankInfo.getUserType());
		if (bankInfoChannel != null) {
			this.insertbankInfo(bankInfoChannel);
		}
	}

}
