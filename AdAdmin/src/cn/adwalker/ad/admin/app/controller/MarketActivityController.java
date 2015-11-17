package cn.adwalker.ad.admin.app.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.adwalker.ad.admin.app.bean.MarketAvtivityBean;
import cn.adwalker.ad.admin.app.form.MarketAvtivityForm;
import cn.adwalker.ad.admin.app.service.IMarketActivityService;
import cn.adwalker.ad.admin.common.controller.AbstractControllerParent;
import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.page.SetPage;
import cn.adwalker.core.util.JacksonMapper;
import cn.adwalker.core.util.OutputHelper;
import cn.adwalker.core.vo.ResultSuccessVo;
import cn.adwalker.core.vo.ResultVo;
import cn.adwalker.model.app.domain.MarketActivity;

/**
 * <p>
 * Title: MarketActivityController
 * </p>
 * <p>
 * Description:市场活动
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author zouguibao
 * @date 2013-5-23
 */
@Controller
public class MarketActivityController extends AbstractControllerParent {

	/** 日志记录器 */
	Logger logger = Logger.getLogger(MarketActivityController.class);

	/** 市场活动相关业务 */
	@Resource
	private IMarketActivityService marketActivityService;

	/**
	 * <p>
	 * Title: listMarketActivitySetting
	 * </p>
	 * <p>
	 * Description:市场活动
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @return String
	 * @date 2013-5-23
	 * @version 1.0
	 * @author zouguibao
	 */
	@RequestMapping("/manage!marketactivity.do")
	public String listMarketActivitySetting(HttpServletRequest request,
			HttpServletResponse response, MarketAvtivityBean bean) {
		List<MarketActivity> list = null;
		IPageInfo pageInfo = new SetPage(request);
		try {
			list = this.marketActivityService.findByPage(bean, pageInfo);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("bean", bean);
			request.setAttribute("list", list);
			request.setAttribute("pageRecord", pageInfo.getTotalRow());
			request.setAttribute("page", pageInfo.getPageSize());
		} catch (Exception e) {
			logger.debug("获取市场活动列表异常！");
			e.printStackTrace();
		}
		return "media/app/marketactive_list";
	}

	/**
	 * <p>
	 * Title: findMarketContent
	 * </p>
	 * <p>
	 * Description:市场活动详细内容查询
	 * </p>
	 * 
	 * @param id
	 * @param request
	 * @return String
	 * @date 2013-5-23
	 * @version 1.0
	 * @author zouguibao
	 */
	@RequestMapping("/manage!findMarketContent.do")
	public String findMarketContent(HttpServletRequest request,
			HttpServletResponse response, Long id) {
		MarketActivity vo = marketActivityService.findById(id);
		request.setAttribute("vo", vo);
		return "media/app/marketactive_edit";
	}

	/**
	 * <p>
	 * Title: updateAdContent
	 * </p>
	 * <p>
	 * Description:修改市场活动
	 * </p>
	 * updateDictionary
	 * 
	 * @param request
	 * @param response
	 * @param form
	 * @return String
	 * @date 2013-5-23
	 * @version 1.0
	 * @author zouguibao
	 */
	@RequestMapping("/manage!updatadwalkerketActivity.do")
	public String updateDictionary(HttpServletRequest request,
			HttpServletResponse response, MarketAvtivityForm form) {
		try {
			marketActivityService.updatadwalkerketActivity(form);
			ResultVo vo = new ResultSuccessVo();
			OutputHelper.outPut(response, JacksonMapper.objectToJsonString(vo));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * <p>
	 * Title: adOnline
	 * </p>
	 * <p>
	 * Description:跳转到添加页面
	 * </p>
	 * 
	 * @param adId
	 * @param request
	 * @return String
	 * @date 2013-5-17
	 * @version 1.0
	 * @author zouguibao
	 */
	@RequestMapping("manage!jumpAddMarketActivity.do")
	public String jumpAddDictionary(HttpSession session,
			Map<String, Object> model) throws Exception {
		return "media/app/marketactive_add";
	}

	/**
	 * <p>
	 * Title: AddDictionary
	 * </p>
	 * <p>
	 * Description:添加市场活动
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @param form
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-5-30
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("manage!addMarketActivity.do")
	public void AddDictionary(HttpServletRequest request,
			HttpServletResponse response, MarketAvtivityForm form)
			throws Exception {
		marketActivityService.addMarketActivity(form);
	}

}
