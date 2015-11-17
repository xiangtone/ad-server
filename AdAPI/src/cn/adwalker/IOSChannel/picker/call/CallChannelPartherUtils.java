package cn.adwalker.IOSChannel.picker.call;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;

import cn.adwalker.IOSChannel.picker.bean.ChannelConfig;
import cn.adwalker.IOSChannel.picker.bean.IosActivateLog;
import cn.adwalker.IOSChannel.picker.util.MD5Util;
import cn.adwalker.IOSChannel.picker.util.StringUtil;

public class CallChannelPartherUtils {
	private static final Logger log= Logger.getLogger(CallChannelPartherUtils.class);
/**
 * 为多盟生成回调接口url
 * @param activate
 * @param config
 * @return
 */
	public static String createUrl4domob(final IosActivateLog activate,final ChannelConfig config){
		String url=config.getUrl();
		StringBuffer sburl=new StringBuffer(url);
		if(url.contains("?")){
			sburl.append("&");
		}else{
			sburl.append("?");
		}
		String [] params={"","","","","",""};
		params[5]="5c758d01d2216216d15876786cc15f2c";
		if(!StringUtil.isEmpty(config.getAdid_para())){
			sburl.append(config.getAdid_para()).append("=").append(activate.getAd_id());
			params[0]=activate.getAd_id();
		}
		if(!StringUtil.isEmpty(config.getDeviceid_para())){
			String deviceid=activate.getMac();
			if(!StringUtil.isEmpty(deviceid) && deviceid.length()==12){
				deviceid=StringUtil.formatMac(deviceid);
			}else{
				deviceid="02:00:00:00:00:00";
			}
			params[1]=deviceid;
			sburl.append("&").append(config.getDeviceid_para()).append("=").append(deviceid);
			MD5Util md5u = new MD5Util();
			params[2]=md5u.getMD5ofStr(deviceid);
			sburl.append("&").append("ma").append("=").append(params[2]);
			
		}
		if(!StringUtil.isEmpty(config.getIdfa()) && !StringUtil.isEmpty(activate.getIdfa())){
			sburl.append("&").append(config.getIdfa()).append("=").append(activate.getIdfa());
			params[3]=activate.getIdfa();
		}	
		if(!StringUtil.isEmpty(config.getOpenudid()) && !StringUtil.isEmpty(activate.getOpenudid())){
			params[4]=activate.getOpenudid().toUpperCase();
			sburl.append("&").append(config.getOpenudid()).append("=").append(params[4]);
		}
		try{
			String sign=getDomobSign(params[0],params[1],params[2],params[3],params[4],params[5]);
			sburl.append("&").append("sign").append("=").append(sign);
		}catch(Exception e){
			log.error("多盟接口生成签名错误！"+e.getMessage(),e);
			return null;
		}
		if(!StringUtil.isEmpty(activate.getClick_time())){
			long ct=activate.getClick_time().getTime()/1000;
			sburl.append("&clktime=").append(ct+"");
		}
		if(!StringUtil.isEmpty(config.getTime_para()) && !StringUtil.isEmpty(activate.getCreate_time())){
			long at=activate.getCreate_time().getTime()/1000;
			sburl.append("&").append(config.getTime_para()).append("=").append(at+"");
		}
		if(!StringUtil.isEmpty(config.getClient_ip()) && !StringUtil.isEmpty(activate.getClient_ip())){
			sburl.append("&").append(config.getClient_ip()).append("=").append(activate.getClient_ip());
		}
		return sburl.toString();
	}
	
	public static String getDomobSign (String appid, String udid, String ma, String ifa, String oid, String key) throws NoSuchAlgorithmException {
		char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',  'e', 'f' };
		String s = appid + "," + udid + "," + ma + "," + ifa + "," + oid + "," + key;
		byte[] strTemp = s.getBytes();
		MessageDigest mdTemp = MessageDigest.getInstance("MD5");
		mdTemp.update(strTemp);
		byte[] md = mdTemp.digest();
		int j = md.length;
		char[] str = new char[j * 2];
		int k = 0;
		for (int i = 0; i < j; i++) {
			byte byte0 = md[i];
			str[(k++)] = HEX_DIGITS[(byte0 >>> 4 & 0xF)];
			str[(k++)] = HEX_DIGITS[(byte0 & 0xF)];
		}
		return new String(str);
	}
	
	public static void main(String[] args) throws NoSuchAlgorithmException {
		System.out.println(getDomobSign("4498731", "7C:AB:A3:D6:E7:81", "", "511F7987-6E2F-423A-BFED-E4C52CB5A6DC", "", "123456"));
	}

}
