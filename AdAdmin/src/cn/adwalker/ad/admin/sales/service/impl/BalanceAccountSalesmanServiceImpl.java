package cn.adwalker.ad.admin.sales.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import cn.adwalker.ad.admin.ad.form.CampaignPaymentsForm;
import cn.adwalker.ad.admin.common.vo.SysUserVo;
import cn.adwalker.ad.admin.sales.bean.BalanceAccountSalesmanBean;
import cn.adwalker.ad.admin.sales.service.IBalanceAccountSalesmanService;
import cn.adwalker.ad.admin.sales.vo.BalanceAccountDetailInfoVo;
import cn.adwalker.ad.admin.sales.vo.BalanceAccountInfoVo;
import cn.adwalker.ad.admin.sales.vo.BalanceAccountVo;
import cn.adwalker.ad.task.SendMailTask;
import cn.adwalker.core.bean.SimpleBean;
import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.pool.ThreadPoolUtil;
import cn.adwalker.core.spring.AppContext;
import cn.adwalker.core.util.ConfigUtil;
import cn.adwalker.core.util.lang.StringUtil;
import cn.adwalker.model.ad.dao.ICampaignPaymentsDao;
import cn.adwalker.model.ad.domain.CampaignPayments;
import cn.adwalker.model.news.domain.Mail;
import cn.adwalker.model.operation.dao.ICampaignConfirmDao;
import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 
* <p>Title: BalanceAccountSalesmanServiceImpl</p>
* <p>Description:TODO</p>
* <p>Company: adwalker</p> 
* @author    cuidd
* @date       2014-3-27
 */
