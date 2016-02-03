/*
 * UCUtils.java
 *
 * Copyright 2010 1000CHI Software Technology Co.,Ltd
 * All rights reserved.
 * 
 * Created on 2010-8-10
 */
package cn.adwalker.ad.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;



/**
 * 功能概述：<br>
 * sis包转化工具类
 * 
 * @author kangxuming
 */
public class SisxParser {

	/** 转换工具exe所在目录 */
	public static String PATH;
	
	static{
		java.net.URL url = SisxParser.class.getResource("");
		int index = url.getPath().indexOf("/WEB-INF/"); // 取属性文件的绝对路径
		String pathPrefix = url.getPath().substring(0, index + 9).replace("%20", " "); // 替换掉空格
		PATH = pathPrefix + "python/";
	}

	/** 命令前缀 */
	public static String CMD_PREFIX = "python ";

	public static final String CMD_SISINFO = "sisinfo.py";

	/**
	 * 生成ucs文件，生成后的ucs文件为，源文件名.ucs
	 * 
	 * @param mp4
	 * @param urlPrefix
	 */
	public static String sisInfo(String filePath) {
		return sisInfo(filePath, PATH, CMD_SISINFO);
	}

	/**
	 * 生成ucs文件，生成后的ucs文件为，源文件名.ucs
	 * 
	 * @param mp4
	 * @param urlPrefix
	 */
	public static String sisInfo(String filePath, String commandPath, String commandName) {
		String cmd = CMD_PREFIX + commandPath + File.separator + commandName + " -s " + "-f " + filePath;
		return execCommand(cmd);
	}

	/**
	 * 执行外部命令 WatchDog
	 * 
	 * @param command
	 */
	public static String execCommand(final String command) {
		BufferedReader br = null;
		BufferedReader brerr = null;
		Process process = null;
		try {
			process = Runtime.getRuntime().exec(command);
			br = new BufferedReader(new InputStreamReader(process.getInputStream()));
			brerr = new BufferedReader(new InputStreamReader(process.getErrorStream()));
			String inline;
			StringBuffer sbf = new StringBuffer();
			while (null != (inline = br.readLine())) {
				sbf.append(inline);
			}
			while (null != (inline = brerr.readLine())) {
				sbf.append(inline);
			}
			return sbf.toString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (brerr != null) {
				try {
					brerr.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			process.destroy();
		}
		return null;
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		String string = sisInfo("/home/fangguanhong/sisx/QAD_S60V5.S3_V1.0.1_Demo.sisx", PATH, CMD_SISINFO);
		System.out.println(string);
		// Map map = JSONObject.fromObject(string);
		// System.out.println(URLDecoder.decode(map.get("name").toString(),
		// "UTF-8"));

	}
	
	/**
	 * @param sisxFilePath
	 * @return
	 */
	public static ApkParserResult getSisxInfo(String sisxFilePath) {
		return getSisxInfo(new File(sisxFilePath));
	}

	
	/**
	 * @param file
	 * @return
	 */
	public static ApkParserResult getSisxInfo(File file) {
		System.out.println("Sisx解包文件："+PATH);
//		return SisxParser.getApkInfo(file, PATH);
		ApkParserResult sisInfo = new ApkParserResult();
		sisInfo.setFileName(file.getName());
		sisInfo.setSize(file.length());
		String parserResult = sisInfo(file.toString(), PATH, CMD_SISINFO);//解包得到的json串
		
		ObjectMapper mapper = JacksonMapper.getInstance();
		try {
			SisxVO sisxVO = mapper.readValue(parserResult, new SisxVO().getClass());
			sisInfo.setPackageName(sisxVO.getUid());
			sisInfo.setVersionName(sisxVO.getVersion());
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sisInfo;
	}
	

}
