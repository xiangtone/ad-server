package cn.adwalker.IOSChannel.strategy.media;

import cn.adwalker.IOSChannel.picker.bean.IosActivateLog;
import cn.adwalker.IOSChannel.picker.dao.SupportDao;
import cn.adwalker.IOSChannel.picker.util.StringUtil;
import cn.adwalker.ad.util.ConfigUtil;
/**
 * 扣量策略
 * @author adwalkerji
 *
 */
public class MediaDetainStrategy implements IMediaCallbackStrategy{
	private static MediaDetainStrategy DetainStrategy=new MediaDetainStrategy();
	public static MediaDetainStrategy getInstance(){
		return DetainStrategy;
	}
	@Override
	public boolean isCallback(SupportDao supportDao,final MediaCallbackConfig callbackConfig,final IosActivateLog activate,StringBuffer sbuf) {
		boolean b=true;
		Double dr=null;
		if(null==callbackConfig || null==callbackConfig.getDetain_rate()){
			String strdr=ConfigUtil.getString("detain_rate");
			if(!StringUtil.isEmpty(strdr) && strdr.matches("^\\d(\\.\\d+)?")){
				dr=Double.parseDouble(strdr);
			}
		}else{
			dr=callbackConfig.getDetain_rate();
		}
		if(dr!=null && dr >0.0d){
			double rand=Math.random();
			if(rand <= dr){
				b=false;
				sbuf.append("\t").append("被扣量!扣量比例是:"+dr);
			}
		}
		return b;
	}

}
