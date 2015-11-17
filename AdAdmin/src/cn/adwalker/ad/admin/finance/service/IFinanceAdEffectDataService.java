/**
 * 
 */
package cn.adwalker.ad.admin.finance.service;

import java.util.HashMap;

import jxl.Sheet;

import cn.adwalker.model.operation.domain.FinanceAdEffectData;

/**
 * @author wjp
 * 
 */
public interface IFinanceAdEffectDataService {

	/**
	 * 插入
	 * 
	 * @param financeAdEffectData
	 * @return
	 * @throws Exception
	 */
	public int insert(FinanceAdEffectData financeAdEffectData) throws Exception;

	/**
	 * 修改
	 * 
	 * @param financeAdEffectData
	 * @return
	 * @throws Exception
	 */
	public int update(FinanceAdEffectData financeAdEffectData) throws Exception;

	
	/**
	 * 数据验证
	 * 
	 * @param sheet
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> checkDataWapActivate(Sheet sheet) throws Exception;

}
