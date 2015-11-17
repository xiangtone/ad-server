/**
 * <p>Title: AdEffectIosController.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author kingdom
 * @date 2013-9-10
 * @version 1.0
 */
package cn.adwalker.ad.admin.operation.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.page.SetPage;
import cn.adwalker.core.util.JacksonMapper;
import cn.adwalker.core.util.OutputHelper;
import cn.adwalker.core.vo.ResultErrorVo;
import cn.adwalker.core.vo.ResultSuccessVo;
import cn.adwalker.core.vo.ResultVo;
import cn.adwalker.ad.admin.operation.bean.DspInfobean;
import cn.adwalker.ad.admin.operation.form.DspConfigInfoForm;
import cn.adwalker.ad.admin.operation.form.DspInfoForm;
import cn.adwalker.ad.admin.operation.service.IDspInfoImpService;
import cn.adwalker.ad.admin.operation.vo.DspInfoVo;

/**
* <p>Title: DspInfoController</p>
* <p>Description:dsp业务</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-12-3
 */
@Controller
public class DspInfoController {
	/** 日志记录器 */
	Logger logger = Logger.getLogger(AdEffectAndroidController.class);
	@Resource
	private IDspInfoImpService dspInfoImpService;

	/**
	* <p>Title: dspInfoList</p>
	* <p>Description:列表</p>
	* @param request
	* @param response
	* @param bean
	* @return
	* @author lichuang
	* @date 2013-12-3
	* @return String
	* @version 1.0
	 */
	@RequestMapping("/manage!dspInfoList.do")
	public String dspInfoList(HttpServletRequest request,
			HttpServletResponse response, DspInfobean bean) {
		List<DspInfoVo> list = null;
		try {
			IPageInfo pageInfo = new SetPage(request);
			list = this.dspInfoImpService.findList(bean, pageInfo);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("bean", bean);
			request.setAttribute("list", list);
		} catch (Exception e) {
			logger.debug("获取广告单价列表异常！");
			e.printStackTrace();
		}
		return "operation/dsp_info_list";
	}

	/**
	 * <p>
	 * Title: addAdv
	 * </p>
	 * <p>
	 * Description:录数链接
	 * </p>
	 * 
	 * @param session
	 * @param model
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-5-24
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!addDspInfo.do")
	public String addDspInfo(HttpServletRequest request) throws Exception {
		return "operation/dsp_add";
	}
	/**
	* <p>Title: saveDspInfo</p>
	* <p>Description:保存录入数据</p>
	* @param request
	* @param response
	* @param form
	* @return
	* @throws Exception
	* @author lichuang
	* @date 2013-12-3
	* @return String
	* @version 1.0
	 */
	@RequestMapping("/manage!saveDspInfo.do")
	public String saveDspInfo(HttpServletRequest request,
			HttpServletResponse response,DspInfoForm form) throws Exception {
		ResultVo vo = null;
			try {
				dspInfoImpService.save(form);
				vo = new ResultSuccessVo();
			} catch (Exception e) {
				e.printStackTrace();
				vo = new ResultErrorVo("系统错误");
			}
		
		OutputHelper.outPut(response, JacksonMapper.objectToJsonString(vo));
		return null;
	}
	/**
	* <p>Title: editDsp</p>
	* <p>Description:TODO</p>
	* @param request
	* @param response
	* @param id
	* @return
	* @author lichuang
	* @date 2013-12-3
	* @return String
	* @version 1.0
	 */
	@RequestMapping("/manage!editDspInfo.do")
	public String editDsp(HttpServletRequest request,
			HttpServletResponse response, Long id) {
		DspInfoVo dspVo = null;

		try {
			dspVo =dspInfoImpService.getDspDetail(id);
			request.setAttribute("vo", dspVo);
		} catch (Exception e) {
			logger.debug("修改dsp服务列表异常！");
			e.printStackTrace();
		}
		return "operation/dsp_edit";
	}
	/**
	* <p>Title: updateDspInfo</p>
	* <p>Description:TODO</p>
	* @param request
	* @param response
	* @param model
	* @param session
	* @param code
	* @param form
	* @return
	* @author lichuang
	* @date 2013-12-3
	* @return String
	* @version 1.0
	 */
	@RequestMapping("/manage!updateDspInfo.do")
	public String updateDspInfo(HttpServletRequest request,
			HttpServletResponse response,DspInfoForm form)
			throws Exception {
		ResultVo vo = null;
		try {
			dspInfoImpService.updateDspService(form);
			vo = new ResultSuccessVo();
		} catch (Exception e) {
			e.printStackTrace();
			vo = new ResultErrorVo("系统错误");
		}
		OutputHelper.outPut(response, JacksonMapper.objectToJsonString(vo));
		return null;

	}
	
