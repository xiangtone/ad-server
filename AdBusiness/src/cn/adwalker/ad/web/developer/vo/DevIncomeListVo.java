package cn.adwalker.ad.web.developer.vo;

import java.io.Serializable;

import cn.adwalker.ad.util.Function;

public class DevIncomeListVo implements Serializable {

	private static final long serialVersionUID = 8465237714859413876L;

	private Long id;

	private String name;

	private int score_wall_num;
	private double score_wall_cost;
	private int page_wall_num;
	private double page_wall_cost;
	private int banner_num;
	private double banner_cost;
	private int chartboost_num;
	private double chartboost_cost;

	public double getTotal_cost() {
		return Function.add(Function.add(Function.add(score_wall_cost, page_wall_cost), banner_cost), chartboost_cost);
	}

	public int getScore_wall_num() {
		return score_wall_num;
	}

	public void setScore_wall_num(int score_wall_num) {
		this.score_wall_num = score_wall_num;
	}

	public double getScore_wall_cost() {
		return score_wall_cost;
	}

	public void setScore_wall_cost(double score_wall_cost) {
		this.score_wall_cost = score_wall_cost;
	}

	public int getPage_wall_num() {
		return page_wall_num;
	}

	public void setPage_wall_num(int page_wall_num) {
		this.page_wall_num = page_wall_num;
	}

	public double getPage_wall_cost() {
		return page_wall_cost;
	}

	public void setPage_wall_cost(double page_wall_cost) {
		this.page_wall_cost = page_wall_cost;
	}

	public int getBanner_num() {
		return banner_num;
	}

	public void setBanner_num(int banner_num) {
		this.banner_num = banner_num;
	}

	public double getBanner_cost() {
		return banner_cost;
	}

	public void setBanner_cost(double banner_cost) {
		this.banner_cost = banner_cost;
	}

	public int getChartboost_num() {
		return chartboost_num;
	}

	public void setChartboost_num(int chartboost_num) {
		this.chartboost_num = chartboost_num;
	}

	public double getChartboost_cost() {
		return chartboost_cost;
	}

	public void setChartboost_cost(double chartboost_cost) {
		this.chartboost_cost = chartboost_cost;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
