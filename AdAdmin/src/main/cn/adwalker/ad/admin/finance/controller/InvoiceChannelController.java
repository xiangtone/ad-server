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
import cn.adwalker.ad.admin.finance.bean.CostBean;
import cn.adwalker.ad.admin.finance.bean.InvoiceChannelBean;
import cn.adwalker.ad.admin.finance.bean.PaymentChannelBean;
import cn.adwalker.ad.admin.finance.service.IInvoiceChannelService;
import cn.adwalker.ad.admin.finance.vo.AchievementReportSumVo;
import cn.adwalker.ad.admin.finance.vo.CostVo;
import cn.adwalker.ad.admin.finance.vo.InvoiceChannelVo;
import cn.adwalker.ad.admin.finance.vo.PaymentChannelVo;
import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.page.SetPage;

@Controller
public class InvoiceChannelController extends AbstractControllerParent {
	
	Logger logger = Logger.getLogger(InvoiceChannelController.class);
	
	@Resource
	private IInvoiceChannelService invoiceChannelService;
	
	/**
	* <p>Title: invoiceChannelList</p>
	* <p>Description:渠道发票明细表</p>
	* @param request
	* @param response
	* @param bean
	* @return
	* @author cuidd
	* @date 2014年11月17日
	* @return String
	* @version 1.0
	 */
	@RequestMapping("/manage!invoiceChannelList.do")
	public String invoiceChannelList(HttpServletRequest request, HttpServletResponse response, InvoiceChannelBean bean) {
		List<InvoiceChannelVo> list = null;
		IPageInfo pageInfo = new SetPage(request);
		try {
			list = invoiceChannelService.findByPage(bean, pageInfo);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("list", list);
			request.setAttribute("bean", bean);
			request.setAttribute("pageRecord", pageInfo.getTotalRow());
			request.setAttribute("page", pageInfo.getPageSize());
		} catch (Exception e) {
			logger.debug("获取发票列表异常！");
			e.printStackTrace();
		}
		return "finance/invoice_channel_list";
	}

	/**
	* <p>Title: submitInvoiceChannelNum</p>
	* <p>Description:渠道收票状态修改</p>
	* @param request
	* @param Response
	* @param ids
	* @param remarks
	* @param session
	* @return
	* @throws IOException
	* @author cuidd
	* @date 2014年11月17日
	* @return String
	* @version 1.0
	 */
	@RequestMapping("/manage!submitInvoiceChannelNum.do")
	public @ResponseBody String submitInvoiceChannelNum(HttpServletRequest request, HttpServletResponse Response, String ids, String remarks, HttpSession session) throws IOException {
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
			invoiceChannelService.updateInvoiceChannel(objList);
		} catch (Exception e) {
			e.printStackTrace();
			return "0";
		}
		return "1";
	}

	/**
	* <p>Title: financeChannelPaymentlist</p>
	* <p>Description:付款明细表</p>
	* @param request
	* @param response
	* @param bean
	* @return
	* @author cuidd
	* @date 2014年11月17日
	* @return String
	* @version 1.0
	 */
	@RequestMapping("/manage!financeChannelPaymentlist.do")
	public String financeChannelPaymentlist(HttpServletRequest request, HttpServletResponse response, PaymentChannelBean bean) {
		List<PaymentChannelVo> list = null;
		IPageInfo pageInfo = new SetPage(request);
		try {
			list = invoiceChannelService.findByPage(bean, pageInfo);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("list", list);
			request.setAttribute("bean", bean);
			request.setAttribute("pageRecord", pageInfo.getTotalRow());
			request.setAttribute("page", pageInfo.getPageSize());
		} catch (Exception e) {
			logger.debug("获取付款列表异常！");
			e.printStackTrace();
		}
		return "finance/payment_channel_list";
	}
	
	/**
	* <p>Title: submitPaymentNum</p>
	* <p>Description:渠道付款状态改变</p>
	* @param request
	* @param Response
	* @param ids
	* @param remarks
	* @param session
	* @return
	* @throws IOException
	* @author cuidd
	* @date 2014年11月17日
	* @return String
	* @version 1.0
	 */
	@RequestMapping("/manage!submitPaymentChannelNum.do")
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
			invoiceChannelService.updatePayment(objList);
		} catch (Exception e) {
			e.printStackTrace();
			return "0";
		}
		return "1";
	}

	/**
	* <p>Title: channelCostList</p>
	* <p>Description:渠道成本结算表</p>
	* @param request
	* @param response
	* @param bean
	* @return
	* @author cuidd
	* @date 2014年11月17日
	* @return String
	* @version 1.0
	 */
	@RequestMapping("/finance/channelCostList.do")
	public String channelCostList(HttpServletRequest request, HttpServletResponse response, CostBean bean) {
		List<CostVo> list = null;
		AchievementReportSumVo sum = null;
		IPageInfo pageInfo = new SetPage(request);
		try {
			list = invoiceChannelService.findByPage(bean, pageInfo);
			// 求和
			sum = invoiceChannelService.findSum(bean);
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
		return "finance/cost_list";
	}
	
}
