package cn.adwalker;


import java.util.Date;

import cn.adwalker.model.ad.dao.IPlacementDao;
import cn.adwalker.model.ad.domain.Placement;
import cn.adwalker.ad.admin.channel.form.ChannelAddForm;
import cn.adwalker.core.repository.IBaseDao;

public class TestPlacementDao extends TestBase {
	
	public void testtable() throws Exception{
		IPlacementDao placementDao=(IPlacementDao) context.getBean("placementDao");
		Placement registChannel =new Placement();
		
		
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
