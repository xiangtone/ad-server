package cn.adwalker.ad.upload.servlet.upload;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.adwalker.ad.upload.service.impl.FileUploadService;
import cn.adwalker.ad.upload.util.AppConstant;
import cn.adwalker.ad.upload.util.Function;
import cn.adwalker.ad.upload.util.JacksonMapper;
import cn.adwalker.ad.upload.util.MessageConstant;

public class UploadFileServlet extends HttpServlet {

	private static final long serialVersionUID = 7162000826905809429L;

	private static Log log = LogFactory.getLog(UploadFileServlet.class);

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			log.info("UploadFileServlet begin...");
			long start = System.currentTimeMillis();

			// 1.接收参数
			String policy = request.getParameter("po");// 文件名称策略 0:不变1:随机生成2:不变
			String path = request.getParameter("pa");// 上传的相对路径
			String isCover = request.getParameter("ic");// 是否覆盖 0:不覆盖 1:覆盖
			// String minSizeLimit = request.getParameter("msl");// 规定最少上传文件的数量

			Map<String, Object> map = new HashMap<String, Object>();

			// 2.参数验证
			boolean nullFlag = Function.paramsHasNull(policy, path, isCover);
			if (!nullFlag) {
				map.put(AppConstant.KEY_ERROR,
						MessageConstant.ERROR_REQUEST_PARAM);
				log.info("*****************************---->");
			} else {
				// 3.逻辑处理
				FileUploadService us = new FileUploadService();
				map = us.uploadFile(policy, path, isCover, request);
			}
			System.out.println(path);

			// 4.返回
			String json = JacksonMapper.objectToJsonString(map);
			log.info(json);
			System.out.println(json);
			response.getWriter().print(json);
			log.info("UploadFileServlet end. cost time:"
					+ (System.currentTimeMillis() - start) + "ms");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;

	}

}
