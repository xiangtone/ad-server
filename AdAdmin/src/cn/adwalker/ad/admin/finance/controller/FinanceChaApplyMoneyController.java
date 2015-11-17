package cn.adwalker.ad.admin.finance.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.adwalker.ad.admin.common.controller.AbstractControllerParent;
import cn.adwalker.ad.admin.common.vo.SysUserVo;
import cn.adwalker.ad.admin.finance.bean.ChaApplyMoneylbean;
import cn.adwalker.ad.admin.finance.service.IFinanceChaApplyMoneyService;
import cn.adwalker.ad.admin.finance.vo.FinanceApplyChannelSumVo;
import cn.adwalker.ad.admin.finance.vo.FinanceChaApplyMoneyVo;
import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.page.SetPage;
import cn.adwalker.core.util.ExportUtils;
import cn.adwalker.core.util.lang.MathUtil;
import cn.adwalker.core.util.lang.ObjectUtils;

/**
* <p>Title: FinanceChaApplyMoneyController</p>
* <p>Description:渠道提款支付</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2014-2-17
 */
@Controller
public class FinanceChaApplyMoneyController extends AbstractControllerParent {

	/** 日志记录器 */
	Logger logger = Logger.getLogger(FinanceChaApplyMoneyController.class);

	@Resource
	private IFinanceChaApplyMoneyService financeChaApplyMoneyService;

	/**
	* <p>Title: financeChannekApplyList</p>
	* <p>Description:查询财务渠道财务提款List</p>
	* @param request
	* @param response
	* @param bean
	* @return
	* @author lichuang
	* @date 2014-2-17
	* @return String
	* @version 1.0
	 */
					
