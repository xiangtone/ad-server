package cn.adwalker.ad.web.common.vo;

public class ResultSuccessVo extends ResultVo {
	/**
	 * <p>
	 * Title:
	 * </p>
	 * <p>
	 * Description:默认的无参数的构造函数
	 * </p>
	 */
	public ResultSuccessVo() {
		super();
		this.status = 1;

	}

	public ResultSuccessVo(Object data) {
		super();
		this.status = 1;
		this.data = data;

	}

}
