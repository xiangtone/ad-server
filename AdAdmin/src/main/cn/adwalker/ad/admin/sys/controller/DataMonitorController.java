package cn.adwalker.ad.admin.sys.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.page.SetPage;
import cn.adwalker.core.util.JacksonMapper;
import cn.adwalker.core.util.OutputHelper;
import cn.adwalker.core.vo.ResultSuccessVo;
import cn.adwalker.core.vo.ResultVo;
import cn.adwalker.ad.admin.common.controller.AbstractControllerParent;
import cn.adwalker.ad.admin.sys.bean.DataMonitorBean;
import cn.adwalker.ad.admin.sys.service.IDataMonitorService;
import cn.adwalker.ad.admin.sys.vo.DataMonitorVo;

/**
* <p>Title: DataMonitorController</p>
* <p>Description:TODO</p> 
* <p>Company: adwalker</p>  
* @author   lichuang
* @date       2013-8-22
 */
@Controller
public class DataMonitorController extends AbstractControllerParent {

	/** 日志记录器 */
	Logger logger = Logger.getLogger(DataMonitorController.class);

	/** 資源管理 */
	@Resource
	private IDataMonitorService dataMonitorService;

	/**
	 * 
	 * <p>
	 * Title: listResourceManage
	 * </p>
	 * <p>
	 * Description:定时数据监控
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @param bean
	 * @return
	 * @author lichuang
	 * @date 2013-3-15
	 * @return String
	 * @version 1.0
	 * @throws
	 */
	@RequestMapping("/manage!dataMonitorList.do")
	public String list(HttpServletRequest request,
			HttpServletResponse response, DataMonitorBean bean) {
		List<DataMonitorVo> list = null;
		IPageInfo pageInfo = new SetPage(request);
		try {
			list = this.dataMonitorService.findByPage(
					bean, pageInfo);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("list", list);
			request.setAttribute("bean", bean);
			request.setAttribute("page", pageInfo.getPageSize());
		} catch (Exception e) {
			logger.error("获取广告效果列表异常！");
			e.printStackTrace();
		}
		return "sys/log/timing_data_list";
	}
	
	/**
	* <p>Title: update</p>
	* <p>Description:TODO</p>
	* @param session
	* @param form
	* @param model
	* @param response
	* @param id
	* @return
	* @throws Exception
	* @author lichuang
	* @date 2013-8-22
	* @return String
	* @version 1.0
	 */
	@RequestMapping("manage!dataMonitorDel.do")
	public String update(HttpSession session,
			Map<String, Object> model, HttpServletResponse response, Long id)
			throws Exception {
		ResultVo vo = null;
		dataMonitorService.update(id);
		vo = new ResultSuccessVo();
		OutputHelper.outPut(response, JacksonMapper.objectToJsonString(vo));
		return null;
	}
}