package cn.adwalker.ad.web.developer.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import cn.adwalker.ad.config.AppConstant;
import cn.adwalker.ad.config.Role;
import cn.adwalker.ad.core.page.IPageInfo;
import cn.adwalker.ad.core.page.SetPage;
import cn.adwalker.ad.log.service.AdwalkerLogger;
import cn.adwalker.ad.model.common.domain.BankInfo;
import cn.adwalker.ad.model.common.domain.EJumpType;
import cn.adwalker.ad.model.common.domain.ProvinceCitySort;
import cn.adwalker.ad.model.developer.domain.DevInfo;
import cn.adwalker.ad.model.developer.domain.Developer;
import cn.adwalker.ad.upload.util.JacksonMapper;
import cn.adwalker.ad.util.DateUtil;
import cn.adwalker.ad.util.FileUploadTool;
import cn.adwalker.ad.util.MD5;
import cn.adwalker.ad.util.ObjectUtils;
import cn.adwalker.ad.util.OutputHelper;
import cn.adwalker.ad.util.page.PageUtil;
import cn.adwalker.ad.web.common.controller.AbstractControllerParent;
import cn.adwalker.ad.web.common.vo.ResultErrorVo;
import cn.adwalker.ad.web.common.vo.ResultSuccessVo;
import cn.adwalker.ad.web.common.vo.ResultVo;
import cn.adwalker.ad.web.developer.bean.DevImcomeQueryBean;
import cn.adwalker.ad.web.developer.form.DevBankInfoFrom;
import cn.adwalker.ad.web.developer.form.DevPassword;
import cn.adwalker.ad.web.developer.form.UpdateDevInfoForm;
import cn.adwalker.ad.web.developer.service.DeveloperReportService;
import cn.adwalker.ad.web.developer.service.DeveloperApplyMoneyService;
import cn.adwalker.ad.web.developer.service.DeveloperService;
import cn.adwalker.ad.web.developer.vo.DevAccountInfoVo;
import cn.adwalker.ad.web.developer.vo.DevIncome;
import cn.adwalker.ad.web.developer.vo.DevIncomeListVo;
import cn.adwalker.ad.web.developer.vo.DevIteriorMailVo;

/**
 * 开发者用户账户控制层
 */

@Controller
public class DeveloperController extends AbstractControllerParent {

	private static final Logger log = LoggerFactory.getLogger(DeveloperController.class);

	@Resource
	private DeveloperService developerService;
	
	@Resource
	private DeveloperApplyMoneyService developerApplyMoneyService;
	
	@Resource
	private DeveloperReportService developerReportService;
	
