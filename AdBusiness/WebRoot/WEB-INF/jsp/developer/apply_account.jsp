<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/util/config_manager.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title><%=company%>广告平台</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<%@include file="/WEB-INF/jsp/include/common/header.jsp"%>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/compiled/index.css" type="text/css" media="screen" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/adwalker.category.js"></script>
		<script type="text/javascript">
			$(document).ready(function() {
				getCategoryById('${bankInfo.city_id}');//获取默认分类
			});
			function submitForm() {
				if (vaildateForm("devApplyMoney")) {
					$("#devApplyMoney").ajaxSubmit(function(data) {
						var dataObj = eval("(" + data + ")");//转换为json对象 
						if (dataObj.status == 1) {
							alert("确认提交成功！！");
							window.location.href = 'toFinanceApplySuccess.action';
						} else if (dataObj.status == -1) {
							alert("确认提交失败！！");
							window.location.href = 'toDeveloperApplyMoney.action';
						} else {
							alert("提款失败！！");
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
			<div id="pad-wrapper" class="new-user">
				<div class="row header">
					<div class="col-md-12">
						<h3>提款申请</h3>
					</div>
				</div>
				<div class="row form-wrapper ">
					<!-- left column -->
					<div class="col-md-9 with-sidebar">
						<div class="container">
							<form class="new_user_form" action="developerApplyMoneyAccount.action" id="devApplyMoney" method="post">
								<input name="apply_id" value="${id}" type="hidden" />
								<div class="col-md-12">
									<h4 class="clearfix">核对信息</h4>
								</div>
								<div class="col-md-12 field-box">
									<label>用户名：</label>
									<p>${dev_mail}</p>
								</div>
								<div class="col-md-12 field-box">
									<label>开户人姓名：</label>
									<p>${bankInfo.accountHoder}</p>
								</div>
								<div class="col-md-12 field-box">
									<label>证件类型：</label>
									<p>
										<c:choose>
											<c:when test="${bankInfo.cardType==2}">公司号</c:when>
											<c:otherwise>身份证</c:otherwise>
										</c:choose>
									</p>
								</div>
								<div class="col-md-12 field-box">
									<label>证件号：</label>
									<p>${bankInfo.cardCode}</p>
								</div>
								<div class="col-md-12 field-box">
									<label>开户所在地:</label>
									<p>
										<select id="type" reg="[^0]" disabled="disabled">
											<option value="0">省份</option>
											<c:forEach items="${provinceCitySort}" var="app">
												<option value="${app.id}">${app.province_City}</option>
											</c:forEach>
										</select>
										<select id="platform" reg="[^0]" tip="请选择城市" disabled="disabled">
											<option value="0">城市</option>
										</select>
									</p>
								</div>
								<div class="col-md-12 field-box">
									<label>开户银行:</label>
									<p>${bankInfo.bankName }</p>
								</div>
								<div class="col-md-12 field-box">
									<label>开户支行名称:</label>
									<p>${bankInfo.bankSubbranch}</p>
								</div>
								<div class="col-md-12 field-box">
									<label>银行账号：</label>
									<p>${bankInfo.bankAccount}</p>
								</div>
								<div class="col-md-12 field-box">
									<label>发票方式:</label> <input type="radio" name="open_invoice" value="1" checked="checked" /> 未开发票 <input type="radio" name="open_invoice" value="0" /> 已开发票
									<p class="alert-a">注意：请确认发票的收款账户信息与您填写的财务账户信息一致，否则会导致付款失败</p>
								</div>
								<div class="col-md-11 field-box actions">
									<a href="javascript:void(0);" onclick="submitForm();"><input type="button" class="btn-glow primary" value="确认提交"/></a>
	
								</div>
							</form>
						</div>
					</div>
					<div class="col-md-3_1 col-xs-12 form-sidebar pull-right">
						
					</div>
				</div>
			</div>
		</div>
		<script type="text/javascript">
			selectedMenu("menu4_2");
		</script>
	</body>
</html>