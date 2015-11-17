package cn.adwalker.ad.param;

import java.io.Serializable;

public class WeixinUserParam implements Serializable{
	/** @Fields serialVersionUID : TODO*/
	private static final long serialVersionUID = -6731119620093773589L;
	private String	idfa;
	private String	idfv;
	private String	userid;
	private String	clinet_ip;
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
	
	public String getClinet_ip() {
		return clinet_ip;
	}
	public void setClinet_ip(String clinet_ip) {
		this.clinet_ip = clinet_ip;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	
}
