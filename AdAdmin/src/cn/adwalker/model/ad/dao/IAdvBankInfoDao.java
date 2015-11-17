package cn.adwalker.model.ad.dao;

import cn.adwalker.model.ad.domain.AdvBankInfo;
import cn.adwalker.ad.admin.ad.form.AdvBankInfoform;
import cn.adwalker.core.repository.IBaseDao;

/**
 * 
 * <p>
 * Title: IAdDao
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-4-9
 */
public interface IAdvBankInfoDao extends IBaseDao<AdvBankInfo> {

	/**
	 * 查看广告主财务信息
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
	public AdvBankInfo findAdvBankInfo(Long advId) throws Exception;
	
	
	/**
	 * 插入广告主财务信息
	 * 
	 * @param advertiser
	 * @return
	 * @throws Exception
	 */
	public void insertbankInfo(AdvBankInfoform advBankInfoVo) throws Exception;
	
	
	/**
	 * 更新广告主财务信息
	 * <p>
	 * Title: updatesBankInfoService
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param advBankInfoVo
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-4-2
	 * @return int
	 * @version 1.0
	 */
	public void updatesBankInfoService(AdvBankInfoform advBankInfoVo)
			throws Exception;


}
