package cn.adwalker;

import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

/**
 * <p>Title: DiffMap.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author hadoop
 * @date 2013-12-2
 * @version 1.0
 */

/**
 * <p>
 * Title: DiffMap
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-12-2
 */
public class TestDiffMap extends TestCase {

	public void testA() {
		Map<String, String> map1 = null;
		Map<String, Object> map2 = null;
		Map<String, String> result = null;
		DiffMap diff = new DiffMap();
		result = diff.diffMap(map1, map2);
		assertNotNull(result);// 结果不为空
		assertEquals(result.size(), 0);// 长度是0;
	}

	public void testB() {
		Map<String, String> map1 = null;
		Map<String, Object> map2 = new HashMap<String, Object>();
		for (int i = 0; i < 100; i++) {
			map2.put("key" + i, "value" + i);
		}
		Map<String, String> result = null;
		DiffMap diff = new DiffMap();
		result = diff.diffMap(map1, map2);
		assertNotNull(result);// 结果不为空
		assertEquals(result.size(), 0);// 长度是0;
	}

	public void testC() {
		Map<String, String> map1 = new HashMap<String, String>();
		Map<String, Object> map2 = null;
		for (int i = 0; i < 100; i++) {
			map1.put("key" + i, "value" + i);
		}
		Map<String, String> result = null;
		DiffMap diff = new DiffMap();
		result = diff.diffMap(map1, map2);
		assertNotNull(result);// 结果不为空
		assertEquals(result.size(), 0);// 长度是0;
	}

	public void testD() {

		Map<String, String> map1 = new HashMap<String, String>();
		Map<String, Object> map2 = new HashMap<String, Object>();
		for (int i = 0; i < 100; i++) {
			map1.put("key" + i, "value" + i);
		}
		for (int i = 50; i < 100; i++) {
			map2.put("key" + i, "value" + i);
		}
		Map<String, String> result = null;
		DiffMap diff = new DiffMap();
		result = diff.diffMap(map1, map2);
		assertNotNull(result);// 结果不为空
		assertEquals(result.size(), 50);// 长度是50;

	}

	public void testE() {
		Map<String, String> map1 = new HashMap<String, String>();
		Map<String, Object> map2 = new HashMap<String, Object>();
		for (int i = 0; i < 100; i++) {
			map1.put("key" + i, "value" + i);
		}
		for (int i = 50; i < 100; i++) {
			String arr[] = new String[10];
			for (int j = i; j < i + 10; j++) {
				arr[j]="value" + j;
			}
			map2.put("key" + i, arr);
		}
		Map<String, String> result = null;
		DiffMap diff = new DiffMap();
		result = diff.diffMap(map1, map2);
		assertNotNull(result);// 结果不为空
		assertEquals(result.size(), 50);// 长度是50;
	}
	
	
	public void testF() {
		Map<String, String> map1 = new HashMap<String, String>();
		Map<String, Object> map2 = new HashMap<String, Object>();
		for (int i = 0; i < 100; i++) {
			map1.put("key" + i, "value" + i);
		}
		for (int i = 50; i < 100; i++) {
			String arr[] = new String[10];
			for (int j = i; j < i + 10; j++) {
				arr[j]="value" + j;
			}
			map2.put("key" + i, arr);
		}
		
		for (int i = 0; i < 10; i++) {
			map1.put("key" + i, "value" + i);
		}
		
		Map<String, String> result = null;
		DiffMap diff = new DiffMap();
		result = diff.diffMap(map1, map2);
		assertNotNull(result);// 结果不为空
		assertEquals(result.size(), 60);// 长度是60;
	}


}
