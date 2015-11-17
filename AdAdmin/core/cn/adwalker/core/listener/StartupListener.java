package cn.adwalker.core.listener;

import javax.servlet.ServletContextEvent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.ContextLoaderListener;

import cn.adwalker.core.util.ConfigUtil;

public class StartupListener extends ContextLoaderListener {
	private static Log logger = LogFactory.getLog(StartupListener.class);

	public void contextInitialized(ServletContextEvent event) {
		super.contextInitialized(event);
		logger.info("system startup.........");
		event.getServletContext().setAttribute("version", ConfigUtil.getString(ConfigUtil.SYS_VERSION));
		logger.info(ConfigUtil.getString(ConfigUtil.SYS_VERSION));//去掉sysout
		
	}
}