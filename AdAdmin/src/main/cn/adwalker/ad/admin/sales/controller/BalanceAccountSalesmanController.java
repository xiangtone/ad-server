package cn.adwalker.ad.admin.sales.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.adwalker.ad.admin.ad.form.CampaignPaymentsForm;
import cn.adwalker.ad.admin.common.controller.AbstractControllerParent;
import cn.adwalker.ad.admin.common.vo.SysUserVo;
import cn.adwalker.ad.admin.sales.bean.BalanceAccountSalesmanBean;
import cn.adwalker.ad.admin.sales.service.IBalanceAccountSalesmanService;
import cn.adwalker.ad.admin.sales.service.ICampaignSalesmanService;
import cn.adwalker.ad.admin.sales.vo.BalanceAccountInfoVo;
import cn.adwalker.ad.admin.sales.vo.BalanceAccountVo;
import cn.adwalker.ad.config.Constant;
import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.page.SetPage;
import cn.adwalker.core.util.JacksonMapper;
import cn.adwalker.core.util.OutputHelper;
import cn.adwalker.core.vo.ResultSuccessVo;
import cn.adwalker.core.vo.ResultVo;

/**
 * 
 * <p>
 * Title: BalanceAccountController
 * </p>
 * <p>
 * Description:销售对账
 * </p>
 * <p>
 * </p>
 * 
 * @author cuidd
 * @date 2014-2-21
 */
@Controller
public class BalanceAccountSalesmanController extends AbstractControllerParent {

	/** 日志记录器 */
	Logger logger = Logger.getLogger(BalanceAccountSalesmanController.class);

	/** 广告方案调整相关业务 */
	@Resource
	private IBalanceAccountSalesmanService balanceAccountSalesmanService;
	
	private ICampaignSalesmanService campaignSalesmanService;

	/**
	 * <p>
	 * Title: listAdAjustment
	 * </p>
	 * <p>
	 * Description:方案调整
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @return String
	 * @date 2013-5-15
	 * @version 1.0
	 * @author zouguibao
	 */
	@RequestMapping("/balanceaccount.do")
	public String list(HttpServletRequest request,
			HttpServletResponse response,HttpSession session, BalanceAccountSalesmanBean bean) {
		List<BalanceAccountVo> list = null;
		IPageInfo pageInfo = new SetPage(request);
		SysUserVo user = (SysUserVo) session.getAttribute("manageUser");
		try {
			
			if (user.getRoleCode().equals(Constant.ROLE_CODE_SALES_MANAGER)) {
				bean.setArea_type(user.getArea_type());
			} else if (user.getRoleCode().equals(Constant.ROLE_CODE_SALES_MAN)) {
				Long l = campaignSalesmanService.getSalesmanIdBySysUser(user
						.getId());
				bean.setSales_id(l);

			}
			list = this.balanceAccountSalesmanService
					.findByPage(bean, pageInfo);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("bean", bean);
			request.setAttribute("list", list);
			request.setAttribute("pageRecord", pageInfo.getTotalRow());
			request.setAttribute("page", pageInfo.getPageSize());
		} catch (Exception e) {
			logger.debug("获取方案调整列表异常！");
			e.printStackTrace();
		}
		return "ad/balanceAccount/list";
	}

