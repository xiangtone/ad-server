package cn.adwalker.ad.admin.sys.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import cn.adwalker.ad.admin.common.controller.AbstractControllerParent;
import cn.adwalker.ad.admin.sys.bean.PermissionSearchBean;
import cn.adwalker.ad.admin.sys.form.RoleAddForm;
import cn.adwalker.ad.admin.sys.service.ISysRolePurviewService;
import cn.adwalker.ad.admin.sys.service.ISysRoleService;
import cn.adwalker.ad.admin.sys.service.ISysUserService;
import cn.adwalker.ad.admin.sys.vo.PermissionByRoleVo;
import cn.adwalker.ad.admin.sys.vo.RoleVo;
import cn.adwalker.ad.config.AppConstant;
import cn.adwalker.core.context.Context;
import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.page.SetPage;
import cn.adwalker.core.util.JacksonMapper;
import cn.adwalker.core.util.MD5;
import cn.adwalker.core.util.OutputHelper;
import cn.adwalker.core.vo.ResultSuccessVo;
import cn.adwalker.core.vo.ResultVo;
import cn.adwalker.model.sys.domain.SysRole;
import cn.adwalker.model.sys.domain.SysUser;

/**
 * @author wjp 管理员权限管理
 */
@Controller
public class RoleController extends AbstractControllerParent {

	/** 日志记录器 */
	Logger logger = Logger.getLogger(RoleController.class);
	/** 管理员服务接口 */
	@Resource
	private ISysUserService sysUserService;

	/** 角色服务接口 */
	@Resource
	private ISysRoleService sysRoleService;

	/** 角色、权限映射关系服务接口 */
	@Resource
	private ISysRolePurviewService sysRolePurviewService;

	/**
	 * 跳转到角色管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manage!addRole.do")
	public String addRole(HttpServletRequest request,
			HttpServletResponse response) {
		return "sys/role/role_add";
	}

	/**
	 * <p>
	 * Title: roleList
	 * </p>
	 * <p>
	 * Description:角色管理List
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @author lichuang
	 * @date 2013-10-12
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!roleManage.do")
	public String roleList(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			IPageInfo pageInfo = new SetPage(request);
			List<RoleVo> list = this.sysRoleService.findByPage(pageInfo);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("list", list);
			return "sys/role/role_list";
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("获取角色列表异常");
		}
		return null;
	}

	/**
	 * <p>
	 * Title: listManageUserInRole
	 * </p>
	 * <p>
	 * Description:获取角色下用户的列表
	 * </p>
	 * 
	 * @param request
	 * @param model
	 * @param roleId
	 * @param roleName
	 * @return
	 * @author lichuang
	 * @date 2013-5-30
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!roleUserList.do")
	public String listManageUserInRole(HttpServletRequest request,
			Map<String, Object> model, Long roleId, String roleName) {

		try {
			List<SysUser> list = this.sysRoleService
					.getManageUserByRoleId(roleId);
			request.setAttribute("manageUserList", list);
			request.setAttribute("roleName", roleName);
			request.setAttribute("roleId", roleId);
			return "sys/role/role_user_list";
		} catch (Exception e) {
			logger.debug("获取角色下的用户列表异常！");
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * <p>
	 * Title: addManageRole
	 * </p>
	 * <p>
	 * Description:添加角色
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @author lichuang
	 * @date 2013-5-30
	 * @return ModelAndView
	 * @version 1.0
	 * @throws Exception
	 */
	@RequestMapping("/manage!saveRole.do")
	public ModelAndView saveRole(HttpServletResponse response, RoleAddForm form)
			throws Exception {
		ResultVo vo = null;
		this.sysRoleService
				.insert(form, Context.getInstance().getCurrentUser());
		vo = new ResultSuccessVo();
		OutputHelper.outPut(response, JacksonMapper.objectToJsonString(vo));
		return null;
	}

	/**
	 * <p>
	 * Title: addManageRole
	 * </p>
	 * <p>
	 * Description:添加角色
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @author lichuang
	 * @date 2013-5-30
	 * @return ModelAndView
	 * @version 1.0
	 * @throws Exception
	 */
	@RequestMapping("/manage!updateRole.do")
	public ModelAndView updateRole(HttpServletResponse response, Long id,
			RoleAddForm form) throws Exception {
		ResultVo vo = null;
		this.sysRoleService.update(id, form, Context.getInstance()
				.getCurrentUser());
		vo = new ResultSuccessVo();
		OutputHelper.outPut(response, JacksonMapper.objectToJsonString(vo));
		return null;
	}

