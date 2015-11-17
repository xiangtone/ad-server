package test;

import java.util.HashMap;
import java.util.Map;
import cn.adwalker.IOSChannel.picker.util.HttpClientUtils;
import cn.adwalker.ad.util.StringUtil;

public class Test {
    public static final String callBackUrl="http://api.adwalker.lc/AdAPI/common/ainfo.do";
	public static final String url = "http://106.120.153.229/AdAPI/common/cinfo.do";
	//public static final String url = "http://localhost/AdAPI/common/cinfo.do";

	//http://106.120.153.228/AdAPI/common/monitor.do?adId=6908&appkey=AWAAB718ZDIG1173B3TO3JUVL80MCXIEHB
	//public static final String url = "http://localhost/AdAPI/common/cinfo.do";
	public static void main(String args[]){
		/**
		 * 网易新闻         xztx_ios                            
         * 灭失西游   838086272   (未上线) 
         * 随手记           suishouji     
         * 航班管家    320479357
         * 返利网   fanli
         * 挖财 wacai
         * 秦时明月  qinsmy
         * 天外飞仙  tianwfx     
         * 勇者世界  yongzsj
         * 悟空传    wukongz
         * 美柚   meiyou
         * 忍之对决 renzdj
         * 放开那三国  fangknsg
         * 攻城掠地 gongcld
         * 糯米 nuomi
         * 封神英雄   fengsyx
         * 赶集网  ganji
         * 高铁管家 com.openet.gtgj
         * 快的打车 kuaiddc
         * 女帝-海贼王 nvdi
         * 格斗江湖 gedoujh
         * 途牛旅游 tuniu
         * 暴走武侠 baozwx
         * 怪兽连萌  xztx787040240 
         * 马上赚钱 1000
         * 黑暗光年  heiangn
         * 东邪西毒  dongxxd
         * 魔姬  moji
         * 星际联盟:失落的神庙  xingjlm
         * 星际联盟hd:失落的神庙  xingjlmhd
         * 决战尸魂界     juezshj
         * 国美在线  guomei2
         * 乱世曲 luansqym
         * 新浪新闻 sinaym
         * 战国之道  588276695
         * 向上理财 xiangshang360
         * 彩票宝  caipbym
         * 我去西游  woqxyym
         * 三国小时代  sanguoxsd
         * 霸者三国  bazhesg
         * 龙之守护   817912647  (米迪) 
         * 赚荷包  zhuanhebao
         * 搜狗 sogoumse
         * 365日历  365rili
         * 世纪佳缘  shijijy
         * 快的打车-米迪    551963288
         * 荒岛英雄  huangdyx
         * 恋舞OL  820140906
         * 勇者前线  yongzqxtd
         * 百度地图 baidudtym
         * 快的打车 (艾德思奇)  kuaiddcadsq
         * 灭世西游   838086272 
         * 天天爱萌仙   tiantamxck
         * 口袋奇迹  koudqjck
         * 绝杀2014  JS2014
         * 武侠Q传 wuxqcck
         * 同程旅游 tongclyaisi
         * 百度地图  baidumapaisi
         * 国美在线 (哇棒0515) guomei2
         * 格斗江湖 gedoujhck
         * 足球大逆袭  zuqiudnxaisi
         * 中华军事  xxs3VkyC5
         * 九城  1000099
         * 武侠Q传  wuxiaqzaisi
         * 口袋神兽   839428311
         * 曹操去哪儿(万普) caocqnewp
         * 地狱之门 2lvorApuht
         * 大海贼时代 dahzsdym
         * 
         * 百度地图 (有米)baidudituym
         * 创造球会 (千乘) CZQH01
         * 坑爹3   	861258056
         * 旅游攻略 (金山)  406596432
         * 大闹天宫    DNTG01HD
         * 大闹天宫   864846905
         * 锁链战记(触控)  suolzjck
         * 攻城掠地 (飞流)  100131
         * 四大萌捕 (聚点)  sdmb
         * 皇上吉祥  (huangsjx)  http://lnk8.cn/s4MBR1
         * 帝国塔防2 GTS_TD2
         * glu测试     	MEHTA_CORP
         * 进击の小伙伴(爱德威)  2663
         * 超级英雄 chaojyxfz
         * 三剑豪  sanjianhaowp
 		 * */
		//String res = HttpClientUtils.readFromURL(url, createMapIpone5s("meiyou"), "utf-8");
		//String res = HttpClientUtils.readFromURL(url, createMapPad("koudqjck"), "utf-8");
		String res = HttpClientUtils.readFromURL(url, createMapIpone5s("oU9rwxniR"), "utf-8");
		//String res = HttpClientUtils.readFromURL(url, test("gongcld"), "utf-8"createMapXiaojing);
		System.out.println(res+"   ---------------------------");
	}
	
