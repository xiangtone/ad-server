/*
 * registService.java
 *
 * Copyright 2011 1000CHI Software Technology Co.,Ltd
 * All rights reserved.
 * 
 * Created on 30-Nov-2011
 */
package cn.adwalker.ad.admin.ad.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.adwalker.model.ad.dao.IAdvBankInfoDao;
import cn.adwalker.model.ad.dao.IAdvDao;
import cn.adwalker.model.ad.domain.Advertiser;
import cn.adwalker.model.ad.domain.AdvBankInfo;
import cn.adwalker.model.common.dao.ISysSortDao;
import cn.adwalker.model.common.domain.SysCategory;
import cn.adwalker.ad.admin.ad.bean.AdvSerach;
import cn.adwalker.ad.admin.ad.form.AdvAddForm;
import cn.adwalker.ad.admin.ad.form.AdvBankInfoform;
import cn.adwalker.ad.admin.ad.form.AdvForm;
import cn.adwalker.ad.admin.ad.service.IAdvService;
import cn.adwalker.ad.admin.ad.vo.AdvInfoVo;
import cn.adwalker.ad.admin.ad.vo.AdvertiserVo;
import cn.adwalker.ad.admin.common.vo.SysUserVo;
import cn.adwalker.ad.admin.common.vo.ViewUserVo;
import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.util.MD5;
import cn.adwalker.core.util.lang.ObjectUtils;
import cn.adwalker.core.util.lang.StringUtil;

/**
 * 
 * <p>
 * Title: RegistAdvServiceImpl
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-3-19
 */
@Service
public class AdvServiceImpl implements IAdvService {

	@Resource
	private IAdvDao advDao;

	@Resource
	private IAdvBankInfoDao advBankInfoDao;

	@Resource
	private ISysSortDao sysSortDao;

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: registService
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param registadvvo
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.ad.service.IAdvService.ad.service.IRegistAdvService#registService(cn.adwalker.admin.ad.form.AdvForm.ad.vo.AdvAddForm)
	 */
	@Override
	public Long save(AdvAddForm form, SysUserVo manageUser) throws Exception {
		Long id = null;
		if (form != null) {
			Advertiser entity = new Advertiser();
			entity.setPassword(new MD5().getMD5ofStr(form.getPassword()));
			entity.setCompany_address(form.getCompanyAddress());
			entity.setCompany_name(form.getCompanyName());
			entity.setCreate_time(new Date());
			entity.setCredit_quota(form.getCreditQuota());
			entity.setEmail(form.getEmail());
			entity.setFixed_phone(form.getFixedPhone());
			entity.setMobile_phone(form.getMobilePhone());
			entity.setPassword(form.getPassword());
			entity.setPostcode(form.getPostCode());
			entity.setQq(form.getQq());
			entity.setReal_name(form.getRealName());
			entity.setStatus(0);
			entity.setArea_type(form.getSalesman_area_type());
			id = advDao.insert(entity);

		}
		return id;

	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: exists
	 * </p>
	 * <p>
	 * Description:判断广告主油箱是否存在
	 * </p>
	 * 
	 * @param email
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.ad.service.IAdvService#exists(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean exists(String email) throws Exception {
		boolean b = false;
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from VIEW_USER where EMAIL = ? ");
		List<ViewUserVo> objects = (List<ViewUserVo>) advDao.findAll(
				sql.toString(), new Object[] { email }, ViewUserVo.class);
		if (objects != null && objects.size() > 0) {
			b = true;
		}

		return b;
	}

