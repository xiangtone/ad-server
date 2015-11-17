package cn.adwalker.ad.picker.form;

import java.util.List;

import cn.adwalker.ad.cache.element.Advertise;
import cn.adwalker.ad.cache.element.DevApp;
import cn.adwalker.ad.rules.config.ISDKConfig;

public class AdForm {
	
	private List<Advertise> list;
	private Integer pageNo;
	private Integer pageSize;
	private String page_type;
	private ISDKConfig config;
	private DevApp app;
	private String version;
	private String image_type;
	private Integer quickly_task_config;
	private String quickly_task;

	public List<Advertise> getList() {
		return list;
	}

	public void setList(List<Advertise> list) {
		this.list = list;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getPage_type() {
		return page_type;
	}

	public void setPage_type(String page_type) {
		this.page_type = page_type;
	}

	public ISDKConfig getConfig() {
		return config;
	}

	public void setConfig(ISDKConfig config) {
		this.config = config;
	}

	public DevApp getApp() {
		return app;
	}

	public void setApp(DevApp app) {
		this.app = app;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getImage_type() {
		return image_type;
	}

	public void setImage_type(String image_type) {
		this.image_type = image_type;
	}

	public Integer getQuickly_task_config() {
		return quickly_task_config;
	}

	public void setQuickly_task_config(Integer quickly_task_config) {
		this.quickly_task_config = quickly_task_config;
	}

	public String getQuickly_task() {
		return quickly_task;
	}

	public void setQuickly_task(String quickly_task) {
		this.quickly_task = quickly_task;
	}
}
