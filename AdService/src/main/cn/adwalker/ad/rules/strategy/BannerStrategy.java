/**
* <p>Title: BannerPicture.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2013-1-14
* @version 1.0
*/
package cn.adwalker.ad.rules.strategy;

import java.util.Random;

import cn.adwalker.ad.cache.element.Advertise;
import cn.adwalker.ad.dao.domain.MaterielBanner;
import cn.adwalker.ad.rules.RulesUtil;
import cn.adwalker.ad.util.AppConstant;

/**
 * <p>Title: BannerPicture</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2013-1-14
 */
public class BannerStrategy implements StrategyPic {


	@SuppressWarnings("unused")
	private static BannerStrategy instance = null;//暂时没用
	
	/**  (non-Javadoc)
	 * <p>Title: getPicture</p>
	 * <p>Description:TODO</p>
	 * @param wallType
	 * @param imageType
	 * @param ad
	 * @return
	 * @see cn.adwalker.ad.rules.strategy.StrategyPic#getPicture(java.lang.String, java.lang.String, com.adwalker.escore.bean.Ad)
	 */
	@Override
	public  Advertise getPicture(String wallType, String imageType, Advertise ad,String os) {
		Random random = new Random(3);
		MaterielBanner materielBanner=(MaterielBanner) ad.getWall();
		ad.setAdimage_url(AppConstant.imageF + materielBanner.getImg_url_first());		
		if(random.nextInt()==1){
			ad.setAdimage_url(AppConstant.imageF + materielBanner.getImg_url_first());		
		}else if(random.nextInt()==2){
			if(materielBanner.getImg_url_second()!=null)
			ad.setAdimage_url(AppConstant.imageF + materielBanner.getImg_url_second());		
		}else{
			if(materielBanner.getImg_url_third()!=null)
			ad.setAdimage_url(AppConstant.imageF + materielBanner.getImg_url_third());		
		}
		
		ad.setAdimage_width(RulesUtil.IMAGGE_TYPE_0_WIDTH);
		ad.setAdimage_height(RulesUtil.IMAGGE_TYPE_0_HEIGHT);
		return ad;
	}
	
	public Advertise setPictureWh(String wallType, String imageType, Advertise ad,String os){
		ad.setAdimage_width(RulesUtil.IMAGGE_TYPE_0_WIDTH);
		ad.setAdimage_height(RulesUtil.IMAGGE_TYPE_0_HEIGHT);
		return ad;
	}

}