	/**
	* <p>Title: addDspInfoConfig</p>
	* <p>Description:TODO</p>
	* @param request
	* @param dsp_id
	* @return
	* @throws Exception
	* @author lichuang
	* @date 2013-12-3
	* @return String
	* @version 1.0
	 */
	@RequestMapping("/manage!addDspInfoConfig.do")
	public String addDspInfoConfig(HttpServletRequest request,Long dsp_id) throws Exception {
		request.setAttribute("dsp_id",dsp_id);
		return "operation/dsp_config_add";
	}
	
	
	/**
	* <p>Title: saveDspConfigInfo</p>
	* <p>Description:TODO</p>
	* @param request
	* @param response
	* @param form
	* @return
	* @throws Exception
	* @author lichuang
	* @date 2013-12-3
	* @return String
	* @version 1.0
	 */
		@RequestMapping("/manage!saveDspConfigInfo.do")
		public String saveDspConfigInfo(HttpServletRequest request,
				HttpServletResponse response,DspConfigInfoForm form) throws Exception {
			ResultVo vo = null;
				try {
					dspInfoImpService.saveConfig(form);
					vo = new ResultSuccessVo();
				} catch (Exception e) {
					e.printStackTrace();
					vo = new ResultErrorVo("系统错误");
				}
			
			OutputHelper.outPut(response, JacksonMapper.objectToJsonString(vo));
			return null;
		}
		
		
		/**
		* <p>Title: editConfig</p>
		* <p>Description:读取参数</p>
		* @param request
		* @param response
		* @param dsp_id
		* @return
		* @author lichuang
		* @date 2013-12-3
		* @return String
		* @version 1.0
		 */
			@RequestMapping("/manage!editDspInfoConfig.do")
			public String editConfig(HttpServletRequest request,
					HttpServletResponse response ,Long dsp_id) {
				List<DspConfigInfoForm> list= null;
				try {
					list =dspInfoImpService.getConfigList(dsp_id);
					request.setAttribute("list", list);
				} catch (Exception e) {
					logger.debug("修改dsp服务参数异常！");
					e.printStackTrace();
				}
				return "operation/dsp_config_edit";
			}
			/**
			* <p>Title: updateDspConfig</p>
			* <p>Description:修改参数</p>
			* @param response
			* @param id
			* @param param_type
			* @param param_name
			* @param res
			* @throws Exception
			* @author lichuang
			* @date 2013-12-3
			* @return void
			* @version 1.0
			 */
			@RequestMapping("/manage!updateDspInfoConfig.do")
			public void updateDspConfig(HttpServletResponse response, Long[] id,
					String[] param_type,String[] param_name,
					HttpServletResponse res) throws Exception {
				ResultVo vo = null;
				try {
					dspInfoImpService.updateDspConfig(id, param_type, param_name);
				} catch (Exception e) {
					e.printStackTrace();
				}
				vo = new ResultSuccessVo();
				OutputHelper.outPut(res, JacksonMapper.objectToJsonString(vo));
			}
}
