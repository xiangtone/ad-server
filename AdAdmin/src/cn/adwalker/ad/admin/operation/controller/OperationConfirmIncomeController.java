package cn.adwalker.ad.admin.operation.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.Sheet;
import jxl.Workbook;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import cn.adwalker.ad.admin.common.controller.AbstractControllerParent;
import cn.adwalker.ad.admin.common.vo.SysUserVo;
import cn.adwalker.ad.admin.operation.bean.IncomeRevenueBean;
import cn.adwalker.ad.admin.operation.form.AdIosEntering;
import cn.adwalker.ad.admin.operation.service.IOperationConfirmIncome;
import cn.adwalker.ad.admin.operation.vo.IncomeConfirmVo;
import cn.adwalker.ad.config.AppConstant;
import cn.adwalker.core.exception.SysException;
import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.page.SetPage;
import cn.adwalker.core.util.ConfigUtil;
import cn.adwalker.core.util.HttpClientUtil;
import cn.adwalker.core.util.ImpExcelUtil;
import cn.adwalker.core.util.JacksonMapper;
import cn.adwalker.core.util.OutputHelper;
import cn.adwalker.core.vo.ResultErrorVo;
import cn.adwalker.core.vo.ResultSuccessVo;
import cn.adwalker.core.vo.ResultVo;

/**
 * <p>
 * Title: OperationAdEffectController
 * </p>
 * <p>
 * Description:确认收入
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-5-9
 */
@Controller
public class OperationConfirmIncomeController extends AbstractControllerParent {

	/** 日志记录器 */
	Logger logger = Logger.getLogger(OperationConfirmIncomeController.class);

	/** 广告效果确认相关服务 */
	@Resource
	private IOperationConfirmIncome operationConfirmIncome;
	
	/**
	 * <p>
	 * Title: listAdEffectData
	 * </p>
	 * <p>
	 * Description:收入确认List
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @param bean
	 * @return
	 * @author lichuang
	 * @date 2013-5-8
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!listAdEffectData.do")
	public String listAdEffectData(HttpServletRequest request,
			HttpServletResponse response, IncomeRevenueBean bean) {
		List<IncomeConfirmVo> list = null;
		IPageInfo pageInfo = new SetPage(request);
		try {
			list = this.operationConfirmIncome.findByPage(bean, pageInfo);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("bean", bean);
			request.setAttribute("entiy", list);
			request.setAttribute("pageRecord", pageInfo.getTotalRow());
			request.setAttribute("page", pageInfo.getPageSize());

		} catch (Exception e) {
			logger.debug("获取广告效果列表异常！");
			e.printStackTrace();
		}
		return "manage/operation/ope_cw_enter";
	}
	
	/**
	 * <p>
	 * Title: adverEffectBatchDealStatus
	 * </p>
	 * <p>
	 * Description:批量审核处理步骤： 1、发布，
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @param checkbox
	 * @param status
	 * @return
	 * @author lichuang
	 * @date 2013-5-9
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!aEBatchDealStatus.do")
	public String adverEffectBatchDealStatus(HttpServletRequest request,
			HttpServletResponse response, String [] campaign_date) {
		// 当前登录用户
		SysUserVo curManageUser = (SysUserVo) request.getSession()
				.getAttribute("manageUser");
		
		String [] s_date= new String[campaign_date.length];
		Long[] ad_id= new Long[campaign_date.length];
		Integer[] ch_plf_type=new Integer[campaign_date.length]; 
		for (int i = 0; i < campaign_date.length; i++) {
			String [] arr=campaign_date[i].split("\\|");
			ad_id[i]=Long.valueOf(arr[0]);
			s_date[i]=arr[1];
			ch_plf_type[i]=Integer.valueOf(arr[2]);
		}
		try {
			this.operationConfirmIncome.tranAudit( s_date,ad_id,ch_plf_type,curManageUser);// 处理审核
		} catch (Exception e) {
			logger.debug("广确认收入批量审核处理异常！");
			e.printStackTrace();
			try {
			} catch (Exception e2) {
			}
		}
		return null;
	}
	
	/**
	* <p>Title: impAdEffectIosData</p>
	* <p>Description:录入IOS渠道数据</p>
	* @param file
	* @param request
	* @param response
	* @return
	* @author lichuang
	* @date 2013-6-1
	* @return String
	* @version 1.0
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/manage!impAdEffectIosData.do")
	public String impAdEffectIosData(@RequestParam MultipartFile file_ios,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		// 当前登录用户
		SysUserVo currUser = (SysUserVo) request.getSession().getAttribute(
				"manageUser");
		ResultVo resultVo = null;
		// 读取表格中的数据
		Workbook workbook = ImpExcelUtil.getWorkbook(file_ios);
		try {
			Sheet sheet = workbook.getSheet(0);
			// 文件内容为空
			if (sheet.getRows() == 1) {
				resultVo = new ResultErrorVo("录入失败！！");
			}
			// 表格中的数据验证
			HashMap<String, Object> map = this.operationConfirmIncome
					.checkDataIos(sheet,currUser);
			String msg = (String) map.get("error");
			if (msg != null) {
				resultVo = new ResultErrorVo("录入失败！！");
			}
			List<AdIosEntering> list = (List<AdIosEntering>) map.get("list");
			this.operationConfirmIncome.insertAdEffectIosData(list);
			resultVo = new ResultSuccessVo();

		} catch (SysException e1) {
			resultVo = new ResultErrorVo("录入失败！！");
		} catch (Exception e) {

			e.printStackTrace();
			logger.debug("excel文件格式不对！", e);
		} finally {
			ImpExcelUtil.close(workbook);// 关闭
		}
		OutputHelper.outPut(response,
				JacksonMapper.objectToJsonString(resultVo));
		return null;
	}
	/**
	* <p>Title: iosBelowList</p>
	* <p>Description:ios线下数据录入List</p>
	* @param request
	* @param response
	* @param bean
	* @return
	* @author lichuang
	* @date 2013-11-20
	* @return String
	* @version 1.0
	 */
	@RequestMapping("/manage!iosBelowListData.do")
	public String iosBelowList(HttpServletRequest request,
			HttpServletResponse response, IncomeRevenueBean bean) {
		List<IncomeConfirmVo> list = null;
		IPageInfo pageInfo = new SetPage(request);
		try {
			list = this.operationConfirmIncome.findByPage(bean, pageInfo);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("bean", bean);
			request.setAttribute("entiy", list);
			request.setAttribute("pageRecord", pageInfo.getTotalRow());
			request.setAttribute("page", pageInfo.getPageSize());

		} catch (Exception e) {
			logger.debug("获取广告效果列表异常！");
			e.printStackTrace();
		}
		return "manage/operation/ope_ios_below_list";
	}
	
