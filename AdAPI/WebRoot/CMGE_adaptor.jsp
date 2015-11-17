<%@ page language="java" import="java.util.*,org.apache.commons.lang.StringUtils" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<% 
  response.setContentType("text/xml;charset=UTF-8");
  response.setHeader("Cache-Control", "no-cache");
  response.setCharacterEncoding("UTF-8");
  String appid=request.getParameter("b");
  String mac=request.getParameter("a");
  String idfa=request.getParameter("c");
  String responseJson="";
  if(StringUtils.isBlank(appid) || StringUtils.isBlank(mac)){
	   responseJson="{'success':false,'message':'参数异常'}";
	   out.print(responseJson);
	   System.out.println("mopan error {'success':false,'message':'参数异常'}");
  }else{
	  if(mac.length()==17){%>
	  	<jsp:forward page="/common/comfirmActivateCheckGet.do">
		<jsp:param name="appid" value="<%=appid%>" />
		<jsp:param name="deviceid" value="<%=mac%>" />
		</jsp:forward>
	<%		 
	  }else{%>
		<jsp:forward page="/common/comfirmActivateCheckGet.do">
		<jsp:param name="appid" value="<%=appid%>" />
		<jsp:param name="IDFA" value="<%=mac%>" />
		</jsp:forward>
	  <%}
  }
%>