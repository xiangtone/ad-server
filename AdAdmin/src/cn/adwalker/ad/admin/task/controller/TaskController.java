package cn.adwalker.ad.admin.task.controller;

import java.io.IOException;
import java.util.Date;

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
import cn.adwalker.ad.admin.task.service.IApiFinanceTaskService;
import cn.adwalker.ad.admin.task.service.IDevIncomeTaskService;
import cn.adwalker.ad.api.app.service.IApiService;
import cn.adwalker.core.util.DateUtil;
import cn.adwalker.core.util.DevIncomeUtils;
import cn.adwalker.core.util.JacksonMapper;
import cn.adwalker.core.util.OutputHelper;
import cn.adwalker.core.vo.ResultSuccessVo;
import cn.adwalker.core.vo.ResultVo;

/**
 * 功能描述：<br>
 * 开发者管理员后台控制层
 * 
 * @author guoyongxiang
 */
@Controller
public class TaskController extends AbstractControllerParent {
	private static final Logger loger = LoggerFactory
			.getLogger(TaskController.class);

	@Resource
	private IDevIncomeTaskService devIncomeTaskService;

	@Resource
	private IApiService apiService;

	@Resource
	private IApiFinanceTaskService apiFinanceTaskService;

	/**
	 * <p>
	 * Title: report
	 * </p>
	 * <p>
	 * Description:入口
	 * </p>
	 * 
	 * @param request
	 * @return
	 * @author lichuang
	 * @date 2013-7-9
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/report.do")
	public String report(HttpServletRequest request) {
		String statDate = DateUtil.getDateStringByPattern(
				DateUtil.getDateAddDay(-1), "yyyy-MM-dd");
		request.setAttribute("statDate", statDate);
		return "platform/report";
	}

	/**
	 * <p>
	 * Title: devIncome
	 * </p>
	 * <p>
	 * Description:媒体列表（应用）
	 * </p>
	 * 
	 * @param session
	 * @param request
	 * @param date
	 * @return
	 * @author lichuang
	 * @date 2013-5-30
	 * @return String
	 * @version 1.0
	 * @throws IOException
	 */
	@RequestMapping("report/tranceDevIncome.do")
	public void devIncome(HttpSession session, HttpServletResponse response,
			Date date) throws IOException {
		loger.debug("into method devAppListManager: 管理员查询应用列表");
		manageUser = (SysUserVo) session.getAttribute("manageUser");
		try {
			devIncomeTaskService.tranceDevIncome(date);
		} catch (Exception e) {
			loger.error(e.toString());
		}
		ResultVo vo = new ResultSuccessVo();
		OutputHelper.outPut(response, JacksonMapper.objectToJsonString(vo));
	}

	@RequestMapping("report/apiDataByDay.do")
	public void apiDataByDay(HttpSession session, HttpServletResponse response,
			Date date) throws IOException {
		loger.debug("into method devAppListManager: 管理员查询应用列表");
		manageUser = (SysUserVo) session.getAttribute("manageUser");
		try {
			apiService.exportCsvByDay(date);
		} catch (Exception e) {
			loger.error(e.toString());
		}

		ResultVo vo = new ResultSuccessVo();
		OutputHelper.outPut(response, JacksonMapper.objectToJsonString(vo));
	}

	@RequestMapping("report/apiFinance.do")
	public void apiFinance(HttpSession session, HttpServletResponse response,
			Date date) throws IOException {
		loger.debug("into method devAppListManager: 管理员查询应用列表");
		manageUser = (SysUserVo) session.getAttribute("manageUser");
		try {
			apiFinanceTaskService.doTask(date);
		} catch (Exception e) {
			loger.error(e.toString());
		}

		ResultVo vo = new ResultSuccessVo();
		OutputHelper.outPut(response, JacksonMapper.objectToJsonString(vo));
	}
	
	@RequestMapping("report/devEffect.do")
	public void devEffect(HttpSession session, HttpServletResponse response) throws IOException {
		loger.debug("into method devAppListManager: 管理员查询应用列表");
		manageUser = (SysUserVo) session.getAttribute("manageUser");
		try {
			DevIncomeUtils.query();
		} catch (Exception e) {
			loger.error(e.toString());
		}
		ResultVo vo = new ResultSuccessVo();
		OutputHelper.outPut(response, JacksonMapper.objectToJsonString(vo));
	}

}
