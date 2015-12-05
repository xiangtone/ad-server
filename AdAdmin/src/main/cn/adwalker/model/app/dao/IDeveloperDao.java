package cn.adwalker.model.app.dao;

import java.util.List;
import java.util.Map;

import cn.adwalker.model.app.domain.Developer;
import cn.adwalker.model.app.domain.DeveloperEntity;
import cn.adwalker.model.finance.domain.UserDeveloper;
import cn.adwalker.ad.admin.app.vo.DeveloperVo;
import cn.adwalker.ad.admin.finance.form.FinanceAwardDetail;
import cn.adwalker.ad.admin.finance.form.FinancePunishDetail;
import cn.adwalker.core.repository.IBaseDao;

/**
 * 功能概述： <br>
 * 开发者管理接口
 * 
 * @author zhaozengbin
 */
public interface IDeveloperDao extends IBaseDao<DeveloperEntity> {
	/**
	 * 添加开发者
	 * 
	 * @param userDeveloper
	 * @throws Exception
	 */
	public long insert(Developer userDeveloper) throws Exception;

	/**
	 * 修改开发者
	 * 
	 * @param userDeveloper
	 * @throws Exception
	 */
	public Integer update(Developer userDeveloper) throws Exception;

	/**
	 * 修改开发者
	 * 
	 * @param userDeveloper
	 * @throws Exception
	 */
	public Integer saveOrUpdate(DeveloperEntity userDeveloper) throws Exception;

	/**
	 * 修改开发者
	 * 
	 * @param long1
	 * 
	 * @param userDeveloper
	 * @throws Exception
	 */
	public Integer updateStatus(Long dev_id, Integer status, Long long1)
			throws Exception;

	/**
	 * 查询开发者列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Developer> findAll() throws Exception;

	/**
	 * 根据主键获取开发者
	 * 
	 * @return
	 * @throws Exception
	 */
	public Developer getById(Long id) throws Exception;

	/**
	 * 根据主键获取开发者
	 * 
	 * @return
	 * @throws Exception
	 */
	public Developer getByIdAndName(Long id, String name) throws Exception;

	/**
	 * 用户是否存在
	 * 
	 * @param email
	 * @return
	 * @throws Exception
	 */
	public Developer exists(String email) throws Exception;

	/**
	 * 修改用户密码
	 * 
	 * @param userDeveloper
	 * @return
	 * @throws Exception
	 */
	public Integer updatePass(Developer userDeveloper) throws Exception;

	/**
	 * 查询用户应用数量
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<DeveloperVo> getAppCount() throws Exception;

	/**
	 * 根据开发者消耗积分收入更新相关字段CUR_SCORE,UNCONFIRMED_MONEY,CONFIRMED_MONEY
	 * 
	 * @param score
	 * @param forecastIncome
	 * @param manageMoney
	 * @param id
	 * @throws Exception
	 */
	public void updateoperateConfirmedMoney(Double manageMoney, Long id,
			Long managerId) throws Exception;

	public Double getTax(Double preTax);

	/**
	 * <p>
	 * Title: getByName
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param name
	 * @return
	 * @return UserDeveloper
	 */
	public List<Developer> getByEmail(String name);



	/**
	 * 开发者免税审核
	 * <p>
	 * Title: devTaxSetting
	 * </p>
	 * <p>
	 * Description:开发者免税审核
	 * </p>
	 * 
	 * @param dev_id
	 * @param tax_status
	 */
	public void updateDevTaxSetting(Long dev_id, Integer tax_status);

	/**
	 * <p>
	 * Title: updateFreeze
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param dev_id
	 * @author cuidd
	 * @date 2013-5-15
	 * @return void
	 * @version 1.0
	 * @throws Exception
	 */

	public Integer updateFreeze(Long dev_id) throws Exception;

	/**
	 * 批量修改开发者用户，当前积分、未确认佣金、已确认佣金
	 * 
	 * @param user
	 * @throws Exception
	 */
	public void batchUpdateMoney(Map<Long, UserDeveloper> user)
			throws Exception;

	/**
	 * 查找网站主Email
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public String findByEmail(Long id) throws Exception;

	/**
	 * 
	 * <p>
	 * Title: updateDev
	 * </p>
	 * <p>
	 * Description:修改开发者可提款收入
	 * </p>
	 * 
	 * @param form
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-7-2
	 * @return int
	 * @version 1.0
	 */
	public int updateDev(FinanceAwardDetail form) throws Exception;

	/**
	 * 批量修改开发者的累积提现和已确认佣金
	 * 
	 * @param user
	 * @throws Exception
	 */
	public void batchUpdateTotalMAndConfirmM(Map<Long, Developer> user)
			throws Exception;
	
	
	
	/**
	* <p>Title: updateDevPunish</p>
	* <p>Description:扣除款开发者</p>
	* @param from
	* @return
	* @throws Exception
	* @author lichuang
	* @date 2013-7-4
	* @return int
	* @version 1.0
	 */
	public int updateDevPunish(FinancePunishDetail from)throws Exception;
	
	
	/**
	 * 重置密码
	 * 
	 * @param email
	 * @param password
	 * @param type  1：广告主 2：开发者 
	 * @return
	 * @throws Exception
	 */
	public int resetPassword(String email,String password,int type) throws Exception;
	/**
	* <p>Title: updateMonthFinance</p>
	* <p>Description:每月5号月结算数据定时</p>
	* @return
	* @throws Exception
	* @author cuidd
	* @date 2014年7月1日
	* @return Long
	* @version 1.0
	 */
	public int updateMonthFinance()throws Exception;
	/**
	* <p>Title: updateMonth</p>
	* <p>Description:每月5号月结算数据定时</p>
	* @return
	* @throws Exception
	* @author cuidd
	* @date 2014年7月2日
	* @return int
	* @version 1.0
	 */
	public int updateMonth()throws Exception;

}
