package cn.adwalker.ad.admin.app.service;

import java.util.List;

import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.model.ad.domain.PlacementPackage;
import cn.adwalker.ad.admin.ad.form.AdAddForm;
import cn.adwalker.ad.admin.ad.form.AdEditForm;
import cn.adwalker.ad.admin.app.bean.HistoryAdQuery;
import cn.adwalker.ad.admin.app.bean.MediaBean;
import cn.adwalker.ad.admin.app.vo.AdByAppVo;
import cn.adwalker.ad.admin.app.vo.BigAppVo;
import cn.adwalker.ad.admin.app.vo.PlacementVo;

/**
 * <p>
 * Title: IMediaService
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
public interface IMediaService {

	/**
	 * <p>
	 * Title: findList
	 * </p>
	 * <p>
	 * Description:大媒体List
	 * </p>
	 * 
	 * @param bean
	 * @param pageInfo
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-9-6
	 * @return List<AppBlackAdVo>
	 * @version 1.0
	 */
	public List<BigAppVo> findList(MediaBean bean, IPageInfo pageInfo)
			throws Exception;

	/**
	 * <p>
	 * Title: findAll
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param app_id
	 * @param l
	 * @return
	 * @author cuidd
	 * @date 2013-9-6
	 * @return java.util.List<AdertiseAppVo>
	 * @version 1.0
	 * @param placement_id_name 
	 * @throws Exception
	 */
	public List<PlacementVo> findAll(Long app_id, Long type_id, String placement_id_name)
			throws Exception;

	/**
	 * <p>
	 * Title: findPackage
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param placement_id
	 * @return
	 * @author cuidd
	 * @date 2013-9-6
	 * @return java.util.List<PlacementPackage>
	 * @version 1.0
	 * @throws Exception
	 */
	public List<PlacementPackage> findPackage(Long placement_id)
			throws Exception;

	/**
	 * <p>
	 * Title: getPageStrByApp
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param app_id
	 * @return
	 * @author cuidd
	 * @date 2013-9-6
	 * @return String
	 * @version 1.0
	 */

	public String getPageStrByApp(Long app_id);

	/**
	 * <p>
	 * Title: insertAd
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param form
	 * @param i
	 * @return
	 * @author cuidd
	 * @date 2013-9-6
	 * @return Long
	 * @version 1.0
	 * @throws Exception
	 */
	public Long insertAd(AdAddForm form, int i) throws Exception;

	/**
	 * <p>
	 * Title: deleteAd
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param id
	 * @author cuidd
	 * @date 2013-9-6
	 * @return void
	 * @version 1.0
	 * @throws Exception
	 */
	public void deleteAd(Long id) throws Exception;

	/**
	 * <p>
	 * Title: updateAd
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param form
	 * @author cuidd
	 * @date 2013-9-6
	 * @return void
	 * @version 1.0
	 * @throws Exception
	 */
	public void updateAd(AdEditForm form) throws Exception;

	/**
	 * <p>
	 * Title: findAndroidByPlacement
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param app_id
	 * @return
	 * @author cuidd
	 * @date 2013-9-7
	 * @return java.util.List<AdByAppVo>
	 * @version 1.0
	 * @throws Exception
	 */
	public List<AdByAppVo> findAndroidByPlacement(Long app_id) throws Exception;

	/**
	 * <p>
	 * Title: submitAd
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param ad_id
	 * @author cuidd
	 * @date 2013-9-7
	 * @return void
	 * @version 1.0
	 * @throws Exception
	 */
	public void submitAd(Long ad_id) throws Exception;

	/**
	* <p>Title: findHistoryAdByPage</p>
	* <p>Description:TODO</p>
	* @param bean
	* @param pageInfo
	* @return
	* @author cuidd
	* @date 2013-9-7
	* @return java.util.List<AdByAppVo>
	* @version 1.0
	 * @throws Exception 
	*/
	public java.util.List<AdByAppVo> findHistoryAdByPage(HistoryAdQuery bean,
			IPageInfo pageInfo) throws Exception;

}
