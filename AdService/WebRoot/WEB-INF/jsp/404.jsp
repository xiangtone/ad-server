<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>error</title>
<style type="text/css">
	.errorimage{
		width: 100%;
		height: 100%
	}
</style>
</head>
<body>
	<img alt="error" class="errorimage"  src="../images/404X.jpg">
</body>
</html>