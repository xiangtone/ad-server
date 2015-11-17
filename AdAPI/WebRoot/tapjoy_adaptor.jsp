<%@ page language="java" import="java.util.*,org.apache.commons.lang.StringUtils,java.net.URLEncoder,cn.adwalker.ad.util.StringUtil" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<% 
  //tapjoy点击接口适配
  response.setContentType("text/xml;charset=UTF-8");
  response.setHeader("Cache-Control", "no-cache");
  response.setCharacterEncoding("UTF-8");
  String appid=request.getParameter("appid");
  String mac=request.getParameter("mac");
  String source=request.getParameter("source");
  String idfa=request.getParameter("IDFA");
  if(StringUtils.isBlank(mac) || "null".equals(mac)) mac="";
  if(StringUtils.isBlank(idfa)|| "null".equals(idfa)) idfa="";
  String responseJson="";
  if(StringUtils.isBlank(appid)){
	   responseJson="{'success':false,'message':'参数异常'}";
	   out.print(responseJson);
	   System.out.println("jinshan error {'success':false,'message':'参数异常'}");
  }else{
  		idfa=StringUtil.formatIDFA(idfa);
  		%>
	  	<jsp:forward page="/common/channelCheckGet.do">
		<jsp:param name="appid" value="<%=appid%>" />
		<jsp:param name="deviceid" value="<%=mac%>" />
		<jsp:param name="source" value="<%=source%>" />
		<jsp:param name="IDFA" value="<%=idfa%>" />
		</jsp:forward>
	<%}%>