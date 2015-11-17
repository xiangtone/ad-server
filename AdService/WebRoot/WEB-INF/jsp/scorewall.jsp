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
<title>积分墙</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/score-wall.css" />
<script src="${pageContext.request.contextPath}/js/base64.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/canvasloader-min.js"></script>
<script src="${pageContext.request.contextPath}/js/global.js"></script>
<script src="${pageContext.request.contextPath}/js/scorewall.js"></script>
</head>
<body>
	<div id="recolist">
	<!-- 顶部菜单 -->
		<div class="toptitle">
		   <div class="titletop">
				<div class="topleft"><img id="backlogo" class="backlogo" alt="backlogo" src="../images/jifenback.png" onclick="goBack()"></div>
				<div class="topright"><span class="titleword">安装应用获得奖励</span></div>
			</div>
			<div class="titlebottom">
				<div id="titleleft" class="titleleft" onclick="switchPage(true, 'titleleft', 'titleright')"><img class="titleleftlogo" src="../images/jifenplus.png"><span class="bookword">获取积分</span></div>
				<div id="titleright" class="titleright" onclick="switchPage(false, 'titleleft', 'titleright')"><span class="bookword">我的签到簿</span><img  class="titlerightlogo" src="../images/jifenbook.png"></div>
			</div>
			<div class="divider2" id="divider"></div>
		</div>
		
		<!-- 中间内容 -->
		<div id="listcontent" class="listcontent">
		 <div id="jifenData">
		  <c:if test="${!empty walllist}">
		   <c:forEach items="${walllist}" var="vo" varStatus="status">
			<div  id="box${status.index + 1}" class="box" onclick="changeColor('#box${status.index + 1}','#title${status.index + 1}','#catagory${status.index + 1}','#desc${status.index + 1}','#downTop${status.index + 1}','#freeword${status.index + 1}','jifendown${status.index + 1}')">
				<div class="leftColor"></div>
				<div id="boxLM${status.index + 1}" class="boxLM" onclick="gotoDetail('${vo.id}','${vo.ad_type}','${vo.ad_url }')">
				<div id="boxL" class="boxL">
					<img class="logo" src="${vo.general.wall_icon_Url}" >
				</div>
				
				<div id="boxM" class="boxM" >
					<div id="title${status.index + 1 }" class="title">${vo.title}</div>
					<div id="catagory${status.index + 1 }" class="catagory">所属类别：${vo.category }|
								${fn:substring(vo.general.wall_left_third, 3, -1)}</div>
					<div id="desc${status.index + 1 }" class="desc">${vo.general.wall_desc}</div>
				</div>
				</div>
				<div id="boxR" class="boxR" onclick="downloadFile('${vo.resourceUrl}','${vo.id }','${vo.title }','${vo.packageName}','${vo.resourceSize }')">
						<div class="vertical_divider"><img src="../images/vertical_divider.jpg"></div>
						<div>
							<div id="downTop${status.index + 1 }" class="downTop"><span  class="downloadplus">+</span><span id="jf${status.index + 1}" class="downjifen">${vo.general.score}</span></div>
							<div class="downMiddle"><span  class="downjinbi">${vo.general.scoreUnit}</span></div>
							<div class="downBottom"><span id="freeword${status.index + 1 }" class="freeword">免费下载</span><img id="jifendown${status.index + 1 }" class="download01" src="../images/jifendownload01.png"></div>
						</div>
				</div>
			</div>
			<div class="divider" id="divider${status.index + 1 }"></div>
			<input type="hidden" id="idKey${status.index }" value="${vo.id }"/>
			<input type="hidden" id="nameValue${status.index }" value="${vo.title}"/>
			<input type="hidden" id="imageValue${status.index }" value="${vo.general.wall_icon_Url}"/>
			<input type="hidden" id="adType${status.index }" value="${vo.ad_type}"/>
			<input type="hidden" id="adUrl${status.index }" value="${vo.ad_url}"/>
			<script type="text/javascript">
				var index = ${status.index };
				var indexValue = ${status.index + 1 };
				var key = document.getElementById("idKey"+index).value;
				var nameValue = document.getElementById("nameValue"+index).value;
				var imageValue = document.getElementById("imageValue"+index).value;
				var adTypeValue = document.getElementById("adType"+index).value;
				var adUrlValue = document.getElementById("adUrl"+index).value;
				if(indexValue == 10){
					ids += key;
				}else{
					ids += key + ",";
				}
				//将id的值存储在本地
				storageData(key, nameValue+","+imageValue+","+adTypeValue+","+adUrlValue+","+indexValue);
			</script>
		  </c:forEach>
		</c:if>
		<c:if test="${empty walllist}">
				无记录！
  		</c:if>
  	  </div>
  	  <div id="signData" style="display: none;" >
  	  	<div id="signInfo" class="signInfo">每天使用应用，即可获取响应奖励</div>
  	  	<div id="divider0" class="divider" ></div>
  	  	<div id="emptyInfo" class="emptyInfo">
  	  		<div>没有可签到的应用,</div>
  	  		<div>快去安装吧!</div>
  	  	</div>
  	  </div>
  	   <div id="bottomId2" >
			<span class="bottomleft">赠送积分说明</span>
			<span class="bottomright">意见反馈</span>
	   </div>
	 </div>
	 
	
	<!-- 底部菜单 -->
	<div id="bottomId">
		<span class="bottomleft">赠送积分说明</span>
		<span class="bottomright">意见反馈</span>
	</div>

	</div>
	<!-- Create a div which will be the canvasloader wrapper -->
	<div class="preload">
		<div id="canvasloader-container" class="wrapper"></div>
		<script type="text/javascript">
			var cl = new CanvasLoader('canvasloader-container'); 
		</script> 
	</div>
	<input type="hidden" id="uuid" value="${uuid }" />
	<input type="hidden" id="isOpen" value="1" />
	<input type="hidden" id="pageType" value="${pageType }" />
	<input type="hidden" id="imageType" value="${imageType }" />
	<input type="hidden" id="version" value="${version }" />
	<input type="hidden" id="channel" value="${channel }" />
	<input type="hidden" id="appId" value="${appId }" />
	<input type="hidden" id="terminalType" value="${terminalType }" />
	<input type="hidden" id="imsi" value="${imsi} }" />
	<input type="hidden" id="pageCount" value="${wallpage.pageCount }" />
	<input type="hidden" id="signPageCount" value="" />
	<input type="hidden" id="pageSize" value="${pageSize }" />
</body>
</html>