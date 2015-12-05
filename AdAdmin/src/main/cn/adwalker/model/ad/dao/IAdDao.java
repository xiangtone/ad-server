package cn.adwalker.model.ad.dao;

import java.util.List;

import cn.adwalker.model.ad.domain.Ad;
import cn.adwalker.ad.admin.ad.vo.AdAjustmentEditVo;
import cn.adwalker.ad.admin.ad.vo.AdChannelEditVo;
import cn.adwalker.core.repository.IBaseDao;

/**
 * 
 * <p>
 * Title: IAdDao
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-6-4
 */
public interface IAdDao extends IBaseDao<Ad> {

	/**
	 * 
	 * <p>
	 * Title: updateStatus
	 * </p>
	 * <p>
	 * Description:更新状态
	 * </p>
	 * 
	 * @param id
	 * @param manageUser
	 * @return
	 * @throws Exception
	 * @author cuidd
	 * @date 2013-4-12
	 * @return int
	 * @version 1.0
	 */
	public void updateStatus(List<Long> idList, Integer status)
			throws Exception;

	/**
	 * 
	 * <p>
	 * Title: updateStatus
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param idList
	 * @param status
	 * @throws Exception
	 * @author cuidd
	 * @date 2013-9-7
	 * @return void
	 * @version 1.0
	 */
	public void updateStatus(Long id,Integer status) throws Exception;

	/**
	 * <p>
	 * Title: updateStatusByPlacement
	 * </p>
	 * <p>
	 * Description:广告上线
	 * </p>
	 * 
	 * @param id
	 * @param i
	 * @author cuidd
	 * @date 2013-5-18
	 * @return void
	 * @version 1.0
	 * @throws Exception
	 */

	public void onlineAd(List<Long> onLine) throws Exception;

	/**
	 * 
	 * <p>
	 * Title: getAdsByByPlacement
	 * </p>
	 * <p>
	 * Description:根据投放id，获取上线的广告
	 * </p>
	 * 
	 * @param placement_id
	 * @return
	 * @author cuidd
	 * @date 2013-6-1
	 * @return List<AdOld>
	 * @version 1.0
	 * @throws Exception
	 */
	public List<Ad> getAdsByByPlacement(Long placement_id) throws Exception;

	/**
	 * <p>
	 * Title: modifyAdPrice
	 * </p>
	 * <p>
	 * Description:修改广告单价
	 * </p>
	 * 
	 * @param adId
	 * @param price
	 * @author lichuang
	 * @date 2013-6-4
	 * @return void
	 * @version 1.0
	 */
	public void modifyAdPrice(Long adId, Double price);

	/**
	 * <p>
	 * Title: findByAdId
	 * </p>
	 * <p>
	 * Description:查询某一条广告
	 * </p>
	 * 
	 * @param adId
	 * @return
	 * @author lichuang
	 * @date 2013-6-4
	 * @return AdAjustmentEditVo
	 * @version 1.0
	 */
	public AdAjustmentEditVo findByAdId(Long adId);

	/**
	 * <p>
	 * Title: updateAdAjustment
	 * </p>
	 * <p>
	 * Description:修改某一条广告的投放方案
	 * </p>
	 * 
	 * @param form
	 * @author lichuang
	 * @date 2013-6-4
	 * @return void
	 * @version 1.0
	 */
	public void updateAdAjustment(Ad ad);

	/**
	 * <p>
	 * Title: adOnline
	 * </p>
	 * <p>
	 * Description:修改某一条广告的状态为上线
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
	 * Description:修改某一条广告的状态为下线
	 * </p>
	 * 
	 * @param adId
	 * @author lichuang
	 * @date 2013-6-4
	 * @return void
	 * @version 1.0
	 */
	public void adOffline(Long adId);

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
	 */

	public void adSuspend(Long adId);

	/**
	 * 删除广告
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public void deleteAd(Long id) throws Exception;

	/**
	 * <p>
	 * Title: findChannelAdById
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param adId
	 * @return
	 * @author cuidd
	 * @date 2013-8-13
	 * @return AdChannelEditVo
	 * @version 1.0
	 */

	public AdChannelEditVo findChannelAdById(Long adId);

	/**
	 * <p>
	 * Title: adOver
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param adId
	 * @author cuidd
	 * @date 2013-8-13
	 * @return void
	 * @version 1.0
	 */
	public void adOver(Long adId);

	/**
	* <p>Title: adAudit</p>
	* <p>Description:TODO</p>
	* @param ad_id
	* @author cuidd
	* @date 2013-9-26
	* @return void
	* @version 1.0
	*/
	
	public void adAudit(Long ad_id);
}
