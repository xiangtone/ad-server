/*
 * INewsNoticeService.java
 */
package cn.adwalker.ad.admin.common.service;

import java.util.List;

import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.model.news.domain.Mail;

/**
 * 功能描述<br>
 * 群发邮件接口  
 *
 * @author cai
 */
public interface IMailsNoticeService {

	/**
	 * 查询已发送邮件的标题列表
	 * @param pageIndex
	 * @param pageRecord
	 * @return
	 * @throws Exception
	 */
	public List<Mail> findByPage(IPageInfo pageInfo) throws Exception;

	/**
	 * 根据ID查询邮件详情
	 * @param id
	 * @return
	 */
	public Mail findById(Long id);

	
	
	/**
	* <p>Title: sendEmail</p>
	* <p>Description:发送邮件</p>
	* @param newsNotice
	* @return void
	* @throws
	*/
	void sendEmail(Mail newsNotice);

}