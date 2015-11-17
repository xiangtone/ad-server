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
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/isojfwall.css" />
<script src="${pageContext.request.contextPath}/js/base64.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/canvasloader-min.js"></script>
<script src="${pageContext.request.contextPath}/js/global.js"></script>
<script src="${pageContext.request.contextPath}/js/loading.js"></script>
<script src="${pageContext.request.contextPath}/js/iosjfwall.js"></script>
</head>
<body>
	<div id="recolist">
	<!-- 顶部菜单 -->
		<div class="toptitle">
			<div class="backtitle" >
<!-- 					<img id="backlogo" class="backlogo" alt="backlogo" -->
<!-- 					src="../images/iosjfback.png" ><span>返回</span> -->
			</div>
			<div class="wordtitle">
				免费获取金币
			</div>
			<div class="nexttitle">
<!-- 				<img alt="titlelogo" src="../images/iosjflogo.png"> -->
			</div>
			<div id="sss">
				<div class="dividerup" id="divider"></div>
		    	<div class="divider" id="divider"></div>
		    </div>
		</div>
		<!-- 中间内容 -->
		<div id="listcontent" class="listcontent">
		 <div id="jifenData">
		  
		  <c:if test="${!empty walllist}">
		   <c:forEach items="${walllist}" var="vo" varStatus="status">
			<div  id="box${status.index + 1}" class="box">
<%-- 			 onclick="gotoDetail('${vo.id}','${vo.ad_type}','${vo.ad_url }')" --%>
			   <div id="boxLM${status.index + 1}" class="boxLM">
				<div id="boxL" class="boxL">
					<div class="boxlogo">
						<div class="logoimage"><img class="logo" src="${vo.general.wall_icon_Url}" ></div>
						<c:if test="${vo.new_app == 1 }">
							<div class="logonew"><img src="../images/iosjfnew.png"></div>
						</c:if>
						
					</div>
				</div>				
				<div id="boxM" class="boxM">
					<div id="title${status.index + 1 }" class="title"><span>${vo.title}</span>
					<c:if test="${vo.priority > 0 }">
						<img src="../images/iosjfhot.png">
					</c:if>
					</div>
					<div id="catagory${status.index + 1 }" class="catagory">
						<c:choose>
										<c:when test="${vo.star_level == 1}">
											<img class="star" src="../images/iosstar1.png">
											<img class="star" src="../images/iosstar4.png">
											<img class="star" src="../images/iosstar4.png">
											<img class="star" src="../images/iosstar4.png">
											<img class="star" src="../images/iosstar4.png">
										</c:when>
										<c:when test="${vo.star_level == 1.5}">
											<img class="star" src="../images/iosstar1.png">
											<img class="star" src="../images/iosstar3.png">
											<img class="star" src="../images/iosstar4.png">
											<img class="star" src="../images/iosstar4.png">
											<img class="star" src="../images/iosstar4.png">
										</c:when>
										<c:when test="${vo.star_level == 2}">
											<img class="star" src="../images/iosstar1.png">
											<img class="star" src="../images/iosstar2.png">
											<img class="star" src="../images/iosstar4.png">
											<img class="star" src="../images/iosstar4.png">
											<img class="star" src="../images/iosstar4.png">
										</c:when>
										<c:when test="${vo.star_level == 2.5}">
											<img class="star" src="../images/iosstar1.png">
											<img class="star" src="../images/iosstar2.png">
											<img class="star" src="../images/iosstar3.png">
											<img class="star" src="../images/iosstar4.png">
											<img class="star" src="../images/iosstar4.png">
										</c:when>
										<c:when test="${vo.star_level == 3}">
											<img class="star" src="../images/iosstar1.png">
											<img class="star" src="../images/iosstar2.png">
											<img class="star" src="../images/iosstar2.png">
											<img class="star" src="../images/iosstar4.png">
											<img class="star" src="../images/iosstar4.png">
										</c:when>
										<c:when test="${vo.star_level == 3.5}">
											<img class="star" src="../images/iosstar1.png">
											<img class="star" src="../images/iosstar2.png">
											<img class="star" src="../images/iosstar2.png">
											<img class="star" src="../images/iosstar3.png">
											<img class="star" src="../images/iosstar4.png">
										</c:when>
										<c:when test="${vo.star_level == 4}">
											<img class="star" src="../images/iosstar1.png">
											<img class="star" src="../images/iosstar2.png">
											<img class="star" src="../images/iosstar2.png">
											<img class="star" src="../images/iosstar2.png">
											<img class="star" src="../images/iosstar4.png">
										</c:when>
										<c:when test="${vo.star_level == 4.5}">
											<img class="star" src="../images/iosstar1.png">
											<img class="star" src="../images/iosstar2.png">
											<img class="star" src="../images/iosstar2.png">
											<img class="star" src="../images/iosstar2.png">
											<img class="star" src="../images/iosstar3.png">
										</c:when>
										<c:when test="${vo.star_level == 5}">
											<img class="star" src="../images/iosstar1.png">
											<img class="star" src="../images/iosstar2.png">
											<img class="star" src="../images/iosstar2.png">
											<img class="star" src="../images/iosstar2.png">
											<img class="star" src="../images/iosstar2.png">
										</c:when>
										<c:otherwise>
											<img class="star" src="../images/iosstar1.png">
											<img class="star" src="../images/iosstar2.png">
											<img class="star" src="../images/iosstar2.png">
											<img class="star" src="../images/iosstar2.png">
											<img class="star" src="../images/iosstar2.png">
										</c:otherwise>
									</c:choose>
						<span>${vo.category }|${fn:substring(vo.general.wall_left_third, 3, -1)}</span>
					</div>
					<div id="desc${status.index + 1 }" class="desc">${vo.general.wall_desc}</div>
				</div>
			  </div>
				<c:if test="${(status.index + 1) % 7 == 1}">
					<div id="boxR" class="boxR" onclick="downloadFile('${vo.app_url}','${vo.id}')">
				</c:if>

				<c:if test="${(status.index + 1) % 7 == 2}">
					<div id="boxR" class="boxR1" onclick="downloadFile('${vo.app_url}','${vo.id}')">
				</c:if>
				<c:if test="${(status.index + 1) % 7 == 3}">
					<div id="boxR" class="boxR2" onclick="downloadFile('${vo.app_url}','${vo.id}')">
				</c:if>
				
				<c:if test="${(status.index + 1) % 7 == 4}">
					<div id="boxR" class="boxR3" onclick="downloadFile('${vo.app_url}','${vo.id}')">
				</c:if>
				<c:if test="${(status.index + 1) % 7 == 5}">
					<div id="boxR" class="boxR4" onclick="downloadFile('${vo.app_url}','${vo.id}')">
				</c:if>
				<c:if test="${(status.index + 1) % 7 == 6}">
					<div id="boxR" class="boxR5" onclick="downloadFile('${vo.app_url}','${vo.id}')">
				</c:if>
				<c:if test="${(status.index + 1) % 7 == 0}">
					<div id="boxR" class="boxR6" onclick="downloadFile('${vo.app_url}','${vo.id}')">
				</c:if>

							<div id="downTop${status.index + 1 }" class="downTop"><span  class="downloadplus">+</span><span id="jf${status.index + 1}" class="downjifen">${vo.general.score}</span></div>
							<div class="downMiddle" id="downMiddle${status.index + 1 }"><span  class="downjinbi">${vo.general.scoreUnit}</span></div>
