package cn.adwalker.ad.admin.app.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.adwalker.ad.admin.app.bean.AppMediaQueryBean;
import cn.adwalker.ad.admin.app.form.AppMediaAddForm;
import cn.adwalker.ad.admin.app.form.AppMediaEditForm;
import cn.adwalker.ad.admin.app.service.IAppMediaService;
import cn.adwalker.ad.admin.app.vo.AppMediaVo;
import cn.adwalker.ad.admin.common.vo.SysUserVo;
import cn.adwalker.ad.admin.sales.vo.SalesmanEditVo;
import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.model.app.dao.IAppMediaDao;
import cn.adwalker.model.app.domain.AppMedia;

@Service
public class AppMediaServiceImpl implements IAppMediaService {

	@Resource
	private IAppMediaDao appMediaDao;

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
	 * @param pageInfor
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.ad.service.emar.escore.platform.ad.service.IAdAjustmentService#findByPage(com.emar.escore.platform.ad.bean.AdAjustmentBean,
	 *      com.emar.escore.core.page.IPageInfo)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<AppMediaVo> findByPage(AppMediaQueryBean bean,
			IPageInfo pageInfor) throws Exception {
		List<AppMediaVo> list = null;
		List<Object> param = new ArrayList<Object>();
		String columns = "t.id,t.name,t.mobile,t.qq,t.create_time,t.status,t.area_type,t.create_user,u.real_name create_user_name";
		StringBuilder sb = new StringBuilder(
				"t_app_media t left join t_sys_user u on t.create_user=u.id where t.status>=0 ");
		if (bean != null) {
			if (bean.getId() != null) {
				sb.append(" and t.id=? ");
				param.add(bean.getId());
			}
			if (!StringUtils.isEmpty(bean.getName())) {
				sb.append(" and t.name like ? ");
				param.add(bean.getName());
			}

			if (bean.getArea_type() != null) {
				sb.append(" and t.area_type= ? ");
				param.add(bean.getArea_type());
			}

			if (bean.getStatus() != null) {
				sb.append(" and a.status= ? ");
				param.add(bean.getStatus());
			}

		}
		list = (List<AppMediaVo>) appMediaDao.findByPage(
				columns, sb.toString(), param.toArray(),
				"order by t.create_time desc", AppMediaVo.class,
				pageInfor);

		return list;

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
	 * @param adId
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.ad.service.emar.escore.platform.ad.service.IAdAjustmentService#findById(java.lang.Long)
	 */
	@Override
	public SalesmanEditVo findById(Long id) throws Exception {
		SalesmanEditVo vo = null;
		if (id != null) {
			AppMedia entity = appMediaDao.get(id,
					AppMedia.class);
			if (entity != null) {
				vo = new SalesmanEditVo();
				vo.setArea_type(entity.getArea_type());
				vo.setMobile(entity.getMobile());
				vo.setName(entity.getName());
				vo.setQq(entity.getQq());
				vo.setSys_user_id(entity.getSys_user_id());
			}
		}
		return vo;
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
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.app.service.sales.service.IAppMediaService.escore.platform.ad.service.IAppMediaService#delete(java.lang.Long)
	 */
	@Override
	public void delete(Long id) throws Exception {
		appMediaDao.update(
				"update t_app_media set status=? where id=?",
				new Object[] { -1, id });

	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: saveSalesman
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param form
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.app.service.sales.service.IAppMediaService.escore.platform.ad.service.IAppMediaService#saveSalesman(cn.adwalker.ad.admin.app.form.sales.form.AppMediaAddForm.escore.platform.ad.form.SalesmanAddForm)
	 */
	@Override
	public void saveSalesman(AppMediaAddForm form, SysUserVo manageUser)
			throws Exception {
		AppMedia entity = new AppMedia();
		entity.setArea_type(form.getArea_type());
		entity.setCreate_time(new Date());
		entity.setMobile(form.getMobile());
		entity.setName(form.getName());
		entity.setQq(form.getQq());
		entity.setStatus(0);
		entity.setCreate_user(manageUser.getId());
		entity.setSys_user_id(form.getSys_user_id());
		appMediaDao.insert(entity);

	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: updateSalesman
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param form
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.app.service.sales.service.IAppMediaService.escore.platform.ad.service.IAppMediaService#updateSalesman(cn.adwalker.ad.admin.app.form.sales.form.AppMediaEditForm.escore.platform.ad.form.SalesmanEditForm)
	 */
	@Override
	@Transactional(rollbackFor = { Exception.class })
	public void updateSalesman(AppMediaEditForm form) throws Exception {
		if (form != null) {
			AppMedia entity = appMediaDao.get(form.getId(),
					AppMedia.class);
			if (entity != null) {
				entity.setArea_type(form.getArea_type());
				entity.setMobile(form.getMobile());
				entity.setName(form.getName());
				entity.setQq(form.getQq());
				entity.setSys_user_id(form.getSys_user_id());
				appMediaDao.update(entity);
			}
		}

	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: querySysUser
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.app.service.sales.service.IAppMediaService.escore.platform.ad.service.IAppMediaService#querySysUser()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<SysUserVo> querySysUser(Long id) throws Exception {
		List<SysUserVo> list = null;
		StringBuilder sb = new StringBuilder();
		Long current_user_id = null;
		if (id != null) {
			AppMedia entity = appMediaDao.get(id,
					AppMedia.class);
			if (entity != null && entity.getSys_user_id() != null) {
				current_user_id = entity.getSys_user_id();
			}
		}
		sb.append("SELECT  u.real_name,u.id,u.del "
				+ "from  t_sys_user_role rel   "
				+ "INNER  JOIN  t_sys_user u on  rel.user_id=u.id  "
				+ "where  not  exists(select  1  from t_app_media  where  sys_user_id=u.id "
				+ (current_user_id != null ? "and sys_user_id!= "
						+ current_user_id : "")
				+ ") "
				+ "and  rel.role_id in (SELECT   id   from   t_sys_role where code in  ('MEIJIEZHUANYUAN','weiying1'))");

		list = (List<SysUserVo>) appMediaDao.findAll(sb.toString(),
				SysUserVo.class);

		return list;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: getSalesmanIdBySysUser
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.app.service.sales.service.IAppMediaService.escore.platform.ad.service.IAppMediaService#getSalesmanIdBySysUser(java.lang.Long)
	 */
	@Override
	public Long getSalesmanIdBySysUser(Long id) throws Exception {
		Long l=null;
		if (id!=null) {
			l=appMediaDao.getSalesmanIdBySysUser(id);
		}
		return l;
	}
}
