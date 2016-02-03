package cn.adwalker.ad.web.developer.vo;

public class DevIteriorMailVo {

	/** ID */
	private Long id;
	/** 开发者id */
	private Long dev_id;
	/** 0-未读，1已读，2删除 */
	private Integer status;
	/** 站内信id */
	private Long interior_id;
	/** 标题 */
	private String title;
	/** 内容 */
	private String content;
	/** 总数 */
	private Long count;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDev_id() {
		return dev_id;
	}

	public void setDev_id(Long dev_id) {
		this.dev_id = dev_id;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getInterior_id() {
		return interior_id;
	}

	public void setInterior_id(Long interior_id) {
		this.interior_id = interior_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

}
