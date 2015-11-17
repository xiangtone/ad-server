package cn.adwalker.model.operation.dao;

import cn.adwalker.core.repository.IBaseDao;
import cn.adwalker.model.operation.domain.ActivateNumDetailIos;

/**
 * <p>
 * Title: IPackageActivateDetailDao
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-5-23
 */
public interface IActivateIosDetailDao extends IBaseDao<ActivateNumDetailIos> {

	/**
	 * <p>
	 * Title: saveConfIos
	 * </p>
	 * <p>
	 * Description:保存IOS日确认数分数
	 * </p>
	 * 
	 * @param id
	 * @param confirm_num
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-11-8
	 * @return int
	 * @version 1.0
	 * @param comfirm_cost
	 */
	public int saveConfIos(Long[] id, Integer[] confirm_num,
			Integer[] comfirm_cost) throws Exception;

	/**
	 * <p>
	 * Title: saveConfirm
	 * </p>
	 * <p>
	 * Description:保存渠道的效果数
	 * </p>
	 * 
	 * @param id
	 * @param confirm_num
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-5-27
	 * @return void
	 * @version 1.0
	 */
	public int saveConfirm(Long id, Integer confirm_num) throws Exception;

}
