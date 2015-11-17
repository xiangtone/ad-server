<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>运营管理后台广告单价设置</title>
<link href="${pageContext.request.contextPath}/css/main.css?v=${version}"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/submit.js?v=${version}"></script>
	<script type="text/javascript"	src="${pageContext.request.contextPath}/dialog/lhgdialog.min.js?v=${version}"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/submit.js?v=${version}"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js?v=${version}"></script>
	<script type="text/javascript">
	var dg = frameElement.lhgDG;
	
	function item(id,tableid){	
		var url ="manage!showEditItem.do?itemid="+id+"&tableid="+tableid;
			var showDg = new dg.curWin.$.dialog({id:'dialogrole',title:'编辑',page:url,width:980,height:560,fixed:true,drag:true,resize:false,maxBtn:false,cover:true,parent:dg});
			showDg.ShowDialog();
	}
	
	
	function addItem(){
		var url ="manage!showAddItem.do?tableid=${tableid}";
		var showDg = new dg.curWin.$.dialog({id:'dialogrole',title:'添加新的统计报表item',page:url,width:980,height:560,fixed:true,drag:true,resize:false,maxBtn:false,cover:true,parent:dg});
		showDg.ShowDialog();
	}
	
	function refresh(){
		location.reload();
	}	
		</script>
		
</head>

<body>
	<div class="main">
		<div class="content clearfix">
			<div class="content_right content_new">
				<input  type="button" onclick="addItem();" value="新增" />
				<div style="display: block;height: 430px;overflow-y: scroll;">
					<form action="manage!showItemList.do" method="post"	id="report_list">
						<table width="100%" cellpadding="0" cellspacing="1"
							class="table_bod1 font_stat">
							<tr class="tr_td tb_head">
								<td>ID</td>
								<td>SQL名称</td>
								<td>页面名称</td>
								<td>数据格式</td>
								<td>状态</td>
								<td>排序值</td>
								<td>是否搜索</td>
								<td>页面展示类型</td>
								<td>SQL查询方式</td>
								<td>源数据SQL</td>
								<td>是否链接</td>
								<td>链接地址</td>
								<td>操作</td>
							</tr>

							<c:if test="${!empty data}">
								<c:forEach items="${data}" var="item" varStatus="recordCount">
									<tr onmouseover="changeColor(this);"
										onmouseout="returnColor(this)">
										<td>${item.id}</td>
										<td>${item.sqlName}</td>
										<td>${item.jspName}</td>
										<td><c:choose>
												<c:when test="${item.format=='per'}">百分比</c:when>
												<c:when test="${item.format=='flo'}">浮点数</c:when>
												<c:when test="${item.format=='flo'}">浮点数</c:when>
												<c:when test="${item.format=='dat'}">日期</c:when>
												<c:when test="${item.format=='time'}">时间</c:when>
												<c:when test="${item.format=='int'}">整数</c:when>
												<c:otherwise>字符串</c:otherwise>
											</c:choose></td>
										<td><c:if test="${item.status=='1'}">启用</c:if> <c:if
												test="${item.status=='9'}">弃用</c:if></td>
										<td>${item.sequence}</td>
										<td><c:if test="${item.isSearch=='y'}">搜索</c:if> <c:if
												test="${item.isSearch=='n'}">不搜索</c:if></td>
										<td><c:if test="${item.jspType=='i'}">input</c:if> <c:if
												test="${item.jspType=='s'}">select</c:if></td>
										<td><c:if test="${item.sqlType=='='}">=</c:if> <c:if
												test="${item.sqlType=='like'}">like</c:if></td>
										<td>${item.originSql}</td>
										<td><c:if test="${item.isLink=='y'}">链接</c:if> <c:if
												test="${item.isLink=='n'}">无链接</c:if></td>
										<td>${item.link}</td>
										<td>
											<input type="button" value="编辑" onclick="item(${item.id},${tableid})"/>
										</td>
									</tr>
								</c:forEach>
							</c:if>
							<c:if test="${empty data}">
								<td colspan="13" align="center" style="text-align: center;">暂无记录！</td>
							</c:if>
						</table>
						${pageInfo.pageInfoStr}
					</form>
				</div>
				</div>
			</div>
	</div>
</body>

</html>