	/**
	 * <p>
	 * Title: deleteManageRole
	 * </p>
	 * <p>
	 * Description:删除角色(物理删除)
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 * @author lichuang
	 * @date 2013-5-30
	 * @return ModelAndView
	 * @version 1.0
	 */
	@RequestMapping("/manage!delRole.do")
	public ModelAndView deleteRole(HttpServletRequest request,
			HttpServletResponse response, Long id) {
		try {
			this.sysRoleService.deleteById(id);
			// 当前登录用户
			return new ModelAndView(new RedirectView("manage!roleManage.do"));
		} catch (Exception e) {
			logger.debug("删除角色异常");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * <p>
	 * Title: getPurviewInfo
	 * </p>
	 * <p>
	 * Description:获取角色权限信息列表
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @author lichuang
	 * @date 2013-5-30
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!editRole.do")
	public String editRole(HttpServletResponse response,
			Map<String, Object> model, Long id) {

		try {

			SysRole entity = sysRoleService.getEntity(id);
			model.put("id", id);
			model.put("entity", entity);
			return "sys/role/role_edit";
		} catch (Exception e) {
			logger.debug("获取权限列表异常");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * <p>
	 * Title: rolePermission
	 * </p>
	 * <p>
	 * Description:角色拥有现有权限列表
	 * </p>
	 * 
	 * @param response
	 * @param model
	 * @param id
	 * @return
	 * @author cuidd
	 * @date 2013-8-1
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!rolePermission.do")
	public String rolePermission(HttpServletResponse response,
			Map<String, Object> model, Long id) {
		try {
			List<PermissionByRoleVo> list = sysRoleService
					.findPermissionByRole(id);
			model.put("id", id);
			model.put("list", list);
			return "sys/role/role_permisssion_list";
		} catch (Exception e) {
			logger.debug("获取权限列表异常");
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping("/manage!delRolePermission.do")
	public String delRolePermission(HttpServletResponse response,
			Map<String, Object> model, Long id) throws IOException {
		try {
			sysRoleService.delRolePermission(id);
		} catch (Exception e) {
			logger.debug("获取权限列表异常");
			e.printStackTrace();
		}
		ResultVo vo = new ResultSuccessVo();
		OutputHelper.outPut(response, JacksonMapper.objectToJsonString(vo));
		return null;
	}

	/**
	 * 
	 * <p>
	 * Title: roleSelPermission
	 * </p>
	 * <p>
	 * Description:角色可选权限列表
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 * @author cuidd
	 * @date 2013-8-1
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!roleSelPermission.do")
	public String roleSelPermission(HttpServletRequest request,
			HttpServletResponse response, Long id, PermissionSearchBean bean) {
		try {
			IPageInfo pageInfo = new SetPage(request);
			List<PermissionByRoleVo> list = sysRoleService
					.findPermissionForRoleSel(id, bean, pageInfo);
			request.setAttribute("list", list);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("bean", bean);
			request.setAttribute("id", id);
			return "sys/role/role_sel_permission";
		} catch (Exception e) {
			logger.debug("获取权限列表异常");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * <p>
	 * Title:用户添加权限
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @param ids
	 * @return
	 * @author cuidd
	 * @date 2013-8-1
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!roleAddPermission.do")
	public String roleAddPermission(HttpServletRequest request,
			HttpServletResponse response, Long id, String ids) {
		try {
			sysRoleService.roleAddPermission(id, ids);
			ResultVo vo = new ResultSuccessVo();
			OutputHelper.outPut(response, JacksonMapper.objectToJsonString(vo));
		} catch (Exception e) {
			logger.debug("获取权限列表异常");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * <p>
	 * Title: setPurviewToRole
	 * </p>
	 * <p>
	 * Description:给角色设置权限
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @param checkbox
	 * @param model
	 * @return T_TMP_DATA
	 * @author lichuang
	 * @date 2013-5-30
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!setPurviewToRole.do")
	public String setPurviewToRole(HttpServletRequest request,
			HttpServletResponse response, String[] checkbox,
			Map<String, Object> model) {

		Long roleId = Long.valueOf(ServletRequestUtils.getStringParameter(
				request, "roleId", ""));

		try {
			this.sysRolePurviewService.deleteRolePurviewMapping(roleId);
			if (checkbox == null) {// 权限置空
				// 日志记录
			} else {// 权限重新分配
				this.sysRolePurviewService.addRolePurviewMapping(roleId,
						checkbox);
			}
		} catch (Exception e) {
			logger.debug("角色权限设置异常！");
			e.printStackTrace();
			try {
			} catch (Exception e2) {
			}
		}
		return null;
	}

	/**
	 * <p>
	 * Title: resetPassInRole
	 * </p>
	 * <p>
	 * Description:重置后台登陆人员密码
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param userId
	 * @param roleId
	 * @param roleName
	 * @return
	 * @author lichuang
	 * @date 2013-5-30
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!resetPassInRole.do")
	public String resetPassInRole(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> model,
			Long userId, Long roleId, String roleName) {

		try {
			String passWord = new MD5().getMD5ofStr(AppConstant.RESET_ROLE);// 加密
			int i = this.sysUserService.updatePassWord(userId, passWord);
			if (i == 1) {
				// 记录日志

			}
		} catch (Exception e) {
			logger.debug("角色重置管理员密码异常!");
			e.printStackTrace();
			try {
			} catch (Exception e2) {
			}
		}
		return null;
	}

	/**
	 * <p>
	 * Title: delMURole
	 * </p>
	 * <p>
	 * Description:删除管理员用户当前的角色
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param userId
	 * @param roleId
	 * @param roleName
	 * @return
	 * @author lichuang
	 * @date 2013-5-30
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!delMURole.do")
	public String delMURole(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> model,
			Long userId, Long roleId, String roleName) {

		try {
			int i = this.sysUserService.deleteUserRole(userId);
			if (i == 1) {

			}
		} catch (Exception e) {
			logger.debug("删除管理员用户当前的角色异常!");
			e.printStackTrace();
			try {
			} catch (Exception e2) {
			}
		}
		return null;
	}
}
