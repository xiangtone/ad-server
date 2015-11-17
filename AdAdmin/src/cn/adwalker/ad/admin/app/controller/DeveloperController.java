package cn.adwalker.ad.admin.app.controller;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.adwalker.ad.admin.app.bean.DevQuery;
import cn.adwalker.ad.admin.app.service.IApplicationService;
import cn.adwalker.ad.admin.app.service.IDevApplyMoneyService;
import cn.adwalker.ad.admin.app.service.IDeveloperService;
import cn.adwalker.ad.admin.app.vo.DeveloperDetailVo;
import cn.adwalker.ad.admin.app.vo.DeveloperVo;
import cn.adwalker.ad.admin.app.vo.VApplication;
import cn.adwalker.ad.admin.common.controller.AbstractControllerParent;
import cn.adwalker.ad.admin.common.vo.SysUserVo;
import cn.adwalker.ad.admin.report.vo.ReportDevAppDayStatManageVo;
import cn.adwalker.ad.config.AppConstant;
import cn.adwalker.ad.config.Constant;
import cn.adwalker.ad.config.StatusConstant;
import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.page.SetPage;
import cn.adwalker.core.util.BeanUtils;
import cn.adwalker.core.util.ConfigUtil;
import cn.adwalker.core.util.DateUtil;
import cn.adwalker.core.util.ExportUtils;
import cn.adwalker.core.util.FileUploadTool;
import cn.adwalker.core.util.JacksonMapper;
import cn.adwalker.core.util.OutputHelper;
import cn.adwalker.core.util.TimeUtil;
import cn.adwalker.core.util.lang.ObjectUtils;
import cn.adwalker.core.vo.ResultErrorVo;
import cn.adwalker.core.vo.ResultSuccessVo;
import cn.adwalker.core.vo.ResultVo;
import cn.adwalker.model.app.domain.Application;
import cn.adwalker.model.app.domain.Developer;
import cn.adwalker.model.common.dao.IBankInfoDao;
import cn.adwalker.model.common.dao.IProvinceCitySortDao;
import cn.adwalker.model.common.domain.BankInfo;
import cn.adwalker.model.common.domain.ProvinceCitySort;

/**
 * 功能描述：<br>
 * 开发者管理员后台控制层
 * 
 * @author guoyongxiang
 */
@Controller(value = "developerController")
public class DeveloperController extends AbstractControllerParent {

	/**
	 * 日志
	 */
	private static final Logger logger = LoggerFactory.getLogger(DeveloperController.class);

	@Resource
	private IApplicationService applicationService;

	/** 开发者申请提现服务 */
	@Resource
	private IDevApplyMoneyService devApplyMoneyService;

	@Resource
	private IDeveloperService developerService;

	@Resource
	private IBankInfoDao bankInfoDao;

	@Resource
	private IProvinceCitySortDao provinceCitySortDao;

