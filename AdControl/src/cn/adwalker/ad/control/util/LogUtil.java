package cn.adwalker.ad.control.util;

import java.io.File;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;


public class LogUtil {
	public static Logger getLogger(String path,Class<?> cla) {
        // 生成新的Logger
        Logger logger = Logger.getLogger(cla);
        // 清空Appender。特別是不想使用現存實例時一定要初期化
        logger.removeAllAppenders();
        // 設定Logger級別。
        logger.setLevel(Level.INFO);
        logger.setAdditivity(false);
        // 生成新的Appender
        FileAppender appender = new FileAppender();
        PatternLayout layout = new PatternLayout();
        // log的输出形式
        String conversionPattern = "%m%n";
        layout.setConversionPattern(conversionPattern);
        appender.setLayout(layout);
        // log输出路径
       // appender.setFile(path + File.separator+  "UserLogger.log");
        appender.setFile(path + File.separator);
        // log的文字码
        appender.setEncoding("UTF-8");
        // true:在已存在log文件后面追加 false:新log覆盖以前的log
        appender.setAppend(true);
        // 适用当前配置
        appender.activateOptions();
        // 将新的Appender加到Logger中
        logger.addAppender(appender);
        return logger;
    }
}
