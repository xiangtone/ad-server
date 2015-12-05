/**
 * 
 */
package cn.adwalker.ad.admin.common.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import cn.adwalker.model.sys.domain.EManageUserType;
import cn.adwalker.model.sys.domain.SysPurview;
import cn.adwalker.model.sys.domain.SysUser;
import cn.adwalker.ad.admin.common.vo.SysUserVo;
import cn.adwalker.ad.admin.sys.service.ISysUserService;
import cn.adwalker.core.util.MD5;

/**
 * @author wjp
 * 
 */
@Controller
public class LoginController {
	/**
	 * 日志记录器
	 */
	Logger logger = Logger.getLogger(LoginController.class);

	@Resource
	private ISysUserService sysUserService;

	/**
	 * 登录，表单提交
	 */
	@RequestMapping("/dologin.do")
	public ModelAndView manageLogin(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String userName = ServletRequestUtils.getStringParameter(request,
					"userName", "");
			String passWord = ServletRequestUtils.getStringParameter(request,
					"passWord", "");
			passWord = new MD5().getMD5ofStr(passWord);//
			SysUser manageUser = this.sysUserService.getUserByName(userName);
			if (manageUser != null) {
				SysUserVo sysUserVo = this.sysUserService
						.manageUserToVo(manageUser);
				sysUserVo.setPassWord("");
				if (passWord.equals(manageUser.getPassWord())) {
					try {
						request.getSession().setAttribute("manageUser",
								sysUserVo);
						List<SysPurview> list = sysUserService
								.findPurview(sysUserVo.getRoleId());
						Set<String> purviewUrlSets = null;
						if (list != null && list.size() > 0) {
							purviewUrlSets = new HashSet<String>();
							for (int i = 0; i < list.size(); i++) {
								purviewUrlSets.add(list.get(i).getUrl());
							}
						}
						request.getSession().setAttribute("purviewUrlSets",
								purviewUrlSets);
					} catch (Exception e) {
						logger.error(e.toString());
					}
					logger.debug("管理员" + sysUserVo.getUserName() + "登录成功!");
					return new ModelAndView(new RedirectView("main.do"));
				} else {
					request.getSession().setAttribute("loginMsg", "密码错误！");
					return new ModelAndView(new RedirectView("manage.do"));
				}
			} else {
				request.getSession().setAttribute("loginMsg", "用户不存在！");
				return new ModelAndView(new RedirectView("manage.do"));
			}
		} catch (Exception e) {
			request.getSession().setAttribute("loginMsg", "管理员用户登录异常！");
			e.printStackTrace();
			return new ModelAndView(new RedirectView("manage.do"));
		}
	}

	/**
	 * 根据管理员类型获取名称
	 * 
	 * @param type
	 * @return
	 */
	public static String getRoleNameByType(int type) {
		String name = "";
		switch (type) {
		case 1:
			name = EManageUserType.ADMIN.getName();
			break;
		default:
			name = "";
		}
		return name;
	}
}