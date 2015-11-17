package cn.adwalker.ad.admin.operation.controller;

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
import cn.adwalker.ad.admin.operation.bean.CahannelConfirmbean;
import cn.adwalker.ad.admin.operation.bean.CampaignConfirmbean;
import cn.adwalker.ad.admin.operation.service.IOperationChannelConfirmService;
import cn.adwalker.ad.admin.operation.vo.CampaignConfirmSumVo;
import cn.adwalker.ad.admin.operation.vo.CampaignConfirmVo;
import cn.adwalker.ad.admin.operation.vo.ChannelCampaignConfirmSumVo;
import cn.adwalker.ad.admin.operation.vo.ChannelConfirmVo;
import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.page.SetPage;
import cn.adwalker.core.util.ExportUtils;
import cn.adwalker.model.ad.domain.Area;

/**
* <p>Title: OperationChannelConfirmController</p>
* <p>Description:渠道成本结算数据</p>
* <p>Company: adwalker</p> 
* @author    lichuang
* @date       2014年11月17日
 */
@Controller
public class OperationChannelConfirmController extends
		AbstractControllerParent {

	@Resource
	private IOperationChannelConfirmService channelConfirmService;

	/** 管理员实体 */
	private SysUserVo manageUser;
	/** 日志记录器 */
	Logger logger = Logger.getLogger(OperationChannelConfirmController.class);



	/**
	* <p>Title: list</p>
	* <p>Description:渠道成本结算数据List</p>
	* @param request
	* @param response
	* @param bean
	* @return
	* @author cuidd
	* @date 2014年11月17日
	* @return String
	* @version 1.0
	 */

	@RequestMapping("/manage!operationChannelConfirmList.do")
	public String list(HttpServletRequest request,
			HttpServletResponse response, CahannelConfirmbean bean) {
		List<ChannelConfirmVo> list = null;
		ChannelCampaignConfirmSumVo sum = null;
		IPageInfo pageInfo = new SetPage(request);
		try {
			list = this.channelConfirmService.findByPage(bean, pageInfo);
			// 求和
			sum = channelConfirmService.findSum(bean);
			List<Area> areaList =channelConfirmService.findByArea();
			request.setAttribute("areaList", areaList);
			request.setAttribute("list", list);
			request.setAttribute("bean", bean);
			request.setAttribute("sum", sum);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("pageRecord", pageInfo.getTotalRow());
			request.setAttribute("page", pageInfo.getPageSize());

		} catch (Exception e) {
			logger.debug("渠道成本结算数据List！");
			e.printStackTrace();
		}
		return "operation/ope_channel_confirm_list";
	}

	/**
	* <p>Title: ChannelConfirmListDown</p>
	* <p>Description:TODO</p>
	* @param request
	* @param response
	* @param bean
	* @return
	* @author cuidd
	* @date 2014年11月17日
	* @return String
	* @version 1.0
	 */
	@RequestMapping("manage!ChannelConfirmListDown.do")
	public String ChannelConfirmListDown(HttpServletRequest request,
			HttpServletResponse response, CahannelConfirmbean bean) {
		List<CampaignConfirmVo> list = null;
		ChannelCampaignConfirmSumVo sum = null;
		IPageInfo pageInfo = new SetPage(request);
		try {
			list = this.channelConfirmService.findAll(bean);
			// 求和
			sum = channelConfirmService.findSum(bean);
			boolean[] isMoney = new boolean[] { false, false, false, false,
					false, false, false, false, true, false, false, false,
					false, true, false };

			ExportUtils.exportExcel(
					list,
					"结算数据录入",
					"结算数据录入",
					new String[] { "效果发生开始时间", "效果发生结束时间", "广告主id", "活动id",
							"活动名称", "预确认数", "确认数", "单价", "结算方式", "结算金额",
							"平台类型", "状态", "销售人员", "大区", "录数人" },
					new String[] { "month_stat_date", "month_end_date",
							"adv_id", "campaign_id", "campaign_name",
							"forecast_amount", "income_amount", "price",
							"charge_type", "income_money", "os", "status_name",
							"name", "area_type_name", "create_user_name" },
					new String[] { "汇总", "--", "--", "--", "--",
							"" + sum.getSum_forecast_amount(),
							"" + sum.getSum_forecast_money(), "--", "--",
							"" + sum.getSum_income_money(), "--", "--", "--",
							"--", "--" }, null, null, isMoney, null, response);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("list", list);
			request.setAttribute("bean", bean);
			request.setAttribute("sum", sum);
			request.setAttribute("pageRecord", pageInfo.getTotalRow());
			request.setAttribute("page", pageInfo.getPageSize());

		} catch (Exception e) {
			logger.debug("获取广告效果列表异常！");
			e.printStackTrace();
		}
		return "operation/ope_channel_confirm_list";
	}

	

	
	/**
	* <p>Title: submitCostNum</p>
	* <p>Description:批量通过</p>
	* @param request
	* @param Response
	* @param ids
	* @param inremark
	* @param session
	* @return
	* @throws IOException
	* @author cuidd
	* @date 2014年11月17日
	* @return String
	* @version 1.0
	 */
	@RequestMapping("/manage!submitCostNum.do")
	public @ResponseBody String submitCostNum(HttpServletRequest request, HttpServletResponse Response, String ids, String inremark, HttpSession session) throws IOException {
		manageUser = (SysUserVo) session.getAttribute("manageUser");
		List<Object[]> objList = new ArrayList<Object[]>();
		String[] idList = ids.split(",");
		String[] remarkList = inremark.split(",");
		for(int i = 0; i < idList.length; i++) {
			String id = idList[i];
			String income_rema = remarkList[i];
			Object[] obj = new Object[4];
			obj[0] = 3;
			obj[1] = income_rema;
			obj[2] = manageUser.getId();
			obj[3] = id;
			objList.add(obj);
		}
		try {
			channelConfirmService.updateInCome(objList,idList,null);
		} catch (Exception e) {
			e.printStackTrace();
			return "0";
		}
		return "1";
	}
	
	
	/**
	* <p>Title: submitCostNumNot</p>
	* <p>Description:批量不通过</p>
	* @param request
	* @param Response
	* @param ids
	* @param inremark
	* @param session
	* @return
	* @throws IOException
	* @author cuidd
	* @date 2014年11月17日
	* @return String
	* @version 1.0
	 */
	@RequestMapping("/manage!submitCostNumNot.do")
	public @ResponseBody String submitCostNumNot(HttpServletRequest request, HttpServletResponse Response, String ids, String inremark, HttpSession session) throws IOException {
		manageUser = (SysUserVo) session.getAttribute("manageUser");
		List<Object[]> objList = new ArrayList<Object[]>();
		String[] idList = ids.split(",");
		String[] remarkList = inremark.split(",");
		for(int i = 0; i < idList.length; i++) {
			String id = idList[i];
			String income_rema = remarkList[i];
			Object[] obj = new Object[4];
			obj[0] = 9;
			obj[1] = income_rema;
			obj[2] = manageUser.getId();
			obj[3] = id;
			objList.add(obj);
		}
		try {
			channelConfirmService.updateInCome(objList,idList);
		} catch (Exception e) {
			e.printStackTrace();
			return "0";
		}
		return "1";
	}
}
