package cn.adwalker.ad.admin.finance.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.adwalker.ad.admin.common.controller.AbstractControllerParent;
import cn.adwalker.ad.admin.common.vo.SysUserVo;
import cn.adwalker.ad.admin.finance.bean.IncomeBean;
import cn.adwalker.ad.admin.finance.bean.InvoiceBean;
import cn.adwalker.ad.admin.finance.bean.PaymentBean;
import cn.adwalker.ad.admin.finance.service.IInvoiceService;
import cn.adwalker.ad.admin.finance.vo.AchievementReportSumVo;
import cn.adwalker.ad.admin.finance.vo.IncomeVo;
import cn.adwalker.ad.admin.finance.vo.InvoiceVo;
import cn.adwalker.ad.admin.finance.vo.PaymentVo;
import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.page.SetPage;

@Controller
public class InvoiceController extends AbstractControllerParent {
	
	Logger logger = Logger.getLogger(InvoiceController.class);
	
	@Resource
	private IInvoiceService invoiceService;
	
	/**
	* <p>Title: CampaignConfirmList</p>
	* <p>Description: 发票明细表</p>
	* @param request
	* @param response
	* @param bean
	* @return
	* @author cuidd
	* @date 2014年8月13日
	* @return String
	* @version 1.0
	 */
	@RequestMapping("/manage!invoiceList.do")
	public String invoiceList(HttpServletRequest request, HttpServletResponse response, InvoiceBean bean) {
		List<InvoiceVo> list = null;
		IPageInfo pageInfo = new SetPage(request);
		try {
			list = invoiceService.findByPage(bean, pageInfo);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("list", list);
			request.setAttribute("bean", bean);
			request.setAttribute("pageRecord", pageInfo.getTotalRow());
			request.setAttribute("page", pageInfo.getPageSize());
		} catch (Exception e) {
			logger.debug("获取发票列表异常！");
			e.printStackTrace();
		}
		return "finance/invoice_list";
	}

	/**
	* <p>Title: submitIncomeNum</p>
	* <p>Description:插入汇总数据</p>
	* @param request
	* @param Response
	* @param ids
	* @return
	* @throws IOException
	* @author cuidd
	* @date 2014年11月14日
	* @return String
	* @version 1.0
	 */
	@RequestMapping("/manage!submitInvoiceNum.do")
	public @ResponseBody String submitInvoiceNum(HttpServletRequest request, HttpServletResponse Response, String ids, String remarks, HttpSession session) throws IOException {
		manageUser = (SysUserVo) session.getAttribute("manageUser");
		List<Object[]> objList = new ArrayList<Object[]>();
		String[] idList = ids.split(",");
		String[] remarkList = remarks.split(",");
		for(int i = 0; i < idList.length; i++) {
			String id = idList[i];
			String invoice_remark = remarkList[i];
			Object[] obj = new Object[4];
			obj[0] = 1;
			obj[1] = invoice_remark;
			obj[2] = manageUser.getId();
			obj[3] = id;
			objList.add(obj);
		}
		try {
			invoiceService.updateInvoice(objList);
		} catch (Exception e) {
			e.printStackTrace();
			return "0";
		}
		return "1";
	}

	/**
	* <p>Title: CampaignConfirmList</p>
	* <p>Description: 回款明细表</p>
	* @param request
	* @param response
	* @param bean
	* @return
	* @author cuidd
	* @date 2014年8月13日
	* @return String
	* @version 1.0
	 */
	@RequestMapping("/manage!financeIncomeAnswerlist.do")
	public String paymentList(HttpServletRequest request, HttpServletResponse response, PaymentBean bean) {
		List<PaymentVo> list = null;
		IPageInfo pageInfo = new SetPage(request);
		try {
			list = invoiceService.findByPage(bean, pageInfo);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("list", list);
			request.setAttribute("bean", bean);
			request.setAttribute("pageRecord", pageInfo.getTotalRow());
			request.setAttribute("page", pageInfo.getPageSize());
		} catch (Exception e) {
			logger.debug("获取付款列表异常！");
			e.printStackTrace();
		}
		return "finance/payment_list";
	}
	
	/**
	* <p>Title: submitIncomeNum</p>
	* <p>Description:插入汇总数据</p>
	* @param request
	* @param Response
	* @param ids
	* @return
	* @throws IOException
	* @author cuidd
	* @date 2014年11月14日
	* @return String
	* @version 1.0
	 */
	@RequestMapping("/manage!submitPaymentNum.do")
	public @ResponseBody String submitPaymentNum(HttpServletRequest request, HttpServletResponse Response, String ids, String remarks, HttpSession session) throws IOException {
		manageUser = (SysUserVo) session.getAttribute("manageUser");
		List<Object[]> objList = new ArrayList<Object[]>();
		String[] idList = ids.split(",");
		String[] remarkList = remarks.split(",");
		for(int i = 0; i < idList.length; i++) {
			String id = idList[i];
			String payment_remark = remarkList[i];
			Object[] obj = new Object[4];
			obj[0] = 1;
			obj[1] = payment_remark;
			obj[2] = manageUser.getId();
			obj[3] = id;
			objList.add(obj);
		}
		try {
			invoiceService.updatePayment(objList);
		} catch (Exception e) {
			e.printStackTrace();
			return "0";
		}
		return "1";
	}

	/**
	* <p>Title: CampaignConfirmList</p>
	* <p>Description:回款明细表</p>
	* @param request
	* @param response
	* @param bean
	* @return
	* @author cuidd
	* @date 2014年8月13日
	* @return String
	* @version 1.0
	 */
	@RequestMapping("/finance/IncomeAccountList.do")
	public String incomeAccountList(HttpServletRequest request, HttpServletResponse response, IncomeBean bean) {
		List<IncomeVo> list = null;
		AchievementReportSumVo sum = null;
		IPageInfo pageInfo = new SetPage(request);
		try {
			list = invoiceService.findByPage(bean, pageInfo);
			// 求和
			sum = invoiceService.findSum(bean);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("list", list);
			request.setAttribute("bean", bean);
			request.setAttribute("sum", sum);
			request.setAttribute("pageRecord", pageInfo.getTotalRow());
			request.setAttribute("page", pageInfo.getPageSize());
		} catch (Exception e) {
			logger.debug("获取付款列表异常！");
			e.printStackTrace();
		}
		return "finance/income_list";
	}
	
}
