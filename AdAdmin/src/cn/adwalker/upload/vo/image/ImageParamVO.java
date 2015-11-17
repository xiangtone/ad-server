package cn.adwalker.upload.vo.image;

import java.io.IOException;

import cn.adwalker.core.util.JacksonMapper;
import cn.adwalker.upload.vo.ParamVO;

public class ImageParamVO extends ParamVO {

	private String cc;// 是否允许裁剪 0:不允许 1:允许
	private String zs;// 缩放尺寸

	public ImageParamVO() {

	}

	public ImageParamVO(ParamVO vo) {
		super.setIc(vo.getIc());
		super.setPa(vo.getPa());
		super.setPo(vo.getPo());
	}

	public String getCc() {
		return cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	public String getZs() {
		return zs;
	}

	public void setZs(String zs) {
		this.zs = zs;
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
