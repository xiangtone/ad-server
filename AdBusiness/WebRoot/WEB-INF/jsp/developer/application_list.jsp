<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>行云广告平台</title>
		<%@include file="/WEB-INF/jsp/include/common/header.jsp"%>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/compiled/user-profile.css" type="text/css" media="screen" />
	    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/compiled/form-wizard.css" type="text/css" media="screen" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/compiled/index.css" type="text/css" media="screen" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/fuelux.wizard.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-hcheckbox.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.mousewheel.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.jscrollpane.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/poshytip/jquery.poshytip.min.js"></script>
		<script type="text/javascript">
			function submitForm() {
				var b=true;
				if(b){
					$("#appListForm").submit();
				}
			}	
			function pauseApplication(appId) {
				if(confirm("确定暂停该应用?")) {
					document.location = "pauseApplication.action?appId=" + appId;
				}
			}
			function onlineApplication(appId) {
				if(confirm("确定上线该应用?")) {
					document.location = "onlineApplication.action?appId=" + appId;
				}
			}
			function deleteApplication(appId) {
				if(confirm("确定删除该应用?")) {
					document.location = "deleteApplication.action?appId=" + appId;
				}
			}
		</script>
	</head>
	<body>
		<jsp:include page="/WEB-INF/jsp/include/dev/top.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/dev/left.jsp"></jsp:include>
		<div class="content">
			<div id="pad-wrapper" class="users-list">
				<div class="row header"><h3>应用列表</h3></div>
				<div class="table-products section">
					<form name="appListForm" id="appListForm" action="applicationList.action" method="post">
						<div class="row filter-block">
							<div class="ui-dropdown">状态：
								<select id="status" name="status">
									<option value="-100">全部</option>
									<option value="1" <c:if test="${status==1}">selected</c:if>>草稿</option>
									<option value="2" <c:if test="${status==2}">selected</c:if>>待审核</option>
									<option value="3" <c:if test="${status==3}">selected</c:if>>运行中</option>
									<option value="-1" <c:if test="${status==-1}">selected</c:if>>未通过</option>
									<option value="0" <c:if test="${status==0}">selected</c:if>>暂停</option>
									<option value="-2" <c:if test="${status==-2}">selected</c:if>>下线</option>
									<option value="-3" <c:if test="${status==-3}">selected</c:if>>终止</option>
								</select>
							</div>
							<button class="btn-flat new-product">查询</button>
						</div>
					</form>
				</div>
				<div class="row">
					<table class="table table-hover">
						<thead>
							<tr>
								<th class="col-md-3">应用名称 </th>
								<th class="col-md-3">平台</th>
								<th class="col-md-3">创建时间</th>
								<th class="col-md-3">应用类别</th>
								<th class="col-md-3">广告形式</th>
								<th class="col-md-3">状态</th>
								<th class="col-md-3">操作</th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${!empty applist}">
								<c:forEach items="${applist}" var="app" varStatus="status">
									<tr class="first">
										<td class="description">
											<a href="app_info.action?id=${app.id}";" style="font-size: 12px;color: red;">${app.name}</a>	
										</td>
										<td class="description">${app.os}</td>
										<td class="description"><fmt:formatDate value="${app.createTime}" type="date" dateStyle="long" pattern="yyyy-MM-dd" /></td>
										<td class="description">${app.categoryName}</td>
										<td class="description">${app.typeNames}</td>
										<td><span class="label label-success">${app.statusName}</span></td>
										<td>
											<ul class="actions">
												<c:if test="${app.statusName=='待审核' || app.statusName=='暂停'}">
													<li>编辑</li>
												</c:if>
												<c:if test="${app.statusName!='待审核' && app.statusName!='暂停'}">
													<li><a href="toUpdateApplication.action?id=${app.id}">编辑</a></li>
												</c:if>
												<c:if test="${app.statusName=='运行中'}">
													<li><a href="javascript:void(0);" onclick="pauseApplication('${app.id}');">暂停</a></li>
												</c:if>
												<c:if test="${app.statusName=='暂停'}">
													<li><a href="javascript:void(0);" onclick="onlineApplication('${app.id}');">运行</a></li>
												</c:if>
												<c:if test="${app.statusName!='运行中' && app.statusName!='暂停'}">
													<li>暂停</li>
												</c:if>
												<li class="last label-stop"><a href="javascript:void(0);" onclick="deleteApplication(${app.id});">删除</a></li>
											</ul>
										</td>
									</tr>
								</c:forEach>
							</c:if>
						</tbody>
					</table>
				</div>
				${pageInfo}
			</div>
		</div>
		<script type="text/javascript">
			selectedMenu("menu2_2");
		</script>
	</body>
</html>