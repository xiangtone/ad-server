package cn.adwalker.ad.control.service;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import cn.adwalker.ad.control.dao.DevloperDao;

/**
* <p>Title: DevMonthIncomeTaskService</p>
* <p>Description:每月5号月结算数据定时</p>
* <p>Company: adwalker</p> 
* @author    lichuang
* @date       2014年7月15日
 */
@Repository("devMonthIncomeTaskService")
public class DevMonthIncomeTaskService  {
	private static Logger log = Logger.getLogger(DevMonthIncomeTaskService.class);

	//开发者dao
	
	@Resource
	private DevloperDao userDeveloperDao;

	

	/**
	* <p>Title: tranceData</p>
	* <p>Description:TODO</p>
	* @throws Exception
	* @author cuidd
	* @date 2014年7月15日
	* @return void
	* @version 1.0
	 */
	public void tranceData() throws Exception {
		log.info("开始--DevMonthIncomeTaskService--tranceData");
		int num= this.userDeveloperDao.updateMonthFinance();
		int mun= this.userDeveloperDao.updateMonth();
		
		if (num==0) {
			System.out.println("--------错误" );
		}
		if (mun==0) {
			System.out.println("--------错误" );
		}
		log.info("结束--DevMonthIncomeTaskService--tranceData");
	}

	

	
	



}
