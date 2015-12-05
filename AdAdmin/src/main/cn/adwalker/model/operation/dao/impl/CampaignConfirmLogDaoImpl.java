package cn.adwalker.model.operation.dao.impl;

import org.springframework.stereotype.Repository;

import cn.adwalker.core.repository.impl.BaseDaoImpl;
import cn.adwalker.model.operation.dao.ICampaignConfirmLogDao;
import cn.adwalker.model.operation.domain.CampaignConfirmLog;

/**
 * <p>
 * Title: OperationHistoryRecoreDaoDaoImpl
 * </p>
 * <p>
 * Description:历史记录dao实现类
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-10-24
 */

@Repository("historyRecoreDao")
public class CampaignConfirmLogDaoImpl extends BaseDaoImpl<CampaignConfirmLog> implements ICampaignConfirmLogDao {
	
	public CampaignConfirmLogDaoImpl() {
		super();
		this.setTableName("T_LOG_CAMPAIGN_CONFIRM");
	}
	
}
