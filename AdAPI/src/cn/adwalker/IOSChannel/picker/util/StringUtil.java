package cn.adwalker.IOSChannel.picker.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import org.apache.commons.lang.StringUtils;


public class StringUtil {
	public final static String NEWLINE = "\r";
	public final static String BLANK_SPACE = " ";
	private static final String BLANK = "";
	private static final String SINA_URL="http://int.dpool.sina.com.cn/iplookup/iplookup.php?ip=";
	
	
	public static String adUrl(String url,String ad_code){
		if(StringUtil.equals("MEHTA_CORP", ad_code)){
			return url.replace("{clickid}", StringUtil.uuid());
		}else{
			 return url;
		}
	}
	
	
	 public static String createUrl(String path,Map<String,String> map) {
	    	boolean b = true;
	    	StringBuffer sbuf = new StringBuffer(path);
	    	int i=path.indexOf("?");
	    	sbuf.append(i==-1?"?":"&");
	        for(Entry<String, String> en:map.entrySet()){
	        	String param = en.getKey();
	        	String value = en.getValue();
	        	sbuf.append(b?(param+"="+value):("&"+param+"="+value));
	        	if(b){b=false;}
	        }
			return sbuf.toString();
		}
	 
	 

		//把ip形如 192.168.1.20转换为192.168.1
		public static String ipdo(String ip){
			Pattern pattern = Pattern.compile("\\.\\d+$"); 
	        Matcher matcher = pattern.matcher(ip); 
	        //String a=matcher.replaceAll(""); 
	        return matcher.replaceAll(""); 
		}
		
		
		public String loggerStr(String url,Map<String,String> map){
			boolean b = true;
	    	StringBuffer sbuf = new StringBuffer(url);
	        for(Entry<String, String> en:map.entrySet()){
	        	String param = en.getKey();
	        	String value = en.getValue();
	        	sbuf.append(b?("?"+param+"="+value):("&"+param+"="+value));
	        	if(b){b=false;}
	        }
	        return sbuf.toString();
		}

	
	
	
	
	
	
	public static boolean isEmpty(String ...str){
		for(String s:str){
			if(isEmpty(s)){ return true;}
		}
		return false;
	}
	public static boolean isEmpty(String s){
		return s == null || BLANK.compareTo(s) == 0;
    }
	public static boolean isEmpty(Object str) {
		return str == null || BLANK.equals(str);
	}
	public static int getInt(String str, int defaultVal){
		if(str!=null){
			return Integer.valueOf(str);
		}
		return defaultVal;
	}
	public static String dealNull(String str) {
		return str != null ? str.trim() : BLANK;
	}
	public static String dealNull(Object obj) {
		String str = BLANK;
		if(obj!=null){
			if(obj instanceof String){
				str = (String)obj;
			}else{
				str = obj.toString();
			}
		}
		return str;
	}
	public static String convertHtml(String str) {
		str = (str == null) ? BLANK : str;
		str = str.replaceAll(NEWLINE, "<br/>");
		str = str.replaceAll(BLANK_SPACE, "&nbsp;");
		return str;
	}
	
	public static String dealNull(Object str,String defaultValue){
		return str != null ? str.toString().trim() : defaultValue;
	}
	
	public static String dealNull(String str, String defaultVal) {
		return str != null ? str.trim() : defaultVal;
	}
	
	
	public static String dealEmpty(String str,String defaultVal){
		return !isEmpty(str)?str.trim():defaultVal;
	}
	
