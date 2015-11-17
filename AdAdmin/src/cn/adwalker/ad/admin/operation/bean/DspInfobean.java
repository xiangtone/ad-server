package cn.adwalker.ad.admin.operation.bean;

/**
* <p>Title: DspInfobean</p>
* <p>Description:dspbean</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-12-3
 */
public class DspInfobean {
	//主键
	private Long dsp_id;
	//dsp名称
	private String dsp_name;
	//开始时间
	private String start_time;
	//结束时间
	private String end_time;
	
	public Long getDsp_id() {
		return dsp_id;
	}
	public void setDsp_id(Long dsp_id) {
		this.dsp_id = dsp_id;
	}
	public String getDsp_name() {
		return dsp_name;
	}
	public void setDsp_name(String dsp_name) {
		this.dsp_name = dsp_name;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	
}
