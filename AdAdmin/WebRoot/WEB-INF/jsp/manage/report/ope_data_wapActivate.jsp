<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>运营管理后台平台管理平台设置</title>
<link href="${pageContext.request.contextPath}/css/main.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/manage/adEffectConfirm.js"></script>
<link href="${pageContext.request.contextPath}/css/regist/validate.css?v=${version}"
	rel="stylesheet" type="text/css" />
<script type="text/javascript">
	$(function() {
		$("input[reg],textarea[reg]").blur(function() {
			validate($(this), true);
		});

		$("form").submit(function() {
			var isSubmit = true;
			for ( var i = 0; i < this.length; i++) {
				var input = $(this[i]);
				if (input.attr("reg") != null) {
					if (input.attr("disabled") != "disabled") {
						if (input.is(":input") || input.is(":textarea")) {
							if (!validate(input, false)) {
								isSubmit = false;
							} else if (input.context.name == "email") {
								if ($(".emailText").length >= 1) {
									isSubmit = false;
								}
							}
						} else if (input.is(":select")) {
							if (!validate(input, false)) {
								isSubmit = false;
							}
						}
					}
				}
			}
			return isSubmit;
		});
	});
	function validate(obj, ajaxFlag) {
		var reg = new RegExp(obj.attr("reg"));
		var objValue = obj.attr("value");
		var objdefValue = obj.attr("defvalue");
		var objTip = obj.attr("tip");
		var objTips = obj.attr("tips");
		// 取得提示信息
		var objectName = obj.context.name;
		var spanName = '.' + objectName + 'Text';
		var span = $(spanName);
		if (obj.parent().parent().css("display") == "none") {
			return true;
		}
		if (objValue != '' || objdefValue == undefined) {
			if (!reg.test(objValue)) {
				if (obj.is(":input") || obj.is(":textarea")) {
					obj.addClass("input_validation-failed");
					if (span.length == 1) {
						span.remove();
					}
					$(
							'<span class="' + objectName + 'Text" style="color:red">'
									+ objTip + '</span>').appendTo(
							'.' + obj.context.name);
				} else {
					obj.addClass("select_validation-failed");
				}
				return false;
			} else {
				if (obj.is(":input") || obj.is(":textarea")) {
					if (objectName == "confimPassword") {
						if ($("#confimPassword").val() != $("#password").val()) {
							obj.addClass("input_validation-failed");
							if (span.length == 1) {
								span.remove();
							}
							$(
									'<span class="' + objectName + 'Text" style="color:red">'
											+ objTips + '</span>').appendTo(
									'.' + obj.context.name);
							return false;
						}
					}
					if (objectName == "email") {
						if (ajaxFlag) {
							exists(obj, span);
							var emailText = $(".emailText");
							if ($(".emailText").length >= 1) {
								return false;
							}
						}
					}
					if (span.length == 1) {
						span.remove();
					}
					obj.removeClass("input_validation-failed");
				} else {
					obj.removeClass("select_validation-failed");
				}
				return true;
			}
		} else {
			if (!reg.test(objdefValue)) {
				if (obj.is(":input") || obj.is(":textarea")) {
					obj.addClass("input_validation-failed");
					if (span.length == 1) {
						span.remove();
					}
					$(
							'<span class="' + objectName + 'Text" style="color:red">'
									+ objTip + '</span>').appendTo(
							'.' + obj.context.name);
				}
				return false;
			}
		}
		return true;
	}
	function search_SetDateClean() {
		var searchBegin = document.getElementById("searchBegin");
		var searchEnd = document.getElementById("searchEnd");
		searchBegin.value = "";
		searchEnd.value = "";
	}
	function search_report() {
		$("#searchWapActivate").attr("action",
				"manage!wapActivateStatListSearch.do").submit();
	}
	function derived_report() {
		$("#searchWapActivate").attr("action",
				"manage!wapActivateStatListDownloadExcel.do").submit();
	}
	function import_report() {
		$("#importWapActivate").attr("action",
				"manage!wapActivateStatListImportExcel.do").submit();
	}
	function impChannelimport() {
		var file = document.getElementById('file').value;
		if (file == "") {
			alert("请选择要上传的文件");
		} else {
			$("#impChannelimportForm").attr("action",
					"manage!wapActivateStatListImportExcel.do").submit();
		}
	}
	function save() {
		$("#saveBoundary").attr("action",
				"manage!wapActivateStatListSaveBoundary.do").submit();
	}
	function impChannelSingle() {
		var ad_id = document.getElementById('ad_id').value;
		var valid_amount = document.getElementById('valid_amount').value;
		var stat_date = document.getElementById('stat_date').value;
		if (ad_id == "") {
			 $('#ad_id').attr('style','border: #CC0033 2px solid;');  
		} else if(valid_amount==""){
			 $('#valid_amount').attr('style','border: #CC0033 2px solid;');  
		}else if(stat_date==""){
		    $('#stat_date').attr('style','border: #CC0033 2px solid;'); 
		     
			}else if(ad_id != "" && valid_amount !=""&& stat_date!=""){			
				$("#searchWapActivate").attr("action",
				"manage!wapActivateStatListSingle.do").submit();
			}		
	}
	
