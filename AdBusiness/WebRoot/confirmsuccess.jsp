<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/include/common/header.jsp"%>
<html class="login-bg">
	<head>
		<title><%=company%>广告平台</title>
	    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
	    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/compiled/signup.css" type="text/css" media="screen" />
	</head>
	<body>
		<div class="header">
			<a href="index.jsp">
				<img src="${pageContext.request.contextPath}<%=menu_head_logo %>" class="logo" />
			</a>
		</div>
		<div class="login-wrapper">
			${msg}
		</div>
	</body>
</html>