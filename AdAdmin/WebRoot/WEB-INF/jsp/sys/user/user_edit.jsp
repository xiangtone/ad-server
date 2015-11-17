<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="cache-control" content="no-cache" />
<title>运营管理后台修改姓名</title>
<link href="${pageContext.request.contextPath}/css/common.css?v=${version}"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/regist/validate.pack.js?v=${version}"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery.form.js?v=${version}"
	charset="utf-8"></script>
<script type="text/javascript">
	var dg = frameElement.lhgDG;
	dg.addBtn("save", "保存", function() {
		editManager();
	});

	function editManager() {
		var userName = document.getElementById("userName").value;
		var realName = document.getElementById("realName").value;
		var email = document.getElementById("email").value;
		if (userName.trim().length == 0) {
			$("#userNameError").html("<font color=red>*用户名不能空！</font>");
			document.getElementById("userName").focus();
			return;
		} else if (realName.trim().length == 0) {
			$("#NameOError").html("<font color=red>*姓名不能空！</font>");
			document.getElementById("userName").focus();
			return;
		} else if (email.trim().length == 0) {
			$("#emailError").html("<font color=red>*邮箱不能空！</font>");
			document.getElementById("email").focus();
			return;
		} else {
			$("#editManager").ajaxSubmit(function(data) {
				if (data == "true") {
					alert("修改成功！！");
					dg.curWin.refresh();
				} else {
					alert("修改失败，请重试！！");
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
			<div class="content_right admin_right">
				<ul class="tab_block2">    
      				<li class="click"><a href="javascript:void(0)" class="sub_1 tab_block2_hover click"><span class="click">基本信息</span></a></li>
     				<li><a href="javascript:void(0)" class="sub_2"><span>拥有权限</span></a></li>
     				<!-- <li><a href="javascript:void(0)" class="sub_3"><span>重置密码</span></a></li> -->
    			</ul>
    			<div class="subblock_1" style="display:block;border: 1px solid #9dbaff;float: left;width: 866px;border-top: 0;height: 412px;">
					<form id="editManager" action="manage!updateUser.do" method="post">
						<input type="hidden" id="id" name="id" value="${entiy.id}" />
						<table>
							<tr>
								<td>用户名</td>
								<td class="userName"><input id="userName" name="userName"
									type="text" value="${entiy.userName}" reg="^[A-Za-z]{2,40}$"
									tip="请输入2-40个字符  " maxlength="40" />
								</td>
								<td id="userNameError"></td>
							</tr>
							<tr>
								<td>姓名</td>
								<td class="realName"><input id="realName" type="text"
									name="realName" value="${entiy.realName}"
									reg="^[\u4e00-\u9fa5\w\W]{2,20}$" tip="请输入2-20个字符  "
									maxlength="20" />
								</td>
								<td id="NameOError"></td>
							</tr>
							<tr>
								<td align="right">角色</td>
								<td>
									<select name="role_id" style="width: 175px;">
										<c:forEach items="${list}" var="vo">
											<option value="${vo.id}"	<c:if test="${entiy.role_id eq vo.id}">   selected="selected" </c:if>>${vo.name}</option>
										</c:forEach>
								</select>
								</td>
							</tr>
							<tr>
								<td align="right">所属区域</td>
								<td><select name="area_type" id="area_type"
									style="width: 175px;">
										<option value="4"
											<c:if test="${entiy.area_type eq 4}"> selected="selected" </c:if>>华南</option>
										<option value="1"
											<c:if test="${entiy.area_type eq 1}"> selected="selected" </c:if>>华东</option>
										<option value="2"
											<c:if test="${entiy.area_type eq 2}"> selected="selected" </c:if>>华北</option>
										<option value="0"
											<c:if test="${entiy.area_type eq 0}"> selected="selected" </c:if>>平台</option>
								</select>
								</td>
							</tr>
							<tr>
								<td>邮箱</td>
								<td class="email"><input id="email" type="text" name="email"
									value="${entiy.email}"
									reg="^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$" tip="请输入邮箱" />
								</td>
								<td id="emailError"></td>
							</tr>
						</table>
					</form>
				</div>
    			<div class="subblock_2" style="display:none;border: 1px solid #9dbaff;float: left;width: 866px;border-top: 0;height: 412px;">
					<input id="roleId" type="hidden" name="roleId" value="${roleId}" />
					<div>
						<input type="button" value="添加" onclick="add('${id}')" style="cursor: pointer;" />
					</div>
					<div class="main_table" style="width: 650px;height: 480px;">
						<iframe frameborder="0" height="380px;" width="600px;" id="_content" src="manage!rolePermission.do?id=${id}"></iframe>
					</div>
					${pageInfo.pageInfoStr}
				</div>
			</div>
					
			</div>
		</div>
	</div>
</body>
</html>