	/**
	 * 
	 * <p>
	 * Title: devList
	 * </p>
	 * <p>
	 * Description:开发者列表
	 * </p>
	 * 
	 * @param session
	 * @param request
	 * @param bean
	 * @return
	 * @author cuidd
	 * @date 2013-6-27
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("manage!devList.do")
	public String devList(HttpSession session, HttpServletRequest request, Map<String, Object> model, DevQuery bean) {
		try {
			// 查询用户应用数量
			Map<Long, Integer> map = new HashMap<Long, Integer>();
			List<DeveloperVo> appCountList = developerService.getAppCount();
			for (DeveloperVo appCount : appCountList) {
				map.put(appCount.getDev_id(), appCount.getAppCount());
			}

			// 返回的list
			IPageInfo pageInfo = new SetPage(request);
			List<Developer> devList = (List<Developer>) this.developerService.findByPage(bean, pageInfo);
			if (devList != null && devList.size() > 0) {
				for (Developer dev : devList) {
					Long devId = dev.getId();
					Integer count = map.get(devId);
					if (count == null) {
						dev.setAppCount(0);
					} else {
						dev.setAppCount(count);
					}
				}
			}

			model.put("bean", bean);
			model.put("devCount", pageInfo.getTotalRow());
			model.put("pageInfo", pageInfo);
			model.put("devList", devList);
		} catch (Exception e) {
			logger.error(e.toString());
		}

		return "media/dev/dev_list";
	}

	/**
	 * 
	 * <p>
	 * Title: appByDevListManager
	 * </p>
	 * <p>
	 * Description:单用户应用列表（开发者概况）
	 * </p>
	 * 
	 * @param session
	 * @param request
	 * @param dev_id
	 * @return
	 * @author cuidd
	 * @date 2013-3-29
	 * @return String
	 * @version 1.0
	 * @throws
	 */
	@RequestMapping("manage!editDev.do")
	public String appByDevListManager(HttpSession session, HttpServletRequest request, Long dev_id) {
		logger.debug("into method manage!editDev.do 管理员查询某开发者应用列表");
		try {
			IPageInfo pageInfo = new SetPage(request);
			List<VApplication> appList = this.applicationService.findByPage(dev_id, pageInfo);
			Developer userDeveloper = developerService.getById(dev_id);
			request.setAttribute("appCount", pageInfo.getTotalRow());
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("email", userDeveloper.getEmail());
			request.setAttribute("appList", appList);
			request.setAttribute("dev", userDeveloper);
			request.setAttribute("dev_id", dev_id);
			request.setAttribute("STATUS_UNCHECK", StatusConstant.APP_STATUS_FOR_AUDTI);
			request.setAttribute("STATUS_PASS", StatusConstant.APP_STATUS_ONLINE);
			request.setAttribute("STATUS_ONLINE", StatusConstant.APP_STATUS_ONLINE);
		} catch (Exception e) {
			logger.error(e.toString());

		}
		return "media/dev/dev_edit";
	}

	/**
	 * 用户详情
	 * 
	 * @param developerId
	 * @return
	 */
	@RequestMapping("manage!devInfo.do")
	public String detailDevManager(HttpSession session, Map<String, Object> model, Long dev_id) {
		try {
			Developer userDeveloper = developerService.getById(dev_id);
			BankInfo bankInfo = bankInfoDao.getDevBlank(dev_id, AppConstant.USER_DEVELOPER);
			// 可提款收入
			DeveloperDetailVo developerDetailVo = new DeveloperDetailVo();
			BeanUtils.copyProperties(developerDetailVo, userDeveloper);
			developerDetailVo.setApplyingMoney(devApplyMoneyService.getApplyingByDevId(dev_id).doubleValue());
			// 减去冻结资金
			if (ObjectUtils.isNotEmpty(userDeveloper.getFinance_income())) {
				developerDetailVo.setApplyMoney(userDeveloper.getFinance_income() - developerDetailVo.getApplyingMoney());
			}
			model.put("developerVo", developerDetailVo);
			model.put("dev", userDeveloper);
			model.put("dev_id", dev_id);
			model.put("bankInfo", bankInfo);
			int count = applicationService.findByUser(dev_id).size();
			model.put("appCount", count);
		} catch (Exception e) {
			logger.error(e.toString());
		}

		return "media/dev/dev_info";
	}

	/**
	 * 用户详情
	 * 
	 * @param developerId
	 * @return
	 */
	@RequestMapping("manage!devEdit.do")
	public String editDev(HttpSession session, Map<String, Object> model, Long dev_id) {
		try {
			Developer userDeveloper = developerService.getById(dev_id);
			
			// 可提款收入
			DeveloperDetailVo developerDetailVo = new DeveloperDetailVo();
			BeanUtils.copyProperties(developerDetailVo, userDeveloper);
			developerDetailVo.setApplyingMoney(devApplyMoneyService.getApplyingByDevId(dev_id).doubleValue());
			// 减去冻结资金
			if (ObjectUtils.isNotEmpty(userDeveloper.getFinance_income())) {
				developerDetailVo.setApplyMoney(userDeveloper.getFinance_income() - developerDetailVo.getApplyingMoney());
			}
			model.put("developerVo", developerDetailVo);
			model.put("dev", userDeveloper);
			model.put("dev_id", dev_id);
			int count = applicationService.findByUser(dev_id).size();
			model.put("appCount", count);
		} catch (Exception e) {
			logger.error(e.toString());
		}

		return "media/dev/ope_dev_account_info";
	}

