package cn.adwalker.ad.web.common.vo;

public class ResultErrorVo extends ResultVo {
	/**
	 * 
	 * <p>
	 * Title:
	 * </p>
	 * <p>
	 * Description:默认的无参数的构造函数
	 * </p>
	 */
	public ResultErrorVo() {
		super();
	}

	/**
	 * <p>
	 * Title:
	 * </p>
	 * <p>
	 * Description:重写构造函数方便使用
	 * </p>
	 * 
	 * @param error
	 */
	public ResultErrorVo(String error) {
		super();
		this.status = -1;
		this.error = error;
	}

	/**
	 * <p>
	 * Title:
	 * </p>
	 * <p>
	 * Description:重写构造函数方便使用
	 * </p>
	 * 
	 * @param error
	 */
	public ResultErrorVo(Integer code, String error) {
		super();
		this.status = code;
		this.error = error;
	}

}
