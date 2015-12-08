/**
* <p>Title: MaterielScore.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2013-5-30
* @version 1.0
*/
package cn.adwalker.ad.dao.domain;

import cn.adwalker.ad.cache.element.Wall;

/**
 * <p>Title: MaterielScore</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2013-5-30
 */
public class MaterielScore extends Wall  {

	/** @Fields serialVersionUID : */
	private static final long serialVersionUID = 7139946814824897653L;
	private String score_desc;// 积分描述
	private String banner_url;// banner地址
    private String score_long_desc;
    private Integer score_delay; //积分延时 column:SCORE_DELAY add by jierfei 2013-08-23
    private Integer limit_time;
    private String weixin_desc;
	public String getScore_desc() {
		return score_desc;
	}
	public void setScore_desc(String score_desc) {
		this.score_desc = score_desc;
	}
	public String getBanner_url() {
		return banner_url;
	}
	public void setBanner_url(String banner_url) {
		this.banner_url = banner_url;
	}
	public String getScore_long_desc() {
		return score_long_desc;
	}
	public void setScore_long_desc(String score_long_desc) {
		this.score_long_desc = score_long_desc;
	}
	
	public Integer getScore_delay() {
		return score_delay;
	}
	public void setScore_delay(Integer score_delay) {
		this.score_delay = score_delay;
	}
	public Integer getLimit_time() {
		return limit_time;
	}
	public void setLimit_time(Integer limit_time) {
		this.limit_time = limit_time;
	}
	public String getWeixin_desc() {
		return weixin_desc;
	}
	public void setWeixin_desc(String weixin_desc) {
		this.weixin_desc = weixin_desc;
	}
}
