package cn.adwalker.ad.admin.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.adwalker.ad.admin.app.bean.MediaAchievementBean;
import cn.adwalker.ad.admin.app.service.IMediaAchievementService;
import cn.adwalker.ad.admin.app.vo.MediaAchievementVo;
import cn.adwalker.ad.admin.common.vo.SysUserVo;
import cn.adwalker.core.log.Log;
import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.model.app.dao.IAppMediaDao;
import cn.adwalker.model.app.dao.IMediaAchievementDao;
import cn.adwalker.model.app.domain.AppMedia;

/**
 * <p>
 * Title: BigAppServiceImpl
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-9-6
 */
@Service("mediaAchievementService")
public class MediaAchievementServiceImpl implements IMediaAchievementService {

	@Resource
	private IMediaAchievementDao mediaAchievementDao;

	@Resource
	private IAppMediaDao appMediaDao;

	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.NEVER)
	@Override
	@Log
	public List<MediaAchievementVo> findByPage(IPageInfo pageInfo,
			MediaAchievementBean bean, SysUserVo user) throws Exception {
		List<MediaAchievementVo> resultList = null;
		if (user.getRoleCode().equals("weiying1")
				|| user.getRoleCode().equals("MEIJIEZHUANYUAN")) {
			AppMedia appMedia = appMediaDao.getEntityBySysUser(user.getId());
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

		String temp_table = "";
		StringBuilder sb = new StringBuilder(" where t.id IS NOT NULL");
		List<Object> param = new ArrayList<Object>();
		if (bean != null) {
			param.add(bean.getBeginTime());
			param.add(bean.getEndTime());
			if (!StringUtils.isEmpty(bean.getId())) {
				sb.append(" and  app_id=?");
				param.add(bean.getId());
			}
			if (!StringUtils.isEmpty(bean.getName())) {
				sb.append(" and upper(t.name) like ?  ");
				param.add("%" + bean.getName().trim().toUpperCase() + "%");
			}
			if (bean.getApp_manager_id() != null) {
				sb.append(" and t.app_manager_id= ?  ");
				param.add(bean.getApp_manager_id());
			}

			if (!StringUtils.isEmpty(bean.getDev_id())) {
				sb.append(" and t.dev_id = ?");
				param.add(bean.getDev_id().trim());
			}
			if (bean.getType_id() != null) {
				sb.append(" and a.type_id=?");
				param.add(bean.getType_id());
			}

			if (bean.getApp_res() != null) {
				sb.append(" and t.app_res = ?");
				param.add(bean.getApp_res());
			}

			if (!StringUtils.isEmpty(bean.getOs())) {
				sb.append(" and os=?");
				param.add(bean.getOs());
			}
		}
		String table = "(select static_date,app_id ,type_id, sum(pospv) as pospv,sum(pv) as apppv, sum(click) as click,sum(download) as download,sum(clickd) as clickd, sum(activate) as activate, sum(cost) as cost from t_static_app_byday  where static_date BETWEEN ? and ? group by static_date,app_id , type_id) a " +
				"left join T_APPLICATION t on t.id = a.app_id left join t_type f on f.id = a.type_id " +
				"left join t_app_media m on t.app_manager_id=m.id "
				+ temp_table;
		resultList = (List<MediaAchievementVo>) mediaAchievementDao
				.findByPage(
						"a.static_date,t.dev_id,t.id as app_id,t.name, t.app_manager,f.name as fname,t.os, IFNULL(a.pospv, 0) as pospv,IFNULL(a.apppv, 0) as apppv, IFNULL(a.click, 0) as click,IFNULL(a.clickd, 0) as clickd,IFNULL(a.download, 0) as download,IFNULL(a.activate, 0) as activate,IFNULL(round(a.cost, 4), 0) as cost,m.name app_manager_name",
						table + sb.toString(), param.toArray(),
						"order by static_date", MediaAchievementVo.class,
						pageInfo);

		return resultList;
	}

	private AppMedia getAppMedia(Long id) {
		AppMedia entity = null;
		try {
			entity = appMediaDao.getEntityBySysUser(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return entity;
	}

	/**
	 * <p>
	 * Title: findTotal
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-9-6
	 * @return ReportAdByDay
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	@Override
	public MediaAchievementVo findTotal(MediaAchievementBean bean,
			SysUserVo user) throws Exception {
		MediaAchievementVo total = null;
		if (user.getRoleCode().equals("weiying1")
				|| user.getRoleCode().equals("MEIJIEZHUANYUAN")) {
			AppMedia appMedia = this.getAppMedia(user.getId());
			if (appMedia == null || appMedia.getId() == null) {
				return total;
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

		String temp_table = "";
		StringBuilder sb = new StringBuilder(" where t.id IS NOT NULL");
		List<Object> param = new ArrayList<Object>();
		if (bean != null) {
			param.add(bean.getBeginTime());
			param.add(bean.getEndTime());
			if (!StringUtils.isEmpty(bean.getId())) {
				sb.append(" and  app_id=?");
				param.add(bean.getId());
			}
			if (!StringUtils.isEmpty(bean.getName())) {
				sb.append(" and upper(t.name) like ?  ");
				param.add("%" + bean.getName().trim().toUpperCase() + "%");
			}
			if (bean.getApp_manager_id() != null) {
				sb.append(" and t.app_manager_id= ?  ");
				param.add(bean.getApp_manager_id());
			}

			if (!StringUtils.isEmpty(bean.getDev_id())) {
				sb.append(" and t.dev_id = ?");
				param.add(bean.getDev_id().trim());
			}
			if (bean.getType_id() != null) {
				sb.append(" and a.type_id=?");
				param.add(bean.getType_id());
			}

			if (bean.getApp_res() != null) {
				sb.append(" and t.app_res = ?");
				param.add(bean.getApp_res());
			}

			if (!StringUtils.isEmpty(bean.getOs())) {
				sb.append(" and os=?");
				param.add(bean.getOs());
			}
		}
		String table = "(select static_date,app_id , type_id,sum(pospv) as pospv, sum(pv) as apppv,sum(click) as click,sum(download) as download,sum(clickd) as clickd,sum(activate) as activate,  sum(cost) as cost from t_static_app_byday  where static_date BETWEEN ? and ? group by static_date,app_id , type_id) a left join T_APPLICATION t on t.id = a.app_id left join t_type f on f.id = a.type_id"
				+ temp_table;

		List<MediaAchievementVo> list = mediaAchievementDao
				.findAll(
						" IFNULL(sum(a.pospv), 0) as pospv, IFNULL(sum(a.apppv), 0) as apppv,IFNULL(sum(a.click), 0) as click, IFNULL(sum(a.clickd), 0) as clickd,IFNULL(sum(a.download), 0) as download,IFNULL(sum(a.activate), 0) as activate, IFNULL(round(sum(a.cost), 4), 0) as cost",
						table + sb.toString(), param.toArray(),
						" order by static_date", MediaAchievementVo.class);
		if (list != null && list.size() > 0) {
			total = list.get(0);

		}
		return total;
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
	 * @param bean
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.app.service.IMediaAchievementService#findAll(cn.adwalker.ad.admin.app.bean.MediaAchievementBean)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> findAll(MediaAchievementBean bean, SysUserVo user)
			throws Exception {
		List<Object> resultList = null;
		if (user.getRoleCode().equals("weiying1")
				|| user.getRoleCode().equals("MEIJIEZHUANYUAN")) {
			AppMedia appMedia = this.getAppMedia(user.getId());
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

		String temp_table = "";
		StringBuilder sb = new StringBuilder(" where t.id IS NOT NULL");
		List<Object> param = new ArrayList<Object>();
		if (bean != null) {
			param.add(bean.getBeginTime());
			param.add(bean.getEndTime());
			if (!StringUtils.isEmpty(bean.getId())) {
				sb.append(" and  app_id=?");
				param.add(bean.getId());
			}
			if (!StringUtils.isEmpty(bean.getName())) {
				sb.append(" and upper(t.name) like ?  ");
				param.add("%" + bean.getName().trim().toUpperCase() + "%");
			}
			if (bean.getApp_manager_id() != null) {
				sb.append(" and t.app_manager_id= ?  ");
				param.add(bean.getApp_manager_id());
			}

			if (!StringUtils.isEmpty(bean.getDev_id())) {
				sb.append(" and t.dev_id = ?");
				param.add(bean.getDev_id().trim());
			}
			if (bean.getType_id() != null) {
				sb.append(" and a.type_id=?");
				param.add(bean.getType_id());
			}
			if (bean.getApp_res() != null) {
				sb.append(" and t.app_res = ?");
				param.add(bean.getApp_res());
			}
			if (!StringUtils.isEmpty(bean.getOs())) {
				sb.append(" and os=?");
				param.add(bean.getOs());
			}
		}

		String table = "(select static_date,app_id ,type_id, sum(pospv) as pospv,sum(pv) as apppv, sum(click) as click,sum(download) as download,sum(clickd) as clickd, sum(activate) as activate, sum(cost) as cost from t_static_app_byday  where static_date BETWEEN ? and ? group by static_date,app_id , type_id) a " +
				"left join T_APPLICATION t on t.id = a.app_id " +
				"left join t_type f on f.id = a.type_id " +
				"left join t_app_media m on t.app_manager_id=m.id"
				+ temp_table;
		resultList = (List<Object>) mediaAchievementDao
				.findAll(
						"a.static_date,t.dev_id,t.id as app_id,t.name, t.app_manager,f.name as fname,t.os, IFNULL(a.pospv, 0) as pospv,IFNULL(a.apppv, 0) as apppv, IFNULL(a.click, 0) as click,IFNULL(a.clickd, 0) as clickd,IFNULL(a.download, 0) as download,IFNULL(a.activate, 0) as activate,IFNULL(round(a.cost, 4), 0) as cost,m.name as  app_manager_name",
						table + sb.toString(), param.toArray(),
						" order by static_date", MediaAchievementVo.class);

		return resultList;
	}

}
