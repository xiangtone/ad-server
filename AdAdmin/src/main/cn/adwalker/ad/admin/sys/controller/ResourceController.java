package cn.adwalker.ad.admin.sys.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.adwalker.ad.admin.common.controller.AbstractControllerParent;
import cn.adwalker.ad.admin.common.vo.SysUserVo;
import cn.adwalker.ad.admin.sys.bean.ResourceSearchBean;
import cn.adwalker.ad.admin.sys.form.ResourceForm;
import cn.adwalker.ad.admin.sys.service.IResourceService;
import cn.adwalker.ad.admin.sys.vo.ResourceListVo;
import cn.adwalker.ad.config.AppConstant;
import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.page.SetPage;
import cn.adwalker.core.util.JacksonMapper;
import cn.adwalker.core.util.OutputHelper;
import cn.adwalker.core.vo.ResultSuccessVo;
import cn.adwalker.core.vo.ResultVo;
import cn.adwalker.model.sys.domain.SysResource;

/**
 * 资源管理
 * <p>
 * Title: ResourceController
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-3-15
 */
@Controller
public class ResourceController extends AbstractControllerParent {

	/** 日志记录器 */
	Logger logger = Logger.getLogger(ResourceController.class);

	/** 資源管理 */
	@Resource
	private IResourceService resourceService;

	/**
	 * 
	 * <p>
	 * Title: listResourceManage
	 * </p>
	 * <p>
	 * Description:资源List
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @param bean
	 * @return
	 * @author lichuang
	 * @date 2013-3-15
	 * @return String
	 * @version 1.0
	 * @throws
	 */
	@RequestMapping("manage!resourceList.do")
	public String resourceList(HttpServletRequest request,
			HttpServletResponse response, ResourceSearchBean bean) {
		List<ResourceListVo> list = null;
		IPageInfo pageInfo = new SetPage(request);
		try {
			list = (List<ResourceListVo>) this.resourceService.findByPage(bean,
					pageInfo);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("list", list);
			request.setAttribute("bean", bean);
			request.setAttribute("pageRecord", pageInfo.getTotalRow());
			request.setAttribute("page", pageInfo.getPageSize());
		} catch (Exception e) {
			logger.error("获取广告效果列表异常！");
			e.printStackTrace();
		}
		return "sys/resource/res_list";
	}

	/**
	 * 
	 * <p>
	 * Title: accountClosed
	 * </p>
	 * <p>
	 * Description:删除资源（页面删除）
	 * </p>
	 * 
	 * @param session
	 * @param Id
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-3-15
	 * @return String
	 * @version 1.0
	 * @throws
	 */
	@RequestMapping("manage!delResource.do")
	public void delResource(HttpSession session, Long id,
			Map<String, Object> model, HttpServletRequest request)
			throws Exception {
		manageUser = (SysUserVo) session.getAttribute("manageUser");
		logger.debug("into method accountClosed: 管理员删除该项【" + id + "】列表");
		try {
			resourceService.updateStatus(id, AppConstant.RESOURCE_CLOSED);// 账户停用

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * <p>
	 * Title: addResourceManage
	 * </p>
	 * <p>
	 * Description:添加资源跳转页面
	 * </p>
	 * 
	 * @param session
	 * @param model
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-3-15
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("manage!addResource.do")
	public String addResource(HttpSession session, Map<String, Object> model)
			throws Exception {
		manageUser = (SysUserVo) session.getAttribute("manageUser");
		return "sys/resource/res_add";
	}

	/**
	 * <p>
	 * Title: updateResourceManage
	 * </p>
	 * <p>
	 * Description:管理员修改资源
	 * </p>
	 * 
	 * @param session
	 * @param model
	 * @param Id
	 * @param request
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-3-15
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("manage!editResource.do")
	public String eidtResource(HttpSession session, Map<String, Object> model,
			Long id, HttpServletRequest request) throws Exception {
		manageUser = (SysUserVo) session.getAttribute("manageUser");
		try {

			SysResource resourcevo = resourceService.getById(id);
			model.put("entity", resourcevo);
			model.put("id", id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "sys/resource/res_edit";
	}

	/**
	 * <p>
	 * Title: addResource
	 * </p>
	 * <p>
	 * Description:管理员添加保存资源
	 * </p>
	 * 
	 * @param session
	 * @param Id
	 * @param bean
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-3-15
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("manage!saveResource.do")
	public String saveResource(HttpSession session, ResourceForm form,
			HttpServletResponse response) throws Exception {
		manageUser = (SysUserVo) session.getAttribute("manageUser");
		ResultVo vo = null;
		resourceService.insertResource(form, manageUser);

		vo = new ResultSuccessVo();
		OutputHelper.outPut(response, JacksonMapper.objectToJsonString(vo));
		return null;
	}

	/**
	 * <p>
	 * Title: updateResource
	 * </p>
	 * <p>
	 * Description:保存修改资源
	 * </p>
	 * 
	 * @param session
	 * @param Id
	 * @param bean
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-3-17
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("manage!updateResource.do")
	public String updateResource(HttpSession session, ResourceForm form,
			Long id, Map<String, Object> model, HttpServletResponse response)
			throws Exception {
		manageUser = (SysUserVo) session.getAttribute("manageUser");
		ResultVo vo = null;
		resourceService.updateResource(id, form, manageUser);
		vo = new ResultSuccessVo();
		OutputHelper.outPut(response, JacksonMapper.objectToJsonString(vo));
		return null;
	}

}