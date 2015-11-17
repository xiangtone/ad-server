package cn.adwalker.ad.control.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.adwalker.ad.control.exception.DatabaseException;
import cn.adwalker.ad.control.logger.ReceiveLogger;
import cn.adwalker.ad.control.service.AdActualDayService;
import cn.adwalker.ad.control.util.JacksonMapper;
import cn.adwalker.ad.control.util.Result;
import cn.adwalker.ad.control.util.ReturnError;
import cn.adwalker.ad.control.vo.AdActualDayVo;

/**
 * 广告实时投放量
 * @author liuchen
 */
@Controller
@RequestMapping("/adActualDay")
public class AdActualDayController {

	@Resource
	private AdActualDayService adActualDayService;
	
	@RequestMapping(value="/receiveAdActualDayJson.do", produces="text/html;charset=UTF-8")
	public @ResponseBody String receiveAdActualDayJson(HttpServletRequest request, String json) throws Exception {
		String content = "";
		if(json != null) {
			String ip = request.getHeader("X-Real-IP");
			List<AdActualDayVo> adActualDayVoList = JacksonMapper.jsonToList(json, AdActualDayVo.class);
			ReceiveLogger receiveLogger = new ReceiveLogger();
			receiveLogger.log(adActualDayVoList, ip);
			Result result = adActualDayService.putAdActualDayAll(adActualDayVoList);
			content = JacksonMapper.objectToJsonString(result);
		} else {
			Result result = new Result();
			result.setReturnError(ReturnError.PARAM_INVALID);
			result.setSucceed(false);
			content =  JacksonMapper.objectToJsonString(result);
		}
		return content;
	}
	
	@RequestMapping(value="/dataImportSucceed.do", produces="text/html;charset=UTF-8")
	public @ResponseBody String dataImportSucceed() throws IOException, DatabaseException {
		String content = "";
		Result result = adActualDayService.writeRealData();
		content = JacksonMapper.objectToJsonString(result);
		return content;
	}
	
}
