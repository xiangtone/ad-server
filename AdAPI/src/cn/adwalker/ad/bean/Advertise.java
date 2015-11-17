/**
 * <p>Title: Advertise.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author www.adwalker.cn
 * @date 2013-4-11
 * @version 1.0
 */
package cn.adwalker.ad.bean;


/**
 * <p>
 * Title: Advertise
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author www.adwalker.cn
 * @date 2013-4-11
 */
public class Advertise extends Data {
	/** @Fields serialVersionUID : TODO*/
	private static final long serialVersionUID = 615732657232841220L;
	
	private	Long id;
	private Double blance_price;//
	private Integer confirm_type;
	
	//构造器
	public Advertise(){
	}

	public Double getBlance_price() {
		return blance_price;
	}

	public void setBlance_price(Double blance_price) {
		this.blance_price = blance_price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getConfirm_type() {
		return confirm_type;
	}

	public void setConfirm_type(Integer confirm_type) {
		this.confirm_type = confirm_type;
	}
	
	
}
