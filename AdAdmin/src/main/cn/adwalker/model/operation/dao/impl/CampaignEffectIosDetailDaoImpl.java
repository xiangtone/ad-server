package cn.adwalker.model.operation.dao.impl;

import org.springframework.stereotype.Repository;

import cn.adwalker.core.repository.impl.BaseDaoImpl;
import cn.adwalker.model.operation.dao.ICampaignEffectIosDetailDao;
import cn.adwalker.model.operation.domain.CampaignEffectIosDetail;

/**
 * 
 * <p>
 * Title: CampaignEffectIosDaoImpl
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-9-12
 */
@Repository
public class CampaignEffectIosDetailDaoImpl extends BaseDaoImpl<CampaignEffectIosDetail> implements ICampaignEffectIosDetailDao {

	public CampaignEffectIosDetailDaoImpl() {
		setTableName("T_IOS_EFFECT_DETAIL");
	}

}