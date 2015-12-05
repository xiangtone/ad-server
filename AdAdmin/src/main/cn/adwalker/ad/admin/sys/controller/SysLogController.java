/**
 * 
 */
package cn.adwalker.ad.admin.sys.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.page.SetPage;
import cn.adwalker.model.log.domain.SysLog;
import cn.adwalker.model.sys.domain.SysPurview;
import cn.adwalker.model.sys.domain.SysRole;
import cn.adwalker.ad.admin.common.bean.LogQueryBean;
import cn.adwalker.ad.admin.common.controller.AbstractControllerParent;
import cn.adwalker.ad.admin.common.service.ISysLogService;
import cn.adwalker.ad.admin.common.vo.SysManagerLogVo;
import cn.adwalker.ad.admin.sys.service.IResourceService;
import cn.adwalker.ad.admin.sys.service.ISysRoleService;

/**
 * @author wjp 系统日志
 */
@Controller
public class SysLogController extends AbstractControllerParent {

	/** 日志记录器 */
	Logger logger = Logger.getLogger(SysLogController.class);

	/** 管理员服务接口 */
	@Resource
	private ISysLogService sysLogService;

	/** 管理员用户权限服务接口 */
	@Resource
	private IResourceService resourceService;

	/** 角色服务接口 */
	@Resource
	private ISysRoleService sysRoleService;

	/**
	 * 跳转到登录页面
	 * 
	 * @return
	 */
	@RequestMapping("/manage!listManagerLog.do")
	public String listManagerLog(HttpServletRequest request, LogQueryBean bean) {

		try {
			IPageInfo pageInfo = new SetPage(request);

			List<SysLog> list = (List<SysLog>) this.sysLogService
					.findByPage(bean, pageInfo);
			List<SysManagerLogVo> managerLogVoList = this.sysLogService
					.eScoreManagerLogToVo(list);// 日志列表
			List<SysRole> roleList = this.sysRoleService.findAll();// 角色列表
			List<SysPurview> purviewList = this.resourceService.findSub();// 权限子菜单列表

			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("list", managerLogVoList);
			request.setAttribute("bean", bean);
			request.setAttribute("roleList", roleList);
			request.setAttribute("purviewList", purviewList);

			return "sys/log/sys_log";
		} catch (Exception e) {
			logger.debug("获取系统日志信息异常！");
			e.printStackTrace();
		}
		return null;
	}

}
