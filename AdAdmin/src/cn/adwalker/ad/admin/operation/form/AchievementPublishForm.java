/**
 * <p>Title: AchievementPublishForm.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author hadoop
 * @date 2013-12-10
 * @version 1.0
 */
package cn.adwalker.ad.admin.operation.form;

/**
 * <p>
 * Title: AchievementPublishForm
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-12-10
 */
public class AchievementPublishForm {

	/**
	 * 主键
	 */
	private Long id;

	/**
	 * 发布月份
	 */
	private String month;

	/**
	 * 备注
	 */
	private String note;

	/**
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return month
	 */
	public String getMonth() {
		return month;
	}

	/**
	 * @param month the month to set
	 */
	
	public void setMonth(String month) {
		this.month = month;
	}

	/**
	 * @return note
	 */
	public String getNote() {
		return note;
	}

	/**
	 * @param note the note to set
	 */
	
	public void setNote(String note) {
		this.note = note;
	}
	
	

}
