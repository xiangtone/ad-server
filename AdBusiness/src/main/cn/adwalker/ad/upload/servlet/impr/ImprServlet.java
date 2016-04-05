package cn.adwalker.ad.upload.servlet.impr;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.adwalker.ad.upload.util.AppConstant;
import cn.adwalker.ad.upload.util.Function;
import cn.adwalker.ad.upload.vo.ParamVO;
import cn.adwalker.ad.util.ConfigUtil;

public class ImprServlet extends HttpServlet {

	private static final long serialVersionUID = 7162000826905809429L;

	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 1.接收参数
		// String queueSizeLimit = request.getParameter("qsl");// 允许最大上传队列长度
		// String simUploadLimit = request.getParameter("sul");// 同时处理上传任务数
		String fileExt = request.getParameter("fe");// 允许访问的文件格式

		String policy = request.getParameter("po");// 文件名称策略 0:不变1:随机生成
		String path = request.getParameter("pa");// 上传的相对路径
		String isCover = request.getParameter("ic");// 是否覆盖 0:不覆盖 1:覆盖
		// String minSizeLimit = request.getParameter("msl");// 规定最小上传图片的数量

		String functionName = request.getParameter("fn");// 父页面接收返回值的js方法名称

		// 2.参数验证
		// 2.1必须传入的参数，不传入没法继续
		if (path == null || path.equals("")) {// 上传路径
			Function.forwardError("pa参数不正确", request, response);
			return;
		} else {
			if (!path.startsWith("/")) {
				path = "/" + path;
			}
			if (!path.endsWith("/")) {
				path = path + "/";
			}
		}
		if (functionName == null || functionName.equals("")) {// 回调函数名称
			Function.forwardError("fn参数不正确", request, response);
			return;
		}

		// 2.2上传个数限定
		// int qsl = 0;// 允许最大上传队列
		// try {
		// if (!queueSizeLimit.matches(AppConstant.IS_NUM_MATCH)) {// 验证参数是否为正整数
		// qsl = AppConstant.MIN_QUENE_SIZE;
		// }
		// qsl = Integer.valueOf(queueSizeLimit);
		// if (qsl > AppConstant.MAX_QUENE_SIZE) {
		// qsl = AppConstant.MAX_QUENE_SIZE;
		// }
		// } catch (Exception e) {
		// e.printStackTrace();
		// qsl = AppConstant.MIN_QUENE_SIZE;
		// }

		// int msl = 0;// 至少上传几个
		// try {
		// if (!minSizeLimit.matches(AppConstant.IS_NUM_MATCH)) {// 验证参数是否为正整数
		// msl = AppConstant.MIN_QUENE_SIZE;
		// }
		// msl = Integer.valueOf(minSizeLimit);
		// if (msl > AppConstant.MAX_QUENE_SIZE) {
		// msl = AppConstant.MAX_QUENE_SIZE;
		// }
		// } catch (Exception e) {
		// e.printStackTrace();
		// msl = AppConstant.MIN_QUENE_SIZE;
		// }

		// int sul = 0;// 同时进行几个
		// try {
		// if (!simUploadLimit.matches(AppConstant.IS_NUM_MATCH)) {// 验证参数是否为正整数
		// sul = AppConstant.MIN_SIM_UPLOAD;
		// }
		// sul = Integer.valueOf(simUploadLimit);
		// if (sul > AppConstant.MAX_SIM_UPLOAD) {
		// sul = AppConstant.MAX_SIM_UPLOAD;
		// }
		// } catch (Exception e) {
		// e.printStackTrace();
		// sul = AppConstant.MIN_SIM_UPLOAD;
		// }

		// 不允许超出最大限制
		// if (msl > qsl) {
		// msl = qsl;
		// }

		// if (sul > qsl) {
		// sul = qsl;
		// }

		// 2.3 上传文件类型
		try {
			if (!fileExt.matches(AppConstant.FILE_EXT_MATCH)) {
				fileExt = AppConstant.DEFAULT_FILE_EXT;
			}
		} catch (Exception e) {
			e.printStackTrace();
			fileExt = AppConstant.DEFAULT_FILE_EXT;
		}

		// 2.4策略参数
		if (policy == null
				|| (!policy.equals(AppConstant.FILE_NAME_POLOCY_NOCHANGE_RULE) && !policy.equals(AppConstant.FILE_NAME_POLOCY_RANDOM) && !policy
						.equals(AppConstant.FILE_NAME_POLOCY_NOCHANGE_ALL))) {
			policy = AppConstant.FILE_NAME_POLOCY_NOCHANGE_RULE;// 默认不变切符合正则
		}

		// 2.5是否覆盖
		if (isCover == null || (!isCover.equals(AppConstant.IS_COVER_YES) && !isCover.equals(AppConstant.IS_COVER_NO))) {
			isCover = AppConstant.IS_COVER_YES;// 默认覆盖
		}

		// 3.封装参数
		ParamVO vo = new ParamVO(policy, path, isCover);
		request.setAttribute("ParamVO", vo);
		// request.setAttribute("queueSizeLimit", qsl);
		// request.setAttribute("simUploadLimit", sul);
		request.setAttribute("fileExt", fileExt);
		request.setAttribute("functionName", functionName);
		request.setAttribute("basePath", ConfigUtil.getString("project.url"));
		
		doGet(request, response);

	}

}
