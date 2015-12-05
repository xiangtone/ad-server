package cn.adwalker.ad.admin.operation.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.adwalker.ad.admin.common.controller.AbstractControllerParent;
import cn.adwalker.ad.admin.common.vo.SysUserVo;
import cn.adwalker.ad.admin.operation.bean.DevApplyMoneySearchBean;
import cn.adwalker.ad.admin.operation.service.IOperationDevApplyMoneyService;
import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.page.SetPage;
import cn.adwalker.model.app.domain.DevApplyMoney;

/**
 * <p>
 * Title: OperationDevApplyMoneyController
 * </p>
 * <p>
 * Description:运营提款审核
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-5-21
 */
@Controller
public class OperationDevApplyMoneyController extends AbstractControllerParent {

	/** 日志记录器 */
	Logger logger = Logger.getLogger(OperationDevApplyMoneyController.class);

	/** 运营提款审核Service */
	@Resource
	private IOperationDevApplyMoneyService operationDevApplyMoneyService;

	/**
	 * <p>
	 * Title: listDMOperAudit
	 * </p>
	 * <p>
	 * Description:运营提款List
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @param bean
	 * @return
	 * @author lichuang
	 * @date 2013-5-21
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!listDMOperAudit.do")
	public String listDMOperAudit(HttpServletRequest request, HttpServletResponse response, DevApplyMoneySearchBean bean) {
		List<DevApplyMoney> list = null;
		IPageInfo pageInfo = new SetPage(request);
		try {
			list = (List<DevApplyMoney>) this.operationDevApplyMoneyService.findAuditList(bean, pageInfo);

			// 添加统计数据
			DevApplyMoney dMOperAuditSum = this.operationDevApplyMoneyService.findReportSum(bean, 0);
			request.setAttribute("dMOperAuditSum", dMOperAuditSum);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("listDMOperAudit", list);
			request.setAttribute("bean", bean);
			request.setAttribute("pageRecord", pageInfo.getTotalRow());
			request.setAttribute("page", pageInfo.getPageSize());

			return "manage/finance/ope_cw_devope";
		} catch (Exception e) {
			logger.debug("获取开发者提款审核列表异常！");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 运营单个审核,操作步骤：a：如果是审核通过状态，更新运营确认金额、运营说明字段。b：更改状态
	 * 
	 * @param request
	 * @param response
	 * @param id 被审核id
	 * @param status 状态
	 * @param managerDesc 运营说明
	 * @param managerMoney 运营确认金额
	 * @return
	 */
	@RequestMapping("/manage!singleAuditOper.do")
	public String singleAuditOper(HttpServletRequest request) {
		SysUserVo manageUser = (SysUserVo) request.getSession().getAttribute("manageUser");// 当前登录管理员用户
		Long id = Long.parseLong(request.getParameter("id"));
		String status = request.getParameter("status");
		String mDesc = request.getParameter("mDesc");
		String mMoney = request.getParameter("mMoney");
		try {
			double manageMoney = 0;
			if (!"".equals(mMoney)) {
				manageMoney = Double.valueOf(mMoney);
			}
			Long[] ids = { id };
			double[] managerMoneys = { manageMoney };
			String[] managerDescs = { mDesc };
			this.operationDevApplyMoneyService.tranAuditInOper(ids, Integer.parseInt(status), managerDescs, managerMoneys, manageUser);
		} catch (Exception e) {
			logger.debug("开发者财务运营单个审核异常！");
			e.printStackTrace();
		}
		return "redirect:manage!listDMOperAudit.do";
	}

	/**
	 * 运营批量审核,操作步骤：a：如果是审核通过状态，更新运营确认金额、运营说明字段。b：更改状态
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/manage!batchAuditOper.do")
	public String batchAuditOper(HttpServletRequest request, HttpServletResponse response, Long[] checkbox, String status, String mDescs, String mMoneys) {
		SysUserVo manageUser = (SysUserVo) request.getSession().getAttribute("manageUser");// 当前登录管理员用户
		try {
			String[] mMoneysString = mMoneys.trim().split(",");
			double[] mms = new double[mMoneysString.length];
			if (!"".equals(mMoneys.trim())) {
				for (int i = 0; i < mMoneysString.length; i++) {
					mms[i] = Double.valueOf((mMoneysString[i]));
				}
			}
			String[] mDescString = mDescs.trim().split(",");
			int tag = Integer.parseInt(status);
			String[] mds;
			if (tag != 1) {
				mds = new String[mDescString.length];
				for (int i = 0; i < mDescString.length; i++) {
					mds[i] = mDescString[i];
				}
			} else {
				mds = new String[mMoneysString.length];
				for (int i = 0; i < mMoneysString.length; i++) {
					mds[i] = " ";
				}
			}
			if (!"".equals(mDescs.trim())) {
				for (int i = 0; i < mDescString.length; i++) {
					mds[i] = mDescString[i];
				}
			}
			this.operationDevApplyMoneyService.tranAuditInOper(checkbox, Integer.parseInt(status), mds, mms, manageUser);

		} catch (Exception e) {
			logger.debug("开发者财务运营单个审核异常！");
			e.printStackTrace();
		}
		return "redirect:manage!listDMOperAudit.do";
	}
}
