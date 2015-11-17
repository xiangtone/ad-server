package cn.adwalker.ad.vo;
import java.util.List;

import cn.adwalker.ad.bean.Data;


/**
 * <p>Title: AdvertisementVO</p>
 * <p>Description: 换量墙广告信息资源对象</p>
 * <p>Company: adwalker</p> 
 * @author    caiqiang
 * @date       Aug 14, 2012
 */
public class AdverJson extends Data {
	
	private static final long serialVersionUID = 8148999916544312788L;
	private AdWallPage wallPage;//分页信息
	private List<AdJson> adList;//广告列表
	private List<AdJson> recodeList;
	private NoticeInfo notice;
	private Integer noticeNum=0;
	private Integer totalInteger;
	private SignBean sign;//签到信息
	
	public Integer getNoticeNum() {
		return noticeNum;
	}
	public void setNoticeNum(Integer noticeNum) {
		this.noticeNum = noticeNum;
	}
	public Integer getTotalInteger() {
		return totalInteger;
	}
	public void setTotalInteger(Integer totalInteger) {
		this.totalInteger = totalInteger;
	}
	public List<AdJson> getRecodeList() {
		return recodeList;
	}
	public void setRecodeList(List<AdJson> recodeList) {
		this.recodeList = recodeList;
	}
	public NoticeInfo getNotice() {
		return notice;
	}
	public void setNotice(NoticeInfo notice) {
		this.notice = notice;
	}
	public List<AdJson> getAdList() {
		return adList;
	}
	public void setAdList(List<AdJson> adList) {
		this.adList = adList;
	}
	
	public AdWallPage getWallPage() {
		return wallPage;
	}
	public void setWallPage(AdWallPage wallPage) {
		this.wallPage = wallPage;
	}
	public SignBean getSign() {
		return sign;
	}
	public void setSign(SignBean sign) {
		this.sign = sign;
	}
}
