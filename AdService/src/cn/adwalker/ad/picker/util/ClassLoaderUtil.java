package cn.adwalker.ad.picker.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;



public final class ClassLoaderUtil {
	private static final Logger logger = Logger.getLogger(ClassLoaderUtil.class);

	@SuppressWarnings("rawtypes")
	public static Class loadClass(String callName) {
		try {
			return Thread.currentThread().getContextClassLoader().loadClass(StringUtil.trim(callName));
		} catch (ClassNotFoundException e) {
			logger.error("Class Is Missing For Name " + callName + "Cause By " + e.getMessage());
			throw new RuntimeException("Load  Class [" + callName + "] Cause By ClassNotFoundExceptionError", e);
		}
	}

	@SuppressWarnings("rawtypes")
	public static Object newInstance(String callName) {
		Class cls = loadClass(callName);
		Object obj = null;
		try {
			obj = cls.newInstance();
		} catch (InstantiationException e) {
			logger.error("InstantiationException Error!", e);
		} catch (IllegalAccessException e) {
			logger.error("IllegalAccessException Error!", e);
		}
		return obj;
	}
	
	@SuppressWarnings("rawtypes")
	public static Object newInstance(Class cls) {
		Object obj = null;
		try {
			obj = cls.newInstance();
		} catch (InstantiationException e) {
			logger.error("InstantiationException Error!", e);
		} catch (IllegalAccessException e) {
			logger.error("IllegalAccessException Error!", e);
		}
		return obj;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Object executeGetIntanceMethod(Class cls) {
		Class[] parameterTypes = new Class[0];
		Object object = null;
		try {
			Object invoker = cls.newInstance();
			Method method = cls.getMethod("getInstance", parameterTypes);
			object = method.invoke(invoker, new Object[0]);
		} catch (SecurityException e) {
			logger.error("SecurityException Error!", e);
		} catch (NoSuchMethodException e) {
			logger.error("NoSuchMethodException Error!", e);
		} catch (IllegalArgumentException e) {
			logger.error("IllegalArgumentException Error!", e);
		} catch (IllegalAccessException e) {
			logger.error("IllegalAccessException Error!", e);
		} catch (InvocationTargetException e) {
			logger.error("InvocationTargetException Error!", e);
		} catch(Exception e){
			e.printStackTrace();
		}
		return object;
	}
	
	public static Object executeGetIntanceMethod(String className){
		return executeGetIntanceMethod(loadClass(className));
		
	}
}
