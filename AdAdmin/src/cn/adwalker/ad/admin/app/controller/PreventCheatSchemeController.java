package cn.adwalker.ad.admin.app.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.adwalker.ad.admin.app.bean.PreventBean;
import cn.adwalker.ad.admin.app.service.IPreventCheatSchemeService;
import cn.adwalker.ad.admin.app.vo.PreventCheatSchemeVo;
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
public class PreventCheatSchemeController extends AbstractControllerParent {

	/** 日志记录器 */
	Logger logger = Logger.getLogger(PreventCheatSchemeController.class);

	/** 方案基础数据相关业务 */
	@Resource
	private IPreventCheatSchemeService preventCheatSchemeService;
	
	/**
	 * Description: 方案基础数据List
	 */
	@RequestMapping("manage!preventList.do")
	public String preventList(HttpSession session,HttpServletRequest request, PreventBean bean) {
		try {
			IPageInfo pageInfo = new SetPage(request);
			List<PreventCheatSchemeVo> list = this.preventCheatSchemeService.findByPage(bean, pageInfo);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("vo", bean);
			request.setAttribute("List", list);
		} catch (Exception e) {
			logger.debug("方案基础数据设置异常！");
			e.printStackTrace();
		}
		return "manage/adapp/prevent_list";

	}

	/**
	 * Title: modifyAppDeduction
	 * Description:修改 方案基础数据
	 * 修改广告默认方案配置的时候，/cache/deletecache.do?key=area_def_scheme_0
   	         修改媒体默认方案配置的时候，/cache/deletecache.do?key=area_def_scheme_1
	 */
	@RequestMapping("manage!saveOrModifyPrevent.do")
	public String saveOrModifyPrevent(PreventBean bean,HttpServletResponse response) {
		try {
			int id=0;
			ResultVo vo = null;
			if(bean.getId() !=0) {
					 if (bean.getIsDefault() ==1) {
							PreventCheatScheme pr = preventCheatSchemeService.getIsDefaultPreventCheatScheme(bean.getIsDefault(), bean.getAdormedia());	
							PreventCheatScheme pr2 = preventCheatSchemeService.getPreventCheatSchemeById(bean.getId());
							if (pr !=null && pr2 !=null) {
								if (pr.getId() != bean.getId() && pr2.getIs_default() !=1) {
									PreventBean pb =new PreventBean();
									pb.setId(pr.getId());
									pb.setName(pr.getName());
									pb.setIsDefault(0);
									pb.setAdormedia(pr.getAdormedia());					
									id = this.preventCheatSchemeService.saveOrUpdate(bean,pb);
									vo = new ResultSuccessVo();	
								} else if (pr.getId() == bean.getId()){
									id = this.preventCheatSchemeService.saveOrUpdate(bean,null);
									vo = new ResultSuccessVo();								
								}else {
									vo = new ResultSuccessVo(-1);
								}	
							}
						} else {
							PreventCheatScheme pr2 = preventCheatSchemeService.getPreventCheatSchemeById(bean.getId());
							if (pr2.getIs_default() == 0){
								id = this.preventCheatSchemeService.saveOrUpdate(bean,null);
								vo = new ResultSuccessVo();
							} else {
								vo = new ResultSuccessVo(-1);
							}
						}
			} else {
				if (bean.getIsDefault() ==1) {
					PreventCheatScheme pr = preventCheatSchemeService.getIsDefaultPreventCheatScheme(bean.getIsDefault(), bean.getAdormedia());
					if (pr !=null) {
						PreventBean pb =new PreventBean();
						pb.setId(pr.getId());
						pb.setName(pr.getName());
						pb.setIsDefault(0);
						pb.setAdormedia(pr.getAdormedia());					
						id = this.preventCheatSchemeService.saveOrUpdate(bean,pb);
						vo = new ResultSuccessVo();		
					}
				} else {
					 id = this.preventCheatSchemeService.saveOrUpdate(bean,null);
					 vo = new ResultSuccessVo();
				}
				
			}
			
			if (id > 0) {
				CacheUtils.updateAdMediaDeleteCache("area_def_scheme_0");
				CacheUtils.updateAdMediaDeleteCache("area_def_scheme_1");
			}
			OutputHelper.outPut(response, JacksonMapper.objectToJsonString(vo));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
