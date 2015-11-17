<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
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
<title>平台管理后台资源管理列表</title>
<link href="${pageContext.request.contextPath}/css/common.css?v=${version}" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/dialog/lhgdialog.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js?v=${version}"></script>
<script type="text/javascript">	
	function resourceClosed(id){
		var url = "manage!delResource.do?id="+id;
		if(confirm("删除后将会终止该项所有显示，确认删除吗？")){
				jump(url);
		}
	}	
	function resourceAdd(){
		var url = "manage!addResource.do";
			new $.dialog({
				title:'添加资源',
				page:url,
				width:675,
				height:360,
				drag:true,
				resize:true,
				cover:true,
				maxBtn:false}).ShowDialog();
	}
	
	function refresh(){
		$("#my_form").submit();
	}

	function edit(id){
		var url = "manage!editResource.do?id="+id;
		new $.dialog({
			title:'修改资源',
			page:url,
			width:675,
			height:520,
			drag:true,
			resize:true,
			cover:true,
			maxBtn:false}).ShowDialog();
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
</script>
</head>
<body>
	<div class="main admin_main">
		<div class="content clearfix">
			<div class="admin_right">
				<div class="content_right content_new">
					<div class="bord_bom1px">资源管理列表</div>
					<form action="manage!resourceList.do" method="post" id="my_form">
						<!--新增end-->
						<table>
							<tr>
								<td>资源名称</td>
								<td>
									<input name="res_name" id="res_name" type="text" value="${bean.res_name}" />
								</td>
								<td>资源路径</td>
								<td>
									<input name="res_url" id="res_url" type="text" value="${bean.res_url}" />
								</td>
								<td><input value="查询" type="submit" /></td>
							</tr>
						</table>
					</form>
					<div>
						<input type="button" value="添加" onclick="resourceAdd()" />
					</div>
					<div class="main_table">
					<table width="100%" cellpadding="0" cellspacing="1"	class="font_stat" id="tb">
						<!--修改-->
						<tr class="tr_td">
							<th>主键</th>
							<th>资源名称</th>
							<th>资源URL</th>
							<th>描述</th>
							<th>上级ID</th>
							<th>排序字段</th>
							<th>报表配置id</th>
							<th>资源类型</th>
							<th>创建时间</th>
							<th>操作</th>
						</tr>
						<c:forEach items="${list}" var="k">
						<tr>
							<td><font>${k.id }</font></td>
							<td><font>${k.name}</font></td>
							<td><font>${k.url }</font></td>
							<td><font>${k.note}</font></td>
							<td><font>${k.parent_id }</font></td>
							<td><font>${k.order_num}</font></td>
							<td><font>${k.stat_report_id }</font></td>
							<td>
								<c:choose>
									<c:when test="${k.type == 0}">
										链接
									</c:when>
									<c:when test="${k.type == 1}">
										表单
									</c:when>
									<c:otherwise>
										操作按钮
									</c:otherwise>
								</c:choose>
							</td>
							<td>
								<fmt:formatDate value="${k.create_time}" pattern="yyyy-MM-dd" />
							</td>
							<td>
								<input type="button" value="修改" onclick="edit('${k.id}')" />
								<input type="button" value="刪除" onclick="resourceClosed('${k.id}')" />
							</td>
						</tr>
						</c:forEach>
						<c:if test="${empty list}">
						<tr>
							<td align="center" colspan="17" style="text-align: center;">暂无数据！</td>
						</tr>
						</c:if>
					</table>
					</div>
					${pageInfo.pageInfoStr}
					<span class="clear_span"></span>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
