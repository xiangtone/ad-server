/**
 * <p>Title: TestLruMap.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author hadoop
 * @date 2014-1-3
 * @version 1.0
 */
package cn.adwalker;

import java.util.Iterator;

import org.apache.commons.collections.map.LRUMap;

/**
 * <p>
 * Title: TestLruMap
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2014-1-3
 */
public class TestLruMap {

	/**
	 * <p>
	 * Title: main
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param args
	 * @author cuidd
	 * @date 2014-1-3
	 * @return void
	 * @version 1.0
	 */

	public static void main(String[] args) {
		LRUMap map = new LRUMap(3);
		map.put("1", 1);
		map.put("2", 2);
	//	map.containsKey("1");
		map.put("3", 3);
		map.put("4", 4);
		map.put("5", 5);

		for (Iterator it = map.entrySet().iterator(); it.hasNext();) {
			System.out.println(it.next());
		}

	}

}
