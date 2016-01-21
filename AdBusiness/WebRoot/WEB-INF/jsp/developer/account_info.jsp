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
	</head>
	<body>
		<jsp:include page="/WEB-INF/jsp/include/dev/top.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/dev/left.jsp"></jsp:include>
		<div class="content">
			<div id="pad-wrapper" class="users-list">
				<div class="row header">
					<h3>账户信息</h3>
					<div class="col-md-10 col-sm-12 col-xs-12 pull-right">
						<a href="toDeveloperApplyMoney.action" class="btn-glow primary pull-right"> <span>&#43;</span>我要提款</a>
					</div>
				</div>
				<div class="table-products section">
					<div class="row filter-block">
						<div class="ui-dropdown">
							帐号类型：<label><c:if test="${bankvo.cardType==1}">个人开发者</c:if>
							<c:if test="${bankvo.cardType==2}">公司开发</c:if> </label>
						</div>
						<div class="ui-dropdown">
							开户银行：<label>${bankvo.bankSubbranch}</label>
						</div>
						<div class="ui-dropdown">
							银行账号：<label>${bankvo.bankAccount}</label>
						</div>
					</div>
					<div id="main-stats">
						<div class="row stats-row">
							<div class="col-md-3 col-sm-3 stat">
								<span class="date">累计收入</span>
								<div class="data">
									<span class="number">￥<fmt:formatNumber value="${vo.total_income}" pattern="0.00"></fmt:formatNumber></span> 元
								</div>
							</div>
							<div class="col-md-3 col-sm-3 stat">
								<span class="date">可提款收入</span>
								<div class="data">
									<span class="number">￥<fmt:formatNumber value="${vo.finance_income}" pattern="0.00"></fmt:formatNumber></span> 元
								</div>
							</div>
							<div class="col-md-3 col-sm-3 stat">
								<span class="date">未确认收入</span>
								<div class="data">
									<span class="number">￥<fmt:formatNumber value="${vo.confirmMoney}" pattern="0.00"></fmt:formatNumber></span> 元
								</div>
							</div>
							<div class="col-md-3 col-sm-3 stat">
								<span class="date">正在受理入</span>
								<div class="data">
									<span class="number">￥<fmt:formatNumber value="${vo.apply_money}" pattern="0.00"></fmt:formatNumber></span> 元
								</div>
							</div>
							<div class="col-md-3 col-sm-3 stat last">
								<span class="date">累计提款</span>
								<div class="data">
									<span class="number">￥<fmt:formatNumber value="${vo.total_money}" pattern="0.00"></fmt:formatNumber></span> 元
								</div>
							</div>
						</div>
					</div>
					<div id="main-stats">
						<div class="row stats-row">
							<div class="col-md-3 col-sm-3 stat">
								<span class="date">奖励金额</span>
								<div class="data">
									<span class="number">￥<fmt:formatNumber value="${vo.award_income}" pattern="0.00"></fmt:formatNumber></span> 元
								</div>
							</div>
							<div class="col-md-3 col-sm-3 stat last">
								<span class="date">累计税费</span>
								<div class="data">
									<span class="number">￥<fmt:formatNumber value="${vo.dues}" pattern="0.00"></fmt:formatNumber></span> 元
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<script type="text/javascript">
				selectedMenu("menu4_1");
			</script>
		</div>
	</body>
</html>