/**
* <p>Title: ResultVo.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author kingdom
* @date 2013-4-30
* @version 1.0
*/
package cn.adwalker.core.vo;

/**
 * <p>Title: ResultVo</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    cuidd
 * @date       2013-4-30
 */
public class ResultErrorVo extends ResultVo {
	/**
	 * 
	* <p>Title: </p>
	* <p>Description:默认的无参数的构造函数</p>
	 */
	public ResultErrorVo() {
		super();
	}

	/**
	* <p>Title: </p>
	* <p>Description:重写构造函数方便使用</p>
	* @param error
	*/
	public ResultErrorVo(String error) {
		super();
		this.status=-1;
		this.error = error;
	}
}
