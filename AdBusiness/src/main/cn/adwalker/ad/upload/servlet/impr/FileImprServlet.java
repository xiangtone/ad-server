package cn.adwalker.ad.upload.servlet.impr;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.adwalker.ad.upload.util.AppConstant;
import cn.adwalker.ad.upload.vo.ParamVO;
import cn.adwalker.ad.upload.vo.file.FileParamVO;
import cn.adwalker.ad.util.ConfigUtil;

public class FileImprServlet extends ImprServlet {

	private static final long serialVersionUID = 4897610920500715014L;

	private static Log log = LogFactory.getLog(FileImprServlet.class);

	/**
	 * 测试链接:http://localhost:8080/EScore_component_common/upload/fi?qsl=3&fe=*.zip&po=0&pa=test/&ic=1&fn=lly
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("FileImprServlet begin...");
		long start = System.currentTimeMillis();

		// 1.拼接上传的url和参数
		StringBuffer url = new StringBuffer();
		url.append(ConfigUtil.getString("project.url")).append(AppConstant.METHOD_FILE);

		ParamVO pVO = (ParamVO) request.getAttribute("ParamVO");
		FileParamVO vo = new FileParamVO(pVO);

		// 2.封装参数
		request.setAttribute("script", url.toString());
		request.setAttribute("scriptData", vo.toString());
		request.setAttribute("auto", false);// 自动提交

		// 3.跳转页面
		RequestDispatcher rd = request.getRequestDispatcher("/upload/jsp/index.jsp");
		rd.forward(request, response);
		log.info("FileImprServlet end. cost time:" + (System.currentTimeMillis() - start) + "ms");
		return;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
