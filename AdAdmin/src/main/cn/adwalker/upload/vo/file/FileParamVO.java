package cn.adwalker.upload.vo.file;

import java.io.IOException;

import cn.adwalker.core.util.JacksonMapper;
import cn.adwalker.upload.vo.ParamVO;

public class FileParamVO extends ParamVO {

	public FileParamVO() {

	}

	public FileParamVO(ParamVO vo) {
		super.setIc(vo.getIc());
		super.setPa(vo.getPa());
		super.setPo(vo.getPo());
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
