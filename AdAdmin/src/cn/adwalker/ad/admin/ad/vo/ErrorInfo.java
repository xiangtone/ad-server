/**
* <p>Title: ErrorInfo.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author kingdom
* @date 2013-6-25
* @version 1.0
*/
package cn.adwalker.ad.admin.ad.vo;

/**
 * <p>Title: ErrorInfo</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    cuidd
 * @date       2013-6-25
 */
public class ErrorInfo {
	
	private String msg;
	
	public ErrorInfo(){
		super();
	}
	
	public ErrorInfo(String error){
		this.msg=error;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
