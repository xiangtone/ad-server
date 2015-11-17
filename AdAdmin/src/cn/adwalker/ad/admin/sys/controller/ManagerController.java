package cn.adwalker.ad.admin.sys.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.adwalker.model.sys.dao.IPermissionDao;
import cn.adwalker.model.sys.domain.SysPurview;
import cn.adwalker.ad.admin.common.controller.AbstractControllerParent;
import cn.adwalker.ad.admin.common.vo.SysUserVo;
import cn.adwalker.ad.admin.sys.service.IResourceService;

/**
 * <p>
 * Title: ManagerController
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-4-19
 */
@Controller
public class ManagerController extends AbstractControllerParent {

	/** 日志记录器 */
	Logger logger = Logger.getLogger(ManagerController.class);

	/** 管理员用户权限服务接口 */
	@Resource
	private IResourceService resourceService;

	/**
	 * 系统权限控制
	 */
	@Resource
	private IPermissionDao permissionDao;

	/**
	 * 跳转到登录页面
	 * 
	 * @return
	 */
	@RequestMapping("/login.do")
	public String toLogin(HttpServletRequest request) {
		return "login";
	}

	/**
	 * 登录成功跳到管理员首页
	 * 
	 * @return
	 */
	@RequestMapping("/main.do")
	public String main(HttpServletRequest request) {
		// 获取当前登录管理员
		SysUserVo manageUser = (SysUserVo) request.getSession().getAttribute(
				"manageUser");
		if (manageUser == null) {
			return "login";
		}
		try {
			// 初始化账户权限信息
			TreeMap<SysPurview, List<SysPurview>> treemap = this.resourceService
					.getCurrentUserPurview(manageUser.getId());

			Set<String> buttonList = permissionDao
					.getButtonPermission(manageUser.getId());

			// SysPurviewVo vo = this.resourceService.getUserPurview(manageUser
			// .getId());
			request.getSession().setAttribute("myButtonList", buttonList);
			request.getSession().setAttribute("myPurviewMap", treemap);
			return "main";
		} catch (Exception e) {
			logger.debug("获取管理员用户权限信息异常！");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 登录成功跳到管理员首页
	 * 
	 * @return
	 */
	@RequestMapping("/index.do")
	public String initManageIndexPage(HttpServletRequest request) {
		return "index";

	}

	/**
	 * 退出/重新登录
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/manage!manageLogout.do")
	public String logout(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		Enumeration<String> attrName = session.getAttributeNames();
		String name;
		while (attrName.hasMoreElements()) {
			name = attrName.nextElement();
			session.removeAttribute(name);
		}
		session.invalidate();
		response.sendRedirect("manage.do");
		return null;
	}
}
