package cn.adwalker.ad.admin.operation.vo;

public class ConfirmationPackageVo {
	
	private Long id;
	
	private Long placement_id;
	
	private String campaign_name;
	
	private String file_name;
	
	private String code;
	
	private String remarks;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCampaign_name() {
		return campaign_name;
	}
	public void setCampaign_name(String campaign_name) {
		this.campaign_name = campaign_name;
	}
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getremarks() {
		return remarks;
	}
	public void setremarks(String remarks) {
		this.remarks = remarks;
	}
	public Long getPlacement_id() {
		return placement_id;
	}
	public void setPlacement_id(Long placement_id) {
		this.placement_id = placement_id;
	}
	
}
