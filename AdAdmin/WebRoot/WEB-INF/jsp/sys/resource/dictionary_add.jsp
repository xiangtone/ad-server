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
<link href="${pageContext.request.contextPath}/css/common.css?v=${version}"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/regist/validate.css?v=${version}"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/escoreCategory.js?v=${version}"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/loading/loading.js?v=${version}"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/regist/validate.pack.js?v=${version}"></script>
</head>
<body>
	<div class="main">
		<div class="content clearfix">
			<div class="content_right admin_right">
				<div class="bord_bom1px">添加字典</div>
				<form name="addDictionary" action="manage!addDictionary.do"
					id="addDictionary" method="post" >
					<table>
						<tr>
							<td width="100" valign="top">字段名称：</td>
							<td class="res_name"><input name="columnName" id="columnName"
								reg="^(?=.*?\S)[\s\S]{0,20}$" tip="资源名称不能为空" type="text"
								value="${entity.columnName }" maxlength="20" />* <font color="red">${errMsg_appName}</font>
							</td>
						</tr>
						<tr>
							<td width="100" valign="top">字段类型：</td>
							<td class="res_url"><input name="columnType" id="columnType"
								reg="^(?=.*?\S)[\s\S]{0,40}$" tip="url不能为空" type="text"
								value="${entity.columnType }" maxlength="40" />* <font color="red">${errMsg_appName}</font>
							</td>
						</tr>
						
						<tr>
							<td width="100" valign="top">从属表名称：</td>
							<td class="res_url"><input name="tableName" id="tableName"
								value="${entity.tableName }" maxlength="40" />* <font color="red">${errMsg_appName}</font>
							</td>
						</tr>
						
						<tr>
							<td width="100" valign="top">从属视图名称：</td>
							<td class="res_url"><input name="viewName" id="viewName"
								value="${entity.viewName }" maxlength="40" />* <font color="red">${errMsg_appName}</font>
							</td>
						</tr>
						
						<tr>
							<td valign="top"></td>
							<td><input name="" type="submit" value="保存" /></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
