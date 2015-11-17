package cn.adwalker.IOSChannel.strategy.media;

import java.util.List;

import cn.adwalker.IOSChannel.picker.bean.IosActivateLog;
import cn.adwalker.IOSChannel.picker.dao.SupportDao;
import cn.adwalker.IOSChannel.picker.util.StringUtil;
import cn.adwalker.IOSChannel.picker.xmemcached.ConfigCache;
import cn.adwalker.IOSChannel.strategy.AreaScheme;
import cn.adwalker.IOSChannel.strategy.AreaSchemeInfo;
/**
 * 区域投放限制策略
 * @author adwalkerji
 *
 */
public class MediaareasStrategy implements IMediaCallbackStrategy{
private static MediaareasStrategy areasStrategy=new MediaareasStrategy();
public static MediaareasStrategy getInstance(){
	return areasStrategy;
}
	@Override
	public boolean isCallback(SupportDao supportDao,
			MediaCallbackConfig callbackConfig, IosActivateLog activate,StringBuffer strbuf) {
		boolean b=true;
		List<AreaSchemeInfo> areas=null;
		if(StringUtil.isEmpty(activate.getArea_code())){
			return b;
		}
		if(null!=callbackConfig && callbackConfig.getSchemeInfo()!=null ){
			areas=callbackConfig.getSchemeInfo();
		}else{
			AreaScheme scheme =ConfigCache.getDefaultAreaScheme("1");
			if(scheme!=null && scheme.getSchemeInfo()!=null){
				areas=scheme.getSchemeInfo();
			}
		}
		if(StringUtil.isEmpty(areas)){
			return b;
		}else{
			Integer config=null;
				for(AreaSchemeInfo area : areas){
					if(activate.getArea_code().equals(area.getArea())){
						config=area.getConfig();
						break;
					}
				}
				if(!StringUtil.isEmpty(config)){
					if(0==config){
						b=false;
						strbuf.append("\t").append("此地区受限"+activate.getArea_code());
					}else if(-1==config){
						b=true;
					}else if(config>0){
						int actnum=supportDao.getAdAreaActNum(activate.getArea_code(), activate.getApplication_key());
						b=actnum<config;
						if(!b){
							strbuf.append("\t").append("此地区:"+activate.getArea_code()+"激活数:"+actnum+"超过限制数:"+config);
						}
					}
				}
			}
		return b;
	}

}
