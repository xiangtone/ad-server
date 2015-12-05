/**
 * 
 */
package cn.adwalker.ad.admin.ad.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.adwalker.ad.admin.common.controller.AbstractControllerParent;
import cn.adwalker.core.util.ConfigUtil;
import cn.adwalker.core.util.FileUploadTool;
import cn.adwalker.core.util.OutputHelper;
import cn.adwalker.core.util.TimeUtil;

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
public class UploadController extends AbstractControllerParent {

	/** 日志记录器 */
	Logger logger = Logger.getLogger(UploadController.class);

	/**
	* <p>Title: uploadIco</p>
	* <p>Description:icon图片上传</p>
	* @param request
	* @param response
	* @param model
	* @param advEmail
	* @return
	* @throws Exception
	* @author lichuang
	* @date 2013-5-30
	* @return String
	* @version 1.0
	 */
	@RequestMapping("/manage!uploadIco.do")
	public String uploadIco(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> model,
			String advEmail) throws Exception {
		String timeStamp = TimeUtil.getTimeStamp();
		String iconUrl = FileUploadTool.upload(request, "icon", new String[] {
				advEmail, timeStamp }, FileUploadTool.IMG,
				ConfigUtil.getString("upload.icon.size"),
				ConfigUtil.getString("upload.icon.cut"));

		OutputHelper.outPut(response, iconUrl);
		return null;
	}

	/**
	 * <p>
	 * Title: uploadPreviews
	 * </p>
	 * <p>
	 * Description:应用截图（上传保存）
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param advEmail
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-5-30
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!uploadPreviews.do")
	public String uploadPreviews(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> model,
			String advEmail) throws Exception {
		String timeStamp = TimeUtil.getTimeStamp();
		String preview = FileUploadTool.upload(request, "appimg", new String[] {
				advEmail, timeStamp }, FileUploadTool.IMG,
				ConfigUtil.getString("upload.preview.size"));

		OutputHelper.outPut(response, preview);
		return null;
	}

	/**
	 * 
	 * <p>
	 * Title: uploadWallBanner
	 * </p>
	 * <p>
	 * Description:上传墙banner
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param advEmail
	 * @return
	 * @author cuidd
	 * @date 2013-5-8
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!uploadWallScoreBanner.do")
	public String uploadWallScoreBanner(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> model,
			String advEmail) {
		String timeStamp = TimeUtil.getTimeStamp();
		String big_banner = null;
		try {
			big_banner = FileUploadTool.upload(request, "wall_score_banner",
					new String[] { advEmail, timeStamp }, FileUploadTool.IMG,
					ConfigUtil.getString("upload.big_banner.size"),
					ConfigUtil.getString("upload.icon.cut"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		OutputHelper.outPut(response, big_banner);
		return null;
	}
	
	
	
	@RequestMapping("/manage!uploadWallListBanner.do")
	public String uploadWallListBanner(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> model,
			String advEmail) {
		String timeStamp = TimeUtil.getTimeStamp();
		String big_banner = null;
		try {
			big_banner = FileUploadTool.upload(request, "wall_list_banner",
					new String[] { advEmail, timeStamp }, FileUploadTool.IMG,
					ConfigUtil.getString("upload.big_banner.size"),
					ConfigUtil.getString("upload.icon.cut"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		OutputHelper.outPut(response, big_banner);
		return null;
	}

	/**
	 * 
	 * <p>
	 * Title: uploadBanner
	 * </p>
	 * <p>
	 * Description:上传Banner图片
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param advEmail
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-5-30
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!uploadBanner.do")
	public String uploadBanner(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> model,
			String advEmail) throws Exception {
		String timeStamp = TimeUtil.getTimeStamp();
		String bannerUrl = FileUploadTool.upload(request, "banner",
				new String[] { advEmail, timeStamp }, FileUploadTool.IMG,
				ConfigUtil.getString("upload.banner.size"),
				ConfigUtil.getString("upload.icon.cut"));
		OutputHelper.outPut(response, bannerUrl);
		return null;
	}

	/**
	 * <p>
	 * Title: uploadChartboost_x
	 * </p>
	 * <p>
	 * Description:插屏横屏
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param advEmail
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-5-30
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!uploadChartboost_x.do")
	public String uploadChartboost_x(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> model,
			String advEmail) throws Exception {
		String timeStamp = TimeUtil.getTimeStamp();
		String plaque_height = FileUploadTool.upload(request, "chartboost_x",
				new String[] { advEmail, timeStamp }, FileUploadTool.IMG,
				ConfigUtil.getString("upload.plaque_width.size"),
				ConfigUtil.getString("upload.icon.cut"));
		OutputHelper.outPut(response, plaque_height);
		return null;
	}

	/**
	 * <p>
	 * Title: uploadChartboost_y
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
	 * @author lichuang
	 * @date 2013-5-30
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!uploadChartboost_y.do")
	public String uploadChartboost_y(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> model,
			String advEmail) throws Exception {
		String timeStamp = TimeUtil.getTimeStamp();
		String plaque_width = FileUploadTool.upload(request, "chartboost_y",
				new String[] { advEmail, timeStamp }, FileUploadTool.IMG,
				ConfigUtil.getString("upload.plaque_height.size"),
				ConfigUtil.getString("upload.icon.cut"));
		OutputHelper.outPut(response, plaque_width);
		return null;
	}
	/**
	* <p>Title: uploaddataPhotoUrl_x</p>
	* <p>Description:保存截图地址</p>
	* @param request
	* @param response
	* @param model
	* @param advEmail
	* @return
	* @throws Exception
	* @author lichuang
	* @date 2013-10-24
	* @return String
	* @version 1.0
	 */
	@RequestMapping("/manage!uploadDataPhotoUrl_x.do")
	public String uploaddataPhotoUrl_x(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> model,long id) throws Exception {
		String timeStamp = TimeUtil.getTimeStamp();
		String plaque_height = FileUploadTool.upload(request, "email_data_x",
				new String[] {String.valueOf(id), timeStamp }, FileUploadTool.IMG);
		OutputHelper.outPut(response, plaque_height);
		return null;
	}
}
