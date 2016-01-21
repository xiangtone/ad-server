<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/include/common/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title><%=company%>广告平台</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/compiled/user-profile.css" type="text/css" media="screen" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/compiled/form-wizard.css" type="text/css" media="screen" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/compiled/index.css" type="text/css" media="screen" />
		<script type="text/javascript">
			function cancle_apply(id) {
				if (window.confirm("您确认取消提款申请？")) {
					var url = "${pageContext.request.contextPath}/developerCancelApplyMoney.action";
					$.ajax({
						url : url,
						type : 'POST',
						data : 'id=' + id,
						dataType : 'text',
						success : function(data) {
							if (data == "true") {
								alert("撤销成功！");
								document.location = document.location;
							}
						}
					});
				}
			}
		</script>
	</head>
	<body>
		<jsp:include page="/WEB-INF/jsp/include/dev/top.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/dev/left.jsp"></jsp:include>
		<div class="content">
			<div id="pad-wrapper" class="users-list">
				<div class="row header">
					<h3>提款记录</h3>
				</div>
				<div class="table-products section">
					<div class="row filter-block"></div>
					<div class="row">
						<table class="table table-hover">
							<thead>
								<tr>
									<th class="col-md-3">提款时间</th>
									<th class="col-md-3">提款金额</th>
									<th class="col-md-3">到账时间</th>
									<th class="col-md-3">实际到账</th>
									<!-- <th class="col-md-3">账户余额</th> -->
									<th class="col-md-3">发票</th>
									<th class="col-md-3">状态</th>
									<th class="col-md-3">操作</th>
									<th class="col-md-3">备注</th>
								</tr>
							</thead>
							<tbody>
								<c:if test="${!empty list}">
									<c:forEach items="${list}" var="aml" varStatus="status">
										<tr>
											<td><fmt:formatDate value="${aml.createTime}" type="date" dateStyle="long" pattern="yyyy-MM-dd" /></td>
											<td class="description">${aml.applyMoney}</td>
											<td>
												<c:choose>
													<c:when test="${aml.status==-3}"></c:when>
													<c:otherwise>
														<fmt:formatDate value="${aml.managerTime}" type="date" dateStyle="long" pattern="yyyy-MM-dd" />
													</c:otherwise>
												</c:choose>
											</td>
											<td class="description"><c:choose>
													<c:when test="${aml.status == 2}">
														<fmt:formatNumber value="${aml.managerMoney-aml.finance_tax-aml.finance_dues}" pattern="##.00" maxFractionDigits="2" />
													</c:when>
													<c:otherwise>0.0</c:otherwise>
												</c:choose>
											</td>
											<!--<td>${aml.finance_income}</td> -->
											<td>${aml.invoice_name}</td>
											<td>
												<c:choose>
													<c:when test="${aml.status == 0}">
														<span class="label label-info">${aml.statusName}</span>
													</c:when>
													<c:when test="${aml.status == 1}">
														<span class="label label-info">${aml.statusName}</span>
													</c:when>
													<c:when test="${aml.status == -1}">
														<span class="label label-stop">${aml.statusName}</span>
													</c:when>
													<c:when test="${aml.status == 2}">
														<span class="label label-success">${aml.statusName}</span>
													</c:when>
													<c:when test="${aml.status == -2}">
														<span class="label label-stop">${aml.statusName}</span>
													</c:when>
													<c:otherwise>
														<span class="label label-stop">${aml.statusName}</span>
													</c:otherwise>
												</c:choose>
											</td>
											<td>
												<c:choose>
													<c:when test="${aml.status==0}">
														<a href="javascript:void(0)" title="撤销申请" class="btn btn-orange" onclick="cancle_apply('${aml.id}');">撤销申请</a>
													</c:when>
													<c:otherwise>
														<a href="javascript:void(0)" class="btn" title="不能操作">撤销申请</a>
													</c:otherwise>
												</c:choose>
											</td>
											<td>
												<c:choose>
													<c:when test="${aml.managerDesc==null}">--</c:when>
													<c:otherwise>${aml.managerDesc}</c:otherwise>
												</c:choose>
											</td>
										</tr>
									</c:forEach>
								</c:if>
								<c:if test="${empty list}">
									<tr>
										<td align="center" colspan="13" style="text-align: center;">暂无记录！</td>
									</tr>
								</c:if>
							</tbody>
						</table>
					</div>
					${pageInfo}
				</div>
			</div>
		</div>
		<script type="text/javascript">
			selectedMenu("menu4_3");
		</script>
	</body>
</html>