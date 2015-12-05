package cn.adwalker.upload.servlet.upload;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.adwalker.ad.config.AppConstant;
import cn.adwalker.ad.config.MessageConstant;
import cn.adwalker.core.util.Function;
import cn.adwalker.core.util.JacksonMapper;
import cn.adwalker.upload.service.impl.ImageUploadService;

public class UploadImageServlet extends HttpServlet {

	private static final long serialVersionUID = -6197276215237446328L;

	private static Log log = LogFactory.getLog(UploadImageServlet.class);

	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		log.info("UploadImageServlet begin...");
		long start = System.currentTimeMillis();

		// 1.接收参数
		String policy = request.getParameter("po");// 文件名称策略 0:不变1:使用fn的名称2:随机生成
		String path = request.getParameter("pa");// 上传的相对路径
		String isCover = request.getParameter("ic");// 是否覆盖 0:不覆盖 1:覆盖
		String canCut = request.getParameter("cc");// 是否允许裁剪 0:不允许 1:允许
		String zoomSize = request.getParameter("zs");// 缩放尺寸
		// String minSizeLimit = request.getParameter("msl");// 规定最少上传文件的数量

		Map<String, Object> map = new HashMap<String, Object>();

		// 2.参数验证
		boolean nullFlag = Function.paramsHasNull(policy, path, isCover, canCut, zoomSize);
		if (!nullFlag) {
			map.put(AppConstant.KEY_ERROR, MessageConstant.ERROR_REQUEST_PARAM);
		} else {
			// 3.逻辑处理
			ImageUploadService us = new ImageUploadService();
			map = us.uploadImage(policy, path, isCover, canCut, zoomSize, request);
		}

		// 4.返回
		String json = JacksonMapper.objectToJsonString(map);
		response.getWriter().print(json);
		log.info("UploadImageServlet end. cost time:" + (System.currentTimeMillis() - start) + "ms");
		return;

	}
}
