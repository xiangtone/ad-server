/**
* <p>Title: SignBean.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2013-5-14
* @version 1.0
*/
package cn.adwalker.ad.bean;

import org.apache.log4j.Logger;

import cn.adwalker.ad.picker.util.NumberUtil;
import cn.adwalker.ad.util.AppConstant;
import cn.adwalker.ad.util.ConfigUtil;

/**
 */
public class PickerConfig{
	public static final Logger logger = Logger.getLogger(PickerConfig.class);
	public static PickerConfig config;
	public static PickerConfig getInstance(){
	   if(config==null || ConfigUtil.isChange){
		   config = initConfig();
		   ConfigUtil.isChange=false;
	   }	
	   return config;
	}
	public synchronized static PickerConfig initConfig(){
		String signStr = ConfigUtil.getString("sign.bean");
		logger.info("init:"+signStr);
		if(null!=signStr && signStr.split(",").length>=4){
			String[] bean = signStr.split(",");
			Integer signStatus = NumberUtil.getInteger(bean[0], 2);//签到次数
			Integer first = NumberUtil.getInteger(bean[1], 7); 
			Integer second = NumberUtil.getInteger(bean[2], 2);
			Integer third = NumberUtil.getInteger(bean[3], 1);

			return new PickerConfig(signStatus, first, second, third);
		}
		return new PickerConfig(2, 7, 2, 1);
	}
	
	
	private int sign_status;//0：关闭签到，1：一次签到，2：二次签到
	private int sign_first;
	private int sign_second; 
	private int sign_third;
	
	
	public PickerConfig(){}
	public PickerConfig(int signStatus, int signFirst, int signSecond,int signThird) {
		super();
		sign_status = signStatus;
		sign_first = signFirst;
		sign_second = signSecond;
		sign_third = signThird;
	}
	public int getSign_status() {
		return sign_status;
	}
	public void setSign_status(int sign_status) {
		this.sign_status = sign_status;
	}
	public int getSign_first() {
		return sign_first;
	}
	public void setSign_first(int sign_first) {
		this.sign_first = sign_first;
	}
	public int getSign_second() {
		return sign_second;
	}
	public void setSign_second(int sign_second) {
		this.sign_second = sign_second;
	}
	public int getSign_third() {
		return sign_third;
	}
	public void setSign_third(int sign_third) {
		this.sign_third = sign_third;
	}
	public Integer getScale(Integer signNum,Integer sdkFlag){
		if(null!=signNum && sdkFlag!=1){
			if(getSign_status()==AppConstant.SCORE_SIGN_STATUS_CLOSED){
				return  AppConstant.SCORE_SIGN_STATUS_CLOSED_SCALE;
			}else{
				if(signNum==AppConstant.SIGN_NUM_FIRST){
					return getSign_first();  
				}else if(signNum==AppConstant.SIGN_NUM_SECOND){
					return getSign_second();
				}else if(signNum==AppConstant.SIGN_NUM_THIRD){
					return getSign_third();
				}
			}
		}
		return   AppConstant.SCORE_SIGN_STATUS_CLOSED_SCALE;//AppConstant.SCORE_SIGN_STATUS_CLOSED_SCALE;
	}
	
	
	
	
}
