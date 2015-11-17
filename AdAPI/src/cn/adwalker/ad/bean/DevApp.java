package cn.adwalker.ad.bean;
/**
 * 
* <p>Title: DevApp</p>
* <p>Description:应用</p>
* <p>Company: adwalker</p> 
* @author    cuidd
* @date       2014年10月22日
 */
public class DevApp extends Data{
	
	private static final long serialVersionUID = 4199241910463005925L;
	private Long id;
	private Integer is_coordinate;//是否获取经纬度
	
	public Integer getIs_coordinate() {
		return is_coordinate;
	}

	public void setIs_coordinate(Integer isCoordinate) {
		is_coordinate = isCoordinate;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
}
