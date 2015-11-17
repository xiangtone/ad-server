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
<link href="${pageContext.request.contextPath}/css/common.css?v=${version}" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/tabTool.css?v=${version}" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/manage/adEffectConfirm.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/submit.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/exporting.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tabTool.js?v=${version}"></script>


<style type="text/css">
.bgclor01 {
	background-color: #ACACAC !important;
}
</style>
<script type="text/javascript">
	//页面初始化加载
	$(document).ready(function() {
		click_init();
		rodio_read();
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
			<div class="bord_bom1px">统计概览</div>
			<div class="content_right admin_right">
				<form action="manage!censusGeneralView.do" method="post" id="censusGeneralView">
					<table width="100%" border="1">
						<tr>
							<td style="color: blue;" bgcolor="#E8E8E8" width="10%">请选择</td>
							<td width="80%">
								<div style="display: block;" id="response_div">
									<div>
										<input id="wall_response_category_radio_0" name="wall_response_category" type="radio" value="0" />
										<span>日</span>
										<span id="wall_response_span_0" style="display: none;">
											<input type="text" id="start_stat_date"	name="start_stat_date" maxlength="20" onfocus="WdatePicker()" class="Wdate" value="${bean.start_stat_date}" />
											至 
											<input type="text" id="end_stat_date" name="end_stat_date" maxlength="20" onfocus="WdatePicker()" class="Wdate" value="${bean.end_stat_date}" />
										</span>
									</div>
									<div>
										<input id="wall_response_category_radio_1" name="wall_response_category" type="radio" value="1"	checked="checked" />
										<span>周</span>
										<span id="wall_response_span_1" style="display: none;">
											<input type="text" id="week_stat_date" name="week_stat_date" maxlength="20" onfocus="WdatePicker()" class="Wdate" value="${bean.week_stat_date}" />
										</span>
									</div>
									<div>
										<input id="wall_response_category_radio_2" name="wall_response_category" type="radio" value="2"	checked="checked" />
										<span>月</span>
										<span id="wall_response_span_2" style="display: none;">
										<input type="text" id="month_stat_date" name="month_stat_date"	onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM'})" class="Wdate" value="${bean.month_stat_date}" /> </span>
									</div>
								</div></td>
							<td><input type="submit" value="查询" id="FindAll" width="10%" />
							</td>
						</tr>
					</table>
				</form>
				<ul class="tab_block2">    
      				<li class="click"><a href="javascript:void(0)" class="sub_1 tab_block2_hover click"><span class="click">概览</span></a></li>
     				<li><a href="javascript:void(0)" class="sub_2"><span>广告主/媒体</span></a></li>
     				<li><a href="javascript:void(0)" class="sub_3"><span>流量趋势</span></a></li>
     				<li><a href="javascript:void(0)" class="sub_4"><span>收入/支出</span></a></li>
    			</ul>
				 <div class="subblock_1" style="display:block;border: 1px solid #9dbaff;float: left;width:866px;border-top: 0;border-right: 0;height: 412px;">
				 	1
				 </div>
				 <div class="subblock_2" style="display:block;border: 1px solid #9dbaff;float: left;width: 866px;border-top: 0;border-right: 0;height: 412px;">
				 2
				 </div>
				  <div class="subblock_3" style="display:block;border: 1px solid #9dbaff;float: left;width: 866px;border-top: 0;border-right: 0;height: 412px;">
				  3
				 </div>
				  <div class="subblock_4" style="display:block;border: 1px solid #9dbaff;float: left;width: 866px;border-top: 0;border-right: 0;height: 412px;">
				  4
				 </div>
			</div>
		</div>
	</div>
</body>
</html>