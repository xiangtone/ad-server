<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>运营管理后台账户设置</title>
<link href="${pageContext.request.contextPath}/css/common.css?v=${version}" rel="stylesheet" type="text/css" />
<script type="text/javascript"src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript"src="${pageContext.request.contextPath}/js/manage/managePurview.js?v=${version}"></script>
<script type="text/javascript">

/**
 * 删除管理员用户的角色
 * @param userId
 * @return
 */
function deleteManageUserRole(userId){
	var flag = confirm("确认要删除该用户的角色吗？");
	if(flag){
		document.getElementById("userId").value = userId;
		$("#baseForm").attr("action",
		"manage!delMURole.do").submit();
	}
}

</script>
</head>

<body>
	<div class="main admin_main" style="background:#F6F6F6">
		<div class="content clearfix">
			<div class="content_right admin_right">
				<div class="bord_bom1px">
					运营管理后台账户设置
				</div>
				<form id="baseForm" action="" method="post">
					<input type="hidden" id="userId" name="userId"  />
					<input type="hidden" id="roleId" name="roleId" value="${roleId }" />
					<input type="hidden" id="roleName" name="roleName" value="${roleName }" />
					<table width="100%" cellpadding="0" cellspacing="1" class="table_bod1 font_stat">
						<tr class="tr_td">
							<td>序号</td>
							<td>用户名</td>
							<td>创建时间</td>
							<td>账户角色</td>
							<td>操作</td>
						</tr>
						<c:forEach items="${manageUserList}" var="obj" varStatus="k">
							<tr>
								<td>${k.index+1 }</td>
								<td>${obj.userName }</td>
								<td>${obj.createTime }</td>
								<td>${roleName }</td>
								<td>
									<input type="button" onclick="deleteManageUserRole(${obj.id})" value="删除角色" />
								</td>
							</tr>
						</c:forEach>
					</table>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
