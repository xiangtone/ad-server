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
import cn.adwalker.ad.admin.sys.bean.PermissionSearchBean;
import cn.adwalker.ad.admin.sys.form.PermissionForm;
import cn.adwalker.ad.admin.sys.service.IPermissionService;
import cn.adwalker.ad.admin.sys.vo.PermissionListVo;
import cn.adwalker.ad.config.AppConstant;
import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.page.SetPage;
import cn.adwalker.core.util.JacksonMapper;
import cn.adwalker.core.util.OutputHelper;
import cn.adwalker.core.vo.ResultSuccessVo;
import cn.adwalker.core.vo.ResultVo;
import cn.adwalker.model.sys.domain.Permission;

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
public class PermissionController extends AbstractControllerParent {

	/** 日志记录器 */
	Logger logger = Logger.getLogger(PermissionController.class);

	/** 資源管理 */
	@Resource
	private IPermissionService permissionService;

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
	@RequestMapping("manage!permissionList.do")
	public String list(HttpServletRequest request,
			HttpServletResponse response, PermissionSearchBean bean) {
		List<PermissionListVo> list = null;
		IPageInfo pageInfo = new SetPage(request);
		try {
			list = (List<PermissionListVo>) this.permissionService.findByPage(
					bean, pageInfo);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("list", list);
			request.setAttribute("bean", bean);
			request.setAttribute("page", pageInfo.getPageSize());
		} catch (Exception e) {
			logger.error("获取广告效果列表异常！");
			e.printStackTrace();
		}
		return "sys/permission/list";
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
	@RequestMapping("manage!permissionDel.do")
	public void del(HttpSession session, Long Id, Map<String, Object> model,
			HttpServletRequest request) throws Exception {
		manageUser = (SysUserVo) session.getAttribute("manageUser");
		logger.debug("into method accountClosed: 管理员删除该项【" + Id + "】列表");
		try {
			permissionService.updateStatus(Id, AppConstant.RESOURCE_CLOSED);// 账户停用

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
	@RequestMapping("manage!addPermission.do")
	public String add(HttpSession session, Map<String, Object> model)
			throws Exception {
		manageUser = (SysUserVo) session.getAttribute("manageUser");
		return "sys/permission/add";
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
	@RequestMapping("manage!editPermission.do")
	public String edit(HttpSession session, Map<String, Object> model, Long id,
			HttpServletRequest request) throws Exception {
		manageUser = (SysUserVo) session.getAttribute("manageUser");
		try {
			Permission entity = permissionService.getById(id);
			model.put("entity", entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "sys/permission/edit";
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
	@RequestMapping("manage!savePermission.do")
	public String save(HttpSession session, Long Id, PermissionForm form,
			Map<String, Object> model, HttpServletResponse response)
			throws Exception {
		manageUser = (SysUserVo) session.getAttribute("manageUser");
		ResultVo vo = null;
		permissionService.insert(form, manageUser);
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
	@RequestMapping("manage!updatePermission.do")
	public String update(HttpSession session, PermissionForm form,
			Map<String, Object> model, HttpServletResponse response, Long id)
			throws Exception {
		manageUser = (SysUserVo) session.getAttribute("manageUser");
		ResultVo vo = null;
		permissionService.update(id, form, manageUser);

		vo = new ResultSuccessVo();
		OutputHelper.outPut(response, JacksonMapper.objectToJsonString(vo));
		return null;
	}

}