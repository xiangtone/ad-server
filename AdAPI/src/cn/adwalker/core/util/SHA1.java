package cn.adwalker.core.util;

import java.security.MessageDigest;

/**
 * <p>SHA-1 加密</p>
 * @author jief
 *
 */
public class SHA1 {

	public static void main(String args[]) throws Exception{
		String myinfo="我的测试信息";
		MessageDigest md = MessageDigest.getInstance("SHA-1");
		md.update(myinfo.getBytes());
	}
}
