/**
* <p>Title: Type.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author wanghl
* @date 2013-1-16
* @version 1.0
*/
package cn.adwalker.model.ad.domain;

import cn.adwalker.core.repository.Entity;

/**
 * <p>Title: Type</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    wanghl
 * @date       2013-1-16
 */
public class Type implements Entity {
	
	
	
	/** @Fields serialVersionUID : TODO*/
	private static final long serialVersionUID = 6373251798388325076L;
	private Long id;
	private String name;
	private Integer status;
	private Long order_num;
	
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
	public Long getOrder_num() {
		return order_num;
	}
	public void setOrder_num(Long order_num) {
		this.order_num = order_num;
	}
	
}
