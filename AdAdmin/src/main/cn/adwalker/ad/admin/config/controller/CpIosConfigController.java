package cn.adwalker.ad.admin.config.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.adwalker.ad.admin.common.controller.AbstractControllerParent;
import cn.adwalker.ad.admin.config.bean.ICpCampaignBean;
import cn.adwalker.ad.admin.config.form.CpCampaignConfigEditForm;
import cn.adwalker.ad.admin.config.form.CpCampaignConfigForm;
import cn.adwalker.ad.admin.config.form.SendClickInfoForm;
import cn.adwalker.ad.admin.config.service.ICpIosConfigService;
import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.page.SetPage;
import cn.adwalker.core.util.OutputHelper;
import cn.adwalker.model.config.domain.CampaignConfig;
@Controller
public class CpIosConfigController extends AbstractControllerParent{
	
	@Resource
	ICpIosConfigService cpIosConfigService;
	
	@RequestMapping("/manage!cpIosConfig.do")
	public String reportList(HttpServletRequest request, ICpCampaignBean bean,
			HttpSession session) throws Exception {
		IPageInfo pageInfo = new SetPage(request);
		List<CampaignConfig> configList=this.cpIosConfigService.getList(bean, pageInfo);
		request.setAttribute("list", configList);
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("vo", bean);
		request.setAttribute("pageRecord", pageInfo.getTotalRow());
		request.setAttribute("page", pageInfo.getPageSize());
		return "operation/campaignConfig/interface_Campaign_list";
	}
	/**
	 * 刷新广告配置缓存
	 * @param request
	 * @param ad_id
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/manage!reflushCache.do")
	public String reflushCache(HttpServletRequest request,String ad_id,HttpSession session,HttpServletResponse response) throws Exception{
		String result=cpIosConfigService.reflushCache(ad_id);
		OutputHelper.outPut(response, result);
		return null;
	}
	
	@RequestMapping("/manage!eidteCampaignConfig.do")
	public String editCampaign(HttpServletRequest request,Long id,HttpSession session) throws Exception {
		if(id!=null){
		  CampaignConfig config=cpIosConfigService.getConfigById(id);
		  request.setAttribute("vo", config);
		}
		return "operation/campaignConfig/interface_Campign_edit";
	}
	
	@RequestMapping("/manage!addCampaignConfig.do")
	public String addCampaign(HttpServletRequest request,HttpSession session) throws Exception {
		return "operation/campaignConfig/interface_Campign_add";
	}
	
	/**
	 * 
	* <p>Title: saveCampaign</p>
	* <p>Description:保存配置</p>
	* @param request
	* @param form
	* @param session
	* @param response
	* @return
	* @throws Exception
	* @author cuidd
	* @date 2014年11月17日
	* @return String
	* @version 1.0
	* @throws
	 */
	@RequestMapping("/manage!saveCampaignConfig.do")
	public String saveCampaign(HttpServletRequest request,CpCampaignConfigForm form,HttpSession session
			,HttpServletResponse response) throws Exception {
		String result="{status:1}";
		try{
			this.cpIosConfigService.saveCampaignConfig(form);
		}catch(Exception e){
			e.printStackTrace();
			result="{status:-1,error:'保存失败！'}";
		}
		OutputHelper.outPut(response, result);
		return null;
	}
	
	
	/**
	 * 
	* <p>Title: update</p>
	* <p>Description:更新配置</p>
	* @param request
	* @param form
	* @param session
	* @param response
	* @return
	* @throws Exception
	* @author cuidd
	* @date 2014年11月17日
	* @return String
	* @version 1.0
	 */
	@RequestMapping("/manage!updateCampaignConfig.do")
	public String update(HttpServletRequest request,CpCampaignConfigEditForm form,HttpSession session
			,HttpServletResponse response) throws Exception {
		String result="{status:1}";
		try{
			this.cpIosConfigService.updateCampaignConfig(form);
		}catch(Exception e){
			e.printStackTrace();
			result="{status:-1,error:'保存失败！'}";
		}
		OutputHelper.outPut(response, result);
		return null;
	}
	
	@RequestMapping("/manage!sendClickInfoPage.do")
	public String sendClickInfo(HttpServletRequest request,String id,HttpSession session,HttpServletResponse response) throws Exception {
			SendClickInfoForm  sendForm=new SendClickInfoForm();
			sendForm.setId(id);
			request.setAttribute("vo", sendForm);
		return "operation/campaignConfig/interface_CampaignSend";
	}
	
	
	@RequestMapping("/manage!sendClickInfo.do")
	public String sendClickInfo(HttpServletRequest request,SendClickInfoForm form,HttpSession session,HttpServletResponse response) throws Exception {
		String result=this.cpIosConfigService.sendClickInfo(form);
		OutputHelper.outPut(response, result);
		return null;
	}
}
