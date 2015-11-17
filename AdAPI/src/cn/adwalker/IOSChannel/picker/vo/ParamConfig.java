package cn.adwalker.IOSChannel.picker.vo;

import java.io.Serializable;
import java.util.Map;

public class ParamConfig implements Serializable {

	/** @Fields serialVersionUID : TODO*/
	private static final long serialVersionUID = 6583468162686485964L;
	
	/**
	 * 头部信息配置
	 */
	private Map<String,String> headers;
	
	/**
	 * 参数信息配置
	 */
	private Map<String,String> Params;
	
	
	public ParamConfig( Map<String, String> params) {
		super();
		Params = params;
	}

	public ParamConfig( Map<String, String> params,Map<String, String> headers) {
		super();
		this.headers = headers;
		Params = params;
	}

	public Map<String, String> getHeaders() {
		return headers;
	}

	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}

	public Map<String, String> getParams() {
		return Params;
	}

	public void setParams(Map<String, String> params) {
		Params = params;
	}
}
