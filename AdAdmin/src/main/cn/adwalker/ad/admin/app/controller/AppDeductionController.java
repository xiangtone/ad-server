package cn.adwalker.ad.admin.app.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.page.SetPage;
import cn.adwalker.core.util.JacksonMapper;
import cn.adwalker.core.util.OutputHelper;
import cn.adwalker.core.vo.ResultSuccessVo;
import cn.adwalker.core.vo.ResultVo;
import cn.adwalker.ad.admin.app.service.IAppDeductionService;
import cn.adwalker.ad.admin.common.controller.AbstractControllerParent;
import cn.adwalker.ad.admin.common.vo.SysUserVo;
import cn.adwalker.ad.admin.operation.bean.AppDeductionBean;
import cn.adwalker.ad.admin.operation.vo.AppDeductionVo;

/**
 * <p>
 * Title: AppDeductionController
 * </p>
 * <p>
 * Description:媒体数量调控
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author zouguibao
 * @date 2013-5-16
 */
@Controller
public class AppDeductionController extends AbstractControllerParent {

	/** 日志记录器 */
	Logger logger = Logger.getLogger(AppDeductionController.class);

	/** 媒体数量调控相关业务 */
	@Resource
	private IAppDeductionService appDeductionService;

	/**
	 * <p>
	 * Title: appDeductionUp
	 * </p>
	 * <p>
	 * Description: 媒体数量调控设置List
	 * </p>
	 * 
	 * @param session
	 * @param request
	 * @param bean
	 * @return
	 * @author lichuang
	 * @date 2013-7-9
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("manage!appDeductionUp.do")
	public String appDeductionUp(HttpSession session,
			HttpServletRequest request, AppDeductionBean bean) {
		try {
			IPageInfo pageInfo = new SetPage(request);
			
			SysUserVo user = (SysUserVo) session.getAttribute("manageUser");
			List<AppDeductionVo> list = this.appDeductionService.findByPage(
					bean, user,pageInfo);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("vo", bean);
			request.setAttribute("List", list);
		} catch (Exception e) {
			logger.debug("媒体数量调控设置异常！");
			e.printStackTrace();
		}
		return "operation/media/app_duction";

	}

	/**
	 * <p>
	 * Title: modifyAppDeduction
	 * </p>
	 * <p>
	 * Description:修改 媒体数量比例
	 * </p>
	 * 
	 * @param session
	 * @param id
	 * @param rate
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-3-20
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("manage!deductionScaleAppUp.do")
	public String modifyAppDeduction(Long id, Double rate,
			HttpServletResponse response) {
		try {
			this.appDeductionService.updateAppDeductionRate(id, rate);
			ResultVo vo = new ResultSuccessVo();
			OutputHelper.outPut(response, JacksonMapper.objectToJsonString(vo));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
