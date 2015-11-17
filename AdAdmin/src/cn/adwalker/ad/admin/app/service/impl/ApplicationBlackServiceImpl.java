package cn.adwalker.ad.admin.app.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.adwalker.ad.admin.app.bean.Blackbean;
import cn.adwalker.ad.admin.app.bean.SelCampaignQueryBean;
import cn.adwalker.ad.admin.app.service.IApplicationBlackService;
import cn.adwalker.ad.admin.app.vo.AppBlackAdVo;
import cn.adwalker.ad.admin.app.vo.AppSelCampaign;
import cn.adwalker.ad.admin.common.vo.SysUserVo;
import cn.adwalker.ad.config.StatusConstant;
import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.model.ad.dao.ICampaignDao;
import cn.adwalker.model.app.dao.IAppMediaDao;
import cn.adwalker.model.app.dao.IApplicationBlackDao;
import cn.adwalker.model.app.dao.IApplicationDao;
import cn.adwalker.model.app.domain.AppMedia;
import cn.adwalker.model.app.domain.ApplicationBlack;
import cn.adwalker.model.app.domain.ApplicationEntity;

/**
 * <p>
 * Title: ConfirmationPackageServiceImpl
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-7-16
 */
@Service
public class ApplicationBlackServiceImpl implements IApplicationBlackService {

	@Resource
	private IApplicationBlackDao applicationBlackDao;
	
	
	@Resource
	private IApplicationDao applicationDao;
	@Resource
	private IAppMediaDao appMediaDao;
	
	
	@Resource
	private ICampaignDao campaignDao;

	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.NEVER)
	@Override
	public List<AppBlackAdVo> findList(Blackbean bean, SysUserVo user,
			IPageInfo pageInfo) throws Exception {
		List<AppBlackAdVo> resultList = null;

		if (user.getRoleCode().equals("weiying1")
				|| user.getRoleCode().equals("MEIJIEZHUANYUAN")) {
			AppMedia appMedia = this.appMediaDao.getEntityBySysUser(user
					.getId());
			if (appMedia == null || appMedia.getId() == null) {
				return resultList;
			} else {
				if (user.getRoleCode().equals("weiying1")) {
					// 设置应用来源
					bean.setApp_res(appMedia.getArea_type());
				} else if (user.getRoleCode().equals("MEIJIEZHUANYUAN")) {
					bean.setApp_res(appMedia.getArea_type());
					bean.setApp_manager_id(appMedia.getId());
				}

			}
		}

		StringBuilder sb = new StringBuilder(
				" t_application a LEFT JOIN (SELECT b.application_id,GROUP_CONCAT(p.name) placement_name,GROUP_CONCAT(r.campaign_id) campaign_id FROM t_application_config b LEFT JOIN t_placement p ON b.placement_id=p.id LEFT JOIN T_CAMPAIGN_PLACEMENT_REL r ON p.id=r.placement_id GROUP BY b.application_id) b ON a.id=b.application_id where 1=1 ");
		List<Object> param = new ArrayList<Object>();
		if (bean != null) {
			if (!StringUtils.isEmpty(bean.getOs())) {
				sb.append(" and a.os =?");
				param.add(bean.getOs());
			}
			if (bean.getApp_id() != null) {
				sb.append(" and a.id =?");
				param.add(bean.getApp_id());
			}
			if (!StringUtils.isEmpty(bean.getApp_name())) {
				sb.append(" and upper(a.name) like ?");
				param.add("%" + bean.getApp_name().trim().toUpperCase() + "%");
			}
			if (bean.getApp_res() != null) {
				sb.append(" and a.app_res= ?");
				param.add(bean.getApp_res());
			}
			if (bean.getApp_manager_id()!=null) {
				sb.append(" and a.app_manager_id= ?");
				param.add(bean.getApp_manager_id());
			}
		}
		resultList = (List<AppBlackAdVo>) applicationBlackDao
				.findByPage(
						"a.id,a.name,a.os,a.status,b.placement_name black,b.campaign_id black_id",
						sb.toString(), param.toArray(), "order by a.id desc",
						AppBlackAdVo.class, pageInfo);
		return resultList;
	}

	@Override
	@Transactional
	public void updateApplicationBlack(Long applicationId, String black)
			throws Exception {
		String[] placementIds = black.split(",");
		List<Object[]> paramsList = new ArrayList<Object[]>();
		for (String placementId : placementIds) {
			Object[] objs = new Object[2];
			objs[0] = applicationId;
			objs[1] = placementId;
			paramsList.add(objs);
		}
		applicationBlackDao
				.deleteApplicationBlackByApplicationid(applicationId);
		applicationBlackDao.createApplicationBlack(paramsList);
	}

	@Override
	public Integer deleteApplicationBlack(Long applicationId) throws Exception {
		return applicationBlackDao
				.deleteApplicationBlackByApplicationid(applicationId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AppSelCampaign> findCampaign(SelCampaignQueryBean bean, IPageInfo pageInfo) throws Exception {
		 List<AppSelCampaign> list=null;
		if (bean!=null&&bean.getApp_id()!=null) {
			ApplicationEntity entity=applicationDao.get(bean.getApp_id(), ApplicationEntity.class);
			if (entity!=null) {
				list=(List<AppSelCampaign>) campaignDao.findByPage("v.campaign_name,v.placement_id", "v_campaign v where v.os=? and v.status=?", new Object[]{entity.getOs(),StatusConstant.CAMPAIGN_STATUS_PLACEMENT_ON_LINE},AppSelCampaign.class, pageInfo);
				if (list!=null&&list.size()>0) {
					//查询下现有的活动，如果配置表中没有就插入。
					for (AppSelCampaign selCampaign:list) {
						ApplicationBlack black=saveAppConfig(bean.getApp_id(), selCampaign.getPlacement_id());
						selCampaign.setId(black.getId());
						selCampaign.setStatus(black.getStatus());
					}
					
				}
			}
		}
		
		return list;
	}
	
	
	@SuppressWarnings("unchecked")
	private ApplicationBlack saveAppConfig(Long app_id,Long placement_id) throws Exception{
		ApplicationBlack entity=null;
		List<ApplicationBlack> list=(List<ApplicationBlack>) applicationBlackDao.findAll("select * from t_application_config where application_id=? and placement_id=?", new Object[]{app_id,placement_id},ApplicationBlack.class);
		if (list!=null&&list.size()==1) {
			entity=list.get(0);
		}else {
			//插入
			entity=new ApplicationBlack();
			entity.setApplication_id(app_id);
			entity.setPlacement_id(placement_id);
			entity.setCreate_time(new Date());
			entity.setStatus(1);
		Long id=applicationBlackDao.insert(entity);
			entity.setId(id);
		}
		
		return entity;
		
		
	}

	@Override
	public void changetStatus(Long id, Integer status) throws Exception {
		applicationBlackDao.update("update t_application_config set status=? where id=?",new Object[]{status,id});
	}
}
