package cn.adwalker.model.operation.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import cn.adwalker.ad.admin.finance.vo.ChannelConfirmVo;
import cn.adwalker.core.repository.impl.BaseDaoImpl;
import cn.adwalker.core.util.lang.ObjectUtils;
import cn.adwalker.model.finance.domain.CostMonthChannel;
import cn.adwalker.model.operation.dao.IChannelConfirmDao;
import cn.adwalker.model.operation.domain.CampaignConfirm;
import cn.adwalker.model.operation.domain.CampaignIncomeCost;

/**
* <p>Title: ChannelConfirmDaoImpl</p>
* <p>Description:渠道成本结算dao</p>
* <p>Company: adwalker</p> 
* @author    lichuang
* @date       2014年11月17日
 */

@Repository("channelConfirmDao")
public class ChannelConfirmDaoImpl extends BaseDaoImpl<CampaignConfirm> implements IChannelConfirmDao {

	public ChannelConfirmDaoImpl() {
		super();
		this.setTableName("T_CHANNEL_CONFIRM");
	}
	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: updateinvoice
	 * </p>
	 * Long id, String remark, Long invoice_user_id, Date invoice_time
	 * @param id
	 * @throws Exception
	 * @see cn.adwalker.model.operation.dao.ICampaignConfirmDao#updateinvoice(java.lang.Long[])
	 */
	@Override
	public int[] updateInvoiceChannel(List<Object[]> objList) throws Exception {
		String sql = "update t_channel_confirm set invoice_status=?,invoice_remark=?,invoice_user_id=?,invoice_time=NOW() where status=3 and id=?";
		return jdbcTemplate.batchUpdate(sql, objList);
	}
	/**
	 * (non-Javadoc)
	* <p>Title: updatePayment</p>
	* <p>Description:TODO</p>
	* @param objList
	* @return
	* @throws Exception
	* @see cn.adwalker.model.operation.dao.IChannelConfirmDao#updatePayment(java.util.List)
	 */
	@Override
	public int[] updatePayment(List<Object[]> objList) throws Exception {
		String sql = "update t_channel_confirm set payment_status=?,payment_remark=?,payment_user_id=?,payment_time=NOW() where status=3 and id=?";
		return jdbcTemplate.batchUpdate(sql, objList);
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
	 * @see cn.adwalker.model.operation.dao.ICampaignConfirmDao#findById(java.lang.Long)
	 */
	@Override
	public CampaignIncomeCost findById(Long id) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select l.SYS_COST,l.CONFIRM_NUM*l.IN_PRICE as focast_money from T_CAMPAIGN_CONFIRM a " + "left join V_INCOME_OUTCOME l on a.campaign_id = l.CAMPAIGN_ID where 1 = 1 and a.id= ?");
		List<CampaignIncomeCost> objects = jdbcTemplate.query(sb.toString(), new BeanPropertyRowMapper<CampaignIncomeCost>(CampaignIncomeCost.class), id);
		CampaignIncomeCost com = null;
		if (objects != null && objects.size() > 0) {
			com = (CampaignIncomeCost) objects.get(0);
			return com;
		}
		return null;
	}
	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: getCampaignConfirm
	 * </p>
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.model.operation.dao.ICampaignConfirmDao#getCampaignConfirm(java.lang.Long)
	 */
	@Override
	public CampaignConfirm getCampaignConfirm(Long id) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select * from T_CAMPAIGN_CONFIRM a where 1 = 1 and a.id= ?");
		List<CampaignConfirm> objects = jdbcTemplate.query(sb.toString(), new BeanPropertyRowMapper<CampaignConfirm>(CampaignConfirm.class), id);
		CampaignConfirm com = null;
		if (objects != null && objects.size() > 0) {
			com = (CampaignConfirm) objects.get(0);
			return com;
		}
		return null;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: updateinvoice
	 * </p>
	 * 
	 * @param id
	 * @throws Exception
	 * @see cn.adwalker.model.operation.dao.ICampaignConfirmDao#updateinvoice(java.lang.Long[])
	 */
	@Override
	public void updateInvoice(Long confirm_id, Long id) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" update T_CAMPAIGN_CONFIRM set INVOICE_STATUS=2");
		if (ObjectUtils.isNotEmpty(id)) {
			sql.append(",INVOICE_ID=");
			sql.append(id);
		}
		sql.append(" where 1=1  and status! =2 and ID=");
		sql.append(confirm_id);
		jdbcTemplate.update(sql.toString());

	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: deleteInvoice
	 * </p>
	 * 
	 * @param id
	 * @throws Exception
	 * @see cn.adwalker.model.operation.dao.ICampaignConfirmDao#deleteInvoice(long)
	 */
	@Override
	public void deleteInvoice(long id) throws Exception {
		String sql = " update  t_campaign_confirm  set INVOICE_STATUS=1 where 1=1 and status! =2  and invoice_id=?";
		jdbcTemplate.update(sql, id);
	}
	
	/**
	 * (non-Javadoc)
	* <p>Title: updatereduceAchievement</p>
	* <p>Description:TODO</p>
	* @param id
	* @throws Exception
	* @see cn.adwalker.model.operation.dao.ICampaignConfirmDao#updatereduceAchievement(java.lang.Long)
	 */
	@Override
	public void updatereduceAchievement(Long id) throws Exception {
		String sql = " update  t_campaign_confirm  set STATUS=5 where 1=1 and id=?";
		jdbcTemplate.update(sql, id);
	}

	/**
	 * (non-Javadoc)
	* <p>Title: updatepassThrough</p>
	* <p>Description:TODO</p>
	* @param id
	* @param status
	* @throws Exception
	* @see cn.adwalker.model.operation.dao.ICampaignConfirmDao#updatepassThrough(java.lang.Long, java.lang.Integer)
	 */
	@Override
	public void updatepassThrough(Long id, Integer status) throws Exception {
		String sql = " update  t_campaign_confirm  set STATUS=? where 1=1 and id=?";
		jdbcTemplate.update(sql,status, id);
		
	}
	
	
	/**
	 * (non-Javadoc)
	* <p>Title: findByIosId</p>
	* <p>Description:TODO</p>
	* @param id
	* @return
	* @throws Exception
	* @see com.emar.escore.model.operation.dao.ICampaignConfirmDao#findByIosId(java.lang.Long)
	 */
	@Override
	public CampaignIncomeCost findByIosId(Long id) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT l.SYS_COST,l.CONFIRM_NUM*l.IN_PRICE AS focast_money FROM T_CAMPAIGN_CONFIRM a " +
				"LEFT JOIN v_ios_config_income l ON a.campaign_id = l.CAMPAIGN_ID WHERE 1 = 1 AND a.id= ?");
		List<CampaignIncomeCost> objects = jdbcTemplate.query(sb.toString(), new BeanPropertyRowMapper<CampaignIncomeCost>(CampaignIncomeCost.class), id);
		CampaignIncomeCost com = null;
		if (objects != null && objects.size() > 0) {
			com = (CampaignIncomeCost) objects.get(0);
			return com;
		}
		return null;
	}
	/**
	 * (non-Javadoc)
	* <p>Title: getgetById</p>
	* <p>Description:TODO</p>
	* @param income_id
	* @return
	* @throws Exception
	* @see cn.adwalker.model.operation.dao.ICampaignConfirmDao#getgetById(java.lang.Long)
	 */

	@Override
	public ChannelConfirmVo getgetById(Long income_id) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT a.* ,t.campaign_name,t.os,s.`channe_manager`,s.`name` channel_name FROM t_channel_confirm a LEFT JOIN v_campaign t  ON a.campaign_id= t.id LEFT JOIN t_channel s ON a.`channel_id` = s.id  WHERE 1=1 AND a.id= ?");
		List<ChannelConfirmVo> objects = jdbcTemplate.query(sb.toString(), new BeanPropertyRowMapper<ChannelConfirmVo>(ChannelConfirmVo.class), income_id);
		ChannelConfirmVo com = null;
		if (objects != null && objects.size() > 0) {
			com = (ChannelConfirmVo) objects.get(0);
			return com;
		}
		return null;
	}

	/**
	 * (non-Javadoc)
	* <p>Title: updateStatus</p>
	* <p>Description:TODO</p>
	* @param plist
	* @throws Exception
	* @see cn.adwalker.model.operation.dao.ICampaignConfirmDao#updateStatus(cn.adwalker.model.finance.domain.IncomeMonthCampaign)
	 */
	@Override
	public void updateStatus(CostMonthChannel plist) throws Exception {
		StringBuffer sb = new StringBuffer();
		if (ObjectUtils.isNotEmpty(plist.getId())) {
			sb.append("update T_CHANNEL_CONFIRM set STATUS=1");
			if (ObjectUtils.isNotEmpty(plist.getIncome_money())) {
				sb.append(",INCOME_MONEY=");
				sb.append(plist.getIncome_money());
			}
			if (ObjectUtils.isNotEmpty(plist.getIncome_remark())) {
				sb.append(",INCOME_REMARK='");
				sb.append(plist.getIncome_remark());
				sb.append("'");
			}
			sb.append(" where ID=");
			sb.append(plist.getId());
		}
		jdbcTemplate.update(sb.toString());
		
	}
	/**
	 * (non-Javadoc)
	* <p>Title: updateInCome</p>
	* <p>Description:TODO</p>
	* @param objList
	* @return
	* @throws Exception
	* @see cn.adwalker.model.operation.dao.ICampaignConfirmDao#updateInCome(java.util.List)
	 */
	@Override
	public int[] updateInCome(List<Object[]> objList) throws Exception {
		String sql = "update t_channel_confirm set status=?,income_remark=?,operater_id=?,income_time=NOW() where 1=1 and id=?";
		return jdbcTemplate.batchUpdate(sql, objList);
	}
	/**
	 * (non-Javadoc)
	* <p>Title: deleteStatus</p>
	* <p>Description:TODO</p>
	* @param id
	* @throws Exception
	* @see cn.adwalker.model.operation.dao.IChannelConfirmDao#deleteStatus(java.lang.Long)
	 */
	@Override
	public void deleteStatus(Long id) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("delete FROM T_CHANNEL_CONFIRM  where 1=1 and id=");
		if(ObjectUtils.isNotEmpty(id)){
			sb.append(id);
		}
		jdbcTemplate.update(sb.toString());
	}
	
}
