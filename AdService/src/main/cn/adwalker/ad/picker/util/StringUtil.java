package cn.adwalker.ad.picker.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class StringUtil {
	
	private static final Logger logger = LoggerFactory
			.getLogger(StringUtil.class);
	public final static String NEWLINE = "\r";
	public final static String BLANK_SPACE = " ";
	private static final String BLANK = "";
	
	public static boolean isEmpty(Object ...strs){
		for(Object str:strs){
			if(isEmpty(str)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 
	* <p>Title: isNotEmpty</p>
	* <p>Description:参数不全为空</p>
	* @param strs
	* @return
	* @author cuidd
	* @date 2014年12月4日
	* @return boolean
	* @version 1.0
	 */
	public static boolean isAllEmpty(Object ...strs){
		boolean b=true;
		for(Object str:strs){
			if(!isEmpty(str)){
				b=false;
				break;
			}
		}
		return b;
	}
	
	//去掉头尾的,符号
	public static String rt(String str){
		return StringUtil.dealNull(str).replaceAll(",$", "").replaceAll("^,", "");//
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
	
	/**
	 * 
	* <p>Title: dealNull</p>
	* <p>Description:把空对象转换成null</p>
	* @param obj
	* @return
	* @author luoyouhua
	* @date 2014年10月10日
	* @return String
	* @version 1.0
	 */
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
	
	/**
	 * 
	* <p>Title: dealNull</p>
	* <p>Description:判断是否为空，加上默认值</p>
	* @param str
	* @param defaultVal
	* @return
	* @author cuidd
	* @date 2014年10月14日
	* @return String
	* @version 1.0
	 */
	public static String dealNull(String str, String defaultVal) {
		return str != null ? str.trim() : defaultVal;
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
	
	private static final String default_char_encode = "utf-8";
	
	public static String encode(String s) {
		String str = null;
		if (!StringUtil.isEmpty(s)) {
			try {
				str = URLEncoder.encode(s, default_char_encode);
			} catch (UnsupportedEncodingException e) {
				logger.error(e.toString());
				e.printStackTrace();
			}
		}
		return str;

	}
	
	
	public static String decode(String s) {
		String str = null;
		if (!isEmpty(s)) {
			try {
				str=URLDecoder.decode(s, "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return str;

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
	public static Integer getFileSpace(File file){
		return file==null?0:NumberUtil.getInt(file.length()/1024, 0);
	}
	//获取后缀名
	public static String getExtensionName(String filename){
		if ((filename != null) && (filename.length() > 0)) {
			int i = filename.lastIndexOf('.');
			if ((i > -1) && (i < (filename.length() - 1))) {
				return filename.substring(i + 1);
			}
		}
		return null;
	}
	//获取文件名
	public static String getFileName(String filename){
		if ((filename != null) && (filename.length() > 0)) {
			int i = filename.lastIndexOf('/');
			if ((i > -1) && (i < (filename.length() - 1))) {
				return filename.substring(i + 1);
			}
		}
		return filename;
	}
	//获取文件名
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
	/**<b>功能说明：判断图片尺寸</b>
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
	public static Double parseVersion(String ver){
		if(null==ver){return 0d;}
		ver=ver.replaceAll("\\D", "");
	    Double version = getDouble(ver, 0d);
	    while(version>10){
	    	version=version/10d;
	    }
	    return version;
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
	
	
	
	public static void main(String[] args){
		
		
		//System.out.println("IOS2.1.0".compareTo(V.IOS220.toString()));
		//String sv = "V2.1.0_iOS7";
		
		System.out.println("0".matches("(0)"));
		
		
		/**
		
		String sdkVerision = "".compareTo(V.IOS220.toString())<0?V.IOS210.toString():V.IOS220.toString();
		String iosVersion= isIos7?"iOS7":"iOS6";
		String sdkMatch=sdkVerision+"_"+iosVersion;
		String sv = ad.getPlacement().getSdk_version_str();
		String svReg=StringUtil.isEmpty(sv)?".*":"("+rt(sv).replaceAll(",", "|")+")";
		
		if(!sdkMatch.matches(svReg)){
		*/
		 //String ps="192.168.10.29";
		 //Pattern pattern = Pattern.compile("\\.\\d+$"); 
         //Matcher matcher = pattern.matcher(ps); 
         //String a=matcher.replaceAll(""); 
		 //System.out.println(a.replaceAll("\\.", "_"));
		 //"ABc".matches("(?i)abc")
		 //System.out.println("IOS2.2.0_iOS7".matches("(IOS2.2.0_iOS7)"));
		 
	}
}
