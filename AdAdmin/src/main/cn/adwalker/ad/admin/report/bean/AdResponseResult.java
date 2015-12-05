package cn.adwalker.ad.admin.report.bean;

public class AdResponseResult {

	/***匹配的结果      mac地址**/
	private String MatchContents;
	
	/***匹配到的结果个数    激活数**/
	private Integer MatchCnt;
	
	/***消息内容**/
	private String ErrorMsg;
	
	/***是否有错**/
	private Integer IsError;
	
	

	
	
	public String getMatchContents() {
		return MatchContents;
	}

	public void setMatchContents(String matchContents) {
		MatchContents = matchContents;
	}

	public Integer getMatchCnt() {
		return MatchCnt;
	}

	public void setMatchCnt(Integer matchCnt) {
		MatchCnt = matchCnt;
	}

	public String getErrorMsg() {
		return ErrorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		ErrorMsg = errorMsg;
	}

	public Integer getIsError() {
		return IsError;
	}

	public void setIsError(Integer isError) {
		IsError = isError;
	}

	

	public AdResponseResult() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
