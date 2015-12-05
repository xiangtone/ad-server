/**
 * <p>Title: TestResource.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author kingdom
 * @date 2013-5-28
 * @version 1.0
 */
package cn.adwalker;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import cn.adwalker.model.sys.dao.ISysResourceDao;

/**
 * <p>
 * Title: TestResource
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-5-28
 */
public class TestResource {
	public static void main(String[] args) {
		ApplicationContext context = new FileSystemXmlApplicationContext(
				new String[] { "WebRoot/WEB-INF/spring/applicationContext.xml" });

		File f = new File("D://adwalker/AdPlatform/src/com");

		tree(f,context);

	}

	// 显示目录的方法

	public static void tree(File f,ApplicationContext context) {

		// 判断传入对象是否为一个文件夹对象

		if (!f.isDirectory()) {
			System.out.println("你输入的不是一个文件夹，请检查路径是否有误！！");
		} else {
			File[] t = f.listFiles();
			for (int i = 0; i < t.length; i++) {
				// 判断文件列表中的对象是否为文件夹对象，如果是则执行tree递归，直到把此文件夹中所有文件输出为止
				if (t[i].isDirectory()) {
					tree(t[i],context);
				} else {
					getHaha(t[i],context);
				}

			}

		}

	}

	public static void getHaha(File file,ApplicationContext context) {

		try {
			List<String> list = FileUtils.readLines(file);
			if (list != null && list.size() > 0) {
				for (String s : list) {
					if (!StringUtils.isEmpty(s)) {
						s = s.trim();
						if (s.startsWith("@RequestMapping")) {
							ISysResourceDao dao=(ISysResourceDao)	context.getBean("resourceManageDao");
//							System.out.println("urlMap.put("
//									+ s.substring(16, s.length()) + ", value)");
							try {
								System.out.println(s.substring(17, s.length()-2));
								dao.saveOrUpdate(s.substring(17, s.length()-2));
							} catch (Exception e) {
								e.printStackTrace();
							}

						}
					}
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
