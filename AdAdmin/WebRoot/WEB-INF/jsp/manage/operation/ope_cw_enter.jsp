<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
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
<title>运营管理后台财务管理广告效果确认录入</title>
<link
	href="${pageContext.request.contextPath}/css/common.css?v=${version}"
	rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/manage/adEffectConfirm.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/manage/checkbox.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/dialog/lhgdialog.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js?v=${version}"></script>
<script type="text/javascript">

function campaignEntering(){
	var url = "manage!campaignEnteringDate.do";
	new $.dialog({
		title:'确认收入录入',
		page:url,
		width:350,
		height:300,
		drag:true,
		resize:true,
		cover:true,
		maxBtn:false}).ShowDialog();
}

function refresh(){
	$("#listAd").submit();
}

/**
 * tb效果
 *	
 **/
$(document).ready(function (){
});	

function batchDealAudit(status){
	 if ($("#tb input:checkbox[checked][name='campaign_date']").length > 0) {
		var msg = "";
		var flag = confirm("确认要审核通过吗？");
		
		if(flag){
			$("#batchForm").attr("action","manage!aEBatchDealStatus.do").submit();
		}
	}else{
		alert("请选择要操作的数据！");
		return;
	}
}
</script>

</head>
<body>
	<div class="main">
		<div class="content clearfix">
			<div class="admin_right">
				<div class="content_right content_new">
					<div class="bord_bom1px">收入确认</div>
					<div>
						<table>
							<tr>
								<td>录入格式：</td>
								<td>
									<table width="100%" cellpadding="0" cellspacing="1"
										class="table_bod1 font_stat">
										<tr class="tr_td">
											<td>发生日期</td>
											<td>MAC</td>
											<td>广告ID</td>
											<td>渠道ID</td>
										</tr>
									</table></td>
								<td><a href="demo/确认收入录入IOS.xls">范例下载</a>
								</td>
							</tr>
						</table>
					</div>
					<form action="manage!impAdEffectIosData.do" method="post" enctype="multipart/form-data" onsubmit="return checkIosNull()">
						<div>
							提交excel：&nbsp;<input id="file_ios" name="file_ios" type="file" /><font
								color="red">*请使用97-03版本的excel文件！</font>
						</div>
						<div>
							<font color="red">${msg }</font>
						</div>
						<div>
							<input type="submit" value="确认" />
						</div>
					</form>
						<div style="size: 20px; border-color: red;">《录入广告主确认回来的MAC》
						</div>
						<table>
							<tr>
								<td>录入格式：</td>
								<td>
									<table width="100%" cellpadding="0" cellspacing="1"
										class="table_bod1 font_stat">
										<tr class="tr_td">
											<td>MAC</td>
										</tr>
									</table></td>
								<td><a href="demo/确认收入录入IOS.xls">范例下载</a>
								</td>
							</tr>
						</table>
				</div>

				<form action="manage!impAdEffectData.do" method="post"
					enctype="multipart/form-data" onsubmit="return checkNull()">
					<div>
						提交excel：&nbsp;<input id="file1" name="file1" type="file" /><font
							color="red">*请使用97-03版本的excel文件！</font>
					</div>
					<div>
						<font color="red">${msg }</font>
					</div>
					<div>
						<input type="submit" value="确认" />
					</div>
				</form>
					<form action="manage!listAdEffectData.do" method="get" id="listAd">
						<input type="hidden" name="pageRecord" value="${pageRecord}"
							id="pageRecord" />
						<table width="100%" border="1" cellpadding="1" cellspacing="0"
							id="ios_ca" bordercolor="#85caff">
							<tr>
								<td style="color: blue;" bgcolor="#E8E8E8">活动ID/名称</td>
								<td><input name="campaign"  type="text"	value="${bean.campaign}"/></td>
								<td style="color: blue;" bgcolor="#E8E8E8">渠道ID/名称</td>
								<td><input name="channel" type="text" value="${bean.channel}"/></td>
							</tr>
							<tr>
								
							</tr>
							<tr>
								<td style="color: blue;" bgcolor="#E8E8E8">发生日期</td>
								<td><input name="beginTime" id="beginTime" type="text"
									value="${bean.beginTime}" class="Wdate" readonly="readonly"
									onclick="WdatePicker()" /> 至<input name="endTime" id="endTime"
									type="text" value="${bean.endTime}" class="Wdate"
									readonly="readonly" onclick="WdatePicker()" />
								</td>
								<td style="color: blue;" bgcolor="#E8E8E8">状态</td>
								<td><select name="status" id="status" style="width: 155px;">
										<option value=" ">全部</option>
										<option value="0"
											<c:if test="${bean.status ==0}">selected="selected"</c:if>>未发布</option>
										<option value="1"
											<c:if test="${bean.status ==1}">selected="selected"</c:if>>已发布</option>
								</select></td>
							</tr>
							<tr>
								<td style="color: blue;" bgcolor="#E8E8E8">类型</td>
								<td><select name="ch_pl_type" id="ch_pl_type"
									style="width: 155px;">
										<option value=" ">全部</option>
										<option value="0"
											<c:if test="${bean.ch_pl_type ==0}">selected="selected"</c:if>>渠道</option>
										<option value="1"
											<c:if test="${bean.ch_pl_type ==1}">selected="selected"</c:if>>平台</option>
								</select></td>
							</tr>
							<tr>
								<td colspan="4" bgcolor="#E8E8E8">
									<div style="text-align: center;">
										<input type="submit" value="查询" id="activityFindAll" />
									</div>
								</td>
							</tr>

						</table>
					</form> 
					<div class="main_table">
					<form id="batchForm" action="manage!adverEffectDealStatus.do" method="post">
						<table width="100%" cellpadding="0" cellspacing="1" id="tb" class="font_stat">
							<!--列名start-->
							<tr class="tr_td">
								<th>发生日期</th>
								<th>活动ID</th>
								<th>活动名称</th>
								<th>渠道ID</th>
								<th>渠道名称</th>
								<th>状态</th>
								<th>广告主确认数</th>
								<th>确认收入</th>
								<th>类型</th>
								<th>录入人</th>
								<th>批量</th>
							</tr>

							<c:forEach items="${entiy}" var="k">
								<input type="hidden" name="ad_id" value="${k.ad_id}" id="ad_id" />
								<tr>
									<td>${k.stat_date}</td>
									<td><font color="blue">${k.campaign_id }</font>
									</td>
									<td><font color="blue">${k.campaign_name }</font>
									</td>
									<td><font color="blue">${k.channel_id }</font>
									</td>
									<td><font color="blue">${k.name }</font>
									</td>
									<c:choose>
										<c:when test="${k.status == 0}">
											<td>未发布</td>
										</c:when>
										<c:when test="${k.status == 1}">
											<td>已发布</td>
										</c:when>
									</c:choose>
									<td><font color="blue">${k.income_amount}</font>
									</td>
									<td>${k.income_money}</td>
									<c:choose>
										<c:when test="${k.ch_plf_type == 0}">
											<td>渠道</td>
										</c:when>
										<c:when test="${k.ch_plf_type == 1}">
											<td>平台</td>
										</c:when>
									</c:choose>
									<td>-</td>
									<c:choose>
										<c:when test="${k.status == 0}">
											<td><input id="checkbox" name="campaign_date"
												type="checkbox"
												value="${k.ad_id}|${k.stat_date}|${k.ch_plf_type}" />
											</td>
										</c:when>
										<c:otherwise>
											<td><input id="checkbox" name="campaign_date"
												type="checkbox"
												value="${k.ad_id}|${k.stat_date}|${k.ch_plf_type}"
												disabled="disabled" />
											</td>
										</c:otherwise>
									</c:choose>
								</tr>
							</c:forEach>
							<c:if test="${empty entiy}">
								<tr>
									<td align="center" colspan="17" style="text-align: center;">暂无数据！</td>
								</tr>
							</c:if>
							<!--数据end-->
						</table>
					</form>
					</div>
					<div>${pageInfo.pageInfoStr}</div>
					<c:if test="${ not empty entiy}">
						<div class="fr">
							<input type="button" id="btn3" value="全选" />批量操作：<input
								type="button" value="发布" onclick="batchDealAudit('1');" />
						</div>
					</c:if> <span class="clear_span"></span>
			</div>
		</div>

	</div>
</body>
</html>
