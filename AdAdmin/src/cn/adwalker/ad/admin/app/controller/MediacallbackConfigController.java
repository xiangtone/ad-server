package cn.adwalker.ad.admin.app.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.adwalker.ad.admin.app.bean.AppMediaBean;
import cn.adwalker.ad.admin.app.service.IMediacallbackConfigService;
import cn.adwalker.ad.admin.app.service.IPreventCheatSchemeService;
import cn.adwalker.ad.admin.app.vo.MediacallbackConfigVo;
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
 * Description:媒体应用控量
 */
@Controller
public class MediacallbackConfigController extends AbstractControllerParent {

	/** 日志记录器 */
	Logger logger = Logger.getLogger(MediacallbackConfigController.class);

	/** 媒体数量调控相关业务 */
	@Resource
	private IMediacallbackConfigService mediacallbackConfigService;
	
	@Resource
	private IPreventCheatSchemeService preventCheatSchemeService;
	/**
	 * Description: 媒体数量调控设置List
	 */
	@RequestMapping("manage!appMediaList.do")
	public String appMediaList(HttpSession session,HttpServletRequest request, AppMediaBean bean) {
		try {
			IPageInfo pageInfo = new SetPage(request);
			List<MediacallbackConfigVo> list = this.mediacallbackConfigService.findByPage(bean, pageInfo);
			List<PreventCheatScheme> listP = preventCheatSchemeService.getPreventCheatSchemeList(1);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("vo", bean);
			request.setAttribute("List", list);
			request.setAttribute("listP", listP);
		} catch (Exception e) {
			logger.debug("媒体数量调控设置异常！");
			e.printStackTrace();
		}
		return "manage/adapp/app_config_list";

	}

	/**
	 * Title: modifyAppDeduction
	 * Description:修改 媒体数量比例
	 */
	@RequestMapping("manage!saveOrModifyAppMedia.do")
	public String saveOrModifyAppMedia(AppMediaBean bean,HttpServletResponse response) {
		try {
			int id = this.mediacallbackConfigService.saveOrUpdate(bean);
			if (id > 0) {
				CacheUtils.updateAdMediaDeleteCache("mediaCallbackConfig_"+String.valueOf(bean.getId()));
			}			
			ResultVo vo = new ResultSuccessVo();
			OutputHelper.outPut(response, JacksonMapper.objectToJsonString(vo));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Title: modifyAppDeduction
	 * Description:删除关系
	 */
	@RequestMapping("manage!delAppMedia.do")
	public String delAppMedia(Long id,HttpServletResponse response) {
		try {
			int num = this.mediacallbackConfigService.delMediacallbackConfig(id);
			if (num > 0) {
				CacheUtils.updateAdMediaDeleteCache("mediaCallbackConfig_"+String.valueOf(id));
			}	
			ResultVo vo = new ResultSuccessVo();
			OutputHelper.outPut(response, JacksonMapper.objectToJsonString(vo));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
