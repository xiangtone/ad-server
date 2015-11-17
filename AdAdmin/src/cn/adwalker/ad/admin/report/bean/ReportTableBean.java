package cn.adwalker.ad.admin.report.bean;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;

import cn.adwalker.model.report.domain.TStatReportTable;

public class ReportTableBean {
	@SuppressWarnings("unused")
	private static final Logger logger = Logger
			.getLogger(ReportTableBean.class);
	private TStatReportTable table = new TStatReportTable();
	@SuppressWarnings("rawtypes")
	private ArrayList items = new ArrayList();
	@SuppressWarnings("rawtypes")
	private HashMap data = new HashMap();

	public TStatReportTable getTable() {
		return table;
	}

	public void setTable(TStatReportTable table) {
		this.table = table;
	}

	@SuppressWarnings("rawtypes")
	public ArrayList getItems() {
		return items;
	}

	@SuppressWarnings("rawtypes")
	public void setItems(ArrayList items) {
		this.items = items;
	}

	@SuppressWarnings("rawtypes")
	public HashMap getData() {
		return data;
	}

	@SuppressWarnings("rawtypes")
	public void setData(HashMap data) {
		this.data = data;
	}
}
