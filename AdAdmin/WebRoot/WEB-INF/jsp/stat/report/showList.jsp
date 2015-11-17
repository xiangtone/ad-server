<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="page" uri="/WEB-INF/tld/paginationTagWeb.tld"%>
<%@ taglib prefix="order" uri="/WEB-INF/tld/orderTag.tld"%>
<%@ include file="../comm/basePath.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>运营管理后台报表</title>
<link href="${pageContext.request.contextPath}/css/common.css?v=${version}" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/submit.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.cookie.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js?v=${version}"></script>
<style type="text/css">
.img01{
	display: none;
}
</style>
<script type="text/javascript">
	function query(form){
        editForm.action="manage!statManage.do?id=${bean.table.id}";
        editForm.submit();
    }
	 function exportCSV(form){
         editForm.action="manage!exportCSV.do?id=${bean.table.id}";
         editForm.submit();
     }
	 
	 
	 function changeOrder(key,url){
		 $.cookie("table_order",key);
		 window.location.href=url;
	 }
	 
     function openNewWindow(url){
    		window.open(url,'newPage','top=0,left=0,toolbar=no,menubar=no,scrollbars=yes, resizable=no,location=no, status=no');     
    }
     $(document).ready(function (){
 		initOrder();
 	});
     
     function initOrder(){
    	 $(".tb_head th").css("cursor","pointer");
    	 var code=$.cookie("table_order");
    	 if(code){
    		 $("#"+code).removeClass('img01');
    	 }
    
    	 $(".tb_head th").click(function(){
    		 var obj;
    		 if($(this).find(".img01").length==2){
    			 obj=$(this).find("img").eq(0);
    		 }else{
    			 obj=$(this).find(".img01");
    			 obj.removeClass('img01');
    			 obj.siblings().addClass('img01');
    		 }
    		 changeOrder(obj.attr("id"),obj.attr("url"));
    	 });    	 
     }
