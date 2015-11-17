package cn.adwalker.ad.control.util;

public enum ReturnError {
	
	PARAM_INVALID(1001, "传入参数不合法!");
	
	private Integer code;
	private String desc;
	
	private ReturnError(Integer code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}