	/**
	 * 查看信息 (non-Javadoc)
	 * <p>
	 * Title: findAdvInformation
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param advId
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.ad.service.IAdvService.ad.service.IRegistAdvService#findAdvInformation(java.lang.Long)
	 */
	public AdvInfoVo getAdv(Long advId) throws Exception {
		// 广告主
		Advertiser advInfo = advDao.findAdvInfo(advId);
		// 广告主财务
		AdvBankInfo bankInfo = advBankInfoDao.findAdvBankInfo(advId);
		// 复制
		AdvInfoVo advInfoVo = new AdvInfoVo(advInfo, bankInfo);

		return advInfoVo;

	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: updateAdvService
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param registadvvo
	 * @param advSalesmanVo
	 * @param advBankInfoVo
	 * @param advId
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.ad.service.IAdvService.ad.service.IRegistAdvService#updateAdvService(cn.adwalker.admin.ad.form.AdvForm.ad.vo.AdvAddForm,
	 *      cn.adwalker.admin.ad.form.AdvSalesmanForm.ad.vo.AdvSalesmanVo,
	 *      cn.adwalker.admin.ad.form.AdvBankInfoform.ad.vo.AdvBankInfoVo,
	 *      java.lang.Long)
	 */
	@Override
	public void updateAdvService(AdvForm advForm, AdvBankInfoform advBankInfoVo)
			throws Exception {
		// 广告主基本信息更新
		if (advForm != null) {
			Advertiser adv = (Advertiser) advDao.get(advForm.getId(),
					Advertiser.class);
			adv.setCompany_address(advForm.getCompanyAddress());
			adv.setCompany_name(advForm.getCompanyName());
			adv.setCredit_quota(advForm.getCreditQuota());
			adv.setFixed_phone(advForm.getFixedPhone());
			adv.setMobile_phone(advForm.getMobilePhone());
			adv.setPostcode(advForm.getPostCode());
			adv.setQq(advForm.getQq());
			adv.setReal_name(advForm.getRealName());
			adv.setArea_type(advForm.getArea_type());
			advDao.update(adv);
		}
		// 广告主财务信息更新
		if (advBankInfoVo != null) {
			AdvBankInfo findAdvBankInfo = advBankInfoDao
					.findAdvBankInfo(advForm.getId());
			advBankInfoVo.setAdv_id(advForm.getId());
			advBankInfoVo.setUserType(1);
			if (ObjectUtils.isNotEmpty(findAdvBankInfo.getId())) {
				advBankInfoDao.updatesBankInfoService(advBankInfoVo);
			} else {
				advBankInfoDao.insertbankInfo(advBankInfoVo);

			}
		}
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
	 * @param advertiserId
	 * @param status
	 * @see cn.adwalker.admin.ad.service.IAdvertiserService#updateStatus(java.lang.Long,
	 *      java.lang.Integer)
	 */
	@Override
	public void updateStatus(Long advertiserId, Integer status) {
		advDao.updateStatus(advertiserId, status);
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findBylist
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param bean
	 * @param pageInfo
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.admin.ad.service.IAdvertiserService#findBylist(cn.adwalker.ad.admin.ad.bean.AdvSerach,
	 *      cn.adwalker.core.page.IPageInfo)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<AdvertiserVo> findBylist(AdvSerach bean, IPageInfo pageInfo,
			SysUserVo manageUser) throws Exception {
		List<Object> param = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer("T_ADVERTISER a  where 1=1");
		if (bean != null) {
			if (!StringUtil.isEmpty(bean.getAdv_name())) {

				sb.append(" and upper(a.company_name)  like ?");
				param.add("%" + bean.getAdv_name() + "%");

			}
			if (bean.getAdv_id() != null) {
				sb.append(" and a.ID=? ");
				param.add(bean.getAdv_id());
			}

			if (bean.getReal_name() != null) {
				sb.append(" and upper(a.REAL_NAME)  like ?");
				param.add("%" + bean.getReal_name() + "%");

			}

			if (ObjectUtils.isNotEmpty(bean.getStatus())) {
				sb.append(" and a.STATUS= ?");
				param.add(bean.getStatus());
			}
		}

		return (List<AdvertiserVo>) advDao.findByPage("a.*", sb.toString(),
				param.toArray(), " order by a.create_time desc ",
				AdvertiserVo.class, pageInfo);
	}

	/**
	 * 根据条件查找记录数
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	@Override
	public int getCountByCondition(String condition) throws Exception {
		return advDao.getCount(condition);
	}

	/**
	 * <p>
	 * Title: findECList
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @return
	 * @return List<EscoreCategory>
	 * @throws
	 */
	@Override
	public List<SysCategory> findECList() {
		return this.sysSortDao.findECList();
	}

}
