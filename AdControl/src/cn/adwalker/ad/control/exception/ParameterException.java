package cn.adwalker.ad.control.exception;

/**
 * 参数异常
 * 
 * @version 1.0
 * @since 2010-03-17
 * @author liuchen
 */
public class ParameterException extends Exception {
	private static final long serialVersionUID = 1L;

	public ParameterException(String message) {
		super(message);
	}

	public ParameterException() {
		super();
	}
}
