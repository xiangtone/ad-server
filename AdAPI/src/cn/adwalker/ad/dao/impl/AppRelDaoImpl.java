package cn.adwalker.ad.dao.impl;


import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Repository;

import cn.adwalker.ad.dao.IAppRelDao;


@Repository("appRelDao")
public class AppRelDaoImpl implements IAppRelDao {
	
	private static final Logger logger = LoggerFactory.getLogger(AppRelDaoImpl.class);

	@Resource
	private SimpleJdbcTemplate simpleJdbcTemplate;



	@Override
	public String fingChangDouUrl(String appId) {
		StringBuffer sql = new StringBuffer();
		sql.append("select response_url from T_COOPERATION_APP_REL where app_id = ? ");
		try {
			return simpleJdbcTemplate.queryForObject(sql.toString(),String.class,new Object[]{appId});
		} catch (Exception e) {
			logger.error("获取合作伙伴回调地址失败,应用id:"+appId+e.getLocalizedMessage());
			return null;
		}
	
	}

	

	
}
