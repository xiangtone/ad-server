package cn.adwalker.model.news.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import cn.adwalker.core.repository.impl.BaseDaoImpl;
import cn.adwalker.core.util.lang.ObjectUtils;
import cn.adwalker.model.news.dao.INewsNoticeDao;
import cn.adwalker.model.news.domain.NewsNoticedomain;

/**
 * 功能概述：<br>
 * 新闻公共实现类
 * 
 * @author guoyongxiang
 */
@Repository("newsNoticeDao")
public class NewsNoticeDaoImpl extends BaseDaoImpl<NewsNoticedomain> implements INewsNoticeDao {

	@Resource
	private JdbcTemplate jdbcTemplate;

	public NewsNoticeDaoImpl() {
		super();
		this.setTableName("T_PLATFORM_NOTICE");
	}

	/**
	 * @see cn.adwalker.model.news.dao.onekchi.escore.server.manage.dao.impl.INewsNoticeDao#findAll()
	 */
	public List<NewsNoticedomain> findAll() {
		String sql = "select * from T_PLATFORM_NOTICE order by UPDATE_TIME desc";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<NewsNoticedomain>(NewsNoticedomain.class));
	}

	/**
	 * @see cn.adwalker.model.news.dao.onekchi.escore.server.manage.dao.impl.INewsNoticeDao#findAll(int, int)
	 */
	@SuppressWarnings(value = "unchecked")
	public List<NewsNoticedomain> findAll(final int pageIndex, final int pageRecord) throws Exception {
		String sql = "select * from T_PLATFORM_NOTICE order by UPDATE_TIME desc";
		return (List<NewsNoticedomain>) findAll(sql, NewsNoticedomain.class);
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findById
	 * </p>
	 * 
	 * @param id
	 * @return
	 * @see cn.adwalker.model.news.dao.INewsNoticeDao#findById(java.lang.Long)
	 */
	public NewsNoticedomain findById(Long id) {
		String sql = "select * from T_PLATFORM_NOTICE where ID=?";
		return (NewsNoticedomain) jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<NewsNoticedomain>(NewsNoticedomain.class), id);
	}

	/**
	 * @see cn.adwalker.model.news.dao.onekchi.escore.server.manage.dao.impl.INewsNoticeDao#deleteById(java.lang.Long)
	 */
	public Integer deleteById(Long id) {
		String sql = "delete from T_PLATFORM_NOTICE where id = ?";
		return jdbcTemplate.update(sql, id);
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
		sql.append(" select count(*) from T_PLATFORM_NOTICE where 1=1 ");
		if (!StringUtils.isEmpty(condition)) {
			sql.append(" " + condition.trim()); // 如果条件为空则取全部
		}
		return this.jdbcTemplate.queryForInt(sql.toString());
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: updateDel
	 * </p>
	 * 
	 * @param newsNotice
	 * @return
	 * @see cn.adwalker.model.news.dao.INewsNoticeDao#updateDel(cn.adwalker.model.news.domain.NewsNoticedomain)
	 */
	@Override
	public int updateDel(NewsNoticedomain newsNotice) {
		StringBuffer sql = new StringBuffer();
		sql.append(" update T_PLATFORM_NOTICE set ");
		if (ObjectUtils.isNotEmpty(newsNotice.getDel())) {
			sql.append("DEL=?");
		}
		sql.append(" where ID=?");
		return jdbcTemplate.update(sql.toString(), newsNotice.getDel(), newsNotice.getId());

	}
}
