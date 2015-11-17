package cn.adwalker;

import org.apache.commons.codec.binary.Base64;


public class Test {
	public static void main(String[] args) {
		String string=new String(Base64.decodeBase64("NjU4Nyo2NjA0".getBytes()));
		System.out.println(string.split("\\*")[0]);

	}

}
