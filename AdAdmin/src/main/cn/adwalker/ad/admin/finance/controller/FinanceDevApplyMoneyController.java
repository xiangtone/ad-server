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
import cn.adwalker.ad.admin.finance.bean.DevApplyMoneylbean;
import cn.adwalker.ad.admin.finance.service.IFinanceDevApplyMoneyService;
import cn.adwalker.ad.admin.finance.vo.FinanceApplyMoneyDownVo;
import cn.adwalker.ad.admin.finance.vo.FinanceApplyMoneySumVo;
import cn.adwalker.ad.admin.finance.vo.FinanceApplyMoneyVo;
import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.page.SetPage;
import cn.adwalker.core.util.ExportUtils;
import cn.adwalker.core.util.lang.MathUtil;

/**
 * <p>
 * Title: FinanceDevApplyMoneyController
 * </p>
 * <p>
 * Description:网站主财务提款审核
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-6-7
 */
@Controller
public class FinanceDevApplyMoneyController extends AbstractControllerParent {

	/** 日志记录器 */
	Logger logger = Logger.getLogger(FinanceDevApplyMoneyController.class);

	@Resource
	private IFinanceDevApplyMoneyService financeDevApplyMoneyService;

	/**
	 * <p>
	 * Title: listDMFinanceAudit
	 * </p>
	 * <p>
	 * Description:查询财务网站主财务提款List
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @param bean
	 * @return
	 * @author lichuang
	 * @date 2013-6-7
	 * @return String
	 * @version 1.0
	 */

