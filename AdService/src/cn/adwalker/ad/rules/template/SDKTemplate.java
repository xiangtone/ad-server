/**
 * <p>Title: SDKTemplate.java</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author www.adwalker.cn
 * @date 2013-2-28
 * @version 1.0
 */
package cn.adwalker.ad.rules.template;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.adwalker.ad.cache.element.Advertise;
import cn.adwalker.ad.cache.element.UserAdRel;
import cn.adwalker.ad.dao.domain.DevCurrency;
import cn.adwalker.ad.picker.constants.C;
import cn.adwalker.ad.picker.constants.T;
import cn.adwalker.ad.picker.constants.V;
import cn.adwalker.ad.picker.form.AdForm;
import cn.adwalker.ad.picker.memcached.AdCache;
import cn.adwalker.ad.picker.util.NumberUtil;
import cn.adwalker.ad.picker.util.RandomCollection;
import cn.adwalker.ad.picker.util.StringUtil;
import cn.adwalker.ad.rules.PageEngine;
import cn.adwalker.ad.rules.RulesUtil;
import cn.adwalker.ad.rules.bean.ScoreConfigBean;
import cn.adwalker.ad.rules.config.ISDKConfig;
import cn.adwalker.ad.rules.config.SignConfig;
import cn.adwalker.ad.util.AppConstant;
import cn.adwalker.ad.util.ComparatorAd;
import cn.adwalker.ad.util.ComparatorAdScore;
import cn.adwalker.ad.util.WallUtils;
import cn.adwalker.ad.vo.SignBean;
/**
 * <p>
 * Title: SDKTemplate
 * </p>
 * <p>
 * 模板算法骨架 公用方法 定义为 final 基本方法 定义为 abstract
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author www.adwalker.cn
 * @date 2013-2-28
 */
public abstract class SDKTemplate implements ISDKConfig{
	private static final Logger logger = LoggerFactory.getLogger(SDKTemplate.class);
	
	private SDKTemplate sdkTemplate;
	
	protected String os;
	
	protected String page_type;
	
	protected List<UserAdRel> userAdRels;
	
	public void setUserAdRels(List<UserAdRel> userAdRels) {
		this.userAdRels = userAdRels;
	}

	private SignBean sign = SignConfig.getInstance().getSignBean();
	
	@Override
	public PageEngine initPageEngine(AdForm form){
		PageEngine page = PageEngine.createPage(form.getList(), form.getPageNo(), form.getPageSize(),form.getPage_type());
		List<Advertise> list=this.fatchPage(page.isSingle(),page,form.getList(),form.getVersion(),form.getImage_type(),form.getQuickly_task_config(),form.getQuickly_task(),form.getApp().getCurrency(),form.getApp().getScale());
		page.setList(list);
		return page;
		
	}
	
