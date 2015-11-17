package cn.adwalker.model.news.dao;

import java.util.List;

import cn.adwalker.core.repository.IBaseDao;
import cn.adwalker.model.news.domain.NewsNoticedomain;

/**
 * 功能描述<br>
 * 新闻公告实现类接口
 * 
 * @author guoyongxiang
 */
public interface INewsNoticeDao extends IBaseDao<NewsNoticedomain> {

	/**
	 * 查询新闻公告列表
	 * 
	 * @return
	 */
	public List<NewsNoticedomain> findAll();

	/**
	 * 查询新闻公告列表-分页
	 * 
	 * @param pageIndex
	 *            当前页数
	 * @param pageRecord
	 *            每页条数
	 * @return
	 * @throws Exception
	 */
	public List<NewsNoticedomain> findAll(final int pageIndex,
			final int pageRecord) throws Exception;

	/**
	 * <p>
	 * Title: findById
	 * </p>
	 * <p>
	 * Description:新闻公告ID
	 * </p>
	 * 
	 * @param id
	 * @return
	 * @author lichuang
	 * @date 2013-7-10
	 * @return NewsNotice
	 * @version 1.0
	 */
	public NewsNoticedomain findById(Long id);

	/**
	 * 根据ID删除（物理删除）
	 * 
	 * @param id
	 *            新闻公告ID
	 * @return
	 */
	public Integer deleteById(Long id);


	/**
	 * <p>
	 * Title: updateDel
	 * </p>
	 * <p>
	 * Description:删除
	 * </p>
	 * 
	 * @param newsNotice
	 * @return
	 * @author lichuang
	 * @date 2013-7-10
	 * @return int
	 * @version 1.0
	 */
	public int updateDel(NewsNoticedomain newsNotice);

}