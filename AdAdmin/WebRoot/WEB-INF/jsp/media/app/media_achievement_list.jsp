<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="page" uri="/WEB-INF/tld/paginationTagWeb.tld"%>
<%@ taglib prefix="order" uri="/WEB-INF/tld/orderTag.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>运营管理后台报表</title>
<link href="${pageContext.request.contextPath}/css/common.css?v=${version}" rel="stylesheet" type="text/css" />
<script type="text/javascript"	src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath}/js/submit.js?v=${version}"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery.cookie.js?v=${version}"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/common.js?v=${version}"></script>
<style type="text/css">
.img01 {
	display: none;
}
</style>
<script type="text/javascript">
function exportCSV(form){
    editForm.action="manage!mediaAchievementSDown.do";
    editForm.submit();
}


function query(){
    editForm.action="manage!mediaAchievementList.do";
    editForm.submit();
}

$(document).keydown(function(event){
	if(event.keyCode==13){
		query();
	}
}); 

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
		 if($(this).attr("order")&&($(this).attr("order")=='false')){
			 return;
		 }
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
function changeOrder(key,url){
	 $.cookie("table_order",key);
	 window.location.href=url;
}
</script>
</head>
<body>
	<div class="main">
		<div class="content clearfix">
			<div class="content_right content_new">
				<form name="editForm" action="manage!ReportAdByDay.do" method="post">
					<table width="98%" border="0" align="center" cellpadding="0"
						cellspacing="1" class="style_table">
						<!-- 标题部分 -->
						<tbody>
							<tr>
								<td colspan="6"><b>媒介BD业绩</b></td>
							</tr>
							<!-- 搜索项部分-日期 -->
							<tr>
								<td colspan="" align="left">统计起期</td>
								<td colspan="" align="left">
									<input name="beginTime"	id="beginTime" type="text" value="${bean.beginTime}" class="Wdate"	readonly="readonly"/>
								</td>
								<td colspan="" align="left">统计止期</td>
								<td colspan="" align="left">
									<input name="endTime" id="endTime" type="text" value="${bean.endTime}" class="Wdate" readonly="readonly"/>
								</td>
								<td colspan="" align="left">开发者ID</td>
								<td colspan="1" align="left">
									<input name="dev_id" id="t.DEV_ID" value="${bean.dev_id}" /> 
								</td>
							</tr>
							<tr>
								<td colspan="" align="left">应用ID</td>
								<td colspan="1" align="left">
									 <input name="id" id="t.ID" value="${bean.id}" /> 
								</td>
								<td colspan="" align="left">应用名称</td>
								<td colspan="1" align="left">
									 <input name="name" value="${bean.name}"/> 
								</td>
									<td colspan="" align="left">广告样式</td>
								<td colspan="1" align="left">
									 <select name="type_id">
										<option value="">请选择</option>
										<option value="0" <c:if test="${bean.type_id ==0}"> selected="selected"</c:if> >积分墙</option>
										<option value="1" <c:if test="${bean.type_id ==1}"> selected="selected"</c:if> >推荐墙</option>
										<option value="4" <c:if test="${bean.type_id ==4}"> selected="selected"</c:if> >Banner</option>
										<option value="5" <c:if test="${bean.type_id ==5}"> selected="selected"</c:if> >插屏</option>
									</select>
								</td>
							</tr>
							<tr>
							<td colspan="" align="left">平台类型</td>
							<td colspan="1" align="left">
								 <select name="os">
										<option value="">请选择</option>
										<option value="android" <c:if test="${bean.os=='android'}"> selected="selected"</c:if> >android</option>
										<option value="ios" <c:if test="${bean.os =='ios'}"> selected="selected"</c:if> >ios</option>
								</select>
							</td>
							<!--  
							<td colspan="" align="left">负责人</td>
								<td colspan="1" align="left">
									 <input name="app_manager_id" value="${bean.app_manager_id}"/> 
								</td>
								-->
							</tr>
							<!-- 搜索项部分-按钮部分-->
							<tr>
								<td colspan="6">
									<input type="button" value="搜索" onclick="query(this.form)" /> &nbsp;&nbsp;
									<input type="button" name="Submit" onclick="exportCSV(this.form);" value="导出Excel" />
								</td>
							</tr>
						</tbody>
					</table>
					<div class="main_table">
						<table width="100%" cellpadding="0" cellspacing="1"	class="font_stat" id="tb">
							<tr class="tr_td tb_head" style="background-color: rgb(232, 238, 247);">
								<th style="white-space: nowrap; cursor: pointer;">日期<order:order url='${pageInfo.url}'  orderName='static_date' orderMode='desc' base="${pageContext.request.contextPath}" /></th>
								<th style="white-space: nowrap; cursor: pointer;">负责人<order:order url='${pageInfo.url}'  orderName='app_manager' orderMode='desc' base="${pageContext.request.contextPath}" /></th>
								<th style="white-space: nowrap; cursor: pointer;">开发者ID<order:order url='${pageInfo.url}'  orderName='dev_id' orderMode='desc' base="${pageContext.request.contextPath}" /></th>
								<th style="white-space: nowrap; cursor: pointer;">应用id<order:order url='${pageInfo.url}'  orderName='app_id' orderMode='desc' base="${pageContext.request.contextPath}" /></th>
								<th style="white-space: nowrap; cursor: pointer;">应用名称<order:order url='${pageInfo.url}'  orderName='name' orderMode='desc' base="${pageContext.request.contextPath}" /></th>
								<th style="white-space: nowrap; cursor: pointer;">广告样式<order:order url='${pageInfo.url}'  orderName='fname' orderMode='desc' base="${pageContext.request.contextPath}" /></th>
								<th style="white-space: nowrap; cursor: pointer;">SDK类型<order:order url='${pageInfo.url}'  orderName='os' orderMode='desc' base="${pageContext.request.contextPath}" /></th>
								<th style="white-space: nowrap; cursor: pointer;">请求展示<order:order url='${pageInfo.url}'  orderName='pospv' orderMode='desc' base="${pageContext.request.contextPath}" /></th>
								<th style="white-space: nowrap; cursor: pointer;">广告展示<order:order url='${pageInfo.url}'  orderName='apppv' orderMode='desc' base="${pageContext.request.contextPath}" /></th>
								<th style="white-space: nowrap; cursor: pointer;">广告点击<order:order url='${pageInfo.url}'  orderName='click' orderMode='desc' base="${pageContext.request.contextPath}" /></th>
								<th style="white-space: nowrap; cursor: pointer;">开始下载<order:order url='${pageInfo.url}'  orderName='clickd' orderMode='desc' base="${pageContext.request.contextPath}" /></th>
								<th style="white-space: nowrap; cursor: pointer;">下载完成<order:order url='${pageInfo.url}'  orderName='download' orderMode='desc' base="${pageContext.request.contextPath}" /></th>
								<th style="white-space: nowrap; cursor: pointer;">激活数<order:order url='${pageInfo.url}'  orderName='activate' orderMode='desc' base="${pageContext.request.contextPath}" /></th>
								<th style="white-space: nowrap; cursor: pointer;">成本（元）<order:order url='${pageInfo.url}'  orderName='cost' orderMode='desc' base="${pageContext.request.contextPath}" /></th>
								
							</tr>
							<!-- 表头排序参数设置-->

							<!-- 报表数据部分-总和数据-->

							<tr class="">
								<td>总计</td>
								<td>--</td>
								<td>--</td>
								<td>--</td>
								<td>--</td>
								<td>--</td>
								<td>--</td>
								<td style="text-align: right;">
									<fmt:formatNumber  type="number" pattern="###,###" value="${total.pospv}"/>
								</td>
								<td style="text-align: right;">
									<fmt:formatNumber  type="number" pattern="###,###" value="${total.apppv}"/>
								</td>
								<td style="text-align: right;">
									<fmt:formatNumber  type="number" pattern="###,###" value="${total.click}"/>
								</td>
								<td style="text-align: right;">
									<fmt:formatNumber  type="number" pattern="###,###" value="${total.clickd}"/>
								</td>
								<td style="text-align: right;">
									<fmt:formatNumber  type="number" pattern="###,###" value="${total.download}"/>
								</td>
								<td style="text-align: right;">
									<fmt:formatNumber  type="number" pattern="###,###" value="${total.activate}"/>
								</td>
								<td style="text-align: right;">
									<fmt:formatNumber  type="number" pattern="###,###.##" value="${total.cost}"  minFractionDigits="2" maxFractionDigits="2" />
								</td>															
							</tr>
							<c:if test="${!empty list}">
								<c:forEach items="${list}" var="vo" varStatus="status">
							<!-- 报表数据部分-主体数据-->
							<tr class="">
								<td>${vo.static_date}</td>
								<td>${vo.app_manager_name}</td>
								<td>${vo.dev_id}</td>
								<td>${vo.app_id}</td>
								<td>${vo.name}</td>
								<td>${vo.fname}</td>
								<td>${vo.os}</td>
								<td style="text-align: right;">
									<fmt:formatNumber  type="number" pattern="###,###" value="${vo.pospv}"/>
								</td>
								<td style="text-align: right;">
									<fmt:formatNumber  type="number" pattern="###,###" value="${vo.apppv}"/>
								</td>
								<td style="text-align: right;">
									<fmt:formatNumber  type="number" pattern="###,###" value="${vo.click}"/>
								</td>
								<td style="text-align: right;">
									<fmt:formatNumber  type="number" pattern="###,###" value="${vo.clickd}"/>
								</td>
								<td style="text-align: right;">
									<fmt:formatNumber  type="number" pattern="###,###" value="${vo.download}"/>
								</td>
								<td style="text-align: right;">
									<fmt:formatNumber  type="number" pattern="###,###" value="${vo.activate}"/>
								</td>
								<td style="text-align: right;">
									<fmt:formatNumber  type="number" pattern="###,###.##" value="${vo.cost}"  minFractionDigits="2" maxFractionDigits="2" />
								</td>
							</tr>
							</c:forEach>
							</c:if>
							<c:if test="${empty list}">
								<tr>
									<td align="center" colspan="15" style="text-align: center;">暂无记录！</td>
								</tr>
							</c:if>
						</table>
					</div>
					<!-- 分页部分 -->
					<div align="center">
						${pageInfo.pageInfoStr}
						<div style="display: block;height: 100px;"></div>
						<c:if test="${empty pageInfo.pageInfoStr}">
							<div style="display: block;height: 300px;"></div>
						</c:if>
					</div>
				</form>

			</div>
		</div>
	</div>


	<!-- 时间控件相关end -->
</body>
</html>
<!-- 时间控件相关start -->
<link href="${pageContext.request.contextPath}/date/ui.daterangepicker.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/date/ui-lightness/jquery-ui-1.8.4.custom.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/date/jquery-ui-1.8.4.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/date/daterangepicker.jQuery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/date/ui.datepicker-zh-CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/date/calendar.js"></script>