package cn.adwalker.ad.dao;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * 
* <p>Title: TestUserAddScoreInfoDao</p>
* <p>Description:TODO</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-1-14
 */
public class TestUserAddScoreInfoDao {

	/**
	 * 单元测试
	* <p>Title: main</p>
	* <p>Description:TODO</p>
	* @param args
	* @author lichuang
	* @date 2013-1-14
	* @return void
	* @version 1.0
	* @throws
	 */

	public static void main(String[] args) {
		ApplicationContext context = new FileSystemXmlApplicationContext(
				new String[] { "WebRoot/WEB-INF/spring/applicationContext.xml" });
	}	

}
