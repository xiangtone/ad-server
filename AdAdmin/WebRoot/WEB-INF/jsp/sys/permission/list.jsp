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
<title>平台管理后台权限管理列表</title>
<link href="${pageContext.request.contextPath}/css/common.css?v=${version}" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/dialog/lhgdialog.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js?v=${version}"></script>
<script type="text/javascript">	
	function resourceClosed(Id){
		var url = "manage!permissionDel.do?Id="+Id;
		if(confirm("删除后将会终止该项所有显示，确认删除吗？")){
				jump(url);
		}
	}	
	function add(){
		var url = "manage!addPermission.do";
			new $.dialog({
				title:'添加权限',
				page:url,
				width:675,
				height:400,
				drag:true,
				resize:true,
				cover:true,
				maxBtn:false}).ShowDialog();
	}
	
	function refresh(){
		$("#my_form").submit();
	}


	function edit(id){
		var url = "manage!editPermission.do?id="+id;
		new $.dialog({
			title:'修改权限',
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
					<div class="bord_bom1px">权限管理列表</div>
					<form action="manage!permissionList.do" method="post" id="my_form">
						<input id="id" name="id" type="hidden" />
						<input id="status"	name="status" type="hidden" />
						<input type="hidden" name="pageRecord" value="${pageInfo.pageSize}" />
						<!--新增end-->
						<table>
							<tr>
								<td>权限名称</td>
								<td>
									<input name="name" id="res_name" type="text" value="${bean.name}" />
								</td>
								<td>权限编号</td>
								<td>
									<input name="code" id="res_url" type="text" value="${bean.code}" />
								</td>
								<td><input value="查询" type="submit" /></td>
							</tr>
						</table>
					</form>
					<div>
						<input type="button" value="添加" onclick="add();" />
					</div>
					<div class="main_table">
					<table width="100%" cellpadding="0" cellspacing="1"	class="font_stat" id="tb">
						<!--修改-->
						<tr class="tr_td">
							<th>主键</th>
							<th>权限名称</th>
							<th>权限编号</th>
							<th>描述</th>
							<th>排序字段</th>
							<th>权限类型</th>
							<th>创建时间</th>
							<th>操作</th>
						</tr>
						<c:forEach items="${list}" var="vo">
						<tr>
							<td><font>${vo.id}</font></td>
							<td><font>${vo.name}</font></td>
							<td><font>${vo.code}</font></td>
							<td><font>${vo.note}</font></td>
							<td><font>${vo.order_num}</font></td>
							<td>
								<c:choose>
									<c:when test="${vo.type == '0'}">
										链接
									</c:when>
									<c:when test="${vo.type == '1'}">
										表单
									</c:when>
									<c:when test="${vo.type == '2'}">
										操作按钮
									</c:when>
								</c:choose>
							</td>
							<td>
								<fmt:formatDate value="${vo.create_time}" pattern="yyyy-MM-dd" />
							</td>
							<td>
								<input type="button" value="修改" onclick="edit('${vo.id}')" />
								<input type="button" value="刪除" onclick="resourceClosed('${vo.id}')" />
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
