package cn.adwalker.ad.web.developer.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.adwalker.ad.config.AppConstant;
import cn.adwalker.ad.core.page.IPageInfo;
import cn.adwalker.ad.model.developer.dao.DeveloperApplyMoneyDao;
import cn.adwalker.ad.model.developer.dao.DeveloperDao;
import cn.adwalker.ad.model.developer.domain.DevInfo;
import cn.adwalker.ad.model.developer.domain.Developer;
import cn.adwalker.ad.util.ObjectUtils;
import cn.adwalker.ad.web.developer.form.UpdateDevInfoForm;
import cn.adwalker.ad.web.developer.vo.DevIteriorMailVo;

/**
 * 功能概述：<br>
 * 开发者用户账户服务
 */
@Service("developerService")
public class DeveloperService {

	@Resource
	private DeveloperDao userDeveloperDao;

	@Resource
	private DeveloperApplyMoneyDao devApplyMoneyDao;

	public Developer exists(String email) throws Exception {
		return this.userDeveloperDao.exists(email);
	}

	public Developer getById(Long id) throws Exception {
		Developer userDeveloper = new Developer();
		userDeveloper = userDeveloperDao.getById(id);
		return userDeveloper;
	}

	@Transactional
	public void updateUserInfo(DevInfo userInfo) throws Exception {
		userInfo.setUpdateTime(new Date());
		userInfo.setStatus(AppConstant.BLOCK_USE);
		userDeveloperDao.updateUserInfo(userInfo);
	}

	@Transactional
	public void updateDeveloperPassword(String password, Long id) throws Exception {
		userDeveloperDao.updatePassword(password, id);
	}

	public Double getTax(Double preTax) throws Exception {
		return this.userDeveloperDao.getTax(preTax);
	}

	public Double getDues() throws Exception {
		return this.userDeveloperDao.getDues();
	}

	@Transactional
	public void updateDeveloperInfo(Long id, UpdateDevInfoForm form) throws Exception {
		Developer entity = getById(id);
		entity.setMobilePhone(form.getMobilePhone());
		entity.setQq(form.getQq());
		entity.setUpdateTime(new Date());
		entity.setCompanyName(form.getCompanyName());
		entity.setRealName(form.getRealName());
		entity.setPostCode(form.getPostCode());
		entity.setMailingAddress(form.getMailingAddress());
		userDeveloperDao.update(entity);
	}

	public List<DevIteriorMailVo> findByDevUserId(Long dev_id,Integer interior_status, IPageInfo pageInfo) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append(" t_interior_mail_dev t LEFT JOIN t_platform_interior_mail m ON t.INTERIOR_ID=m.ID where 1=1 and t.STATUS != 2 and t.DEV_ID = " + dev_id);
		if(ObjectUtils.isNotEmpty(interior_status)){
			sb.append(" and t.STATUS = 0");
			sb.append(interior_status);
		}
		sb.append(" order by m.CREATE_TIME desc");
		return (List<DevIteriorMailVo>) devApplyMoneyDao.findByPage("t.*,m.TITLE,m.CONTENT,m.CREATE_TIME", sb.toString(), DevIteriorMailVo.class, pageInfo);
	}

	public int findNoReadCountByDevId(Long dev_id) throws Exception {
		String sql = "SELECT id FROM t_interior_mail_dev WHERE STATUS=0 AND dev_id=" + dev_id;
		return devApplyMoneyDao.getCount(sql);
	}

	public List<DevIteriorMailVo> findById(Long dev_id) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT t.STATUS,COUNT(t.STATUS) as count FROM  t_interior_mail_dev t WHERE 1=1 AND t.STATUS != 2 AND t.DEV_ID =" + dev_id);
		sb.append("  GROUP BY t.STATUS");
		return (List<DevIteriorMailVo>) this.devApplyMoneyDao.findAll(sb.toString(), DevIteriorMailVo.class);
	}

	@Transactional
	public Integer deleteById(Long id) throws Exception {
		return userDeveloperDao.deleteById(id);
	}

	@Transactional
	public Integer updateById(Long id) throws Exception {
		return userDeveloperDao.updateById(id);
	}

}
