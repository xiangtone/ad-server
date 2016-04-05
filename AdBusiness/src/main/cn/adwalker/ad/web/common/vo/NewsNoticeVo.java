/*
 * NewsNoticeVo.java
 */
package cn.adwalker.ad.web.common.vo;

import java.util.Date;

import cn.adwalker.ad.config.AppConstant;
import cn.adwalker.ad.util.ObjectUtils;

/**
 * 功能描述<br>
 * 新闻公告vo	
 *
 * @author guoyongxiang
 */
public class NewsNoticeVo {

	/** 主键 */
	private Long id;
	/** 管理员ID */
	private Long managerId;
	/** 标题 */
	private String title;
	
	/** 内容 */
	private String content;
	/** 类型 1:新闻 2:公告 */
	private Integer type;
	
	/** 发布日期 */
	private String createDate;
	
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the managerId
	 */
	public Long getManagerId() {
		return managerId;
	}
	/**
	 * @param managerId the managerId to set
	 */
	public void setManagerId(Long managerId) {
		this.managerId = managerId;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	
	public String getTitleDisplay() {
		String ret = title;
		if(title != null && title.length() > 23){
			ret = title.substring(0,23)+"...";
		}
		return ret;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * @return the type
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * @return the createDate
	 */
	public String getCreateDate() {
		return createDate.substring(5);
	}
	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	/**
	 * @return the showType
	 */
	public String getShowType() {
		if(ObjectUtils.isNotEmpty(type)){
			if(AppConstant.NEWS_NOTICE_TYPE_NEWS == this.type){
				return "新闻";
			}else if(AppConstant.NEWS_NOTICE_TYPE_NOTICE == this.type){
				return "公告";
			}
		}else{
			return "";
		}
		return content;
	}
	/**
	* <p>Title: setUpdateTime</p>
	* <p>Description:TODO</p>
	* @param date
	* @author cuidd
	* @date 2013-1-7
	* @return void
	* @version 1.0
	* @throws
	*/
	
	public void setUpdateTime(Date date) {
		// TODO Auto-generated method stub
		
	}
}
