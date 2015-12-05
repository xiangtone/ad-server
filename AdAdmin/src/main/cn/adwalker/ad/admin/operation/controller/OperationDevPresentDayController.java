package cn.adwalker.ad.admin.operation.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.page.SetPage;
import cn.adwalker.ad.admin.common.controller.AbstractControllerParent;
import cn.adwalker.ad.admin.common.vo.SysUserVo;
import cn.adwalker.ad.admin.operation.bean.OperDevPresentbean;
import cn.adwalker.ad.admin.operation.service.IOperationDevPresentDayServer;
import cn.adwalker.ad.admin.operation.vo.OperationDevPresentDayVo;

/**
* <p>Title: OperationDevPresentDayController</p>
* <p>Description:开发者奖励记录</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-9-17
 */

@Controller(value = "OperationDevPresentDayController")
public class OperationDevPresentDayController extends AbstractControllerParent{

	@Resource
	private IOperationDevPresentDayServer OperationDevPresentDayServer;


	/**
	 * 
	 * <p>
	 * Title: devPresentStatListManage
	 * </p>
	 * <p>
	 * Description:开发者奖励记录List
	 * </p>
	 * 
	 * @param session
	 * @param request
	 * @param bean
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-3-11
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!devRewardList.do")
	public String devRewardList(HttpSession session,
			HttpServletRequest request, OperDevPresentbean bean)
			throws Exception {
		manageUser = (SysUserVo) session.getAttribute("manageUser");
		IPageInfo pageInfo = new SetPage(request);
		List<OperationDevPresentDayVo> list = null;
		double reward_money = 0d;
		try {
			list = this.OperationDevPresentDayServer.findByRewardList(bean,pageInfo);
			reward_money = OperationDevPresentDayServer.findSumReward(bean,pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("entry", list);
		request.setAttribute("reward_money", reward_money);
		request.setAttribute("bean", bean);
		request.setAttribute("pageRecord", pageInfo.getTotalRow());
		request.setAttribute("page", pageInfo.getPageSize());
		return "manage/report/ope_data_present";
	}
}
