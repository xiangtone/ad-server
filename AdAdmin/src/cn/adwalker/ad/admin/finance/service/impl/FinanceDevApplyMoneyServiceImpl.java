package cn.adwalker.ad.admin.finance.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.util.lang.ObjectUtils;
import cn.adwalker.model.app.dao.IDeveloperDao;
import cn.adwalker.model.app.domain.DevApplyMoney;
import cn.adwalker.model.app.domain.Developer;
import cn.adwalker.model.finance.dao.IFinanceDevApplyMoneyDao;
import cn.adwalker.ad.admin.common.vo.SysUserVo;
import cn.adwalker.ad.admin.finance.bean.DevApplyMoneylbean;
import cn.adwalker.ad.admin.finance.service.IFinanceDevApplyMoneyService;
import cn.adwalker.ad.admin.finance.vo.FinanceApplyMoneyDownVo;
import cn.adwalker.ad.admin.finance.vo.FinanceApplyMoneySumVo;
import cn.adwalker.ad.admin.finance.vo.FinanceApplyMoneyVo;

/**
 * 
 * <p>
 * Title: FinanceDevApplyMoneyServiceImpl
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-1-20
 */

@Service(value = "financeDevApplyMoneyService")
@Transactional
public class FinanceDevApplyMoneyServiceImpl implements
		IFinanceDevApplyMoneyService {

	@Resource
	private IFinanceDevApplyMoneyDao financeDevApplyMoneyDao;

	/**
	 * 开发者服务组件
	 */
	@Resource
	private IDeveloperDao developerDao;

	/**
	 */
	public DevApplyMoney getById(Long id) throws Exception {
		DevApplyMoney DevApplyMoney = new DevApplyMoney();
		DevApplyMoney = financeDevApplyMoneyDao.getById(id);
		return DevApplyMoney;
	}

	public Double getApplyingByDevUserId(Long devUserId) throws Exception {
		Double applying = financeDevApplyMoneyDao
				.getApplyingByAppUserId(devUserId);
		return applying;
	}

	/**
	 * 统计 tag:区分运营(0)和财务(1)和财务下载(2)
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<DevApplyMoney> findReport(String con, int tag) throws Exception {
		return this.financeDevApplyMoneyDao.findReport(con, tag);
	}

	/**
	 * 财务审核处理 a:更新数据的审核信息 b:更新开发者的已确认佣金(开发者用户已确认佣金 - 财务审核申请金额)、累积提现(开发者用户累积提现 +
	 * 财务审核确认金额)
	 */
	@Override
	@Transactional(rollbackFor = { Exception.class })
	public void tranAuditInFinance(Long[] ids, int status,
			String[] financeDescs, double[] financeMoneys,
			SysUserVo manageUser, int[] pay_types, double[] finance_taxs,
			double[] finance_duess) throws Exception {
		Long manageUserId = manageUser.getId();
		Map<Long, Developer> tempMap = new HashMap<Long, Developer>();// 临时存放。累加开发者的已确认佣金、累积体现,key:开发者id，value：开发者对象
		List<DevApplyMoney> updateList = new ArrayList<DevApplyMoney>();// 待更新列表
		Developer devUserBean;// 开发者
		DevApplyMoney bean;
		List<DevApplyMoney> log1Bean = new ArrayList<DevApplyMoney>();
		List<DevApplyMoney> log2Bean = new ArrayList<DevApplyMoney>();
		for (int i = 0; i < ids.length; i++) {
			bean = this.financeDevApplyMoneyDao.getById(ids[i]);// 获取当前对象信息
			log1Bean.add(bean);
			bean.setFinanceId(manageUserId);// 财务审批人id
			bean.setStatus(String.valueOf(status));// 状态
			bean.setFinanceTime(new Date());// 运营审批时间
			bean.setManagerDesc(financeDescs[i]);// 运营审批描述
			bean.setPay_type(String.valueOf(pay_types[i]));// 运营审批描述
			bean.setFinance_tax(finance_taxs[i]);
			bean.setFinance_dues(finance_duess[i]);
			try {
				bean.setFinanceMoney(financeMoneys[i]);// 运营确认金额
			} catch (Exception e) {
				bean.setFinanceMoney(null);// 不存在默认null
			}
			updateList.add(bean);
			log2Bean.add(bean);
			// 累加开发者的已确认佣金和累积提现
			devUserBean = new Developer();
			Developer tempDevUser = tempMap.get(bean.getDev_Id()); // 从map中通过key回去值
			if (tempDevUser == null) {// 不存在则添加
				devUserBean.setTotalMoney(financeMoneys[i]);// 累积提现
				devUserBean.setConfirmedMoney(bean.getApplyMoney());// 已确认佣金
				tempMap.put(bean.getDev_Id(), devUserBean);
			} else {// 存在就累加，最后会在sql中进行加减运算
				tempDevUser.setTotalMoney(tempDevUser.getTotalMoney()
						+ financeMoneys[i]);// 累积提现
				tempDevUser.setConfirmedMoney(tempDevUser.getConfirmedMoney()
						+ bean.getApplyMoney());// 已确认佣金
				tempMap.put(bean.getDev_Id(), tempDevUser);
			}
		}
		try {
			this.financeDevApplyMoneyDao.batchAuditInFinance(updateList);// 批量审核处理
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (status == 2) {// 审核成功引发动作
			try {
				this.developerDao.batchUpdateTotalMAndConfirmM(tempMap);// 引发的动作，更新开发者已确认佣金、累积体现
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: tranAuditInFinance
	 * </p>
	 * <p>
	 * Description:导出财务报表时，保存税率和手续费
	 * </p>
	 * 
	 * @param ids
	 * @param finance_taxs
	 * @param finance_duess
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.finance.service.IFinanceDevApplyMoneyService#tranAuditInFinance(java.lang.Long[],
	 *      double[], double[])
	 */
	@Override
	@Transactional(rollbackFor = { Exception.class })
	public void tranAuditInFinance(Long[] ids, double[] finance_taxs,
			double[] finance_duess) throws Exception {

		List<DevApplyMoney> updateList = new ArrayList<DevApplyMoney>();// 待更新列表

		for (int i = 0; i < ids.length; i++) {
			DevApplyMoney bean = new DevApplyMoney();
			bean.setId(ids[i]);
			bean.setFinance_tax(finance_taxs[i]);
			bean.setFinance_dues(finance_duess[i]);

			updateList.add(bean);
		}
		this.financeDevApplyMoneyDao.batchFinance_TaxAndDues(updateList);// 批量审核处理

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
	 * @see cn.adwalker.ad.admin.finance.service.IFinanceDevApplyMoneyService#findAll(cn.adwalker.ad.admin.finance.bean.DevApplyMoneylbean)
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.NEVER)
	@Override
	public List<FinanceApplyMoneySumVo> findAll(DevApplyMoneylbean bean) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select de.MANAGER_MONEY as managerMoney ,de.BALANCE_MONEY as balance ,de.FINANCE_MONEY as finance_money ,de.FINANCE_DUES as finance_dues, de.FINANCE_TAX as finance_tax from v_dev_apply_money de where 1=1 and de.status in (1,2,-2)");
		// 网站主账号
		if (ObjectUtils.isNotEmpty((bean.getDev_email()))) {
			sql.append(" and DEV_EMAIL ");
			sql.append(" like '%");
			sql.append(bean.getDev_email());
			sql.append("%'");
		}
		// 审批人
		if (ObjectUtils.isNotEmpty((bean.getOperatorMan()))) {
			sql.append(" and FINANCE_NAME ");
			sql.append(" like '%");
			sql.append(bean.getOperatorMan());
			sql.append("%'");
		}
		// 支付类型
		if (ObjectUtils.isNotEmpty((bean.getPayType()))) {
			sql.append(" and PAY_TYPE ");
			sql.append(" like '%");
			sql.append(bean.getPayType());
			sql.append("%'");
		}
		// 支付状态
		if (ObjectUtils.isNotEmpty((bean.getStatus()))) {
			sql.append(" and STATUS ");
			sql.append(" like '%");
			sql.append(bean.getStatus());
			sql.append("%'");
		}
		// 发票状态
		if (ObjectUtils.isNotEmpty((bean.getInvoice()))) {
			sql.append(" and de.INVOICE= '");
			sql.append(bean.getInvoice());
			sql.append("'");
		}
		// 运营审批时间
		if (ObjectUtils.isNotEmpty(bean.getOpe_begin())) {
			sql.append(" and date_format(de.manager_time, '%Y-%m-%d') >= '");
			sql.append(bean.getOpe_begin());
			sql.append("'");
		}

		if (ObjectUtils.isNotEmpty(bean.getOpe_end())) {
			sql.append(" and date_format(de.manager_time, '%Y-%m-%d') <= '");
			sql.append(bean.getOpe_end());
			sql.append("'");
		}
		// 财务审批时间
		if (ObjectUtils.isNotEmpty(bean.getOperatorBegin())) {
			sql.append(" and date_format(FINANCE_TIME, '%Y-%m-%d') >= '");
			sql.append(bean.getOperatorBegin());
			sql.append("'");
		}

		if (ObjectUtils.isNotEmpty(bean.getOperatorEnd())) {
			sql.append(" and date_format(FINANCE_TIME, '%Y-%m-%d') <= '");
			sql.append(bean.getOperatorEnd());
			sql.append("'");
		}
		// 申请时间
		if (ObjectUtils.isNotEmpty(bean.getBegin())) {
			sql.append(" and date_format(CREATE_TIME, '%Y-%m-%d') >= '");
			sql.append(bean.getBegin());
			sql.append("'");
		}

		if (ObjectUtils.isNotEmpty(bean.getEnd())) {
			sql.append(" and date_format(CREATE_TIME, '%Y-%m-%d') <= '");
			sql.append(bean.getEnd());
			sql.append("'");
		}
		sql.append(" order by de.create_time desc ");
		return financeDevApplyMoneyDao.findAll(sql.toString(),FinanceApplyMoneySumVo.class);
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findDevApplyListPage
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param bean
	 * @param pageInfo
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.finance.service.IFinanceDevApplyMoneyService#findDevApplyListPage(cn.adwalker.ad.admin.finance.bean.DevApplyMoneylbean,
	 *      cn.adwalker.core.page.IPageInfo)
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.NEVER)
	@Override
	public List<FinanceApplyMoneyVo> findDevApplyListPage(
			DevApplyMoneylbean bean, IPageInfo pageInfo) throws Exception {
		List<Object> list = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer();
		sql.append("v_dev_apply_money de where 1=1 and de.status in (1,2,-2)");
		
		
		// 申请单号
		if (ObjectUtils.isNotEmpty((bean.getId()))) {
			sql.append(" and de.id= '");
			sql.append(bean.getId());
			sql.append("'");
		}
		
		// 网站主账号
		if (ObjectUtils.isNotEmpty((bean.getDev_email()))) {
			sql.append(" and upper(de.DEV_EMAIL) ");
			sql.append(" like '%");
			sql.append(bean.getDev_email().trim().toUpperCase());
			sql.append("%'");
		}
		// 审批人
		if (ObjectUtils.isNotEmpty((bean.getOperatorMan()))) {
			sql.append(" and de.FINANCE_NAME ");
			sql.append(" like '%");
			sql.append(bean.getOperatorMan());
			sql.append("%'");
		}
		// 支付类型
		if (ObjectUtils.isNotEmpty((bean.getPayType()))) {
			sql.append(" and de.PAY_TYPE= '");
			sql.append(bean.getPayType());
			sql.append("'");
		}
		// 支付状态
		if (ObjectUtils.isNotEmpty((bean.getStatus()))) {
			sql.append(" and de.STATUS= '");
			sql.append(bean.getStatus());
			sql.append("'");
		}
		// 发票状态
		if (ObjectUtils.isNotEmpty((bean.getInvoice()))) {
			sql.append(" and de.INVOICE= '");
			sql.append(bean.getInvoice());
			sql.append("'");
		}
		// 运营审批时间
		if (ObjectUtils.isNotEmpty(bean.getOpe_begin())) {
			sql.append(" and date_format(de.manager_time, '%Y-%m-%d') >= '");
			sql.append(bean.getOpe_begin());
			sql.append("'");
		}

		if (ObjectUtils.isNotEmpty(bean.getOpe_end())) {
			sql.append(" and date_format(de.manager_time, '%Y-%m-%d') <= '");
			sql.append(bean.getOpe_end());
			sql.append("'");
		}
		// 财务审批时间
		if (ObjectUtils.isNotEmpty(bean.getOperatorBegin())) {
			sql.append(" and date_format(de.FINANCE_TIME, '%Y-%m-%d') >= '");
			sql.append(bean.getOperatorBegin());
			sql.append("'");
		}

		if (ObjectUtils.isNotEmpty(bean.getOperatorEnd())) {
			sql.append(" and date_format(de.FINANCE_TIME, '%Y-%m-%d') <= '");
			sql.append(bean.getOperatorEnd());
			sql.append("'");
		}
		// 申请时间
		if (ObjectUtils.isNotEmpty(bean.getBegin())) {
			sql.append(" and date_format(de.CREATE_TIME, '%Y-%m-%d') >= '");
			sql.append(bean.getBegin());
			sql.append("'");
		}

		if (ObjectUtils.isNotEmpty(bean.getEnd())) {
			sql.append(" and date_format(de.CREATE_TIME, '%Y-%m-%d') <= '");
			sql.append(bean.getEnd());
			sql.append("'");
		}
		return (List<FinanceApplyMoneyVo>) financeDevApplyMoneyDao.findByPage(
				"*", sql.toString(), list.toArray(),
				" order by de.create_time desc ", FinanceApplyMoneyVo.class,
				pageInfo);

	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findDevApplyList
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param bean
	 * @param pageInfo
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.finance.service.IFinanceDevApplyMoneyService#findDevApplyList(cn.adwalker.ad.admin.finance.bean.DevApplyMoneylbean,
	 *      cn.adwalker.core.page.IPageInfo)
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.NEVER)
	@Override
	public List<FinanceApplyMoneyDownVo> findDevApplyList(
			DevApplyMoneylbean bean) throws Exception {
		List<Object> list = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer();
		sql.append("v_dev_apply_money de where 1=1 and de.status in (1,2,-2)");
		// 网站主账号
		if (ObjectUtils.isNotEmpty((bean.getDev_email()))) {
			sql.append(" and upper(de.DEV_EMAIL) ");
			sql.append(" like '%");
			sql.append(bean.getDev_email().trim().toUpperCase());
			sql.append("%'");
		}
		// 审批人
		if (ObjectUtils.isNotEmpty((bean.getOperatorMan()))) {
			sql.append(" and de.FINANCE_NAME ");
			sql.append(" like '%");
			sql.append(bean.getOperatorMan());
			sql.append("%'");
		}
		// 支付类型
		if (ObjectUtils.isNotEmpty((bean.getPayType()))) {
			sql.append(" and de.PAY_TYPE= '");
			sql.append(bean.getPayType());
			sql.append("'");
		}
		// 支付状态
		if (ObjectUtils.isNotEmpty((bean.getStatus()))) {
			sql.append(" and de.STATUS= '");
			sql.append(bean.getStatus());
			sql.append("'");
		}
		// 发票状态
		if (ObjectUtils.isNotEmpty((bean.getInvoice()))) {
			sql.append(" and de.INVOICE= '");
			sql.append(bean.getInvoice());
			sql.append("'");
		}
		// 运营审批时间
		if (ObjectUtils.isNotEmpty(bean.getOpe_begin())) {
			sql.append(" and date_format(de.manager_time, '%Y-%m-%d') >= '");
			sql.append(bean.getOpe_begin());
			sql.append("'");
		}

		if (ObjectUtils.isNotEmpty(bean.getOpe_end())) {
			sql.append(" and date_format(de.manager_time, '%Y-%m-%d') <= '");
			sql.append(bean.getOpe_end());
			sql.append("'");
		}
		// 财务审批时间
		if (ObjectUtils.isNotEmpty(bean.getOperatorBegin())) {
			sql.append(" and date_format(de.FINANCE_TIME, '%Y-%m-%d') >= '");
			sql.append(bean.getOperatorBegin());
			sql.append("'");
		}

		if (ObjectUtils.isNotEmpty(bean.getOperatorEnd())) {
			sql.append(" and date_format(de.FINANCE_TIME, '%Y-%m-%d') <= '");
			sql.append(bean.getOperatorEnd());
			sql.append("'");
		}
		// 申请时间
		if (ObjectUtils.isNotEmpty(bean.getBegin())) {
			sql.append(" and date_format(de.CREATE_TIME, '%Y-%m-%d') >= '");
			sql.append(bean.getBegin());
			sql.append("'");
		}

		if (ObjectUtils.isNotEmpty(bean.getEnd())) {
			sql.append(" and date_format(de.CREATE_TIME, '%Y-%m-%d') <= '");
			sql.append(bean.getEnd());
			sql.append("'");
		}
		return (List<FinanceApplyMoneyDownVo>) financeDevApplyMoneyDao
				.findAll("*", sql.toString(), list.toArray(),
						" order by de.create_time desc ",
						FinanceApplyMoneyDownVo.class);

	}
}