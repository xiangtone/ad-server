package cn.adwalker.ad.web.developer.form;

public class UpdateDevInfoForm {

	/** 移动电话 */
	private String mobilePhone;

	/** QQ */
	private String qq;

	/** 公司名 */
	private String companyName;

	/** 真实姓名 */
	private String realName;

	/** 通信地址 */
	private String mailingAddress;

	/** 邮编 */
	private String postCode;

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getMailingAddress() {
		return mailingAddress;
	}

	public void setMailingAddress(String mailingAddress) {
		this.mailingAddress = mailingAddress;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
}
