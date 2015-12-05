package cn.adwalker.ad.admin.operation.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import cn.adwalker.ad.admin.operation.bean.InterfaceIosBean;
import cn.adwalker.ad.admin.operation.form.CollocationIosForm;
import cn.adwalker.ad.admin.operation.service.IOperationInterfaceIosService;
import cn.adwalker.ad.admin.operation.vo.InterfaceChannelVo;
import cn.adwalker.ad.admin.operation.vo.InterfaceIosVo;

/**
 * <p>
 * Title: OperationInterfaceIosController
 * </p>
 * <p>
 * Description:iOS接口配置页面
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-6-5
 */
@Controller
public class OperationInterfaceIosController extends AbstractControllerParent {

	/** 日志记录器 */
	Logger logger = Logger.getLogger(OperationInterfaceIosController.class);

	/** Ios业务 */
	@Resource
	private IOperationInterfaceIosService operationIosService;

	/**
	 * <p>
	 * Title: interfaceIosList
	 * </p>
	 * <p>
	 * Description:IOS活动列表
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @param bean
	 * @return
	 * @author lichuang
	 * @date 2013-6-5
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!interfaceIosList.do")
	public String interfaceIosList(HttpServletRequest request,
			HttpServletResponse response, InterfaceIosBean bean) {
		List<InterfaceIosVo> list = null;
		try {
			IPageInfo pageInfo = new SetPage(request);
			list = this.operationIosService.findList(bean, pageInfo);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("bean", bean);
			request.setAttribute("list", list);
			request.setAttribute("pageRecord", pageInfo.getTotalRow());
			request.setAttribute("page", pageInfo.getPageSize());
		} catch (Exception e) {
			logger.debug("获取广告单价列表异常！");
			e.printStackTrace();
		}
		return "operation/config-ios/interface_ois_list";
	}

	/**
	 * <p>
	 * Title: collocationIos
	 * </p>
	 * <p>
	 * Description:ios配置
	 * </p>
	 * 
	 * @param channelId
	 * @param score
	 * @param request
	 * @return
	 * @author lichuang
	 * @date 2013-6-5
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!collocationIos.do")
	public String collocationIos(HttpServletRequest request,
			CollocationIosForm form, HttpServletResponse response)
			throws IOException {

		ResultVo result = null;
		try {
			this.operationIosService.updatecollocationIos(form);
			result = new ResultSuccessVo();
		} catch (Exception e) {
			e.printStackTrace();
			result = new ResultErrorVo("系统异常！！");
		}
		OutputHelper.outPut(response, JacksonMapper.objectToJsonString(result));
		return null;
	}

	/**
	 * <p>
	 * Title: addCollocationIos
	 * </p>
	 * <p>
	 * Description:添加或修改渠道配置
	 * </p>
	 * 
	 * @param id
	 * @param request
	 * @return
	 * @author lichuang
	 * @date 2013-6-6
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!addCollocationIos.do")
	public String addCollocationIos(HttpServletRequest request,
			Long placement_id) {
//		CollocationIosVo iosVo = null;
		try {
//			iosVo = this.operationIosService.getIos(placement_id);
			String configid=this.operationIosService.getConfigByPlacmentId(placement_id);
			request.setAttribute("configid", configid);
			request.setAttribute("placement_id", placement_id);
		} catch (Exception e) {
			logger.debug("获取广告效果列表异常！");
			e.printStackTrace();
		}
		return "operation/config-ios/interface_ios_add";
	}

	/**
	 * <p>
	 * Title: interfaceIosList
	 * </p>
	 * <p>
	 * Description:IOS活动列表
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @param bean
	 * @return
	 * @author lichuang
	 * @date 2013-6-5
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!interfaceChannelList.do")
	public String interfaceChannelList(HttpServletRequest request,
			HttpServletResponse response) {
		List<InterfaceChannelVo> list = null;

		String channelName = request.getParameter("channelName") != null ? request
				.getParameter("channelName") : "";

		IPageInfo pageInfo = new SetPage(request);
		try {
			list = this.operationIosService.findChannelList(pageInfo);
		} catch (Exception e) {
		}
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("channelName", channelName);
		request.setAttribute("list", list);
		request.setAttribute("pageRecord", pageInfo.getTotalRow());
		request.setAttribute("page", pageInfo.getPageSize());

		return "operation/config-ios/interface_channel_list";
	}
	/**
	* <p>Title: addCollocationChannel</p>
	* <p>Description:TODO</p>
	* @param request
	* @param channel
	* @return
	* @author lichuang
	* @date 2013-9-18
	* @return String
	* @version 1.0
	 */
	@RequestMapping("/manage!addCollocationChannel.do")
	public String addCollocationChannel(HttpServletRequest request,
			String channel) {

		try {
			InterfaceChannelVo vo = operationIosService.getChannel(channel);
			request.setAttribute("vo", vo);
		} catch (Exception e) {
			logger.debug("获取广告效果列表异常！");
			e.printStackTrace(); 
		}
		return "operation/config-ios/interface_channel_edit";
	}
	/**
	* <p>Title: collocationChannel</p>
	* <p>Description:TODO</p>
	* @param request
	* @param form
	* @param response
	* @return
	* @throws IOException
	* @author lichuang
	* @date 2013-9-18
	* @return String
	* @version 1.0
	 */
	@RequestMapping("/manage!collocationChannel.do")
	public String collocationChannel(HttpServletRequest request,
			CollocationIosForm form, HttpServletResponse response)
			throws IOException {

		ResultVo result = null;
		try {
			this.operationIosService.updatecollocationIos(form);
			result = new ResultSuccessVo();
		} catch (Exception e) {
			e.printStackTrace();
			result = new ResultErrorVo("系统异常！！");
		}
		OutputHelper.outPut(response, JacksonMapper.objectToJsonString(result));
		return null;
	}
}
