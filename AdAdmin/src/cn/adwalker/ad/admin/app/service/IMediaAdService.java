package cn.adwalker.ad.admin.app.service;

import java.util.List;

import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.ad.admin.ad.form.AdAjustmentEditForm;
import cn.adwalker.ad.admin.ad.vo.AdAjustmentEditVo;
import cn.adwalker.ad.admin.app.bean.MediaAdBean;
import cn.adwalker.ad.admin.app.vo.MediaAdVo;

/**
 * 
 * @author zouguibao
 * @version 1.0
 * @since 2013-5-14
 */
public interface IMediaAdService {
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
	 * @author cuidd
	 * @date 2013-5-15
	 * @return List<MediaRatingVo>
	 * @version 1.0
	 * @throws
	 */

	public List<MediaAdVo> findByPage(MediaAdBean bean, IPageInfo pageInfor)
			throws Exception;

	/**
	 * <p>
	 * Title: findById
	 * </p>
	 * <p>
	 * Description:方案详细内容查询
	 * </p>
	 * 
	 * @param adId
	 * @return
	 * @author lichuang
	 * @date 2013-6-4
	 * @return AdAjustmentEditVo
	 * @version 1.0
	 * @throws Exception
	 */
	public AdAjustmentEditVo findById(Long adId) throws Exception;

	/**
	 * <p>
	 * Title: updateAdAjustment
	 * </p>
	 * <p>
	 * Description:修改广告投放方案
	 * </p>
	 * 
	 * @param form
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-6-4
	 * @return void
	 * @version 1.0
	 */
	public void updateAdAjustment(AdAjustmentEditForm form) throws Exception;

	/**
	 * <p>
	 * Title: adOnline
	 * </p>
	 * <p>
	 * Description:修改广告上线
	 * </p>
	 * 
	 * @param adId
	 * @author lichuang
	 * @date 2013-6-4
	 * @return void
	 * @version 1.0
	 */
	public void adOnline(Long adId);

	/**
	 * <p>
	 * Title: adOffline
	 * </p>
	 * <p>
	 * Description:修改广告下线
	 * </p>
	 * 
	 * @param ids
	 * @author lichuang
	 * @date 2013-6-4
	 * @return void
	 * @version 1.0
	 */
	public void adbatchOffline(String ids);

	/**
	 * <p>
	 * Title: adSuspend
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param adId
	 * @author cuidd
	 * @date 2013-7-10
	 * @return void
	 * @version 1.0
	 * @throws Exception
	 */
	public void adOffline(Long adId) throws Exception;

	/**
	 * <p>
	 * Title: auditAd
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param ad_id
	 * @author cuidd
	 * @date 2013-9-26
	 * @return void
	 * @version 1.0
	 */
	public void auditAd(Long ad_id);
}
