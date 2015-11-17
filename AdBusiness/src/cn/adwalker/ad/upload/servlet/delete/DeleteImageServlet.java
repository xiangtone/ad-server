package cn.adwalker.ad.upload.servlet.delete;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.adwalker.ad.util.ConfigUtil;

public class DeleteImageServlet extends HttpServlet {

	private static final long serialVersionUID = -8888761384994276627L;

	private static Log log = LogFactory.getLog(DeleteImageServlet.class);

	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("DeleteImageServlet begin...");
		long start = System.currentTimeMillis();
		
		String path = request.getParameter("p");
		// 拼接绝对路径
		path = ConfigUtil.getString("img.path") + path;

		File f = new File(path);

		if (f.exists()) {
			f.delete();
		}

		log.info("DeleteImageServlet end. cost time:" + (System.currentTimeMillis() - start) + "ms");
		return;
	}

}
