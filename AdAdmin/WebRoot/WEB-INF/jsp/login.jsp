<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>管理平台</title>
	<link rel="shortcut icon"  href="favicon.ico" />
	<link type="text/css" rel="stylesheet" id="globalStyle"  href="${pageContext.request.contextPath}/css/global.css" />
	<link type="text/css" rel="stylesheet" id="cssfile" href="${pageContext.request.contextPath}/css/login.css" />
	<script type="text/javascript"src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
	<script type="text/javascript"src="${pageContext.request.contextPath}/js/manage/manageLogin.js"></script>
</head>
<body>
	<div id="mainLogin">
		<div id="logo" style="display: block;height: 47px;"></div>
		<div id="login">
			<div class="login_in">
				<div class="left_pic">
					<!-- <img src="css/ChenZ/images/left_pic.png" alt="" /> -->
				</div>
				<div class="login_form"  >
					<form  id=loginForm action="dologin.do" method="post"  onsubmit="return toLogin();">
						<span><input type="submit" class="btn" value="登录" onclick="ValidateForm();"/></span>
						<p>
							<input type="text" class="txt" name="userName" id="username" />
						</p>
						<p>
							<input type="password" class="txt" name="passWord" />
						</p>
						<p><font color="red">${loginMsg}</font></p>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
<script type="text/javascript">
//防止被嵌套
if (self.location != top.location) {
        top.location.href = self.location;
}
</script>