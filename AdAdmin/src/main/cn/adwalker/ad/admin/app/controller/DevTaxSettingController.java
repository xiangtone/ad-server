package cn.adwalker.ad.admin.app.controller;

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
import cn.adwalker.ad.admin.app.bean.DevTaxSettting;
import cn.adwalker.ad.admin.app.service.IDevTaxSettingService;
import cn.adwalker.ad.admin.common.controller.AbstractControllerParent;
import cn.adwalker.ad.admin.operation.controller.OperationConfirmIncomeController;
import cn.adwalker.ad.admin.operation.vo.DevTaxSettingVo;

/**
 * <p>
 * Title: OperationDevTaxSettingcontroller
 * </p>
 * <p>
 * Description:确认收入
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author nemo
 * @date 2013-5-14
 */

@Controller
public class DevTaxSettingController extends AbstractControllerParent {

	/** 日志记录器 */
	Logger logger = Logger.getLogger(OperationConfirmIncomeController.class);

	/** 开发者免税审核 */
	@Resource
	private IDevTaxSettingService devTaxSettingService;

	/*
	 * <p>Title: devTaxList</p> <p>Description:免税审核List</p>
	 * 
	 * @param request
	 * 
	 * @param response
	 * 
	 * @param bean
	 * 
	 * @return
	 * 
	 * @author nemo
	 * 
	 * @date 2013-5-14
	 * 
	 * @return String
	 * 
	 * @version 1.0
	 */

	@RequestMapping("/manage!devTaxList.do")
	public String devTaxList(HttpServletRequest request,
			HttpServletResponse response, DevTaxSettting bean) {
		List<DevTaxSettingVo> list = null;
		IPageInfo pageInfo = new SetPage(request);
		try {
			list = this.devTaxSettingService.findBypage(bean, pageInfo);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("bean", bean);
			request.setAttribute("list", list);
			request.setAttribute("pageRecord", pageInfo.getTotalRow());
			request.setAttribute("page", pageInfo.getPageSize());

		} catch (Exception e) {
			logger.debug("获取开发者免税列表异常！");
			e.printStackTrace();
		}
		return "operation/media/dev_tax";
	}

	/**
	 * <p>
	 * Title: modifyDevTaxSetting
	 * </p>
	 * <p>
	 * Description:开发者免税审核
	 * </p>
	 * 
	 * @param session
	 * @param mediaId
	 * @param score
	 * @param model
	 * @param request
	 * @return String
	 * @date 2013-5-14
	 * @version 1.0
	 * @author nemo
	 */
	@RequestMapping("/manage!modifyDevTaxSetting.do")
	public String modifyDevTaxSetting(Long dev_id, Integer tax_status,
			HttpServletResponse response) {
		try {
			this.devTaxSettingService.updateDevTaxSetting(dev_id, tax_status);
			ResultVo vo = new ResultSuccessVo();
			OutputHelper.outPut(response, JacksonMapper.objectToJsonString(vo));
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
		}

		return null;
	}
}
