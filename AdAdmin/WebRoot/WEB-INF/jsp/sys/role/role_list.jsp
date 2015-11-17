<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>运营管理后台角色设置</title>
<link href="${pageContext.request.contextPath}/css/common.css?v=${version}" rel="stylesheet" type="text/css" />
<script type="text/javascript"src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/dialog/lhgdialog.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js?v=${version}"></script>
<script type="text/javascript">
/**
 * 添加渠道
 *	
 **/
function add(){
		var url = "manage!addRole.do";
		new $.dialog({
			title:'添加渠道',
			page:url,
			width:675,
			height:590,
			drag:true,
			resize:true,
			cover:true,
			maxBtn:false}).ShowDialog();
}

/**
 * 根据id删除角色
 * @param id
 * @return
 */
function deleteRole(id){
	if(id == ""){
		return;
	}else{
		var flag = confirm("确认要删除吗？");
		if(flag){				
			window.location.href = "manage!delRole.do?id="+id;
		}
	}
}

/**
 * 获取角色的权限信息
 * @param id
 * @return
 */
function edit(roleId){
	var url = "manage!editRole.do?id="+roleId;
	new $.dialog({
		title:'修改角色',
		page:url,
		width:675,
		height:510,
		drag:true,
		resize:true,
		cover:true,
		maxBtn:false}).ShowDialog();
}



/**
 * 查询角色下用户列表 
 * @param roleId
 * @return
 */
function roleUserList(roleId,roleName){
	roleName = encodeURI(roleName);
	var url = "manage!roleUserList.do?roleId="+roleId+"&roleName="+roleName;
	new $.dialog({
		title:'添加角色',
		page:url,
		width:675,
		height:590,
		drag:true,
		resize:true,
		cover:true,
		maxBtn:false}).ShowDialog();
}

function refresh(){
	$("#myform").submit();
}
</script>
</head>
<body>
	<div class="main admin_main">
		<div class="content clearfix">
			<div class="admin_right">
				<div class="content_right content_new">
					<fieldset class="search_fieldset">
						<legend>运营管理角色设置</legend>
						<form action="" id="myform">
						</form>
						<div>
							<input type="button" onclick="add();" value="添加角色" />
						</div>
					</fieldset>
					<div class="main_table">
						<table width="100%" cellpadding="0" cellspacing="1" class="font_stat" id="tb">
								<tr class="tr_td">
									<th width="3%">序号</th>
									<th width="20%">角色名称</th>
									<th width="20%">角色编号</th>
									<th width="20%">角色描述</th>
									<th width="20%">用户数量</th>
									<th width="10%">操作</th>
								</tr>
								<c:if test="${!empty list}">
								<c:forEach items="${list}" var="obj" varStatus="k">
									<tr>
										<td style="text-align: center;">${k.index+1}</td>
										<td>${obj.name}</td>
										<td>${obj.code}</td>
										<td>${obj.note}</td>
										<td>${obj.userCount }</td>
										<td align="center">
											<input type="button" onclick="roleUserList('${obj.id }','${obj.name }')" value="用户列表" />
											<c:choose>
												<c:when test="${obj.name == '超级管理员'}">
													<input type="button" onclick="edit('${obj.id}')" value="修改角色" />
												</c:when>
												<c:otherwise>
													<input type="button" onclick="edit('${obj.id}')" value="修改角色" />
												</c:otherwise>
											</c:choose>
											<c:choose>
												<c:when test="${obj.userCount != 0}">
													<input type="button" onclick="deleteRole(${obj.id})" disabled="disabled" value="删除角色" />
												</c:when>
												<c:otherwise>
													<input type="button" onclick="deleteRole(${obj.id})" value="删除角色" />
												</c:otherwise>
											</c:choose>
										</td>
									</tr>
								</c:forEach>	
								</c:if>
								<c:if test="${empty list}">
									<tr>
										<td align="center" colspan="15" style="text-align: center;">暂无记录！</td>
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