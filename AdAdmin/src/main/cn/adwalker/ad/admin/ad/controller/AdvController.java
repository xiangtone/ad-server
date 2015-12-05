package cn.adwalker.ad.admin.ad.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.adwalker.ad.admin.ad.bean.AdvSerach;
import cn.adwalker.ad.admin.ad.form.AdvAddForm;
import cn.adwalker.ad.admin.ad.form.AdvBankInfoform;
import cn.adwalker.ad.admin.ad.form.AdvForm;
import cn.adwalker.ad.admin.ad.service.IAdvService;
import cn.adwalker.ad.admin.ad.vo.AdvInfoVo;
import cn.adwalker.ad.admin.ad.vo.AdvertiserVo;
import cn.adwalker.ad.admin.common.controller.AbstractControllerParent;
import cn.adwalker.ad.admin.common.vo.SysUserVo;
import cn.adwalker.ad.config.Constant;
import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.page.SetPage;
import cn.adwalker.core.util.JacksonMapper;
import cn.adwalker.core.util.OutputHelper;
import cn.adwalker.core.vo.ResultErrorVo;
import cn.adwalker.core.vo.ResultSuccessVo;
import cn.adwalker.core.vo.ResultVo;

/**
 * 
 * <p>
 * Title: AdvController
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-4-2
 */
@Controller
public class AdvController extends AbstractControllerParent {
	@Resource
	private IAdvService advService;

	/** 依赖注入广告主服务层 */
	@Resource
	private IAdvService advertiserService;
	/** 日志记录器 */
	Logger logger = Logger.getLogger(AdvController.class);

	/**
	 * 
	 * <p>
	 * Title: addAdv
	 * </p>
	 * <p>
	 * Description:广告主添加页面跳转
	 * </p>
	 * 
	 * @param session
	 * @param model
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-5-30
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!addAdv.do")
	public String addAdv(HttpSession session, Map<String, Object> model)
			throws Exception {
		logger.debug("into method addResourceManage: 管理员添加广告主");
		manageUser = (SysUserVo) session.getAttribute("manageUser");
		return "ad/adv/adv_add";
	}

	/**
	 * 广告主后台注册
	 * <p>
	 * Title: regist
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param request
	 * @param model
	 * @param session
	 * @param code
	 * @param registadvvo
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-3-20
	 * @return String
	 * @version 1.0
	 * @throws
	 */
	@RequestMapping("/manage!saveAdv.do")
	public String saveAdv(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> model,
			HttpSession session, String code, AdvAddForm form) throws Exception {
		ResultVo vo = null;
		manageUser = (SysUserVo) session.getAttribute("manageUser");
		if (advService.exists(form.getEmail())) {
			vo = new ResultErrorVo("账号已存在");
		} else {
			try {
				advService.save(form, manageUser);
				vo = new ResultSuccessVo();
			} catch (Exception e) {
				e.printStackTrace();
				vo = new ResultErrorVo("系统错误");
			}
		}
		OutputHelper.outPut(response, JacksonMapper.objectToJsonString(vo));
		return null;
	}

	/**
	 * <p>
	 * Title: findAll
	 * </p>
	 * <p>
	 * Description:查找所有广告主列表
	 * </p>
	 * 
	 * @param session
	 * @param request
	 * @param response
	 * @param bean
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-4-15
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!advList.do")
	public String findAll(HttpSession session, HttpServletRequest request,
			HttpServletResponse response, AdvSerach bean) throws Exception {
		manageUser = (SysUserVo) session.getAttribute("manageUser");
		List<AdvertiserVo> list = null;
		IPageInfo pageInfo = new SetPage(request);
		list = this.advertiserService.findBylist(bean, pageInfo, manageUser);
		request.setAttribute("allUserAdvertiser", list);
		request.setAttribute("bean", bean);
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("pageRecord", pageInfo.getTotalRow());
		request.setAttribute("page", pageInfo.getPageSize());
		return "ad/adv/adv_list";

	}

	/**
	 * 查看信息
	 * <p>
	 * Title: findAdv
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @param advId
	 * @return
	 * @author lichuang
	 * @date 2013-4-2
	 * @return String
	 * @version 1.0
	 * @throws
	 */
	@RequestMapping("/manage!findAdv.do")
	public String findAdv(HttpServletRequest request,
			HttpServletResponse response, Long advId) {
		AdvInfoVo advInfoVo = null;

		try {
			advInfoVo = this.advService.getAdv(advId);
			request.setAttribute("findAdvVo", advInfoVo);
		} catch (Exception e) {
			logger.debug("获取广告效果列表异常！");
			e.printStackTrace();
		}
		return "ad/adv/adv_info";
	}

	/**
	 * 
	 * <p>
	 * Title: findUpdateAdv
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @param advId
	 * @return
	 * @author lichuang
	 * @date 2013-4-2
	 * @return String
	 * @version 1.0
	 * @throws
	 */
	@RequestMapping("/manage!editAdv.do")
	public String editAdv(HttpServletRequest request,
			HttpServletResponse response, Long advId) {
		AdvInfoVo advInfoVo = null;

		try {
			advInfoVo = this.advService.getAdv(advId);
			request.setAttribute("findAdvVo", advInfoVo);
			request.setAttribute("advId", advId);
		} catch (Exception e) {
			logger.debug("获取广告效果列表异常！");
			e.printStackTrace();
		}
		return "ad/adv/adv_edit";
	}

	/**
	 * <p>
	 * Title: updateAdv
	 * </p>
	 * <p>
	 * Description:更新广告主信息
	 * </p>
	 * 
	 * @param request
	 * @param model
	 * @param session
	 * @param code
	 * @param registadvvo
	 * @param advSalesmanVo
	 * @param advBankInfoVo
	 * @param advId
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-4-17
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!updateAdv.do")
	public String updateAdv(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> model,
			HttpSession session, String code, AdvForm advForm, AdvBankInfoform advBankInfo)
			throws Exception {
		ResultVo vo = null;
		try {
			advService.updateAdvService(advForm, advBankInfo);
			vo = new ResultSuccessVo();
		} catch (Exception e) {
			e.printStackTrace();
			vo = new ResultErrorVo("系统错误");
		}
		OutputHelper.outPut(response, JacksonMapper.objectToJsonString(vo));
		return null;

	}

	/**
	 * <p>
	 * Title: activationAdver
	 * </p>
	 * <p>
	 * Description:激活账户 1：激活
	 * </p>
	 * 
	 * @param request
	 * @return
	 * @author lichuang
	 * @date 2013-4-15
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!activationAdver.do")
	public String activationAdver(Long adverId, HttpServletResponse response) {
		try {
			this.advertiserService.updateStatus(adverId,Constant.BLOCK_USE);// 帐号激活
			ResultVo vo = new ResultSuccessVo();
			OutputHelper.outPut(response, JacksonMapper.objectToJsonString(vo));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
