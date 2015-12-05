/**
 * 
 */
package cn.adwalker.ad.admin.common.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.pool.ThreadPoolUtil;
import cn.adwalker.core.util.DateUtil;
import cn.adwalker.model.common.dao.ISendMailDao;
import cn.adwalker.model.news.dao.IMailsNoticeDao;
import cn.adwalker.model.news.domain.Mail;
import cn.adwalker.ad.admin.common.service.IMailsNoticeService;
import cn.adwalker.ad.task.SendMailTask;

/**
 * 功能描述：<br>
 * 邮件服务
 * 
 * @author cai
 * 
 */
@Service(value = "mailsNoticeService")
@Transactional
public class MailsNoticeServiceImpl implements IMailsNoticeService {

	@Resource
	private IMailsNoticeDao mailsNoticeDao;
	@Resource
	private ISendMailDao sendMailDao;

	/**
	 */
	@SuppressWarnings("unchecked")
	public List<Mail> findByPage(IPageInfo pageInfo) throws Exception {
		List<Object> list = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer("T_PLATFORM_MAIL ");
		return (List<Mail>) mailsNoticeDao.findByPage("*", sb.toString(),
				list.toArray(), "  order by UPDATE_TIME desc ", Mail.class,
				pageInfo);
	}

	/**
	 */
	public Mail findById(Long id) {
		return (Mail) mailsNoticeDao.getById(id);
	}

	private Long insert(Mail newsNotice) {
		return this.mailsNoticeDao.insert(newsNotice);
	}

	@Override
	public void sendEmail(Mail newsNotice) {
		// 发送邮件信息保存
		Long id = this.insert(newsNotice);
		// 开启线程执行发送
		try {
			this.sendMailsServiceStat(id);
			System.out.println("send");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void sendMailsServiceStat(Long id) throws Exception {
		String statDate = DateUtil.getDateStringByPattern(
				DateUtil.getDateAddDay(0), "yyyy-MM-dd HH:mm:ss");
		List<Mail> mails = sendMailDao.findNotSend(id);
		ThreadPoolExecutor threadPool = ThreadPoolUtil.getThreadPool();
		if (mails != null && mails.size() > 0) {
			for (Mail mail : mails) {
				sendMailDao.updateMail(mail.getId(), statDate);
				if (mail.getType() == 1) {
					List<Mail> list = sendMailDao.findDevEmails();
					if (list != null && list.size() > 0) {
						for (Mail devEmail : list) {
							System.out.println(devEmail.getEmail());
							threadPool.execute(new SendMailTask(mail, devEmail
									.getEmail()));
						}
					}
				} else if (mail.getType() == 2) {
					List<Mail> advEmailsVo = sendMailDao.findAdvEmails();
					if (advEmailsVo != null && advEmailsVo.size() > 0) {
						for (Mail advEmail : advEmailsVo) {
							threadPool.execute(new SendMailTask(mail, advEmail
									.getEmail()));
						}
					}

				}
			}
			// threadPool.execute(new
			// ClearCacheTask(mails.get(0),"jiangwei@adwalker.com.cn"));

		}
	}

	/**
	 * 按条件查询记录数
	 * 
	 * @param condition
	 *            查询条件
	 * @return
	 * @throws Exception
	 */
	public int getCount(String condition) {
		return this.mailsNoticeDao.getCount(condition);
	}
}
