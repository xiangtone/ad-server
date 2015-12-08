package cn.adwalker.ad.rules.config;

import java.util.List;
import java.util.Map;

import cn.adwalker.ad.cache.element.Advertise;
import cn.adwalker.ad.cache.element.UserAdRel;
import cn.adwalker.ad.dao.domain.DevCurrency;
import cn.adwalker.ad.picker.form.AdForm;
import cn.adwalker.ad.rules.PageEngine;
import cn.adwalker.ad.vo.SignBean;

public interface ISDKConfig {
	
	public PageEngine initPageEngine(AdForm form);
	
	public SignBean getSign();
	
	/**
	 * 
	* <p>Title: matchAdListByRules</p>
	* <p>Description:广告规则处理</p>
	* @param advertiseList
	* @param isSign
	* @param mac
	* @param os
	* @param app_matchBlack
	* @param app_placement
	* @param u
	* @param hot
	* @return
	* @author cuidd
	* @date 2014年10月31日
	* @return List<Advertise>
	* @version 1.0
	 */
	public List<Advertise> matchAdListByRules(List<Advertise> advertiseList,Map<String,String> ruleMap,String mac);
	
	
	/**
	 * 
	* <p>Title: removeRepeate</p>
	* <p>Description:广告排重</p>
	* @param advertiseList
	* @param os
	* @param page_type
	* @return
	* @author cuidd
	* @date 2014年11月19日
	* @return List<Advertise>
	* @version 1.0
	 */
	public List<Advertise> removeRepeate(List<Advertise> advertiseList);
	
	
	/**
	 * 
	* <p>Title: adSorter</p>
	* <p>Description:广告排序</p>
	* @param advertiseList
	* @param page_type
	* @param os
	* @param isSign
	* @return
	* @author cuidd
	* @date 2014年11月19日
	* @return List<Advertise>
	* @version 1.0
	 */
	public List<Advertise> adSorter(List<Advertise> advertiseList,Integer isSign);

	
	List<Advertise> fatch(List<Advertise> list,DevCurrency currency, Float scale);

	List<Advertise> removeExecute(Advertise ad, Map<String, String> ruleMap,
			List<UserAdRel> userAdRels, boolean isIos7);

	List<Advertise> fatch(List<Advertise> list, String version,
			String image_type, Integer quickly_task_rate, String quickly_task,
			DevCurrency currency, Float scale);
}
