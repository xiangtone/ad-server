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
	<title>IOS积分墙详情页</title> 
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/detail-ios-jf.css" />
	<script src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js"></script>	
	<script src="${pageContext.request.contextPath}/js/jsbn.js"></script>
	<script src="${pageContext.request.contextPath}/js/prng4.js"></script>
	<script src="${pageContext.request.contextPath}/js/rng.js"></script>
	<script src="${pageContext.request.contextPath}/js/rsa.js"></script>
	<script src="${pageContext.request.contextPath}/js/global.js"></script>
	<script src="${pageContext.request.contextPath}/js/detail-ios-jf.js"></script>
</head> 
<body> 


<div class="toptitle">
			<div class="backtitle" onclick="goBack()">
				<img id="backlogo" class="backlogo" alt="backlogo"
					src="../images/tjiosleft.png" ><span>返回</span>
			</div>
			<div class="wordtitle">
				安装应用获取奖励
			</div>
			<div class="nexttitle" onclick="requestDetail()">
				<span>下一个</span><img alt="titlelogo" src="../images/tjiosright.png">
			</div>
</div>

	<div id="detailcontent" class="detailcontent">	
		<div id="content" class="content"> 	
		 	<div class="leftcontent" >
		 		<img  class="logo" id="logo" src="../images/003.png">
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
					
			<div class="reflectdown" ></div>
		</div>
		
		<div class="softdesc">
			<span id="softTitle">应用简介</span>
		</div>
		
		
		<div class="scoreDesc" id="softInfo" >
			<div class="desccontent" id="softContent">
				
			</div>
		</div>
		
		<div id="extend" class="extend"><img id="extendImage" class="extendImage" src="../images/tjzhankai.jpg" onclick="senfe()"></div>
		<div class="divider2" id="divider"></div>
		<div id="other" class="other">下载此应用的用户还下载了:</div>
		<div id="othercontent" class="othercontent">
			<div id="otherfirst" class="leftcontent" style="-webkit-tap-highlight-color:rgba(0,0,0,0);"><img  class="otherimage" id="otherimage1" ><div id="otherword1"></div></div>
			<div id="othersecond" class="leftcontent" style="-webkit-tap-highlight-color:rgba(0,0,0,0);"><img  class="otherimage" id="otherimage2" ><div id="otherword2"></div></div>
			<div id="otherthird" class="leftcontent" style="-webkit-tap-highlight-color:rgba(0,0,0,0);"><img  class="otherimage" id="otherimage3" ><div id="otherword3"></div></div>
			<div id="otherfouth" class="leftcontent" style="-webkit-tap-highlight-color:rgba(0,0,0,0);"><img  class="otherimage" id="otherimage4" ><div id="otherword4"></div></div>
		</div>
		<div id="blank" class="blank" style="height: 70px"></div>
		<div id="bottomId2">
				<div id="bigDownload2" class="bigdownload2"><img  id="downloadlogo" class="downloadlogo" src="../images/iostjdown.png"><span class="downloadword">立即下载</span></div>
				<div id="shareDiv2" class="shareDiv2"><img id="share" class="share" src="../images/tjshare.png" height="30"></div>
		</div>
</div>

		<div id="bottomId">
				<div id="bigDownload" class="bigdownload"><img  id="downloadlogo" class="downloadlogo" src="../images/iostjdown.png"><span class="downloadword">立即下载</span></div>
				<div id="shareDiv" class="shareDiv"><img id="share" class="share" src="../images/tjshare.png" height="30"></div>
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
		
		<input type="hidden" id="adId" value="${adId }" />
		<input type="hidden" id="sCatagory" value="${detversiony_name}">
		<input type="hidden" id="uuid" value="${uuid }" />
		<input type="hidden" id="version" value="${yjf_version }" />
		<input type="hidden" id="channel" value="${channel }" />
		<input type="hidden" id="appId" value="${appId }" />
		<input type="hidden" id="terminalType" value="${terminalType }" />
		<input type="hidden" id="imsi" value="${imsi} }" />
		<input type="hidden" id="pageType" value="${pageType} }" />
		<input type="hidden" name="RSAPublicKey" id="RSAPublicKey"
		value="dd6be3d4de56287b8c3616b33bc1b7a5a2bb9148252140262420ee047f83b3165fb7674a759d60c24b71fd5437c7810f127f2c4370c2d4bdfcb55c08f1b3c715b7b2f57228e78e34039d2b967f54a58e345bc91e3dd54c7bea86d73c9e2de968736bf2b97f50bea891aa3519ae7238d76dff57cabba7cc0d370775657f3b5c83" />
		
</body>
</html>

