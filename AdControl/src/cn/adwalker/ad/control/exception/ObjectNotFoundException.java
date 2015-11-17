package cn.adwalker.ad.control.exception;

/**
 * 对象没有找到异常异常
 * 
 * @version 1.0
 * @since 2010-03-17
 * @author liuchen
 */
public class ObjectNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	public ObjectNotFoundException(String msg) {
		super(msg);
	}

}