	@RequestMapping("manage!financeApplymoneyList.do")
	public String financeApplymoneyList(HttpServletRequest request, HttpServletResponse response, DevApplyMoneylbean bean) {
		try {

			List<FinanceApplyMoneyVo> listDMFinanceAudit = new ArrayList<FinanceApplyMoneyVo>();
			List<FinanceApplyMoneySumVo> listDMFinanceAuditSum = new ArrayList<FinanceApplyMoneySumVo>();
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
				listDMFinanceAudit = this.financeDevApplyMoneyService.findDevApplyListPage(bean, pageInfo);
				// list数据和
				listDMFinanceAuditSum = this.financeDevApplyMoneyService.findAll(bean);
				for (FinanceApplyMoneySumVo devApplyMoneySum : listDMFinanceAuditSum) {
					appleMoneySum = MathUtil.add(appleMoneySum != null ? appleMoneySum : 0d, devApplyMoneySum.getManagerMoney() != null ? devApplyMoneySum.getManagerMoney() : 0d);
					finance_moneySum = MathUtil.add(finance_moneySum, devApplyMoneySum.getFinance_money());
					finance_taxSum = MathUtil.add(finance_taxSum, devApplyMoneySum.getFinance_tax());
					finance_duesSum = MathUtil.add(finance_duesSum, devApplyMoneySum.getFinance_dues());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("entiy", listDMFinanceAudit);
			request.setAttribute("bean", bean);
			request.setAttribute("appleMoneySum", appleMoneySum);
			request.setAttribute("finance_moneySum", finance_moneySum);
			request.setAttribute("finance_duesSum", finance_duesSum);
			request.setAttribute("finance_taxSum", finance_taxSum);
			request.setAttribute("pageRecord", pageInfo.getTotalRow());
			request.setAttribute("page", pageInfo.getPageSize());
			return "manage/finance/finance_apply_money_list";
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
	@RequestMapping("/manage!financeApplyDownloadList.do")
	public String financeApplyDownloadList(HttpSession session, HttpServletRequest request, String ids, String finance_taxs, String finance_duess, HttpServletResponse response, DevApplyMoneylbean bean) {
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
					String[] finance_taxsString = finance_taxs.trim().split(",");
					finance_taxsI = new double[finance_taxsString.length];
					for (int i = 0; i < finance_taxsString.length; i++) {
						finance_taxsI[i] = Double.valueOf((finance_taxsString[i]));
					}
				}
				double[] finance_duessI = null;
				if (!StringUtils.isEmpty(finance_duess)) {
					String[] finance_duessString = finance_duess.trim().split(",");
					finance_duessI = new double[finance_duessString.length];
					for (int i = 0; i < finance_duessString.length; i++) {
						finance_duessI[i] = Double.valueOf((finance_duessString[i]));
					}
				}
				this.financeDevApplyMoneyService.tranAuditInFinance(checkbox, finance_taxsI, finance_duessI);

			} catch (Exception e) {
				e.printStackTrace();
				logger.debug("报表导出异常！");
				return null;
			}
			List<FinanceApplyMoneyDownVo> listDMFinanceAudit = new ArrayList<FinanceApplyMoneyDownVo>();
			List<FinanceApplyMoneySumVo> listDMFinanceAuditSum = new ArrayList<FinanceApplyMoneySumVo>();
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
				listDMFinanceAudit = this.financeDevApplyMoneyService.findDevApplyList(bean);
				// list数据和
				listDMFinanceAuditSum = this.financeDevApplyMoneyService.findAll(bean);
				for (FinanceApplyMoneySumVo devApplyMoneySum : listDMFinanceAuditSum) {
					appleMoneySum = MathUtil.add(appleMoneySum != null ? appleMoneySum : 0d, devApplyMoneySum.getManagerMoney() != null ? devApplyMoneySum.getManagerMoney() : 0d);
					finance_moneySum = MathUtil.add(finance_moneySum, devApplyMoneySum.getFinance_money());
					finance_taxSum = MathUtil.add(finance_taxSum, devApplyMoneySum.getFinance_tax());
					finance_duesSum = MathUtil.add(finance_duesSum, devApplyMoneySum.getFinance_dues());
				}
			} catch (Exception e) {
			}
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("appleMoneySum", appleMoneySum);
			request.setAttribute("finance_moneySum", finance_moneySum);
			request.setAttribute("finance_duesSum", finance_duesSum);
			request.setAttribute("finance_taxSum", finance_taxSum);
			request.setAttribute("listDMFinanceAudit", listDMFinanceAudit);
			request.setAttribute("bean", bean);

			boolean[] isMoney = new boolean[] { false, false, false, false, false, false, false, false, false, true, true, true, true, true, false, false, false, false };
			ExportUtils.exportExcel(listDMFinanceAudit, "开发者提款财务审核 ", "开发者提款财务审核 ", new String[] { "申请单编号", " 申请时间", " 网站主", " 开户名", "城市", " 银行名称", "开户行名称", " 开户帐号", " 支付类型", "发票状态", " 申请金额", "账户余额", " 支付金额", " 扣税（元）", " 手续费（元）", " 支付状态", " 操作人名称", " 操作日期", " 操作" }, new String[] { "id", "createTime", "devEmail", "kaihu_name", "bank_city", "bank_name", "bank_subbranch", "bank_account", "pay_type", "invoice_name", "managerMoney", "balance", "finance_money", "finance_tax", "finance_dues", "status", "operator", "financeTime", "status" }, new String[] { "统计", "-", "-", "-", "-", "-", "-", "-", "-", "-", "" + appleMoneySum, "-", "" + finance_moneySum, "" + finance_taxSum, "" + finance_duesSum, "-", "-", "-", "-", "-" }, null, null, isMoney, null, response);

			return "manage/finance/finance_apply_money_list";
		} catch (Exception e) {
			logger.debug("导出开发者提款财务审核失败！");
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * <p>
	 * Title: singleAuditFinance
	 * </p>
	 * <p>
	 * Description:财务提款单个审核
	 * </p>
	 * 
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
	 * @date 2013-6-7
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!singleAuditFinance.do")
	public String singleAuditFinance(HttpServletRequest request, HttpServletResponse response, Long id, String status, String fDesc, String fMoney, String pay_type, String finance_tax, String finance_dues) {
		// 当前登录用户
		SysUserVo curManageUser = (SysUserVo) request.getSession().getAttribute("manageUser");
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
			this.financeDevApplyMoneyService.tranAuditInFinance(ids, Integer.parseInt(status), financeDescs, financeMoneys, curManageUser, pay_types, finance_taxs, finance_duess);

		} catch (Exception e) {
			logger.debug("开发者财务运营单个审核异常！");
			e.printStackTrace();
			try {
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return "redirect:manage!financeApplymoneyList.do";
	}

	/**
	 * <p>
	 * Title: batchAuditFinance
	 * </p>
	 * <p>
	 * Description:财务批量审核提款
	 * </p>
	 * 
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
	 * @date 2013-6-7
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!batchAuditFinance.do")
	public String batchAuditFinance(HttpServletRequest request, HttpServletResponse response, String ids, String status, String fDescs, String fMoneys, String pay_types, String finance_taxs, String finance_duess, String balances) {
		// 当前登录用户
		SysUserVo curManageUser = (SysUserVo) request.getSession().getAttribute("manageUser");
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
					finance_duessI[i] = Double.valueOf((finance_duessString[i]));
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
			this.financeDevApplyMoneyService.tranAuditInFinance(checkbox, Integer.parseInt(status), fds, fms, curManageUser, pay_typeI, finance_taxsI, finance_duessI);
		} catch (Exception e) {
			logger.debug("开发者财务财务单个审核异常！");
			e.printStackTrace();
		}
		return "redirect:manage!financeApplymoneyList.do";
	}

}
