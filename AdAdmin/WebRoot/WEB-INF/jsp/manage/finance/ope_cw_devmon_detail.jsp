<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="escore" uri="/WEB-INF/tld/spring.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>运营管理后台财务管理网站主广告效果下载明细</title>
<link href="${pageContext.request.contextPath}/css/common.css?v=${version}" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript"src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/manage/adEffectConfirm.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/submit.js"></script>

<script type="text/javascript">
function clearStatDate(){
	$('#statDateStartTime').val('');
	$('#statDateEndTime').val('');
}
</script>
</head>
<body>
	<div class="head_bj head_bj_bt">
		<div class="header rel admin_head">
			<div class="clearfix land_top">
			<div class="land_nav fl">
				财务管理&nbsp;>&nbsp;网站主广告效果下载明细
			</div>
			</div>
		</div>
	</div>
<div class="main">

<div class="content clearfix">
	<jsp:include page="../../../jsp/manage/common/manage_left.jsp"></jsp:include>
	<div class="content_right admin_right">
		<div class="bord_bom1px">
			网站主广告效果下载明细
		</div>
		<!--新增-->
		<form action="manage!AdvListDevmonDetailFindAll.do" method="post" id="devmonDetail_Form">
			<table width="100%" border="1" cellpadding="1" cellspacing="0" bordercolor="#85caff">
				<tr>
					<td style="color: blue;" bgcolor="#E8E8E8">广告ID</td>
					<td><input name="ad_Id" id="ad_Id" type="text" value="${vo.ad_Id }" onkeyup="value=value.replace(/[^\d]/g,'')"/></td>
					<td style="color: blue;" bgcolor="#E8E8E8">广告名称</td>
					<td><input name="ad_Name" id="ad_Name" type="text" value="${vo.ad_Name }" /></td>
				</tr>
				<tr>
					<td style="color: blue;" bgcolor="#E8E8E8">网站主ID</td>
					<td><input name="dev_Id" id="dev_Id" type="text" value="${vo.dev_Id }" onkeyup="value=value.replace(/[^\d]/g,'')"/></td>
					<td style="color: blue;" bgcolor="#E8E8E8">网站主名称</td>
					<td><input name="dev_Name" id="dev_Name" type="text" value="${vo.dev_Name }" /></td>
				</tr>
				<tr>
					<td style="color: blue;" bgcolor="#E8E8E8">应用ID</td>
					<td><input name="app_Id" id="app_Id" type="text" value="${vo.app_Id }" onkeyup="value=value.replace(/[^\d]/g,'')"/></td>
					<td style="color: blue;" bgcolor="#E8E8E8">应用名称</td>
					<td><input name="app_Name" id="app_Name" type="text" value="${vo.app_Name }" /></td>
				</tr>
				<tr>
					<td style="color: blue;" bgcolor="#E8E8E8">效果发生日期</td>
					<td style="color: blue;">从<input  id="statDateStartTime" name="statDateStartTime" onClick="WdatePicker()" type="text" value="${vo.statDateStartTime}" class="Wdate" readonly="readonly"/>
					          至<input  id="statDateEndTime" name="statDateEndTime" type="text" onClick="WdatePicker()" value="${vo.statDateEndTime}" class="Wdate" readonly="readonly"/>
					          <input type="button" value="清空" onclick="clearStatDate()"/></td>
					<td style="color: blue;" bgcolor="#E8E8E8">结算类型</td>
					<td><select name="status" id="status">
							<option value="-1" >全部</option>
							<option value="1" <c:if test="${vo.status == 1  }">selected="selected"</c:if>>已结算</option>
							<option value="0" <c:if test="${vo.status == 0  }">selected="selected"</c:if>>未结算</option>
					</select></td>
				</tr>
				<tr>
					
					<td  colspan="4" bgcolor="#E8E8E8"><div style=" text-align: center;"><input type="button" value="查询" id="finance_devmonDetail_Search"/> 										
							<input type="button" value="导出数据" id="finance_devmonDetail_Report"/></div></td>
				</tr>
							
			</table>			 
		<div>${pageInfo }</div>
		</form>
		<table width="100%" cellpadding="0" cellspacing="1" class="table_bod1 font_stat">
			<!--修改-->
			<tr class="tr_td">
				<td>网站主ID</td>
				<td>网站主名称</td>
				<td>应用ID</td>
				<td>应用名称</td>
				<td>广告ID</td>
				<td>广告名称</td>
				<td>效果发生日期</td>
				<td>结算类型</td>
				<td>网站主确认佣金</td>
				<td>业绩提交人</td>
				<td>业绩提交时间</td>
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
				<td style="color: red;"><escore:formatMoney value="${sum.sum_sumMoney }" maxFractionDigits="2" /></td>
				<td>-</td>
				<td>-</td>
			</tr>
			<!--修改end-->
			<c:forEach items="${rdadsr}" var="k">
					<tr style="text-align: center;">						
						<td>${k.dev_Id }</td>
						<td>${k.dev_Email }</td>
						<td >${k.app_Id }</td>
						<td >${k.app_Name }</td>
						<td>${k.ad_id }</td>
						<td>${k.ad_Name }</td>
						<td><fmt:formatDate value="${k.effect_Time}" pattern="yyyy-MM-dd" /></td>
						<td >
						<c:choose >
								<c:when test="${k.status == 1}">
									已结算
								</c:when>
								<c:otherwise>
									未结算
								</c:otherwise>
						</c:choose></td>
						<td><escore:formatMoney value="${k.dev_Cost }" maxFractionDigits="2" /></td>
						<td>${k.finance_Name}</td>
						<td><fmt:formatDate value="${k.create_Time }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
					</tr>
			</c:forEach>
				<c:if test="${empty rdadsr}">
					<tr>
						<td align="center" colspan="15" style="text-align: center;">暂无数据！</td>
					</tr>	
				</c:if>
			
			
		</table>
		<div>${pageInfo }</div>
	</div>
</div>

</div>
</body>
		<!-- 时间控件相关start -->    
		    <link href="${pageContext.request.contextPath}/date/ui.daterangepicker.css" rel="stylesheet" type="text/css" />
		    <link href="${pageContext.request.contextPath}/date/ui-lightness/jquery-ui-1.8.4.custom.css" rel="stylesheet" type="text/css" />    
		    <script type="text/javascript" src="${pageContext.request.contextPath}/date/jquery-ui-1.8.4.custom.min.js"></script>
		   <!--  <script type="text/javascript" src="${pageContext.request.contextPath}/date/daterangepicker.jQuery.js"></script>-->
		    <script type="text/javascript" src="${pageContext.request.contextPath}/date/ui.datepicker-zh-CN.js"></script>
		    <!-- <script type="text/javascript" src="${pageContext.request.contextPath}/date/calendar.js"></script> -->
		<!-- 时间控件相关end -->
</html>
