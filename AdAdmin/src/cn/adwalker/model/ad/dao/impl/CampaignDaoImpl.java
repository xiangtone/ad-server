package cn.adwalker.model.ad.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import cn.adwalker.model.ad.dao.ICampaignDao;
import cn.adwalker.model.ad.domain.Campaign;
import cn.adwalker.ad.admin.common.vo.SysUserVo;
import cn.adwalker.core.repository.impl.BaseDaoImpl;

/**
 * 
 * <p>
 * Title: CampaignDaoImpl
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-4-2
 */
@Repository("campaignDao")
public class CampaignDaoImpl extends BaseDaoImpl<Campaign> implements ICampaignDao {

	public CampaignDaoImpl() {
		setTableName("T_CAMPAIGN");
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findActiInfo
	 * </p>
	 * 
	 * @param advId
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.model.ad.dao.ICampaignDao#findActiInfo(java.lang.Long)
	 */
	@Override
	public Campaign get(Long advId) throws Exception {
		Campaign entity = null;
		StringBuffer sql = new StringBuffer();
		sql.append(" select id,campaign_name,campaign_type,category_id,status,charge_type,price,budget,os,plan_start,plan_end,create_time,create_user,area_directional,app_type,terminal_type,time_directional from V_CAMPAIGN where 1=1 and id= ?");
		List<Campaign> list = (List<Campaign>) jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<Campaign>(Campaign.class), advId);
		if (list != null && list.size() > 0) {
			entity = list.get(0);
		}
		return entity;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: updateStatus
	 * </p>
	 * 
	 * @param id
	 * @param manageUser
	 * @param status
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.model.ad.dao.ICampaignDao#updateStatus(java.lang.Long, cn.adwalker.ad.admin.common.vo.SysUserVo.common.vo.ManageUserVo, java.lang.Integer)
	 */
	@Override
	public int updateStatus(Long id, SysUserVo user, Integer status) throws Exception {
		int result = -1;
		if (id != null && user != null && status != null) {
			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE T_CAMPAIGN SET STATUS = '");
			sql.append(status);
			sql.append("',OPERATER_ID= '");
			sql.append(user.getId());
			sql.append("' where 1=1 and ID= ?");
			result = jdbcTemplate.update(sql.toString(), new Object[] { id });
		}
		return result;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: getByName
	 * </p>
	 * 
	 * @param campaign_name
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.model.operation.dao.ICampaignConfirmDao#getByName(java.lang.String)
	 */
	@Override
	public Campaign getByName(String campaign_name) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from T_CAMPAIGN where CAMPAIGN_NAME = ?");
		List<Campaign> objects = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<Campaign>(Campaign.class), campaign_name);
		Campaign com = null;
		if (objects != null && objects.size() > 0) {
			com = (Campaign) objects.get(0);
			return com;
		}
		return null;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: getById
	 * </p>
	 * 
	 * @param campaign_id
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.model.ad.dao.ICampaignDao#getById(java.lang.String)
	 */
	@Override
	public Campaign getById(String campaign_id) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from T_CAMPAIGN where ID = ?");
		List<Campaign> objects = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<Campaign>(Campaign.class), campaign_id);
		Campaign com = null;
		if (objects != null && objects.size() > 0) {
			com = (Campaign) objects.get(0);
			return com;
		}
		return null;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findById
	 * </p>
	 * 
	 * @param id
	 * @return
	 * @see cn.adwalker.model.ad.dao.ICampaignDao#findById(java.lang.Long)
	 */
	@SuppressWarnings("deprecation")
	@Override
	public Long findById(Long id) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select income_money from t_campaign_confirm  where id = " + id);
		return jdbcTemplate.queryForLong(sql.toString());
	}
	/**
	 * (non-Javadoc)
	* <p>Title: getConfirmMode</p>
	* <p>Description:TODO</p>
	* @param id
	* @return
	* @throws Exception
	* @see cn.adwalker.model.ad.dao.ICampaignDao#getConfirmMode(java.lang.Long)
	 */
	@Override
	public Campaign getConfirmMode(Long id) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT t.confirm_mode FROM t_campaign_placement_rel l  LEFT JOIN  T_CAMPAIGN t ON l.campaign_id=t.id WHERE l.placement_id=?");
		List<Campaign> objects = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<Campaign>(Campaign.class), id);
		Campaign com = null;
		if (objects != null && objects.size() > 0) {
			com = (Campaign) objects.get(0);
			return com;
		}
		return null;
	}

}