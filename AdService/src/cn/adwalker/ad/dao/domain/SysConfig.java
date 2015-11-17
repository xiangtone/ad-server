/**
 * <p>Title: SysConfig.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author www.adwalker.cn
 * @date 2013-5-14
 * @version 1.0
 */
package cn.adwalker.ad.dao.domain;

import cn.adwalker.ad.bean.Data;

/**
 * <p>
 * Title: SysConfig
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author www.adwalker.cn
 * @date 2013-5-14
 */
public class SysConfig extends Data {

	/** @Fields serialVersionUID : TODO */
	private static final long serialVersionUID = -804942669073167806L;
	private Long id;
	private String config_type;
	private String config_value;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getConfig_type() {
		return config_type;
	}

	public void setConfig_type(String config_type) {
		this.config_type = config_type;
	}

	public String getConfig_value() {
		return config_value;
	}

	public void setConfig_value(String config_value) {
		this.config_value = config_value;
	}

}
