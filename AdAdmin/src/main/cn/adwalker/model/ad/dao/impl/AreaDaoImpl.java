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

import org.springframework.stereotype.Repository;

import cn.adwalker.core.repository.impl.BaseDaoImpl;
import cn.adwalker.model.ad.dao.IAreaDao;
import cn.adwalker.model.ad.domain.Area;
import cn.adwalker.model.ad.domain.CampaignSalesman;

/**
* <p>Title: AreaDaoImpl</p>
* <p>Description:功能概述</p>
* <p>Company: emar</p> 
* @author   lichuang
* @date       2014-3-12
 */
@Repository("areaDao")
public class AreaDaoImpl extends BaseDaoImpl<CampaignSalesman>
		implements IAreaDao {

	public AreaDaoImpl() {
		setTableName("t_area_name");
	}

	/**
	 * (non-Javadoc)
	* <p>Title: findByArea</p>
	* <p>Description:TODO</p>
	* @return
	* @throws Exception
	* @see com.emar.escore.model.ad.dao.IAreaDao#findByArea()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Area> findByArea() throws Exception {
		List<Area> list = null;
		list = (List<Area>) findAll(
				"select * from t_area_name where 1=1 and status>=? ",
				new Object[] { 0 }, Area.class);
		return list;
	}
}
