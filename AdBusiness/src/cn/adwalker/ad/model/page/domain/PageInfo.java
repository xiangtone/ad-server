package cn.adwalker.ad.model.page.domain;

public class PageInfo {
	/** 当前页 */
	private int currentPage;

	/** 访问地址前缀 */
	private String prefixUrl;

	/** 分隔符 */
	private String separator = "&nbsp;&nbsp;";

	/** 参数 */
	private String pagingParam;

	/** 页面显示数量 1 2 3 4 5 */
	private int pageCount = 5;

	/** 每页显示条数，默认20条 */
	private int pageSize= 20;

	/** 总数量 */
	private int recordCount;

	public int getTotalPage() {
		if (recordCount % pageSize == 0)
			return recordCount / pageSize;
		else
			return recordCount / pageSize + 1;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public String getPrefixUrl() {
		return prefixUrl;
	}

	public void setPrefixUrl(String prefixUrl) {
		this.prefixUrl = prefixUrl;
	}

	public String getSeparator() {
		return separator;
	}

	public void setSeparator(String separator) {
		this.separator = separator;
	}

	public String getPagingParam() {
		return pagingParam;
	}

	public void setPagingParam(String pagingParam) {
		this.pagingParam = pagingParam;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

	public int getRecordCount() {
		return recordCount;
	}
}