	/**
	 * 
	    2014-04-29 11:41:18.085 deviceId[2104:907] idfa=9216BFC0-18AF-43FE-ACB9-84CDC33BAFE0
		2014-04-29 11:41:18.100 deviceId[2104:907] idfv=7C768EE8-CC39-44F0-B03E-B0AAD76196E2
		2014-04-29 11:41:18.105 deviceId[2104:907] mac=7CC3A1065EF1
		2014-04-29 11:41:18.262 deviceId[2104:907] openudid=76399ae4a4190074c61cf30015b9b532ce8f83c4
	 * */
	public static Map<String, String> createMapXiaojing(String appid){
		 Map<String, String> map = new HashMap<String, String>();
		 map.put("appid", appid);//appid
		 map.put("source", "adwalker"); //渠道名称
		 map.put("deviceid", "7CC3A1065EF1");//uuid mac
		 map.put("client_ip", "223.223.196.2");  //ip
		 map.put("app_key", "4000");//appKeys
		 map.put("ad_key","2398");//adKey5604f21af70416d13f24c4baeef368a56f938923
		 map.put("OPENUDID", "76399ae4a4190074c61cf30015b9b532ce8f83c4");//udid
		 map.put("IDFA", "9216BFC0-18AF-43FE-ACB9-84CDC33BAFE0");//idfa
		 map.put("IDFV", "7C768EE8-CC39-44F0-B03E-B0AAD76196E2");//idfv
		 map.put("os","7.0.1");//os
		 map.put("sdkVersion","2.0.1");
         map.put("page_type", "0");//类型
         String callback = callBackUrl+"?uuid="+map.get("deviceid")+"&appId="+map.get("app_key")+"&adId="+map.get("ad_key")+"&channel="+map.get("source")+"&page_type="+map.get("page_type")+"&version=Android2.0.1&&idfa="+map.get("IDFA");
         map.put("callback", StringUtil.encode(callback)); //callback
		 return map;
	}
	
	
/**
2014-08-12 17:46:54.422 deviceId[2123:60b] idfa=CA272410-31D8-452B-A41C-338928E35B00
2014-08-12 17:46:54.429 deviceId[2123:60b] idfv=F24C452F-BEBF-4979-A929-F8D47E5469CE
2014-08-12 17:46:54.431 deviceId[2123:60b] mac=020000000000
2014-08-12 17:46:54.542 deviceId[2123:60b] openudid=b94867f10c5166e9b277075e1c6354aa45f1641f
 * */	
	public static Map<String, String> test(String appid){
		 Map<String, String> map = new HashMap<String, String>();
		 map.put("appid", appid);//appid
		 map.put("source", "adwalker"); //渠道名称
		 map.put("deviceid", "CA272410-31D8-452B-A41C-338928E35B00");//uuid mac
		 map.put("client_ip", "111.197.111.253");  //ip
		 map.put("app_key", "4000");//appKeys
		 map.put("mac", "02:00:00:00:00:00");//appKeys
		 map.put("ad_key","2398");//adKey5604f21af70416d13f24c4baeef368a56f938923
		 map.put("OPENUDID", "b94867f10c5166e9b277075e1c6354aa45f1641f");//udid
		 map.put("IDFA", "CA272410-31D8-452B-A41C-338928E35B00");//idfa
		 map.put("IDFV", "F24C452F-BEBF-4979-A929-F8D47E5469CE");//idfv
		 map.put("os","7.0.1");//os
		 map.put("sdkVersion","2.0.1");
         map.put("page_type", "0");//类型
         String callback = callBackUrl+"?uuid="+map.get("deviceid")+"&appId="+map.get("app_key")+"&adId="+map.get("ad_key")+"&channel="+map.get("source")+"&page_type="+map.get("page_type")+"&version=Android2.0.1&&idfa="+map.get("IDFA");
         map.put("callback", StringUtil.encode(callback)); //callback
		 return map;
	}
	
	
	
