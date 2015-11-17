package cn.adwalker.ad.admin.report.bean;

import java.util.ArrayList;
import java.util.List;

import cn.adwalker.model.report.domain.TStatReportItem;



public class ReportItemBean{
    private TStatReportItem item = new TStatReportItem();

    private String input = "";

    private String codeInput = "";

    @SuppressWarnings("rawtypes")
	private List select = new ArrayList();
    
    private String colspan= "1";

    public String getColspan() {
        return colspan;
    }

    public void setColspan(String colspan) {
        this.colspan = colspan;
    }

    public TStatReportItem getItem() {
        return item;
    }

    public void setItem(TStatReportItem item) {
        this.item = item;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }
  
    @SuppressWarnings("rawtypes")
	public List getSelect() {
		return select;
	}

	@SuppressWarnings("rawtypes")
	public void setSelect(List select) {
		this.select = select;
	}

	public String getCodeInput() {
        return codeInput;
    }

    public void setCodeInput(String codeInput) {
        this.codeInput = codeInput;
    }

}
