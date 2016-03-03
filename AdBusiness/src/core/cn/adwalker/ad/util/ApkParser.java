package cn.adwalker.ad.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import jxl.common.Logger;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * 功能概述：<br>
 *	
 *		解析apk
 *
 * @author jiaozhichao
 */
public class ApkParser {
  
  private final static Logger LOG = Logger.getLogger(ApkParser.class);
  
	/** 格式化之前地址 */
	private final static String strBeforeFileName = "AndroidManifest.xml";
	/** 格式化之后地址 */
	private final static String strAfterFileName = "cmdResult.xml";
	
	/** APK包名 */
	public static String strpackage = "";
	/** APK版本名称 */
	public static String strVersionName = "";
	/** APK版本号 */
	public static String versionCode = "";
	/** 系统版本号 */
	public static String sdkVer = "";
	
	/**
	 * 获取AXMLPrinter2.jar地址
	 * 
	 * @return
	 */
	private static String getAXMLPrinterPath(){
		URL url = ApkParser.class.getResource("");
		int index = url.getPath().indexOf("/WEB-INF/"); // 取属性文件的绝对路径
		String pathPrefix = url.getPath().substring(0, index + 9).replace("%20", " "); // 替换掉空格
		StringBuilder sb = new StringBuilder(pathPrefix);
//		sb.append("lib/AXMLPrinter2.jar");
		sb.append("lib/axmlprinter2-1.0.jar");
		if (sb.indexOf(":")!=-1){
		  sb.deleteCharAt(0);
		}
		pathPrefix = sb.toString();
		return pathPrefix;
	}

	/**
	 * @param apkFilePath
	 * @param aXMLPrinterPath
	 * @return
	 */
	public static ApkParserResult getApkInfo(String apkFilePath,
			String aXMLPrinterPath) {
		File file = new File(apkFilePath);
		return ApkParser.getApkInfo(file, aXMLPrinterPath);
	}
	
	/**
	 * @param apkFilePath
	 * @param aXMLPrinterPath
	 * @return
	 */
	public static ApkParserResult getApkInfo(File file) {
		return ApkParser.getApkInfo(file, getAXMLPrinterPath());
	}
	
	/**
	 * @param apkFilePath
	 * @param aXMLPrinterPath
	 * @return
	 */
	public static ApkParserResult getApkInfo(String apkFilePath) {
		return getApkInfo(new File(apkFilePath));
	}

	/**
	 * @param apkFile
	 * @param aXMLPrinterPath
	 * @return
	 */
	public static ApkParserResult getApkInfo(File apkFile,
			String aXMLPrinterPath) {
		ApkParserResult apkInfo = new ApkParserResult();
		try {
			ZipFile zFile = new ZipFile(apkFile);
			ZipEntry entry = zFile.getEntry("AndroidManifest.xml");
			entry.getComment();
			entry.getCompressedSize();
			entry.getCrc();
			entry.isDirectory();
			entry.getSize();
			entry.getMethod();
			InputStream in = zFile.getInputStream(entry);
			String apkFilePath = apkFile.getAbsolutePath();
			String apkFileName = apkFile.getName();
			String strBeforeFilePath = apkFilePath.replace(apkFileName,
					strBeforeFileName);
			String strAfterFilePath = apkFilePath.replace(apkFileName,
					strAfterFileName);
			File file = new File(strBeforeFilePath);
			if (!file.exists()) {
				file.createNewFile();
			}
			FileOutputStream out = new FileOutputStream(file);
			byte[] buffer = new byte[1024];
			int length;
			while ((length = in.read(buffer)) != -1) {
				out.write(buffer, 0, length);
			}
			out.close();
			in.close();
			zFile.close();
			String command = "java -jar " + aXMLPrinterPath + " "
					+ strBeforeFilePath + " > " + strAfterFilePath;
			ExecCommandUtils.execCommand(command, strAfterFilePath);
			apkInfo = resolve(strAfterFilePath);
			apkInfo.setFileName(apkFile.getName());
			apkInfo.setSize(apkFile.length());
		} catch (IOException e) {
			e.printStackTrace();
			apkInfo = null;
		}
		return apkInfo;
	}

	/**
	 * 解析XML文件，获取APK包名、版本
	 */
	public static ApkParserResult resolve(String strAfterFilePath) {
		ApkParserResult apkInfo = new ApkParserResult();
		try {
			SAXParserFactory parserFactory = SAXParserFactory.newInstance();
			parserFactory.setValidating(false);
			parserFactory.setNamespaceAware(false);
			SaxFormat MySaxParserInstance = new SaxFormat();
			String strContent = "";
			String strLine = "";
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream(strAfterFilePath)));
			while ((strLine = br.readLine()) != null) {
				strContent += strLine + "\n";
			}
			br.close();
			byte[] by = strContent.getBytes("utf-8");
			ByteArrayInputStream bais = new ByteArrayInputStream(by);
			SAXParser parser = parserFactory.newSAXParser();
			parser.parse(bais, MySaxParserInstance);
			strLine = null;
			strContent = null;
			by = null;
			bais.close();
			parser = null;

			apkInfo.setPackageName(strpackage);
			apkInfo.setVersionName(strVersionName);
			apkInfo.setVersionCode(versionCode);
			apkInfo.setSdkVer(sdkVer);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return apkInfo;

	}

	/**
	 * 获取文件夹下的文件名
	 */
	public static ArrayList<String> getFiles(String path) {
		ArrayList<String> listAPKFiles = null;
		File files = null;
		try {
			files = new File(path);
			String[] strFiles = files.list(new FilterAPKGetWithCMD());
			if (strFiles.length > 0) {
				listAPKFiles = new ArrayList<String>();
				for (int i = 0; i < strFiles.length; i++) {
					String strFile = strFiles[i];
					if (strFile.length() > 0) {
						listAPKFiles.add(path + strFile);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		files = null;
		return listAPKFiles;
	}
}

/**
 * 过滤文件后缀
 * 
 * @author Administrator
 * 
 */
class FilterAPKGetWithCMD implements FilenameFilter {

	public boolean accept(File dir, String name) {
		if (name.substring(name.lastIndexOf(".")).equals(".apk")) {
			return true;
		} else {
			return false;
		}
	}
}

class SaxFormat extends DefaultHandler {

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		super.characters(ch, start, length);
	}

	@Override
	public void endDocument() throws SAXException {
		super.endDocument();
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		super.endElement(uri, localName, qName);
	}

	@Override
	public void startDocument() throws SAXException {
		super.startDocument();
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if (qName.equals("manifest")) {
			for (int i = 0; i < attributes.getLength(); i++) {
				String strQname = attributes.getQName(i);
				if (strQname.equals("package")) {
					ApkParser.strpackage = attributes.getValue(strQname);
				}
				if (strQname.equals("android:versionName")) {
					ApkParser.strVersionName = attributes.getValue(strQname);
				}
				if (strQname.equals("android:versionCode")) {
					ApkParser.versionCode = attributes.getValue(strQname);
				}
			}
		}
		if (qName.equals("uses-sdk")) {
			for (int i = 0; i < attributes.getLength(); i++) {
				String strQname = attributes.getQName(i);
				if (strQname.equals("android:minSdkVersion")) {
					ApkParser.sdkVer = attributes.getValue(strQname);
				}
			}
		}
		super.startElement(uri, localName, qName, attributes);
	}
}
