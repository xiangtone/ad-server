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

	<title>推荐墙详情页</title> 
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/detail-tuijian.css" />
	<script src="${pageContext.request.contextPath}/js/base64.js"></script>
	<script src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/global.js"></script>
	<script src="${pageContext.request.contextPath}/js/detail-tuijian.js"></script>

</head> 
<body> 
<div id="detailtuijian">
<div class="toptitle">
			<div class="backtitle" onclick="goBack()">
				<img id="backlogo" class="backlogo" alt="backlogo"
					src="../images/back.png">
			</div>
			<div class="wordtitle">
				精品推荐
			</div>
			<div class="nexttitle">
				<div id="next" class="next" onclick="requestDetail()"><span class="righttrangle">下一个></span></div>
			</div>
</div>

	<div id="detailcontent" class="detailcontent">	
		<div id="content" class="content"> 	
		 	<div class="leftcontent" >
		 		<img  class="logo" id="logo" src="">
		 	</div>
		    <div class="middlecontent">
		    	<div id="title" class="title"></div>
				<div id="catagory" class="catagory"></div>
				<div id="desc" class="desc"></div>
		    </div>
			<div class="rightcontent">
				<img  class="renzheng" id="renzheng" src="../images/tjrenzheng.png" ><span class="rzword">安全认证</span>
			</div>
		</div>
			<div id="banner" class="banner">
				<div class="reflectup"></div>
					<div id="containers" class="container" >
						<div id="slides" class="slider">
								<div class="item">
									<img id="icon1"  />
								</div>
								<div class="item">
									<img  id="icon2"  />
								</div>
								<div class="item">
									<img id="icon3" />
								</div>
						</div>
				 </div>
			<div id="diot" class="diot">
				<img  id="diot1" alt="diot" src="../images/diotnofocus.png">
				<img  id="diot2" alt="diot" src="../images/diotonfocus.png">
				<img  id="diot3" alt="diot" src="../images/diotnofocus.png">
			</div>
			<div class="reflectdown" ></div>
			</div>
		
		<div class="softdesc">
			<span id="softTitle">应用简介</span>
		</div>
		
		
		<div class="scoreDesc" id="softInfo" >
				<textarea id="bbb" class="textereacontent" disabled="disabled"></textarea>
		</div>
		
		<div id="extend" class="extend" onclick="senfe()"><img id="extendImage" class="extendImage" src="../images/tjzhankai.jpg"></div>
		<div class="divider2" id="divider"></div>
		<div id="other" class="other">下载此应用的用户还下载了:</div>
		<div id="othercontent" class="othercontent">
			<div id="otherfirst" class="leftcontent" ><img  class="otherimage" id="otherimage1" ><div id="otherword1"></div></div>
			<div id="othersecond" class="leftcontent" ><img  class="otherimage" id="otherimage2" ><div id="otherword2"></div></div>
			<div id="otherthird" class="leftcontent" ><img  class="otherimage" id="otherimage3" ><div id="otherword3"></div></div>
			<div id="otherfouth" class="leftcontent" ><img  class="otherimage" id="otherimage4" ><div id="otherword4"></div></div>
		</div>
		<div id="blank" class="blank" style="height: 80px"></div>
		<div id="bottomId2">
				<div id="bigDownload2" class="bigdownload2"><img  id="downloadlogo2" class="downloadlogo" src="../images/jifendownload01.png"><span id="downloadword2" class="downloadword"></span></div>
<!-- 				<div id="shareDiv2" class="shareDiv2"><img id="share" class="share" src="../images/jfshare.png" height="30"></div> -->
		</div>
	</div>
</div>
		<div id="bottomId">
				<div id="bigDownload" class="bigdownload"><img  id="downloadlogo" class="downloadlogo" src="../images/jifendownload01.png"><span id="downloadword" class="downloadword"></span></div>
<!-- 				<div id="shareDiv" class="shareDiv"><img id="share" class="share" src="../images/tjshare.png" height="30"></div> -->
		</div>
		<c:if test="${!empty detail.adDetailPicture}">
			 <c:forEach items="${detail.adDetailPicture}" var="vo" varStatus="status">
					<input type="hidden" id="iconinfo${status.index }" value="${vo.detail_picture_Url }">
			</c:forEach>
        </c:if>

		<input type="hidden" id="softInfoDesc" value="${detail.detail_seventh }">
		<input type="hidden" id="softTitleDesc" value="${detail.detail_sixth }">
		<input type="hidden" id="resourceUrl" value="${detail.resourceUrl}">
		<input type="hidden" id="sUpdate" value="${detail.detail_second}">
		<input type="hidden" id="sVersion" value="${detail.detail_third}">
		<input type="hidden" id="sSize" value="${detail.detail_fourth}">
		<input type="hidden" id="sTitle" value="${detail.detail_first}">
		<input type="hidden" id="image" value="${detail.detail_icon_Url}">
		<input type="hidden" id="sCatagory" value="${detail.category_name}">		
		<input type="hidden" id="packageName" value="${detail.packageName}">
		<input type="hidden" id="resourceSize" value="${detail.resourceSize}">		
		<input type="hidden" id="adId" versionId }" />	
		<input type="hidden" id="uuid" value="${uuid }" />
		<input type="hidden" id="version" value="${yjf_version }" />
		<input type="hidden" id="channel" value="${channel }" />
		<input type="hidden" id="appId" value="${appId }" />
		<input type="hidden" id="terminalType" value="${terminalType }" />
		<input type="hidden" id="imsi" value="${imsi }" />
		<input type="hidden" id="isWap" value="${isWap }" />
		<input type="hidden" id="pageType" value="${pageType}" />
		
</body>
</html>

