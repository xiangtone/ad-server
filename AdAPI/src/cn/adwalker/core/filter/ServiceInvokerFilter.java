package cn.adwalker.core.filter;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.adwalker.IOSChannel.picker.util.Logger;

/**
 * 
 * <p>
 * Title: ServiceInvokerFilter
 * </p>
 * <p>
 * Description:接口调用权限检查过滤器
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author caiqiang
 * @date 2013-1-10
 */
public class ServiceInvokerFilter implements Filter {

	private static final Logger logger = Logger.getLogger(ServiceInvokerFilter.class);
	

	@Override
	public void init(FilterConfig config) throws ServletException {

	}

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {	
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		response.setCharacterEncoding("UTF-8");
		String servletPath = request.getServletPath();
		loggerParams(request,servletPath);
		/**
		 * 广告调用确认激活接口
		 * 
		if (!StringUtils.isEmpty(servletPath) && (servletPath.contains(UrlMapping.COMMON_COMFIRM_ACTIVATE_GET))) {
			String action = getAppId(request);
			if(hasPicker(action)){
				RequestDispatcher dispatch=request.getRequestDispatcher(UrlMapping.PICKER_COMFIRM_ACTIVATE_GET); 
				dispatch.forward(request, response);
				return ;
			}	
		}*/
		/**
		 * 渠道请求确认接口 get appId为广告对接标识
		 *
		if (!StringUtils.isEmpty(servletPath) && (servletPath.contains(UrlMapping.COMMON_CHANNEL_CHECK_GET))) {
			String action = getAppId(request);
			if(hasPicker(action)){
				RequestDispatcher dispatch=request.getRequestDispatcher(UrlMapping.PICKER_CHANNEL_CHECK_GET); 
				dispatch.forward(request, response);
				return ;
			}
		} */
		chain.doFilter(req, res);
	}
	
	/**
	 * 打印请求url带参数
	 * @param request
	 * @param url
	 */
	@SuppressWarnings("unchecked")
	private void loggerParams(HttpServletRequest request,String url){
		StringBuffer sbuf = new StringBuffer(url+"");
        Enumeration<String> en = request.getParameterNames();
		boolean b=true;
		while(en.hasMoreElements()){
			String t = b?"?":"&";
			String name=en.nextElement();
			String value=request.getParameter(name);
			sbuf.append(t+name+"="+value);
			if(b) b=false;
		}
		logger.logInfo(sbuf.toString());
	}

}
