package cn.adwalker.ad.cache.element;

import java.io.Serializable;

/**
 * 
* <p>Title: Wall</p>
* <p>Description:广告墙抽象类</p>
* <p>Company: adwalker</p> 
* @author    cuidd
* @date       2014年10月17日
 */
public abstract class Wall implements Serializable {

	/** @Fields serialVersionUID : TODO*/
	private static final long serialVersionUID = 1251981099670876876L;
	
	
	/**
	 * 响应分类--下载类，还是注册类
	 */
	 protected Integer response_category;
	 
	 /**
	  * 实际响应类型
	  */
	 protected Integer response_type;
	 
	 /**
	  * 排序权重
	  */
	 protected Integer weight;
	 
	 protected long placment_id;// 活动id


	public Integer getResponse_category() {
		return response_category;
	}


	public void setResponse_category(Integer response_category) {
		this.response_category = response_category;
	}


	public Integer getResponse_type() {
		return response_type;
	}


	public void setResponse_type(Integer response_type) {
		this.response_type = response_type;
	}


	public Integer getWeight() {
		return weight;
	}


	public void setWeight(Integer weight) {
		this.weight = weight;
	}


	public long getPlacment_id() {
		return placment_id;
	}


	public void setPlacment_id(long placment_id) {
		this.placment_id = placment_id;
	}
}
