package cn.adwalker.model.news.dao.impl;

import org.springframework.stereotype.Repository;

import cn.adwalker.core.repository.impl.BaseDaoImpl;
import cn.adwalker.model.news.dao.IInteriorMailDevDao;
import cn.adwalker.model.news.domain.NewsNoticedomain;

/**
 * 
* <p>Title: InteriorMailDaoImpl</p>
* <p>Description:站内信和开发者中间表</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2014-1-26
 */
@Repository("interiorMailDevDao")
public class InteriorMailDaoImpl extends BaseDaoImpl<NewsNoticedomain> implements IInteriorMailDevDao {

	public InteriorMailDaoImpl() {
		super();
		this.setTableName("t_interior_mail_dev");
	}

}
