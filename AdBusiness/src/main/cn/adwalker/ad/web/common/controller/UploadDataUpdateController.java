package cn.adwalker.ad.web.common.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.common.util.ad.ApkParser;
import org.common.util.ad.ApkParserResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.adwalker.ad.model.application.domain.Application;
import cn.adwalker.ad.util.ConfigUtil;
import cn.adwalker.ad.util.JacksonMapper;
import cn.adwalker.ad.web.common.vo.UploadResultVo;
import cn.adwalker.ad.web.developer.service.ApplicationService;

/**
 * 功能描述<br>
 * 更新上传信息 控制层
 */
@Controller
public class UploadDataUpdateController {

	private static Log log = LogFactory.getLog(UploadDataUpdateController.class);

	@Resource
	private ApplicationService applicationService;

	/**
	 * 更新应用信息
	 * 
	 * @param id
	 * @param path
	 * @param response
	 */
	@RequestMapping("/updateAppDate.action")
	public void updateAppDate(long id, String path, HttpServletResponse response) {
		try {
			path = java.net.URLDecoder.decode(path, "UTF-8");
		} catch (UnsupportedEncodingException e2) {
			e2.printStackTrace();
			log.error("编码转换失败！");
		}
		String tar = ConfigUtil.getString("file.path");// 读取文件地址前缀
		Application developedApp = new Application();
		developedApp.setId(id);
		developedApp.setProjectUrl(path);
		UploadResultVo uploadResultVo = new UploadResultVo();
		try {
			// ========此处解包处理流程start==========
			ApkParserResult pr = new ApkParserResult();
			try {
				if (log.isDebugEnabled()) {
					log.debug("上传完成路径为:" + tar + path);
					log.debug("上传相对路径为:" + path);
				}
				if (path != null) {
					if (path.toLowerCase().endsWith(".apk")) {
						pr = ApkParser.getApkInfo(tar + path);
						System.out.println("android系统解包结束");
					}
				} else {
					log.error("解包失败,未能获取到path！");
					uploadResultVo.setStatus("err");
					uploadResultVo.setErrMag("解包失败,未能获取到path！");
				}
				// 装配解包信息

				developedApp.setResourceSize(pr.getSize());
				developedApp.setPackageName(pr.getPackageName());
				developedApp.setVersionCode(pr.getVersionCode());
				developedApp.setVersionName(pr.getVersionName());
			} catch (Exception e1) {
				e1.printStackTrace();
				log.error("解包失败未能更新解包相关信息,path:" + path);
				uploadResultVo.setStatus("err");
				uploadResultVo.setErrMag("解包失败未能更新解包相关信息,path:" + path);
			}
			// ========此处解包处理流程end==========
			uploadResultVo.setPath(path);// 相对路径
			uploadResultVo.setFileName(path.substring(path.lastIndexOf("/") + 1));// 截取文件名
			uploadResultVo.setUrlPrefix(ConfigUtil.getString("resources.url.prefix"));
			developedApp.setStatus(null);
			applicationService.update(developedApp);// 更新文件信息
			System.out.println("数据更新结束");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("文件储存地址更新失败！");
			uploadResultVo.setStatus("err");
			uploadResultVo.setErrMag("文件储存地址更新失败！");
		}
		writerJSON(uploadResultVo, response);
	}

	/**
	 * 更新应用icon
	 * 
	 * @param id
	 * @param path
	 * @param response
	 */
	@RequestMapping("/updateAppIcon.action")
	public void updateAppIcon(long id, String path, HttpServletResponse response) {
		Application developedApp = new Application();
		developedApp.setId(id);
		developedApp.setIconUrl(path);
		UploadResultVo uploadResultVo = new UploadResultVo();
		try {
			uploadResultVo.setPath(path);// 相对路径
			uploadResultVo.setFileName(path.substring(path.lastIndexOf("/") + 1));// 截取文件名
			uploadResultVo.setUrlPrefix(ConfigUtil.getString("images.url.prefix"));
			applicationService.update(developedApp);// 更新文件信息
			System.out.println("数据更新结束");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("icon储存地址更新失败！");
			uploadResultVo.setStatus("err");
			uploadResultVo.setErrMag("icon储存地址更新失败！");
		}
		writerJSON(uploadResultVo, response);
	}

	/**
	 * 更新应用预览图
	 * 
	 * @param id
	 * @param path
	 * @param response
	 */
	@RequestMapping("/updateAppScreenshot.action")
	public void updateAppScreenshot(long id, int sort, String[] paths, HttpServletResponse response) {
		List<String> appScreenshots = new ArrayList<String>();
		Collections.addAll(appScreenshots, paths);
		UploadResultVo uploadResultVo = new UploadResultVo();
		uploadResultVo.setSort(sort);
		try {
			uploadResultVo.setPaths(paths);
			uploadResultVo.setUrlPrefix(ConfigUtil.getString("images.url.prefix"));
		} catch (Exception e) {
			e.printStackTrace();
			log.error("preview储存地址更新失败！");
			uploadResultVo.setStatus("err");
			uploadResultVo.setErrMag("preview储存地址更新失败！");
		}
		writerJSON(uploadResultVo, response);
	}

	/**
	 * 共用方法，ajax打印对象类型的json串
	 * 
	 * @param object
	 * @param reponse
	 */
	public void writerJSON(Object object, HttpServletResponse response) {
		try {
			String json = JacksonMapper.objectToJsonString(object);
			Writer writer = response.getWriter();
			writer.write(json);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void setLog(Log log) {
		UploadDataUpdateController.log = log;
	}
}
