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
<title>运营管理后台财务管理开发者消耗积分收入</title>
<link href="style/main.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/main.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/manage/devCwEffect.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/regist/validate.pack.js?v=${version}"></script>
<script type="text/javascript">
	function checkInput(obj) {
		obj = $.trim(obj);
		var doubleReg = /^￥[-]?[0-9]+$|^￥[-]?[0-9]+[\.][0-9]{0,2}$/;
		if (doubleReg.test(obj)){
			obj = obj.substring(1,obj.length);
		}
		return obj;
	}

	function changeSum(obj, managerMoneyId, oldManagerMoneyId) {
		//新的确认金额
		var newManagerMoney = document.getElementById(managerMoneyId);
		//初始的确认金额
		var oldManagerMoney = document.getElementById(oldManagerMoneyId);
		
		if (!isNaN(newManagerMoney.value)) {
			//统计的确认金额
			var sum = document.getElementById("sum_ManagerMoney").innerHTML;

			if (newManagerMoney.value == "") {
				newManagerMoney.value = 0;
			}
			
			var doubleReg = /^[0-9]+$|^[0-9]+[\.][0-9]{0,2}$/;//金额，小数点后边最多两位小数
			if(!doubleReg.test(newManagerMoney.value)){
				alert("只能输入两位小数");
				newManagerMoney.value = checkInput(oldManagerMoney.value);
				return;
			}
			if(newManagerMoney.value > obj){
				alert("确认收入不能大于预计收入");
				newManagerMoney.value = checkInput(oldManagerMoney.value);
				return;
			}

			//计算后的数据
			var selt = (checkInput(sum) - oldManagerMoney.value)
					+ parseFloat(newManagerMoney.value);
			document.getElementById("sum_ManagerMoney").innerHTML = "￥"+parseFloat(
					selt).toFixed(2);
			oldManagerMoney.value = checkInput(newManagerMoney.value);
		} else {
			newManagerMoney.value = checkInput(oldManagerMoney.value);
			alert("只能输入两位小数");
		}
	}
</script>
</head>

