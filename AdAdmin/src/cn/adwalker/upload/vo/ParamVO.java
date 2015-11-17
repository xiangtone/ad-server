package cn.adwalker.upload.vo;

import java.io.IOException;

import cn.adwalker.core.util.JacksonMapper;

public class ParamVO {

	private String po;// 文件名称策略 0:不变1:随机生成
	private String pa;// 上传的相对路径
	private String ic;// 是否覆盖 0:不覆盖 1:覆盖

	public ParamVO() {

	}

	public ParamVO(String po, String pa, String ic) {
		this.po = po;
		this.pa = pa;
		this.ic = ic;
	}

	public String getPo() {
		return po;
	}

	public void setPo(String po) {
		this.po = po;
	}

	public String getPa() {
		return pa;
	}

	public void setPa(String pa) {
		this.pa = pa;
	}

	public String getIc() {
		return ic;
	}

	public void setIc(String ic) {
		this.ic = ic;
	}

	public String toString() {
		String json = null;
		try {
			json = JacksonMapper.objectToJsonString(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return json;
	}

}
