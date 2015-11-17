package cn.adwalker.ad.control.exception;

/**
 * 应用程序异常
 * 
 * @author liuchen
 * @version 1.0
 * @since 2010-03-17
 */
public class AppException extends Exception {
	private static final long serialVersionUID = 1L;

	public AppException(String msg) {
		super(msg);
	}

	public AppException() {
		super();
	}

}
