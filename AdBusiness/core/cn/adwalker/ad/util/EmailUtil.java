package cn.adwalker.ad.util;

public class EmailUtil {

	/**
	* @param email
	* @return
	* @return String
	* @throws
	*/
	public static String getUrl(String email) {
        int aa= email.indexOf("@");
        String url = "http://mail."+email.substring(aa+1, email.length());
		return url;
	}

}
