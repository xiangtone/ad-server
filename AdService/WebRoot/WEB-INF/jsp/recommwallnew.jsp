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
<title>推荐墙</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/wall-tuijian.css" />
	<script src="${pageContext.request.contextPath}/js/base64.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js"></script>
<%-- <script src="${pageContext.request.contextPath}/js/canvasloader-min.js"></script> --%>
<script src="${pageContext.request.contextPath}/js/global.js"></script>
<script src="${pageContext.request.contextPath}/js/wall_tuijiannew.js"></script>
</head>
<body>
	<div id="recolist">
		<div class="toptitle">
			<div class="backtitle">
<!-- 				<img id="backlogo" class="backlogo" alt="backlogo" -->
<!-- 					src="../images/back.png" onclick="goBack()"> -->
			</div>
			<div class="wordtitle">精品推荐</div>
			<div class="divider2" id="divider"></div>
		</div>
		<div id="listcontent" class="listcontent">
			<c:if test="${!empty walllist}">
				<c:forEach items="${walllist}" var="vo" varStatus="status">
					<div id="box${status.index + 1}" class="box">
						<div id="boxLM${status.index + 1}" class="boxLM"
							onclick="boxMClick('#boxM${status.index + 1}','#boxR${status.index + 1}','#title${status.index + 1}','#catagory${status.index + 1}','#desc${status.index + 1}','#downlogo${status.index + 1}','#downloadcontent${status.index + 1}','#detail${status.index + 1}','#download${status.index + 1}',${status.index + 1},'${vo.id}','${vo.ad_type}','${vo.ad_url }')">
							<div id="boxL${status.index + 1}" class="boxL">
								<img class="logo" src="${vo.general.wall_icon_Url}">
							</div>
							<div id="boxM${status.index + 1}" class="boxM">
								<div id="title${status.index + 1}" class="title">${vo.title}</div>
								<div id="catagory${status.index + 1}" class="catagory">所属类别：${vo.category
									}| ${fn:substring(vo.general.wall_left_third, 3, -1)}</div>
								<div id="desc${status.index + 1}" class="desc">${vo.general.wall_desc}</div>
							</div>
						</div>


						<c:if test="${status.index % 3 == 1}">
							<div id="boxR${status.index + 1}" class="boxR">
								<div class="leftcontentR"></div>
								<div class="rightcontentR">
						</c:if>

						<c:if test="${status.index % 3 == 2}">
							<div id="boxR${status.index + 1}" class="boxRB">
								<div class="leftcontentB"></div>
								<div class="rightcontentB">
						</c:if>

						<c:if test="${status.index % 3 == 0}">
							<div id="boxR${status.index + 1}" class="boxRG">
								<div class="leftcontentG"></div>
								<div class="rightcontentG">
						</c:if>

						<div id="downlogo${status.index + 1}" class="downlogo"
							onclick="downloadFile('${vo.ad_type}','${vo.ad_url}','${vo.resourceUrl}','${vo.id }','${vo.title }','${vo.packageName}','${vo.resourceSize }','${vo.isDownload }','${status.index + 1}')">
							<div class="downimage">
								<img id="downimage${status.index + 1}">
							</div>
							<div class="downword" id="downword${status.index + 1}"></div>
						</div>
						<div id="downloadcontent${status.index + 1}"
							class="downloadcontent"
							onclick="getBoxR('#boxM${status.index + 1}','#boxR${status.index + 1}','#title${status.index + 1}','#catagory${status.index + 1}','#desc${status.index + 1}','#downlogo${status.index + 1}','#downloadcontent${status.index + 1}','#detail${status.index + 1}','#download${status.index + 1}')">
							<div class="downloadLeft">
								<img id="dd${status.index + 1}" src="../images/sanjiao.png">
							</div>
							<div class="downloadRight">
								<div class="downtitle">
									<font color="white">${vo.title}</font>
								</div>
								<div class="downcontent">

									<c:choose>
										<c:when test="${vo.star_level == 1}">
											<img class="star" src="../images/star01.png">
											<img class="star" src="../images/star02.png">
											<img class="star" src="../images/star02.png">
											<img class="star" src="../images/star02.png">
											<img class="star" src="../images/star02.png">
										</c:when>
										<c:when test="${vo.star_level == 1.5}">
											<img class="star" src="../images/star01.png">
											<img class="star" src="../images/star03.png">
											<img class="star" src="../images/star02.png">
											<img class="star" src="../images/star02.png">
											<img class="star" src="../images/star02.png">
										</c:when>
										<c:when test="${vo.star_level == 2}">
											<img class="star" src="../images/star01.png">
											<img class="star" src="../images/star01.png">
											<img class="star" src="../images/star02.png">
											<img class="star" src="../images/star02.png">
											<img class="star" src="../images/star02.png">
										</c:when>
										<c:when test="${vo.star_level == 2.5}">
											<img class="star" src="../images/star01.png">
											<img class="star" src="../images/star01.png">
											<img class="star" src="../images/star03.png">
											<img class="star" src="../images/star02.png">
											<img class="star" src="../images/star02.png">
										</c:when>
										<c:when test="${vo.star_level == 3}">
											<img class="star" src="../images/star01.png">
											<img class="star" src="../images/star01.png">
											<img class="star" src="../images/star01.png">
											<img class="star" src="../images/star02.png">
											<img class="star" src="../images/star02.png">
										</c:when>
										<c:when test="${vo.star_level == 3.5}">
											<img class="star" src="../images/star01.png">
											<img class="star" src="../images/star01.png">
											<img class="star" src="../images/star01.png">
											<img class="star" src="../images/star03.png">
											<img class="star" src="../images/star02.png">
										</c:when>
										<c:when test="${vo.star_level == 4}">
											<img class="star" src="../images/star01.png">
											<img class="star" src="../images/star01.png">
											<img class="star" src="../images/star01.png">
											<img class="star" src="../images/star01.png">
											<img class="star" src="../images/star02.png">
										</c:when>
										<c:when test="${vo.star_level == 4.5}">
											<img class="star" src="../images/star01.png">
											<img class="star" src="../images/star01.png">
											<img class="star" src="../images/star01.png">
											<img class="star" src="../images/star01.png">
											<img class="star" src="../images/star03.png">
										</c:when>
										<c:when test="${vo.star_level == 5}">
											<img class="star" src="../images/star01.png">
											<img class="star" src="../images/star01.png">
											<img class="star" src="../images/star01.png">
											<img class="star" src="../images/star01.png">
											<img class="star" src="../images/star01.png">
										</c:when>
										<c:otherwise>
											<img class="star" src="../images/star01.png">
											<img class="star" src="../images/star01.png">
											<img class="star" src="../images/star01.png">
											<img class="star" src="../images/star01.png">
											<img class="star" src="../images/star01.png">
										</c:otherwise>
									</c:choose>


									<span>${vo.star_level }</span> &nbsp; <a href="#"
										id="detail${status.index + 1}" class="detail"
										onclick="gotoDetail('${vo.id}','${vo.ad_type}','${vo.ad_url }');return false;"
										onmouseover="downEffect()" onmouseout="downUneffect()"> <span>查看详情></span>
									</a> &nbsp; <a href="#" id="download${status.index + 1}"
										class="download"
										onclick="downloadFile('${vo.ad_type}','${vo.ad_url}','${vo.resourceUrl}','${vo.id }','${vo.title }','${vo.packageName }','${vo.resourceSize }','${vo.isDownload }','${status.index + 1}');return false;"
										onmouseover="downEffect()" onmouseout="downUneffect()"> <span
										id="clickword${status.index + 1}"></span><img
										id="clickimage${status.index + 1}" class="downloadlogo">
									</a>
								</div>
							</div>
						</div>
					</div>
		</div>
	</div>
	<div class="divider" id="divider${status.index + 1 }"></div>
	<input type="hidden" id="idKey${status.index }" value="${vo.id }" />

	<input type="hidden" id="nameValue${status.index }" value="${vo.title}" />
	<input type="hidden" id="imageValue${status.index }"
		value="${vo.general.wall_icon_Url}" />
	<input type="hidden" id="adType${status.index }" value="${vo.ad_type}" />
	<input type="hidden" id="adUrl${status.index }" value="${vo.ad_url}" />

	<input type="hidden" id="package${status.index }"
		value="${vo.packageName}" />
	<script type="text/javascript">
			var index = ${status.index };
			var indexValue = ${status.index + 1 };
			var key = document.getElementById("idKey"+index).value;
			var nameValue = document.getElementById("nameValue"+index).value;
			var imageValue = document.getElementById("imageValue"+index).value;
			var adTypeValue = document.getElementById("adType"+index).value;
			var adUrlValue = document.getElementById("adUrl"+index).value;
			
			var name = document.getElementById("package"+index).value;
			ids += key + ",";
			//将id的值存储在本地
			storageData(key, nameValue+","+imageValue+","+adTypeValue+","+adUrlValue+","+indexValue);
			isInstalled(name, indexValue);
		</script>
	</c:forEach>
	</c:if>
	<c:if test="${empty walllist}">
	无记录！
  </c:if>
	</div>
	</div>
	<!-- Create a div which will be the canvasloader wrapper -->
	<div class="preload">
<!-- 		<div id="canvasloader-container" class="wrapper"></div> -->
<!-- 		<script type="text/javascript"> -->
<!-- 			var cl = new CanvasLoader('canvasloader-container');  -->
<!-- 		</script>  -->
	</div>
	<input type="hidden" id="uuid" value="${uuid }" />
	<input type="hidden" id="isOpen" value="${isSlide}" />
	<input type="hidden" id="pageType" value="${pageType }" />
	<input type="hidden" id="imageType" value="${imageType }" />
	<input type="hidden" id="version" value="${version }" />
	<input type="hidden" id="channel" value="${channel }" />
	<input type="hidden" id="appId" value="${appId }" />
	<input type="hidden" id="terminalType" value="${terminalType }" />
	<input type="hidden" id="imsi" value="${imsi }" />
	<input type="hidden" id="pageCount" value="${wallpage.pageCount }" />
	<input type="hidden" id="pageSize" value="${pageSize }" />
	<input type="hidden" id="isSlide" value="${isSlide }" />
</body>
</html>