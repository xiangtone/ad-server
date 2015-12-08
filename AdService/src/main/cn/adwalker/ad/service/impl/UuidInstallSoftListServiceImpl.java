package cn.adwalker.ad.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.adwalker.ad.bean.user.UuidInstallSoftList;
import cn.adwalker.ad.exception.AdwalkerException;
import cn.adwalker.ad.exception.ExceptionCode;
import cn.adwalker.ad.service.IUuidInstallSoftListService;
import cn.adwalker.ad.util.PublicUtil;
import cn.adwalker.core.utils.JacksonMapper;

/**
 * 
* <p>Title: UuidInstallSoftListServiceImpl</p>
* <p>Description:用户软件安装列表服务实现类</p>
* <p>Company: adwalker</p> 
* @author    cuidd
* @date       2014年10月10日
 */
@Service("uuidInstallSoftListService")
public class UuidInstallSoftListServiceImpl implements IUuidInstallSoftListService {

	private static final Logger log = LoggerFactory
			.getLogger(UuidInstallSoftListServiceImpl.class);

	/**
	 * (non-Javadoc)
	* <p>Title: softListToDB</p>
	* <p>Description:json解析，并把相关数据写入日志</p>
	* @param uuid
	* @param softList
	* @throws AdwalkerException
	* @see cn.adwalker.ad.service.IUuidInstallSoftListService#softListToDB(java.lang.String, java.lang.String)
	 */
	@Override
	public void softListToDB(String uuid,String softList,String appkey, String sdk_version) throws AdwalkerException  {
		/*
		 * 1、解析json。
		 * 2、写入日志数据。
		 */
		try {
			List<UuidInstallSoftList> list =JacksonMapper
					.jsonToList(softList, UuidInstallSoftList.class);
			UuidInstallSoftList uuidsoftlist= new UuidInstallSoftList();
			if (list!=null&&list.size()>0) {
				for(int i=0;i<list.size();i++){
					UuidInstallSoftList SoftList = list.get(i);
					 uuidsoftlist.setUuid(uuid);
					 uuidsoftlist.setPackageName(SoftList.getPackageName());
					 uuidsoftlist.setAppName(SoftList.getAppName());
					 uuidsoftlist.setAppkey(appkey);
					 uuidsoftlist.setSdk_version(sdk_version);
					 //日志输出终端用户手机应用列表
					 uuidsoftlist.softListlogInfo();
				 }
			}
			
		} catch (Exception e) {
			log.error("vo转换json错误,String:", softList);
			log.error("vo转换json错误,Exception:", e);
			throw new AdwalkerException(
					PublicUtil.installErrorJVO(ExceptionCode.SERVER_ERROR));
		}

	}
	
}
