package cn.adwalker.ad.rules;

/**
* <p>Title: Test.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2013-1-8
* @version 1.0
*/



/**
 * <p>Title: Test</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2013-1-8
 */
public class Test {

	/**
	 * <p>Title: main</p>
	 * <p>Description:TODO</p>
	 * @param args
	 * @return void
	 * @throws
	 */
	public static void main(String[] args) {
		String str="SELECT  "
				+ "t.id,"
				+ "t.category_id,"
				+ "t.os,"
				+ "t.name,"
				+ "t.package_name,t."
				+ "version_code,"
				+ "t.placement,"
				+ "t.is_coordinate,"
				+ "t.scale,t.appkey,"
				+ "t.dev_id,"
				+ "t.status,"
				+ "t.del,"
				+ "a.response_url FROM t_application t LEFT JOIN t_cooperation_app_rel a ON t.id=a.app_id WHERE t.id=?";
		
		
	System.out.println(str);	
	
	}

}
