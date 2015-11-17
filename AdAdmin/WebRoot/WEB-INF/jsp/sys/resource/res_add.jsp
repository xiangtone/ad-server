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
			$("#addResource").ajaxSubmit(function(data){
				if(vaildateForm("addResource")){
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
				<form name="addResource" action="manage!saveResource.do"
					id="addResource" method="post" enctype="multipart/form-data">
					<table>
						<tr>
							<td width="100" valign="top">资源名称：</td>
							<td class="name"><input name="name"
								reg="^(?=.*?\S)[\s\S]{0,20}$" tip="资源名称不能为空" type="text"
								value="${entity.res_name }" maxlength="30" /><font color="red">${errMsg_appName}</font>
							</td>
						</tr>
						<tr>
							<td width="100" valign="top">资源URL：</td>
							<td class="url"><input name="url" id="url"
								reg="^(?=.*?\S)[\s\S]{0,40}$" tip="url不能为空" type="text"
								value="${entity.res_url }" maxlength="40" /><font color="red">${errMsg_appName}</font>
							</td>
						</tr>
						<tr>
							<td width="100" valign="top">描述：</td>
							<td class="note"><input name="note"
								id="short_desc" type="text" maxlength="20" /></td>
						</tr>
						<tr>
							<td width="100" valign="top">上级ID：</td>
							<td class="parent_id"><input name="parent_id" id="parent_id"
								onkeyup="value=value.replace(/[^\d]/g,'')" type="text" 
								value="${entity.parent_id }" maxlength="20" /><font color="red">${errMsg_appName}</font>
							</td>
						</tr>
						<tr>
							<td width="100" valign="top">排序字段 ：</td>
							<td class="order_num">
								<input name="order_num" id="order_num"
								onkeyup="value=value.replace(/[^\d]/g,'')" type="text" maxlength="20" /><font color="red">${errMsg_appName}</font>
							</td>
						</tr>
						<tr>
							<td width="100" valign="top">报表配置id：</td>
							<td class="stat_report_id">
								<input name="stat_report_id" id="stat_report_id" onkeyup="value=value.replace(/[^\d]/g,'')"
								type="text" value="${entity.stat_report_id }" maxlength="20" />
							</td>
						</tr>
						<tr>
							<td valign="top">资源类型：</td>
							<td><select name="type" id="type">
									<option value="0">链接</option>
									<option value="1">表单</option>
									<option value="2">操作按钮</option>
							</select>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
