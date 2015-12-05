/*
 * INewsNoticeDao.java
 */
package cn.adwalker.model.news.dao;

import java.util.List;

import cn.adwalker.core.repository.IBaseDao;
import cn.adwalker.model.news.domain.Mail;

/**
 * 功能描述<br>
 * 邮件管理接口
 * 
 * @author cai
 */
public interface IMailsNoticeDao extends IBaseDao<Mail> {

	/**
	 * 添加要发送的邮件
	 * 
	 * @param newsNotice
	 * @return
	 */
	public Long insert(Mail newsNotice);

	/**
	 * 查询邮件管理列表-分页
	 * 
	 * @param pageIndex
	 *            当前页数
	 * @param pageRecord
	 *            每页条数
	 * @return
	 * @throws Exception
	 */
	public List<Mail> findAll(final int pageIndex, final int pageRecord)
			throws Exception;

	/**
	 * 查询邮件管理标题列表-分页
	 * 
	 * @param pageIndex
	 *            当前页数
	 * @param pageRecord
	 *            每页条数
	 * @return
	 * @throws Exception
	 */
	public List<Mail> findTitleList(final int pageIndex, final int pageRecord)
			throws Exception;

	/**
	 * 根据ID查询
	 * 
	 * @param id
	 *            邮件管理ID
	 * @return
	 */
	public Mail getById(Long id);
}