	/**
	* <p>Title: EnteringDate</p>
	* <p>Description:设备标识导入</p>
	* @param session
	* @param model
	* @return
	* @throws Exception
	* @author lichuang
	* @date 2013-11-20
	* @return String
	* @version 1.0
	 */
	@RequestMapping("/manage!iosbelowEnteringDate.do")
	public String EnteringDate(HttpSession session,
			Map<String, Object> model) throws Exception {
		return "manage/operation/campaign_entering";
	}
	/**
	 * <p>
	 * Title: devIncomeStatus
	 * </p>
	 * <p>
	 * Description:单个激活
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @param type
	 * @return
	 * @author lichuang
	 * @date 2013-6-17
	 * @return String
	 * @version 1.0
	 * @throws IOException
	 */
	@RequestMapping("/manage!iosbelowEnterStatus.do")
	public String iosbelowEnterStatus(HttpServletRequest request,
			HttpServletResponse response, Long id, Integer type)
			throws IOException {

		ResultVo vo = null;
		SysUserVo manageUser = (SysUserVo) request.getSession().getAttribute(
				"manageUser");
		try {
			Long[] ids = { id };
			this.operationConfirmIncome.tranAudit(ids, manageUser);// 处理审核
			String url = ConfigUtil.getString(AppConstant.IOS_BELOW);
			HttpClientUtil.httpRequestPost(url, "status=2");
			vo = new ResultSuccessVo();
		} catch (Exception e) {
			logger.debug("广告ios线下单个激活处理异常！" + e.getLocalizedMessage());
			vo = new ResultErrorVo("广告ios线下单个激活处理异常！！");
		}
		OutputHelper.outPut(response, JacksonMapper.objectToJsonString(vo));
		return null;
	}
	/**
	* <p>Title: keyBalanceDevIncome</p>
	* <p>Description:一键激活</p>
	* @param session
	* @param request
	* @param bean
	* @return
	* @throws Exception
	* @author lichuang
	* @date 2013-11-21
	* @return String
	* @version 1.0
	 */
	@RequestMapping("/manage!keyIosbelowStatus.do")
	public void keyBalanceDevIncome(HttpSession session,
			HttpServletRequest request, IncomeRevenueBean bean)
			throws Exception {
		SysUserVo manageUser = (SysUserVo) request.getSession().getAttribute(
				"manageUser");
		List<IncomeConfirmVo> iosList = null;
		try {
			if (bean != null && bean.getStatus() == 0) {
				iosList = this.operationConfirmIncome.findAll(bean);
				Long[] ids = new Long[iosList.size()];

				for (int i = 0; i < iosList.size(); i++) {
					IncomeConfirmVo vo = iosList.get(i);
					ids[i] = vo.getId();
				}
				operationConfirmIncome.tranAudit(ids, manageUser);
				String url = ConfigUtil.getString(AppConstant.IOS_BELOW);
				HttpClientUtil.httpRequestPost(url, "status=2");
			}
			request.setAttribute("bean", bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
