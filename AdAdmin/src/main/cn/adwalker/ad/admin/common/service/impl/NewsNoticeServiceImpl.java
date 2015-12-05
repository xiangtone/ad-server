package cn.adwalker.ad.admin.common.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.model.news.dao.INewsNoticeDao;
import cn.adwalker.model.news.domain.NewsNoticedomain;
import cn.adwalker.model.operation.dao.IOperationConfirmIncomeDao;
import cn.adwalker.ad.admin.common.form.NewsNotice;
import cn.adwalker.ad.admin.common.service.INewsNoticeService;
import cn.adwalker.ad.admin.common.vo.NewsNoticeVo;
import cn.adwalker.ad.admin.common.vo.SysUserVo;

/**
 * <p>
 * Title: NewsNoticeServiceImpl
 * </p>
 * <p>
 * Description:新闻公告服务
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-7-10
 */
@Service(value = "newsNoticeService")
@Transactional
public class NewsNoticeServiceImpl implements INewsNoticeService {

	@Resource
	private INewsNoticeDao newsNoticeDao;
	@Resource
	private IOperationConfirmIncomeDao adEffectFirstConfirmDao;

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findTitleList
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param pageInfo
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.common.service.INewsNoticeService#findTitleList(cn.adwalker.core.page.IPageInfo)
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.NEVER)
	@Override
	public List<NewsNoticeVo> findTitleList(IPageInfo pageInfo)
			throws Exception {
		List<Object> list = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer(" T_PLATFORM_NOTICE t where 1=1 and t.del=0 ");
		return (List<NewsNoticeVo>) adEffectFirstConfirmDao.findByPage("*",
				sb.toString(), list.toArray(), " order by t.UPDATE_TIME desc ",
				NewsNoticeVo.class, pageInfo);
	}

	/**
	 * 
	 * <p>
	 * Title: convertToDomain
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param from
	 * @return
	 * @author lichuang
	 * @date 2013-7-10
	 * @return NewsNoticedomain
	 * @version 1.0
	 */
	private NewsNoticedomain convertToDomain(NewsNotice from) {
		NewsNoticedomain entity = null;
		if (from != null) {
			entity = new NewsNoticedomain();
			entity.setContent(from.getContent());
			entity.setId(from.getId());
			entity.setManager_id(from.getManagerId());
			entity.setTitle(from.getTitle());
			entity.setType(from.getType());
			entity.setUpdate_time(new Date());
			entity.setCreate_time(new Date());
			entity.setDel(0);
		}
		return entity;

	}

	/**
	 * 
	 * <p>
	 * Title: convertToVo
	 * </p>
	 * <p>
	 * Description:实体转换成vo
	 * </p>
	 * 
	 * @param entity
	 * @return
	 * @author cuidd
	 * @date 2013-1-17
	 * @return NewsNoticeVo
	 * @version 1.0
	 * @throws
	 */
	private NewsNoticeVo convertToVo(NewsNoticedomain entity) {
		NewsNoticeVo vo = null;
		if (entity != null) {
			vo = new NewsNoticeVo();
			vo.setContent(entity.getContent());
			vo.setId(entity.getId());
			vo.setManagerId(entity.getManager_id());
			vo.setTitle(entity.getTitle());
			vo.setType(entity.getType());
			vo.setUpdateTime(entity.getUpdate_time());
		}

		return vo;

	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findById
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param id
	 * @return
	 * @see cn.adwalker.ad.admin.common.service.INewsNoticeService#findById(java.lang.Long)
	 */
	@Override
	public NewsNoticeVo findById(Long id) {
		return convertToVo(newsNoticeDao.findById(id));
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: insert
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param from
	 * @param manageUser
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.common.service.INewsNoticeService#insert(cn.adwalker.ad.admin.common.form.NewsNotice,
	 *      cn.adwalker.ad.admin.common.vo.SysUserVo)
	 */
	@Override
	public void insert(NewsNotice from, SysUserVo manageUser) throws Exception {
		from.setManagerId(manageUser.getId());
		this.newsNoticeDao.insert(convertToDomain(from));
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: update
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param from
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.common.service.INewsNoticeService#update(cn.adwalker.ad.admin.common.form.NewsNotice)
	 */
	@Override
	public void update(NewsNotice from) throws Exception {
		from.setUpdateTime(new Date());// 修改时间
		this.newsNoticeDao.update(convertToDomain(from));
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: delete
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param id
	 * @throws ParseException
	 * @see cn.adwalker.ad.admin.common.service.INewsNoticeService#delete(java.lang.Long)
	 */
	@Override
	public void delete(Long id) throws ParseException {
		NewsNoticedomain newsNotice = new NewsNoticedomain();
		newsNotice.setId(id);
		newsNotice.setDel(1);
		this.newsNoticeDao.updateDel(newsNotice);
	}

	/**
	 * @param newsNoticeDao
	 *            the newsNoticeDao to set
	 */
	public void setNewsNoticeDao(INewsNoticeDao newsNoticeDao) {
		this.newsNoticeDao = newsNoticeDao;
	}
}
