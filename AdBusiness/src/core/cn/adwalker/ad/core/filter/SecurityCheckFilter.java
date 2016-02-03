package cn.adwalker.ad.core.filter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import cn.adwalker.ad.core.spring.AppContext;
import cn.adwalker.ad.web.common.vo.LoginVo;

/**
 * session验证filter
 */
public class SecurityCheckFilter implements Filter {

	private static final Logger log = LoggerFactory.getLogger(SecurityCheckFilter.class);

	private FilterConfig filterConfig;// web.xml配置文件配置
	private String onErrorUrl;// onError属性值
	private String onManageUrl;// onManageError属性值
	private String onAllErrorUrl;// onAllError属性值
	private Set<String> noNeedCheckUrlSet;// 无需验证的url集合
	private String allUrlPrefix;// 前后台页面前缀

	public SecurityCheckFilter() {

	}

	public void init(FilterConfig config) throws ServletException {

		// 配置文件属性
		filterConfig = config;
		onErrorUrl = filterConfig.getInitParameter("onError");
		if (onErrorUrl == null || "".equals(onErrorUrl)) {
			onErrorUrl = "/login.action";
		}
		onManageUrl = filterConfig.getInitParameter("onManageError");
		if (onManageUrl == null || "".equals(onManageUrl)) {
			onManageUrl = "manage.action";
		}
		onAllErrorUrl = filterConfig.getInitParameter("onAllError");
		if (onAllErrorUrl == null || "".equals(onAllErrorUrl)) {
			onAllErrorUrl = "index.action";
		}

		// 无需验证的url集合
		noNeedCheckUrlSet = new HashSet<String>();
		noNeedCheckUrlSet.add("/dologin.action");// 前台登录
		noNeedCheckUrlSet.add("/login.action");// 前台登录
		noNeedCheckUrlSet.add("/gotoReg.action");// 前台注册
		noNeedCheckUrlSet.add("/adlogin.action");// 前台广告主登录
		noNeedCheckUrlSet.add("/developerLogin.action");// 前台开发者登录
		noNeedCheckUrlSet.add("/register.action");// 注册
		noNeedCheckUrlSet.add("/doNoCodeLogin.action");// 登录
		noNeedCheckUrlSet.add("/regeisterSuccess.action");// 注册陈宫跳转
		noNeedCheckUrlSet.add("/sendMail.action");// 注册
		noNeedCheckUrlSet.add("/ajax/checkEmail.action");// 注册的ajax验证
		noNeedCheckUrlSet.add("/loginOut.action");// 前台登录
		noNeedCheckUrlSet.add("/resetPassword.action");// 重置密码
		noNeedCheckUrlSet.add("/refreshConfig.action");// 刷新财务设置信息
		noNeedCheckUrlSet.add("/loginNoCode.action");// 顶栏登录
		noNeedCheckUrlSet.add("/confrimAccount.action");// 激活
		noNeedCheckUrlSet.add("/getType.action");// 分类
		noNeedCheckUrlSet.add("/getPlatform.action");// 分类类型
		noNeedCheckUrlSet.add("/getCategory.action");// 获取分类
		noNeedCheckUrlSet.add("/getCategoryById.action");// 根据ID获取分类信息
		noNeedCheckUrlSet.add("/index.action");// 首页（新闻公告展示）

		noNeedCheckUrlSet.add("/updateAppDate.action");// 修改上传应用信息共用方法（暂时不拦截）
		noNeedCheckUrlSet.add("/updateAppIcon.action");
		noNeedCheckUrlSet.add("/updateAppScreenshot.action");
		noNeedCheckUrlSet.add("/updateAdScreenshot.action");
		noNeedCheckUrlSet.add("/provinceCitySort.action");
		noNeedCheckUrlSet.add("/getCurrency.action");// 虚拟货币
		noNeedCheckUrlSet.add("/channelLogin.action");// 渠道登录

		allUrlPrefix = "/all!";
	}

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest hreq = (HttpServletRequest) request;
		HttpServletResponse hres = (HttpServletResponse) response;
		HttpSession httpSession = hreq.getSession();

		// 确保浏览器不缓存页面数据
		hres.setHeader("Cache-Control", "no-cache");
		hres.setHeader("Cache-Control", "no-store");
		hres.setDateHeader("Expires", 0);
		hres.setHeader("Pragma", "no-cache");

		// 验证开始
		if (!noNeedDecideURL(hreq)) {

			// 前后台都验证
			if (requestHasUrl(hreq, allUrlPrefix)) {
				if (!checkFront()) {// 前后都没登录,跳转首页
					hreq.setAttribute("errorMsg", "您还没登录!");
					hres.sendRedirect(onAllErrorUrl);
					return;
				}
			} else {// 验证前台
				try {
					httpSession.setAttribute("menuNumber", Long.parseLong(hreq.getParameter("parentId")));
				} catch (Exception e) {

				}
				if (!checkFront()) {
					hreq.setAttribute("errorMsg", "您还没登录!");
					hres.sendRedirect(hreq.getContextPath() + "/" + onErrorUrl);
					return;
				}
			}
		}
		chain.doFilter(request, response);
	}

	/**
	 * 验证前台
	 * 
	 * @return
	 */
	private boolean checkFront() {
		ApplicationContext ctx = AppContext.getApplicationContext();
		LoginVo loginVo = (LoginVo) ctx.getBean("currentUser");

		if (loginVo == null) {
			log.info("前台未登录！！！");
			return false;
		}

		try {
			if (loginVo.getEmail() == null) {
				log.info("前台未登录！！！");
				return false;
			}
		} catch (Exception e) {
			log.info("前台未登录！！！");
			return false;
		}

		return true;
	}

	/**
	 * 哪些请求不需要拦截
	 * 
	 * @param hreq
	 * @return
	 */
	private boolean noNeedDecideURL(HttpServletRequest hreq) {
		// 如果访问的url为null，则无需验证
		if (hreq.getServletPath() == null) {
			return true;
		}

		// 无需验证集合包含访问的url，则无通过
		if (noNeedCheckUrlSet.contains(hreq.getServletPath())) {
			return true;
		}
		return false;
	}

	/**
	 * 请求中是否有此url
	 * 
	 * @param url
	 * @return
	 */
	private boolean requestHasUrl(HttpServletRequest hreq, String url) {
		if (hreq.getServletPath().contains(url)) {
			return true;
		}
		return false;
	}
}