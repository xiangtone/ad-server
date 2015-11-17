<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="page" uri="/WEB-INF/tld/paginationTagWeb.tld"%>
<%@ taglib prefix="order" uri="/WEB-INF/tld/orderTag.tld"%>
<%@ taglib prefix="escore" uri="/WEB-INF/tld/spring.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>运营管理后台报表</title>
<link
	href="${pageContext.request.contextPath}/css/common.css?v=${version}"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/submit.js?v=${version}"></script>
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
function exportSalesDown(form){
    editForm.action="manage!salesAchievementDown.do";
    editForm.submit();
}

function exportSales(form){
    editForm.action="manage!salesAchievement.do";
    editForm.submit();
}

function changeOrder(key,url){
	 $.cookie("table_order",key);
	 window.location.href=url;
}
</script>
</head>
<body marginwidth="0" marginheight="0">
	<div class="main">
		<div class="content clearfix">
			<div class="content_right content_new">
				<form name="editForm" action="manage!salesAchievement.do" method="post">
					<table width="98%" border="0" align="center" cellpadding="0"
						cellspacing="1" class="style_table">
						<!-- 标题部分 -->
						<tbody>
							<tr>
								<td colspan="6"><b>销售业绩</b></td>
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
								<td colspan="" align="left">广告主ID</td>
								<td colspan="1" align="left">
									<input name="adv_id" id="t.ADV_ID" value="${bean.adv_id}" /> 
								</td>
							</tr>
							<tr>

								<td colspan="" align="left">活动ID</td>
								<td colspan="1" align="left">
									 <input name="campaign_id" id="t.campaign_id" value="${bean.campaign_id}" /> 
								</td>
								<td colspan="" align="left">活动名称</td>
								<td colspan="1" align="left">
									 <input name="campaign_name" value="${bean.campaign_name}"/> 
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
							<tr>
							<td colspan="" align="left">销售人</td>
								<td colspan="1" align="left">
									 <input name="sales_name" value="${bean.sales_name}"/> 
								</td>
							<td colspan="" align="left">大区</td>
							<td colspan="3" align="left">
								 <select name="area_type_ad">
										<option value="">全部</option>
										<option value="4" <c:if test="${bean.area_type_ad==4}"> selected="selected"</c:if> >华南</option>
										<option value="1" <c:if test="${bean.area_type_ad ==1}"> selected="selected"</c:if> >华东</option>
										<option value="2" <c:if test="${bean.area_type_ad ==2}"> selected="selected"</c:if> >华北</option>
										<option value="0" <c:if test="${bean.area_type_ad ==0}"> selected="selected"</c:if> >平台</option>
								</select>
							</td>
							</tr>
							<!-- 搜索项部分-按钮部分-->
							<tr>
								<td colspan="6">
									<input type="submit" value="搜索" onclick="exportSales(this.form);"/> &nbsp;&nbsp;
									<input type="button" name="Submit" onclick="exportSalesDown(this.form);" value="导出Excel" />
								</td>
							</tr>
						</tbody>
					</table>
					<div class="main_table">
						<table width="100%" cellpadding="0" cellspacing="1"	class="font_stat" id="tb">
							<tr class="tr_td tb_head" style="background-color: rgb(232, 238, 247);">
								<th>发生时间</th>
								<th>广告主ID</th>
								<th>广告主账号</th>
								<th>活动ID</th>
								<th>活动名称</th>
								<th>平台类型</th>
								<th>大区</th>
								<th>销售人</th>
								<th>确认收入</th>
								
								
							</tr>	
								<tr class="">
									<td>总计</td>
															<td>*</td>
															<td>**</td>
															<td>***</td>
															<td>****</td>
															<td>*****</td>
															<td>****</td>
															<td>*****</td>
															<td style="text-align: right;">
																<fmt:formatNumber  type="number" pattern="###,###.##" value="${sumresult.sum_income_money}"  minFractionDigits="2" maxFractionDigits="2" />
															</td>
														</tr>												
														<c:if test="${!empty list}">
															<c:forEach items="${list}" var="d" varStatus="status">
														<tr class="">
															<td align="center">
							${d.month_stat_date }至${d.month_end_date }
						</td>
						<td align="center">
							${d.adv_Id }
						</td>
						<td align="center">
							${d.adv_email }
						</td>
						<td align="center">
							${d.campaign_id }
						</td>
						<td align="center">
							${d.campaign_name}
						</td>
						<td align="center">
							${d.os}
						</td>								
						<td align="center">
							${d.area_type_name}
						</td>
						<td align="center">
							${d.sales_name}
						</td>
						<td align="center">
						<c:if test="${d.income_money!=null }"><escore:formatMoney value="${d.income_money }" maxFractionDigits="2" /></c:if>
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