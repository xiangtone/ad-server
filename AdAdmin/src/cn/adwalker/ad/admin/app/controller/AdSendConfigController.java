package cn.adwalker.ad.admin.app.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.adwalker.ad.admin.app.bean.AdSendBean;
import cn.adwalker.ad.admin.app.service.IAdSendConfigService;
import cn.adwalker.ad.admin.app.service.IPreventCheatSchemeService;
import cn.adwalker.ad.admin.app.vo.AdSendConfigVo;
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
 * Description:广告ip设置
 */
@Controller
public class AdSendConfigController extends AbstractControllerParent {

	/** 日志记录器 */
	Logger logger = Logger.getLogger(AdSendConfigController.class);

	/** 广告ip数量调控相关业务 */
	@Resource
	private IAdSendConfigService adSendConfigService;
	
	@Resource
	private IPreventCheatSchemeService preventCheatSchemeService;
	
	/**
	 * Description: 广告ip数量调控设置List
	 */
	@RequestMapping("manage!adSendList.do")
	public String adSendList(HttpSession session,HttpServletRequest request, AdSendBean bean) {
		try {
			IPageInfo pageInfo = new SetPage(request);
			List<AdSendConfigVo> list = this.adSendConfigService.findByPage(bean, pageInfo);
			List<PreventCheatScheme> listP=preventCheatSchemeService.getPreventCheatSchemeList(0);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("vo", bean);
			request.setAttribute("List", list);
			request.setAttribute("listP", listP);
		} catch (Exception e) {
			logger.debug("广告ip数量调控设置异常！");
			e.printStackTrace();
		}
		return "manage/adapp/ad_config_list";

	}

	/**
	 * Title: modifyAppDeduction
	 * Description:修改添加广告ip设置
	 */
	@RequestMapping("manage!saveOrModifyAdSend.do")
	public String saveOrModifyAdSend(AdSendBean bean,HttpServletResponse response) {
		try {
			int id=this.adSendConfigService.saveOrUpdate(bean);
			if(id > 0){
				CacheUtils.updateAdMediaDeleteCache("ADSendConfig_"+String.valueOf(bean.getId()));
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
	@RequestMapping("manage!delAdSend.do")
	public String delAdSend(Long id,HttpServletResponse response) {
		try {
			int num = this.adSendConfigService.delAdSendConfig(id);
			if(num > 0){
				CacheUtils.updateAdMediaDeleteCache("ADSendConfig_"+String.valueOf(id));
			}
			ResultVo vo = new ResultSuccessVo();
			OutputHelper.outPut(response, JacksonMapper.objectToJsonString(vo));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
