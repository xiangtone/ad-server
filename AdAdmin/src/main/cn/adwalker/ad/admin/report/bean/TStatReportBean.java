package cn.adwalker.ad.admin.report.bean;

import java.util.Date;

/**
 * TStatReportTable generated by MyEclipse Persistence Tools
 */

public class TStatReportBean implements java.io.Serializable {

    // Fields

    /** @Fields serialVersionUID : TODO*/
	private static final long serialVersionUID = -3179830971805497791L;
	private Long id;
    private String jspName;
    private String menuName;
    private String originSql;
    private String countSql;
    private Integer pageSize;
    private String status;
    private String role;
    private String note;
    private Date createtime;
    private String COperator;
    private Date updatetime;
    private String UOperator;
    private String totalSql;
    private Integer defaultStart;
    private Integer defaultEnd;

    // Constructors

    /** default constructor */
    public TStatReportBean() {
    }

    /** full constructor */
    public TStatReportBean(String jspName, String menuName, String originSql,
            String countSql, Integer pageSize, String status, String role,
            String note, Date createtime, String COperator, Date updatetime,
            String UOperator, String totalSql, Integer defaultStart,
            Integer defaultEnd) {
        this.jspName = jspName;
        this.menuName = menuName;
        this.originSql = originSql;
        this.countSql = countSql;
        this.pageSize = pageSize;
        this.status = status;
        this.role = role;
        this.note = note;
        this.createtime = createtime;
        this.COperator = COperator;
        this.updatetime = updatetime;
        this.UOperator = UOperator;
        this.totalSql = totalSql;
        this.defaultStart = defaultStart;
        this.defaultEnd = defaultEnd;
    }

    // Property accessors

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJspName() {
        return this.jspName;
    }

    public void setJspName(String jspName) {
        this.jspName = jspName;
    }

    public String getMenuName() {
        return this.menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getOriginSql() {
        return this.originSql;
    }

    public void setOriginSql(String originSql) {
        this.originSql = originSql;
    }

    public String getCountSql() {
        return this.countSql;
    }

    public void setCountSql(String countSql) {
        this.countSql = countSql;
    }

    public Integer getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getNote() {
        return this.note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getCreatetime() {
        return this.createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getCOperator() {
        return this.COperator;
    }

    public void setCOperator(String COperator) {
        this.COperator = COperator;
    }

    public Date getUpdatetime() {
        return this.updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getUOperator() {
        return this.UOperator;
    }

    public void setUOperator(String UOperator) {
        this.UOperator = UOperator;
    }

    public String getTotalSql() {
        return this.totalSql;
    }

    public void setTotalSql(String totalSql) {
        this.totalSql = totalSql;
    }

    public Integer getDefaultStart() {
        return this.defaultStart;
    }

    public void setDefaultStart(Integer defaultStart) {
        this.defaultStart = defaultStart;
    }

    public Integer getDefaultEnd() {
        return this.defaultEnd;
    }

    public void setDefaultEnd(Integer defaultEnd) {
        this.defaultEnd = defaultEnd;
    }

}