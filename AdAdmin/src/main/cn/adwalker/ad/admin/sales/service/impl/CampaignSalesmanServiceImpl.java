package cn.adwalker.ad.admin.sales.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.adwalker.ad.admin.common.vo.SysUserVo;
import cn.adwalker.ad.admin.sales.bean.CampaignSalesmanBean;
import cn.adwalker.ad.admin.sales.form.SalesmanAddForm;
import cn.adwalker.ad.admin.sales.form.SalesmanEditForm;
import cn.adwalker.ad.admin.sales.service.ICampaignSalesmanService;
import cn.adwalker.ad.admin.sales.vo.CampaignSalesmanVo;
import cn.adwalker.ad.admin.sales.vo.SalesmanEditVo;
import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.model.ad.dao.ICampaignSalesmanDao;
import cn.adwalker.model.ad.domain.CampaignSalesman;

@Service
public class CampaignSalesmanServiceImpl implements ICampaignSalesmanService {

	@Resource
	private ICampaignSalesmanDao campaignSalesmanDao;

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
	 * @see cn.adwalker.ad.admin.sales.service.ICampaignSalesmanService#findByPage(cn.adwalker.ad.admin.sales.bean.CampaignSalesmanBean,
	 *      cn.adwalker.core.page.IPageInfo)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<CampaignSalesmanVo> findByPage(CampaignSalesmanBean bean,
			IPageInfo pageInfor) throws Exception {
		List<CampaignSalesmanVo> list = null;
		List<Object> param = new ArrayList<Object>();
		String columns = "t.id,t.name,t.mobile,t.qq,t.create_time,t.status,t.area_type,t.create_user,u.real_name create_user_name";
		StringBuilder sb = new StringBuilder(
				"t_campaign_salesman t left join t_sys_user u on t.create_user=u.id where t.status>=0 ");
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
		list = (List<CampaignSalesmanVo>) campaignSalesmanDao.findByPage(
				columns, sb.toString(), param.toArray(),
				"order by t.create_time desc", CampaignSalesmanVo.class,
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
	 * @param id
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.sales.service.ICampaignSalesmanService#findById(java.lang.Long)
	 */
	@Override
	public SalesmanEditVo findById(Long id) throws Exception {
		SalesmanEditVo vo = null;
		if (id != null) {
			CampaignSalesman entity = campaignSalesmanDao.get(id,
					CampaignSalesman.class);
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
	 * @see cn.adwalker.ad.admin.sales.service.ICampaignSalesmanService#delete(java.lang.Long)
	 */
	@Override
	public void delete(Long id) throws Exception {
		campaignSalesmanDao.update(
				"update t_campaign_salesman set status=? where id=?",
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
	 * @see cn.cn.adwalker.ad.admin.app.service.IAppMediaService.escore.platform.ad.service.ICampaignSalesmanService#saveSalesman(cn.adwalker.ad.admin.sales.form.AppMediaAddForm.escore.platform.ad.form.SalesmanAddForm)
	 */
	@Override
	public void saveSalesman(SalesmanAddForm form, SysUserVo manageUser)
			throws Exception {
		CampaignSalesman entity = new CampaignSalesman();
		entity.setArea_type(form.getArea_type());
		entity.setCreate_time(new Date());
		entity.setMobile(form.getMobile());
		entity.setName(form.getName());
		entity.setQq(form.getQq());
		entity.setStatus(0);
		entity.setCreate_user(manageUser.getId());
		entity.setSys_user_id(form.getSys_user_id());
		campaignSalesmanDao.insert(entity);

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
	 * @see cn.cn.adwalker.ad.admin.app.service.IAppMediaService.escore.platform.ad.service.ICampaignSalesmanService#updateSalesman(cn.adwalker.ad.admin.sales.form.AppMediaEditForm.escore.platform.ad.form.SalesmanEditForm)
	 */
	@Override
	@Transactional(rollbackFor = { Exception.class })
	public void updateSalesman(SalesmanEditForm form) throws Exception {
		if (form != null) {
			CampaignSalesman entity = campaignSalesmanDao.get(form.getId(),
					CampaignSalesman.class);
			if (entity != null) {
				entity.setArea_type(form.getArea_type());
				entity.setMobile(form.getMobile());
				entity.setName(form.getName());
				entity.setQq(form.getQq());
				entity.setSys_user_id(form.getSys_user_id());
				campaignSalesmanDao.update(entity);
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
	 * @see cn.cn.adwalker.ad.admin.app.service.IAppMediaService.escore.platform.ad.service.ICampaignSalesmanService#querySysUser()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<SysUserVo> querySysUser(Long id) throws Exception {
		List<SysUserVo> list = null;
		StringBuilder sb = new StringBuilder();
		Long current_user_id = null;
		if (id != null) {
			CampaignSalesman entity = campaignSalesmanDao.get(id,
					CampaignSalesman.class);
			if (entity != null && entity.getSys_user_id() != null) {
				current_user_id = entity.getSys_user_id();
			}

		}
		sb.append("SELECT  u.real_name,u.id,u.del "
				+ "from  t_sys_user_role rel   "
				+ "INNER  JOIN  t_sys_user u on  rel.user_id=u.id  "
				+ "where  not  exists(select  1  from t_campaign_salesman  where  sys_user_id=u.id "
				+ (current_user_id != null ? "and sys_user_id!= "
						+ current_user_id : "")
				+ ") "
				+ "and  rel.role_id in (SELECT   id   from   t_sys_role where code in  ('XIAOSHOUZONGJIAN','XIAOSHOUZHUANYUAN','XIAOSHOUJINGLI'))");

		list = (List<SysUserVo>) campaignSalesmanDao.findAll(sb.toString(),
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
	 * @see cn.cn.adwalker.ad.admin.app.service.IAppMediaService.escore.platform.ad.service.ICampaignSalesmanService#getSalesmanIdBySysUser(java.lang.Long)
	 */
	@Override
	public Long getSalesmanIdBySysUser(Long id) throws Exception {
		Long l = null;
		if (id != null) {
			l = campaignSalesmanDao.getSalesmanIdBySysUser(id);
		}
		return l;

	}
}
