package cn.adwalker.ad.admin.ad.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.adwalker.ad.admin.ad.service.IAdPriceService;
import cn.adwalker.ad.admin.common.controller.AbstractControllerParent;
import cn.adwalker.ad.admin.common.vo.SysUserVo;
import cn.adwalker.ad.admin.operation.bean.AdPriceBean;
import cn.adwalker.ad.admin.operation.vo.AdPriceVo;
import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.page.SetPage;
import cn.adwalker.core.util.JacksonMapper;
import cn.adwalker.core.util.OutputHelper;
import cn.adwalker.core.vo.ResultSuccessVo;
import cn.adwalker.core.vo.ResultVo;

/**
 * <p>
 * Title: AppRatingController
 * </p>
 * <p>
 * Description:渠道评级
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author zouguibao
 * @date 2013-5-15
 */
@Controller
public class AdPriceController extends AbstractControllerParent {

	/** 日志记录器 */
	Logger logger = Logger.getLogger(AdPriceController.class);

	/** 广告单价相关业务 */
	@Resource
	private IAdPriceService adPriceService;

	/**
	 * <p>
	 * Title: listMediaRating
	 * </p>
	 * <p>
	 * Description:广告单价
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @return String
	 * @date 2013-5-15
	 * @version 1.0
	 * @author zouguibao
	 */
	@RequestMapping("/manage!adPriceSetting.do")
	public String listAdPrice(HttpServletRequest request,
			HttpServletResponse response, AdPriceBean bean) {
		List<AdPriceVo> list = null;
		IPageInfo pageInfo = new SetPage(request);
		try {
			list = this.adPriceService.findByPage(bean, pageInfo);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("bean", bean);
			request.setAttribute("list", list);
			request.setAttribute("pageRecord", pageInfo.getTotalRow());
			request.setAttribute("page", pageInfo.getPageSize());
		} catch (Exception e) {
			logger.debug("获取广告单价列表异常！");
			e.printStackTrace();
		}
		return "ad/ad/price";
	}

	/**
	 * <p>
	 * Title: modifyMediaRating
	 * </p>
	 * <p>
	 * Description:广告单价修改
	 * </p>
	 * 
	 * @param adId
	 * @param price
	 * @param request
	 * @return String
	 * @date 2013-5-15
	 * @version 1.0
	 * @author zouguibao
	 */
	@RequestMapping("/manage!modifyAdPrice.do")
	public String modifyAdPrice(Long adId, Double price,Double price_update,HttpSession session,
			HttpServletResponse response) {
		manageUser = (SysUserVo) session.getAttribute("manageUser");
		try {
			this.adPriceService.modifyAdPrice(adId, price,price_update,manageUser.getId());
			ResultVo vo = new ResultSuccessVo();
			OutputHelper.outPut(response, JacksonMapper.objectToJsonString(vo));
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
		}

		return null;

	}
}
