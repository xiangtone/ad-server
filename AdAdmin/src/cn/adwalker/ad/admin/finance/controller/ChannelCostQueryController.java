package cn.adwalker.ad.admin.finance.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.adwalker.ad.admin.common.controller.AbstractControllerParent;
import cn.adwalker.ad.admin.common.vo.SysUserVo;
import cn.adwalker.ad.admin.finance.bean.ChannelCostQueryBean;
import cn.adwalker.ad.admin.finance.form.ChannelConfirmFrom;
import cn.adwalker.ad.admin.finance.service.IChannelCostQueryService;
import cn.adwalker.ad.admin.finance.vo.ChannelConfirmVo;
import cn.adwalker.ad.admin.finance.vo.ChannelCostSumVo;
import cn.adwalker.ad.admin.finance.vo.ChannelCostVo;
import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.page.SetPage;
import cn.adwalker.core.util.ExportUtils;
import cn.adwalker.core.util.JacksonMapper;
import cn.adwalker.core.util.OutputHelper;
import cn.adwalker.core.vo.ResultErrorVo;
import cn.adwalker.core.vo.ResultSuccessVo;
import cn.adwalker.core.vo.ResultVo;

/**
 * <p>
 * Title: DevIncomeAuditController
 * </p>
 * <p>
 * Description:运营开发者收入审核
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-6-17
 */

@Controller
public class ChannelCostQueryController extends AbstractControllerParent {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory
			.getLogger(ChannelCostQueryController.class);

	@Resource
	private IChannelCostQueryService channelCostQueryService;

