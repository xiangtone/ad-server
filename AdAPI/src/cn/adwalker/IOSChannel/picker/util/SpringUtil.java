package cn.adwalker.IOSChannel.picker.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringUtil implements ApplicationContextAware {
	private static ApplicationContext applicationContext = null;

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		SpringUtil.applicationContext = applicationContext;
	}

	public static Object getBean(String name) {
		return applicationContext.getBean(name);
	}

	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name, Class <T> type) {
		return (T)applicationContext.getBean(name);
	}
}
