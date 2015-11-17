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
<script type="text/javascript">
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
<script type="text/javascript">
//流量图
$(function () {
	
	var _categories='${jsonstatic_date}';
	if(_categories){
		_categories=eval("("+_categories+")");
	}else{
		_categories=[];
	}
	var _pv='${jsonpv}';
	if(_pv){
		_pv=eval("("+_pv+")");
	}else{
		_pv=[];
	}
	var _click='${jsonclick}';
	if(_click){
		_click=eval("("+_click+")");
	}else{
		_click=[];
	}
	var _income_amount='${jsonincome_amount}';
	if(_income_amount){
		_income_amount=eval("("+_income_amount+")");
	}else{
		_income_amount=[];
	}
	
    $('#container').highcharts({
        chart: {
            type: 'line',
            marginRight: 130,
            marginBottom: 25
        },
        title: {
            text: '平台流量趋势图',
            x: -20 //center
        },
        subtitle: {
            text: 'Source: www.adwalker.com',
            x: -20
        },
        xAxis: {
        	categories:_categories
        },
        yAxis: {
            title: {
                text: 'amount (数量)'
            },
            plotLines: [{
                value: 0,
                width: 1,
                color: '#808080'
            }]
        },
        tooltip: {
            valueSuffix: '$'
        },
         tooltip: {
        crosshairs: true,
        shared: true
    },
        legend: {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'top',
            x: -10,
            y: 100,
            borderWidth: 0
        },
        series: [{
            name: '展示总PV',
            data: _pv
        }, {
            name: '总点击',
            data: _click
        }, {
            name: '总确认激活',
            data: _income_amount
        }]
    });
});
</script>
</head>
<body>
	<div class="main">

		<div class="content clearfix">
			<div class="content_right admin_right">
				<div class="bord_bom1px">统计概览</div>
				<form action="manage!yjfFlow.do" method="post"
					id="censusGeneralView">
					<table width="100%" border="1" 
						bordercolor="#85caff">
						<tr height="10px;">
							<td style="color: blue;" bgcolor="#E8E8E8" width="10%">请选择</td>
							<td width="70%">
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
											<input id="wall_response_category_radio_1" name="wall_response_category" type="radio" value="1" checked="checked"><span>周</span>
											<span id="wall_response_span_1" style="display: none;">
												<input type="text" id="week_stat_date" name="week_stat_date" maxlength="20"
																				onfocus="WdatePicker()" class="Wdate" value="${bean.week_stat_date}" />
											</span>
										</div>
										<div>
											<input id="wall_response_category_radio_2" name="wall_response_category" type="radio" value="2" checked="checked"><span>月</span>
											<span id="wall_response_span_2" style="display: none;">
												<input type="text" id="month_stat_date" name="month_stat_date"  onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM'})" class="Wdate" value="${bean.month_stat_date}"/>
											</span>
										</div>
									</div>
								</td>					
								<td width="10%">
								<select name="os" id="os" style="width: 155px;">
										<option value="">全部</option>
										<option value="android"
											<c:if test="${bean.os == 'android'}">selected="selected"</c:if>>android</option>
										<option value="ios"
											<c:if test="${bean.os =='ios'}">selected="selected"</c:if>>ios</option>
								</select>
								</td>
								<td>					
									<input type="submit" value="查询" id="FindAll" width="10%"/>
								</td>
						</tr>
					</table>
				</form>			
				<div style="width:1150px;overflow-x: scroll;" >
					<div id="container" style="min-width: 1200px; height: 400px; margin: 0 auto;width: 100%"></div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>