	/**
	 * 开发者登录
	 * 
	 * @param email
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/developerLogin.action")
	public ModelAndView developerLogin(HttpSession session) throws Exception {
		log.debug("into method devlogin: 开发者登录");
		String email = (String) session.getAttribute(("user_email"));
		if (!StringUtils.isEmpty(email)) {
			Developer userDeveloper = developerService.exists(email);
			super.setUserDeveloper(userDeveloper);
			return new ModelAndView(new RedirectView("developerIndex.action"));
		}
		return new ModelAndView(new RedirectView("login.action"));
	}

	/**
	 * 开发者登录
	 * 
	 * @param email
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("developerIndex.action")
	public String developerIndex(HttpServletRequest request, String email, Map<String, Object> model, DevImcomeQueryBean bean) throws Exception {
		if (bean == null) {
			bean = new DevImcomeQueryBean();
		}
		if (bean.getDate() == null) {
			bean.setDate(new Date());
		}
		try {
			IPageInfo pageInfo = new SetPage(request);
			DevIncome rdadsr = this.developerReportService.findDevIncome(super.getUserDeveloper().getId(), bean.getDate());
			List<DevIncomeListVo> list = this.developerReportService.findDevIncome(super.getUserDeveloper().getId(), bean, pageInfo);
			request.setAttribute("endtime", DateUtil.getDateAddHour(-1));
			request.setAttribute("noReadCount", developerService.findNoReadCountByDevId(super.getUserDeveloper().getId()));
			request.setAttribute("income", rdadsr);
			request.setAttribute("list", list);
			request.setAttribute("executeContorller", "devIndexPage.action");
			DevAccountInfoVo vo = developerApplyMoneyService.findByIdInfo(super.getUserDeveloper().getId());

			model.put("incomeList", developerApplyMoneyService.queryIncomeByDate(DateUtil.getDateAddDay(-3), getUserDeveloper().getId()));
			model.put("smap", developerApplyMoneyService.queryAppCount(getUserDeveloper().getId()));
			model.put("bean", bean);
			model.put("pageInfo", PageUtil.fenye(pageInfo));
			model.put("vo", vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "developer/index";
	}

	@RequestMapping("/getNoReadCount.action")
	public String getNoReadCount(HttpServletResponse response) throws Exception {
		int noReadCount = developerService.findNoReadCountByDevId(super.getUserDeveloper().getId());
		OutputHelper.outPut(response, String.valueOf(noReadCount));
		return null;
	}

	/**
	 * to修改用户信息
	 * 
	 * @return
	 */
	@RequestMapping("/toUpdateInfo.action")
	public String toUpdateUser(Map<String, Object> model) throws Exception {
		log.debug("into method toUpdateUser: to开发者账户信息");
		try {
			Developer userDeveloper = developerService.getById(super.getUserDeveloper().getId());
			if (!ObjectUtils.isNotEmpty(userDeveloper.getMobilePhone())) {
				userDeveloper.setMobilePhone("");
			}
			if (!ObjectUtils.isNotEmpty(userDeveloper.getQq())) {
				userDeveloper.setQq("");
			}
			model.put("userDeveloper", userDeveloper);
			AdwalkerLogger.log(super.getUserDeveloper().getId(), Role.DEVELOPER, "/toUpdateUser.action", "userid:" + super.getUserDeveloper().getId(), "to修改开发者账户信息");
		} catch (Exception e) {
			e.printStackTrace();
			AdwalkerLogger.error(AppConstant.IS_LOGIN_FAIL, Role.DEVELOPER, "/toUpdateUser.action", AppConstant.IS_LOGIN_FAIL + "", e.getMessage());
		}
		return "developer/basic_info";
	}

