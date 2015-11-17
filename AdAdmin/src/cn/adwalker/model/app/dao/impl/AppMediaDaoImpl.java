/*
 * AdvDaoImpl.java
 *
 * Copyright 2011 1000CHI Software Technology Co.,Ltd
 * All rights reserved.
 * 
 * Created on 2011-11-30
 */
package cn.adwalker.model.app.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import cn.adwalker.core.repository.impl.BaseDaoImpl;
import cn.adwalker.core.util.lang.ObjectUtils;
import cn.adwalker.model.app.dao.IAppMediaDao;
import cn.adwalker.model.app.domain.AppMedia;

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
@Repository
public class AppMediaDaoImpl extends BaseDaoImpl<AppMedia> implements
		IAppMediaDao {

	public AppMediaDaoImpl() {
		setTableName("t_app_media");
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
	public long updateSalesman(AppMedia advSalesman) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" update t_app_media set ");
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
	public AppMedia findSalesmanInfo(Long advId) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from t_app_media  where 1=1 and adv_id= ?");
		List<AppMedia> list = (List<AppMedia>) jdbcTemplate.query(sql
				.toString(),
				new BeanPropertyRowMapper<AppMedia>(AppMedia.class), advId);
		AppMedia advSalesman = new AppMedia();
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
	 * @see cn.adwalker.model.ad.dao.IAppMediaDao#all()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<AppMedia> all() throws Exception {
		List<AppMedia> list = null;
		// 查询销售根据广告主所在区域控制暂时给去掉了and area_type=?
		list = (List<AppMedia>) findAll(
				"select  * from t_app_media where status>=?",
				new Object[] { 0 }, AppMedia.class);
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Long getSalesmanIdBySysUser(Long id) throws Exception {
		Long l = null;
		List<AppMedia> list = (List<AppMedia>) this.findAll(
				"select * from t_app_media where sys_user_id=?",
				new Object[] { id }, AppMedia.class);
		if (list != null && list.size() == 1) {
			l = list.get(0).getId();
		}
		return l;
	}

	@SuppressWarnings("unchecked")
	@Override
	public AppMedia getEntityBySysUser(Long id) throws Exception {
		AppMedia entity=null;
		if (id!=null) {
			List<AppMedia> list = (List<AppMedia>) this.findAll(
					"select * from t_app_media where sys_user_id=?",
					new Object[] { id }, AppMedia.class);
			if (list != null && list.size() == 1) {
				Long l = list.get(0).getId();
				if (l!=null) {
					entity=this.get(l, AppMedia.class);
				}
			}
		}
		
		
		return entity;
	}
}
