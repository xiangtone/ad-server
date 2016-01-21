<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/util/config_manager.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title><%=company%>广告平台</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<%@include file="/WEB-INF/jsp/include/common/header.jsp"%>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/compiled/personal-info.css" type="text/css" media="screen" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/compiled/index.css" type="text/css" media="screen" />
		<script type="text/javascript">
			function inputTipText() {
		
			}
			$(function() {
				inputTipText(); //直接调用就OK了  
			});
			function submitForm() {
				if (vaildateForm("updateUser")) {
					$("#updateUser").ajaxSubmit(function(data) {
						if (data == "true") {
							alert("修改成功！！");
							window.location.href = 'toUpdateInfo.action';
						} else {
							alert("修改失败，请重试！！");
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
			<div class="settings-wrapper" id="pad-wrapper">
				<div class="row header">
					<h3>基本信息设置</h3>
				</div>
				<div class="table-products section">
					<div class="row filter-block">
						<div class="personal-info">
							<form class="form-horizontal" role="form" id="updateUser" action="updateUser.action" method="get">
								<div class="form-group">
									<label class="col-lg-2 control-label">注册账号:</label>
									<div class="col-lg-8">
										<span>${userDeveloper.email}</span>
									</div>
								</div>
								<c:if test="${userDeveloper.type == 2}">
									<div class="form-group">
										<label class="col-lg-2 control-label">公司名称:</label>
										<div class="companyName">
											<input type="text" class="form-control" name="companyName" id="companyName" value="${userDeveloper.companyName}" reg="^[\u4e00-\u9fa5\w\W]{4,50}$" tip="请输入4-50个字符  " maxlength="50" />
										</div>
									</div>
								</c:if>
								<div class="form-group">
									<label class="col-lg-2 control-label">联系人:</label>
									<div class="realName">
										<input class="form-control" type="text" name="realName" value="${userDeveloper.realName}" id="realName" reg="^[\u4e00-\u9fa5]+$" tip="请填写姓名 " maxlength="10" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-lg-2 control-label">手机号码:</label>
									<div class="mobilePhone">
										<input class="form-control" type="text" name="mobilePhone" value="${userDeveloper.mobilePhone}" id="mobilePhone" reg="^1[3,5,8,7]\d{9}$" tip="请输入手机号 " maxlength="11" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-lg-2 control-label">QQ号码:</label>
									<div class="qq">
										<input class="form-control" type="text" name="qq" value="${userDeveloper.qq}" id="qq" reg="[1-9][0-9]{5,}" tip="请输入QQ号" maxlength="11" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-lg-2 control-label">联系地址:</label>
									<div class="mailingAddress">
										<input class="form-control" name="mailingAddress" type="text" value="${userDeveloper.mailingAddress}" id="mailingAddress" value="${userDeveloper.mailingAddress }" reg="^[\u4e00-\u9fa5\w\W]{5,100}$" tip="请输入5-100个字符 " maxlength="100" " />
									</div>
								</div>
								<div class="form-group">
									<label class="col-lg-2 control-label">邮政编码:</label>
									<div class="postCode">
										<input class="form-control" value="${userDeveloper.postCode }" type="text" name="postCode" id="postCode" reg="[1-9]\d{5}(?!\d)" tip="请输入邮编 如:100000" maxlength="6" />
									</div>
								</div>
								<div class="actions">
									<input type="button" class="btn-glow primary" onclick="submitForm();" value="确认保存"/>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
			<script type="text/javascript">
				selectedMenu("menu5_1");
			</script>
		</div>
	</body>
</html>