package cn.adwalker.ad.picker.system.servlet;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

import org.apache.log4j.Logger;

import cn.adwalker.ad.picker.monitor.ActionMonitor;
import cn.adwalker.ad.picker.monitor.CallCenterTask;
import cn.adwalker.ad.picker.util.DateUtil;
import cn.adwalker.ad.picker.util.NumberUtil;
import cn.adwalker.ad.util.ConfigUtil;



/**
 * @功能描述:执行系统定时任务
 * author：luoyouhua
 * date：Sep 14, 2011 10:31:47 AM 
 */
public class SystemScanTesk implements IStartUp{
	private static final org.apache.log4j.Logger logger = Logger.getLogger(SystemScanTesk.class);
    @SuppressWarnings("unused")
	private static final int DAY = 24 * 3600 * 1000;
    private static SystemScanTesk scan = new SystemScanTesk();
    private static Timer timer = new Timer(false);
    private static Integer MINUTE=NumberUtil.getInteger(ConfigUtil.getString("monitor.minute"), 5);
	public static SystemScanTesk getInstance(){
	    	return scan;
	}
	public void destory(){
		logger.info("call monitor center before destory");
		ActionMonitor.getInstance().callCenter();
		logger.info("destory!");
		
	}
	public int getMinute(Date d){
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
	    return cal.get(Calendar.MINUTE);
	}
	//获取任务启动时间
	public Date getBeginDate(){
		Date d = new Date();
		int i = getMinute(d);
		int beginMin = (i/MINUTE+1)*MINUTE;
		Date beginDate=null;
		if(beginMin>=60){
		   beginDate = DateUtil.getDate(DateUtil.absoluteHour(d, 1),"yyyy-MM-dd HH:00:00");
		}else{
		   String m=beginMin<10?("0"+beginMin):beginMin+"";
		   String bs="yyyy-MM-dd HH:"+m+":00";
		   beginDate = DateUtil.getDate(d,bs);
		}	
		return beginDate;
	}
	//启动控量任务调度
	public void init() {
		logger.info("begin system scan tesk!!");
		timer.scheduleAtFixedRate(new CallCenterTask(),getBeginDate(), MINUTE*60*1000);
		//timer.scheduleAtFixedRate(DailyJober.getInstance(), DateUtil.getDate(DateUtil.absoluteDate(new Date(), 1), "yyyy-MM-dd 01:10:00"), DAY);//ÿ�����ִ��һ��
	}
	

}
