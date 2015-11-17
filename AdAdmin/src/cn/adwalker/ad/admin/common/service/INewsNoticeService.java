package cn.adwalker.ad.admin.common.service;

import java.text.ParseException;
import java.util.List;

import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.ad.admin.common.form.NewsNotice;
import cn.adwalker.ad.admin.common.vo.NewsNoticeVo;
import cn.adwalker.ad.admin.common.vo.SysUserVo;

/**
 * 功能描述<br>
 * 新闻公告服务接口
 *
 * @author guoyongxiang
 */
public interface INewsNoticeService {

	/**
	* <p>Title: findTitleList</p>
	* <p>Description:查询新闻公告标题列表</p>
	* @param pageInfo
	* @return
	* @throws Exception
	* @author lichuang
	* @date 2013-7-10
	* @return List<NewsNoticeVo>
	* @version 1.0
	 */
	public List<NewsNoticeVo> findTitleList(IPageInfo pageInfo) throws Exception;

	/**
	* <p>Title: findById</p>
	* <p>Description:根据ID查询新闻公告详情</p>
	* @param id
	* @return
	* @author lichuang
	* @date 2013-7-10
	* @return NewsNoticeVo
	* @version 1.0
	 */
	public NewsNoticeVo findById(Long id);

	/**
	* <p>Title: insert</p>
	* <p>Description:添加新闻公告</p>
	* @param newsNotice
	* @param manageUser
	* @author lichuang
	* @date 2013-7-10
	* @return void
	* @version 1.0
	 */
	public void insert(NewsNotice from,SysUserVo manageUser)throws Exception ;

	/**
	* <p>Title: update</p>
	* <p>Description:修改新闻公告</p>
	* @param from
	* @author lichuang
	* @date 2013-7-10
	* @return void
	* @version 1.0
	 */
	public void update(NewsNotice from)throws Exception ;

	/**
	* <p>Title: delete</p>
	* <p>Description:新闻公告逻辑删除</p>
	* @param id
	* @throws ParseException
	* @author lichuang
	* @date 2013-7-10
	* @return void
	* @version 1.0
	 */
	public void delete(Long id) throws ParseException;
}