	/**
	 * 修改用户信息
	 * 
	 * @param session
	 * @param model
	 * @param userDeveloper
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("manage!updateDevManager.do")
	public String updateDevManager(HttpSession session, Map<String, Object> model, Developer userDeveloper, HttpServletResponse res) throws Exception {
		ResultVo vo = null;
		// 必填信息验证
		if (!validate(model, userDeveloper)) {
			model.put("dev", userDeveloper);
			int count = applicationService.findByUser(userDeveloper.getId()).size();
			model.put("appCount", count);
			return "media/dev/ope_dev_account_info";
		}
		try {
			userDeveloper.setLastUpdateMan(manageUser.getId());
			developerService.update(userDeveloper);
		} catch (Exception e) {
			logger.error(e.toString());
		}
		vo = new ResultSuccessVo();
		OutputHelper.outPut(res, JacksonMapper.objectToJsonString(vo));
		return null;
	}

	/**
	 * 查看开发者财务资料
	 * 
	 * @param session
	 * @param model
	 * @param developerId
	 * @return
	 */
	@RequestMapping("manage!detailFnancialInfo.do")
	public String detailFnancialInfo(HttpSession session, Map<String, Object> model, Long dev_id) {
		manageUser = (SysUserVo) session.getAttribute("manageUser");
		List<ProvinceCitySort> provinceCitySort = null;
		int parent_id = 0;
		try {
			BankInfo bankInfo = bankInfoDao.getDevBlank(dev_id, AppConstant.USER_DEVELOPER);

			// 查找开户行城市
			if (bankInfo != null && bankInfo.getCity_id() != null) {
				ProvinceCitySort provinceCity = provinceCitySortDao.findprovinceCityBean(bankInfo.getCity_id());
				List<ProvinceCitySort> areaList = provinceCitySortDao.findprovinceCityList(bankInfo.getCity_id());
				model.put("provinceCity", provinceCity);
				model.put("provinceCitySortList", areaList);
			}

			provinceCitySort = provinceCitySortDao.findprovinceCity(parent_id);
			if (ObjectUtils.isNotEmpty(provinceCitySort)) {
				model.put("provinceCitySort", provinceCitySort);
			}
			model.put("bankInfo", bankInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "media/dev/dev_info_fina";
	}

	/**
	 * 查看开发者财务资料
	 * 
	 * @param session
	 * @param model
	 * @param developerId
	 * @return
	 */
	@RequestMapping("manage!detailFnancialInfo2.do")
	public String detailFnancialInfo2(HttpSession session, Map<String, Object> model, Long dev_id) {
		manageUser = (SysUserVo) session.getAttribute("manageUser");
		List<ProvinceCitySort> provinceCitySort = null;
		int parent_id = 0;
		try {
			Developer userDeveloper = developerService.getById(dev_id);
			BankInfo bankInfo = bankInfoDao.getDevBlank(dev_id, AppConstant.USER_DEVELOPER);

			// 查找开户行城市
			if (bankInfo != null && bankInfo.getCity_id() != null) {
				ProvinceCitySort provinceCity = provinceCitySortDao.findprovinceCityBean(bankInfo.getCity_id());
				List<ProvinceCitySort> areaList = provinceCitySortDao.findprovinceCityList(bankInfo.getCity_id());
				model.put("provinceCity", provinceCity);
				model.put("provinceCitySortList", areaList);
			}

			provinceCitySort = provinceCitySortDao.findprovinceCity(parent_id);
			if (ObjectUtils.isNotEmpty(provinceCitySort)) {
				model.put("provinceCitySort", provinceCitySort);
			}
			model.put("dev", userDeveloper);

			if (ObjectUtils.isNotEmpty(bankInfo)) {
				model.put("bankInfo", bankInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "media/dev/ope_dev_account_fina";
	}

	/**
	 * 修改财务资料
	 * 
	 * @param session
	 * @param model
	 * @param userDeveloper
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("manage!updateFnancialInfoManage.do")
	public void updateFnancialInfoManage(HttpSession session, Map<String, Object> model, Developer userDeveloper, BankInfo bankInfo, Long bankInfoId, HttpServletRequest request) throws Exception {
		bankInfo.setCity_id(Integer.valueOf(request.getParameter("platform")));
		logger.debug("into method updateFnancialInfoManage: 修改财务资料");
		manageUser = (SysUserVo) session.getAttribute("manageUser");
		// 必填信息验证
		if (!validate(model, bankInfo)) {
			model.put("dev", userDeveloper);
		}
		// ====组装资源存放目录start====
		String timeStamp = TimeUtil.getTimeStamp();// 获取当前时间戳
		String[] dirName = { userDeveloper.getEmail(), timeStamp };
		// ====组装资源存放目录end====
		try {
			String cardPath = FileUploadTool.upload(request, "cardFile", dirName, FileUploadTool.IMG);
			userDeveloper.setCardUrl(cardPath);// 暂时保留
			developerService.update(userDeveloper);// 暂时保留

			bankInfo.setCardUrl(cardPath);
			bankInfo.setId(bankInfoId);
			if (ObjectUtils.isNotEmpty(bankInfoId)) {
				userDeveloper.setLastUpdateMan(manageUser.getId());
				bankInfoDao.update(bankInfo);
			} else {
				userDeveloper.setLastUpdateMan(manageUser.getId());
				bankInfo.setUserId(userDeveloper.getId());
				bankInfo.setRole(AppConstant.USER_DEVELOPER);
				bankInfo.setCreateTime(new Date());
				bankInfoDao.insert(bankInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 查询开发者账号管理列表-下载
	 * 
	 * @return
	 */
	@RequestMapping("/manage!devAccountListDownloadManage.do")
	public String devAccountListDownloadManage(HttpSession session, Map<String, Object> model, DevQuery bean, String type, HttpServletRequest request, HttpServletResponse response, ReportDevAppDayStatManageVo vo) throws Exception {
		if (!ObjectUtils.isNotEmpty(vo.getStatType())) {// 统计类型
			vo.setStatType(AppConstant.STAT_TYPE_COMPREHENSIVE);
		}
		// 默认查找默认值为今天到前6天的记录
		if (ObjectUtils.isEmpty(vo.getStartTime()) && ObjectUtils.isEmpty(vo.getEndTime())) {
			// 设置起止日期默认值为今天到前6天
			vo.setStartTime(DateUtil.getDateStringByPattern(DateUtil.getDateAddDay(-6), "yyyy-MM-dd"));
			vo.setEndTime(DateUtil.getDateStringByPattern("yyyy-MM-dd"));
		}

		manageUser = (SysUserVo) session.getAttribute("manageUser");

		// ======
		IPageInfo pageInfo = new SetPage(request);
		List<Developer> userDeveloperList = developerService.findByDev(bean);
		request.setAttribute("pageInfo", pageInfo);

		// 查询用户应用数量
		Map<Long, Integer> map = new HashMap<Long, Integer>();
		List<DeveloperVo> appCountList = developerService.getAppCount();
		for (DeveloperVo appCount : appCountList) {
			map.put(appCount.getDev_id(), appCount.getAppCount());
		}

		// 返回的list
		for (Developer dev : userDeveloperList) {
			Long devId = dev.getId();
			Integer count = map.get(devId);
			if (count == null) {
				dev.setAppCount(0);
			} else {
				dev.setAppCount(count);
			}
		}

		// model.put("map", map);
		model.put("devList", userDeveloperList);
		model.put("devCount", pageInfo.getTotalRow());
		// 导出报表
		if (vo.getStatType().equals(AppConstant.STAT_TYPE_DAY)) {
			boolean[] isMoney = new boolean[] { false, false, false, false, false, false, true, true, true, false };

			ExportUtils.exportExcel(userDeveloperList, "开发者账号管理", vo.getTimeInterval() + "账户管理", new String[] { "UID", "账号", "联系人", "注册时间", "应用个数", "帐号状态", "确定收入", "可提款收入", "累计提现", "操作" }, new String[] { "id", "email", "realName", "createTime", "appCount", "status", "confirmedMoney", "finance_income", "totalMoney", "status" }, null, null, null, isMoney, null, response);
		} else {
			boolean[] isMoney = new boolean[] { false, false, false, false, false, false, true, true, true, false };

			ExportUtils.exportExcel(userDeveloperList, "开发者账号管理", vo.getTimeInterval() + "账户管理", new String[] { "UID", "账号", "联系人", "注册时间", "应用个数", "帐号状态", "确定收入", "可提款收入", "累计提现", "操作" }, new String[] { "id", "email", "realName", "createTime", "appCount", "status", "confirmedMoney", "finance_income", "totalMoney", "status" }, null, null, null, isMoney, null, response);
		}
		model.put("devCount", 0);
		return null;
	}

	/**
	 * 管理员重置开发者密码
	 * 
	 * @param session
	 * @param devId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("manage!resetPassManage.do")
	public void resetPassManage(HttpSession session, Long developerId, Map<String, Object> model) throws Exception {
		logger.debug("into method resetPassManage: 管理员重置用户【" + developerId + "】的密码");

		manageUser = (SysUserVo) session.getAttribute("manageUser");
		Developer userDeveloper = new Developer();
		userDeveloper.setId(developerId);
		userDeveloper.setPassword(ConfigUtil.getString("resetPass"));
		try {
			developerService.updatePass(userDeveloper);
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	/**
	 * 管理员冻结
	 * 
	 * @param session
	 * @param developerId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("manage!accountFreeze.do")
	public void accountFreeze(HttpSession session, Long developerId, Map<String, Object> model, HttpServletRequest request) throws Exception {
		logger.debug("into method accountClosed: 管理员冻结用户【" + developerId + "】的帐号");
		manageUser = (SysUserVo) session.getAttribute("manageUser");
		try {
			developerService.updateStatus(developerId, Constant.BLOCK_FREEZE, manageUser.getId());// 账户冻结
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 管理员解冻
	 * 
	 * @param session
	 * @param developerId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("manage!accountReverseFreeze.do")
	public void accountReverseFreeze(HttpSession session, Long developerId, Map<String, Object> model, HttpServletRequest request) throws Exception {
		logger.debug("into method accountClosed: 管理员解冻用户【" + developerId + "】的帐号");
		manageUser = (SysUserVo) session.getAttribute("manageUser");
		try {
			developerService.updateStatus(developerId, Constant.BLOCK_USE, manageUser.getId());// 账户正常
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 管理员封号
	 * 
	 * @param session
	 * @param developerId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("manage!accountClosed.do")
	public void accountClosed(HttpSession session, Long developerId, Map<String, Object> model, HttpServletRequest request) throws Exception {
		logger.debug("into method accountClosed: 管理员封停用户【" + developerId + "】的帐号");
		manageUser = (SysUserVo) session.getAttribute("manageUser");
		try {
			developerService.updateStatus(developerId, Constant.BLOCK_CLOSED, manageUser.getId());// 账户停用
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	/**
	 * 管理员解封
	 * 
	 * @param session
	 * @param developerId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("manage!accountOpen.do")
	public void accountOpen(HttpSession session, Long developerId, Map<String, Object> model, HttpServletRequest request) throws Exception {
		logger.debug("into method accountOpen: 管理员解封用户【" + developerId + "】的帐号");

		manageUser = (SysUserVo) session.getAttribute("manageUser");
		Developer userDeveloper = new Developer();
		userDeveloper.setId(developerId);
		userDeveloper.setStatus(Constant.BLOCK_USE);

		Application developedApp = new Application();
		developedApp.setDev_id(developerId);
		developedApp.setStatus(StatusConstant.APP_STATUS_FOR_AUDTI);
		developedApp.setLastUpdateMan(manageUser.getId());
		try {
			developerService.updateFreeze(developerId);// 账户启用
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 管理员手动激活
	 * 
	 * @param session
	 * @param developerId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("accountActivation.do")
	public void activation(HttpSession session, Long dev_id, Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("into method accountActivation: 管理员手动激活用户【" + dev_id + "】的帐号");
		ResultVo result = null;

		manageUser = (SysUserVo) session.getAttribute("manageUser");
		Developer userDeveloper = new Developer();
		userDeveloper.setId(dev_id);
		userDeveloper.setStatus(Constant.BLOCK_USE);
		userDeveloper.setLastUpdateMan(manageUser.getId());// 将激活帐号的管理员ID计入账户最后修改人

		try {
			developerService.update(userDeveloper);// 账户启用
			result = new ResultSuccessVo();
		} catch (Exception e) {
			e.printStackTrace();
			result = new ResultErrorVo("系统异常！！");
		}
		OutputHelper.outPut(response, JacksonMapper.objectToJsonString(result));

	}

	/**
	 * 验证开发者信息
	 * 
	 * @param model
	 * @param userDeveloper
	 * @throws Exception
	 */
	public boolean validate(Map<String, Object> model, Developer userDeveloper) throws Exception {
		boolean falg = true;
		if (ObjectUtils.isNotEmpty(userDeveloper)) {
			// 账户类型为个人
			if (userDeveloper.getType() == 1) {
				if (ObjectUtils.isEmpty(userDeveloper.getRealName())) {
					model.put("errMsg_devRealName", "联系人不能为空！");
					falg = false;
				}
				if (ObjectUtils.isEmpty(userDeveloper.getMobilePhone())) {
					model.put("errMsg_devMobilePhone", "手机号不能为空！");
					falg = false;
				}
				if (ObjectUtils.isEmpty(userDeveloper.getQq())) {
					model.put("errMsg_devQQ", "QQ号不能为空！");
					falg = false;
				}
			}
			// 账户类型为公司
			if (userDeveloper.getType() == 2) {
				if (ObjectUtils.isEmpty(userDeveloper.getCompanyName())) {
					model.put("errMsg_devCompanyName", "公司名称不能为空！");
					falg = false;
				}
				if (ObjectUtils.isEmpty(userDeveloper.getWebsiteUrl())) {
					model.put("errMsg_devWebsiteUrl", "公司网址不能为空！");
					falg = false;
				}
				if (ObjectUtils.isEmpty(userDeveloper.getCompanyAddress())) {
					model.put("errMsg_devCompanyAddress", "详细地址不能为空！");
					falg = false;
				}
				if (ObjectUtils.isEmpty(userDeveloper.getMailingAddress())) {
					model.put("errMsg_devMailingAddress", "通信地址不能为空！");
					falg = false;
				}
				if (ObjectUtils.isEmpty(userDeveloper.getPostCode())) {
					model.put("errMsg_devPostCode", "邮编不能为空！");
					falg = false;
				}
				if (ObjectUtils.isEmpty(userDeveloper.getRealName())) {
					model.put("errMsg_devRealName", "联系人不能为空！");
					falg = false;
				}
				if (ObjectUtils.isEmpty(userDeveloper.getMobilePhone())) {
					model.put("errMsg_devMobilePhone", "手机号不能为空！");
					falg = false;
				}
				if (ObjectUtils.isEmpty(userDeveloper.getQq())) {
					model.put("errMsg_devQQ", "QQ号不能为空！");
					falg = false;
				}
			}
			return falg;
		}
		return falg;
	}

	/**
	 * 验证银行信息
	 * 
	 * @param model
	 * @param bankInfo
	 * @throws Exception
	 */
	public boolean validate(Map<String, Object> model, BankInfo bankInfo) throws Exception {
		boolean falg = true;
		if (ObjectUtils.isNotEmpty(bankInfo)) {
			if (ObjectUtils.isEmpty(bankInfo.getCardCode())) {
				model.put("errMsg_bankCardCode", "证件号不能为空！");
				falg = false;
			}
			if (ObjectUtils.isEmpty(bankInfo.getBankName())) {
				model.put("errMsg_bankBankName", "开户行不能为空！");
				falg = false;
			}
			if (ObjectUtils.isEmpty(bankInfo.getBankSubbranch())) {
				model.put("errMsg_bankBankSubbranch", "支行名称不能为空！");
				falg = false;
			}
			if (ObjectUtils.isEmpty(bankInfo.getBankAccount())) {
				model.put("errMsg_bankBankAccount", "银行帐号不能为空！");
				falg = false;
			}
			return falg;
		}
		return falg;
	}

	/**
	 * add by cdd
	 * <p>
	 * Title: channelLoginShow
	 * </p>
	 * <p>
	 * Description:开发者信息
	 * </p>
	 * 
	 * @param request
	 * @param id
	 * @return
	 * @return String
	 * @throws
	 */
	@RequestMapping("/manage!checkUserById.do")
	public String checkUserById(HttpServletRequest request, HttpServletResponse response, String id) {
		Developer user = null;
		try {
			user = developerService.getById(Long.valueOf(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		PrintStream out = null;
		try {
			out = new PrintStream(response.getOutputStream());
			if (user != null && user.getId() != null) {
				out.print(user.getId() + "|" + user.getEmail());
			} else {
				out.print("-1");
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}

		}

		return null;
	}

	/**
	 * add by cdd
	 * <p>
	 * Title: channelLoginShow
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param request
	 * @param id
	 * @return
	 * @return String
	 * @throws
	 */
	@RequestMapping("/manage!checkUserByIdName.do")
	public String checkUserByIdName(HttpServletRequest request, HttpServletResponse response, String id, String name) {
		Developer user = null;
		try {
			user = developerService.getByIdAndName(Long.valueOf(id), name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		PrintStream out = null;
		try {
			out = new PrintStream(response.getOutputStream());
			if (user != null && user.getId() != null) {
				out.print("1");
			} else {
				out.print("-1");
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}

		}
		return null;
	}

	/**
	 * add by cdd
	 * <p>
	 * Title: channelLoginShow
	 * </p>
	 * <p>
	 * Description:注册验证账号（开发者）
	 * </p>
	 * 
	 * @param request
	 * @param id
	 * @return
	 * @return String
	 * @throws
	 */
	@RequestMapping("/manage!getUsersByName.do")
	public String getUsersByName(HttpServletResponse response, String name) {
		List<Developer> list = null;
		try {
			list = developerService.getByEmail(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		PrintStream out = null;
		try {
			String str = "";
			out = new PrintStream(response.getOutputStream());
			if (list != null && list.size() > 0) {
				StringBuilder sb = new StringBuilder();
				for (Developer user : list.subList(0, Math.min(list.size(), 10))) {
					if (user != null && user.getId() != null) {
						sb.append(user.getEmail() + "-" + user.getId() + "|");
					}
				}
				sb.deleteCharAt(sb.length() - 1);
				str = sb.toString();

			}
			out.print(str);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}

		}
		return null;
	}
}
