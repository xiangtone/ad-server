/**
 * <p>Title: RulesUtil.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author www.adwalker.cn
 * @date 2013-1-5
 * @version 1.0
 */
package cn.adwalker.ad.rules;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.adwalker.ad.cache.element.Advertise;
import cn.adwalker.ad.cache.element.UserAdRel;
import cn.adwalker.ad.cache.element.UserInfo;
import cn.adwalker.ad.picker.constants.T;
import cn.adwalker.ad.picker.util.DateUtil;
import cn.adwalker.ad.picker.util.StringUtil;
import cn.adwalker.ad.rules.config.SignConfig;
import cn.adwalker.ad.rules.strategy.BannerStrategy;
import cn.adwalker.ad.rules.strategy.StrategyPic;
import cn.adwalker.ad.util.AppConstant;
import cn.adwalker.ad.util.ComparatorAd;
import cn.adwalker.ad.util.ComparatorAdScore;
import cn.adwalker.ad.util.ConfigUtil;

/**
 * <p>
 * Title: RulesUtil
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author www.adwalker.cn
 * @date 2013-1-5
 */
@SuppressWarnings("rawtypes")
public class RulesUtil {

	private static final Logger log = LoggerFactory.getLogger(RulesUtil.class);
	public final static int IMAGGE_TYPE_0_WIDTH = Integer.parseInt(ConfigUtil.getString("images.type.0.width"));
	public final static int IMAGGE_TYPE_0_HEIGHT = Integer.parseInt(ConfigUtil.getString("images.type.0.height"));
	public final static int IMAGGE_TYPE_1_WIDTH = Integer.parseInt(ConfigUtil.getString("images.type.1.width"));
	public final static int IMAGGE_TYPE_1_HEIGHT = Integer.parseInt(ConfigUtil.getString("images.type.1.height"));
	public final static int IMAGGE_TYPE_2_WIDTH = Integer.parseInt(ConfigUtil.getString("images.type.2.width"));
	public final static int IMAGGE_TYPE_2_HEIGHT = Integer.parseInt(ConfigUtil.getString("images.type.2.height"));
	public final static int IMAGGE_TYPE_3_WIDTH = Integer.parseInt(ConfigUtil.getString("images.type.3.width"));
	public final static int IMAGGE_TYPE_3_HEIGHT = Integer.parseInt(ConfigUtil.getString("images.type.3.height"));
	public final static int IMAGGE_TYPE_4_WIDTH = Integer.parseInt(ConfigUtil.getString("images.type.4.width"));
	public final static int IMAGGE_TYPE_4_HEIGHT = Integer.parseInt(ConfigUtil.getString("images.type.4.height"));
	public final static int IMAGGE_TYPE_5_WIDTH = Integer.parseInt(ConfigUtil.getString("images.type.5.width"));
	public final static int IMAGGE_TYPE_5_HEIGHT = Integer.parseInt(ConfigUtil.getString("images.type.5.height"));
	
	
	public static final String RULE_KEY_APP_BLACK="app_black";//应用属性--黑名单
	
	public static final String RULE_KEY_APP_PLACEMENT="app_placement";//应用属性过滤--大媒体投放
	
	public static final String RULE_KEY_SYS_SIGN="isSign";//系统属性过滤--是否签到
	
	public static final String RULE_KEY_SYS_HOT="hot";//系统属性过滤--热门推荐
	
	
	public static final String RULE_KEY_USER_MATCHINFO="u_matchInfo";//用户属性过滤--用户属性
	
	
	public static final String RULE_KEY_USER_AREACODE="u_areacode";
	
	
	public static Map<String,String> createRuleMap(String app_matchBlack,Long app_placement,Integer isSign,String hot,UserInfo u){
		Map<String, String> ruleMap=new HashMap<String, String>();
		ruleMap.put(RulesUtil.RULE_KEY_APP_BLACK,app_matchBlack);
		ruleMap.put(RulesUtil.RULE_KEY_APP_PLACEMENT,String.valueOf(app_placement));
		ruleMap.put(RulesUtil.RULE_KEY_SYS_SIGN, String.valueOf(isSign));
		ruleMap.put(RulesUtil.RULE_KEY_SYS_HOT, hot);
		ruleMap.put(RulesUtil.RULE_KEY_USER_MATCHINFO,u.matchInfo);
		ruleMap.put(RulesUtil.RULE_KEY_USER_AREACODE, u.getAreaCode());
		return ruleMap;
	}
	
	
	

	// 1:不限，2：指定，3：排除
	public final static int RULE_TYPE_UNLIMIT = 1;
	public final static int RULE_TYPE_APPPOINT = 2;
	public final static int RULE_TYPE_EXCLUDE = 3;

	// 定向投放优先级
	public final static int APPOINT_ORDER = 1000;

	/**
	 * 策略模式
	 */
	private static HashMap<String, Class> picMap;
	{
		picMap = new HashMap<String, Class>();
		picMap.put(AppConstant.PAGE_WALLTYPE_BANNER, BannerStrategy.class);
		picMap.put(AppConstant.PAGE_WALLTYPE_PLAQUE, StrategyPic.class);
	}
	
