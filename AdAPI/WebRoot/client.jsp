<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="ISO-8859-1"%>
<%@page import="cn.adwalker.ad.util.ConfigUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<div style="line-height: 28px;margin:0 20px;">
portal data
 <form action="/client/cinfo.do">
    <b>click</b><br/>
    key:<input type="password"  name="data" value="" size="32"/><br/>
    <input type="button" value="submit"/>
 </form>
 
 
 aaaa
 <% out.println(ConfigUtil.getString("check.callback")); %>
 
 </div>
</body>
</html>