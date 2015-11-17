package cn.adwalker.IOSChannel.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.adwalker.IOSChannel.param.ClickParam;
import cn.adwalker.IOSChannel.service.IAdClusterService;
import cn.adwalker.ad.cache.IAdClusterCache;
import cn.adwalker.ad.cache.element.AdCluster;

@Service
public class AdClusterServiceImpl implements IAdClusterService {
	@Resource
	private IAdClusterCache adClusterCache;

	@SuppressWarnings("unused")
	@Override
	public AdCluster cc(ClickParam param) {
		AdCluster adCluster=adClusterCache.getCacheElement(param.getAd_id());
		System.out.println(adCluster);
		//发送数据
		String url=adCluster.getClick_url();//http://ios.wall.youmi.net/v2/c_goto?s=UYLXi_AU9RNVrAOi0gpPF6iiXjVHTVNjmPui1TOSPz9fZn2XFjtqA
		//参数 s后边追加参数
		//mac=a0edcd0b5235&udid=b81af39a6b5abf56e77dba50c51f8a6c88fbcf91&ifa=c50e8ca3ccc3452293a7d4265694ac62&ip=58.63.2.4
		return adCluster;
		
	}



}
