package cn.adwalker.model.operation.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import cn.adwalker.core.repository.impl.BaseDaoImpl;
import cn.adwalker.core.util.lang.ObjectUtils;
import cn.adwalker.model.finance.domain.IncomeMonthCampaign;
import cn.adwalker.model.operation.dao.IIosAndroidDayNumDao;
import cn.adwalker.model.operation.domain.PackageActivate;

/**
* <p>Title: IosAndroidDayNumDaoImpl</p>
* <p>Description:ios/android每天的数据</p>
* <p>Company: adwalker</p> 
* @author    lichuang
* @date       2014年11月14日
 */
@Repository("iosAndroidDayNumDao")
public class IosAndroidDayNumDaoImpl extends BaseDaoImpl<PackageActivate> implements IIosAndroidDayNumDao {
	
	public IosAndroidDayNumDaoImpl() {
		super();
		this.setTableName("T_IOS_ANDROID_DAY_NUM");
	}
	/**
	 * (non-Javadoc)
	* <p>Title: getById</p>
	* <p>Description:TODO</p>
	* @param ars
	* @return
	* @throws Exception
	* @see cn.adwalker.model.operation.dao.IIosAndroidDayNumDao#getById(java.lang.String)
	 */
	@Override
	public IncomeMonthCampaign getById(String ars) throws Exception {
		StringBuffer sb = new StringBuffer();
		 sb.append("select t.`campaign_id`,t.`charge_type` balance_model,SUM(t.`confirm_amount`) forecast_amount,t.price*SUM(t.`confirm_amount`) forecast_money,t.`price`,MIN(t.`static_date`) month_stat_date,MAX(t.`static_date`) month_end_date "
		 		+ " from t_ios_android_day_num t where t.id in(");
		 if(ObjectUtils.isNotEmpty(ars)){
			 sb.append(ars); 
			 sb.append(")"); 
		 }
		 sb.append(" GROUP BY t.campaign_id,t.charge_type,t.price"); 
		 List<IncomeMonthCampaign> list = (List<IncomeMonthCampaign>) jdbcTemplate.query(sb.toString(), new BeanPropertyRowMapper<IncomeMonthCampaign>(IncomeMonthCampaign.class));
		 IncomeMonthCampaign paa = new IncomeMonthCampaign();
			if (list != null && list.size() > 0) {
				paa = list.get(0);
			}
			return paa;
	}
	/**
	 * (non-Javadoc)
	* <p>Title: updateById</p>
	* <p>Description:TODO</p>
	* @param ars
	* @throws Exception
	* @see cn.adwalker.model.operation.dao.IIosAndroidDayNumDao#updateById(java.lang.String)
	 */
	@Override
	public void updateById(String ars,Long income_id) throws Exception {
		
		StringBuffer sb = new StringBuffer();
		if (ObjectUtils.isNotEmpty(ars)) {
			sb.append("update t_ios_android_day_num set STATUS= 2");
			sb.append(", balance_id= ");
			sb.append(income_id);
			sb.append(" where ID in( ");
			sb.append(ars);
			sb.append(")");
		}
		jdbcTemplate.update(sb.toString());
	}
	
	/**
	 * (non-Javadoc)
	* <p>Title: updateInCome</p>
	* <p>Description:TODO</p>
	* @param ars
	* @throws Exception
	* @see cn.adwalker.model.operation.dao.IIosAndroidDayNumDao#updateInCome(java.lang.String)
	 */
	@Override
	public void updateInCome(String ars,Integer status) throws Exception {
		StringBuffer sb = new StringBuffer();
		if (ObjectUtils.isNotEmpty(ars)) {
			sb.append("update t_ios_android_day_num set STATUS=");
			sb.append(status);
			sb.append(" where balance_id in( ");
			sb.append(ars);
			sb.append(")");
		}
		jdbcTemplate.update(sb.toString());
	}
	/**
	 * (non-Javadoc)
	* <p>Title: updateStatus</p>
	* <p>Description:TODO</p>
	* @param id
	* @param status
	* @throws Exception
	* @see cn.adwalker.model.operation.dao.IIosAndroidDayNumDao#updateStatus(java.lang.Long, int)
	 */
	@Override
	public void updateStatus(Long id, int status) throws Exception {
		StringBuffer sb = new StringBuffer();
		if (ObjectUtils.isNotEmpty(id)) {
			sb.append("update t_ios_android_day_num set STATUS=");
			sb.append(status);
			sb.append(" , balance_id=");
			sb.append("null");
			sb.append(" where balance_id in( ");
			sb.append(id);
			sb.append(")");
		}
		jdbcTemplate.update(sb.toString());
		
	}

}
