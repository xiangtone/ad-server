<%@ page language="java" import="java.util.*,org.apache.commons.lang.StringUtils,java.net.URLEncoder,cn.adwalker.ad.util.StringUtil" pageEncoding="UTF-8"%>
<%@page import="cn.adwalker.ad.util.StringUtil"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<% 
  //金山点击接口适配
  response.setContentType("text/xml;charset=UTF-8");
  response.setHeader("Cache-Control", "no-cache");
  response.setCharacterEncoding("UTF-8");
  String appid=request.getParameter("appid");
  String mac=request.getParameter("mac");
  String openudid=request.getParameter("udid");
  String clickid=request.getParameter("clickid");
  String ckey=request.getParameter("ckey");
  String idfa=request.getParameter("idfa");
  String os=request.getParameter("dv");
  String terminalType=request.getParameter("dm");
  String client_ip=request.getParameter("ip");
  out.print("mac="+mac);
  if(StringUtils.isBlank(mac) || "null".equals(mac)) mac="";
  if(StringUtils.isBlank(idfa)|| "null".equals(idfa)) idfa="";
  if(StringUtils.isBlank(os)|| "null".equals(os)) os="";
  String responseJson="";
  StringBuffer callback_url=new StringBuffer("http://c.ios.ijinshan.com/receiver/active");
  String source="jinshan";
  if(StringUtils.isBlank(appid)){
	   responseJson="{'success':false,'message':'参数异常'}";
	   out.print(responseJson);
	   System.out.println("jinshan error {'success':false,'message':'参数异常'}");
  }else{
  		callback_url.append("?ckey=").append(ckey);
  		callback_url.append("&clickid=").append(clickid);
  		callback_url.append("&udid=").append(openudid);
  		callback_url.append("&mac=").append(mac);
  		callback_url.append("&idfa=").append(idfa);
  		callback_url.append("&ip=").append(client_ip);
  		String url=URLEncoder.encode(callback_url.toString(),"utf-8");
  		idfa=StringUtil.formatIDFA(idfa);
  		%>
	  	<jsp:forward page="/common/channelCheckGet.do">
		<jsp:param name="appid" value="<%=appid%>" />
		<jsp:param name="deviceid" value="<%=mac%>" />
		<jsp:param name="source" value="<%=source%>" />
		<jsp:param name="IDFA" value="<%=idfa%>" />
		<jsp:param name="OPENUDID" value="<%=openudid%>" />
		<jsp:param name="callback_url" value="<%=url%>" />
		<jsp:param name="os" value="<%=os%>" />
		<jsp:param name="client_ip" value="<%=client_ip%>" />
		</jsp:forward>
	<%}%>