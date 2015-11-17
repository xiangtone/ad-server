package cn.adwalker.ad.admin.common.service;


/**
 * 功能概述：<br>
 * 文件上传
 * 
 * @author zhaozengbin
 */

public interface IFileUploadService {
	/**
	 * 根据类型分别添加文件路径
	 * 
	 * @param path
	 * @param appId
	 * @param objType
	 * @return
	 */
	public boolean updatePath(String path, Long appId, Long opeId,
			Class<? extends Object> objType);
}
