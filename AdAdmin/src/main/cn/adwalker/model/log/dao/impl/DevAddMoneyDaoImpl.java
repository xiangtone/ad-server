/**
 * <p>Title: DevAddMoneyDaoImpl.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author hadoop
 * @date 2013-12-2
 * @version 1.0
 */
package cn.adwalker.model.log.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import cn.adwalker.core.repository.impl.BaseDaoImpl;
import cn.adwalker.model.log.dao.IDevAddMoneyDao;
import cn.adwalker.model.log.domain.DevAddMoneyLog;

/**
 * <p>
 * Title: DevAddMoneyDaoImpl
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-12-2
 */
@Repository
public class DevAddMoneyDaoImpl extends BaseDaoImpl<DevAddMoneyLog> implements
		IDevAddMoneyDao {

	public DevAddMoneyDaoImpl() {
		setTableName("T_LOG_DEV_ADD_MONEY");
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: batchInsert
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param logList
	 * @see cn.adwalker.model.log.dao.IDevAddMoneyDao#batchInsert(java.util.List)
	 */
	@Override
	public void batchInsert(List<DevAddMoneyLog> logList) {
		if (logList != null && logList.size() > 0) {
			StringBuffer sql = new StringBuffer();
			sql.append("INSERT INTO T_LOG_DEV_ADD_MONEY(DEV_ID,CREATE_TIME,INCOME_ID,MONEY,OLD_MONEY) VALUES (?, ?, ?, ?, ?)");
			List<Object[]> parameters = new ArrayList<Object[]>();
			for (DevAddMoneyLog log : logList) {
				Object[] objects = { log.getDev_id(), new Date(),
						log.getIncome_id(), log.getMoney(), log.getOld_money() };
				parameters.add(objects);
			}
			jdbcTemplate.batchUpdate(sql.toString(), parameters);
		}

	}

}
