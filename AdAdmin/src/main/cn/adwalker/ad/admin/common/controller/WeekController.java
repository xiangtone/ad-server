package cn.adwalker.ad.admin.common.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.adwalker.core.util.JacksonMapper;
import cn.adwalker.core.util.OutputHelper;
import cn.adwalker.core.util.WeekUtil;

/**
 * 
* <p>Title: WeekController</p>
* <p>Description:TODO</p>
* <p>Company: adwalker</p> 
* @author    cuidd
* @date       2013-12-29
 */
@Controller
public class WeekController extends AbstractControllerParent {

	/**
	 * 编辑邮件管理
	 * 
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("manage!weekList.do")
	public String weekList(HttpServletRequest request,
			HttpServletResponse response, Integer season) throws IOException {
		String s = "";
		if (season != null) {
			List<Integer> list = WeekUtil.getWeekListBySeason(season);
			s = JacksonMapper.objectToJsonString(list);
		}
		OutputHelper.outPut(response, s);
		return null;

	}

}
