<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>运营管理后台平台管理</title>
<link href="${pageContext.request.contextPath}/css/common.css?v=${version}" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/manage/adEffectConfirm.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/manage/checkbox.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/dialog/lhgdialog.min.js?v=${version}"></script>
<script type="text/javascript">

function adOnline(adID){
	var url = "manage!adOnline.do?adId="+adID;
	if(confirm("确认要执行该操作吗？")){
		window.location.href=url;//不是驳回直接跳转
		jump(url);
	}//取消操作
}


function adOffline(adID){
	var url = "manage!adOffline.do?adId="+adID;
	if(confirm("确认要执行该操作吗？")){
		window.location.href=url;//不是驳回直接跳转
		jump(url);
	}//取消操作
}
function jump(url){
	var a = document.createElement("a");
	if(!a.click) {
 		 window.location = url;
 		 return;
		 }
		a.setAttribute("href", url);
		a.style.display = "none";
		document.body.appendChild(a);
		a.click();
}

/**
 * 查看修改信息
 *	
 **/
function findDictionaryContent(id){
		var url = "manage!findDictionaryContent.do?id="+id;
		new $.dialog({
			title:'数据字典',
			page:url,
			width:1000,
			height:400,
			drag:true,
			resize:true,
			cover:true,
			maxBtn:false}).ShowDialog();
}

function dictionaryAdd(){
	var url = "manage!jumpAddDictionary.do";
	 window.location = url;
}
</script>
<!-- <style>
table,td{ border:1px solid #000; border-collapse:collapse}
</style> -->
</head>
<body>
	
	<div class="main admin_main">
		<div class="content clearfix">
			<div class="admin_right">
				<div class="content_right content_new">
					<div class="bord_bom1px">数据字典</div>
					<fieldset class="search_fieldset">
						<legend> 查询条件 </legend>
						<form action="manage!dictionarySetting.do" id="dictionarySetting" method="get">
							<input type="hidden" name="orderColumn" id="orderColumn" value="${bean.id }" />
							<table>
								<tr>
									<td>字段名称</td>
									<td><input type="text" name="columnName" value="${bean.columnName}" /></td>
									<td>字段类型</td>
									<td><input type="text" name="columnType" value="${bean.columnType}" /></td>
								</tr>
								<tr>
									<td>从属表名称</td>
									<td><input type="text" name="tableName" value="${bean.tableName}" /></td>
									<td>从属视图名称</td>
									<td><input type="text" name="viewName"	value="${bean.viewName}" />
									</td>
								</tr>
								<tr>	
									<td>
										<input name="find" type="submit" id="adPriceSearch" style="width:100px;height:50px"
												value="查询" />	
									</td>
									<td>
										<input type="button" value="添加" style="width:100px;height:50px" onclick="dictionaryAdd()" />
									</td>
								</tr>
								
							</table>
								</form>
					</fieldset>
				<div class="main_table">
				<form>
					<table width="100%" cellpadding="0" cellspacing="1" id="tb" class="font_stat" >
						<tr class="tr_td tb_head">
							<th><div class="date"></div>
							 字段名称</th>
							<th>字段类型</th>
							<th>从属表名称</th>
							<th>从属视图名称</th>
							<th>操作</th>
						</tr>
						<c:if test="${!empty list}">
							<c:forEach items="${list}" var="vo" varStatus="status">
								<tr>
									<td>
										${vo.columnName}
									</td>
									<td>
										${vo.columnType}
									</td>
									<td>
										${vo.tableName}
									</td>
									<td>
										${vo.viewName}
									</td>
									<td>
										<input type="button" value="编辑" onclick="findDictionaryContent('${vo.id}');" />
									</td>
									<td>
									</td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${empty list}">
							<td colspan="17" align="center" style="text-align: center;">暂无记录！</td>
						</c:if>
					</table>
					</form>
					</div>
					${pageInfo.pageInfoStr}
				</div>
			</div>
		</div>
	</div>
</body>
</html>
