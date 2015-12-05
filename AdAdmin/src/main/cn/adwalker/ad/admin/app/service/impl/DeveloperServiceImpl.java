/**
 * 
 */
package cn.adwalker.ad.admin.app.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.util.lang.ObjectUtils;
import cn.adwalker.model.app.dao.IDeveloperDao;
import cn.adwalker.model.app.domain.Developer;
import cn.adwalker.ad.admin.app.bean.DevQuery;
import cn.adwalker.ad.admin.app.service.IDeveloperService;
import cn.adwalker.ad.admin.app.vo.DeveloperVo;

/**
 * <p>
 * Title: DeveloperServiceImpl
 * </p>
 * <p>
 * Description:功能概述：<br>
 * 开发者用户账户服务
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-7-9
 */
@Service(value = "developerService")
@Transactional
public class DeveloperServiceImpl implements IDeveloperService {

	@Resource
	private IDeveloperDao developerDao;

	@Override
	public Developer exists(String email) throws Exception {
		return this.developerDao.exists(email);
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findAll
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.app.service.IDeveloperService#findAll()
	 */
	@Transactional(propagation = Propagation.NEVER)
	@Override
	public List<Developer> findAll() throws Exception {
		List<Developer> userDeveloper = new ArrayList<Developer>();
		userDeveloper = this.developerDao.findAll();
		return userDeveloper;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: getById
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.app.service.IDeveloperService#getById(java.lang.Long)
	 */
	@Transactional(propagation = Propagation.NEVER)
	@Override
	public Developer getById(Long id) throws Exception {
		Developer userDeveloper = new Developer();
		userDeveloper = developerDao.getById(id);
		return userDeveloper;
	}

	/**
	 * @return
	 * @see cn.adwalker.ad.admin.app.service.manager.dev.service.IDeveloperService.escore.server.developer.service.impl.IUserDeveloperService#update(com.DeveloerChannel.adwalker.escore.model.developer.domain.UserDeveloper)
	 */
	@Override
	public void updateFreeze(Long dev_id) throws Exception {
		developerDao.updateFreeze(dev_id);

	}

	/**
	 * @return
	 * @see cn.adwalker.ad.admin.app.service.manager.dev.service.IDeveloperService.escore.server.developer.service.impl.IUserDeveloperService#update(cn.adwalker.model.app.domain.DeveloerChannel.adwalker.escore.model.developer.domain.UserDeveloper)
	 */
	@Override
	public Developer update(Developer userDeveloper) throws Exception {
		Developer developer = null;
		if (!ObjectUtils.isNotEmpty(userDeveloper.getMobilePhone())) {
			userDeveloper.setMobilePhone("");
		}
		if (!ObjectUtils.isNotEmpty(userDeveloper.getQq())) {
			userDeveloper.setQq("");
		}
		userDeveloper.setUpdateTime(new Date());
		developerDao.update(userDeveloper);
		developer = getById(userDeveloper.getId());
		return developer;

	}

	@Override
	public void updatePass(Developer userDeveloper) throws Exception {
		userDeveloper.setUpdateTime(new Date());
		developerDao.updatePass(userDeveloper);
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: getAppCount
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.app.service.IDeveloperService#getAppCount()
	 */
	@Transactional(propagation = Propagation.NEVER)
	@Override
	public List<DeveloperVo> getAppCount() throws Exception {
		return this.developerDao.getAppCount();
	}

	@Override
	public Double getTax(Double preTax) throws Exception {
		return this.developerDao.getTax(preTax);
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: getByIdAndName
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param id
	 * @param name
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.app.service.manager.dev.service.IDeveloperService#getByIdAndName(java.lang.Long,
	 *      java.lang.String)
	 */
	@Transactional(propagation = Propagation.NEVER)
	@Override
	public Developer getByIdAndName(Long id, String name) throws Exception {
		Developer userDeveloper = new Developer();
		userDeveloper = developerDao.getByIdAndName(id, name);
		return userDeveloper;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: getByName
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param name
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.app.service.manager.dev.service.IDeveloperService#getByName(java.lang.String)
	 */
	@Transactional(propagation = Propagation.NEVER)
	@Override
	public List<Developer> getByEmail(String name) throws Exception {
		List<Developer> list = null;
		if (!StringUtils.isEmpty(name)) {
			list = developerDao.getByEmail(name);
		}
		return list;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: updateStatus
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param userDeveloper
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.app.service.manager.dev.service.IDeveloperService#updateStatus(cn.adwalker.model.app.domain.Developer)
	 */
	@Override
	public Developer updateStatus(Long dev_id, Integer status, Long long1)
			throws Exception {
		developerDao.updateStatus(dev_id, status, long1);
		return null;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findByPage
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param bean
	 * @param pageInfo
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.app.service.manager.dev.service.IDeveloperService#findByPage(cn.adwalker.ad.admin.app.bean.manager.dev.bean.DevQuery,
	 *      cn.adwalker.core.page.IPageInfo)
	 */
	@Transactional(propagation = Propagation.NEVER)
	@SuppressWarnings("unchecked")
	@Override
	public List<Developer> findByPage(DevQuery bean, IPageInfo pageInfo)
			throws Exception {
		StringBuilder sb = new StringBuilder("where 1=1");
		List<Object> param = new ArrayList<Object>();
		if (bean != null) {
			if (!StringUtils.isEmpty(bean.getIdOrEmail())) {
				sb.append(" and (id=? or upper(email) like ?)");
				param.add(bean.getDev_id());
				param.add("%" + bean.getDev_email().trim().toUpperCase() + "%");

			}
			if (!StringUtils.isEmpty(bean.getDev_name())) {
				sb.append(" and upper(real_name) like ?");
				param.add("%" + bean.getDev_name().trim().toUpperCase() + "%");
			}
			if (bean.getStatus() != null) {
				sb.append(" and status=?");
				param.add(bean.getStatus());
			}

			if (!StringUtils.isEmpty(bean.getResource())) {
				sb.append(" and RESOURCE_CODE = ?");
				param.add(bean.getResource());

			}

			if (!ObjectUtils.isEmpty(bean.getLogonStartTime())
					&& !ObjectUtils.isEmpty(bean.getLogonEndTime())) {
				sb.append(" and date_format(CREATE_TIME,'%Y-%m-%d') between ? ");
				param.add(bean.getLogonStartTime());
				sb.append(" and ?");
				param.add(bean.getLogonEndTime());
			}
		}
		return (List<Developer>) developerDao.findByPage("*", "T_DEVELOPER "
				+ sb.toString(), param.toArray(), "order by CREATE_TIME desc",
				Developer.class, pageInfo);
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findByDev
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.app.service.IDeveloperService#findByDev(cn.adwalker.ad.admin.app.bean.DevQuery)
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.NEVER)
	@Override
	public List<Developer> findByDev(DevQuery bean) throws Exception {
		StringBuilder sb = new StringBuilder("where 1=1");
		List<Object> param = new ArrayList<Object>();
		if (bean != null) {
			if (!StringUtils.isEmpty(bean.getIdOrEmail())) {
				sb.append(" and (id=? or upper(email) like ?)");
				param.add(bean.getDev_id());
				param.add("%" + bean.getDev_email().trim().toUpperCase() + "%");

			}
			if (!StringUtils.isEmpty(bean.getDev_name())) {
				sb.append(" and upper(real_name) like ?");
				param.add("%" + bean.getDev_name().trim().toUpperCase() + "%");
			}
			if (bean.getStatus() != null) {
				sb.append(" and status=?");
				param.add(bean.getStatus());
			}

			if (!StringUtils.isEmpty(bean.getResource())) {
				sb.append(" and RESOURCE_CODE = ?");
				param.add(bean.getResource());

			}

			if (!ObjectUtils.isEmpty(bean.getLogonStartTime())
					&& !ObjectUtils.isEmpty(bean.getLogonEndTime())) {
				sb.append(" and date_format(CREATE_TIME,'%Y-%m-%d') between ? ");
				param.add(bean.getLogonStartTime());
				sb.append(" and ?");
				param.add(bean.getLogonEndTime());
			}
		}
		return (List<Developer>) developerDao.findAll("*",
				"T_DEVELOPER " + sb.toString(), param.toArray(),
				" order by CREATE_TIME desc", Developer.class);
	}
}
