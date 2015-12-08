package cn.adwalker.ad.picker.util;

import java.util.List;

public class PageCollection<T> {
    private List<T> list;
	private Integer pageNo=0; //页码 第几页
	private Integer pageSize=0; //页大小
	private Integer totalSize=0; //总条数
	public Integer totalPage=-1; //页数
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getTotalSize() {
		return totalSize;
	}
	public void setTotalSize(Integer totalSize) {
		this.totalSize = totalSize;
	}
	public static <T> PageCollection<T> createPage(List<T> list,Integer pageNo,Integer pageSize){
		PageCollection<T> page = new PageCollection<T>();
		page.init(list, pageNo, pageSize);
		return page;
	} 
	public void init(List<T> list,Integer pageNo,Integer pageSize){
		this.list=list;
		this.pageNo=pageNo;
		this.pageSize=pageSize;
		this.totalSize=list.size();
		if(pageSize!=0){
			this.totalPage=(this.totalSize%this.pageSize)==0? (this.totalSize/this.pageSize): (this.totalSize/this.pageSize)+1;
		}
	}
	public List<T> getPageList(){
		int begin = Math.min(pageSize*pageNo,totalSize);
		int end = Math.min(begin+pageSize, totalSize);
		if(list!=null){			
			return list.subList(begin, end);
		}
		return null;
	}
	
	
}
