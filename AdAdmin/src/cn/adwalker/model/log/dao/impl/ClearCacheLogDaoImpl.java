package cn.adwalker.model.log.dao.impl;

import org.springframework.stereotype.Repository;

import cn.adwalker.core.repository.impl.BaseDaoImpl;
import cn.adwalker.model.log.dao.IClearCacheLogDao;
import cn.adwalker.model.log.domain.ClearCacheLog;

/**
 * <p>
 * Title: ClearCacheLogDaoImpl
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-9-11
 */
@Repository
public class ClearCacheLogDaoImpl extends BaseDaoImpl<ClearCacheLog> implements IClearCacheLogDao {
	
	public ClearCacheLogDaoImpl() {
		setTableName("T_LOG_CLEAR_CACHE");
	}

}
