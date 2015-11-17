<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>运营管理后台广告单价设置</title>
<link href="${pageContext.request.contextPath}/css/common.css?v=${version}" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/submit.js?v=${version}"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath}/dialog/lhgdialog.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js?v=${version}"></script>
<script type="text/javascript">
/**
		 * 添加报表配置
		 *	
		 **/
		function add(){	
			var url = "manage!showAddTable.do";
			new $.dialog({
				title:'添加报表配置',
				page:url,
				width:874,
				height:605,
				drag:true,
				resize:true,
				cover:true,
				rang:true,
				autoPos:{left:'center',top:'center'},
				maxBtn:false}).ShowDialog();
		}


		/**
		 * 修改报表配置
		 *	
		 **/
		function EditTable(id){	
			var url = "manage!showEditTable.do?tableid="+id;
			new $.dialog({
				title:'修改报表配置',
				page:url,
				width:874,
				height:605,
				drag:true,
				resize:true,
				cover:true,
				rang:true,
				autoPos:{left:'center',top:'center'},
				maxBtn:false}).ShowDialog();
		}

		/**
		 * 查看item
		 *	
		 **/
		function showItem(id){	
			var url = "manage!showItemList.do?tableid="+id;
			new $.dialog({
				title:'查看item',
				page:url,
				width:1020,
				height:545,
				drag:true,
				resize:true,
				cover:true,
				rang:true,
				autoPos:{left:'center',top:'center'},
				maxBtn:false}).ShowDialog();
		}
		
		function refresh(){
			$("#report_list").submit();
		}	
</script>
</head>
<body>
	<div class="main">

		<div class="content clearfix">
				<div class="content_right content_new">
					<table>
					<tr>
						<td><input type="button" name="add" value="添加新报表" onclick="add()"/></td>
					</tr>
					</table>
					<div class="main_table">
					<form action="manage!reportManage.do" method="post"	id="report_list">
						<table width="100%" cellpadding="0" cellspacing="1" class="font_stat">
							<tr class="tr_td tb_head">
								<th>ID</th>
								<th>备注</th>
								<th>页面显示名称</th>
								<th>菜单显示名称</th>
								<th>请求URL</th>
								<th>权限</th>
								<th>分页大小</th>
								<th>状态</th>
								<th>操作</th>
							</tr>
							<c:if test="${!empty data}">
								<c:forEach items="${data}" var="table" varStatus="recordCount">
									<tr onmouseover="changeColor(tdis);"
										onmouseout="returnColor(this)">
										<td>${table.id}</td>
										<td>${table.note}</td>
										<td>${table.jspName}</td>
										<td>${table.menuName}</td>
										<td>${table.menuRequest}</td>
										<td>${table.role}</td>
										<td>${table.pageSize}</td>
										<td><c:if test="${table.status==1}">启用</c:if> <c:if
												test="${table.status!=1}">弃用</c:if></td>
										<td>
										<input type="button" value="修改信息" onclick="EditTable(${table.id})"/>
										&nbsp;
											<input type="button" value="查看item" onclick="showItem(${table.id})"/>
										</td>
									</tr>
								</c:forEach>
							</c:if>
							<c:if test="${empty data}">
								<td colspan="8" align="center" style="text-align: center;">暂无记录！</td>
							</c:if>
						</table>
					</form>
					</div>
					${pageInfo.pageInfoStr}
				</div>
			</div>
	</div>
</body>

</html>