	/**
	 * <p>
	 * Title: updateUser
	 * </p>
	 * <p>
	 * Description:执行修改用户信息
	 * </p>
	 * 
	 * @param model
	 * @param form
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/updateUser.action")
	public String updateUser(Map<String, Object> model, UpdateDevInfoForm form, HttpServletResponse response) throws Exception {
		log.debug("into method updateUser: 执行开发者账户修改");
		Long userId = null;
		try {
			AdwalkerLogger.log(super.getUserDeveloper().getId(), Role.DEVELOPER, "/updateUser.action", "userId:" + userId, "执行修改开发者账户信息");
			developerService.updateDeveloperInfo(super.getUserDeveloper().getId(), form);
			OutputHelper.outPut(response, "true");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("updateUser:" + e.getMessage());
			AdwalkerLogger.error(super.getUserDeveloper().getId(), Role.DEVELOPER, "/updateUser.action", "userId:" + userId, e.getMessage());
			OutputHelper.outPut(response, "false");
		}
		return null;
	}

	/**
	 * <p>
	 * Title: toUpdateFnancialInfo
	 * </p>
	 * <p>
	 * Description:to修改财务信息
	 * </p>
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/toUpdateFnancialInfo.action")
	public String toUpdateFnancialInfo(Map<String, Object> model) throws Exception {
		log.debug("into method toUpdateFnancialInfo: to编辑开发者财务信息");
		List<BankInfo> bankInfoList = null;
		List<ProvinceCitySort> provinceCitySort = null;
		int parent_id = 0;
		try {
			// 开发者财务信息
			bankInfoList = developerApplyMoneyService.findByUser(super.getUserDeveloper().getId());
			// 开发者城市
			provinceCitySort = developerApplyMoneyService.findprovinceCity(parent_id);
			if (ObjectUtils.isNotEmpty(bankInfoList)) {
				model.put("bankInfo", bankInfoList.get(0));
			}
			if (ObjectUtils.isNotEmpty(provinceCitySort)) {
				model.put("provinceCitySort", provinceCitySort);
			}
			model.put("dev", super.getUserDeveloper());
		} catch (Exception e) {
			e.printStackTrace();
		}
		AdwalkerLogger.log(super.getUserDeveloper().getId(), Role.DEVELOPER, "/toUpdateFnancialInfo.action", "userid:" + super.getUserDeveloper().getId(), "to编辑开发者财务信息");
		if (ObjectUtils.isNotEmpty(bankInfoList)) {
			return "developer/finance_info";
		}
		return "developer/finance_info";
	}

	/**
	 * <p>
	 * Title: updateFnancialInfo
	 * </p>
	 * <p>
	 * Description:执行修改或添加财务信息
	 * </p>
	 * 
	 * @param request
	 * @param model
	 * @param bankInfo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/updateFnancialInfo.action")
	public String updateFnancialInfo(HttpServletRequest request, HttpServletResponse response, Map<String, Object> model, DevBankInfoFrom bankInfo) throws Exception {
		ResultVo vo = null;
		try {
			bankInfo.setUserId(super.getUserDeveloper().getId());
			if (ObjectUtils.isNotEmpty(bankInfo.getId())) {
				developerApplyMoneyService.updateFnancialInfoMoney(bankInfo);
			} else {
				developerApplyMoneyService.insertFnancialInfoMoney(bankInfo);
			}
			AdwalkerLogger.log(super.getUserDeveloper().getId(), Role.DEVELOPER, "/updateUser.action", "userId:" + super.getUserDeveloper().getId(), "执行修改开发者账户信息");
			vo = new ResultSuccessVo();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("updateUser:" + e.getMessage());
			AdwalkerLogger.error(super.getUserDeveloper().getId(), Role.DEVELOPER, "/updateUser.action", "userId:" + super.getUserDeveloper().getId(), e.getMessage());
			vo = new ResultErrorVo();
		}
		OutputHelper.outPut(response, JacksonMapper.objectToJsonString(vo));
		return null;
	}

	/**
	 * <p>
	 * Title: uploadDevImg
	 * </p>
	 * <p>
	 * Description:图片上传
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/uploadDevImg.action")
	public String uploadDevImg(DefaultMultipartHttpServletRequest request, HttpServletResponse response) throws IOException {
		ResultVo vo = null;
		try {
			String url = FileUploadTool.upload(request, "card", new String[] { super.getUserDeveloper().getEmail(), FileUploadTool.getTimeStamp() }, FileUploadTool.IMG);
			if (!StringUtils.isEmpty(url)) {
				vo = new ResultSuccessVo(url);
			}
		} catch (Exception e) {
			vo = new ResultErrorVo();
			e.printStackTrace();
		}
		OutputHelper.outPut(response, JacksonMapper.objectToJsonString(vo));
		return null;
	}

	/**
	 * 
	 * <p>
	 * Title: updateFnancialInfoMoney
	 * </p>
	 * <p>
	 * Description: 提款的时候执行修改财务信息
	 * </p>
	 * 
	 * @param request
	 * @param model
	 * @param bankInfo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/updateFnancialInfoMoney.action")
	public String updateFnancialInfoMoney(DefaultMultipartHttpServletRequest request, Map<String, Object> model, DevBankInfoFrom bankInfo) throws Exception {
		log.debug("into method updateFnancialInfoMoney.action:提款的时候执行修改财务信息");
		bankInfo.setCity_id(Integer.valueOf(request.getParameter("platform")));
		try {
			bankInfo.setCardUrl(FileUploadTool.upload(request, "card", new String[] { super.getUserDeveloper().getEmail(), FileUploadTool.getTimeStamp() }, FileUploadTool.IMG));
			bankInfo.setUserId(super.getUserDeveloper().getId());
			if (ObjectUtils.isNotEmpty(bankInfo.getId())) {
				developerApplyMoneyService.updateFnancialInfoMoney(bankInfo);
			} else {
				developerApplyMoneyService.insertFnancialInfoMoney(bankInfo);
			}
			AdwalkerLogger.log(1001, Role.DEVELOPER, "/updateUser.action", "userId:" + super.getUserDeveloper().getId(), "提款的时候执行修改财务信息");
			return super.jumpPage(model, "修改成功！", "/toDeveloperApplyMoney.action?id=" + super.getUserDeveloper().getId(), EJumpType.SUCCESS.getValue());
		} catch (Exception e) {
			e.printStackTrace();
			log.error("updateUser:" + e.getMessage());
			AdwalkerLogger.error(1001, Role.DEVELOPER, "/updateUser.action", "userId:" + super.getUserDeveloper().getId(), e.getMessage());
			return super.jumpPage(model, "修改失败！", "/toUpdateFnancialInfo.action?id=" + super.getUserDeveloper().getId(), EJumpType.FAIL.getValue());
		}
	}

	/**
	 * <p>
	 * Title: toUpdateUserPass
	 * </p>
	 * <p>
	 * Description:to修改用户密码
	 * </p>
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/toUpdatePassword.action")
	public String toUpdateUserPass(Map<String, Object> model) throws Exception {
		Developer userDeveloper = null;
		try {
			userDeveloper = developerService.getById(super.getUserDeveloper().getId());
			model.put("userDeveloper", userDeveloper);
		} catch (Exception e) {
			e.printStackTrace();
		}
		AdwalkerLogger.log(super.getUserDeveloper().getId(), Role.DEVELOPER, "/toUpdateUserPass.action", "userid:" + super.getUserDeveloper().getId(), "修改开发者密码");
		return "developer/password";
	}

	/**
	 * <p>
	 * Title: passwordRatio
	 * </p>
	 * <p>
	 * Description:对比输入的就密码是否一致
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @param password
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/passwordRatio.action")
	public String passwordRatio(HttpServletRequest request, HttpServletResponse response, String oldpass) throws Exception {
		if (!new MD5().getMD5ofStr(oldpass.trim()).equals(this.getUserDeveloper().getPassword())) {
			OutputHelper.outPut(response, "false");
		} else {
			OutputHelper.outPut(response, "true");
		}
		return null;
	}

	/**
	 * <p>
	 * Title: updateUserPass
	 * </p>
	 * <p>
	 * Description:修改用户密码
	 * </p>
	 * 
	 * @param model
	 * @param developerVo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/updateUserPass.action")
	public String updateUserPass(Map<String, Object> model, HttpServletResponse response, DevPassword devPassword) throws Exception {
		if (!new MD5().getMD5ofStr(devPassword.getOldpass()).equals(this.getUserDeveloper().getPassword())) {
			AdwalkerLogger.error(super.getUserDeveloper().getId(), Role.DEVELOPER, "/updateUserPass.action", "userId:" + super.getUserDeveloper().getId(), "修改失败，您输入的旧密码错误！");
			return super.jumpPage(model, "修改失败，您输入的旧密码错误！", "/toUpdateUserPass.action?id=" + super.getUserDeveloper().getId(), EJumpType.FAIL.getValue());
		}
		try {
			Developer userDeveloper = super.getUserDeveloper();
			developerService.updateDeveloperPassword(new MD5().getMD5ofStr(devPassword.getPassword()), userDeveloper.getId());
			super.setUserDeveloper(userDeveloper);
			AdwalkerLogger.log(super.getUserDeveloper().getId(), Role.DEVELOPER, "/updateUserPass.action", "userId:" + super.getUserDeveloper().getId(), "执行修改开发者账户密码");
			OutputHelper.outPut(response, "true");
		} catch (Exception e) {
			e.printStackTrace();
			AdwalkerLogger.error(super.getUserDeveloper().getId(), Role.DEVELOPER, "/toUpdateUserPass.action?id=" + super.getUserDeveloper().getId(), "userId:" + super.getUserDeveloper().getId(), e.getMessage());
			OutputHelper.outPut(response, "false");
		}
		return null;
	}

	/**
	 * <p>
	 * Title: devbUpdateUser
	 * </p>
	 * <p>
	 * Description:补全开发者账号信息
	 * </p>
	 * 
	 * @param model
	 * @param userInfo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/devbUpdateUser.action")
	public String devbUpdateUser(Map<String, Object> model, DevInfo userInfo, HttpServletResponse response) throws Exception {
		Long userId = null;
		try {
			AdwalkerLogger.log(super.getUserDeveloper().getId(), Role.DEVELOPER, "/updateUser.action", "userId:" + userId, "执行修改开发者账户信息");
			userInfo.setId(super.getUserDeveloper().getId());
			developerService.updateUserInfo(userInfo);
			model.put("userInfo", userInfo);
			OutputHelper.outPut(response, "true");
		} catch (Exception e) {
			e.printStackTrace();
			AdwalkerLogger.error(super.getUserDeveloper().getId(), Role.DEVELOPER, "/updateUser.action", "userId:" + userId, e.getMessage());
			OutputHelper.outPut(response, "false");
		}
		return null;
	}

	/**
	 * <p>
	 * Title: toInteriorMailList
	 * </p>
	 * <p>
	 * Description:站内信列表
	 * </p>
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */

