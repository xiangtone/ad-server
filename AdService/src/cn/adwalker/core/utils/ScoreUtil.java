package cn.adwalker.core.utils;

import cn.adwalker.ad.util.AppConstant;

public abstract class ScoreUtil {
	
	private ScoreUtil(){
	}
	
	/**
	 * 
	* <p>Title: getFlag</p>
	* <p>Description:获取版本标识</p>
	* @param sdkversion
	* @return
	* @author cuidd
	* @date 2014年10月8日
	* @return int
	* @version 1.0
	* @throws
	 */
	public static int getFlag(String sdkversion){
		int flag=0;
		if (sdkversion.equals(AppConstant.ANDROIDV300)) {
			flag=1;
		} else if (sdkversion.equals(AppConstant.ANDROIDV310)) {
			flag=1;
		} else if (sdkversion.equals(AppConstant.ANDROIDV311)) {
			flag=1;
		}else if (sdkversion.equals(AppConstant.ANDROIDV312)) {
			flag=1;
		}else if (sdkversion.equals(AppConstant.ANDROIDV313)) {
			flag=1;
		}else if (sdkversion.equals(AppConstant.ANDROIDV314)) {
			flag=1;
		}
		/*
		else if (sdkversion.equals(AppConstant.ANDROIDV316)) {
			flag=1;
		}*/
		
		return flag;
	}
	
	

}
