package cn.adwalker.ad.admin.channel.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.adwalker.ad.admin.channel.bean.SearchChannelBean;
import cn.adwalker.ad.admin.channel.form.ChannelAddForm;
import cn.adwalker.ad.admin.channel.form.ChannelBankInfo;
import cn.adwalker.ad.admin.channel.form.UpdateChannel;
import cn.adwalker.ad.admin.channel.service.IChannelService;
import cn.adwalker.ad.admin.channel.vo.ChannelAndBankinfoVo;
import cn.adwalker.ad.admin.channel.vo.ChannelBankInfoVo;
import cn.adwalker.ad.admin.channel.vo.ChannelListVo;
import cn.adwalker.ad.admin.channel.vo.ChannelVo;
import cn.adwalker.ad.admin.common.controller.AbstractControllerParent;
import cn.adwalker.ad.admin.common.vo.SysUserVo;
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
 * Description:渠道管理
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-4-2
 */
@Controller
public class ChannelController extends AbstractControllerParent {

	@Resource
	private IChannelService channelService;

	/** 管理员实体 */
	private SysUserVo manageUser;
	/** 日志记录器 */
	Logger logger = Logger.getLogger(ChannelController.class);

	/**
	 * <p>
	 * Title: addChannel
	 * </p>
	 * <p>
	 * Description:渠道添加界面
	 * </p>
	 * 
	 * @param session
	 * @param model
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-5-15
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!addchannel.do")
	public String addChannel(HttpSession session, Map<String, Object> model)
			throws Exception {
		logger.debug("into method addResourceManage: 管理员添加渠道");
		manageUser = (SysUserVo) session.getAttribute("manageUser");
		return "channel/channel_add";
	}

	/**
	 * <p>
	 * Title: regist
	 * </p>
	 * <p>
	 * Description:渠道注册
	 * </p>
	 * 
	 * @param request
	 * @param model
	 * @param session
	 * @param code
	 * @param registChannelVo
	 * @param channelBankInfoVo
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-4-11
	 * @return String
	 * @version 1.0
	 */

	@RequestMapping("/manage!addAccountChannel.do")
	public String save(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> model,
			HttpSession session, String code, ChannelAddForm channelAddForm) throws Exception {
		manageUser = (SysUserVo) session.getAttribute("manageUser");
		ResultVo resultVo = null;
		if (channelService.exists(channelAddForm.getEmail())) {
			resultVo = new ResultErrorVo("添加失败!可能帐号已存在!");
		}
		try {
			channelService.channelAddForm(channelAddForm, manageUser);
			resultVo = new ResultSuccessVo();
		} catch (Exception e) {
			e.printStackTrace();
			resultVo = new ResultErrorVo("操作失败请重试");
		}
		OutputHelper.outPut(response,
				JacksonMapper.objectToJsonString(resultVo));

		return null;

	}

	/**
	 * <p>
	 * Title: editChannel
	 * </p>
	 * <p>
	 * Description:渠道修改进入界面
	 * </p>
	 * 
	 * @param request
	 * @param session
	 * @param model
	 * @param id
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-4-16
	 * @return String
	 * @version 1.0
	 * @throws
	 */
	@RequestMapping("/manage!editchannel.do")
	public String editChannel(HttpServletRequest request, HttpSession session,
			Map<String, Object> model, Long id) throws Exception {
		logger.debug("into method addResourceManage: 管理员添加渠道");
		manageUser = (SysUserVo) session.getAttribute("manageUser");
		ChannelVo vo = channelService.getChannel(id);
		ChannelBankInfoVo channelBankInfoVo = channelService
				.getBankInfoService(id);
		request.setAttribute("entiy", vo);
		request.setAttribute("channelBankInfo", channelBankInfoVo);
		return "channel/channel_edit";
	}

	/**
	 * <p>
	 * Title: findChannelList
	 * </p>
	 * <p>
	 * Description:查看渠道list
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @param channelBean
	 * @return
	 * @author lichuang
	 * @date 2013-4-11
	 * @return String
	 * @version 1.0
	 * @throws
	 */

	@RequestMapping("/manage!findChannelList.do")
	public String list(HttpServletRequest request,
			HttpServletResponse response, SearchChannelBean bean) {
		List<ChannelListVo> list = null;
		IPageInfo pageInfo = new SetPage(request);
		try {
			list = this.channelService.findByPage(bean, pageInfo);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("list", list);
			request.setAttribute("bean", bean);
			request.setAttribute("pageRecord", pageInfo.getTotalRow());
			request.setAttribute("page", pageInfo.getPageSize());

		} catch (Exception e) {
			logger.debug("获取广告效果列表异常！");
			e.printStackTrace();
		}
		return "channel/channel_list";
	}

