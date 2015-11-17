package cn.adwalker.model.ad.dao.impl;

import org.springframework.stereotype.Repository;

import cn.adwalker.core.repository.impl.BaseDaoImpl;
import cn.adwalker.model.ad.dao.ICampaignPaymentsDao;
import cn.adwalker.model.ad.domain.CampaignPayments;

/**
 * 
 * <p>
 * Title: AdDaoImpl
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-4-9
 */
@Repository
public class CampaignPaymentsDaoImpl extends BaseDaoImpl<CampaignPayments> implements ICampaignPaymentsDao {

	public CampaignPaymentsDaoImpl() {
		setTableName("t_campaign_payments");
	}

}
