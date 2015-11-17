/**
 * <p>Title: AdvertisementChannel.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author www.adwalker.cn
 * @date 2012-12-21
 * @version 1.0
 */
package cn.adwalker.IOSChannel.vo;

import cn.adwalker.ad.bean.Data;

/**
 * <p>
 * Title: AdvertisementChannel
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author www.adwalker.cn
 * @date 2012-12-21
 */
public class AdvertisementChannel extends Data{
	
	

	/** @Fields serialVersionUID : TODO*/
	private static final long serialVersionUID = -6134766701767186477L;
	
	private String channel;
	private String url;
	private String channel_name;
	private String adid_para;
	private String deviceid_para;
	private String time_para;
	private String openUdid;//OPENUDID; add by jief 2013-09-10
	private String idfa;	//IDFA; add by jief 2013-09-10
	private String idfv;	//IDFV; add by jief 2013-09-10

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getChannel_name() {
		return channel_name;
	}

	public void setChannel_name(String channel_name) {
		this.channel_name = channel_name;
	}

	



	public String getTime_para() {
		return time_para;
	}

	public void setTime_para(String time_para) {
		this.time_para = time_para;
	}

	public String getAdid_para() {
		return adid_para;
	}

	public void setAdid_para(String adid_para) {
		this.adid_para = adid_para;
	}

	public String getDeviceid_para() {
		return deviceid_para;
	}

	public void setDeviceid_para(String deviceid_para) {
		this.deviceid_para = deviceid_para;
	}

	public String getOpenUdid() {
		return openUdid;
	}

	public void setOpenUdid(String openUdid) {
		this.openUdid = openUdid;
	}

	public String getIdfa() {
		return idfa;
	}

	public void setIdfa(String idfa) {
		this.idfa = idfa;
	}

	public String getIdfv() {
		return idfv;
	}

	public void setIdfv(String idfv) {
		this.idfv = idfv;
	}

	
}
