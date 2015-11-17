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
 dg.addBtn("save_0001","保存",function(){ 
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
	if(id == ""){
		return;
	}else{
		var flag = confirm("确认要删除吗？");
		if(flag){				
			window.location.href = "manage!deleteManageRole.do?id="+id;
		}
	}
}


function refresh(){
	$("#_content").attr("src", 'manage!rolePermission.do?id=${id}');
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
				<ul class="tab_block2">    
      				<li class="click"><a href="javascript:void(0)" class="sub_1 tab_block2_hover click"><span class="click">基本信息</span></a></li>
     				<li><a href="javascript:void(0)" class="sub_2"><span>拥有权限</span></a></li>
     				<!-- <li><a href="javascript:void(0)" class="sub_3"><span>重置密码</span></a></li> -->
    			</ul>
    			<div class="subblock_1" style="display:block;border: 1px solid #9dbaff;float: left;width: 866px;border-top: 0;height: 412px;">
    				<form action="manage!updateRole.do" id="my_form" method="post">
    					<div>
    						<input type="hidden" name="id" value="${id}" />
    					</div>
    					<table>
								<tr>
									<td>角色名：</td>
									<td class="name"><input  name="name" type="text" value="${entity.name}" reg="^(?=.*?\S)[\s\S]{0,20}$" tip="角色名不能为空"/>
									</td>
								</tr>
								<tr>
									<td>角色编号：</td>
									<td class="code"><input  name="code" type="text" value="${entity.code}" reg="^(?=.*?\S)[\s\S]{0,40}$" tip="角色编号不能为空"/>
									</td>
								</tr>
								<tr>
									<td>角色描述：</td>
									<td>
										<textarea name="note" rows="" cols="">${entity.note}</textarea>
									</td>
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
</body>
</html>