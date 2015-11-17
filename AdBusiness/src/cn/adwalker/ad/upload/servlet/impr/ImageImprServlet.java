package cn.adwalker.ad.upload.servlet.impr;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.adwalker.ad.upload.util.AppConstant;
import cn.adwalker.ad.upload.util.Function;
import cn.adwalker.ad.upload.vo.ParamVO;
import cn.adwalker.ad.upload.vo.image.ImageParamVO;
import cn.adwalker.ad.util.ConfigUtil;

public class ImageImprServlet extends ImprServlet {

	private static final long serialVersionUID = 4897610920500715014L;

	private static Log log = LogFactory.getLog(ImageImprServlet.class);

	/**
	 * 测试链接:http://localhost:8080/EScore_component_common/upload/ii?qsl=3&fe=*.jpg&po=0&pa=test/&ic=1&cc=1&zs=480_854&fn=lly
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("ImageImprServlet begin...");
		long start = System.currentTimeMillis();

		// 1.接收参数
		String canCut = request.getParameter("cc");// 是否允许裁剪 0:不允许 1:允许
		String zoomSize = request.getParameter("zs");// 缩放尺寸

		// 2.参数验证
		if (canCut == null || (!canCut.equals(AppConstant.IS_CUT_YES) && !canCut.equals(AppConstant.IS_CUT_NO))) {
			canCut = AppConstant.IS_CUT_NO;// 默认允许裁剪
		}

		if (zoomSize == null || zoomSize.trim().equals("")) {
			Function.forwardError("zs参数不正确", request, response);
			return;
		}

		// 3.拼接上传的url和参数
		StringBuffer url = new StringBuffer();
		url.append(ConfigUtil.getString("project.url")).append(AppConstant.METHOD_IMAGE);

		ParamVO pVO = (ParamVO) request.getAttribute("ParamVO");
		ImageParamVO vo = new ImageParamVO(pVO);
		vo.setZs(zoomSize);
		vo.setCc(canCut);

		// 4.封装参数
		request.setAttribute("script", url.toString());
		request.setAttribute("scriptData", vo.toString());
		request.setAttribute("auto", true);// 不自动自动提交

		// 5.跳转页面
		RequestDispatcher rd = request.getRequestDispatcher("/upload/jsp/index.jsp");
		rd.forward(request, response);
		log.info("ImageImprServlet end. cost time:" + (System.currentTimeMillis() - start) + "ms");
		return;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
