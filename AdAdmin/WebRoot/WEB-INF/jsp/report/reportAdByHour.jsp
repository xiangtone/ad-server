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
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Buttons/css/font-awesome.min.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Buttons/css/buttons.css" />
<link href="${pageContext.request.contextPath}/css/common.css?v=${version}" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/submit.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.cookie.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js?v=${version}"></script>
<style type="text/css">
.img01 {
	display: none;
}
</style>
<script type="text/javascript">
function exportCSV(){
	var form=document.getElementById("editForm");
    editForm.action="manage!exportAdByHourCSV.do";
    editForm.submit();
}




function query(form){
    editForm.action="manage!ReportAdByHour.do";
    editForm.submit();
}

function refresh(){
	var form=document.getElementById("editForm");
	query(form);
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
	<div class="main admin_main">
		<div class="content clearfix">
			<div class="admin_right">
				<div class="content_right content_new">
					<fieldset class="search_fieldset">
						<legend>广告统计--按小时</legend>
						<div id="search_bar">
							<form name="editForm" action="manage!ReportAdByHour.do" method="post">
								<table width="100%">
									<tr>
										<td>
											<table width="100%">
												<tr>
													<td colspan="" align="left">统计起期</td>
													<td colspan="" align="left">
														<input name="beginTime"	id="beginTime" type="text" value="${bean.beginTime}" class="Wdate"	readonly="readonly"/>
													</td>
													<td colspan="" align="left">统计止期</td>
													<td colspan="" align="left">
														<input name="endTime" id="endTime" type="text" value="${bean.endTime}" class="Wdate" readonly="readonly"/>
													</td>
													<td colspan="" align="left">小时</td>
													<td colspan="1" align="left">
														<input name="static_hour" value="${bean.static_hour}"/> 
													</td>
						 						</tr>
												<tr>
					
													<td colspan="" align="left">广告主ID</td>
													<td colspan="1" align="left">
														<input name="adv_id" value="${bean.adv_id}" onkeyup="value=value.replace(/[^\d]/g,'')"  /> 
													</td>
													<td colspan="" align="left">广告ID</td>
													<td colspan="1" align="left">
														 <input name="id"  value="${bean.id}" onkeyup="value=value.replace(/[^\d]/g,'')"  /> 
													</td>
													<td colspan="" align="left">广告名称</td>
													<td colspan="1" align="left">
														 <input name="placement_name" value="${bean.placement_name}"/> 
													</td>
												</tr>
												<tr>
													<td colspan="" align="left">广告样式</td>
													<td colspan="1" align="left">
														 <select name="type_id">
															<option value="">请选择</option>
															<option value="0" <c:if test="${bean.type_id ==0}"> selected="selected"</c:if> >积分墙</option>
															<option value="1" <c:if test="${bean.type_id ==1}"> selected="selected"</c:if> >推荐墙</option>
															<option value="4" <c:if test="${bean.type_id ==4}"> selected="selected"</c:if> >Banner</option>
															<option value="5" <c:if test="${bean.type_id ==5}"> selected="selected"</c:if> >插屏</option>
															<option value="6" <c:if test="${bean.type_id ==6}"> selected="selected"</c:if> >热荐</option>
														</select>
													</td>
													<td colspan="" align="left">平台类型</td>
													<td colspan="3" align="left">
														 <select name="os">
																<option value="">请选择</option>
																<option value="android" <c:if test="${bean.os=='android'}"> selected="selected"</c:if> >android</option>
																<option value="ios" <c:if test="${bean.os =='ios'}"> selected="selected"</c:if> >ios</option>
														</select>
													</td>
												</tr>
											</table>
										</td>
										<td width="15%" valign="middle" align="right">
											<div style="width: 100px;height: 100%;display: inline;line-height: 20px;">
												<button onclick="query(this.form)" class="button button-pill button-primary" style="line-height: 21px;height: 21px;font-size: 13px;padding-left: 10px;padding-right: 10px;">查询</button>
												<button onclick="resetAll('my_form');" class="button button-pill button-primary" style="line-height: 21px;height: 21px;font-size: 13px;padding-left: 10px;padding-right: 10px;">重置</button>
											</div>
										</td>
										</tr>
								</table>
							</form>
						</div>
					</fieldset>
					<input type="button" name="Submit" onclick="exportCSV(this.form);" value="导出Excel" />
					<div class="main_table">
						<table width="100%" cellpadding="0" cellspacing="1"	class="font_stat" id="tb">
							<tr class="tr_td tb_head" style="background-color: rgb(232, 238, 247);">
								<th style="white-space: nowrap; cursor: pointer;">日期<order:order url='${pageInfo.url}'  orderName='static_date' orderMode='desc' base="${pageContext.request.contextPath}" /></th>
								<th style="white-space: nowrap; cursor: pointer;">小时<order:order url='${pageInfo.url}'  orderName='static_hour' orderMode='desc' base="${pageContext.request.contextPath}" /></th>
								<th style="white-space: nowrap; cursor: pointer;">广告主ID<order:order url='${pageInfo.url}'  orderName='adv_id' orderMode='desc' base="${pageContext.request.contextPath}" /></th>
								<th style="white-space: nowrap; cursor: pointer;">广告ID<order:order url='${pageInfo.url}'  orderName='id' orderMode='desc' base="${pageContext.request.contextPath}" /></th>
								<th style="white-space: nowrap; cursor: pointer;">广告名称<order:order url='${pageInfo.url}'  orderName='placement_name' orderMode='desc' base="${pageContext.request.contextPath}" /></th>
								<th style="white-space: nowrap; cursor: pointer;">广告样式<order:order url='${pageInfo.url}'  orderName='fname' orderMode='desc' base="${pageContext.request.contextPath}" /></th>
								<th style="white-space: nowrap; cursor: pointer;">平台类型<order:order url='${pageInfo.url}'  orderName='os' orderMode='desc' base="${pageContext.request.contextPath}" /></th>
								<th style="white-space: nowrap; cursor: pointer;">广告展示<order:order url='${pageInfo.url}'  orderName='adpv' orderMode='desc' base="${pageContext.request.contextPath}" /></th>
								<th style="white-space: nowrap; cursor: pointer;">广告点击<order:order url='${pageInfo.url}'  orderName='click' orderMode='desc' base="${pageContext.request.contextPath}" /></th>
								<th style="white-space: nowrap; cursor: pointer;">广告下载<order:order url='${pageInfo.url}'  orderName='download' orderMode='desc' base="${pageContext.request.contextPath}" /></th>
								<th style="white-space: nowrap; cursor: pointer;">广告激活<order:order url='${pageInfo.url}'  orderName='activate' orderMode='desc' base="${pageContext.request.contextPath}" /></th>
								<th style="white-space: nowrap; cursor: pointer;">费用支出(元)<order:order url='${pageInfo.url}'  orderName='cost' orderMode='desc' base="${pageContext.request.contextPath}" /></th>
								<th style="white-space: nowrap; cursor: pointer;">点击转化率<order:order url='${pageInfo.url}'  orderName='ctrc' orderMode='desc' base="${pageContext.request.contextPath}" /></th>
								<th style="white-space: nowrap; cursor: pointer;">下载转化率<order:order url='${pageInfo.url}'  orderName='ctrd' orderMode='desc' base="${pageContext.request.contextPath}" /></th>
								<th style="white-space: nowrap; cursor: pointer;">激活转化率<order:order url='${pageInfo.url}'  orderName='ctrd' orderMode='desc' base="${pageContext.request.contextPath}" /></th>
							</tr>
														<!-- 表头排序参数设置-->

														<!-- 报表数据部分-总和数据-->

														<tr class="">
															<td>总计</td>
															<td>*</td>
															<td>**</td>
															<td>***</td>
															<td>****</td>
															<td>*****</td>
															<td>******</td>
															<td style="text-align: right;">
																<fmt:formatNumber  type="number" pattern="###,###" value="${total.adpv}"/>
															</td>
															<td style="text-align: right;">
																<fmt:formatNumber  type="number" pattern="###,###" value="${total.click}"/>
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
															<td style="text-align: right;">
																<fmt:formatNumber  type="number" pattern="###,###.####" value="${total.ctrc}"  minFractionDigits="2" maxFractionDigits="4" />%
															</td>
															<td style="text-align: right;">
																<fmt:formatNumber  type="number" pattern="###,###.####" value="${total.ctrd}"  minFractionDigits="2" maxFractionDigits="4" />%
															</td>
															<td style="text-align: right;">
																<fmt:formatNumber  type="number" pattern="###,###.####" value="${total.ctra}"  minFractionDigits="2" maxFractionDigits="4" />%
															</td>
														</tr>
														<c:if test="${!empty list}">
															<c:forEach items="${list}" var="vo" varStatus="status">
														<!-- 报表数据部分-主体数据-->
														<tr class="">
															<td>${vo.static_date}</td>
															<td>${vo.static_hour}</td>
															<td>${vo.adv_id}</td>
															<td>${vo.id}</td>
															<td>${vo.ad_name}</td>
															<td>${vo.fname}</td>
															<td>${vo.os}</td>
															<td style="text-align: right;">
																<fmt:formatNumber  type="number" pattern="###,###" value="${vo.adpv}"/>
															</td>
															<td style="text-align: right;">
																<fmt:formatNumber  type="number" pattern="###,###" value="${vo.click}"/>
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
															<td style="text-align: right;">
																<fmt:formatNumber  type="number" pattern="###,###.####" value="${vo.ctrc}"  minFractionDigits="2" maxFractionDigits="4" />%
															</td>
															<td style="text-align: right;">
																<fmt:formatNumber  type="number" pattern="###,###.####" value="${vo.ctrd}"  minFractionDigits="2" maxFractionDigits="4" />%
															</td>
															<td style="text-align: right;">
																<fmt:formatNumber  type="number" pattern="###,###.####" value="${vo.ctra}"  minFractionDigits="2" maxFractionDigits="4" />%
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