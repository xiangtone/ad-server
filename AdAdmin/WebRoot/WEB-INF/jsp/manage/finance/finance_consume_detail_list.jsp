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
<title>运营管理后台财务管理收入明细</title>
<link href="${pageContext.request.contextPath}/css/common.css?v=${version}" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/manage/adEffectConfirm.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/submit.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js?v=${version}"></script>
<script type="text/javascript">
	function clearStatDate() {
		$('#statDateStartTime').val('');
		$('#statDateEndTime').val('');
	}
</script>
</head>
<body>
	<div class="main">
		<div class="content clearfix">
			<div class="admin_right">
				<div class="content_right content_new">
					<div class="bord_bom1px">预确认收入</div>
					<!--新增-->
					<form action="" method="post" id="conSumeDetail_Form">
						<table width="100%" border="1" bordercolor="#85caff"
							cellpadding="1" cellspacing="0">
							<tr>
								<td style="color: blue;" bgcolor="#E8E8E8">广告主ID/名称</td>
								<td><input name="adv" type="text"
									value="${bean.adv}" />
								</td>
								<td style="color: blue;" bgcolor="#E8E8E8">活动ID/名称</td>
								<td><input name="campaign" type="text"
									value="${bean.campaign}" />
								</td>
							</tr>
							<tr>
									<td style="color: blue;" bgcolor="#E8E8E8">媒体类型</td>
								<td><select name="media_type" id="media_type"
									style="width: 155px;">
										<option value="">全部</option>
										<option value="平台"
											<c:if test="${bean.media_type =='平台'}">selected="selected"</c:if>>平台</option>
										<option value="渠道"
											<c:if test="${bean.media_type =='渠道'}">selected="selected"</c:if>>渠道</option>
								</select>
								</td>
								
								<td style="color: blue;" bgcolor="#E8E8E8">平台类型</td>
								<td><select name="os" id="os" style="width: 155px;">
										<option value="">全部</option>
										<option value="android"
											<c:if test="${bean.os == 'android'}">selected="selected"</c:if>>android</option>
										<option value="ios"
											<c:if test="${bean.os =='ios'}">selected="selected"</c:if>>ios</option>
								</select>
								</td>

							</tr>
							<tr>
								<td style="color: blue;" bgcolor="#E8E8E8">效果发生日期</td>
								<td style="color: blue;"><input id="statDateStartTime"
									name="statDateStartTime" onClick="WdatePicker()" type="text"
									value="${bean.statDateStartTime}" class="Wdate"
									readonly="readonly" /> 至<input id="statDateEndTime"
									name="statDateEndTime" type="text" onClick="WdatePicker()"
									value="${bean.statDateEndTime}" class="Wdate"
									readonly="readonly" /> <input type="button" value="清空"
									onclick="clearStatDate()" />
								</td>
							</tr>
							<tr>

								<td colspan="4" bgcolor="#E8E8E8"><div
										style="text-align: center;">
										<input type="button" value="查询" id="conSumeDetail_Search" />
										<input type="button" value="导出数据" id="conSumeDetail_Report" />
									</div>
								</td>
							</tr>

						</table>
					</form>
					<div class="main_table">
						<table width="100%" cellpadding="0" cellspacing="1"
							class="font_stat">
							<!--修改-->
							<tr class="tr_td">
								<th>效果发生日期</th>
								<th>广告主ID</th>
								<th>广告主</th>
								<th>活动ID</th>
								<th>活动名称</th>
								<th>平台类型</th>
								<th>媒体</th>
								<th>媒体类型</th>
								<th>加价率</th>
								<th>单价(元)</th>
								<th>广告主确认数</th>
								<th>预计收入</th>
								<th>预确认收入(元)</th>
								<th>收入差额</th>
								<!--<th>毛利率</th>-->
							</tr>
							<tr style="text-align: center;">
								<td>汇总</td>
								<td>-</td>
								<td>-</td>
								<td>-</td>
								<td>-</td>
								<td>-</td>
								<td>-</td>
								<td>-</td>
								<td>-</td>
								<td>-</td>
								<td><font color="red">${sum.sum_platform_amount}</font>
								</td>
								<td> <font color="red"><escore:formatMoney
											value="${sum.sum_forecast_money}" maxFractionDigits="2" />
								</font></td>
								<td><font color="red"><escore:formatMoney
											value="${sum.sum_income_money}" maxFractionDigits="2" />
								</font>
								</td>
								<td>-</td>
								<!--<td>-</td>-->
							</tr>
							<!--修改end-->
							<c:forEach items="${entiy}" var="k">
								<tr style="text-align: center;">
									<td>${k.static_date}</td>
									<td>${k.adv_id}</td>
									<td>${k.adv_email}</td>
									<td>${k.campaign_id}</td>
									<td>${k.campaign_name}</td>
									<td>${k.os}</td>
									<td>${k.meia_name}</td>
									<td>${k.type}</td>
									<td>${k.profit_rate}</td>
									<td><escore:formatMoney value="${k.in_price }"
											maxFractionDigits="2" />
									</td>
									<td>${k.confirm_num}</td>
									<td><escore:formatMoney value="${k.forecast_income}"
											maxFractionDigits="2" />
									</td>
									<td><escore:formatMoney value="${k.income_money}"
											maxFractionDigits="2" />
									</td>
									<td><escore:formatMoney value="${k.balance}"
											maxFractionDigits="2" />
									</td>
									<!--<td><c:if test="${k.gross_profit!=null}">
											<fmt:formatNumber value="${k.gross_profit}" pattern="##.##" />
										</c:if>
									</td>-->
								</tr>
							</c:forEach>
							<c:if test="${empty entiy}">
								<tr>
									<td align="center" colspan="15">暂无数据！</td>
								</tr>
							</c:if>


						</table>
					</div>
					<div>${pageInfo.pageInfoStr}</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>