package cn.adwalker.ad.admin.common.form;

import java.util.Date;

import cn.adwalker.ad.config.AppConstant;
import cn.adwalker.core.util.lang.ObjectUtils;

/**
* <p>Title: NewsNotice</p>
* <p>Description: 新闻公告</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-7-10
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
	private Date create_time;
	
	
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
	
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
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