	@RequestMapping("/manage!financeChannekApplyList.do")
	public String financeChannekApplyList(HttpServletRequest request,
			HttpServletResponse response, ChaApplyMoneylbean bean) {
		try {

			List<FinanceChaApplyMoneyVo> listinanceAudit = new ArrayList<FinanceChaApplyMoneyVo>();
			List<FinanceApplyChannelSumVo> listinanceAuditSum = new ArrayList<FinanceApplyChannelSumVo>();
			IPageInfo pageInfo = new SetPage(request);
			// 申请金额汇总
			Double appleMoneySum = 0d;
			// 实际支付金额汇总
			Double finance_moneySum = 0d;
			// 扣税金额汇总
			Double finance_taxSum = 0d;
			// 扣费金额汇总
			Double finance_duesSum = 0d;
			try {
				// list数据
				listinanceAudit = this.financeChaApplyMoneyService
						.findChaApplyListPage(bean, pageInfo);
				// list数据和
				listinanceAuditSum = this.financeChaApplyMoneyService.findAll(bean);
				for (FinanceApplyChannelSumVo chaApplyMoneySum : listinanceAuditSum) {
					appleMoneySum = MathUtil
							.add(appleMoneySum != null ? appleMoneySum : 0d,
									chaApplyMoneySum.getManagerMoney() != null ? chaApplyMoneySum
											.getManagerMoney() : 0d);
					finance_moneySum = MathUtil.add(finance_moneySum,
							chaApplyMoneySum.getFinance_money());
					finance_taxSum = MathUtil.add(finance_taxSum,
							chaApplyMoneySum.getFinance_tax());
					finance_duesSum = MathUtil.add(finance_duesSum,
							chaApplyMoneySum.getFinance_dues());
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("entiy", listinanceAudit);
			request.setAttribute("bean", bean);
			request.setAttribute("appleMoneySum", appleMoneySum);
			request.setAttribute("finance_moneySum", finance_moneySum);
			request.setAttribute("finance_duesSum", finance_duesSum);
			request.setAttribute("finance_taxSum", finance_taxSum);
			request.setAttribute("pageRecord", pageInfo.getTotalRow());
			request.setAttribute("page", pageInfo.getPageSize());
			return "manage/finance/finance_apply_channel_list";
		} catch (Exception e) {
			logger.debug("获取开发者提款审核列表异常！");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * <p>
	 * Title: devDownloadIncomeManage
	 * </p>
	 * <p>
	 * Description:查询财务网站主财务提款List导出报表
	 * </p>
	 * 
	 * @param session
	 * @param request
	 * @param ids
	 * @param finance_taxs
	 * @param finance_duess
	 * @param response
	 * @param bean
	 * @return
	 * @author lichuang
	 * @date 2013-5-15
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!financeApplyChaDownloadList.do")
	public String financeApplyDownloadList(HttpSession session,
			HttpServletRequest request, String ids, String finance_taxs,
			String finance_duess, HttpServletResponse response,
			ChaApplyMoneylbean bean) {
		try {
			try {
				Long[] checkbox = null;
				if (!StringUtils.isEmpty(ids)) {
					String[] newids = ids.trim().split(",");
					checkbox = new Long[newids.length];
					for (int i = 0; i < newids.length; i++) {
						checkbox[i] = Long.valueOf((newids[i]));
					}
				}

				double[] finance_taxsI = null;
				if (!StringUtils.isEmpty(finance_taxs)) {
					String[] finance_taxsString = finance_taxs.trim()
							.split(",");
					finance_taxsI = new double[finance_taxsString.length];
					for (int i = 0; i < finance_taxsString.length; i++) {
						finance_taxsI[i] = Double
								.valueOf((finance_taxsString[i]));
					}
				}
				double[] finance_duessI = null;
				if (!StringUtils.isEmpty(finance_duess)) {
					String[] finance_duessString = finance_duess.trim().split(
							",");
					finance_duessI = new double[finance_duessString.length];
					for (int i = 0; i < finance_duessString.length; i++) {
						finance_duessI[i] = Double
								.valueOf((finance_duessString[i]));
					}
				}
				if(ObjectUtils.isNotEmpty(checkbox)&&ObjectUtils.isNotEmpty(finance_duessI)&&ObjectUtils.isNotEmpty(finance_taxsI)){
					this.financeChaApplyMoneyService.tranAuditInFinance(checkbox,
							finance_taxsI, finance_duessI);
				}

			} catch (Exception e) {
				e.printStackTrace();
				logger.debug("报表导出异常！");
				return null;
			}
			List<FinanceChaApplyMoneyVo> listinanceAudit = new ArrayList<FinanceChaApplyMoneyVo>();
			List<FinanceApplyChannelSumVo> listinanceAuditSum = new ArrayList<FinanceApplyChannelSumVo>();
			IPageInfo pageInfo = new SetPage(request);
			// 申请金额汇总
			Double appleMoneySum = 0d;
			// 实际支付金额汇总
			Double finance_moneySum = 0d;
			// 扣税金额汇总
			Double finance_taxSum = 0d;
			// 扣费金额汇总
			Double finance_duesSum = 0d;
			try {
				listinanceAudit = this.financeChaApplyMoneyService
					.findDevApplyList(bean);
				// list数据和
				listinanceAuditSum = this.financeChaApplyMoneyService
					.findAll(bean);
				for (FinanceApplyChannelSumVo chaApplyMoneySum : listinanceAuditSum) {
					appleMoneySum = MathUtil
							.add(appleMoneySum != null ? appleMoneySum : 0d,
									chaApplyMoneySum.getManagerMoney() != null ? chaApplyMoneySum
											.getManagerMoney() : 0d);
					finance_moneySum = MathUtil.add(finance_moneySum,
							chaApplyMoneySum.getFinance_money());
					finance_taxSum = MathUtil.add(finance_taxSum,
							chaApplyMoneySum.getFinance_tax());
					finance_duesSum = MathUtil.add(finance_duesSum,
							chaApplyMoneySum.getFinance_dues());
				}
			} catch (Exception e) {
			}
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("appleMoneySum", appleMoneySum);
			request.setAttribute("finance_moneySum", finance_moneySum);
			request.setAttribute("finance_duesSum", finance_duesSum);
			request.setAttribute("finance_taxSum", finance_taxSum);
			request.setAttribute("entiy", listinanceAudit);
			request.setAttribute("bean", bean);

			boolean[] isMoney = new boolean[] { false, false, false, false,
					false, false, false, false, false,false, true, true, true, true,
					true, false, false, false, false };
			ExportUtils.exportExcel(listinanceAudit, "渠道提款财务审核 ",
					"渠道提款财务审核 ", new String[] { "申请单编号", " 申请时间"," 渠道ID", " 渠道 账号",
							" 开户名", "城市", " 银行名称", "开户行名称", " 开户帐号", " 支付类型","发票状态","发票号",
							" 申请金额", "账户余额", " 支付金额", " 扣税（元）", " 手续费（元）",
							" 支付状态", " 操作人名称", " 操作日期" }, new String[] {
							"id", "createTime", "cha_Id","chaEmail", "kaihu_name",
							"bank_city", "bank_name", "bank_subbranch",
							"bank_account", "pay_type","invoice_s","invoice_name", "managerMoney",
							"balance", "finance_money", "finance_tax",
							"finance_dues", "status_name", "finance_name",
							"financeTime" }, new String[] { "统计", "-", "-","-", "-", "-", "-", "-","-", "-","-",
									"-", "" + appleMoneySum, "-","" + finance_moneySum,
									"" + finance_taxSum,"" + finance_duesSum, "-", "-", "-", "-", "-" }, null, null,
					isMoney, null, response);

			return "manage/finance/finance_apply_channel_list";
		} catch (Exception e) {
			logger.debug("导出渠道提款财务审核失败！");
			e.printStackTrace();
		}
		return null;

	}

	/**
	* <p>Title: singleAuditChaFinance</p>
	* <p>Description:财务渠道提款单个审核</p>
	* @param request
	* @param response
	* @param id
	* @param status
	* @param fDesc
	* @param fMoney
	* @param pay_type
	* @param finance_tax
	* @param finance_dues
	* @return
	* @author lichuang
	* @date 2014-2-17
	* @return String
	* @version 1.0
	 */
	@RequestMapping("/manage!singleAuditChaFinance.do")
	public String singleAuditChaFinance(HttpServletRequest request,
			HttpServletResponse response, Long id, String status, String fDesc,
			String fMoney, String pay_type, String finance_tax,
			String finance_dues) {
		// 当前登录用户
		SysUserVo curManageUser = (SysUserVo) request.getSession()
				.getAttribute("manageUser");
		try {
			double financeMoney = 0;
			if (!StringUtils.isEmpty(fMoney)) {
				int tag = Integer.parseInt(status);
				if (tag == 2) {
					financeMoney = Double.valueOf(fMoney);
				}
			}
			Long[] ids = { id };
			double[] financeMoneys = { financeMoney };
			double[] finance_taxs = { Double.valueOf(finance_tax) };
			double[] finance_duess = { Double.valueOf(finance_dues) };
			int[] pay_types = { Integer.parseInt(pay_type) };
			String[] financeDescs = { fDesc };
			this.financeChaApplyMoneyService.tranAuditInFinance(ids,
					Integer.parseInt(status), financeDescs, financeMoneys,
					curManageUser, pay_types, finance_taxs, finance_duess);
		} catch (Exception e) {
			logger.debug("渠道财务运营单个审核异常！");
			e.printStackTrace();
			try {
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return null;
	}

	/**
	* <p>Title: batchAuditChaFinance</p>
	* <p>Description:财务渠道批量审核提款</p>
	* @param request
	* @param response
	* @param ids
	* @param status
	* @param fDescs
	* @param fMoneys
	* @param pay_types
	* @param finance_taxs
	* @param finance_duess
	* @param balances
	* @return
	* @author lichuang
	* @date 2014-2-17
	* @return String
	* @version 1.0
	 */
	@RequestMapping("/manage!batchAuditChaFinance.do")
	public String batchAuditChaFinance(HttpServletRequest request,
			HttpServletResponse response, String ids, String status,
			String fDescs, String fMoneys, String pay_types,
			String finance_taxs, String finance_duess, String balances) {
		// 当前登录用户
		SysUserVo curManageUser = (SysUserVo) request.getSession()
				.getAttribute("manageUser");
		try {
			String[] newids = ids.trim().split(",");
			Long[] checkbox = new Long[newids.length];
			if (!"".equals(ids.trim())) {
				for (int i = 0; i < newids.length; i++) {
					checkbox[i] = Long.valueOf((newids[i]));
				}
			}
			String[] pay_typesString = pay_types.trim().split(",");
			int[] pay_typeI = new int[pay_typesString.length];
			if (!"".equals(pay_types.trim())) {
				for (int i = 0; i < pay_typesString.length; i++) {
					pay_typeI[i] = Integer.valueOf((pay_typesString[i]));
				}
			}
			String[] finance_taxsString = finance_taxs.trim().split(",");
			double[] finance_taxsI = new double[finance_taxsString.length];
			if (!"".equals(finance_taxs.trim())) {
				for (int i = 0; i < finance_taxsString.length; i++) {
					finance_taxsI[i] = Double.valueOf((finance_taxsString[i]));
				}
			}
			String[] finance_duessString = finance_duess.trim().split(",");
			double[] finance_duessI = new double[finance_duessString.length];
			if (!"".equals(finance_duess.trim())) {
				for (int i = 0; i < finance_duessString.length; i++) {
					finance_duessI[i] = Double
							.valueOf((finance_duessString[i]));
				}
			}

			String[] fMoneysString = fMoneys.trim().split(",");
			double[] fms = new double[fMoneysString.length];
			if (!"".equals(fMoneys.trim())) {
				for (int i = 0; i < fMoneysString.length; i++) {
					fms[i] = Double.valueOf((fMoneysString[i]));
				}
			}
			String[] fDescString = fDescs.split(",");

			int tag = Integer.parseInt(status);
			String[] fds;
			if (tag != 2) {
				fds = new String[fDescString.length];
				for (int i = 0; i < fDescString.length; i++) {
					fds[i] = fDescString[i];
				}
			} else {
				fds = new String[fMoneysString.length];
				for (int i = 0; i < fMoneysString.length; i++) {
					fds[i] = " ";
				}
			}

			this.financeChaApplyMoneyService.tranAuditInFinance(checkbox,
					Integer.parseInt(status), fds, fms, curManageUser, pay_typeI,
					finance_taxsI, finance_duessI);
		} catch (Exception e) {
			logger.debug("渠道财务单个审核异常！");
			e.printStackTrace();
		}
		return null;
	}

}
