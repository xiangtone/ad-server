/**
 * <p>Title: AdEffectIosController.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author kingdom
 * @date 2013-9-10
 * @version 1.0
 */
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
import cn.adwalker.ad.admin.operation.bean.AdEffectIosImpbean;
import cn.adwalker.ad.admin.operation.service.IAdEffectIosImpService;
import cn.adwalker.ad.admin.operation.vo.AdEffectIosImpVo;

/**
 * <p>
 * Title: AdEffectIosController
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-9-10
 */
@Controller
public class AdEffectIosImpController {
	/** 日志记录器 */
	Logger logger = Logger.getLogger(AdEffectAndroidController.class);
	@Resource
	private IAdEffectIosImpService adEffectIosImpService;

	/**
	 * 
	 * <p>
	 * Title: listadvConfirmationNumber
	 * </p>
	 * <p>
	 * Description:列表
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @param bean
	 * @return
	 * @author cuidd
	 * @date 2013-9-13
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!adEffectIosImp.do")
	public String list(HttpServletRequest request,
			HttpServletResponse response, AdEffectIosImpbean bean) {
		List<AdEffectIosImpVo> list = null;
		try {
			IPageInfo pageInfo = new SetPage(request);
			list = this.adEffectIosImpService.findByPage(bean, pageInfo);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("bean", bean);
			request.setAttribute("list", list);
		} catch (Exception e) {
			logger.debug("获取广告单价列表异常！");
			e.printStackTrace();
		}
		return "operation/effect/confirm_number_imp_list";
	}

	@RequestMapping("/summitIosForecastIncomeCost.do")
	public String submit(HttpServletRequest request,
			HttpServletResponse Response, Long id) throws IOException {
		ResultVo vo = null;
		try {
			vo = new ResultSuccessVo();
			this.adEffectIosImpService.submit(id);
		} catch (Exception e) {
			e.printStackTrace();
			vo = new ResultErrorVo("系统错误");

		}
		OutputHelper.outPut(Response, JacksonMapper.objectToJsonString(vo));
		return null;
	}

}
