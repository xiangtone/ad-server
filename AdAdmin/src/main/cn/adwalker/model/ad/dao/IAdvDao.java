/*
 * IAdvDao.java
 *
 * Copyright 2011 1000CHI Software Technology Co.,Ltd
 * All rights reserved.
 * 
 * Created on 2011-11-30
 */
package cn.adwalker.model.ad.dao;

import cn.adwalker.core.repository.IBaseDao;
import cn.adwalker.model.ad.domain.Advertiser;

/**
 * 
 * <p>
 * Title: IAdvDao
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-4-2
 */
public interface IAdvDao extends IBaseDao<Advertiser> {

	/**
	 * <p>
	 * Title: updateStatus
	 * </p>
	 * <p>
	 * Description:修改状态
	 * </p>
	 * 
	 * @param advertiserId
	 * @param status
	 * @author lichuang
	 * @date 2013-4-15
	 * @return void
	 * @version 1.0
	 */

	public void updateStatus(Long advertiserId, Integer status);

	/**
	 * 修改广告主密码
	 * 
	 * @param password
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int updatePassword(String password, Long id) throws Exception;

	/**
	 * 根据id查找广告注
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Advertiser findById(Long id) throws Exception;


	/**
	 * 查看广告主信息
	 * <p>
	 * Title: findAdvInformation
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param advId
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-4-1
	 * @return AdvInfoVo
	 * @version 1.0
	 */
	public Advertiser findAdvInfo(Long advId) throws Exception;

	/**
	 * 更新广告主
	 * <p>
	 * Title: updateAdvService
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param registadvvo
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-4-2
	 * @return id
	 * @version 1.0
	 */
	public long update(Advertiser entity) throws Exception;

}
