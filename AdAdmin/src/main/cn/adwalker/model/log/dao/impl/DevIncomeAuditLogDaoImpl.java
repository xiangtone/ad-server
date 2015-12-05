package cn.adwalker.model.log.dao.impl;

import org.springframework.stereotype.Repository;

import cn.adwalker.core.repository.impl.BaseDaoImpl;
import cn.adwalker.model.log.dao.IDevIncomeAuditLogDao;
import cn.adwalker.model.log.domain.DevIncomeAuditLog;

/**
 * <p>
 * Title: DevIncomeAuditLogDaoImpl
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-10-26
 */
@Repository
public class DevIncomeAuditLogDaoImpl extends BaseDaoImpl<DevIncomeAuditLog> implements IDevIncomeAuditLogDao {

	public DevIncomeAuditLogDaoImpl() {
		setTableName("T_LOG_DEV_INCOME_AUDIT");
	}

}
