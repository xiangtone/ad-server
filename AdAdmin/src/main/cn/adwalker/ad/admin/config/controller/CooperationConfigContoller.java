package cn.adwalker.ad.admin.config.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.page.SetPage;
import cn.adwalker.core.util.OutputHelper;
import cn.adwalker.model.config.domain.CooperationConfig;
import cn.adwalker.ad.admin.config.form.CooperationConfigForm;
import cn.adwalker.ad.admin.config.service.ICooperationConfigService;

@Controller
public class CooperationConfigContoller {
	@Resource
	 ICooperationConfigService cooperationConfigService;

	@RequestMapping("/config!cooperConfigList.do")
	public String configList(HttpServletRequest request,
			  CooperationConfigForm form,HttpServletResponse response)throws Exception {
		IPageInfo pageInfo = new SetPage(request);
		List<CooperationConfig> configList= cooperationConfigService.getList(form, pageInfo);
		request.setAttribute("list", configList);
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("vo", form);
		request.setAttribute("pageRecord", pageInfo.getTotalRow());
		request.setAttribute("page", pageInfo.getPageSize());
		return "operation/cooperationConfig/interface_Cooperation_list";
	}
	
	@RequestMapping("/config!editConfig.do")
	public String editConfig(HttpServletRequest request,
			  CooperationConfigForm form,HttpServletResponse response)throws Exception {
		
		if(form!=null && form.getApp_id()!=null){
			CooperationConfig config=this.cooperationConfigService.getConfigByAppId(form.getApp_id());
			request.setAttribute("vo", config);
		}
		return "operation/cooperationConfig/interface_Cooperation_edit";
	}
	@RequestMapping("/config!addConfig.do")
	public String addConfig(HttpServletRequest request,HttpServletResponse response)throws Exception {
		
		return "operation/cooperationConfig/interface_Cooperation_add";
	}
	@RequestMapping("/config!saveConfig.do")
	public String saveConfig(HttpServletRequest request,
			  CooperationConfigForm form,HttpServletResponse response) {
		String result="{status:1}";
		if(form!=null){
		 boolean isok=false;
		 try{
			 isok= this.cooperationConfigService.saveOrUpdateConfig(form);
			 if(isok){
				 result="{status:1}";
			 }else{
				 result="{status:-1,error:'保存失败!'}";
			 }
		 }catch(Exception e){
			 result="{status:-1,error:'保存失败!'}";
			 e.printStackTrace();
		 }
		}else{
			 result="{status:-1,error:'保存失败!'}";
		}
		OutputHelper.outPut(response, result);
		return null;
	}
}