	@RequestMapping("/toInteriorMailAllList.action")
	public String toInteriorMailAllList(Map<String, Object> model, HttpServletRequest request,Integer interior_status) throws IOException, Exception {
		Long dev_id = super.getUserDeveloper().getId();
		DevIteriorMailVo dVo = null;
		Long count_hava = 0l;
		Long count_not = 0l;
		try {

			IPageInfo pageInfo = new SetPage(request);
			List<DevIteriorMailVo> list = developerService.findByDevUserId(dev_id, interior_status,pageInfo);
			List<DevIteriorMailVo> vo = developerService.findById(dev_id);
			for (int i = 0; i < vo.size(); i++) {
				dVo = vo.get(i);
				if (dVo.getStatus() == 0) {
					count_not = dVo.getCount();
				} else {
					count_hava = dVo.getCount();
				}
			}
			model.put("pageInfo", PageUtil.fenyeNew(pageInfo));
			model.put("count_hava", count_hava + count_not);
			model.put("count_not", count_not);
			model.put("list", list);
			model.put("email", super.getUserDeveloper().getEmail());
		} catch (Exception e) {
			e.printStackTrace();
			log.error("toDevApplyMoney:" + e.getMessage());
		}
		return "developer/letter_interior_all";
	}
	
	
	@RequestMapping("/toInteriorMailNotList.action")
	public String toInteriorMailNotList(Map<String, Object> model, HttpServletRequest request,Integer interior_status) throws IOException, Exception {
		Long dev_id = super.getUserDeveloper().getId();
		DevIteriorMailVo dVo = null;
		Long count_hava = 0l;
		Long count_not = 0l;
		try {

			IPageInfo pageInfo = new SetPage(request);
			List<DevIteriorMailVo> list = developerService.findByDevUserId(dev_id, interior_status,pageInfo);
			List<DevIteriorMailVo> vo = developerService.findById(dev_id);
			for (int i = 0; i < vo.size(); i++) {
				dVo = vo.get(i);
				if (dVo.getStatus() == 0) {
					count_not = dVo.getCount();
				} else {
					count_hava = dVo.getCount();
				}
			}
			model.put("pageInfo", PageUtil.fenyeNew(pageInfo));
			model.put("count_hava", count_hava + count_not);
			model.put("count_not", count_not);
			model.put("list", list);
			model.put("email", super.getUserDeveloper().getEmail());
		} catch (Exception e) {
			e.printStackTrace();
			log.error("toDevApplyMoney:" + e.getMessage());
		}
		return "developer/letter_interior_not";
	}
	

	/**
	 * <p>
	 * Title: deleteInteriorMail
	 * </p>
	 * <p>
	 * Description:删除站内信
	 * </p>
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/deleteInteriorMail.action")
	public String deleteInteriorMail(HttpServletRequest request, HttpServletResponse response, Long id) throws Exception {
		try {
			Integer con = developerService.deleteById(id);
			if (con != 1) {
				OutputHelper.outPut(response, "false");
			} else {
				OutputHelper.outPut(response, "true");
			}
		} catch (Exception e) {
			e.printStackTrace();
			OutputHelper.outPut(response, "false");
		}
		return null;
	}

	/**
	 * <p>
	 * Title: updatemInteriorMail
	 * </p>
	 * <p>
	 * Description:刷新以阅读
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/updatemInteriorMail.action")
	public String updatemInteriorMail(HttpServletRequest request, HttpServletResponse response, Long id) throws Exception {
		try {
			Integer con = developerService.updateById(id);
			if (con != 1) {
				OutputHelper.outPut(response, "false");
			} else {
				OutputHelper.outPut(response, "true");
			}
		} catch (Exception e) {
			e.printStackTrace();
			OutputHelper.outPut(response, "false");
		}
		return null;
	}
}
