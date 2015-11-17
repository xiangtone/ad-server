package cn.adwalker.IOSChannel.picker.util;

import org.apache.log4j.xml.DOMConfigurator;

public class Logger {

	private final static String LOG4J_NAME = "logMain";

	private final static String LOG4J_PATH = "conf/log4j.xml";

	private org.apache.log4j.Logger logger = null;

	private static Boolean init = Boolean.FALSE;

	/**
	 * 
	 */
	public Logger() {
		initLogger();
		logger = org.apache.log4j.Logger.getLogger(LOG4J_NAME);
	}

	public Logger(String logName) {
		initLogger();
		logger = org.apache.log4j.Logger.getLogger(logName);
	}

	@SuppressWarnings("rawtypes")
	public Logger(Class callerClass) {
		initLogger();
		logger = org.apache.log4j.Logger.getLogger(callerClass);
	}

	@SuppressWarnings("rawtypes")
	public static Logger getLogger(Class callerClass) {
		return new Logger(callerClass);
	}

	public static Logger getLogger() {
		return new Logger();
	}

	public static Logger getLogger(String logName) {
		return new Logger(logName);
	}

	private static void initLogger() {
		if (!init.booleanValue()) {
			synchronized (init) {
				if (!init.booleanValue()) {
					 DOMConfigurator.configure(Thread.currentThread().getContextClassLoader().getResource(LOG4J_PATH));  
					 //PropertyConfigurator.configure(Thread.currentThread().getContextClassLoader().getResource(LOG4J_PATH));  //log4j.properties ����
					init = Boolean.TRUE;
				}
			}
		}
	}

	public final void logDebug(Object message) {
		logger.debug(message);
	}

	public final void logDebug(Object message, Throwable t) {
		logger.debug(message, t);
	}

	public final void logError(Object message) {
		logger.error(message);
	}

	public final void logError(Object message, Throwable t) {
		logger.error(message, t);
	}

	public final void logFatal(Object message) {
		logger.fatal(message);
	}

	public final void logFatal(Object message, Throwable t) {
		logger.fatal(message, t);
	}

	public final void logWarning(Object message) {
		logger.warn(message);
	}

	public final void logWarning(Object message, Throwable t) {
		logger.warn(message, t);
	}

	public final void logInfo(Object message) {
		logger.info(message);
	}

	public final void logInfo(Object message, Throwable t) {
		logger.info(message, t);
	}

	public final boolean isDebugEnabled() {
		return logger.isDebugEnabled();
	}

	public final boolean isInfoEnabled() {
		return logger.isInfoEnabled();
	}
    public static void main(String[] args){
    	Logger loger = Logger.getLogger("ChannelSendLogger");
    	loger.logInfo("aaaaaaaaaaaaa-bbbbbbbbbbbbbbbb-ccccccccccccc-dddddddddd");
    	//loger.logInfo("test");
    }
}
