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
import cn.adwalker.model.app.domain.ChaApplyMoney;
import cn.adwalker.model.app.domain.DevApplyMoney;
import cn.adwalker.model.channel.dao.IChannelDao;
import cn.adwalker.model.channel.domain.ChannelMoney;
import cn.adwalker.model.finance.dao.IFinanceChaApplyMoneyDao;
import cn.adwalker.ad.admin.common.vo.SysUserVo;
import cn.adwalker.ad.admin.finance.bean.ChaApplyMoneylbean;
import cn.adwalker.ad.admin.finance.service.IFinanceChaApplyMoneyService;
import cn.adwalker.ad.admin.finance.vo.FinanceApplyChannelSumVo;
import cn.adwalker.ad.admin.finance.vo.FinanceChaApplyMoneyVo;

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

@Service(value = "financeChaApplyMoneyService")
@Transactional
public class FinanceChaApplyMoneyServiceImpl implements IFinanceChaApplyMoneyService {

	@Resource
	private IFinanceChaApplyMoneyDao financeChaApplyMoneyDao;

	/**
	 * 渠道组件
	 */
	@Resource
	private IChannelDao userChannelDao;
	
	public Double getApplyingByDevUserId(Long devUserId) throws Exception {
		Double applying = financeChaApplyMoneyDao
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
		return this.financeChaApplyMoneyDao.findReport(con, tag);
	}