	public static String encode(String str, final String encoding) {
		try {
			if (!StringUtil.isEmpty(str)) {
				str = URLEncoder.encode(str, encoding);
			}
		} catch (Exception e) {
		}
		return str;
	}
	public static String decode(String str,String encoding){
		try {
			if(!isEmpty(str)){
			    return URLDecoder.decode(str,"utf-8");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static boolean equalsIgnoreCase(String value1,String value2){
		boolean is = false;
		if (value1 != null && value2 != null) { // is not null;
			return value1.equalsIgnoreCase(value2);
		}
		return is;
	}
	
	public static boolean equals(Object value1, Object value2) {
		boolean is = false;
		if (value1 == value2) { // is null or self
			return is = true;
		}
		if (value1 != null && value2 != null) { // is not null;
			return value1.equals(value2);
		}
		return is;
	}
	public static boolean regex(String regex, String str) {
		boolean trueOrFalse;
		Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(str);
		trueOrFalse = m.matches();
		return trueOrFalse;
	}
	
	//获取后缀�?
	public static String getExtensionName(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int i = filename.lastIndexOf('.');
			if ((i > -1) && (i < (filename.length() - 1))) {
				return filename.substring(i + 1);
			}
		}
		return null;
	}
	//获取文件�?
	public static String getFileName(String filename){
		if ((filename != null) && (filename.length() > 0)) {
			int i = filename.lastIndexOf('/');
			if ((i > -1) && (i < (filename.length() - 1))) {
				return filename.substring(i + 1);
			}
		}
		return filename;
	}
	//获取文件�?
	public static String getFileNameNoSuffix(String filename){
		if ((filename != null) && (filename.length() > 0)) {
			int i = filename.lastIndexOf('/');
			if ((i > -1) && (i < (filename.length() - 1))) {
				return filename.substring(i + 1);
			}
		}
		return filename.replaceAll("\\..*", "");
	}

	
	public static boolean createFolder(String file) {
		File folder = new File(file);
		if(!folder.exists()){
			return folder.mkdirs();
		};return true;
	}

	
	public static String trim(String str){
		return StringUtil.dealNull(str).trim();
	}
	public static String subString(String str,int i){
		if(StringUtil.isEmpty(str)) return null;
		return str.length()>i?str.substring(0,i)+"...":str;
	}
	public static int random(int n){
		if(n==0) return 0;
		Random random = new Random();
		return Math.abs(random.nextInt())%n;
	}
	/**
	 * 随机返回0.0-1.0之间的浮点值
	 * @return
	 */
	public static float floatRandom(){
		Random random = new Random();
		return random.nextFloat();
	}
	public static void mkDir(String str){
		File file = new File(str);
		if(!file.exists()){
			file.mkdir();
		}
	}

    public static String filterIp(String ip){
    	if(StringUtil.isEmpty(ip)) return "";
    	String[][] object={new String[]{"\\.\\d*\\.",".*."},new String[]{"\\.\\d*\\.",".*."}}; 
    	return replace(ip,object);
    }
	public static String replace(final String sourceString,Object[] object) { 
        String temp=sourceString;    
        for(int i=0;i<object.length;i++){ 
           String[] result=(String[])object[i]; 
           Pattern    pattern = Pattern.compile(result[0]); 
           Matcher matcher = pattern.matcher(temp); 
           temp=matcher.replaceAll(result[1]); 
        } 
        return temp; 
    } 
	/**<b>功能说明：判断图片尺�?/b>
	 * 日期：Aug 5, 2010
	 * @author luo
	 * @param file
	 * @param width
	 * @param height
	 * @return
	 */
	public static boolean checkImgSize(File file, int width, int height) {
		try {
			BufferedImage img = ImageIO.read(file);
			if ((img.getWidth() >= width - 5 && img.getWidth() <= width + 5) && (img.getHeight() >= height - 5 && img.getHeight() <= height + 5)) {
				return true;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	public static String fillZero(String code,Integer len){
    	String s = StringUtil.dealNull(code);//code.toString();
        while(s.length()<len){
        	s="0"+s;
        }
        return s;
    }
	public static InputStream getFullPathInputStream(String path){
		try {
			URL url = new URL(path);
			HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
			return urlc.getInputStream();
		} catch (Exception e) {
			//e.printStackTrace();
		}
		return null;
	}
	public static double getDouble(Object value, double defVal) {
		if (value != null) {
			try{
				if (value instanceof String) {
					return Double.parseDouble((String) value);
				} else if (value instanceof Number) {
					return ((Number) value).doubleValue();
				}
			}catch(Exception e){
				return defVal;
			}
		}
		return defVal;
	}
	
	public static String getAreaCodeByIp(String ip){
		if(!StringUtil.isEmpty(ip)){
			String responseStr = HttpClientUtils.readFromURL(SINA_URL+ip, "gbk");
			if(!StringUtil.isEmpty(responseStr)){
				String[] bb = responseStr.split("\\s+");			
				if (bb.length >= 6) {				
					if (!StringUtils.isEmpty(bb[5]) && !bb[5].equals("null")) {				
						return bb[5];
					}
				}
			}
		}
		return null;
	}
	
	public static boolean isMac(String mac){
		return StringUtil.dealNull(mac).replaceAll(":", "").length()==12;
	}
	/**
	 * <p>例如020000000000  转化成 02:00:00:00:00:00</p>
	 * @param mac
	 * @return
	 */
    public static String formatMac(String mac){
    	String macNew="";
    	if(StringUtils.isBlank(mac)){
    		return macNew;
    	}
    	String[] strs = new String[mac.length() - 1];
		for (int k = 0; k < mac.length() - 1; k += 2) {
		strs[k] = mac.substring(k, k + 2);
		macNew += strs[k] + ":";
       }
		macNew = macNew.substring(0, macNew.length()-1);
		return macNew;
    }
    /**
     * 去掉mac冒号
     * @param mac
     * @return
     */
    public static String qdmacmaohao(String mac){
    	if(!isEmpty(mac)){
    		return mac.replaceAll(":","");
    	}
    	return dealNull(mac);
    }
	public static Double parseVersion(String ver){
		if(null==ver){return 0d;}
		ver=ver.replaceAll("\\D", "");
	    Double version = getDouble(ver, 0d);
	    while(version>10){
	    	version=version/10d;
	    }
	    return version;
	}
	public static String uuid(){
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	public static void main(String[] args){
		
		try {
			String str = URLEncoder.encode("李文涛","utf8");
		    System.out.println(str);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//String ip = "192.168.1.1";
		//System.out.println(ip.replaceAll("\\.", ""));
		//System.out.println(StringUtil.qdmacmaohao("88:1F:A1:E8:13:28"));
//		System.out.println("龙岩".matches("(龙岩|福州|泉州|漳州|三明)"));
	    //System.out.println(getAreaCodeByIp("175.42.209.213"));
		//System.out.println(StringUtil.isMac("78:6C:1C:D1:4C:19"));
		//System.out.println(StringUtil.encode("DC:EF:A9:8C", "utf-8"));
		
//		System.out.println("030b5f5c-3214-4e6e-9c01-c2c8ccace675".toUpperCase());
		   
	}
}
