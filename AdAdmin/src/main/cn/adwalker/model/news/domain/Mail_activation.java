/**
 * <p>Title: Mail_activation.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author wanghl
 * @date 2012-11-26
 * @version 1.0
 */
package cn.adwalker.model.news.domain;

/**
 * <p>
 * Title: Mail_activation
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author wanghl
 * @date 2012-11-26
 */
public class Mail_activation {
	/** 主键编号 */
	private Long id;
	private Integer user_type;
	private String stat_date;
	private Integer num;
	private String mail;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getUser_type() {
		return user_type;
	}

	public void setUser_type(Integer user_type) {
		this.user_type = user_type;
	}

	public String getStat_date() {
		return stat_date;
	}

	public void setStat_date(String stat_date) {
		this.stat_date = stat_date;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

}