</script>
</head>
<body>
	<div class="main">
		<div class="content clearfix">
			<div class="content_right content_new">
				<form name="editForm" action="" method="post">
					<table width="98%" border="0" align="center" cellpadding="0" cellspacing="1" class="style_table">
						<!-- 标题部分 -->
						<tr>
							<td colspan="6"><b>${bean.table.note}--${bean.table.jspName}</b>
							</td>
						</tr>
						<!-- 搜索项部分-日期 -->
						<tr>
							
							<td colspan="" align="left">统计起期</td>
							<td colspan="" align="left">
								<input	name="beginTime" id="beginTime" type="text"	value="${bean.data.input.beginTime}" class="Wdate" readonly="readonly" /></td>
							<td colspan="" align="left">统计止期</td>
							<td colspan="" align="left">
								<input name="endTime" id="endTime" type="text" value="${bean.data.input.endTime}" class="Wdate" readonly="readonly" /></td>
							<!-- 搜索项部分-自定义部分 -->
							<c:set var="length" value="2" />
							<c:forEach items="${bean.items}" var="search" varStatus="recordCount">
								<c:if test="${search.item.isSearch=='y'}">
									<c:if test="${(length)%3==0}">
										</tr>
										<tr>
									</c:if>
							<td colspan="" align="left">${search.item.jspName}</td>
							<td colspan="${search.colspan }" align="left">
								<!-- 搜索项部分-如果搜索为input类型 --> <c:if
									test="${search.item.jspType=='i'}">
									<input name="${search.item.sqlName}"
										id="${search.item.sqlName}" value="${search.input}" />
								</c:if> <!-- 搜索项部分-如果搜索为select类型 --> <c:if
									test="${search.item.jspType=='s'}">
									<select name="${search.item.sqlName}"
										id="${search.item.sqlName}">
										<option value="">请选择</option>
										<c:forEach items="${search.select}" var="s"
											varStatus="recordCount">
											<option value="${s.id}"
												<c:if test="${!(search.input eq'')&&(s.id eq search.input)}">selected="selected"</c:if>>
												${s.value}</option>
										</c:forEach>
									</select>
								</c:if>
								<c:set var="length" value="${length+1}" /></td>
							</c:if>
							</c:forEach>
						</tr>
						<!-- 搜索项部分-按钮部分-->
						<tr>
							<td colspan="6">
								<input type="button" name="Submit" onclick="query(this.form);" value="搜索" /> &nbsp;&nbsp;
								<input type="button" name="Submit" onclick="exportCSV(this.form);" value="导出Excel" />
							</td>
						</tr>
					</table>
					<div class="main_table">
					<table width="100%" cellpadding="0" cellspacing="1" class="font_stat" id="tb">
						<!-- 拼分页的其他参数传递 -->
						<c:set var="tt" value="" />
						<c:forEach items="${bean.items}" var="search" varStatus="recordCount">
							<c:if test="${search.item.isSearch=='y'}">
								<c:url value="">
									<c:param name="" />
								</c:url>
								<input id="temp" value="<c:url value="xxxx"><c:param name="dd" value="3333"/></c:url>" type="hidden" />
								<c:set var="tt" value="get" />
								<c:set var="other" value="${other}${search.item.sqlName}=${search.codeInput}&"></c:set>
							</c:if>
						</c:forEach>
						<!-- 报表头部分 -->
						<tr class="tr_td tb_head">
							<c:forEach items="${bean.items}" var="head"	varStatus="status">
								<th style="white-space: nowrap;">${head.item.jspName}
									<order:order url='manage!statManage.do'  orderName='${head.item.sqlName}' orderMode='desc' base="${pageContext.request.contextPath}"><order:param name='op' value='showList' /><order:param name='id' value='${bean.table.id}' /><order:param name='beginTime' value='${bean.data.input.beginTime}' /><order:param name='endTime' value='${bean.data.input.endTime}' /><order:param name='${other}' value='' /><order:param name='tt' value='${tt}' /></order:order>
								</th>
							</c:forEach>
						</tr>
						<!-- 表头排序参数设置-->
						<c:set var="order" value="${bean.data.input.order}"></c:set>
						<!-- 报表数据部分-总和数据-->
						<c:if test="${bean.table.totalSql!=''}">
							<tr>
								<c:forEach items="${bean.data.total}" var="t" varStatus="aa">
									<c:set var="titleFormat"
										value="${bean.items[aa.count-1].item.format}"></c:set>
									<td>
										<c:choose>
											<c:when test="${titleFormat=='per'}">
												<fmt:formatNumber value="${t.value}" pattern="#,###.##%" />
											</c:when>
											<c:when test="${titleFormat=='flo'}">
												<fmt:formatNumber value="${t.value}" pattern="#,###.##" />
											</c:when>
											<c:when test="${titleFormat=='int'}">
												<fmt:formatNumber value="${t.value}" pattern="#,###" />
											</c:when>
											<c:otherwise>${t.value}</c:otherwise>
										</c:choose>
									</td>
								</c:forEach>
							</tr>
						</c:if>
						<!-- 报表数据部分-主体数据-->
						<c:forEach items="${bean.data.data.list}" var="data" varStatus="a">
							<c:set var="url"
								value="${other}&beginTime=${bean.data.input.beginTime}&endTime=${bean.data.input.endTime}" />
							<c:set var="i" value="1" />
							<tr>
								<c:forEach items="${data}" var="d" varStatus="b">
									<c:set var="url" value="${url}&${bean.items[b.count-1].item.sqlName}=${d.value}" />
									<td>
										<c:set var="dataFormat" value="${bean.items[b.count-1].item.format}" />
										<c:set var="isLink" value="${bean.items[b.count-1].item.isLink}" />
										<c:set var="link" value="${bean.items[b.count-1].item.link}" />
										<c:if test="${isLink=='y'}">
											<c:choose>
												<c:when test="${fn:contains(d.value,'#')}">
													<a href="javascript:openNewWindow('<c:url value="${link}"><c:param name="${bean.items[b.count-1].item.sqlName}" value="${fn:split(d.value, '#')[1]}"/><c:param name="beginTime" value="${bean.data.input.beginTime}"/><c:param name="endTime" value="${bean.data.input.endTime}"/></c:url>')"></a>
												</c:when>
												<c:otherwise>
													<a href="javascript:openNewWindow('<c:url value="${link}"><c:param name="${bean.items[b.count-1].item.sqlName}" value="${d.value}"/><c:param name="beginTime" value="${bean.data.input.beginTime}"/><c:param name="endTime" value="${bean.data.input.endTime}"/></c:url>')"></a>
												</c:otherwise>
											</c:choose>
										</c:if>
										<c:choose>
											<c:when test="${dataFormat=='per'}">
												<fmt:formatNumber value="${d.value}" pattern="#,###.##%" />
											</c:when>
											<c:when test="${dataFormat=='flo'}">
												<fmt:formatNumber value="${d.value}" pattern="#,###.##" />
											</c:when>
											<c:when test="${dataFormat=='int'}">
												<fmt:formatNumber value="${d.value}" pattern="#,###" />
											</c:when>
											<c:when test="${dataFormat=='dat'}">
												<fmt:formatDate value="${d.value}" pattern="yyyy-MM-dd"></fmt:formatDate>
											</c:when>
											<c:when test="${dataFormat=='time'}">
												<fmt:formatDate value="${d.value}" pattern="yyyy-MM-dd HH:mm:SS"></fmt:formatDate>
											</c:when>
											<c:otherwise>
												<c:choose>
													<c:when test="${fn:contains(d.value,'#')}">${fn:split(d.value, '#')[0]}</c:when>
													<c:otherwise>${d.value}</c:otherwise>
												</c:choose>
											</c:otherwise>
										</c:choose>
									</td>
								</c:forEach>
							</tr>
						</c:forEach>
					</table>
					</div>
					<!-- 分页部分 -->
					<div align="center">
						<page:page url="manage!statManage.do"
							totalPage="${bean.data.data.totalPage}"
							currentPage="${bean.data.data.currentPage}">
							<page:param name="op" value="showList" />
							<page:param name="id" value="${bean.table.id}" />
							<page:param name="beginTime" value="${bean.data.input.beginTime}" />
							<page:param name="endTime" value="${bean.data.input.endTime}" />
							<page:param name="${other}" value="" />
							<page:param name="tt" value="${tt}" />
							<page:param name="order" value="${order}" />
						</page:page>
					</div>
				</form>
				<div style="display: block;height: 100px;"></div>
			</div>
		</div>
	</div>
</body>
<!-- 时间控件相关start -->
<link href="${pageContext.request.contextPath}/date/ui.daterangepicker.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/date/ui-lightness/jquery-ui-1.8.4.custom.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/date/jquery-ui-1.8.4.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/date/daterangepicker.jQuery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/date/ui.datepicker-zh-CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/date/calendar.js"></script>
<!-- 时间控件相关end -->
</html>
