package cn.adwalker.ad.web.developer.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.adwalker.ad.config.Role;
import cn.adwalker.ad.core.page.IPageInfo;
import cn.adwalker.ad.core.page.SetPage;
import cn.adwalker.ad.core.pool.SpringDatePool;
import cn.adwalker.ad.log.service.AdwalkerLogger;
import cn.adwalker.ad.model.common.dao.BankInfoDao;
import cn.adwalker.ad.model.common.domain.BankInfo;
import cn.adwalker.ad.model.common.domain.ConfigEScore;
import cn.adwalker.ad.model.common.domain.EJumpType;
import cn.adwalker.ad.model.common.domain.ProvinceCitySort;
import cn.adwalker.ad.model.developer.domain.DevApplyMoney;
import cn.adwalker.ad.model.developer.domain.Developer;
import cn.adwalker.ad.util.BeanUtils;
import cn.adwalker.ad.util.JacksonMapper;
import cn.adwalker.ad.util.ObjectUtils;
import cn.adwalker.ad.util.OutputHelper;
import cn.adwalker.ad.util.page.PageUtil;
import cn.adwalker.ad.web.common.controller.AbstractControllerParent;
import cn.adwalker.ad.web.common.vo.ResultErrorVo;
import cn.adwalker.ad.web.common.vo.ResultSuccessVo;
import cn.adwalker.ad.web.common.vo.ResultVo;
import cn.adwalker.ad.web.developer.bean.DevApplyMoneySearch;
import cn.adwalker.ad.web.developer.service.DeveloperApplyMoneyService;
import cn.adwalker.ad.web.developer.service.DeveloperService;
import cn.adwalker.ad.web.developer.vo.DevAccountInfoVo;
import cn.adwalker.ad.web.developer.vo.DevApplyMoneyVo;
import cn.adwalker.ad.web.developer.vo.DeveloperVo;

/**
 * 开发者申请提现控制层
 */
@Controller
public class DeveloperApplyMoneyController extends AbstractControllerParent {

	private static final Logger log = LoggerFactory.getLogger(DeveloperApplyMoneyController.class);

	@Resource
	private DeveloperService developerService;
	
	@Resource
	private SpringDatePool springDatePool;
	
	@Resource
	private BankInfoDao bankInfoDao;
	
	@Resource
	private DeveloperApplyMoneyService developerApplyMoneyService;

