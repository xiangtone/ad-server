package cn.adwalker.IOSChannel.service;

import cn.adwalker.IOSChannel.vo.Advertisement_IOS;
import cn.adwalker.IOSChannel.vo.ChannelRequestResult;
/**
 * <p>发送点击请求</p>
 * @author jief
 *
 */
public interface ISendClickService {
	/**
	 * 
	 * @param vo
	 * @param ios
	 */
	public void SendClick(ChannelRequestResult vo, Advertisement_IOS ios);
}
