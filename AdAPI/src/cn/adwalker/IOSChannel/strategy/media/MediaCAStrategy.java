package cn.adwalker.IOSChannel.strategy.media;

import cn.adwalker.IOSChannel.picker.bean.IosActivateLog;
import cn.adwalker.IOSChannel.picker.dao.SupportDao;
import cn.adwalker.IOSChannel.picker.util.DateUtil;
import cn.adwalker.IOSChannel.picker.util.StringUtil;
import cn.adwalker.ad.util.ConfigUtil;
/**
 * 点击和激活时间间隔策略 nM/8 +10 (n标示app大小)
 * @author adwalkerji
 *
 */
public class MediaCAStrategy implements IMediaCallbackStrategy{
	private static MediaCAStrategy CAStrategy=new MediaCAStrategy();
	public static MediaCAStrategy getInstance(){
		return CAStrategy;
	}
	@Override
	public boolean isCallback(SupportDao supportDao,
			MediaCallbackConfig callbackConfig, IosActivateLog activate,StringBuffer sbuf) {
		boolean b=true;
		Integer caxishu=null;
		if(callbackConfig==null || callbackConfig.getCa_time_ratio()==null){
			String caxstr=ConfigUtil.getString("ca_time_ratio");
			if(!StringUtil.isEmpty(caxstr) && caxstr.matches("[0-9]{1}[0-9]*")){
				caxishu=Integer.parseInt(caxstr);
			}
		}else{
			caxishu=callbackConfig.getCa_time_ratio();
		}
		if(caxishu != null){
			int time=15;
			String calen=ConfigUtil.getString("ca_time_len");
			if(!StringUtil.isEmpty(calen) && calen.matches("[0-9]{1}[0-9]*")){
				time=Integer.parseInt(calen);
			}
			time+=caxishu;
			if(!StringUtil.isEmpty(activate.getCreate_time()) && !StringUtil.isEmpty(activate.getClick_time())){
					Long second = DateUtil.getSecondByTwoDate(activate.getClick_time(), activate.getCreate_time());
					b = second.intValue()>time;
					if(!b){
						sbuf.append("\t").append("激活时间点击时间的间隔是 "+second+"小于规定时间"+time);
				    }
			}
		}
		return b;
	}

}
