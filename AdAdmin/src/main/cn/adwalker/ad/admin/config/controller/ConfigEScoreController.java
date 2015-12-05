/**
 * 
 */
package cn.adwalker.ad.admin.config.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.adwalker.ad.admin.common.controller.AbstractControllerParent;
import cn.adwalker.ad.admin.config.form.ConfigEScoreForm;
import cn.adwalker.ad.admin.config.service.IConfigEScoreService;
import cn.adwalker.core.pool.SpringDatePool;
import cn.adwalker.model.config.domain.ConfigEScore;

/**
 * @author wjp 平台设置
 */
@Controller
public class ConfigEScoreController extends AbstractControllerParent {

	/** 日志记录器 */
	Logger logger = Logger.getLogger(ConfigEScoreController.class);

	/** 平台设置相关服务 */
	@Resource
	private IConfigEScoreService configEScoreService;

	/**  */
	@Resource
	private SpringDatePool springDatePool;

	/**
	 * 获取设置信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/manage!configEScoreInfo.do")
	public String getConfigEScoreInfo(HttpServletRequest request) {
		try {
			ConfigEScore bean;
			try {
				bean = this.configEScoreService.getPlatformConfig();

			} catch (Exception e) {
				bean = new ConfigEScore();
			}
			request.setAttribute("configEScore", bean);
			return "platform/ope_pt";
		} catch (Exception e) {
			logger.debug("获取平台设置信息异常！");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * <p>
	 * Title: updateConfigEScore
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param request
	 * @return
	 * @author lichuang
	 * @date 2013-5-30
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!updateConfigEScore.do")
	public String updateConfigEScore(HttpServletRequest request,
			ConfigEScoreForm form) {

		try {
			ConfigEScore configEScore = new ConfigEScore();
			configEScore.setDevApplyMinMoney(form.getDevApplyMinMoney());
			configEScore.setDev_min_draw_money(form.getDev_min_draw_money());
			configEScore.setId(form.getId());
			if (form.getId() == 0) {// 添加
				this.configEScoreService.insert(configEScore);
			} else {// 修改
				this.configEScoreService.updateById(configEScore);
			}
			this.springDatePool.initPlatformConfig();
		} catch (Exception e) {
			logger.debug("修改平台设置信息异常");
			e.printStackTrace();
			try {
			} catch (Exception e2) {
			}
		}
		return "";
	}
}
