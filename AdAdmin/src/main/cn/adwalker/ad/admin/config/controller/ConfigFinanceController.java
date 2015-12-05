/**
 * 
 */
package cn.adwalker.ad.admin.config.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.adwalker.ad.admin.common.controller.AbstractControllerParent;
import cn.adwalker.ad.admin.config.service.IConfigEScoreService;
import cn.adwalker.core.pool.SpringDatePool;
import cn.adwalker.core.util.CacheUtils;
import cn.adwalker.core.util.JacksonMapper;
import cn.adwalker.core.util.OutputHelper;
import cn.adwalker.core.vo.ResultSuccessVo;
import cn.adwalker.core.vo.ResultVo;
import cn.adwalker.model.config.domain.ConfigFinance;
import cn.adwalker.model.config.domain.ConfigPushDelay;
import cn.adwalker.model.config.domain.ConfigQuickLyTaskFinance;

/**
 * @author wjp
 * 财务设置
 */
@Controller
public class ConfigFinanceController extends AbstractControllerParent{

	/** 日志记录器 */
	Logger logger = Logger.getLogger(ConfigFinanceController.class);
	
	/** 平台设置相关服务 */
	@Resource
	private IConfigEScoreService configEScoreService; 
	
	/**  */
	@Resource
	private SpringDatePool springDatePool;

	/**
	* <p>Title: refreshFinanceConfig</p>
	* <p>Description:刷新财务接口</p>
	* @param response
	* @return
	* @throws IOException
	* @author lichuang
	* @date 2013-5-30
	* @return ModelAndView
	* @version 1.0
	 */
	@RequestMapping("/refreshConfig.do")
	public ModelAndView refreshFinanceConfig(HttpServletResponse response) throws IOException {
		ConfigFinance from = springDatePool.getFinanceConfig();
		logger.info("修改之前的对象是：" + from.toString());
		springDatePool.initFinanceConfig();
		from = springDatePool.getFinanceConfig();
		logger.info("修改之后的对象是：" + from.toString());
		PrintWriter pw = response.getWriter();
		pw.write("0");
		logger.info("刷新业务财务报表成功！");
		pw.close();
		return null;
	}

	/**
	 * 获取财务设置信息
	 * @param request
	 * @return
	 */
	@RequestMapping("/manage!configFinance.do")
	public String getConfigFinance(HttpServletRequest request){
	
		try {
			ConfigFinance lineData = this.configEScoreService.getFinanceConfig(1);//状态1：线上数据，2:修改数据
			ConfigFinance modifyData = this.configEScoreService.getFinanceConfig(1);//状态1：线上数据，2:修改数据
			ConfigQuickLyTaskFinance quickLyTask = this.configEScoreService.getQuickLyTask(1);//状态1：线上数据，2:修改数据
			
			ConfigPushDelay configPushDelay = this.configEScoreService.getPushDelayConfigTax();
			if (configPushDelay.getConfigValue().equals("null") || configPushDelay.getConfigValue() == null) {
				configPushDelay.setConfigValue("");
			}
			
			request.setAttribute("lineData", lineData);
			request.setAttribute("modifyData", modifyData);
			request.setAttribute("quickLy", quickLyTask);
			request.setAttribute("configPushDelay", configPushDelay);
			return "platform/ope_finance";
		} catch (Exception e) {
			logger.debug("获取平台财务设置信息异常！");
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	* <p>Title: updateConfigFinanceTaxrate</p>
	* <p>Description:修改财务税率信息</p>
	* @param request
	* @return
	* @author lichuang
	* @date 2013-5-30
	* @return String
	* @version 1.0
	 */
	@RequestMapping("/manage!updateFinanceTaxrate.do")
	public String updateConfigFinanceTaxrate(HttpServletRequest request,HttpServletResponse response){
		
		try {
			String s = ServletRequestUtils.getStringParameter(request,"financeTax");
			Double d = Double.parseDouble(s);
			String financeTax = String.valueOf(d);
			ConfigPushDelay configPushDelay = new ConfigPushDelay("FINANCETAX", financeTax);
			//记录日志
				
			this.configEScoreService.updatePushDelayTax(configPushDelay);
			
//			Double tax = userDeveloperService.getTax(300d);
			ResultVo vo = new ResultSuccessVo();
			OutputHelper.outPut(response, JacksonMapper.objectToJsonString(vo));
		
		} catch (Exception e) {
			logger.debug("修改平台设置信息异常");
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	* <p>Title: updateConfigFinance</p>
	* <p>Description:修改财务设置信息</p>
	* @param request
	* @param moneyScoreRate
	* @return
	* @author cuidd
	* @date 2014年10月31日
	* @return String
	* @version 1.0
	 */
	@RequestMapping("/manage!updateFinanceData.do")
	public String updateConfigFinance(HttpServletRequest request,Integer moneyScoreRate){
		try {
			ConfigFinance configFinance = new ConfigFinance();
			configFinance.setId(0l);
			configFinance.setMoneyScoreRate(moneyScoreRate);
			this.configEScoreService.updateById(configFinance);
			this.springDatePool.initFinanceConfig();
			CacheUtils.updateConfigCache();
		} catch (Exception e) {
			logger.debug("修改平台设置信息异常");
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	* <p>Title: updateQuickLyTaskEScore</p>
	* <p>Description:修改快速任务配置</p>
	* @param request
	* @param quickLy_task
	* @return
	* @author cuidd
	* @date 2014年10月31日
	* @return String
	* @version 1.0
	 */
	@RequestMapping("/manage!updateQuickLyTaskEScore.do")
	public String updateQuickLyTaskEScore(HttpServletRequest request,Integer quickLy_task,HttpServletResponse response){
		try {
			ConfigQuickLyTaskFinance qt = new ConfigQuickLyTaskFinance();
			qt.setId(1l);
			qt.setQuickly_task(quickLy_task);
			this.configEScoreService.updateQuickLyTask(qt);
			this.springDatePool.initFinanceConfig();
			CacheUtils.updateConfigCache();
			ResultVo vo = new ResultSuccessVo();
			OutputHelper.outPut(response, JacksonMapper.objectToJsonString(vo));
			
		} catch (Exception e) {
			logger.debug("修改平台设置信息异常");
			e.printStackTrace();
		}
		return null;
	}
}