	/**
	 * 
	 * 测试机器5s
idfa=9A7AA37E-4ADC-49F1-8C7C-5CBA480DCAEB
 idfv=2AA514F4-BF9A-4F6A-B818-EB37B7F99C9D
 mac=020000000000
 openudid=a19eee8330483183de05906b396c1d47aea378a3

测试机器ipad
idfa=EA911C82-EB20-4CE2-9035-396C29ED772E
idfv=0794D67D-94F0-449E-BCB2-D179034C2839
mac=020000000000
openudid=3276f8f0083b8c6e823673438650a16992a99fbd

庆福的iphone
idfa=06AC8090-8493-4F84-9F51-58B6549095C0
idfv=60709EB5-91B3-47CD-AFA2-FBBCDE7F35A2
mac=020000000000
openudid=5604f21af70416d13f04c6bfaef368a56a938923

波哥的iphone
idfa=C8DF116C-FA43-4831-891D-7C069133207A
idfv=BE016EA6-ECA4-4E8B-AA1D-BFB3D171DBDF
mac=1CE62B343D35
openudid=00fde8d7b8503181171de247e94f5c8bff16242c

秋梨的iphone
idfa=57161729-FA95-453D-9E59-8AEDE313F7F4
idfv=21DB21E3-04F6-4231-A9AE-BEC90E11452C
mac=020000000000
openudid=1bbb0820cefb35229262427a70afbf8e54a98af9
	 * 


	 * */
	
