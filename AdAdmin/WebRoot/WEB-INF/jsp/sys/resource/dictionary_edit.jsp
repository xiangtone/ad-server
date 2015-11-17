<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="escore" uri="/WEB-INF/tld/spring.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>运营管理后台方案调整修改页面</title>
<link href="${pageContext.request.contextPath}/css/common.css?v=${version}" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/regist/validate.css?v=${version}"	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/tabTool.css?v=${version}" rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js?v=${version}" charset="utf-8"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/escoreCategory.js?v=${version}"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/loading/loading.js?v=${version}"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/regist/validate.pack.js?v=${version}"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js?v=${version}"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/submit.js?v=${version}"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tabTool.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/dialog/lhgdialog.min.js?v=${version}"></script>
<script type="text/javascript">
	var dg;
	$(document).ready(function(){
		dg = frameElement.lhgDG;
		dg.addBtn("save","保存",function(){
			form_submit();
		});
	});
	
	function form_submit(){
		var fieldName = document.getElementById("columnName").value;
		var fieldType = document.getElementById("columnType").value;
		var fieldDesc = document.getElementById("columnDesc").value;
		var tableName = document.getElementById("tableName").value;
		var viewName = document.getElementById("viewName").value;
		if(fieldName==''){
			alert("字段名称不能为空!");
			return;
		}
		if(fieldType==''){
			alert("字段类型不能为空!");
			return;
		}
		if(fieldDesc==''){
			alert("字段描述不能为空!");
			return;
		}
		
		if(tableName=='' && viewName==''){
			alert("从属表名称和 从属视图名称至少有一个不为空!");
			return;
		}
		var dg=frameElement.lhgDG;
		if(vaildateForm("updateDictionary")){
			$("#updateDictionary").ajaxSubmit(function(data){
				if(data){
					var dataObj=eval("("+data+")");//转换为json对象 
					if(dataObj.status==1){
						alert("提交成功！！");
						dg.curWin.refresh();
						//dg.cancel();
					}else if(dataObj.status=-1){
						
					}else{
						alert("登录失败，请重试！！");
					}
				}else{
					alert("登录失败，请重试！！");
				}
			});
		};
	}
</script>
</head>
<body>
	<div class="main">
		<div class="content clearfix">
			<form id="updateDictionary" name="updateDictionary" action="manage!updateDictionary.do" method="post" >
				<div>
					<input type="hidden" name="id" id="id" value="${vo.id}" />
				</div>
				<div class="content_right admin_right">
				
    			<div class="subblock_1" style="display:block;border: 1px solid #9dbaff;float: left;width: 866px;border-top: 0;height: 412px;">
					<table>
						<tr>
							<td valign="top">字段名称：</td>
							<td colspan="3" class="limit_required">
								<input type="text" name="columnName" id="columnName" value="${vo.columnName}" />
								<font color="red">*</font>	
							</td>
						</tr>
						
						<tr>
							<td valign="top">字段类型：</td>
							<td colspan="3" class="limit_required">
								<input type="text" name="columnType" id="columnType" value="${vo.columnType}" />
								<font color="red">*</font>	
							</td>
						</tr>
						<tr>
							<td valign="top">字段描述：</td>
							<td colspan="3" class="limit_required">
								<textarea name="columnDesc" id="columnDesc" cols ="100" rows = "5" >${vo.columnDesc}</textarea>
								<font color="red">*</font>	
							</td>
						</tr>
						<tr>
							<td valign="top">从属表名称：</td>
							<td colspan="3" class="limit_required">
								<input type="text" name="tableName" id="tableName" value="${vo.tableName}" />
							</td>
						</tr>
						<tr>
							<td valign="top">从属视图名称：</td>
							<td colspan="3" class="limit_required">
								<input type="text" name="viewName" id="viewName" value="${vo.viewName}" />	
							</td>
						</tr>
						
					</table>
				</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>