<body>
	<div class="head_bj head_bj_bt">
		<div class="header rel admin_head">
			<div class="clearfix land_top">
				<div class="land_nav fl">运营管理&nbsp;&gt;&nbsp;开发者消耗积分收入</div>
			</div>
		</div>
	</div>
	<div class="main">
		<div class="content clearfix">
			<jsp:include page="../../../jsp/manage/common/manage_left.jsp"></jsp:include>
			<div class="admin_right">
				<div class="content_right content_new">
				<div class="bord_bom1px">开发者消耗积分收入</div>
				<!--新增-->
				<form action="manage!listDevConsume.do" method="get">
					<table>
						<tr>
							<td>开始时间</td>
							<td><input name="startTime" id="startTime" type="text"
								value="${vo.startTime }" class="Wdate" readonly="readonly" /></td>
							<td>结束时间</td>
							<td><input name="endTime" id="endTime" type="text"
								value="${vo.endTime }" class="Wdate" readonly="readonly" /></td>
						</tr>
					</table>
					<!--新增end-->
					<table>
						<tr>
							<td><input name="searchText" type="text"
								value="${vo.searchText }" /></td>
							<td><select name="searchType">
									<c:choose>
										<c:when test="${vo.searchType == 'DEVELOPER_ID'}">
											<!--<option value="developerId" selected="selected">开发者ID</option> -->
											<option value="mail">开发者</option>
											<option value="name">联系人</option>
										</c:when>
										<c:when test="${vo.searchType == 'mail'}">
											<!--<option value="developerId">开发者ID</option> -->
											<option value="mail" selected="selected">开发者</option>
											<option value="name">联系人</option>
										</c:when>
										<c:when test="${vo.searchType == 'name'}">
											<!--<option value="developerId">开发者ID</option>  -->
											<option value="mail">开发者</option>
											<option value="name" selected="selected">联系人</option>
										</c:when>
										<c:otherwise>
											<!--<option value="developerId">开发者ID</option>  -->
											<option value="mail">开发者</option>
											<option value="name">联系人</option>
										</c:otherwise>
									</c:choose>
							</select></td>
							<td><select name="status">
									<option value="">全部</option>
									<c:choose>
									
										<c:when test="${vo.status=='0'}">
											<option value="0" selected="selected">未审核</option>
											<option value="1">审核通过</option>
											<option value="2">已结算</option>
										</c:when>

										<c:when test="${vo.status =='1'}">
											<option value="0">未审核</option>
											<option value="1" selected="selected">审核通过</option>
											<option value="2">已结算</option>
										</c:when>
										<c:when test="${vo.status == '2'}">
											<option value="0">未审核</option>
											<option value="1">审核通过</option>
											<option value="2" selected="selected">已结算</option>
										</c:when>
										<c:otherwise>
											<option value="0">未审核</option>
											<option value="1">审核通过</option>
											<option value="2">已结算</option>
										</c:otherwise>
									</c:choose>
							</select></td>
							<td><input type="submit" value="查询" /></td>
						</tr>
					</table>
				</form>
				${pageInfo }
				<table width="100%" cellpadding="0" cellspacing="1"
					class="table_bod1 font_stat">
					<!--修改-->
					<tr class="tr_td">
						<td>开发者</td>
						<td>联系人</td>
						<td>日期</td>
						<td>应用ID</td>
						<td>应用名称</td>
						<td>应用状态</td>
						<td>消耗积分用户数</td>
						<td>预计消耗积分收入</td>
						<td>运营确认消耗积分收入</td>
						<!--  <td>说明</td>-->
						<td>操作</td>
						<td>批量操作</td>
					</tr>
					<tr style="text-align: center;">
						<td>汇总</td>
						<td>-</td>
						<td>-</td>
						<td>-</td>
						<td>-</td>
						<td>-</td>
						<td style="text-align: left; color: red;">${sum_scoreVisitorAmount }</td>
						<td style="text-align: left; color: red;">
							<escore:formatMoney value="${sum_forecastIncome }" maxFractionDigits="2" /></td>
						<td id="sum_ManagerMoney" style="text-align: left; color: red;">
							<escore:formatMoney value="${sum_ManagerMoney }" maxFractionDigits="2" /></td>
						<td>-</td>
						<td>-</td>
					</tr>
					<form name="batchForm" id="batchForm" action="" method="post">
						<input type="hidden" name="description" id="description" />
						<input type="hidden" name="manageMoney" id="manageMoney" />
						<input type="hidden" name="id" id="id" />
						<input type="hidden" name="confirm_status" id="confirm_status" />

						<c:forEach items="${devList}" var="con">
							<tr>
								<td>
								<a href ="manage!editDev.do?dev_id=${con.developerId }">
									<font color="blue">${con.devEmail }</font>
								</a>
								</td>
								<td>
								<a href ="manage!editDev.do?dev_id=${con.developerId }">
									<font color="blue">${con.realName }</font>
								</a>
								</td>
								<td>${con.statDate }</td>
								<td>
								<a href ="manage!detailDevApp.do?appId=${con.appId }">
								<font color="blue">${con.appId }</font>
								</a>
								</td>
								<td>
								<a href ="manage!detailDevApp.do?appId=${con.appId }">
									<font color="blue">${con.appName }</font>
								</a>
								</td>
								<td><c:choose>
										<c:when test="${con.status eq 0}">未审核</c:when>
										<c:when test="${con.status eq 1}">审核通过</c:when>
										<c:when test="${con.status eq 2}">已结算</c:when>
										<c:when test="${con.status eq -1}">审核未审核</c:when>
									</c:choose>
								</td>
								<td>${con.scoreVisitorAmount_String }</td>
								<td><escore:formatMoney value="${con.forecastIncome }" maxFractionDigits="2" />
										<input type="hidden"
									value="${con.forecastIncome }" id="${ con.id}forecastIncomeId" /></td>
								<td><c:choose>
										<c:when test="${con.status eq 2}">
											<input type="text" size="10" id="${con.id }manageMoney"
												name="mMoney"
												value="<escore:formatMoney value="${con.manageMoney }" maxFractionDigits="2" />"
												disabled="disabled" />
										</c:when>
										<c:when test="${con.status eq 1}">
											<input type="text" size="10" id="${con.id }manageMoney"
												name="mMoney"
												value="<escore:formatMoney value="${con.manageMoney }" maxFractionDigits="2" />"
												disabled="disabled" />
										</c:when>
										<c:when test="${con.status eq 0}">
											<input type="hidden" id="${con.id }oldManagerMoney"
												value="<escore:formatNumber value="${con.forecastIncome }" maxFractionDigits="2" />" />
											<input type="text" size="10" id="${con.id }manageMoney"
												name="mMoney"
												value="<escore:formatNumber value="${con.forecastIncome }" maxFractionDigits="2" />"
												onkeyup="changeSum(${con.forecastIncome },'${con.id }manageMoney','${con.id }oldManagerMoney')" />
										</c:when>
										<c:otherwise>
											<c:choose>
												<c:when test="${con.manageMoney == null}">
													<input type="hidden" id="${con.id }oldManagerMoney"
														value="<escore:formatNumber value="${con.forecastIncome }" maxFractionDigits="2" />" />
													<input type="text" id="${con.id }manageMoney" name="mMoney"
														value="<escore:formatNumber value="${con.forecastIncome }" maxFractionDigits="2" />"
														onkeyup="changeSum(${con.forecastIncome },'${con.id }manageMoney','${con.id }oldManagerMoney')" />
												</c:when>
												<c:otherwise>
													<input type="hidden" id="${con.id }oldManagerMoney"
														value="<escore:formatNumber value="${con.manageMoney }" maxFractionDigits="2" />" />
													<input type="text" id="${con.id }manageMoney" name="mMoney"
														value="<escore:formatNumber value="${con.manageMoney }" maxFractionDigits="2" />"
														onkeyup="changeSum(${con.forecastIncome },'${con.id }manageMoney','${con.id }oldManagerMoney')" />
												</c:otherwise>
											</c:choose>
										</c:otherwise>
									</c:choose> <input type="hidden" name="manageMoneys" id="manageMoneys" />
								</td>
								<!-- <td>
			<c:choose>
				<c:when test="${con.status eq 1}">
					<input type="text" id="${con.id }description" name="dion" value="${con.description }" disabled="disabled"/>
				</c:when>
				<c:otherwise>
					<input type="text" id="${con.id }description" name="dion" value="${con.description }"/>
				</c:otherwise>
			</c:choose>
			
			<input type="hidden" name="descriptions" id="descriptions"/>
			<input type="hidden" name="manageMoneys" id="manageMoneys"/>
		</td> -->
								<td align="center"><c:choose>
										<c:when test="${con.status eq 2}">
											<input type="button" value="通过" disabled="disabled"
												onclick="singleDealAuditDevInOper('${con.id }','1');" />
										</c:when>
										<c:when test="${con.status eq 1}">
											<input type="button" value="通过" disabled="disabled"
												onclick="singleDealAuditDevInOper('${con.id }','1');" />
										</c:when>
										<c:otherwise>
											<input type="button" value="通过"
												onclick="singleDealAuditDevInOper('${con.id }','1');" />
										</c:otherwise>
									</c:choose></td>
								<td><c:choose>
										<c:when test="${con.status eq 2}">
											<input id="checkbox" name="checkbox" type="checkbox"
												value="${con.id }" disabled="disabled" /></td>
								</c:when>
								<c:when test="${con.status eq 1}">
									<input id="checkbox" name="checkbox" type="checkbox"
										value="${con.id }" disabled="disabled" />
									</td>
								</c:when>
								<c:otherwise>
									<input id="checkbox" name="checkbox" type="checkbox"
										value="${con.id }" />
									</td>
								</c:otherwise>
								</c:choose>

							</tr>
						</c:forEach>
						<c:if test="${empty devList}">
							<tr>
								<td align="center" colspan="12" style="text-align: center;">暂无数据！</td>
							</tr>
						</c:if>
					</form>
				</table>
				<!--修改end-->
				<c:if test="${!empty devList}">
					<div class="fr">
						<input type="button" id="btn3" value="全选">批量操作：<input
							type="button" value="通过" onclick="batchDealAuditDevInOper('1')" />
					</div>
				</c:if>
				${pageInfo.pageInfoStr}
			</div>
		</div>
		</div>
	</div>
	<link href="date/ui.daterangepicker.css" rel="stylesheet" type="text/css" />
	<link href="date/ui-lightness/jquery-ui-1.8.4.custom.css" rel="stylesheet" type="text/css" />	
	<script type="text/javascript" src="date/jquery-ui-1.8.4.custom.min.js"></script>
	<script type="text/javascript" src="date/daterangepicker.jQuery.js"></script>
	<script type="text/javascript" src="date/ui.datepicker-zh-CN.js"></script>
	<script type="text/javascript" src="date/calendar.js"></script>
</body>
</html>