	/**
	 * 庆福的iphone
		idfa:06AC8090-8493-4F84-9F51-58B6549095C0
		idfv:8ABD3EEA-0975-4F00-AB44-3A436488D954
		mac:020000000000
		openudid:5604f21af70416d13f04c6bfaef368a56a938923
		
		
		idfa:06AC8090-8493-4F84-9F51-58B6549095C0
		idfv:8ABD3EEA-0975-4F00-AB44-3A436488D954
		mac:020000000000
		openudid:5604f21af70416d13f04c6bfaef368a56a938923
	 * */
	public static Map<String, String> createMapQingfu(String appid){
		 Map<String, String> map = new HashMap<String, String>();
		 map.put("appid", appid);//appid
		 map.put("source", "adwalker"); //渠道名称
		 map.put("deviceid", "862E62FE-58DE-4CD1-844D-AD8BF670FE44");//uuid mac
		 map.put("client_ip", "223.223.196.2");  //ip
		 map.put("app_key", "4000");//appKeys
		 map.put("ad_key","2398");//adKey5604f21af70416d13f24c4baeef368a56f938923
		 map.put("OPENUDID", "5604f21af70416d13f04c6bfaef368a56a938923");//udid
		 map.put("IDFA", "862E62FE-58DE-4CD1-844D-AD8BF670FE44");//idfa
		 map.put("IDFV", "8ABD3EEA-0975-4F00-AB44-3A436488D954");//idfv
		 map.put("os","7.0.1");//os
		 map.put("mac", "");
		 map.put("sdkVersion","2.0.1");
         map.put("page_type", "0");//类型
         String callback = callBackUrl+"?uuid="+map.get("deviceid")+"&appId="+map.get("app_key")+"&adId="+map.get("ad_key")+"&channel="+map.get("source")+"&page_type="+map.get("page_type")+"&version=Android2.0.1&&idfa="+map.get("IDFA");
         map.put("callback", StringUtil.encode(callback)); //callback
		 return map;
	}
	
	
	/**
     *
     *波哥的iphone
		idfa:3C044B52-509D-4CC7-873F-4C4F28C66D56
		idfv:E1C9E1AD-5ABD-410A-9884-905028FA8D37
		mac:020000000000
		openudid:00fde8d7b8503181171de247e94f5c8bff16242c
	 * */
	public static Map<String, String> createMapBoge(String appid){
		 Map<String, String> map = new HashMap<String, String>();
		 map.put("appid", appid);//appid
		 map.put("source", "adwalker"); //渠道名称
		 map.put("deviceid", "3C044B52-509D-4CC7-873F-4C4F28C66D56");//uuid mac
		 map.put("client_ip", "127.0.0.1");  //ip
		 map.put("app_key", "4000");//appKeys
		 map.put("ad_key","2398");//adKey5604f21af70416d13f24c4baeef368a56f938923
		 map.put("OPENUDID", "00fde8d7b8503181171de247e94f5c8bff16242c");//udid
		 map.put("IDFA", "3C044B52-509D-4CC7-873F-4C4F28C66D56");//idfa
		 map.put("IDFV", "E1C9E1AD-5ABD-410A-9884-905028FA8D37");//idfv
		 map.put("os","7.0.1");//os
		 map.put("sdkVersion","2.0.1");
		 map.put("mac", "02:00:00:00:00:00");
         map.put("page_type", "0");//类型
         String callback = callBackUrl+"?uuid="+map.get("deviceid")+"&appId="+map.get("app_key")+"&adId="+map.get("ad_key")+"&channel="+map.get("source")+"&page_type="+map.get("page_type")+"&version=Android2.0.1&&idfa="+map.get("IDFA");
         map.put("callback", StringUtil.encode(callback)); //callback
		 return map;
	}
	
	
	/**
idfa:163E23E6-3E52-4BC4-AF0B-3C00EB7566D1
idfv:5E5738A6-F7C2-489F-ABA4-32FE9C0B2EAD
mac:020000000000
openudid:167150c2c862134a90af0cda8ad089edc83db6eb

	 * */
	public static Map<String, String> createMapIpone5s(String appid){
		 Map<String, String> map = new HashMap<String, String>();
		 map.put("appid", appid);//appid
		 map.put("source", "adwalker"); //渠道名称
		 map.put("deviceid", "163E23E6-3E52-4BC4-AF0B-3C00EB7566D1");//uuid mac
		 map.put("client_ip", "111.197.110.189");  //ip
		 map.put("app_key", "4000");//appKeys
		 map.put("ad_key","2398");//adKey5604f21af70416d13f24c4baeef368a56f938923
		 map.put("OPENUDID", "167150c2c862134a90af0cda8ad089edc83db6eb");//udid
		 map.put("IDFA", "163E23E6-3E52-4BC4-AF0B-3C00EB7566D1");//idfa
		 map.put("IDFV", "5E5738A6-F7C2-489F-ABA4-32FE9C0B2EAD");//idfv
		 map.put("os","7.0.1");//os
		 map.put("mac", "02:00:00:00:00:00");
		 map.put("sdkVersion","2.0.1");
         map.put("page_type", "0");//类型
         String callback = callBackUrl+"?uuid="+map.get("deviceid")+"&appId="+map.get("app_key")+"&adId="+map.get("ad_key")+"&channel="+map.get("source")+"&page_type="+map.get("page_type")+"&version=Android2.0.1&&idfa="+map.get("IDFA");
         map.put("callback", StringUtil.encode(callback)); //callback
		 return map;
	}
	/**
	 * 蔡强
	 * 
idfa:0A936CCE-4FCF-49FB-8A63-9D77BF48051D
idfv:8BE4D726-110C-4A6E-A483-2BBFD1A785B4
mac:020000000000
openudid:53e0fa47a4367f124ed29a9dea2256830eb570cb

	 * */
	public static Map<String, String> createMapCaiqiang(String appid){
		 Map<String, String> map = new HashMap<String, String>();
		 map.put("appid", appid);//appid
		 map.put("source", "adwalker"); //渠道名称
		 map.put("deviceid", "0A936CCE-4FCF-49FB-8A63-9D77BF48051D");//uuid mac
		 map.put("client_ip", "127.0.0.1");  //ip
		 map.put("app_key", "4000");//appKeys
		 map.put("ad_key","2398");//adKey5604f21af70416d13f24c4baeef368a56f938923
		 map.put("OPENUDID", "53e0fa47a4367f124ed29a9dea2256830eb570cb");//udid
		 map.put("IDFA", "0A936CCE-4FCF-49FB-8A63-9D77BF48051D");//idfa
		 map.put("IDFV", "8BE4D726-110C-4A6E-A483-2BBFD1A785B4");//idfv
		 map.put("os","7.0.1");//os
		 map.put("sdkVersion","2.0.1");
         map.put("page_type", "0");//类型
         String callback = callBackUrl+"?uuid="+map.get("deviceid")+"&appId="+map.get("app_key")+"&adId="+map.get("ad_key")+"&channel="+map.get("source")+"&page_type="+map.get("page_type")+"&version=Android2.0.1&&idfa="+map.get("IDFA");
         map.put("callback", StringUtil.encode(callback)); //callback
		 return map;
	}
	