	/**
	* <p>Title: list</p>
	* <p>Description:渠道成本</p>
	* @param session
	* @param request
	* @param bean
	* @return
	* @throws Exception
	* @author cuidd
	* @date 2014年8月13日
	* @return String
	* @version 1.0
	 */
	@RequestMapping("/channelCostList.do")
	public String list(HttpSession session, HttpServletRequest request,
			ChannelCostQueryBean bean) throws Exception {
		List<ChannelCostVo> list = null;
		ChannelCostSumVo sum = null;
		IPageInfo pageInfo = new SetPage(request);
		try {
			list = this.channelCostQueryService.findList(bean, pageInfo);
			sum = channelCostQueryService.findSumByCondition(bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("list", list);
		request.setAttribute("sum", sum);
		request.setAttribute("bean", bean);
		return "finance/channel_cost_list";
	}
	
	
	/**
	* <p>Title: submitChannelCost</p>
	* <p>Description:插入汇总数据</p>
	* @param request
	* @param Response
	* @param ids
	* @param session
	* @return
	* @throws IOException
	* @author cuidd
	* @date 2014年11月17日
	* @return String
	* @version 1.0
	 */
	@RequestMapping("/manage!submitChannelCost.do")
	public String submitChannelCost(HttpServletRequest request,
			HttpServletResponse Response, String ids,HttpSession session) throws IOException {
		manageUser = (SysUserVo) session.getAttribute("manageUser");
		ResultVo vo = null;
		try {
			Long income_id=this.channelCostQueryService.submitIncomeNum(ids, manageUser);
			vo = new ResultSuccessVo(income_id);
		} catch (Exception e) {
			e.printStackTrace();
			vo = new ResultErrorVo("系统错误");
		}
		OutputHelper.outPut(Response, JacksonMapper.objectToJsonString(vo));
		return null;
	}
	
	
	/**
	* <p>Title: entering</p>
	* <p>Description:申请显示页</p>
	* @param request
	* @param Response
	* @param session
	* @param model
	* @param income_id
	* @return
	* @throws Exception
	* @author cuidd
	* @date 2014年11月17日
	* @return String
	* @version 1.0
	 */
	@RequestMapping("/manage!channelDayIncome.do")
	public String entering(HttpServletRequest request,
			HttpServletResponse Response, HttpSession session, Map<String, Object> model,Long income_id)
			throws Exception {
		ChannelConfirmVo plist=new ChannelConfirmVo();
		try {
			plist=channelCostQueryService.getById(income_id);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("vo", plist);
		return "finance/entering_channel_cost";
	}
	
	/**
	* <p>Title: saveChannelCostDay</p>
	* <p>Description:提交渠道成本结算明细</p>
	* @param request
	* @param response
	* @param model
	* @param session
	* @param from
	* @return
	* @throws Exception
	* @author cuidd
	* @date 2014年11月17日
	* @return String
	* @version 1.0
	 */
	@RequestMapping("/manage!saveChannelCostDay.do")
	public String saveChannelCostDay(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> model,
			HttpSession session, ChannelConfirmFrom from) throws Exception {
		manageUser = (SysUserVo) session.getAttribute("manageUser");
		ResultVo vo = null;
		try {
			channelCostQueryService.updateStatus(from);
		} catch (Exception e) {
			e.printStackTrace();
		}
		vo = new ResultSuccessVo();
		OutputHelper.outPut(response, JacksonMapper.objectToJsonString(vo));
		return null;

	}
	/**
	* <p>Title: deleteChannelCostDay</p>
	* <p>Description:取消渠道申请</p>
	* @param request
	* @param response
	* @param model
	* @param session
	* @param from
	* @return
	* @throws Exception
	* @author cuidd
	* @date 2014年12月10日
	* @return String
	* @version 1.0
	 */
	@RequestMapping("/manage!deleteChannelCostDay.do")
	public String deleteChannelCostDay(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> model,
			HttpSession session,Long id) throws Exception {
		manageUser = (SysUserVo) session.getAttribute("manageUser");
		ResultVo vo = null;
		try {
			channelCostQueryService.deleteStatus(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		vo = new ResultSuccessVo();
		OutputHelper.outPut(response, JacksonMapper.objectToJsonString(vo));
		return null;
		
	}
	
	/**
	 * <p>
	 * Title: devAdStatListDownloadManage
	 * </p>
	 * <p>
	 * Description:开发者收入审核List导出报表
	 * </p>
	 * 
	 * @param session
	 * @param request
	 * @param response
	 * @param bean
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-6-17
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/channelCostDownload.do")
	public String devAdStatListDownload(HttpSession session,
			HttpServletRequest request, HttpServletResponse response,
			ChannelCostQueryBean bean) throws Exception {
		// 组装vo
		List<ChannelCostVo> devList = new ArrayList<ChannelCostVo>();
		ChannelCostSumVo sum = null;
		IPageInfo pageInfo = new SetPage(request);
		try {
			devList = this.channelCostQueryService.findAll(bean);
			sum = channelCostQueryService.findSumByCondition(bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("devList", devList);
		request.setAttribute("summary", sum);
		request.setAttribute("bean", bean);
		// 此处调用excel导出工具类方法
		// 导出报表
		boolean[] isMoney = new boolean[] { false, false, true, false, false,
				false, false, false, false, true, false, false, false, false,
				false };

		ExportUtils.exportExcel(devList, "网站主收入审核明细", "网站主收入审核明细",
				new String[] { "发生时间", "网站主ID", "网站主名称", "账户状态", "应用ID",
						"应用名称", "活动ID", "活动名称", "结算状态", "网站主确认佣金", "业绩提交人",
						"业绩提交时间", "审核人", "审核时间" }, new String[] {
						"effect_time", "dev_id", "dev_email",
						"dev_statusString", "app_id", "app_name",
						"campaign_id", "campaign_name", "statusString",
						"confirmMoney", "manager_name", "manager_time",
						"finance_name", "finance_time" }, new String[] { "汇总",
						"--", "--", "--", "--", "--", "--", "--", "--",
						"" + sum.getSum_cost(), "--", "--", "--", "--" },
				null, null, isMoney, null, response);
		return "finance/dev_income_audit_list";
	}
}
