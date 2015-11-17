package cn.adwalker.ad.picker.system.servlet;

import java.util.Enumeration;

import javax.servlet.http.HttpServlet;
import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import cn.adwalker.ad.picker.util.ClassLoaderUtil;
import cn.adwalker.ad.util.AppConstant;
import cn.adwalker.ad.util.PListUrl;



/*
* 功能描述: 启动一些系统常用任务
* @author luo
*/
public class Startup extends HttpServlet implements Servlet {
	/** @Fields serialVersionUID : TODO*/
	private static final long serialVersionUID = -8087265424000265903L;
	private static final Logger logger =  Logger.getLogger(Startup.class);
	public void init() throws ServletException {
		AppConstant.PLIST_URL=PListUrl.getPlist();
		System.out.println(">>"+AppConstant.PLIST_URL);
		startup();
	}

	public void destroy() {
		shutdown();
	}
	@SuppressWarnings("rawtypes")
	public void startup(){
		logger.info("System Start Begin ......");
		Enumeration names = getServletConfig().getInitParameterNames();
		String name = null;
		IStartUp startUp = null;
		while (names.hasMoreElements()) {
			name = (String) names.nextElement();
			logger.info("Load Start  ......[" + name + "]");
			startUp = (IStartUp) ClassLoaderUtil.executeGetIntanceMethod(getServletConfig().getInitParameter(name));
			if (startUp != null) {
				startUp.init();
			}
		}
	}
	
	@SuppressWarnings("rawtypes")
	public void shutdown() {
		logger.info("shutdown startup .....");
		Enumeration names = getServletConfig().getInitParameterNames();
		String name = null;
		IStartUp startUp = null;
		while (names.hasMoreElements()) {
			name = (String) names.nextElement();
			startUp = (IStartUp) ClassLoaderUtil.executeGetIntanceMethod(getServletConfig().getInitParameter(name));
			if (startUp != null) {
				startUp.destory();
			}
		}
	}
}
