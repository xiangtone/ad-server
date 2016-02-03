package cn.adwalker.ad.core.page;

public interface IPageInfo {

	public int getCurrentPage();

	public String getPrefixUrl();

	public String getUrl();

	public void setTotalRow(Integer totalRow);

	public int getTotalPage();

	public void setCurrentPage(int page);

	public int getPageSize();

	public abstract int getTotalRow();

	public String getPagingParam();

	public int getMenuLen();

	public String getPageInfoStr();

	public String getOrder();
}
