/**
* <p>Title: PlaquePicture.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2013-1-14
* @version 1.0
*/
package cn.adwalker.ad.rules.strategy;


import cn.adwalker.ad.cache.element.Advertise;
import cn.adwalker.ad.dao.domain.MaterielChartboost;
import cn.adwalker.ad.rules.RulesUtil;
import cn.adwalker.ad.util.AppConstant;

/**
 * <p>Title: PlaquePicture</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2013-1-14
 */
public class PlaqueStrategy implements StrategyPic {
	
	/**  (non-Javadoc)
	 * <p>Title: getPicture</p>
	 * <p>Description:TODO</p>
	 * @param wallType
	 * @param imageType
	 * @param ad
	 * @return
	 * @see cn.adwalker.ad.rules.strategy.StrategyPic#getPicture(java.lang.String, java.lang.String, com.adwalker.escore.bean.Ad)
	 */
	
	public Advertise setPictureWh(String wallType, String imageType, Advertise ad,String os){
		if(os.equals(AppConstant.OS_ANDROID)){
			if(imageType.equals(AppConstant.PAGE_IMAGE_TYPE_PLAQUE_WIDTH)){
				ad.setAdimage_width(RulesUtil.IMAGGE_TYPE_2_WIDTH);
				ad.setAdimage_height(RulesUtil.IMAGGE_TYPE_2_HEIGHT);
			}else{
				ad.setAdimage_width(RulesUtil.IMAGGE_TYPE_3_WIDTH);
				ad.setAdimage_height(RulesUtil.IMAGGE_TYPE_3_HEIGHT);
			}
		}else{

			if(imageType.equals(AppConstant.PAGE_IMAGE_TYPE_PLAQUE_WIDTH)){
				ad.setAdimage_width(RulesUtil.IMAGGE_TYPE_4_WIDTH);
				ad.setAdimage_height(RulesUtil.IMAGGE_TYPE_4_HEIGHT);
			}else{
				ad.setAdimage_width(RulesUtil.IMAGGE_TYPE_5_WIDTH);
				ad.setAdimage_height(RulesUtil.IMAGGE_TYPE_5_HEIGHT);
			}
		}
		return ad;
	}
	
	
	@Override
	public Advertise getPicture(String wallType, String imageType, Advertise ad,String os) {
		MaterielChartboost materielChartboost=(MaterielChartboost) ad.getWall();
		if(os.equals(AppConstant.OS_ANDROID)){
			ad.setAdimage_url(AppConstant.imageF + materielChartboost.getImg_vertical());		
			ad.setAdimage_width(RulesUtil.IMAGGE_TYPE_3_WIDTH);
			ad.setAdimage_height(RulesUtil.IMAGGE_TYPE_3_HEIGHT);
			if(imageType.equals(AppConstant.PAGE_IMAGE_TYPE_PLAQUE_WIDTH)){
				ad.setAdimage_url(AppConstant.imageF + materielChartboost.getImg_horizontal());		
				ad.setAdimage_width(RulesUtil.IMAGGE_TYPE_2_WIDTH);
				ad.setAdimage_height(RulesUtil.IMAGGE_TYPE_2_HEIGHT);
			}		
		}else{
			ad.setAdimage_url(AppConstant.imageF + materielChartboost.getImg_vertical());		
			ad.setAdimage_width(RulesUtil.IMAGGE_TYPE_5_WIDTH);
			ad.setAdimage_height(RulesUtil.IMAGGE_TYPE_5_HEIGHT);
			if(imageType.equals(AppConstant.PAGE_IMAGE_TYPE_PLAQUE_WIDTH)){
				ad.setAdimage_url(AppConstant.imageF + materielChartboost.getImg_horizontal());		
				ad.setAdimage_width(RulesUtil.IMAGGE_TYPE_4_WIDTH);
				ad.setAdimage_height(RulesUtil.IMAGGE_TYPE_4_HEIGHT);
			}
		}
		return ad;
	}

}
