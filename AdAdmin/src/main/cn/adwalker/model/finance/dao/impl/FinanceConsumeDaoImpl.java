package cn.adwalker.model.finance.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import cn.adwalker.core.repository.impl.BaseDaoImpl;
import cn.adwalker.model.finance.dao.IFinanceConsumeDao;
import cn.adwalker.model.finance.domain.AdConsumeDetailVo;

/**
 * 
 * <p>
 * Title: AdvConsumeDetailDaoImpl
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-1-15
 */
@SuppressWarnings("rawtypes")
@Repository("financeConsumeDao")
public class FinanceConsumeDaoImpl extends BaseDaoImpl implements IFinanceConsumeDao {

	/**
	 * 获取广告明细表对象
	 * 
	 * @param detail
	 * @return
	 */
	@Override
	public AdConsumeDetailVo checkDevConsumeDetail(AdConsumeDetailVo detail) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from T_FINANCE_AD_EFFECT_DETAIL t " + " where t.adv_id = " + detail.getAdv_Id() + " and t.ad_id=" + detail.getAd_Id() + " and t.stat_date = '" + detail.getStatDate() + "'");
		List<AdConsumeDetailVo> list = new ArrayList<AdConsumeDetailVo>();
		list = (List<AdConsumeDetailVo>) this.jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<AdConsumeDetailVo>(AdConsumeDetailVo.class));
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

}
