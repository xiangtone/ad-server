package cn.adwalker.ad.cache.element;

import java.util.List;

import cn.adwalker.ad.bean.AdDetailPicture;
import cn.adwalker.ad.bean.Data;
/**
 * 
* <p>Title: AdDetailVo</p>
* <p>Description:TODO</p>
* <p>Company: adwalker</p> 
* @author    www.adwalker.cn
* @date       2013-1-28
 */
public class AdDetailVo extends Data {
	/** @Fields serialVersionUID : TODO*/
	private static final long serialVersionUID = 2960471831229938745L;
	private String detail_icon_Url;
	private String detail_first;// 名称
	private String detail_second;// 更新时间
	private String detail_third;// 版本
	private String detail_fourth; // 大小
	private String detail_fifth;// (0-空 1-积分 2-空)
	private String detail_sixth;// 文字:软件介绍
	private String detail_seventh;// 介绍内容
	private Long category_id;
	private List<AdDetailPicture> adDetailPicture;// 界面图片(多)
	private String category_name;
	private int isDownload;//0:广告未被下载，1被下载
	private String resourceUrl;// 资源URL
	private Long resourceSize;// 资源大小
	private String packageName;// 包名称
	private String score_msg;//积分获取详细说明 jief2013-09-06
	
	public String getDetail_icon_Url() {
		return detail_icon_Url;
	}
	public void setDetail_icon_Url(String detail_icon_Url) {
		this.detail_icon_Url = detail_icon_Url;
	}
	public String getDetail_first() {
		return detail_first;
	}
	public void setDetail_first(String detail_first) {
		this.detail_first = detail_first;
	}
	public String getDetail_second() {
		return detail_second;
	}
	public void setDetail_second(String detail_second) {
		this.detail_second = detail_second;
	}
	public String getDetail_third() {
		return detail_third;
	}
	public void setDetail_third(String detail_third) {
		this.detail_third = detail_third;
	}
	public String getDetail_fourth() {
		return detail_fourth;
	}
	public void setDetail_fourth(String detail_fourth) {
		this.detail_fourth = detail_fourth;
	}
	public String getDetail_fifth() {
		return detail_fifth;
	}
	public void setDetail_fifth(String detail_fifth) {
		this.detail_fifth = detail_fifth;
	}
	public String getDetail_sixth() {
		return detail_sixth;
	}
	public void setDetail_sixth(String detail_sixth) {
		this.detail_sixth = detail_sixth;
	}
	public String getDetail_seventh() {
		return detail_seventh;
	}
	public void setDetail_seventh(String detail_seventh) {
		this.detail_seventh = detail_seventh;
	}
	public List<AdDetailPicture> getAdDetailPicture() {
		return adDetailPicture;
	}
	public void setAdDetailPicture(List<AdDetailPicture> adDetailPicture) {
		this.adDetailPicture = adDetailPicture;
	}
	
	
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	public Long getCategory_id() {
		return category_id;
	}
	public void setCategory_id(Long category_id) {
		this.category_id = category_id;
	}
	
	
	
	public Long getResourceSize() {
		return resourceSize;
	}
	public void setResourceSize(Long resourceSize) {
		this.resourceSize = resourceSize;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String getScore_msg() {
		return score_msg;
	}
	public void setScore_msg(String score_msg) {
		this.score_msg = score_msg;
	}
	public String getResourceUrl() {
		return resourceUrl;
	}
	public void setResourceUrl(String resourceUrl) {
		this.resourceUrl = resourceUrl;
	}
	public int getIsDownload() {
		return isDownload;
	}
	public void setIsDownload(int isDownload) {
		this.isDownload = isDownload;
	}
}
