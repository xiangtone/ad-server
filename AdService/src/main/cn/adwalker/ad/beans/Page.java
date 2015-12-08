package cn.adwalker.ad.beans;

public class Page {

	public Integer pageNo;
	public Integer total;
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Page(){}
	public Page(Integer pageNo,Integer total){
		this.pageNo=pageNo;
		this.total=total;
	}
	public static Page createPage(Integer pageNo,Integer total){
		return new Page(pageNo,total);
	}
	
	
	
}
