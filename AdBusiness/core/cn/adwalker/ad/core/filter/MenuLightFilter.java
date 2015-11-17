package cn.adwalker.ad.core.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 管理后台菜单高亮显示过滤
 * 
 * @author wjp
 * 
 */
public class MenuLightFilter implements Filter {

	public MenuLightFilter() {

	}

	public void init(FilterConfig config) throws ServletException {

	}

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest hreq = (HttpServletRequest) request;
		HttpServletResponse hres = (HttpServletResponse) response;

		// 确保浏览器不缓存页面数据
		hres.setHeader("Cache-Control", "no-cache");
		hres.setHeader("Cache-Control", "no-store");
		hres.setDateHeader("Expires", 0);
		hres.setHeader("Pragma", "no-cache");
		String curCheckMenu = hreq.getRequestURI();// 获取当前请求的.do地址
		curCheckMenu = curCheckMenu.substring(curCheckMenu.lastIndexOf("/"), curCheckMenu.length());// 截取
		chain.doFilter(request, response);
	}

}