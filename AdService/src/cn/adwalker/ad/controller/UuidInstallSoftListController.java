package cn.adwalker.ad.controller;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.adwalker.ad.exception.AdwalkerException;
import cn.adwalker.ad.exception.ExceptionCode;
import cn.adwalker.ad.logger.Ad_enull;
import cn.adwalker.ad.service.IUuidInstallSoftListService;
import cn.adwalker.ad.util.AppConstant;
import cn.adwalker.ad.util.Des3;
import cn.adwalker.ad.util.PublicUtil;
/**
 * 记录用户手机安装应用log日志
* <p>Title: UuidInstallSoftListController</p>
* <p>Description:记录用户手机安装软件列表信息</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-1-5
 */
 
@Controller
public class UuidInstallSoftListController {

	private static final Logger log = LoggerFactory.getLogger(UuidInstallSoftListController.class);

	/** 依赖注入 **/
	@Resource
	private IUuidInstallSoftListService uuidInstallSoftListService;
	
	/**
	 * 
	* <p>Title: appList</p>
	* <p>Description:用户安装软件列表</p>
	* @param request
	* @param response
	* @return
	* @throws IOException
	* @author cuidd
	* @date 2014年10月10日
	* @return ModelAndView
	* @version 1.0
	* @throws
	 */
	@RequestMapping("/common/app_list.do")
	public ModelAndView appList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		long start = System.currentTimeMillis();
		//由于用户本地手机安装列表名称中包含&等特殊符号，需要特殊处理下
		String req_param=request.getParameter("m");
		if (StringUtils.isEmpty(req_param)) {
			PublicUtil.installErrorVO(ExceptionCode.REQUEST_KEY_NOT_MATCH);
		}
		log.info("userSoftList解密前：" + req_param);
		String str = null;
		try {
			str = Des3.decode(req_param);
			log.info("userSoftList解密后：" + str);
		} catch (Exception e1) {
			PublicUtil.installErrorVO(ExceptionCode.REQUEST_KEY_NOT_MATCH);
			e1.printStackTrace();
			Ad_enull enull = new Ad_enull();
			enull.setTitle("android---->:" + e1.toString());
			enull.softAd_enull();
			return null;
		}
		
		Map<String,String> paramsMap=PublicUtil.parseParameter(str);
		if(paramsMap==null){
			PublicUtil.returnValue(response, AppConstant.STATUS_ERROR, PublicUtil.installErrorJVO(ExceptionCode.REQUEST_PARAM_INVALID));
			log.error("parameter has null!");
			return null;
		}
		String uuid = paramsMap.get("uuid");// 终端用户ID
		if(uuid==null){
			uuid = paramsMap.get("mac");
		}
		
		String softList = paramsMap.get("softList");
		// 2.参数处理
		boolean isNull = PublicUtil.paramsIsNull(softList);
		if (!isNull) {
			PublicUtil.returnValue(response, AppConstant.STATUS_ERROR, PublicUtil.installErrorJVO(ExceptionCode.REQUEST_PARAM_INVALID));
			log.error("parameter has null!");
			return null;
		}
		String appkey=paramsMap.get("appkey");
		String sdk_version=paramsMap.get("version");
		
		//用户本地的安装列表
		// 3.逻辑处理
		try {
			//解析终端用户手机应用列表
			this.uuidInstallSoftListService.softListToDB(uuid,softList,appkey,sdk_version);
		} catch (AdwalkerException e) {
			log.error(e.toString()+"处理用户安装软件列表请求失败。。，请求参数为："+paramsMap.get(AppConstant.REQUENST_PARAM_KEY));
			PublicUtil.returnValue(response, AppConstant.STATUS_ERROR, e.getError());
			return null;
		} catch (Exception e) {
			log.error(e.toString()+"处理用户安装软件列表产生系统异常。。，请求参数为："+paramsMap.get(AppConstant.REQUENST_PARAM_KEY));
			e.printStackTrace();
			PublicUtil.returnValue(response, AppConstant.STATUS_ERROR, PublicUtil.installErrorJVO(ExceptionCode.SERVER_ERROR));
			return null;
		}
		// 4.返回值
		PublicUtil.returnValue(response, AppConstant.STATUS_OK, null);
		log.info("/common/ uuid_softlistE.do end.cost time:" + (System.currentTimeMillis() - start));
		return null;
	}
}
