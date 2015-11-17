/**
 * <p>Title: App.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author kingdom
 * @date 2013-7-23
 * @version 1.0
 */
package cn.adwalker.ad.api.app.form;

/**
 * <p>
 * Title: App
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-7-23
 */
public class App {
	private String id;// 主键
	private String appkey;// 开发者密钥
	private String os;// 操作系统
	private String dev_email;// 开发者id
	private String name;// 应用名称
	private String keyword;// 关键字
	private String long_desc;// 长描述
	private String project_url;// 应用上传路径url
	private String resource_size;// 资源大小
	private String package_name;// 资源包名称
	private String version_name;// 版本名称
	private String version_code;// 版本号
	private String del;// 逻辑删除标识(0:未删除 1:删除)
	private String release_time;// 上线时间
	private String update_time;// sysdate
	private String create_time;// sysdate
	private String down_time;//
	private String category_id;
	private String page_type[];// (多个type_id拼接)
	private String currency_name;// 如：积分，金币--汇率单位
	private Integer currency_value;// 10的整数倍--汇率值
	private String callback;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAppkey() {
		return appkey;
	}

	public void setAppkey(String appkey) {
		this.appkey = appkey;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getDev_email() {
		return dev_email;
	}

	public void setDev_email(String dev_email) {
		this.dev_email = dev_email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getLong_desc() {
		return long_desc;
	}

	public void setLong_desc(String long_desc) {
		this.long_desc = long_desc;
	}

	public String getProject_url() {
		return project_url;
	}

	public void setProject_url(String project_url) {
		this.project_url = project_url;
	}

	public String getResource_size() {
		return resource_size;
	}

	public void setResource_size(String resource_size) {
		this.resource_size = resource_size;
	}

	public String getPackage_name() {
		return package_name;
	}

	public void setPackage_name(String package_name) {
		this.package_name = package_name;
	}

	public String getVersion_name() {
		return version_name;
	}

	public void setVersion_name(String version_name) {
		this.version_name = version_name;
	}

	public String getVersion_code() {
		return version_code;
	}

	public void setVersion_code(String version_code) {
		this.version_code = version_code;
	}

	public String getDel() {
		return del;
	}

	public void setDel(String del) {
		this.del = del;
	}

	public String getRelease_time() {
		return release_time;
	}

	public void setRelease_time(String release_time) {
		this.release_time = release_time;
	}

	public String getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public String getDown_time() {
		return down_time;
	}

	public void setDown_time(String down_time) {
		this.down_time = down_time;
	}

	public String getCategory_id() {
		return category_id;
	}

	public void setCategory_id(String category_id) {
		this.category_id = category_id;
	}

	public String[] getPage_type() {
		return page_type;
	}

	public void setPage_type(String[] page_type) {
		this.page_type = page_type;
	}

	public String getCurrency_name() {
		return currency_name;
	}

	public void setCurrency_name(String currency_name) {
		this.currency_name = currency_name;
	}

	public Integer getCurrency_value() {
		return currency_value;
	}

	public void setCurrency_value(Integer currency_value) {
		this.currency_value = currency_value;
	}

	public String getCallback() {
		return callback;
	}

	public void setCallback(String callback) {
		this.callback = callback;
	}
}
