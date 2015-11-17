<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="escore" uri="/WEB-INF/tld/spring.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>运营管理后台收入确认</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/loading/loading.js?v=${version}"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/js/regist/validate.pack.js?v=${version}"></script>
<link href="${pageContext.request.contextPath}/css/regist/validate.css?v=${version}" rel="stylesheet" type="text/css" />

</head>
<script>

	//验证搜索数据
	function checkSearchData(){
		var beginDate = document.getElementById("beginDate").value;
		var endDate = document.getElementById("endDate").value;
		if(endDate < beginDate){//结束时间小于开始时间
			alert("结束时间不能早于开始时间！");
			document.getElementById("endDate").focus();
			return false;
		}
		return true;
	}
</script>
<body>
<div class="main">
	<div class="content clearfix">
			<div class="content_right content_new">
			<div class="bord_bom1px">收入确认</div>
			<form id="addFinanceAdvRecharge" action="manage!addFinanceAdvRecharge.do" method="post">
				<table>
					<tr>
						<td>广告主邮箱</td>
						<td class="advEmail">
							<input id="advEmail" name="advEmail" type="text" value="${advEmail }" reg="^[-_A-Za-z0-9]+@([_A-Za-z0-9]+\.)+[A-Za-z0-9]{2,3}$" tip="请填写有效的邮箱地址！" />*
						</td>
					</tr>
					<tr>
						<td>充值金额</td>
						<td class="rechargeMoney">
							<input id="rechargeMoney" name="rechargeMoney" type="text" value="${rechargeMoney }" reg="^(?!0)[0-9]+$|^(?!0)[0-9]+[\.][0-9]{0,2}$" tip="最低1元并且是整数或者小数，如果是小数最多保留两位！"/>*
						</td>
					</tr>
					<tr>
						<td>充值时间</td>
						<td class="rechargeDate">
							<input type="text" id="rechargeDate" name="rechargeDate" maxlength="20" onfocus="WdatePicker()" class="Wdate" value="${rechargeDate }" reg="^.{1,10}$" tip="时间不能空！"/>*
						</td>
					</tr>
					<tr>
						<td>说明</td>
						<td class="description">
							<textarea id="description" name="description" cols="40" rows="5" value="${description }" reg="^[\s\S]{1,100}$" tip="说明不能空、并且不能超过100个字符！"></textarea>*
						</td>
					</tr>
					<tr>
						<td></td>
						<td><input id="submit" name="submit" type="submit" value="添加" /></td>
					</tr>
				</table>
			</form>
			<div class="bord_bom1px">充值记录查询</div>
			<!--修改-->
			<form action="manage!listFinanceAdvRecharge.do" method="post" onSubmit="return checkSearchData()">
				<table>
					<tr>
						<td>开始时间</td>
						<td>
						<!-- <input type="text" id="beginDate" name="beginDate" maxlength="20" onfocus="WdatePicker()" class="Wdate" value="${beginDate }"/>  -->
							<input name="beginTime" id="beginTime" type="text" value="${bean.beginDate}" class="Wdate" readonly="readonly"/>
						</td>
						<td>结束时间</td>
						<td>
							<!-- <input type="text" id="endDate" name="endDate" maxlength="20" onfocus="WdatePicker()" class="Wdate" value="${endDate }"/>  -->
							<input name="endTime" id="endTime" type="text" value="${bean.endDate}" class="Wdate" readonly="readonly"/>
						</td>
						<td></td>
					</tr>
				</table>
				<table>
					<tr>
						<td>
							<select name="keyword">
								<c:choose>
									<c:when test="${bean.keyword == 'ADV_ID'}">
										 <option value="ADV_ID" selected="selected">广告主ID</option>
							 			 <option value="REAL_NAME">联系人</option>
									</c:when>						
									<c:when test="${bean.keyword == 'REAL_NAME'}">
										<option value="ADV_ID">广告主ID</option>
							 			 <option value="REAL_NAME" selected="selected">联系人</option>
									</c:when>
									<c:otherwise>
										<option value="ADV_ID">广告主ID</option>
							 			 <option value="REAL_NAME">联系人</option>
									</c:otherwise>
								</c:choose>
							</select>
						</td>
						<td><input name="value" value="${bean.value}" type="text" /></td>
						<td><input name="" type="submit" value="搜索" /></td>
					</tr>
				</table>
			
			${pageInfo }
			<table width="100%" cellpadding="0" cellspacing="1" class="table_bod1 font_stat">
				<tr class="tr_td">
					<td>流水号</td>
					<td>广告主</td>
					<td>广告主ID</td>
					<td>联系人</td>
					<td>充值金额</td>
					<td>充值时间</td>
					<td>说明</td>
				</tr>
				<c:forEach items="${advRechargeLogList }" var="k">
					<tr>
						<td>${k.id }</td>
						<td>${k.advEmail }</td>
						<td>${k.adv_Id }</td>
						<td>${k.realName }</td>
						<td><escore:formatMoney value="${k.rechargeMoney }" maxFractionDigits="2" /></td>
						<td>${k.rechargeDate }</td>
						<td>${k.description }</td>
					</tr>
				</c:forEach>
				<c:if test="${empty advRechargeLogList }">
					<tr>
						<td align="center" colspan="6">
							暂无数据！
						</td>
					</tr>
				</c:if>
			</table>
			${pageInfo }
			<!--修改end-->
			</form>
		</div>
		<span class="clear_span"></span>
	</div>
</div>
</body>
		<!-- 时间控件相关start -->    
		    <link href="${pageContext.request.contextPath}/date/ui.daterangepicker.css" rel="stylesheet" type="text/css" />
		    <link href="${pageContext.request.contextPath}/date/ui-lightness/jquery-ui-1.8.4.custom.css" rel="stylesheet" type="text/css" />    
		    <script type="text/javascript" src="${pageContext.request.contextPath}/date/jquery-ui-1.8.4.custom.min.js"></script>
		    <script type="text/javascript" src="${pageContext.request.contextPath}/date/daterangepicker.jQuery.js"></script>
		    <script type="text/javascript" src="${pageContext.request.contextPath}/date/ui.datepicker-zh-CN.js"></script>
		    <script type="text/javascript" src="${pageContext.request.contextPath}/date/calendar.js"></script>
		<!-- 时间控件相关end -->
</html>
