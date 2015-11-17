package cn.adwalker.ad.admin.operation.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.adwalker.ad.admin.common.controller.AbstractControllerParent;
import cn.adwalker.ad.admin.operation.bean.ConfirmNumberbean;
import cn.adwalker.ad.admin.operation.service.IConfirmationNumberService;
import cn.adwalker.ad.admin.operation.vo.ConfirmationNumberVo;
import cn.adwalker.ad.admin.operation.vo.FractionNumberVo;
import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.page.SetPage;
import cn.adwalker.core.util.DateUtil;
import cn.adwalker.core.util.ExportUtils;
import cn.adwalker.core.util.JacksonMapper;
import cn.adwalker.core.util.OutputHelper;
import cn.adwalker.core.util.lang.ObjectUtils;
import cn.adwalker.core.vo.ResultErrorVo;
import cn.adwalker.core.vo.ResultSuccessVo;
import cn.adwalker.core.vo.ResultVo;

/**
 * <p>
 * Title: AdEffectAndroidController
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-5-23
 */

@Controller
public class AdEffectAndroidController extends AbstractControllerParent {

	/** 日志记录器 */
	Logger logger = Logger.getLogger(AdEffectAndroidController.class);

	/** 广告业务 */
	@Resource
	private IConfirmationNumberService confirmationNumberService;

	/**
	 * <p>
	 * Title: listadvConfirmationNumber
	 * </p>
	 * <p>
	 * Description:广告主确认数录入List
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
	@RequestMapping("/manage!advConfirmationNumber.do")
	public String list(HttpServletRequest request,
			HttpServletResponse response, ConfirmNumberbean bean) {
		List<ConfirmationNumberVo> list = null;
		try {
			IPageInfo pageInfo = new SetPage(request);
			if (bean == null) {
				bean = new ConfirmNumberbean();
			}
			if (StringUtils.isEmpty(bean.getStart_time())
					&& StringUtils.isEmpty(bean.getEnd_time())) {
				String default_date_end = DateUtil.formatDate(DateUtil.addDays(
						new Date(), -1));
				String default_date_start = DateUtil.formatDate(DateUtil
						.addDays(new Date(), -6));
				bean.setStart_time(default_date_start);
				bean.setEnd_time(default_date_end);

			}
			list = this.confirmationNumberService.findList(bean, pageInfo);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("bean", bean);
			request.setAttribute("list", list);
		} catch (Exception e) {
			logger.debug("获取广告单价列表异常！");
			e.printStackTrace();
		}
		return "operation/confirm_number_list";
	}

	@RequestMapping("/exportadvConfirmationNumberCSV.do")
	public void exportCSV(HttpSession session, HttpServletResponse response,
			ConfirmNumberbean bean) throws Exception {
		String fileName = "按天统计----广告统计报表.xls";
		String workbookName = "广告按天统计";
		String title[] = { "日期", "广告主ID", "广告ID", "广告名称", "广告样式", "平台类型",
				"广告展示", "广告点击", "广告下载", "广告激活", "	费用支出(元)", "点击转化率", "下载转化率",
				"激活转化率" };
		List<Object> list = confirmationNumberService.findAll(bean);
		String clumes[] = { "static_date", "adv_id", "id", "placement_name",
				"fname", "os", "adpv", "click", "download", "activate", "cost",
				"ctrc", "ctrd", "ctra" };
		ExportUtils.exportXlsFile(response, title, null, list, clumes,
				fileName, workbookName);
	}

	/**
	 * <p>
	 * Title: confirmationNumber
	 * </p>
	 * <p>
	 * Description:广告主确认数录入
	 * </p>
	 * 
	 * @param request
	 * @param Id
	 * @param number
	 * @return
	 * @author lichuang
	 * @date 2013-5-23
	 * @return String
	 * @version 1.0
	 * @throws IOException
	 */
	@RequestMapping("/manage!confirmationNumber.do")
	public String confirmationNumber(HttpServletRequest request,
			HttpServletResponse Response, Long id, Integer number)
			throws IOException {
		ResultVo vo = null;
		try {
			vo = new ResultSuccessVo();
			this.confirmationNumberService.confirmationNumber(id, number);
		} catch (Exception e) {
			e.printStackTrace();
			vo = new ResultErrorVo("系统错误");

		}
		OutputHelper.outPut(Response, JacksonMapper.objectToJsonString(vo));
		return null;
	}

