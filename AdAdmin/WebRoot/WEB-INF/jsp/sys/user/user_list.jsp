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
<script type="text/javascript" src="${pageContext.request.contextPath}/dialog/lhgdialog.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js?v=${version}"></script>
<script type="text/javascript">
/**
 * tb效果
 *	
 **/
function refresh(){
	$("#listuser").submit();
}

/**
 * 添加用户
 *	
 **/
function addUser(){	
	var url = "manage!addUser.do";
	new $.dialog({
		title:'添加用户',
		page:url,
		width:500,
		height:330,
		drag:true,
		resize:true,
		cover:true,
		rang:true,
		autoPos:{left:'center',top:'center'},
		maxBtn:false}).ShowDialog();
}

/**
 * 授权
 * 
 * @param userId
 * @return
 */
function authorize(userId) {
	var url = "manage!toAuthorize.do?userId=" + userId;
	new $.dialog({
		title:'授权用户',
		page:url,
		width:455,
		height:330,
		drag:true,
		resize:true,
		cover:true,
		rang:true,
		autoPos:{left:'center',top:'center'},
		maxBtn:false}).ShowDialog();
}


/**
 * 重置密码
 * 
 * @param id
 * @return
 */
function reSetPassword(id) {
	var flag = confirm("密码重置后默认是：123456，确认要重置密码吗？");
	if (flag) {
		var url="manage!resetManagerPass.do";
		$.ajax({
			url:url,
			type:'POST',
			data:'id='+id,
			dataType:'text',
			beforeSend:function(){
				//$(obj).after('<img src="${pageContext.request.contextPath}/images/ajax_loader.gif" />');
		},
		success:function(data){
			if(data){
				var dataObj=eval("("+data+")");//转换为json对象 
				if(dataObj.status=='1'){
					alert("操作成功！！");
				}
			}
			
		}
		});
		
		
		
	}
}


/**
 * 账户设置修改
 *	
 **/
function  toEditUser(id) {
	var url = "manage!toEditUser.do?id=" + id;
	new $.dialog({
		title:'账户设置修改',
		page:url,
		width:500,
		height:280,
		drag:true,
		resize:true,
		cover:true,
		maxBtn:false}).ShowDialog();
}
/**
 * 根据id删除账户
 * @param id
 * @return
 */
function deleteById(id){
	if(id == ""){
		return;
	}else{
		var flag = confirm("确认要删除吗？");
		if(flag){				
			window.location.href = "manage!deleteManageById.do?id="+id;
		}
	}
}

</script>
</head>

<body>
<div class="main admin_main">
	<div class="content clearfix">
		<div class="admin_right">
				<div class="content_right content_new">
					<div class="bord_bom1px">运营管理后台账户设置</div>
					<form id="listuser" action="manage!listManageUser.do" method="post">
						<table class="mar_bom10 margtop10">
							<tr>
								<td>用户名</td>
								<td><input name="user_name" type="text" value="${bean.user_name}"/></td>
								<td><input name="" type="submit" value="搜索" /></td>
							</tr>
						</table>
					</form>
					<table>
					<tr>
					<td><input type="button" value="添加" onclick="addUser();" /></td></tr>
					</table>
			<div class="main_table">
				<table width="100%" cellpadding="0" cellspacing="1" class="font_stat" id="tb">
					<tr class="tr_td">
						<th width="%3">序号</th>
						<th>用户名</th>
						<th>姓名</th>
						<th>邮箱</th>
						<th>创建时间</th>
						<th>账户角色</th>
						<th>操作</th>
					</tr>
					<c:if test="${!empty manageUserList}">
					<c:forEach items="${manageUserList}" var="vo" varStatus="k">
						<tr>
							<td style="text-align: center;">${k.index+1 }</td>
							<td>${vo.userName }</td>
							<td>${vo.realName }</td>
							<td>${vo.email }</td>
							<td><fmt:formatDate value="${vo.createTime }" type="date" dateStyle="long" pattern="yyyy-MM-dd HH:mm:ss"/></td>
							<td>${vo.roleName }</td>
							<c:choose>
								<c:when test="${vo.type == 1}">
									<td align="center">
										<input type="button" onclick="authorize('${vo.id}')" value="授权" disabled="disabled" />
										<input type="button" onclick="toEditUser('${vo.id}')" value="修改"  disabled="disabled" />
										<input type="button" onclick="reSetPassword('${vo.id}')" value="重置密码" disabled="disabled" />
										<input type="button" onclick="deleteById('${vo.id}')" value="删除用户" disabled="disabled" />
									</td>
								</c:when>
								<c:otherwise>
									<td align="center">
										<input type="button" onclick="authorize('${vo.id}')" value="授权" />
										<input type="button" onclick="toEditUser('${vo.id}')" value="修改" />
										<input type="button" onclick="reSetPassword('${vo.id}')" value="重置密码" />
										<input type="button" onclick="deleteById('${vo.id}')" value="删除用户" />
									</td>
								</c:otherwise>
							</c:choose>
						</tr>
					</c:forEach>
					</c:if>
					<c:if test="${empty manageUserList}">
						<tr>
							<td colspan="15" align="center" style="text-align: center;">暂无记录！</td>
						</tr>
					</c:if>
				</table>
				</div>
				${pageInfo.pageInfoStr}
		</div>
	</div>
	</div>
</div>
</body>
</html>
