/**
 * 
 */
package cn.adwalker.ad.model.platform.dao;

import java.text.ParseException;
import java.util.Date;

import cn.adwalker.ad.config.AppConstant;
import cn.adwalker.ad.util.TimeUtil;

/**
 * 功能概述：<br>
 * 新闻公告实体类
 * 
 * @author guoyongxiang
 */
public class NewsNotice {

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
	/** sysdate */
	private Date createTime;
	/** 更新时间 */
	private Date updateTime; 
	/** 逻辑删除标识*/
	private Integer del;
	
	public NewsNotice() throws ParseException{
		createDate = TimeUtil.toStrDateFromUtilDateByFormat(new Date(), "yyyy-MM-dd");
		updateTime = new Date();
		del = AppConstant.DEL_NO;
	}
	
	
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
		return createDate;
	}
	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * @return the updateTime
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	/**
	 * @param updateTime the updateTime to set
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}


	/**
	 * @return the del
	 */
	public Integer getDel() {
		return del;
	}
	/**
	 * @param del the del to set
	 */
	public void setDel(Integer del) {
		this.del = del;
	}
}
