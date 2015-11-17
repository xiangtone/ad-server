package cn.adwalker.IOSChannel.picker.util;

import java.util.HashMap;
import java.util.Map;

public class CmdBean {
	private String action;
	private String className;
	private Map<String, String> map = new HashMap<String, String>();

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}

}
