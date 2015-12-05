package cn.adwalker.core.filter;

import java.io.IOException;
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

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import cn.adwalker.core.pool.SpringDatePool;
import cn.adwalker.core.spring.AppContext;

/**
 * 管理后台菜单高亮显示过滤
 * 
 * @author wjp
 * 
 */
public class MenuLightFilter implements Filter {

	private static Logger logger = Logger.getLogger(MenuLightFilter.class);

	public MenuLightFilter() {

	}

	public void init(FilterConfig config) throws ServletException {

	}

	public void destroy() {

	}

	@SuppressWarnings("unchecked")
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
		String curCheckMenu = hreq.getRequestURI();// 获取当前请求的.do地址
		String contextPath = hreq.getContextPath();
		curCheckMenu = curCheckMenu.substring(contextPath.length(),
				curCheckMenu.length());// 截取
		logger.debug(curCheckMenu);
		ApplicationContext ctx = AppContext.getApplicationContext();
		SpringDatePool springDatePool = (SpringDatePool) ctx
				.getBean("springDatePool");
		if (springDatePool.urlIsExist(curCheckMenu)) {
			httpSession.setAttribute("curCheckMenu", curCheckMenu);
		}
		// purviewUrlSets
		Set<String> purviewUrlSets = (Set<String>) httpSession
				.getAttribute("purviewUrlSets");
		if (!"/manage.do".equals(curCheckMenu)
				&& !"/index.do".equals(curCheckMenu)
				&& !"/login.do".equals(curCheckMenu)
				&& !"/manage!manageLogout.do".equals(curCheckMenu)) {
			boolean tag = urlIsExist(purviewUrlSets, curCheckMenu);
			if (!tag) {
				// return;
				// curCheckMenu="/manage.do";
				// hres.sendRedirect("index.do");
			}
		}

		chain.doFilter(request, response);
	}

	private boolean urlIsExist(Set<String> purviewUrlSets, String url) {
		if (!StringUtils.isEmpty(url) && purviewUrlSets != null
				&& purviewUrlSets.size() > 0) {
			if (purviewUrlSets.contains(url)) {
				return true;
			}
		}
		return false;
	}
}