	/**
idfa:8D0DF918-E510-4EC3-85B9-7970D07D1DCE
idfv:D0C058BA-D578-4904-9AFA-690BBBB576A2
mac:020000000000
openudid:987e625837cbc3e1bcaa2ea160b826ba3fb02194
ipad 设备标示
	 * */
	public static Map<String, String> createMapPad(String appid){
		 Map<String, String> map = new HashMap<String, String>();
		 map.put("appid", appid);//appid
		 map.put("source", "adwalker"); //渠道名称
		 map.put("deviceid", "8D0DF918-E510-4EC3-85B9-7970D07D1DCE");//uuid mac
		 map.put("client_ip", "127.0.0.1");  //ip
		 map.put("app_key", "4000");//appKeys
		 map.put("ad_key","2398");//adKey5604f21af70416d13f24c4baeef368a56f938923
		 map.put("OPENUDID", "987e625837cbc3e1bcaa2ea160b826ba3fb02194");//udid
		 map.put("IDFA", "8D0DF918-E510-4EC3-85B9-7970D07D1DCE");//idfa
		 map.put("IDFV", "D0C058BA-D578-4904-9AFA-690BBBB576A2");//idfv
		 map.put("os","7.0.1");//os
		 map.put("sdkVersion","2.0.1");
         map.put("page_type", "0");//类型
         String callback = callBackUrl+"?uuid="+map.get("deviceid")+"&appId="+map.get("app_key")+"&adId="+map.get("ad_key")+"&channel="+map.get("source")+"&page_type="+map.get("page_type")+"&version=Android2.0.1&&idfa="+map.get("IDFA");
         map.put("callback", StringUtil.encode(callback)); //callback
		 return map;
	}
	
   /**
	季冬
idfa:38CB92E5-59E5-467D-90A6-894F5DED17D4
idfv:D374101C-4CAA-44D6-AD35-486B4BF4ECA4
mac:020000000000
openudid:1241bb51630b789100c3f2845a3e91a9143ffe9c
		 * */
		
		public static Map<String, String> createMapJiDong(String appid){
			 Map<String, String> map = new HashMap<String, String>();
			 map.put("appid", appid);//appid
			 map.put("source", "adwalker"); //渠道名称
			 map.put("deviceid", "38CB92E5-59E5-467D-90A6-894F5DED17D4");//uuid mac
			 map.put("client_ip", "127.0.0.1");  //ip
			 map.put("app_key", "4000");//appKeys
			 map.put("ad_key","2398");//adKey5604f21af70416d13f24c4baeef368a56f938923
			 map.put("OPENUDID", "1241bb51630b789100c3f2845a3e91a9143ffe9c");//udid
			 map.put("IDFA", "38CB92E5-59E5-467D-90A6-894F5DED17D4");//idfa
			 map.put("IDFV", "D374101C-4CAA-44D6-AD35-486B4BF4ECA4");//idfv
			 map.put("os","7.0.1");//os
			 map.put("sdkVersion","2.0.1");
	        map.put("page_type", "0");//类型
	        String callback = callBackUrl+"?uuid="+map.get("deviceid")+"&appId="+map.get("app_key")+"&adId="+map.get("ad_key")+"&channel="+map.get("source")+"&page_type="+map.get("page_type")+"&version=Android2.0.1&&idfa="+map.get("IDFA");
	        map.put("callback", StringUtil.encode(callback)); //callback
			 return map;
		}
	
	/**
	 * 秋梨的iphone
idfa:D10C74C4-E85B-4EB6-8D7D-BA35F14EF43D
idfv:C0FDBA4D-DB1F-421B-BB6B-C61306A75FB3
mac:020000000000
openudid:1bbb0820cefb35229262427a70afbf8e54a98af9

idfa:D10C74C4-E85B-4EB6-8D7D-BA35F14EF43D
idfv:C0FDBA4D-DB1F-421B-BB6B-C61306A75FB3
mac:020000000000
openudid:1bbb0820cefb35229262427a70afbf8e54a98af9

	 * 
	 * 
	 * 
	 * */
	
