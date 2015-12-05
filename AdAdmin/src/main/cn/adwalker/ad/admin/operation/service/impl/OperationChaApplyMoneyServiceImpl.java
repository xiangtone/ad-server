package cn.adwalker.ad.admin.operation.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.util.lang.ObjectUtils;
import cn.adwalker.model.app.dao.IDevApplyMoneyDao;
import cn.adwalker.model.app.domain.ChaApplyMoney;
import cn.adwalker.model.app.domain.DevApplyMoney;
import cn.adwalker.model.operation.dao.IOperationChaApplyMoneyDao;
import cn.adwalker.model.sys.dao.ISysUserDao;
import cn.adwalker.ad.admin.common.vo.SysUserVo;
import cn.adwalker.ad.admin.operation.bean.ChaApplyMoneySearchBean;
import cn.adwalker.ad.admin.operation.service.IOperationChaApplyMoneyService;
import cn.adwalker.ad.config.DevelopConstants;

/**
* <p>Title: OperationChaApplyMoneyServiceImpl</p>
* <p>Description:运营提款审核Service</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2014-2-14
 */
@Service(value = "operationChaApplyMoneyService")
@Transactional
public class OperationChaApplyMoneyServiceImpl implements IOperationChaApplyMoneyService {
	@Resource
	private IOperationChaApplyMoneyDao operationChaApplyMoneyDao;
	
	
	@Resource
	private IDevApplyMoneyDao devApplyMoneyDao;
	
	
	@Resource
	private ISysUserDao sysUserDao;

	/**
	 */
	@Override
	public DevApplyMoney getById(Long id) throws Exception {
		DevApplyMoney DevApplyMoney = new DevApplyMoney();
		DevApplyMoney = devApplyMoneyDao.getById(id);
		return DevApplyMoney;
	}


	@Override
	public void update(DevApplyMoney DevApplyMoney) throws Exception {
		operationChaApplyMoneyDao.update(DevApplyMoney);
	}
	

	/**
	 * (non-Javadoc)
	* <p>Title: findReportSum</p>
	* <p>Description:TODO</p>
	* @param bean
	* @param tag
	* @return
	* @throws Exception
	* @see cn.adwalker.ad.admin.operation.service.IOperationDevApplyMoneyService#findReportSum(cn.adwalker.admin.operation.bean.DevApplyMoneySearchBean, int)
	 */
	@Transactional(propagation = Propagation.NEVER)
	@Override
	public ChaApplyMoney findReportSum(ChaApplyMoneySearchBean bean, int tag)
			throws Exception {
		List<ChaApplyMoney> listReportDMOperAudit = this.operationChaApplyMoneyDao
				.findReport(bean, tag);
		// 数据数量
		int size = listReportDMOperAudit.size();
		// 申请金额汇总
		float sum_ApplyMoney = 0;
		// 确认金额汇总
		float sum_ManagerMoney = 0;
		ChaApplyMoney sum = new ChaApplyMoney();
		for (int i = 0; i < size; i++) {
			ChaApplyMoney devApp = listReportDMOperAudit.get(i);

			sum_ApplyMoney += devApp.getApplyMoney() != null ? devApp
					.getApplyMoney() : 0;
			if (devApp != null
					&& !devApp
							.getStatus()
							.equals(String
									.valueOf(DevelopConstants.DEV_APPLY_MONEY_STATUS_CANCEL))) {
				sum_ManagerMoney += devApp.getManagerMoney() != null ? devApp
						.getManagerMoney() : 0;
			}

		}
		sum.setApplyMoney(((Float) sum_ApplyMoney).doubleValue());
		sum.setManagerMoney(((Float) sum_ManagerMoney).doubleValue());
		return sum;
	}

