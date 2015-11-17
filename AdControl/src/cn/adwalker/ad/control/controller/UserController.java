package cn.adwalker.ad.control.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.adwalker.ad.control.exception.DatabaseException;
import cn.adwalker.ad.control.service.UserService;
import cn.adwalker.ad.control.util.JacksonMapper;
import cn.adwalker.ad.control.util.Result;

/**
 * 小时日志接口
 * @author liuchen
 */
@Controller
@RequestMapping("/user")
public class UserController {

	@Resource
	private UserService userService;
	
	@RequestMapping(value="/userLog.do", produces="text/html;charset=UTF-8")
	public @ResponseBody String userLog(HttpServletRequest request, String date,Integer hour) throws IOException, DatabaseException {
		String content = "";
		Result result = userService.userDayHourLog(date,hour);
		content = JacksonMapper.objectToJsonString(result);
		return content;
	}
	
}
