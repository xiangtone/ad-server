package cn.adwalker.ad.service;

import cn.adwalker.ad.exception.AdwalkerException;
/**
 * 
* <p>Title: IUuidInstallSoftListService</p>
* <p>Description:用户软件安装列表服务组件</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-1-5
 */
public interface IUuidInstallSoftListService {
	/**
	 * 终端用户安装的软件集合入库
	 * 
	 * @param softList
	 * @param sdk_version 
	 * @param appkey 
	 * @param softList2 
	 * @throws QianchiException
	 */
	public void softListToDB(String uuid,String softList, String appkey, String sdk_version) throws AdwalkerException;
	
}

	