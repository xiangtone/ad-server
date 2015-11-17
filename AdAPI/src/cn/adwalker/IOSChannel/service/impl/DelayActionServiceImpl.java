package cn.adwalker.IOSChannel.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.adwalker.IOSChannel.dao.IChannelDao;
import cn.adwalker.IOSChannel.picker.dao.SupportDao;
import cn.adwalker.IOSChannel.picker.vo.IosClick;
import cn.adwalker.IOSChannel.service.IDelayActionService;
import cn.adwalker.ad.pool.ChannelClickPool;

@Service("delayActionService")
public class DelayActionServiceImpl implements IDelayActionService{

	@Resource
	IChannelDao channelDao;
	@Resource
	SupportDao supportDao;
	/**
	 * <p>批量添加点击信息</p>
	 */
	public Integer batchAddClick(){
		List<IosClick> list=ChannelClickPool.getInstance().getBattchList();
		if(list!=null && list.size()>0){
			supportDao.battchAddClick(list);
			return list.size();
		}
		return 0;
	}
}
