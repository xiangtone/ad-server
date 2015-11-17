package cn.adwalker.ad.admin.ad.service;

import java.util.List;

import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.ad.admin.operation.bean.AdPriceBean;
import cn.adwalker.ad.admin.operation.vo.AdPriceVo;

/**
 * <p>
 * Title: IAdPriceService
 * </p>
 * <p>
 * Description:广告单价service接口
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-7-9
 */
public interface IAdPriceService {
	/**
	 * <p>
	 * Title: findByPage
	 * </p>
	 * <p>
	 * Description:广告单价List
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

	public List<AdPriceVo> findByPage(AdPriceBean bean, IPageInfo pageInfor)
			throws Exception;

	/**
	 * <p>
	 * Title: modifyAdPrice
	 * </p>
	 * <p>
	 * Description:广告单价修改
	 * </p>
	 * 
	 * @param adId
	 * @param price
	 * @author lichuang
	 * @date 2013-7-9
	 * @return void
	 * @version 1.0
	 */
	public void modifyAdPrice(Long adId, Double price,Double price_update,Long manager_id)throws Exception;
}
