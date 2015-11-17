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
</script>
</head>
<body>
	<div class="main admin_main">
		<div class="content clearfix">
			<div class="admin_right">
				<div class="content_right content_new">
					<fieldset class="search_fieldset">
						<legend>收支结算(平台)</legend>
						<div id="search_bar">
							<form action="manage!financeRevenueCostYJFList.do" method="post" id="financeRevenueCost">
								<table width="100%" border="1" cellpadding="1" cellspacing="0" bordercolor="#85caff">
									<tr>
										<td style="color: blue" bgcolor="#E8E8E8">广告主ID/名称</td>
										<td><input name="adv" type="text" value="${bean.adv}" /></td>
										<td style="color: blue" bgcolor="#E8E8E8">活动ID/名称</td>
										<td><input name="campaign" type="text" value="${bean.campaign}" /></td>
									</tr>
									<tr>
										<td style="color: blue" bgcolor="#E8E8E8">广告类型</td>
										<td>
											<select name="type_id" id="adForm" style="width: 155px;">
												<option value="">全部</option>
												<option value="0" <c:if test="${bean.type_id ==0}">selected="selected"</c:if> >积分墙</option>
												<option value="1" <c:if test="${bean.type_id ==1}"> selected="selected"</c:if> >推荐墙</option>
												<option value="4" <c:if test="${bean.type_id ==4}"> selected="selected"</c:if> >Banner</option>
												<option value="5" <c:if test="${bean.type_id ==5}"> selected="selected"</c:if> >插屏</option>
											</select>
										</td>
										<td style="color: blue" bgcolor="#E8E8E8">平台类型</td>
										<td>
											<select name="os" id="os" style="width: 155px;">
												<option value="" >全部</option>
												<option value="android" <c:if test="${bean.os == 'android'}">selected="selected"</c:if>>安卓</option>
												<option value="ios" <c:if test="${bean.os =='ios'}">selected="selected"</c:if>>iOs</option>
											</select>
										</td>
									</tr>
									<tr>
										<td style="color: blue" bgcolor="#E8E8E8">发生日期</td>
										<td style="color: blue;">
											<input  id="statDateStartTime" name="statDateStartTime" onclick="WdatePicker()" type="text" value="${bean.statDateStartTime}" class="Wdate" readonly="readonly"/>
						     			    至<input  id="statDateEndTime" name="statDateEndTime" type="text" onclick="WdatePicker()" value="${bean.statDateEndTime}" class="Wdate" readonly="readonly"/>
						         		</td>
						         		 <td style="color: blue" bgcolor="#E8E8E8">媒体类型</td>
										<td>
											<select name="media_type" id="media_type" style="width: 155px;">
												<option value="">全部</option>
												<option value="平台" <c:if test="${bean.media_type =='平台'}">selected="selected"</c:if>>平台</option>
												<option value="渠道" <c:if test="${bean.media_type =='渠道'}">selected="selected"</c:if>>渠道</option>
											</select>
										</td>
									</tr>
								<tr>
				
										<td  colspan="4" bgcolor="#E8E8E8">
											<div  style=" text-align: center;">
												<input type="submit" style="cursor: pointer;" value="查询" />
												<input type="button" onclick="resetAll('financeRevenueCost');" value="重置" onfocus="this.blur();" class="cx"/>
											</div>
										</td>
									</tr>
							</table>
						</form>
			
				<!--修改-->
			<div class="main_table">
			<table width="100%" cellpadding="0"cellspacing="1" class="font_stat" id="tb">		
				<tr class="tr_td">
					<th>序号</th>			
					<th>发生时间</th>
					<th>广告主ID</th>
					<th>广告主</th>
					<th>活动ID</th>
					<th>活动名称</th>
					<th>系统</th>
					<th>媒体</th>
					<th>媒体类型</th>
					<th>广告类型</th>
					<th>预计成本</th>
					<th>预计收入</th>
					<th>预确认成本</th>
					<th>预确认收入</th>									
					<th>毛利</th>									
					<th>毛利率</th>									
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
								<td> <font color="red"><escore:formatMoney
											value="${sum.sum_sys_cost}" maxFractionDigits="2" />
								</font></td>
								<td><font color="red"><escore:formatMoney
											value="${sum.sum_forecast_income}" maxFractionDigits="2" />
								</font>
								</td>
								<td> <font color="red"><escore:formatMoney
											value="${sum.sum_forecast_confirm_cost}" maxFractionDigits="2" />
								</font></td>
								<td><font color="red"><escore:formatMoney
											value="${sum.sum_forecast_confirm_income}" maxFractionDigits="2" />
								</font>
								</td>
								<td><font color="red"><escore:formatMoney
											value="${sum.sum_gross_profit}" maxFractionDigits="2" />
								</font>
								</td>
								<td><fmt:formatNumber value="${sum.sum_gross_profit_rate}"
									type="percent" pattern="#0.000#" />%</td>
							</tr>
					<c:forEach items="${advList}" var="d" varStatus="status">
					<tr>
						<td align="center">
							${status.index+1}
						</td>
						<td align="center">
							${d.static_date}
						</td>
						<td align="center">
							${d.adv_id }
						</td>
						<td align="center">
							${d.company_name }
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
							${d.meia_name}
						</td>					
						<td align="center">
							${d.type}
						</td>					
						<td align="center">
							${d.type_name}
						</td>										
						<td align="center">
							<c:if test="${d.sys_cost!=null }"><escore:formatMoney value="${d.sys_cost }" maxFractionDigits="2" /></c:if>
						</td>
						<td align="center">
						<c:if test="${d.forecast_income!=null }"><escore:formatMoney value="${d.forecast_income }" maxFractionDigits="2" /></c:if>
						</td>
						<td align="center">
						<c:if test="${d.forecast_confirm_cost!=null }"><escore:formatMoney value="${d.forecast_confirm_cost }" maxFractionDigits="2" /></c:if>
						</td>
						<td align="center">
						<c:if test="${d.forecast_confirm_income!=null }"><escore:formatMoney value="${d.forecast_confirm_income }" maxFractionDigits="2" /></c:if>
						</td>
						<td align="center">
						<c:choose>
								<c:when test="${d.gross_profit>=0}">					
								<escore:formatMoney value="${d.gross_profit }" maxFractionDigits="2" /> 
								</c:when>			
								<c:otherwise>
								<font color="red"><escore:formatMoney value="${d.gross_profit }" maxFractionDigits="2" /></font>
								</c:otherwise>
								</c:choose>
						</td>
						<td>
						
						<c:choose>
								<c:when test="${d.gross_profit_rate>=0}">					
								<fmt:formatNumber value="${d.gross_profit_rate}" type="percent" pattern="#0.000#"/>	 
								</c:when>			
								<c:otherwise>
								<font color="red"><fmt:formatNumber value="${d.gross_profit_rate}" type="percent" pattern="#0.000#"/></font>
								</c:otherwise>
								</c:choose>
						%</td>
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