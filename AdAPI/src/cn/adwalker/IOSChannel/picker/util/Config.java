package cn.adwalker.IOSChannel.picker.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class Config{
    public static Logger logger = Logger.getLogger(Config.class);
	public static Map<String, String> map = new HashMap<String, String>();
	public static Map<String, String> cmdMap = new HashMap<String, String>();
	public static Map<String, CmdBean> cmdBeanMap = new HashMap<String, CmdBean>();
	public static boolean tag=false;
	public static List<String> source = null;
	public static Config config = new Config();
	static {
		source=new ArrayList<String>();
		source.add("/conf");
		//source.add("/config");
	}
	public Config(){
       
	}
	@SuppressWarnings("rawtypes")
	public  void putInMap() throws FileNotFoundException, IOException{
		logger.logInfo("begin load properties===========================");
		for(String s:source){
			File fileProperties = new File(getClass().getResource(s).getPath());
			logger.logInfo("load dir:"+fileProperties.getName());
			if(fileProperties!=null && fileProperties.isDirectory()){
					File[] fileArray = fileProperties.listFiles();
					if(fileArray!=null){
						for(File f:fileArray){
							if(f.getPath().contains(".properties")){
								logger.logInfo("load properties file:"+f.getName());
								Properties properties = new Properties();
								properties.load(new FileInputStream(f));
								for(Entry e:properties.entrySet()){
									/**
									if(!regex("jdbc.driver|",StringUtil.dealNull(e.getKey()))){
										
									}
									*/
									map.put(StringUtil.dealNull(e.getKey(),"null"), StringUtil.dealNull(e.getValue()));
								}
							}
					}
				}
			}
		}
        tag=true;
		logger.logInfo("load properties  done!===========================");
	}
	public void putCmdInMap() {
		try{
			logger.logInfo("parse support cmd ......begin");
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document d =builder.parse(getClass().getClassLoader().getResource("conf/support_cmd.xml").openStream());
			//builder.p
		    Element root = d.getDocumentElement();
		    for(int i=0;i<root.getChildNodes().getLength();i++){
		    	Node n = root.getChildNodes().item(i);
		    	if(n instanceof Element){
			    	Element cmd = (Element)root.getChildNodes().item(i);
			    	cmdMap.put(cmd.getAttribute("action"), cmd.getTextContent());
			    	logger.logInfo(cmd.getAttribute("action")+"   "+cmd.getTextContent());
		    	}
		    }
		    logger.logInfo("parse support cmd ...... done");
		}catch(Exception e){
			e.printStackTrace();
			logger.logError(e.getMessage());
		}

	}
	
	public void putCmdBeanInMap() {
		try{
			logger.logInfo("parse support cmd ......begin");
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document d =builder.parse(getClass().getClassLoader().getResource("conf/support_cmd.xml").openStream());
			//builder.p
		    Element root = d.getDocumentElement();
		    for(int i=0;i<root.getChildNodes().getLength();i++){
		    	Node n = root.getChildNodes().item(i);
		    	if(n instanceof Element){
		    		
			    	Element cmd = (Element)root.getChildNodes().item(i);
			    	String action = StringUtil.dealNull(cmd.getAttribute("action"));
			    	String className = StringUtil.dealNull(cmd.getAttribute("class"));
			    	NodeList list = cmd.getElementsByTagName("param");
			    	Map<String, String> paraMap = new HashMap<String, String>();
			    	
			    	for(int j = 0;j<list.getLength();j++){
			    		Node pn = list.item(j);
			    		if(pn instanceof Element){
			    			Element param = (Element)pn;
			    			String name=StringUtil.dealNull(param.getAttribute("name"));
			    			String alis = StringUtil.dealNull(param.getAttribute("alis"));
			    			paraMap.put(alis, name);
			    		}
			    	}
			    	
			    	
			    	CmdBean cb = new CmdBean();
			    	cb.setAction(action);
			    	cb.setClassName(className);
			    	cb.setMap(paraMap);
		
			    	cmdBeanMap.put(cb.getAction(),cb);
			    	logger.logInfo(action+"   "+className);
		    	}
		    }
		    logger.logInfo("parse support cmd ...... done");
		}catch(Exception e){
			e.printStackTrace();
			logger.logError(e.getMessage());
		}

	}
	
	
	
	@SuppressWarnings("static-access")
	public static String getValue(String key){
		if(!config.tag){
			try {
				config.putCmdInMap();
				config.putInMap();
			} catch (Exception e) {
				//e.printStackTrace();
				logger.logError("load properties error:"+e.getMessage());
			} 
		}
		return map.get(key);
	}
	
	@SuppressWarnings("static-access")
	public static String getCmd(String action){
		if(!config.tag){
			try {
				config.putCmdInMap();
				config.putCmdBeanInMap();
				config.putInMap();
			} catch (Exception e) {
				//e.printStackTrace();
				logger.logError("load properties error:"+e.getMessage());
			} 
		}
		return cmdMap.get(action);
	}
	
	@SuppressWarnings("static-access")
	public static CmdBean getBeanCmd(String source){
		if(!config.tag){
			try {
				config.putCmdBeanInMap();
				config.putInMap();
			} catch (Exception e) {
				//e.printStackTrace();
				logger.logError("load properties error:"+e.getMessage());
			} 
		}
		return cmdBeanMap.get(source);
	}
	
	public static boolean regex(String regex, String str) {
		boolean trueOrFalse;
		Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(str);
		trueOrFalse = m.matches();
		return trueOrFalse;
	}

	public static void main(String[] args){
		config.putCmdBeanInMap();
	}
}
