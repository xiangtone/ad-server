package cn.adwalker.ad.admin.sys.controller;

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

import cn.adwalker.ad.admin.common.controller.AbstractControllerParent;
import cn.adwalker.ad.admin.sys.bean.SysDictionaryBean;
import cn.adwalker.ad.admin.sys.service.ISysDictionarySettingService;
import cn.adwalker.ad.admin.sys.vo.SysDictionaryVo;
import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.page.SetPage;
import cn.adwalker.core.util.JacksonMapper;
import cn.adwalker.core.util.OutputHelper;
import cn.adwalker.core.vo.ResultSuccessVo;
import cn.adwalker.core.vo.ResultVo;

/**
 * <p>
 * Title: SysDictionarySettingController
 * </p>
 * <p>
 * Description:数据字典
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author zouguibao
 * @date 2013-5-20
 */
@Controller
public class SysDictionarySettingController extends AbstractControllerParent {

	/** 日志记录器 */
	Logger logger = Logger.getLogger(SysDictionarySettingController.class);

	/** 数据字典相关业务 */
	@Resource
	private ISysDictionarySettingService sysDictionarySettingService;

	/**
	 * <p>
	 * Title: listDictionarySetting
	 * </p>
	 * <p>
	 * Description:数据字典
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @return String
	 * @date 2013-5-20
	 * @version 1.0
	 * @author zouguibao
	 */
	@RequestMapping("/manage!dictionarySetting.do")
	public String listDictionarySetting(HttpServletRequest request,
			HttpServletResponse response, SysDictionaryBean bean) {
		List<SysDictionaryVo> list = null;
		IPageInfo pageInfo = new SetPage(request);
		try {
			list = this.sysDictionarySettingService.findByPage(bean, pageInfo);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("bean", bean);
			request.setAttribute("list", list);
			request.setAttribute("pageRecord", pageInfo.getTotalRow());
			request.setAttribute("page", pageInfo.getPageSize());
		} catch (Exception e) {
			logger.debug("获取数据字典列表异常！");
			e.printStackTrace();
		}
		return "sys/resource/dictionary";
	}

	/**
	 * <p>
	 * Title: findDictionaryContent
	 * </p>
	 * <p>
	 * Description:数据字典详细内容查询
	 * </p>
	 * 
	 * @param id
	 * @param request
	 * @return String
	 * @date 2013-5-20
	 * @version 1.0
	 * @author zouguibao
	 */
	@RequestMapping("/manage!findDictionaryContent.do")
	public String findDictionaryContent(HttpServletRequest request,
			HttpServletResponse response, Long id) {
		SysDictionaryVo vo = sysDictionarySettingService.findById(id);
		request.setAttribute("vo", vo);
		return "sys/resource/dictionary_edit";
	}

	/**
	 * <p>
	 * Title: updateAdContent
	 * </p>
	 * <p>
	 * Description:修改数据字典
	 * </p>
	 * updateDictionary
	 * 
	 * @param request
	 * @param response
	 * @param form
	 * @return String
	 * @date 2013-5-20
	 * @version 1.0
	 * @author zouguibao
	 */
	@RequestMapping("/manage!updateDictionary.do")
	public String updateDictionary(HttpServletRequest request,
			HttpServletResponse response, SysDictionaryBean form) {
		// System.out.println("adId = "+form.getId());
		try {
			sysDictionarySettingService.updateDictionary(form);
			ResultVo vo = new ResultSuccessVo();
			OutputHelper.outPut(response, JacksonMapper.objectToJsonString(vo));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * <p>
	 * Title: adOnline
	 * </p>
	 * <p>
	 * Description:跳转到新增字典页面
	 * </p>
	 * 
	 * @param adId
	 * @param request
	 * @return String
	 * @date 2013-5-17
	 * @version 1.0
	 * @author zouguibao
	 */
	@RequestMapping("manage!jumpAddDictionary.do")
	public String jumpAddDictionary(HttpSession session,
			Map<String, Object> model) throws Exception {
		// manageUser = (SysUserVo) session.getAttribute("manageUser");
		return "sys/resource/dictionary_add";
	}

	/**
	 * <p>
	 * Title: AddDictionary
	 * </p>
	 * <p>
	 * Description:添加数据字典
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
	@RequestMapping("manage!addDictionary.do")
	public void AddDictionary(HttpServletRequest request,
			HttpServletResponse response, SysDictionaryBean form)
			throws Exception {
		sysDictionarySettingService.addDictionary(form);
	}
}
