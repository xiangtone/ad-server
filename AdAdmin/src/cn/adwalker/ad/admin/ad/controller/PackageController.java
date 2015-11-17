/**
 * 
 */
package cn.adwalker.ad.admin.ad.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.adwalker.core.util.ConfigUtil;
import cn.adwalker.core.util.JacksonMapper;
import cn.adwalker.core.util.OutputHelper;
import cn.adwalker.core.vo.ResultErrorVo;
import cn.adwalker.core.vo.ResultSuccessVo;
import cn.adwalker.core.vo.ResultVo;
import cn.adwalker.model.ad.domain.Placement;
import cn.adwalker.model.ad.domain.PlacementPackage;
import cn.adwalker.ad.admin.ad.form.IosPackageForm;
import cn.adwalker.ad.admin.ad.service.IPlacementPackageService;
import cn.adwalker.ad.admin.ad.service.IPlacementService;
import cn.adwalker.ad.admin.ad.vo.AdvVo;
import cn.adwalker.ad.admin.ad.vo.PlacementPackageEditVo;
import cn.adwalker.ad.admin.common.controller.AbstractControllerParent;

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
public class PackageController extends AbstractControllerParent {

	@Resource
	private IPlacementPackageService placementPackageService;

	@Resource
	private IPlacementService placementService;

	/** 日志记录器 */
	Logger logger = Logger.getLogger(PackageController.class);

	/**
	 * 上传表页面
	 * <p>
	 * Title: uploadIco
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param advEmail
	 * @return
	 * @throws Exception
	 * @author cuidd
	 * @date 2013-5-9
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!uploadPackage.do")
	public String uploadPackage(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> model,
			Long placement_id) throws Exception {
		String result = null;
		if (placement_id != null) {
			AdvVo adv = placementPackageService
					.findAdvByPlacement(placement_id);
			Placement placement = placementService.getPlacement(placement_id);
			if (placement != null && !StringUtils.isEmpty(placement.getOs())) {
				if (placement.getOs().equals("android")) {
					List<PlacementPackage> list = placementPackageService
							.findByPlacement(placement_id);
					model.put("list", list);
					model.put("adv", adv);
					model.put("resources_url_prefix",
							ConfigUtil.getString("resources.url.prefix"));
					result = "ad/res/package_android";
				} else {
					PlacementPackageEditVo placementPackage = placementPackageService
							.getIosPackageByPlacement(placement_id);
					if (placementPackage == null) {
						placementPackage = new PlacementPackageEditVo();
					}
					if (StringUtils.isEmpty(placementPackage.getVersion_code())) {
						placementPackage.setVersion_code("1.0.1");
					}
					if (StringUtils.isEmpty(placementPackage.getVersion_name())) {
						placementPackage.setVersion_name("v");
					}
					if (placementPackage.getCreate_time() == null) {
						placementPackage.setCreate_time(new Date());
					}
					if (StringUtils.isEmpty(placementPackage.getCode())) {
						placementPackage.setCode(""
								+ System.currentTimeMillis());
					}
					if (StringUtils.isEmpty(placementPackage.getFile_name())) {
						placementPackage.setFile_name("default");
					}
					model.put("vo", placementPackage);
					
					result = "ad/res/package_ios";
				}
			}
		}
		model.put("placement_id", placement_id);
		return result;
	}

	/**
	 * 
	 * <p>
	 * Title: addIosPackage
	 * </p>
	 * <p>
	 * Description:保存广告包
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param placement_id
	 * @param form
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-5-22
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!addIosPackage.do")
	public String addIosPackage(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> model,
			Long placement_id, IosPackageForm form) throws Exception {
		ResultVo vo = null;
		placementPackageService.addIosPackage(placement_id, form);
		vo = new ResultSuccessVo();
		OutputHelper.outPut(response, JacksonMapper.objectToJsonString(vo));
		return null;
	}

	/**
	 * 
	 * <p>
	 * Title: deletePackage
	 * </p>
	 * <p>
	 * Description:删除广告包
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param id
	 * @return
	 * @throws Exception
	 * @author cuidd
	 * @date 2013-5-29
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!delPackage.do")
	public String deletePackage(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> model, Long id)
			throws Exception {
		ResultVo vo = null;
		placementPackageService.delete(id);
		vo = new ResultSuccessVo();
		OutputHelper.outPut(response, JacksonMapper.objectToJsonString(vo));
		return null;
	}

	/**
	 * 
	 * <p>
	 * Title: updatePackageCode
	 * </p>
	 * <p>
	 * Description:更新包编号
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param id
	 * @param code
	 * @return
	 * @throws Exception
	 * @author cuidd
	 * @date 2013-5-29
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!updatePackageCode.do")
	public String updatePackageCode(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> model, Long id,
			String code) throws Exception {
		ResultVo vo = null;
		boolean b = placementPackageService.query(id, code);
		if (b) {
			placementPackageService.update(id, code);
			vo = new ResultSuccessVo();
		} else {
			vo = new ResultErrorVo("包编号已存在！！");
		}
		OutputHelper.outPut(response, JacksonMapper.objectToJsonString(vo));
		return null;
	}

}
