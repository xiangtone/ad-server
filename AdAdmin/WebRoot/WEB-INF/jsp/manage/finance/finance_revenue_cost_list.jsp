<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="escore" uri="/WEB-INF/tld/spring.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>运营管理后台财务管理确认收入成本</title>
<link href="${pageContext.request.contextPath}/css/common.css?v=${version}" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript"src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js?v=${version}"></script>
<script type="text/javascript">

function clearStatDate() {
	$('#month_stat_date').val('');
	$('#month_end_date').val('');
}

</script>
</head>

<body>
<div class="main">
	<div class="content clearfix">
		<div class="content_right admin_right">
			<div class="bord_bom1px">收支结算</div>
		<form action="manage!financeRevenueCostList.do" method="post" id="financeRevenueCost">
			<table width="100%" border="1" bordercolor="#85caff" cellpadding="1" cellspacing="0">
				<tr>
					<td style="color: blue;" bgcolor="#E8E8E8" >广告主ID</td>
					<td><input name="adv_Id" type="text" value="${bean.adv_Id }" /></td>
					<td style="color: blue;" bgcolor="#E8E8E8" >广告主名称</td>
					<td><input name="adv_email" type="text" value="${bean.adv_email}" /></td>
				</tr>
				<tr>
					<td style="color: blue;" bgcolor="#E8E8E8">活动ID</td>
					<td><input name="campaign_id" type="text" value="${bean.campaign_id}" /></td>
					<td style="color: blue;" bgcolor="#E8E8E8">活动名称</td>
					<td><input name="campaign_name" type="text" value="${bean.campaign_name}" /></td>
				</tr>
				<tr>
					<td style="color: blue;" bgcolor="#E8E8E8" >效果发生时间</td>
					<td style="color: blue;"><input type="text" id="month_stat_date" name="month_stat_date"  onClick="WdatePicker()" class="Wdate" value="${bean.month_stat_date}"/>						
					至<input type="text" id="month_end_date" name="month_end_date"  onClick="WdatePicker()" class="Wdate" value="${bean.month_end_date}"/>
					<input type="button" value="清空"	onclick="clearStatDate()" /></td>							
					<td style="color: blue;" bgcolor="#E8E8E8">平台类型</td>
					<td><select name="os" id="os" style="width: 155px;">
							<option value="" >全部</option>
							<option value="android" <c:if test="${bean.os == 'android'}">selected="selected"</c:if>>安卓</option>
							<option value="ios" <c:if test="${bean.os =='ios'}">selected="selected"</c:if>>iOS</option>
					</select></td>
					
				</tr>
					<tr>
					<td  colspan="4" bgcolor="#E8E8E8">
					<div style=" text-align: center;"><input type="submit" value="查询"/>										
					</div></td>
				</tr>
			</table>
						</form>
				<!--修改-->
			<div class="main_table">
			<table width="100%" cellpadding="0"cellspacing="1" class="font_stat">		
				<tr class="tr_td">				
					<th>发生时间</th>
					<th>广告主ID</th>
					<th>广告主</th>
					<th>活动ID</th>
					<th>活动名称</th>
					<th>平台类型</th>
					<th>确认收入</th>
					<th>确认成本</th>
					<th>毛利率</th>
					<!-- <th>收入差额</th> -->
				</tr>
				<tr style="text-align: center;">
								<td>汇总</td>
								<td>-</td>
								<td>-</td>
								<td>-</td>
								<td>-</td>
								<td>-</td>															
								<td> <font color="red"><escore:formatMoney
											value="${sum.sum_income_money}" maxFractionDigits="2" />
								</font></td>
								<td><font color="red"><escore:formatMoney
											value="${sum.sum_forecast_money}" maxFractionDigits="2" />
								</font>
								</td>
								<td><fmt:formatNumber value="${sum.sum_gross_profit}"
									type="percent" pattern="#0.000#" />%</td>
							</tr>
					<c:forEach items="${advList}" var="d">
					<tr>
						<td align="center">
							${d.month_stat_date }至${d.month_end_date }
						</td>
						<td align="center">
							${d.adv_Id }
						</td>
						<td align="center">
							${d.company_name}
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
						<c:if test="${d.income_money!=null }"><escore:formatMoney value="${d.income_money }" maxFractionDigits="2" /></c:if>
						</td>
						<td align="center">
						<c:if test="${d.exprice_money!=null }"><escore:formatMoney value="${d.exprice_money }" maxFractionDigits="2" /></c:if>
						</td>
						<td><fmt:formatNumber value="${d.gross_profit}" type="percent" pattern="#0.000#"/>%</td>
						<!--<td align="center">
						<c:if test="${d.balance!=null }"><escore:formatMoney value="${d.balance}" maxFractionDigits="2" /></c:if>
						</td> -->
					</tr>
					</c:forEach>
					<c:if test="${empty advList}">
						<tr>
							<td align="center" colspan="18" style="text-align: center;">暂无数据！</td>
						</tr>	
					</c:if>
			</table>
			</div>
			<div>${pageInfo.pageInfoStr}</div>
			<span class="clear_span" style="height: 150px;"></span>
		</div>
	</div>
</div>
</body>
</html>