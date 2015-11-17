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
<title>运营管理后台平台成本明细 </title>
<link href="${pageContext.request.contextPath}/css/common.css?v=${version}" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/manage/adEffectConfirm.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/manage/checkbox.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/submit.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/dialog/lhgdialog.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js?v=${version}"></script>
</head>
<body>
	<div class="main">
		<div class="content clearfix">
			<div class="main_table" style="width: 1070px;height: 320px;overflow-y: scroll;" >
				<table width="100%" cellpadding="0" cellspacing="1" id="tb"	class="table_bod1 font_stat">
					<tr class="tr_td">	
						<th width="3%">序号</th>
						<th>发生时间</th>
						<th>成本时间</th>
						<th>来源</th>
						<th>广告主id</th>
						<th>广告主名称</th>
						<th>活动id</th>
						<th>活动名称</th>
						<th>开发者id</th>
						<th>应用id</th>
						<th>应用名称</th>
						<th>效果数</th>
						<th>结算方式</th>
						<th>单价</th>
						<th>结算金额</th>
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
							--
						</td>
						<td align="center">
							<font color="red"><escore:formatMoney
											value="${sum.sum_pha_money}" maxFractionDigits="2" />
								</font>
						</td>						
					</tr>
					<c:forEach items="${list}" var="vo" varStatus="status">
						<tr style="text-align: center;">
							<td style="text-align: center;">
								${status.index+1}
							</td>
							<td>${month_stat_date}至${month_end_date}</td>
							<td>${str_stat}</td>
							<td>平台</td>
							<td>${vo.adv_id}</td>
							<td>${vo.company_name}</td>
							<td>${vo.campaign_id}</td>
							<td>${vo.campaign_name}</td>
							<td>${vo.dev_id}</td>
							<td>${vo.app_id}</td>
							<td>${vo.app_name}</td>
							<td>${vo.income_amount}</td>
							<td>${vo.charge_type}</td>
							<td><fmt:formatNumber  type="number" pattern="#0.00" value="${vo.price}"/></td>
							<td><fmt:formatNumber  type="number" pattern="#0.0#" value="${vo.income_money}"/></td>
						</tr>
					</c:forEach>
					<c:if test="${empty list}">
						<tr>
							<td align="center" colspan="15" style="text-align: center;">暂无数据！</td>
						</tr>
					</c:if>
				</table>
				</div>
				<div>${pageInfo.pageInfoStr}</div>
			</div>
		</div>
	</div>
</body>
</html>