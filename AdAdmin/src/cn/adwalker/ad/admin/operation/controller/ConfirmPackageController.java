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
import cn.adwalker.ad.admin.operation.bean.ConfirmPackagebean;
import cn.adwalker.ad.admin.operation.service.IConfirmationPackageService;
import cn.adwalker.ad.admin.operation.vo.ConfirmationPackageVo;

/**
* <p>Title: ConfirmPackageController</p>
* <p>Description:TODO</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-7-16
 */

@Controller
public class ConfirmPackageController extends AbstractControllerParent {

	/** 日志记录器 */
	Logger logger = Logger.getLogger(ConfirmPackageController.class);

	/** 广告业务 */
	@Resource
	private IConfirmationPackageService confirmationPackageService;

	/**
	* <p>Title: listadvConfirmationNumber</p>
	* <p>Description:渠道包备注添加List</p>
	* @param request
	* @param response
	* @param bean
	* @return
	* @author lichuang
	* @date 2013-7-16
	* @return String
	* @version 1.0
	 */
	@RequestMapping("/manage!operationPackageList.do")
	public String listadvConfirmationNumber(HttpServletRequest request,
			HttpServletResponse response, ConfirmPackagebean bean) {
		List<ConfirmationPackageVo> list = null;
		try {
			IPageInfo pageInfo = new SetPage(request);
			list = this.confirmationPackageService.findList(bean, pageInfo);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("bean", bean);
			request.setAttribute("list", list);
			request.setAttribute("page", pageInfo.getPageSize());
		} catch (Exception e) {
			logger.debug("获取广告单价列表异常！");
			e.printStackTrace();
		}
		return "operation/confirm_package_list";
	}

	/**
	* <p>Title: confirmationPackageremarks</p>
	* <p>Description:修改备注</p>
	* @param request
	* @param Response
	* @param id
	* @param Packageremarks
	* @return
	* @throws IOException
	* @author lichuang
	* @date 2013-7-16
	* @return String
	* @version 1.0
	 */
	@RequestMapping("/manage!confirmationPackageremarks.do")
	public String confirmationPackageremarks(HttpServletRequest request,
			HttpServletResponse Response, Long id, String Packageremarks)
			throws IOException {
		ResultVo vo = null;
		try {
			vo = new ResultSuccessVo();
			this.confirmationPackageService.packageremarks(id, Packageremarks);
		} catch (Exception e) {
			e.printStackTrace();
			vo = new ResultErrorVo("系统错误");

		}
		OutputHelper.outPut(Response, JacksonMapper.objectToJsonString(vo));
		return null;
	}
	
}
