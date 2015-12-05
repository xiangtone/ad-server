package cn.adwalker.ad.admin.sys.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.adwalker.ad.admin.common.controller.AbstractControllerParent;
import cn.adwalker.ad.admin.common.vo.SysUserAccoutVo;
import cn.adwalker.ad.admin.common.vo.SysUserVo;
import cn.adwalker.ad.admin.sys.bean.UserQueryBean;
import cn.adwalker.ad.admin.sys.form.UserAddForm;
import cn.adwalker.ad.admin.sys.form.UserEditForm;
import cn.adwalker.ad.admin.sys.service.ISysUserService;
import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.page.SetPage;
import cn.adwalker.core.util.JacksonMapper;
import cn.adwalker.core.util.MD5;
import cn.adwalker.core.util.OutputHelper;
import cn.adwalker.core.vo.ResultErrorVo;
import cn.adwalker.core.vo.ResultSuccessVo;
import cn.adwalker.core.vo.ResultVo;
import cn.adwalker.model.sys.domain.SysRole;
import cn.adwalker.model.sys.domain.SysUser;
import cn.adwalker.model.sys.domain.SysUserRoleView;

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
public class UserController extends AbstractControllerParent {

	/** 日志记录器 */
	Logger logger = Logger.getLogger(UserController.class);

	/** 管理员服务接口 */
	@Resource
	private ISysUserService sysUserService;

	/**
	 * <p>
	 * Title: getManageUserList
	 * </p>
	 * <p>
	 * Description:获取管理员用户列表
	 * </p>
	 * 
	 * @param request
	 * @param bean
	 * @return
	 * @author lichuang
	 * @date 2013-10-23
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!listManageUser.do")
	public String getManageUserList(HttpServletRequest request, UserQueryBean bean) {
		try {
			IPageInfo pageInfo = new SetPage(request);
			List<SysUserVo> list = this.sysUserService.findByPage(bean, pageInfo);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("manageUserList", list);
			request.setAttribute("keyword", bean);
			return "sys/user/user_list";
		} catch (Exception e) {
			logger.debug("获取管理员用户列表异常！");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 添加管理员用户
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/manage!saveUser.do")
	public String saveUser(HttpServletRequest request, UserAddForm form, Map<String, Object> model, HttpServletResponse response) throws IOException {
		ResultVo result = null;
		try {
			SysUser user = this.sysUserService.getUserByName(form.getUserName());
			if (user != null) {
				result = new ResultErrorVo("该用户已存在，请更换用户名!");
			}
			int i = this.sysUserService.addManageUser(form);
			if (i == 1) {
				result = new ResultSuccessVo();
			}
		} catch (Exception e) {
			logger.debug("管理员添加用户异常");
			e.printStackTrace();
			result = new ResultErrorVo("操作失败！！");
		}
		OutputHelper.outPut(response, JacksonMapper.objectToJsonString(result));
		return null;
	}

	/**
	 * <p>
	 * Title: deleteManageById
	 * </p>
	 * <p>
	 * Description:根据id删除用户，逻辑删除
	 * </p>
	 * 
	 * @param request
	 * @param id
	 * @param model
	 * @return
	 * @author lichuang
	 * @date 2013-10-23
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!deleteManageById.do")
	public String deleteManageById(HttpServletRequest request, Long id, Map<String, Object> model) {

		try {
			int i = this.sysUserService.deleteById(id);
			if (i == 1) {

			}
		} catch (Exception e) {
			logger.debug("删除管理员用户异常！");
			e.printStackTrace();

		}
		return null;
	}

	/**
	 * 跳转到密码修改页面
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("/manage!toModifyPass.do")
	public String toModifyPass(HttpServletRequest request, Long id) {
		if (id == null) {// 修改当前用户密码
			SysUserVo user = (SysUserVo) request.getSession().getAttribute("manageUser");
			id = user.getId();
		}
		request.setAttribute("id", id);
		return "sys/self/ope_sys_xpass";
	}

	/**
	 * 修改管理员密码
	 * 
	 * @param request
	 */
	@RequestMapping("/manage!modifyManagerPassword.do")
	public String modifyManagerPassword(HttpServletRequest request, Model model) {

		String id = ServletRequestUtils.getStringParameter(request, "id", "");
		try {
			String passWord = ServletRequestUtils.getStringParameter(request, "passWord", "");
			String newPassWord = ServletRequestUtils.getStringParameter(request, "newPassWord", "");
			String repeatNewPassWord = ServletRequestUtils.getStringParameter(request, "repeatNewPassWord", "");
			passWord = new MD5().getMD5ofStr(passWord);//
			newPassWord = new MD5().getMD5ofStr(newPassWord);//
			repeatNewPassWord = new MD5().getMD5ofStr(repeatNewPassWord);//

			SysUser user = this.sysUserService.getManageUserById(Long.valueOf(id));
			if (!passWord.equals(user.getPassWord())) {

			}
			if (!newPassWord.equals(repeatNewPassWord)) {

			}
			int i = this.sysUserService.updatePassWord(Long.valueOf(id), newPassWord);
			if (i == 1) {
				// 当前登录用户
				SysUserVo currenUser = (SysUserVo) request.getSession().getAttribute("manageUser");
				// 被操作的用户
				// 日志记录
				if (id.equals(String.valueOf(currenUser.getId()))) {// 如果是当前登录用户，则需重新登录

				} else {

				}
			}
			model.addAttribute("msg", "修改密码成功!!!");
		} catch (Exception e) {
			logger.debug("管理员修改密码异常！");
			e.printStackTrace();
			try {
			} catch (Exception e2) {
			}
			model.addAttribute("msg", "修改密码失败!!!");
		}
		return "sys/self/ope_sys_xpass";
	}

