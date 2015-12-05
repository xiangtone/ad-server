package cn.adwalker.ad.admin.operation.controller;

import java.io.IOException;
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
import cn.adwalker.core.vo.ResultErrorVo;
import cn.adwalker.core.vo.ResultSuccessVo;
import cn.adwalker.core.vo.ResultVo;
import cn.adwalker.ad.admin.common.controller.AbstractControllerParent;
import cn.adwalker.ad.admin.common.vo.SysUserVo;
import cn.adwalker.ad.admin.operation.bean.ConfirmIosNumberbean;
import cn.adwalker.ad.admin.operation.service.IConfirmationIosNumberService;
import cn.adwalker.ad.admin.operation.vo.ConfirmationIosNumberVo;
import cn.adwalker.ad.admin.operation.vo.FractionIosNumberVo;

/**
 * <p>
 * Title: AdEffectIosNumberController
 * </p>
 * <p>
 * Description:IOS确认数录入
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-11-8
 */

@Controller
public class AdEffectIosNumberController extends AbstractControllerParent {

	/** 日志记录器 */
	Logger logger = Logger.getLogger(AdEffectIosNumberController.class);

	/** 广告业务 */
	@Resource
	private IConfirmationIosNumberService confirmationIosNumberService;

	/**
	 * <p>
	 * Title: IosNumList
	 * </p>
	 * <p>
	 * Description:IOS确认数录入List
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @param bean
	 * @return
	 * @author lichuang
	 * @date 2013-11-8
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!ConfirmationIosNumList.do")
	public String IosNumList(HttpServletRequest request,
			HttpServletResponse response, ConfirmIosNumberbean bean) {
		List<ConfirmationIosNumberVo> list = null;
		try {
			IPageInfo pageInfo = new SetPage(request);
			list = this.confirmationIosNumberService.findList(bean, pageInfo);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("bean", bean);
			request.setAttribute("list", list);
		} catch (Exception e) {
			logger.debug("获取广告单价列表异常！");
			e.printStackTrace();
		}
		return "operation/confirm_ios_number_list";
	}

	/**
	 * <p>
	 * Title: confIosNum
	 * </p>
	 * <p>
	 * Description:IOS日确认数录入
	 * </p>
	 * 
	 * @param request
	 * @param Response
	 * @param id
	 * @param number
	 * @return
	 * @throws IOException
	 * @author lichuang
	 * @date 2013-11-8
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!confIosNum.do")
	public String confIosNum(HttpServletRequest request,
			HttpServletResponse Response, Long id, Integer number)
			throws IOException {
		ResultVo vo = null;
		try {
			vo = new ResultSuccessVo();
			this.confirmationIosNumberService.confIosNum(id, number);
		} catch (Exception e) {
			e.printStackTrace();
			vo = new ResultErrorVo("系统错误");

		}
		OutputHelper.outPut(Response, JacksonMapper.objectToJsonString(vo));
		return null;
	}

	/**
	 * <p>
	 * Title: restConfIosNum
	 * </p>
	 * <p>
	 * Description:IOS日确认数重置
	 * </p>
	 * 
	 * @param request
	 * @param Response
	 * @param id
	 * @return
	 * @throws IOException
	 * @author lichuang
	 * @date 2013-11-8
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!restConfIosNum.do")
	public String restConfIosNum(HttpServletRequest request,
			HttpServletResponse Response, Long id) throws IOException {
		ResultVo vo = null;
		try {
			vo = new ResultSuccessVo();
			this.confirmationIosNumberService.restConfIosNum(id);
		} catch (Exception e) {
			e.printStackTrace();
			vo = new ResultErrorVo("系统错误");

		}
		OutputHelper.outPut(Response, JacksonMapper.objectToJsonString(vo));
		return null;
	}

	/**
	 * <p>
	 * Title: delConfIosNum
	 * </p>
	 * <p>
	 * Description:IOS日确认数删除
	 * </p>
	 * 
	 * @param request
	 * @param Response
	 * @param id
	 * @return
	 * @throws IOException
	 * @author lichuang
	 * @date 2013-11-8
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!delConfIosNum.do")
	public String delConfIosNum(HttpServletRequest request,
			HttpServletResponse Response, Long id) throws IOException {
		ResultVo vo = null;
		try {
			vo = new ResultSuccessVo();
			this.confirmationIosNumberService.delConfIosNum(id);
		} catch (Exception e) {
			e.printStackTrace();
			vo = new ResultErrorVo("系统错误");

		}
		OutputHelper.outPut(Response, JacksonMapper.objectToJsonString(vo));
		return null;
	}

	/**
	 * <p>
	 * Title: submitConfNumIos
	 * </p>
	 * <p>
	 * Description:提交IOS日确认数
	 * </p>
	 * 
	 * @param request
	 * @param Response
	 * @param ids
	 * @return
	 * @throws IOException
	 * @author lichuang
	 * @date 2013-11-8
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!submitConfNumIos.do")
	public String submitConfNumIos(HttpServletRequest request,HttpSession session,
			HttpServletResponse Response, String ids) throws IOException {
		
		manageUser = (SysUserVo) session.getAttribute("manageUser");
		ResultVo vo = null;
		try {
			vo = new ResultSuccessVo();
			this.confirmationIosNumberService.submitConfNumIos(ids,manageUser);
		} catch (Exception e) {
			e.printStackTrace();
			vo = new ResultErrorVo("系统错误");

		}
		OutputHelper.outPut(Response, JacksonMapper.objectToJsonString(vo));
		return null;
	}

	/**
	 * <p>
	 * Title: fractionIos
	 * </p>
	 * <p>
	 * Description:IOS日确认数分数
	 * </p>
	 * 
	 * @param request
	 * @param income_amount
	 * @param id
	 * @param price
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-11-8
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!fractionIos.do")
	public String fractionIos(HttpServletRequest request,
			Integer income_amount, Long id) throws Exception {
		List<FractionIosNumberVo> list = null;
		int toatal = 0;
		try {
			list = this.confirmationIosNumberService.fractionIos(id);
			if (list != null && list.size() > 0) {

				for (FractionIosNumberVo vo : list) {
					if (vo.getConfirm_num() != null) {
						toatal = toatal + vo.getConfirm_num();
					}

				}

			}
			request.setAttribute("list", list);
			request.setAttribute("id", id);
			request.setAttribute("income_amount", income_amount - toatal);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "operation/channel_ios_Fraction_list";
	}

	/**
	 * <p>
	 * Title: saveConfIos
	 * </p>
	 * <p>
	 * Description:保存IOS日确认数分数
	 * </p>
	 * 
	 * @param request
	 * @param Response
	 * @param id
	 * @param confirm_num
	 * @param fraction_id
	 * @param price
	 * @return
	 * @throws IOException
	 * @author lichuang
	 * @date 2013-11-8
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!saveConfIos.do")
	public String saveConfIos(HttpServletRequest request,
			HttpServletResponse Response, Long[] id, Integer[] confirm_num,
			Integer[] comfirm_cost, Long fraction_id) throws IOException {
		ResultVo vo = null;
		try {
			vo = new ResultSuccessVo();
			this.confirmationIosNumberService.saveConfIos(id, confirm_num,comfirm_cost,
					fraction_id);
		} catch (Exception e) {
			e.printStackTrace();
			vo = new ResultErrorVo("系统错误");

		}
		OutputHelper.outPut(Response, JacksonMapper.objectToJsonString(vo));
		return null;
	}
}
