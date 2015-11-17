<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="escore" uri="/WEB-INF/tld/spring.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"><html xmlns="http://www.w3.org/1999/xhtml">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>运营管理后台活动查询统计</title>
<link href="${pageContext.request.contextPath}/css/common.css?v=${version}"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/manage/adEffectConfirm.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/submit.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/exporting.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/highcharts.js"></script>

<style type="text/css">
.bgclor01 {
	background-color: #ACACAC !important;
}
</style>
<script type="text/javascript"><!--
//页面初始化加载
$(document).ready(function(){
	click_init();
	rodio_read();
});

function click_init(){
	$("#response_div input:radio").click(function(){
		if($(this).val()==0){
			$("#wall_response_span_0").show();
			$("#wall_response_span_1").hide();
			$("#wall_response_span_2").hide();
			$('#week_stat_date').val('');
			$('#month_stat_date').val('');
		}else if($(this).val()==1){
			$("#wall_response_span_0").hide();
			$("#wall_response_span_1").show();
			$("#wall_response_span_2").hide();
			$('#start_stat_date').val('');
			$('#end_stat_date').val('');
			$('#month_stat_date').val('');
		}else if($(this).val()==2){
			$("#wall_response_span_0").hide();
			$("#wall_response_span_1").hide();
			$("#wall_response_span_2").show();
			$('#week_stat_date').val('');
			$('#start_stat_date').val('');
			$('#end_stat_date').val('');
		}
	});
}

function rodio_read(){
	var b="${bean.start_stat_date}";
	var a="${bean.month_stat_date}";
	var c="${bean.week_stat_date}";
	if(a){
		$("#wall_response_category_radio_2").attr("checked","checked");
	$("#wall_response_span_0").hide();
	$("#wall_response_span_1").hide();
	$("#wall_response_span_2").show();
		}else if(c){
			$("#wall_response_category_radio_1").attr("checked","checked");
			$("#wall_response_span_0").hide();
			$("#wall_response_span_1").show();	
			$("#wall_response_span_2").hide();
			}else{
				$("#wall_response_category_radio_0").attr("checked","checked");
				$("#wall_response_span_0").show();
				$("#wall_response_span_1").hide();		
				$("#wall_response_span_2").hide();
					}
}

</script>
</head>
<body>
	<div class="main">

		<div class="content clearfix">
			<div class="content_right admin_right">
				<div class="bord_bom1px">广告主top10/媒体top10</div>
				<form action="manage!viewTopAppAdv.do" method="post"
					id="censusGeneralView">
					<table width="100%" border="1" 
						bordercolor="#85caff">
						<tr height="10px;">
							<td style="color: blue;" bgcolor="#E8E8E8" width="10%">请选择</td>
							<td width="80%">
									<div style="display: block;" id="response_div">
										<div>
											<input id="wall_response_category_radio_0" name="wall_response_category" type="radio" value="0"><span>日</span>
											<span id="wall_response_span_0" style="display: none;">
											<input type="text" id="start_stat_date" name="start_stat_date" maxlength="20"
																				onfocus="WdatePicker()" class="Wdate" value="${bean.start_stat_date}" />至
											<input type="text" id="end_stat_date" name="end_stat_date" maxlength="20"
																				onfocus="WdatePicker()" class="Wdate" value="${bean.end_stat_date}" />
											</span>
										</div>
										<div>
											<input id="wall_response_category_radio_1" name="wall_response_category" type="radio" value="1" ><span>周</span>
											<span id="wall_response_span_1" style="display: none;">
												<input type="text" id="week_stat_date" name="week_stat_date" maxlength="20"
																				onfocus="WdatePicker()" class="Wdate" value="${bean.week_stat_date}" />
											</span>
										</div>
										<div>
											<input id="wall_response_category_radio_2" name="wall_response_category" type="radio" value="2" ><span>月</span>
											<span id="wall_response_span_2" style="display: none;">
												<input type="text" id="month_stat_date" name="month_stat_date"  onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM'})" class="Wdate" value="${bean.month_stat_date}"/>
											</span>
										</div>
									</div>
								</td>					
								<td>					
									<input type="submit" value="查询" id="FindAll" width="10%"/>
								</td>
						</tr>
					</table>
				</form>
				<div>广告主top10</div>
				<table width="100%" cellpadding="0" cellspacing="1" id="tb"
					class="table_bod1 font_stat">
					<tr class="tr_td">
						<td>排名</td>
						<td>广告主名称</td>
						<td>确认数</td>
						<td>金额</td>
						<td>占比</td>
						<!-- <td>排名变化</td> -->
					</tr>	
					<c:forEach items="${listAdv}" var="a" varStatus="xh">
						<tr style="text-align: center;">
							<td>${xh.index+1}</td>
							<td>${a.company_name}</td>
							<td>${a.income_amount}</td>
							<td>
							<c:if test="${a.income_money!=null }"><escore:formatMoney value="${a.income_money }" maxFractionDigits="2" /></c:if>
							</td>
							<td><fmt:formatNumber value="${a.proportion}" type="percent" pattern="#0.000#"/>%</td>
							<!--<td>${a.income_money}</td> -->
						</tr>
					</c:forEach>
					<c:if test="${empty listAdv}">
						<tr>
							<td align="center" colspan="15" style="text-align: center;">暂无数据！</td>
						</tr>
					</c:if>

				</table>
				<div>媒体top10</div>
				<table width="100%" cellpadding="0" cellspacing="1" id="tb"
					class="table_bod1 font_stat">
					<tr class="tr_td">
						<td>排名</td>
						<td>媒体名称</td>
						<td>请求数</td>
						<td>展示出</td>
						<td>下载数</td>
						<td>激活数</td>
						<td>金额</td>
						<td>金额占比</td>
					</tr>
					<c:forEach items="${listApp}" var="k" varStatus="xh">
						<tr style="text-align: center;">
							<td>${xh.index+1}</td>
							<td>${k.app_name}</td>
							<td>${k.cpc_cost }</td>
							<td>${k.cpm_cost}</td>
							<td>${k.cpd_cost}</td>
							<td>${k.cpa_cost}</td>
							<td><c:if test="${k.cost!=null }"><escore:formatMoney value="${k.cost }" maxFractionDigits="2" /></c:if></td>
							<td><fmt:formatNumber value="${k.proportion}" type="percent" pattern="#0.000#"/>%</td>
						</tr>
					</c:forEach>
					<c:if test="${empty listApp}">
						<tr>
							<td align="center" colspan="15" style="text-align: center;">暂无数据！</td>
						</tr>
					</c:if>

				</table>				
			</div>
		</div>
	</div>
</body>
</html>