$(document).ready(function () {
	var  wapsuccessdate=$('#wapsuccessdate').val();	
	if(wapsuccessdate==1){
	alert("提交成功！");			
	window.location.href="${pageContext.request.contextPath}/manage!wapActivateStatListManage.do";
		}
});	
	
</script>
</head>
<body>
	<div class="head_bj head_bj_bt">
		<div class="header rel admin_head">
			<div class="clearfix land_top">
				<div class="land_nav fl">数据统计&nbsp;&gt;&nbsp;渠道统计</div>
			</div>
		</div>
	</div>
	<div class="main">
		<div class="content clearfix">
			<jsp:include page="../../../jsp/manage/common/manage_left.jsp"></jsp:include>
			<div class="admin_right">
				<div class="content_right content_new">
				<div class="">渠道统计</div>

				<div>
					<table>
						<tr>
							<td>广告激活数录入格式：</td>
							<td>
								<table width="100%" cellpadding="0" cellspacing="1"
									class="table_bod1">
									<tr class="tr_td">
										<td>广告ID</td>
										<td>确认激活数</td>
										<td>日期</td>
									</tr>
								</table>
							</td>
							<td><a href="demo/广告激活数录入.xls">范例下载</a></td>
						</tr>
					</table>
					<form action="manage!wapActivateStatListImportExcel.do"	method="post" id="impChannelimportForm"
						name="impChannelimportForm" enctype="multipart/form-data">
						<div>
							提交excel：&nbsp;<input id="file" name="file" type="file" /><font
								color="red">*请使用97-03版本的excel文件！</font>
						</div>
						<div>
							<font color="red">${msg }</font>
						</div>
						<div>
							<input type="button" value="确认" onclick="impChannelimport()" />
						</div>
						<input type="hidden" value="${wapsuccessdate}" id="wapsuccessdate"/>
					</form>
				</div>

				<form id="searchWapActivate">
					<fieldset>
						<legend>广告激活数查询</legend>
                 <table width="100%">
                      <tr>
                       <td>
						<table>
							<tr>
								<td><font style="margin-right: 14px;" color="blue">广告ID</font></td>
								<td class="searchAdId"><input id="searchAdId"
									name="searchAdId" type="text" maxlength="20"
									style="border: #999 1px solid; height: 20px; background: #fff no-repeat right;"
									value="${vo_Activate.ad_id }" reg="^[0-9]*$" tip="请输入数字 !" /></td>
							</tr>
							<tr>
								<td><font style="margin-right: 11px;" color="blue">广告名称</font></td>
								<td><input id="searchAdName" name="searchAdName"
									type="text" maxlength="20"
									style="border: #999 1px solid; height: 20px; background: #fff no-repeat right;"
									value="${vo_Activate.ad_name }" /></td>
							</tr>
							<tr>
								<td><font style="margin-right: 11px;" color="blue">渠道ID</font></td>
								<td><input id="searchChannleId" name="searchChannleId"
									type="text" maxlength="20"
									style="border: #999 1px solid; height: 20px; background: #fff no-repeat right;"
									value="${vo_Activate.channel_id }" /></td>
							</tr>
							<tr>
								<td><font style="margin-right: 39px;" color="blue">渠道名称</font></td>
								<td><input id="searchChannleName" name="searchChannleName"
									type="text" maxlength="20"
									style="border: #999 1px solid; height: 20px; background: #fff no-repeat right;"
									value="${vo_Activate.channel_name }" /></td>
							</tr>
							<tr>
								<td><font style="margin-right: 39px;" color="blue">状态</font></td>
								<td><select name="searchChannleType" id="searchChannleType">
										<option value="All"
											<c:if test="${searchChannleType == 'All'}">selected="selected"</c:if>>
											全部</option>
										<option value="NoImport"
											<c:if test="${searchChannleType == 'NoImport'}">selected="selected"</c:if>>
											未导入</option>
										<option value="Imported"
											<c:if test="${searchChannleType == 'Imported'}">selected="selected"</c:if>>
											已导入</option>
								</select>
								<td>
							</tr>
							<tr>
								<td><font style="margin-right: 39px;" color="blue">广告状态</font></td>
								<td><select name="seach_ad_status" id="seach_ad_status">
										<option value="All"
											<c:if test="${vo_Activate.seach_ad_status == 'All'}">selected="selected"</c:if>>
											全部</option>
										<option value="online"
											<c:if test="${vo_Activate.seach_ad_status == 'online'}">selected="selected"</c:if>>
											上线</option>
										<option value="downline"
											<c:if test="${vo_Activate.seach_ad_status == 'downline'}">selected="selected"</c:if>>
											下线</option>
								</select>
								<td>
							</tr>
						</table>
						<td width="47%">	
						</td>	
					<td>	  	
		<table >
			<tr>
				<td>广告ID:</td>
				<td ><input name="ad_id" id="ad_id" type="text"
					value="${ad_id}"onkeyup="value=value.replace(/[^\d]/g,'')" maxlength="4" /></td>
			</tr>
			<tr>
				<td>激活数:</td>
				<td><input name="valid_amount" id="valid_amount" type="text"
					value="${valid_amount}" onkeyup="value=value.replace(/[^\d]/g,'')" maxlength="8"/></td>				
			</tr>
			<tr>
			<td>日期:</td>
				<td >
				<input name="stat_date" id="stat_date" type="text" value="${stat_date}" onfocus="WdatePicker()" maxlength="20" class="Wdate" readonly="readonly" />
				</td>
			</tr>
			<tr>
			<td><input type="button" value="确认"  onclick="impChannelSingle()"/></td>
			</tr>
		</table>
					</td>
				</tr>
			</table>
						<table>
							<tr>
								<td><font style="margin-right: 11px;" color="blue">日期</font></td>
								<td><font style="margin-right: 5px;">从</font></td>
								<td><input type="text" id="searchBegin" name="searchBegin"
									maxlength="20" onfocus="WdatePicker()" class="Wdate"
									value="${setBegin }" /></td>
								<td><font style="margin-left: 5px; mamargin-right: 5px;">至</font></td>
								<td><input type="text" id="searchEnd" name="searchEnd"
									maxlength="20" onfocus="WdatePicker()" class="Wdate"
									value="${setEnd }" /></td>
								<td><input name="searchSetDateClean"
									id="searchSetDateClean" type="button"
									style="margin-left: 10px;" onclick="search_SetDateClean()"
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
				</form>            
				<form id="saveBoundary">
					<fieldset>
						<table align="right">
							<tr>
								<td><font style="margin-right: 14px;" color="blue">舍入分界点</font></td>
								<td class="boundary"><input id="boundary" name="boundary"
									type="text" maxlength="20"
									style="border: #999 1px solid; height: 20px; background: #fff no-repeat right;"
									value="${boundary }" reg="^[0-9]{1,5}$" tip="请输入1-5位整数!" /></td>
								<td><input name="saveButton" id="saveButton" type="button"
									style="margin-left: 30px;" onclick="save()" value="保存" /></td>
							</tr>
						</table>
					</fieldset>
				</form>

				
				<table width="100%" cellpadding="0" cellspacing="1"	class="table_bod1 font_stat">
					<tr class="tr_td">
						<td>日期</td>
						<td>广告ID</td>
						<td>广告名称</td>
						<td>渠道ID</td>
						<td>渠道名称</td>
						<td>广告状态</td>
						<td>状态</td>
						<td>激活数</td>
						<td>广告主确认激活数</td>
					</tr>
					<c:if test="${!empty voList}">
						<td align="center">汇总</td>
						<td align="center">--</td>
						<td align="center">--</td>
						<td align="center">--</td>
						<td align="center">--</td>
						<td align="center">--</td>
						<td align="center">--</td>
						<td align="center">
										<c:choose>
										<c:when test="${setBegin >= numberformat_StartTime }">
										<fmt:formatNumber value="${summaryAct }" type="number" pattern="0"/>
										</c:when>
										<c:otherwise>
										<fmt:formatNumber value="${summaryAct }" type="number" pattern="0.0"/>
										</c:otherwise>
										</c:choose>
						</td>
						<td align="center">
										<c:choose>
										<c:when test="${setBegin >= numberformat_StartTime }">
										<fmt:formatNumber value="${summaryVal }" type="number" pattern="0"/>
										</c:when>
										<c:otherwise>
										<fmt:formatNumber value="${summaryVal }" type="number" pattern="0.0"/>
										</c:otherwise>
										</c:choose>
						</td>
					</c:if>
					<c:if test="${!empty voList}">
						<c:forEach items="${voList}" var="report">
							<tr>
								<td align="center">${report.stat_date }</td>
								<td align="center">${report.ad_id }</td>
								<td align="center">${report.ad_name }</td>
								<td align="center">${report.channel_id }</td>
								<td align="center">${report.channel_name }</td>
								<td align="center">
									<c:if test="${report.ad_status ==0 }">待提交</c:if>
									<c:if test="${report.ad_status ==2 }">通过</c:if>
									<c:if test="${report.ad_status ==3 }">未通过</c:if>
									<c:if test="${report.ad_status ==4 }">上线</c:if>
									<c:if test="${report.ad_status ==5 }">下线</c:if>
									<c:if test="${report.ad_status ==6 }">终止</c:if>
									<c:if test="${report.ad_status ==7 }">余额不足</c:if>
									<c:if test="${report.ad_status ==8 }">不在推广时间</c:if>
								</td>
								<td align="center">${report.channel_type }</td>
								<td align="center">${report.activity_cnt }</td>
								<td align="center">
										<c:choose>
										<c:when test="${report.stat_date >= numberformat_StartTime }">
										<fmt:formatNumber value="${report.valid_amount }" type="number" pattern="#"/>
										</c:when>
										<c:otherwise>
										<fmt:formatNumber value="${report.valid_amount }" type="number" pattern="#.0"/>
										</c:otherwise>
										</c:choose>
								</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty voList}">
						<td colspan="14" align="center" style="text-align: center;">暂无记录！</td>
					</c:if>
				</table>
				
			</div>
			<span class="clear_span"></span>
		</div>
	</div>
	</div>
</body>
</html>