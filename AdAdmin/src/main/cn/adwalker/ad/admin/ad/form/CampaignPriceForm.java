package cn.adwalker.ad.admin.ad.form;


/**
 * 活动主持VO
 * <p>
 * Title: CampaignVo   
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-4-7
 */
public class CampaignPriceForm {
	
	// 主键
	private Long id;
	
	/**
	 * 投放
	 */
	private Long placement_id;

	// 接入单价
	private Double price;
	
	private String os;
	/********************** getter\setter方法 ****************************/

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPlacement_id() {
		return placement_id;
	}

	public void setPlacement_id(Long placement_id) {
		this.placement_id = placement_id;
	}
	
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}
}
