/**
 * <p>Title: CampaginPackage.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author www.adwalker.cn
 * @date 2013-4-11
 * @version 1.0
 */
package cn.adwalker.ad.dao.domain;

import java.util.Date;

import cn.adwalker.ad.bean.Data;

/**
 * <p>
 * Title: CampaginPackage
 * </p>
 * <p>
 * Description:TODO   
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author www.adwalker.cn
 * @date 2013-4-11
 */
public class PlacementPackage extends Data {
	/** @Fields serialVersionUID : TODO */
	private static final long serialVersionUID = -5982018152498995199L;
	private String res_url;
	private String file_name;
	private Double file_size;
	private String package_name;
	private String version_code;
	private Date update_time;
	
	private String appstore_id;
	
	public String getRes_url() {
		return res_url;
	}
	public void setRes_url(String res_url) {
		this.res_url = res_url;
	}
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public Double getFile_size() {
		return file_size;
	}
	public void setFile_size(Double file_size) {
		this.file_size = file_size;
	}
	public String getPackage_name() {
		return package_name;
	}
	public void setPackage_name(String package_name) {
		this.package_name = package_name;
	}
	public String getVersion_code() {
		return version_code;
	}
	public void setVersion_code(String version_code) {
		this.version_code = version_code;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	public String getAppstore_id() {
		return appstore_id;
	}
	public void setAppstore_id(String appstore_id) {
		this.appstore_id = appstore_id;
	}
}
