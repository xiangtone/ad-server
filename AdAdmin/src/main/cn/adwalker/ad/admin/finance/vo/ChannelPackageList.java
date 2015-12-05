/**
 * <p>Title: ChannelPackageList.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author hadoop
 * @date 2013-10-29
 * @version 1.0
 */
package cn.adwalker.ad.admin.finance.vo;

/**
 * <p>
 * Title: ChannelPackageList
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-10-29
 */
public class ChannelPackageList {

	/**
	 * 包id
	 */
	private String id;

	/**
	 * 编号
	 */
	private String code;
	
	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
