package cn.adwalker.ad.control.exception;

/**
 * 数据异常
 * 
 * @version 1.0
 * @since 2010-03-17
 * @author liuchen
 */
public class DataException extends Exception {
	private static final long serialVersionUID = 1L;

	public DataException() {
		super();
	}
	public DataException(String msg) {
		super(msg);
	}
}
