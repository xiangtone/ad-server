<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="cache-control" content="no-cache" />
<title>运营管理后台修改密码</title>
<link href="${pageContext.request.contextPath}/css/common.css?v=${version}" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/manage/manageAccout.js"></script>
<script type="text/javascript">
 if("${msg}" != "") {
	 alert("${msg}");
 }
</script>
</head>
<body>
	<div class="main">
		<div class="content clearfix">
			<div class="admin_right">
				<div class="content_right content_new">
					<div class="bord_bom1px">修改密码</div>
					<form action="manage!modifyManagerPassword.do" method="post" onsubmit="return submitModifyPass();">
						<input type="hidden" name="id" value="${id}" />
						<table>
							<tr>
								<td>旧密码：</td>
								<td><input id="passWord" name="passWord" type="password" />*</td>
							</tr>
							<tr>
								<td>新密码：</td>
								<td><input id="newPassWord" name="newPassWord" type="password" />*</td>
							</tr>
							<tr>
								<td>重复新密码：</td>
								<td><input id="repeatNewPassWord" name="repeatNewPassWord" type="password" />*</td>
							</tr>
						</table>
						<div>
							<input type="submit" value="确认" />
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>