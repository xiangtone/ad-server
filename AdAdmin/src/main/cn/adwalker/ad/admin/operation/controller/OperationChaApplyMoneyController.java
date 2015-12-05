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
import cn.adwalker.ad.admin.operation.bean.ChaApplyMoneySearchBean;
import cn.adwalker.ad.admin.operation.service.IOperationChaApplyMoneyService;
import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.page.SetPage;
import cn.adwalker.model.app.domain.ChaApplyMoney;

/**
* <p>Title: OperationChaApplyMoneyController</p>
* <p>Description:运营渠道提款审核</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2014-2-14
 */
@Controller
public class OperationChaApplyMoneyController extends AbstractControllerParent{

	
	/** 日志记录器 */
	Logger logger = Logger.getLogger(OperationChaApplyMoneyController.class);
	
	/** 运营提款审核Service */
	@Resource
	private IOperationChaApplyMoneyService operationChaApplyMoneyService;
	/**
	* <p>Title: listChaperAudit</p>
	* <p>Description:运营渠道提款List</p>
	* @param request
	* @param response
	* @param bean
	* @return
	* @author lichuang
	* @date 2014-2-14
	* @return String
	* @version 1.0
	 */
	@RequestMapping("/manage!listChaperAudit.do")
	public String listChaperAudit(HttpServletRequest request,HttpServletResponse response,ChaApplyMoneySearchBean bean){
		List<ChaApplyMoney> list=null;
		IPageInfo pageInfo = new SetPage(request);
		try {
				list = (List<ChaApplyMoney>) this.operationChaApplyMoneyService.findAuditList(bean,pageInfo);				
			
			//添加统计数据
				ChaApplyMoney cMOperAuditSum = this.operationChaApplyMoneyService.findReportSum(bean, 0);
			request.setAttribute("dMOperAuditSum", cMOperAuditSum);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("listDMOperAudit", list);
			request.setAttribute("bean",bean);
			request.setAttribute("pageRecord", pageInfo.getTotalRow());
			request.setAttribute("page", pageInfo.getPageSize());
			
			return "manage/finance/ope_cw_chaope";
		} catch (Exception e) {
			logger.debug("获取开发者提款审核列表异常！");
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
	/**
	 * 运营单个审核,操作步骤：a：如果是审核通过状态，更新运营确认金额、运营说明字段。b：更改状态
	 * @param request
	 * @param response
	 * @param id		被审核id
	 * @param status	状态
	 * @param managerDesc	运营说明
	 * @param managerMoney	运营确认金额
	 * @return
	 */
	@RequestMapping("/manage!singleChaAuditOper.do")
	public String singleAuditOper(HttpServletRequest request){
		
		SysUserVo manageUser = (SysUserVo) request.getSession().getAttribute(
		"manageUser");// 当前登录管理员用户
		Long id = Long.parseLong(request.getParameter("id"));
		String status = request.getParameter("status");
		String mDesc = request.getParameter("mDesc");
		String mMoney = request.getParameter("mMoney");
		String invoice_suv = request.getParameter("invoice_suv");
		String invoice_status = request.getParameter("invoice_statu");
		try {
			double manageMoney = 0;
			if(!"".equals(mMoney)){
				manageMoney = Double.valueOf(mMoney);
			}
			Integer mInvoice_status = 1;
			if(!"".equals(invoice_status)){
				mInvoice_status = Integer.valueOf(invoice_status);
			}
			String invoices=null;
			if(!"".equals(invoice_suv)){
				invoices = invoice_suv;
			}
			Long[] ids = { id };
			double[] managerMoneys = { manageMoney };
			String[] managerDescs = { mDesc };
			String[] invoice = { invoices };
			Integer[] invoice_sta = { mInvoice_status };
			this.operationChaApplyMoneyService.tranAuditInOper(ids,Integer.parseInt(status), managerDescs,invoice,invoice_sta, managerMoneys,manageUser);
		} catch (Exception e) {
			logger.debug("开发者财务运营单个审核异常！");
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 运营批量审核,操作步骤：a：如果是审核通过状态，更新运营确认金额、运营说明字段。b：更改状态
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/manage!batchChaAuditOper.do")
	public String batchAuditOper(HttpServletRequest request,HttpServletResponse response,Long[] checkbox,String status,String mDescs,String mMoneys,String invoice_suvS,String invoice_statusS){
		SysUserVo manageUser = (SysUserVo) request.getSession().getAttribute(
		"manageUser");// 当前登录管理员用户
		try {
			String[] mMoneysString = mMoneys.trim().split(",");
			double[] mms = new double[mMoneysString.length];
			if(!"".equals(mMoneys.trim())){
				for(int i = 0; i < mMoneysString.length; i++){
					mms[i] = Double.valueOf((mMoneysString[i]));
				}
			}
			String[] mDescString = mDescs.trim().split(",");
			int tag = Integer.parseInt(status);
			String[] mds;
			if(tag!=1){
				 mds = new String[mDescString.length];
				 for(int i = 0; i < mDescString.length; i++){
					 mds[i] = mDescString[i];
					}
			 }else{
				 mds = new String[mMoneysString.length];
				 for(int i = 0; i < mMoneysString.length; i++){
					 mds[i] = " ";
					}
			 }
			
			if(!"".equals(mDescs.trim())){
				for(int i = 0; i < mDescString.length; i++){
					mds[i] = mDescString[i];
				}
			}
			
			String[] invoice_suvSString = invoice_suvS.trim().split(",");
			String[] mInvoices = new String[invoice_suvSString.length];
			if(!"".equals(invoice_suvS.trim())){
				for(int i = 0; i < invoice_suvSString.length; i++){
					mInvoices[i] = invoice_suvSString[i];
				}
			}
			String[] minvoice_statusSString = invoice_statusS.trim().split(",");
			Integer[] minvoice_statusS = new Integer[minvoice_statusSString.length];
			if(!"".equals(invoice_statusS.trim())){
				for(int i = 0; i < minvoice_statusSString.length; i++){
					minvoice_statusS[i] = Integer.valueOf((minvoice_statusSString[i]));
				}
			}
			this.operationChaApplyMoneyService.tranAuditInOper(checkbox,
				Integer.parseInt(status), mds, mInvoices,minvoice_statusS,mms,
					manageUser);
			
		} catch (Exception e) {
			logger.debug("开发者财务运营单个审核异常！");
			e.printStackTrace();
		}
		return null;
	}
}
