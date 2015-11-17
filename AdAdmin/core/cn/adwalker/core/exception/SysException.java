package cn.adwalker.core.exception;

import java.util.Date;

import cn.adwalker.core.util.DateUtil;

/**
 * 
 * <p>
 * Title: EscoreException 
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-5-27
 */
public class SysException extends Exception {

	private static final long serialVersionUID = -3480534266751018920L;

	/**
	 * 无参EscoreException()构造器
	 */
	public SysException() {
		super();
	}

	/**
	 * 
	 * @param message
	 *            构造参数，接收一个字符串信息
	 */
	public SysException(String message) {
		super(DateUtil.formatDateTime(new Date()) + message);
	}
}