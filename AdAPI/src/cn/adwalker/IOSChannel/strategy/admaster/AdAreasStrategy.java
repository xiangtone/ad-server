package cn.adwalker.IOSChannel.strategy.admaster;


import java.util.List;

import cn.adwalker.IOSChannel.picker.dao.SupportDao;
import cn.adwalker.IOSChannel.picker.util.StringUtil;
import cn.adwalker.IOSChannel.picker.vo.IosClick;
import cn.adwalker.IOSChannel.picker.xmemcached.ConfigCache;
import cn.adwalker.IOSChannel.strategy.AreaScheme;
import cn.adwalker.IOSChannel.strategy.AreaSchemeInfo;
/**
 * 区域限制策略
 * @author adwalkerji
 *
 */
public class AdAreasStrategy implements IAdMasterSendStrategy{
	private static AdAreasStrategy areasStrategy=new AdAreasStrategy();
	public static AdAreasStrategy getInstance(){
		if(areasStrategy==null){
			synchronized (AdAreasStrategy.class) {
				if(areasStrategy==null){
					areasStrategy=new AdAreasStrategy();
				}
			}
		}
		return areasStrategy;
	}
	
	@Override
	public boolean isSend(SupportDao supportDao,final AdSendConfig sendConfig, final IosClick iosClick,StringBuffer strbuf) {
		boolean b=true;
		List<AreaSchemeInfo> areas=null;
		if(StringUtil.isEmpty(iosClick.areaCode)){
			return b;
		}
		if(null!=sendConfig && sendConfig.getSchemeInfo()!=null ){
			areas=sendConfig.getSchemeInfo();
		}else{
			AreaScheme scheme =ConfigCache.getDefaultAreaScheme("0");
			if(scheme!=null && scheme.getSchemeInfo()!=null){
				areas=scheme.getSchemeInfo();
			}
		}
		if(StringUtil.isEmpty(areas)){
			return b;
		}else{
			Integer config=null;
				for(AreaSchemeInfo area : areas){
					if(iosClick.areaCode.equals(area.getArea())){
						config=area.getConfig();
						break;
					}
				}
				if(!StringUtil.isEmpty(config)){
					if(0==config){
						b=false;
						strbuf.append("\t").append("此地区受限"+iosClick.areaCode);
					}else if(-1==config){
						b=true;
					}else if(config>0){
						int actnum=supportDao.getAdAreaActNum(iosClick.areaCode, iosClick.getAppid());
						b=actnum<config;
						if(!b){
							strbuf.append("\t").append("此地区:"+iosClick.areaCode+"激活数:"+actnum+"超过限制数:"+config);
						}
					}
				}
			}
		return b;
	}

}
