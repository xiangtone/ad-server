/**
 * <p>Title: CacheElement.java</p>
 * <p>Description:简单缓存对象</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author kingdom
 * @date 2013-7-5
 * @version 1.0
 */
package cn.adwalker.ad.bean;

import java.io.Serializable;
import java.util.Date;

import cn.adwalker.ad.util.DateUtil;

/**
 * <p>
 * Title: CacheElement
 * </p>
 * <p>
 * Description:TODO 
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-7-5
 */
public class CacheElement<T> implements Serializable {

	/** @Fields serialVersionUID : TODO */
	private static final long serialVersionUID = -3664811707030656103L;

	/**
	 * 设置缓存多少时间（分钟）
	 */
	public CacheElement(int minutes) {
		this.cahcheTime = minutes;
		this.distoryTime = DateUtil.addMinutes(new Date(), cahcheTime);
	}

	/**
	 * 缓存对象
	 */
	private T object;

	/**
	 * 缓存时间
	 */
	private Date distoryTime;

	/**
	 * 缓存生命周期
	 */
	private Integer cahcheTime;

	/**
	 * 
	 * <p>
	 * Title: set
	 * </p>
	 * <p>
	 * Description:设置对象
	 * </p>
	 * 
	 * @param obj
	 * @author cuidd
	 * @date 2013-7-5
	 * @return void
	 * @version 1.0
	 */
	public void setElement(T obj) {
		this.object = obj;
		distoryTime = DateUtil.addMinutes(new Date(), cahcheTime);
	}

	/**
	 * 
	 * <p>
	 * Title: get
	 * </p>
	 * <p>
	 * Description:获取对象
	 * </p>
	 * 
	 * @return
	 * @author cuidd
	 * @date 2013-7-5
	 * @return Object
	 * @version 1.0
	 */
	public T getElement() {
		if (DateUtil.compare(new Date(), distoryTime)) {
			object = null;
		}
		return object;

	}

	/**
	 * 
	 * <p>
	 * Title: clearElement
	 * </p>
	 * <p>
	 * Description:清空缓存
	 * </p>
	 * 
	 * @return
	 * @author cuidd
	 * @date 2013-7-5
	 * @return T
	 * @version 1.0
	 */
	public void clearElement() {
		object = null;
	}
}
