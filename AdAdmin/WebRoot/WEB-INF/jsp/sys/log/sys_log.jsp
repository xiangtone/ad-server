<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>运营管理后台系统日志</title>
<link href="${pageContext.request.contextPath}/css/common.css?v=${version}" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js?v=${version}"></script>
</head>
<body>
	<div class="main">
		<div class="content clearfix">
			<div class="admin_right">
				<div class="content_right content_new">
					<div class="bord_bom1px">运营管理系统日志</div>
					<form action="manage!listManagerLog.do" method="post">
					<!--  	<table>
							<tr>
								<td><input name="value" type="text" value="${bean.value }" /></td>
								<td><select name="keyword">
										<option value="user_name">用户名</option>
								</select></td>
								<td><select name="roleId">
										<option value="">角色</option>
										<c:forEach items="${roleList}" var="r">
											<c:choose>
												<c:when test="${r.id == roleId}">
													<option value="${r.id }" selected="selected">${r.name}</option>
												</c:when>
												<c:otherwise>
													<option value="${r.id }">${r.name}</option>
												</c:otherwise>
											</c:choose>
										</c:forEach>
								</select></td>
								<td><select name="menu">
										<option value="">菜单</option>
										<c:forEach items="${purviewList}" var="p">
											<c:choose>
												<c:when test="${p.name == menu}">
													<option value="${p.resName}" selected="selected">${p.name}</option>
												</c:when>
												<c:otherwise>
													<option value="${p.name }">${p.name}</option>
												</c:otherwise>
											</c:choose>
										</c:forEach>
								</select></td>
							</tr>
						</table>-->
						<table>
							<tr>
								<td>开始时间</td>
								<td>
									<input name="beginDate" id="beginTime" type="text" value="${bean.beginDate}" class="Wdate" readonly="readonly" onfocus="WdatePicker()" /></td>
								<td>结束时间</td>
								<td>
									<input name="endDate" id="endTime" type="text"	value="${bean.endDate}" class="Wdate" readonly="readonly" onfocus="WdatePicker()"  /></td>
								<td><input name="" type="submit" value="查询" />
								</td>
							</tr>
						</table>
						</form>
						<div class="main_table">
						<table width="100%" cellpadding="0" cellspacing="1" class="font_stat" id="tb">
							<tr class="tr_td">
								<th>操作时间</th>
								<th>用户名</th>
								<th>角色</th>
								<th>菜单</th>
								<th>具体操作</th>
							</tr>
							<c:if test="${!empty list}">
								<c:forEach items="${list}" var="k">
									<tr>
										<td>
											<fmt:formatDate value="${k.createTime}" pattern="yyyy-MM-dd HH:mm:ss" />
										</td>
										<td>${k.userName }</td>
										<td>${k.roleName }</td>
										<td>${k.menu }</td>
										<td>${k.msg }</td>
									</tr>
								</c:forEach>
							</c:if>
							<c:if test="${empty list}">
								<tr>
									<td align="center" colspan="15" style="text-align: center;">暂无记录！</td>
								</tr>
							</c:if>
						</table>
						</div>
						<div>${pageInfo.pageInfoStr}</div>
				</div>
			</div>
		</div>

	</div>
</body>
</html>
