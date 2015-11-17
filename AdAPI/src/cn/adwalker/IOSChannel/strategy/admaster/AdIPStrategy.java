package cn.adwalker.IOSChannel.strategy.admaster;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.adwalker.IOSChannel.picker.dao.SupportDao;
import cn.adwalker.IOSChannel.picker.util.StringUtil;
import cn.adwalker.IOSChannel.picker.vo.IosClick;
import cn.adwalker.ad.util.ConfigUtil;
/**
 * ip限制激活数策略
 * @author adwalkerji
 *
 */
public class AdIPStrategy implements IAdMasterSendStrategy{
	private static AdIPStrategy ipStrategy=new AdIPStrategy();
	public static AdIPStrategy getInstance(){
		if(ipStrategy==null){
			synchronized (AdIPStrategy.class) {
				if(ipStrategy==null){
					ipStrategy=new AdIPStrategy();
				}
			}
		}
		return ipStrategy;
	}
	@Override
	public boolean isSend(SupportDao supportDao,final AdSendConfig sendConfig, final IosClick iosClick,StringBuffer strbuf) {
		boolean b=true;
		if(StringUtil.isEmpty(iosClick.getClient_ip())){
			return b;
		}
		Integer ipmax=null;
		if(null==sendConfig || StringUtil.isEmpty(sendConfig.getAd_ip_num())){
			String ipmaxstr=ConfigUtil.getString("ad_ip_num");
			if(!StringUtil.isEmpty(ipmaxstr) && ipmaxstr.matches("[1-9]{1}[0-9]*")){
				ipmax=Integer.parseInt(ipmaxstr);
			}
		}else{
			ipmax=sendConfig.getAd_ip_num();
		}
		if(ipmax!=null){
			int aba=supportDao.getActivateCountByIp(iosClick.getClient_ip(), iosClick.getAppid());
			if(aba >= ipmax){
				b=false;
				strbuf.append("\t").append("根据ip"+iosClick.getClient_ip()+"统计激活数为"+aba+"大于配置数 "+ipmax);
			}
		}
		if(b){
			Integer ipduanmax=null;
			if(null==sendConfig || StringUtil.isEmpty(sendConfig.getAd_ipsegment_num())){
				String ipduanmaxstr=ConfigUtil.getString("ad_ipsegment_num");
				if(!StringUtil.isEmpty(ipduanmaxstr) && ipduanmaxstr.matches("[1-9]{1}[0-9]*")){
					ipduanmax=Integer.parseInt(ipduanmaxstr);
				}
			}else{
				ipduanmax=sendConfig.getAd_ipsegment_num();
			}
			if(ipduanmax!=null){
				int aba=supportDao.getActivateCountByIpduan(ipdo(iosClick.getClient_ip()), iosClick.getAppid());
				if(aba >= ipduanmax){
					b=false;
					strbuf.append("\t").append("根据ip 段 ="+iosClick.getClient_ip()+"统计激活数为"+aba+"大于配置数"+ipduanmax);
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