	public static Integer getAdListByNotice(List<Advertise> advertiseList,List<UserAdRel> userAdRels){
		Integer num =0;
		if(userAdRels!=null){
		   for(Advertise ad:advertiseList){
			   UserAdRel uar = getUserAdRel(ad.getId(),userAdRels);
			   if(uar!=null&&uar.getCreate_time()!=null && SignConfig.getInstance().getSign_status()>uar.getSign_num()){
				   num+=1;
			   }
		   }	
		}
		return num;
	}
	
	public static UserAdRel getUserAdRel(Long ad_id,List<UserAdRel> list){
		if(list!=null){
			for(UserAdRel uar:list){
				if(StringUtil.equals(ad_id, uar.getAd_id())){
					return uar;
				}
			}
		}
		return null;
	}
	

	
	public static List<Advertise> getAdListByTrack(List<Advertise> advertiseList,UserInfo userInfo, T t) {
		List<Advertise> trackList = new ArrayList<Advertise>();
		for(Advertise ad:advertiseList){
			if(StringUtil.dealNull(ad.getId()).matches(StringUtil.dealNull(userInfo.getAppIds(),"nomatch"))){
				UserAdRel uar = getUserAdRel(ad.getId(),userInfo.getSignAdRels());
				if(null!=uar){
					ad.setCreate_time(uar.getCreate_time());
				}
				trackList.add(ad);
				log.info(ad.getId()+" match "+userInfo.getAppIds());
			}else{
				log.info(ad.getId()+" not match "+userInfo.getAppIds());
			}	
		}
		// 排序
		if (t==T.jifen) {
			ComparatorAdScore comparator = new ComparatorAdScore();
			Collections.sort(trackList, comparator);
		} else {
			ComparatorAd comparator = new ComparatorAd();
			Collections.sort(trackList, comparator);
		}
		return trackList;
	}
	
	public static boolean isIos7(String mac,String os){
		return StringUtil.equals("020000000000",mac)||StringUtil.parseVersion(os)>=7;
	}
	
	
	
	
	
	

	/**
	 * 
	* <p>Title: getBannel</p>
	* <p>Description:获取banner广告</p>
	* @param adList
	* @return
	* @author cuidd
	* @date 2013-7-5
	* @return List<Advertise>
	* @version 1.0
	 */
	public static final List<Advertise> getBannel(List<Advertise> adList) {
		int fenmu = 0;
		int adSize = adList.size();
		for (Advertise ad : adList) {
			fenmu = fenmu + ad.getWeight();
		}
		int fenzi = (int) (Math.random() * (fenmu)) + 1;
		int position = getPosition(fenzi, adList);
		if (position >= adSize)
			position = adSize - 1;
		List<Advertise> result = adList.subList(position, position + 1);

		return result;
	}

	private static int getPosition(int fenzi, List<Advertise> adList) {
		int result = 0;
		int sum = 0;
		for (int i = 0; i < adList.size(); i++) {
			sum = sum + adList.get(i).getWeight();
			if (sum >= fenzi) {
				result = i;
				break;
			}
		}
		return result;
	}

	
	//1:终端类型(pad) 2:手机品牌 3:运营商 4:应用类型 5:黑名单 6:时间段 7:地区 
	//System.out.println("P:0,B:sunm,O:union,C:1002,H:15,A:北京".matches("P:.*,B:.*,P:.*,C:(1002|1003),H:(14|15),A:北京|福建"));
	public static void createMatchInfo(UserInfo u,Long app_catalog_id,String telModel){
		StringBuffer sbuf = new StringBuffer();
		//String pad = StringUtil.equals(AppConstant.LOGIC_FALSE, StringUtil.dealNull(u.getPdaType(),AppConstant.LOGIC_FALSE))?AppConstant.PHONE:AppConstant.PAD;
		String pad = parsePadType(u,telModel);
		sbuf.append("P:"+pad);
		String band = StringUtil.equals(StringUtil.dealNull(u.getBrand()), "samsung")?"samsung":"other"; //三星与非三星
		sbuf.append(",B:"+band);
		String operate = StringUtil.dealNull(u.getOperator());
		sbuf.append(",O:"+operate);
		String appCate=StringUtil.dealNull(app_catalog_id);//
		sbuf.append(",C:"+appCate);
		String hour = DateUtil.getHourString(new Date());
		sbuf.append(",H:"+hour);
		//String area = StringUtil.dealNull(u.getAreaCode());
		//sbuf.append(",A:"+area);
		u.matchInfo=sbuf.toString();
	}
	//1:pad 0:非pad -1:未知
	public static String parsePadType(UserInfo u,String telModel){
        if(u!=null){
        	//telmodel有值则用telmodel来判断
        	if(!StringUtil.isEmpty(u.getTelModel())){
        		return u.getTelModel().matches("(?i).*ipad.*")?AppConstant.PAD:AppConstant.PHONE;
        	}else if(!StringUtil.isEmpty(u.getPdaType())){
        		return StringUtil.equals(AppConstant.LOGIC_FALSE, StringUtil.dealNull(u.getPdaType(),AppConstant.LOGIC_FALSE))?AppConstant.PHONE:AppConstant.PAD;
        	}else {
        		return AppConstant.UNKNOWN;
        	}
        }else{
        	if(!StringUtil.isEmpty(telModel)){
        		return  telModel.matches("(?i).*ipad.*")?AppConstant.PAD:AppConstant.PHONE;
        	}else{
        		  return AppConstant.UNKNOWN;
        	}
        }
      
	}
}
