<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>运营管理后台权限设置</title>
<link href="${pageContext.request.contextPath}/css/common.css?v=${version}" rel="stylesheet" type="text/css" />
<script type="text/javascript"src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript"src="${pageContext.request.contextPath}/js/manage/managePurview.js?v=${version}"></script>
</head>
<body>
<div class="main admin_main" style="background:#F6F6F6">
	<div class="content clearfix">
		<form action="manage!authorize.do" method="post">
		<input name="userId" id="userId" type="hidden" value="${manageUserVo.id }" />
			<div class="content_right admin_right">
				<div>
					<input type="button" value="添加" onclick="add('${app.id}')" style="cursor: pointer;" />
					<input type="button" value="删除" onclick="edit('${app.id}')" style="cursor: pointer;" /> 
				</div>
				<div class="main_table">
						<table width="100%" cellpadding="0" cellspacing="1" id="tb"
							class="font_stat">
							<tr class="tr_td">
								<th><input id="checkbox" type="checkbox" value="${k.id}" onclick="selectAll(this.checked);" /></th>
								<th width="8%">名称</th>
								<th>编号</th>
								<th width="8%">上线时间</th>
								<th width="4%">状态</th>
							</tr>
							<c:if test="${!empty list}">
								<c:forEach items="${list}" var="vo" varStatus="status">
									<tr>
										<td style="text-align: center;"><input id="checkbox" name="app" type="checkbox" value="${k.id}" /></td>
										<td align="center">${vo.name}</td>
										<td align="center">
											${vo.code}
										</td>
										<td align="center"><fmt:formatDate
												value="${app.createTime}" pattern="yyyy-MM-dd" />
										</td>
										<td align="center">${app.statusName}</td>
									</tr>
								</c:forEach>
							</c:if>
							<c:if test="${empty list}">
								<tr>
									<td align="center" colspan="9" style="text-align: center;">暂无记录！</td>
								</tr>
							</c:if>
						</table>
					</div>
					${pageInfo.pageInfoStr}
				</div>
				</form>
			</div>
		</div>
</body>
</html>
