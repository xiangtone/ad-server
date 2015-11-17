<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="escore" uri="/WEB-INF/tld/spring.tld"%>
<%@ taglib prefix="ax" uri="/WEB-INF/tld/AlanXUpload.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>运营管理后台开发者应用审核</title>
<link href="${pageContext.request.contextPath}/css/common.css?v=${version}"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/regist/validate.css?v=${version}"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js?v=${version}" charset="utf-8"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/escoreCategory.js?v=${version}"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/loading/loading.js?v=${version}"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/regist/validate.pack.js?v=${version}"></script>

</head>
<body>
	<div class="main" style="background: #F6F6F6">
		<div class="content clearfix">
			<div class="content_right admin_right">
					<table width="500px;" border="1px;" style="display: none;">
						<tr>
							<td height="30" align="right" width="10px;">活动名称:</td>
							<td align="left" width="30px;">${vo.campaign_name}</td>
							<td align="right" width="30px;">平台类型:</td>
							<td align="left" width="30px;">${vo.os}</td>
						</tr>
						<tr>
							<td align="right">活动类型：</td>
							<td>
								<c:choose>
									<c:when test="${vo.category_id==0}">
										下载类															
									<c:choose>
									<c:when test="${vo.category_id == 1}">
										电商
									</c:when>
									<c:when test="${vo.category_id ==2}">
								     	 品牌
									</c:when>
									<c:when test="${vo.category_id ==3}">
								     	 游戏
									</c:when>
									<c:when test="${vo.category_id ==4}">
								      	系统工具
									</c:when>
									<c:otherwise>
										生活工具
									</c:otherwise>
									</c:choose>
									</c:when>
									<c:otherwise>
									 注册类
								</c:otherwise>
								</c:choose>
							</td>
							<td align="right">计费方式：</td>
							<td align="left">${vo.charge_type}</td>
						</tr>
						<tr>
							<td align="right">排期时间：</td>
							<td colspan="3">
								${vo.plan_start}至${vo.plan_end}
							</td>
						</tr>
						<tr>
							<td align="right">活动要求:</td>
							<td colspan="3">
								${vo.campaign_required}
							</td>
						</tr>
						<tr>
							<td align="right">接入单价:</td>
							<td align="left">${vo.price} 元</td>
							<td align="right">总预算:</td>
							<td align="left">${vo.budget}</td>
						</tr>
						<tr>
							
						</tr>
						<tr>
							<td align="right">终端类型:</td>
							<td colspan="3">
								<c:choose>
									<c:when test="${vo.terminal_type == '1'}">
										${vo.terminal_type_name}
									</c:when>
									<c:otherwise>
										  不限制
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
						<tr>
							<td align="right">运营商:</td>
							<td colspan="3">
								<c:choose>
									<c:when test="${vo.operat_agencies == '1'}">
										${vo.operat_agencies_name}
									</c:when>
									<c:otherwise>
									  不限制
									</c:otherwise>
								</c:choose>
							</td>

						</tr>
						<tr>
							<td align="right">手机品牌:</td>
							<td colspan="3">
								<c:choose>
									<c:when test="${vo.phone_brand == '1'}">
										${vo.phone_brand_name}
									</c:when>
									<c:otherwise>
										 不限制
									</c:otherwise>
								</c:choose>
							</td>

						</tr>
						<tr>
							<td align="right">应用类型:</td>
							<td colspan="3">
								<c:choose>
									<c:when test="${vo.app_type == '1'}">
										${vo.app_type_name}
									</c:when>
									<c:otherwise>
										  不限制
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
						<tr>
							<td align="right">时段定向:</td>
							<td colspan="3">
								<c:choose>
									<c:when test="${vo.time_directional == '1'}">
										${vo.time_directional_name}
									</c:when>
									<c:otherwise>
										 不限制
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
						<tr>
							<td align="right">区域定向:</td>
							<td colspan="3">
								<c:choose>
									<c:when test="${vo.area_directional == '1'}">
										${vo.area_directional_name}
									</c:when>
									<c:otherwise>
										 不限制
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
					</table>
					<div style="display: block;height: 500px;">
						<form id="myform" action="" method="post">
							
							<table width="500px;" border="1px;">
								<tr>
									<td width="30%" align="right">是否通过：</td>
									<td width="70%">
										<input name="ispass" type="radio" checked="checked" value="1" />是
										<input name="ispass" type="radio" value="0" />否
									</td>
								</tr>
								<tr>
									<td align="right">意见：</td>
									<td>
										<textarea rows="6" cols="50" name=""></textarea>
									</td>
								</tr>
						</table>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
