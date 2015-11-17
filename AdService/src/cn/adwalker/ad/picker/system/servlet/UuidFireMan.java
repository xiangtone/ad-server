package cn.adwalker.ad.picker.system.servlet;

import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.log4j.Logger;

import cn.adwalker.ad.picker.form.UserForm;
import cn.adwalker.ad.picker.util.StringUtil;
import cn.adwalker.ad.picker.vo.InitVo;


/**
 * @author luo
 * t_user_* 表数据
 */
public class UuidFireMan implements IStartUp {
    private static Logger logger = Logger.getLogger(UuidFireMan.class);
	public static Timer timer = null;
	public static UuidFireMan control = null;
	public Queue<Object> cache;
	
	
	public UuidFireMan(){
		cache = new ConcurrentLinkedQueue<Object>();
	}
	public static UuidFireMan getInstance() {
		if (control == null) {
			initInstance();
		}
		return control;
	}
	private synchronized static void initInstance() {
		if (control == null) {
			control = new UuidFireMan();
		}
	}
	
	public void submit(UserForm o) {
		UserForm form = (UserForm)o;	
		logger.info("uuidnull:"+form.getUuid());
		cache.offer(o);
	}
	
	@Override
	public void destory() {
		timer.cancel();
		timer = null;
	}

	@Override
	public void init() {
		timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				saveUserInfo();
			}
		}, 0, 1000 * 60);
	}
	public void saveUserInfo(){
		logger.info("begin save userInfo=============");
		while(!cache.isEmpty()){
			UserForm form = (UserForm)cache.poll();			
			if(!StringUtil.isEmpty(form.getUuid())){
				logger.info("uuid:"+form.getUuid());
			    new Thread(new UuidRunnable(toInitVo(form))).start();
			}
		}
	}
	private InitVo toInitVo(UserForm vo){
		InitVo init = new InitVo();
		init.setAppId(vo.getAppId());
		init.setChannel(vo.getChannel());
		init.setDevUserId(vo.getDevUserId());
		init.setIdfa(vo.getIdfa());
		init.setIp(vo.getIp());
		init.setMac(vo.getMac());
		init.setUuid(vo.getUuid());
		init.setTelModel(vo.getTelModel());
		init.setOs_version(vo.getOs_version());
		init.setOpenudid(vo.getOpenudid());
		init.setOperator(vo.getOperator());
		init.setTerminalType(vo.getTerminalType());
		init.setVersion(vo.getVersion());
		return init;
	}
	
	public static void main(String[] args){
		UuidFireMan fm = UuidFireMan.getInstance();
		fm.saveUserInfo();
		
	}
	
	

}
