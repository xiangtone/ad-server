<%@ page language="java" import="java.util.*,org.apache.commons.lang.StringUtils" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<% 
  response.setContentType("text/xml;charset=UTF-8");
  response.setHeader("Cache-Control", "no-cache");
  response.setCharacterEncoding("UTF-8");
  String appid=request.getParameter("appid");
  String mac=request.getParameter("mac");
  if(StringUtils.isBlank(mac) || "null".equals(mac)) mac="";
  String idfa=request.getParameter("ifa");
  if(StringUtils.isBlank(idfa)|| "null".equals(idfa)) idfa="";
  String responseJson="";
  if(StringUtils.isBlank(appid)){
	   responseJson="{'success':false,'message':'参数异常'}";
	   out.print(responseJson);
	   System.out.println("mopan error {'success':false,'message':'参数异常'}");
  }else{%>
	  	<jsp:forward page="/common/comfirmActivateCheckGet.do">
		<jsp:param name="appid" value="<%=appid%>" />
		<jsp:param name="deviceid" value="<%=mac%>" />
		<jsp:param name="IDFA" value="<%=idfa%>" />
		</jsp:forward>
	<%}%>