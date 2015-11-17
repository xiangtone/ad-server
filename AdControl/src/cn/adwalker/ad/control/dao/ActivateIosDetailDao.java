package cn.adwalker.ad.control.dao;

import org.springframework.stereotype.Repository;

import cn.adwalker.ad.control.domain.ActivateNumDetailIos;
import cn.adwalker.ad.control.exception.DatabaseException;


@Repository("activateIosDetailDao")
public class ActivateIosDetailDao extends BaseDao<ActivateNumDetailIos>{
	
	public ActivateIosDetailDao() {
		setClazz(ActivateNumDetailIos.class);
	}
	
	public Long insert(ActivateNumDetailIos activateNumDetailIos) throws DatabaseException {
		StringBuilder sql = new StringBuilder("INSERT INTO t_ios_activate_num_detail(parent_id,media_id,static_date,type_id,create_time,`status`,blance_mode,out_price,media_type,click,activate,ad_id)");
		sql.append(" VALUES (:parent_id,:media_id,:static_date,:type_id,:create_time,:status,:blance_mode,:out_price,:media_type,:click,:activate,:ad_id)");
		return save(sql.toString(),activateNumDetailIos);
	}
}
