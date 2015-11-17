package cn.adwalker.ad.admin.sys.form;

/**
 * 
 * <p>
 * Title: UserAddForm
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-7-9
 */
public class RoleAddForm {

	/** 用户名 */
	private String name;

	/** 密码 */
	private String code;

	/** 真实姓名 */
	private String note;

	/** 电话号码 */
	private Integer order_num;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Integer getOrder_num() {
		return order_num;
	}

	public void setOrder_num(Integer order_num) {
		this.order_num = order_num;
	}

}