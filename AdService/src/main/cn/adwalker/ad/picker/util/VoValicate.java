package cn.adwalker.ad.picker.util;

import cn.adwalker.ad.param.AcParam;
import cn.adwalker.ad.param.PageParam;
import cn.adwalker.ad.param.PayScoreParam;
import cn.adwalker.ad.param.ScoreQueryParam;
import cn.adwalker.ad.param.WallParam;
import cn.adwalker.ad.param.WallParam2;
import cn.adwalker.ad.picker.vo.InitVo;
import cn.adwalker.ad.util.WallUtils;

public class VoValicate {

	//初始化参数验证
	public static boolean validateInitVo(InitVo vo){
		return true;
	}
	//广告请求参数验证以及参数处理
	public static boolean validatePageVoForAndroid(PageParam pvo){
		///android/ad_picker.do?uuid=&pageNo=1&pageSize=10&page_type=0&image_type=1&version=androidV2.1.0&appkey=AWAAB718ZDIG1173B3TO3JUVL80MCXIEHB&channel=adwalker&terminalType=mobile&imsi=460002320208241&isSign=0
		boolean isNull =StringUtil.isEmpty(pvo.getAppkey(), pvo.getUuid(),pvo.getPage_type(),pvo.getImage_type());
		if(!isNull){
			pvo.setUuid(StringUtil.dealNull(pvo.getUuid(),pvo.getMac()));
			pvo.setTerminalType(StringUtil.dealNull(pvo.getTerminalType()));
			pvo.setIsSign(StringUtil.equals(1, pvo.getIsSign())?1:0);
			return true;
		}
		return false;
	}
	
	public static boolean validatePageVoForAndroid(WallParam pvo){
		///android/ad_picker.do?uuid=&pageNo=1&pageSize=10&page_type=0&image_type=1&version=androidV2.1.0&appkey=AWAAB718ZDIG1173B3TO3JUVL80MCXIEHB&channel=adwalker&terminalType=mobile&imsi=460002320208241&isSign=0
		boolean isNull =StringUtil.isEmpty(pvo.getAppId(), pvo.getUuid(),pvo.getPage_type(),pvo.getImage_type());
		if(!isNull){
			pvo.setUuid(StringUtil.dealNull(pvo.getUuid(),pvo.getMac()));
			pvo.setTerminalType(StringUtil.dealNull(pvo.getTerminalType()));
			pvo.setIsSign(StringUtil.equals(1, pvo.getIsSign())?1:0);
			return true;
		}
		return false;
	}
	//appId=200&mac=AC3C0B296B4D&page_type=1&pageSize=10&image_type=1&version=IOS1.2.0&channel=IOS1.2.0&terminalType=iphone&pageNo=1
	public static boolean validatePageVoForIos(PageParam pvo){
		changeUuid(pvo);
		boolean isNull =StringUtil.isEmpty(pvo.getAppkey(), pvo.getUuid(),pvo.getPage_type(),pvo.getImage_type());
		if(!isNull){
			pvo.setTerminalType(StringUtil.dealNull(pvo.getTerminalType()));
			pvo.setIsSign(StringUtil.equals(1, pvo.getIsSign())?1:0);
			return true;
		}
		return false;
		
	}
	
	public static boolean validatePageVoForIos2(WallParam2 pvo){
		boolean isNull =StringUtil.isEmpty(pvo.getAppId(), pvo.getUuid(),pvo.getPage_type());
		if(!isNull){
			pvo.setTerminalType(StringUtil.dealNull(pvo.getTerminalType()));
			return true;
		}
		return false;
		
	}
	
	public static boolean validatePageVoForIos(WallParam pvo){
		boolean isNull =StringUtil.isEmpty(pvo.getAppId(), pvo.getUuid(),pvo.getPage_type(),pvo.getImage_type());
		if(!isNull){
			pvo.setTerminalType(StringUtil.dealNull(pvo.getTerminalType()));
			pvo.setIsSign(StringUtil.equals(1, pvo.getIsSign())?1:0);
			return true;
		}
		return false;
		
	}
	public static boolean validatePageVoForGetScore(ScoreQueryParam pvo){
		changeUuid(pvo);
		return !StringUtil.isEmpty(pvo.getAppId(), pvo.getUuid());
	}
	//ios7  版本系统在1.2.2以后采用udid做为uuid.
	public static void changeUuid(PageParam pvo){
		if(WallUtils.isIos7(pvo.getMac(),pvo.getOs_version())){
			pvo.setUuid(StringUtil.dealNull(pvo.getOpenudid(),pvo.getMac()));
		}else{
			pvo.setUuid(StringUtil.dealNull(pvo.getUuid(),pvo.getMac()));
		}
	}
	
	public static void changeUuid(PayScoreParam pvo){
		if(WallUtils.isIos7(pvo.getMac(),pvo.getOs_version())){
			pvo.setUuid(StringUtil.dealNull(pvo.getOpenudid(),pvo.getMac()));
		}else{
			pvo.setUuid(StringUtil.dealNull(pvo.getUuid(),pvo.getMac()));
		}
	}
	
	
	//ios7  版本系统在1.2.2以后采用udid做为uuid.
		public static void changeUuid(AcParam pvo){
			if(WallUtils.isIos7(pvo.getMac(),pvo.getOs_version())){
				pvo.setUuid(StringUtil.dealNull(pvo.getOpenudid(),pvo.getMac()));
			}else{
				pvo.setUuid(StringUtil.dealNull(pvo.getUuid(),pvo.getMac()));
			}
		}
		
		
		public static void changeUuid(ScoreQueryParam pvo){
			if(WallUtils.isIos7(pvo.getMac(),pvo.getOs_version())){
				pvo.setUuid(StringUtil.dealNull(pvo.getOpenudid(),pvo.getMac()));
			}else{
				pvo.setUuid(StringUtil.dealNull(pvo.getUuid(),pvo.getMac()));
			}
		}
	
	
	
}
