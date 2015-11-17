package cn.adwalker.ad.control.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.adwalker.ad.control.domain.Application;
import cn.adwalker.ad.control.exception.DatabaseException;
@Repository("applicationDao")
public class ApplicationDao extends BaseDao<Application> {
	
	public ApplicationDao() {
		setClazz(Application.class);
	} 
	
	public List<Application> getApplicationList() throws DatabaseException {
		String sql = "SELECT id,appkey,os,name,create_time FROM t_application ";	
		return getAll(sql);
	}

	
	
}
