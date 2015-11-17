package cn.adwalker.IOSChannel.factory;

import org.apache.commons.lang.StringUtils;

import cn.adwalker.IOSChannel.service.ISendClickService;
import cn.adwalker.IOSChannel.service.impl.OneDeviceidMacFirstServiceImpl;
import cn.adwalker.IOSChannel.service.impl.OneDeviceidServiceImpl;
import cn.adwalker.IOSChannel.service.impl.RawMacAllServiceImpl;
import cn.adwalker.IOSChannel.service.impl.RawMacServiceImpl;
import cn.adwalker.IOSChannel.service.impl.SendClick4CMGE;
import cn.adwalker.IOSChannel.service.impl.SendClick4QianChengWuYou;
import cn.adwalker.IOSChannel.service.impl.SendClick58TongCheng;
import cn.adwalker.IOSChannel.vo.Advertisement_IOS;
import cn.adwalker.ad.util.AppConstant;
/**
 * 创建点击工厂
 * @author jief
 *
 */
public class ClickChannelFactory {

	public static ISendClickService createClickService(Advertisement_IOS ios){
		
		if(StringUtils.isNotBlank(ios.getService_name()) && AppConstant.CP_ZHSHY.equals(ios.getService_name())){
			return SendClick4CMGE.getInstance();
		}else if(StringUtils.isNotBlank(ios.getService_name()) && AppConstant.CP_RAW_MAC.equals(ios.getService_name())){
			return RawMacServiceImpl.getInstance();
		}else if(StringUtils.isNotBlank(ios.getService_name()) && AppConstant.CP_ONEDEVICEID.equals(ios.getService_name())){
			return  OneDeviceidServiceImpl.getInstance();
		}else if(StringUtils.isNotBlank(ios.getService_name()) && AppConstant.CP_ONEDEVICEID_MAC_FIR.equals(ios.getService_name())){
			return  OneDeviceidMacFirstServiceImpl.getInstance();
		}else if(StringUtils.isNotBlank(ios.getService_name()) && AppConstant.CP_RAW_MAC_ALL.equals(ios.getService_name())){
			return  RawMacAllServiceImpl.getInstance();
		}else if(StringUtils.isNotBlank(ios.getService_name()) && AppConstant.CP_QIAN_CHENG_WU_YOU.equals(ios.getService_name())){
			return  SendClick4QianChengWuYou.getInstance();
		}else if(StringUtils.isNotBlank(ios.getService_name()) && AppConstant.CP_58_TONG_CHENG.equals(ios.getService_name())){
			return  SendClick58TongCheng.getInstance();
		}
		
		 return null;
	}
}
