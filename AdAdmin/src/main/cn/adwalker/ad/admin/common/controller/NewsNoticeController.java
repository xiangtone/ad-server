package cn.adwalker.ad.admin.common.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.adwalker.ad.admin.common.form.NewsNotice;
import cn.adwalker.ad.admin.common.service.INewsNoticeService;
import cn.adwalker.ad.admin.common.vo.NewsNoticeVo;
import cn.adwalker.ad.admin.common.vo.SysUserVo;
import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.page.SetPage;
import cn.adwalker.core.util.lang.ObjectUtils;

/**
 * <p>
 * Title: NewsNoticeController
 * </p>
 * <p>
 * Description:新闻公告控制层
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-7-10
 */
@Controller(value = "newsNoticeController")
public class NewsNoticeController extends AbstractControllerParent {


	@Resource
	private INewsNoticeService newsNoticeService;

	private SysUserVo manageUser;

	/**
	 * <p>
	 * Title: newsNoticeListManage
	 * </p>
	 * <p>
	 * Description:获取新闻公告列表-后台
	 * </p>
	 * 
	 * @param session
	 * @param request
	 * @return
	 * @author lichuang
	 * @date 2013-7-10
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("manage!newsNoticeListManage.do")
	public String newsNoticeListManage(HttpSession session,
			HttpServletRequest request) {
		IPageInfo pageInfo = new SetPage(request);
		try {
			List<NewsNoticeVo> newsNoticeList = this.newsNoticeService
					.findTitleList(pageInfo);
			// 装配分页信息
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("newsNoticeList", newsNoticeList);
			request.setAttribute("pageRecord", pageInfo.getTotalRow());
			request.setAttribute("page", pageInfo.getPageSize());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "manage/news/ope_news_list";
	}

	/**
	* <p>Title: editNewsNotice</p>
	* <p>Description:编辑新闻公告-后台</p>
	* @param request
	* @param id
	* @return
	* @author lichuang
	* @date 2013-7-10
	* @return String
	* @version 1.0
	 */
	@RequestMapping("manage!editNewsNotice.do")
	public String editNewsNotice(HttpServletRequest request, Long id) {
		if (ObjectUtils.isEmpty(id)) {
			return "manage/news/ope_news";
		} else {
			NewsNoticeVo newsNotice = this.newsNoticeService.findById(id);
			request.setAttribute("newsNotice", newsNotice);
			return "manage/news/ope_news_contentModify";
		}
	}

	/**
	 * 保存新闻公告-后台
	 * 
	 * @param session
	 * @param newsNotice
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping("manage!saveNewsNotice.do")
	public void saveNewsNotice(HttpSession session,
			Map<String, Object> model, NewsNotice newsNotice) throws Exception {
		manageUser = (SysUserVo) session.getAttribute("manageUser");
		if (ObjectUtils.isEmpty(newsNotice.getId())) {
			this.newsNoticeService.insert(newsNotice,manageUser);
		} else {
			this.newsNoticeService.update(newsNotice);
		}
	}

	/**
	* <p>Title: detailNewsNoticeManage</p>
	* <p>Description:获取新闻公告详细-后台</p>
	* @param request
	* @param id
	* @return
	* @author lichuang
	* @date 2013-7-10
	* @return String
	* @version 1.0
	 */
	@RequestMapping("manage!detailNewsNoticeManage.do")
	public String detailNewsNoticeManage(HttpServletRequest request, Long id) {
		NewsNoticeVo newsNotice = this.newsNoticeService.findById(id);
		request.setAttribute("newsNotice", newsNotice);
		return "manage/news/ope_news_content";
	}

	/**
	* <p>Title: deleteNewsNoticeManage</p>
	* <p>Description:删除新闻公告-后台</p>
	* @param id
	* @param model
	* @return
	* @author lichuang
	* @date 2013-7-10
	* @return String
	* @version 1.0
	 */
	@RequestMapping("manage!deleteNewsNoticeManage.do")
	public void deleteNewsNoticeManage(Long id, Map<String, Object> model) {
		try {
			this.newsNoticeService.delete(id);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param newsNoticeService
	 *            the newsNoticeService to set
	 */
	public void setNewsNoticeService(INewsNoticeService newsNoticeService) {
		this.newsNoticeService = newsNoticeService;
	}

	/**
	 * @return the manageUser
	 */
	public SysUserVo getManageUser() {
		return manageUser;
	}

	/**
	 * @param manageUser
	 *            the manageUser to set
	 */
	public void setManageUser(SysUserVo manageUser) {
		this.manageUser = manageUser;
	}

}
