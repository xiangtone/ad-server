package cn.adwalker.ad.admin.app.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.adwalker.ad.admin.app.bean.PreventInfoBean;
import cn.adwalker.ad.admin.app.service.IPreventCheatSchemeInfoService;
import cn.adwalker.ad.admin.app.service.IPreventCheatSchemeService;
import cn.adwalker.ad.admin.app.vo.PreventCheatSchemeInfoVo;
import cn.adwalker.ad.admin.common.controller.AbstractControllerParent;
import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.page.SetPage;
import cn.adwalker.core.util.CacheUtils;
import cn.adwalker.core.util.JacksonMapper;
import cn.adwalker.core.util.OutputHelper;
import cn.adwalker.core.vo.ResultSuccessVo;
import cn.adwalker.core.vo.ResultVo;
import cn.adwalker.model.app.domain.PreventCheatScheme;

/**
 * Description:方案基础配置
 */
@Controller
public class PreventCheatSchemeInfoController extends AbstractControllerParent {

	/** 日志记录器 */
	Logger logger = Logger.getLogger(PreventCheatSchemeInfoController.class);

	/** 方案基础数据相关业务 */
	@Resource
	private IPreventCheatSchemeInfoService preventCheatSchemeInfoService;
	@Resource
	private IPreventCheatSchemeService preventCheatSchemeService;
	
	/**
	 * Description: 方案基础数据List
	 */
	@RequestMapping("manage!preventInfoList.do")
	public String preventInfoList(HttpSession session,HttpServletRequest request, PreventInfoBean bean) {
		try {
			IPageInfo pageInfo = new SetPage(request);
			List<PreventCheatSchemeInfoVo> list = this.preventCheatSchemeInfoService.findByPage(bean, pageInfo);
			List<PreventCheatScheme> listP=preventCheatSchemeService.getPreventCheatSchemeList(-2);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("vo", bean);
			request.setAttribute("List", list);
			request.setAttribute("listP", listP);
		} catch (Exception e) {
			logger.debug("方案基础数据设置异常！");
			e.printStackTrace();
		}
		return "manage/adapp/prevent_info_list";

	}

	/**
	 * Title: modifyAppDeduction
	 * Description:修改 方案基础数据
	 */
	@RequestMapping("manage!saveOrModifyPreventInfo.do")
	public String saveOrModifyPreventInfo(PreventInfoBean bean,HttpServletResponse response) {
		try {
			int data = this.preventCheatSchemeInfoService.saveOrUpdate(bean);
			if (data > 0) {
				CacheUtils.updateSchemeDeleteCache(String.valueOf(bean.getSchemeId()));
			}
			ResultVo vo = new ResultSuccessVo();
			OutputHelper.outPut(response, JacksonMapper.objectToJsonString(vo));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
