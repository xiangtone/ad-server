package cn.adwalker;


import java.util.Date;

import cn.adwalker.ad.admin.channel.form.ChannelAddForm;
import cn.adwalker.core.repository.IBaseDao;

public class TestIBasePageDao extends TestBase {
	
	public void testtable(){
		IBaseDao baseDao=(IBaseDao) context.getBean("basePageDao");
		ChannelAddForm registChannel =new ChannelAddForm();
		registChannel.setName("信息");
		registChannel.setEmail("muzi@eamr.com");
		registChannel.setChanne_mode(1);
		registChannel.setCreate_time(new Date());
		registChannel.setPhone("12154564");
		try {
			baseDao.insert(registChannel);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void testtableupdate( ){
		IBaseDao baseDao=(IBaseDao) context.getBean("basePageDao");
		ChannelAddForm registChannel =new ChannelAddForm();
		registChannel.setName("上海上海");
		registChannel.setEmail("benjing@eamr.com");
		registChannel.setChanne_mode(2);
		registChannel.setCreate_time(new Date());
		registChannel.setPhone("1398886666");
		registChannel.setId(6l);
		try {
			baseDao.update(registChannel);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
