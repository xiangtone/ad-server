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
<title>运营管理后台活动查询统计</title>
<link href="${pageContext.request.contextPath}/css/common.css?v=${version}"
	rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/manage/adEffectConfirm.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/submit.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/exporting.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/highcharts.js"></script>

<style type="text/css">
.bgclor01 {
	background-color: #ACACAC !important;
}
</style>
<script type="text/javascript">
	//页面初始化加载
	$(document).ready(function() {
		//click_init();
		//rodio_read();
	});

	function click_init() {
		$("#response_div input:radio").click(function() {
			if ($(this).val() == 0) {
				$("#wall_response_span_0").show();
				$("#wall_response_span_1").hide();
				$("#wall_response_span_2").hide();
				$('#week_stat_date').val('');
				$('#month_stat_date').val('');
			} else if ($(this).val() == 1) {
				$("#wall_response_span_0").hide();
				$("#wall_response_span_1").show();
				$("#wall_response_span_2").hide();
				$('#start_stat_date').val('');
				$('#end_stat_date').val('');
				$('#month_stat_date').val('');
			} else if ($(this).val() == 2) {
				$("#wall_response_span_0").hide();
				$("#wall_response_span_1").hide();
				$("#wall_response_span_2").show();
				$('#week_stat_date').val('');
				$('#start_stat_date').val('');
				$('#end_stat_date').val('');
			}
		});
	}

	function rodio_read() {
		var b = "${bean.start_stat_date}";
		var a = "${bean.month_stat_date}";
		var c = "${bean.week_stat_date}";
		if (a) {
			$("#wall_response_category_radio_2").attr("checked", "checked");
			$("#wall_response_span_0").hide();
			$("#wall_response_span_1").hide();
			$("#wall_response_span_2").show();
		} else if (c) {
			$("#wall_response_category_radio_1").attr("checked", "checked");
			$("#wall_response_span_0").hide();
			$("#wall_response_span_1").show();
			$("#wall_response_span_2").hide();
		} else {
			$("#wall_response_category_radio_0").attr("checked", "checked");
			$("#wall_response_span_0").show();
			$("#wall_response_span_1").hide();
			$("#wall_response_span_2").hide();
		}
	}

	function viewtop() {
		var url = "manage!viewTopAppAdv.do";
		window.location.href = url;
	}
	function flow() {
		var url = "manage!yjfFlow.do";
		window.location.href = url;
	}
	function incomeExpenses() {
		var url = "manage!reportIncomeExpenses.do";
		window.location.href = url;
	}
