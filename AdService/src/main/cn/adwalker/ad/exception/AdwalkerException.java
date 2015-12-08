package cn.adwalker.ad.exception;


/**
 * 
 * @author cq
 * 
 */
public class AdwalkerException extends Exception {

	private static final long serialVersionUID = -3480534266751018920L;

	private ErrorInfo error;
	

	/**
	 * 无参QianchiException()构造器
	 */
	public AdwalkerException() {
		super();
	}

	/**
	 * 
	 * @param message
	 *            构造参数，接收一个字符串信息
	 */
	public AdwalkerException(String message) {
		super(message);
	}

	public AdwalkerException(ErrorInfo error) {
		super();
		this.error = error;
	}

	public ErrorInfo getError() {
		return error;
	}

}