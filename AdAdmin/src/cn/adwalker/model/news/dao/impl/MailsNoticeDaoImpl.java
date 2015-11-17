package cn.adwalker.model.news.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import cn.adwalker.core.repository.impl.BaseDaoImpl;
import cn.adwalker.model.news.dao.IMailsNoticeDao;
import cn.adwalker.model.news.domain.Mail;

/**
 * 功能概述：<br>
 * 新闻公共实现类
 * 
 * @author cai
 */
@Repository("mailsNoticeDao")
public class MailsNoticeDaoImpl extends BaseDaoImpl<Mail> implements IMailsNoticeDao {

	public Long insert(Mail newsNotice) {
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into T_PLATFORM_MAIL(");
		sql.append("CREATE_USER_ID,");
		sql.append("TITLE,");
		sql.append("CONTENT,");
		sql.append("TYPE,");
		sql.append("UPDATE_TIME) VALUES(");
		sql.append(":manager_id,");
		sql.append(":title,");
		sql.append(":content,");
		sql.append(":type,");
		sql.append(":update_time)");
		return super.insert(sql.toString(), newsNotice);
	}

	/**
	 */
	@SuppressWarnings(value = "unchecked")
	public List<Mail> findAll(final int pageIndex, final int pageRecord) throws Exception {
		String sql = "select * from T_PLATFORM_MAIL order by UPDATE_TIME desc";
		return (List<Mail>) this.findAll(sql, Mail.class);
	}

	@SuppressWarnings(value = "unchecked")
	public List<Mail> findTitleList(final int pageIndex, final int pageRecord) throws Exception {
		String sql = "select ID,TITLE,TYPE,UPDATE_TIME from T_PLATFORM_MAIL order by UPDATE_TIME desc";
		return (List<Mail>) this.findAll(sql, Mail.class);
	}

	/**
	 * @see cn.adwalker.model.news.dao.onekchi.escore.server.manage.dao.impl.INewsNoticeDao#findById(java.lang.Long)
	 */
	public Mail getById(Long id) {
		String sql = "select * from T_PLATFORM_MAIL where ID=?";
		return (Mail) jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Mail>(Mail.class), id);
	}

	/**
	 * 按条件查询记录数
	 * 
	 * @param condition 查询条件
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	public int getCount(String condition) {
		StringBuffer sql = new StringBuffer();
		sql.append("select count(*) from T_PLATFORM_NOTICE where 1=1");
		if (!"".equals(condition.trim())) {
			sql.append(" " + condition); // 如果条件为空则取全部
		}
		return this.jdbcTemplate.queryForInt(sql.toString());
	}
}
