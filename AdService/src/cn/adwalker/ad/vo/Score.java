package cn.adwalker.ad.vo;

import cn.adwalker.ad.bean.Data;


public class Score extends Data {

	private static final long serialVersionUID = 4028096826078543300L;
	private Integer score;// 变化后的积分(奖励后总分)
	private Integer updateScore;// 变化的积分(奖励积分)
	private String addInfo; //返回信息
	private String unit;	//积分(金币)
	private String scoreInof;//当前积分(金币):
	private String subInfo;//消费成功: 10积分(金币）:
	
	public Score() {
		this.updateScore = 0;
	}

	public Score(Integer score, Integer updateScore) {
		this.score = score;
		this.updateScore = updateScore;
	}

	public Score(Integer score) {
		this.score = score;
		this.updateScore = 0;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getUpdateScore() {
		return updateScore;
	}

	public void setUpdateScore(Integer updateScore) {
		this.updateScore = updateScore;
	}

	public String getAddInfo() {
		return addInfo;
	}

	public void setAddInfo(String addInfo) {
		this.addInfo = addInfo;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getScoreInof() {
		return scoreInof;
	}

	public void setScoreInof(String scoreInof) {
		this.scoreInof = scoreInof;
	}

	public String getSubInfo() {
		return subInfo;
	}

	public void setSubInfo(String subInfo) {
		this.subInfo = subInfo;
	}
	
}