	/**
	 * <p>
	 * Title: toDevApplyMoney
	 * </p>
	 * <p>
	 * Description:to申请提现
	 * </p>
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/toDeveloperApplyMoney.action")
	public String toDeveloperApplyMoney(HttpServletRequest request, Map<String, Object> model) throws Exception {
		try {
			DeveloperVo developerVo = new DeveloperVo();
			Developer userDeveloper = developerService.getById(super.getUserDeveloper().getId());
			BeanUtils.copyProperties(developerVo, userDeveloper);
			developerVo.setApplyingMoney(developerApplyMoneyService.getApplyingByDevUserId(super.getUserDeveloper().getId()).doubleValue());
			// 减去冻结资金
			if (ObjectUtils.isNotEmpty(userDeveloper.getFinance_income())) {
				developerVo.setApplyMoney(userDeveloper.getFinance_income() - developerVo.getApplyingMoney());
			}
			ConfigEScore ce = springDatePool.getPlatformConfig();
			Double devApplyMinMoney = (ce != null) ? ce.getDevApplyMinMoney() : 0d;
			Double devMinDrawMoney = (ce != null) ? ce.getDev_min_draw_money() : 0d;
			double balance = 0.00;
			developerVo.setBalance(balance);// 设置余额
			developerVo.setDevApplyMinMoney(devApplyMinMoney);// 设置提现最小值
			developerVo.setDevMinDrawMoney(devMinDrawMoney);
			developerVo.setEmail(userDeveloper.getEmail());
			log.debug("提现最小值" + devApplyMinMoney);
			model.put("developerVo", developerVo);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("toDevApplyMoney:" + e.getMessage());
			AdwalkerLogger.error(super.getUserDeveloper().getId(), Role.DEVELOPER, "/toDeveloperApplyMoney.action", "devId:" + super.getUserDeveloper().getId(), e.getMessage());
		}
		AdwalkerLogger.log(super.getUserDeveloper().getId(), Role.DEVELOPER, "/toDeveloperApplyMoney.action", "devId:" + super.getUserDeveloper().getId(), "to申请提现");
		return "developer/apply";
	}

	/**
	 * <p>
	 * Title: devApplyMoney
	 * </p>
	 * <p>
	 * Description:申请提现
	 * </p>
	 * 
	 * @param request
	 * @param applyMoney
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/developerApplyMoney.action")
	public String developerApplyMoney(HttpServletRequest request, Double applyMoney, HttpServletResponse response, Map<String, Object> model) throws Exception {
		Long id = null;
		ResultVo resultVo = null;
		BankInfo bankInfo = this.getBankInfo(super.getUserDeveloper().getId());
		if (bankInfo == null) {
			resultVo = new ResultErrorVo(ResultVo.STATUS_ERROR_REDIRECT, "请先完善财务信息");
		} else {
			try {
				Developer userDeveloper = developerService.getById(super.getUserDeveloper().getId());
				// 查询CONFIG_ESCORE表中的汇率
				ConfigEScore configEScore = springDatePool.getPlatformConfig();
				Double dev_min_draw_money = configEScore.getDev_min_draw_money();
				double balance = 0d;
				if (userDeveloper.getFinance_income() != null) {
					balance = userDeveloper.getFinance_income() - developerApplyMoneyService.getApplyingByDevUserId(super.getUserDeveloper().getId()).doubleValue();
				}
				if (applyMoney >= dev_min_draw_money && applyMoney <= balance) {
					DevApplyMoney devApplyMoney = new DevApplyMoney();
					devApplyMoney.setDev_id(userDeveloper.getId());// 记录开发者ID
					devApplyMoney.setApplyMoney(applyMoney);// 记录申请提现金额
					devApplyMoney.setBankId(bankInfo.getId());// 记录银行帐号
					devApplyMoney.setRealName(super.getUserDeveloper().getRealName());// 填充联系人
					Double tax = developerService.getTax(applyMoney);
					Double dues = developerService.getDues();
					devApplyMoney.setFinance_tax(tax);
					devApplyMoney.setFinance_dues(dues);
					devApplyMoney.setManagerMoney(applyMoney);
					devApplyMoney.setAccount_hoder(bankInfo.getAccountHoder());
					devApplyMoney.setBank_name(bankInfo.getBankName());
					devApplyMoney.setBank_subbranch(bankInfo.getBankSubbranch());
					devApplyMoney.setBank_account(bankInfo.getBankAccount());
					List<ProvinceCitySort> provinceCitySort = bankInfoDao.findIdCity(bankInfo.getCity_id());
					devApplyMoney.setBank_city(provinceCitySort.get(0).getProvince_City());
					devApplyMoney.setTax_status(userDeveloper.getTax_Status());
					id = developerApplyMoneyService.insert(devApplyMoney);
					model.put("id", id);
				} else {
					model.put("msg", "可能由于汇率变动或余额不足导致提现失败！");
					log.debug("可能由于汇率变动或余额不足导致提现失败！");
					log.debug("当前汇率：,当前余额：" + balance + ",申请提现金额：" + applyMoney + ",当前余额 将扣除余额：");
					AdwalkerLogger.error(getUserDeveloper().getId(), Role.DEVELOPER, "/developerApplyMoney.action", "当前汇率：" + ",当前余额：" + balance + ",申请提现金额：" + applyMoney + ",当前余额：" + "将扣除余额：", "可能由于余额不足导致提现失败！");
					resultVo = new ResultErrorVo("余额不足");
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error("devApplyMoney:" + e.getMessage());
				AdwalkerLogger.error(getUserDeveloper().getId(), Role.DEVELOPER, "/developerApplyMoney.action", JacksonMapper.objectToJsonString(applyMoney), e.getMessage());
			}
			AdwalkerLogger.log(getUserDeveloper().getId(), Role.DEVELOPER, "/developerApplyMoney.action", JacksonMapper.objectToJsonString(applyMoney), "申请提现");
			resultVo = new ResultSuccessVo(id);
		}
		OutputHelper.outPut(response, JacksonMapper.objectToJsonString(resultVo));
		return null;
	}

	/**
	 * 
	 * <p>
	 * Title: getBankInfo
	 * </p>
	 * <p>
	 * Description:获取提款人银行信息
	 * </p>
	 * 
	 * @param dev_id
	 * @return
	 * @throws Exception
	 */
	private BankInfo getBankInfo(Long dev_id) throws Exception {
		BankInfo entity = null;
		List<BankInfo> list = bankInfoDao.findByUser(dev_id);
		if (list != null && list.size() > 0) {
			entity = list.get(0);
			if (ObjectUtils.isEmpty(entity.getAccountHoder()) || ObjectUtils.isEmpty(entity.getBankName()) || ObjectUtils.isEmpty(entity.getBankSubbranch()) || ObjectUtils.isEmpty(entity.getBankAccount())) {
				entity = null;
			}
		}
		return entity;
	}

	/**
	 * 
	 * <p>
	 * Title: cancelApplyMoney
	 * </p>
	 * <p>
	 * Description:取消申请请求处理
	 * </p>
	 * 
	 * @param request
	 * @param id
	 * @param model
	 * @return
	 * @throws
	 */
	@RequestMapping("/developerCancelApplyMoney.action")
	public String developerCancelApplyMoney(HttpServletRequest request, Long id, HttpServletResponse response, Map<String, Object> model) {
		try {
			Integer result = developerApplyMoneyService.cancelApply(id);
			model.put("msg", "撤销申请成功！");
			AdwalkerLogger.log(super.getUserDeveloper().getId(), Role.DEVELOPER, "/developerApplyMoney.action", String.valueOf(id), "撤销提现申请");
			if (result == null || result != 1) {
				super.jumpPage(request, "您的申请已处理，不能撤销！", "/developerApplyMoney.action", EJumpType.FAIL.getValue());
			}
		} catch (Exception e) {
			e.printStackTrace();
			OutputHelper.outPut(response, "false");
			log.error(e.toString());
		}
		OutputHelper.outPut(response, "true");
		return null;
	}

