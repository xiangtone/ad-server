<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/util/config_manager.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title><%=company%>广告平台</title>
		<%@include file="/WEB-INF/jsp/include/common/header.jsp"%>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/compiled/index.css" type="text/css" media="screen" />
		<style type="text/css">
			.stat{
				float: left;
			}
		
		</style>
	</head>
	<body style="overflow-x: hidden;">
		<div id="all">
			<jsp:include page="/WEB-INF/jsp/include/dev/top.jsp"></jsp:include>
			<div>
				<jsp:include page="/WEB-INF/jsp/include/dev/left.jsp"></jsp:include>
				<div class="content">
					<div id="main-stats" style="width: 100%;">
						<div class="row stats-row">
							<div class="col-md-3 col-sm-3 stat">
								<span class="date">累计收入</span>
								<div class="data">
									<span class="number">￥<fmt:formatNumber value="${vo.total_income}" pattern="0.00"></fmt:formatNumber></span>元
								</div>
							</div>
							<div class="col-md-3 col-sm-3 stat">
								<span class="date">可提款收入</span>
								<div class="data">
									<span class="number">￥<fmt:formatNumber value="${vo.finance_income}" pattern="0.00"></fmt:formatNumber></span>元
								</div>
							</div>
							<div class="col-md-3 col-sm-3 stat">
								<span class="date">本月收入</span>
								<div class="data">
									<span class="number">￥<fmt:formatNumber value="${vo.month_income}" pattern="0.00"></fmt:formatNumber></span>元
								</div>
							</div>
							<div class="col-md-3 col-sm-3 stat">
								<span class="date">未确认收入</span>
								<div class="data">
									<span class="number">￥<fmt:formatNumber value="${vo.confirmMoney}" pattern="0.00"></fmt:formatNumber></span>元
								</div>
							</div>
							<div class="col-md-3 col-sm-3 stat">
								<span class="date">正在受理收入</span>
								<div class="data">
									<span class="number">￥<fmt:formatNumber value="${vo.apply_money}" pattern="0.00"></fmt:formatNumber></span>元
								</div>
							</div>
							<div class="col-md-3 col-sm-3 stat last">
								<span class="date">奖励金额</span>
								<div class="data">
									<span class="number">￥<fmt:formatNumber value="${vo.award_income}" pattern="0.00"></fmt:formatNumber></span>元
								</div>
							</div>
						</div>
					</div>
					<!-- end upper main stats -->
					<div id="pad-wrapper">
						<div class="row ui-elements">
							<div class="col-md-12">
								<h4 class="clearfix">
									应用信息:
									<div class="btn-group pull-right">
										<a class="btn-flat new-product" href="toAddApplication1.action">添加应用</a>
									</div>
								</h4>
							</div>
							<div class="row">
								<table class="table">
									<thead>
										<tr>
											<th class="col-md-3">运行中</th>
											<th class="col-md-3">暂停</th>
											<th class="col-md-3">待审核</th>
											<th class="col-md-3">审核未通过</th>
											<th class="col-md-3">未上传APP</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td><a href="applicationList.action?status=3">${smap['status_3']}</a></td>
											<td><a href="applicationList.action?status=0">${smap['status_0']}</a></td>
											<td><a href="applicationList.action?status=2">${smap['status_2']}</a></td>
											<td><a href="applicationList.action?status=-1">${smap['status_-1']}</a></td>
											<td><a href="applicationList.action?status=1">${smap['status_1']}</a></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
						<div class="row section ui-elements">
							<div class="col-md-12">
								<h4 class="clearfix">
									详细收入:
									<div class="btn-group pull-right">
										<a class="btn-flat new-product" href="reportHistorical.action">历史统计</a>
									</div>
								</h4>
							</div>
							<div class="row">
								<table class="table">
									<thead>
										<tr>
											<th class="col-md-3">日期</th>
											<th class="col-md-3">收入/元</th>
											<th class="col-md-3">效果数</th>
											<th class="col-md-3">积分墙/元</th>
											<th class="col-md-3">推荐墙/元</th>
											<th class="col-md-3">插屏/元</th>
										</tr>
									</thead>
									<tbody>
										<c:if test="${fn:length(incomeList)==0}">
											<tr><td colspan="6" style="color:#ff6666;">最近三天没有收入数据 </td></tr>
										</c:if> 
										<c:forEach items="${incomeList}" var="o">
										    <tr>
												<td><fmt:formatDate value="${o.static_date}" pattern="yyyy-MM-dd"/>  </td>
												<td><fmt:formatNumber value="${o.sumCost}" pattern="0.00"></fmt:formatNumber></td>
												<td>${o.activate}</td>
												<td><fmt:formatNumber value="${o.cost0}" pattern="0.00"></fmt:formatNumber></td>
												<td><fmt:formatNumber value="${o.cost1}" pattern="0.00"></fmt:formatNumber></td>
												<td><fmt:formatNumber value="${o.cost5}" pattern="0.00"></fmt:formatNumber></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<script type="text/javascript">
			//selectedMenu("menu1_1");
		</script>
	</body>
</html>