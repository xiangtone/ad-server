package cn.adwalker.ad.util;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

public class RequestUtils {
	
	
	@SuppressWarnings("unchecked")
	public static String getQueryString(HttpServletRequest request){
		StringBuffer sbuf = new StringBuffer();
        Enumeration<String> en = request.getParameterNames();
		boolean b=true;
		while(en.hasMoreElements()){
			String t = b?"?":"&";
			String name=en.nextElement();
			String value=request.getParameter(name);
			sbuf.append(t+name+"="+value);
			if(b) b=false;
		}
		return sbuf.toString();
		
	}

}
