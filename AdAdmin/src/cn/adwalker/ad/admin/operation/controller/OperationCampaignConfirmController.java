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
import cn.adwalker.ad.admin.operation.bean.CampaignConfirmbean;
import cn.adwalker.ad.admin.operation.form.CampaignConfirmEditForm;
import cn.adwalker.ad.admin.operation.service.IOperationCampaignConfirmService;
import cn.adwalker.ad.admin.operation.vo.CampaignConfirmSumVo;
import cn.adwalker.ad.admin.operation.vo.CampaignConfirmVo;
import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.page.SetPage;
import cn.adwalker.core.util.ExportUtils;
import cn.adwalker.core.util.JacksonMapper;
import cn.adwalker.core.util.OutputHelper;
import cn.adwalker.core.vo.ResultErrorVo;
import cn.adwalker.core.vo.ResultSuccessVo;
import cn.adwalker.core.vo.ResultVo;
import cn.adwalker.model.ad.domain.Area;

/**
* <p>Title: OperationCampaignConfirmController</p>
* <p>Description:结算数据</p>
* <p>Company: adwalker</p> 
* @author    lichuang
* @date       2014年11月15日
 */
@Controller
public class OperationCampaignConfirmController extends
		AbstractControllerParent {

	@Resource
	private IOperationCampaignConfirmService campaignConfirmService;

	/** 管理员实体 */
	private SysUserVo manageUser;
	/** 日志记录器 */
	Logger logger = Logger.getLogger(OperationCampaignConfirmController.class);



	/**
	 * <p>
	 * Title: CampaignConfirmList
	 * </p>
	 * <p>
	 * Description:确认数录入List
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @param bean
	 * @return
	 * @author lichuang
	 * @date 2013-6-8
	 * @return String
	 * @version 1.0
	 */

	@RequestMapping("manage!operationCampaignConfirmList.do")
	public String list(HttpServletRequest request,
			HttpServletResponse response, CampaignConfirmbean bean) {
		List<CampaignConfirmVo> list = null;
		CampaignConfirmSumVo sum = null;
		IPageInfo pageInfo = new SetPage(request);
		try {
			list = this.campaignConfirmService.findByPage(bean, pageInfo);
			// 求和
			sum = campaignConfirmService.findSum(bean);
			List<Area> areaList =campaignConfirmService.findByArea();
			request.setAttribute("areaList", areaList);
			request.setAttribute("list", list);
			request.setAttribute("bean", bean);
			request.setAttribute("sum", sum);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("pageRecord", pageInfo.getTotalRow());
			request.setAttribute("page", pageInfo.getPageSize());

		} catch (Exception e) {
			logger.debug("获取广告效果列表异常！");
			e.printStackTrace();
		}
		return "operation/ope_campaign_confirm_list";
	}

	/**
	 * <p>
	 * Title: CampaignConfirmList
	 * </p>
	 * <p>
	 * Description:下载
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @param bean
	 * @return
	 * @author lichuang
	 * @date 2013-10-14
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("manage!CampaignConfirmListDown.do")
	public String CampaignConfirmListDown(HttpServletRequest request,
			HttpServletResponse response, CampaignConfirmbean bean) {
		List<CampaignConfirmVo> list = null;
		CampaignConfirmSumVo sum = null;
		IPageInfo pageInfo = new SetPage(request);
		try {
			list = this.campaignConfirmService.findAll(bean);
			// 求和
			sum = campaignConfirmService.findSum(bean);
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
		return "operation/ope_campaign_confirm_list";
	}

	/**
	 * <p>
	 * Title: campaignConfirm
	 * </p>
	 * <p>
	 * Description:修改
	 * </p>
	 * 
	 * @param request
	 * @param Response
	 * @param Id
	 * @param number
	 * @return
	 * @throws IOException
	 * @author lichuang
	 * @date 2013-6-8
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!campaignConfirm.do")
	public String campaignConfirm(HttpServletRequest request,
			HttpServletResponse Response, CampaignConfirmEditForm form,
			HttpSession session) throws IOException {
		manageUser = (SysUserVo) session.getAttribute("manageUser");
		ResultVo vo = null;
		try {
			vo = new ResultSuccessVo();
			this.campaignConfirmService.updateConfirm(form, manageUser);
		} catch (Exception e) {
			e.printStackTrace();
			vo = new ResultErrorVo("系统错误");

		}
		OutputHelper.outPut(Response, JacksonMapper.objectToJsonString(vo));
		return null;
	}

	
	/**
	* <p>Title: submitInComeNum</p>
	* <p>Description:批量通过</p>
	* @param request
	* @param Response
	* @param ids
	* @param inremark
	* @param session
	* @return
	* @throws IOException
	* @author cuidd
	* @date 2014年11月15日
	* @return String
	* @version 1.0
	 */
	@RequestMapping("/manage!submitInComeNum.do")
	public @ResponseBody String submitInComeNum(HttpServletRequest request, HttpServletResponse Response, String ids, String inremark, HttpSession session) throws IOException {
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
			campaignConfirmService.updateInCome(objList,idList,null);
		} catch (Exception e) {
			e.printStackTrace();
			return "0";
		}
		return "1";
	}
	
	
	/**
	* <p>Title: submitInComeNum</p>
	* <p>Description:批量不通过</p>
	* @param request
	* @param Response
	* @param ids
	* @param inremark
	* @param session
	* @return
	* @throws IOException
	* @author cuidd
	* @date 2014年11月15日
	* @return String
	* @version 1.0
	 */
	@RequestMapping("/manage!submitInComeNumNot.do")
	public @ResponseBody String submitInComeNumNot(HttpServletRequest request, HttpServletResponse Response, String ids, String inremark, HttpSession session) throws IOException {
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
			campaignConfirmService.updateInCome(objList,idList);
		} catch (Exception e) {
			e.printStackTrace();
			return "0";
		}
		return "1";
	}
}
