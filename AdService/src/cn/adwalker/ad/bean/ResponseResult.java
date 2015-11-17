/**
* <p>Title: ResponseResult.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2012-12-25
* @version 1.0
*/
package cn.adwalker.ad.bean;

/**
 * <p>Title: ResponseResult</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2012-12-25
 */
public class ResponseResult {
 
  private String message;//返回消息
  private String success;//是否成功
  
  public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getSuccess() {
		return success;
	}
	public void setSuccess(String success) {
		this.success = success;
	}
}
