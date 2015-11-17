package test;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;

import cn.adwalker.IOSChannel.picker.util.HttpClientUtils;
import cn.adwalker.ad.util.MD5;

public class TestChukong {
	public static void main(String[] args) throws IOException {
			List<String> list = FileUtils.readLines(new File("D://haha/haha1.log"));
			for (String s:list) {
				s=s.substring(66, s.length());
				System.out.println();
				 URL urlObj = new URL(s.split("\\s+")[1]);
				 //System.out.println(urlObj.getQuery());
				 Map<String, String> pMap=getParam(urlObj.getQuery());
				 Set<String> set=pMap.keySet();
				 StringBuilder sBuilder=new StringBuilder("http://service.cocounion.com/core/adclick/wallktask?");
				 for (String key:set) {
					 if (key.equals("osv")) {
						 sBuilder.append("osv=7.0.1&");
						 
					}else {
						sBuilder.append(key+"="+pMap.get(key)+"&");
					}
					
				}
				;
				String url=sBuilder.substring(0, sBuilder.length()-1);
				 Map<String, String> headers=new HashMap<String, String>();
				 MD5 md5 = new MD5();
				 String sign = "pid=838616047-CCED8E-685B-C8C4-98691B468&ctime="+pMap.get("ctime")+"&key=B1F6C7FE36708436B0A12F2A12281DA1";
				 headers.put("S2S-SIGNATURE", md5.getMD5ofStr(sign));
				 String result=HttpClientUtils.readFromURL(url, "utf-8",headers);
				System.out.println(result);
				 
			}
//			String url="http://106.120.153.228/AdAPI/common/ainfo_chukong.do?adid=417_857_794_310&idfa=6B4FBC14-3810-4592-B1B9-16216376014E";
//			
	}
	
	
	
	public static Map<String, String> getParam(String query){
         String[] params = query.split("&");
         Map<String, String> map = new HashMap<String, String>();
         for (String each : params) {
             String name = each.split("=")[0];
             String value;
             try {
             	if (each.split("=").length>1) {
             		  value = URLDecoder.decode(each.split("=")[1], "UTF-8");
					}else {
						value="";
					}
             } catch (UnsupportedEncodingException e) {
                 value = "";
             }
             map.put(name, value);
         }
		return map;
         
	}

}
