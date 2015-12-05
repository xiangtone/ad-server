package cn.adwalker.ad.admin.common.service;

import java.text.ParseException;
import java.util.List;

import cn.adwalker.ad.admin.common.form.InteriorMailForm;
import cn.adwalker.ad.admin.common.vo.InteriorMailVo;
import cn.adwalker.ad.admin.common.vo.SysUserVo;
import cn.adwalker.core.page.IPageInfo;

/**
 * 功能描述<br>
 * 新闻公告服务接口
 *
 * @author guoyongxiang
 */
public interface IInteriorMailService {

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
	public List<InteriorMailVo> findTitleList(IPageInfo pageInfo) throws Exception;

	/**
	* <p>Title: findById</p>
	* <p>Description:根据ID查询编辑站内信详情</p>
	* @param id
	* @return
	* @author lichuang
	* @date 2014-1-24
	* @return InteriorMailVo
	* @version 1.0
	 */
	public InteriorMailVo findById(Long id);

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
	public void InteriorMailInsert(InteriorMailForm form,SysUserVo manageUser)throws Exception ;

	/**
	* <p>Title: update</p>
	* <p>Description:修改新闻公告</p>
	* @param from
	* @author lichuang
	* @date 2013-7-10
	* @return void
	* @version 1.0
	 */
	public void updateInteriorMail(InteriorMailForm form,SysUserVo manageUser)throws Exception ;

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