package cn.adwalker.ad.vo;

import cn.adwalker.ad.bean.Data;

/**
 * 
* <p>Title: GeneralJson</p>
* <p>Description:这个类复制一份，今天想办法删除掉</p>
* <p>Company: adwalker</p> 
* @author    cuidd
* @date       2014年10月18日
 */

public class GeneralJson extends Data {
	
	private static final long serialVersionUID = 7658569042928546190L;
	private String wall_icon_Url;//图标URL
	private String wall_left_first;// (0-描述 1-描述 2-空)
	private String wall_left_second;// (0-名称 1-名称 2-名称)
	private String wall_left_third;// (0-版本号 1-版本号+大小 2-空)
	private String wall_right;// (0-大小 1-积分 2-空)
	private String wall_desc;// (0-大小 1-积分 2-空)
	private String wall_version;// (0-大小 1-积分 2-空)
	private int score;// 积分墙分数
	private String scoreUnit;// 积分墙单位
	private int	ad_surplus;// 广告余额 int 
	private String limit_time;// 限制时间
	
	
	public String getWall_version() {
		return wall_version;
	}
	public void setWall_version(String wall_version) {
		this.wall_version = wall_version;
	}
	public String getWall_icon_Url() {
		return wall_icon_Url;
	}
	public void setWall_icon_Url(String wall_icon_Url) {
		this.wall_icon_Url = wall_icon_Url;
	}
	public String getWall_left_first() {
		return wall_left_first;
	}
	public void setWall_left_first(String wall_left_first) {
		this.wall_left_first = wall_left_first;
	}
	public String getWall_left_second() {
		return wall_left_second;
	}
	public void setWall_left_second(String wall_left_second) {
		this.wall_left_second = wall_left_second;
	}
	public String getWall_left_third() {
		return wall_left_third;
	}
	public void setWall_left_third(String wall_left_third) {
		this.wall_left_third = wall_left_third;
	}
	public String getWall_right() {
		return wall_right;
	}
	public void setWall_right(String wall_right) {
		this.wall_right = wall_right;
	}
	public String getWall_desc() {
		return wall_desc;
	}
	public void setWall_desc(String wall_desc) {
		this.wall_desc = wall_desc;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getScoreUnit() {
		return scoreUnit;
	}
	public void setScoreUnit(String scoreUnit) {
		this.scoreUnit = scoreUnit;
	}
	public int getAd_surplus() {
		return ad_surplus;
	}
	public void setAd_surplus(int ad_surplus) {
		this.ad_surplus = ad_surplus;
	}
	public String getLimit_time() {
		return limit_time;
	}
	public void setLimit_time(String limit_time) {
		this.limit_time = limit_time;
	}
}