	/**
	 * 
	 * <p>
	 * Title: restConfirmationNumber
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param request
	 * @param Response
	 * @param id
	 * @return
	 * @throws IOException
	 * @author cuidd
	 * @date 2013-8-13
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!restConfirmationNumber.do")
	public String restConfirmationNumber(HttpServletRequest request,
			HttpServletResponse Response, Long id) throws IOException {
		ResultVo vo = null;
		try {
			vo = new ResultSuccessVo();
			this.confirmationNumberService.restConfirmationNumber(id);
		} catch (Exception e) {
			e.printStackTrace();
			vo = new ResultErrorVo("系统错误");

		}
		OutputHelper.outPut(Response, JacksonMapper.objectToJsonString(vo));
		return null;
	}

	@RequestMapping("/manage!delConfirmationNumber.do")
	public String delConfirmationNumber(HttpServletRequest request,
			HttpServletResponse Response, Long id) throws IOException {
		ResultVo vo = null;
		try {
			vo = new ResultSuccessVo();
			this.confirmationNumberService.delConfirmationNumber(id);
		} catch (Exception e) {
			e.printStackTrace();
			vo = new ResultErrorVo("系统错误");

		}
		OutputHelper.outPut(Response, JacksonMapper.objectToJsonString(vo));
		return null;
	}

	/**
	 * 
	 * <p>
	 * Title: submitNumber
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param request
	 * @param Response
	 * @param ids
	 * @return
	 * @throws IOException
	 * @author lichuang
	 * @date 2013-7-18
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!submitConfirmNumber.do")
	public String submitNumber(HttpServletRequest request,
			HttpServletResponse Response, String ids) throws IOException {
		ResultVo vo = null;
		try {
			vo = new ResultSuccessVo();
			this.confirmationNumberService.submitConfirmNumber(ids);
		} catch (Exception e) {
			e.printStackTrace();
			vo = new ResultErrorVo("系统错误");

		}
		OutputHelper.outPut(Response, JacksonMapper.objectToJsonString(vo));
		return null;
	}

	/**
	 * <p>
	 * Title: addAdv
	 * </p>
	 * <p>
	 * Description:渠道分数
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
	@RequestMapping("/manage!channelFraction.do")
	public String channelFraction(HttpServletRequest request,
			Integer income_amount, long id, double price) throws Exception {
		List<FractionNumberVo> list = null;
		int toatal = 0;
		if (ObjectUtils.isEmpty(income_amount)) {
			income_amount = 0;
		}
		try {
			list = this.confirmationNumberService.findFractionList(id);
			if (list != null && list.size() > 0) {

				for (FractionNumberVo vo : list) {
					if (vo.getConfirm_num() != null) {
						toatal = toatal + vo.getConfirm_num();
					}
				}
			}
			request.setAttribute("list", list);
			request.setAttribute("id", id);
			request.setAttribute("income_amount", income_amount - toatal);
			request.setAttribute("price", price);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "operation/channel_Fraction_list";
	}

	/**
	 * <p>
	 * Title: saveConfirm
	 * </p>
	 * <p>
	 * Description:保存分数
	 * </p>
	 * 
	 * @param request
	 * @param Response
	 * @param id
	 * @param confirm_num
	 * @param fraction_id
	 * @return
	 * @throws IOException
	 * @author lichuang
	 * @date 2013-5-28
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!saveConfirm.do")
	public String saveConfirm(HttpServletRequest request,
			HttpServletResponse Response, Long[] id, Integer[] confirm_num,
			Long fraction_id, double price) throws IOException {
		ResultVo vo = null;
		try {
			vo = new ResultSuccessVo();
			this.confirmationNumberService.saveConfirm(id, confirm_num,
					fraction_id);
		} catch (Exception e) {
			e.printStackTrace();
			vo = new ResultErrorVo("系统错误");

		}
		OutputHelper.outPut(Response, JacksonMapper.objectToJsonString(vo));
		return null;
	}
}
