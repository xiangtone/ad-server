package cn.adwalker.IOSChannel.strategy.media;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.adwalker.IOSChannel.picker.bean.IosActivateLog;
import cn.adwalker.IOSChannel.picker.dao.SupportDao;
import cn.adwalker.IOSChannel.picker.util.StringUtil;
import cn.adwalker.IOSChannel.picker.xmemcached.ConfigCache;
import cn.adwalker.ad.util.ConfigUtil;
/**
 * 媒体ip激活数限制策略
 * @author adwalkerji
 *
 */
public class MediaIPStrategy implements IMediaCallbackStrategy{
	private static MediaIPStrategy IPStrategy=new MediaIPStrategy();
	public static MediaIPStrategy getInstance(){
		return IPStrategy;
	}
	@Override
	public boolean isCallback(SupportDao supportDao,
			MediaCallbackConfig callbackConfig, IosActivateLog activate,StringBuffer sbuf) {
		boolean b=true;
		if(StringUtil.isEmpty(activate.getClient_ip())){
			return b;
		}
		int iosadNums=ConfigCache.getiosAdNum();
		Integer ipmax=null;
		if(null==callbackConfig || StringUtil.isEmpty(callbackConfig.getIp_times())){
			String ipmaxstr=ConfigUtil.getString("ip_times");
			if(!StringUtil.isEmpty(ipmaxstr) && ipmaxstr.matches("[1-9]{1}[0-9]*")){
				ipmax=Integer.parseInt(ipmaxstr);
			}
		}else{
			ipmax=callbackConfig.getIp_times();
		}
		if(ipmax!=null){
			int aba=supportDao.getAppActivateCountByIp(activate.getClient_ip(), activate.getApplication_key());
			if(aba >= ipmax*iosadNums){
				b=false;
				sbuf.append("\t").append("此ip:"+activate.getClient_ip()+"统计激活数大于配置值"+ipmax*iosadNums+"当前的广告数:"+iosadNums);
			}
		}
		if(b){
			Integer ipduanmax=null;
			if(null==callbackConfig || StringUtil.isEmpty(callbackConfig.getIpsegment_times())){
				String ipduanmaxstr=ConfigUtil.getString("ipsegment_times");
				if(ipduanmaxstr.matches("[1-9]{1}[0-9]*")){
					ipduanmax=Integer.parseInt(ipduanmaxstr);
				}
			}else{
				ipduanmax=callbackConfig.getIpsegment_times();
			}
			if(ipduanmax!=null){
				int aba=supportDao.getAppActivateCountByIpduan(ipdo(activate.getClient_ip()), activate.getApplication_key());
				if(aba >= ipduanmax*iosadNums){
					b=false;
					sbuf.append("\t").append("此ip段:"+activate.getClient_ip()+"激活数大于配置值"+ipduanmax*iosadNums+"当前的广告数:"+iosadNums);
				}
			}
		}
		return b;
	}
	//把ip形如 192.168.1.20转换为192.168.1
	public String ipdo(String ip){
		Pattern pattern = Pattern.compile("\\.\\d+$"); 
        Matcher matcher = pattern.matcher(ip); 
        //String a=matcher.replaceAll(""); 
        return matcher.replaceAll(""); 
	}

}
