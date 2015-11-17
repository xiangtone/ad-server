/**
 * <p>Title: Context.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author kingdom
 * @date 2013-5-26
 * @version 1.0
 */
package cn.adwalker.core.context;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.ServletContextAware;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import cn.adwalker.ad.admin.common.vo.SysUserVo;

/**
 * <p>
 * Title: Context
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-5-26
 */
public class Context implements ServletContextAware {

	private static ServletContext sc;

	public static Context context;

	private Context() {
	}

	public static Context getInstance() {
		if (context == null) {
			context = new Context();
		}
		return context;
	}

	public ServletContext getServletContext() {
		return Context.sc;
	}

	/**
	 * 
	 * <p>
	 * Title: getHttpServletRequest
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @return
	 * @author cuidd
	 * @date 2013-5-28
	 * @return HttpServletRequest
	 * @version 1.0
	 */
	public HttpServletRequest getHttpServletRequest() {
		HttpServletRequest request = null;
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		if (attributes != null) {
			request = attributes.getRequest();
		}
		return request;
	}

	public SysUserVo getCurrentUser() {
		SysUserVo user = null;
		HttpSession session = getSession();
		if (session != null) {
			user = (SysUserVo) session.getAttribute("manageUser");
		}
		return user;
	}

	/**
	 * 
	 * <p>
	 * Title: getRequest
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @return
	 * @author cuidd
	 * @date 2013-6-13
	 * @return HttpServletRequest
	 * @version 1.0
	 */
	public HttpServletRequest getRequest() {
		return this.getHttpServletRequest();
	}

	/**
	 * 
	 * <p>
	 * Title: getSession
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @return
	 * @author cuidd
	 * @date 2013-6-13
	 * @return HttpSession
	 * @version 1.0
	 */
	public HttpSession getSession() {
		HttpSession session = null;
		HttpServletRequest request = getRequest();
		if (request != null) {
			session = request.getSession();
		}
		return session;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: setServletContext
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param arg0
	 * @see org.springframework.web.context.ServletContextAware#setServletContext(javax.servlet.ServletContext)
	 */
	@Override
	public void setServletContext(ServletContext arg0) {
		sc = arg0;
	}

	public String getRequestURI() {
		String s = null;
		HttpServletRequest request = getRequest();
		if (request != null) {
			s = request.getRequestURI();
		}
		return s;
	}

}
