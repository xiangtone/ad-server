package cn.adwalker.core.util;

import java.io.IOException;
import java.io.PrintStream;
import java.io.Writer;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.adwalker.core.util.lang.StringUtil;

public abstract class OutputHelper {
	private static final Logger logger = LoggerFactory
			.getLogger(OutputHelper.class);

	/**
	 * 通过servletResponse输出数据
	 * 
	 * @param response
	 * @param content
	 */
	public static void outPut(HttpServletResponse response, String content) {
		if (response == null || StringUtil.isEmpty(content)) {
			return;
		}
		response.reset();
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintStream out = null;
		try {
			out = new PrintStream(response.getOutputStream());
			out.print(content);
		} catch (IOException e) {
			logger.error("输出数据失败");
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
	
	/**
	 * 共用方法，ajax打印对象类型的json串
	 * 
	 * @param object
	 * @param reponse
	 */
	public  static void writerJSON(Object object, HttpServletResponse response) {
		try {
			String json = JacksonMapper.objectToJsonString(object);
			System.out.println(json);
			Writer writer = response.getWriter();
			writer.write(json);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
