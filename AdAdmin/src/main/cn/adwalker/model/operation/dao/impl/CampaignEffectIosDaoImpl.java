package cn.adwalker.model.operation.dao.impl;

import org.springframework.stereotype.Repository;

import cn.adwalker.core.repository.impl.BaseDaoImpl;
import cn.adwalker.model.operation.dao.ICampaignEffectIosDao;
import cn.adwalker.model.operation.domain.CampaignEffectIos;

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
public class CampaignEffectIosDaoImpl extends BaseDaoImpl<CampaignEffectIos> implements ICampaignEffectIosDao {

	public CampaignEffectIosDaoImpl() {
		setTableName("T_IOS_EFFECT");
	}

}