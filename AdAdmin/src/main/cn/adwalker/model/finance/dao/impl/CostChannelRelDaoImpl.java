/**
 * <p>Title: CostChannelRelDaoImpl.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author hadoop
 * @date 2013-11-27
 * @version 1.0
 */
package cn.adwalker.model.finance.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import cn.adwalker.core.bean.SimpleBean;
import cn.adwalker.core.exception.SysException;
import cn.adwalker.core.repository.impl.BaseDaoImpl;
import cn.adwalker.model.finance.dao.ICostChannelRelDao;
import cn.adwalker.model.finance.domain.BalanceChannel;
import cn.adwalker.model.finance.domain.CostChannelRel;

/**
 * <p>
 * Title: CostChannelRelDaoImpl
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-11-27
 */
@Repository
public class CostChannelRelDaoImpl extends BaseDaoImpl<CostChannelRel>
		implements ICostChannelRelDao {

	public CostChannelRelDaoImpl() {
		setTableName("T_FINANCE_COST_CHANNEL_REL");
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: tranceDate
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param long1
	 * @param long2
	 * @param string
	 * @param string2
	 * @throws Exception
	 * @see cn.adwalker.model.finance.dao.ICostChannelRelDao#tranceDate(java.lang.Long,
	 *      java.lang.Long, java.lang.String, java.lang.String)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public void tranceDate(BalanceChannel vo) throws Exception {
		if (vo != null) {
			List<SimpleBean<Long>> list = (List<SimpleBean<Long>>) findAll(
					"select id as obj from V_AD_EFFECT v where v.campaign_id=? and media_id=? and static_date>=? and static_date<=?",
					new Object[] { vo.getCampaign_id(), vo.getChannel_id(),
							vo.getStart_date(), vo.getEnd_date() },
					SimpleBean.class);
			if (list == null || list.size() <= 0) {
				throw new SysException("成本对应的明细未找到");
			}

			for (SimpleBean<Long> bean : list) {
				CostChannelRel entity = new CostChannelRel();
				entity.setBalance_id(vo.getId());
				entity.setDetail_id(bean.getObj());
				entity.setCreate_time(new Date());
				entity.setStatus(0);
				insert(entity);

			}
		}

	}
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public void tranceIosDate(BalanceChannel vo) throws Exception {
		if (vo != null) {
			List<SimpleBean<Long>> list = (List<SimpleBean<Long>>) findAll(
					"select id as obj from v_ad_ios_effect v where v.campaign_id=? and media_id=? and static_date>=? and static_date<=?",
					new Object[] { vo.getCampaign_id(), vo.getChannel_id(),
							vo.getStart_date(), vo.getEnd_date() },
					SimpleBean.class);
			if (list == null || list.size() <= 0) {
				throw new SysException("成本对应的明细未找到");
			}

			for (SimpleBean<Long> bean : list) {
				CostChannelRel entity = new CostChannelRel();
				entity.setBalance_id(vo.getId());
				entity.setDetail_id(bean.getObj());
				entity.setCreate_time(new Date());
				entity.setStatus(0);
				entity.setOs("ios");
				insert(entity);

			}
		}
	}

}
