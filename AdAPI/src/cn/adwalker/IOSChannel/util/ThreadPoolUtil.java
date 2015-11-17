package cn.adwalker.IOSChannel.util;

import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class ThreadPoolUtil {
  private static ThreadPoolUtil hpu=null;
  private Executor exe;
  private ThreadPoolUtil(){
	  exe=new ScheduledThreadPoolExecutor(5);
  }
  
  public static ThreadPoolUtil getInstance(){
	  if(null==hpu){
	       synchronized (ThreadPoolUtil.class) {
			  hpu=new ThreadPoolUtil();
		   }
		  }
	  return hpu;
  }
  
  public void submitTask(Runnable task){
	  exe.execute(task);
  }
  
}