	private List<Advertise> fatchPage(boolean isSingle,
			PageEngine page,List<Advertise> list,
			String version,
			String image_type,
			Integer quickly_task_rate,
			String quickly_task,
			DevCurrency currency,Float scale){
		List<Advertise> aList = null;
		if(isSingle){
			aList = new ArrayList<Advertise>();
			Advertise ad = this.fatchAd(list,quickly_task_rate,version,image_type,quickly_task,currency,scale);
			if(ad!=null){
				aList.add(ad);
			}
		}else{
			aList= fatch(page.getPageList(), version, image_type, quickly_task_rate, quickly_task, currency, scale);
		}
		return aList;
	}
	
	
	@Override
	public List<Advertise> fatch(List<Advertise> list,String version,String image_type, Integer quickly_task_rate,String quickly_task,DevCurrency currency,Float scale){
		List<Advertise> aList = new ArrayList<Advertise>();
		if(null!=list){
			for(Advertise adv:list){
				if(this.checkAdvertise(adv,version,image_type,currency, scale,quickly_task,quickly_task_rate)){
					aList.add(adv);
				}
			}
		}
		return aList;
		
	}
	
	
	@Override
	public List<Advertise> fatch(List<Advertise> list,DevCurrency currency,Float scale){
		return fatch(list, null, null, null, null, currency, scale);
		
	}
	
	
	public void setPage_type(String page_type) {
		this.page_type = page_type;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public SDKTemplate getSdkTemplate() {
		return sdkTemplate;
	}
	public void setSdkTemplate(SDKTemplate sdkTemplate) {
		this.sdkTemplate = sdkTemplate;
	}

	
	public SignBean getSign() {
		return sign;
	}

	public String sdkVersion="SDK";
	
	
	public String setSdkVersion(String sdkVersion){
		return this.sdkVersion=sdkVersion;
	}
	public String getSdkVersion(){
		return sdkVersion;
	}
	
	/**
	 * 
	* <p>Title: removeExecute</p>
	* <p>Description:广告判断过滤</p>
	* @param ad
	* @param app_balck
	* @param app_placement
	* @param u
	* @param isSign
	* @param sign
	* @param os
	* @param isIos7
	* @param hot
	* @return
	* @author cuidd
	* @date 2014年10月16日
	* @return List<Advertise>
	* @version 1.0
	 */
	@Override
	public final List<Advertise> removeExecute(Advertise ad,Map<String, String> ruleMap,List<UserAdRel> userAdRels,boolean isIos7) {
		List<Advertise> removeList = new ArrayList<Advertise>();
		boolean target = false;
		String adMatch = createAdMatch(ad);
		String u_matchInfo=ruleMap.get(RulesUtil.RULE_KEY_USER_MATCHINFO);
		if(u_matchInfo.matches(adMatch)){
			//地区过滤
			if(ad.getArea_directional_str()!=null){
				String u_areacode=ruleMap.get(RulesUtil.RULE_KEY_USER_AREACODE);
			    if(u_areacode==null){
			    	logger.debug("area filter code is null!");
			    	target = true;
			    }else if(ad.getArea_directional_str().indexOf(u_areacode)==-1){
			    	logger.debug("area filter code ["+u_areacode+"]"+"area str ["+ad.getArea_directional_str()+"]");
			    	target = true;
				}
		     }
			
		   	//黑名单判断
			String adIdStr = StringUtil.dealNull(ad.getPlacement_id());
			String app_black=ruleMap.get(RulesUtil.RULE_KEY_APP_BLACK);
			 if(adIdStr.matches(app_black)){
				 logger.debug(adIdStr+" black filter:" + adIdStr+" matches  "+app_black);
				 target = true;
			 }
			 
			//ios版本判断
			if (StringUtil.equals(os, AppConstant.OS_IOS)){
				String sdkVerision = getSdkVersion().compareTo(V.IOS220.toString())<0?V.IOS210.toString():V.IOS220.toString();
				String iosVersion= isIos7?"iOS7":"iOS6";
				String sdkMatch=sdkVerision+"_"+iosVersion;
				String sv = ad.getSdk_version_str();
				String svReg=StringUtil.isEmpty(sv)?".*":"("+StringUtil.rt(sv).replaceAll(",", "|")+")";
				if(!sdkMatch.matches(svReg)){
					logger.debug(adIdStr+" sdkVersion filter:" + sdkMatch+" matches  "+svReg);
					target = true;
				}
			}
			
			//签到判断
			UserAdRel uar =RulesUtil.getUserAdRel(ad.getId(), userAdRels);
			//签到完成过滤
			Integer sign_status=StringUtil.equals(os, AppConstant.OS_IOS)?0:sign.getSign_status();
			if(uar!=null && uar.getSign_num()>sign_status){
				logger.debug(adIdStr+" signNum filter:" + uar.getSign_num());
				target = true;
			}
			
			//签到过滤
			String si=ruleMap.get(RulesUtil.RULE_KEY_SYS_SIGN);
			if(!StringUtil.isEmpty(si)&&si.equals("1")&&uar==null){
				logger.debug(adIdStr+" sign filter");
				target = true;
			}
			
			//热门推荐墙滤
			String hot=ruleMap.get(RulesUtil.RULE_KEY_SYS_HOT);
			if(StringUtil.equals(hot, C.LOGIC_TRUE) && 1!=NumberUtil.getInteger(ad.getPopular_app(), 0)){
				logger.debug(adIdStr+" hot filter");
				target = true;
			}
			//热门推荐，如果积分墙中有此广告则不显示
			if(StringUtil.equals(hot, C.LOGIC_TRUE) && 1==NumberUtil.getInteger(ad.getPopular_app(), 0)){
				String app_placement=ruleMap.get(RulesUtil.RULE_KEY_APP_PLACEMENT);
				String placementReg = AdCache.newInstance().getPlacementReg(os, T.jifen.toInteger(),Long.valueOf(app_placement));
				//logger.info("=============================================="+adIdStr+"  "+ad.getPlacement_id()+" matches "+placementReg);
				if(StringUtil.dealNull(ad.getPlacement_id()).matches(placementReg)){
					logger.debug(adIdStr+"  "+ad.getPlacement_id()+" matches "+placementReg);
					target = true;
				}
			}
		}else{
			logger.debug("matchInfo:"+u_matchInfo+" matches  "+createAdMatch(ad)+")");
			target=true;
		}
		
		if (target == true) {
			removeList.add(ad);
		}
		return removeList;
	}
	
	
	
	@Override
	public final List<Advertise> matchAdListByRules(List<Advertise> advertiseList,Map<String,String> ruleMap,String mac){
		List<Advertise> removeList = new ArrayList<Advertise>();
		for(Advertise ad:advertiseList){
			removeList.addAll(this.removeExecute(ad,ruleMap,userAdRels,RulesUtil.isIos7(mac,os)));
		}
		//删除不匹配的广告
		advertiseList.removeAll(removeList);
		return advertiseList;
	}
	
	
	@Override
	public  List<Advertise> removeRepeate(List<Advertise> advertiseList) {
		// 排重复
		if (page_type.equals(AppConstant.PAGE_WALLTYPE_LIST_SMALL)&&os.equals(AppConstant.OS_IOS)){
			Map<String, Advertise> map=new HashMap<String, Advertise>();
			List<Advertise> removeList=new ArrayList<Advertise>() ;
			for (Advertise ad:advertiseList) {
				if (!StringUtil.isEmpty(ad.getAppstore_id())) {
					String key=ad.getAppstore_id();
					if (map.get(key)!=null) {
						Advertise old=map.get(key);
						if (ad.getBlance_price()>old.getBlance_price()) {
							removeList.add(old);
						}else {
							removeList.add(ad);
						}
					}else {
						map.put(ad.getAppstore_id(), ad);
					}
					
				}
			}
			
		}
			
		return advertiseList;
	}
	
	@Override
	public  List<Advertise> adSorter(List<Advertise> advertiseList,Integer isSign){
		// 排序
		if (page_type.equals(AppConstant.PAGE_WALLTYPE_LIST_SMALL)) {
			if (os.equals(AppConstant.OS_ANDROID) &&isSign == 1) {
				ComparatorAdScore comparator = new ComparatorAdScore();
				Collections.sort(advertiseList, comparator);
			} else {
				ComparatorAd comparator = new ComparatorAd();
				Collections.sort(advertiseList, comparator);
			}
		} else {
			ComparatorAd comparator = new ComparatorAd();
			Collections.sort(advertiseList, comparator);
		}
		return advertiseList;	
	}
	
	
	
	
	//匹配规则
	private final String createAdMatch(Advertise ad){
		StringBuffer sbuf = new StringBuffer();
		String pad = StringUtil.dealNull(ad.getTerminal_type_str(),".*");
		sbuf.append("P:"+pad);
		String brand =   StringUtil.dealNull(ad.getPhone_brand_str(),".*"); 
		sbuf.append(",B:"+brand);
		//运营商
		String op = ad.getOperat_agencies_str();
		String operate = StringUtil.isEmpty(op)?".*":"("+StringUtil.rt(op).replaceAll(",", "|")+")";
		sbuf.append(",O:"+operate);
		//应用类型
		
		String cate = ad.getApp_type_str();
		String appCate=StringUtil.isEmpty(cate)?".*":"("+StringUtil.rt(cate).replaceAll(",", "|")+")";
		sbuf.append(",C:"+appCate);
		//时间段
		String h = ad.getTime_directional_str();
		String hour=StringUtil.isEmpty(h)?".*":"("+StringUtil.rt(h).replaceAll(",", "|")+")";
		sbuf.append(",H:"+hour);
		//地区
		//String a = ad.getPlacement().getArea_directional_str();
		//String area = StringUtil.isEmpty(a)?".*":"("+rt(a).replaceAll(",", "|").replaceAll("市", "")+")";
		//sbuf.append(",A:"+area);
		return sbuf.toString();
	}
		

	/***
	 * 根据SDK版本号返回广告类型
	 * <p>
	 * Title: getAd_type
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param version
	 * @param ad_type
	 * @return int
	 * @throws
	 */
	private int getAd_type(String version, int ad_type) {
		return ad_type;
	}
	
	
	private Advertise fatchAd(List<Advertise> adList,Integer quickly_task_rate,String version,String image_type,String quickly_task,DevCurrency devCurrency,Float scale){
		RandomCollection<Advertise> rc = new RandomCollection<Advertise>();
		for(Advertise adv:adList){
			rc.add(adv.getWeight(), adv);
		}
		Advertise ad=rc.next();
		while(!this.checkAdvertise(ad,version,image_type,devCurrency, scale,quickly_task,quickly_task_rate)){
			rc.remap(ad);
			ad = rc.next();
		}
		return ad;
	}
	
	
	/**
	 * 积分流程 
	* <p>Title: getAdScore</p>
	* <p>Description:计算积分</p>
	* @param price
	* @param exchange_rate_rmb
	* @param sign
	* @return
	* @return int
	 */
	//--------------------
	protected int getAdScore(ScoreConfigBean bean) {
		int result =0;
		Double score = bean.getPrice()* bean.getExchange_rate_rmb() * bean.getScale();
		if(sign.getSign_status()==AppConstant.SCORE_SIGN_STATUS_CLOSED){
			
		}else {
			int sign_num = 0;
			if(bean.getUserAdRels()!=null){
				for(UserAdRel userAdRel:bean.getUserAdRels()){
					if(userAdRel.getAd_id().equals(bean.getAd_id())){								
						sign_num=userAdRel.getSign_num();
						break;
					}
				}
			}
						
			if(sign_num==0){
			     score = (score*sign.getSign_first())/10;
			}else if(sign_num==AppConstant.SIGN_NUM_FIRST){
				 score = (score*sign.getSign_second())/10;
			}else{
				score = (score*sign.getSign_third())/10;
			}
		}
		
		result = score.intValue();
		return result;
	}
	
	//-----------2014-05-21---------,更新广告最新数据(单个广告)
	private boolean checkAdvertise(Advertise ad,String version,String image_type,DevCurrency currency,Float scale,String quickly_task,Integer quickly_task_rate){
		if(ad!=null){
			if (page_type.equals(AppConstant.PAGE_WALLTYPE_LIST_SMALL)) {
				if (ad.getBlance_price() != null && ad.getBlance_price() > 0) {
					ad.setShow_score(this.getAdScore(new ScoreConfigBean(ad.getBlance_price(), Integer.parseInt(currency.getExchange_rate_rmb()), scale, userAdRels, ad.getId(),quickly_task,quickly_task_rate)));
				} else {
					ad.setShow_score(0);
				}
				// 积分转化为虚拟货币
				if (!StringUtil.isEmpty(quickly_task)&&quickly_task.equals("1")) {
					ad.setScoreUnit("金豆");
				}else {
					ad.setScoreUnit(currency.getVirtual_currency_name());
				}
				
				// 广告下载激活状态
				ad.setIsDownload(WallUtils.getIsDownload(ad.getId(), ad.getIsDownload(),userAdRels));
			}
			// 获取图片
			if (!StringUtil.isEmpty(image_type)) {
				ad = WallUtils.getPicture(page_type, image_type, ad, os);
			}
			// ad_type处理
			ad.setResponse_type(this.getAd_type(version,ad.getResponse_type()));
		}
		return true;
	}
}