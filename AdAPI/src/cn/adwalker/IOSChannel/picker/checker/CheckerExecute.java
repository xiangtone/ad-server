package cn.adwalker.IOSChannel.picker.checker;

import cn.adwalker.IOSChannel.picker.bean.YjfBean;
import cn.adwalker.IOSChannel.picker.util.ClassLoaderUtil;
import cn.adwalker.IOSChannel.picker.util.CmdBean;
import cn.adwalker.IOSChannel.picker.util.Config;
import cn.adwalker.IOSChannel.picker.util.Logger;
import cn.adwalker.IOSChannel.picker.util.StringUtil;

import net.sf.json.JSONObject;


public class CheckerExecute {
    
	public static final Logger logger = Logger.getLogger(CheckerExecute.class);
	public static final String NORMAL_PARCHER_CHECK="normalParcherCheck";

	public JSONObject json = new JSONObject();
	
	public static CheckerExecute createChecker(){
		return new CheckerExecute();
	}
	
	public JSONObject excuteChannelParther(YjfBean bean){
		PartherCheck check = getPartherCheck(bean);
		if(check.validateChannelBean(bean)){
			check.saveIosActionLog(bean);
			check.callComfirmCheck(bean);
		}else{
			logger.logInfo("paramter is null! ");
		}
		return check.getJsonObject();
	}
	
	public PartherCheck getPartherCheck(YjfBean bean){
		PartherCheck check=null;
		String className = getClassName(bean);
		if(!StringUtil.isEmpty(className)){
			check = (PartherCheck)ClassLoaderUtil.executeGetIntanceMethod(className);
		}else{
			//logger.logError("classNameNotFound Error:"+className +" cmd "+bean.getSource());
		}
        return check;
	}
	private  String getClassName(YjfBean bean){
		CmdBean cb = Config.getBeanCmd(bean.getAction());
		//String className =  Config.getCmd(bean.getSource());
		//if(StringUtil.isEmpty(cb)){
		//	cb = Config.getBeanCmd(NORMAL_PARCHER_CHECK);
		//}
		return cb.getClassName();
	}
	
}
