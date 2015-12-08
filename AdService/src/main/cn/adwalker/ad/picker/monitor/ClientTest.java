package cn.adwalker.ad.picker.monitor;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import cn.adwalker.ad.picker.util.DateUtil;
import cn.adwalker.ad.picker.util.NumberUtil;
import cn.adwalker.ad.picker.util.StringUtil;
import cn.adwalker.ad.util.ConfigUtil;


public class ClientTest {
	private Logger logger = Logger.getLogger(ClientTest.class);
	private static Timer timer = new Timer(false);
	private static Integer min=NumberUtil.getInteger(ConfigUtil.getString("controll.timer"), 5);
	public void task(){
		Date d = new Date();
		int i = getMinute(d);
		int beginMin = (i/min+1)*5;
		Date beginDate=null;
		if(beginMin>=60){
		   beginDate = DateUtil.getDate(DateUtil.absoluteHour(d, 1),"yyyy-MM-dd HH:00:00");
		}else{
		   String m=beginMin<10?("0"+beginMin):beginMin+"";
		   String bs="yyyy-MM-dd HH:"+m+":00";
		   beginDate = DateUtil.getDate(d,bs);
		}
		logger.info("begin task time:"+DateUtil.getFormatDate(beginDate, "yyyy-MM-dd HH:mm:ss"));
		timer.scheduleAtFixedRate(new CallCenterTask(),beginDate, 5*60*1000);
		//timer.scheduleAtFixedRate(new CallCenterTask(), DateUtil.getDate(beginTime, "yyyy-MM-dd HH:00:00"), 20);
	}
	
	public static void main(String[] args){
    	PropertyConfigurator.configure(Thread.currentThread().getContextClassLoader().getResource("config/log4j.properties"));//  
		//ClientTest client = new ClientTest();
	    for(int i=0;i<3000;i++){
	    	new Thread(new MyRunner()).start();
	    }
		
	
		
		
		
	}
	public int getMinute(Date d){
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
	    return cal.get(Calendar.MINUTE);
	}
	
}
class MyRunner implements Runnable{
@SuppressWarnings("unused")
private static final Logger logger = Logger.getLogger(MyRunner.class);
	@SuppressWarnings("unused")
	@Override
	public void run() {
		int i = 100;
		while(i>0){
		    ActionMonitor.getInstance().putActionInMap(10, ATTActionType.STATUS_CPM.toValue());
		    //logger.info("Thread_"+Thread.currentThread());
		    try {
		    	int t = StringUtil.random(200);
		    	//System.out.println(t);
		    	Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			i--;
		}
		
	}
}