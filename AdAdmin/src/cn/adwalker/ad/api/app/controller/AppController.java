/**
 * 
 */
package cn.adwalker.ad.api.app.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.adwalker.ad.admin.common.controller.AbstractControllerParent;
import cn.adwalker.ad.api.app.form.App;
import cn.adwalker.ad.api.app.form.Dev;
import cn.adwalker.ad.api.app.form.DevAccount;
import cn.adwalker.ad.api.app.service.IApiLogService;
import cn.adwalker.ad.api.app.service.IApiService;
import cn.adwalker.ad.api.app.util.Crypts;
import cn.adwalker.ad.api.app.util.Signature;
import cn.adwalker.ad.api.app.vo.Result;
import cn.adwalker.core.util.JacksonMapper;
import cn.adwalker.core.util.OutputHelper;

/**
 * 
 * <p>
 * Title: AppController
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-8-2
 */
@Controller("api.AppController")
public class AppController extends AbstractControllerParent {

	@Resource
	private IApiService apiService;

	@Resource
	private IApiLogService apiLogService;

	/** 日志记录器 */
	Logger logger = Logger.getLogger(AppController.class);

	@RequestMapping("/api/updatedev.do")
	public String updatedev(HttpSession session, HttpServletRequest request,
			HttpServletResponse response, String res_data, String sig)
			throws Exception {
		Result result = null;
		try {
			apiLogService.log(res_data);
			logger.debug(res_data);
			if (!StringUtils.isEmpty(res_data)) {
				ObjectMapper mapper = JacksonMapper.getInstance();
				Dev dev = mapper.readValue(res_data, Dev.class);
				if (dev != null && !StringUtils.isEmpty(sig)
						&& Signature.vaildateSignature(sig)) {
					apiService.saveOrUpdateDev(dev);
					result = new Result(true);
				} else {
					result = new Result(false, "密钥不正确" + res_data);
				}
			} else {
				result = new Result(false, "数据为空");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		OutputHelper.outPut(response, JacksonMapper.objectToJsonString(result));

		return null;
	}

	/**
	 * 
	 * <p>
	 * Title: updateApp
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param session
	 * @param request
	 * @param response
	 * @param res_data
	 * @return
	 * @throws IOException
	 * @author cuidd
	 * @date 2013-8-2
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/api/updateapp.do")
	public String updateApp(HttpSession session, HttpServletRequest request,
			HttpServletResponse response, String res_data, String sig)
			throws IOException {
		Result result = new Result();
		try {
			if (!StringUtils.isEmpty(res_data)) {
				apiLogService.log(res_data);
				ObjectMapper mapper = JacksonMapper.getInstance();
				App app = mapper.readValue(res_data, App.class);
				if (app != null && !StringUtils.isEmpty(app.getId())
						&& !StringUtils.isEmpty(sig)
						&& Signature.vaildateSignature(sig)) {
					if (!StringUtils.isEmpty(app.getDev_email())) {
						apiService.saveOrUpdateApp(app);
						result.setMsg(true);
					} else {
						result.setError("开发者不存在");
						result.setMsg(false);
					}
				} else {
					result.setError("应用数据为空或者密钥不正确！！");
					result.setMsg(false);
				}

			} else {
				result.setError("数据为空");
				result.setMsg(false);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		OutputHelper.outPut(response, JacksonMapper.objectToJsonString(result));

		return null;
	}

	/**
	 * 
	 * <p>
	 * Title: devAccount
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param session
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * @author cuidd
	 * @date 2013-8-2
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/api/sendByHour.do")
	public String devAccount(HttpSession session, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {
			apiService.exportCsv();

		} catch (Exception e) {
			e.printStackTrace();
		}
		OutputHelper.outPut(response,
				JacksonMapper.objectToJsonString(new Result(true)));

		return null;
	}

	@RequestMapping("/api/sendApp.do")
	public String sendApp(HttpSession session, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {
			apiService.sendApp(50167L, "审核通过", 4, 1d);

		} catch (Exception e) {
			e.printStackTrace();
		}
		OutputHelper.outPut(response,
				JacksonMapper.objectToJsonString(new Result(true)));

		return null;
	}

	/**
	 * 
	 * <p>
	 * Title: devApplyMoney
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param session
	 * @param request
	 * @param response
	 * @param res_data
	 * @return
	 * @throws IOException
	 * @author cuidd
	 * @date 2013-8-2
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/api/devApplyMoney.do")
	public String devApplyMoney(HttpSession session,
			HttpServletRequest request, HttpServletResponse response,
			String res_data) throws IOException {
		Result result = null;
		try {
			System.out.print(res_data);
			apiLogService.log(res_data);
			if (!StringUtils.isEmpty(res_data)) {
				res_data = Crypts.xorMapDecrypt(res_data);
				System.out.print(res_data);
				ObjectMapper mapper = JacksonMapper.getInstance();
				DevAccount applyMoney = mapper.readValue(res_data,
						DevAccount.class);
				apiService.saveApplyMoney(applyMoney);
				result = new Result(true);
			} else {
				result = new Result(false, "数据为空");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		OutputHelper.outPut(response, JacksonMapper.objectToJsonString(result));
		return null;
	}
}
