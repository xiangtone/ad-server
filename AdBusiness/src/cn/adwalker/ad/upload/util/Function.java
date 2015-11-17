package cn.adwalker.ad.upload.util;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Function {

	/**
	 * 判断参数是有为空
	 * 
	 * @param params
	 * @return
	 */
	public static boolean paramsHasNull(String... params) {
		if (params != null && params.length != 0) {
			for (int i = 0; i < params.length; i++) {
				String str = params[i];
				if (str == null || str.trim().equals("")) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	/**
	 * 跳转错误页面
	 * 
	 * @param errorMsg
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public static void forwardError(String errorMsg, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute(AppConstant.KEY_ERROR, errorMsg);
		RequestDispatcher rd = request.getRequestDispatcher("/upload/jsp/error.jsp");
		rd.forward(request, response);
		return;
	}

}
