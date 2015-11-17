package cn.adwalker.IOSChannel.strategy.channel;

import cn.adwalker.IOSChannel.picker.bean.ChannelConfig;
import cn.adwalker.IOSChannel.picker.bean.IosActivateLog;
import cn.adwalker.IOSChannel.picker.util.StringUtil;
import cn.adwalker.ad.util.ConfigUtil;

public class ChannelDetainStrategy implements IChannelCallbackStrategy{
private static ChannelDetainStrategy detainStrategy=new ChannelDetainStrategy();
public static ChannelDetainStrategy getInstance(){
	return detainStrategy;
}
	@Override
	public boolean isCallback(ChannelConfig config, IosActivateLog activate,
			StringBuffer sbuf) {
		boolean b=true;
		Float pr=null;
		if(config==null || config.getPass_rate()==null){
			String prstr=ConfigUtil.getString("channel_pass_rate");
			if(!StringUtil.isEmpty(prstr) && prstr.matches("^\\d(\\.\\d+)?")){
				pr=Float.parseFloat(prstr);
			}
		}else{
			pr=config.getPass_rate();
		}
		float rdf=StringUtil.floatRandom();
		if(pr!=null && rdf > pr){
			b=false;
			sbuf.append("\t").append("被扣量，通过率是"+pr);
		}
		return b;
	}

}
