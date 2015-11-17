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
<link href="${pageContext.request.contextPath}/css/page.css?v=${version}" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/fill/style.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/submit.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.cookie.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/fill/jquery.mockjax.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/fill/jquery.autocomplete.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/fill/fill.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js?v=${version}"></script>
<style type="text/css">
.img01 {
	display: none;
}
</style>
<script type="text/javascript">

function exportCSV(form){
    editForm.action="manage!exportAdByDayCSV.do";
    editForm.submit();
}


function query(){
    editForm.action="${pageContext.request.contextPath}/report/effectIosByDay.do";
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


var countries = ${data};
 

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
						<legend>IOS渠道统计</legend>
						<div id="search_bar">
							<form name="editForm" action="${pageContext.request.contextPath}/report/effectIosByDay.do" method="post">
								<table width="100%">
									<tr>
										<td>
											<table width="100%">
												<tr>
													<td>广告 ID：</td>
													<td>
													 <!--  
														<select name="ad_id">
															<option value="">全部</option>
															<c:forEach items="${adList}" var="ad" varStatus="status">
															<option value="${ad.id}" <c:if test="${bean.ad_id eq ad.id}">selected="selected"</c:if>>${ad.ad_name}</option>
															</c:forEach>
														</select>
														-->
														<div class="container">         
													        <div>
													            <input type="text" name="country" id="autocomplete" value="${countryName}"/>
													        </div>
													       <input type="text" hidden="hidden" name="ad_id" id="selection" value="${adId}"/>
													    </div>
														
													</td>
													<td>渠道名称：</td>
													<td>
														<select name="channel">
															<option value="">全部</option>
															<c:forEach items="${channelList}" var="chan" varStatus="status">
															<option value="${chan.channel}"	<c:if test="${bean.channel eq chan.channel}">selected="selected"</c:if>>${chan.channel_name}</option>
															</c:forEach>
														</select>
													</td>
												</tr>
												<tr>
													<td  align="left">统计起期</td>
													<td  align="left">
														<input name="beginTime"	id="beginTime" type="text" value="${bean.beginTime}" class="Wdate"	readonly="readonly"/>
													</td>
													<td  align="left">统计止期</td>
													<td  align="left">
														<input name="endTime" id="endTime" type="text" value="${bean.endTime}" class="Wdate" readonly="readonly"/>
													</td>
												</tr>
											</table>
										</td>
										<td width="15%" valign="middle" align="right">
											<div style="width: 100px;height: 100%;display: inline;line-height: 20px;">
												<button onclick="query(this.form);" class="button button-pill button-primary" style="line-height: 21px;height: 21px;font-size: 13px;padding-left: 10px;padding-right: 10px;">查询</button>
												<button onclick="resetAll('my_form');" class="button button-pill button-primary" style="line-height: 21px;height: 21px;font-size: 13px;padding-left: 10px;padding-right: 10px;">重置</button>
											</div>
										</td>
									</tr>
								</table>
							</form>
						</div>
					</fieldset>
					<div class="main_table">
						<table width="100%" cellpadding="0" cellspacing="1"	class="font_stat" id="tb">
							<tr class="tr_td tb_head" style="background-color: rgb(232, 238, 247);">
								<th style="white-space: nowrap; cursor: pointer;">日期<order:order url='${pageInfo.url}'  orderName='static_date' orderMode='desc' base="${pageContext.request.contextPath}" /></th>
								<th style="white-space: nowrap; cursor: pointer;">广告名称<order:order url='${pageInfo.url}'  orderName='ad_name' orderMode='desc' base="${pageContext.request.contextPath}" /></th>
								<th style="white-space: nowrap; cursor: pointer;">渠道名称<order:order url='${pageInfo.url}'  orderName='channel_name' orderMode='desc' base="${pageContext.request.contextPath}" /></th>
								<th style="white-space: nowrap; cursor: pointer;">单价<order:order url='${pageInfo.url}'  orderName='in_price' orderMode='desc' base="${pageContext.request.contextPath}" /></th>
								<th style="white-space: nowrap; cursor: pointer;">广告点击数<order:order url='${pageInfo.url}'  orderName='sys_num' orderMode='desc' base="${pageContext.request.contextPath}" /></th>
								<th style="white-space: nowrap; cursor: pointer;">广告主确认数<order:order url='${pageInfo.url}'  orderName='confirm_num' orderMode='desc' base="${pageContext.request.contextPath}" /></th>
								<th style="white-space: nowrap; cursor: pointer;">确认率<order:order url='${pageInfo.url}'  orderName='ctrc' orderMode='desc' base="${pageContext.request.contextPath}" /></th>
							</tr>
							<!-- 表头-->
							<tr class="">
								<td>总计</td>
								<td>*</td>
								<td>**</td>
								<td>***</td>
								<td style="text-align: right;">
									<fmt:formatNumber  type="number" pattern="###,###" value="${total.sys_num}"/>
								</td>
								<td style="text-align: right;">
									<fmt:formatNumber  type="number" pattern="###,###" value="${total.confirm_num}"/>
								</td>
								<td style="text-align: right;">
									<fmt:formatNumber  type="number" pattern="###,###.####" value="${total.ctrc}"  minFractionDigits="2" maxFractionDigits="4" />%
								</td>
							</tr>
							<!-- 报表数据部分-主体数据-->
							<c:if test="${!empty list}">
							<c:forEach items="${list}" var="vo" varStatus="status">
							<tr class="">
								<td>${vo.static_date}</td>
								<td>${vo.ad_name}</td>
								<td>${vo.channel_name}</td>
								<td style="text-align: right;">
									<fmt:formatNumber  type="number" pattern="###,###.####" value="${vo.in_price}"/>
								</td>
								<td style="text-align: right;">
									<fmt:formatNumber  type="number" pattern="###,###" value="${vo.sys_num}"/>
								</td>
								<td style="text-align: right;">
									<fmt:formatNumber  type="number" pattern="###,###" value="${vo.confirm_num}"/>
								</td>
								<td style="text-align: right;">
									<fmt:formatNumber  type="number" pattern="###,###.####" value="${vo.ctrc}"  minFractionDigits="2" maxFractionDigits="4" />%
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