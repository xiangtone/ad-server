package cn.adwalker.model.channel.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import cn.adwalker.model.channel.dao.IChannelDao;
import cn.adwalker.model.channel.domain.BankInfoChannel;
import cn.adwalker.model.channel.domain.Channel;
import cn.adwalker.model.channel.domain.ChannelMoney;
import cn.adwalker.ad.admin.channel.form.ChannelBankInfo;
import cn.adwalker.ad.admin.channel.vo.ChannelBankInfoVo;
import cn.adwalker.ad.admin.channel.vo.ChannelVo;
import cn.adwalker.ad.admin.common.vo.SysUserVo;
import cn.adwalker.core.repository.impl.BaseDaoImpl;
import cn.adwalker.core.util.lang.StringUtil;

/**
 * <p>
 * Title: ChannelDaoImpl
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-4-12
 */
@Repository("userChannelDao")
public class ChannelDaoImpl extends BaseDaoImpl<Channel> implements IChannelDao {
	
	/**
	 * <p>
	 * Title:
	 * </p>
	 */
	public ChannelDaoImpl() {
		super();
		this.setTableName("T_CHANNEL");
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: Exists
	 * </p>
	 * 
	 * @param email
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.model.channel.dao.IChannelDao#Exists(java.lang.String)
	 */
	@SuppressWarnings("deprecation")
	@Override
	public Boolean exists(String email) throws Exception {
		boolean b = false;
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) from  VIEW_USER where EMAIL = ?");
		Integer i = jdbcTemplate.queryForInt(sql.toString(), email);
		if (i > 0) {
			b = true;
		}

		return b;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: channelSealStatus
	 * </p>
	 * 
	 * @param id
	 * @param manageUser
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.model.channel.dao.IChannelDao#channelSealStatus(java.lang.Long, cn.adwalker.ad.admin.common.vo.SysUserVo.common.vo.ManageUserVo)
	 */
	@Override
	public int updateStatus(Long id, SysUserVo manageUser, Integer value) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE T_CHANNEL SET STATUS = '");
		sql.append(value);
		sql.append("',OPERATER_ID= '");
		sql.append(manageUser.getId());
		sql.append("' where 1=1 and ID= ?");
		return jdbcTemplate.update(sql.toString(), new Object[] { id });
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
	 * @see cn.adwalker.model.channel.dao.IChannelDao#getService(java.lang.Long)
	 */
	@Override
	public ChannelVo getChannel(Long id) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from T_CHANNEL  where 1=1 and id= ?");
		List<ChannelVo> list = (List<ChannelVo>) jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<ChannelVo>(ChannelVo.class), id);
		ChannelVo channelVo = new ChannelVo();
		if (list != null && list.size() > 0) {
			channelVo = list.get(0);
		}
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
	 * @see cn.adwalker.model.channel.dao.IChannelDao#getBankInfoService(java.lang.Long)
	 */
	@Override
	public ChannelBankInfoVo getBankInfoService(Long id) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from T_BANK_INFO  where 1=1 and USER_ID= ?");
		List<ChannelBankInfoVo> list = (List<ChannelBankInfoVo>) jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<ChannelBankInfoVo>(ChannelBankInfoVo.class), id);
		ChannelBankInfoVo bankInfoVo = new ChannelBankInfoVo();
		if (list != null && list.size() > 0) {
			bankInfoVo = list.get(0);
		}
		return bankInfoVo;
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
	 * @param md5ofStr
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.model.channel.dao.IChannelDao#setPasswoid(java.lang.Long, cn.adwalker.ad.admin.common.vo.SysUserVo, java.lang.String)
	 */
	@Override
	public int setPasswoid(Long id, SysUserVo manageUser, String md5ofStr) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE T_CHANNEL SET PASSWORD = '");
		sql.append(md5ofStr);
		sql.append("',OPERATER_ID= '");
		sql.append(manageUser.getId());
		sql.append("' where 1=1 and ID= ?");
		return jdbcTemplate.update(sql.toString(), new Object[] { id });

	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: updateServicebankInfo
	 * </p>
	 * 
	 * @param bankInfoChannel
	 * @param id
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.model.channel.dao.IChannelDao#updateServicebankInfo(cn.adwalker.ad.admin.channel.form.ChannelBankInfo, java.lang.Long)
	 */
	@Override
	public int updateServicebankInfo(BankInfoChannel bankInfoChannel, Long id) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" update T_BANK_INFO  set ");

		if (StringUtil.isNotEmpty(bankInfoChannel.getBank_account())) {
			sql.append("BANK_ACCOUNT= :bank_account,");
		}
		if (StringUtil.isNotEmpty(bankInfoChannel.getBank_subbranch())) {
			sql.append("BANK_SUBBRANCH= :bank_subbranch,");
		}
		if (StringUtil.isNotEmpty(bankInfoChannel.getAccount_hoder())) {
			sql.append("ACCOUNT_HODER= :account_hoder,");
		}
		if (StringUtil.isNotEmpty(bankInfoChannel.getInvoice_require_others())) {
			sql.append("INVOICE_REQUIRE_OTHERS= :invoice_require_others,");
		}
		if (bankInfoChannel.getInvoice_require() != null) {
			sql.append("INVOICE_REQUIRE= :invoice_require,");
		}
		sql.append("ROLE = 3,");
		sql.append("UPDATE_TIME = :update_time ");
		sql.append(" where USER_ID=:user_id");
		return super.update(sql.toString(), bankInfoChannel);
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findChanInfo
	 * </p>
	 * 
	 * @param chaId
	 * @throws Exception
	 * @see cn.adwalker.model.channel.dao.IChannelDao#findChanInfo(java.lang.Long)
	 */
	@Override
	public Channel findChanInfo(Long chaId) throws Exception {
		Channel entity = null;
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from T_CHANNEL where 1=1 and id= ?");
		List<Channel> list = (List<Channel>) jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<Channel>(Channel.class), chaId);
		if (list != null && list.size() > 0) {
			entity = list.get(0);
		}
		return entity;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findchanBankInfo
	 * </p>
	 * 
	 * @param chaId
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.model.channel.dao.IChannelDao#findchanBankInfo(java.lang.Long)
	 */
	@Override
	public ChannelBankInfo findchanBankInfo(Long chaId) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from T_BANK_INFO  where 1=1 and ROLE=3 and USER_ID= ?");
		List<ChannelBankInfo> list = (List<ChannelBankInfo>) jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<ChannelBankInfo>(ChannelBankInfo.class), chaId);
		ChannelBankInfo bankInfo = new ChannelBankInfo();
		if (list != null && list.size() > 0) {
			bankInfo = list.get(0);
		}
		return bankInfo;
	}

	@Override
	public void updateChannelRating(Long channelId, Double score) {
		String sql = "update T_CHANNEL  set SCALE=? where ID=?";
		jdbcTemplate.update(sql, new Object[] { score, channelId });
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: updateStatus
	 * </p>
	 * 
	 * @param list
	 * @param channelStatusDel
	 * @see cn.adwalker.model.channel.dao.IChannelDao#updateStatus(java.util.List, java.lang.Integer)
	 */
	@Override
	public void updateStatus(List<Long> list, Integer channelStatusDel) {
		StringBuilder sb = new StringBuilder();
		sb.append(" UPDATE " + tableName + " SET STATUS=? WHERE ID=?");
		List<Object[]> parameters = new ArrayList<Object[]>();
		for (Long id : list) {
			parameters.add(new Object[] { channelStatusDel, id });
		}
		this.jdbcTemplate.batchUpdate(sb.toString(), parameters);

	}
	/**
	 * (non-Javadoc)
	* <p>Title: batchUpdateTotalMAndConfirmM</p>
	* <p>Description:TODO</p>
	* @param tempMap
	* @throws Exception
	* @see cn.adwalker.model.channel.dao.IChannelDao#batchUpdateTotalMAndConfirmM(java.util.Map)
	 */
	@Override
	public void batchUpdateTotalMAndConfirmM(Map<Long, ChannelMoney> user)
			throws Exception {
		String sql = " update t_channel set  FINANCE_INCOME = IFNULL(FINANCE_INCOME,0.0) - ? , TOTAL_MONEY = IFNULL(TOTAL_MONEY,0.0) + ? where ID = ? ";
		List<Object[]> parameters = new ArrayList<Object[]>();
		Set<Long> set = user.keySet();
		for (Long key : set) {
			ChannelMoney bean = user.get(key);
			bean.setId(key);
			parameters.add(new Object[] { bean.getConfirmedMoney(), bean.getConfirmedMoney(), bean.getId() });
		}
		this.jdbcTemplate.batchUpdate(sql.toString(), parameters);
		
	}
	/**
	 * (non-Javadoc)
	* <p>Title: updateFinanceIncome</p>
	* <p>Description:TODO</p>
	* @param channel_id
	* @param cost
	* @throws Exception
	* @see cn.adwalker.model.channel.dao.IChannelDao#updateFinanceIncome(java.lang.Long, java.lang.Double)
	 */
	@Override
	public void updateFinanceIncome(Long channel_id, Double cost)
			throws Exception {
		String sql = "update T_CHANNEL  set finance_income = IFNULL(finance_income,0.0) +? where ID=?";
		jdbcTemplate.update(sql, new Object[] { cost, channel_id });
		
	}
}