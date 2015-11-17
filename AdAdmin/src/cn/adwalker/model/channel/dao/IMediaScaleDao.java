/*
 * IUserAdvertiserDao.java
 *
 * Copyright 2011 1000CHI Software Technology Co.,Ltd
 * All rights reserved.
 * 
 * Created on 2011-11-30
 */
package cn.adwalker.model.channel.dao;

import cn.adwalker.core.repository.IBaseDao;
import cn.adwalker.model.channel.domain.MediaScale;

/**
 * 
* <p>Title: IMediaScaleDao</p>
* <p>Description:TODO</p>
* <p>Company: adwalker</p> 
* @author    cuidd
* @date       2013-7-17
 */
public interface IMediaScaleDao extends IBaseDao<MediaScale> {

	/**
	* <p>Title: updateChannelRating</p>
	* <p>Description:TODO</p>
	* @param channelId
	* @param score
	* @author cuidd
	* @date 2013-7-17
	* @return void
	* @version 1.0
	 * @param campaign_id 
	 * @param media_id 
	 * @throws Exception 
	*/
	void updateChannelRating(Long channelId, Long campaign_id, Long media_id, Double score) throws Exception;
	
	

	/**
	 * 
	* <p>Title: getMediaScale</p>
	* <p>Description:TODO</p>
	* @param media_id
	* @param camplaign_id
	* @return
	* @author cuidd
	* @date 2013-8-5
	* @return Double
	* @version 1.0
	 * @throws Exception 
	 */
	public Double getMediaScale(Long media_id,Long camplaign_id) throws Exception;
	 
}
