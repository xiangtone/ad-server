package cn.adwalker.core.filter;

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

/**
 * session验证filter
 * 
 * @author gary
 * 
 */
public class SecurityCheckFilter implements Filter {

	private static final Logger log = LoggerFactory
			.getLogger(SecurityCheckFilter.class);

	private FilterConfig filterConfig;// web.xml配置文件配置
	private String onErrorUrl;// onError属性值
	private String onManageUrl;// onManageError属性值
	private String onAllErrorUrl;// onAllError属性值
	private Set<String> noNeedCheckUrlSet;// 无需验证的url集合

	public SecurityCheckFilter() {

	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		// 配置文件属性
		filterConfig = config;
		onErrorUrl = filterConfig.getInitParameter("onError");
		if (onErrorUrl == null || "".equals(onErrorUrl)) {
			onErrorUrl = "login.jsp";
		}
		onManageUrl = filterConfig.getInitParameter("onManageError");
		if (onManageUrl == null || "".equals(onManageUrl)) {
			onManageUrl = "manage.do";
		}
		onAllErrorUrl = filterConfig.getInitParameter("onAllError");
		if (onAllErrorUrl == null || "".equals(onAllErrorUrl)) {
			onAllErrorUrl = "index.do";
		}

		// 无需验证的url集合
		noNeedCheckUrlSet = new HashSet<String>();
		noNeedCheckUrlSet.add("/resetPassword.do");// 重置密码
		noNeedCheckUrlSet.add("/dologin.do");// 后台登录
		noNeedCheckUrlSet.add("/login.do");// 后台登录
		noNeedCheckUrlSet.add("/manage!manageLogout.do");// 管理员退出登录
		noNeedCheckUrlSet.add("/newsNoticeList.do");// 新闻
		noNeedCheckUrlSet.add("/detailNewsNotice.do");// 新闻
		noNeedCheckUrlSet.add("/refreshConfig.do");// 刷新财务设置信息
		noNeedCheckUrlSet.add("/confrimAccount.do");// 激活
		noNeedCheckUrlSet.add("/getType.do");// 分类
		noNeedCheckUrlSet.add("/getPlatform.do");// 分类类型
		noNeedCheckUrlSet.add("/getCategory.do");// 获取分类
		noNeedCheckUrlSet.add("/getCategoryById.do");// 根据ID获取分类信息
		noNeedCheckUrlSet.add("/index.do");// 首页（新闻公告展示）

		noNeedCheckUrlSet.add("/updateAppDate.do");// 修改上传应用信息共用方法（暂时不拦截）
		noNeedCheckUrlSet.add("/updateAdDate.do");
		noNeedCheckUrlSet.add("/updateAppIcon.do");
		noNeedCheckUrlSet.add("/updateAdIcon.do");
		noNeedCheckUrlSet.add("/provinceCitySort.do");
		noNeedCheckUrlSet.add("/findChannelPagebyId.do");// 渠道包号
		noNeedCheckUrlSet.add("/getCurrency.do");// 虚拟货币

		noNeedCheckUrlSet.add("/channelLogin.do");// 渠道查看
		noNeedCheckUrlSet.add("/channelLoginSearch.do");// 渠道查询
		noNeedCheckUrlSet.add("/channelLoginDownloadExcel.do");// 渠道导出数据
		noNeedCheckUrlSet.add("/manage!saveConfirmTemp.do");// 保存运营确认临时个数

		noNeedCheckUrlSet.add("/updateAdStatus.do");// 系统限量更新接口

		// 接口
		noNeedCheckUrlSet.add("/api/updatedev.do");//
		noNeedCheckUrlSet.add("/api/updateapp.do");//
		noNeedCheckUrlSet.add("/api/devApplyMoney.do");//
		noNeedCheckUrlSet.add("/api/sendApp.do");

	}

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest hreq = (HttpServletRequest) request;
		HttpServletResponse hres = (HttpServletResponse) response;
		HttpSession httpSession = hreq.getSession();

		// 确保浏览器不缓存页面数据
		hres.setHeader("Cache-Control", "no-cache");
		hres.setHeader("Cache-Control", "no-store");
		hres.setDateHeader("Expires", 0);
		hres.setHeader("Pragma", "no-cache");

		log.debug("【请求地址】" + hreq.getRequestURI());
		// 验证开始
		if (!noNeedDecideURL(hreq)) {
			if (!checkBack(httpSession)) {
				log.info("管理员未登录！！！");
				hres.sendRedirect(hreq.getContextPath() + "/" + onManageUrl);
				return;
			}
		}
		chain.doFilter(request, response);
	}

	/**
	 * 验证后台
	 * 
	 * @param httpSession
	 * @return
	 */
	private boolean checkBack(HttpSession httpSession) {
		if (httpSession.getAttribute("manageUser") == null) {
			log.info("管理员未登录！！！");
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
}