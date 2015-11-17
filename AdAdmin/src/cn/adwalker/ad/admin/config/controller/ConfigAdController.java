package cn.adwalker.ad.admin.config.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.adwalker.ad.admin.common.controller.AbstractControllerParent;
import cn.adwalker.ad.admin.config.form.ConfigSDKForm;
import cn.adwalker.ad.admin.config.service.IConfigAdService;
import cn.adwalker.ad.config.AlarmConfigConstants;
import cn.adwalker.core.pool.SpringDatePool;
import cn.adwalker.model.config.domain.ConfigPushDelay;

/**
 * 
 * <p>
 * Title: ConfigAdController
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2012-9-20
 */
@Controller
public class ConfigAdController extends AbstractControllerParent {

	/**
	 * 日志工具
	 */
	private Logger logger = Logger.getLogger(ConfigAdController.class);

	/** 服务接口 */
	@Resource
	private IConfigAdService configAdService;

	@Resource
	private SpringDatePool springDatePool;

	/**
	 * 
	 * <p>
	 * Title: getConfigFinance
	 * </p>
	 * <p>
	 * Description:获取现有配置信息
	 * </p>
	 * 
	 * @param request
	 * @return
	 * @return String
	 * @throws
	 */
	@RequestMapping("/manage!configSDK.do")
	public String getAlarmConfig(HttpServletRequest request) {
		try {
			Map<String, ConfigPushDelay> map = this.configAdService
					.getAlarmConfig();
			if (map != null && map.size() > 0) {
				String arr[] = AlarmConfigConstants.getColumns();
				for (int i = 0; i < arr.length; i++) {
					ConfigPushDelay bean = null;
					bean = map.get(arr[i]);
					request.setAttribute(arr[i], bean);
				}

			}
			return "platform/ope_alarm";
		} catch (Exception e) {
			logger.debug("获取平台财务设置信息异常！");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * <p>
	 * Title: updateConfigFinance
	 * </p>
	 * <p>
	 * Description:更新预警参数配置
	 * </p>
	 * 
	 * @param request
	 * @return
	 * @return String
	 * @throws
	 */
	@RequestMapping("/manage!updateAlarmConfig.do")
	public String updateConfigFinance(HttpServletRequest request,
			ConfigSDKForm form) {

		try {
			List<ConfigPushDelay> list = new ArrayList<ConfigPushDelay>();

			list.add(new ConfigPushDelay(
					AlarmConfigConstants.COLUMN_SCORE_DELAY_TIME, form
							.getScore_delay_time() + ""));
			this.configAdService.updateAlarmConfig(list);
			springDatePool.initSDKConfig();
		} catch (Exception e) {
			logger.debug("修改平台设置信息异常");
			e.printStackTrace();
		}
		return "";
	}

}
