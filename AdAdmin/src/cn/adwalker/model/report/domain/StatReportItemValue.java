package cn.adwalker.model.report.domain;


/**
 * TStatReportItem generated by MyEclipse Persistence Tools
 */

public class StatReportItemValue implements java.io.Serializable {


    private Long id;
    private String value;
 

    // Constructors

    /** default constructor */
    public StatReportItemValue() {
    }

    /** full constructor */
    public StatReportItemValue(Long id, String value) {
        this.id = id;
        this.value = value;

    }

    // Property accessors

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

  
}