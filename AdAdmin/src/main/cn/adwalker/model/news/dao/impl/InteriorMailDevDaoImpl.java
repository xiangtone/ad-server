package cn.adwalker.model.news.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import cn.adwalker.core.repository.impl.BaseDaoImpl;
import cn.adwalker.core.util.lang.ObjectUtils;
import cn.adwalker.model.news.dao.IInteriorMainDao;
import cn.adwalker.model.news.domain.InteriorMail;
import cn.adwalker.model.news.domain.NewsNoticedomain;

/**
* <p>Title: InteriorMailDaoImpl</p>
* <p>Description:站内信</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2014-1-26
 */
@Repository("interiorMainDao")
public class InteriorMailDevDaoImpl extends BaseDaoImpl<NewsNoticedomain> implements IInteriorMainDao {

	@Resource
	private JdbcTemplate jdbcTemplate;

	public InteriorMailDevDaoImpl() {
		super();
		this.setTableName("t_platform_interior_mail");
	}

	/**
	 * @see com.emar.escore.model.news.dao.onekchi.escore.server.manage.dao.impl.INewsNoticeDao#findAll()
	 */
	public List<NewsNoticedomain> findAll() {
		String sql = "select * from T_PLATFORM_NOTICE order by UPDATE_TIME desc";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<NewsNoticedomain>(NewsNoticedomain.class));
	}

	/**
	 * @see com.emar.escore.model.news.dao.onekchi.escore.server.manage.dao.impl.INewsNoticeDao#findAll(int, int)
	 */
	@SuppressWarnings(value = "unchecked")
	public List<NewsNoticedomain> findAll(final int pageIndex, final int pageRecord) throws Exception {
		String sql = "select * from T_PLATFORM_NOTICE order by UPDATE_TIME desc";
		return (List<NewsNoticedomain>) findAll(sql, NewsNoticedomain.class);
	}

	/**
	 * (non-Javadoc)
	* <p>Title: findById</p>
	* <p>Description:TODO</p>
	* @param id
	* @return
	* @see com.emar.escore.model.news.dao.IInteriorMainDao#findById(java.lang.Long)
	 */
	public InteriorMail findById(Long id) {
		String sql = "select * from t_platform_interior_mail where ID=?";
		return (InteriorMail) jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<InteriorMail>(InteriorMail.class), id);
	}

	/**
	 * @see com.emar.escore.model.news.dao.onekchi.escore.server.manage.dao.impl.INewsNoticeDao#deleteById(java.lang.Long)
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
	 * @see com.emar.escore.model.news.dao.INewsNoticeDao#updateDel(com.emar.escore.model.news.domain.NewsNoticedomain)
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
