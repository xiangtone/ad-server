package cn.adwalker.ad.cache.element;

import cn.adwalker.ad.bean.AppBlack;
import cn.adwalker.ad.bean.Data;
import cn.adwalker.ad.dao.domain.DevCurrency;
import cn.adwalker.ad.picker.util.StringUtil;

/**
 * 
* <p>Title: DevApp</p>
* <p>Description:应用相关缓存</p>
* <p>Company: adwalker</p> 
* @author    cuidd
* @date       2014年10月30日
 */
public class DevApp extends Data{
	
	private static final long serialVersionUID = 4199241910463005925L;
	private Long id;
	private Long category_id;
	private String os;
	private String name;
	private String package_name;
	private String version_code;	
	private DevCurrency currency;
	private Long placement;
	private String response_url;//积分回调地址
	private String matchBlack;//黑名单
	private String typeIds="";
	private Integer is_coordinate;//是否获取经纬度
	private Float scale;//媒体评级2014-05-21
	
	private String ad_res;
	
	private String appkey;
	private Long dev_id;
	private Integer status;
	private Integer del;

	
	public Integer getIs_coordinate() {
		return is_coordinate;
	}

	public void setIs_coordinate(Integer isCoordinate) {
		is_coordinate = isCoordinate;
	}

	public void setMatchBack(AppBlack black){
		if(black!=null){
			String blackStr=black.getBlack();
			this.matchBlack=StringUtil.isEmpty(blackStr)?"-1000":("("+blackStr+")");// black.getBlack();
		}
	}
	
	public String getResponse_url() {
		return response_url;
	}
	public void setResponse_url(String responseUrl) {
		response_url = responseUrl;
	}
	public DevCurrency getCurrency() {
		return currency;
	}
	public void setCurrency(DevCurrency currency) {
		this.currency = currency;
	}
	
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public Long getCategory_id() {
		return category_id;
	}
	public void setCategory_id(Long category_id) {
		this.category_id = category_id;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPackage_name() {
		return package_name;
	}
	public void setPackage_name(String package_name) {
		this.package_name = package_name;
	}
	public String getVersion_code() {
		return version_code;
	}
	public void setVersion_code(String version_code) {
		this.version_code = version_code;
	}
	public Long getPlacement() {
		return placement;
	}
	public void setPlacement(Long placement) {
		this.placement = placement;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getScale() {
		return scale;
	}

	public void setScale(Float scale) {
		this.scale = scale;
	}

	public String getMatchBlack() {
		return matchBlack;
	}

	public void setMatchBlack(String matchBlack) {
		this.matchBlack = matchBlack;
	}

	public String getTypeIds() {
		return typeIds;
	}

	public void setTypeIds(String typeIds) {
		this.typeIds = typeIds;
	}

	public String getAppkey() {
		return appkey;
	}

	public void setAppkey(String appkey) {
		this.appkey = appkey;
	}

	public Long getDev_id() {
		return dev_id;
	}

	public void setDev_id(Long dev_id) {
		this.dev_id = dev_id;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getDel() {
		return del;
	}

	public void setDel(Integer del) {
		this.del = del;
	}

	public String getAd_res() {
		return ad_res;
	}

	public void setAd_res(String ad_res) {
		this.ad_res = ad_res;
	}
}