	/**
	 * 重置密码，重置后默认密码是：123456
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/manage!resetManagerPass.do")
	public String resetManagerPass(HttpServletRequest request, HttpServletResponse res, Map<String, Object> model, Long id) {
		ResultVo vo = null;
		try {
			String passWord = new MD5().getMD5ofStr("123456");//
			int i = this.sysUserService.updatePassWord(id, passWord);
			if (i == 1) {
				vo = new ResultSuccessVo();
				OutputHelper.outPut(res, JacksonMapper.objectToJsonString(vo));
			}
		} catch (Exception e) {
			logger.debug("管理员重置密码异常!");
			e.printStackTrace();
			try {
			} catch (Exception e2) {
			}
		}
		return null;
	}

	/**
	 * 授权
	 * 
	 * @param request
	 * @param model
	 * @param userId
	 */
	@RequestMapping("/manage!toAuthorize.do")
	public String toAuthorize(HttpServletRequest request, Map<String, Object> model, Long userId) {

		try {
			SysUser manageUser = this.sysUserService.getManageUserById(userId);
			SysUserVo sysUserVo = this.sysUserService.manageUserToVo(manageUser);
			List<SysUserRoleView> list = this.sysUserService.findRoleByUser(userId);
			request.setAttribute("manageUserVo", sysUserVo);
			request.setAttribute("list", list);
			return "sys/user/user_role";
		} catch (Exception e) {
			logger.debug("跳转到授权页面异常");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 授权
	 * 
	 * @param request
	 * @param model
	 * @param userId
	 * @param roleId
	 * @return
	 */
	@RequestMapping("/manage!authorize.do")
	public String authorize(HttpServletRequest request, Map<String, Object> model, Long userId, String[] checkbox) {

		try {
			this.sysUserService.deleteByRoleId(userId);
			this.sysUserService.updateRoleByUserId(userId, checkbox);

		} catch (Exception e) {
			logger.debug("授权异常！");
			e.printStackTrace();
			try {
			} catch (Exception e2) {
			}
		}
		return null;
	}

	/**
	 * 跳转到账户设置修改
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("/manage!toEditUser.do")
	public String toModifyName(HttpServletRequest request, Long id) {
		SysUserAccoutVo user = null;
		List<SysRole> list = null;
		try {
			list = sysUserService.findAllRole();
			user = sysUserService.getSysUser(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("list", list);
		request.setAttribute("entiy", user);
		return "sys/user/user_edit";
	}

	/**
	 * 修改管理员姓名
	 * 
	 * @param request
	 */
	@RequestMapping("/manage!updateUser.do")
	public String updateUser(HttpServletRequest request, HttpServletResponse response, Map<String, Object> model, UserEditForm form) {
		try {
			int i = this.sysUserService.updateAcount(form);
			if (i == 1) {
				// 当前登录用户
				SysUserVo currenUser = (SysUserVo) request.getSession().getAttribute("manageUser");
				if (form.getId().equals(String.valueOf(currenUser.getId()))) {// 如果是当前登录用户，则需重新登录
					OutputHelper.outPut(response, "true");
				} else {
					OutputHelper.outPut(response, "true");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * <p>
	 * Title: addManage
	 * </p>
	 * <p>
	 * Description:添加账号
	 * </p>
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-4-14
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!addUser.do")
	public String addManage(HttpServletRequest request) throws Exception {
		return "sys/user/user_add";
	}
}
