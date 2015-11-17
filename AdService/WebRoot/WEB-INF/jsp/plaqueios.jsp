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
	<title>插屏广告</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/plaqueios.css" />
	<script src="${pageContext.request.contextPath}/js/base64.js"></script>
	<script src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/plaque.js"></script>
</head>
<body >
			<div id="plaque" class="plaquecontent"  onclick="showDiv('${id}', '${adType}','${adUrl}')">
				<img id="plaquelogo" class="plaquelogo" src="${adImageUrl }">
			</div>
<!-- 			<div class="two"> -->
<!-- 				<img id="watermark" class="watermark" alt="watermark" src="../images/watermark.png"> -->
<!-- 			</div> -->
	  		<div id="download" class="downloadcontent"  onclick="hiddenDiv()">
	  			<img alt="downloadbutton" src="../images/downbutton.png" id="downloadButton" class="downloadButton"  onmouseover="downEffect()" onmouseout="downUneffect()">
	  		</div>
		
		<input type="hidden" id="appUrl" value="${appUrl}" />
		<input type="hidden" id="adType" value="${adType}" />
		<input type="hidden" id="resourceUrl" value="${resourceUrl}" />
		<input type="hidden" id="title" value="${title}" />
		<input type="hidden" id="imageurl" value="${adImageUrl}" />
		<input type="hidden" id="id" value="${id }" />
		<input type="hidden" id="uuid" value="${uuid }" />
		<input type="hidden" id="pageType" value="${pageType }" />
		<input type="hidden" id="imageType" value="${imageType }" />
		<input type="hidden" id="version" value="${version }" />
		<input type="hidden" id="channel" value="${channel }" />
		<input type="hidden" id="appId" value="${appId }" />
		<input type="hidden" id="terminalType" value="${terminalType }" />
		<input type="hidden" id="imsi" value="${imsi}" />
		<input type="hidden" id="packageName" value="${packageName}">
		<input type="hidden" id="resourceSize" value="${resourceSize}">	
		<input type="hidden" id="phoneType" value="${phoneType}" />		
		<input type="hidden" id="openudid" value="${openudid }" />
	    <input type="hidden" id="idfa" value="${idfa }" />
	    <input type="hidden" id="idfv" value="${idfv }" />
	    <input type="hidden" id="os" value="${os }" />
</body>
</html>