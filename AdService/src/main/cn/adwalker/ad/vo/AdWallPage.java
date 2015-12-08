package cn.adwalker.ad.vo;

public class AdWallPage {

	private Integer pageCount;// 共多少页
	private Integer resultSize;// 返回值数量
	public AdWallPage() {

	}
	public AdWallPage(Integer pageCount,  Integer resultSize) {
		///this.pageSize = pageSize;
		this.pageCount = pageCount;
		//this.pageNo = pageNo;
		this.resultSize = resultSize;
	}



	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}




	public Integer getResultSize() {
		return resultSize;
	}

	public void setResultSize(Integer resultSize) {
		this.resultSize = resultSize;
	}

}
