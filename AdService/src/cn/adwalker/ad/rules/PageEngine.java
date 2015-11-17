package cn.adwalker.ad.rules;

import java.util.List;

import cn.adwalker.ad.cache.element.Advertise;
import cn.adwalker.ad.picker.util.NumberUtil;
import cn.adwalker.ad.util.AppConstant;
import cn.adwalker.ad.vo.AdWallPage;

/**
 * @author www.adwalker.cn
 */
public class PageEngine {
    private List<Advertise> list;
	private Integer pageNo=0; //页码 第几页
	private Integer pageSize=0; //页大小
	private Integer totalSize=0; //总条数
	private Integer totalPage=-1; //页数
	private boolean isSingle;
	
	
	public boolean isSingle() {
		return isSingle;
	}
	public void setSingle(boolean isSingle) {
		this.isSingle = isSingle;
	}
	public List<Advertise> getList() {
		return list;
	}
	public void setList(List<Advertise> list) {
		this.list = list;
	}
	public AdWallPage getWallPage(){
		return  new AdWallPage(totalPage,totalSize);
	}
	public static  PageEngine createPage(List<Advertise> list,Integer pageNo,Integer pageSize,String pageType){
		PageEngine page = new PageEngine();
		page.init(list, NumberUtil.getInt(pageNo, 0),NumberUtil.getInt(pageSize, 10),pageType);
		return page;
	}
	
	private void init(List<Advertise> list,Integer pageNo,Integer pageSize,String pageType){
		this.list=list;
		this.pageNo=pageNo-1<0?0:pageNo-1; //sdk端传过来第一页为1.
		this.pageSize=pageSize;
		this.totalSize=list.size();
		isSingle = pageType.equals(AppConstant.PAGE_WALLTYPE_PUSH)|| pageType.equals(AppConstant.PAGE_WALLTYPE_PLAQUE)|| pageType.equals(AppConstant.PAGE_WALLTYPE_BANNER);
		if(this.pageSize!=0){
			this.totalPage=(this.totalSize%this.pageSize)==0? (this.totalSize/this.pageSize): (this.totalSize/this.pageSize)+1;
		}
	}
	public List<Advertise> getPageList(){
		int begin = Math.min(pageSize*pageNo,totalSize);
		int end = Math.min(begin+pageSize, totalSize);
		if(list!=null){			
			return list.subList(begin, end);
		}
		return null;
	}
	
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
}
