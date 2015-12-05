package cn.adwalker.model.ad.dao.impl;

import org.springframework.stereotype.Repository;

import cn.adwalker.core.repository.impl.BaseDaoImpl;
import cn.adwalker.model.ad.dao.IPriceUpdateDao;
import cn.adwalker.model.ad.domain.CampaignPlacementRel;

/**
* <p>Title: PriceUpdateDaoImpl</p>
* <p>Description:TODO</p>
* <p>Company: adwalker</p> 
* @author    lichuang
* @date       2014年6月20日
 */
@Repository("priceUpdateDao")
public class PriceUpdateDaoImpl extends BaseDaoImpl<CampaignPlacementRel> implements IPriceUpdateDao {

	public PriceUpdateDaoImpl() {
		setTableName("T_PRICE_UPDATE_LOG");
	}

	
}
