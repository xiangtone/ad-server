package cn.adwalker.ad.admin.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.model.app.dao.IAppMediaDao;
import cn.adwalker.model.app.dao.IApplicationDao;
import cn.adwalker.model.app.domain.AppMedia;
import cn.adwalker.ad.admin.app.service.IAppDeductionService;
import cn.adwalker.ad.admin.common.vo.SysUserVo;
import cn.adwalker.ad.admin.operation.bean.AppDeductionBean;
import cn.adwalker.ad.admin.operation.vo.AppDeductionVo;

@Service("appDeductionService")
public class AppDeductionServiceImpl implements IAppDeductionService {

	@Resource
	private IApplicationDao appDao;

	@Resource
	private IAppMediaDao appMediaDao;

	/**
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
	 * @see cn.adwalker.ad.admin.app.service.IAppDeductionService#findByPage(cn.adwalker.ad.admin.operation.bean.AppDeductionBean,
	 *      cn.adwalker.core.page.IPageInfo)
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.NEVER)
	@Override
	public List<AppDeductionVo> findByPage(AppDeductionBean bean,
			SysUserVo user, IPageInfo pageInfor) throws Exception {
		List<AppDeductionVo> list = null;

		if (user.getRoleCode().equals("weiying1")
				|| user.getRoleCode().equals("MEIJIEZHUANYUAN")) {
			AppMedia appMedia = this.appMediaDao.getEntityBySysUser(user
					.getId());
			if (appMedia == null || appMedia.getId() == null) {
				return list;
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

		String columns = "a.ID id,a.DEV_ID devID,a.DEV_EMAIL devMail,a.DEV_NAME devName,a.APP_ID appId,a.APP_NAME appName,a.OS os,a.TYPE_NAME adForm,a.STATUS status,a.RATE rate,a.SCALE scale";
		StringBuilder sb = new StringBuilder("V_APP_PAGE a where 1=1");
		List<Object> param = new ArrayList<Object>();
		if (bean != null) {
			if (bean.getDevID() != null) {
				sb.append(" and a.DEV_ID=?");
				param.add(bean.getDevID());
			}
			if (!StringUtils.isEmpty(bean.getDevName())) {
				sb.append(" and upper(a.DEV_NAME) like ?");
				param.add("%" + bean.getDevName().toUpperCase().trim() + "%");
			}

			if (bean.getMediaID() != null) {
				sb.append(" and a.APP_ID=?");
				param.add(bean.getMediaID());
			}

			if (!StringUtils.isEmpty(bean.getMediaName())) {
				sb.append(" and upper(a.APP_NAME) like ?");
				param.add("%" + bean.getMediaName().toUpperCase().trim() + "%");
			}

			if (!StringUtils.isEmpty(bean.getOs())) {
				sb.append(" and a.OS=?");
				param.add(bean.getOs());
			}

			if (bean.getApp_res() != null) {
				sb.append(" and a.app_res= ?");
				param.add(bean.getApp_res());
			}
			if (bean.getApp_manager_id() != null) {
				sb.append(" and a.app_manager_id= ?");
				param.add(bean.getApp_manager_id());
			}

			if (!StringUtils.isEmpty(bean.getMediaRating())) {
				if (bean.getMediaRating().equals("A")) {
					sb.append(" and a.SCALE between " + 1.31 + " and " + 1.5);
				} else if (bean.getMediaRating().equals("B")) {
					sb.append(" and a.SCALE between " + 1.01 + " and " + 1.3);
				} else if (bean.getMediaRating().equals("C")) {
					sb.append(" and a.SCALE between " + 0.71 + " and " + 1.00);
				} else if (bean.getMediaRating().equals("D")) {
					sb.append(" and a.SCALE between " + 0.41 + " and " + 0.7);
				} else if (bean.getMediaRating().equals("E")) {
					sb.append(" and a.SCALE between " + 0.00 + " and " + 0.40);
				}
			}

			if (bean.getType_id() != null) {
				sb.append(" and a.TYPE_ID=" + bean.getType_id());
			}
		}
		list = (List<AppDeductionVo>) appDao.findByPage(columns, sb.toString(),
				param.toArray(), AppDeductionVo.class, pageInfor);
		return list;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: updateAppDeductionRate
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param adId
	 * @param rate
	 * @see cn.adwalker.ad.admin.app.service.IAppDeductionService#updateAppDeductionRate(java.lang.Long,
	 *      java.lang.Double)
	 */
	@Override
	public void updateAppDeductionRate(Long adId, Double rate) {
		appDao.updateAppRate(adId, rate);
	}

}
