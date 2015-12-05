package cn.adwalker.ad.admin.finance.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.adwalker.ad.admin.finance.bean.CampaignConfirmbean;
import cn.adwalker.ad.admin.finance.service.ICampaignConfirmService;
import cn.adwalker.ad.admin.finance.vo.CampaignConfirmSumVo;
import cn.adwalker.ad.admin.finance.vo.CampaignConfirmVo;
import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.util.lang.ObjectUtils;
import cn.adwalker.model.ad.dao.ICampaignDao;
import cn.adwalker.model.operation.dao.ICampaignConfirmDao;
import cn.adwalker.model.operation.domain.CampaignConfirmSum;
import cn.adwalker.model.operation.domain.CampaignIncomeCost;

/**
 * <p>
 * Title: CampaignConfirmServiceImpl
 * </p>
 * <p>
 * Description:活动确认数service实现类
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-6-8
 */
@Service("finance.campaignConfirmService")
public class CampaignConfirmServiceImpl implements
		ICampaignConfirmService {

	@Resource
	private ICampaignConfirmDao campaignConfirmDao;

	@Resource
	private ICampaignDao campaignDao;

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findCampaignList
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param bean
	 * @param pageInfo
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.operation.service.IOperationCampaignConfirmService#findCampaignList(cn.adwalker.ad.admin.operation.bean.CampaignConfirmbean,
	 *      cn.adwalker.core.page.IPageInfo)
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.NEVER)
	@Override
	public List<CampaignConfirmVo> findByPage(CampaignConfirmbean bean,
			IPageInfo pageInfo) throws Exception {
		List<Object> list = new ArrayList<Object>();
		StringBuilder sb = new StringBuilder(
				" T_CAMPAIGN_CONFIRM a left join  V_CAMPAIGN t  on a.campaign_id=t.ID left join T_ADV_SALESMAN s on t.adv_id=s.adv_id left  join  t_sys_user u  on a.Manager_id=u.id  where a.STATUS>=0");
		if (bean != null) {
			if (ObjectUtils.isNotEmpty(bean.getMonth_stat_date())) {
				sb.append(" and a.month_stat_date>= '");
				sb.append(bean.getMonth_stat_date());
				sb.append("'");
			}
			if (ObjectUtils.isNotEmpty(bean.getMonth_end_date())) {
				sb.append(" and a.month_end_date<= '");
				sb.append(bean.getMonth_end_date());
				sb.append("'");
			}
			if (ObjectUtils.isNotEmpty(bean.getCampaign_id())) {
				sb.append(" and a.campaign_id like '%");
				sb.append(bean.getCampaign_id());
				sb.append("%'");
			}
			if (ObjectUtils.isNotEmpty(bean.getStatus())) {
				sb.append(" and a.STATUS =");
				sb.append(bean.getStatus());
			}
			if (ObjectUtils.isNotEmpty(bean.getAdv_id())) {
				sb.append(" and t.adv_id like '%");
				sb.append(bean.getAdv_id());
				sb.append("%'");
			}
			if (!StringUtils.isEmpty(bean.getCampaign_name())) {
				sb.append(" and upper(t.CAMPAIGN_NAME) like '%");
				sb.append(bean.getCampaign_name().trim().toUpperCase());
				sb.append("%'");
			}

		}
		return (List<CampaignConfirmVo>) campaignConfirmDao
				.findByPage(
						"a.*,t.campaign_name, t.charge_type,t.os,t.adv_id,u.real_name as create_user_name,s.name,s.area_type",
						sb.toString(), list.toArray(),
						" order by a.create_time desc ",
						CampaignConfirmVo.class, pageInfo);
	}
	
	

	

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: updateStatus
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param id
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.operation.service.IOperationCampaignConfirmService#updateStatus(java.lang.Long)
	 */
	@Override
	public void updateStatus(Long id) throws Exception {
		CampaignIncomeCost caIn = new CampaignIncomeCost();
		caIn = campaignConfirmDao.findById(id);
		if (caIn != null && caIn.equals("")) {
			caIn.setId(id);
		} else {
			caIn.setId(id);
			caIn.setFocast_money(0d);
			caIn.setSys_cost(0d);
		}
		//campaignConfirmDao.updateStatus(caIn);
	}
	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: getEntering
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.operation.service.IOperationCampaignConfirmService#getEntering(java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public CampaignConfirmVo getEntering(Long id) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select e.*,t.campaign_name  from t_campaign_confirm e left join  V_CAMPAIGN t  on e.campaign_id=t.ID where 1=1 and e.id= ?");
		List<CampaignConfirmVo> list = (List<CampaignConfirmVo>) campaignDao
				.findAll(sql.toString(), new Object[] { id },
						CampaignConfirmVo.class);
		CampaignConfirmVo c = new CampaignConfirmVo();
		if (list != null && list.size() > 0) {
			c = list.get(0);
		}
		return c;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findSum
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param bean
	 * @return
	 * @see cn.adwalker.ad.admin.operation.service.IOperationCampaignConfirmService#findSum(cn.adwalker.ad.admin.operation.bean.CampaignConfirmbean)
	 */
	@Override
	public CampaignConfirmSumVo findSum(CampaignConfirmbean bean) {
		List<CampaignConfirmSum> list;
		list = this.getSumList(bean);
		Integer sum_income_amount = 0;// 统计 总确认数
		Double sum_income_money = 0d;// 确认 总金额
		Integer sum_forecast_amount = 0;// 预确认数
		if (list != null && list.size() > 0) {
			sum_income_money = list.get(0).getSum_income_money();
			sum_income_amount =0;
			sum_forecast_amount = list.get(0).getSum_forecast_amount();
		}
		return new CampaignConfirmSumVo(sum_income_money, sum_income_amount,
				sum_forecast_amount);
	}

	@SuppressWarnings("unchecked")
	private List<CampaignConfirmSum> getSumList(CampaignConfirmbean bean) {
		List<CampaignConfirmSum> list = null;
		List<Object> param = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer(
				"select sum(a.income_amount) as sum_income_amount,sum(a.income_money) as sum_income_money,sum(a.forecast_amount) as sum_forecast_amount from T_CAMPAIGN_CONFIRM a left join  V_CAMPAIGN t  on a.campaign_id=t.ID left join T_ADV_SALESMAN s on t.adv_id=s.adv_id left  join  t_sys_user u  on a.Manager_id=u.id  where a.STATUS>=0");
		if (bean != null) {
			if (ObjectUtils.isNotEmpty(bean.getMonth_stat_date())) {
				sb.append(" and a.month_stat_date>= '");
				sb.append(bean.getMonth_stat_date());
				sb.append("'");
			}
			if (ObjectUtils.isNotEmpty(bean.getMonth_end_date())) {
				sb.append(" and a.month_end_date<= '");
				sb.append(bean.getMonth_end_date());
				sb.append("'");
			}
			if (ObjectUtils.isNotEmpty(bean.getCampaign_id())) {
				sb.append(" and a.campaign_id like '%");
				sb.append(bean.getCampaign_id());
				sb.append("%'");
			}
			if (ObjectUtils.isNotEmpty(bean.getStatus())) {
				sb.append(" and a.STATUS =");
				sb.append(bean.getStatus());
			}

			if (ObjectUtils.isNotEmpty(bean.getInvoice_id())) {
				sb.append(" and a.INVOICE_ID =");
				sb.append(bean.getInvoice_id());
			}

			if (ObjectUtils.isNotEmpty(bean.getAdv_id())) {
				sb.append(" and t.adv_id like '%");
				sb.append(bean.getAdv_id());
				sb.append("%'");
			}
			if (!StringUtils.isEmpty(bean.getCampaign_name())) {
				sb.append(" and upper(t.CAMPAIGN_NAME) like '%");
				sb.append(bean.getCampaign_name().trim().toUpperCase());
				sb.append("%'");
			}

		}
		try {
			list = (List<CampaignConfirmSum>) this.campaignConfirmDao.findAll(
					sb.toString(), param.toArray(), CampaignConfirmSum.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: getInfo
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param invoice_id
	 * @param pageInfo
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.operation.service.IOperationCampaignConfirmService#getInfo(java.lang.Long,
	 *      cn.adwalker.core.page.IPageInfo)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<CampaignConfirmVo> getInfo(Long invoice_id, IPageInfo pageInfo)
			throws Exception {

		List<Object> list = new ArrayList<Object>();
		StringBuilder sb = new StringBuilder(
				" T_CAMPAIGN_CONFIRM a left join  V_CAMPAIGN t  on a.campaign_id=t.ID left join T_ADV_SALESMAN s on t.adv_id=s.adv_id left  join  t_sys_user u  on a.Manager_id=u.id  where 1=1");

		if (ObjectUtils.isNotEmpty(invoice_id)) {
			sb.append(" and a.INVOICE_ID =");
			sb.append(invoice_id);
		}

		return (List<CampaignConfirmVo>) campaignConfirmDao
				.findByPage(
						"a.*,t.campaign_name, t.charge_type,t.os,t.adv_id,u.real_name as create_user_name,s.name,s.area_type",
						sb.toString(), list.toArray(),
						" order by a.create_time desc ",
						CampaignConfirmVo.class, pageInfo);
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findAll
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.operation.service.IOperationCampaignConfirmService#findAll(cn.adwalker.ad.admin.operation.bean.CampaignConfirmbean)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<CampaignConfirmVo> findAll(CampaignConfirmbean bean)
			throws Exception {
		List<Object> list = new ArrayList<Object>();
		StringBuilder sb = new StringBuilder(
				" T_CAMPAIGN_CONFIRM a left join  V_CAMPAIGN t  on a.campaign_id=t.ID left join T_ADV_SALESMAN s on t.adv_id=s.adv_id left  join  t_sys_user u  on a.Manager_id=u.id  where 1=1");
		if (bean != null) {
			if (ObjectUtils.isNotEmpty(bean.getMonth_stat_date())) {
				sb.append(" and a.month_stat_date>= '");
				sb.append(bean.getMonth_stat_date());
				sb.append("'");
			}
			if (ObjectUtils.isNotEmpty(bean.getMonth_end_date())) {
				sb.append(" and a.month_end_date<= '");
				sb.append(bean.getMonth_end_date());
				sb.append("'");
			}
			if (ObjectUtils.isNotEmpty(bean.getCampaign_id())) {
				sb.append(" and a.campaign_id like '%");
				sb.append(bean.getCampaign_id());
				sb.append("%'");
			}
			if (ObjectUtils.isNotEmpty(bean.getStatus())) {
				sb.append(" and a.STATUS =");
				sb.append(bean.getStatus());
			}
			if (ObjectUtils.isNotEmpty(bean.getAdv_id())) {
				sb.append(" and t.adv_id like '%");
				sb.append(bean.getAdv_id());
				sb.append("%'");
			}
			if (!StringUtils.isEmpty(bean.getCampaign_name())) {
				sb.append(" and upper(t.CAMPAIGN_NAME) like '%");
				sb.append(bean.getCampaign_name().trim().toUpperCase());
				sb.append("%'");
			}

		}
		return (List<CampaignConfirmVo>) campaignConfirmDao
				.findAll(
						"a.*,t.campaign_name, t.charge_type,t.os,t.adv_id,u.real_name as create_user_name,s.name,s.area_type",
						sb.toString(), list.toArray(),
						" order by a.create_time desc ",
						CampaignConfirmVo.class);
	}
}
