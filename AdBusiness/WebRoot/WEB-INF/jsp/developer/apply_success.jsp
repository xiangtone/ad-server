<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/include/common/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title><%=company%>广告平台</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/compiled/index.css" type="text/css" media="screen" />
		<script language="javascript" type="text/javascript">
			var interval = setInterval("redirect()", 3000);
			function redirect() {
				location.href = '${pageContext.request.contextPath}/developerAccount.action';
				clearInterval(interval);
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
							<form class="new_user_form">
								<div class="col-md-12">
									<h4 class="clearfix"></h4>
								</div>
								<div class="col-md-12 field-box">
									<p>${dev_email}</p>
									<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;您的提款申请已进入审核流程，我们会尽快处理。提款申请通过后，我们将在2-4个工作日内支付款项。</p>
									<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;系统将在3s后自动跳转，如未成功跳转请点击 <a href="developerAccount.action">这里</a>。</p>
								</div>
							</form>
						</div>
					</div>
					<!-- side right column -->
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