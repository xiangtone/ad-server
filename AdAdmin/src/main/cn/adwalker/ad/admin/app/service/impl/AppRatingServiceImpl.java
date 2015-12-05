package cn.adwalker.ad.admin.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.util.CacheUtils;
import cn.adwalker.model.app.dao.IAppMediaDao;
import cn.adwalker.model.app.dao.IApplicationDao;
import cn.adwalker.model.app.domain.AppMedia;
import cn.adwalker.ad.admin.app.service.IAppRatingService;
import cn.adwalker.ad.admin.app.vo.MediaRatingVo;
import cn.adwalker.ad.admin.common.vo.SysUserVo;
import cn.adwalker.ad.admin.operation.bean.MediaRatingBean;

@Service("appRatingService")
public class AppRatingServiceImpl implements IAppRatingService {

	@Resource
	private IApplicationDao appDao;

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
	 * @see cn.adwalker.ad.admin.app.service.IAppRatingService#findByPage(cn.adwalker.ad.admin.operation.bean.MediaRatingBean,
	 *      cn.adwalker.core.page.IPageInfo)
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.NEVER)
	@Override
	public List<MediaRatingVo> findByPage(MediaRatingBean bean, SysUserVo user,
			IPageInfo pageInfor) throws Exception {
		List<MediaRatingVo> list = null;
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

		String columns = "a.ID devId,a.EMAIL devEmail,b.ID mediaId,b.NAME mediaName,b.OS os,b.SCALE scale ";
		StringBuilder sb = new StringBuilder(
				"T_APPLICATION b LEFT JOIN T_DEVELOPER a ON b.DEV_ID=a.ID WHERE b.status=3 ");
		List<Object> param = new ArrayList<Object>();
		if (bean != null) {
			if (bean.getDevID() != null) {
				sb.append(" and a.ID=" + bean.getDevID());
			}
			if (!StringUtils.isEmpty(bean.getDevMail())) {
				sb.append(" and upper(a.EMAIL) like '%"
						+ bean.getDevMail().trim().toUpperCase() + "%'");
			}

			if (bean.getMediaID() != null) {
				sb.append(" and b.ID=" + bean.getMediaID());
			}

			if (!StringUtils.isEmpty(bean.getMediaName())) {
				sb.append(" and upper(b.NAME) like '%"
						+ bean.getMediaName().trim().toUpperCase() + "%'");
			}

			if (bean.getOs() != null && !bean.getOs().equals("")) {
				sb.append(" and b.OS='" + bean.getOs().toString().trim() + "'");
			}

			if (bean.getApp_res() != null) {
				sb.append(" and b.app_res= ?");
				param.add(bean.getApp_res());
			}
			if (bean.getApp_manager_id() != null) {
				sb.append(" and b.app_manager_id= ?");
				param.add(bean.getApp_manager_id());
			}

		}
		list = (List<MediaRatingVo>) appDao.findByPage(columns, sb.toString(),
				param.toArray(), MediaRatingVo.class, pageInfor);
		return list;

	}

	@Override
	public void updateMediaRating(Long mediaId, Double score) {
		appDao.updateMediaRating(mediaId, score);
		//更新缓存
		CacheUtils.updateAppCache(mediaId);
	}

}
