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
//柱状图收支
$(function () {
	var _static_date='${jsonstatic_date}';
	if(_static_date){
		_static_date=eval("("+_static_date+")");
	}else{
		_static_date=[];
	}
	var _cost='${jsoncost}';
	if(_cost){
		_cost=eval("("+_cost+")");
	}else{
		_cost=[];
	}
	var _income_money='${jsonincome_money}';
	if(_income_money){
		_income_money=eval("("+_income_money+")");
	}else{
		_income_money=[];
	}
    $('#container_pillar').highcharts({
        chart: {
            type: 'column'
        },
        title: {
            text: '平台平台收支'
        },
        subtitle: {
            text: 'Source: www.adwalker.com'
        },
        xAxis: {
            categories: _static_date
        },
        yAxis: {
            min: 0,
            title: {
                text: 'money (元)'
            }
        },
        tooltip: {
            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                '<td style="padding:0"><b>{point.y:.1f} </b></td></tr>',
            footerFormat: '</table>',
            shared: true,
            useHTML: true
        },
        plotOptions: {
            column: {
                pointPadding: 0.1,
                borderWidth: 0
            }
        },
        series: [{
            name: 'Income',
            data: _income_money

        }, {
            name: 'Cost',
            data: _cost

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
				<form action="manage!reportIncomeExpenses.do" method="post"
					id="censusGeneralView">
					<table width="100%" border="1" 
						bordercolor="#85caff">
						<tr height="10px;">
							<td style="color: blue;" bgcolor="#E8E8E8" width="10%">请选择</td>
							<td width="60%">
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
								<select name="android_ios_type">
													<c:choose>
														<c:when test="${bean.android_ios_type =='android'}">
															<option value="" >全部</option>
															<option value="android" selected="selected">安卓</option>
															<option value="ios">IOS</option>
														</c:when>
														<c:when test="${bean.android_ios_type =='ios'}">
															<option value="">全部</option>
															<option value="android">安卓</option>
															<option value="ios" selected="selected">IOS</option>
														</c:when>														
														<c:otherwise>
															<option value=""  selected="selected">全部</option>
															<option value="android">安卓</option>
															<option value="ios">IOS</option>
														</c:otherwise>
													</c:choose>
												</select>	
																				
								</td>
									<td width="10%">
								<select name="platform_channel_type">
													<c:choose>
														<c:when test="${bean.platform_channel_type == 0}">
															<option value="" >总体</option>
															<option value="0" selected="selected">平台</option>
															<option value="1">渠道</option>
														</c:when>
														<c:when test="${bean.platform_channel_type == 1}">
															<option value="">总体</option>
															<option value="0">平台</option>
															<option value="1" selected="selected">渠道</option>
														</c:when>														
														<c:otherwise>
															<option value=""  selected="selected">总体</option>
															<option value="0">平台</option>
															<option value="1">渠道</option>
														</c:otherwise>
													</c:choose>
												</select>									
								</td>
								<td>					
									<input type="submit" value="查询" id="FindAll" width="10%"/>
								</td>
						</tr>
					</table>
				</form>			
				<div style="width:1150px;overflow-x: scroll;" >
				   <div id="container_pillar" style="min-width: 400px; height: 400px; margin: 0 auto"></div>
				   </div>
			</div>
		</div>
	</div>
</body>
</html>