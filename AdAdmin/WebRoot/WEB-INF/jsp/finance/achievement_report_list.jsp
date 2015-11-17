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
<title>财务管理财务收入成本总表 </title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Buttons/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Buttons/css/buttons.css">
<link href="${pageContext.request.contextPath}/css/page.css?v=${version}" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/manage/checkbox.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/dialog/lhgdialog.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js?v=${version}"></script>
<script type="text/javascript">
$(document).ready(function (){
	$("#achievementDown").click(function(){
		$("#achievement").attr("action","${pageContext.request.contextPath}/finance/achievementReportListDown.do").submit();
	});

	$("#achievementReport").click(function(){
		$("#achievement").attr("action","${pageContext.request.contextPath}/finance/achievementReportList.do").submit();
	});

});	


/**
 * 查看平台成本明细
 *	
 **/
function findPlaDetail(id,month_stat_date,month_end_date,cost_date) {
	var url = "manage!getPlaDetailInfo.do?confirm_id="+id+"&month_stat_date="+month_stat_date+"&month_end_date="+month_end_date+"&cost_date="+cost_date;
	new $.dialog({
		title:'平台成本明细',
		page:url,
		width:1100,
		height:430,
		drag:true,
		resize:true,
		cover:true,
		maxBtn:false}).ShowDialog();
}

/**
 * 查看渠道成本明细
 *	
 **/

function findChaDetail(id,month_stat_date,month_end_date,cost_date) {
	var url = "manage!getChaDetailInfo.do?confirm_id="+id+"&month_stat_date="+month_stat_date+"&month_end_date="+month_end_date+"&cost_date="+cost_date;
	new $.dialog({
		title:'渠道成本明细',
		page:url,
		width:1100,
		height:430,
		drag:true,
		resize:true,
		cover:true,
		maxBtn:false}).ShowDialog();
}
		/**
 * 查看
 *	
 **/
function findData(id){
	var url = "manage!findDataPhoto.do?id="+id;
	new $.dialog({
		title:'查看截图',
		page:url,
		width:600,
		height:500,
		drag:true,
		resize:true,
		cover:true,
		maxBtn:false}).ShowDialog();
}



