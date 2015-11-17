/**
 * 
 */
package cn.adwalker.model.common.dao;

import java.util.List;

import cn.adwalker.model.common.domain.SysCategory;
import cn.adwalker.model.common.domain.ProvinceCitySort;

/**
 * 功能概述：<br>
 * 分类接口
 * 
 * @author guoyongxiang
 */
public interface ISysCategoryDao {

	/**
	 * 获取分类类别列表
	 * 
	 * @return
	 */
	public List<SysCategory> getType();

	/**
	 * 根据分类类别获取对应平台列表
	 * 
	 * @param type 类别
	 * @return
	 */
	public List<SysCategory> getPlatform(String type);
	/**
	 * 根据省份查找城市
	 * 
	 * @param id 类别
	 * @return
	 */
	public List<ProvinceCitySort> getprovinceCitySort(int id);

	/**
	 * 根据分类类别、平台、父级分类ID获取子分类列表
	 * 
	 * @param type 类别
	 * @param platform 平台
	 * @param parentId 父级分类（顶级分类为0，传null默认为顶级分类）
	 * @return
	 * @throws Exception 
	 */
	public List<SysCategory> getCategory(String type, String platform, Long parentId) throws Exception;

	
	public List<SysCategory> getCategoryNew(String type,Long parentId) throws Exception;
	
	public ProvinceCitySort getCityId(Long id);

	


	/**
	 * 根据分类id查询该分类信息
	 * 
	 * @param id 分类ID
	 * @return
	 */
	public SysCategory getCategoryById(Long id);

	
	

}