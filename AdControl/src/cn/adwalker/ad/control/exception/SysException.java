package cn.adwalker.ad.control.exception;

/**
 * 系统异常，不需要处理
 * 
 * @version 1.0
 * @since 2010-03-17
 * @author liuchen
 */
public class SysException extends Exception {
	private static final long serialVersionUID = 1L;

	public SysException(String msg) {
		super(msg);
	}

	public SysException() {
		super();
	}

}
