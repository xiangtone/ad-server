package cn.adwalker.ad.admin.finance.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.adwalker.ad.admin.finance.bean.CostBean;
import cn.adwalker.ad.admin.finance.bean.IncomeBean;
import cn.adwalker.ad.admin.finance.bean.InvoiceChannelBean;
import cn.adwalker.ad.admin.finance.bean.PaymentChannelBean;
import cn.adwalker.ad.admin.finance.service.IInvoiceChannelService;
import cn.adwalker.ad.admin.finance.vo.AchievementReportSumVo;
import cn.adwalker.ad.admin.finance.vo.CostVo;
import cn.adwalker.ad.admin.finance.vo.InvoiceChannelVo;
import cn.adwalker.ad.admin.finance.vo.PaymentChannelVo;
import cn.adwalker.ad.admin.operation.vo.AchievementReportSum;
import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.util.lang.ObjectUtils;
import cn.adwalker.model.operation.dao.IChannelConfirmDao;

@Service("invoiceChannelService")
public class InvoiceChannelService implements IInvoiceChannelService {


	@Resource
	private IChannelConfirmDao channelConfirmDao;

	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.NEVER)
	@Override
	public List<InvoiceChannelVo> findByPage(InvoiceChannelBean bean, IPageInfo pageInfo) throws Exception {
		List<Object> params = new ArrayList<Object>();
		StringBuilder sb = new StringBuilder(
				"  t_channel_confirm n LEFT JOIN v_campaign t ON n.campaign_id= t.ID LEFT JOIN t_channel s ON n.`channel_id` = s.id WHERE n.status=3");
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
			if (ObjectUtils.isNotEmpty(bean.getChannel())) {
				sb.append(" and (upper(s.name)  like ? or n.channel_id =? )");
				params.add("%" + bean.getChannel_name().toUpperCase() + "%");
				params.add(bean.getChannel_id());
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
				sb.append(" and s.channe_manager like ?");
				params.add("%" + bean.getSales()+ "%");
			}
		}
		return (List<InvoiceChannelVo>) channelConfirmDao.findByPage(" n.* ,t.company_name,t.campaign_name,t.os,s.`channe_manager`,s.`name` channel_name", sb.toString(), params.toArray(), " order by n.create_time desc ", InvoiceChannelVo.class, pageInfo);
	}
	/**
	 * (non-Javadoc)
	* <p>Title: InvoiceChannel</p>
	* <p>Description:TODO</p>
	* @param objList
	* @return
	* @throws Exception
	* @see cn.adwalker.ad.admin.finance.service.IInvoiceChannelService#InvoiceChannel(java.util.List)
	 */
	@Override
	public int[] updateInvoiceChannel(List<Object[]> objList) throws Exception {
		return channelConfirmDao.updateInvoiceChannel(objList);
	}
	/**
	 * (non-Javadoc)
	* <p>Title: findByPage</p>
	* <p>Description:TODO</p>
	* @param bean
	* @param pageInfo
	* @return
	* @throws Exception
	* @see cn.adwalker.ad.admin.finance.service.IInvoiceChannelService#findByPage(cn.adwalker.ad.admin.finance.bean.PaymentChannelBean, cn.adwalker.core.page.IPageInfo)
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.NEVER)
	@Override
	public List<PaymentChannelVo> findByPage(PaymentChannelBean bean, IPageInfo pageInfo) throws Exception {
		List<Object> params = new ArrayList<Object>();
		StringBuilder sb = new StringBuilder(
				" t_channel_confirm n LEFT JOIN v_campaign t ON n.campaign_id= t.ID LEFT JOIN t_channel s ON n.`channel_id` = s.id WHERE n.status=3");
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
			if (ObjectUtils.isNotEmpty(bean.getChannel())) {
				sb.append(" and (upper(s.name)  like ? or n.channel_id =? )");
				params.add("%" + bean.getChannel_name().toUpperCase() + "%");
				params.add(bean.getChannel_id());
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
		return (List<PaymentChannelVo>) channelConfirmDao.findByPage(" n.* ,t.company_name,t.campaign_name,t.os,s.`channe_manager`,s.`name` channel_name", sb.toString(), params.toArray(), " order by n.create_time desc ", PaymentChannelVo.class, pageInfo);
	}
	/**
	 * (non-Javadoc)
	* <p>Title: updatePayment</p>
	* <p>Description:TODO</p>
	* @param objList
	* @return
	* @throws Exception
	* @see cn.adwalker.ad.admin.finance.service.IInvoiceChannelService#updatePayment(java.util.List)
	 */
	@Override
	public int[] updatePayment(List<Object[]> objList) throws Exception {
		return channelConfirmDao.updatePayment(objList);
	}
	/**
	 * (non-Javadoc)
	* <p>Title: findByPage</p>
	* <p>Description:TODO</p>
	* @param bean
	* @param pageInfo
	* @return
	* @throws Exception
	* @see cn.adwalker.ad.admin.finance.service.IInvoiceChannelService#findByPage(cn.adwalker.ad.admin.finance.bean.CostBean, cn.adwalker.core.page.IPageInfo)
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.NEVER)
	@Override
	public List<CostVo> findByPage(CostBean bean, IPageInfo pageInfo) throws Exception {
		List<Object> params = new ArrayList<Object>();
		StringBuilder sb = new StringBuilder(
				" t_channel_confirm n LEFT JOIN v_campaign t ON n.campaign_id= t.ID LEFT JOIN t_channel s ON n.`channel_id` = s.id WHERE n.status=3");
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
			if (ObjectUtils.isNotEmpty(bean.getChannel())) {
				sb.append(" and (upper(s.name)  like ? or n.channel_id =? )");
				params.add("%" + bean.getChannel_name().toUpperCase() + "%");
				params.add(bean.getChannel_id());
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
				sb.append(" and s.channe_manager like ?");
				params.add("%" + bean.getSales()+ "%");
			}
		}
		return (List<CostVo>) channelConfirmDao.findByPage(" n.* ,t.company_name,t.campaign_name,t.os,s.`channe_manager`,s.`name` channel_name", sb.toString(), params.toArray(), " order by n.create_time desc ", CostVo.class, pageInfo);
	}
	/**
	 * (non-Javadoc)
	* <p>Title: findSum</p>
	* <p>Description:TODO</p>
	* @param bean
	* @return
	* @throws Exception
	* @see cn.adwalker.ad.admin.finance.service.IInvoiceChannelService#findSum(cn.adwalker.ad.admin.finance.bean.CostBean)
	 */
	@Override
	public AchievementReportSumVo findSum(CostBean bean) throws Exception {
		List<AchievementReportSum> list;
		list = this.getSumChaList(bean);
		Double sum_income_money = 0d;// 确认 总金额
		Double sum_income_plm_money = 0d;// 平台 统计
		if (list != null && list.size() > 0) {
			sum_income_money = list.get(0).getSum_income_money();
			sum_income_plm_money = list.get(0).getSum_income_plm_money();
			
		}
		return new AchievementReportSumVo(sum_income_money,
				sum_income_plm_money);
	}
	/**
	 * 求和
	 */
	@SuppressWarnings("unchecked")
	private List<AchievementReportSum> getSumChaList(CostBean bean) {
		List<AchievementReportSum> list = null;
		List<Object> params = new ArrayList<Object>();
		StringBuilder sb = new StringBuilder(
				"SELECT sum(n.forecast_money) sum_income_plm_money, sum(n.income_money) sum_income_money FROM t_channel_confirm n LEFT JOIN v_campaign t ON n.campaign_id= t.ID LEFT JOIN t_channel s ON n.`channel_id` = s.id WHERE n.status=3");
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
			if (ObjectUtils.isNotEmpty(bean.getChannel())) {
				sb.append(" and (upper(s.name)  like ? or n.channel_id =? )");
				params.add("%" + bean.getChannel_name().toUpperCase() + "%");
				params.add(bean.getChannel_id());
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
				sb.append(" and s.channe_manager like ?");
				params.add("%" + bean.getSales()+ "%");
			}
		}
		try {
			list = (List<AchievementReportSum>) this.channelConfirmDao
					.findAll(sb.toString(), params.toArray(),
							AchievementReportSum.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
