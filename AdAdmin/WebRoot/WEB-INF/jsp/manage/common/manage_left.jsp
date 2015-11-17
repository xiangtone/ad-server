<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/menu.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/manage/menu.js"></script>
<!--  <script type="text/javascript" src="${pageContext.request.contextPath}/js/loading.js"></script>-->
<script type="text/javascript">
	
</script>

<style type="text/css">
<!--
.STYLE1 {
	font-size: 12px;
	color: #FFFFFF;
}

.STYLE3 {
	font-size: 12px;
	color: #033d61;
}
-->
</style>

<style type="text/css">
.menu_div {
	float: left;
	color: #fff;
	font-size: 13px;
	background: #F6F6F6;
	font-family: Arial, Helvetica, sans-serif;
	display: block;
	width: 180px;
}

.menu_title SPAN {
	FONT-WEIGHT: bold;
	LEFT: 3px;
	COLOR: #ffffff;
	POSITION: relative;
	TOP: 2px
}

.menu_title2 SPAN {
	FONT-WEIGHT: bold;
	LEFT: 3px;
	COLOR: #FFCC00;
	POSITION: relative;
	TOP: 2px
}

.font_13 {
	font-size: 13px;;
}

.dropdown ul li a {
	color: #1068c2;
}

.dropdown ul li a:VISITED {
	color: #1068c2;
}

.dropdown ul li a:HOVER {
	color: red;
}
</style>
<div class="menu_div">
<fieldset style="width: 170px; border: 0;">
<div id="scroll_bar" style="font-size: 13px; scrollbar-face-color: #1187ec; overflow-x: hidden;width: 183; scrollbar-shadow-color: #188aeb; direction: ltr;height: 100%;">
<div id="main">
<ul class="container">
	<c:forEach items="${myPurviewMap}" var="entry">
		<li class="menu">
			<ul>
				<li class="menu_button">
					<a href="#" class="blue">${entry.key.name}<span></span></a>
				</li>
				<li class="dropdown">
					<ul id="numue_list">
						<c:forEach items="${entry.value}" var="value">
							<c:choose>
								<c:when	test="${(sessionScope.curCheckMenu!=null)&&(sessionScope.curCheckMenu==value.url)}">
									<li>
										<a id="on_check" class="font_13" style="color: orange;"	href="javascript:void(0);" url="${pageContext.request.contextPath}${value.url}">${value.name}</a>
									</li>
								</c:when>
								<c:otherwise>
									<li>
										<a class="font_13" style="color: 1068c2;" url="${pageContext.request.contextPath}${value.url}" href="javascript:void(0);">${value.name}</a>
									</li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</ul>
				</li>
			</ul>
		</li>
	</c:forEach>
</ul>
<div class="clear"></div>
</div>
</div>
</fieldset>
</div>