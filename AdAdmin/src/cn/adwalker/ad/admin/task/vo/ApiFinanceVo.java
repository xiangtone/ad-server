/**
 * <p>Title: ApiFinanceVo.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author kingdom
 * @date 2013-9-2
 * @version 1.0
 */
package cn.adwalker.ad.admin.task.vo;

import java.io.Serializable;

/**
 * <p>
 * Title: ApiFinanceVo
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-9-2
 */
public class ApiFinanceVo implements Serializable {

	/** @Fields serialVersionUID : TODO */
	private static final long serialVersionUID = -75767692019619035L;
	private Long dev_id;
	public Long getDev_id() {
		return dev_id;
	}
	public void setDev_id(Long dev_id) {
		this.dev_id = dev_id;
	}
}
