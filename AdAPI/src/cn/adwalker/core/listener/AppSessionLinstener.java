package cn.adwalker.core.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import cn.adwalker.ad.pool.ThreadPool;
/**
 * <p>初始化通用类</p>
 * @author jief
 *
 */
public class AppSessionLinstener implements HttpSessionListener{

	public AppSessionLinstener(){
		ThreadPool.getInstance();
		System.out.println("线程池初始化完成。。");
	}
	
	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
