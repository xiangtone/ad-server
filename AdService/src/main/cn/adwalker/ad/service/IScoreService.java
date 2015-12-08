package cn.adwalker.ad.service;

import cn.adwalker.ad.exception.AdwalkerException;
import cn.adwalker.ad.vo.Score;


public interface IScoreService {

	public void updateConfig(String uuid, String devUserId, String appId,String os);


	public String findMediaUser(String uuid, String appId);
	
	/***
	* <p>Title: doDevScore</p>
	* <p>Description:TODO</p>
	* @param score
	* @param appId
	* @param channel
	* @param adId
	* @param uuid
	* @param page_Type
	* @param price
	* @param sdkversion
	* @param categoryid
	* @param pay_type
	* @throws AdwalkerException
	* @return void
	* @throws
	 */
	public void doDevScore(Double score, Long appId, String channel,
			Long adId, String uuid, String page_Type, Double price,
			String sdkversion, Integer categoryid, String pay_type,int signNum,String ip);


	/**
	* <p>Title: getScore</p>
	* <p>Description:TODO</p>
	* @param uuid
	* @return
	* @return Score
	* @throws
	*/
	public Score getScore(String uuid,long appId,String version)throws AdwalkerException;
	
}
