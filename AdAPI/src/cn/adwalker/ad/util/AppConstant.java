/**
 * <p>Title: RefreshLog4jConstant.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author www.adwalker.cn
 * @date 2013-1-4
 * @version 1.0
 */
package cn.adwalker.ad.util;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>
 * Title: RefreshLog4jConstant
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author www.adwalker.cn
 * @date 2013-1-4
 */
public class AppConstant {

	public final static String MEM_API_APP_REL = "memAPIAppRel";
	public static final String MEM_API_CHANNEL = "memAPIChannel";
	public static final String MEM_API_IOS_KEY = "memAPIKey";
	public static final String MEM_API_IOS_ID = "memAPIId";
	public static final String MEM_API_URLS = "memAPIUrls";
	
	// 墙的类别,1:墙，2：banner
	public static final String PAGE_WALLTYPE_LIST_SMALL = "0";// 积分墙
	
	
	
	public static final String OS_ANDROID = "android";
	public static final String OS_IOS = "ios";	//add by jeif
	public final static int CACHE_TIME = Integer.parseInt(ConfigUtil
			.getString("cache_time"));

	
	// 广告主接收数据方式
	public final static String SEND_TYPE_GET = "GET";
	public final static String SEND_TYPE_POST = "POST";
	
	
	/**
	 * 渠道
	 */
	public final static String CHANNEL_RUANLIE = "ruanlie";

	/**
	 * 终端类型 mark by cdd
	 */
	public static final String PAD = "ipad";
	/**
	 * 终端类型 mark by cdd
	 */
	public static final String ZIJIREN = "zijiren";
	
	public static final String CONFIG_ESCORE_SERVICE="---";//原来的值》》http://sdk.yijifen.com/EScore_Service/
	/**
	 * add by jief for 掌上惠
	 * @param appid
	 * @return
	 */
	public static boolean containt(String appid){
		Set<String> appids=new HashSet<String>();
		appids.add("13525");
		appids.add("13524");
		appids.add("13523");
		appids.add("13522");
		appids.add("13521");
		return appids.contains(appid);
	}
	//dsp 配置缓存
	public static final String DSP_INFO="dsp_info";
	public static final String DSP_CONFIG="dsp_config";
	
	//广告主接口服务
	public static final String CP_ZHSHY="zhongshouyou";						//中手游 接口服务
	public static final String CP_RAW_MAC="rawmac";	   						//发送原始mac值服务
	public static final String CP_ONEDEVICEID="oneDeviceid";				//mac 和Idfa 公用一个参数 ,idfa优先 并且mac小写不带":"
	public static final String CP_ONEDEVICEID_MAC_FIR="oneDeviceidMacFirst";//mac 和Idfa 公用一个参数 mac 优先
	public static final String CP_RAW_MAC_ALL="rawmacall";					//要求全部mac包括ios7的mac值并且发送ios version
	public static final String CP_QIAN_CHENG_WU_YOU="qianchengwuyou"; 		//增加了一个加密的签名sign
	public static final String CP_58_TONG_CHENG="58tongcheng";				//mac 和Idfa 公用一个参数 mac 优先 mac格式带 ":" 
	
	//每次从点击池中获取的点击的最大数目
	public static final int MAX_NUM=500;
	//百度广告类型 cpc 和 cpm
	public static final String BAIDU_CPM="1";
	public static final String BAIDU_CPC="2";
	
	//渠道接口回调服务名
	public static final String CH_DOMOB="ch_domob";							//多盟接口回调服务名
	public static final String MEMCACHE_AD_CLUSTER = "AD_CLUSTER";
}
