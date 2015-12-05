/**
 * 
 */
package cn.adwalker.model.operation.dao;

import java.util.List;

import cn.adwalker.model.operation.domain.FinanceAdEffectData;

/**
 * @author wjp
 *
 */
public interface IAdEffectDataDao {

	/**
	 * 插入
	 * @param financeAdEffectData
	 * @return
	 * @throws Exception
	 */
	public int insert(FinanceAdEffectData financeAdEffectData) throws Exception;
	
	/**
	 * 批量插入
	 * @param financeAdEffectData
	 * @return
	 * @throws Exception
	 */
	public int batchInsert(List<FinanceAdEffectData> financeAdEffectData) throws Exception;
	
	/**
	 * 修改
	 * @param financeAdEffectData
	 * @return
	 * @throws Wxception
	 */
	public int update(FinanceAdEffectData financeAdEffectData) throws Exception;
	
	/**
	 * 根据条件查找数量
	 * @param con
	 * @return
	 * @throws Exception
	 */
	public int getCount(String con) throws Exception;
	
	/**
	 * 根据广告主id、广告id、日期
	 * @param adverId
	 * @param adId
	 * @param startDate
	 * @return
	 * @throws Exception
	 */
	public FinanceAdEffectData findOne(Long adverId,Long adId,String startDate) throws Exception;
	
	/**
	 * 根据统计日期和广告主id删除
	 * @param adverId
	 * @param startDate
	 * @return
	 * @throws Exception
	 */
	public int deleteByAdverIdAndStatDate(Long adverId,String startDate) throws Exception;
	
	/**
	 * 广告主id、广告id 是否匹配
	 * @param keySet
	 * @throws Exception
	 */
	public List<String> isMatch() throws Exception;
}