function refresh(){
	$("#achievement").submit();
}
</script>
</head>
<body>
	<div class="main admin_main">
		<div class="content clearfix">
			<div class="content_right admin_right" style="overflow-x: scroll;">
				<div class="content_right content_new">
					<fieldset class="search_fieldset">
						<legend>财务收入成本总表</legend>
						<div id="search_bar">
							<form action="${pageContext.request.contextPath}/finance/achievementReportList.do" method="post" id="achievement">
								<table width="100%">
									<tr>
										<td>
											<table width="100%">
												<tr>
													<td>效果发生时间</td>
													<td>
														<input type="text" id="month_stat_date" name="month_stat_date"  onclick="WdatePicker()" class="Wdate" value="${bean.month_stat_date}"/>						
														至<input type="text" id="month_end_date" name="month_end_date"  onclick="WdatePicker()" class="Wdate" value="${bean.month_end_date}"/>
													</td>	
													<td>广告主ID/名称</td>
													<td><input name="adv" type="text" value="${bean.adv}" /></td>
												</tr>
												<tr>
													<td>活动ID/名称</td>
													<td>
														<input name="campaign" type="text" value="${bean.campaign}" />
													</td>
													<td>业绩月份</td>
													<td>
														<input type="text" id="month" name="month" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM'})" class="Wdate" value="${bean.month }">
													</td>	
												</tr>
												<tr>
													<td>收入确认时间</td>
													<td>
														<input type="text" id="confirm_stat_date" name="confirm_stat_date"  onclick="WdatePicker()" class="Wdate" value="${bean.confirm_stat_date}"/>						
														至<input type="text" id="confirm_end_date" name="confirm_end_date"  onclick="WdatePicker()" class="Wdate" value="${bean.confirm_end_date}"/>
													</td>
													<td ></td>
													<td>
													</td>
												</tr>
												
											</table>
										</td>
										<td width="15%" valign="middle" align="right">
											<div style="width: 100px;height: 100%;display: inline;line-height: 20px;">
												<button id="achievementReport" class="button button-pill button-primary" style="line-height: 21px;height: 21px;font-size: 13px;padding-left: 10px;padding-right: 10px;">查询</button>
												<button onclick="resetAll('achievement');" class="button button-pill button-primary" style="line-height: 21px;height: 21px;font-size: 13px;padding-left: 10px;padding-right: 10px;">重置</button>
											</div>
										</td>
									</tr>
								</table>
							</form>
						</div>
					</fieldset>
					<input name="achievementDown" type="button" id="achievementDown" value="导出Excel"/>
					<div class="main_table">
						<table width="100%" cellpadding="0" cellspacing="1" id="tb"	class="table_bod1 font_stat">
							<tr class="tr_td">
								<th rowspan="2">业绩月份</th>
								<th rowspan="2">收入确认时间</th>
								<th rowspan="2">业绩提交时间</th>
								<th rowspan="2">发生时间</th>
								<th rowspan="2">广告主名称</th>
								<th rowspan="2">活动id</th>
								<th rowspan="2">活动名称</th>
								<th rowspan="2">预确认数</th>
								<th rowspan="2">确认数</th>
								<th rowspan="2">损耗</th>
								<th rowspan="2">单价</th>
								<th rowspan="2">结算<br/>方式</th>
								<th rowspan="2">总收入</th>
								<th rowspan="2">总成本</th>
								<th rowspan="2">总毛利</th>
								<th colspan="3">平台</th>
								<th colspan="3">渠道</th>
								<th rowspan="2">平台<br/>类型</th>
								<th rowspan="2">状态</th>
								<th rowspan="2">销售<br/>人员</th>
								<th rowspan="2">大区</th>
								<th rowspan="2">资料</th>
							</tr>
							<tr class="tr_td">	
								<th>收入</th>
								<th>成本</th>
								<th>毛利</th>
								<th>收入</th>
								<th>成本</th>
								<th>毛利</th>
							</tr>
							<tr>
								<td align="center">
									汇总
								</td>
								<td align="center">
									--
								</td>
								<td align="center">
									--
								</td>
								<td align="center">
									--
								</td>
								<td align="center">
									--
								</td>
								<td align="center">
									--
								</td>
								<td align="center">
									--
								</td>
								<td align="center">
									<font color="red">${sum.sum_forecast_amount}</font>
								</td>
								<td align="center">
									<font color="red">${sum.sum_income_amount}</font>
								</td>
								<td align="center">
									--
								</td>
								<td align="center">
									--
								</td>
								<td align="center">
									--
								</td>
									<td align="center">
									<font color="red">
										<escore:formatMoney value="${sum.sum_income_money}" maxFractionDigits="2" />
									</font>
								</td>
								<td align="center">
									<font color="red">
										<escore:formatMoney value="${sum.sum_cost_money}" maxFractionDigits="2" />
									</font>
								</td>
								<td align="center">
									<font color="red">
										<fmt:formatNumber value="${sum.sum_gross_profit}" type="percent" pattern="#0.0#" />%
									</font>
								</td>
								<td align="center">
									<font color="red">
										<escore:formatMoney value="${sum.sum_income_plm_money}" maxFractionDigits="2" />
									</font>
								</td>
								<td align="center">
									<font color="red">
										<escore:formatMoney value="${sum.sum_cos_plm_money}" maxFractionDigits="2" />
									</font>
								</td>
								<td align="center">
									<font color="red">
										<fmt:formatNumber value="${sum.sum_plm_gross_profit}" type="percent" pattern="#0.0#" />%
									</font>
								</td>
								<td align="center">
									<font color="red">
										<escore:formatMoney	value="${sum.sum_income_cha_money}" maxFractionDigits="2" />
									</font>
								</td>
								<td align="center">
									<font color="red">
										<escore:formatMoney	value="${sum.sum_cost_cha_money}" maxFractionDigits="2" />
									</font>
								</td>
								<td align="center">
									<font color="red">
										<fmt:formatNumber value="${sum.sum_cha_gross_profit}" type="percent" pattern="#0.0#" />%
									</font>
								</td>
								<td align="center">
									--
								</td>
								<td align="center">
									--
								</td>
								<td align="center">
									--
								</td>
								<td align="center">
									--
								</td>
								<td align="center">
									--
								</td>				
							</tr>
							<c:forEach items="${list}" var="vo" varStatus="status">
								<tr style="text-align: center;">
									<td>${vo.month}</td>
									<td><fmt:formatDate value="${vo.confirm_time}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
									<td><fmt:formatDate value="${vo.publish_time}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
									<td>${vo.month_stat_date}至${vo.month_end_date}</td>
									<td><escore:subStr len="20" value="${vo.company_name}" /></td>
									<td>${vo.campaign_id}</td>
									<td>${vo.campaign_name}</td>
									<td>${vo.forecast_amount}</td>
									<td>${vo.income_amount}</td>
									<td>
										<fmt:formatNumber value="${vo.ullage}" type="percent" pattern="#0.00" />%</td>
									<td>${vo.price}</td>
									<td>${vo.charge_type}</td>
									<td><fmt:formatNumber type="number" pattern="###,###0.0#" value="${vo.income_money}"/></td>
									<td><fmt:formatNumber type="number" pattern="###,###0.0#" value="${vo.cost_money}"/></td>
									<td>1
										<c:choose>
											<c:when test="${vo.gross_profit_rate!= null}">
												<fmt:formatNumber value="${vo.gross_profit_rate}" type="percent" pattern="#0.0#" />%
											</c:when>
											<c:otherwise>
											0.0%
											</c:otherwise>
										</c:choose>
									</td>
									<td><fmt:formatNumber  type="number" pattern="###,###0.0#" value="${vo.platform_income_money}"/></td>
									<td>
										<a href="javascript:void(0);" onclick="findPlaDetail('${vo.id}','${vo.month_stat_date}','${vo.month_end_date}','${vo.cost_date}')">
											<fmt:formatNumber  type="number" pattern="###,###0.0#" value="${vo.platform_cost_money}"/>
										</a>
									</td>
									<td>
										<c:choose>
											<c:when test="${vo.pla_gross_profit_rate!= null}">
												<fmt:formatNumber value="${vo.pla_gross_profit_rate}" type="percent" pattern="#0.0#" />%
											</c:when>
											<c:otherwise>
											 	0.0%
											</c:otherwise>
										</c:choose>
									</td>
									<td><fmt:formatNumber  type="number" pattern="###,###0.0#" value="${vo.channel_income_money}"/></td>
									<td>
										<a href="javascript:void(0);" onclick="findChaDetail('${vo.id}','${vo.month_stat_date}','${vo.month_end_date}','${vo.cost_date}')">
											<fmt:formatNumber  type="number" pattern="###,###0.0#" value="${vo.channel_cost_money}"/>
										</a>
									</td>
									<td>
										<c:choose>
											<c:when test="${vo.cha_gross_profit_rate!= null}">
												<fmt:formatNumber value="${vo.cha_gross_profit_rate}" type="percent" pattern="#0.0#" />%
											</c:when>
											<c:otherwise>
											 	0.0%
											</c:otherwise>
										</c:choose>
									</td>
									<td>${vo.os}</td>
									<td>
										<c:choose>
											<c:when test="${vo.status == 3}">
												已发布
											</c:when>
											<c:when test="${vo.status == 8}">
												通过
											</c:when>
											<c:when test="${vo.status == 9}">
												不通过
											</c:when>
											<c:otherwise>
								 				未发布
											</c:otherwise>
										</c:choose>
									</td>
									<td>${vo.name}</td>
									<td>
										 <c:forEach items="${areaList}" var="entity">
											<c:if test="${vo.area_type eq entity.id}"> ${entity.area_name}</c:if>
										</c:forEach>
									</td>
									<td>
										<a href="javascript:void(0);" onclick="findData('${vo.id}')">
											<font color="red;">查看</font>
										</a>
									</td>
								</tr>
							</c:forEach>
							<c:if test="${empty list}">
								<tr>
									<td align="center" colspan="26" style="text-align: center;">暂无数据！</td>
								</tr>
							</c:if>
						</table>
					</div>
					${pageInfo.pageInfoStr}
				</div>
			</div>
		</div>
	</body>
</html>