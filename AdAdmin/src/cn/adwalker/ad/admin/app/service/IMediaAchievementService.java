package cn.adwalker.ad.admin.app.service;

import java.util.List;

import cn.adwalker.ad.admin.app.bean.MediaAchievementBean;
import cn.adwalker.ad.admin.app.vo.MediaAchievementVo;
import cn.adwalker.ad.admin.common.vo.SysUserVo;
import cn.adwalker.core.page.IPageInfo;

/**
 * <p>
 * Title: IBigAppService
 * </p>
 * <p>
 * Description:大媒体Service
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-9-6
 */
public interface IMediaAchievementService {

	/**
	 * <p>
	 * Title: findByPage
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param pageInfo
	 * @param bean
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-9-6
	 * @return java.util.List<mediaAchievementVo>
	 * @version 1.0
	 * @param user
	 * @param res
	 */
	public List<MediaAchievementVo> findByPage(IPageInfo pageInfo,
			MediaAchievementBean bean,SysUserVo user)
			throws Exception;

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
	 * @return mediaAchievementVo
	 * @version 1.0
	 */
	public MediaAchievementVo findTotal(MediaAchievementBean bean, SysUserVo user)
			throws Exception;

	/**
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
	 * @author lichuang
	 * @date 2013-9-7
	 * @return List<Object>
	 * @version 1.0
	 */
	public List<Object> findAll(MediaAchievementBean bean,SysUserVo user) throws Exception;

}
