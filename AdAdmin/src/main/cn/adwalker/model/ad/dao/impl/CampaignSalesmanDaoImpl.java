/*
 * AdvDaoImpl.java
 *
 * Copyright 2011 1000CHI Software Technology Co.,Ltd
 * All rights reserved.
 * 
 * Created on 2011-11-30
 */
package cn.adwalker.model.ad.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import cn.adwalker.core.repository.impl.BaseDaoImpl;
import cn.adwalker.core.util.lang.ObjectUtils;
import cn.adwalker.model.ad.dao.ICampaignSalesmanDao;
import cn.adwalker.model.ad.domain.CampaignSalesman;

/**
 * <p>
 * Title: AdvSalesmanDaoImpl
 * </p>
 * <p>
 * Description:功能概述
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-12-4
 */
@Repository("advSalesmanDao")
public class CampaignSalesmanDaoImpl extends BaseDaoImpl<CampaignSalesman>
		implements ICampaignSalesmanDao {

	public CampaignSalesmanDaoImpl() {
		setTableName("t_campaign_salesman");
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: updateSalesmanServic
	 * </p>
	 * 
	 * @param advSalesmanVo
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.model.ad.dao.IAdvDao#updateSalesmanServic(cn.adwalker.admin.ad.form.AdvSalesmanForm.ad.vo.AdvSalesmanVo)
	 */
	@Override
	public long updateSalesman(CampaignSalesman advSalesman) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" update t_campaign_salesman set ");
		if (ObjectUtils.isNotEmpty(advSalesman.getName())) {
			sql.append("NAME = :name,");
		}
		if (ObjectUtils.isNotEmpty(advSalesman.getMobile())) {
			sql.append("MOBILE = :mobile,");
		}
		if (advSalesman.getQq() != null) {
			sql.append("QQ = :qq,");
		}
		if (ObjectUtils.isNotEmpty(advSalesman.getArea_type())) {
			sql.append("AREA_TYPE = :area_type");
		}
		sql.append(" where ADV_ID=:adv_id");
		return update(sql.toString(), advSalesman);
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findAdvInformation
	 * </p>
	 * 
	 * @param advId
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.model.ad.dao.IAdvDao#findAdvInformation(java.lang.Long)
	 */
	public CampaignSalesman findSalesmanInfo(Long advId) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from t_campaign_salesman  where 1=1 and adv_id= ?");
		List<CampaignSalesman> list = (List<CampaignSalesman>) jdbcTemplate
				.query(sql.toString(),
						new BeanPropertyRowMapper<CampaignSalesman>(
								CampaignSalesman.class), advId);
		CampaignSalesman advSalesman = new CampaignSalesman();
		if (list != null && list.size() > 0) {
			advSalesman = list.get(0);
		}
		return advSalesman;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: all
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.model.ad.dao.ICampaignSalesmanDao#all()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<CampaignSalesman> all() throws Exception {
		List<CampaignSalesman> list = null;
		// 查询销售根据广告主所在区域控制暂时给去掉了and area_type=?
		list = (List<CampaignSalesman>) findAll(
				"select  * from t_campaign_salesman where status>=?",
				new Object[] { 0 }, CampaignSalesman.class);
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Long getSalesmanIdBySysUser(Long id) throws Exception {
		Long l = null;
		List<CampaignSalesman> list = (List<CampaignSalesman>) this.findAll(
				"select * from t_campaign_salesman where sys_user_id=?",
				new Object[] { id }, CampaignSalesman.class);
		if (list != null && list.size() == 1) {
			l = list.get(0).getId();
		}
		return l;
	}
}