	/**
	 * <p>
	 * Title: devAccount
	 * </p>
	 * <p>
	 * Description:to查询提款记录
	 * </p>
	 * 
	 * @param model
	 * @param request
	 * @param bean
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	@RequestMapping("/developerAccount.action")
	public String developerAccount(Map<String, Object> model, HttpServletRequest request, DevApplyMoneySearch bean) throws IOException, Exception {
		if (bean == null) {
			bean = new DevApplyMoneySearch();
		}
		bean.setDev_id(super.getUserDeveloper().getId());
		try {
			IPageInfo pageInfo = new SetPage(request);
			List<DevApplyMoneyVo> list = developerApplyMoneyService.findByDevUserId(bean, pageInfo);
			model.put("pageInfo", PageUtil.fenyeNew(pageInfo));
			model.put("list", list);
			model.put("bean", bean);
			model.put("executeContorller", "developerAccount.action");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("toDevApplyMoney:" + e.getMessage());
			AdwalkerLogger.error(super.getUserDeveloper().getId(), Role.DEVELOPER, "/toDeveloperApplyMoney.action", JacksonMapper.objectToJsonString(bean), e.getMessage());
		}
		AdwalkerLogger.log(super.getUserDeveloper().getId(), Role.DEVELOPER, "/toDeveloperApplyMoney.action", JacksonMapper.objectToJsonString(bean), "to申请提现");
		return "developer/apply_detail";
	}

	/**
	 * <p>
	 * Title: accountInfo
	 * </p>
	 * <p>
	 * Description:开发者财务账户信息
	 * </p>
	 * 
	 * @param model
	 * @param request
	 * @param bean
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	@RequestMapping("/accountInfo.action")
	public String accountInfo(Map<String, Object> model, HttpServletRequest request) throws IOException, Exception {
		log.debug("into method accountInfo: to开发者财务账户信息");
		Long dev_id = super.getUserDeveloper().getId();
		try {
			DevAccountInfoVo vo = developerApplyMoneyService.findByIdInfo(dev_id);
			List<BankInfo> bankInfoList = developerApplyMoneyService.findByUser(dev_id);
			model.put("vo", vo);
			if (ObjectUtils.isNotEmpty(bankInfoList)) {
				model.put("bankvo", bankInfoList.get(0));
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("accountInfo:" + e.getMessage());
		}
		return "developer/account_info";
	}

	/**
	 * <p>
	 * Title: toUpdateUser
	 * </p>
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/toDeveloperApplyMoneyAccount.action")
	public String toDeveloperApplyMoneyAccount(Map<String, Object> model, Long id) throws Exception {
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
			model.put("dev_mail", super.getUserDeveloper().getEmail());
			model.put("id", id);
		} catch (Exception e) {
			e.printStackTrace();

		}
		return "developer/apply_account";
	}

	/**
	 * <p>
	 * Title: devApplyMoneyAccount
	 * </p>
	 * <p>
	 * Description:修改发票状态
	 * </p>
	 * 
	 * @param request
	 * @param open_invoice
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/developerApplyMoneyAccount.action")
	public String developerApplyMoneyAccount(HttpServletRequest request, int open_invoice, Long apply_id, HttpServletResponse response, Map<String, Object> model) throws Exception {
		ResultVo vo = null;
		try {
			DevApplyMoney devApplyMoney = new DevApplyMoney();
			devApplyMoney.setId(apply_id);
			if (open_invoice == 0) {
				devApplyMoney.setFinance_tax(0d);
			}
			if ( ObjectUtils.isNotEmpty(open_invoice)) {
				devApplyMoney.setInvoice(open_invoice);
			}
			developerApplyMoneyService.updateApplyAccount(devApplyMoney);
			vo = new ResultSuccessVo();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("devApplyMoney:" + e.getMessage());
			vo = new ResultErrorVo();
		}
		OutputHelper.outPut(response, JacksonMapper.objectToJsonString(vo));
		return null;
	}

	/**
	 * <p>
	 * Title: financeApplySuccess
	 * </p>
	 * <p>
	 * Description:信息核对完成成功页面跳转
	 * </p>
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	@RequestMapping("/toFinanceApplySuccess.action")
	public String financeApplySuccess(Map<String, Object> model, HttpServletRequest request) throws IOException, Exception {
		String dev_email = super.getUserDeveloper().getEmail();
		model.put("dev_email", dev_email);
		return "developer/apply_success";
	}
}
