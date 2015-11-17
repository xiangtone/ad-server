/*
 * GetShowScore.java
 *
 * Copyright 2011 1000CHI Software Technology Co.,Ltd
 * All rights reserved.
 * 
 * Created on 2011-12-30
 */
package cn.adwalker.ad.util;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * 功能概述：<br>
 * 
 * 广告根据出价获取showscroe
 *
 * @author jiaozhichao
 */
public class GetShowScore {
	
	/** 文件路径配置key */
	private static final String SHOWSCORE_URL_KEY = "showScore.url";
	
	private static String SHOWSCORE_URL="";
	
	static {
		SHOWSCORE_URL = ConfigUtil.getString(SHOWSCORE_URL_KEY);
	}
	
	/**
	 * 根据价格获取积分
	 * 
	 * @param money
	 * @return
	 * @throws Exception
	 */
	public static String showScore(String money) throws Exception{
		String result = "";
		try {
			StringBuilder sb = new StringBuilder();
			sb.append(SHOWSCORE_URL);
			sb.append(money);
			URL url = new URL(sb.toString());
			HttpURLConnection connection = null;
			connection = (HttpURLConnection) url.openConnection();
			connection.setConnectTimeout(5000);
			connection.setDoInput(true);
			connection.setReadTimeout(5000);
			connection.setDoOutput(true);
			InputStream in = url.openConnection().getInputStream();
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			for (int b = in.read(); b >= 0; b = in.read()) {
			outputStream.write((byte) b);
			}
			result = new String(outputStream.toByteArray(), "UTF-8").trim();
			
			outputStream.close();
			in.close();  
		} catch (MalformedURLException e) {
			e.printStackTrace();
			System.out.println("网络超时");
		}
		return result;
	}
	
}
