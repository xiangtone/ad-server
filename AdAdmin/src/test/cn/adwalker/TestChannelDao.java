package cn.adwalker;


import java.util.Date;

import cn.adwalker.model.channel.dao.IChannelDao;
import cn.adwalker.ad.admin.channel.form.ChannelAddForm;
import cn.adwalker.core.repository.IBaseDao;

public class TestChannelDao extends TestBase {
	
	public void testtable() throws Exception{
		IChannelDao placementDao=(IChannelDao) context.getBean("userChannelDao");
		 ChannelAddForm registChannel=new ChannelAddForm();
		
		
		placementDao.insert(registChannel);
		
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
