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
import cn.adwalker.core.vo.ResultSuccessVo;
import cn.adwalker.core.vo.ResultVo;
import cn.adwalker.ad.admin.operation.bean.AdEffectIosbean;
import cn.adwalker.ad.admin.operation.form.AdIosEffect;
import cn.adwalker.ad.admin.operation.service.IAdEffectIosService;
import cn.adwalker.ad.admin.operation.vo.AdEffectIosVo;
import cn.adwalker.ad.admin.operation.vo.CampaignConfirmIos;
import cn.adwalker.ad.admin.operation.vo.CampaignConfirmIosEditVo;

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
public class AdEffectIosController {
	/** 日志记录器 */
	Logger logger = Logger.getLogger(AdEffectAndroidController.class);
	@Resource
	private IAdEffectIosService adEffectIosService;

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
	@RequestMapping("/manage!adEffectIos.do")
	public String listadvConfirmationNumber(HttpServletRequest request,
			HttpServletResponse response, AdEffectIosbean bean) {
		List<AdEffectIosVo> list = null;
		try {
			IPageInfo pageInfo = new SetPage(request);
			list = this.adEffectIosService.findList(bean, pageInfo);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("bean", bean);
			request.setAttribute("list", list);
		} catch (Exception e) {
			logger.debug("获取广告单价列表异常！");
			e.printStackTrace();
		}
		return "operation/effect/confirm_number_list";
	}

	/**
	 * <p>
	 * Title: addAdv
	 * </p>
	 * <p>
	 * Description:录数链接
	 * </p>
	 * 
	 * @param session
	 * @param model
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-5-24
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!addEffectIos.do")
	public String channelFraction(HttpServletRequest request) throws Exception {
		return "operation/effect/channel_Fraction_list";
	}

	/**
	 * 
	 * <p>
	 * Title: getAdByCampaign
	 * </p>
	 * <p>
	 * Description:初始化数据
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @param form
	 * @throws Exception
	 * @author cuidd
	 * @date 2013-9-13
	 * @return void
	 * @version 1.0
	 */
	@RequestMapping("/operation/getAdByCampaign.do")
	public void getAdByCampaign(HttpServletRequest request,
			HttpServletResponse response, AdIosEffect form) throws Exception {
		List<CampaignConfirmIos> list = null;
		try {
			list = adEffectIosService.initData(form);
			OutputHelper.outPut(response,
					JacksonMapper.objectToJsonString(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * <p>
	 * Title: addConfirmNum
	 * </p>
	 * <p>
	 * Description:分数
	 * </p>
	 * 
	 * @param response
	 * @param effect_id
	 * @param id
	 * @param price
	 * @param confirm_num
	 * @param res
	 * @throws Exception
	 * @author cuidd
	 * @date 2013-9-13
	 * @return void
	 * @version 1.0
	 */
	@RequestMapping("/operation/addConfirmNum.do")
	public void addConfirmNum(HttpServletResponse response, Long effect_id,
			Long[] id, Double[] price, Integer[] confirm_num,
			HttpServletResponse res) throws Exception {
		ResultVo vo = null;
		try {
			adEffectIosService.addConfirmNum(effect_id, id, price, confirm_num);
		} catch (Exception e) {
			e.printStackTrace();
		}
		vo = new ResultSuccessVo();
		OutputHelper.outPut(res, JacksonMapper.objectToJsonString(vo));
	}

	/**
	 * 
	 * <p>
	 * Title: editFraction
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 * @author cuidd
	 * @date 2013-9-13
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!editEffectIos.do")
	public String editFraction(HttpServletRequest request, Long id)
			throws Exception {
		List<CampaignConfirmIosEditVo> list = null;
		try {
			list = adEffectIosService.getEffectDetail(id);
			request.setAttribute("list", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "operation/effect/edit_effect";
	}

	@RequestMapping("/operation/submitEffect.do")
	public void submit(HttpServletResponse response, Long id) throws Exception {
		ResultVo vo = null;
		try {
			adEffectIosService.submitEffect(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		vo = new ResultSuccessVo();
		OutputHelper.outPut(response, JacksonMapper.objectToJsonString(vo));
	}

	@RequestMapping("/operation/deleteEffect.do")
	public void delete(HttpServletResponse response, Long id)
			throws Exception {
		ResultVo vo = null;
		try {
			adEffectIosService.deleteEffect(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		vo = new ResultSuccessVo();
		OutputHelper.outPut(response, JacksonMapper.objectToJsonString(vo));
	}

}
