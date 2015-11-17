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
import cn.adwalker.ad.admin.operation.bean.ConfirmNumCpdbean;
import cn.adwalker.ad.admin.operation.service.IConfirmNumberChannelService;
import cn.adwalker.ad.admin.operation.vo.ConfirmCpdSumVo;
import cn.adwalker.ad.admin.operation.vo.ConfirmNumberCpdVo;

/**
 * <p>
 * Title: ConfirmNumberCpdController
 * </p>
 * <p>
 * Description:pcd渠道确认数录入
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-8-6
 */
@Controller
public class ConfirmNumberCpdController extends AbstractControllerParent {

	/** 日志记录器 */
	Logger logger = Logger.getLogger(ConfirmNumberCpdController.class);

	/** 广告业务 */
	@Resource
	private IConfirmNumberChannelService confirmNumberChannelService;

	/**
	 * <p>
	 * Title: listadvConfirmationNumber
	 * </p>
	 * <p>
	 * Description:渠道确认数录入List
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @param bean
	 * @return
	 * @author lichuang
	 * @date 2013-5-23
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!operationChannelCpdList.do")
	public String ChannelCpdList(HttpServletRequest request,
			HttpServletResponse response, ConfirmNumCpdbean bean) {
		List<ConfirmNumberCpdVo> list = null;
		ConfirmCpdSumVo sum = null;
		try {
			IPageInfo pageInfo = new SetPage(request);
			list = this.confirmNumberChannelService.findByCpdList(bean,
					pageInfo);
			// 求和
			sum = confirmNumberChannelService.findSum(bean);

			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("bean", bean);
			request.setAttribute("list", list);
			request.setAttribute("sum", sum);
		} catch (Exception e) {
			logger.debug("获取广告单价列表异常！");
			e.printStackTrace();
		}
		return "operation/confirm_number_cpd_list";
	}

	/**
	 * 
	 * <p>
	 * Title: updateCpdData
	 * </p>
	 * <p>
	 * Description:录入cpd的渠道返回的数据
	 * </p>
	 * 
	 * @param request
	 * @param Response
	 * @param id
	 * @param amount
	 * @return
	 * @throws IOException
	 * @author cuidd
	 * @date 2013-10-18
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!updateCpdData.do")
	public String updateCpdData(HttpServletRequest request,
			HttpServletResponse Response, Long id, Double money,Integer num)
			throws IOException {
		ResultVo vo = null;
		try {
			vo = new ResultSuccessVo();
			this.confirmNumberChannelService.updateCpdData(id, money,num);
		} catch (Exception e) {
			e.printStackTrace();
			vo = new ResultErrorVo("系统错误");

		}
		OutputHelper.outPut(Response, JacksonMapper.objectToJsonString(vo));
		return null;
	}

	@RequestMapping("/manage!confirmationCpd.do")
	public String confirmationCpd(HttpServletRequest request,
			HttpServletResponse Response, Long id) throws IOException {
		ResultVo vo = null;
		try {
			vo = new ResultSuccessVo();
			this.confirmNumberChannelService.confirmationCpd(id);
		} catch (Exception e) {
			e.printStackTrace();
			vo = new ResultErrorVo("系统错误");

		}
		OutputHelper.outPut(Response, JacksonMapper.objectToJsonString(vo));
		return null;
	}
}
