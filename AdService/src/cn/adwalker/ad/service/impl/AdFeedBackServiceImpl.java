/**
 * <p>Title: AdWallServiceImpl.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author www.adwalker.cn
 * @date 2014-03-14
 * @version 1.0
 */
package cn.adwalker.ad.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.adwalker.ad.bean.AdFeedBackJson;
import cn.adwalker.ad.cache.IAppBlackCache;
import cn.adwalker.ad.cache.IDevAppCache;
import cn.adwalker.ad.cache.ITypeCache;
import cn.adwalker.ad.cache.element.Advertise;
import cn.adwalker.ad.cache.element.DevApp;
import cn.adwalker.ad.cache.element.Type1;
import cn.adwalker.ad.param.FeedParam;
import cn.adwalker.ad.picker.constants.T;
import cn.adwalker.ad.service.IAdFeedbackService;
import cn.adwalker.ad.util.ConfigUtil;
import cn.adwalker.ad.vo.AdFeedBackNameJson;


@Service("adFeedBackService")
public class AdFeedBackServiceImpl implements IAdFeedbackService {

	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory
			.getLogger(AdFeedBackServiceImpl.class);

	public final static int CACHE_TIME = Integer.parseInt(ConfigUtil
			.getString("cache_time"));
	@Resource
	private IDevAppCache devAppCache;
	@Resource
	private ITypeCache typeCache;
	@Resource
	private IAppBlackCache appBlackCache;

	
		
		
	public AdFeedBackNameJson adBackFeedPicker(FeedParam vo,String os){
		AdFeedBackNameJson json = new AdFeedBackNameJson();
		//应用
		DevApp app = devAppCache.getDevApp(vo.getAppId());
		//if(!StringUtil.isEmpty(vo.page_type) && !StringUtil.isEmpty(app.typeIds) && vo.page_type.matches(app.typeIds)){			
			Type1 type = typeCache.getFeedBackType(os, T.jifen.toInteger(), app);
			List<Advertise> advertiseList =type.getAdvertiselist();
			json.setAdList(getAdsonList(advertiseList));						
		/*}else{
			json.setAdList(null);
			log.error(vo.page_type+" no match "+app.typeIds);
		}*/
		return json;
	}

	
	/**
	 * Title: getAdsonList
	 * Description:TODO
	 * @param result
	 * @return
	 * @return Object
	 * @throws
	 */
	private static final List<AdFeedBackJson> getAdsonList(List<Advertise> adList) {
		List<AdFeedBackJson> result = new ArrayList<AdFeedBackJson>();
		for (int i = 0; i < adList.size(); i++) {
			Advertise ad = (Advertise) adList.get(i);
			AdFeedBackJson adJson = new AdFeedBackJson();
			adJson.setId(ad.getId());			
			adJson.setName(ad.getAd_name());
			result.add(adJson);
		}
		return result;
	}

}