@Service
public class BalanceAccountSalesmanServiceImpl implements
		IBalanceAccountSalesmanService {

	@Resource
	private ICampaignConfirmDao campaignConfirmDao;

	@Resource
	private ICampaignPaymentsDao campaignPaymentsDao;

	/**
	 * (non-Javadoc)
	* <p>Title: findByPage</p>
	* <p>Description:TODO</p>
	* @param bean
	* @param pageInfor
	* @return
	* @throws Exception
	* @see cn.adwalker.ad.admin.sales.service.IBalanceAccountSalesmanService#findByPage(cn.adwalker.ad.admin.sales.bean.BalanceAccountSalesmanBean, cn.adwalker.core.page.IPageInfo)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<BalanceAccountVo> findByPage(BalanceAccountSalesmanBean bean,
			IPageInfo pageInfor) throws Exception {
		List<BalanceAccountVo> list = null;
		List<Object> param = new ArrayList<Object>();
		String columns = "t.income_money,t.bad_debt,t.balance_status,t.forecast_amount,t.forecast_money,t.id,t.month_stat_date as start_date,t.month_end_date as end_date,t.campaign_id,c.campaign_name ,c.company_name as adv_name,c.adv_email,c.os,c.adv_id,c.charge_type,s.name salesman_name";
		StringBuilder sb = new StringBuilder(
				"t_campaign_confirm t "
						+ "left join v_campaign c on t.campaign_id=c.id "
						+ "left join t_campaign_salesman s on c.salesman_id=s.id where 1=1 and t.status=8 ");
		if (bean != null) {
			if (!StringUtils.isEmpty(bean.getCampaign())) {
				sb.append(" and (c.ID=? or upper(c.CAMPAIGN_NAME) like ?)");
				param.add(bean.getCampaign_id());
				param.add("%" + bean.getCampaign_name().trim().toUpperCase()
						+ "%");
			}
			if (!StringUtils.isEmpty(bean.getAdv())) {
				sb.append(" and (c.ADV_ID=? or upper(c.company_name) like ?)");
				param.add(bean.getAdv_id());
				param.add("%" + bean.getAdv().trim().toUpperCase() + "%");
			}

			if (!StringUtils.isEmpty(bean.getOs())) {
				sb.append(" and c.OS=?");
				param.add(bean.getOs().trim());
			}

			if (!StringUtils.isEmpty(bean.getCharge_type())) {
				sb.append(" and c.charge_type=?");
				param.add(bean.getCharge_type().trim());
			}
			if (bean.getBalance_status() != null) {
				sb.append(" and t.balance_status= ?");
				param.add(bean.getBalance_status());
			}

			if (bean.getSales_id() != null) {
				sb.append(" and c.salesman_id=");
				sb.append(bean.getSales_id());
				sb.append("");
			}
			/*
			 * 1、销售总监可以看到全部数据2、销售经理可以看到本区内的数据3、销售专员只能看到自己的数据
			 */
			if (bean.getArea_type() != null) {
				sb.append(" and s.area_type=");
				sb.append(bean.getArea_type());
			}

		}
		list = (List<BalanceAccountVo>) campaignConfirmDao.findByPage(columns,
				sb.toString(), param.toArray(), "order by t.create_time desc",
				BalanceAccountVo.class, pageInfor);

		if (list != null) {
			for (BalanceAccountVo vo : list) {
				vo.setPayments(getPayments(vo.getId()));

			}
		}

		return list;

	}

	/**
	 * 
	* <p>Title: getPayments</p>
	* <p>Description:TODO</p>
	* @param id
	* @return
	* @throws Exception
	* @author cuidd
	* @date 2014-3-27
	* @return BigDecimal
	* @version 1.0
	 */
	@SuppressWarnings("unchecked")
	private BigDecimal getPayments(Long id) throws Exception {
		BigDecimal b = null;
		if (id != null) {
			List<SimpleBean<Double>> list = (List<SimpleBean<Double>>) campaignPaymentsDao
					.findAll(
							"select sum(amount) as obj from t_campaign_payments where balance_account_id=?",
							new Object[] { id }, SimpleBean.class);
			if (list != null && list.get(0) != null
					&& list.get(0).getObj() != null) {
				b = new BigDecimal(list.get(0).getObj());
			}
		}
		return b;

	}

	/**
	 * (non-Javadoc)
	* <p>Title: savePayments</p>
	* <p>Description:TODO</p>
	* @param form
	* @param user
	* @throws Exception
	* @see cn.adwalker.ad.admin.sales.service.IBalanceAccountSalesmanService#savePayments(cn.adwalker.ad.admin.ad.form.CampaignPaymentsForm, cn.adwalker.ad.admin.common.vo.SysUserVo)
	 */
	@Override
	public void savePayments(CampaignPaymentsForm form, SysUserVo user)
			throws Exception {
		CampaignPayments entity = new CampaignPayments();
		entity.setBalance_account_id(form.getBalance_account_id());
		entity.setCreate_time(new Date());
		entity.setPayments_type(form.getPayments_type());
		entity.setAmount(form.getAmount());
		entity.setStatus(0);
		entity.setPay_date(form.getPay_date());
		entity.setCreate_user(user.getId());
		campaignPaymentsDao.insert(entity);
		campaignConfirmDao.update(
				"update T_CAMPAIGN_CONFIRM set balance_status=? where id=?",
				new Object[] { form.getBalance_status(),
						form.getBalance_account_id() });

	}

	/**
	 * (non-Javadoc)
	* <p>Title: bad_debt</p>
	* <p>Description:TODO</p>
	* @param id
	* @param user
	* @throws Exception
	* @see cn.adwalker.ad.admin.sales.service.IBalanceAccountSalesmanService#bad_debt(java.lang.Long, cn.adwalker.ad.admin.common.vo.SysUserVo)
	 */
	@Override
	public void bad_debt(Long id, SysUserVo user) throws Exception {
		if (id != null) {
			campaignConfirmDao
					.update("update t_campaign_confirm set bad_debt=?,balance_status=? where id=? ",
							new Object[] { 1, 20, id });

		}

	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: updateConfirmMoney
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param id
	 * @param confirm_money
	 * @param confirm_date
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.sales.service.IBalanceAccountSalesmanService#updateConfirmMoney(java.lang.Long,
	 *      java.math.BigDecimal, java.util.Date)
	 */
	@Override
	public void updateConfirmMoney(Long id, BigDecimal confirm_money,
			Date confirm_date) throws Exception {
		campaignConfirmDao
				.update("update t_campaign_confirm set confirm_money=?,confirm_date=? where id=? ",
						new Object[] { confirm_money, confirm_date, id });
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: info
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.sales.service.IBalanceAccountSalesmanService#info(java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public BalanceAccountInfoVo info(Long id) throws Exception {
		BalanceAccountInfoVo vo = null;
		if (id != null) {
			String sql = "select t.forecast_amount,t.forecast_money,t.month_stat_date as start_date,t.month_end_date as end_date,t.campaign_id,c.campaign_name,c.company_name as adv_name from t_campaign_confirm t "
					+ "left join v_campaign c on t.campaign_id=c.id "
					+ "left join t_campaign_salesman s on c.salesman_id=s.id where t.id=? ";
			List<BalanceAccountInfoVo> list = (List<BalanceAccountInfoVo>) campaignConfirmDao
					.findTop(sql, new Object[] { id },
							BalanceAccountInfoVo.class, 1);
			if (list != null) {
				vo = list.get(0);
				if (vo != null) {
					vo.setDetailList(datailInfo(id));
				}
			}
		}

		return vo;
	}

	/**
	 * 
	* <p>Title: datailInfo</p>
	* <p>Description:TODO</p>
	* @param id
	* @return
	* @throws Exception
	* @author cuidd
	* @date 2014-3-27
	* @return List<BalanceAccountDetailInfoVo>
	* @version 1.0
	 */
	@SuppressWarnings("unchecked")
	private List<BalanceAccountDetailInfoVo> datailInfo(Long id)
			throws Exception {
		List<BalanceAccountDetailInfoVo> list = null;
		if (id != null) {
			list = (List<BalanceAccountDetailInfoVo>) campaignConfirmDao
					.findAll(
							"select  t3.static_date,sum(t3.confirm_amount) confirm_amount,sum(t3.confirm_money) confirm_money   from  (select   t2.* ,p.confirm_amount,p.static_date,p.in_price*p.confirm_amount as confirm_money  from  (SELECT  balance_id,detail_id   from   T_FINANCE_BALANCE_INCOME_REL t where t.balance_id=?) t2 left join  t_package_activate  p on t2.detail_id=p.id order by p.static_date) t3 group  by t3.static_date",
							new Object[] { id },
							BalanceAccountDetailInfoVo.class);

		}
		return list;

	}

	/**
	 * 
	* <p>Title: sendMailBalance</p>
	* <p>Description:TODO</p>
	* @param id
	* @throws Exception
	* @author cuidd
	* @date 2014-3-27
	* @return void
	* @version 1.0
	 */
	public void sendMailBalance(Long id) throws Exception {
		BalanceAccountInfoVo balanceAccountInfoVo = info(id);
		ApplicationContext ctx = AppContext.getApplicationContext();
		Configuration freemarkerConfig = (Configuration) ctx
				.getBean("freemarkerConfigurer");
		Template temp = freemarkerConfig
				.getTemplate("/template/ad/salesman-balance.ftl");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("vo", balanceAccountInfoVo);
		String s = FreeMarkerTemplateUtils.processTemplateIntoString(temp, map);
		String email = ConfigUtil.getString("send_mail_for_aduit");
		if (!StringUtil.isEmpty(email)) {
			String arr[] = email.split(",");
			ThreadPoolExecutor threadPool = ThreadPoolUtil.getThreadPool();
			for (int i = 0; i < arr.length; i++) {
				Mail vo = new Mail();
				vo.setTitle("活动ID:" + id);
				vo.setEmail(arr[i]);
				vo.setContent(s);
				threadPool.execute(new SendMailTask(vo, arr[i]));

			}

		}
	}

	/**
	 * (non-Javadoc)
	* <p>Title: sendBalanceMail</p>
	* <p>Description:TODO</p>
	* @param id
	* @throws Exception
	* @see cn.adwalker.ad.admin.sales.service.IBalanceAccountSalesmanService#sendBalanceMail(java.lang.Long)
	 */
	@Override
	public void sendBalanceMail(Long id) throws Exception {
		sendMailBalance(id);
		campaignConfirmDao.update(
				"update t_campaign_confirm set balance_status=? where id=? ",
				new Object[] { 10, id });
	}

}
