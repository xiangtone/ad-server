package cn.adwalker.ad.beans;

import java.util.ArrayList;
import java.util.List;

import cn.adwalker.ad.bean.Data;

/**
 * 
* <p>Title: PortalData</p>
* <p>Description:TODO</p>
* <p>Company: adwalker</p> 
* @author    cuidd
* @date       2014年12月5日
 */
public class PortalData extends Data{
	private static final long serialVersionUID = 5565773459939577846L;
	private String code;
	private List<PortalAdInfo> list = new ArrayList<PortalAdInfo>();
	private Page page=new Page(0, 0);
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public List<PortalAdInfo> getList() {
		return list;
	}
	public void setList(List<PortalAdInfo> list) {
		this.list = list;
	}
}
