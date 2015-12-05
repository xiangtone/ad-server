package cn.adwalker.ad.admin.app.vo;

public class AppBlackAdVo {

	/** 主键 */
	private Long id;
	/** 黑名单 */
	private String black;
	/** 黑名单id */
	private String black_id;
	/** 应用名称 */
	private String name;
	/** 投放名称 */
	private String placement_name;
	/** 状态（0：待审核，2：通过，3：未通过，4：上线，5：下线，6：终止） */
	private Integer status;
	/** 操作系统 */
	private String os;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getBlack() {
		return black;
	}

	public void setBlack(String black) {
		this.black = black;
	}

	public String getPlacement_name() {
		return placement_name;
	}

	public void setPlacement_name(String placement_name) {
		this.placement_name = placement_name;
	}

	public String getBlack_id() {
		return black_id;
	}

	public void setBlack_id(String black_id) {
		this.black_id = black_id;
	}

	

}