</script>
</head>
<body>
	<div class="main">

		<div class="content clearfix">
			<div class="content_right admin_right">
				<div class="bord_bom1px">统计概览</div>
				<form action="manage!censusGeneralView.do" method="post"
					id="censusGeneralView">
					<table width="100%" border="1" bordercolor="#85caff">
						<tr height="10px;">
							<td style="color: blue;" bgcolor="#E8E8E8" width="10%">请选择</td>
							<td width="80%">
								<div style="display: block;" id="response_div">
									<div>
										<input id="wall_response_category_radio_0" name="wall_response_category" type="radio" value="0" />
										<span>日</span>
									<%--  <span id="wall_response_span_0" style="display: none;">
											<input type="text" id="start_stat_date"	name="start_stat_date" maxlength="20" onfocus="WdatePicker()" class="Wdate" value="${bean.start_stat_date}" />
											至 
											<input type="text" id="end_stat_date" name="end_stat_date" maxlength="20" onfocus="WdatePicker()" class="Wdate" value="${bean.end_stat_date}" />
										</span> --%>	
									</div>
									<div>
										<input id="wall_response_category_radio_1" name="wall_response_category" type="radio" value="1"/>
										<span>周</span>
										<%--<span id="wall_response_span_1" style="display: none;">
											<input type="text" id="week_stat_date" name="week_stat_date" maxlength="20" onfocus="WdatePicker()" class="Wdate" value="${bean.week_stat_date}" />
										</span> --%>	
									</div>
									<div>
										<input id="wall_response_category_radio_2" name="wall_response_category" type="radio" value="2"/>
										<span>月</span>
										<%--<span id="wall_response_span_2" style="display: none;">
										<input type="text" id="month_stat_date" name="month_stat_date"	onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM'})" class="Wdate" value="${bean.month_stat_date}" /> </span>
									--%>	
									</div>
								</div></td>
							<td><input type="submit" value="查询" id="FindAll" width="10%" />
							</td>
						</tr>
					</table>
				</form>
				<table>
					<tr>
						<td><input type="button" name="add" value="广告主/媒体"
							onclick="viewtop()" />
						</td>
						<td><input type="button" name="add" value="流量趋势图"
							onclick="flow()" />
						</td>
						<td><input type="button" name="add" value="收入/支出"
							onclick="incomeExpenses()" />
						</td>
					</tr>
				</table>
				<div>总体</div>
				<table width="100%" cellpadding="0" cellspacing="1" id="tb"
					class="table_bod1 font_stat">
					<tr class="tr_td">
						<td>日期</td>
						<td>PV</td>
						<td>点击</td>
						<td>下载</td>
						<td>系统效果数</td>
						<td>广告主确认数</td>
						<td>收入</td>
						<td>成本</td>
						<td>利润</td>
						<td>毛利率</td>
					</tr>
						<tr style="text-align: center;">
							<td>${bean.start_date}至${bean.end_date}</td>
							<td><fmt:formatNumber value="${l.pv_amount}" type="number" pattern="#,#00.0#" /></td>
							<td><fmt:formatNumber value="${l.click_amount}" type="number" pattern="#,#00.0#" /></td>
							<td><fmt:formatNumber value="${l.down_amount}" type="number" pattern="#,#00.0#" /></td>
							<td><fmt:formatNumber value="${l.sum_platform_amount}" type="number" pattern="#,#00.0#" /></td>
							<td><fmt:formatNumber value="${l.sum_income_amount}" type="number" pattern="#,#00.0#" /></td>
							<td><fmt:formatNumber value="${l.sum_income_money}" type="number" pattern="#,#00.0#" /></td>
							<td><fmt:formatNumber value="${l.sum_cost_price}" type="number" pattern="#,#00.0#" /></td>
							<td><fmt:formatNumber value="${l.profit_money}" type="number" pattern="#,#00.0#" /></td>
							<td><fmt:formatNumber value="${l.gross_profit_margin}"
									type="percent" pattern="#0.000#" />%</td>
						</tr>
				</table>
				<div>平台</div>
				<table width="100%" cellpadding="0" cellspacing="1" id="tb"
					class="table_bod1 font_stat">
					<tr class="tr_td">
						<td>日期</td>
						<td>PV</td>
						<td>点击</td>
						<td>下载</td>
						<td>系统效果数</td>
						<td>广告主确认数</td>
						<td>收入</td>
						<td>成本</td>
						<td>利润</td>
						<td>毛利率</td>
					</tr>
						<tr style="text-align: center;">
							<td>${bean.start_date}至${bean.end_date}</td>
							<td><fmt:formatNumber value="${p.pv_amount}" type="number" pattern="#,#00.0#" /></td>
							<td><fmt:formatNumber value="${p.click_amount}" type="number" pattern="#,#00.0#" /></td>
							<td><fmt:formatNumber value="${p.down_amount}" type="number" pattern="#,#00.0#" /></td>
							<td><fmt:formatNumber value="${p.sum_platform_amount}" type="number" pattern="#,#00.0#" /></td>
							<td><fmt:formatNumber value="${p.sum_income_amount}" type="number" pattern="#,#00.0#" /></td>
							<td><fmt:formatNumber value="${p.sum_income_money}" type="number" pattern="#,#00.0#" /></td>
							<td><fmt:formatNumber value="${p.sum_cost_price}" type="number" pattern="#,#00.0#" /></td>
							<td><fmt:formatNumber value="${p.profit_money}" type="number" pattern="#,#00.0#" /></td>
							<td><fmt:formatNumber value="${p.gross_profit_margin}"
									type="percent" pattern="#0.000#" />%</td>
						</tr>				
				</table>
				<div>渠道</div>
				<table width="100%" cellpadding="0" cellspacing="1" id="tb"
					class="table_bod1 font_stat">
					<tr class="tr_td">
						<td>日期</td>
						<td>PV</td>
						<td>点击</td>
						<td>下载</td>
						<td>系统效果数</td>
						<td>广告主确认数</td>
						<td>收入</td>
						<td>成本</td>
						<td>利润</td>
						<td>毛利率</td>
					</tr>
					<c:forEach items="${listChannel}" var="c">
						<tr style="text-align: center;">
							<td>${c.static_date}</td>
							<td><c:if test="${c.pv_amount==null }">--</c:if> <c:if
									test="${c.pv_amount!=null }">${c.pv_amount}</c:if></td>
							<td><c:if test="${c.click_amount==null }">--</c:if> <c:if
									test="${c.click_amount!=null }">${c.click_amount}</c:if></td>
							<td><c:if test="${c.down_amount==null }">--</c:if> <c:if
									test="${c.down_amount!=null }">${c.down_amount}</c:if></td>
							<td><c:if test="${c.sum_platform_amount==null }">--</c:if> <c:if
									test="${c.sum_platform_amount!=null }">${c.sum_platform_amount}</c:if>
							</td>
							<td><fmt:formatNumber value="${c.sum_income_amount}" type="number" pattern="#,#00.0#" /></td>
							<td><fmt:formatNumber value="${c.sum_income_money}" type="number" pattern="#,#00.0#" /></td>
							<td><fmt:formatNumber value="${c.sum_cost_price}" type="number" pattern="#,#00.0#" /></td>
							<td><fmt:formatNumber value="${c.profit_money}" type="number" pattern="#,#00.0#" /></td>
							<td><fmt:formatNumber value="${c.gross_profit_margin}"
									type="percent" pattern="#0.000#" />%</td>
						</tr>
					</c:forEach>
					<c:if test="${empty listChannel}">
						<tr>
							<td align="center" colspan="10" style="text-align: center;">暂无数据！</td>
						</tr>
					</c:if>

				</table>
				<%--
				<div>活动操作记录</div>
				<table width="100%" cellpadding="0" cellspacing="1" id="tb"
					class="table_bod1 font_stat">
					<tr class="tr_td">
						<td>活动ID</td>
						<td>活动名称</td>
						<td>操作</td>
						<td>说明</td>
						<td>操作人</td>
						<td>操作时间</td>
						<td>当前在线活动数</td>
						
					</tr>
					<c:forEach items="${listChannel}" var="c">
						<tr style="text-align: center;">
						<td>${c.stat_date}</td>
							<td>${c.pv_amount}</td>
							<td>${c.click_amount}</td>
							<td>${c.down_amount}</td>
							<td>${c.sum_platform_amount}</td>
							<td>${c.sum_income_amount}</td>
							<td>${c.sum_income_money}</td>							
						</tr>
					</c:forEach>
					<c:if test="${empty listChannel}">
						<tr>
							<td align="center" colspan="15" style="text-align: center;">暂无数据！</td>
						</tr>
					</c:if>

				</table>
			  --%>
			</div>
		</div>
	</div>
</body>
</html>