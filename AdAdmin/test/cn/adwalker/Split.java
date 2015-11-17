/**
 * <p>Title: Split.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author kingdom
 * @date 2013-6-2
 * @version 1.0
 */
package cn.adwalker;

/**
 * <p>
 * Title: Split
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-6-2
 */
public class Split {

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
	 * @date 2013-6-2
	 * @return void
	 * @version 1.0
	 */

	public static void main(String[] args) {
		String string = "fjfjfj|kfkfkfkf|w92929";
		String arr[] = string.split("\\|");
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}

		String aa = "2014-01-06 00:08:32 ";
		System.out.println(aa.substring(0, 13));

	}

}
