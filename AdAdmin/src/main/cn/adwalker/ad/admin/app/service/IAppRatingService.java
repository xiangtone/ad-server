package cn.adwalker.ad.admin.app.service;

import java.util.List;

import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.ad.admin.app.vo.MediaRatingVo;
import cn.adwalker.ad.admin.common.vo.SysUserVo;
import cn.adwalker.ad.admin.operation.bean.MediaRatingBean;
/**
 * 
 * @author zouguibao
 * @version 1.0
 * @since 2013-5-14
 */
public interface IAppRatingService {
	/**
	 * @param user 
	* <p>Title: findByPage</p>
	* <p>Description:TODO</p>
	* @param bean
	* @param pageInfor
	* @return
	* @throws Exception
	* @author cuidd
	* @date 2013-5-14
	* @return List<MediaRatingVo>
	* @version 1.0
	* @throws
	*/
	
	public List<MediaRatingVo> findByPage(MediaRatingBean bean,
			SysUserVo user, IPageInfo pageInfor) throws Exception;
	
	public void updateMediaRating(Long mediaId,Double score);
}
