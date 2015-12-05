package cn.adwalker.ad.admin.finance.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.adwalker.ad.admin.finance.bean.AchievementReportbean;
import cn.adwalker.ad.admin.finance.bean.IncomeBean;
import cn.adwalker.ad.admin.finance.bean.InvoiceBean;
import cn.adwalker.ad.admin.finance.bean.PaymentBean;
import cn.adwalker.ad.admin.finance.service.IInvoiceService;
import cn.adwalker.ad.admin.finance.vo.AchievementReportSumVo;
import cn.adwalker.ad.admin.finance.vo.IncomeVo;
import cn.adwalker.ad.admin.finance.vo.InvoiceVo;
import cn.adwalker.ad.admin.finance.vo.PaymentVo;
import cn.adwalker.ad.admin.operation.vo.AchievementReportSum;
import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.util.lang.ObjectUtils;
import cn.adwalker.model.operation.dao.ICampaignConfirmDao;

@Service("invoiceService")
public class InvoiceService implements IInvoiceService {

	@Resource
	private ICampaignConfirmDao campaignConfirmDao;

	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.NEVER)
	@Override
	public List<InvoiceVo> findByPage(InvoiceBean bean, IPageInfo pageInfo) throws Exception {
		List<Object> params = new ArrayList<Object>();
		StringBuilder sb = new StringBuilder(
				" t_campaign_confirm n LEFT JOIN v_campaign t ON n.campaign_id= t.ID LEFT JOIN t_campaign_salesman s ON t.salesman_id = s.id where n.status=3");
		if (bean != null) {
			if (ObjectUtils.isNotEmpty(bean.getMonth_stat_date())) {
				sb.append(" and n.month_stat_date>= '");
				sb.append(bean.getMonth_stat_date());
				sb.append("'");
			}
			if (ObjectUtils.isNotEmpty(bean.getMonth_end_date())) {
				sb.append(" and n.month_end_date<= '");
				sb.append(bean.getMonth_end_date());
				sb.append("'");
			}
			if (ObjectUtils.isNotEmpty(bean.getAdv())) {
				sb.append(" and (upper(t.company_name)  like ? or t.adv_id =? )");
				params.add("%" + bean.getCompany_name().toUpperCase() + "%");
				params.add(bean.getAdv_id());
			}
			if (ObjectUtils.isNotEmpty(bean.getCampaign_name())) {
				sb.append(" and (upper(t.campaign_name)  like ? or n.campaign_id =? )");
				params.add("%" + bean.getCampaign_name().toUpperCase() + "%");
				params.add(bean.getCampaign_id());
			}
			if (ObjectUtils.isNotEmpty(bean.getInvoice_status())) {
				sb.append(" and n.invoice_status =?");
				params.add(bean.getInvoice_status());
			}
			if (ObjectUtils.isNotEmpty(bean.getBalance_model())) {
				sb.append(" and n.balance_model=?");
				params.add(bean.getBalance_model());
			}
			if (ObjectUtils.isNotEmpty(bean.getOs())) {
				sb.append(" and t.os =?");
				params.add(bean.getOs());
			}
			if (ObjectUtils.isNotEmpty(bean.getSales())) {
				sb.append(" and s.name like ?");
				params.add("%" + bean.getSales()+ "%");
			}
		}
		return (List<InvoiceVo>) campaignConfirmDao.findByPage(" n.* ,t.company_name,t.campaign_name,t.os,s.name", sb.toString(), params.toArray(), " order by n.create_time desc ", InvoiceVo.class, pageInfo);
	}

	@Override
	public int[] updateInvoice(List<Object[]> objList) throws Exception {
		return campaignConfirmDao.updateInvoice(objList);
	}

	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.NEVER)
	@Override
	public List<PaymentVo> findByPage(PaymentBean bean, IPageInfo pageInfo) throws Exception {
		List<Object> params = new ArrayList<Object>();
		StringBuilder sb = new StringBuilder(
				" t_campaign_confirm n LEFT JOIN v_campaign t ON n.campaign_id= t.ID LEFT JOIN t_campaign_salesman s ON t.salesman_id = s.id where n.status=3");
		if (bean != null) {
			if (ObjectUtils.isNotEmpty(bean.getMonth_stat_date())) {
				sb.append(" and n.month_stat_date>= '");
				sb.append(bean.getMonth_stat_date());
				sb.append("'");
			}
			if (ObjectUtils.isNotEmpty(bean.getMonth_end_date())) {
				sb.append(" and n.month_end_date<= '");
				sb.append(bean.getMonth_end_date());
				sb.append("'");
			}
			if (ObjectUtils.isNotEmpty(bean.getAdv())) {
				sb.append(" and (upper(t.company_name)  like ? or t.adv_id =? )");
				params.add("%" + bean.getCompany_name().toUpperCase() + "%");
				params.add(bean.getAdv_id());
			}
			if (ObjectUtils.isNotEmpty(bean.getCampaign_name())) {
				sb.append(" and (upper(t.campaign_name)  like ? or n.campaign_id =? )");
				params.add("%" + bean.getCampaign_name().toUpperCase() + "%");
				params.add(bean.getCampaign_id());
			}
			if (ObjectUtils.isNotEmpty(bean.getPayment_status())) {
				sb.append(" and n.payment_status =?");
				params.add(bean.getPayment_status());
			}
			if (ObjectUtils.isNotEmpty(bean.getBalance_model())) {
				sb.append(" and n.balance_model=?");
				params.add(bean.getBalance_model());
			}
			if (ObjectUtils.isNotEmpty(bean.getOs())) {
				sb.append(" and t.os =?");
				params.add(bean.getOs());
			}
			if (ObjectUtils.isNotEmpty(bean.getSales())) {
				sb.append(" and s.name like ?");
				params.add("%" + bean.getSales()+ "%");
			}
		}
		return (List<PaymentVo>) campaignConfirmDao.findByPage(" n.* ,t.company_name,t.campaign_name,t.os,s.name", sb.toString(), params.toArray(), " order by n.create_time desc ", PaymentVo.class, pageInfo);
	}

	@Override
	public int[] updatePayment(List<Object[]> objList) throws Exception {
		return campaignConfirmDao.updatePayment(objList);
	}

	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.NEVER)
	@Override
	public List<IncomeVo> findByPage(IncomeBean bean, IPageInfo pageInfo) throws Exception {
		List<Object> params = new ArrayList<Object>();
		StringBuilder sb = new StringBuilder(
				" t_campaign_confirm n LEFT JOIN v_campaign t ON n.campaign_id= t.ID LEFT JOIN t_campaign_salesman s ON t.salesman_id = s.id where n.status=3");
		if (bean != null) {
			if (ObjectUtils.isNotEmpty(bean.getMonth_stat_date())) {
				sb.append(" and n.month_stat_date>= '");
				sb.append(bean.getMonth_stat_date());
				sb.append("'");
			}
			if (ObjectUtils.isNotEmpty(bean.getMonth_end_date())) {
				sb.append(" and n.month_end_date<= '");
				sb.append(bean.getMonth_end_date());
				sb.append("'");
			}
			if (ObjectUtils.isNotEmpty(bean.getAdv())) {
				sb.append(" and (upper(t.company_name)  like ? or t.adv_id =? )");
				params.add("%" + bean.getCompany_name().toUpperCase() + "%");
				params.add(bean.getAdv_id());
			}
			if (ObjectUtils.isNotEmpty(bean.getCampaign_name())) {
				sb.append(" and (upper(t.campaign_name)  like ? or n.campaign_id =? )");
				params.add("%" + bean.getCampaign_name().toUpperCase() + "%");
				params.add(bean.getCampaign_id());
			}
			if (ObjectUtils.isNotEmpty(bean.getInvoice_status())) {
				sb.append(" and n.invoice_status =?");
				params.add(bean.getInvoice_status());
			}
			if (ObjectUtils.isNotEmpty(bean.getPayment_status())) {
				sb.append(" and n.payment_status =?");
				params.add(bean.getPayment_status());
			}
			if (ObjectUtils.isNotEmpty(bean.getBalance_model())) {
				sb.append(" and n.balance_model=?");
				params.add(bean.getBalance_model());
			}
			if (ObjectUtils.isNotEmpty(bean.getOs())) {
				sb.append(" and t.os =?");
				params.add(bean.getOs());
			}
			if (ObjectUtils.isNotEmpty(bean.getSales())) {
				sb.append(" and s.name like ?");
				params.add("%" + bean.getSales()+ "%");
			}
		}
		return (List<IncomeVo>) campaignConfirmDao.findByPage(" n.* ,t.company_name,t.campaign_name,t.os,s.name", sb.toString(), params.toArray(), " order by n.create_time desc ", IncomeVo.class, pageInfo);
	}
	
		/**
		 * (non-Javadoc)
		* <p>Title: findSum</p>
		* <p>Description:TODO</p>
		* @param bean
		* @return
		* @throws Exception
		* @see cn.adwalker.ad.admin.finance.service.IInvoiceService#findSum(cn.adwalker.ad.admin.finance.bean.IncomeBean)
		 */
	@Override
	public AchievementReportSumVo findSum(IncomeBean bean) throws Exception {

		List<AchievementReportSum> list;
		list = this.getSumList(bean);
		Double sum_income_money = 0d;// 确认 总金额
		Double sum_income_plm_money = 0d;// 平台 统计
		if (list != null && list.size() > 0) {
			sum_income_money = list.get(0).getSum_income_money();
			sum_income_plm_money = list.get(0).getSum_income_plm_money();
			
		}
		return new AchievementReportSumVo(sum_income_money,
				sum_income_plm_money);
	}
	
	@SuppressWarnings("unchecked")
	private List<AchievementReportSum> getSumList(IncomeBean bean) {
		List<AchievementReportSum> list = null;
		List<Object> params = new ArrayList<Object>();
		StringBuilder sb = new StringBuilder(
				"SELECT sum(n.forecast_money) sum_income_plm_money, sum(n.income_money) sum_income_money FROM t_campaign_confirm n LEFT JOIN v_campaign t ON n.campaign_id= t.ID LEFT JOIN t_campaign_salesman s ON t.salesman_id = s.id where n.status=3");
		if (bean != null) {
			if (ObjectUtils.isNotEmpty(bean.getMonth_stat_date())) {
				sb.append(" and n.month_stat_date>= '");
				sb.append(bean.getMonth_stat_date());
				sb.append("'");
			}
			if (ObjectUtils.isNotEmpty(bean.getMonth_end_date())) {
				sb.append(" and n.month_end_date<= '");
				sb.append(bean.getMonth_end_date());
				sb.append("'");
			}
			if (ObjectUtils.isNotEmpty(bean.getAdv())) {
				sb.append(" and (upper(t.company_name)  like ? or t.adv_id =? )");
				params.add("%" + bean.getCompany_name().toUpperCase() + "%");
				params.add(bean.getAdv_id());
			}
			if (ObjectUtils.isNotEmpty(bean.getCampaign_name())) {
				sb.append(" and (upper(t.campaign_name)  like ? or n.campaign_id =? )");
				params.add("%" + bean.getCampaign_name().toUpperCase() + "%");
				params.add(bean.getCampaign_id());
			}
			if (ObjectUtils.isNotEmpty(bean.getInvoice_status())) {
				sb.append(" and n.invoice_status =?");
				params.add(bean.getInvoice_status());
			}
			if (ObjectUtils.isNotEmpty(bean.getPayment_status())) {
				sb.append(" and n.payment_status =?");
				params.add(bean.getPayment_status());
			}
			if (ObjectUtils.isNotEmpty(bean.getBalance_model())) {
				sb.append(" and n.balance_model=?");
				params.add(bean.getBalance_model());
			}
			if (ObjectUtils.isNotEmpty(bean.getOs())) {
				sb.append(" and t.os =?");
				params.add(bean.getOs());
			}
			if (ObjectUtils.isNotEmpty(bean.getSales())) {
				sb.append(" and s.name like ?");
				params.add("%" + bean.getSales()+ "%");
			}
		}
		try {
			list = (List<AchievementReportSum>) this.campaignConfirmDao
					.findAll(sb.toString(), params.toArray(),
							AchievementReportSum.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
