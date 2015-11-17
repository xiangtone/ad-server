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
<link href="${pageContext.request.contextPath}/css/common.css?v=${version}" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/regist/validate.css?v=${version}" rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/dialog/lhgdialog.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js?v=${version}"></script>
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
	
	var s_index=1;
	var s_len=0;
	function searchDevNamekeydown(event) {
		s_len=$("#search_suggest2 div").length;
		e=event?event :(window.event ? window.event : null);
		var keyCode=e.keyCode||e.which||e.charCode;
		if($("#search_suggest2 div[class='suggest_link_sel']").html()==null){
			$("#search_suggest2 div:first").attr("class","suggest_link_sel");
			s_index=1;
		}
		if((keyCode==40)||keyCode==38){
		
			var toDown=(keyCode==40);
			var current=$("#search_suggest2 div[class='suggest_link_sel']");
			if(toDown){
				s_index=s_index+1;
			}else{
				s_index=s_index-1;
			}
			var next;
			if(s_index==s_len+1){
				next=$("#search_suggest2 div:first");
				s_index=1;
			}else if(s_index==0){
				next=$("#search_suggest2 div:last");
				s_index=s_len;
			}else{
				if(toDown){
					next=current.next();
				}else{
					next=current.prev();
				}
			}
			next.attr("class","suggest_link_sel");
			current.attr("class","suggest_link");
		}
		if(keyCode==13){
			var obj=$("#search_suggest2 div[class='suggest_link_sel']");
			s_index=1;
			setSearch2(obj.html(),obj.attr("v_id"));
		}
	}
	
	function setSearch2(value,id) {
		document.getElementById('search_suggest2').style.display="none";
		document.getElementById('searchDevName').value = value;
		document.getElementById('search_suggest2').innerHTML = '';
	}
	
	

	//Click function
	function setSearch(value,id) {
		document.getElementById('search_suggest').style.display="none";
		document.getElementById('entryDevName').value = value;
		document.getElementById('search_suggest').innerHTML = '';
		$("#entryDevId").val(id);
		v_flag=0;
		if($("#entryDevId").parent().find('span')){
			$("#entryDevId").parent().find('span').remove();
		}
		$("#entryActName").focus();
	}
	
	
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
		$("#devPunishDetail").attr("action",
				"manage!devPunishDetail.do").submit();
	}
	function dowm() {
		$("#devPunishDetail").attr("action",
				"manage!excelDevPunishDetail.do").submit();
	}

	function addPunish(){
		var url = "manage!addPunish.do";
		new $.dialog({
			title:'新增费用录入',
			page:url,
			width:570,
			height:210,
			drag:true,
			resize:true,
			cover:true,
			maxBtn:false}).ShowDialog();

}
		function refresh(){
			$("#devPunishDetail").submit();
		}	
</script>
</head>
<body>
	
	<div class="main">
		<div class="content clearfix">
			<div class="content_right admin_right">
				<div class="">网站主扣费</div>
	<input type="button" value="新增扣费" onclick="addPunish()" />
				<form id="devPunishDetail">
					<fieldset>
						<legend>网站主扣费查询</legend>
						<table>
							<tr>
								<td><font style="margin-right: 14px;" color="blue">网站主ID</font></td>
								<td class="searchDevId"><input id="searchDevId" name="dev_id"
									type="text" maxlength="20"
									style="border: #999 1px solid; height: 20px; background: #fff no-repeat right;"
									value="${bean.dev_id }" 
									onkeyup="value=value.replace(/[^\d]/g,'')"/></td>
								<td><font style="margin-right: 11px;" color="blue">网站主账号</font></td>
								<td>
									<div>
										<input id="searchDevName" name="dev_account" onkeydown="searchDevNamekeydown(event);" onkeyup="searchSuggest2(event);"	type="text" maxlength="50" style="border: #999 1px solid; height: 20px; background: #fff no-repeat right;" value="${bean.dev_account }" />
									</div>
									<div id="search_suggest2" style="display:none;">
									</div>
								</td>
							</tr>							
							<tr>
								<td><font style="margin-right: 11px;" color="blue">操作时间:</font></td>
								<td><input type="text" id="searchSetDateBegin"
									name="stat_date_begin" maxlength="20"
									onfocus="WdatePicker()" class="Wdate" value="${bean.stat_date_begin }" /></td>
								<td><font style="margin-left: 5px; mamargin-right: 5px;">至</font></td>
								<td><input type="text" id="searchSetDateEnd"
									name="stat_date_end" maxlength="20" onfocus="WdatePicker()"
									class="Wdate" value="${bean.stat_date_end }" /></td>
								<td><input name="searchSetDateClean"
									id="searchSetDateClean" type="button"
									style="margin-left: 10px;" onclick="search_SetDateClean()"
									value="清空" /></td>
										<td><font style="margin-right: 39px;" color="blue">操作人:</font></td>
								<td><input id="searchSetPeo" name="finance_name"
									type="text" maxlength="20"
									style="border: #999 1px solid; height: 20px; background: #fff no-repeat right;"
									value="${bean.finance_name }" /></td>
							</tr>
						</table>
						<table>
							<tr>
								<td><input name="searchButton" id="searchButton"
									type="button" onclick="search_report()" value="查询" /></td>
								<td><input name="dowm_report" id="dowm_report"
									type="button" onclick="dowm()" value="导出报表" /></td>
							</tr>
						</table>
					</fieldset>
					${pageInfo.pageInfoStr}
				</form>
				<div class="main_table">
				<table width="100%" cellpadding="0" cellspacing="1"	class="font_stat">
					<tr class="tr_td">
						<th>开发者ID</th>
						<th>开发者账号</th>
						<th>扣款原因</th>
						<th>金额(元)</th>
						<th>操作时间</th>
						<th>操作人</th>
					</tr>
					<c:if test="${!empty voList}">
						<td align="center">汇总</td>
						<td align="center">--</td>
						<td align="center">--</td>
						<td align="center"><escore:formatMoney value="${summary.sum_sumMoney }" maxFractionDigits="2" /></td>
						<td align="center">--</td>
						<td align="center">--</td>
					</c:if>
					<c:if test="${!empty voList}">
						<c:forEach items="${voList}" var="report">
							<tr>
								<td align="center">${report.dev_id }</td>
								<td align="center">${report.dev_account }</td>
								<td align="center">${report.activity_name }</td>
								<td align="center"><escore:formatMoney value="${report.award_money }" maxFractionDigits="2" /></td>
								<td align="center"><fmt:formatDate
										value="${report.stat_date }" pattern="yyyy-MM-dd" /></td>
								<td align="center">${report.finance_name }</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty voList}">
						<td colspan="14" align="center" style="text-align: center;">暂无记录！</td>
					</c:if>
				</table>
				</div>
				${pageInfo.pageInfoStr}
				<span class="clear_span"></span>
			</div>
		</div>
	</div>
</body>
</html>
