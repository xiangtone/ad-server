<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>运营管理后台权限设置</title>
<link href="${pageContext.request.contextPath}/css/common.css?v=${version}" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/tabTool.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tabTool.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/manage/managePurview.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/dialog/lhgdialog.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/regist/validate.pack.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js?v=${version}" charset="utf-8"></script>
<script type="text/javascript">
var dg = frameElement.lhgDG;
/**
 * 添加渠道
 *	
 **/
function add(id){
	var url = "manage!roleSelPermission.do?id="+id;
		var showDg = new dg.curWin.$.dialog({id:'dialogrole',title:'调入角色',page:url,width:778,height:450,fixed:true,drag:true,resize:false,maxBtn:false,cover:true,parent:dg});
		showDg.ShowDialog();
}

/**
 * 添加角色
 * @return
 */
 dg.addBtn("save","保存",function(){ 
		$("#my_form").ajaxSubmit(function(data){
			if(vaildateForm("my_form")){
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
			}
		
		});
	});

/**
 * 根据id删除角色
 * @param id
 * @return
 */
function deleteRole(id){
	var url='manage!delRolePermission.do';
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
				refresh();
			}
		}
		
	}
	});
}



function refresh(){
	$("#my_form").submit();
}

/**
 * 获取角色的权限信息
 * @param id
 * @return
 */
function getPurviewInfo(roleId){
	var url = "manage!getPurviewInfo.do?id="+roleId;
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
 * 查询角色下用户列表 
 * @param roleId
 * @return
 */
function roleUserList(roleId,roleName){
	roleName = encodeURI(roleName);
	var url = "manage!roleUserList.do?roleId="+roleId+"&roleName="+roleName;
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
</script>
</head>
<body>
	<div class="main">
		<div class="content clearfix">
			<div class="content_right admin_right">
				<form method="post" id="my_form" action="manage!rolePermission.do">
					<input type="hidden" name="id" value="${id}" />
					<div class="main_table" style="width: 650px;overflow-y: scroll;height: 380px;">
						<table width="100%" cellpadding="0" cellspacing="1" id="tb" class="font_stat">
							<tr class="tr_td">
								<th width="8%">名称</th>
								<th>编号</th>
								<th width="10%">操作</th>
							</tr>
							<c:if test="${!empty list}">
								<c:forEach items="${list}" var="vo" varStatus="status">
									<tr>
										<td align="center">${vo.name}</td>
										<td align="center">
											${vo.code}
										</td>
										<td align="center">
											<input type="button" value="删除" onclick="deleteRole('${vo.id}');" />
										</td>
									</tr>
								</c:forEach>
							</c:if>
							<c:if test="${empty list}">
								<tr>
									<td align="center" colspan="9" style="text-align: center;">暂无记录！</td>
								</tr>
							</c:if>
						</table>
					</div>
					</form>
					${pageInfo.pageInfoStr}
				</div>
			</div>
		</div>
</body>
</html>