<%-- 							<c:choose> --%>
<%-- 								<c:when test="${(status.index + 1) == 1}"> --%>
<%-- 									<div class="downBottom"><span id="freeword${status.index + 1 }" class="freeword">免费观看</span><img id="jifendown${status.index + 1 }" class="download01" src="../images/iosjfwatch.png"></div> --%>
<%-- 								</c:when> --%>
<%-- 								<c:when test="${(status.index + 1) == 2}"> --%>
<%-- 									<div class="downBottom"><span id="freeword${status.index + 1 }" class="freeword">免费注册</span><img id="jifendown${status.index + 1 }" class="download01" src="../images/iosjfregist.png"></div> --%>
<%-- 								</c:when> --%>
<%-- 								<c:otherwise> --%>
									<div class="downBottom" id="downBottom${status.index + 1 }"><span id="freeword${status.index + 1 }" class="freeword">免费下载</span><img id="jifendown${status.index + 1 }" class="download01" src="../images/iosjfdownload.png"></div>
<%-- 								</c:otherwise> --%>
<%-- 							</c:choose> --%>

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
				ids += key + ",";
				//将id的值存储在本地
// 				storageData(key, nameValue+","+imageValue+","+adTypeValue+","+adUrlValue+","+indexValue);
			</script>
		  </c:forEach>
		</c:if>
		<c:if test="${empty walllist}">
				无记录！
  		</c:if>
  	  </div>
  	  <div id="bottomId2">
		<div class="bottomleft" onclick="getScoreDesc()"><span >获取帮助</span><img src="../images/iosjfhelp.png"></div>
		<div class="bottomright" onclick="toFeedBack()"><span>问题反馈</span><img src="../images/iosjfwrite.png"></div>
	 </div>
	 </div>
	 
	
	<!-- 底部菜单 -->
	<div id="bottomId">
		<div class="bottomleft" onclick="getScoreDesc()"><span>获取帮助</span><img src="../images/iosjfhelp.png"></div>
		<div class="bottomright" onclick="toFeedBack()"><span>问题反馈</span><img src="../images/iosjfwrite.png"></div>
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
	<input type="hidden" id="imsi" value="${imsi }" />
	<input type="hidden" id="pageCount" value="${wallpage.pageCount }" />
	<input type="hidden" id="pageSize" value="${pageSize }" />
	<input type="hidden" id="coopinfo" value="${coopinfo }" />
	<input type="hidden" id="openudid" value="${openudid }" />
	<input type="hidden" id="idfa" value="${idfa }" />
	<input type="hidden" id="idfv" value="${idfv }" />
	<input type="hidden" id="os" value="${os }" />
</body>
</html>