	/**
	 * 
	 * <p>
	 * Title: edit
	 * </p>
	 * <p>
	 * Description:回款添加
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @param bean
	 * @return
	 * @author cuidd
	 * @date 2014-2-24
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/addPayments.do")
	public String edit(HttpServletRequest request,
			HttpServletResponse response, Long id) {
		Date date = new Date();

		request.setAttribute("today", date);
		request.setAttribute("id", id);
		return "ad/balanceAccount/addPayments";
	}

	@RequestMapping("/balanceAccountInfo.do")
	public String info(HttpServletRequest request,
			HttpServletResponse response, Long id) throws Exception {
		BalanceAccountInfoVo vo = balanceAccountSalesmanService.info(id);
		request.setAttribute("vo", vo);
		request.setAttribute("id", id);
		return "ad/balanceAccount/info";
	}

	/**
	 * 
	 * <p>
	 * Title: save
	 * </p>
	 * <p>
	 * Description:保存回款
	 * </p>
	 * 
	 * @param request
	 * @param session
	 * @param response
	 * @param form
	 * @throws Exception
	 * @author cuidd
	 * @date 2014-2-24
	 * @return void
	 * @version 1.0
	 */
	@RequestMapping("/savePayments.do")
	public void save(HttpServletRequest request, HttpSession session,
			HttpServletResponse response, CampaignPaymentsForm form)
			throws Exception {
		SysUserVo user = (SysUserVo) session.getAttribute("manageUser");
		try {
			balanceAccountSalesmanService.savePayments(form, user);
			ResultVo vo = new ResultSuccessVo();
			OutputHelper.outPut(response, JacksonMapper.objectToJsonString(vo));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * <p>
	 * Title: bad_debt
	 * </p>
	 * <p>
	 * Description:标记为坏账
	 * </p>
	 * 
	 * @param request
	 * @param session
	 * @param response
	 * @param form
	 * @throws Exception
	 * @author cuidd
	 * @date 2014-2-24
	 * @return void
	 * @version 1.0
	 */
	@RequestMapping("/bad_debt.do")
	public void bad_debt(HttpServletRequest request, HttpSession session,
			HttpServletResponse response, Long id) throws Exception {
		SysUserVo user = (SysUserVo) session.getAttribute("manageUser");
		try {
			balanceAccountSalesmanService.bad_debt(id, user);
			ResultVo vo = new ResultSuccessVo();
			OutputHelper.outPut(response, JacksonMapper.objectToJsonString(vo));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * <p>
	 * Title: update_confirm_money
	 * </p>
	 * <p>
	 * Description:更新预确认收入
	 * </p>
	 * 
	 * @param request
	 * @param session
	 * @param response
	 * @param id
	 * @throws Exception
	 * @author cuidd
	 * @date 2014-2-24
	 * @return void
	 * @version 1.0
	 */
	@RequestMapping("/add_confirm_money.do")
	public String addConfirmMoney(HttpServletRequest request,
			HttpSession session, HttpServletResponse response, Long id)
			throws Exception {
		request.setAttribute("id", id);
		return "ad/balanceAccount/addConfirmMoney";

	}

	/**
	 * 
	 * <p>
	 * Title: updateConfirmMoney
	 * </p>
	 * <p>
	 * Description:更新预确认金额
	 * </p>
	 * 
	 * @param request
	 * @param session
	 * @param response
	 * @param id
	 * @param confirm_money
	 * @param confirm_date
	 * @throws Exception
	 * @author cuidd
	 * @date 2014-2-24
	 * @return void
	 * @version 1.0
	 */
	@RequestMapping("/update_confirm_money.do")
	public void updateConfirmMoney(HttpServletRequest request,
			HttpSession session, HttpServletResponse response, Long id,
			Double confirm_money,Double confirm_num, Date confirm_date) throws Exception {
		try {
			balanceAccountSalesmanService.updateConfirmMoney(id,
					new BigDecimal(confirm_money), confirm_date);
			ResultVo vo = new ResultSuccessVo();
			OutputHelper.outPut(response, JacksonMapper.objectToJsonString(vo));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/sendBalanceMail.do")
	public void sendMail(HttpServletRequest request, HttpSession session,
			HttpServletResponse response, Long id, BigDecimal confirm_money,
			Date confirm_date) throws Exception {
		try {
			balanceAccountSalesmanService.sendBalanceMail(id);
			ResultVo vo = new ResultSuccessVo();
			OutputHelper.outPut(response, JacksonMapper.objectToJsonString(vo));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