	/**
	 * 运营审核处理 更新数据的审核信息。
	 */
	@Override
	public void tranAuditInOper(Long[] ids, int status, String[] managerDescs,String[] invoice,Integer[] invoice_sta ,
			double[] managerMoneys, SysUserVo manageUser)
			throws Exception {
		Long manageUserId = manageUser.getId();
		List<ChaApplyMoney> updateList = new ArrayList<ChaApplyMoney>();// 待更新列表
		ChaApplyMoney bean;
		for (int i = 0; i < ids.length; i++) {
			bean = new ChaApplyMoney();

			bean.setId(ids[i]);
			bean.setManagerId(manageUserId);// 运营审批人id
			bean.setStatus(String.valueOf(status));// 状态
			bean.setManagerTime(new Date());// 运营审批时间
			bean.setManagerDesc(managerDescs[i]);// 运营审批描述
			bean.setInvoice_name(invoice[i]);// 运营发票号
			bean.setInvoice(invoice_sta[i]);// 运营发票状态
			try {
				bean.setManagerMoney(managerMoneys[i]);// 运营确认金额
			} catch (Exception e) {
				bean.setManagerMoney(null);// 不存在默认为null
			}
			updateList.add(bean);
		}
		try {
			this.operationChaApplyMoneyDao.batchAuditInOper(updateList);// 批量审核处理
			// 记录日志
		} catch (Exception e) {
			// 记录日志
		}
	}

	@Override
	public Integer findOperator(String operator) {
		Integer operators = sysUserDao.findOperator(operator);
		return operators;
	}

	/**
	 * (non-Javadoc)
	* <p>Title: findAuditList</p>
	* <p>Description:TODO</p>
	* @param bean
	* @param pageInfo
	* @return
	* @throws Exception
	* @see cn.adwalker.ad.admin.operation.service.IOperationChaApplyMoneyService#findAuditList(cn.adwalker.ad.admin.operation.bean.ChaApplyMoneySearchBean, cn.adwalker.core.page.IPageInfo)
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.NEVER)
	@Override
	public List<ChaApplyMoney> findAuditList(ChaApplyMoneySearchBean bean,
			IPageInfo pageInfo) throws Exception {
		List<Object> list = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer();
			sql.append("v_cha_apply_money de where 1=1 ");	 
		if (ObjectUtils.isNotEmpty((bean.getId()))) {
			sql.append(" and de.id ");
			sql.append(" like '%");
			sql.append(bean.getId());
			sql.append("%'");
		}
		if (ObjectUtils.isNotEmpty((bean.getOperator()))) {
			sql.append(" and de.manager_name  ");
			sql.append(" like '%");
			sql.append(bean.getOperator());
			sql.append("%'");
		}	
		
		//申请时间
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
		sql.append(" and de.status in (0,1,-1,2,-2,-3) ");		
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
		if (ObjectUtils.isNotEmpty(bean.getOperatorBegin())) {
			sql.append(" and date_format(de.MANAGER_TIME, '%Y-%m-%d') >= '");
			sql.append(bean.getOperatorBegin());
			sql.append("'");
		}

		if (ObjectUtils.isNotEmpty(bean.getOperatorEnd())) {
			sql.append(" and date_format(de.MANAGER_TIME, '%Y-%m-%d') <= '");
			sql.append(bean.getOperatorEnd());
			sql.append("'");
		}
		
		if (ObjectUtils.isNotEmpty((bean.getStatus()))) {
			sql.append(" and de.status = '");
			sql.append(bean.getStatus());
			sql.append("'");
		}
		if (ObjectUtils.isNotEmpty((bean.getTax_status()))) {
			sql.append(" and de.tax_status = '");
			sql.append(bean.getTax_status());
			sql.append("'");
		}
		if (ObjectUtils.isNotEmpty((bean.getInvoice_status()))) {
			sql.append(" and de.invoice = '");
			sql.append(bean.getInvoice_status());
			sql.append("'");
		}
		return (List<ChaApplyMoney>) operationChaApplyMoneyDao.findByPage("de.*,de.cha_id as channelId,de.balance_money as balance, manager_name as operator",
				sql.toString(), list.toArray(), " order by create_time desc ",
				ChaApplyMoney.class, pageInfo);
	}

}