package cn.adwalker.ad.bean;

/**
 * <p>
 * Title: SimpleBean
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @param <T>
 * @date 2013-11-8
 */
public class SimpleBean<T> {

	private T obj;

	public T getObj() {
		return obj;
	}

	public void setObj(T obj) {
		this.obj = obj;
	}

}
