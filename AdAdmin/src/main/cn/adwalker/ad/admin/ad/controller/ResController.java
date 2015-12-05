/**
 * 
 */
package cn.adwalker.ad.admin.ad.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.adwalker.ad.admin.ad.form.ResForm;
import cn.adwalker.ad.admin.ad.service.IPlacementService;
import cn.adwalker.ad.admin.ad.vo.ResVo;
import cn.adwalker.ad.admin.common.controller.AbstractControllerParent;
import cn.adwalker.core.pool.SpringDatePool;
import cn.adwalker.core.util.ConfigUtil;
import cn.adwalker.core.util.JacksonMapper;
import cn.adwalker.core.util.OutputHelper;
import cn.adwalker.core.vo.ResultSuccessVo;
import cn.adwalker.core.vo.ResultVo;

/**
 * 
 * <p>
 * Title: CampaignController
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-4-2
 */
@Controller
public class ResController extends AbstractControllerParent {

	@Resource
	private IPlacementService placementService;

	@Resource
	private SpringDatePool springDatePool;

	/** 日志记录器 */
	public static final Logger logger = Logger.getLogger(ResController.class);


	/**
	 * <p>
	 * Title: addMaterialWall
	 * </p>
	 * <p>
	 * Description:墙（添加素材）
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 * @author lichuang
	 * @date 2013-4-17
	 * @return String
	 * @version 1.0
	 * @throws
	 */
	@RequestMapping("/manage!editMaterial.do")
	public String editMaterial(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> model, Long id) {
		try {
			String advEmail = placementService.getAdvEmailByPlacement(id);
			ResVo vo = placementService.getRes(id);
			String typeStr = placementService.getTypeStr(id);
			if (vo.getScore_delay() == null) {
				vo.setScore_delay(springDatePool.getSDKConfig()
						.getScore_delay_time());

			}
			model.put("page_type", typeStr);
			model.put("vo", vo);
			model.put("advEmail", advEmail);
			model.put("placement_id", id);

			model.put("resources_url_prefix",
					ConfigUtil.getString("resources.url.prefix"));
			model.put("images_url_prefix",
					ConfigUtil.getString("images.url.prefix"));

		} catch (Exception e) {
			logger.debug("获取广告效果列表异常！");
			e.printStackTrace();
		}
		return "ad/res/res_edit";
	}

	/**
	 * <p>
	 * Title: saveMaterialWall
	 * </p>
	 * <p>
	 * Description:保存素材
	 * </p>
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @param id
	 * @param form
	 * @param appimg_url
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-5-21
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!updateMaterial.do")
	public String updateMaterial(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response, Long id,
			ResForm form, String appimg_url[]) throws Exception {
		ResultVo resultVo = null;
		form.setAppimg_url(appimg_url);
		placementService.updateMaterial(id, form);
		resultVo = new ResultSuccessVo();
		OutputHelper.outPut(response,
				JacksonMapper.objectToJsonString(resultVo));
		return null;
	}
}