	public static Map<String, String> createMapQiuli(String appid){
		 Map<String, String> map = new HashMap<String, String>();
		 map.put("appid", appid);//appid
		 map.put("source", "adwalker"); //渠道名称
		 map.put("deviceid", "D10C74C4-E85B-4EB6-8D7D-BA35F14EF43D");//uuid mac
		 map.put("client_ip", "127.0.0.1");  //ip
		 map.put("app_key", "4000");//appKeys
		 map.put("ad_key","2398");//adKey5604f21af70416d13f24c4baeef368a56f938923
		 map.put("OPENUDID", "1bbb0820cefb35229262427a70afbf8e54a98af9");//udid
		 map.put("IDFA", "D10C74C4-E85B-4EB6-8D7D-BA35F14EF43D");//idfa
		 map.put("IDFV", "C0FDBA4D-DB1F-421B-BB6B-C61306A75FB3");//idfv
		 map.put("os","7.0.1");//os
		 map.put("sdkVersion","2.0.1");
       map.put("page_type", "0");//类型
       String callback = callBackUrl+"?uuid="+map.get("deviceid")+"&appId="+map.get("app_key")+"&adId="+map.get("ad_key")+"&channel="+map.get("source")+"&page_type="+map.get("page_type")+"&version=Android2.0.1&&idfa="+map.get("IDFA");
       map.put("callback", StringUtil.encode(callback)); //callback
		 return map;
	}
	public static void t1(){
		
	}
	
	/**
idfa:F9EE71C2-A929-4527-B346-2FEA37D9CCFE
idfv:C93C60D1-BADA-44E2-9768-6122B99F5D51
mac:020000000000
openudid:e39108e0080b2d262b3aebc79668ac6f6d719f98


idfa:182471B0-8FF8-429C-863F-F373D8F62962
idfv:F9B83A61-DE1F-44BC-BB78-7121D9E24C34
mac:020000000000
openudid:ee8684d850aa76a537b677af455d14c28eb2a003
	*/
	public static Map<String, String> createMapErwa(String appid){
		 Map<String, String> map = new HashMap<String, String>();
		 map.put("appid", appid);//appid
		 map.put("source", "adwalker"); //渠道名称
		 map.put("deviceid", "F9EE71C2-A929-4527-B346-2FEA37D9CCFE");//uuid mac
		 map.put("client_ip", "223.223.196.2");  //ip
		 map.put("app_key", "4000");//appKeys
		 map.put("ad_key","2398");//adKey5604f21af70416d13f24c4baeef368a56f938923
		 map.put("OPENUDID", "e39108e0080b2d262b3aebc79668ac6f6d719f98");//udid
		 map.put("IDFA", "F9EE71C2-A929-4527-B346-2FEA37D9CCFE");//idfa
		 map.put("IDFV", "C93C60D1-BADA-44E2-9768-6122B99F5D51");//idfv
		 map.put("os","7.0.1");//os
		 map.put("sdkVersion","2.0.1");
      map.put("page_type", "0");//类型
      String callback = callBackUrl+"?uuid="+map.get("deviceid")+"&appId="+map.get("app_key")+"&adId="+map.get("ad_key")+"&channel="+map.get("source")+"&page_type="+map.get("page_type")+"&version=Android2.0.1&&idfa="+map.get("IDFA");
      map.put("callback", StringUtil.encode(callback)); //callback
		 return map;
	}
	
	/**
	 * 
idfa:1A2B3A33-9567-4A9A-BCF9-206E15739D93
idfv:42C470EE-22C8-4954-BC44-BE90B08D5512
mac:020000000000
openudid:c70cae8c6c510a4e3918378641d6207233b0127d
	 * */
	
	
	public static Map<String, String> createMapXiechao(String appid){
		 Map<String, String> map = new HashMap<String, String>();
		 map.put("appid", appid);//appid
		 map.put("source", "adwalker"); //渠道名称
		 map.put("deviceid", "1A2B3A33-9567-4A9A-BCF9-206E15739D93");//uuid mac
		 map.put("client_ip", "223.223.196.2");  //ip
		 map.put("app_key", "4000");//appKeys
		 map.put("ad_key","2398");//adKey5604f21af70416d13f24c4baeef368a56f938923
		 map.put("OPENUDID", "c70cae8c6c510a4e3918378641d6207233b0127d");//udid
		 map.put("IDFA", "1A2B3A33-9567-4A9A-BCF9-206E15739D93");//idfa
		 map.put("IDFV", "42C470EE-22C8-4954-BC44-BE90B08D5512");//idfv
		 map.put("os","7.0.1");//os
		 map.put("sdkVersion","2.0.1");
         map.put("page_type", "0");//类型
         String callback = callBackUrl+"?uuid="+map.get("deviceid")+"&appId="+map.get("app_key")+"&adId="+map.get("ad_key")+"&channel="+map.get("source")+"&page_type="+map.get("page_type")+"&version=Android2.0.1&&idfa="+map.get("IDFA");
         map.put("callback", StringUtil.encode(callback)); //callback
		 return map;
	}
	
	
	
	
}