	/**
	 * <p>
	 * Title: channelSeal
	 * </p>
	 * <p>
	 * Description:封号
	 * </p>
	 * 
	 * @param request
	 * @param model
	 * @param session
	 * @param code
	 * @param id
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-4-11
	 * @return String
	 * @version 1.0
	 * @throws
	 */
	@RequestMapping("/manage!channelSeal.do")
	public void channelSeal(HttpServletRequest request,
			Map<String, Object> model, HttpSession session, String code, Long id)
			throws Exception {
		logger.debug("into method publishActivity: 操作人渠道下线");
		manageUser = (SysUserVo) session.getAttribute("manageUser");
		try {
			channelService.channelSealStatus(id, manageUser);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * <p>
	 * Title: channelActivation
	 * </p>
	 * <p>
	 * Description:激活
	 * </p>
	 * 
	 * @param request
	 * @param model
	 * @param session
	 * @param code
	 * @param id
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-4-11
	 * @return String
	 * @version 1.0
	 * @throws
	 */
	@RequestMapping("/manage!channelActivation.do")
	public void channelActivation(HttpServletRequest request,
			Map<String, Object> model, HttpSession session, String code, Long id)
			throws Exception {
		logger.debug("into method publishActivity: 操作人渠道下线");
		manageUser = (SysUserVo) session.getAttribute("manageUser");
		try {
			channelService.channelActivationStatus(id, manageUser);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * <p>
	 * Title: resetChannel
	 * </p>
	 * <p>
	 * Description:渠道重置密码
	 * </p>
	 * 
	 * @param request
	 * @param model
	 * @param session
	 * @param code
	 * @param id
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-5-14
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!resetChannel.do")
	public void resetChannel(HttpServletRequest request,
			Map<String, Object> model, HttpSession session, String code, Long id)
			throws Exception {
		logger.debug("into method publishActivity: 操作人渠道重置密码");
		manageUser = (SysUserVo) session.getAttribute("manageUser");
		try {
			channelService.setPasswoid(id, manageUser);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * <p>
	 * Title: updateChannel
	 * </p>
	 * <p>
	 * Description:渠道修改
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param session
	 * @param code
	 * @param updateChannel
	 * @param bankInfo
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-5-14
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!updateChannel.do")
	public String updateChannel(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> model,
			HttpSession session, String code, UpdateChannel updateChannel,
			ChannelBankInfo bankInfo) throws Exception {
		manageUser = (SysUserVo) session.getAttribute("manageUser");
		ResultVo vo = null;
		try {
			channelService.updateChannel(updateChannel, bankInfo, manageUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
		vo = new ResultSuccessVo();
		OutputHelper.outPut(response, JacksonMapper.objectToJsonString(vo));
		return null;

	}

	@RequestMapping("/manage!delChannel.do")
	public String delChannel(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> model, String ids)
			throws Exception {
		ResultVo vo = null;
		try {
			channelService.delChannel(ids);
		} catch (Exception e) {
			e.printStackTrace();
		}
		vo = new ResultSuccessVo();
		OutputHelper.outPut(response, JacksonMapper.objectToJsonString(vo));
		return null;

	}

	/**
	 * <p>
	 * Title: channelInfo
	 * </p>
	 * <p>
	 * Description:渠道基本信息查看
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @param chaId
	 * @return
	 * @author lichuang
	 * @date 2013-5-17
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!channelInfo.do")
	public String channelInfo(HttpServletRequest request,
			HttpServletResponse response, Long chaId) {
		ChannelAndBankinfoVo channel = null;
		try {
			channel = this.channelService.findChanInfo(chaId);
			request.setAttribute("vo", channel);
		} catch (Exception e) {
			logger.debug("获取广告效果列表异常！");
			e.printStackTrace();
		}
		return "channel/channel_info";
	}

	/**
	 * <p>
	 * Title: channelemail
	 * </p>
	 * <p>
	 * Description:账户是否存在
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @param oldpass
	 * @return
	 * @throws Exception
	 * @author cuidd
	 * @date 2014年6月10日
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!channelemail.do")
	public String channelemail(HttpServletRequest request,
			HttpServletResponse response, String oldpass) throws Exception {

		if (channelService.exists(oldpass)) {
			OutputHelper.outPut(response, "false");
		} else {
			OutputHelper.outPut(response, "true");
		}
		return null;
	}
}
