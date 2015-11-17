<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<title>问题反馈</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/feedback.css" />
<script src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/base64.js"></script>
<script src="${pageContext.request.contextPath}/js/global.js"></script>
<script src="${pageContext.request.contextPath}/js/feedback.js"></script>
</head>
<body>
	<!-- 顶部菜单 -->
		<div class="toptitle">
		   <div class="titletop">
				<div class="topleft"><img id="backlogo" class="backlogo" alt="backlogo" src="../images/jifenback.png" onclick="goBack()"></div>
				<div class="topright"><span class="titleword">问题反馈</span></div>
			</div>
			<div class="divider2"></div>
		</div>
		
		<!-- 中间内容 -->
		<div class="desc">
			<div class="feedback"><textarea style="width: 100%;height: 150px;margin-top: 5px" id="opinion" onclick="dothings('opinion')">请输入您的反馈意见</textarea></div>
			<div class="email"><input type="text" class="user_email" id="emailAddr" value="请输入您的邮箱" onclick="dothings('emailAddr')"/></div>
			<div class="refer" onclick="submitData()">提交反馈</div>
		</div>
	<input type="hidden" id="uuid" value="${uuid }" />
	<input type="hidden" id="version" value="${version }" />
	<input type="hidden" id="appId" value="${appId }" />
	<input type="hidden" id="phoneType" value="${phoneType }" />
</body>
</html>