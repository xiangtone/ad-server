package cn.adwalker.core.spring;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;


public class ApplicationContextProvider implements ApplicationContextAware {
	
	private static Logger logger = Logger.getLogger(ApplicationContextProvider.class);
	
	public void setApplicationContext(ApplicationContext ctx) throws BeansException {
		logger.info("ctx***init"+ctx);
		AppContext.setApplicationContext(ctx);
	}
	
}
    