	/**
	 * (non-Javadoc)
	* <p>Title: tranAuditInFinance</p>
	* <p>Description:财务审核处理 a:更新数据的审核信息 b:更新渠道的已确认佣金(渠道用户已确认佣金 - 财务审核申请金额)、累积提现(渠道用户累积提现 +
	 * 财务审核确认金额)</p>
	* @param ids
	* @param status
	* @param financeDescs
	* @param financeMoneys
	* @param manageUser
	* @param pay_types
	* @param finance_taxs
	* @param finance_duess
	* @throws Exception
	* @see cn.adwalker.ad.admin.finance.service.IFinanceChaApplyMoneyService#tranAuditInFinance(java.lang.Long[], int, java.lang.String[], double[], cn.adwalker.ad.admin.common.vo.SysUserVo, int[], double[], double[])
	 */
	@Override
	@Transactional(rollbackFor = { Exception.class })
	public void tranAuditInFinance(Long[] ids, int status,
			String[] financeDescs, double[] financeMoneys,
			SysUserVo manageUser, int[] pay_types, double[] finance_taxs,
			double[] finance_duess) throws Exception {
		Long manageUserId = manageUser.getId();
		Map<Long, ChannelMoney> tempMap = new HashMap<Long, ChannelMoney>();// 临时存放。累加开发者的已确认佣金、累积体现,key:开发者id，value：开发者对象
		List<ChaApplyMoney> updateList = new ArrayList<ChaApplyMoney>();// 待更新列表
		ChannelMoney chaUserBean;// 开发者
		ChaApplyMoney bean;
		List<ChaApplyMoney> log1Bean = new ArrayList<ChaApplyMoney>();
		List<ChaApplyMoney> log2Bean = new ArrayList<ChaApplyMoney>();
		for (int i = 0; i < ids.length; i++) {
			bean = this.financeChaApplyMoneyDao.getById(ids[i]);// 获取当前对象信息
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
			chaUserBean = new ChannelMoney();
			ChannelMoney tempUser = tempMap.get(bean.getCha_Id()); // 从map中通过key回去值
			if (tempUser == null) {// 不存在则添加
				chaUserBean.setTotalMoney(financeMoneys[i]);// 累积提现
				chaUserBean.setConfirmedMoney(bean.getApplyMoney());// 已确认佣金
				tempMap.put(bean.getCha_Id(), chaUserBean);
			} else {// 存在就累加，最后会在sql中进行加减运算
				tempUser.setTotalMoney(tempUser.getTotalMoney()
						+ financeMoneys[i]);// 累积提现
				tempUser.setConfirmedMoney(tempUser.getConfirmedMoney()
						+ bean.getApplyMoney());// 已确认佣金
				tempMap.put(bean.getCha_Id(), tempUser);
			}
		}
		try {
			this.financeChaApplyMoneyDao.batchAuditInFinance(updateList);// 批量审核处理
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (status == 2) {// 审核成功引发动作
			try {
				this.userChannelDao.batchUpdateTotalMAndConfirmM(tempMap);// 引发的动作，更新开发者已确认佣金、累积体现
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

		List<ChaApplyMoney> updateList = new ArrayList<ChaApplyMoney>();// 待更新列表

		for (int i = 0; i < ids.length; i++) {
			ChaApplyMoney bean = new ChaApplyMoney();
			bean.setId(ids[i]);
			bean.setFinance_tax(finance_taxs[i]);
			bean.setFinance_dues(finance_duess[i]);

			updateList.add(bean);
		}
		this.financeChaApplyMoneyDao.batchFinance_TaxAndDues(updateList);// 批量审核处理

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
	 * @see cn.adwalker.ad.admin.finance.service.IFinanceDevApplyMoneyService#findAll(cn.adwalker.admin.finance.bean.DevApplyMoneylbean)
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.NEVER)
	@Override
	public List<FinanceApplyChannelSumVo> findAll(ChaApplyMoneylbean bean) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select de.MANAGER_MONEY as managerMoney ,de.BALANCE_MONEY as balance ,de.FINANCE_MONEY as finance_money ,de.FINANCE_DUES as finance_dues, de.FINANCE_TAX as finance_tax from v_cha_apply_money de where 1=1 and de.status in (1,2,-2)");
		if (ObjectUtils.isNotEmpty(bean.getKeyword())&& ObjectUtils.isNotEmpty(bean.getValue())) {			
			if("CHA_ID".equals(bean.getKeyword())){
				sql.append(" and ");
				sql.append(bean.getKeyword());
				sql.append(" = ");
				sql.append(" "+bean.getValue()+" " );
			}else{
				sql.append(" and ");
				sql.append(bean.getKeyword());
				sql.append(" like '%");
				sql.append(bean.getValue());
				sql.append("%'");
			}
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
		return financeChaApplyMoneyDao.findAll(sql.toString(),FinanceApplyChannelSumVo.class);
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
	 * @see cn.adwalker.ad.admin.finance.service.IFinanceDevApplyMoneyService#findDevApplyListPage(cn.adwalker.admin.finance.bean.DevApplyMoneylbean,
	 *      cn.adwalker.core.page.IPageInfo)
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.NEVER)
	@Override
	public List<FinanceChaApplyMoneyVo> findChaApplyListPage(
			ChaApplyMoneylbean bean, IPageInfo pageInfo) throws Exception {
		List<Object> list = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer();
		sql.append("v_cha_apply_money de where 1=1 and de.status in (1,2,-2)");
		// 渠道账号
		if (ObjectUtils.isNotEmpty(bean.getKeyword())&& ObjectUtils.isNotEmpty(bean.getValue())) {			
			if("CHA_ID".equals(bean.getKeyword())){
				sql.append(" and ");
				sql.append(bean.getKeyword());
				sql.append(" = ");
				sql.append(" "+bean.getValue()+" " );
			}else{
				sql.append(" and ");
				sql.append(bean.getKeyword());
				sql.append(" like '%");
				sql.append(bean.getValue());
				sql.append("%'");
			}
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
		return (List<FinanceChaApplyMoneyVo>) financeChaApplyMoneyDao.findByPage(
				"*", sql.toString(), list.toArray(),
				" order by de.create_time desc ", FinanceChaApplyMoneyVo.class,
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
	 * @see cn.adwalker.ad.admin.finance.service.IFinanceDevApplyMoneyService#findDevApplyList(cn.adwalker.admin.finance.bean.DevApplyMoneylbean,
	 *      cn.adwalker.core.page.IPageInfo)
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.NEVER)
	@Override
	public List<FinanceChaApplyMoneyVo> findDevApplyList(
			ChaApplyMoneylbean bean) throws Exception {
		List<Object> list = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer();
		sql.append("v_cha_apply_money de where 1=1 and de.status in (1,2,-2)");
		// 渠道账号
		if (ObjectUtils.isNotEmpty(bean.getKeyword())&& ObjectUtils.isNotEmpty(bean.getValue())) {			
			if("CHA_ID".equals(bean.getKeyword())){
				sql.append(" and ");
				sql.append(bean.getKeyword());
				sql.append(" = ");
				sql.append(" "+bean.getValue()+" " );
			}else{
				sql.append(" and ");
				sql.append(bean.getKeyword());
				sql.append(" like '%");
				sql.append(bean.getValue());
				sql.append("%'");
			}
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
		return (List<FinanceChaApplyMoneyVo>) financeChaApplyMoneyDao
				.findAll("*", sql.toString(), list.toArray(),
						" order by de.create_time desc ",
						FinanceChaApplyMoneyVo.class);

	}
}