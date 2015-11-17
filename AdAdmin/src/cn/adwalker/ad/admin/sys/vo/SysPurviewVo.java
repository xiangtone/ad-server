/**
 * <p>Title: SysPurviewVo.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author kingdom
 * @date 2013-6-18
 * @version 1.0
 */
package cn.adwalker.ad.admin.sys.vo;

import java.util.List;

/**
 * <p>
 * Title: SysPurviewVo
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-6-18
 */
public class SysPurviewVo {

	private Long id;
	private String text;
	private List<SysPurviewVo> item;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<SysPurviewVo> getItem() {
		return item;
	}

	public void setItem(List<SysPurviewVo> item) {
		this.item = item;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
