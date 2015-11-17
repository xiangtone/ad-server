<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="escore" uri="/WEB-INF/tld/spring.tld"%>
<%@ taglib prefix="ax" uri="/WEB-INF/tld/AlanXUpload.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>运营管理后台开发者应用审核</title>
<link href="${pageContext.request.contextPath}/css/common.css?v=${version}" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/regist/validate.css?v=${version}" rel="stylesheet" type="text/css" />
<script type="text/javascript"	src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath}/js/loading/loading.js?v=${version}"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath}/js/regist/validate.pack.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js?v=${version}" charset="utf-8"></script>
<script type="text/javascript">
	var dg = frameElement.lhgDG;
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
</script>




</head>
<body>
	<div class="main">
		<div class="content clearfix">
			<div class="content_right admin_right">
				<form name="addResource" action="manage!savePermission.do" id="my_form" method="post">
					<table>
						<tr>
							<td width="100" valign="top">权限名称：</td>
							<td class="name">
								<input name="name" reg="^(?=.*?\S)[\s\S]{0,20}$" tip="权限名称不能为空" type="text"  maxlength="20" />*
								<font color="red">${errMsg_appName}</font>
							</td>
						</tr>
						<tr>
							<td width="100" valign="top">权限编号：</td>
							<td class="code">
								<input name="code" reg="^(?=.*?\S)[\s\S]{0,40}$" tip="编号不能为空" type="text" maxlength="40" />*
								<font color="red">${errMsg_appName}</font>
							</td>
						</tr>
						<tr>
							<td width="100" valign="top">排序字段 ：</td>
							<td class="order_num">
								<input name="order_num" onkeyup="value=value.replace(/[^\d]/g,'')" reg="^(?=.*?\S)[\s\S]{0,40}$" tip="只能输入数字" type="text"
								value="${entity.sort }" maxlength="20" />
							</td>
						</tr>
						<tr>
							<td width="100" valign="top">权限类型：</td>
							<td><select name="type" id="type" style="width:175px;">
									<option value="0">链接</option>
									<option value="1">表单</option>
									<option value="2">操作按钮</option>
							</select>
							</td>
						</tr>
						<tr>
							<td width="100" valign="top">描述：</td>
							<td class="note">
								<textarea rows="" cols="" name="note"></textarea>
								*
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
