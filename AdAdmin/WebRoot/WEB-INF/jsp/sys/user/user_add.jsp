<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>运营管理后台账户设置</title>
<link href="${pageContext.request.contextPath}/css/common.css?v=${version}"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/manage/manageAccout.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/regist/validate.pack.js?v=${version}"></script>	
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery.form.js?v=${version}"	charset="utf-8"></script>
<script type="text/javascript">

var dg = frameElement.lhgDG;
dg.addBtn("save", "保存", function() {
	addManager();
});

/**
 * 添加管理员用户
 * 
 * @return
 */
function addManager() {
	var userName = document.getElementById("userName").value;
	var passWord = document.getElementById("passWord").value;
	var realName = document.getElementById("realName").value;
	var email = document.getElementById("email").value;
	if (userName.trim().length == 0) {
		$("#userNameError").html("<font color=red>*用户名不能空！</font>");
		document.getElementById("userName").focus();
		return;
	}
	if (realName.trim().length == 0) {
		$("#NameOError").html("<font color=red>*姓名不能空！</font>");
		document.getElementById("userName").focus();
		return;
	} else if (passWord.trim().length == 0) {
		$("#PasswordError").html("<font color=red>*密码不能空！</font>");
		document.getElementById("passWord").focus();
		return;
	} else if (email.trim().length == 0) {
		$("#emailError").html("<font color=red>*邮箱不能空！</font>");
		document.getElementById("email").focus();
		return;
	} else {
		$("#addUser").ajaxSubmit(function(data) {
			if(data){
				var dataObj=eval("("+data+")");//转换为json对象 
				if(dataObj.status==1){
					alert("操作成功！！");
					dg.curWin.refresh();
				}else if(dataObj.status=-1){
					alert("操作失败，请重试！！");
					dg.curWin.refresh();
				}else{
					alert("操作失败，请重试！！");
					dg.curWin.refresh();
				}
			}else{
				alert("操作失败，请重试！！");
				dg.curWin.refresh();
			}
			
		});
		
	}
}
</script>
</head>
<body>
	<div class="main">
		<div class="content clearfix">
			<div class="admin_right">
				<div class="content_right content_new">
					<form id="addUser" action="manage!saveUser.do" method="post">
					<table>
						<tr>
							<td>用户名</td>
							<td class="userName"><input id="userName" name="userName" type="text" reg="^[A-Za-z]{2,40}$" tip="请输入用户名"/>
							</td>
							<td id="userNameError"></td>
						</tr>
						<tr>
							<td>姓名</td>
							<td class="realName"><input id="realName" type="text" name="realName" reg="^[\u4e00-\u9fa5]+$"
								tip="请填写姓名 " maxlength="10" />
							</td>
							<td id="NameOError"></td>
						</tr>
						<tr>
							<td>密码</td>
							<td class="passWord"><input id="passWord" type="password" name="passWord" reg="^(\w){6,15}$" tip="请输入密码 " maxlength="15" />
							</td>
							<td id="PasswordError"></td>
						</tr>
						<tr>
							<td align="right">所属区域</td>
							<td><select name="area_type" id="area_type" style="width: 175px;">
									<option value="4">华南</option>
									<option value="1">华东</option>
									<option value="2">华北</option>
									<option value="0">平台</option>
							</select>
							</td>
						</tr>
						<tr>
							<td>邮箱</td>
							<td class="email"><input id="email" type="text" name="email" reg="^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$" tip="请输入邮箱"/>
							</td>
							<td id="emailError"></td>
						</tr>
					</table>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
