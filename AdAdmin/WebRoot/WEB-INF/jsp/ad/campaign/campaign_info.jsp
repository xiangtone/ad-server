<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="escore" uri="/WEB-INF/tld/spring.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>${business_title}</title>
<link type="text/css" rel="stylesheet" id="cssfile"	href="<%=request.getContextPath()%>/css/landscape.css?v=${version}" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js?v=${version}" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/escoreCategory.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/loading/loading.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/regist/validate.pack.js?v=${version}"></script>
<script type="text/javascript">
	var dg;
	$(document).ready(function(){
		dg = frameElement.lhgDG;
		dg.SetCancelBtn("关闭",function(){
			dg.cancel();
		});
	});
</script>
</head>
<body>
	<div id="content" style="padding: 0; height: 420px;">
		<div class="main_right" style="height: 550px; overflow: hidden; overflow-y: auto;">
			<table width="950" style="border: 0;">
				<tr>
					<td>
						<table width="950" border="0">
							<tr>
								<td width="75" bgcolor="#cfe1e2" align="right">活动名称：</td>
								<td width="220" align="left">${vo.campaign_name}</td>
								<td width="79" bgcolor="#cfe1e2" align="right">平台类型：</td>
								<td width="212" align="left">${vo.os}</td>
							</tr>
							<tr>
								<td bgcolor="#cfe1e2" align="right">活动类型：</td>
								<td align="left">
									<c:choose>
										<c:when test="${vo.campaign_type == 0}">
											下载类
										</c:when>
										<c:when test="${vo.campaign_type ==1}">
									     	 注册类
										</c:when>
									</c:choose>
								</td>
								<td bgcolor="#cfe1e2" align="right">计费方式：</td>
								<td align="left">${vo.charge_type}</td>
							</tr>
							<tr>
								<td bgcolor="#cfe1e2" align="right">总预算：</td>
								<td align="left">${vo.budget}</td>
								<td bgcolor="#cfe1e2" align="right">接入单价：</td>
								<td align="left">${vo.price} 元</td>
							</tr>
							<tr>
								<td bgcolor="#cfe1e2" align="right">排期时间：</td>
								<td align="left">
									<fmt:formatDate value="${vo.plan_start}" type="date" dateStyle="long" pattern="yyyy-MM-dd"/>
									至
									<fmt:formatDate value="${vo.plan_end}" type="date" dateStyle="long" pattern="yyyy-MM-dd"/>
								</td>
								<td bgcolor="#cfe1e2" align="right">活动分类：</td>
								<td align="left">
									<c:forEach items="${sList}" var="entry">
										<c:if test="${vo.category_id eq entry.id}">${entry.name}</c:if>
									</c:forEach>
								</td>
							</tr>
							<tr>
								<td bgcolor="#cfe1e2" align="right">确认方式：</td>
								<td align="left">${vo.confirm_mode_name}</td>
							</tr>
							<tr>
								<td bgcolor="#cfe1e2" align="right">活动要求：</td>
								<td colspan="3" align="left">
									<textarea rows="5" cols="60">${vo.campaign_required}</textarea>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>