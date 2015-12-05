package cn.adwalker.ad.admin.config.form;

import java.io.Serializable;
/**
 * <p>向广告主发送点击数据</p>
 * @author jief
 *
 */
public class SendClickInfoForm implements Serializable{
	  /**
	 * 
	 */
	private static final long serialVersionUID = -790527426409773905L;
	  private String id;				//id
	  private String mac;				//mac值
	  private String client_ip;			//终端ip值
	  private String openudid;			//OPENUDID值
	  private String idfa;				//IDFA值
	  private String idfv;				//IDFV值
	  private String channel;			//渠道名称
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public String getClient_ip() {
		return client_ip==null?"":client_ip;
	}
	public void setClient_ip(String client_ip) {
		this.client_ip = client_ip;
	}
	public String getOpenudid() {
		return openudid==null?"":openudid;
	}
	public void setOpenudid(String openudid) {
		this.openudid = openudid;
	}
	public String getIdfa() {
		return idfa==null?"":idfa;
	}
	public void setIdfa(String idfa) {
		this.idfa = idfa;
	}
	public String getIdfv() {
		return idfv==null?"":idfv;
	}
	public void setIdfv(String idfv) {
		this.idfv = idfv;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	  
}
