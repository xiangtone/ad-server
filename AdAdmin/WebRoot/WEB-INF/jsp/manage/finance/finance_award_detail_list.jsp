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
<title>运营管理后台平台管理平台设置</title>
<link href="${pageContext.request.contextPath}/css/common.css?v=${version}"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/dialog/lhgdialog.min.js"></script>
<link href="${pageContext.request.contextPath}/css/regist/validate.css?v=${version}"
	rel="stylesheet" type="text/css" />
		<style type="text/css">
			.reg_ok,.reg_no{ 
				background:url(${pageContext.request.contextPath}/images/icons-signup.png) no-repeat;
				padding-left:25px;
				display:inline-block; 
				height:23px;
				line-height:23px;
				font-size:14px;
			}
			.reg_ok{
				background-position:5px 1px;
				color:#090;
			}
			.reg_no{
				background-position:5px -50px;
				color:red;
			}
			.suggest_link {
				background-color: #FFFFFF;
				padding: 2px 6px 2px 6px;
			}
			.suggest_link_over {
				background-color: #E8F2FE;
				padding: 2px 6px 2px 6px;
			}
			
			.suggest_link_sel {
				background-color: #039CEA;
				padding: 2px 6px 2px 6px;
			}
			#search_suggest {
				position: absolute; 
				background-color: #FFFFFF; 
				width:298px;
				border:1px solid #000000;			
			}
			#search_suggest2 {
				position: absolute; 
				background-color: #FFFFFF; 
				width:298px;
				border:1px solid #000000;			
			}			
		</style>
<script type="text/javascript">
	
	function search_SetDateClean() {
		var searchSetDateBegin = document.getElementById("searchSetDateBegin");
		var searchSetDateEnd = document.getElementById("searchSetDateEnd");
		searchSetDateBegin.value = "";
		searchSetDateEnd.value = "";
	}
	
	function search_ActCycleClean() {
		var searchActCycleBegin = document
				.getElementById("searchActCycleBegin");
		var searchActCycleEnd = document.getElementById("searchActCycleEnd");
		searchActCycleBegin.value = "";
		searchActCycleEnd.value = "";
	}
	function search_report() {
		$("#searchDevListAwardDetail").attr("action",
				"manage!searchDevListAwardDetail.do").submit();
	}
	function derived_report() {
		$("#searchDevListAwardDetail").attr("action",
				"manage!awardDetailDownloadExcel.do").submit();
	}
	
	//Mouse over function
	function suggestOver(div_value) {
		div_value.className = 'suggest_link_over';
	}
	//Mouse out function
	function suggestOut(div_value) {
		div_value.className = 'suggest_link';
	}
	

	function addAward(){
		var url = "manage!addAward.do";
		new $.dialog({
			title:'新增费用录入',
			page:url,
			width:675,
			height:270,
			drag:true,
			resize:true,
			cover:true,
			maxBtn:false}).ShowDialog();

}
		function refresh(){
			$("#searchDevListAwardDetail").submit();
		}	
</script>
</head>
<body>
	<div class="main">
		<div class="content clearfix">
			<div class="admin_right">
				<div class="content_right content_new">
				<div class="">平台费用</div>
				<form id="searchDevListAwardDetail" action="manage!searchDevListAwardDetail.do">
					<fieldset>
						<legend>平台费用查询</legend>
						<table>
							<tr>
								<td><font style="margin-right: 14px;" color="blue">网站主id</font></td>
								<td class="searchDevId"><input id="searchDevId" name="dev_id"
									type="text" maxlength="20"
									style="border: #999 1px solid; height: 20px; background: #fff no-repeat right;"
									value="${vo.dev_id }"  onkeyup="value=value.replace(/[^\d]/g,'')"/></td>
							</tr>
							<tr>
								<td><font style="margin-right: 11px;" color="blue">网站主名称</font></td>
								<td>
									<div>
										<input id="searchDevName" onkeydown="searchDevNameKeydown(event);" name="dev_account" type="text" maxlength="20" style="border: #999 1px solid; height: 20px; background: #fff no-repeat right;" value="${vo.dev_account }" onkeyup="searchSuggest2(event);" />
									</div>
									<div id="search_suggest2" style="display:none;">
									</div>
								</td>
							</tr>
							<tr>
								<td><font style="margin-right: 11px;" color="blue">活动名称</font></td>
								<td><input id="searchActName" name="activity_name"
									type="text" maxlength="20"
									style="border: #999 1px solid; height: 20px; background: #fff no-repeat right;"
									value="${vo.activity_name }" /></td>
							</tr>							
						</table>
						<table>					
							<tr>
								<td><font style="margin-right: 11px;" color="blue">活动周期</font></td>
								<td><font style="margin-right: 5px;">从</font></td>
								<td><input type="text" id="searchActCycleBegin"
									name="activity_begin" maxlength="20"
									onfocus="WdatePicker()" class="Wdate"
									value="${vo.activity_begin }" /></td>
								</td>
								<td><font style="margin-left: 5px; mamargin-right: 5px;">至</font></td>
								<td><input type="text" id="searchActCycleEnd"
									name="activity_end" maxlength="20" onfocus="WdatePicker()"
									class="Wdate" value="${vo.activity_end }" /></td>
								<td><input name="searchActCycleClean"
									id="searchActCycleClean" type="button"
									style="margin-left: 10px;" onclick="search_ActCycleClean()"
									value="清空" /></td>
							</tr>
						</table>
						<table>
							<tr>
								<td><input name="searchButton" id="searchButton"
									type="button" onclick="search_report()" value="查询" /></td>
								<td><input name="derivedButton" id="derivedButton"
									type="button" style="margin-left: 30px;"
									onclick="derived_report()" value="导出数据" /></td>
							</tr>
						</table>
					</fieldset>		
	<input type="button" value="新增费用" onclick="addAward()" />
				</form>
				<table width="100%" cellpadding="0" cellspacing="1"
					class="table_bod1 font_stat">
					<tr class="tr_td">
						<td>网站主ID</td>
						<td>网站主名称</td>
						<td>活动名称</td>
						<td>金额(元)</td>
						<td>活动周期</td>
						<td>结算日期</td>
						<td>结算人</td>
						<td>结算类型</td>
					</tr>
					<c:if test="${!empty voList}">
						<td align="center">汇总</td>
						<td align="center">--</td>
						<td align="center">--</td>
						<td align="center"><escore:formatMoney value="${summary.sum_sumMoney}" maxFractionDigits="2" />
						<td align="center">--</td>
						<td align="center">--</td>
						<td align="center">--</td>
						<td align="center">--</td>
					</c:if>
					<c:if test="${!empty voList}">
						<c:forEach items="${voList}" var="report">
							<tr>
								<td align="center">${report.dev_id }
								</td>
								<td align="center">${report.dev_account }</td>
								<td align="center">${report.activity_name }</td>
								<td align="center"><escore:formatMoney value="${report.award_money }" maxFractionDigits="2" /></td>
								<td align="center">${report.activity_begin
									}&nbsp;至&nbsp;${report.activity_end }</td>
								<td align="center"><fmt:formatDate
										value="${report.stat_date }" pattern="yyyy-MM-dd" /></td>
								<td align="center">${report.finance_name }</td>
								<td align="center">${report.finance_type }</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty voList}">
						<td colspan="14" align="center" style="text-align: center;">暂无记录！</td>
					</c:if>
				</table>
				${pageInfo.pageInfoStr}
			</div>
			<span class="clear_span"></span>
		</div>
		</div>
	</div>
</body>
</html>