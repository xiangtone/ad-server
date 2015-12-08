package cn.adwalker.ad.picker.portal;

import java.util.ArrayList;
import java.util.List;

import cn.adwalker.ad.cache.element.Advertise;
import cn.adwalker.ad.cache.element.DevApp;
import cn.adwalker.ad.cache.element.UserInfo;
import cn.adwalker.ad.picker.util.NumberUtil;
import cn.adwalker.ad.picker.util.RandomCollection;
import cn.adwalker.ad.rules.template.SDKTemplate;
import cn.adwalker.ad.util.AppConstant;
import cn.adwalker.ad.vo.AdWallPage;
import cn.adwalker.ad.vo.SignBean;

/**
 * @author www.adwalker.cn
 */
public class PortalEngine {
    private List<Advertise> list;
	private Integer pageNo=0; //页码 第几页
	private Integer pageSize=0; //页大小
	private Integer totalSize=0; //总条数
	private Integer totalPage=-1; //页数
	private boolean isSingle;
	private SDKTemplate sdkTemplate;
	private DevApp app;
	private UserInfo userInfo;
	private SignBean sign;
	public SignBean getSign() {
		return sign;
	}
	public void setSign(SignBean sign) {
		this.sign = sign;
	}
	public SDKTemplate getSdkTemplate() {
		return sdkTemplate;
	}
	public void setSdkTemplate(SDKTemplate sdkTemplate) {
		this.sdkTemplate = sdkTemplate;
	}
	public DevApp getApp() {
		return app;
	}
	public void setApp(DevApp app) {
		this.app = app;
	}
	public UserInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
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
	public AdWallPage getWallPage(){
		return  new AdWallPage(totalPage,totalSize);
	}
	public static  PortalEngine createPage(List<Advertise> list,Integer pageNo,Integer pageSize,String pageType){
		PortalEngine page = new PortalEngine();
		page.init(list, pageNo, pageSize,pageType);
		return page;
	}
	
	private void init(List<Advertise> list,Integer pageNo,Integer pageSize,String pageType){
		this.list=list;
		this.pageNo=NumberUtil.getInteger(pageNo, 0);
		this.pageSize=NumberUtil.getInteger(pageSize, 10);
		this.totalSize=list.size();
		//this.pageType=pageType;
		isSingle = pageType.equals(AppConstant.PAGE_WALLTYPE_PUSH)|| pageType.equals(AppConstant.PAGE_WALLTYPE_PLAQUE)|| pageType.equals(AppConstant.PAGE_WALLTYPE_BANNER);
		if(this.pageSize!=0){
			this.totalPage=(this.totalSize%this.pageSize)==0? (this.totalSize/this.pageSize): (this.totalSize/this.pageSize)+1;
		}
	}
	private List<Advertise> getPageList(){
		int begin = Math.min(pageSize*pageNo,totalSize);
		int end = Math.min(begin+pageSize, totalSize);
		if(list!=null){			
			return list.subList(begin, end);
		}
		return null;
	}
	
	public List<Advertise> fatchPage(){
		List<Advertise> list = new ArrayList<Advertise>();
		if(isSingle){
			Advertise ad = fatchAd();
			if(ad!=null){
				list.add(ad);
			}
			return list;
		}else{
			return getPageList();
		}
	}
	
	private Advertise fatchAd(){
		RandomCollection<Advertise> rc = new RandomCollection<Advertise>();
		for(Advertise adv:list){
			rc.add(adv.getWeight(), adv);
		}
		Advertise ad=rc.next();
		return ad;
	}
	
	
	
	
}
